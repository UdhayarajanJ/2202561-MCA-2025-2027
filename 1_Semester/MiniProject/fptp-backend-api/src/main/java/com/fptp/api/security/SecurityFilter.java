package com.fptp.api.security;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;
import java.util.List;

@Component
public class SecurityFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${spring.identity.config.url}")
    private String identityConfigURL;

    @Value("${spring.identity.issuer.url}")
    private String issuerURL;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String path = httpRequest.getRequestURI();

        if (path.startsWith("/v3/api-docs")
                || path.startsWith("/swagger-ui")
                || path.startsWith("/swagger-resources")
                || path.equals("/swagger-ui.html")) {
            chain.doFilter(request, response);
            return;
        }

        String authHeader = httpRequest.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("Missing Authorization header");
            return;
        }

        try {
            String token = authHeader.substring(7);
            DecodedJWT decodedJWT = JWT.decode(token);

            String jwkUrl = identityConfigURL + "/protocol/openid-connect/certs";
            logger.info("JWT Config URL" + jwkUrl);
            logger.info("JWT Issuer URL" + issuerURL);
            JwkProvider jwkProvider = new UrlJwkProvider(new URL(jwkUrl));
            Jwk jwk = jwkProvider.get(decodedJWT.getKeyId());

            Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey());
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuerURL)
                    .withAudience("account")
                    .acceptLeeway(1)
                    .build();

            verifier.verify(decodedJWT);

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(decodedJWT.getSubject(), null, List.of());

            SecurityContextHolder.getContext().setAuthentication(auth);
            chain.doFilter(request, response);


        } catch (TokenExpiredException ex) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            httpResponse.getWriter().write("Token expired");
        } catch (JWTVerificationException ex) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            httpResponse.getWriter().write("Invalid token");
        } catch (Exception ex) {
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

package com.fptp.api;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("First Park Then Pay - API")
                        .version("1.0.0")
                        .description("A smart parking system that follows a 'First Park, Then Pay' model. Users can park their vehicles first and are charged based on the duration of stay.")
                        .contact(new Contact().name("FPTP Dev Team").email("udhayarajanj@outlook.com").url("https://fptp.com"))
                );
    }
}

export const environment = {
    API_BASE_URL: "http://localhost:4003",
    DATE_FORMAT: 'dd-MM-yyyy hh:mm:ss a',
    LOGS_ENABLED: true,
    keycloak: {
        enable: true,
        authority: 'http://localhost:4001',
        redirectUri: 'http://localhost:4200',
        postLogoutRedirectUri: 'http://localhost:4200/logout',
        realm: 'fptp',
        clientId: '1d02c020-0fe8-40ab-9939-a9d465220e1e',
    },
};

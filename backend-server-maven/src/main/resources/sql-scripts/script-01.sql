CREATE TABLE oauth2_registered_client (
    id VARCHAR(255) NOT NULL,
    client_id VARCHAR(255) NOT NULL,
    client_id_issued_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    client_secret VARCHAR(255) DEFAULT NULL,
    client_secret_expires_at TIMESTAMP DEFAULT NULL,
    client_name VARCHAR(255) NOT NULL,
    client_authentication_methods VARCHAR(1000) NOT NULL,
    authorization_grant_types VARCHAR(1000) NOT NULL,
    redirect_uris VARCHAR(1000) DEFAULT NULL,
    scopes VARCHAR(1000) NOT NULL,
    client_settings VARCHAR(2000) NOT NULL,
    token_settings VARCHAR(2000) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO oauth2_registered_client (
    id,
    client_id,
    client_secret,
    client_name,
    client_authentication_methods,
    authorization_grant_types,
    redirect_uris,
    scopes,
    client_settings,
    token_settings
) VALUES (
    1,  -- A randomly generated UUID
    'oidc-client',                           -- From the client_id in the URL
    NULL,                                    -- No client secret for public clients
    'OIDC Client',                           -- A descriptive name for the client
    'none',                                  -- As specified in your previous configuration
    'authorization_code',                    -- As specified in your previous configuration
    'http://localhost:4200/auth/callback',   -- As specified in your previous configuration
    'openid',                                -- Default scope for OIDC
    '{"requireAuthorizationConsent":false,"requireProofKey":true}', -- Based on your ClientSettings
    '{}'                                     -- Default empty TokenSettings
);
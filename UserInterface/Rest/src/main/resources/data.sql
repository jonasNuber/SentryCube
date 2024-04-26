-- creating default realm for super-admin
INSERT INTO realm (realm_name, enabled, user_manageable, registrable, verify_email, email_login, access_token_lifespan, refresh_token_lifespan, description) VALUES ('default_realm', true, false, false, false, false, 5, 10, 'The default realm for initial configuration and the super-admins.');
-- creating super-admin role
INSERT INTO realm_role (realm_role_id, realm_name, role_name, description) VALUES ('adf2232c-0bc3-4794-838e-9eb0cb6bc264', 'default_realm', 'admin', 'the highest administrative role within the application.');
-- creating super-admin user
INSERT INTO "user" (user_id, realm_name, username, email, password_hash, password_salt, personal_data_id, creation_time, last_login, status) VALUES ('c54f7148-93c6-4978-8452-1e89d85e0264', 'default_realm', 'admin', 'admin@admin.com', 'hIkBfIrc8v1LOMIZq5yycEjWWHRZoD9xF4ergt8FStY=', 'jmjr8grNN5E7KlHNxhXt50w4EoqZiaJ5RtzG6Wu3B4Q=', null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ACTIVE');
-- adding admin role to admin user
INSERT INTO user_realm_role (user_realm_role_id, user_id, realm_role_id) VALUES ('04739953-8642-402b-89cb-3f6c482b9cbc', 'c54f7148-93c6-4978-8452-1e89d85e0264', 'adf2232c-0bc3-4794-838e-9eb0cb6bc264');
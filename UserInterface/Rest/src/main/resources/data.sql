-- creating default realm for super-admin
INSERT INTO realm (realm_name, enabled, user_manageable, registrable, verify_email, email_login, access_token_lifespan, refresh_token_lifespan, description)
VALUES ('default_realm', true, false, false, false, false, 5, 10, 'The default realm for initial configuration and the super-admins.');
-- creating super-admin role
INSERT INTO realm_role (realm_role_id, realm_name, role_name, description)
VALUES ('adf2232c-0bc3-4794-838e-9eb0cb6bc264', 'default_realm', 'admin', 'the highest administrative role within the application.');
-- creating super-admin user
INSERT INTO sentry_cube_user (user_id, realm_name, username, email, password_hash, password_salt, personal_data_id, creation_time, last_login, status)
VALUES ('c54f7148-93c6-4978-8452-1e89d85e0264', 'default_realm', 'admin', 'admin@admin.com', 'hIkBfIrc8v1LOMIZq5yycEjWWHRZoD9xF4ergt8FStY=', 'jmjr8grNN5E7KlHNxhXt50w4EoqZiaJ5RtzG6Wu3B4Q=', null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ACTIVE');
-- adding admin role to admin user
INSERT INTO user_realm_role (user_realm_role_id, user_id, realm_role_id)
VALUES ('04739953-8642-402b-89cb-3f6c482b9cbc', 'c54f7148-93c6-4978-8452-1e89d85e0264', 'adf2232c-0bc3-4794-838e-9eb0cb6bc264');

-- insert test data for ASE
-- Insert the Realm
INSERT INTO realm (realm_name, enabled, user_manageable, registrable, verify_email, email_login, access_token_lifespan, refresh_token_lifespan, description)
VALUES ('test_realm', true, true, true, false, true, 5, 10, 'This is a test realm');

-- Insert the Client
INSERT INTO client (client_id, realm_name, name, client_secret, protocol, grant_type, enabled, access_type, description)
VALUES ('7f9d0a78-f7e4-4f64-a74e-7681a9ff993c', 'test_realm', 'test_client', 'test_secret', 'OAUTH_2', 'RESOURCE_OWNER_PASSWORD_CREDENTIALS', true, 'PUBLIC', 'This is a test client');

-- Insert the User
INSERT INTO sentry_cube_user (user_id, realm_name, username, email, password_hash, password_salt, personal_data_id, creation_time, last_login, status)
VALUES ('f8b2a775-7fd2-4db3-9dcd-882e4d477e47', 'test_realm', 'Testuser', 'testuser@example.com', 'hIkBfIrc8v1LOMIZq5yycEjWWHRZoD9xF4ergt8FStY=', 'jmjr8grNN5E7KlHNxhXt50w4EoqZiaJ5RtzG6Wu3B4Q=', NULL, current_timestamp, current_timestamp, 'UNVERIFIED');

-- Insert the Realm Role
INSERT INTO realm_role (realm_role_id, realm_name, role_name, description)
VALUES ('8a8a9055-9d8e-47f2-8091-522d8ec7bb55', 'test_realm', 'test_realm_role', 'This is a test realm role');

-- Insert the Client Role
INSERT INTO client_role (client_role_id, client_id, role_name, description)
VALUES ('4e6d1f12-c469-4c7e-b9d7-8538e1d54234', '7f9d0a78-f7e4-4f64-a74e-7681a9ff993c', 'test_client_role', 'This is a test client role');

-- Assign the Realm Role to the User
INSERT INTO user_realm_role (user_realm_role_id, user_id, realm_role_id)
VALUES ('6a795b85-4c5e-4b0a-987d-5d4a8f0d6975', 'f8b2a775-7fd2-4db3-9dcd-882e4d477e47', '8a8a9055-9d8e-47f2-8091-522d8ec7bb55');

-- Assign the Client Role to the User
INSERT INTO user_client_role (user_client_role_id, user_id, client_role_id)
VALUES ('ac33c5d5-1c5a-4d3a-87d1-14e5b4e36e0a', 'f8b2a775-7fd2-4db3-9dcd-882e4d477e47', '4e6d1f12-c469-4c7e-b9d7-8538e1d54234');

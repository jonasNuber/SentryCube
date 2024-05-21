CREATE TYPE "gender" AS ENUM (
  'MALE',
  'FEMALE',
  'NON_BINARY',
  'NOT_LISTED',
  'UNSURE',
  'DID_NOT_WANT_TO_SPECIFY'
);

CREATE TYPE "status" AS ENUM (
  'ACTIVE',
  'INACTIVE',
  'SUSPENDED',
  'UNVERIFIED',
  'DELETED'
);

CREATE TYPE "protocol" AS ENUM (
  'OAUTH_2'
);

CREATE TYPE "grant_type" AS ENUM (
  'AUTHORIZATION_CODE',
  'CLIENT_CREDENTIALS',
  'RESOURCE_OWNER_PASSWORD_CREDENTIALS'
);

CREATE TYPE "access_type" AS ENUM (
  'PUBLIC',
  'CONFIDENTIAL'
);

CREATE TYPE "token_type" AS ENUM (
  'ACCESS',
  'REFRESH'
);

CREATE TABLE "realm" (
  "realm_name" varchar(255) PRIMARY KEY,
  "enabled" bool NOT NULL DEFAULT true,
  "user_manageable" bool NOT NULL DEFAULT true,
  "registrable" bool NOT NULL DEFAULT true,
  "verify_email" bool NOT NULL DEFAULT false,
  "email_login" bool NOT NULL DEFAULT true,
  "access_token_lifespan" integer NOT NULL DEFAULT 5,
  "refresh_token_lifespan" integer NOT NULL DEFAULT 10,
  "description" varchar(500),

  CONSTRAINT positive_access_token_lifespan CHECK (access_token_lifespan > 0),
  CONSTRAINT positive_refresh_token_lifespan CHECK (refresh_token_lifespan > 0)
);

CREATE TABLE "client" (
  "client_id" uuid PRIMARY KEY,
  "realm_name" varchar(255) NOT NULL,
  "name" varchar(255) NOT NULL,
  "client_secret" varchar(255) NOT NULL,
  "protocol" protocol NOT NULL DEFAULT 'OAUTH_2',
  "grant_type" grant_type NOT NULL DEFAULT 'AUTHORIZATION_CODE',
  "enabled" bool NOT NULL DEFAULT true,
  "access_type" access_type NOT NULL DEFAULT 'PUBLIC',
  "description" varchar(500)
);

CREATE TABLE "redirect_uri" (
  "redirect_uri_id" uuid PRIMARY KEY,
  "client_id" uuid NOT NULL,
  "uri" varchar(510) NOT NULL
);

CREATE TABLE "realm_role" (
  "realm_role_id" uuid PRIMARY KEY,
  "realm_name" varchar(255),
  "role_name" varchar(255),
  "description" varchar(500),

  CONSTRAINT "unique_realm_role" UNIQUE ("realm_name", "role_name")
);

CREATE TABLE "client_role" (
  "client_role_id" uuid PRIMARY KEY,
  "client_id" uuid,
  "role_name" varchar(255),
  "description" varchar(500),

  CONSTRAINT "unique_client_role" UNIQUE ("client_id", "role_name")
);

CREATE TABLE "user_client_role" (
  "user_client_role_id" uuid PRIMARY KEY,
  "user_id" uuid,
  "client_role_id" uuid
);

CREATE TABLE "user_realm_role" (
  "user_realm_role_id" uuid PRIMARY KEY,
  "user_id" uuid,
  "realm_role_id" uuid
);

CREATE TABLE "sentry_cube_user" (
  "user_id" uuid PRIMARY KEY,
  "realm_name" varchar NOT NULL,
  "username" varchar(255) NOT NULL,
  "email" varchar(255) NOT NULL,
  "password_hash" varchar(255) NOT NULL,
  "password_salt" varchar(255) NOT NULL,
  "personal_data_id" uuid,
  "creation_time" timestamp NOT NULL DEFAULT current_timestamp,
  "last_login" timestamp NOT NULL,
  "status" status NOT NULL DEFAULT 'UNVERIFIED',

  CONSTRAINT "unique_username_realm" UNIQUE ("username", "realm_name"),
  CONSTRAINT "unique_email_realm" UNIQUE ("email", "realm_name"),
  CONSTRAINT "username_format_check" CHECK ("username" ~ '^[a-zA-Z0-9_]+$')
);

CREATE TABLE "attribute" (
  "attribute_id" uuid PRIMARY KEY,
  "user_id" uuid NOT NULL,
  "name" varchar(255) NOT NULL,
  "value" varchar(255)
);

CREATE TABLE "address" (
  "address_id" uuid PRIMARY KEY,
  "country" varchar(255) NOT NULL,
  "postal_code" varchar(100) NOT NULL,
  "state" varchar(255),
  "city" varchar(255),
  "street_name" varchar(255),
  "street_number" varchar(50)
);

CREATE TABLE "personal_data" (
  "personal_data_id" uuid PRIMARY KEY,
  "name_id" uuid,
  "date_of_birth" date,
  "gender" gender NOT NULL DEFAULT 'DID_NOT_WANT_TO_SPECIFY',
  "phone_number" varchar(120),
  "address_id" uuid,
  "profile_pic_data" bytea
);

CREATE TABLE "name" (
  "name_id" uuid PRIMARY KEY,
  "first_name" varchar(255) NOT NULL,
  "last_name" varchar(255) NOT NULL
);

CREATE TABLE "session" (
  "session_id" uuid PRIMARY KEY,
  "user_id" uuid NOT NULL,
  "client_id" uuid NOT NULL,
  "creation_time" timestamp NOT NULL DEFAULT current_timestamp,
  "expiration_time" timestamp NOT NULL,
  "ip_address" varchar(60),
  "user_agent" varchar(255)
);

CREATE TABLE "token" (
  "token_id" uuid PRIMARY KEY,
  "session_id" uuid NOT NULL,
  "encoded_token" varchar(2024) NOT NULL,
  "key_id" integer NOT NULL,
  "token_type" token_type NOT NULL DEFAULT 'ACCESS',
  "creation_time" timestamp NOT NULL,
  "expiration_time" timestamp NOT NULL
);

CREATE INDEX ON "client" ("realm_name");

CREATE INDEX ON "redirect_uri" ("client_id");

CREATE INDEX ON "sentry_cube_user" ("username", "realm_name");

CREATE INDEX ON "sentry_cube_user" ("email", "realm_name");

CREATE INDEX ON "attribute" ("user_id");

CREATE INDEX ON "session" ("user_id", "client_id");

CREATE INDEX ON "token" ("session_id");

ALTER TABLE "session" ADD FOREIGN KEY ("user_id") REFERENCES "sentry_cube_user" ("user_id");

ALTER TABLE "session" ADD FOREIGN KEY ("client_id") REFERENCES "client" ("client_id");

ALTER TABLE "sentry_cube_user" ADD FOREIGN KEY ("realm_name") REFERENCES "realm" ("realm_name") ON DELETE SET NULL ON UPDATE NO ACTION;

ALTER TABLE "sentry_cube_user" ADD FOREIGN KEY ("personal_data_id") REFERENCES "personal_data" ("personal_data_id") ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE "attribute" ADD FOREIGN KEY ("user_id") REFERENCES "sentry_cube_user" ("user_id") ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE "personal_data" ADD FOREIGN KEY ("name_id") REFERENCES "name" ("name_id") ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE "personal_data" ADD FOREIGN KEY ("address_id") REFERENCES "address" ("address_id") ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE "client" ADD FOREIGN KEY ("realm_name") REFERENCES "realm" ("realm_name") ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE "realm_role" ADD FOREIGN KEY ("realm_name") REFERENCES "realm" ("realm_name") ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE "client_role" ADD FOREIGN KEY ("client_id") REFERENCES "client" ("client_id") ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE "user_realm_role" ADD FOREIGN KEY ("user_id") REFERENCES "sentry_cube_user" ("user_id") ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE "user_realm_role" ADD FOREIGN KEY ("realm_role_id") REFERENCES "realm_role" ("realm_role_id") ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE "user_client_role" ADD FOREIGN KEY ("user_id") REFERENCES "sentry_cube_user" ("user_id") ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE "user_client_role" ADD FOREIGN KEY ("client_role_id") REFERENCES "client_role" ("client_role_id") ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE "redirect_uri" ADD FOREIGN KEY ("client_id") REFERENCES "client" ("client_id") ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE "token" ADD FOREIGN KEY ("session_id") REFERENCES "session" ("session_id") ON DELETE CASCADE ON UPDATE NO ACTION;

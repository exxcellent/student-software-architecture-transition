\connect thesis;
DROP SCHEMA IF EXISTS thesis CASCADE;

CREATE SCHEMA thesis AUTHORIZATION thesis;

SET search_path TO thesis;

CREATE DOMAIN GERMAN_POSTAL_ROUTING_NUMBER AS TEXT
CHECK (
    VALUE ~ '^(?!01000|99999)(0[1-9]\d{3}|[1-9]\d{4})$'
);

CREATE DOMAIN EMAIL AS TEXT
CHECK (
    VALUE ~ '^\S+@\S+$'
);

CREATE DOMAIN PHONE_NUMBER AS TEXT
CHECK (
    VALUE ~ '\+(9[976]\d|8[987530]\d|6[987]\d|5[90]\d|42\d|3[875]\d|2[98654321]\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|4[987654310]|3[9643210]|2[70]|7|1)\d{1,14}$'
);

-- tables

CREATE SEQUENCE inspector_inspector_id_seq START WITH 1000 INCREMENT BY 1;

CREATE TABLE inspector (
    inspector_id    DECIMAL(19,0)  NOT NULL    PRIMARY KEY  DEFAULT nextval('inspector_inspector_id_seq'),
    first_name      TEXT    NOT NULL,
    last_name       TEXT    NOT NULL,

    version                 INTEGER                     NOT NULL,
    created_at_utc          TIMESTAMP WITH TIME ZONE    NOT NULL,
    created_by              TEXT                        NOT NULL,
    last_modified_at_utc    TIMESTAMP WITH TIME ZONE,
    last_modified_by        TEXT,

    UNIQUE (first_name, last_name)
);

CREATE SEQUENCE location_location_id_seq START WITH 1000 INCREMENT BY 1;

CREATE TABLE location (
    location_id    DECIMAL(19,0)    NOT NULL   PRIMARY KEY  DEFAULT nextval('location_location_id_seq'),
    name           TEXT,
    street         TEXT                          NOT NULL,
    zip            GERMAN_POSTAL_ROUTING_NUMBER  NOT NULL,
    city           TEXT                          NOT NULL,
    latitude       FLOAT                         NOT NULL,
    longitude      FLOAT                         NOT NULL,

    version                 INTEGER                     NOT NULL,
    created_at_utc          TIMESTAMP WITH TIME ZONE    NOT NULL,
    created_by              TEXT                        NOT NULL,
    last_modified_at_utc    TIMESTAMP WITH TIME ZONE,
    last_modified_by        TEXT
);

CREATE SEQUENCE contact_contact_id_seq START WITH 1000 INCREMENT BY 1;

CREATE TABLE contact (
    contact_id     DECIMAL(19,0)  NOT NULL    PRIMARY KEY  DEFAULT nextval('contact_contact_id_seq'),
    first_name     TEXT,
    last_name      TEXT,
    phone_number   PHONE_NUMBER   NOT NULL,
    email          EMAIL,

    version                 INTEGER                     NOT NULL,
    created_at_utc          TIMESTAMP WITH TIME ZONE    NOT NULL,
    created_by              TEXT                        NOT NULL,
    last_modified_at_utc    TIMESTAMP WITH TIME ZONE,
    last_modified_by        TEXT,

    UNIQUE (first_name, last_name, phone_number)
);

CREATE SEQUENCE appointment_appointment_id_seq START WITH 1000 INCREMENT BY 1;

CREATE TABLE appointment (
    appointment_id  DECIMAL(19,0)   NOT NULL    PRIMARY KEY  DEFAULT nextval('appointment_appointment_id_seq'),
    inspector_id    DECIMAL(19,0)   NOT NULL    REFERENCES inspector (inspector_id) ON DELETE CASCADE,
    location_id     DECIMAL(19,0)   NOT NULL    REFERENCES location (location_id) ON DELETE RESTRICT,
    contact_id      DECIMAL(19,0)               REFERENCES contact (contact_id) ON DELETE RESTRICT,

    title             TEXT,
    description       TEXT,
    type              VARCHAR(50)  NOT NULL,
    priority          VARCHAR(50)  NOT NULL,
    date              DATE         NOT NULL,
    travel_duration   INTERVAL,
    start_time        TIME,
    end_time          TIME,
    finished          BOOLEAN      NOT NULL    DEFAULT false,

    version                 INTEGER                     NOT NULL,
    created_at_utc          TIMESTAMP WITH TIME ZONE    NOT NULL,
    created_by              TEXT                        NOT NULL,
    last_modified_at_utc    TIMESTAMP WITH TIME ZONE,
    last_modified_by        TEXT

);

-- technical tables

CREATE SEQUENCE application_user_user_id_seq START WITH 1000 INCREMENT BY 1;

CREATE TABLE application_user (
    user_id     DECIMAL(19,0)   NOT NULL    PRIMARY KEY  DEFAULT nextval('application_user_user_id_seq'),
    name        TEXT     NOT NULL,
    password    TEXT     NOT NULL,

    version                 INTEGER                     NOT NULL,
    created_at_utc          TIMESTAMP WITH TIME ZONE    NOT NULL,
    created_by              TEXT                        NOT NULL,
    last_modified_at_utc    TIMESTAMP WITH TIME ZONE,
    last_modified_by        TEXT,

    UNIQUE (name)
);

CREATE TABLE permission (
    name        TEXT     NOT NULL    PRIMARY KEY,

    version                 INTEGER                     NOT NULL,
    created_at_utc          TIMESTAMP WITH TIME ZONE    NOT NULL,
    created_by              TEXT                        NOT NULL,
    last_modified_at_utc    TIMESTAMP WITH TIME ZONE,
    last_modified_by        TEXT
);

CREATE TABLE user_permission (
    user_id     DECIMAL(19,0)     NOT NULL    REFERENCES application_user (user_id),
    permission  TEXT              NOT NULL    REFERENCES permission (name),

    version                 INTEGER                     NOT NULL,
    created_at_utc          TIMESTAMP WITH TIME ZONE    NOT NULL,
    created_by              TEXT                        NOT NULL,
    last_modified_at_utc    TIMESTAMP WITH TIME ZONE,
    last_modified_by        TEXT,

    PRIMARY KEY(user_id, permission)
);


-- permissions

GRANT ALL ON SCHEMA thesis TO thesis;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA thesis TO thesis;
GRANT USAGE ON ALL SEQUENCES IN SCHEMA thesis TO thesis;

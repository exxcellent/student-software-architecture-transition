\connect thesis;
SET search_path TO thesis;

INSERT INTO thesis.application_user (user_id, name, password, version, created_at_utc, created_by, last_modified_at_utc, last_modified_by) VALUES (1, 'ADMIN', '$2a$10$6HxIJ6fWLL9xsf92xCAILutytFl0rYAzh9fnIlMWgQ7EfWA9nUtN.', 1, '2020-04-26 13:36:28.271000', 'IMPORT', null, null);
INSERT INTO thesis.application_user (user_id, name, password, version, created_at_utc, created_by, last_modified_at_utc, last_modified_by) VALUES (2, 'USER', '$2a$10$6HxIJ6fWLL9xsf92xCAILutytFl0rYAzh9fnIlMWgQ7EfWA9nUtN.', 1, '2020-04-26 13:36:43.634000', 'IMPORT', null, null);
INSERT INTO thesis.application_user (user_id, name, password, version, created_at_utc, created_by, last_modified_at_utc, last_modified_by) VALUES (3, 'IMPORT', '$2a$10$6HxIJ6fWLL9xsf92xCAILutytFl0rYAzh9fnIlMWgQ7EfWA9nUtN.', 1, '2020-04-26 13:36:58.846000', 'IMPORT', null, null);
\connect thesis;
SET search_path TO thesis;

INSERT INTO thesis.inspector (inspector_id, version, first_name, last_name, created_at_utc, created_by, last_modified_at_utc, last_modified_by) VALUES (1, 1, 'Max', 'Mustermann', '2020-04-25 15:11:05.765000', 'IMPORT', null, null);
INSERT INTO thesis.inspector (inspector_id, version, first_name, last_name, created_at_utc, created_by, last_modified_at_utc, last_modified_by) VALUES (2, 1, 'Erika', 'Mustermann', '2020-04-25 15:12:32.312000', 'IMPORT', null, null);
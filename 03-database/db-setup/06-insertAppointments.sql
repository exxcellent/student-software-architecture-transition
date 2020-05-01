\connect thesis;
SET search_path TO thesis;

INSERT INTO thesis.appointment (id, inspector_id, location_id, contact_id, title, description, type, priority, date, travel_duration, start_time, end_time, finished, version, created_at_utc, created_by, last_modified_at_utc, last_modified_by) VALUES (1, 1, 2, 1, null, null, 'NORMAL', 'NORMAL', '2020-05-04', '0 years 0 mons 0 days 1 hours 30 mins 0.00 secs', '09:00:00', '09:15:00', false, 1, '2020-05-01 22:09:48.934000', 'IMPORT', null, null);
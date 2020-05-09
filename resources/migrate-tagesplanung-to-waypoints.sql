INSERT INTO waypoint (
                      appointment_id,
                      inspector_id,
                      date,
                      order_index,
                      status,
                      category,
                      address,
                      latitude,
                      longitude,
                      duration,
                      contact_name,
                      contact_phone_number,
                      contact_email,
                      version,
                      created_at_utc,
                      created_by
)

SELECT a.id,
       p.inspector_id,
       a.date,
       0                                          AS order_index,
       'PENDING'                                  AS status,
       'APPOINTMENT'                              AS category,
       l.street || ', ' || l.zip || ' ' || l.city AS address,
       l.latitude,
       l.longitude,
       a.appointment_duration                     AS duration,
       c.first_name || ' ' || c.last_name         AS contact_name,
       c.phone_number                             AS contact_phone_number,
       c.email                                    AS contact_email,
       1 AS version,
       NOW() AS created_at_utc,
       'IMPORT' AS created_by
FROM appointment a
         LEFT OUTER JOIN process p on a.process_id = p.id
         LEFT OUTER JOIN contact c on p.contact_id = c.id
         LEFT OUTER JOIN location l on p.location_id = l.id
WHERE date = '2020-05-09'
;
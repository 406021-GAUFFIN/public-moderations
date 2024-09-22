INSERT INTO sanction_type (id, created_by, created_date, last_updated_at , last_updated_by , description, name, sanction_severity) VALUES
(999, 'john_doe', '2024-06-12 10:00:00', '2024-06-12 10:00:00', 'john_doe', 'Ruidos molestos', 'Ruidos molesos', 'HIGH');



INSERT INTO fine (id , created_by, created_date , last_updated_at , last_updated_by, description , moderation_state , price, user_id , sanction_type )VALUES
    (999, 'john_doe', '2024-06-12 10:00:00', '2024-06-12 10:00:00', 'john_doe', 'Molestaba', 'ON_ASSEMBLY', 10000, 1, 999);


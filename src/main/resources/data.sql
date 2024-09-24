INSERT INTO sanction_type (id, created_by, created_date, last_updated_at , last_updated_by , description, name, sanction_severity) VALUES
(999, 1, '2024-06-12 10:00:00', '2024-06-12 10:00:00', 1, 'Ruidos molestos', 'Ruidos molesos', 'HIGH');



INSERT INTO fine (id , created_by, created_date , last_updated_at , last_updated_by,  fine_state ,  plot_id , sanction_type )VALUES
    (999, 1, '2024-06-12 10:00:00', '2024-06-12 10:00:00', 1, 'ON_ASSEMBLY',  1, 999);
INSERT INTO fine (id , created_by, created_date , last_updated_at , last_updated_by,  fine_state ,plot_id , sanction_type )VALUES
    (2000, 1, '2024-06-12 10:00:00', '2024-06-12 10:00:00', 1,  'ON_ASSEMBLY',  1, 999);
INSERT INTO fine (id , created_by, created_date , last_updated_at , last_updated_by,  fine_state ,plot_id , sanction_type )VALUES
    (1001, 1, '2024-06-12 10:00:00', '2024-06-12 10:00:00', 1,  'APPROVED',  1, 999);
INSERT INTO fine (id , created_by, created_date , last_updated_at , last_updated_by,  fine_state ,plot_id , sanction_type )VALUES
    (1002, 1, '2024-06-12 10:00:00', '2024-06-12 10:00:00', 1,  'ON_ASSEMBLY',  1, 999);
INSERT INTO fine (id , created_by, created_date , last_updated_at , last_updated_by,  fine_state ,plot_id , sanction_type )VALUES
    (1003, 1, '2024-06-12 10:00:00', '2024-06-12 10:00:00', 1,  'ON_ASSEMBLY',  1, 999);
INSERT INTO fine (id , created_by, created_date , last_updated_at , last_updated_by,  fine_state ,plot_id , sanction_type )VALUES
    (1004, 1, '2024-06-12 10:00:00', '2024-06-12 10:00:00', 1,  'ON_ASSEMBLY',  1, 999);
INSERT INTO fine (id , created_by, created_date , last_updated_at , last_updated_by,  fine_state, plot_id , sanction_type )VALUES
    (1005, 1, '2024-06-12 10:00:00', '2024-06-12 10:00:00', 1,  'ON_ASSEMBLY',  1, 999);
INSERT INTO fine (id , created_by, created_date , last_updated_at , last_updated_by,  fine_state ,plot_id , sanction_type )VALUES
    (1006, 1, '2024-06-12 10:00:00', '2024-06-12 10:00:00', 1,  'ON_ASSEMBLY',  1, 999);
INSERT INTO fine (id , created_by, created_date , last_updated_at , last_updated_by, fine_state ,plot_id , sanction_type )VALUES
    (1007, 1, '2024-06-12 10:00:00', '2024-06-12 10:00:00', 1,  'ON_ASSEMBLY',  1, 999);

insert into  infraction (id , created_by , created_date , last_updated_at , last_updated_by, description , infraction_state  , plot_id, fine_id , sanction_type )
    VALUES (1005, 1, '2024-06-12 10:00:00', '2024-06-12 10:00:00', 1, 'Molestaba demasiado ', 'APPROVED',1, 999, 999)

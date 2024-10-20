INSERT INTO sanction_type (id, created_by, created_date, last_updated_at , last_updated_by , description, name, price_type, price) VALUES
(999, 1, '2024-06-12 10:00:00', '2024-06-12 10:00:00', 1, 'Ruidos molestos', 'Ruidos molesos', 'porcentage', 35000);

INSERT INTO sanction_type (id, created_by, created_date, last_updated_at , last_updated_by , description, name, price_type, price) VALUES
    (1000, 1, '2024-06-12 10:00:00', '2024-06-12 10:00:00', 1, 'Egreso tardío', 'Egreso tardío', 'porcentage', 35000);

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
    VALUES (1005, 1, '2024-06-12 10:00:00', '2024-06-12 10:00:00', 1, 'Molestaba demasiado ', 'APPROVED',1, 999, 999);
INSERT INTO claim (id, created_by, created_date, last_updated_at, last_updated_by, sanction_type, claim_state, infraction_id)
VALUES (43, 1, '2024-09-26 10:00:00', '2024-09-26 10:00:00', 1, 999, 'SENT', 1005);
INSERT INTO claim (id, created_by, created_date, last_updated_at, last_updated_by, sanction_type, claim_state, infraction_id)
VALUES (45, 1, '2024-09-26 10:00:00', '2024-09-26 10:00:00', 1, 999, 'SENT', 1005);




INSERT INTO sanction_type (id, created_by, created_date, last_updated_at , last_updated_by , description, name, CHARGE_TYPE, amount, INFRACTION_DAYS_TO_EXPIRE, AMOUNT_OF_INFRACTION_FOR_FINE) VALUES
(999, 1, '2024-06-12 10:00:00', '2024-06-12 10:00:00', 1, 'Ruidos molestos', 'Ruidos molestos', 'FIXED', 35000, 60, 2);

INSERT INTO sanction_type (id, created_by, created_date, last_updated_at , last_updated_by , description, name, CHARGE_TYPE, amount, INFRACTION_DAYS_TO_EXPIRE, AMOUNT_OF_INFRACTION_FOR_FINE) VALUES
    (1000, 1, '2024-06-12 10:00:00', '2024-06-12 10:00:00', 1, 'Egreso tardío', 'Egreso tardío', 'PROPORTIONAL', 1, 120,2);

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
insert into  infraction (id , created_by , created_date , last_updated_at , last_updated_by, description , infraction_state  , plot_id, fine_id , sanction_type )
VALUES (1006, 1, '2024-10-20 10:00:00', '2024-06-12 10:00:00', 1, 'Molestaba demasiado ', 'APPROVED',1, null, 999);


INSERT INTO claim (id, created_by, created_date, last_updated_at, last_updated_by, sanction_type, claim_state, infraction_id)
VALUES (43, 1, '2024-09-26 10:00:00', '2024-09-26 10:00:00', 1, 999, 'SENT', 1005);
INSERT INTO claim (id, created_by, created_date, last_updated_at, last_updated_by, sanction_type, claim_state, infraction_id)
VALUES (45, 1, '2024-09-26 10:00:00', '2024-09-26 10:00:00', 1, 999, 'SENT', 1005);




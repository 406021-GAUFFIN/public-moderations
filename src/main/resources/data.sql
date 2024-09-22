/*
INSERT INTO fine id, created_by, created_date , last_updated_at, last_updated_by , claim_state, expiring_date , user_id, infraction_id , sanction_type bigint, primary key (id)) VALUES
    (999, 'john_doe', 'password123', 'john.doe@example.com', 'avatar1.png', '2024-06-12 10:00:00', '2024-06-12 10:00:00', '2024-06-12 10:00:00');


('SENT','ON_REVISION','APPEALED','APPROVED','REJECTED')
INSERT INTO players (id, user_name, password, email, avatar, last_login, created_at, updated_at) VALUES
    (777, 'agusnieto', 'Pa$$word777', '654654@654654.com', 'pfp.png', '2024-06-22 10:00:00', '2024-06-12 10:00:00', '2024-06-12 10:00:00');

INSERT INTO players (id, user_name, password, email, avatar, last_login, created_at, updated_at)
VALUES (1000000, 'APP', null, null, null, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO games (id, code, name, description, rules)
VALUES (1000000, 'RPS', 'Rock Paper Scissors', 'Rock, Paper, Scissors',
        '');

INSERT INTO matches(id, game_id, player_id, created_at, updated_at, status)
VALUES (1000000, 1000000, 777, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STARTED');


INSERT INTO matches(id, game_id, player_id, created_at, updated_at, status)
VALUES (1000001, 1000000, 777, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'FINISHED');

INSERT INTO matches(id, game_id, player_id, created_at, updated_at, status)
VALUES (1000002, 1000000, 777, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'CANCELED');

/*
INSERT INTO matches_rps(id, number_of_plays, remainder_plays, player1score, player2score)
VALUES (1000000, 10, 5, 3, 2);
INSERT INTO matches_rps(id, number_of_plays, remainder_plays, player1score, player2score, winner_id)
VALUES (1000001, 10, 0, 6, 4, 777);
INSERT INTO matches_rps(id, number_of_plays, remainder_plays, player1score, player2score)
VALUES (1000002, 10, 5, 3, 2);

INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000000, 1000000, 'ROCK', 'PAPER', 999);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000001, 1000000, 'PAPER', 'ROCK', 777);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000002, 1000000, 'PAPER', 'ROCK', 777);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000003, 1000000, 'ROCK', 'SCISSORS', 777);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000004, 1000000, 'ROCK', 'PAPER', 999);

INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000010, 1000001, 'ROCK', 'PAPER', 999);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000011, 1000001, 'PAPER', 'ROCK', 777);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000012, 1000001, 'PAPER', 'ROCK', 777);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000013, 1000001, 'ROCK', 'SCISSORS', 777);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000014, 1000001, 'ROCK', 'PAPER', 999);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000015, 1000001, 'PAPER', 'ROCK', 777);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000016, 1000001, 'PAPER', 'ROCK', 777);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000017, 1000001, 'ROCK', 'SCISSORS', 777);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000018, 1000001, 'ROCK', 'PAPER', 999);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000019, 1000001, 'ROCK', 'PAPER', 999);

INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000005, 1000002, 'ROCK', 'PAPER', 999);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000006, 1000002, 'PAPER', 'ROCK', 777);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000007, 1000002, 'PAPER', 'ROCK', 777);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000008, 1000002, 'ROCK', 'SCISSORS', 777);
INSERT INTO plays_rps(id, match_rps_id, shape_hand_player1, shape_hand_player2, winner_id)
VALUES (1000009, 1000002, 'ROCK', 'PAPER', 999);*/
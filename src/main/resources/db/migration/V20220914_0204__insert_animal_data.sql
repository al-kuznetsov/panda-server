INSERT INTO animal_type (id, code, name, description)
VALUES (24, 'DOG', 'Собака', 'Собака'),
    (25, 'CAT', 'Кошка', 'Кошка');

INSERT INTO breed (id, name, type_id)
VALUES (26, 'Беспородный', 25),
       (27, 'Беспородный', 24),
       (28, 'Пудель', 24),
       (29, 'Сфинкс', 25);

INSERT INTO animal_status (id, code, name, description)
VALUES (30, 'STRAY', 'Бездомный', NULL),
       (31, 'SHELTERED', 'В приюте', NULL),
       (32, 'HAS_GUARDIAN', 'С куратором', NULL),
       (33, 'HAS_OWNER', 'Хозяйский', NULL),
       (34, 'MISSING', 'Потерян', NULL),
       (35, 'DEAD', 'Мертв', NULL);
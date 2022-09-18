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

INSERT INTO animal (id, name, birth_date, description, full_bio, active, type_id, status_id)
VALUES (37, 'Черный', to_date('2018/01/01', 'YYYY/MM/DD'),
    'Крупный мохнатый крепкий пес, спокойного характера',
    'Крупный, очень спокойный пес, живет у овощных ларьков рядом с магазином "Олива"',
    true, 24, 30),
   (38, null, to_date('2019/01/01', 'YYYY/MM/DD'),
    'Крупный длинноногий пес, активный, много передвигается, дружелюбный',
    'Пес появляется преимущественно в 24-м микрорайоне',
    true, 24, 30);

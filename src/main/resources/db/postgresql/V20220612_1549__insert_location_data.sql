INSERT INTO country (id, alpha_two_code, short_name, name)
VALUES (1, 'US', 'США', 'Соединенные Штаты Америки'),
    (2, 'RU', 'Россия', 'Российская Федерация'),
    (3, 'KZ', 'Казахстан', 'Республика Казахстан'),
    (4, 'IN', 'Индия', 'Республика Индия');

INSERT INTO region_type (id, code, name)
VALUES (5, 'OBLAST', 'область'),
       (6, 'RESPUBLIKA', 'республика'),
       (7, 'GOROD', 'город');

INSERT INTO region (id, name, type_id)
VALUES (8, 'Башкортостан', 6),
       (9, 'Татарстан', 6),
       (10, 'Оребургская', 5),
       (11, 'Москва', 7);

INSERT INTO locality_type (id, code, name)
VALUES (12, 'GOROD', 'город'),
       (14, 'DEREVNYA', 'деревня'),
       (15, 'POSELOK', 'поселок');

INSERT INTO locality (id, name, type_id, region_id)
VALUES (16, 'Оренбург', 12, 10),
       (17, 'Москва', 12, NULL),
       (18, 'Уфа', 12, 8),
       (19, 'Казань', 12, 9);

INSERT INTO address_type (id, code, name)
VALUES (20, 'POSTAL', 'Почтовый адрес'),
       (21, 'BILLING', 'Адрес для выставления счетов'),
       (22, 'DELIVERY', 'Адрес доставки'),
       (23, 'MAIN', 'Основной адрес');
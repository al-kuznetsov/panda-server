-- MIT License
-- Copyright (c) 2022 - present Alexander Kuznetsov alkuznetsov@aol.com
-- ---

-- Create table and function to emulate local variables of type bigint
-- ---
CREATE TEMP TABLE bigint_var
(
    var_id TEXT PRIMARY KEY,
    var_value bigint
);

INSERT INTO bigint_var(var_id, var_value)
    VALUES ('oblast_region_type_id', nextval('global_id_sequence')),
    ('respublica_region_type_id', nextval('global_id_sequence'));

CREATE FUNCTION get_bigint_var(_id TEXT)
    RETURNS bigint LANGUAGE sql STABLE PARALLEL RESTRICTED AS
    'SELECT var_value FROM bigint_var WHERE var_id = $1';

-- Insert sample data into the tables
-- ---
INSERT INTO region_type (id, name)
VALUES (get_bigint_var('oblast_region_type_id'), 'Область'),
    (get_bigint_var('respublica_region_type_id'), 'Республика'),
    (nextval('global_id_sequence'), 'Край'),
    (nextval('global_id_sequence'), 'Автономная область'),
    (nextval('global_id_sequence'), 'Автономный округ'),
    (nextval('global_id_sequence'), 'Город федерального значения');

INSERT INTO region (name, type_id)
VALUES ('Оренбургская', get_bigint_var('oblast_region_type_id')),
    ('Башкортостан', get_bigint_var('respublica_region_type_id'));
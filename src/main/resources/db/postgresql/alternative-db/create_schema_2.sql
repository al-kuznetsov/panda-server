/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  al
 * Created: 19 дек. 2022 г.
 */

CREATE SCHEMA panda_2;

SET search_path TO panda_2;

CREATE SEQUENCE global_id_sequence_2 AS BIGINT START 1 INCREMENT 5;

CREATE TABLE measurement_unit
(
    code varchar(256) PRIMARY KEY,
    name varchar(256) NOT NULL,
    short_name varchar(86)
);

CREATE TABLE indicator_list
(
    code varchar(256) PRIMARY KEY,
    name varchar(256) NOT NULL,
    is_quality_indicator boolean NOT NULL,
    description varchar(512)
);

CREATE TABLE quality_indicator_value
(
    code varchar(256) PRIMARY KEY,
    name varchar(256) NOT NULL,
    value decimal NOT NULL,
    description varchar(512)
);



CREATE TABLE animal_type
(
    code varchar(256) PRIMARY KEY,
    name varchar(256) NOT NULL,
    description varchar(1024)
);

CREATE TABLE sex
(
    code varchar(256) PRIMARY KEY,
    name varchar(256) NOT NULL,
    description varchar(1024)
);

CREATE TABLE animal
(
    id bigint PRIMARY KEY DEFAULT nextval('global_id_sequence_2'),
    name varchar(512),
    birth_date DATE,
    description varchar(15600),
    full_bio TEXT,
    animal_type_code varchar(256),
    sex_code varchar(256),
    CONSTRAINT fk_animal_type FOREIGN KEY (animal_type_code) REFERENCES animal_type (code),
    CONSTRAINT fk_sex FOREIGN KEY (sex_code) REFERENCES sex (code)
);

CREATE TABLE file_type
(
    code varchar(256) PRIMARY KEY,
    extension varchar(256) NOT NULL,
    name varchar(512)
);

CREATE TABLE file_list
(
    id bigint PRIMARY KEY DEFAULT nextval('global_id_sequence_2'),
    last_updated timestamp without time zone NOT NULL DEFAULT now(),
    file_url varchar(512) NOT NULL,
    name varchar(512),
    description varchar(1024),
    file_type_code varchar(256),
    animal_id bigint,
    CONSTRAINT fk_file_type FOREIGN KEY (file_type_code) REFERENCES file_type (code),
    CONSTRAINT fk_file_list_animal FOREIGN KEY (animal_id) REFERENCES animal (id)
);

CREATE TABLE indicator_value
(
    id bigint PRIMARY KEY DEFAULT nextval('global_id_sequence_2'),
    registered_date timestamp without time zone NOT NULL DEFAULT now(),
    weight_multiplier decimal NOT NULL,
    indicator_code varchar(256) NOT NULL,
    quality_indicator_value_code varchar(256),
    quantitative_indicator_value decimal,
    measurement_unit_code varchar(256),
    animal_id bigint NOT NULL,
    CONSTRAINT fk_indicator FOREIGN KEY (indicator_code) REFERENCES indicator_list (code),
    CONSTRAINT fk_quality_indicator_value FOREIGN KEY (quality_indicator_value_code) REFERENCES quality_indicator_value (code),
    CONSTRAINT fk_measurement_unit FOREIGN KEY (measurement_unit_code) REFERENCES measurement_unit (code),
    CONSTRAINT fk_indicator_animal FOREIGN KEY (animal_id) REFERENCES animal (id)
);


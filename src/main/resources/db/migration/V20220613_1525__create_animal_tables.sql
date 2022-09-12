-- MIT License
-- Copyright (c) 2022 - present Alexander Kuznetsov alkuznetsov@aol.com
-- ---

-- Domain tables
-- ---

-- Create animal-related tables
-- ---

CREATE TABLE animal_type
(
    id bigint PRIMARY KEY DEFAULT nextval('global_id_sequence'),
    name varchar(512) NOT NULL,
    image_url varchar(512) NULL DEFAULT NULL
);

CREATE TABLE breed
(
    id bigint PRIMARY KEY DEFAULT nextval('global_id_sequence'),
    name varchar(512) NOT NULL,
    valuable boolean NOT NULL DEFAULT false,
    type_id bigint NOT NULL,
    image_url varchar(512) NULL DEFAULT NULL,
    average_body_mass real NULL DEFAULT NULL,
    average_height real NULL DEFAULT NULL,
    average_length real NULL DEFAULT NULL,
    average_tameness smallint NULL DEFAULT 1,
    CONSTRAINT fk_breed_animal_type FOREIGN KEY (type_id) REFERENCES animal_type (id)
);

-- E.g.: has a guardian, in shelter, stray, wild, duty, etc.
CREATE TABLE animal_status
(
    id integer PRIMARY KEY DEFAULT nextval('global_id_sequence'),
    name varchar(255) NOT NULL
);

-- The criteria to consider when helping decision making
CREATE TABLE animal_criteria
(
    id integer PRIMARY KEY DEFAULT nextval('global_id_sequence'),
    stressed boolean NOT NULL DEFAULT false,
    sick boolean NOT NULL DEFAULT false,
    traumatised boolean NOT NULL DEFAULT false,
    mobile boolean NOT NULL DEFAULT false
);

CREATE TABLE animal
(
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    name varchar(512) NULL DEFAULT NULL,
    birth_date DATE NULL DEFAULT NULL,
    description varchar(15600) NULL DEFAULT NULL,
    full_bio TEXT NULL DEFAULT NULL,
    image_url varchar(512) NULL DEFAULT NULL,
    active boolean NOT NULL DEFAULT false,
    date_created timestamp without time zone NOT NULL,
    last_updated timestamp without time zone NOT NULL,
    type_id bigint NOT NULL,
    breed_id bigint NOT NULL,
    status_id bigint NULL DEFAULT NULL,
    criteria_id bigint NOT NULL,
    CONSTRAINT fk_animal_type FOREIGN KEY (type_id) REFERENCES animal_type (id),
    CONSTRAINT fk_breed FOREIGN KEY (breed_id) REFERENCES breed (id),
    CONSTRAINT fk_animal_status FOREIGN KEY (status_id) REFERENCES animal_status (id),
    CONSTRAINT fk_animal_criteria FOREIGN KEY (criteria_id) REFERENCES animal_criteria (id)
);

CREATE TABLE animal_spot
(
    animal_id uuid NOT NULL,
    spot_id uuid NOT NULL,
    PRIMARY KEY (animal_id, spot_id)
);

CREATE TABLE animal_address
(
    animal_id uuid NOT NULL,
    address_id uuid NOT NULL,
    PRIMARY KEY (animal_id, address_id)
);
-- MIT License
-- Copyright (c) 2022 - present Alexander Kuznetsov alkuznetsov@aol.com
-- ---

-- Domain tables
-- ---

-- Create animal-related tables
-- ---

-- Type of animal in itd common sense (loosely related to species).
-- E.g.: dog, cat, racoon, coyote etc.
CREATE TABLE animal_type
(
    id bigint PRIMARY KEY DEFAULT nextval('global_id_sequence'),
    code varchar(256) NOT NULL,
    name varchar(256) NOT NULL,
    description varchar(1024),
    image_url varchar(512)
);

-- Breed if applicable to an animal type
-- E.g.: dog - poodle, mix, labrador, etc.
CREATE TABLE breed
(
    id bigint PRIMARY KEY DEFAULT nextval('global_id_sequence'),
    name varchar(256) NOT NULL,
    description varchar(1024),
    valuable boolean NULL DEFAULT false,
    type_id bigint NOT NULL,
    image_url varchar(512),
    average_body_mass real,
    average_height real,
    average_length real,
    average_tameness smallint,
    CONSTRAINT fk_breed_animal_type FOREIGN KEY (type_id) REFERENCES animal_type (id)
);

-- E.g.: has a guardian, in shelter, stray, wild, duty, etc.
CREATE TABLE animal_status
(
    id bigint PRIMARY KEY DEFAULT nextval('global_id_sequence'),
    code varchar(256) NOT NULL,
    name varchar(256) NOT NULL,
    description varchar(1024)
);

-- The criteria to consider when helping decision making
-- Rated from 1 to 5
CREATE TABLE animal_criteria
(
    id bigint PRIMARY KEY DEFAULT nextval('global_id_sequence'),
    stress smallint,
    sickness smallint,
    trauma smallint,
    mobility smallint,
    tameness smallint
);

CREATE TABLE animal
(
    id bigint PRIMARY KEY DEFAULT nextval('global_id_sequence'),
    name varchar(512),
    birth_date DATE,
    description varchar(15600),
    full_bio TEXT,
    image_url varchar(512),
    active boolean NULL DEFAULT false,
    date_created timestamp without time zone NOT NULL DEFAULT now(),
    last_updated timestamp without time zone NOT NULL DEFAULT now(),
    type_id bigint NOT NULL,
    breed_id bigint,
    status_id bigint,
    criteria_id bigint,
    CONSTRAINT fk_animal_type FOREIGN KEY (type_id) REFERENCES animal_type (id),
    CONSTRAINT fk_breed FOREIGN KEY (breed_id) REFERENCES breed (id),
    CONSTRAINT fk_animal_status FOREIGN KEY (status_id) REFERENCES animal_status (id),
    CONSTRAINT fk_animal_criteria FOREIGN KEY (criteria_id) REFERENCES animal_criteria (id)
);

CREATE TABLE animal_spot
(
    animal_id bigint NOT NULL,
    spot_id uuid NOT NULL,
    PRIMARY KEY (animal_id, spot_id)
);

CREATE TABLE animal_address
(
    animal_id bigint NOT NULL,
    address_id uuid NOT NULL,
    PRIMARY KEY (animal_id, address_id)
);
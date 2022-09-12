-- MIT License
-- Copyright (c) 2022 - present Alexander Kuznetsov alkuznetsov@aol.com
-- ---

-- Create location-related tables
-- ---
CREATE TABLE country
(
    id bigint PRIMARY KEY DEFAULT nextval('global_id_sequence'),
    alpha_two_code varchar(2) NOT NULL, -- ISO 3166-1 alpha-2 codes
    short_name varchar(128) NOT NULL,
    name varchar(256) NOT NULL
);

-- The first-level Administrative division type
-- Example: state in the USA, oblast in Russia, etc.
CREATE TABLE region_type
(
    id bigint PRIMARY KEY DEFAULT nextval('global_id_sequence'),
    name varchar(512) NOT NULL
);

-- The region in which the locality is, and which is in the country (first-level Administrative division).
-- For example, California or another appropriate first-level Administrative division
-- see https://en.wikipedia.org/wiki/List_of_administrative_divisions_by_country
CREATE TABLE region
(
    id bigint PRIMARY KEY DEFAULT nextval('global_id_sequence'),
    name varchar(512),
    type_id bigint,
    CONSTRAINT fk_region_type FOREIGN KEY (type_id) REFERENCES region_type (id)
);

-- The locality type example: town, city, etc.
CREATE TABLE locality_type
(
    id bigint PRIMARY KEY DEFAULT nextval('global_id_sequence'),
    name varchar(255) NOT NULL
);

-- The locality in which the street address is, and which is in the region. For example, Mountain View.
CREATE TABLE locality
(
    id bigint PRIMARY KEY DEFAULT nextval('global_id_sequence'),
    name varchar(512) NOT NULL,
    type_id bigint NULL,
    region_id integer NULL,
    CONSTRAINT fk_locality_region FOREIGN KEY (region_id) REFERENCES region (id),
    CONSTRAINT fk_locality_type FOREIGN KEY (type_id) REFERENCES locality_type (id)
);

-- E.g.: postal address, billing address, delivery address, etc.
CREATE TABLE address_type
(
    id bigint PRIMARY KEY DEFAULT nextval('global_id_sequence'),
    name varchar(128) NOT NULL
);

-- Follows recommendations on https://schema.org/PostalAddress
CREATE TABLE address
(
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    address_type_id bigint NOT NULL, -- TODO fill in with viable default?
    country_id bigint NOT NULL, -- The country. For example, USA.
    locality_id bigint NULL DEFAULT NULL, -- The locality in which the street address is
    region_id bigint NULL DEFAULT NULL, -- first-level Administrative division
    post_office_box_number varchar(80) NULL DEFAULT NULL, -- The post office box number for PO box addresses.
    postal_code varchar(80) NULL DEFAULT NULL, -- The postal code. For example, 94043.
    street varchar(255) NULL DEFAULT NULL,
    building varchar(80) NULL DEFAULT NULL, -- In some countries names instead of numbers
    apartment varchar(80) NULL DEFAULT NULL,
    CONSTRAINT fk_address_type FOREIGN KEY (address_type_id) REFERENCES address_type (id),
    CONSTRAINT fk_address_country FOREIGN KEY (country_id) REFERENCES country (id),
    CONSTRAINT fk_address_locality FOREIGN KEY (locality_id) REFERENCES locality (id),
    CONSTRAINT fk_address_region FOREIGN KEY (region_id) REFERENCES region (id)
);

-- Follows recommendations on https://schema.org/GeoCoordinates guidelines
-- Note: This table is currently not used
CREATE TABLE geo_coordinate
(
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    address_id uuid NULL DEFAULT NULL, -- For handling coordinates of a certain address
    country_id bigint NULL DEFAULT NULL,
    elevation  integer NULL DEFAULT NULL, -- WGS84
    latitude   DECIMAL(16, 14) NOT NULL, -- WGS84
    longitude  DECIMAL(17, 14) NOT NULL, -- WGS84
    CONSTRAINT fk_geo_coordinates_country FOREIGN KEY (country_id) REFERENCES country (id),
    CONSTRAINT fk_geo_coordinates_address FOREIGN KEY (address_id) REFERENCES address (id)
);

-- Uses PostGIS geometry support to store places
CREATE TABLE spot
(
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    spot geometry NOT NULL
);
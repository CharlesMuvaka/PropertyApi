CREATE DATABASE my_property;
\c
CREATE TABLE defects(
id serial PRIMARY KEY,
description VARCHAR NOT NULL,
tenant_id VARCHAR NOT NULL,
string_uri VARCHAR NOT NULL,
unit_name VARCHAR NOT NULL,
manager_name VARCHAR NOT NULL,
property_name VARCHAR NOT NULL
);

CREATE TABLE property(
id serial PRIMARY KEY,
property_name VARCHAR NOT NULL,
property_location VARCHAR NOT NULL,
manager_name VARCHAR NOT NULL
);

CREATE TABLE property_managers(
id serial PRIMARY KEY,
manager_name VARCHAR NOT NULL,
phone_number VARCHAR NOT NULL,
email VARCHAR NOT NULL
);

CREATE TABLE tenant(
id serial PRIMARY KEY,
tenant_name VARCHAR NOT NULL,
tenant_email VARCHAR NOT NULL,
tenant_phone VARCHAR NOT NULL,
tenant_id VARCHAR NOT NULL,
property_name VARCHAR NOT NULL,
unit_name VARCHAR NOT NULL,
manager_name VARCHAR NOT NULL,
joined TIMESTAMP
);

CREATE TABLE units(
id serial PRIMARY KEY,
unit_name VARCHAR NOT NULL,
property_name VARCHAR NOT NULL,
unit_rooms VARCHAR NOT NULL
);


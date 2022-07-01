CREATE DATABASE my_property;
\c
CREATE TABLE maintenance_quest(
quest_id serial PRIMARY KEY,
description VARCHAR,
quest_url VARCHAR,
property_name VARCHAR
);

CREATE TABLE property(
id serial PRIMARY KEY,
property_name VARCHAR,
manager_name VARCHAR
);

CREATE TABLE property_managers(
id serial PRIMARY KEY,
manager_name VARCHAR,
phone_number VARCHAR,
email VARCHAR,
property_name VARCHAR,
property_description VARCHAR
);

CREATE TABLE tenant(
id serial PRIMARY KEY,
tenant_name VARCHAR,
tenant_email VARCHAR,
property_name VARCHAR
);


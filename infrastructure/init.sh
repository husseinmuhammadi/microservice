#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER digiboy WITH PASSWORD '123456';
    CREATE DATABASE digiboy;
    GRANT ALL PRIVILEGES ON DATABASE digiboy TO digiboy;
EOSQL

psql -v ON_ERROR_STOP=1 --username "digiboy" --dbname "digiboy" <<-EOSQL
    CREATE SCHEMA master;
    CREATE SCHEMA users;
    CREATE SCHEMA product;
EOSQL

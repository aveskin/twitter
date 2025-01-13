--liquibase formatted sql

--changeset aveskin:create-twitter-schema
create schema twitter;
--rollback drop schema twitter;
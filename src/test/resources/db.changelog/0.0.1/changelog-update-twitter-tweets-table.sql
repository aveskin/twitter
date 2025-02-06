--liquibase formatted sql

--changeset aveskin:add-twitter-user_profiles-table-column-modified_timestamp
--comment add column modified_timestamp to twitter.tweets table
alter table twitter.tweets
    add column modified_timestamp timestamp;

update twitter.tweets
set modified_timestamp = created_timestamp
where modified_timestamp is null;

alter table twitter.tweets
    alter column modified_timestamp set not null;
--rollback alter table twitter.tweets drop column modified_timestamp;
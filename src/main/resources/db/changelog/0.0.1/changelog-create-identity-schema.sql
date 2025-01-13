--liquibase formatted sql

--changeset aveskin:create-identity-schema
create schema identity;
--rollback drop schema identity;


--changeset aveskin:create-identity-user_accounts-table
create table identity.user_accounts
(
    id        serial primary key ,
    username  varchar(32) unique not null,
    password  varchar(128) not null
)
--rollback drop table identity.user_accounts;


--changeset aveskin:create-identity-user_roles-table
create table identity.user_roles
(
    id         serial primary key ,
    authority  varchar(32) unique not null
)
--rollback drop table identity.user_roles;


--changeset aveskin:create-identity-user_accounts_roles-table
create table identity.user_accounts_roles
(
    user_account_id    integer not null,
    user_role_id       integer not null
)
--rollback drop table identity.user_accounts_roles;

--changeset aveskin:add-user_accounts_roles-table-constraints
alter table identity.user_accounts_roles
   add constraint user_accounts_roles__user_roles__fk
    foreign key (user_role_id) references identity.user_roles(id);

alter table identity.user_accounts_roles
    add constraint user_accounts_roles__user_accounts__fk
        foreign key (user_account_id) references identity.user_accounts(id);

alter table identity.user_accounts_roles
    add constraint user_accounts_roles_unique
        unique (user_account_id, user_role_id)
--rollback alter table identity.user_accounts_roles drop constraint user_accounts_roles__user_roles__fk;
--rollback alter table identity.user_accounts_roles drop constraint user_accounts_roles__user_accounts__fk;
--rollback alter table identity.user_accounts_roles drop constraint user_accounts_roles_unique;


--changeset aveskin:add-data-to-users_roles-table
insert into identity.user_roles(authority)
values ('ROLE_USER'),
       ('ROLE_ADMIN');
--rollback truncate table identity.user_roles
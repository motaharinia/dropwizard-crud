-- Steps:
-- 1.run externalconfig/sql/DDL.sql
-- 2.run application
-- 3.run externalconfig/sql/DML.sql
CREATE DATABASE IF NOT EXISTS dropwizard_crud CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;


create table if not exists dropwizard_crud.member
(
    id             bigint auto_increment primary key,
    create_at      timestamp    null,
    create_user_id bigint       null,
    create_user_ip varchar(255) null,
    hidden         bit          null,
    invalid        bit          null,
    update_at      timestamp    null,
    update_user_id bigint       null,
    update_user_ip varchar(255) null,
    date_of_birth  date         null,
    first_name     varchar(255) null,
    last_name      varchar(255) null,
    national_code  varchar(255) null,
    update_version int          null,
    setting_id     bigint       null,
    constraint national_code unique (national_code)
);
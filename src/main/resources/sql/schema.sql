drop table if exists users;
drop table if exists auth_history;
create table users
(
    id        bigserial not null
            primary key,
--    login     varchar   not null,
    password  varchar,
    email  varchar not null,
    firstname varchar not null,
    lastname  varchar   not null,
    phone     varchar
);

create table auth_history
(
    user_id bigint,
    --type varchar,
    host varchar,
    time varchar
);
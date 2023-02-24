drop table if exists users;
drop table if exists auth_history;
create table users
(
    id        bigserial not null,
--    login     varchar   not null,
    password  varchar,
    email  varchar not null,
    firstname varchar not null,
    lastname  varchar   not null,
    phone     varchar,
    PRIMARY KEY (id)
);

create table auth_history
(
    entry_id bigserial not null,
    user_id bigint not null,
    transaction_type varchar(10),
    host varchar(15),
    transaction_time varchar,
    PRIMARY KEY (entry_id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
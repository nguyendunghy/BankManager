create table session
(
    id           integer primary key auto_increment not null,
    `session`    varchar(255) not null,
    username     varchar(255) not null,
    expired_time bigint       not null,
    status       integer      not null
);

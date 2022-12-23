create table user_bank_account
(
    id              integer primary key auto_increment not null,
    bank_account_id integer not null,
    user_id         integer not null
);

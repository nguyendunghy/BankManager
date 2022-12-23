create table bank_account
(
    id           integer primary key auto_increment not null,
    account_name varchar(255) not null,
    account_no   varchar(255) not null,
    currency     varchar(100) not null
);

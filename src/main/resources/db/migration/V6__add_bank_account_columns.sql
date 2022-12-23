alter table bank_account
    add column created_by varchar(100);

alter table bank_account
    add column created_at datetime default now();

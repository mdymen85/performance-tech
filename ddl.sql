create table if not exists accounts
(
    id      bigint auto_increment
        primary key,
    account int            not null,
    balance decimal(13, 2) not null,
    constraint transaction_account_uindex
        unique (account)
);

create table if not exists transactions
(
    id         bigint auto_increment
        primary key,
    account    int            not null,
    tvalue     decimal(13, 2) not null,
    created_at timestamp      not null,
    type       char           not null
);

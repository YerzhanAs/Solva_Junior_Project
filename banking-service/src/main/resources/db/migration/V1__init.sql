CREATE TABLE currencyusd(
    id SERIAL PRIMARY KEY,
    closeusd NUMERIC(5,2),
    datetime varchar(15),
    created_at timestamp
);


CREATE TABLE clients(
    id SERIAL PRIMARY KEY,
    full_name varchar(15)
);



CREATE TABLE transactions(
    id SERIAL PRIMARY KEY,
    account_from integer,
    account_to integer,
    sum numeric(10,2),
    limit_exceed_sum numeric(10,2),
    type_operation varchar(15),
    currency varchar(15),
    operation_time timestamp,
    limit_exceed boolean,
    client_id integer REFERENCES clients(id)
);

CREATE TABLE tran_limit(
    id SERIAL PRIMARY KEY,
    type_op varchar(15),
    limit_sum numeric(10,2),
    createdAt timestamp,
    updatedAt timestamp,
    currency varchar(15),
    period varchar(15),
    client_id integer REFERENCES clients(id)
);

INSERT INTO clients VALUES (1, 'Ashimov Yerzhan');

INSERT INTO transactions (id, account_from, account_to, sum, limit_exceed_sum,  type_operation, currency, limit_exceed,  client_id ) VALUES (1, 58215, 78515, 700, 1300, 'product', 'USD',false, 1),
                                (2, 58215, 78515, 500, 800, 'product', 'USD',false, 1);

INSERT INTO tran_limit (type_op, limit_sum, currency, period, client_id) VALUES ( 'product', 2000, 'USD', '2022-12-24', 1);

create sequence HIBERNATE_SEQUENCE
    minvalue 1
    maxvalue 999999999999
    start with 4
    increment by 1
    cache 20;







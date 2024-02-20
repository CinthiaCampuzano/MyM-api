CREATE TABLE IF NOT EXISTS product
(
    product_id  BIGINT          PRIMARY KEY,
    code        VARCHAR(15),
    name        VARCHAR(60)     NOT NULL,
    price       DECIMAL(11,2)   NOT NULL,
    unit        VARCHAR(10)     NOT NULL,
    last_update TIMESTAMP       NOT NULL    DEFAULT CURRENT_TIMESTAMP
);

CREATE SEQUENCE IF NOT EXISTS product_product_id INCREMENT BY 1 START WITH 1;
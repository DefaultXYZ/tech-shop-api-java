DROP TABLE IF EXISTS tech_product_availability;
DROP TABLE IF EXISTS tech_product;
DROP TABLE IF EXISTS tech_shop;
DROP TABLE IF EXISTS type;

CREATE TABLE IF NOT EXISTS type
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS tech_product
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    type_id     SERIAL       NOT NULL,
    description TEXT         NOT NULL,
    cost        REAL         NOT NULL,
    CONSTRAINT fk_type FOREIGN KEY (type_id) REFERENCES type (id)
);

CREATE TABLE IF NOT EXISTS tech_shop
(
    id      SERIAL PRIMARY KEY,
    address VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS tech_product_availability
(
    id         SERIAL PRIMARY KEY,
    product_id INT NOT NULL,
    shop_id    INT NOT NULL,
    amount     INT NOT NULL,
    CONSTRAINT fk_tech_product FOREIGN KEY (product_id) REFERENCES tech_product (id),
    CONSTRAINT fk_tech_shop FOREIGN KEY (shop_id) REFERENCES tech_shop (id)
);


INSERT INTO type VALUES (1, 'Test type');

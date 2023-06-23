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


INSERT INTO type
VALUES (11, 'Test Type 1'),
       (12, 'Test Type 2');

INSERT INTO tech_product
VALUES (51, 'Test 1', 11, text('Desc 1'), 21.1),
       (52, 'Test 2', 11, text('Desc 2'), 22.1),
       (53, 'Test 3', 12, text('Desc 3'), 23.1),
       (54, 'Test 4', 12, text('Desc 4'), 24.1),
       (55, 'Test 5', 12, text('Desc 5'), 25.1),
       (56, 'Test 6', 11, text('Desc 6'), 26.1),
       (57, 'Test 7', 12, text('Desc 7'), 27.1),
       (58, 'Test 8', 11, text('Desc 8'), 28.1);

INSERT INTO tech_shop
VALUES (41, 'Address 1'),
       (42, 'Address 2'),
       (43, 'Address 3'),
       (44, 'Address 4');

INSERT INTO tech_product_availability
VALUES (61, 51, 41, 1),
       (62, 52, 41, 2),
       (63, 53, 41, 3),
       (64, 54, 41, 4),
       (65, 55, 41, 5);

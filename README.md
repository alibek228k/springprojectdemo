# springprojectdemo
Spring boot project
To run the program run this sql request:
DROP table if exists auth_user_role;
drop table if exists auth_role;
drop table if exists users;
drop table if exists shopping_cart_item;
drop table if exists cart_item;
drop table if exists products;
drop table if exists shopping_cart;
CREATE TABLE auth_role(
                          auth_role_id serial primary key,
                          role_name VARCHAR(100),
                          role_desc VARCHAR(1000));

CREATE TABLE users (
                       user_id SERIAL NOT NULL PRIMARY KEY,
                       first_name VARCHAR(100),
                       last_name VARCHAR(100),
                       email VARCHAR(100),
                       password VARCHAR(256),
                       status VARCHAR(255)
);
create table auth_user_role(
                               user_id int REFERENCES users(user_id),
                               auth_role_id int REFERENCES auth_role(auth_role_id));
insert into users(first_name, last_name, email) values('Alibek', 'Joldybayev', 'admin@gmail.com');
insert into auth_role(role_name, role_desc) values('SUPER_USER', 'This user has ultimate rights for everything'),
                                                  ('ADMIN_USER', 'This user has admin rights for adminitstrative work'),
                                                  ('SITE_USER', 'This user has access to site, after login - normal user');
insert into auth_user_role(user_id, auth_role_id) values(1, 1),
                                                        (1, 2),
                                                        (1, 3);
update users
set password = '$2a$12$uZKg6zJXJFJIFJoXvT7p8OF7/voC4Uc0zjgbbVDrg7fwDM4qKBuze'
where user_id = 1;
update users
set status = 'VERIFIED'
where user_id = 1;
CREATE TABLE products(
                         id SERIAL PRIMARY KEY,
                         type_of_clothing VARCHAR(100),
                         name_of_product VARCHAR(100),
                         price int,
                         photo_src VARCHAR
);
INSERT INTO products(type_of_clothing, name_of_product, price, photo_src) VALUES ('Headdress', 'Cap', 100, 'https://intertop.kz/upload/resize_cache/iblock/ed6/275_375_1/0dvv7m90e8yijs0yhfhski2w3vnxf87x.jpg'),
                                                                                 ('Headdress', 'Scarf', 50, 'https://intertop.kz/upload/resize_cache/iblock/3f9/275_375_1/ts3qgd03oy0538o4iqoewtyfuucbi3t0.jpg'),
                                                                                 ('Outwear', 'Jacket', 400, 'https://intertop.kz/upload/resize_cache/iblock/aad/275_375_1/jbzgb3wqo795zfhhyt1ezfd4m1jz7o6h.jpg'),
                                                                                 ('Outwear', 'Coat', 200, 'https://intertop.kz/upload/resize_cache/iblock/ead/275_375_1/g9u5am89lpkjs49w0cf7qtkh55fz6xr4.jpg'),
                                                                                 ('Shoes', 'Sneakers', 150, 'https://intertop.kz/upload/resize_cache/iblock/b7f/275_375_1/cluc759olsyqbnw4wb6kj3nu47bycvfu.jpg'),
                                                                                 ('Shoes','Boots', 120, 'https://intertop.kz/upload/resize_cache/iblock/07d/275_375_1/2m2s4y0pb2f1n4bfivx5e0p06p3u7mra.jpg');
CREATE table  cart_item(
    id BIGSERIAL PRIMARY KEY,
    date date,
    quantity int,
    product_id int REFERENCES products(id)
);
create table shopping_cart(
    id BIGSERIAL PRIMARY KEY,
    date date,
    total_price INT,
    token_session varchar
);
create table shopping_cart_item(
    shopping_cart_id BIGINT REFERENCES shopping_cart(id),
    item_id BIGINT REFERENCES cart_item(id)
)

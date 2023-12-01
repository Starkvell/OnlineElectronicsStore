DELETE FROM carts_products;
DELETE FROM carts;
DELETE FROM categories;
DELETE FROM category_relations;
DELETE FROM order_details;
DELETE FROM orders;
DELETE FROM products;
DELETE FROM products_categories;
DELETE FROM users;

SELECT setval('carts_cart_id_seq', 1, false);
SELECT setval('categories_category_id_seq', 1, false);
SELECT setval('order_details_order_detail_id_seq', 1, false);
SELECT setval('orders_order_id_seq', 1, false);
SELECT setval('products_product_id_seq', 1, false);
SELECT setval('users_user_id_seq', 1, false);


INSERT INTO products(title, description, stock_quantity, base_price, discount_price) VALUES ('Смартфон Apple iPhone 14', 'Крутой телефон', 5, 115999, 100000);
INSERT INTO products(title, description, stock_quantity, base_price, discount_price) VALUES ('Смартфон Apple iPhone 13', 'Крутой телефон 2', 4, 90000, 80000);
INSERT INTO products(title, description, stock_quantity, base_price, discount_price) VALUES ('Смартфон Samsung Galaxy S8', 'Крутой телефон 3', 3, 80000, 70000);
INSERT INTO products(title, description, stock_quantity, base_price, discount_price) VALUES ('Наушники Apple AirPods Pro', 'Крутые наушники', 2, 70000, 60000);
INSERT INTO products(title, description, stock_quantity, base_price, discount_price) VALUES ('Чехол для Huawei P50', 'Крутой чехол', 1, 1000, 500);
INSERT INTO products(title, description, stock_quantity, base_price, discount_price) VALUES ('Умная колонка Яндекс Станция', 'Крутая колонка', 0, 9000, 8000);
INSERT INTO products(title, description, stock_quantity, base_price, discount_price) VALUES ('Смартфон Huawei P50', 'Крутая телефон 4', 10, 30000, 20000);

INSERT INTO categories( category_name) VALUES ('Смартфоны');
INSERT INTO categories( category_name) VALUES ('Apple');
INSERT INTO categories( category_name) VALUES ('Samsung');
INSERT INTO categories( category_name) VALUES ('Сопутствующие товары');
INSERT INTO categories( category_name) VALUES ('Наушники');
INSERT INTO categories( category_name) VALUES ('Чехлы');
INSERT INTO categories( category_name) VALUES ('Аудиотехника');
INSERT INTO categories( category_name) VALUES ('Портативные колонки');

INSERT INTO category_relations(parent_category_id,child_category_id) VALUES (1,2);
INSERT INTO category_relations(parent_category_id,child_category_id) VALUES (1,3);
INSERT INTO category_relations(parent_category_id,child_category_id) VALUES (1,4);
INSERT INTO category_relations(parent_category_id,child_category_id) VALUES (4,5);
INSERT INTO category_relations(parent_category_id,child_category_id) VALUES (4,6);
INSERT INTO category_relations(parent_category_id,child_category_id) VALUES (7,8);
INSERT INTO category_relations(parent_category_id,child_category_id) VALUES (7,5);

INSERT INTO products_categories(product_id, category_id) VALUES (1,2);
INSERT INTO products_categories(product_id, category_id) VALUES (2,2);
INSERT INTO products_categories(product_id, category_id) VALUES (3,3);
INSERT INTO products_categories(product_id, category_id) VALUES (4,5);
INSERT INTO products_categories(product_id, category_id) VALUES (5,6);
INSERT INTO products_categories(product_id, category_id) VALUES (6,8);
INSERT INTO products_categories(product_id, category_id) VALUES (7,4);



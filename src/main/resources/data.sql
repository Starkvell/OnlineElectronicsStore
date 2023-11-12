DELETE FROM carts_products;
DELETE FROM carts;
DELETE FROM categories;
DELETE FROM category_relations;
DELETE FROM order_details;
DELETE FROM orders;
DELETE FROM products;
DELETE FROM products_categories;
DELETE FROM users;

INSERT INTO users(user_id,first_name, last_name, email, password) VALUES (1,'Pavel','Anufriev','paxa-tron@mail.ru', 12345678);
INSERT INTO users(user_id,first_name, last_name, email, password) VALUES (2,'Ilia','Bobkin','bobkin@gmail.com', 123456782);
INSERT INTO users(user_id,first_name, last_name, email, password) VALUES (3,'Egor','Derevtcov','derevtsov@mail.ru', 12345678);
INSERT INTO users(user_id,first_name, last_name, email, password) VALUES (4,'Egor','Petrov','petrov@mail.ru', 12345678);

INSERT INTO products(product_id,title, description, stock_quantity, base_price, discount_price) VALUES (1,'Смартфон Apple iPhone 14', 'Крутой телефон', 5, 115999, 100000);
INSERT INTO products(product_id,title, description, stock_quantity, base_price, discount_price) VALUES (2,'Смартфон Apple iPhone 13', 'Крутой телефон 2', 4, 90000, 80000);
INSERT INTO products(product_id,title, description, stock_quantity, base_price, discount_price) VALUES (3,'Смартфон Samsung Galaxy S8', 'Крутой телефон 3', 3, 80000, 70000);
INSERT INTO products(product_id,title, description, stock_quantity, base_price, discount_price) VALUES (4,'Наушники Apple AirPods Pro', 'Крутые наушники', 2, 70000, 60000);
INSERT INTO products(product_id,title, description, stock_quantity, base_price, discount_price) VALUES (5,'Чехол для Huawei P50', 'Крутой чехол', 1, 1000, 500);
INSERT INTO products(product_id,title, description, stock_quantity, base_price, discount_price) VALUES (6,'Умная колонка Яндекс Станция', 'Крутая колонка', 0, 9000, 8000);
INSERT INTO products(product_id,title, description, stock_quantity, base_price, discount_price) VALUES (7,'Смартфон Huawei P50', 'Крутая телефон 4', 10, 30000, 20000);

INSERT INTO carts(cart_id,user_id, quantity, total_cost) VALUES (1,1,3,100000);
INSERT INTO carts(cart_id,user_id, quantity, total_cost) VALUES (2,2,3,100000);
INSERT INTO carts(cart_id,user_id, quantity, total_cost) VALUES (3,3,3,100000);

INSERT INTO carts_products(cart_id, product_id) VALUES (1,1);
INSERT INTO carts_products(cart_id, product_id) VALUES (2,3);
INSERT INTO carts_products(cart_id, product_id) VALUES (3,4);

INSERT INTO categories(category_id, category_name) VALUES (1,'Смартфоны');
INSERT INTO categories(category_id, category_name) VALUES (2,'Apple');
INSERT INTO categories(category_id, category_name) VALUES (3,'Samsung');
INSERT INTO categories(category_id, category_name) VALUES (4,'Сопутствующие товары');
INSERT INTO categories(category_id, category_name) VALUES (5,'Наушники');
INSERT INTO categories(category_id, category_name) VALUES (6,'Чехлы');
INSERT INTO categories(category_id, category_name) VALUES (7,'Аудиотехника');
INSERT INTO categories(category_id, category_name) VALUES (8,'Портативные колонки');

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

INSERT INTO orders(order_id, user_id, total_amount, address) VALUES (1, 1, '150000', '123 Main St');
INSERT INTO orders(order_id, user_id, total_amount, address) VALUES (2, 2, '150000', '213 Gagarina St');
INSERT INTO orders(order_id, user_id, total_amount, address) VALUES (3, 1, '150000', '312 Lenina St');

INSERT INTO order_details(order_detail_id, order_id, product_id, quantity, price) VALUES (1,1,1,3,1111111);
INSERT INTO order_details(order_detail_id, order_id, product_id, quantity, price) VALUES (2,1,2,5,1124411);
INSERT INTO order_details(order_detail_id, order_id, product_id, quantity, price) VALUES (3,2,3,4,234241);



CREATE TABLE IF NOT EXISTS products (
	product_id SERIAL PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	description TEXT,
	image_url VARCHAR(255),
	stock_quantity INT,
	base_price DECIMAL(10,2) NOT NULL,
	discount_price DECIMAL(10,2)
);

CREATE TABLE IF NOT EXISTS categories(
	category_id SERIAL PRIMARY KEY,
	category_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS category_relations (
    child_category_id INT REFERENCES categories(category_id) ON DELETE CASCADE,
    parent_category_id INT REFERENCES categories(category_id) ON DELETE CASCADE,
    PRIMARY KEY (child_category_id, parent_category_id)
);

CREATE TABLE IF NOT EXISTS products_categories(
	product_id INT REFERENCES products(product_id),
	category_id INT REFERENCES categories(category_id),
	PRIMARY KEY (product_id, category_id)
);

CREATE TABLE IF NOT EXISTS users(
	user_id SERIAL PRIMARY KEY,
	first_name VARCHAR(255) NOT NULL,
	last_name VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL UNIQUE,
	password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS carts (
    cart_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id) NOT NULL UNIQUE,
	quantity INT NOT NULL,
	total_cost DECIMAL(10,2) NOT NULL
);


CREATE TABLE IF NOT EXISTS cart_products(
	cart_id INT REFERENCES carts(cart_id),
	product_id INT REFERENCES products(product_id),
	PRIMARY KEY(cart_id, product_id)
);

CREATE TABLE IF NOT EXISTS orders (
    order_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10, 2) NOT NULL,
	address VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS order_details (
    order_detail_id SERIAL PRIMARY KEY,
    order_id INT REFERENCES orders(order_id) NOT NULL,
    product_id INT REFERENCES products(product_id) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

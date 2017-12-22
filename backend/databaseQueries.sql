CREATE TABLE category (	
	id IDENTITY,
	name VARCHAR(50),
	description VARCHAR(255),
	image_url VARCHAR(50),
	is_active BOOLEAN,	
	CONSTRAINT pk_category_id PRIMARY KEY(id)
	
);

INSERT INTO category(name, description, image_url, is_active) VALUES('Laptop', 'This description for laptop category', 'CAT_1.png', true);
INSERT INTO category(name, description, image_url, is_active) VALUES('Television', 'This description for television category', 'CAT_2.png', true);
INSERT INTO category(name, description, image_url, is_active) VALUES('Mobile', 'This description for mobile category', 'CAT_3.png', true);

CREATE TABLE user_detail(
	id IDENTITY,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	role VARCHAR(50),
	enabled BOOLEAN,
	password VARCHAR(60),
	email VARCHAR(100),
	contact_number VARCHAR(15),
	CONSTRAINT pk_user_id PRIMARY KEY(id)
);

INSERT INTO user_detail
(first_name, last_name, role, enabled, password, email, contact_number)
VALUES ('Sabar', 'Tanjungsari', 'ADMIN', true, '$2a$10$PuhGzY9IxreRKZWpJ6N/R.1tXTN4DAulnK34VN2r8aRRiSS4r/tfa', 'sabar@tondira.com', '888888888');


CREATE TABLE product(
	id IDENTITY,
	code VARCHAR(20),
	name VARCHAR(50),
	brand VARCHAR(50),
	description VARCHAR(255),
	unit_price DECIMAL(10, 2),
	quantity INT,
	is_active BOOLEAN,
	category_id INT,
	supplier_id INT,
	purchases INT DEFAULT 0,
	views INT DEFAULT 0,
	CONSTRAINT pk_product_id PRIMARY KEY(id),
	CONSTRAINT fk_product_category_id FOREIGN KEY(category_id) REFERENCES category(id),
	CONSTRAINT fk_supplier_category_id FOREIGN KEY(supplier_id) REFERENCES user_detail(id)
);

INSERT INTO product(code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES('PRDABC123DEFX', 'iPhone 5s', 'apple', 'This is one of the best phone available the market right now!', 18000, 5, true, 3, 2, 0, 0);

INSERT INTO product(code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES('PRDEF123DEFX', 'Samsung s7', 'Samsung', 'A smart phone by samsung!', 3200, 2, true, 3, 3, 0, 0);

INSERT INTO product(code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES('PRDPQRWGTX', 'Google Pixel', 'google', 'This is one of the best android smart phone in the market right now!', 57000, 5, true, 3, 2, 0, 0);

INSERT INTO product(code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES('PRDMNO123PQRX', 'Maxbook Pro', 'apple', 'This is one of the best laptops available in the market right now!', 54000, 3, true, 1, 2, 0, 0);

INSERT INTO product(code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id, purchases, views)
VALUES('PRDABCXYZDEFX', 'Dell Latitude E6510', 'dell', 'This is one of the best laptops series from dell thet can be used!', 4800, 5, true, 1, 3, 0, 0);


CREATE TABLE cart
(
  id serial NOT NULL,
  cart_lines integer,
  grand_total double precision,
  user_id integer,
  CONSTRAINT cart_pkey PRIMARY KEY (id),
  CONSTRAINT fkiycdwkfx8m2ht0vaitrj8hd0h FOREIGN KEY (user_id)
      REFERENCES user_detail (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

--- the cart line table to store the cart details

CREATE TABLE cart_line(
	id SERIAL,
	cart_id int,
	total DECIMAL(10, 2),
	product_id int,
	product_count int,
	buying_price DECIMAL(10, 2),
	is_available boolean,
	CONSTRAINT fk_cartline_cart_id FOREIGN KEY(cart_id) REFERENCES cart(id),
	CONSTRAINT fk_cartline_product_id FOREIGN KEY(product_id) REFERENCES product(id),
	CONSTRAINT pk_cartline_id PRIMARY KEY(id)
);


CREATE TABLE address
(
  id serial NOT NULL,
  address_line_one character varying(255) NOT NULL,
  address_line_two character varying(255) NOT NULL,
  billing boolean NOT NULL,
  city character varying(255) NOT NULL,
  country character varying(255) NOT NULL,
  postal_code character varying(255) NOT NULL,
  shipping boolean NOT NULL,
  state character varying(255) NOT NULL,
  user_id integer,
  CONSTRAINT address_pkey PRIMARY KEY (id),
  CONSTRAINT fkh0aaa6l9ggi7ixnfoljp3yqmw FOREIGN KEY (user_id)
      REFERENCES user_detail (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
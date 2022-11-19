CREATE TABLE order_object (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	client_id INTEGER REFERENCES  client(id),
	product_id INTEGER NOT NULL REFERENCES product(id),
	delivery_address_id VARCHAR REFERENCES address(id),
	cnt INTEGER NOT NULL,
	price INTEGER NOT NULL
);

CREATE TABLE client (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	first_name VARCHAR NOT NULL,
	last_name VARCHAR NOT NULL,
	email VARCHAR NOT NULL,
	time INTEGER NOT NULL
);

CREATE TABLE address (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	town VARCHAR NOT NULL,
	house INTEGER NOT NULL,
	flat INTEGER NOT NULL,
	street VARCHAR NOT NULL
);

CREATE TABLE product (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	title VARCHAR NOT NULL,
	price INTEGER NOT NULL,
	description VARCHAR NOT NULL
);




INSERT INTO db_coffee.role (role) VALUES ('ROLE_ADMIN');
INSERT INTO db_coffee.role (role) VALUES ('ROLE_USER');

INSERT INTO db_coffee.address (city, country, state, zipcode) VALUES ('Fairfield', 'USA', 'Iowa', '52556');
INSERT INTO db_coffee.address (city, country, state, zipcode) VALUES ('Ottumwa', 'USA', 'Iowa', '64353');

INSERT INTO db_coffee.person (email, enable, encrypted_password, first_name, last_name, phone, username, address_id) VALUES ('admin@mail.com', true, '$2a$12$uFqAqR91PKlpCWU/Mfj5kektQiyhHicUt..M4e2JbJLczlZvI46BW', 'admin', null, '123456789', 'admin', 1);
INSERT INTO db_coffee.person (email, enable, encrypted_password, first_name, last_name, phone, username, address_id) VALUES ('user@mail.com', true, '$2a$12$RguLmBX5yGc6Z4RycD2QMej.9bWAItwC3MhhJQuqzmLPZRBX14sny', 'user', null, '987654321', 'user', 2);

INSERT INTO db_coffee.person_roles (person_id, role_id) VALUES (1, 1);
INSERT INTO db_coffee.person_roles (person_id, role_id) VALUES (2, 2);

INSERT INTO db_coffee.product (description, price, product_name, product_type) VALUES ('', 2.3, 'Pancakes', 'BREAKFAST');
INSERT INTO db_coffee.product (description, price, product_name, product_type) VALUES ('', 1.5, 'Crumb Topped Banana', 'BREAKFAST');
INSERT INTO db_coffee.product (description, price, product_name, product_type) VALUES ('', 3.4, 'Easy Breakfast Casserole', 'BREAKFAST');
INSERT INTO db_coffee.product (description, price, product_name, product_type) VALUES ('', 4, 'The Bestest Belgian Waffles', 'BREAKFAST');
INSERT INTO db_coffee.product (description, price, product_name, product_type) VALUES ('', 5.8, 'Spiced Butternut Squash Soup', 'LUNCH');
INSERT INTO db_coffee.product (description, price, product_name, product_type) VALUES ('', 4.8, 'Tomato Bacon Grilled Cheese', 'LUNCH');
INSERT INTO db_coffee.product (description, price, product_name, product_type) VALUES ('', 5.3, 'Philly Cheesteak Sandwich with Garlic Mayo', 'LUNCH');
INSERT INTO db_coffee.product (description, price, product_name, product_type) VALUES ('', 8.1, 'Pork Marsala', 'DINNER');
INSERT INTO db_coffee.product (description, price, product_name, product_type) VALUES ('', 7.3, 'Pasta Pomodoro', 'DINNER');
INSERT INTO db_coffee.product (description, price, product_name, product_type) VALUES ('', 7.5, 'Fresh Tomato Shrimp Pasta', 'DINNER');
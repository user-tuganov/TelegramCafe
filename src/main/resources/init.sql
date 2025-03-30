INSERT INTO product (id, name, type, imageurl, description) VALUES
(0, 'Капучино', 'Напитки', 'https://statics.mixitup.ru/img/uploads/product2/xl/121/1564.jpg', 'Классический капучино с нежной молочной пенкой.'),
(1, 'Чизкейк Нью-Йорк', 'Десерты', 'https://www.marybakery.ru/wp-content/uploads/2017/04/kapkeyki-anons.jpg', 'Классический чизкейк с насыщенным сливочным вкусом.');

INSERT INTO size (id, product_id, text, price) VALUES
(0, 0, '0.3 л', 150),
(1, 0, '0.4 л', 180),
(2, 0, '0.5 л', 210),
(3, 1, '200 гр.', 250);

INSERT INTO topping (id, name, price) VALUES
(0, 'Сироп ваниль', 20),
(1, 'Корица', 15);

INSERT INTO product_toppings (products_id, toppings_id) VALUES
(0, 0),
(0, 1);


INSERT INTO discount (id, name, description, imageurl, discount_percentage) VALUES
(0, 'Кофейная Тройка', 'Три вкусных напитка по выгодной цене с возможностью добавить топпинги.', 'https://statics.mixitup.ru/img/uploads/product2/xl/121/1564.jpg', 0.2),
(1, 'Кофейная Тройка', 'Три вкусных напитка по выгодной цене с воз   можностью добавить топпинги.', 'https://statics.mixitup.ru/img/uploads/product2/xl/121/1564.jpg', 0.15);

INSERT INTO discount_products (products_id, discounts_id) VALUES
(0, 0),
(0, 1),
(1, 1);

INSERT INTO cafe_address (id, address) VALUES
(0, 'Москва, Тверская улица, 9'),
(1, 'Москва, Улица Шверника, 9');


INSERT INTO role (id, name) VALUES
(0, 'ROLE_COOK');

INSERT INTO cafe_employee (id, first_name, last_name, password, username, cafe_address_id, role_id) VALUES
(0, 'Никита', 'Туганов', '$2a$10$tUUkcRb2Jt97ZeEiz/YBheQg7FExP4MNJBhKOsakEzDHVOQJT6ek6', 'cook', 0, 0);
-- password : cook1
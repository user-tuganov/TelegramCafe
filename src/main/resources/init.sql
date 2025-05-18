INSERT INTO product (id, name, type, imageurl, description) VALUES
(0, 'Капучино', 'Напитки', 'https://mircoffee.ru/ckfinder/userfiles/images/1348611114_02-kapuchino.jpg', 'Классический капучино с нежной молочной пенкой.'),
(1, 'Чизкейк Нью-Йорк', 'Десерты', 'https://www.marybakery.ru/wp-content/uploads/2017/04/kapkeyki-anons.jpg', 'Классический чизкейк с насыщенным сливочным вкусом.'),
(2, 'Латте', 'Напитки', 'https://azbyka.ru/recept/wp-content/uploads/2021/01/close-up-tasty-macchiato-with-milk-table.jpg', 'Нежный кофейный напиток с молоком и воздушной пенкой.'),
(3, 'Американо', 'Напитки', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQXfSMseprNZSqliiNVDY_q7nrq3luh53_ywQ&s', 'Крепкий черный кофе без молока.'),
(4, 'Круассан с шоколадом', 'Выпечка', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ-c7vX3gSZT2zjpIZhLpUXpQwdweQZJAfWcA&s', 'Слоеный круассан с начинкой из бельгийского шоколада.'),
(5, 'Тирамису', 'Десерты', 'https://art-lunch.ru/wp-content/uploads/2014/10/Tiramisu_01.jpg', 'Итальянский десерт с кофейной пропиткой и кремом маскарпоне.'),
(6, 'Матча латте', 'Напитки', 'https://101kofemashina.ru/wp-content/uploads/2023/09/traditional-matcha-latte-640x427.jpg', 'Традиционный японский чай с молоком.'),
(7, 'Красный бархат', 'Десерты', 'https://pteat.ru/wp-content/uploads/2024/02/mg_3792-803x535.jpg', 'Нежный бисквит с крем-чизом и ягодным топпингом.');


INSERT INTO size (id, product_id, text, price) VALUES
(0, 0, '0.3 л', 150),
(1, 0, '0.4 л', 180),
(2, 0, '0.5 л', 210),
(3, 1, '200 гр.', 250),
(4, 2, '0.3 л', 170),
(5, 2, '0.4 л', 200),
(6, 3, '0.2 л', 120),
(7, 3, '0.3 л', 150),
(8, 4, '200 гр.', 190),
(9, 5, '150 гр.', 320),
(10, 6, '0.3 л', 220),
(11, 6, '0.4 л', 250),
(12, 7, '200 гр.', 280);

INSERT INTO topping (id, name, price) VALUES
(0, 'Сироп ваниль', 20),
(1, 'Корица', 15),
(2, 'Карамельный сироп', 25),
(3, 'Шоколадная крошка', 30),
(4, 'Взбитые сливки', 35),
(5, 'Кокосовая стружка', 20);

INSERT INTO product_toppings (products_id, toppings_id) VALUES
(0, 0),
(0, 1),
(2, 0),
(2, 2),
(3, 1),
(6, 3),
(6, 4),
(7, 5);


INSERT INTO discount (id, name, description, imageurl, discount_percentage) VALUES
(0, 'Кофейная Тройка', 'Три вкусных напитка по выгодной цене с возможностью добавить топпинги.', 'https://statics.mixitup.ru/img/uploads/product2/xl/121/1564.jpg', 0.2),
(1, 'Утро с круассаном', 'Кофе + круассан со невероятной скидкой, чтобы начать утро с позитива!', 'https://cdn.prod.website-files.com/5f92b98ef775e43402afe27f/6329ae33ae41231f78e6c511_Polyakovfoto_Simple%20Coffee17874.jpg', 0.25),
(2, 'Десертный день', 'Любой десерт с напитком по выгодной цене только сегодня!', 'https://majaro.ru/wa-data/public/photos/76/02/276.c329e5aac088f1fc9c1f0e45ad4977f6/276.970.jpg', 0.15);

INSERT INTO discount_products (products_id, discounts_id) VALUES
(0, 0),
(2, 0),
(3, 0),
(2, 1),
(4, 1),
(5, 2),
(6, 2),
(7, 2);

INSERT INTO cafe_address (id, address) VALUES
(0, 'Москва, Тверская улица, 9'),
(1, 'Москва, Улица Шверника, 9'),
(2, 'Москва, Кутузовский проспект, 48'),
(3, 'Москва, Улица Арбат, 23'),
(4, 'Москва, Ленинский проспект, 32'),
(5, 'Москва, Улица Новый Арбат, 15'),
(6, 'Москва, Улица Покровка, 16'),
(7, 'Москва, Улица Большая Дмитровка, 10'),
(8, 'Москва, Улица Маросейка, 4'),
(9, 'Москва, Улица Рождественка, 5/7');


INSERT INTO role (id, name) VALUES
(0, 'ROLE_COOK');

INSERT INTO cafe_employee (id, first_name, last_name, password, username, cafe_address_id, role_id) VALUES
(0, 'Никита', 'Туганов', '$2a$10$tUUkcRb2Jt97ZeEiz/YBheQg7FExP4MNJBhKOsakEzDHVOQJT6ek6', 'cook', 0, 0),
(1, 'Алексей', 'Иванов', '$2a$10$tUUkcRb2Jt97ZeEiz/YBheQg7FExP4MNJBhKOsakEzDHVOQJT6ek6', 'cook1', 1, 0),
(2, 'Дмитрий', 'Петров', '$2a$10$tUUkcRb2Jt97ZeEiz/YBheQg7FExP4MNJBhKOsakEzDHVOQJT6ek6', 'cook2', 2, 0),
(3, 'Сергей', 'Сидоров', '$2a$10$tUUkcRb2Jt97ZeEiz/YBheQg7FExP4MNJBhKOsakEzDHVOQJT6ek6', 'cook3', 3, 0),
(4, 'Андрей', 'Смирнов', '$2a$10$tUUkcRb2Jt97ZeEiz/YBheQg7FExP4MNJBhKOsakEzDHVOQJT6ek6', 'cook4', 4, 0),
(5, 'Михаил', 'Кузнецов', '$2a$10$tUUkcRb2Jt97ZeEiz/YBheQg7FExP4MNJBhKOsakEzDHVOQJT6ek6', 'cook5', 5, 0),
(6, 'Евгений', 'Васильев', '$2a$10$tUUkcRb2Jt97ZeEiz/YBheQg7FExP4MNJBhKOsakEzDHVOQJT6ek6', 'cook6', 6, 0),
(7, 'Анна', 'Павлова', '$2a$10$tUUkcRb2Jt97ZeEiz/YBheQg7FExP4MNJBhKOsakEzDHVOQJT6ek6', 'cook7', 7, 0),
(8, 'Ольга', 'Николаева', '$2a$10$tUUkcRb2Jt97ZeEiz/YBheQg7FExP4MNJBhKOsakEzDHVOQJT6ek6', 'cook8', 8, 0),
(9, 'Ирина', 'Федорова', '$2a$10$tUUkcRb2Jt97ZeEiz/YBheQg7FExP4MNJBhKOsakEzDHVOQJT6ek6', 'cook9', 9, 0);
-- password : cook1
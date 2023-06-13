INSERT INTO cimodule (id, name, type, price)
VALUES ('1', 'Ziggo', 'Digital Plus', '14.99'),
       ('2', 'Caiway', 'Tv digital', '12.50');

INSERT INTO remote_controller (id, compatible_with, battery_type, name, brand, price, original_stock)
VALUES ('1', 'Samsung', 'AAA', 'Power', 'Trust', '15.99', '10'),
       ('2', 'LG', 'AA', 'Magic', 'LG', '19.99', '15');

INSERT INTO televisions (id, brand, name, price, available_size, refresh_rate, screen_type, screen_quality,
                         smart_tv, wifi, voice_control, hdr, bluetooth, ambi_light, original_stock, sold, remote_controller_id, ci_module_id)
VALUES ('1', 'LG', 'OLED55C24LA', '1699.00', '55.00', '200.00', 'oled', 'super', 'true', 'true', 'true', 'true', 'true', 'false', '12', '5', '1', '1'),
       ('2', 'Samsung', 'QLED 65Q74B', '1199.00', '65.00', '200.00', 'qled', 'super', 'true', 'true', 'true', 'true', 'true', 'false', '12', '5', '2', '2');

INSERT INTO wall_bracket (id, size, adjustable, name, price)
VALUES ('1', '55', 'false', 'Flat mounting', '35.50'),
       ('2', '65', 'true', 'Vogel', '145.50');

INSERT INTO televisions_wall_brackets (televisions_id, wall_brackets_id)
VALUES ('1', '1'),
       ('1', '2'),
       ('2', '2');

INSERT INTO users (username, password, api_key, enabled, email)
VALUES ('Johan', 'password', 'apiapiapiapi', 'true', 'info@johanvanderKlift.nl');

INSERT INTO users (username, password, api_key, enabled, email)
VALUES ('Yara', 'password', 'apiapiapiapi', 'true', 'Yara@yara.nl');

INSERT INTO authorities (authority, username)
VALUES ('ROLE_USER', 'Johan');

INSERT INTO authorities (authority, username)
VALUES ('ROLE_ADMIN', 'Johan');

INSERT INTO authorities (authority, username)
VALUES ('ROLE_USER', 'Yara');
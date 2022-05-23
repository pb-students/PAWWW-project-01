insert into categories (id, name) values (1, 'No category');
insert into categories (id, name) values (2, 'Web development');

-- password: root
insert into users (id, username, first_name, last_name, password, age, role) values (1, 'root', '', '', '$2a$10$TqVi8gz4KVoyZ2bO/bJl6uZC1oW2H.EmeY.1tnri6SXNcBQZTRgE6', 666, 'admin');

-- passwords: 123456
insert into users (id, username, password, matching_password, first_name, last_name, age, role) values (2, 'xd', '$2a$10$IIm9YpKvMRZhgWyRN0Or2uHyv57mu9pliNMIWXDnmXOZr8j2aH4Ei', '$2a$10$IIm9YpKvMRZhgWyRN0Or2uHyv57mu9pliNMIWXDnmXOZr8j2aH4Ei', 'Raz', 'Dwa', 19, 'limited_user');
insert into users (id, username, password, matching_password, first_name, last_name, age, role) values (3, 'xd1', '$2a$10$IIm9YpKvMRZhgWyRN0Or2uHyv57mu9pliNMIWXDnmXOZr8j2aH4Ei', '$2a$10$IIm9YpKvMRZhgWyRN0Or2uHyv57mu9pliNMIWXDnmXOZr8j2aH4Ei', 'Raz', 'Dwa', 19, 'user');
insert into users (id, username, password, matching_password, first_name, last_name, age, role) values (4, 'xd2', '$2a$10$IIm9YpKvMRZhgWyRN0Or2uHyv57mu9pliNMIWXDnmXOZr8j2aH4Ei', '$2a$10$IIm9YpKvMRZhgWyRN0Or2uHyv57mu9pliNMIWXDnmXOZr8j2aH4Ei', 'Raz', 'Dwa', 19, 'user');
insert into users (id, username, password, matching_password, first_name, last_name, age, role) values (5, 'admin', '$2a$10$IIm9YpKvMRZhgWyRN0Or2uHyv57mu9pliNMIWXDnmXOZr8j2aH4Ei', '$2a$10$IIm9YpKvMRZhgWyRN0Or2uHyv57mu9pliNMIWXDnmXOZr8j2aH4Ei', 'XDD', 'Dwa', 19, 'admin');

insert into information (id, title, content, link, created_at, category_id, author_id) values (1, 'Opinie o projekcie', 'super, bardzo fajny, podoba się, gustowny, elegancki, tak trzymać', 'https://rom4nik.pl', '23-05-2022', 1, 3);
insert into information (id, title, content, link, created_at, category_id, author_id) values (2, 'Ranking frameworków', 'react > angular > vue', 'https://wvffle.net', '01-04-2022', 2, 1);
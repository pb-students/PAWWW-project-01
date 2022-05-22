insert into categories (id, name) values (1, 'No category');

-- password: root
insert into users (id, username, first_name, last_name, password, age, role) values (1, 'root', '', '', '$2a$10$TqVi8gz4KVoyZ2bO/bJl6uZC1oW2H.EmeY.1tnri6SXNcBQZTRgE6', 666, 'admin');

-- passwords: 123456
insert into users (id, username, password, matching_password, first_name, last_name, age, role) values (2, 'xd', '$2a$10$IIm9YpKvMRZhgWyRN0Or2uHyv57mu9pliNMIWXDnmXOZr8j2aH4Ei', '$2a$10$IIm9YpKvMRZhgWyRN0Or2uHyv57mu9pliNMIWXDnmXOZr8j2aH4Ei', 'Raz', 'Dwa', 19, 'limited_user');
insert into users (id, username, password, matching_password, first_name, last_name, age, role) values (3, 'xd1', '$2a$10$IIm9YpKvMRZhgWyRN0Or2uHyv57mu9pliNMIWXDnmXOZr8j2aH4Ei', '$2a$10$IIm9YpKvMRZhgWyRN0Or2uHyv57mu9pliNMIWXDnmXOZr8j2aH4Ei', 'Raz', 'Dwa', 19, 'user');
insert into users (id, username, password, matching_password, first_name, last_name, age, role) values (4, 'xd2', '$2a$10$IIm9YpKvMRZhgWyRN0Or2uHyv57mu9pliNMIWXDnmXOZr8j2aH4Ei', '$2a$10$IIm9YpKvMRZhgWyRN0Or2uHyv57mu9pliNMIWXDnmXOZr8j2aH4Ei', 'Raz', 'Dwa', 19, 'user');
insert into users (id, username, password, matching_password, first_name, last_name, age, role) values (5, 'admin', '$2a$10$IIm9YpKvMRZhgWyRN0Or2uHyv57mu9pliNMIWXDnmXOZr8j2aH4Ei', '$2a$10$IIm9YpKvMRZhgWyRN0Or2uHyv57mu9pliNMIWXDnmXOZr8j2aH4Ei', 'XDD', 'Dwa', 19, 'admin');

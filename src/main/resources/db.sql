CREATE SCHEMA IF NOT EXISTS website_for_university;

USE website_for_university;

CREATE TABLE IF NOT EXISTS users
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

INSERT INTO users (username, email, password)
VALUES ('alice', 'alice@example.com', 'password123'),
       ('bob', 'bob@example.com', 'securepass'),
       ('charlie', 'charlie@example.com', 'pass1234'),
       ('david', 'david@example.com', 'davidpass'),
       ('emma', 'emma@example.com', 'emmapassword'),
       ('frank', 'frank@example.com', 'frank123'),
       ('grace', 'grace@example.com', 'gracepass'),
       ('henry', 'henry@example.com', 'henry123'),
       ('isabel', 'isabel@example.com', 'isabelpass'),
       ('jacob', 'jacob@example.com', 'jacobpassword');


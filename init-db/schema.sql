CREATE TABLE users
(
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(500) NOT NULL,
    enabled  BOOLEAN NOT NULL
);

CREATE TABLE authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users(username)
);

CREATE TABLE additional_information (
    username VARCHAR(50) NOT NULL REFERENCES users(username),
    email VARCHAR(254) NOT NULL UNIQUE
);

CREATE UNIQUE INDEX ix_auth_username ON authorities (username,authority);


create schema IF not exists `security-demo` DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

use `security-demo`;

-- auto-generated definition
create table user
(
    id       int auto_increment
        primary key,
    username varchar(50)  null,
    password varchar(500) null,
    enabled  tinyint(1)   not null,
    constraint user_name_index
        unique (username)
)
    engine = InnoDB;



INSERT INTO `security-demo`.user (id, username, password, enabled) VALUES (1, 'admin', '{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 1);
INSERT INTO `security-demo`.user (id, username, password, enabled) VALUES (2, 'Helen', '{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 1);
INSERT INTO `security-demo`.user (id, username, password, enabled) VALUES (3, 'Tom', '{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 1);


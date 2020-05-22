create table requestip
(
    ID int primary key auto_increment,
    ip varchar(16),
    boolvalue tinyint(1) DEFAULT NULL,
    tag varchar(64)
);

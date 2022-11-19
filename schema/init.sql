CREATE TABLE coupon
(
    id       INT NOT NULL AUTO_INCREMENT,
    name     VARCHAR(100),
    quantity INTEGER,

    PRIMARY KEY (id)
) ENGINE= MYISAM CHARSET=utf8;
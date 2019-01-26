CREATE TABLE IF NOT EXISTS `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO product VALUES(null, 'NetBook', 350);
INSERT INTO product VALUES(null, 'Robot vacuum', 500);
INSERT INTO product VALUES(null, 'Ping Pong Table', 750);
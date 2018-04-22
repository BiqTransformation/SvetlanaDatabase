CREATE TABLE `chain` (
  `chain_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `subchain` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`chain_id`),
  UNIQUE KEY `chain_un` (`name`,`subchain`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8


CREATE TABLE `mall` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(30) NOT NULL,
  `address` char(100) NOT NULL,
  `mall_group` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8

CREATE TABLE `store` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_chain_fk` int(11) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `store_num` varchar(15) DEFAULT NULL,
  `mall_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `store_un` (`id_chain_fk`,`address`),
  KEY `store_mall_fk` (`mall_id`),
  CONSTRAINT `store_chain_fk` FOREIGN KEY (`id_chain_fk`) REFERENCES `chain` (`chain_id`),
  CONSTRAINT `store_mall_fk` FOREIGN KEY (`mall_id`) REFERENCES `mall` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8


CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `passport` varchar(100) DEFAULT NULL,
  `chain_id` int(11) DEFAULT NULL,
  `store_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `employee_un` (`first_name`,`last_name`,`passport`),
  KEY `employee_chain_fk` (`chain_id`),
  KEY `employee_store_fk` (`store_id`),
  CONSTRAINT `employee_chain_fk` FOREIGN KEY (`chain_id`) REFERENCES `chain` (`chain_id`),
  CONSTRAINT `employee_store_fk` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8

CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `enabled` int(11) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `users` (`id`, `email`, `enabled`, `password`, `username`) VALUES
	(1, 'admin@domain.com', 1, '$2a$10$m1T8GE.v915OpsciH9sKBuhpJFioo.GD/R0Wbj/p6TuUPO4g4mmlm', 'admin');


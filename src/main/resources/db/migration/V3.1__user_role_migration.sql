CREATE TABLE IF NOT EXISTS user_roles (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  role varchar(50) NOT NULL,
  userid varchar(50) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY role (role)
);

INSERT INTO user_roles (id, role, userid) VALUES
	(1, 'ROLE_ADMIN', 'admin');
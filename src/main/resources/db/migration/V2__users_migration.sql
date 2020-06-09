CREATE TABLE IF NOT EXISTS users (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  email varchar(50) NOT NULL,
  enabled int(11) NOT NULL,
  password varchar(100) DEFAULT NULL,
  username varchar(50) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY email (email)
);

INSERT INTO users (id, email, enabled, password, username) VALUES
	(1, 'admin@domain.com', 1, '$2a$10$wVPUcGbRLgpEnCMDNYuzJ.WXa8VVdprbBReQ4W1IMelGGSVP8WXmO', 'admin');
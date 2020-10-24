CREATE TABLE IF NOT EXISTS users (
  username varchar(50) DEFAULT NULL,
  email varchar(50) NOT NULL,
  password varchar(100) DEFAULT NULL,
  enabled int(11) NOT NULL,
  PRIMARY KEY (username),
  UNIQUE KEY email (email)
);

INSERT INTO users (id, email, enabled, password, username) VALUES
	('admin', 'admin@domain.com', 1, '$2a$10$wVPUcGbRLgpEnCMDNYuzJ.WXa8VVdprbBReQ4W1IMelGGSVP8WXmO');
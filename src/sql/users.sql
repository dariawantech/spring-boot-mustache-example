CREATE TABLE `users` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,  
  `phone` VARCHAR(255),
  `address` VARCHAR(255), 
  PRIMARY KEY (`id`)
) ENGINE=MYISAM DEFAULT CHARSET=latin1;

CREATE UNIQUE INDEX id_user_email ON users (email(255));

insert into users (`name`, email, phone, address)
values 
('Charlie Croker', 'charlie@cappucino.com', '98765432', 'Los Angeles, California'),
('Stella Bridger', 'stella@cappucino.com', '87654321', 'Los Angeles, California'),
('Handsome Rob', 'handsome@cappucino.com', '76543210', 'Beverly Hills, California'),
('Lyle', 'lyle@cappucino.com', '65432102', 'Los Angeles, California'),
('Gilligan "Left Ear"', 'gilligan@cappucino.com', '54321023', null),
('Wrench', 'wrench@cappucino.com', '43210234', null)
--
-- Spring Boot + Mustache Example  (https://www.dariawan.com)
-- Copyright (C) 2019 Dariawan <hello@dariawan.com>
--
-- Creative Commons Attribution-ShareAlike 4.0 International License
--
-- Under this license, you are free to:
-- # Share - copy and redistribute the material in any medium or format
-- # Adapt - remix, transform, and build upon the material for any purpose,
--   even commercially.
--
-- The licensor cannot revoke these freedoms
-- as long as you follow the license terms.
--
-- License terms:
-- # Attribution - You must give appropriate credit, provide a link to the
--   license, and indicate if changes were made. You may do so in any
--   reasonable manner, but not in any way that suggests the licensor
--   endorses you or your use.
-- # ShareAlike - If you remix, transform, or build upon the material, you must
--   distribute your contributions under the same license as the original.
-- # No additional restrictions - You may not apply legal terms or
--   technological measures that legally restrict others from doing anything the
--   license permits.
--
-- Notices:
-- # You do not have to comply with the license for elements of the material in
--   the public domain or where your use is permitted by an applicable exception
--   or limitation.
-- # No warranties are given. The license may not give you all of
--   the permissions necessary for your intended use. For example, other rights
--   such as publicity, privacy, or moral rights may limit how you use
--   the material.
--
-- You may obtain a copy of the License at
--   https://creativecommons.org/licenses/by-sa/4.0/
--   https://creativecommons.org/licenses/by-sa/4.0/legalcode
--
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
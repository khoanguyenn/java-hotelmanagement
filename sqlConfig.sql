# **Created by Truong Hoang Phuong Nhu**
# The script create new tables and add some data to these tables
# Create database
CREATE DATABASE hotel;
USE hotel;

# Create all of the table
CREATE TABLE IF NOT EXISTS room (
	room_number VARCHAR(255) NOT NULL, 
    room_status VARCHAR(255), 
    room_type VARCHAR(255), 
    PRIMARY KEY (room_number)) 
    ENGINE=InnoDB;
    
CREATE TABLE IF NOT EXISTS customer (
	customer_id INTEGER NOT NULL AUTO_INCREMENT, 
	first_name VARCHAR(255),
	last_name VARCHAR(255),    
    email VARCHAR(255),
    dob DATE, 
	address VARCHAR(255),
    gender VARCHAR(255),
    telephone_number VARCHAR(255), 
    PRIMARY KEY (customer_id)) 
    ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS booking (
	booking_id INTEGER NOT NULL AUTO_INCREMENT, 
    booking_method VARCHAR(255), 
    check_in DATE, 
    check_out DATE, 
    customer_id INTEGER REFERENCES customer(customer_id), 
    room_number VARCHAR(255), 
    PRIMARY KEY (booking_id)) 
	ENGINE=InnoDB;

#Adding data to the room table
INSERT INTO room (room_number, room_type, room_status) VALUES
(101, 'Twin Room', 'Available'),
(102, 'Twin Room', 'Reserved'),
(103, 'Triple Room', 'Staying'),
(104, 'Twin Room', 'Reserved'),
(105, 'Queen Room', 'Available'),
(201, 'Twin Room', 'Staying'),
(202, 'Triple Room', 'Available'),
(203, 'Queen Room', 'Available'),
(204, 'Twin Room', 'Available'),
(205, 'Triple Room', 'Reserved'),
(301, 'Triple Room', 'Available'),
(302, 'Queen Room', 'Available'),
(303, 'Twin Room', 'Staying'),
(304, 'Twin Room', 'Reserved'),
(305, 'Triple Room', 'Available'),
(401, 'Twin Room', 'Available'),
(402, 'Queen Room', 'Reserved'),
(403, 'Queen Room', 'Staying'),
(404, 'Triple Room', 'Staying'),
(405, 'Queen Room', 'Available'),
(501, 'Twin Room', 'Reserved'),
(502, 'Triple Room', 'Available'),
(503, 'Queen Room', 'Available'),
(504, 'Triple Room', 'Reserved'),
(505, 'Twin Room', 'Staying');

#Adding data to customer
INSERT INTO customer (customer_id, first_name, last_name, email, dob, address, gender, telephone_number) VALUES
(1, 'Winnifred', 'Jewster', 'wjewster0@marketwatch.com', '1916-10-19', '26469 Shoshone Court', 'Polygender', '2796876296'),
(2, 'Thedrick', 'Conyard', 'tconyard1@msu.edu', '1988-10-12', '25 Hansons Parkway', 'Female', '6926301629'),
(3, 'Athena', 'Brisseau', 'abrisseau2@gnu.org', '1968-08-06', '30274 Harbort Trail', 'Male', '5245151946'),
(4, 'Dorolisa', 'Hanne', 'dhanne3@sciencedaily.com', '1944-01-02', '0 Loomis Center', 'Female', '1212047920'),
(5, 'Oran', 'Walkling', 'owalkling4@zdnet.com', '1978-05-01', '14 Vermont Junction', 'Agender', '3958766835'),
(6, 'Pru', 'Yeats', 'pyeats5@discovery.com', '1939-12-30', '2888 Bluestem Terrace', 'Female', '5033867011'),
(7, 'Barnett', 'Bousler', 'bbousler6@sakura.ne.jp', '1950-07-09', '99298 Hovde Drive', 'Agender', '1527871600'),
(8, 'Cati', 'Cicullo', 'ccicullo7@topsy.com', '1948-05-15', '779 Redwing Point', 'Female', '2286107916'),
(9, 'Shea', 'Durie', 'sdurie8@msu.edu', '1928-09-09', '0 Cascade Park', 'Genderqueer', '5042742445'),
(10, 'Carmine', 'D''Elia', 'cdelia9@examiner.com', '1952-04-21', '06239 Luster Court', 'Male', '5879825604'),
(11, 'Lee', 'Lilburn', 'llilburna@pen.io', '1954-08-22', '06 Eliot Place', 'Agender', '7681473903'),
(12, 'Vivien', 'Andreuzzi', 'vandreuzzib@etsy.com', '1979-06-13', '53353 Melvin Drive', 'Male', '1762316764'),
(13, 'Gina', 'Tumini', 'gtuminic@weibo.com', '1929-05-03', '1 Prairieview Crossing', 'Female', '9495944723'),
(14, 'Stanton', 'Caig', 'scaigd@disqus.com', '1944-06-04', '127 Dawn Hill', 'Genderfluid', '5645242227'),
(15, 'Jake', 'Joost', 'jjooste@addtoany.com', '1951-06-04', '2839 Lighthouse Bay Junction', 'Male', '8096191007'),
(16, 'Othilia', 'Varfalameev', 'ovarfalameevf@t-online.de', '1949-07-06', '84 Pierstorff Pass', 'Female', '8128404955'),
(17, 'Arlyne', 'Reany', 'areanyg@odnoklassniki.ru', '1968-10-07', '9 Evergreen Hill', 'Male', '8761876997'),
(18, 'Kathrine', 'Singleton', 'ksingletonh@dyndns.org', '1928-05-29', '78 Bultman Place', 'Female', '6787291938'),
(19, 'Pooh', 'Rumble', 'prumblei@miitbeian.gov.cn', '1920-06-14', '933 Becker Trail', 'Female', '6398643687'),
(20, 'Sarita', 'Cayser', 'scayserj@vimeo.com', '1985-06-06', '29575 Iowa Drive', 'Female', '9068348604'),
(21, 'Rory', 'Tough', 'rtoughk@answers.com', '1973-05-12', '228 Sloan Way', 'Female', '9031266039'),
(22, 'Clive', 'Bosher', 'cbosherl@histats.com', '1988-04-27', '03398 Express Junction', 'Female', '5715603636'),
(23, 'Nehemiah', 'Peet', 'npeetm@parallels.com', '1906-07-30', '29669 Jenifer Plaza', 'Female', '6836888557'),
(24, 'Farleigh', 'Gehringer', 'fgehringern@upenn.edu', '1906-04-27', '67677 Hermina Junction', 'Polygender', '6678766342'),
(25, 'Reggis', 'Adenet', 'radeneto@usa.gov', '1901-04-17', '7584 Loomis Street', 'Agender', '6572535409'),
(26, 'Teddie', 'Fishburn', 'tfishburnp@ocn.ne.jp', '1910-11-26', '15 Lake View Crossing', 'Female', '5389855096'),
(27, 'Sonnie', 'Jeanon', 'sjeanonq@google.de', '1980-03-21', '74 Anhalt Road', 'Female', '5071803010'),
(28, 'Elianore', 'Baudinet', 'ebaudinetr@booking.com', '1961-08-19', '06799 David Pass', 'Female', '8565726479'),
(29, 'Durant', 'Hirth', 'dhirths@msu.edu', '1986-01-20', '100 Stuart Center', 'Female', '2075403286'),
(30, 'Zack', 'Gooderson', 'zgoodersont@wikispaces.com', '1982-11-19', '74373 Bunker Hill Pass', 'Male', '8289900788');

#Adding data to booking
INSERT INTO booking (booking_id, booking_method, check_in, check_out, customer_id, room_number) VALUES
(1, 'On desk', '2020-04-01', '2020-04-06', 23, '401'),
(2, 'Via telephone', '2020-02-24', '2020-02-25', 19, '402'),
(3, 'On desk', '2020-05-01', '2020-05-24', 8, '403'),
(4, 'On desk', '2020-04-18', '2020-04-25', 16, '506'),
(5, 'Via telephone', '2020-03-05', '2020-03-27', 26, '302'),
(6, 'On desk', '2020-08-08', '2020-08-30', 30, '205'),
(7, 'On desk', '2020-12-16', '2020-12-20', 29, '207'),
(8, 'Via telephone', '2020-07-05', '2020-07-23', 23, '407'),
(9, 'On desk', '2020-02-04', '2020-03-12', 20, '501'),
(10, 'On desk', '2020-03-01', '2020-03-26', 20, '505'),
(11, 'On desk', '2021-08-20', '2020-08-26', 15, '204'),
(12, 'On desk', '2021-01-01', '2020-01-03', 11, '205'),
(13, 'On desk', '2021-03-06', '2020-03-17', 12, '304'),
(14, 'Via telephone', '2021-02-03', '2020-02-12', 26, '405'),
(15, 'On desk', '2020-08-24', '2020-08-30', 27, '501'),
(16, 'Via telephone', '2020-11-21', '2021-11-21', 28, '502'),
(17, 'Via telephone', '2020-03-19', '2020-03-27', 30, '205'),
(18, 'On desk', '2020-04-07', '2020-04-26', 24, '103'),
(19, 'Via telephone', '2020-07-24', '2020-07-28', 27, '105'),
(20, 'On desk', '2020-04-09', '2020-04-29', 29, '102'),
(21, 'Via telephone', '2020-03-18', '2020-03-24', 30, '303'),
(22, 'On desk', '2020-07-30', '2020-08-03', 18, '301'),
(23, 'Via telephone', '2020-09-14', '2020-09-24', 19, '401'),
(24, 'On desk', '2021-02-19', '2020-02-20', 20, '405'),
(25, 'Via telephone', '2020-03-16', '2020-03-28', 21, '301'),
(26, 'Via telephone', '2020-12-17', '2020-12-25', 21, '302'),
(27, 'Via telephone', '2020-07-10', '2020-07-13', 28, '401'),
(28, 'Via telephone', '2020-10-28', '2020-10-30', 30, '405'),
(29, 'Via telephone', '2020-11-03', '2020-11-04', 13, '503'),
(30, 'Via telephone', '2020-10-26', '2021-10-30', 8, '502');
create database apartments;

use apartments;

create table APARTMENT (
   id INT NOT NULL,
   area DOUBLE(5,2) NOT NULL,
   price DOUBLE(10,2) NOT NULL,
   PRIMARY KEY (id)
);


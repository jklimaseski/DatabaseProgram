create database people;
use people;

create table students
(ID int unsigned not null auto_increment,
 PRIMARY KEY(ID),
student_name varchar(45) Not Null, credit double unsigned, major varchar(15), campus varchar(25),street varchar(75),state varchar(2),zip varchar(5), phone varchar(10),tuition varchar(15), gpa varchar(4));
create table faculty
(ID int unsigned not null auto_increment,
 PRIMARY KEY(ID),
faculty_name varchar(45) Not Null, credit double unsigned, room_number varchar(15), office_number varchar(15),street varchar(75),state varchar(2),zip varchar(5), phone varchar(10),pay varchar(15), rank varchar(15));

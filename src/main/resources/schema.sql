DROP TABLE IF EXISTS department;
DROP TABLE  IF exists employee;

CREATE TABLE department(
                           id bigint auto_increment PRIMARY KEY ,
                           name VARCHAR (100) not null
);
CREATE TABLE employee(
                         id bigint not null auto_increment primary key ,
                         firstName VARCHAR(60) not null ,
                         lastName VARCHAR(60) not null ,
                         birthDate DATE not null ,
                         salary double not null ,
                         departmentId bigint not null
);

alter table employee add foreign key (departmentId) references department(id) on UPDATE cascade  on delete cascade;


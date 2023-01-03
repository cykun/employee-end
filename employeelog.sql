create database employee;
use employee;

drop table if exists employee;
create table employee (
    id int not null primary key ,
    name varchar(32) not null
) CHARSET=utf8;

insert into employee values (1, "员工1");
insert into employee values (2, "员工2");
insert into employee values (3, "员工3");
insert into employee values (4, "员工4");
insert into employee values (5, "员工5");

drop table if exists employee_log;
create table employee_log (
    employee_id int not null,
    function_id int not null,
    time datetime not null,
    primary key(employee_id, time)
) charset =utf8;

INSERT INTO employee_log (employee_id, function_id, create_time) VALUES (1, 1, '2022-12-17 09:02:29');
INSERT INTO employee_log (employee_id, function_id, create_time) VALUES (1, 2, '2022-12-17 09:15:40');
INSERT INTO employee_log (employee_id, function_id, create_time) VALUES (1, 3, '2022-12-17 10:03:57');
INSERT INTO employee_log (employee_id, function_id, create_time) VALUES (1, 1, '2022-12-19 16:26:57');
INSERT INTO employee_log (employee_id, function_id, create_time) VALUES (1, 2, '2022-12-19 16:27:34');
INSERT INTO employee_log (employee_id, function_id, create_time) VALUES (1, 1, '2022-12-20 09:49:44');
INSERT INTO employee_log (employee_id, function_id, create_time) VALUES (1, 2, '2022-12-20 14:55:36');
INSERT INTO employee_log (employee_id, function_id, create_time) VALUES (1, 1, '2022-12-21 10:50:08');
INSERT INTO employee_log (employee_id, function_id, create_time) VALUES (1, 2, '2022-12-21 14:50:23');
INSERT INTO employee_log (employee_id, function_id, create_time) VALUES (1, 2, '2022-12-21 17:34:58');
INSERT INTO employee_log (employee_id, function_id, create_time) VALUES (1, 2, '2022-12-21 17:56:22');
INSERT INTO employee_log (employee_id, function_id, create_time) VALUES (2, 1, '2022-12-17 09:41:18');
INSERT INTO employee_log (employee_id, function_id, create_time) VALUES (2, 2, '2022-12-17 10:41:35');
INSERT INTO employee_log (employee_id, function_id, create_time) VALUES (2, 3, '2022-12-17 10:52:46');
INSERT INTO employee_log (employee_id, function_id, create_time) VALUES (3, 2, '2022-12-17 15:42:07');
INSERT INTO employee_log (employee_id, function_id, create_time) VALUES (3, 4, '2022-12-17 15:42:18');
INSERT INTO employee_log (employee_id, function_id, create_time) VALUES (4, 1, '2022-12-17 15:42:27');
INSERT INTO employee_log (employee_id, function_id, create_time) VALUES (5, 5, '2022-12-17 15:42:34');

create database employee;
use employee;
/* 员工表 */
drop table if exists employee;
create table employee (
    id int not null auto_increment primary key ,
    name varchar(8) not null, /* 名字 */
    gender varchar(2) not null, /* 性别 */
    address varchar(64) not null, /* 家庭地址 */
    academic_degree varchar(4) not null, /* 学历 */
    workplace varchar(64) not null, /* 工作地址 */
    birthdate date not null,
    post varchar(16) not null,
    entry_time date not null
)  CHARSET=utf8 auto_increment=1 comment='员工表';

insert into employee values (1, "员工1", "男", "福州市", "硕士", "福州市", 19890102, "JAVA开发", 20150101);
insert into employee values (2, "员工2", "女", "厦门市", "硕士", "福州市", 19880806, "JAVA开发", 20160101);
insert into employee values (3, "员工3", "男", "广州市", "硕士", "深圳市", 19950707, "C++开发", 20200101);
insert into employee values (4, "员工4", "男", "上海市", "硕士", "上海市", 19940302, "HR", 20180101);
insert into employee values (5, "员工5", "女", "福州市", "硕士", "福州市", 19910304, "经理", 20160101);

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

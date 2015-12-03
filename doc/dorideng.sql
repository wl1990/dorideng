create database dorideng;
use dorideng;
drop table if exists user;
drop table if exists rightmenu;
drop table if exists production;
drop table if exists production_picture;
/**
 * 用户表
 */
create table user(
id int(11) auto_increment primary key,
userName 			varchar(50) 	default ' ' not null,
password 			varchar(255) 	default ' ' not null,
email 				varchar(50) 	default ' ' not null,
status 				char(4) 		default '0' ,
realName 			varchar(50) 	default ' ' ,
nickName 			varchar(50) 	default ' ' ,
registrationTime 	varchar(25) 	default ' ' not null,
level 				char(4) 		default '0' ,
imageUrl 			varchar(255) 	default ' ' ,
sex 				char(4) 		default ' ' ,
phone               varchar(20)     default ' ' ,
qq                  varchar(50)		default ' ' ,
weibo				varchar(50)		default ' ' 
);
-- insert into user(userName,password,email,registrationTime) values('123@qq.com','c418e9ab4c4d9d5feaf3a3d784d5452d','123@qq.com','2015-11-10 19:23:12');

-- select * from user;
/**
 * 权限菜单表
 * 
 */

create table rightMenu(
	id 			int(11) 	auto_increment primary key ,
	selfId 		varchar(20) 	default ' ' not null,
	parentId 	varchar(20)     default '' not null,
	menuName 	varchar(20) 	default ' 'not null,
	menuUrl 	varchar(100) 	default ' ' not null,
	imageUrl 	varchar(100) 	default ' ' ,
	menuType 	char(4) 		default ' ' ,
	menuMark 	char(4) 		default ' ' 
);



-- 产品
create table production(
id 			int(11) 	auto_increment primary key ,
pro_name  varchar(255) default ' ' not null,
pro_pic varchar(255) default ' ' not null,
pro_type int(5) not null default 0,
pro_selfid varchar(32) not null default ' ',
pro_parentid varchar(32) not null default 'root',
detail text
);

-- 产品图片
create table production_picture(
	id int(11) auto_increment primary key,
    production_id int(11) default 0,
    pro_url varchar(255) default ' ',
    status int(4) default 1 not null,
    create_time varchar(32) not null default ' '
);

select * from user;
select * from rightMenu;
select * from production;
select * from production_picture;


set sql_safe_updates=0;
delete from production_picture;


alter table family drop foreign key FK_family_father
alter table family_childrens drop foreign key FK_family_child
alter table family_childrens drop  foreign key FK_family_family

drop table if exists child
drop table if exists family
drop table if exists family_childrens
drop table if exists father

create table child (id integer not null AUTO_INCREMENT, first_name varchar(255), pesel varchar(11), second_name varchar(255), sex varchar(255), primary key (id))
create table family (id integer not null AUTO_INCREMENT, father_id integer, primary key (id))
create table family_childrens (family_id integer not null, childrens_id integer not null)
create table father (id integer not null AUTO_INCREMENT, birth_date date, first_name varchar(255), pesel varchar(11), second_name varchar(255), primary key (id))
alter table family_childrens add constraint UK_family_child unique (childrens_id)
alter table family add constraint FK_family_father foreign key (father_id) references father (id)
alter table family_childrens add constraint FK_family_child foreign key (childrens_id) references child (id)
alter table family_childrens add constraint FK_family_family foreign key (family_id) references family (id)
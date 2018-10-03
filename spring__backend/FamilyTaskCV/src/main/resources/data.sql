insert into child (first_name , pesel, second_name, sex) values ('Jan','34534534545','Marian','Mężczyzna')
insert into child (first_name , pesel, second_name, sex) values ('Frania','34534565445','Ola','Kobieta')
insert into child (first_name , pesel, second_name, sex) values ('Zenek','34534534545','Piotr','Mężczyzna')
insert into father (birth_date, first_name, pesel, second_name) values ('1992-04-12', 'Jakub','92041223445','Mateusz')
insert into family (father_id) values (NULL)
update family set father_id=1 where id=1
insert into family_childrens (family_id, childrens_id) values (1,1)
insert into family_childrens (family_id, childrens_id) values (1,2)
insert into family_childrens (family_id, childrens_id) values (1,3)
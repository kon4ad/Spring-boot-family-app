insert into child (first_name , pesel, second_name, sex) values ('Test1','34534534545','Test5','Mężczyzna')
insert into child (first_name , pesel, second_name, sex) values ('Test2','34534565445','Test6','Kobieta')
insert into child (first_name , pesel, second_name, sex) values ('Test3','14010312312','Test7','Mężczyzna')
insert into father (birth_date, first_name, pesel, second_name) values ('1992-04-12', 'Test10','92041223445','Test11')
insert into family (father_id) values (NULL)
update family set father_id=1 where id=1
insert into family_childrens (family_id, childrens_id) values (1,1)
insert into family_childrens (family_id, childrens_id) values (1,2)
insert into family_childrens (family_id, childrens_id) values (1,3)
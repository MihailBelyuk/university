use universities_db;

insert into addresses (city, street, house, post_index) values ('Vitebsk', 'Prospect Frunze', '10', '224033');
insert into addresses (city, street, house, post_index) values ('Vitebsk', 'Lenina str.', '5', '225666');
insert into addresses (city, street, house, post_index) values ('Vitebsk', 'Lenina str.', '10', '225666');
insert into addresses (city, street, house, post_index) values ('Vitebsk', 'Lenina str.', '11', '225666');
insert into addresses (city, street, house, post_index, flat ) values ('Vitebsk', 'Prospect stroiteley', '1', '225678','7');
insert into addresses (city, street, house, post_index, flat) values ('Vitebsk', 'Prospect stroiteley', '2', '225678', '15');
insert into addresses (city, street, house, post_index, flat) values ('Vitebsk', 'Prospect stroiteley', '2', '225678', '16');
insert into addresses (city, street, house, post_index, flat) values ('Vitebsk', 'Prospect stroiteley', '2', '225678', '17');
insert into addresses (city, street, house, post_index, flat) values ('Vitebsk', 'Prospect stroiteley', '2', '225678', '18');
insert into addresses (city, street, house, post_index, flat) values ('Vitebsk', 'Prospect stroiteley', '2', '225678', '19');
insert into addresses (city, street, house, post_index, flat) values ('Vitebsk', 'Prospect stroiteley', '2', '225678', '20');
insert into addresses (city, street, house, post_index, flat) values ('Vitebsk', 'Prospect stroiteley', '2', '225678', '21');
insert into addresses (city, street, house, post_index, flat) values ('Vitebsk', 'Prospect stroiteley', '2', '225678', '22');
insert into addresses (city, street, house, post_index, flat) values ('Vitebsk', 'Prospect stroiteley', '2', '225678', '23');
insert into addresses (city, street, house, post_index, flat) values ('Vitebsk', 'Prospect stroiteley', '2', '225678', '24');
insert into addresses (city, street, house, post_index, flat) values ('Vitebsk', 'Prospect stroiteley', '2', '225678', '25');
insert into addresses (city, street, house, post_index, flat) values ('Vitebsk', 'Prospect stroiteley', '2', '225678', '26');
insert into addresses (city, street, house, post_index, flat) values ('Vitebsk', 'Prospect stroiteley', '2', '225678', '27');
insert into addresses (city, street, house, post_index, flat) values ('Vitebsk', 'Prospect stroiteley', '2', '225678', '28');

insert into rectors (first_name, last_name, birthday, salary, addresses_id) values ('Anton',' Yatusevich', '1947-01-02', '2000', '1');

insert into trade_union_chairmen(first_name, last_name, birthday, salary, addresses_id) values ('Vasia', 'Vasiliev', '1971-01-01', '3000', '2' );
insert into trade_union_committies (trade_union_chairmen_id) value ('1');

insert into head_accountants  (first_name, last_name, birthday, salary, addresses_id) values ('Petr', 'Petrov', '1972-01-01', '2500',  '3');
insert into accounts_departments (head_accountants_id) value ('1');
insert into accountants (first_name, last_name, birthday, salary, addresses_id, accounts_departments_id) values ('Olga', 'Petrova', '1980-02-03', '700', '7','1'), ('Natalya', 'Rostova', '1980-02-04', '700', '8','1'), ('Yana', 'Zayceva', '1980-02-06', '700', '9','1');

insert into libraries (number_of_books) value ('3566');

insert into universities (name, established_at, rectors_id, trade_union_committies_id, libraries_id, accounts_departments_id, addresses_id) values ('Vitebsk state academy of veterinary medicine', '1924-11-04', '1','1','1','1','4');

insert into deans (first_name, last_name, birthday, salary, addresses_id ) values ('Alexandr','Alexandrov', '1974-02-02', '1000', '5' ),  ('Demyan','Demyanov', '1974-03-03', '1000', '6' );

insert into faculties (name, universities_id, deans_id) values ('Veterinary faculty', '1', '1'),('Zoo engineering faculty', '1', '2');

insert into chairs (name) value ('Physics and radiology'),('Anatomy'), ('Physiology'), ('Chemistry'), ('Surgery'), ('Therapy'), ('Zoohygiene');

insert into faculty_chairs(chair_id, faculty_id) values (1,1);
insert into faculty_chairs(chair_id, faculty_id) values (2,1);
insert into faculty_chairs(chair_id, faculty_id) values (3,1);
insert into faculty_chairs(chair_id, faculty_id) values (4,1);
insert into faculty_chairs(chair_id, faculty_id) values (13,4);
insert into faculty_chairs(chair_id, faculty_id) values (13,1);

-- Query to change birthday column type from timestamp to date in teachers table.
alter table teachers modify birthday date not null;
insert into teachers (first_name, last_name, birthday, salary, academic_status, chairs_id, addresses_id ) values ('Vladimir', 'Klemenkov', '1971-06-05', '1100', 'DOCENT', '1','9'),
('Alexandr', 'Liakh', '1980-03-05', '1100', 'DOCENT', '2','10'),('Mikhail', 'Makaruk', '1971-07-22', '1500', 'DOCENT', '3','11'),('Viktor', 'Kotovich', '1972-06-05', '1300', 'DOCENT', '4','12'),
('Segei', 'Karmalak', '1973-06-05', '1800', 'DOCENT', '5','13'),('Alexandr', 'Belko', '1973-06-07', '1600', 'DOCENT', '5','14'),('Vladimir', 'Zhurba', '1978-03-17', '1500', 'DOCENT', '5','15');
insert into teachers (first_name, last_name, birthday, salary, academic_status, chairs_id, addresses_id ) values ('Vladimir', 'Zuborev', '1971-06-05', '1100', 'DOCENT', '1','20');
insert into teachers (first_name, last_name, birthday, salary, academic_status, chairs_id, addresses_id ) values ('Demyan', 'Demyanov', '1974-03-03', '1000', 'DOCENT', '1','6');

insert into student_cards (student_number, issued_at, expires_at) values ('A1', '2020-09-01', '2025-07-01'), ('A2', '2020-09-01', '2025-07-01'), ('B1', '2021-09-01', '2025-07-01'),
('D1', '2018-09-01', '2023-07-01');

insert into students (first_name, last_name, birthday, scholarship, has_scholarship, lives_in_hostel, course, student_cards_id,faculties_id, addresses_id) 
values ('James', 'Bond', '2005-01-01','100',true, true, '3','1','1','16'), ('Bart', 'Simpson', '2005-02-01','0',false, false, '3','2','1','17'), ('Tom', 'Cruze', '2006-01-01','50',true,false, '2','3','2','18'), 
('Homer', 'Simpson', '1975-01-01','70',true,false, '5','4','2','19');

-- Query for setting scholarship 1000, true for lives_in_ hostel, true for has_scholarship for all students with name Bart.
update  students set scholarship='1000', lives_in_hostel=true, has_scholarship=true where first_name='Bart';
-- Query for changing address parameters for address with id  38.
update  addresses set city='Brest', street='Syabrovskaya', house='99', flat = '12' where id='17';
-- Query to change academic status for teacher with last name 'Makaruk'.
update teachers set academic_status='PROFESSOR' where last_name = 'Makaruk';
-- Query to change salary to 2500 for every accounant with current salary less than 1500.
update accountants set salary=2500 where salary <1500;
-- Query for changing chair name for 'Chemistry' chair.
update chairs set name='Organic and non organic chemistry' where name='Chemistry';
-- Query for changing trade union chairman's with id 1 to 1.
update trade_union_chairmen set salary = 1 where id=1;
-- Query for updating dean's information with id 1.
update deans set first_name = 'Nikolai', last_name =' Tolkach', birthday = '1973-09-07', addresses_id = 19 where id=1;
-- Query for setting salary of 2500 for teachers who have current salary more than 1000 and less than 1500.
update teachers set salary=2500 where salary between 1000 and 1500;
-- Query to update salary for teachers with last name Makaruk or Zhurba.
update teachers set salary = 3000 where last_name = 'Makaruk' or last_name = 'Zhurba';
-- Query for letting  Bart be the only student living in hostel.
update students set lives_in_hostel = false where  not  first_name = 'Bart' ;

-- Query for deleting 1 teacher from teachers table with name 'Alexandr' starting from the end of the table.
delete from teachers where first_name = 'Alexandr'  order by id desc limit 1;	
-- Query for deleting chair with name 'Therapy' from the chairs table.
delete from chairs where name='Therapy';
-- Query for deleting all students with scholarship less than 100 from students table.
delete from students where scholarship<100;
-- Query for deleting all teachers from the teachers table.
delete from teachers;
-- Query for deleting all teachers with id less than 25 and that have name 'Alexandr'.
delete from teachers where id< 25 and first_name = 'Alexandr';
-- Query for deleting 2  teachers starting from id 26.
delete from teachers where id >25 order by id asc limit 2;
-- Query for deleting all faculties that contain "engi" letter sequence in their name.
delete from faculties where name like '%engi%';
-- Query for deleting teacher with id 2 from teachers table.
delete from teachers where id=2;
-- Query for deleting teacher with name Efim Fomin from teachers table.
delete from teachers where last_name = 'Fomin' and first_name='Efim';
-- Query for deleting all students who don't have last name 'Simpson' from students table. 
delete from students where last_name not like 'Simpson';

-- Query to show all teachers's id;
select id as teacher_id from teachers;
-- Query to show id, first and last name for all teachers;
select id as teacher_id, first_name as teacher_first_name, last_name as teacher_last_name from teachers;
-- Query to show all universitis' names.
select name as university_name from universities;
-- Query to show all students' first name, last name and rather student lives in hostel.
select first_name as student_first_name, last_name as student_last_name, lives_in_hostel as student_lives_in_hostel from  students;
-- Query to show first name and last name of all accountants from accountants table.
select first_name as accountant_first_name, last_name as accountant_last_name from accountants;
-- Query to show all  ids and last names for teachers  with first name 'Vladimir'.
select  id as teacher_id, last_name as teacher_last_name from teachers where first_name='Vladimir';
-- Query to show all accountants with salary less than 1500 or more than 2000 form accountants table.
select  id as accountant_id, last_name as accountant_last_name from accountants where salary <1500 or salary >2000;
-- Query to show all student cards with column names changed and which expire after 2023-08-01.
select id as student_card_id, issued_at as assue_date, expires_at as expiry_date from  student_cards where expires_at> '2023-08-01';
-- Query to show city of address with house 99 from addresses table.
select city from addresses where house='99';
-- Query to show all addresses that have flat is not null.
select id as address_id, city,  street, house from addresses where flat is not null;
-- Query to show all addresses that have flat is null.
select id as address_id, city,  street, house from addresses where flat is null;
-- Query to show all addresses' cities in alphabetical order.
select city from addresses order by city;
-- Query to show all addresses' cities and houses in descending house number order.
select city, house from addresses order by house desc;
-- Query to show id and last name of all teachers who's first name matches 'Alexandr' or 'Vladimir' or 'Viktor'.
select id as teacher_id, last_name as teacher_last_name from teachers where first_name in('Alexandr','Vladimir', 'Viktor');

-- Query to combine result set of tables (id,first name, last name) and addresses(id, city, street, house) by id.
select t.id as teacher_id, t.first_name as teacher_first_name, t.last_name as teacher_last_name, a.id as address_id, a.city, a.street, a.house
from teachers t inner join addresses a on t.addresses_id=a.id;
-- Query to combine result set of tables teachers(id,first name, last name) and addresses(id, city, street, house) by id for first name 'Vladimir'.
select t.id as teacher_id, t.first_name as teacher_first_name, t.last_name as teacher_last_name, a.id as address_id, a.city, a.street, a.house
from teachers t inner join addresses a on t.addresses_id=a.id where first_name='Vladimir';
-- Query to combine result set of tables teachers(first name, last name) and chairs (name) by id.
select c.name as chair_name, t.first_name as teacher_first_name, t.last_name as teacher_last_name from chairs c inner join teachers t on t.chairs_id=c.id order by t.id desc;
-- Query to combine result set of tables students(first name, last name) and faculties (name) by id.
select f.name as faculty_name, s.first_name as student_first_name, s.last_name as student_last_name from students s inner join faculties f on s.faculties_id= f.id;
-- Query to combine result set of tables students(first name, last name) and faculties (name) by id for students with first name 'Bart'.
select f.name as faculty_name, s.first_name as student_first_name, s.last_name as student_last_name from students s inner join faculties f on s.faculties_id= f.id where s.first_name in('Bart');

-- Query to lef join result set of tables chairs(id,name) and teachers (id, first name, last name) by id, where chair can have null teachers.
select c.id as chair_id, c.name as chair_name, t.id as teacher_id, t.first_name as teacher_first_name, t.last_name as teacher_last_name
from chairs c left join teachers t on t.chairs_id =c.id;  
-- Query to left join result set of tables chairs(id,name) and teachers (id, first name, last name) by id for chairs id less or equel 3, where chair can have null teachers.
select c.id as chair_id, c.name as chair_name, t.id as teacher_id, t.first_name as teacher_first_name, t.last_name as teacher_last_name
from chairs c left join teachers t on t.chairs_id =c.id where c.id<=3;  
-- Query to left join result set of tables chairs(id,name) and faculty chairs (faculty id) by id.
select c.name as chair_name, fc.faculty_id as faculty_id from chairs c left join faculty_chairs fc on c.id =fc.chair_id;
-- Query to left join result set of tables faculties(name) and deans (last name) by id.
select f.name as faculty_name, d.last_name as dean_last_name from faculties f left join deans d on d.id=f.deans_id;
-- Query to left join result set of tables chairs(name), faculty_chairs (faculty_id) faculties (faculty name) and deans(last name)  by ids.
select c.name as chair_name, fc.faculty_id as faculty_id, f.name as faculty_name, d.last_name as dean_last_name
from chairs c left join faculty_chairs fc on c.id =fc.chair_id left join faculties f on f.id=fc.faculty_id left join deans d on d.id=f.deans_id;

-- Query shows faculties with more than 1 chair.
select f.name, count(fc.chair_id) as chairs_count from faculties f
 left join faculty_chairs fc on f.id= fc.faculty_id  group by f.name having chairs_count >1;

 -- Query shows chair quantity for each faculty.
 select f.name, count(c.name) from faculties f
 left join faculty_chairs fc on f.id = fc.faculty_id
 left join chairs c on c.id = fc.chair_id group by f.name;
 
   -- Query shows to how many faculties relates every chair.
 select c.name, count(f.name) from chairs c
 left join faculty_chairs fc on c.id = fc.chair_id
 left join faculties f on f.id = fc.faculty_id group by c.name;
 
  -- Query shows the sum of chair ids for each faculty.
 select f.name, sum(c.id) from faculties f
 left join faculty_chairs fc on f.id = fc.faculty_id
 left join chairs c on c.id = fc.chair_id group by f.name;
 
 -- Query shows which faculty has chair with name "Anatomy" and the number of chairs with such name (I know it's stupid).
  select f.name, count(c.id) from faculties f
 left join faculty_chairs fc on f.id = fc.faculty_id
 left join chairs c on c.id = fc.chair_id where c.name= 'Anatomy' group by f.name;
 
 -- Query shows the result set of first and last names of teachers and accountants.
 select first_name, last_name from teachers union select first_name, last_name from accountants;
 
 -- Query shows only 1 Demyan (even though he is a dean and a teacher at the same time.)
  select first_name, last_name from teachers union select first_name, last_name from deans;
 -- Query shows Demyan twice (as a dean and as a teacher.)
  select first_name, last_name from teachers union all select first_name, last_name from deans;
 
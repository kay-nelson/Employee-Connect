SET FOREIGN_KEY_CHECKS=0;


INSERT INTO job_titles (job_title_id,job_title)
VALUES 
(100,'software manager'),
(101,'software architect'),
(102,'software engineer'),
(103,'software developer'),
(200,'marketing manager'),
(201,'marketing associate'),
(202,'marketing assistant'),
(900,'Chief Exec. Officer'),
(901,'Chief Finn. Officer'),
(902,'Chief Info. Officer');

INSERT INTO employees (Fname, Lname, email, HireDate, Salary, SSN)
VALUES 
('Snoopy', 'Beagle', 	'Snoopy@example.com', 	'2022-08-01', 45000.00, '111222111'),
('Charlie', 'Brown', 	'Charlie@example.com', 	'2022-07-01', 48000.00, '222333222'),
('Lucy', 'Doctor', 		'Lucy@example.com', 	'2022-07-03', 55000.00, '333444333'),
('Pepermint', 'Patti', 	'Peppermint@example.com', '2022-08-02', 98000.00, '444555444'),
('Linus', 'Blanket', 	'Linus@example.com', 	'2022-09-01', 43000.00, '555666555'),
('PigPin', 'Dusty', 	'PigPin@example.com', 	'2022-10-01', 33000.00, '666777666'),
('Scooby', 'Doo', 		'Scooby@example.com', 	'1973-07-03', 78000.00,'777888777'),
('Shaggy', 'Rodgers', 	'Shaggy@example.com', 	'1973-07-11', 77000.00, '888999888'),
('Velma', 'Dinkley', 	'Velma@example.com', 	'1973-07-21', 82000.00, '999101099'),
('Daphne', 'Blake', 	'Daphne@example.com', 	'1973-07-30', 59000.00, '100111110'),
('Bugs', 'Bunny', 		'Bugs@example.com', 	'1934-07-01', 18000.00, '110121211'),
('Daffy', 'Duck', 		'Daffy@example.com', 	'1935-04-01', 16000.00, '120131312'),
('Porky', 'Pig', 		'Porky@example.com', 	'1935-08-12', 16550.00, '131414131'),
('Elmer', 'Fudd', 		'Elmer@example.com', 	'1934-08-01', 15500.00, '141515140'),
('Marvin', 'Martian', 	'Marvin@example.com', 	'1937-05-01', 28000.00, '151616150');

INSERT INTO payroll (payID, pay_date, empid, earnings, fed_tax, fed_med, fed_SS, state_tax, retire_401k, health_care)
SELECT 
  1,
  '2023-11-30', 
  empid, 
  salary/52.0, 
  (salary/52.0)*0.32, 
  (salary/52.0)*0.0145,
  (salary/52.0)*0.062,
  (salary/52.0)*0.12,
  (salary/52.0)*0.004,
  (salary/52.0)*0.031
FROM employees;

INSERT INTO payroll (payID, pay_date, empid, earnings, fed_tax, fed_med, fed_SS, state_tax, retire_401k, health_care)
SELECT 
  2,
  '2023-12-31', 
  empid, 
  salary/52.0, 
  (salary/52.0)*0.32, 
  (salary/52.0)*0.0145, 
  (salary/52.0)*0.062,
  (salary/52.0)*0.12,
  (salary/52.0)*0.004,
  (salary/52.0)*0.031 
FROM employees;

INSERT INTO employee_division (empid, div_ID)
VALUES(1,999),
      (2,999),
      (3,999),
      (7,1),
      (10,1);

INSERT INTO division (ID, Name, city, addressLine1, addressLine2, state, country, postalCode) 
VALUES(1,'Technology Engineering', 'Atlanta', '200 17th Street NW', '', 'GA', 'USA', '30363'),
    (2,'Marketing', 'Atlanta', '200 17th Street NW', '', 'GA', 'USA', '30363'),
    (3,'Human Resources','New York', '45 West 57th Street', '', 'NY', 'USA', '00034'),
    (999,'HQ','New York', '45 West 57th Street', '', 'NY', 'USA', '00034');

INSERT INTO employee_job_titles (empid, job_title_id)
VALUES(1,902),
(2,900),
(3,901),
(4,102),
(5,101),
(6,201),
(7,100),
(8,102),
(9,102),
(10,102),
(11,200),
(12,201),
(13,202),
(14,103),
(15, 103);


insert into city (city_ID, city)
values
(1, 'Atlanta'),
(2, 'New York City'),
(3, 'Miami');

insert into state(state_ID,state)
values
(1, 'Georgia'),
(2, 'New York'),
(3, 'Florida');

insert into address (empid , city_ID , state_ID , addressline1 ,addressline2 ,gender,pronouns, race, DOB, phonenumber)
values
(1, 1, 1, '1281 Peachtree Street', 'Apt 22', 'Male', 'They/Them', 'African-American', '1968-08-10', '111-111-1111'),
(2, 1, 1, '900 North Avenue', 'Apt 4B', 'Male', 'He/Him/His', 'White', '1950-10-30', '222-222-2222'),
(3, 2, 2, '433 32nd Street', 'Apt 1', 'Female', 'She/Her/Hers', 'Hispanic', '1952-03-03', '333-333-3333'),
(4, 3, 3, '101 Boulevard Avenue', 'Apt 29', 'Female', 'She/Her/Hers', 'Hispanic', '1954-10-20', '444-444-4444'),
(5, 2, 2, 'West 34th Street', 'Apt 327', 'Male', 'He/Him/His', 'Asian', '1964-11-22', '555-555-5555'),
(6, 1, 1, '572 Capitol Avenue', 'Apt 000', 'Male', 'He/Him/His', 'White', '1954-07-13', '666-666-6666'),
(7, 3, 3, 'East Coast Avenue', 'Apt B', 'Male', 'They/Them', 'African-American', '1984-01-12', '777-777-7777'),
(8, 1, 1, '4155 Spring Street', 'Apt 12', 'Male', 'He/Him/His', 'Asain', '1990-12-24', '888-888-8888'),
(9, 2, 2, '1200 11th Street', 'Apt 3001', 'Female', 'She/Her/Hers', 'Hispanic', '1988-09-19', '999-999-9999'),
(10, 2, 2, '468 33rd Street', 'Apt 67', 'Female', 'She/Her/Hers', 'White', '1987-05-15', '101-010-1010'),
(11, 1, 1, '975 Memorial Drive', 'Apt 99', 'Female', 'They/Them', 'African-American', '1999-04-28', '100-111-1100'),
(12, 2, 2, '500 West 10th Street', 'Apt 2D', 'Male', 'She/Her/Hers', 'Asain', '1997-02-20', '121-212-1212'),
(13, 3, 3, '700 North Miami Avenue', 'Apt F', 'Female', 'They/Them', 'Hispanic', '2000-12-02', '131-313-1313'),
(14, 3, 3, '202 SouthWest River Drive', 'Apt 1', 'Male', 'He/Him/His', 'African-American', '1995-06-19', '141-414-1414'),
(15, 1, 1, '1722 Piedmont Road', 'Apt 4699', 'Female', 'She/Her/Hers', 'Asain', '2001-06-04', '151-515-1515');

--Add this to fill in employee_division table in database
insert into employee_division (empid, div_ID)
values
(4,1),
(5,1),
(6,2),
(8,1),
(9,1),
(11,2),
(12,2),
(13,2),
(14,1),
(15,1);
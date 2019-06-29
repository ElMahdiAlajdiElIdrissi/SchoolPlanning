INSERT IGNORE INTO Department (Department_Name)
VALUES
("Informatica"),
("Business"),
("Economie");

INSERT IGNORE INTO Teacher (First_Name, Last_Name, Department_Id, User_Name, Password)
VALUES
("Jonathan", "Brites", 1, "Jonathan", "Brites"),
("Samuel", "L. Jackson", 2, "Samuel", "L. Jackson"),
("Samuel", "Tarly", 3, "Sam", "Tarly"),
("Lesley", "Forn", 1, "Lesley", "Forn");

INSERT IGNORE INTO Course (Course_Name, Start_Date, End_Date, Department_Id)
VALUES
("Java", "2018-09-24", "2019-06-12", 1),
(".NET", "2019-01-12", "2019-06-10", 1),
("PHP", "2018-09-24", "2019-06-12", 1),
("Marketing Strategie", "2018-10-17", "2019-03-15", 2),
("Accountancy Fiscaliteit", "2019-10-17", "2020-06-15", 2),
("Financiën en verzekeringswezen", "2019-09-17", "2020-04-15", 2),
("Geldstroom","2019-10-10","2020-01-28",3);

INSERT IGNORE INTO Student (First_Name , Last_Name, User_Name, Password)
VALUES
("Paul", "Gerarts", "paul", "paul"),
("Ruben", "Neven", "ruben", "ruben"),
("Mahdi", "Alajdi El Idrissi", "mahdi", "mahdi"),
("Mitch", "Munix", "mitch", "mitch"),
("Jonas", "Volders", "jonas", "jonas"),
("Yasin", "Keskin", "yasin", "yasin");

INSERT IGNORE INTO EnrolledCourses (Student_Id, Course_Id)
VALUES
(1,1),
(1,4),
(2,1),
(2,4),
(3,1),
(3,2),
(3,3),
(4,1),
(4,7),
(5,1),
(5,7),
(6,1),
(6,5);

INSERT IGNORE INTO AssignedCourses (Teacher_Id, Course_Id)
VALUES
(1,1),
(2,4),
(2,5),
(3,6),
(3,7),
(4,2),
(4,3);
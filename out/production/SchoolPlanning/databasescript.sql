CREATE TABLE IF NOT EXISTS `Department` (
    Id int(11) NOT NULL AUTO_INCREMENT,
    Department_Name varchar(255) NOT NULL UNIQUE DEFAULT "YTBA",
    Primary key (Id)
)ENGINE=InnoDB DEFAULT CHARSET='utf8';

CREATE TABLE IF NOT EXISTS `Teacher` (
    Id int(11) NOT NULL AUTO_INCREMENT,
    First_Name varchar(255) NOT NULL,
    Last_Name varchar(255) NOT NULL,
    Department_Id int(11) NOT NULL,
    User_Name varchar(255) NOT NULL UNIQUE,
    Password varchar(255) NOT NULL,
    Primary key (Id),
    Foreign key (Department_Id) references Department(Id)
)ENGINE=InnoDB DEFAULT CHARSET='utf8';

CREATE TABLE IF NOT EXISTS `Course` (
    Id int(11) NOT NULL AUTO_INCREMENT,
    Course_Name varchar(255) NOT NULL UNIQUE DEFAULT "YTBA",
    Start_Date datetime(6) NOT NULL,
    End_Date datetime(6) NOT NULL,
    Department_Id int(11) NOT NULL,
    Primary key (Id),
    Foreign key (Department_Id) references Department(Id)
)ENGINE=InnoDB DEFAULT CHARSET='utf8';

CREATE TABLE IF NOT EXISTS `Student` (
    Id int(11) AUTO_INCREMENT,
    First_Name varchar(255) NOT NULL,
    Last_Name varchar(255) NOT NULL,
    User_Name varchar(255) NOT NULL UNIQUE,
    Password varchar(255) NOT NULL,
    Primary key (Id)
)ENGINE=InnoDB DEFAULT CHARSET='utf8';

CREATE TABLE IF NOT EXISTS `EnrolledCourses` (
    Student_Id int(11) NOT NULL,
    Course_Id int(11) NOT NULL,
    Primary key (Course_Id, Student_Id),
    Foreign key (Course_Id) references Course(Id),
    Foreign key (Student_Id) references Student(Id)
)ENGINE=InnoDB DEFAULT CHARSET='utf8';

CREATE TABLE IF NOT EXISTS `AssignedCourses` (
    Teacher_Id int(11) NOT NULL,
    Course_Id int(11) NOT NULL,
    Primary key (Course_Id, Teacher_Id),
    Foreign key (Course_Id) references Course(Id),
    Foreign key (Teacher_Id) references Teacher(Id)
)ENGINE=InnoDB DEFAULT CHARSET='utf8';
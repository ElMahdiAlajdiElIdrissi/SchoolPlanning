CREATE TABLE IF NOT EXISTS `Departement` (
    Id int(11) NOT NULL,
    Departement_Naam varchar(255) NOT NULL DEFAULT "YTBA",
    Primary key (Id)
)ENGINE=InnoDB DEFAULT CHARSET='utf8';

CREATE TABLE IF NOT EXISTS `Docent` (
    Id int(11) NOT NULL,
    Voor_Naam varchar(255) NOT NULL,
    Achter_Naam varchar(255) NOT NULL,
    Departement_Id int(11) NOT NULL,
    Primary key (Id),
    Foreign key (Departement_Id) references Departement(Id)
)ENGINE=InnoDB DEFAULT CHARSET='utf8';

CREATE TABLE IF NOT EXISTS `Cursus` (
    Id int(11) NOT NULL,
    Naam_Cursus varchar(255) NOT NULL DEFAULT "YTBA",
    Start_Datum datetime(6) NOT NULL,
    Eind_Datum datetime(6) NOT NULL,
    Departement_Id int(11) NOT NULL,
    Primary key (Id),
    Foreign key (Departement_Id) references Departement(Id)
)ENGINE=InnoDB DEFAULT CHARSET='utf8';

CREATE TABLE IF NOT EXISTS `Student` (
    Id int(11),
    First_Name varchar(255) NOT NULL,
    Last_Name varchar(255) NOT NULL,
    Gebruikers_Naam varchar(255) NOT NULL,
    Passwoord varchar(255) NOT NULL,
    Primary key (Id)
)ENGINE=InnoDB DEFAULT CHARSET='utf8';

CREATE TABLE IF NOT EXISTS `IngeschrevenCursussen` (
    Cursus_Id int(11) NOT NULL,
    Student_Id int(11) NOT NULL,
    Primary key (Cursus_Id, Student_Id),
    Foreign key (Cursus_Id) references Cursus(Id),
    Foreign key (Student_Id) references Student(Id)
)ENGINE=InnoDB DEFAULT CHARSET='utf8';

CREATE TABLE IF NOT EXISTS `ToegewezenCursus` (
    Cursus_Id int(11) NOT NULL,
    Docent_Id int(11) NOT NULL,
    Primary key (Cursus_Id, Docent_Id),
    Foreign key (Cursus_Id) references Cursus(Id),
    Foreign key (Docent_Id) references Docent(Id)
)ENGINE=InnoDB DEFAULT CHARSET='utf8';
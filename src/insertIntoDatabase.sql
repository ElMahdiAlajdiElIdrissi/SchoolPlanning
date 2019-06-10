
INSERT IGNORE INTO Departement (Departement_Naam)
VALUES
("Informatica"),
("Business"),
("Economie");

INSERT IGNORE INTO Docent (Voor_Naam, Achter_Naam, Departement_Id, Password, Gebruikers_Naam)
VALUES
("Jonathan", "Brites", 1, "Brites", "Jonathan"),
("Samuel", "L. Jackson", 2, "L. Jackson", "Samuel"),
("Dick", "Johnson", 3, "Johnson", "Dick");

INSERT IGNORE INTO Cursus (Naam_Cursus, Start_Datum, Eind_Datum, Departement_Id)
VALUES
("Java", "2018-09-24", "2019-06-12", 1),
("MotherFucker", "2018-09-17", "2019-02-15", 2),
("Geldstroom","2018-10-10","2019-02-10",3);

INSERT IGNORE INTO Student (First_Name , Last_Name, Gebruikers_Naam, Passwoord)
VALUES
("Paul", "Gerarts", "paul", "gerarts"),
("Ruben", "Neven", "ruben", "ruben"),
("Mitch", "Munix", "mitch", "mitch");

INSERT IGNORE INTO IngeschrevenCursussen (Cursus_Id, Student_Id)
VALUES
(1,1),
(1,2),
(1,3),
(2,3),
(2,2),
(3,1);

INSERT IGNORE INTO ToegewezenCursus (Cursus_Id, Docent_Id)
VALUES
(1,1),
(2,2),
(3,3);
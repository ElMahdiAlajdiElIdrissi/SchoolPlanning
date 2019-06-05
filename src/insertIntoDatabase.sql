/*( SELECT 1 FROM Departement WHERE Id = 1 AND Departement_Naam = 'Informatica' )*/
INSERT IGNORE INTO Departement (Id, Departement_Naam)
VALUES
(1, "Informatica"),
(2, "Business"),
(3, "Economie");

/*IF NOT EXISTS ( SELECT 1 FROM Docent WHERE Id = 1 AND Voor_Naam = "Jonathan" )*/
INSERT IGNORE INTO Docent (Id, Voor_Naam, Achter_Naam, Departement_Id)
VALUES
(1, "Jonathan", "Brites", 1),
(2, "Samuel", "L. Jackson", 2),
(3, "Dick", "Johnson", 3);

/*IF NOT EXISTS ( SELECT 1 FROM Cursus WHERE Id = 1 AND Naam_Cursus = "Java" )*/
INSERT IGNORE INTO Cursus (Id, Naam_Cursus, Start_Datum, Eind_Datum, Departement_Id)
VALUES
(1, "Java", "2018-09-24", "2019-06-12", 1),
(2, "MotherFucker", "2018-09-17", "2019-02-15", 2),
(3, "Geldstroom","2018-10-10","2019-02-10",3);

/*IF NOT EXISTS ( SELECT 1 FROM Student WHERE Id = 1 AND First_Name = "Paul" )*/
INSERT IGNORE INTO Student (Id, First_Name , Last_Name, Gebruikers_Naam, Passwoord)
VALUES
(1, "Paul", "Gerarts", "paul", "gerarts"),
(2, "Ruben", "Neven", "ruben", "ruben"),
(3, "Mitch", "Munix", "mitch", "mitch");

/*IF NOT EXISTS ( SELECT 1 FROM IngeschrevenCursussen WHERE Cursus_Id = 1 AND Student_Id = 1 )*/
INSERT IGNORE INTO IngeschrevenCursussen (Cursus_Id, Student_Id)
VALUES
(1,1),
(1,2),
(1,3),
(2,3),
(2,2),
(3,1);

/*IF NOT EXISTS ( SELECT 1 FROM ToegewezenCursus WHERE Cursus_Id = 1 AND Docent_Id = 1 )*/
INSERT IGNORE INTO ToegewezenCursus (Cursus_Id, Docent_Id)
VALUES
(1,1),
(2,2),
(3,3);
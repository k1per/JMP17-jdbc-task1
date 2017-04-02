DROP TABLE IF EXISTS Persons;
CREATE TABLE Persons  (
    PersonID int,
    LastName varchar(255),
    FirstName varchar(255),
    Address varchar(255),
    City varchar(255)
);

INSERT INTO Persons VALUES (1, 'Lanister', 'Tyrion', 'Red Fort', 'Kingslanding');
INSERT INTO Persons VALUES (2, 'Lanister', 'Tywin', 'Casterly Rock', 'Lannisport');
INSERT INTO Persons VALUES (3, 'Lanister', 'Jaime', 'Red Fort', 'Kingslanding');
INSERT INTO Persons VALUES (4, 'Stark', 'Eddard', 'Palace', 'Winterfell');
INSERT INTO Persons VALUES (5, 'Snow', 'John', 'Castle black', 'The Wall');

DROP TABLE IF EXISTS Cities;
CREATE TABLE Cities (
  id int,
  name VARCHAR(255)
);

INSERT INTO Cities  VALUES (1, 'Kingslanding');
INSERT INTO Cities  VALUES (2, 'Winterfell');
INSERT INTO Cities  VALUES (3, 'Lannisport');
INSERT INTO Cities  VALUES (4, 'Sunspear');

DROP TABLE IF EXISTS Houses;
CREATE TABLE Houses (
  id int,
  name VARCHAR(255)
);

INSERT INTO Houses VALUES (1, 'Stark');
INSERT INTO Houses VALUES (2, 'Lannister');
INSERT INTO Houses VALUES (3, 'Greyjoy');
INSERT INTO Houses VALUES (4, 'Baratheon')


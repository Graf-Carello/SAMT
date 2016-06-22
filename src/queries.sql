-- SAMT SQL Queries
-- Insert users and their roles
INSERT INTO Users(userName, firstName, lastName, degreeCourse, email, password, profilePicture, version)
VALUES ('admin','Hans', 'Imma', 'IMA', 'hans@ima.at', '$2a$06$aahb9BD2dl2DNywfGHfYsenkGFRrz7KxmB3h8kM4Daz6xDsJD2sGO', 'hans.png', 1);

INSERT INTO user_roles (role, idUser)
VALUES ('ROLE_USER', '1');

INSERT INTO user_roles (role, idUser)
VALUES ('ROLE_ADMIN', '1');

INSERT INTO Users(userName, firstName, lastName, degreeCourse, email, password, profilePicture, version)
VALUES ('michael','Michael', 'Fuchs', 'IMA', 'michael@fuchs.at', '$2a$06$Qguj48kEhsLbcUclc940u.jVtv8JnRyI83WtymmtHrIH/i.d1dWs6', 'michael.png', 1);

INSERT INTO user_roles (role, idUser)
VALUES ('ROLE_USER', '2');

INSERT INTO Users(userName, firstName, lastName, degreeCourse, email, password, profilePicture, version)
VALUES ('paul','Paul', 'Koerner', 'IMA', 'paul@koerner.at', '$2a$06$D2ZRNg4dA3EX0ehGbMU89u58sWR9TAFzp9LXmGUHxm2BUm6PvUIS6', 'paul.png', 1);

INSERT INTO user_roles (role, idUser)
VALUES ('ROLE_USER', '3');

INSERT INTO Users(userName, firstName, lastName, degreeCourse, email, password, profilePicture, version)
VALUES ('felix','Felix', 'Graf', 'IMA', 'felix@graf.at', '$2a$06$MVAYELlfv3ghyEUF26J1sO/MAFOc7gk08PEQ4C.dJGIsUR7UkBsl6', 'felix.png', 1);

INSERT INTO user_roles (role, idUser)
VALUES ('ROLE_USER', '4');

INSERT INTO Users(userName, firstName, lastName, degreeCourse, email, password, profilePicture, version)
VALUES ('anna','Anna', 'Goerner', 'IMA', 'anna@goerner.at', '$2a$06$bMmBqEHn7aBrmbpM1CokwOkxTDjLZPSPKkubeLup84a7./9pGYfym', 'anna.png', 1);

INSERT INTO user_roles (role, idUser)
VALUES ('ROLE_USER', '5');


-- Insert Notes
INSERT INTO Notes(name, content, version, user_id)
VALUES ('Documents', 'bring student id', 1, 1);

INSERT INTO Notes(name, content, version, user_id)
VALUES ('SWENGA', 'exam on friday, learn Java', 1, 2);

INSERT INTO Notes(name, content, version, user_id)
VALUES ('HVSYS', 'write thesis', 1, 1);

INSERT INTO Notes(name, content, version, user_id)
VALUES ('GPM', 'final exam on monday', 1, 1);

INSERT INTO Notes(name, content, version, user_id)
VALUES ('FH', 'plan final celebration for semester finish', 1, 2);

INSERT INTO Notes(name, content, version, user_id)
VALUES ('Fuﬂball', 'match on friday', 1, 3);

INSERT INTO Notes(name, content, version, user_id)
VALUES ('Android', 'update to newest version', 1, 3);

INSERT INTO Notes(name, content, version, user_id)
VALUES ('Skateboarding', 'land a triple kickflip', 1, 4);

INSERT INTO Notes(name, content, version, user_id)
VALUES ('Smartphone', 'install CyanogenMod', 1, 4);

INSERT INTO Notes(name, content, version, user_id)
VALUES ('Smartphone', 'replace cracked screen', 1, 4);

INSERT INTO Notes(name, content, version, user_id)
VALUES ('Shopping', 'buy milk and bread', 1, 5);

INSERT INTO Notes(name, content, version, user_id)
VALUES ('Presentation', 'research market analysis methods', 1, 5);


-- Insert Forum
INSERT INTO Forum(title, user, content, version)
VALUES ('SWENGA', 1, 'Need help with current homework pls', 1);

INSERT INTO Forum(title, user, content, version)
VALUES ('SWENGA', 2, 'just look at the tutorial on moodle', 1);

INSERT INTO Forum(title, user, content, version)
VALUES ('SWENGA', 3, 'just press it a couple of times, and it will work just fine', 1);

INSERT INTO Forum(title, user, content, version)
VALUES ('HVSYS', 5, 'Need help with understanding AES encryption algorithm', 1);

INSERT INTO Forum(title, user, content, version)
VALUES ('HVSYS', 2, 'look in the presentation from last lecture', 1);

INSERT INTO Forum(title, user, content, version)
VALUES ('SWENGA', 4, 'how can i call a function in Java?', 1);

INSERT INTO Forum(title, user, content, version)
VALUES ('Skateboarding', 1, 'Anyone else here who likes skateboarding?', 1);

INSERT INTO Forum(title, user, content, version)
VALUES ('Skateboarding', 2, 'I love skatebaording as well', 1);

INSERT INTO Forum(title, user, content, version)
VALUES ('Skateboarding', 1, 'Oh thats cool, we need to go for a skate session on the weekend', 1);

INSERT INTO Forum(title, user, content, version)
VALUES ('SWENGA', 1, 'how to install Maven?', 1);

INSERT INTO Forum(title, user, content, version)
VALUES ('SWENGA', 2, 'just look on moodle, there are great instructions there', 1);

INSERT INTO Forum(title, user, content, version)
VALUES ('SWENGA', 5, 'ah thank you!', 1);

INSERT INTO Forum(title, user, content, version)
VALUES ('SWENGA', 4, 'Need help finding good bootstrap templates', 1);

INSERT INTO Forum(title, user, content, version)
VALUES ('SWENGA', 5, 'I can show you our template, its great', 1);

INSERT INTO Forum(title, user, content, version)
VALUES ('SWENGA', 4, 'the admin sb template is also great', 1);


-- Insert Homework
INSERT INTO Homework(deadline, description, version, course, user_id)
VALUES ('2016-06-25', 'write thesis', 1, 'BAC', 1);

INSERT INTO Homework(deadline, description, version, course, user_id)
VALUES ('2016-06-20', 'term paper', 1, 'QMMGT', 3);

INSERT INTO Homework(deadline, description, version, course, user_id)
VALUES ('2016-06-29', 'term paper', 1, 'HVSYS', 3);

INSERT INTO Homework(deadline, description, version, course, user_id)
VALUES ('2016-07-01', 'example 4', 1, 'GMPGT', 2);

INSERT INTO Homework(deadline, description, version, course, user_id)
VALUES ('2016-07-15', 'paper 6', 1, 'HVSYS', 2);

INSERT INTO Homework(deadline, description, version, course, user_id)
VALUES ('2016-07-26', 'marketing analysis', 1, 'GPMGT', 4);

INSERT INTO Homework(deadline, description, version, course, user_id)
VALUES ('2016-07-14', 'upload presentation on moodle', 1, 'TEAMT', 5);

INSERT INTO Homework(deadline, description, version, course, user_id)
VALUES ('2016-07-22', 'AD DS', 1, 'COISD', 5);

INSERT INTO Homework(deadline, description, version, course, user_id)
VALUES ('2016-07-15', 'unity game', 1, 'DMT3', 5);


-- Insert Projects
INSERT INTO Projects(projectName, deadline, pid, progress, course, user, version)
VALUES ('SAMT', '2016-06-20', 2, '40', 'SWENGA', '1', 1);

INSERT INTO Projects(projectName, deadline, pid, progress, course, user, version)
VALUES ('SAMT', '2016-06-20', 2, '40', 'SWENGA', '2', 1);

INSERT INTO Projects(projectName, deadline, pid, progress, course, user, version)
VALUES ('SAMT', '2016-06-20', 2, '40', 'SWENGA', '3', 1);

INSERT INTO Projects(projectName, deadline, pid, progress, course, user, version)
VALUES ('UNITY', '2016-07-20', 3, '65', 'DMT3', '4', 1);

INSERT INTO Projects(projectName, deadline, pid, progress, course, user, version)
VALUES ('UNITY', '2016-07-20', 3, '65', 'DMT3', '5', 1);

INSERT INTO Projects(projectName, deadline, pid, progress, course, user, version)
VALUES ('UNITY', '2016-07-20', 4, '35', 'HVSYS', '5', 1);

INSERT INTO Projects(projectName, deadline, pid, progress, course, user, version)
VALUES ('UNITY', '2016-07-20', 4, '35', 'HVSYS', '2', 1);


-- Insert Events
INSERT INTO Events(title, start, end, creator, version)
VALUES ('SAMT', '2016-06-20', '2016-06-20', 2, 0);

INSERT INTO Events(title, start, end, creator, version)
VALUES ('BAC', '2016-06-21', '2016-06-24', 3, 0);

INSERT INTO Events(title, start, end, creator, version)
VALUES ('HVSYS', '2016-06-22', '2016-06-24', 1, 0);

INSERT INTO Events(title, start, end, creator, version)
VALUES ('DMT3', '2016-06-28', '2016-06-29', 4, 0);

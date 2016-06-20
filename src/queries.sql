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
VALUES ('anna','Anna', 'G�rner', 'IMA', 'anna@g�rner.at', '$2a$06$bMmBqEHn7aBrmbpM1CokwOkxTDjLZPSPKkubeLup84a7./9pGYfym', 'anna.png', 1);

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

-- Insert Forum
INSERT INTO Forum(title, user, content, version)
VALUES ('SWENGA', 1, 'Need help with current homework pls', 1);

INSERT INTO Forum(title, user, content, version)
VALUES ('SWENGA', 2, 'just look at the tutorial on moodle', 1);

INSERT INTO Forum(title, user, content, version)
VALUES ('SWENGA', 3, 'just press it a couple of times, and it will work just fine', 1);

INSERT INTO Forum(title, user, content, version)
VALUES ('SWENGA', 1, 'Need help with current homework pls', 1);

INSERT INTO Forum(title, user, content, version)
VALUES ('SWENGA', 2, 'just look at the tutorial on moodle', 1);

INSERT INTO Forum(title, user, content, version)
VALUES ('SWENGA', 3, 'just press it a couple of times, and it will work just fine', 1);

INSERT INTO Forum(title, user, content, version)
VALUES ('SWENGA', 1, 'Need help with current homework pls', 1);

INSERT INTO Forum(title, user, content, version)
VALUES ('SWENGA', 2, 'just look at the tutorial on moodle', 1);

INSERT INTO Forum(title, user, content, version)
VALUES ('SWENGA', 3, 'just press it a couple of times, and it will work just fine', 1);

INSERT INTO Forum(title, user, content, version)
VALUES ('SWENGA', 1, 'Need help with current homework pls', 1);

INSERT INTO Forum(title, user, content, version)
VALUES ('SWENGA', 2, 'just look at the tutorial on moodle', 1);

INSERT INTO Forum(title, user, content, version)
VALUES ('SWENGA', 3, 'just press it a couple of times, and it will work just fine', 1);

INSERT INTO Forum(title, user, content, version)
VALUES ('SWENGA', 1, 'Need help with current homework pls', 1);

INSERT INTO Forum(title, user, content, version)
VALUES ('SWENGA', 2, 'just look at the tutorial on moodle', 1);

INSERT INTO Forum(title, user, content, version)
VALUES ('SWENGA', 3, 'just press it a couple of times, and it will work just fine', 1);


-- Insert Homework
INSERT INTO Homework(deadline, description, version, course, user_id)
VALUES ('2016-06-20', 'write thesis', 1, 'BAC', 1);

INSERT INTO Homework(deadline, description, version, course, user_id)
VALUES ('2016-06-01', 'term paper', 1, 'QMMGT', 3);

INSERT INTO Homework(deadline, description, version, course, user_id)
VALUES ('2016-06-13', 'term paper', 1, 'HVSYS', 3);

INSERT INTO Homework(deadline, description, version, course, user_id)
VALUES ('2016-07-01', 'example 4', 1, 'GMPGT', 2);


-- Insert Projects
INSERT INTO Projects(projectName, deadline, pid, progress, course, user, version)
VALUES ('SAMT', '2016-06-20', 2, '40', 'SWENGA', '1', 1);

INSERT INTO Projects(projectName, deadline, pid, progress, course, user, version)
VALUES ('SAMT', '2016-06-20', 2, '40', 'SWENGA', '2', 1);

INSERT INTO Projects(projectName, deadline, pid, progress, course, user, version)
VALUES ('SAMT', '2016-06-20', 2, '40', 'SWENGA', '3', 1);

-- Insert Events
INSERT INTO Events(name, startDate, endDate, user_id, version)
VALUES ('SAMT', '2016-06-20', '2016-06-20', 1, 0);

INSERT INTO Events(name, startDate, endDate, user_id, version)
VALUES ('BAC', '2016-06-21', null, 2, 0);

INSERT INTO Events(name, startDate, endDate, user_id, version)
VALUES ('HVSYS', '2016-06-22', '2016-06-24', 3, 0);
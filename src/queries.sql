-- Insert users and their roles
INSERT INTO Users(userName, firstName, lastName, degreeCourse, email, password, profilePicture, version)
VALUES ('admin','Felix', 'Graf', 'IMA', 'felix@graf.at', '$2a$06$MVAYELlfv3ghyEUF26J1sO/MAFOc7gk08PEQ4C.dJGIsUR7UkBsl6', 'felix.png', 1);

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
VALUES ('elsa','Elsa', 'Karimova', 'IMA', 'elsa@karimova.at', '$2a$06$H5LaaUqodExGOOiH/hO.JOFEZWdWsF.na9eRXbhoaovlP.Ex6Tmbi', 'elsa.png', 1);

INSERT INTO user_roles (role, idUser)
VALUES ('ROLE_USER', '4');


-- Insert Notes
INSERT INTO Notes(name, content, version, users_id)
VALUES ('Dokumente mitnehmen', 'Studentenausweis erneuern', 1, 1);

INSERT INTO Notes(name, content, version, users_id)
VALUES ('SWENGA', 'PR am Freitag', 1, 2);

INSERT INTO Notes(name, content, version, users_id)
VALUES ('HVSYS', 'Seminararbeit schreiben', 1, 1);

INSERT INTO Notes(name, content, version, users_id)
VALUES ('GPM', 'Abschlussübung', 1, 1);

INSERT INTO Notes(name, content, version, users_id)
VALUES ('FH', 'Abschlussfeier planen', 1, 2);


-- Insert Grades
INSERT INTO Grades(course, date, grade, version, users_id)
VALUES ('SWENGA', '2016-05-20', '1', 1, 1);

INSERT INTO Grades(course, date, grade, version, users_id)
VALUES ('SWENGA', '2016-04-20', '2', 1, 2);

INSERT INTO Grades(course, date, grade, version, users_id)
VALUES ('QM', '2016-03-10', '1', 1, 1);

INSERT INTO Grades(course, date, grade, version, users_id)
VALUES ('BWL', '2016-05-15', '3', 1, 4);

INSERT INTO Grades(course, date, grade, version, users_id)
VALUES ('Englisch', '2016-03-02', '2', 1, 1);


-- Insert Groups
INSERT INTO Groups(groupName, members, project, version)
VALUES ('SAMT', '1, 2, 3, 4', 'SWENGA Projekt', 1);

INSERT INTO Groups(groupName, members, project, version)
VALUES ('EWGUBI', '1, 2, 3', 'Gaming und Freizeitaktivitäten', 1);


-- Insert Forum
INSERT INTO Forum(forumName, user, isMain, post, thread, version)
VALUES ('SWENGA', 1, true, 'Need help with current homework pls', 1, 1);

INSERT INTO Forum(forumName, user, isMain, post, thread, version)
VALUES ('SWENGA', 2, false, 'just look at the tutorial on moodle', 1, 1);

INSERT INTO Forum(forumName, user, isMain, post, thread, version)
VALUES ('SWENGA', 3, false, 'just press it a couple of times, and it will work just fine', 1, 1);


-- Insert Homework
INSERT INTO Homework(deadline, description, version, users_id)
VALUES ('2016-06-20', 'BAC-1 Arbeit', 1, 1);

INSERT INTO Homework(deadline, description, version, users_id)
VALUES ('2016-06-01', 'QMMGT Seminararbeit', 1, 4);

INSERT INTO Homework(deadline, description, version, users_id)
VALUES ('2016-06-13', 'HVSYS Seminararbeit', 1, 3);

INSERT INTO Homework(deadline, description, version, users_id)
VALUES ('2016-07-01', 'Ferien', 1, 2);


-- Insert Projects
INSERT INTO Projects(projectName, deadline, progress, course, user, version)
VALUES ('SAMT', '2016-06-20', '40', 'SWENGA', '1', 1);

INSERT INTO Projects(projectName, deadline, progress, course, user, version)
VALUES ('BAC-1', '2016-07-11', '30', 'BAC-1', '1', 1);









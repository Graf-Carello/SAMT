INSERT INTO Users(userName, firstName, lastName, degreeCourse, email, password, profilePicture, version)
VALUES ('admin','felix', 'adminsta', 'IMA', 'felix@world.com', '$2a$06$MVAYELlfv3ghyEUF26J1sO/MAFOc7gk08PEQ4C.dJGIsUR7UkBsl6', 'felix.png', 1);

INSERT INTO user_roles (role, idUser)
VALUES ('ROLE_USER', '1');

INSERT INTO user_roles (role, idUser)
VALUES ('ROLE_ADMIN', '1');







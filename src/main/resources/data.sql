INSERT INTO TABLE_EVENTS (name, description, startDate, endDate, startTime, endTime, emailContact, amountFreeTickets, amountPayedTickets, priceTicket) VALUES ('Concert', 'Piano concert', '1999-05-05', '1999-05-05', '15:05:15', '16:05:15', 'Pentecostpianorecital@outlook.com', 1, 10, 100);
INSERT INTO TABLE_EVENTS (name, description, startDate, endDate, startTime, endTime, emailContact, amountFreeTickets, amountPayedTickets, priceTicket) VALUES ('Movie theater', 'Movie theater NY', '2000-05-05', '2000-05-05', '05:33:33', '06:33:33', 'Moviethater@email.com', 2, 20, 200);
INSERT INTO TABLE_EVENTS (name, description, startDate, endDate, startTime, endTime, emailContact, amountFreeTickets, amountPayedTickets, priceTicket) VALUES ('Halloween festival', 'Halloween festival LA', '2015-31-11', '2015-31-1', '22:00:00', '00:00:00', 'Halloweenfestival@gmail.com', 3, 30, 300);

                            -- Administrators --

INSERT INTO TABLE_USERS (id, name, email) VALUES (1, 'Fiódor', 'Fiódor@gmail.com');
INSERT INTO TABLE_ADMINS (user_id, phoneNumber) VALUES (1, '(033) 1515-1515');

INSERT INTO TABLE_USERS (id, name, email) VALUES (2, 'Lev', 'Lev@outlook.com');
INSERT INTO TABLE_ADMINS (user_id, phoneNumber) VALUES (2, '(044) 1616-1616');

INSERT INTO TABLE_USERS (id, name, email) VALUES (3, 'Anton', 'Anton@email.com');
INSERT INTO TABLE_ADMINS (user_id, phoneNumber) VALUES (3, '(092) 2020-2020');

                            -- Attendees --

INSERT INTO TABLE_USERS (id, name, email) VALUES (4, 'Thomas', 'Thomas@email.com');
INSERT INTO TABLE_ATTENDEES (user_id, balance) VALUES (4, 100);

INSERT INTO TABLE_USERS (id, name, email) VALUES (5, 'Jane', 'Jane@outlook.com');
INSERT INTO TABLE_ATTENDEES (user_id, balance) VALUES (5, 200);

INSERT INTO TABLE_USERS (id, name, email) VALUES (6, 'Tolkien', 'Tolkien@gmail.com');
INSERT INTO TABLE_ATTENDEES (user_id, balance) VALUES (6, 300);

                            -- Places --

INSERT INTO TABLE_PLACES (id, name, address) VALUES (1, 'Place 1', 'Street 001');
INSERT INTO TABLE_PLACES (id, name, address) VALUES (2, 'Place 2', 'Street 002');
INSERT INTO TABLE_PLACES (id, name, address) VALUES (3, 'Place 3', 'Street 003');
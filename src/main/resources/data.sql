INSERT INTO TABLE_EVENTS (name, description, startDate, endDate, startTime, endTime, emailContact, amountFreeTickets, amountPayedTickets, priceTicket) VALUES ('Concert',            'Piano concert',           '2001-01-01', '2001-02-02', '02:02:02', '03:03:03', 'Pentecostpianorecital@outlook.com',    1, 10, 100);
INSERT INTO TABLE_EVENTS (name, description, startDate, endDate, startTime, endTime, emailContact, amountFreeTickets, amountPayedTickets, priceTicket) VALUES ('Movie theater',      'Movie theater NY',        '2001-03-03', '2001-04-04', '04:04:04', '05:05:05', 'Moviethater@email.com',                2, 20, 200);
INSERT INTO TABLE_EVENTS (name, description, startDate, endDate, startTime, endTime, emailContact, amountFreeTickets, amountPayedTickets, priceTicket) VALUES ('Halloween festival', 'Halloween festival LA',   '2001-05-05', '2001-06-06', '06:06:06', '07:07:07', 'Halloweenfestival@gmail.com',          3, 30, 300);

                            -- Administrators --

INSERT INTO TABLE_USERS (id, name, email) VALUES (10000, 'Fiódor', 'Fiódor@gmail.com');
INSERT INTO TABLE_ADMINS (user_id, phoneNumber) VALUES (10000, '(033) 1515-1515');

INSERT INTO TABLE_USERS (id, name, email) VALUES (20000, 'Lev T', 'Lev@outlook.com');
INSERT INTO TABLE_ADMINS (user_id, phoneNumber) VALUES (20000, '(044) 1616-1616');

INSERT INTO TABLE_USERS (id, name, email) VALUES (30000, 'Anton', 'Anton@email.com');
INSERT INTO TABLE_ADMINS (user_id, phoneNumber) VALUES (30000, '(092) 2020-2020');

                            -- Attendees --

INSERT INTO TABLE_USERS (id, name, email) VALUES (40000, 'Thomas', 'Thomas@email.com');
INSERT INTO TABLE_ATTENDEES (user_id, balance) VALUES (40000, 10);

INSERT INTO TABLE_USERS (id, name, email) VALUES (50000, 'Jane', 'Jane@outlook.com');
INSERT INTO TABLE_ATTENDEES (user_id, balance) VALUES (50000, 20);

INSERT INTO TABLE_USERS (id, name, email) VALUES (60000, 'Tolkien', 'Tolkien@gmail.com');
INSERT INTO TABLE_ATTENDEES (user_id, balance) VALUES (60000, 30);

                            -- Places --

INSERT INTO TABLE_PLACES (id, name, address) VALUES (10000, 'Place 1', 'Street 001');
INSERT INTO TABLE_PLACES (id, name, address) VALUES (20000, 'Place 2', 'Street 002');
INSERT INTO TABLE_PLACES (id, name, address) VALUES (30000, 'Place 3', 'Street 003');
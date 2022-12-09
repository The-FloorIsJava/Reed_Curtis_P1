CREATE TABLE users(
                      user_id SERIAL PRIMARY KEY,
                      username varchar(255) UNIQUE,
                      user_role varchar(255) DEFAULT 'Employee',
                      user_password varchar(255)
);

CREATE TABLE tickets(
                        id SERIAL PRIMARY KEY,
                        employee_name varchar(255),
                        amount numeric check (amount > 0),
                        description varchar(255),
                        status varchar(255) default 'Pending',
                        FOREIGN KEY(employee_name)REFERENCES users(username)
);

SELECT * FROM tickets;
SELECT * FROM users;
SELECT * FROM users INNER JOIN tickets ON users.username = tickets.employee_name;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS tickets;
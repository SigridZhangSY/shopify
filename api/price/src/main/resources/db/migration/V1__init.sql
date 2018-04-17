CREATE TABLE s_users (
  id INT PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name varchar(255) NOT NULL
);

INSERT INTO s_users (first_name, last_name) values ('Sherlock', 'Holmes');
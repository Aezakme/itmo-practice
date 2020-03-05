CREATE TABLE Books
(
  id          SERIAL PRIMARY KEY,
  title       varchar(255) NOT NULL,
  author      varchar(255) NOT NULL,
  description text         NOT NULL,
  year        date         NOT NULL
);

CREATE TABLE Offices
(
  id      SERIAL PRIMARY KEY,
  name    varchar(255) NOT NULL,
  address varchar(255) NOT NULL
);

CREATE TABLE Availability
(
  id        SERIAL PRIMARY KEY,
  book_id   int references Books (id) ON DELETE CASCADE ON UPDATE CASCADE,
  office_id int references Offices (id) ON DELETE CASCADE ON UPDATE CASCADE,
  amount    int NOT NULL
)
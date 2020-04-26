create table books
(
    id          serial                 not null,
    title       character varying(255) not null,
    author      character varying(255) not null,
    description text,
    year        date,
    primary key (id)
);

create table offices
(
    id      serial                 not null,
    "name"  character varying(255) not null,
    address character varying(255) not null,
    primary key (id)
);

create table availability
(
    id        serial  not null,
    book_id   bigint  not null,
    office_id bigint  not null,
    amount    integer not null,
    primary key (id),
    foreign key (book_id) references books (id),
    foreign key (office_id) references offices (id)
);

insert into books (title, author)
values ('first_book', 'test author'),
       ('second_book', 'test author'),
       ('third_book', 'test author'),
       ('fourth_book', 'test author'),
       ('fifth_book', 'test author');

insert into offices (name, address)
values ('first_office', 'posadskaya'),
       ('second_office', 'levashovsky');

insert into availability (book_id, office_id, amount)
values (1, 1, 5),
       (1, 2, 10);
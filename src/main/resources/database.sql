create table books
(
    id          bigint                 not null,
    title       character varying(255) not null,
    author      character varying(255) not null,
    description text,
    year        date,
    primary key (id)
);

create table offices
(
    id      bigint                 not null,
    "name"    character varying(255) not null,
    address character varying(255) not null,
    primary key (id)
);

create table availability
(
    id        bigint  not null,
    book_id   bigint  not null,
    office_id bigint  not null,
    amount    integer not null,
    primary key (id),
    foreign key (book_id) references books (id),
    foreign key (office_id) references offices (id)
);

insert into books (id, title, author)
values (0, 'first_book', 'test author'),
       (1, 'second_book', 'test author'),
       (2, 'third_book', 'test author');
       (3, 'fourth_book', 'test author');

insert into offices (id, name, address)
values (0, 'first_office', 'posadskaya'),
       (1, 'second_office', 'levashovsky');

insert into availability (id, book_id, office_id, amount)
values (0, 0, 0, 5),
       (1, 0, 1, 10);
create table topic(
    id serial not null primary key,
    title varchar(255) not null,
    description varchar(255),
    additionalSource varchar(255),
    complete bit,
    creationDate date,
    completionDate date
);
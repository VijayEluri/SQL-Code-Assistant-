create table parent (
    id number,
    text varchar2(23)
);

create table child (
    parent_id number,
    text varchar2(23)
);


alter table child rename column <caret>;
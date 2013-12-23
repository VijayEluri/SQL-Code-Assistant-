create table parent (
    id      number,
    text1    varchar2(23)
);

create table child (
    parent_id       number,
    text            varchar2(23)
);

select *
from
    (select *
     from
         (
         select * from child
         )
    ) s2,
    (select s31.*, 129 as a129, 'hello' hello
     from
         (
         select text1 as txt from parent
         ) s31
    ) s3
where
    s3.<caret>

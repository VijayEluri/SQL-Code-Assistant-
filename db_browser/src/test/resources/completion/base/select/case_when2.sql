create table parent (
    id      number,
    text1   varchar2(23)
);

select
case when substr(<caret>) = 4 then 1 else 2 end,
    id, count (*) cnt from parent
order by 1
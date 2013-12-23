CREATE TABLE ext_tab (
empno CHAR(4),
ename CHAR(20),
job CHAR(20),
deptno CHAR(2))
ORGANIZATION EXTERNAL
(TYPE oracle_loader
DEFAULT DIRECTORY ext
ACCESS PARAMETERS
(
FIELDS TERMINATED BY ','
MISSING FIELD VALUES ARE NULL
(empno, ename, job, deptno))
LOCATION ('demo1.dat','demo2.dat'))
PARALLEL
REJECT LIMIT 0;
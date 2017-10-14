* CREATE DATABASE yideng;

### 一对一(一个学生对应一个号码)
   * CREATE TABLE stu(
   * 　　sid INT PRIMARY KEY AUTO_INCREMENT,
   * 　　sname VARCHAR(20)
   * );
   * CREATE TABLE tel(
   * 　　sid INT PRIMARY KEY AUTO_INCREMENT,
   * 　　tel INT
   * );
### 一对多(一个学生对应多个课程分数)
   * CREATE TABLE stu(
   * 　　sid INT PRIMARY KEY AUTO_INCREMENT,
   * 　　sname VARCHAR(20)
   * );
   * CREATE TABLE score(
   * 　　id VARCHAR(20), -- 课程名称
   * 　　score INT,
   * 　　sid INT -- 外键列的数据类型一定要与主键的类型一致
   	);
   * ALTER TABLE score ADD CONSTRAINT kf_stu_score FOREIGN KEY(sid) REFERENCES stu(sid);

### 多对多(一个学生对应多个老师，一个老师对应多个学生)
   * CREATE TABLE stu(
   * 　　sid INT PRIMARY KEY AUTO_INCREMENT,
   * 　　sname VARCHAR(20)
   * );
   * CREATE TABLE teacher(
   * 　　tid INT PRIMARY KEY,
   * 　　tname VARCHAR(20)
   * );
   * CREATE TABLE teacher_stu(
   * 　　sid INT,
   * 　　tid INT 
   * );
   * ALTER TABLE teacher_stu ADD CONSTRAINT fk_sid FOREIGN KEY(sid) REFERENCES stu(sid);
   * ALTER TABLE teacher_stu ADD CONSTRAINT fk_tid FOREIGN KEY(tid) REFERENCES teacher(tid);

* 创建表A和表B
   * CREATE TABLE A(
   * 　　aid INT,
   * 　　aname VARCHAR(20)
   * );
   * INSERT INTO A VALUES(1,'a'),(2,'b'),(3,'c'),(4,'d');
   * CREATE TABLE B(
   * 　　vid INT,
   * 　　bNAME VARCHAR(20)
   * );
   * INSERT INTO B VALUES(4,'d'),(5,'e'),(6,'f');
### 合并结果集
* UNION:去除重复记录
   * SELECT * FROM A UNION SELECT * FROM B;
* UNION ALL:不去除重复记录
   * SELECT * FROM A UNION ALL SELECT * FROM B;

* 创建员工表
   * CREATE TABLE emp(
   * 　　empno INT,	  -- 员工编号
   * 　　ename VARCHAR(50),-- 员工姓名
   * 　　mgr INT,	  -- 领导编号
   * 　　sal DECIMAL(7,2), -- 月薪
   * 　　comm DECIMAL(7,2),-- 奖金
   * 　　deptno INT  	  -- 部门编号
   * );
   * INSERT INTO emp VALUES(111,'YD0',110,800,NULL,20),
   * (222,'YD1',120,1600,300,40),
   * (333,'YD2',222,1250,500,30),
   * (444,'YD3',220,NULL,100,20),
   * (555,'YD4',170,1400,200,40),
   * (666,'YD5',222,NULL,300,30),
   * (777,'YD6',170,NULL,400,10),
   * (888,'YD7',333,1600,NULL,40),
   * (999,'YD8',NULL,5000,NULL,10),
   * (700,'YD9',190,1500,0,30);

* 创建部门表
   * CREATE TABLE dept(
   * 　　deptno		INT, 		-- 部分编码
   * 　　dname		VARCHAR(20),	-- 部分名称
   * 　　loc		VARCHAR(20)	-- 部分所在地点
   * );
   * INSERT INTO dept VALUES(10, '学工部', '新疆');
   * INSERT INTO dept VALUES(20, '销售部', '黄石');
   * INSERT INTO dept VALUES(30, '财务部', '湖师大');
   * INSERT INTO dept VALUES(40, '咨询部', '黄石港');
   * INSERT INTO dept VALUES(50, '教育部', '上海')
### 连接查询
   * SELECT * FROM dept,emp; -- 产生笛卡尔积，查询记录为emp的行数乘以dept的行数

* 使用主外键关系做为条件来去除无用信息
   * SELECT * FROM emp,dept WHERE emp.deptno=dept.deptno;

* 指定列查询
   * SELECT emp.ename,emp.sal,emp.comm,dept.dname FROM emp,dept WHERE emp.deptno=dept.deptno;

### 内连接查询,特点:查询结果必须满足条件

   * SELECT * FROM emp e INNER JOIN dept d ON e.deptno=d.deptno; 

### 外连接查询
* 左外连接: 先查询出左表（即以左表为主），然后查询右表，右表中满足条件的显示出来，不满足条件的显示NULL
   * SELECT * FROM  emp e LEFT OUTER JOIN dept d ON e.deptno=d.deptno;

* 右外连接: 右连接就是先把右表中所有记录都查询出来，然后左表满足条件的显示，不满足显示NULL
   * SELECT * FROM emp e RIGHT OUTER JOIN dept d ON e.deptno=d.deptno;

### 自然连接:两张连接的表中名称和类型完全一致的列作为条件，则以e.deptno=d.deptno作为条件
   * SELECT * FROM emp NATURAL JOIN dept;
   * SELECT * FROM emp NATURAL LEFT JOIN dept;
   * SELECT * FROM emp NATURAL RIGHT JOIN dept;

### 子查询

* 工资高于YD1的员工
   * SELECT * FROM emp WHERE sal > (SELECT sal FROM emp WHERE ename='YD1');

* 查询与YD3同一个部门的员工
   * SELECT * FROM emp WHERE deptno = (SELECT deptno FROM emp WHERE ename='YD3');

* 工资高于30号部门所有人的员工信息
   * SELECT * FROM emp WHERE sal > ALL (SELECT sal FROM emp WHERE deptno=30);

* 查询部门和工资与YD1完全相同的员工信息
   * SELECT * FROM emp WHERE(deptno,sal) IN (SELECT deptno,sal FROM emp WHERE ename='YD1');

* 有2个以上直接下属的员工信息
   * SELECT * FROM emp WHERE empno IN(SELECT mgr FROM emp GROUP BY mgr HAVING COUNT(mgr)>=2);

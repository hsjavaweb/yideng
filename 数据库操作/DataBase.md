* 创建数据库
   * CREATE DATABASE yideng;
* 使用指定数据库
   * USE yideng;
* 创建表格
   * CREATE TABLE Student(
   * id INT PRIMARY KEY, -- 学号
   * sname VARCHAR(20),	 -- 姓名
   * age INT,		 -- 年龄
   * sex VARCHAR(10)	 -- 性别
   * );
* 在上面学生表的基本上增加一个class列
   * ALTER TABLE Student ADD class VARCHAR(20);
* 删除clss列,一次只能删一列。
   * ALTER TABLE Student DROP class;
* 列名sname修改为name
   * ALTER TABLE Student CHANGE NAME sname varchar(20);

# 增加数据操作
* INSERT INTO Student(id,NAME,age,sex) VALUES('1','jack','10','男');
* INSERT INTO Student VALUES('2','rose','11','女');
* INSERT INTO Student VALUES('3','tom','22','男');
* INSERT INTO Student VALUES('4','jerry','100','男');
* INSERT INTO Student VALUES('5','yideng','11','男');
* INSERT INTO Student VALUES('6','gaohong','22','女');
* INSERT INTO Student VALUES('7','qunzhu','11','女');
* INSERT INTO Student VALUES('8','test','44','男');
* INSERT INTO Student(id,age) VALUES('9','33');
* INSERT INTO Student(id,NAME,age) VALUES('10','yideng','22');
* INSERT INTO Student(id,NAME) VALUES('11','haha');

# 修改数据操作
* 将名字为jerry的同学年龄改为25
   * UPDATE Student SET age='25' WHERE NAME='jerry';
* 将年龄为22岁的性别为女的同学名字改为nice
   * UPDATE Student SET NAME='nice' WHERE age='22' AND sex='女';
* 将学号为7的同学性别改为'男'
   * UPDATE Student SET sex='男' WHERE id='7';

# 删除数据操作
* 删除表中名为tom的记录
   * DELETE FROM Student WHERE NAME='tom';
* 删除表中所以的数据
   * DELETE FROM Student;
* 使用TRUNCATE删除表中记录
   * TRUNCATE TABLE Student;

* DELETE 删除表中的数据，表结构还在;删除后的数据可以找回
TRUNCATE 删除是把表直接DROP掉，然后再创建一个同样的新表。
删除的数据不能找回。执行速度比DELETE快

# 查询数据操作
### 1.基础查询
* 查询所有
   * SELECT * FROM Student;
* 查询指定列
   * SELECT id,NAME,sex FROM Student;
### 2.条件查询
* 查询学号为3，或者姓名为rose的记录
   * SELECT * FROM Student WHERE id=3 OR NAME='rose';
* 查询学号为1,2,3,4的记录
   * SELECT * FROM Student WHERE id IN (1,2,3,4);
* 查询学号不是1,2的记录
   * SELECT * FROM Student WHERE id NOT IN (1,2);
* 查询姓名为NULL的记录
   * SELECT * FROM Student WHERE NOT NAME IS NULL;
   * SELECT * FROM Student WHERE NAME IS NOT NULL;
* 查询年龄在10到20之间的学生记录
   * SELECT * FROM Student WHERE age>=10 AND age<=30
   * SELECT * FROM Student WHERE age BETWEEN 10 AND 20;
* 查询性别非男的学生记录
   * SELECT * FROM Student WHERE sex!='男';
   * SELECT * FROM Student WHERE sex<>'男';
   * SELECT * FROM Student WHERE NOT sex='男';
* 查询学号为1的同学的姓名 
   * SELECT NAME FROM Student WHERE id=1;

### 3.模糊查询
* 查询姓名由5个字母构成的学生记录
   * SELECT * FROM Student WHERE NAME LIKE '_____';
* 查询姓名由4个字母构成，并且第4个字母为“e”的学生记录
   * SELECT * FROM Student WHERE NAME LIKE '___e';
* 查询姓名以“t”开头的学生记录
   * SELECT * FROM Student WHERE NAME LIKE 't%';
* 查询姓名中第2个字母为“i”的学生记录
   * SELECT * FROM Student WHERE NAME LIKE '_i%';
* 查询姓名中包含“a”字母的学生记录
   * SELECT * FROM Student WHERE NAME LIKE '%a%';
   
### 4.字段控制查询
* 去除重复记录
   * SELECT DISTINCT age FROM Student;
* 给列表添加别名
   * SELECT NAME AS n  FROM Student;
   * SELECT NAME n FROM Student;（AS也可以省略）
* 查看学号与年龄之和
   * SELECT *,id+age total FROM Student;
   * SELECT *,id+IFNULL(age,0) total FROM Student;
   * 因为任何东西与NULL相加结果还是NULL，使用IFNULL函数可以把NULL转换为数值0
   
### 5.排序
* 查询所有学生记录，按年龄升序排序
   * SELECT * FROM Student ORDER BY age ASC;
   * SELECT * FROM Student ORDER BY age; -- 默认升序
* 查询所有学生记录，按年龄降序排序
   * SELECT * FROM Student ORDER BY age DESC;
* 查询年龄大于10小于30学生记录，按年龄降序排序
   * yideng WHERE age>=10 AND age<=30 ORDER BY age;
* 查询所有学生按年龄降序排序，如果年龄相同按学号升序排序
   * SELECT * FROM Student ORDER BY age DESC,id ASC;
   
### 6.函数聚合 
* 用来做纵向运算的函数
* 查询有一共多少人，用COUNT()函数
   * SELECT COUNT(*) c FROM Student;
* 查询表中有性别的人数
   * SELECT COUNT(sex) s FROM Student;
* 查询表中年龄大于20的人数
   * SELECT COUNT(*) c FROM Student WHERE age > 20;
* 查询所有学生的年龄和，用SUM()函数
   * SELECT SUM(age) FROM Student;
* 查询所有学号+年龄和
   * SELECT SUM(id+IFNULL(age,0)) total FROM Student;
* 查询所有学生的平均年龄，用AVG()函数
   * SELECT AVG(age) FROM Student;
* 查询学生的最高年龄和最低年龄，用MAX(),MIN()函数
   * SELECT MAX(age),MIN(age) FROM Student;
### 7.LIMIT
* LIMIT用来限定查询结果的起始行，以及总行数
* 查询5行记录，起始行从0开始
   * SELECT * FROM Student LIMIT 0,5;

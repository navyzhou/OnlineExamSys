/*
管理员：
  管理注册的教师
  数据备份和还原
  课程的管理
  班级信息管理
  学生信息管理
教员
  题库维护
  在线出卷
  自动评阅
学生：
  在线考试
  查看考试成绩和试卷分析
*/
--角色信息表
create table roleInfo(
   rid number(5) primary key,
   rname varchar2(100) not null unique,  --角色名称
   status number(2)
);
insert into roleInfo values(1, '管理员', 1);
insert into roleInfo values(2, '教师', 1);
insert into roleInfo values(3, '学生', 1);

--管理员信息表
create table adminInfos(
   aid number(5) primary key,
   rid number(5) --所属角色
       constraint FK_adminInfo_rid references roleInfo(rid),
   aname varchar2(100) not null, --管理员姓名
   pwd varchar2(200) not null, --管理员密码
   email varchar2(100) not null unique, --管理员邮箱
   photo varchar2(200), --管理员图像
   status number(2)  
);
create sequence seq_adminInfos_aid start with 1001;


--课程信息表
create table courseInfo(
   cid number(5) primary key,  --课程编号
   cname varchar2(100) not null unique,  --课程名称
   semester number(1), --开设的学期 1,2,3,4,5,6,7,8
   status number(2)   
);
create sequence seq_courseInfo_cid start with 1;

--专业信息表
create table majorInfo(
    mid number(5) primary key,
    mname varchar2(100) not null unique,
    status number(2)
);
create sequence seq_majorInfo_mid start with 1;


--班级信息
create table classInfo(
   cid number(5) primary key,
   cname varchar2(100) not null,  --班级名称
   mid number(5)  --所属专业
       constraint Fk_classInfo_mid references majorInfo(mid),
   grade number(6), --年级
   length number(2), --学制
   status number(2)
);
create sequence seq_classInfo_cid start with 1;

--学生信息表
create table stuInfo(
   sid varchar2(20) primary key,   --学号
   sname varchar2(100) not null,  --姓名
   pwd varchar2(200) not null, --密码
   cid number(5) --所在班级
       constraint FK_stuInfo_sid references classInfo(cid),
   sex varchar2(4), --性别
   photo varchar2(200), --图像
   cardID varchar2(40), --身份证号码
   tel varchar2(20), --联系方式
   status number(2)
);

insert into stuInfo values(?,?,?,?,?,?,?,?,1)

--题型
create table questionTypes(
   tid number(5) primary key,
   tname varchar2(100) not null, --题目类型名称  单选、多选、判断、填空
   status number(2)
);
insert into questionTypes values(1,'单选',1);
insert into questionTypes values(2,'多选',1);
insert into questionTypes values(3,'判断',1);
insert into questionTypes values(4,'填空',1);

--题目表
create table questions(
   qid varchar2(100) primary key,
   qname varchar2(2000) not null unique, --题目名称
   tid number(5) --题目类型
       constraint FK_questions_tid references questionTypes(tid),
   cid number(5) --所属课程
       constraint FK_questions_cid references courseInfo(cid),
   ans1 varchar2(500), --选项A
   ans2 varchar2(500), --选项B
   ans3 varchar2(500), --选项C
   ans4 varchar2(500), --选项D
   ans varchar2(4), --正确答案
   status number(2)
);
create sequence seq_questions_qid start with 1;

create or replace trigger tri_questions_qid
before insert on questions
for each row  --每影响一行触发一次
begin
    select 'Q_'||seq_questions_qid.nextval into :new.qid from dual;
end;

--试卷信息表
create table testPaper(
   pid number(10) primary key,
   pname varchar2(200) not null, --试卷名称
   cid number(5)  --课程编号
       constraint FK_testPaper_cid references courseInfo(cid),
   testTime date, --开考试卷
   longExam number(4), --考试时长  分钟
   cids varchar2(100), --考试班级编号  1,2,6,7
   subjects varchar2(4000), --题目信息 S_1-A-1,S_100-ABD-2,F_2-select-4,F_20-1-3 
   score varchar2(100), --每种题型的分数 1-2;2-4;3-1;4-2  [0]=0 [1]=2  [2]=4 [3]=1  [4]=2
   status number(2) --未考、开考、考试中、已阅
);
create sequence seq_testPaper_pid start with 1000001;

--答卷信息表
create table answerSheet(
   aid number(10) primary key,
   pid number(10)  --试卷编号
       constraint FK_answerSheet_aid references testPaper(pid),
   sid varchar2(20) --学生学号
       constraint FK_answerSheet_sid references stuInfo(sid),
   ans varchar2(4000), --答案 S_1-B-1,S_100-ABD-2,F_2-select-4,F_20-0-3
   score number(5,1),
   surplus number(4), --剩余时长
   status number(2)
);
create sequence seq_testPaper_aid start with 1;

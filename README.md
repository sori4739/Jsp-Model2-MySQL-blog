#### 나만의 블로그 만들기 JSP+Model2+MySQL

- git주소 : <https://github.com/sori4739/Jsp-Model2-MySQL-blog.git>


#### 1. 사용자 생성 및 권한 주기 및 DB 생성
- create user 'cos'@'localhost' identified by 'bitc5600';
- GRANT ALL PRIVILEGES ON *.* TO cos@localhost;
- create database cos;
- use cos;

#### 2. 테이블
```sql
CREATE TABLE user(
   id int auto_increment primary key,
    username varchar(100) not null unique,
    password varchar(100) not null,
    email varchar(100) not null,
    address varchar(100) not null,
    emailCheck int default 0,
    userProfile varchar(200) default '/blog/img/userProfile.png',
    createDate timestamp
) engine=InnoDB default charset=utf8;
```

```sql
CREATE TABLE board(
    id int auto_increment primary key,
    userId int,
    title varchar(100) not null,
    content longtext,
    readCount int default 0,
    createDate timestamp,
    foreign key (userId) references user (id) 
) engine=InnoDB default charset=utf8;
```

```sql
CREATE TABLE comment(
   id int auto_increment primary key,
    userId int,
    boardId int,
    content varchar(300) not null,
    createDate timestamp,
    foreign key (userId) references user (id) on delete set null,
    foreign key (boardId) references board (id) on delete cascade
) engine=InnoDB default charset=utf8;
```

```sql
CREATE TABLE reply(
   id int auto_increment primary key,
    commentId int,
    userId int,
    content varchar(300) not null,
    createDate timestamp,
    foreign key (commentId) references comment (id) on delete cascade,
    foreign key (userId) references user (id) on delete set null
) engine=InnoDB default charset=utf8;
```

#### 3. Factory 세팅하기
<https://blog.naver.com/sori4739/221681422861>

#### 4. 부트스트랩 커스터마이징 HTML파일
WebContent/Blog_CustomPage/**

#### 5. 실행 영상
<iframe width="640" height="360" src="https://www.youtube.com/watch?v=yIQv-1JEwtI&t=58s" frameborder="0" gesture="media" allowfullscreen=""></iframe>
-youtube주소 : <https://www.youtube.com/watch?v=yIQv-1JEwtI&t=58s>
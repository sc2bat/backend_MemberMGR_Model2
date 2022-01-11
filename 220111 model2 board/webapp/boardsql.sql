SELECT * FROM member;

CREATE TABLE board(
	num NUMBER(5) PRIMARY KEY,
	pass VARCHAR2(30), -- 게시물의 수정 삭제를 위한 비밀번호
	userid VARCHAR2(30),
	email VARCHAR2(30),
	title VARCHAR2(50),
	content VARCHAR2(1000),
	readcount NUMBER(4) DEFAULT 0, -- 조회수
	writedate DATE DEFAULT sysdate -- 작성일자
);

CREATE sequence board_seq START with 1 INCREMENT BY 1;

SELECT * FROM board;

INSERT INTO board(num, pass, userid, email, title, content) 
VALUES(board_seq.nextVal, 'heejin', 'abc1@naver.com', '1234', '첫방문', '출생일: 1996년 12월 9일(25세)');
INSERT INTO board(num, pass, userid, email, title, content) 
VALUES(board_seq.nextVal, 'hyunjin', 'abc2@naver.com', '1234', '게시판개설', '출생일: 1997년 5월 24일(24세)');
INSERT INTO board(num, pass, userid, email, title, content) 
VALUES(board_seq.nextVal, 'hasle', 'abc3@naver.com', '1234', '가나다라', '1997년 6월 13일(24세)');
INSERT INTO board(num, pass, userid, email, title, content) 
VALUES(board_seq.nextVal, 'youjin', 'abc4@naver.com', '1234', '출생지', '1997년 8월 18일(24세)');
INSERT INTO board(num, pass, userid, email, title, content) 
VALUES(board_seq.nextVal, 'vivi', 'abc5@naver.com', '1234', '미니 음반', '이달의 소녀 오드아이써클');
INSERT INTO board(num, pass, userid, email, title, content) 
VALUES(board_seq.nextVal, 'kimrip', 'abc6@naver.com', '1234', '김지우', '1999년 2월 10일(22세)');
INSERT INTO board(num, pass, userid, email, title, content) 
VALUES(board_seq.nextVal, 'jinsol', 'abc7@naver.com', '1234', '전희진', '1999년 10월 20일 (21세)');
INSERT INTO board(num, pass, userid, email, title, content) 
VALUES(board_seq.nextVal, 'choilee', 'abc8@naver.com', '1234', '김현진', '2000년 11월 19일(21세)');
INSERT INTO board(num, pass, userid, email, title, content) 
VALUES(board_seq.nextVal, 'chu', 'abc9@naver.com', '1234', '최예림', '이달의 소녀 yyxy');
INSERT INTO board(num, pass, userid, email, title, content) 
VALUES(board_seq.nextVal, 'gowon', 'abc10@naver.com', '1234', '임여진', '2001년 6월 4일 (20세)');

UPDATE board SET content='이달의 소녀 yyxy' WHERE num=9;

SELECT * FROM board;

SELECT * FROM member;

SELECT * FROM reply;

CREATE TABLE reply(
	replynum NUMBER(7) PRIMARY KEY, -- 댓글 순번 
	boardnum NUMBER(7), -- 댓글의 해당 게시물 번호
	userid VARCHAR2(20), -- 댓글쓴이 
	writedate DATE DEFAULT sysdate, -- 작성일 
	content VARCHAR2(1000) -- 작성 내용
);


SELECT * FROM reply;

CREATE sequence reply_seq START WITH 1 INCREMENT BY 1;

ALTER TABLE reply RENAME COLUMN reply_num to replynum;

SELECT * FROM board ORDER BY num DESC;
SELECT * FROM member ORDER BY admin DESC;
SELECT * FROM reply ORDER BY replynum DESC;

DELETE FROM reply WHERE boardnum = 0;

INSERT INTO board(num, pass, userid, email, title, content)
	VALUES(board_seq.nextVal, '1234', 'song', 'song@naver.com', '바람', '블랙베리, 눈물의 이별식');
INSERT INTO board(num, pass, userid, email, title, content)
	VALUES(board_seq.nextVal, '1234', 'song', 'song@naver.com', '바람', '블랙베리, 눈물의 이별식');
INSERT INTO board(num, pass, userid, email, title, content)
	VALUES(board_seq.nextVal, '1234', 'song', 'song@naver.com', '바람', '블랙베리, 눈물의 이별식');
INSERT INTO board(num, pass, userid, email, title, content)
	VALUES(board_seq.nextVal, '1234', 'song', 'song@naver.com', '바람', '블랙베리, 눈물의 이별식');
INSERT INTO board(num, pass, userid, email, title, content)
	VALUES(board_seq.nextVal, '1234', 'song', 'song@naver.com', '바람', '블랙베리, 눈물의 이별식');
INSERT INTO board(num, pass, userid, email, title, content)
	VALUES(board_seq.nextVal, '1234', 'song', 'song@naver.com', '바람', '블랙베리, 눈물의 이별식');
INSERT INTO board(num, pass, userid, email, title, content)
	VALUES(board_seq.nextVal, '1234', 'song', 'song@naver.com', '바람', '블랙베리, 눈물의 이별식');
INSERT INTO board(num, pass, userid, email, title, content)
	VALUES(board_seq.nextVal, '1234', 'song', 'song@naver.com', '바람', '블랙베리, 눈물의 이별식');
INSERT INTO board(num, pass, userid, email, title, content)
	VALUES(board_seq.nextVal, '1234', 'song', 'song@naver.com', '바람', '블랙베리, 눈물의 이별식');
INSERT INTO board(num, pass, userid, email, title, content)
	VALUES(board_seq.nextVal, '1234', 'song', 'song@naver.com', '바람', '블랙베리, 눈물의 이별식');
INSERT INTO board(num, pass, userid, email, title, content)
	VALUES(board_seq.nextVal, '1234', 'song', 'song@naver.com', '바람', '블랙베리, 눈물의 이별식');



























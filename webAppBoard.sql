DROP DATABASE webAppBoard;
CREATE DATABASE webAppBoard CHARACTER SET utf8;
#CREATE DATABASE IF NOT EXISTS webAppBoard CHARACTER SET utf8;

USE webAppBoard;

CREATE TABLE users
(
    u_id           VARCHAR(255) PRIMARY KEY COMMENT '사용자 아이디',
    pw             VARCHAR(255) NOT NULL COMMENT '비밀번호',
    name           VARCHAR(255) NOT NULL COMMENT '이름',
    phone          VARCHAR(20) UNIQUE NOT NULL COMMENT '전화번호',
    img_path       VARCHAR(255) COMMENT '프로필 이미지 경로',
    email          VARCHAR(255) UNIQUE NOT NULL COMMENT '이메일',
    post_time      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '가입 시간',
    birth          DATE NOT NULL COMMENT '생년월일',
    gender         ENUM ('FEMALE','MALE','NONE') COMMENT '성별',
    address        VARCHAR(255) COMMENT '주소',
    detail_address VARCHAR(255) COMMENT '상세주소',
    permission     ENUM ('ADMIN','USER','SILVER','GOLD','PRIVATE') NOT NULL DEFAULT 'USER' COMMENT '권한'
);

CREATE TABLE boards
(
    b_id        INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '게시글 아이디',
    u_id        VARCHAR(255) NOT NULL COMMENT '작성자 아이디',
    post_time   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성 시간',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최종 수정 시간',
    status      ENUM ('PUBLIC','PRIVATE','REPORT','BLOCK') DEFAULT 'PUBLIC' COMMENT '상태',
    title       VARCHAR(255) COMMENT '제목',
    content     TEXT NOT NULL COMMENT '내용',
    view_count  INT UNSIGNED DEFAULT 0 COMMENT '조회수',
    FOREIGN KEY (u_id) REFERENCES users (u_id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE board_replies
(
    br_id        INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '게시글 댓글 아이디',
    b_id         INT UNSIGNED NOT NULL COMMENT '게시글 아이디',
    u_id         VARCHAR(255) NOT NULL COMMENT '작성자 아이디',
    parent_br_id INT UNSIGNED COMMENT '부모 댓글 아이디(대댓글)',
    post_time    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성 시간',
    update_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최종 수정 시간',
    status       ENUM ('PUBLIC','PRIVATE','REPORT','BLOCK') DEFAULT 'PUBLIC' COMMENT '상태',
    img_path     VARCHAR(255) COMMENT '이미지 경로',
    content      MEDIUMTEXT NOT NULL COMMENT '내용',
    FOREIGN KEY (b_id) REFERENCES boards (b_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (u_id) REFERENCES users (u_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (parent_br_id) REFERENCES board_replies (br_id) ON DELETE CASCADE ON UPDATE CASCADE
);
#board_likes UNIQUE (u_id, bl_id) : 유저가 한게시물에 좋아요를 1번만 할 수 있도록
#PK : unique,not null,참조의 가능, index(목차)에 등록되어 해당 필드로 검색하면 빠르다(색인검색:수로 된 데이터가 색인 검색에 더 유리).
#후보키 : 대표키를 대신할 수 있는 필드
#[UNIQUE | PRIMARY KEY](u_id, b_id) : 'userCkm',1=> 'userCkm_1'
#게시글에 좋아요하는 테이블
CREATE TABLE board_likes
(
    bl_id  INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '게시글 좋아요 아이디',
    b_id   INT UNSIGNED NOT NULL COMMENT '게시글 아이디',
    u_id   VARCHAR(255) NOT NULL COMMENT '좋아요 누른 사용자 아이디',
    status ENUM ('LIKE','BAD','SAD','BEST','ANGRY','TIRED') NOT NULL COMMENT '좋아요 상태',
    UNIQUE (u_id, b_id),
    FOREIGN KEY (b_id) REFERENCES boards (b_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (u_id) REFERENCES users(u_id) ON DELETE CASCADE ON UPDATE CASCADE
);
#댓글에 좋아요하는 테이블
# FROM boards NATUAL JOIN users
# FROM boards INNER JOIN users USING (u_id)
# FROM boards INNER JOIN users ON boards.u_id=users.id
CREATE TABLE reply_likes
(
    rl_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '댓글 좋아요 아이디',
    br_id INT UNSIGNED NOT NULL COMMENT '댓글 아이디',
    u_id VARCHAR(255) NOT NULL COMMENT '좋아요 누른 사용자 아이디',
    status ENUM ('LIKE','BAD','SAD','BEST','ANGRY','TIRED') NOT NULL COMMENT '좋아요 상태',
    UNIQUE (u_id, br_id),
    FOREIGN KEY (br_id) REFERENCES board_replies (br_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (u_id) REFERENCES users (u_id) ON DELETE CASCADE ON UPDATE CASCADE
);
#게시글에 복수로 업로드 되는 이미지의 경로(스트리밍서버의 주소 or 해당 웹앱 서버에 업로드한 상대주소)
#board_imgs.img_path : "board_20230302_104644_046_8776.jpeg" (0)
#board_imgs.img_path : "12.jpeg" 파일의 고유 이름
#board_imgs.img_path : "참새2.jpeg"
#board_imgs.img_path : "참새3참새3참새3참새@#ㄴㅇ_ㅇㅋㄹㄴ3참새3참새3참새3참새3참새3참새3참새3참새3참새3참새3참새3참새3참새3참새3참새3참새3참새3참새3참새3참새3참새3.jpeg"
CREATE TABLE board_imgs
(
    bi_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '게시글 이미지 아이디',
    b_id INT UNSIGNED NOT NULL COMMENT '게시글 아이디',
    img_path VARCHAR(255) NOT NULL COMMENT '이미지 경로',
    FOREIGN KEY (b_id) REFERENCES boards (b_id) ON DELETE CASCADE ON UPDATE CASCADE
);
#pk : table 의 row 을 대표하는 키
#fk : 다른 table pk => 이때 부모 자식 관계가 된다. (관계형 데이터 베이스)
#fk 로는 pk 만 지정할 수 있고 존재하지 않는 부모를 참조할 수 없도록 제약된다.(참조의 무결성 적용)
#해시태그로 검색기능 구현
CREATE TABLE hash_tags
(
    h_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '해시태그 아이디',
    b_id INT UNSIGNED COMMENT '게시글 아이디',
    br_id INT UNSIGNED COMMENT '댓글 아이디',
    tag VARCHAR(255) NOT NULL COMMENT '태그 내용',
    FOREIGN KEY (b_id) REFERENCES boards (b_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (br_id) REFERENCES board_replies (br_id) ON DELETE CASCADE ON UPDATE CASCADE
);



-- users 테이블에 더미 데이터 생성
INSERT INTO users(u_id, pw, name, phone, email, birth, gender, address, detail_address, permission)
VALUES
    ('user01', '1234', '김철수', '01012345678', 'user01@gmail.com', '1990-01-01', 'MALE', '서울특별시', '강남구', 'USER'),
    ('user02', '1234', '이영희', '01087654321', 'user02@gmail.com', '1992-03-02', 'FEMALE', '서울특별시', '관악구', 'USER'),
    ('user03', '1234', '박민수', '01011112222', 'user03@gmail.com', '1995-07-03', 'MALE', '경기도', '성남시', 'SILVER'),
    ('user04', '1234', '홍길동', '01033334444', 'user04@gmail.com', '1997-11-04', 'MALE', '서울특별시', '중구', 'GOLD'),
    ('user05', '1234', '임성호', '01055556666', 'user05@gmail.com', '2000-05-05', 'MALE', '서울특별시', '송파구', 'PRIVATE'),
    ('user06', '1234', '이미라', '01077778888', 'user06@gmail.com', '2002-09-06', 'FEMALE', '서울특별시', '용산구', 'USER'),
    ('user07', '1234', '김미나', '01099990000', 'user07@gmail.com', '1988-11-07', 'FEMALE', '서울특별시', '마포구', 'USER'),
    ('user08', '1234', '장기용', '01022223333', 'user08@gmail.com', '1985-06-08', 'MALE', '서울특별시', '강서구', 'USER'),
    ('user09', '1234', '송민주', '01044445555', 'user09@gmail.com', '1979-09-09', 'FEMALE', '서울특별시', '서초구', 'SILVER'),
    ('user10', '1234', '이준호', '01066667777', 'user10@gmail.com', '1998-01-10', 'MALE', '서울특별시', '성동구', 'GOLD'),
    ('user11', '1234', '김주원', '01088889999', 'user11@gmail.com', '2003-04-11', 'MALE', '서울특별시', '강동구', 'PRIVATE'),
    ('user12', '1234', '최다인', '01012121212', 'user12@gmail.com', '1994-12-12', 'FEMALE', '서울특별시', '노원구', 'USER'),
    ('user13', '1234', '이현우', '01013131313', 'user13@gmail.com', '1991-03-13', 'MALE', '경기도', '고양시', 'USER'),
    ('user14', '1234', '서영석', '01014141414', 'user14@gmail.com', '1996-08-14', 'MALE', '경기도', '용인시', 'USER'),
    ('user15', '1234', '임현아', '01015151515', 'user15@gmail.com', '1998-11-15', 'FEMALE', '서울특별시', '강남구', 'SILVER'),
    ('user16', '1234', '김민수', '01016161616', 'user16@gmail.com', '1985-02-16', 'MALE', '서울특별시', '관악구', 'GOLD'),
    ('user17', '1234', '김유진', '01017171717', 'user17@gmail.com', '1994-05-17', 'FEMALE', '서울특별시', '강서구', 'USER'),
    ('user18', '1234', '박세진', '01018181818', 'user18@gmail.com', '2000-07-18', 'MALE', '서울특별시', '중구', 'USER'),
    ('user19', '1234', '장미희', '01019191919', 'user19@gmail.com', '1989-09-19', 'FEMALE', '서울특별시', '송파구', 'PRIVATE'),
    ('user20', '1234', '이선규', '01020202020', 'user20@gmail.com', '1992-11-20', 'MALE', '서울특별시', '용산구', 'USER');

INSERT INTO boards(u_id, title, content)
VALUES
    ('user01', '첫 번째 글입니다.', '안녕하세요. 첫 번째 글입니다.'),
    ('user02', '두 번째 글입니다.', '안녕하세요. 두 번째 글입니다.'),
    ('user03', '세 번째 글입니다.', '안녕하세요. 세 번째 글입니다.'),
    ('user04', '네 번째 글입니다.', '안녕하세요. 네 번째 글입니다.'),
    ('user05', '다섯 번째 글입니다.', '안녕하세요. 다섯 번째 글입니다.'),
    ('user06', '여섯 번째 글입니다.', '안녕하세요. 여섯 번째 글입니다.'),
    ('user07', '일곱 번째 글입니다.', '안녕하세요. 일곱 번째 글입니다.'),
    ('user08', '여덟 번째 글입니다.', '안녕하세요. 여덟 번째 글입니다.'),
    ('user09', '아홉 번째 글입니다.', '안녕하세요. 아홉 번째 글입니다.'),
    ('user10', '열 번째 글입니다.', '안녕하세요. 열 번째 글입니다.'),
    ('user11', '열한 번째 글입니다.', '안녕하세요. 열한 번째 글입니다.'),
    ('user12', '열두 번째 글입니다.', '안녕하세요. 열두 번째 글입니다.'),
    ('user13', '열세 번째 글입니다.', '안녕하세요. 열세 번째 글입니다.'),
    ('user14', '열네 번째 글입니다.', '안녕하세요. 열네 번째 글입니다.'),
    ('user15', '열다섯 번째 글입니다.', '안녕하세요. 열다섯 번째 글입니다.'),
    ('user16', '열여섯 번째 글입니다.', '안녕하세요. 열여섯 번째 글입니다.'),
    ('user17', '열일곱 번째 글입니다.', '안녕하세요. 열일곱 번째 글입니다.'),
    ('user18', '열여덟 번째 글입니다.', '안녕하세요. 열여덟 번째 글입니다.'),
    ('user19', '열아홉 번째 글입니다.', '안녕하세요. 열아홉 번째 글입니다.'),
    ('user20', '스무 번째 글입니다.', '안녕하세요. 스무 번째 글입니다.');

INSERT INTO board_replies(b_id,u_id, content)
VALUES
    (1,'user01', '첫 번째 글에 대한 댓글입니다.'),
    (1,'user01', '첫 번째 글에 대한 댓글 2번째입니다.'),
    (1,'user01', '첫 번째 글에 대한 댓글 3번째입니다.'),
    (2,'user02', '두 번째 글에 대한 댓글입니다.'),
    (2,'user02', '두 번째 글에 대한 댓글 2번째입니다.'),
    (2,'user02', '두 번째 글에 대한 댓글 3번째입니다.'),
    (3,'user03', '세 번째 글에 대한 댓글입니다.'),
    (3,'user03', '세 번째 글에 대한 댓글 2번째입니다.'),
    (3,'user03', '세 번째 글에 대한 댓글 3번째입니다.'),
    (4,'user04', '네 번째 글에 대한 댓글입니다.'),
    (4,'user04', '네 번째 글에 대한 댓글 2번째입니다.'),
    (4,'user04', '네 번째 글에 대한 댓글 3번째입니다.'),
    (5,'user05', '다섯 번째 글에 대한 댓글입니다.'),
    (5,'user06', '다섯 번째 글에 대한 댓글 2번째입니다.'),
    (5,'user07', '다섯 번째 글에 대한 댓글 3번째입니다.'),
    (6,'user08', '여섯 번째 글에 대한 댓글입니다.'),
    (6,'user09', '여섯 번째 글에 대한 댓글 2번째입니다.'),
    (6,'user10', '여섯 번째 글에 대한 댓글 3번째입니다.');

INSERT INTO board_likes(b_id, u_id, status)
VALUES
    (1, 'user01', 'LIKE'),
    (1, 'user02', 'BAD'),
    (2, 'user02', 'SAD'),
    (3, 'user03', 'BEST'),
    (3, 'user04', 'LIKE'),
    (4, 'user05', 'BAD'),
    (5, 'user06', 'SAD'),
    (5, 'user07', 'LIKE'),
    (5, 'user08', 'BEST'),
    (6, 'user09', 'LIKE'),
    (7, 'user10', 'SAD'),
    (8, 'user11', 'BEST'),
    (8, 'user12', 'LIKE'),
    (8, 'user13', 'BAD'),
    (9, 'user14', 'LIKE'),
    (9, 'user15', 'BEST'),
    (10, 'user16', 'SAD'),
    (10, 'user17', 'LIKE'),
    (10, 'user18', 'BAD');


INSERT INTO board_imgs(b_id, img_path)
VALUES
(1, '/img/board/1.jpg'),
(1, '/img/board/2.jpg'),
(2, '/img/board/3.jpg'),
(2, '/img/board/4.jpg'),
(3, '/img/board/5.jpg'),
(4, '/img/board/6.jpg'),
(4, '/img/board/7.jpg'),
(4, '/img/board/8.jpg'),
(5, '/img/board/9.jpg'),
(5, '/img/board/10.jpg'),
(5, '/img/board/11.jpg'),
(6, '/img/board/12.jpg'),
(6, '/img/board/13.jpg'),
(7, '/img/board/14.jpg'),
(8, '/img/board/15.jpg'),
(9, '/img/board/99.jpg'),
(10, '/img/board/17.jpg'),
(11, '/img/board/18.jpg'),
(12, '/img/board/19.jpg'),
(12, '/img/board/20.jpg'),
(13, '/img/board/21.jpg'),
(13, '/img/board/22.jpg'),
(14, '/img/board/23.jpg'),
(15, '/img/board/24.jpg'),
(15, '/img/board/25.jpg'),
(16, '/img/board/26.jpg'),
(16, '/img/board/27.jpg'),
(17, '/img/board/28.jpg'),
(18, '/img/board/29.jpg'),
(19, '/img/board/30.jpg'),
(20, '/img/board/31.jpg');

CREATE USER 'boardDba'@'localhost' IDENTIFIED BY 'mysql123';
GRANT ALL PRIVILEGES ON webAppBoard.* TO 'boardDba'@'localhost';

CREATE USER 'boardServerDev'@'localhost' IDENTIFIED BY 'mysql123';
GRANT SELECT, INSERT, UPDATE, DELETE ON webAppBoard.* TO 'boardServerDev'@'localhost';

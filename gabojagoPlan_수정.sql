CREATE DATABASE gabojagoPlan CHARACTER SET utf8;

#유저 테이블
#MBTI NULL OR NOT NULL??필수 선택??
CREATE TABLE `user` (
                        `u_id`	varchar(255) PRIMARY KEY COMMENT '사용자 아이디',
                        `pw`	varchar(255) NOT NULL COMMENT'비밀번호',
                        `name`	varchar(255) NOT NULL COMMENT'이름',
                        `nk_name`	varchar(255) NOT NULL COMMENT'닉네임',
                        `email`	varchar(255) UNIQUE NOT NULL COMMENT'이메일',
                        `birth`	date NOT NULL COMMENT'생년월일',
                        `phone`	varchar(20) UNIQUE NOT NULL COMMENT'핸드폰',
                        `address`	varchar(255) COMMENT '이메일',
                        `detail_address`	varchar(255) COMMENT '상세주소',
                        `pr_content`	text  COMMENT '자기소개 ',
                        `permission`	enum('USER','PARTNER','ADMIN') NOT NULL DEFAULT 'USER' COMMENT '권한',
                        `mbti`	enum('ISTJ','ISTP','ISFJ','ISFP','INTJ','INTP','INFJ','INFP','ESTJ','ESTP','ESFJ','ESFP','ENTJ','ENTP','ENFJ','ENFP') COMMENT 'MBTI',
                        `img_path`	varchar(255) COMMENT '프로필 이미지',
                        `post_time`	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성 시간',
                        `store_name`	varchar(255) COMMENT '상호명',
                        `business_id`	varchar(255) COMMENT '사업자 번호'
);
#공지사항 테이블
CREATE TABLE `notice` (
                                `n_id`	int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '공지사항 아이디',
                                `u_id`	varchar(255)	NOT NULL COMMENT '작성자 아이디',
                                `title`	varchar(255) NOT NULL COMMENT '제목',
                                `content`	TEXT COMMENT '내용',
                                `post_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성 시간',
                                `update_time` TIMESTAMP	NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정 시간',
                                `view_count`	INT UNSIGNED DEFAULT 0 COMMENT '조회수',
                                `img_path`	varchar(255) COMMENT '프로필 이미지',
                                FOREIGN KEY (u_id) REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE
);
#공지사항 조회수 테이블
#제약조건 추가 조회수1번만 가능하게
CREATE TABLE `notice_viewcount` (
                                    `n_cnt`	int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '조회수 아이디',
                                    `n_id`	int unsigned NOT NULL COMMENT '게시글 아이디',
                                    `u_id`	varchar(255) NOT NULL COMMENT '유저 아이디',
                                    `view_count`	INT UNSIGNED DEFAULT 0 COMMENT '조회수',
                                    FOREIGN KEY (n_id) REFERENCES board_notice (n_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                    FOREIGN KEY (u_id) REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                    CONSTRAINT notice_viewcnt UNIQUE (u_id, n_id)
);

#마일리지 테이블
CREATE TABLE `mileage` (
                           `m_id`	int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '판매글 아이디',
                           `u_id`	varchar(255) NOT NULL COMMENT '유저 아이디',
                           `mileage`	int	default 0 COMMENT '마일리지',
                           `content`	varchar(255)	COMMENT '적립내용',
                           `post_time`	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '적립 일자',
                           FOREIGN KEY (u_id) REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE
);

#맞춤 추천 테이블
#`web_address`	이게 뭐죠?
CREATE TABLE `trips` (
                              `bt_id`	int unsigned  AUTO_INCREMENT PRIMARY KEY COMMENT'맞춤추천 아이디',
                              `u_id`	varchar(255)	NOT NULL COMMENT '작성자 아이디',
                              `title`	varchar(255) NOT NULL COMMENT '제목',
                              `post_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성 시간',
                              `update_time` TIMESTAMP	NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정 시간',
                              `area`	ENUM('서울', '인천', '대전', '광주', '대구', '울산', '부산', '세종', '경기', '강원', '충북', '충남', '전북', '전남', '경북', '경남', '제주')NOT NULL COMMENT '지역',
                              `address`	varchar(255) COMMENT '이메일',
                              `phone`	varchar(20) UNIQUE NOT NULL COMMENT'핸드폰',
                              `web_address`	varchar(255) COMMENT '추천 홈페이지????',
                              `content`	TEXT  COMMENT '내용',
                              `istj`	boolean	COMMENT 'MBTI',
                              `istp`	boolean	COMMENT 'MBTI',
                              `isfj`	boolean	COMMENT 'MBTI',
                              `isfp`	boolean COMMENT 'MBTI',
                              `intj`	boolean	COMMENT 'MBTI',
                              `intp`	boolean	COMMENT 'MBTI',
                              `infj`	boolean	COMMENT 'MBTI',
                              `infp`	boolean	COMMENT 'MBTI',
                              `estj`	boolean	COMMENT 'MBTI',
                              `estp`	boolean	COMMENT 'MBTI',
                              `esfj`	boolean	COMMENT 'MBTI',
                              `esfp`	boolean	COMMENT 'MBTI',
                              `entj`	boolean	COMMENT 'MBTI',
                              `entp`	boolean	COMMENT 'MBTI',
                              `enfj`	boolean	COMMENT 'MBTI',
                              `enfp`	boolean	COMMENT 'MBTI',
                              `category`	enum('힐링','체험','반려동물','레저','박물관')	NOT NULL COMMENT '카테고리',
                              FOREIGN KEY (u_id) REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE
);

#가보자고(리뷰 페이지)
CREATE TABLE `trip_review` (
                               `btr_id` 	int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '가보자고리뷰 아이디',
                               `bt_id`	int unsigned NOT NULL COMMENT '게시글 아이디',
                               `u_id`	varchar(255)NOT NULL COMMENT '유저 아이디',
                               `content`	TEXT  COMMENT '내용 내용',
                               `visit`	boolean	COMMENT '방문여부',
                               `post_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성 시간',
                               `update_time` TIMESTAMP	NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정 시간',
                               `grade`	int unsigned	COMMENT '평점',
                               FOREIGN KEY (u_id) REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE,
                               FOREIGN KEY (bt_id) REFERENCES board_trip (bt_id) ON DELETE CASCADE ON UPDATE CASCADE
);

#가보자고(해시태그 테이블)
CREATE TABLE `trip_review_hashtag` (
                                       `trh_index`	int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '해시태그 아이디',
                                       `tag_id`	int unsigned NOT NULL COMMENT '태그 아이디',
                                       `btr_id`	int unsigned NOT NULL COMMENT '리뷰 아이디',
                                       FOREIGN KEY (tag_id) REFERENCES all_hashtag (tag_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                       FOREIGN KEY (btr_id) REFERENCES trip_review (btr_id) ON DELETE CASCADE ON UPDATE CASCADE
);

#가보자고(이미지보드 테이블)
CREATE TABLE `trip_imgs` (
                             `ti_id` int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '이미지 아이디',
                             `bt_id`	int unsigned NOT NULL COMMENT '게시글 아이디',
                             `img_path`	varchar(255) COMMENT'이미지 경로',
                             `img_main`	boolean	COMMENT '메인이미지'
);
#가보자고(북마크 테이블)
#제약조건 추가 1개만북마크 가능하게
CREATE TABLE `trip_bookmark` (
                                 `btb_id`	int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '북마크 아이디',
                                 `bt_id`	int unsigned NOT NULL COMMENT '게시글 아이디',
                                 `u_id`	varchar(255)NOT NULL COMMENT '유저 아이디',
                                 FOREIGN KEY (u_id) REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                 FOREIGN KEY (bt_id) REFERENCES board_trip (bt_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                 CONSTRAINT t_bookmark UNIQUE (u_id, bt_id)
);
#가보자고(리뷰에 덧글 페이지)
CREATE TABLE `trip_review_replies` (
                                       `btrr_id` int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '리뷰댓글 아이디',
                                       `btr_id` int unsigned NOT NULL COMMENT '리뷰 아이디',
                                       `u_id`	varchar(255)NOT NULL COMMENT '유저 아이디',
                                       `content` TEXT  COMMENT '내용 ',
                                       `status`	enum('PUBLIC','PRIVATE') DEFAULT 'PUBLIC' COMMENT '상태',
                                       `post_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성 시간',
                                       `update_time` TIMESTAMP	NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정 시간',
                                       `parent_btrr_id`	INT UNSIGNED COMMENT '부모 댓글 아이디(대댓글)',
                                       FOREIGN KEY (u_id) REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                       FOREIGN KEY (btr_id) REFERENCES trip_review (btr_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                       FOREIGN KEY (parent_btrr_id) REFERENCES trip_review_replies (btrr_id) ON DELETE CASCADE ON UPDATE CASCADE

);
#가보자고 (좋아요 페이지)
#제약조건 추가 1개만 좋아요 가능하게
CREATE TABLE `trip_likes` (
                              `btl_id` int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '좋아요 아이디',
                              `bt_id`	int unsigned NOT NULL COMMENT '게시글 아이디',
                              `u_id`	varchar(255)NOT NULL COMMENT '유저 아이디',
                              FOREIGN KEY (u_id) REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE,
                              FOREIGN KEY (bt_id) REFERENCES board_trip (bt_id) ON DELETE CASCADE ON UPDATE CASCADE,
                              CONSTRAINT t_likes UNIQUE (u_id, bt_id)
);
#가보자고( 리뷰_좋아요 테이블)
#제약조건 추가 1개만 좋아요 가능하게
CREATE TABLE `trip_review_likes` (
                                     `btrl_id`	 int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '리뷰좋아요 아이디',
                                     `btr_id`	int unsigned NOT NULL COMMENT '리뷰 아이디',
                                     `u_id`	varchar(255)NOT NULL COMMENT '유저 아이디',
                                     FOREIGN KEY (u_id) REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                     FOREIGN KEY (btr_id) REFERENCES trip_review (btr_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                     CONSTRAINT tr_likes UNIQUE (u_id, btr_id)
);
#가보자고( 해시태그 테이블)
CREATE TABLE `trip_hashtag` (
                                `th_index`	 int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '가보자고해시태그 아이디',
                                `bt_id`	int unsigned NOT NULL COMMENT '게시글 아이디',
                                `tag_id`	int unsigned NOT NULL COMMENT '태그 아이디',
                                FOREIGN KEY (bt_id) REFERENCES board_trip (bt_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                FOREIGN KEY (tag_id) REFERENCES all_hashtag (tag_id) ON DELETE CASCADE ON UPDATE CASCADE
);
#가보자고( 리뷰 신고 테이블)
CREATE TABLE `trip_review_report` (
                                      `btrr_id`	int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '신고글 아이디',
                                      `btr_id`	int unsigned NOT NULL COMMENT '마일리지 아이디',
                                      `u_id`	varchar(255) NOT NULL COMMENT '유저 아이디',
                                      `content`	text	COMMENT '신고내용',
                                      `post_time`	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '신고 날짜',
                                      FOREIGN KEY (u_id) REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                      FOREIGN KEY (btr_id) REFERENCES trip_review (btr_id) ON DELETE CASCADE ON UPDATE CASCADE
);
#가보자고 조회수 테이블
#제약조건 추가 조회수1번만 가능하게
CREATE TABLE `trip_viewcount` (
                                  `tv_id`	int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '조회수 아이디',
                                  `bt_id`	int unsigned NOT NULL COMMENT '게시글 아이디',
                                  `u_id`	varchar(255) NOT NULL COMMENT '유저 아이디',
                                  `view_count`	INT UNSIGNED DEFAULT 0 COMMENT '조회수',
                                  FOREIGN KEY (bt_id) REFERENCES board_trip (bt_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                  FOREIGN KEY (u_id) REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                  CONSTRAINT trip_viewcnt UNIQUE (u_id, bt_id)

);


#해시태그 테이블
CREATE TABLE `all_hashtags` (
                               `tag_id`	int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '태그 아이디',
                               `tagname`	varchar(255)	COMMENT '태그이름'
);

#같이가자(플래너)
#`info` `from` `to` 어떤 형식???
CREATE TABLE `board_plans` (
                              `bp_id`	int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '플랜 아이디',
                              `u_id`	varchar(255) NOT NULL COMMENT '작성자 아이디',
                              `title`	varchar(255) NOT NULL COMMENT '제목',
                              `info`	TEXT  COMMENT '설명',
                              `from`	varchar(255) COMMENT '일정시작',
                              `to`	varchar(255) COMMENT '일정 끝',
                              `post_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성 시간',
                              `update_time` TIMESTAMP	NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정 시간',
                              `img_path`	varchar(255) COMMENT '대표 이미지',
                              `status`	enum('PUBLIC','PRIVATE') DEFAULT 'PUBLIC' COMMENT '상태',
                              FOREIGN KEY (u_id) REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE
);

#같이가자(그림판)
#con_info (어떤형식 text? 간단한 글??)
CREATE TABLE `plan_content` (
                                `con_id` int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '컨텐츠 아이디',
                                `bp_id`	int unsigned NOT NULL COMMENT '플랜 아이디',
                                `bt_id`	int unsigned NOT NULL COMMENT '정보글 아이디',
                                `con_info`	varchar(255) COMMENT'개별 스케줄 정보',
                                `img_path`	varchar(255) COMMENT'이미지 경로',
                                FOREIGN KEY (bp_id) REFERENCES board_plan (bp_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                FOREIGN KEY (bt_id) REFERENCES board_trip (bt_id) ON DELETE CASCADE ON UPDATE CASCADE
);

#같이가자(체크리스트)
#content = 항목?? 내용?? title??
CREATE TABLE `plan_checklist` (
                                  `cl_id`	 int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '체크리스트 아이디',
                                  `bp_id`	int unsigned NOT NULL COMMENT '플랜 아이디',
                                  `content`	varchar(255) COMMENT '항목',
                                  `status`	enum('CHECKED','UNCHECKED')	COMMENT '체크여부',
                                  FOREIGN KEY (bp_id) REFERENCES board_plan (bp_id) ON DELETE CASCADE ON UPDATE CASCADE

);
#같이가자(리뷰_덧글 좋아요페이지)
#제약조건 추가 1개만 좋아요 가능하게
#유저아이디 2개여서 뺏어요 확인바람
CREATE TABLE `trip_review_replies_likes` (
                                             `btrrl_id`	 int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '리뷰덧글좋아요 아이디',
                                             `u_id`	varchar(255)NOT NULL COMMENT '유저 아이디',
                                             `btrr_id`	int unsigned NOT NULL COMMENT '리뷰댓글 아이디',
                                             FOREIGN KEY (u_id) REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                             FOREIGN KEY (btrr_id) REFERENCES trip_review_replies (btrr_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                             CONSTRAINT trr_likes UNIQUE (u_id, btrr_id)
);

#같이가자(리뷰_이미지보드 테이블)
CREATE TABLE `trip_review_imgs` (
                                    `btri_id`	 int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '이미지 아이디',
                                    `btr_id`	int unsigned NOT NULL COMMENT '리뷰 아이디',
                                    `img_path`	varchar(255) COMMENT'이미지 경로',
                                    FOREIGN KEY (btr_id) REFERENCES trip_review (btr_id) ON DELETE CASCADE ON UPDATE CASCADE
);
#같이가자(멤버목록)
#멤버유저아이디 fk조건 안가져도 되는건지??
CREATE TABLE `plan_members` (
                                `ml_id`	int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '멤버목록 아이디',
                                `bp_id`	int unsigned NOT NULL COMMENT '플랜 아이디',
                                `mu_id`	varchar(255) COMMENT '멤버유저 아이디',
                                FOREIGN KEY (bp_id) REFERENCES board_plan (bp_id) ON DELETE CASCADE ON UPDATE CASCADE
);
#같이가자(그림판경로데이터 테이블)
CREATE TABLE `plan_content_path` (
                                     `path_id`	int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '경로 아이디',
                                     `con_id`	int unsigned NOT NULL COMMENT '컨텐츠 아이디',
                                     `con_path`	MEDIUMTEXT	COMMENT '캔버스 데이터',
                                     FOREIGN KEY (con_id) REFERENCES plan_content (con_id) ON DELETE CASCADE ON UPDATE CASCADE
);



#같이놀자( 커뮤니티)
#STATUS 없어서 공개 비공개 설정 해놓음.
CREATE TABLE `board_comm` (
                              `bc_id`	int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '커뮤니티글 아이디',
                              `u_id`	varchar(255) NOT NULL COMMENT '작성자 아이디',
                              `bp_id`	int unsigned NOT NULL COMMENT '플랜 아이디',
                              `title`	varchar(255) NOT NULL COMMENT '제목',
                              `content`	TEXT  COMMENT '내용',
                              `post_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성 시간',
                              `update_time` TIMESTAMP	NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정 시간',
                              `view_count`	INT UNSIGNED DEFAULT 0 COMMENT '조회수',
                              `status`	enum('PUBLIC','PRIVATE') DEFAULT 'PUBLIC' COMMENT '상태',
                              `area`	ENUM('서울', '인천', '대전', '광주', '대구', '울산', '부산', '세종', '경기', '강원', '충북', '충남', '전북', '전남', '경북', '경남', '제주')NOT NULL,
                              `istj`	boolean	COMMENT 'MBTI',
                              `istp`	boolean	COMMENT 'MBTI',
                              `isfj`	boolean	COMMENT 'MBTI',
                              `isfp`	boolean COMMENT 'MBTI',
                              `intj`	boolean	COMMENT 'MBTI',
                              `intp`	boolean	COMMENT 'MBTI',
                              `infj`	boolean	COMMENT 'MBTI',
                              `infp`	boolean	COMMENT 'MBTI',
                              `estj`	boolean	COMMENT 'MBTI',
                              `estp`	boolean	COMMENT 'MBTI',
                              `esfj`	boolean	COMMENT 'MBTI',
                              `esfp`	boolean	COMMENT 'MBTI',
                              `entj`	boolean	COMMENT 'MBTI',
                              `entp`	boolean	COMMENT 'MBTI',
                              `enfj`	boolean	COMMENT 'MBTI',
                              `enfp`	boolean	COMMENT 'MBTI',
                              FOREIGN KEY (u_id) REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE,
                              FOREIGN KEY (bp_id) REFERENCES board_plan (bp_id) ON DELETE CASCADE ON UPDATE CASCADE
);


#같이놀자( 덧글)
CREATE TABLE `comm_replies` (
                                `bcr_id`	int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '같이놀자댓글 아이디',
                                `bc_id`	int unsigned NOT NULL COMMENT '게시글 번호',
                                `u_id`	varchar(255)	NOT NULL COMMENT '유저 아이디',
                                `content`	TEXT  COMMENT '내용',
                                `status`	enum('PUBLIC','PRIVATE') DEFAULT 'PUBLIC' COMMENT '상태',
                                `post_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성 시간',
                                `update_time` TIMESTAMP	NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정 시간',
                                `parent_bcr_id`	INT UNSIGNED COMMENT '부모 댓글 아이디(대댓글)',
                                FOREIGN KEY (u_id) REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                FOREIGN KEY (bc_id) REFERENCES board_comm (bc_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                FOREIGN KEY (parent_bcr_id) REFERENCES comm_replies (bcr_id) ON DELETE CASCADE ON UPDATE CASCADE
);
#같이놀자 ( 이미지테이블)
CREATE TABLE `comm_imgs` (
                             `bci_id`	int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '이미지 아이디',
                             `bc_id`	int unsigned NOT NULL COMMENT '게시글 번호',
                             `img_path`	varchar(255) COMMENT '이미지 경로',
                             `img_main`	boolean	COMMENT '대표 이미지',
                             FOREIGN KEY (bc_id) REFERENCES board_comm (bc_id) ON DELETE CASCADE ON UPDATE CASCADE
);
#같이놀자 (신고 테이블)
#게시글에 신고 1번 가능 제약조건
CREATE TABLE `comm_report` (
                               `bcr_id`	int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '신고글 아이디',
                               `bc_id`	int unsigned NOT NULL COMMENT '게시글 번호',
                               `u_id`	varchar(255)	NOT NULL COMMENT '유저 아이디',
                               `content`	TEXT  COMMENT '신고 내용',
                               `post_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성 시간',
                               FOREIGN KEY (u_id) REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE,
                               FOREIGN KEY (bc_id) REFERENCES board_comm (bc_id) ON DELETE CASCADE ON UPDATE CASCADE,
                               CONSTRAINT comm_report UNIQUE (u_id, bc_id)
);
#같이놀자 ( 좋아요 테이블)
#제약조건 추가 1개만 좋아요 가능하게
CREATE TABLE `comm_likes` (
                              `bcl_id` int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '좋아요 아이디',
                              `bc_id`	int unsigned NOT NULL COMMENT '게시글 번호',
                              `u_id`	varchar(255)	NOT NULL COMMENT '유저 아이디',
                              FOREIGN KEY (u_id) REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE,
                              FOREIGN KEY (bc_id) REFERENCES board_comm (bc_id) ON DELETE CASCADE ON UPDATE CASCADE,
                              CONSTRAINT c_likes UNIQUE (u_id, bc_id)
);
#같이놀자(북마크 테이블)
#제약조건 추가 1개만북마크 가능하게
CREATE TABLE `comm_bookmark` (
                                 `cbook_id`	int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '북마크 아이디',
                                 `bc_id`	int unsigned NOT NULL COMMENT '게시글 번호',
                                 `u_id`	varchar(255)	NOT NULL COMMENT '유저 아이디',
                                 FOREIGN KEY (u_id) REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                 FOREIGN KEY (bc_id) REFERENCES board_comm (bc_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                 CONSTRAINT c_bookmark UNIQUE (u_id, bc_id)
);
#같이놀자(해시태그 테이블)
CREATE TABLE `comm_hashtag` (
                                `ch_index`	int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '해시태그 아이디',
                                `bc_id`	int unsigned NOT NULL COMMENT '게시글 번호',
                                `tag_id` int unsigned NOT NULL COMMENT '태그 아이디',
                                FOREIGN KEY (tag_id) REFERENCES all_hashtag (tag_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                FOREIGN KEY (bc_id) REFERENCES board_comm (bc_id) ON DELETE CASCADE ON UPDATE CASCADE
);
#같이놀자(조회수 테이블)
#제약조건 추가 조회수1번만 가능하게
CREATE TABLE `comm_viewcount` (
                                  `cv_index`	int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '조회수 아이디',
                                  `bc_id`	int unsigned NOT NULL COMMENT '게시글 아이디',
                                  `u_id`	varchar(255) NOT NULL COMMENT '유저 아이디',
                                  `view_count` INT UNSIGNED DEFAULT 0 COMMENT '조회수',
                                  FOREIGN KEY (bc_id) REFERENCES board_comm (bc_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                  FOREIGN KEY (u_id) REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                  CONSTRAINT comm_viewcnt UNIQUE (u_id, bc_id)
);
#상품판매 (상품옵션 테이블)
CREATE TABLE `sell_option` (
                               `o_id`	int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '옵션 아이디',
                               `name`	VARCHAR(255) NOT NULL COMMENT '옵션 이름',
                               `price`	varchar(255) NOT NULL COMMENT '옵션 가격',
                               `stock`	int unsigned	COMMENT '재고수량'
);

#여행지티켓 테이블
CREATE TABLE `board_sell` (
                              `s_id`		int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '판패글 아이디',
                              `u_id`	varchar(255) NOT NULL COMMENT '작성자 아이디',
                              `o_id` int unsigned NOT NULL COMMENT '옵션 아이디',
                              `area`	ENUM('서울', '인천', '대전', '광주', '대구', '울산', '부산', '세종', '경기', '강원', '충북', '충남', '전북', '전남', '경북', '경남', '제주')	NOT NULL,
                              `title`	varchar(255) NOT NULL COMMENT '제목',
                              `content`	TEXT  COMMENT '내용',
                              `post_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성 시간',
                              `update_time` TIMESTAMP	NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정 시간',
                              `view_count`	INT UNSIGNED DEFAULT 0 COMMENT '조회수',
                              `category`	enum('워터','테마','키즈','레저','박물관')	NOT NULL COMMENT '카테고리',
                              `qnt`	int unsigned COMMENT '수량',
                              `img_main`	boolean	COMMENT '대표이미지',
                              FOREIGN KEY (u_id) REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE,
                              FOREIGN KEY (o_id) REFERENCES sell_option (o_id) ON DELETE CASCADE ON UPDATE CASCADE
);
#상품판매 (이미지 테이블)
CREATE TABLE `sell-imgs` (
                             `simg_id`	int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '이미지 아이디',
                             `s_id`	int unsigned NOT NULL COMMENT '문의글 아이디',
                             `img_path`	varchar(255) COMMENT'이미지 경로',
                             FOREIGN KEY (s_id) REFERENCES board_sell (s_id) ON DELETE CASCADE ON UPDATE CASCADE

);

#상품판매(조회수)
#제약조건 추가 조회수1번만 가능하게
CREATE TABLE `sell_viewcount` (
                                  `svc_id`	int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '판매글 아이디',
                                  `s_id`		int unsigned NOT NULL COMMENT '판매글 아이디',
                                  `u_id`	varchar(255) NOT NULL COMMENT '유저 아이디',
                                  `view_count`  INT UNSIGNED DEFAULT 0 COMMENT '조회수',
                                  FOREIGN KEY (s_id) REFERENCES board_sell (s_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                  FOREIGN KEY (u_id) REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                  CONSTRAINT sell_viewcnt UNIQUE (u_id, s_id)
);

#상품판매 ( 북마크)
#제약조건 추가 1개만북마크 가능하게
CREATE TABLE `sell_bookmark` (
                                 `sbook_id`	int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '북마크 아이디',
                                 `s_id`	int unsigned NOT NULL COMMENT '판매글 아이디',
                                 `u_id`	varchar(255)NOT NULL COMMENT '유저 아이디',
                                 FOREIGN KEY (u_id) REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                 FOREIGN KEY (s_id) REFERENCES board_sell (s_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                 CONSTRAINT s_bookmark UNIQUE (u_id, s_id)
);

#상품판매(주문상세 테이블)
#카드정보 sale_date 는 보안상 데이터 베이스에 저장하는건 위험해서 따로 인증절차 방법으로
#카드정보말고 결제를 무엇으로 했는지만 알수있게 info 추가.
CREATE TABLE `sell_detail` (
                               `oder_id`	int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '주문번호 아이디',
                               `u_id`	varchar(255)NOT NULL COMMENT '유저 아이디',
                               `o_id`  int unsigned NOT NULL COMMENT '옵션 아이디',
                               `price`	varchar(255)	COMMENT '결제 금액',
                               `sale_date`	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '결제일',
                               `info` text COMMENT '결제 정보',
                               FOREIGN KEY (u_id) REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE,
                               FOREIGN KEY (m_id) REFERENCES mileage (m_id) ON DELETE CASCADE ON UPDATE CASCADE
);
#장바구니
CREATE TABLE `sell_cart` (
                             `cart_id`	int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '장바구니 아이디',
                             `u_id`	varchar(255) NOT NULL COMMENT '작성자 아이디',
                             `s_id`	int unsigned NOT NULL COMMENT '옵션 아이디',
                             FOREIGN KEY (u_id) REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE,
                             FOREIGN KEY (s_id) REFERENCES board_sell (s_id) ON DELETE CASCADE ON UPDATE CASCADE
);


#마이페이지( 팔로우 테이블)
CREATE TABLE `follow` (
                          `f_id`	varchar(255)  PRIMARY KEY COMMENT '팔로우 아이디',
                          `to_user`	varchar(255)	NOT NULL COMMENT '받는유저 아이디',
                          `from_user`	varchar(255)NOT NULL COMMENT '보내는유저',
                          FOREIGN KEY (to_user) REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE
);

#마이페이지( 문의하기 테이블)
CREATE TABLE `board_qna` (
                             `bq_id`	int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '문의글 아이디',
                             `u_id`	varchar(255) NOT NULL COMMENT '유저 아이디',
                             `title`	varchar(255) NOT NULL COMMENT '제목',
                             `content`	text	COMMENT '내용',
                             `file_path`	varchar(255)	COMMENT '파일첨부',
                             `post_time`	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '문의 날짜',
                             FOREIGN KEY (u_id) REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE
);

#문의하기(덧글 테이블)
CREATE TABLE `qna_reply` (
                             `bqr_id` int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '옵션 아이디',
                             `bq_id`	int unsigned NOT NULL COMMENT '문의글 아이디',
                             `u_id`	varchar(255) NOT NULL COMMENT '유저 아이디',
                             `content`	text	COMMENT '댓글 내용',
                             `post_time`	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일',
                             `update_time`	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',
                             FOREIGN KEY (bq_id) REFERENCES board_qna (bq_id) ON DELETE CASCADE ON UPDATE CASCADE,
                             FOREIGN KEY (u_id) REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE
);


#쪽지 테이블
CREATE TABLE `note` (
                        `l_id`	int unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '쪽지 아이디',
                        `to_user`	int unsigned NOT NULL COMMENT '컨텐츠 아이디',
                        `from_user`	varchar(255) NOT NULL COMMENT '보내는 유저 아이디',
                        `post_time`	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '발신일자',
                        `title`	  varchar(255)NOT NULL COMMENT '제목',
                        `content`	varchar(255) COMMENT '짧은내용',
                        `status`	boolean COMMENT '읽은 상태',
                        FOREIGN KEY (to_user) REFERENCES user (u_id) ON DELETE CASCADE ON UPDATE CASCADE

);

# SpringMybatisBoard20230327

<h1>스프링 Gradle 로 좋아요 싫어요, 팔로우, 대댓글, 이메일 체크가 가능한 게시판 구현</h1>
<h2>작동시키는 방법(주의! env.properties 를 직접 생성해야 실행할 수 있습니다!)</h2>
<ol>
    <li>mysql 설치후 webAppBoard.sql 실행해서 스키마 생성</li>
    <li>구글 개정 생성 > 보안 > Google에 로그인 > 2단계 인증 설정 > 앱 비밀번호 설정 > 비밀번호 생성</li>
    <li>src/main/resources/env.properties 파일 생성하기</li>
    <li>env.properties db.password="db 비밀번호"</li>
    <li>env.properties email.password="구글 이메일 비밀번호"</li>
    <li>env.properties email.user="2단계 인증 후 생성한 앱 비밀번호"</li>
    <li>intellij 에서는 모듈 설정 > 모듈 가져오기 > 해당 프로젝트 선택 > gradle </li>
    <li>gradle 탭에서 새로고침 or build.gradle 에서 코끼리 모양 새로고침</li>
</ol>
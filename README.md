# leather-homepage

- TDD

- Back-End

  - Java to Kotlin
  - Spring Boot / Spring MVC / Spring Data JPA
  - JPA / Querydsl / ~~JOOQ~~
  - Mockito, Spock, mockMvc
  - Gradle
  - Redis

- DevOps
  - MySQL
  - Jenkins
  - Nginx
  - AWS-EC2

- Front-End
  - Thymeleaf / Javascript / JQuery

---

- [ ] Index
- [ ] Member 추가 기능

**공통 기능**

- [x] Thymleaf
  - [x] 템플릿 레이아웃 적용
- [x] Controller
  - [x] API 용과 View Resolver 용 분리. (accept 로 구분)
- [ ] Exception
  - [ ] 서블릿 예외 처리
  - [x] 스프링 부트 오류 페이지
    - [x] 기본 오류 페이지
- [x] 로그인
  - [x] 스프링 인터셉터 (로그인 세션 체크)
- [x] Data Validation
  - [x] 타입 에러 메시지 생성
  - [x] Bean Validation
- [x] validation 에 range 설정

---

## ETC

- [ ] 권한에 따른 접근 제어 확인
  - [ ] 인증. 스프링 인터셉터 활용? [참고](https://github.com/jihunparkme/Inflearn_Spring_MVC_Part-2/blob/7fc7ecec6ae9167352f2d14894216037d96c8c7e/login/src/main/java/hello/login/WebConfig.java)
- [ ] 반영 시 
  - [ ] SecuriyConfig configure 에서 h2-console 관련 설정 해제
  - [ ] 메일 템플릿에 링크 수정

---

## (finish) Notice

- [x] List/View/Add/Edit
  - [x] List
    - [x] 등록 버튼은 관리자에게만 표시 (세션 로그인 정보 활용)
  - [x] View
    - [x] 세션을 활용한 조회수 중복 방지 noticeId/ip
    - [x] 수정 버튼은 관리자에게만 표시
    - [x] div 눌러도 View page 이동
  - [x] Add
    - [x] 등록 시 사용자 key id 도 함께 저장되도록
    - [x] 저장 버튼은 관리자에게만 표시
    - [x] 권한이 없을 시 권한 없음 팝업
  - [x] Edit
    - [x] 수정/삭제 버튼은 관리자에게만 표시
    - [x] 수정/삭제 권한이 없을 시 권한 없음 팝업
- [x] 관리자만 등록/수정/삭제 할 수 있도록 Controller 로그인 및 권한 설정
- [x] 로그인 완료 후 이전 페이지로

---

## Member

- [x] OAuth2
  - [x] [구글](https://console.developers.google.com/)로 로그인
    - [API Docs]
  - [x] [네이버](https://developers.naver.com/apps/)로 로그인
    - [API Docs](https://developers.naver.com/docs/login/api/api.md)
  - [x] [카카오](https://developers.kakao.com/)로 로그인
    - [API Docs](https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api)
- [ ] 회원가입의 경우 이메일과 이름 정보 보여주고 가입하기 페이지로 이동
- [x] View (개인정보 확인) `POST`
  - [x] 접근 시 로그인 세션 체크
  - [x] 정보 표시를 아이디와 이미지로
  - [x] 탈퇴하기 기능
  - [ ] 이름 수정
    - [ ] 수정 완료 후 세션 정보 갱신
    - [ ] 방명록 작성자명 수정
- [ ] 관리자 페이지
  - [ ] 회원 관리(권한 수정/삭제)
  - [ ] 상품 카테고리 관리
  - [ ] 문의하기 관리
  - [ ] 메인 페이지 관리?
- [x] 접근 거부 페이지
- [ ] 배포 시 (구글, 네이버, 카카오)
  - [ ] open api 서비스 URL 환경 수정
  - [ ] application-aouth.yml redirect-uri 수정
  - [ ] 사업자 정보 등록

---

## (finish) Review

- List
  - [x] 등록 버튼은 로그인 세션이 있는 사람만 보이도록
  - [x] 탈퇴한 사용자일 경우 nickname 표시
  - [x] 수정 버튼은 작성자 혹은 관리자에게만 표시
- Add
  - [x] 등록 페이지 이동 / 등록 시 세션 확인
  - [x] 등록 시 사용자 정보도 함께 저장되도록
  - [x] nickname 을 함께 저장
- Edit
  - [x] 로그인이 되어 있고, 리뷰 작성자 닉네임과 세션 이름이 같거나 관리자일 경우 수정 버튼 활성화
  - [x] 수정 페이지 이동 / 수정 / 삭제 시 세션 확인
  - [x] 저장 시 등록 사용자 ID 와 동일한지 확인해서 아니면 alert
- Delete
  - [x] 삭제 시 등록 사용자 ID 와 동일한지 확인해서 아니면 alert
- [x] 테스트 코드 작성
- [x] 방명록 남기면 관리자에게 메일 알림

---

## (finish) Product

- [x] 이미지를 로컬 디렉토리에서 가져오도록 설정
- List
  - [x] 테스트 데이터 생성
  - [x] 페이징 추가
  - [x] 카테고리 선택 / 페이지 번호 클릭 시 리스트 리로드
  - [x] 이미지 클릭 시 detail page 이동 (blog-single)
  - [x] 등록 버튼은 관리자에게만 표시
  - [x] 상품이 없을 경우 표시
- Detail
  - [x] 세션을 활용한 조회수 중복 방지 productId/ip
  - [x] 수정 버튼은 관리자에게만 표시
- Add
  - [x] 파일 저장
    - [x] dto 로 요청 받고, 미완성된 dto 완성 후 entity builder 후 저장 처리
  - [x] 등록 시 사용자 key id 도 함께 저장되도록
  - [x] 저장 버튼은 관리자에게만 표시
  - [x] 권한이 없을 시 권한 없음 팝업
  - [x] 저장 시 이미지를 특정 디렉토리에 저장
  - [x] thumbnailFile 썸네일 null 체크
- Edit
  - [x] 등록된 썸네일 표시
  - [x] 수정/삭제 버튼은 관리자에게만 표시
  - [x] 수정/삭제 권한이 없을 시 권한 없음 팝업
- New & Edit `POST`
  - [x] CKeditor Library
  - [x] 이미지 업로드
  - [x] 사진 수정 시, 기존 첨부 리스트와 삭제된 첨부 리스트 체크
  - [x] 접근 시 ADMIN 권한 체크 (서버단에서 ROLE 확인)
- [x] 관리자만 등록/수정/삭제 할 수 있도록 Controller 로그인 및 권한 설정


---

## (finish) Photos

- [x] Product 에 첨부된 모든 이미지 리스트
  - [x] dto 만들어서 상품 썸네일이랑 내용에 포함된 이미지 링크 가져오기
  - [x] Infinite Scrolling
  - [x] 카테고리 선택 시 리스트 동적 변환
    - [x] 카테고리가 변경되면 list 초기화

---

## (finish) Contact Us

- [x] 문의가 접수되면 메일(or 카카오톡)으로 알림

## Index

- Product
  - [ ] NEW ARRIVAL
    - [ ] 최신 상품 10가지 보여주기
    - [ ] 클릭 시 상품 설명 페이지로 이동
  - [ ] 더보기 하단에 더보기 링크

## 문의, 후기 알림 관련

- 카카오 메시지 REST API

  - https://developers.kakao.com/docs/latest/ko/message/rest-api#before-you-begin
	
- 메일 발송
  - https://data-make.tistory.com/666

- 네이버 Simple & Easy Notification Service
  - https://www.ncloud.com/product/applicationService/sens

  - API Doc 
    - https://guide.ncloud-docs.com/docs/sens-sens-1-5
    - https://guide-fin.ncloud-docs.com/docs/application-sens-sensalimtalk

- 참고
  - https://honeystorage.tistory.com/188?category=748845

- 예약기능 만들어서 사용자에게 모일 보낼 경우
  - [비즈엠](https://www.bizmsg.kr/)
  - [네이버 Simple & Easy Notification Service](https://www.ncloud.com/product/applicationService/sens)

## About

- html 로
- About 관리 페이지?

## ETC

**Jacoco Code Coverage 확인**

**database backup**

- DB 데이터 백업 스케쥴러?(https://server-talk.tistory.com/30)

**hosting**

- AWS EC2

**네이버 예약 기능**

> Reference

- https://bootstrapmade.com/sailor-free-bootstrap-theme/
- https://yhworkshop.com/

---

# Pending

## Post

블로그

```mysql
CREATE TABLE POST (
	POST_ID int(1) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    USER_ID int(1) NOT NULL,
    TITLE varchar(50) NOT NULL,
    CONTENTS varchar(MAX) NOT NULL,
    PERIOD DATETIME NOT NULL,
    VIEWS int(1) NOT NULL default 0,
    CATEGORY_ID int(1) NOT NULL, -- foreign key (P:C - N:1) @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="CATEGORY_ID")
    THUMBNAIL_ID Long NULL,
    CREATED_DATE_TIME DATETIME NOT NULL,
    MODIFIED_DATE_TIME DATETIME NULL
    -- commentsList
)

CREATE TABLE POST_THUMBNAIL (
    THUMBNAIL_ID int(1) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    POST_ID int(1) NOT NULL, -- foreign key (I:P - 1:1)
    ORIG_FILENAME varchar(100) NOT NULL,
    FILE_NAME varchar(100) NOT NULL,
    FILE_PATH varchar(100) NOT NULL,
    FILE_SIZE int(1) NOT NULL,
    CREATED_DATE_TIME DATETIME NOT NULL,
    MODIFIED_DATE_TIME DATETIME NULL
)

CREATE TABLE POST_CATEGORY (
    CATEGORY_ID int(1) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    TITLE varchar(20) NOT NULL,
    ORDER_NO int(1) NOT NULL,
    USE TINYINT(1) NOT NULL default 1
)

CREATE TABLE POST_TAG (
    SEQ int(1) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    POST_ID int(1) NOT NULL, -- foreign key (T:P - N:1)
    TAG varchar(200) NOT NULL
)
```

- List `GET`
  - 검색 (제목, 내용, 카테고리, 태그)
  - 카테고리별 조회
  - 최근 게시물 리스트
  - 인기 게시물 Top5 리스트
  - 인기 태그 Top10 리스트
  - paging
- View `GET`
  - admin 권한일 경우 수정 버튼 활성화
- New & Edit `POST`
  - CKeditor
  - 메인 이미지 파일 첨부 칸
  - 접근 시 ADMIN 권한 체크 (서버단에서 ROLE 확인)
    - @PreAuthorize("hasWildCardAnyRole('')")
    - @PreAuthorize("hasAnyRole(')")
  - 태그는`;`(세미콜론) 단위로 입력받고, 서버단에서 split & trim 후 저장
    - 또는 입력 칸 만들고 태그 영역에서 엔터 누르면 칸 추가되도록.
  - 수정 시 삭제 버튼 활성

## Comments

댓글

```sql
CREATE TABLE POST_COMMENTS (
    COMMENTS_ID int(1) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    POST_ID int(1) NOT NULL, -- @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name = "POST_ID")
   	NAME varchar(50) NOT NULL,
  	PASSWORD varchar(50) NOT NULL,
    CONTENTS varchar(MAX) NOT NULL,
    CREATED_DATE_TIME DATETIME NOT NULL,
    MODIFIED_DATE_TIME DATETIME NULL

    -- @OneToMany(mappedBy="postComments") private List<PostCommentsReply> postCommentsReplyList;
)

CREATE TABLE POST_COMMENTS_REPLY (
    COMMENTS_ID int(1) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    POST_COMMENTS_ID int(1) NOT NULL, -- @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="POST_COMMENTS_ID")
   	NAME varchar(50) NOT NULL,
  	PASSWORD varchar(50) NOT NULL,
    CONTENTS varchar(MAX) NOT NULL,
    CREATED_DATE_TIME DATETIME NOT NULL,
    MODIFIED_DATE_TIME DATETIME NULL
)
```

- Edit 버튼
  - 접근 시 작성한 비밀번호 입력
  - admin 권한의 경우 바로 접근
- Reply 버튼 클릭 시 Comment 내용에 @아이디 내용 써주고 forcous
  - 각 로우에 data-name, data-comments-id 정보가 필요할 듯
  - 저장 시 new와 reply 구분해서 저장 분리


```text
Pending

- [ ] Login `POST`
  - [ ] 아이디 저장 기능(쿠키)
  - [ ] setMaxInactiveInterval > 세션 설정(24시간)
  - [ ] 아이디 찾기(가입 시 작성한 이메일 입력 시 아이디 앞부분 보여주기)
  - [ ] 비밀번호 찾기 (가입 시 입력 이메일로 임시 PW 전송 및 DB는 임시 PW 로 수정)
  - [ ] admin 로그인 및 권한 관련 (Spring Security)

- [ ] Sign in `POST`
  - [ ] 비밀번호 숫자/영문 포함 10자 이상

- [ ] Edit (개인정보 변경) `POST`
  - [ ] 접근 시 로그인 세션 체크
  - [ ] 저장 시 현재 비밀번호 확인 (본인 확인 목적)
```
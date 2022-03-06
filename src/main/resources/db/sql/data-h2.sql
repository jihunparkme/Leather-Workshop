insert into notice (created_date_time, modified_date_time, contents, hits, user_Id, title)
values (PARSEDATETIME('2022-01-01 12:00:00','yyyy-MM-dd hh:mm:ss'), null, '내용이요1', 0L, 1L, '제목이요1');

insert into notice (created_date_time, modified_date_time, contents, hits, user_Id, title)
values (PARSEDATETIME('2022-01-02 12:00:00','yyyy-MM-dd hh:mm:ss'), null, '내용이요2', 55L, 1L, '제목이요2');

insert into notice (created_date_time, modified_date_time, contents, hits, user_Id, title)
values (PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), null, '내용이요3', 55L, 1L, '제목이요3');

insert into notice (created_date_time, modified_date_time, contents, hits, user_Id, title)
values (PARSEDATETIME('2022-01-04 12:00:00','yyyy-MM-dd hh:mm:ss'), null, '내용이요4', 55L, 1L, '제목이요4');

insert into notice (created_date_time, modified_date_time, contents, hits, user_Id, title)
values (PARSEDATETIME('2022-01-05 12:00:00','yyyy-MM-dd hh:mm:ss'), null, '내용이요5', 55L, 1L, '제목이요5');

insert into notice (created_date_time, modified_date_time, contents, hits, user_Id, title)
values (PARSEDATETIME('2022-01-06 12:00:00','yyyy-MM-dd hh:mm:ss'), null, '내용이요6', 55L, 1L, '제목이요6');

insert into notice (created_date_time, modified_date_time, contents, hits, user_Id, title)
values (PARSEDATETIME('2022-01-07 12:00:00','yyyy-MM-dd hh:mm:ss'), null, '내용이요7', 55L, 1L, '제목이요7');

insert into notice (created_date_time, modified_date_time, contents, hits, user_Id, title)
values (PARSEDATETIME('2022-01-08 12:00:00','yyyy-MM-dd hh:mm:ss'), null, '내용이요8', 55L, 1L, '제목이요8');

insert into notice (created_date_time, modified_date_time, contents, hits, user_Id, title)
values (PARSEDATETIME('2022-01-09 12:00:00','yyyy-MM-dd hh:mm:ss'), null, '내용이요9', 55L, 1L, '제목이요9');

insert into notice (created_date_time, modified_date_time, contents, hits, user_Id, title)
values (PARSEDATETIME('2022-01-10 12:00:00','yyyy-MM-dd hh:mm:ss'), null, '내용이요10', 55L, 1L, '제목이요10');

insert into notice (created_date_time, modified_date_time, contents, hits, user_Id, title)
values (PARSEDATETIME('2022-01-11 12:00:00','yyyy-MM-dd hh:mm:ss'), null, '내용이요11', 55L, 1L, '제목이요11');

insert into notice (created_date_time, modified_date_time, contents, hits, user_Id, title)
values (PARSEDATETIME('2022-01-01 12:00:00','yyyy-MM-dd hh:mm:ss'), null, '내용이요12', 55L, 1L, '제목이요12');




insert into user (name, email, picture, role, created_date_time)
values ('Cristoval', 'ccc@naver.com', '', 'ADMIN', PARSEDATETIME('2022-01-01 12:00:00','yyyy-MM-dd hh:mm:ss'));

insert into user (name, email, picture, role, created_date_time)
values ('Park', 'aaa@naver.com', '', 'USER', PARSEDATETIME('2022-01-01 12:00:00','yyyy-MM-dd hh:mm:ss'));

insert into user (name, email, picture, role, created_date_time)
values ('JiHun', 'bbb@naver.com', '', 'USER', PARSEDATETIME('2022-01-01 12:00:00','yyyy-MM-dd hh:mm:ss'));

insert into user (name, email, picture, role, created_date_time)
values ('Aaron', 'plk4623@naver.com', 'https://ssl.pstatic.net/static/pwe/address/img_profile.png', 'ADMIN', PARSEDATETIME('2022-01-01 12:00:00','yyyy-MM-dd hh:mm:ss'));





insert into review (created_date_time, modified_date_time, contents, user_Id)
values (PARSEDATETIME('2022-01-01 12:00:00','yyyy-MM-dd hh:mm:ss'), null, '내용입니다.1', 1L);

insert into review (created_date_time, modified_date_time, contents, user_Id)
values (PARSEDATETIME('2022-01-01 12:00:00','yyyy-MM-dd hh:mm:ss'), null, '내용입니다.2222', 2L);

insert into review (created_date_time, modified_date_time, contents, user_Id)
values (PARSEDATETIME('2022-01-01 12:00:00','yyyy-MM-dd hh:mm:ss'), null, '내용입니다.33333', 3L);



insert into PRODUCT_CATEGORY (title, order_no, category_use_yn)
values ('APP', 1, 'Y');

insert into PRODUCT_CATEGORY (title, order_no, category_use_yn)
values ('CARD', 2, 'Y');

insert into PRODUCT_CATEGORY (title, order_no, category_use_yn)
values ('WEB', 3, 'Y');

insert into PRODUCT_CATEGORY (title, order_no, category_use_yn)
values ('WALLET', 4, 'Y');




insert into PRODUCT (product_category_id, name, contents, hits, delete_yn, deleted_date_time, created_date_time, user_id, modified_date_time)
values (4, '멋진 지갑', '멋진 지갑입니다.', 10L, 'N', NULL, PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), 1L, NULL);





insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (1L, 'abc2aa4c.jpg', 'portfolio-2.jpg', 'Y', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (1L, 'abc2cc4c.jpg', 'portfolio-1.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (1L, 'abcavsd.jpg', 'portfolio-1.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);
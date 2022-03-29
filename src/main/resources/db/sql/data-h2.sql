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
values ('BAG', 3, 'Y');

insert into PRODUCT_CATEGORY (title, order_no, category_use_yn)
values ('CARD', 2, 'Y');

insert into PRODUCT_CATEGORY (title, order_no, category_use_yn)
values ('ETC', 100, 'Y');

insert into PRODUCT_CATEGORY (title, order_no, category_use_yn)
values ('WALLET', 1, 'Y');

insert into PRODUCT_CATEGORY (title, order_no, category_use_yn)
values ('ACCESSORY', 4, 'Y');



insert into PRODUCT (product_category_id, name, contents, hits, delete_yn, deleted_date_time, created_date_time, user_id, modified_date_time)
values (1, '화분', '화분입니다.', 10L, 'N', NULL, PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), 1L, NULL);

insert into PRODUCT (product_category_id, name, contents, hits, delete_yn, deleted_date_time, created_date_time, user_id, modified_date_time)
values (1, '의자', '의자입니다.', 10L, 'N', NULL, PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), 1L, NULL);

insert into PRODUCT (product_category_id, name, contents, hits, delete_yn, deleted_date_time, created_date_time, user_id, modified_date_time)
values (2, '썬글라스', '썬글라스입니다.', 10L, 'N', NULL, PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), 1L, NULL);

insert into PRODUCT (product_category_id, name, contents, hits, delete_yn, deleted_date_time, created_date_time, user_id, modified_date_time)
values (4, '멋진 지갑', '멋진 지갑입니다.', 10L, 'N', NULL, PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), 1L, NULL);

insert into PRODUCT (product_category_id, name, contents, hits, delete_yn, deleted_date_time, created_date_time, user_id, modified_date_time)
values (1, '화장품', '화장품 같은 물건입니다.', 10L, 'N', NULL, PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), 1L, NULL);

insert into PRODUCT (product_category_id, name, contents, hits, delete_yn, deleted_date_time, created_date_time, user_id, modified_date_time)
values (3, '쿠키와 커피', '아침에 커피에 쿠키 어때요?.', 10L, 'N', NULL, PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), 1L, NULL);

insert into PRODUCT (product_category_id, name, contents, hits, delete_yn, deleted_date_time, created_date_time, user_id, modified_date_time)
values (3, '스탠드', '스탠드입니다.', 10L, 'N', NULL, PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), 1L, NULL);

insert into PRODUCT (product_category_id, name, contents, hits, delete_yn, deleted_date_time, created_date_time, user_id, modified_date_time)
values (3, '손목시계', '손목시계입니다.', 10L, 'N', NULL, PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), 1L, NULL);

insert into PRODUCT (product_category_id, name, contents, hits, delete_yn, deleted_date_time, created_date_time, user_id, modified_date_time)
values (2, '렌즈', '카메라 렌즈입니다.', 10L, 'N', NULL, PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), 1L, NULL);

insert into PRODUCT (product_category_id, name, contents, hits, delete_yn, deleted_date_time, created_date_time, user_id, modified_date_time)
values (2, '맥주잔', '맥주가 떙기죠?.', 10L, 'N', NULL, PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), 1L, NULL);

insert into PRODUCT (product_category_id, name, contents, hits, delete_yn, deleted_date_time, created_date_time, user_id, modified_date_time)
values (1, '화분', '화분입니다.', 10L, 'N', NULL, PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), 1L, NULL);

insert into PRODUCT (product_category_id, name, contents, hits, delete_yn, deleted_date_time, created_date_time, user_id, modified_date_time)
values (1, '의자', '의자입니다.', 10L, 'N', NULL, PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), 1L, NULL);

insert into PRODUCT (product_category_id, name, contents, hits, delete_yn, deleted_date_time, created_date_time, user_id, modified_date_time)
values (2, '썬글라스', '썬글라스입니다.', 10L, 'N', NULL, PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), 1L, NULL);

insert into PRODUCT (product_category_id, name, contents, hits, delete_yn, deleted_date_time, created_date_time, user_id, modified_date_time)
values (4, '멋진 지갑', '멋진 지갑입니다.', 10L, 'N', NULL, PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), 1L, NULL);

insert into PRODUCT (product_category_id, name, contents, hits, delete_yn, deleted_date_time, created_date_time, user_id, modified_date_time)
values (1, '화장품', '화장품 같은 물건입니다.', 10L, 'N', NULL, PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), 1L, NULL);

insert into PRODUCT (product_category_id, name, contents, hits, delete_yn, deleted_date_time, created_date_time, user_id, modified_date_time)
values (3, '쿠키와 커피', '아침에 커피에 쿠키 어때요?.', 10L, 'N', NULL, PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), 1L, NULL);

insert into PRODUCT (product_category_id, name, contents, hits, delete_yn, deleted_date_time, created_date_time, user_id, modified_date_time)
values (3, '스탠드', '스탠드입니다.', 10L, 'N', NULL, PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), 1L, NULL);

insert into PRODUCT (product_category_id, name, contents, hits, delete_yn, deleted_date_time, created_date_time, user_id, modified_date_time)
values (3, '손목시계', '<p>ㅇㅇㅇ</p>

<p>ㅇㅇ</p>

<p><img alt="" src="/file/ckeditor/fileDownload/product?fileName=b47f264a-322e-4eb4-9202-4d2528f2d57a.png" style="height:128px; width:438px" /></p>

<p><img alt="" src="/file/ckeditor/fileDownload/product?fileName=df5c0701-7e8b-46c5-8d89-fb10bb1a72cf.png" style="height:128px; width:438px" /></p>

<p><img alt="" src="/file/ckeditor/fileDownload/product?fileName=e858f71b-a628-4241-a76c-88e478e39f48.png" style="height:128px; width:438px" /></p>

<p><img alt="" src="/file/ckeditor/fileDownload/product?fileName=b47f264a-322e-4eb4-9202-4d2528f2d57a.png" style="height:128px; width:438px" /></p>
', 10L, 'N', NULL, PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), 1L, NULL);




insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (1L, 'abcdefghijk1.jpg', 'portfolio-2.jpg', 'Y', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (1L, 'abcdefghijk2.jpg', 'portfolio-details-1.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (1L, 'abcdefghijk3.jpg', 'portfolio-details-2.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (1L, 'abcdefghijk4.jpg', 'portfolio-details-3.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (2L, 'abcdefghijk5.jpg', 'portfolio-1.jpg', 'Y', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (2L, 'abcdefghijk6.jpg', 'portfolio-details-1.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (2L, 'abcdefghijk7.jpg', 'portfolio-details-2.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (2L, 'abcdefghijk8.jpg', 'portfolio-details-3.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (3L, 'abcdefghijk9.jpg', 'portfolio-3.jpg', 'Y', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (3L, 'abcdefghijk10.jpg', 'portfolio-details-1.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (3L, 'abcdefghijk11.jpg', 'portfolio-details-2.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (3L, 'abcdefghijk12.jpg', 'portfolio-details-3.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (4L, 'abcdefghijk13.jpg', 'portfolio-6.jpg', 'Y', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (4L, 'abcdefghijk14.jpg', 'portfolio-details-1.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (4L, 'abcdefghijk15.jpg', 'portfolio-details-2.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (4L, 'abcdefghijk16.jpg', 'portfolio-details-3.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (5L, 'abcdefghijk17.jpg', 'portfolio-4.jpg', 'Y', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (5L, 'abcdefghijk18.jpg', 'portfolio-details-1.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (5L, 'abcdefghijk19.jpg', 'portfolio-details-2.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (5L, 'abcdefghijk20.jpg', 'portfolio-details-3.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (6L, 'abcdefghijk21.jpg', 'portfolio-7.jpg', 'Y', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (6L, 'abcdefghijk22.jpg', 'portfolio-details-1.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (6L, 'abcdefghijk23.jpg', 'portfolio-details-2.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (6L, 'abcdefghijk24.jpg', 'portfolio-details-3.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (7L, 'abcdefghijk25.jpg', 'portfolio-8.jpg', 'Y', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (7L, 'abcdefghijk26.jpg', 'portfolio-details-1.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (7L, 'abcdefghijk27.jpg', 'portfolio-details-2.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (7L, 'abcdefghijk28.jpg', 'portfolio-details-3.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (8L, 'abcdefghijk29.jpg', 'portfolio-2.jpg', 'Y', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (8L, 'abcdefghijk30.jpg', 'portfolio-details-1.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (8L, 'abcdefghijk31.jpg', 'portfolio-details-2.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (8L, 'abcdefghijk32.jpg', 'portfolio-details-3.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (9L, 'abcdefghijk33.jpg', 'portfolio-5.jpg', 'Y', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (9L, 'abcdefghijk34.jpg', 'portfolio-details-1.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (9L, 'abcdefghijk35.jpg', 'portfolio-details-2.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (9L, 'abcdefghijk36.jpg', 'portfolio-details-3.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (10L, 'abcdefghijk37.jpg', 'portfolio-9.jpg', 'Y', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (10L, 'abcdefghijk38.jpg', 'portfolio-details-1.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (10L, 'abcdefghijk39.jpg', 'portfolio-details-2.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (10L, 'abcdefghijk40.jpg', 'portfolio-details-3.jpg', 'N', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (11L, 'abcdefghijk37.jpg', 'portfolio-1.jpg', 'Y', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (12L, 'abcdefghijk37.jpg', 'portfolio-2.jpg', 'Y', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (13L, 'abcdefghijk37.jpg', 'portfolio-3.jpg', 'Y', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (14L, 'abcdefghijk37.jpg', 'portfolio-4.jpg', 'Y', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (15L, 'abcdefghijk37.jpg', 'portfolio-5.jpg', 'Y', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (16L, 'abcdefghijk37.jpg', 'portfolio-6.jpg', 'Y', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (17L, 'abcdefghijk37.jpg', 'portfolio-7.jpg', 'Y', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);

insert into PRODUCT_UPLOAD_FILE (product_id, upload_file_name, store_file_name, thumbnail_yn, created_date_time, modified_date_time)
values (18L, 'abcdefghijk37.jpg', 'portfolio-8.jpg', 'Y', PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), NULL);
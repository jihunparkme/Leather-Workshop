insert into notice (created_date_time, modified_date_time, contents, hits, user_Id, title)
values (PARSEDATETIME('2022-01-01 12:00:00','yyyy-MM-dd hh:mm:ss'), null, '내용이요1', 0, 1L, '제목이요1');

insert into notice (created_date_time, modified_date_time, contents, hits, user_Id, title)
values (PARSEDATETIME('2022-01-02 12:00:00','yyyy-MM-dd hh:mm:ss'), null, '내용이요2', 55, 1L, '제목이요2');

insert into notice (created_date_time, modified_date_time, contents, hits, user_Id, title)
values (PARSEDATETIME('2022-01-03 12:00:00','yyyy-MM-dd hh:mm:ss'), null, '내용이요3', 55, 1L, '제목이요3');

insert into notice (created_date_time, modified_date_time, contents, hits, user_Id, title)
values (PARSEDATETIME('2022-01-04 12:00:00','yyyy-MM-dd hh:mm:ss'), null, '내용이요4', 55, 1L, '제목이요4');

insert into notice (created_date_time, modified_date_time, contents, hits, user_Id, title)
values (PARSEDATETIME('2022-01-05 12:00:00','yyyy-MM-dd hh:mm:ss'), null, '내용이요5', 55, 1L, '제목이요5');

insert into notice (created_date_time, modified_date_time, contents, hits, user_Id, title)
values (PARSEDATETIME('2022-01-06 12:00:00','yyyy-MM-dd hh:mm:ss'), null, '내용이요6', 55, 1L, '제목이요6');

insert into notice (created_date_time, modified_date_time, contents, hits, user_Id, title)
values (PARSEDATETIME('2022-01-07 12:00:00','yyyy-MM-dd hh:mm:ss'), null, '내용이요7', 55, 1L, '제목이요7');

insert into notice (created_date_time, modified_date_time, contents, hits, user_Id, title)
values (PARSEDATETIME('2022-01-08 12:00:00','yyyy-MM-dd hh:mm:ss'), null, '내용이요8', 55, 1L, '제목이요8');

insert into notice (created_date_time, modified_date_time, contents, hits, user_Id, title)
values (PARSEDATETIME('2022-01-09 12:00:00','yyyy-MM-dd hh:mm:ss'), null, '내용이요9', 55, 1L, '제목이요9');

insert into notice (created_date_time, modified_date_time, contents, hits, user_Id, title)
values (PARSEDATETIME('2022-01-10 12:00:00','yyyy-MM-dd hh:mm:ss'), null, '내용이요10', 55, 1L, '제목이요10');

insert into notice (created_date_time, modified_date_time, contents, hits, user_Id, title)
values (PARSEDATETIME('2022-01-11 12:00:00','yyyy-MM-dd hh:mm:ss'), null, '내용이요11', 55, 1L, '제목이요11');

insert into notice (created_date_time, modified_date_time, contents, hits, user_Id, title)
values (PARSEDATETIME('2022-01-01 12:00:00','yyyy-MM-dd hh:mm:ss'), null, '내용이요12', 55, 1L, '제목이요12');


insert into user (name, email, picture, role, created_date_time)
values ('Aaron', 'ccc@naver.com', 'https://ssl.pstatic.net/static/pwe/address/img_profile.png', 'ADMIN', PARSEDATETIME('2022-01-01 12:00:00','yyyy-MM-dd hh:mm:ss'));

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
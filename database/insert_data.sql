use postgres;
-- Pass 123456
-- Ma hoa MD5
insert into role(code,name) values('ADMIN','quan tri');
insert into user(username,password,fullname,status,role_id)
values('admin','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','dao tan hai','1','1');
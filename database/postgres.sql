
INSERT INTO public.role(code,name)
VALUES ('ADMIN','quan tri');

INSERT INTO public."User"(
    username,password,fullname,status,role_id)
VALUES ('admin','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','dao tan hai','1','1');
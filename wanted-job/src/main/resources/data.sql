INSERT INTO companys(name, nation, region) values ('원티드랩', '한국', '서울');

INSERT INTO users(name, email)
    SELECT '김부희', 'buhee1029@gmail.com' FROM DUAL
    WHERE NOT EXISTS (SELECT email FROM users WHERE email = 'buhee1029@gmail.com');


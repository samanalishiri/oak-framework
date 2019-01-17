insert into user_info (ID,
                       USERNAME,
                       PASSWD,
                       EMAIL,
                       NON_EXPIRED,
                       NON_LOCKED,
                       PASSWORD_NON_EXPIRED,
                       ENABLED,
                       NAME,
                       SURNAME,
                       NATIONAL_CODE,
                       BIRTH_DATE,
                       BIRTH_PLACE,
                       ID_CARD_NO,
                       ID_CARD_SERIAL)
values (1,
        'user',
        '$2a$12$FoEYfy0JoyQzXudv5jF7SOKXFV4VlFkThFWeqRuuOkn8f40xskXhq',
        'samanalishiri@gmail.com',
        true,
        true,
        true,
        true,
        'Saman',
        'Alishiri',
        '2300588951',
        '1987-8-29',
        'Shiraz',
        '3542',
        '156849');


insert into authority (
ID,
AUTHORITY,
DESCRIPTION,
ENABLED)
values (
1,
'admin',
'admin role',
true );

insert into user_info_authority(
user_info_id, authority_id)
values(
1,1);


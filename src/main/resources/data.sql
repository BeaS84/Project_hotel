INSERT INTO user_model (name, surname, email, password,user_type) VALUES
                                                                      ('admin i user','admin i user','au@admin.com','$2a$12$AfTOTFJLe/4McVlWWKwt.Ozn6sP4vMCNsg07O71BRwNR.HxmbzVQ.','admin'),
                                                                      ('Anna','user','user@user.com','$2a$12$AfTOTFJLe/4McVlWWKwt.Ozn6sP4vMCNsg07O71BRwNR.HxmbzVQ.','client'),
                                                                      ('Judyta','user','user1@user.com','$2a$12$AfTOTFJLe/4McVlWWKwt.Ozn6sP4vMCNsg07O71BRwNR.HxmbzVQ.','client'),
                                                                      ('Bea','user','user2@user.com','$2a$12$AfTOTFJLe/4McVlWWKwt.Ozn6sP4vMCNsg07O71BRwNR.HxmbzVQ.','client'),
                                                                      ('Gosia','user','user3@user.com','$2a$12$AfTOTFJLe/4McVlWWKwt.Ozn6sP4vMCNsg07O71BRwNR.HxmbzVQ.','client'),
                                                                      ('Admin','Admin','a@admin.com','$2a$12$AfTOTFJLe/4McVlWWKwt.Ozn6sP4vMCNsg07O71BRwNR.HxmbzVQ.','admin');


INSERT INTO role_model (name) VALUES ('ROLE_ADMIN'),('ROLE_USER');
INSERT INTO user_x_roles VALUES (1,1),(2,2);
INSERT INTO user_x_roles VALUES (3,2);
INSERT INTO user_x_roles VALUES (4,2);
INSERT INTO user_x_roles VALUES (5,2);
INSERT INTO user_x_roles VALUES (6,1);

INSERT INTO animal_model (type,name,sex,weight,client_id) VALUES ('DOG','Tofi','GIRL','MEDIUM',2);
INSERT INTO animal_model (type,name,sex,weight,client_id) VALUES ('CAT','Mruczek','BOY','MEDIUM',2);


INSERT INTO animal_model (type,name,sex,weight,client_id) VALUES ('DOG','Pika','GIRL','SMALL', 3);
INSERT INTO animal_model (type,name,sex,weight,client_id) VALUES ('DOG','Borys','BOY','MEDIUM',3);
INSERT INTO animal_model (type,name,sex,weight,client_id) VALUES ('DOG','Frania','GIRL','SMALL',3);

INSERT INTO animal_model (type,name,sex,weight,client_id) VALUES ('DOG','SONIA','GIRL','LARGE',4);
INSERT INTO animal_model (type,name,sex,weight,client_id) VALUES ('DOG','Denver','BOY','MEDIUM',4);
INSERT INTO animal_model (type,name,sex,weight,client_id) VALUES ('CAT','Kocurro','BOY','SMALL',4);

INSERT INTO animal_model (type,name,sex,weight,client_id) VALUES ('DOG','Dżek','BOY','LARGE',5);
INSERT INTO animal_model (type,name,sex,weight,client_id) VALUES ('CAT','Zara','GIRL','SMALL',5);

-- INSERT INTO animal_model (type,name,sex,weight,client_id) VALUES ('DOG','Psinka','GIRL','LARGE',5);


INSERT INTO room_model(name, standard, cost_per_night) VALUES
    ('bialy','BASIC', '10000');

-- INSERT INTO animal_size(room_id, animal_size)VALUES(1,'SMALL');
-- INSERT INTO animal_size(room_id, animal_size)VALUES(1,'MEDIUM');
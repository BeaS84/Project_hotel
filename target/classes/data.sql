INSERT INTO user_model (name, surname, email, password,user_type) VALUES
    ('admin i user','admin i user','au@admin.com','$2a$12$AfTOTFJLe/4McVlWWKwt.Ozn6sP4vMCNsg07O71BRwNR.HxmbzVQ.','admin'),
    ('Anna','user','user@user.com','$2a$12$AfTOTFJLe/4McVlWWKwt.Ozn6sP4vMCNsg07O71BRwNR.HxmbzVQ.','client'),
    ('Basia','user1','user1@user.com','$2a$12$AfTOTFJLe/4McVlWWKwt.Ozn6sP4vMCNsg07O71BRwNR.HxmbzVQ.','client'),
    ('Admin','Admin','a@admin.com','$2a$12$AfTOTFJLe/4McVlWWKwt.Ozn6sP4vMCNsg07O71BRwNR.HxmbzVQ.','admin'),
('Adam','Admin','admin@gmail11111.com','$2a$12$AfTOTFJLe/4McVlWWKwt.Ozn6sP4vMCNsg07O71BRwNR.HxmbzVQ.','admin');


INSERT INTO role_model (name) VALUES ('ROLE_ADMIN'),('ROLE_USER');
INSERT INTO user_x_roles VALUES (1,1),(2,2),(3,1);
INSERT INTO user_x_roles VALUES (1,2);

INSERT INTO animal_model (type,name,sex,client_id) VALUES ('DOG','Pika','GIRL',3);
INSERT INTO animal_model (type,name,sex,weight,client_id) VALUES ('DOG','Borys','BOY','MEDIUM',2);
INSERT INTO animal_model (type,name,client_id) VALUES ('DOG','Frania',2);


INSERT INTO room_model(name, standard) VALUES
                                             ('bialy','BASIC');

-- INSERT INTO animal_size(room_id, animal_size)VALUES(1,'SMALL');
-- INSERT INTO animal_size(room_id, animal_size)VALUES(1,'MEDIUM');



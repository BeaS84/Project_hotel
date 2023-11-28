INSERT INTO user_model (name, surname, email, password) VALUES
    ('Adam','Admin','admin@admin.com','$2a$12$AfTOTFJLe/4McVlWWKwt.Ozn6sP4vMCNsg07O71BRwNR.HxmbzVQ.'),
    ('Anna','user','user@user.com','$2a$12$AfTOTFJLe/4McVlWWKwt.Ozn6sP4vMCNsg07O71BRwNR.HxmbzVQ.'),
    ('Adam','Admin','test@gmail11.com','$2a$12$AfTOTFJLe/4McVlWWKwt.Ozn6sP4vMCNsg07O71BRwNR.HxmbzVQ.'),
('Adam','Admin','admin@gmail11111.com','$2a$12$AfTOTFJLe/4McVlWWKwt.Ozn6sP4vMCNsg07O71BRwNR.HxmbzVQ.');


INSERT INTO role_model (name) VALUES ('ROLE_ADMIN'),('ROLE_USER');
INSERT INTO user_x_roles VALUES (1,1),(2,2),(3,1);
INSERT INTO user_x_roles VALUES (1,2);

INSERT INTO room_model(name, standard) VALUES
                                             ('bialy','BASIC');

-- INSERT INTO animal_size(room_id, animal_size)VALUES(1,'SMALL');
-- INSERT INTO animal_size(room_id, animal_size)VALUES(1,'MEDIUM');



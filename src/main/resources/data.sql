INSERT INTO user_model (name, surname, email, password, birthdate) VALUES
    ('Adam','Admin','admin@gmail11.com','$2a$12$AfTOTFJLe/4McVlWWKwt.Ozn6sP4vMCNsg07O71BRwNR.HxmbzVQ.','2000-10-19'),
    ('Adam','Admin','user@gmail11.com','$2a$12$AfTOTFJLe/4McVlWWKwt.Ozn6sP4vMCNsg07O71BRwNR.HxmbzVQ.','2000-10-19'),
    ('Adam','Admin','test@gmail11.com','$2a$12$AfTOTFJLe/4McVlWWKwt.Ozn6sP4vMCNsg07O71BRwNR.HxmbzVQ.','2000-10-19'),
('Adam','Admin','admin@gmail11111.com','$2a$12$AfTOTFJLe/4McVlWWKwt.Ozn6sP4vMCNsg07O71BRwNR.HxmbzVQ.','2000-10-19');


INSERT INTO role_model (name) VALUES ('ROLE_ADMIN'),('ROLE_USER');
INSERT INTO user_x_roles VALUES (1,1),(2,2),(3,1);
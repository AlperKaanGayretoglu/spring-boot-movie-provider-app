-- User Data (admin pass is 123456789)
INSERT INTO users (id, email, name, surname, password, role, verified)
VALUES ('5b8a3d25-2b7a-4683-89ed-ac0e42cdc879', 'alperkaangayretoglu@gmail.com', 'Alper', 'Gayretoğlu',
        '$2a$10$OU9/JC80FudcjfuAj1.X5OopuhG6Trs3JHQEOHdH.Xnm7VSIpW0OC', 'ADMIN', true);

INSERT INTO users (id, email, name, surname, password, role, verified)
VALUES ('6b8a3d25-2b7a-4683-89ed-ac0e42cdc878', 'guest@mail.com', 'Guest', 'Guest',
        '$2a$10$OU9/JC80FudcjfuAj1.X5OopuhG6Trs3JHQEOHdH.Xnm7VSIpW0OC', 'GUEST', true);

-- Subscription Data
INSERT INTO subscription (id, duration, is_active, monthly_fee, name)
VALUES ('11b455da-715a-4dc1-b4f1-b526c1c9ab4e', 1, true, 100, 'GOLD');

INSERT INTO subscription (id, duration, is_active, monthly_fee, name)
VALUES ('33b455da-335a-4dc1-b4f1-b526c1c9ab4e', 3, true, 90, 'PLATINUM');

INSERT INTO subscription (id, duration, is_active, monthly_fee, name)
VALUES ('66b455da-665a-4dc1-b4f1-b526c1c9ab4e', 6, true, 80, 'DIAMOND');


-- Category Data
INSERT INTO category (id, name, parent_id, is_super_category)
VALUES ('10b455da-7e5a-4dc3-b4f5-b526c1c9ab4e', 'SUPER CATEGORY', null, true);

INSERT INTO category (id, name, parent_id, is_super_category)
VALUES ('66403305-972b-42b1-a71a-d7bb2828eebe', 'HORROR', '10b455da-7e5a-4dc3-b4f5-b526c1c9ab4e', false);

INSERT INTO category (id, name, parent_id, is_super_category)
VALUES ('50a5fc87-4cbe-4b50-ac5a-acdd90bbfbf4', 'ACTION', '10b455da-7e5a-4dc3-b4f5-b526c1c9ab4e', false);


-- Movie Data
INSERT INTO movie (id, title)
VALUES ('b4dceb23-d2ea-4432-aa7a-c71b4b15bcee', 'The Walking Dead');

INSERT INTO movie (id, title)
VALUES ('cbf27a05-9abe-40c0-a943-ede62f9ca3de', 'John Wick');

INSERT INTO movie (id, title)
VALUES ('421450be-91e2-4184-8450-3dcc12a33e63', 'Matrix');


-- category_movies join table
INSERT INTO category_movies (categories_id, movies_id)
VALUES ('10b455da-7e5a-4dc3-b4f5-b526c1c9ab4e',
        'b4dceb23-d2ea-4432-aa7a-c71b4b15bcee'); -- super category : The Walking Dead

INSERT INTO category_movies (categories_id, movies_id)
VALUES ('10b455da-7e5a-4dc3-b4f5-b526c1c9ab4e', 'cbf27a05-9abe-40c0-a943-ede62f9ca3de'); -- super category : John Wick

INSERT INTO category_movies (categories_id, movies_id)
VALUES ('10b455da-7e5a-4dc3-b4f5-b526c1c9ab4e', '421450be-91e2-4184-8450-3dcc12a33e63'); -- super category : Matrix

INSERT INTO category_movies (categories_id, movies_id)
VALUES ('66403305-972b-42b1-a71a-d7bb2828eebe', 'b4dceb23-d2ea-4432-aa7a-c71b4b15bcee');
-- horror : The Walking Dead


-- User Interest : favorite movies : users_movies join table
INSERT INTO users_movies (user_id, movie_id)
VALUES ('5b8a3d25-2b7a-4683-89ed-ac0e42cdc879',
        'b4dceb23-d2ea-4432-aa7a-c71b4b15bcee'); -- admin favorite The Walking Dead

INSERT INTO users_movies (user_id, movie_id)
VALUES ('5b8a3d25-2b7a-4683-89ed-ac0e42cdc879', 'cbf27a05-9abe-40c0-a943-ede62f9ca3de');
-- admin favorites nfs mw


-- User Interest : following categories : users_categories join table
INSERT INTO users_categories (user_id, categories_id)
VALUES ('5b8a3d25-2b7a-4683-89ed-ac0e42cdc879', '66403305-972b-42b1-a71a-d7bb2828eebe'); -- admin follows HORROR

INSERT INTO users_categories (user_id, categories_id)
VALUES ('5b8a3d25-2b7a-4683-89ed-ac0e42cdc879', '50a5fc87-4cbe-4b50-ac5a-acdd90bbfbf4'); -- admin follows ACTION

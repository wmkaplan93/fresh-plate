BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	security_question_id int NOT NULL,
	answer varchar(50),
	CONSTRAINT PK_user PRIMARY KEY (user_id),
	CONSTRAINT fk_security_question_id FOREIGN KEY (security_question_id) REFERENCES security_questions(security_question_id)
);


INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');




CREATE TABLE ingredients (
        ingredient_id    serial CONSTRAINT pk_ingredient_id PRIMARY KEY,
        ingredient_name  varchar(100) NOT NULL UNIQUE
);

CREATE TABLE units_of_measure (
        unit_id         serial CONSTRAINT pk_unit_of_measure PRIMARY KEY,
        unit_name       varchar(50) NOT NULL UNIQUE
);

CREATE TABLE types (
        type_id         serial CONSTRAINT pk_recipe_type_id PRIMARY KEY,
        type_name       varchar(100) NOT NULL UNIQUE
);

CREATE TABLE recipes (
        recipe_id       serial CONSTRAINT pk_recipe_id PRIMARY KEY,
        recipe_name     varchar(200) NOT NULL,
        description     varchar(500),
        yield_amount    int NOT NULL,
        yield_unit_id   int NOT NULL,
        duration        varchar(100) NOT NULL,
        recipe_method   varchar NOT NULL,
        is_public       boolean NOT NULL,
        ownername       varchar NOT NULL,
        CONSTRAINT fk_yield_unit_id FOREIGN KEY (yield_unit_id) REFERENCES units_of_measure(unit_id),
        CONSTRAINT fk_ownername FOREIGN KEY (ownername) REFERENCES users (username)
);

CREATE TABLE recipe_types (
        recipe_id       int NOT NULL,
        type_id         int NOT NULL,
        CONSTRAINT fk_type_id FOREIGN KEY (type_id) REFERENCES types(type_id),
        CONSTRAINT fk_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id)
);
        

CREATE TABLE recipe_ingredients (
        recipe_id       int NOT NULL,
        quantity        numeric(20, 4) NOT NULL,
        unit_id         int NOT NULL,
        ingredient_id   int NOT NULL,
        CONSTRAINT fk_unit_id FOREIGN KEY (unit_id) REFERENCES units_of_measure(unit_id),
        CONSTRAINT fk_ingredient_id FOREIGN KEY (ingredient_id) REFERENCES ingredients(ingredient_id),
        CONSTRAINT fk_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id)
);

CREATE TABLE user_recipes (
        username        varchar NOT NULL,
        recipe_id       int NOT NULL,
        is_favorite     boolean NOT NULL,
        CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users(username),
        CONSTRAINT fk_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id)
);

CREATE TABLE meal_plans(
        plan_id                 serial CONSTRAINT pk_plan_id PRIMARY KEY,
        username                varchar NOT NULL,
        plan_name               varchar(100) NOT NULL,
        plan_description        varchar(500) NOT NULL,
        CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username)
);

CREATE TABLE plan_recipes(
        plan_id         int NOT NULL,
        recipe_id       int NOT NULL,
        CONSTRAINT fk_plan_id FOREIGN KEY (plan_id) REFERENCES meal_plans (plan_id),
        CONSTRAINT fk_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id)
);

CREATE TABLE security_questions(
        security_question_id    int NOT NULL,
        security_questions      varchar(100)
);


COMMIT TRANSACTION;

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
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');




CREATE TABLE ingredients (
        ingredient_id    serial CONSTRAINT pk_ingredient_id PRIMARY KEY,
        ingredient_name  varchar(100) NOT NULL
);

CREATE TABLE units_of_measure (
        unit_id         serial CONSTRAINT pk_unit_of_measure PRIMARY KEY,
        unit_name       varchar(50) NOT NULL
);

CREATE TABLE types (
        type_id  serial CONSTRAINT pk_recipe_type_id PRIMARY KEY,
        type     varchar(100) NOT NULL
);

CREATE TABLE recipes (
        recipe_id       serial CONSTRAINT pk_recipe_id PRIMARY KEY,
        name            varchar(200) NOT NULL,
        description     varchar(500),
        yield           int NOT NULL,
        unit_id         int NOT NULL,
        duration        varchar(100) NOT NULL,
        recipe_method   varchar NOT NULL,
        is_public       boolean NOT NULL,
        CONSTRAINT fk_unit_id FOREIGN KEY (unit_id) REFERENCES units_of_measure(unit_id)
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
        user_id         int NOT NULL,
        recipe_id       int NOT NULL,
        is_favorite     boolean NOT NULL,
        CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
        CONSTRAINT fk_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id)
);

ALTER TABLE ingredients ADD CONSTRAINT unique_ingredient UNIQUE (ingredient_name);
ALTER TABLE types ADD CONSTRAINT unique_type UNIQUE (type);
ALTER TABLE units_of_measure ADD CONSTRAINT unique_units UNIQUE (unit_name);

COMMIT TRANSACTION;

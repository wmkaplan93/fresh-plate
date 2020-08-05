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

CREATE TABLE user_recipe (
        user_id         int NOT NULL,
        recipe_id       int NOT NULL,
        CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
        CONSTRAINT fk_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id)
);




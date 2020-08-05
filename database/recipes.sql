CREATE TABLE ingredient (
        ingredient_id    serial CONSTRAINT pk_ingredient_id PRIMARY KEY,
        ingredient_name  varchar(100) NOT NULL
);

CREATE TABLE unit_of_measure (
        unit_id         serial CONSTRAINT pk_unit_of_measure PRIMARY KEY,
        unit_name       varchar(50) NOT NULL
);

CREATE TABLE type (
        type_id  serial CONSTRAINT pk_recipe_type_id PRIMARY KEY,
        type     varchar(100)
);

CREATE TABLE recipe (
        recipe_id       serial CONSTRAINT pk_recipe_id PRIMARY KEY,
        recipe_name     varchar(200) NOT NULL,
        description     varchar(500),
        yield           int,
        unit_id         int,
        duration        varchar(100),
        recipe_method   varchar NOT NULL,
        CONSTRAINT fk_unit_id FOREIGN KEY (unit_id) REFERENCES unit_of_measure(unit_id)
);

CREATE TABLE recipe_type (
        recipe_id       int,
        type_id         int,
        CONSTRAINT fk_type_id FOREIGN KEY (type_id) REFERENCES type(type_id),
        CONSTRAINT fk_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipe(recipe_id)
);
        

CREATE TABLE recipe_ingredients (
        recipe_id       int,
        quantity        numeric(20, 4),
        unit_id         int,
        ingredient_id   int,
        CONSTRAINT fk_unit_id FOREIGN KEY (unit_id) REFERENCES unit_of_measure(unit_id),
        CONSTRAINT fk_ingredient_id FOREIGN KEY (ingredient_id) REFERENCES ingredient(ingredient_id),
        CONSTRAINT fk_recipe_id FOREIGN KEY (recipe_id) REFERENCES recipe(recipe_id)
);




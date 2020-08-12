INSERT INTO users (username,password_hash,role,security_question_id, answer) VALUES ('test1','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 3, 'Pittsburgh');
INSERT INTO users (username,password_hash,role,security_question_id, answer) VALUES ('test2','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 5, 'Steve');



INSERT INTO units_of_measure (unit_name)
        VALUES 
        ('teaspoon'),
        ('tablespoon'),
        ('ounce'),
        ('cup'),
        ('quart'),
        ('pound'),
        ('gallon'),
        ('gram'),
        ('kilogram'),
        ('milliliter'),
        ('liter'),
        ('pinch'),
        ('each'),
        ('serving'),
	('piece'),
	('sheet')
;


INSERT INTO types (type_name)
        VALUES 
        ('breakfast'),
        ('lunch'),
        ('dinner'),
        ('dessert'),
        ('appetizer'),
        ('entree'),
        ('side')
;

INSERT INTO ingredients (ingredient_name)
        VALUES
        ('butter'),
        ('sugar'),
        ('cocoa powder'),
        ('vanilla extract'),
        ('egg'),
        ('all-purpose flour'),
        ('baking powder'),
        ('iodized salt'),
        ('sour cream'),
        ('semi-sweet chocolate chips'),
        ('buttermilk'),
        ('chicken broth'),
        ('carrots'),
        ('celery'),
        ('onion'),
        ('lemon juice'),
        ('egg yolk'),
        ('chicken'),
        ('orzo')
;

INSERT INTO recipes (recipe_name, description, yield_amount, yield_unit_id, duration, recipe_method, is_public, ownername)
        VALUES ('brownies', 'best brownies ever', 24, 15, '1 hour', 'preheat oven to 300 degrees / whisk together sugar and cocoa powder / in a separate bowl whisk together flour, salt, baking powder / melt butter and mix into sugar and cocoa / whisk in eggs and vanilla / whisk in flour mixture / whisk in sour cream and chocolate chips / spread in parchment covered half sheet pan / bake for 20 minutes, turn, check after 15 additional minutes / knife should come out clean when done / let cool completely before turning out of pan and cutting', true, 'test1'),
        ('biscuits', 'lots of layery goodness', 60, 15, '45 minutes', 'preheat oven to 400 degrees / whisk together flour, sugar, salt, and baking powder / incorporate chunks of chilled butter with pastry blender till coarse crumb consistency / add buttermilk and stir by hand, mixture will be lumpy / turn out onto floured surface / pat dough till 2-3 thick, sprinkle with flour, then fold dough in half onto itself / repeat 6 more times, turning dough 1/4 turn each fold / roll dough to 2" thick / cut with round biscuit cutter and arrange on parchement covered sheet pan / bake 8 minutes, turn, check after additional 4 minutes / biscuits should be golden brown on top and bottom / remove from oven and brush with melted butter', false, 'test1'),
        ('avgolemeno soup', 'lemony chicken comfort', 4, 14, '1 hour', 'combine chicken broth, lemon juice, and chopped carrots, celery and onion in stockpot and bring to a boil / reduce heat, partially cover and simmer 20 minutes / in small bowl mix together softened butter and flour / remove lid from pot, increase heat to low boil / add butter flour mixture in 3 parts stirring well after each addition / let boil 10 minutes / meanwhile whisk egg yolks in separate bowl till light colored and thick / temper eggs yolks with hot soup and return egg and soup mixture to pot / add cooked chicken and cooked orzo / serve when heated through', true, 'test2')
;

INSERT INTO recipe_ingredients (recipe_id, quantity, unit_id, ingredient_id)
        VALUES (1, 14.7, 3, 1),
        (1, 26, 3, 2),
        (1, 6, 3, 3),
        (1, 1, 2, 4),
        (1, 8, 13, 5),
        (1, 8, 3, 6),
        (1, 1, 2, 7),
        (1, .75, 1, 8),
        (1, 6, 3, 9),
        (1, 8, 3, 10),
        (2, 80, 3, 6),
        (2, 4, 3, 2),
        (2, 2.5, 3, 7),
        (2, 1, 3, 8),
        (2, 2, 6, 1),
        (2, 2, 5, 11),  
        (3, 8, 4, 12),
        (3, .5, 4, 13),
        (3, .5, 4, 14),
        (3, .5, 4, 15),
        (3, 3, 3, 16),
        (3, .25, 4, 1),
        (3, .25, 4, 6),
        (3, 8, 13, 17),
        (3, 2, 4, 18),
        (2, 1, 4, 19)
;       
        
INSERT INTO recipe_types (recipe_id, type_id)
        VALUES (1, 4),
        (2, 1),
        (2, 2),
        (2, 7),
        (3, 2),
        (3, 3),
        (3, 6)
;

INSERT INTO user_recipes (username, recipe_id, is_favorite)
        VALUES ('test1', 1, true),
        ('test1', 2, false),
        ('test2', 3, true),
        ('test2', 1, false)
;

INSERT INTO meal_plans (username, plan_name, plan_description)
        VALUES ('test1', 'test1 meal plan', 'test1 meal plan description'),
        ('test2', 'test2 meal plan', 'test2 meal plan description'),
        ('test1', 'test1 other meal plan', 'test1 other meal plan description')
;

INSERT INTO plan_recipes (plan_id, recipe_id)
        VALUES (1, 1),
        (1, 2),
        (2, 1),
        (2, 3),
        (3, 2),
        (3, 3)
;

INSERT INTO units_of_measure (unit_id, unit_name)
        VALUES (17, 'clove');
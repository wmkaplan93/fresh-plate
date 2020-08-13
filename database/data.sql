INSERT INTO users (username,password_hash,role,security_question_id, answer) VALUES ('test1','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 3, 'Pittsburgh');
INSERT INTO users (username,password_hash,role,security_question_id, answer) VALUES ('test2','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 5, 'Steve');
INSERT INTO users (username,password_hash,role,security_question_id, answer) VALUES ('Steve Miami','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 1, '90210');

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
        VALUES ('brownies', 'best brownies ever', 24, 15, '1 hour', 'preheat oven to 300 degrees / whisk together sugar and cocoa powder / in a separate bowl whisk together flour, salt, baking powder / melt butter and mix into sugar and cocoa / whisk in eggs and vanilla / whisk in flour mixture / whisk in sour cream and chocolate chips / spread in parchment covered half sheet pan / bake for 20 minutes, turn, check after 15 additional minutes / knife should come out clean when done / let cool completely before turning out of pan and cutting', true, 'Steve Miami'),
        ('biscuits', 'lots of layery goodness', 60, 15, '45 minutes', 'preheat oven to 400 degrees / whisk together flour, sugar, salt, and baking powder / incorporate chunks of chilled butter with pastry blender till coarse crumb consistency / add buttermilk and stir by hand, mixture will be lumpy / turn out onto floured surface / pat dough till 2-3 thick, sprinkle with flour, then fold dough in half onto itself / repeat 6 more times, turning dough 1/4 turn each fold / roll dough to 2" thick / cut with round biscuit cutter and arrange on parchement covered sheet pan / bake 8 minutes, turn, check after additional 4 minutes / biscuits should be golden brown on top and bottom / remove from oven and brush with melted butter', false, 'Steve Miami'),
        ('avgolemeno soup', 'lemony chicken comfort', 4, 14, '1 hour', 'combine chicken broth, lemon juice, and chopped carrots, celery and onion in stockpot and bring to a boil / reduce heat, partially cover and simmer 20 minutes / in small bowl mix together softened butter and flour / remove lid from pot, increase heat to low boil / add butter flour mixture in 3 parts stirring well after each addition / let boil 10 minutes / meanwhile whisk egg yolks in separate bowl till light colored and thick / temper eggs yolks with hot soup and return egg and soup mixture to pot / add cooked chicken and cooked orzo / serve when heated through', true, 'Steve Miami')
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
        VALUES ('Steve Miami', 1, true),
        ('Steve Miami', 2, false),
        ('Steve Miami', 3, true),
        ('Steve Miami', 1, false)
;

INSERT INTO meal_plans (username, plan_name, plan_description)
        VALUES ('Steve Miami', 'weekend meal plan', 'dishes that take more prep time'),
        ('Steve Miami', 'meatless mondays', 'vegetarian and vegan recipes'),
        ('Steve Miami', 'workout meal plan', 'pump you up')
;

INSERT INTO units_of_measure (unit_id, unit_name)
VALUES (17, 'clove');
        
INSERT INTO units_of_measure (unit_id, unit_name)
VALUES (18, 'can');        
        
INSERT INTO recipes (recipe_name, description, yield_amount, yield_unit_id, duration, recipe_method, is_public, ownername)
VALUES ('energy balls', 'easy, homemade energy snack', 14, 15, '15 minutes', 'pit the dates and blend them in a food processor with the oatmeal until sticky clumps form / add the peanut butter, cocoa powder, and vanilla and blend until mixed / check the texture for stickiness and add 1 tablespoon of water at a time until mixture holds together / form into 14 balls', false, 'Steve Miami');

INSERT INTO ingredients (ingredient_id, ingredient_name)
VALUES (20, 'dates'), (21, 'peanut butter'), (22, 'oatmeal');

INSERT INTO recipe_ingredients (recipe_id, quantity, unit_id, ingredient_id)
VALUES (4, 1, 4, 20 ), (4, 0.5, 4, 21), (4, 1, 4, 22), (4, 1, 1, 4);

INSERT INTO recipe_types (recipe_id, type_id)
VALUES (4, 4), (4, 1);
 
INSERT INTO user_recipes (username, recipe_id, is_favorite)
VALUES ('Steve Miami', 4, true);      
        
INSERT INTO recipes (recipe_name, description, yield_amount, yield_unit_id, duration, recipe_method, is_public, ownername)
VALUES ('creamy avocado sauce pasta', 'avocados in pasta, why not?', 4, 14, '30 minutes', 'boil pasta and drain / add avocados, basil, garlic, and lemon juice to a food processor / season with salt and pepper / while blending, add in the olive oil slowly / saute garlic, broccoli, diced yellow squash, and frozen corn in a large pan / add avocado sauce, cherry tomatoes, and pasta to pan and heat through', false, 'Steve Miami');

INSERT INTO ingredients (ingredient_id, ingredient_name)
VALUES (23, 'spaghetti'), (24, 'avocado'), (25, 'basil'), (26, 'garlic'), (27, 'olive oil'), (28, 'broccoli'), (29, 'yellow squash'), (30, 'corn'), (31, 'cherry tomatoes');

INSERT INTO recipe_ingredients (recipe_id, quantity, unit_id, ingredient_id)
VALUES (5, 12, 3, 23 ), (5, 2, 13, 24), (5, 0.5, 4, 25), (5, 2, 17, 26), (5, 2, 2, 16), (5, 0.25, 4, 27), (5, 2, 4, 28), (5, 1, 13, 29), (5, 0.25, 4, 30), (5, 1, 4, 31);

INSERT INTO recipe_types (recipe_id, type_id)
VALUES (5, 3), (5, 6);

INSERT INTO user_recipes (username, recipe_id, is_favorite)
VALUES ('Steve Miami', 5, true);
         
INSERT INTO recipes (recipe_name, description, yield_amount, yield_unit_id, duration, recipe_method, is_public, ownername)
VALUES ('mango salad', 'simple, fresh palate cleanser', 2, 14, '5 minutes', 'peel mango and cut into bite-sized pieces / pour the lime juice over the mango / mince the mint and sprinkle over the mango', false, 'Steve Miami');

INSERT INTO ingredients (ingredient_id, ingredient_name)
VALUES (32, 'mango'), (33, 'lime juice'), (34, 'mint');

INSERT INTO recipe_ingredients (recipe_id, quantity, unit_id, ingredient_id)
VALUES (6, 1, 13, 32), (6, 1, 2, 33), (6, 1, 2, 34);

INSERT INTO recipe_types (recipe_id, type_id)
VALUES (6, 7), (6, 4);

INSERT INTO user_recipes (username, recipe_id, is_favorite)
VALUES ('Steve Miami', 6, false);

INSERT INTO recipes (recipe_name, description, yield_amount, yield_unit_id, duration, recipe_method, is_public, ownername)
VALUES ('vegan spinach white bean dip', 'creamy vegan dip', 1, 14, '20 minutes', 'saute spinach with garlic in olive oil until wilted / season with salt and pepper / combine beans, nutritional yeast, olive oil, mayo, lemon juice, and salt and pepper to taste in a food processor / blend until mixture is creamy / add spinach and pulse to combine', true, 'Steve Miami');

INSERT INTO ingredients (ingredient_id, ingredient_name)
VALUES (35, 'spinach'), (36, 'white beans'), (37, 'nutritional yeast'), (38, 'vegan mayo');

INSERT INTO recipe_ingredients (recipe_id, quantity, unit_id, ingredient_id)
VALUES (7, 1, 13, 35), (7, 1, 17, 26), (7, 1, 18, 36), (7, 0.25, 4, 37), (7, 1, 2, 27), (7, 2, 2, 38), (7, 1, 2, 16);

INSERT INTO recipe_types (recipe_id, type_id)
VALUES (7, 5);

INSERT INTO user_recipes (username, recipe_id, is_favorite)
VALUES ('Steve Miami', 7, false);

INSERT INTO recipes (recipe_name, description, yield_amount, yield_unit_id, duration, recipe_method, is_public, ownername)
VALUES ('chicken and shrimp dumplings', 'crowd-pleasing party food', 48, 15, '1 hour', 'put ground chicken in a large mixing bowl and stir it several times / cut each shrimp into small, bite-size pieces and add to the chicken / cut green onions and mushrooms / grate the ginger  / mince the garlic / add all of these ingredients to the mix / stir in sesame oil and soy sauce / season with salt and pepper / stir, if the moisture is not moist enough, add a tablespoon of water or oil at a time / put about a teaspoon of filling into each wrapper / wet the sides of the wrapper and press the sides together tightly / heat a non-stick skillet and add oil / place the dumplings in the pan with space in between each one / cook on medium heat until they are brown on the bottom / pour a thin layer of water into the pan, turn the heat to low, and cover the pan to steam the dumplings / when the water has evaporated, repeat with another layer of water and steam again  / uncover and allow any remaining water to cook off', true, 'Steve Miami');
         
INSERT INTO ingredients (ingredient_id, ingredient_name)
VALUES (39, 'ground chicken'), (40, 'shrimp'), (41, 'green onions'), (42, 'shiitake mushrooms'), (43, 'ginger'), (44, 'sesame oil'), (45, 'soy sauce'), (46, 'dumpling/wonton wrappers');   

INSERT INTO recipe_ingredients (recipe_id, quantity, unit_id, ingredient_id)
VALUES (8, 0.75, 6, 39), (8, 0.5, 6, 40), (8, 6, 15, 41), (8, 10, 15, 42), (8, 2, 17, 26), (8, 1, 2, 43), (8, 2, 2, 44), (8, 1, 2, 45), (8, 48, 15, 46);

INSERT INTO recipe_types (recipe_id, type_id)
VALUES (8, 2), (8, 3), (8, 5), (8, 7);  

INSERT INTO user_recipes (username, recipe_id, is_favorite)
VALUES ('Steve Miami', 8, true);  

INSERT INTO recipes (recipe_name, description, yield_amount, yield_unit_id, duration, recipe_method, is_public, ownername)
VALUES ('snickers smoothie', 'healthy, liquid snickers', 1, 14, '5 minutes', 'add frozen banana, almond milk, date, cocoal powder, and peanut butter to a high-speed blender / blend until smooth', true, 'Steve Miami');

INSERT INTO ingredients (ingredient_id, ingredient_name)
VALUES (47, 'banana'), (48, 'almond milk');

INSERT INTO recipe_ingredients (recipe_id, quantity, unit_id, ingredient_id)
VALUES (9, 1, 4, 48), (9, 1, 13, 47), (9, 1, 13, 20), (9, 1, 2, 3), (9, 1, 2, 21);

INSERT INTO recipe_types (recipe_id, type_id)
VALUES (9, 1);

INSERT INTO user_recipes (username, recipe_id, is_favorite)
VALUES ('Steve Miami', 9, false);

INSERT INTO recipes (recipe_name, description, yield_amount, yield_unit_id, duration, recipe_method, is_public, ownername)
VALUES ('fried rice', 'clean out your fridge fried rice', 4, 14, '30 minutes', 'stir-fry the veggies (any veggies you have in the fridge will do) in a wok or large pan / season with salt and pepper / add cooked rice (leftover rice that''s been in the fridge works best, otherwise if you''re making new rice, reduce the amount of water a little so that the rice will be dry / stir to break up the rice / whisk the eggs, push everything to the side of the pan and pour in the eggs / scramble them and mix into the rice / season with soy sauce, sesame oil, turmeric, and salt', true, 'Steve Miami');

INSERT INTO ingredients (ingredient_id, ingredient_name)
VALUES (49, 'peas'), (50, 'turmeric'), (51, 'rice');

INSERT INTO recipe_ingredients (recipe_id, quantity, unit_id, ingredient_id)
VALUES (10, 3, 4, 51), (10, 1, 4, 49), (10, 0.5, 4, 13), (10, 1, 4, 28), (10, 0.5, 4, 30), (10, 1, 15, 29), (10, 1.5, 2, 44), (10, 1.5, 2, 45), (10, 1, 1, 50);

INSERT INTO recipe_types (recipe_id, type_id)
VALUES (10, 2), (10, 3), (10, 7);

INSERT INTO user_recipes (username, recipe_id, is_favorite)
VALUES ('Steve Miami', 10, false);

INSERT INTO plan_recipes (plan_id, recipe_id)
        VALUES (1, 1),
        (1, 3),
        (1, 8),
        (2, 5),
        (2, 6),
        (2, 7),
        (3, 4),
        (3, 9)
;



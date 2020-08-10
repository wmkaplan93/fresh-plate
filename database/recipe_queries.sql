
/* .will pull a recipe object given a recipe id */

SELECT recipe_id, name, description, yield, unit_name, duration, recipe_method, is_public
FROM recipes 
JOIN units_of_measure ON recipes.unit_id = units_of_measure.unit_id
WHERE recipe_id = ?;

/* .will pull list of recipes given a username*/
SELECT recipes.recipe_id, name, description, yield, unit_name, duration, recipe_method, is_public, is_favorite
FROM recipes 
JOIN units_of_measure ON recipes.unit_id = units_of_measure.unit_id
JOIN user_recipes ON recipes.recipe_id = user_recipes.recipe_id
JOIN users ON user_recipes.user_id = users.user_id
WHERE username = ?;

/* .will pull all favorite recipe objects given a username*/
SELECT recipes.recipe_id, name, description, yield, unit_name, duration, recipe_method, is_public, is_favorite
FROM recipes 
JOIN units_of_measure ON recipes.unit_id = units_of_measure.unit_id
JOIN user_recipes ON recipes.recipe_id = user_recipes.recipe_id
JOIN users ON user_recipes.user_id = users.user_id
WHERE username = ?
AND is_favorite = true;

/* .will pull all public recipes objects */
SELECT recipe_id, name, description, yield, unit_name, duration, recipe_method, is_public
FROM recipes 
JOIN units_of_measure ON recipes.unit_id = units_of_measure.unit_id
WHERE is_public = true;


/* .will pull the ingredients list from a recipe with recipe id */
SELECT quantity, unit_name, ingredient_name
FROM recipe_ingredients
JOIN units_of_measure ON recipe_ingredients.unit_id = units_of_measure.unit_id
JOIN ingredients ON recipe_ingredients.ingredient_id = ingredients.ingredient_id
WHERE recipe_id = ?;

/* .will pull the recipe type tags given a recipe id*/
SELECT type
FROM types
JOIN recipe_types ON types.type_id = recipe_types.type_id
WHERE recipe_id = ?;

/* .will pull all public recipes given a type*/
SELECT recipes.recipe_id, name, description, yield, unit_name, duration, recipe_method, is_public
FROM recipes 
JOIN units_of_measure ON recipes.unit_id = units_of_measure.unit_id
JOIN recipe_types ON recipes.recipe_id = recipe_types.recipe_id
JOIN types ON recipe_types.type_id = types.type_id
WHERE type = ?
AND is_public = true;

/* .will pull all recipe objects given a type and username */
SELECT recipes.recipe_id, name, description, yield, unit_name, duration, recipe_method, is_public, is_favorite
FROM recipes 
JOIN units_of_measure ON recipes.unit_id = units_of_measure.unit_id
JOIN recipe_types ON recipes.recipe_id = recipe_types.recipe_id
JOIN types ON recipe_types.type_id = types.type_id
JOIN user_recipes ON recipes.recipe_id = user_recipes.recipe_id
JOIN users ON user_recipes.user_id = users.user_id
WHERE type = ?
AND username = ?;

/* inserts new recipe into recipe table */
        
INSERT INTO recipes (recipe_id, name, description, yield, unit_id, duration, recipe_method, is_public)
        VALUES (?, ?, ?, ?, (SELECT unit_id FROM units_of_measure WHERE unit_name = ?), ?, ?, ?);

/* inserts one ingredient into recipe_ingredients table */        
INSERT INTO recipe_ingredients (recipe_id, quantity, unit_id, ingredient_id)
        VALUES (?, ?, (SELECT unit_id FROM units_of_measure WHERE unit_name = ?), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = ?));
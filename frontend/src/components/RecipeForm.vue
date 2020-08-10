<template>
    <form class="container">
        <link href='https://fonts.googleapis.com/css?family=Lora' rel='stylesheet'>
        <div class="form-group">
            <label id="name-label" for="recipe-name">Recipe Name: </label>
            <input type="text" id="recipe-name" class="form-control" v-model="name" />
        </div>
        <div class="form-group">
            <label id="description-label" for="description">Recipe Description: </label>
            <input type="text" id="description" class="form-control" v-model="description" /> 
        </div>    
        <div class="type-dropdown">
         <!--   <p>Recipe Type: </p>
            <label class="type-options" for="option1">
            <input type="checkbox" id="option1" value="breakfast" v-model="recipeType"><span>breakfast</span>
            </label>
            <label class="type-options" for="option2">
            <input type="checkbox" id="option2" value="lunch" v-model="recipeType"><span>lunch</span>
            </label>
            <label class="type-options" for="option3">
            <input type="checkbox" id="option3" value="dinner" v-model="recipeType"><span>dinner</span><br>
            </label>
            <label class="type-options" for="option4">
            <input type="checkbox" id="option4" value="appetizer" v-model="recipeType"><span>appetizer</span>
            </label>
            <label class="type-options" for="option5">
            <input type="checkbox" id="option5" value="entree" v-model="recipeType"><span>entree</span>
            </label>
            <label class="type-options" for="option6">
             <input type="checkbox" id="option6" value="side" v-model="recipeType"><span>side</span>
             </label>
             <label class="type-options" for="option7">
             <input type="checkbox" id="option7" value="dessert" v-model="recipeType"><span>dessert</span>
             </label> -->
            <label id="type-label" for="recipe-type">Recipe Type: </label>
            <select name="basic-dropdown" id="recipe-type" multiple v-model="type">
                    <option>breakfast</option>
                    <option>lunch</option>
                    <option>dinner</option>
                    <option>dessert</option>
                    <option>appetizer</option>
                    <option>entree</option>
                    <option>side</option>
                </select><br><br>
        </div>
        <div class="form-group">
            <label id="yield-label" for="servings">Servings: </label>
            <input type="text" id="servings" class="form-control" v-model="servings" />
        </div>
        <div class="form-group">
            <label id="time-label" for="duration">Total Time: </label>
            <input type="text" id="duration" class="form-control" v-model="duration" />
        </div>  
        <div class="ingredient-section">  
            <ingredient-form /> 
             

             
               <!-- <option value=
                 <select v-model="ingredient"
                options="formProperty | extract 'ingredients'">
                </select> -->         
        </div>    
        <div class="form-group">
            <label id="instructions-label" for="instructions">Instructions:  </label>
            <textarea id="instructions" class="form-control" v-model="instructions" />
           
        </div>
        <button type="submit">Submit</button>
       
        
    </form>   
</template>


<script>

import recipeService from "../services/RecipeService";
import IngredientForm from "../components/IngredientForm";

export default {
    name: "add-recipe-form",
    components: {
        IngredientForm
    },
    
    data() {
        return {
            name: '',
            description: '',
            type: [],
            servings: '',
            duration: '',
            instructions: ''
            
        };
    },
    methods: {
        addIngredient() {
            const newIngredient = {
                quantity: this.quantity,
                unitOfMeasure: this.unitOfMeasure,
                ingredient: this.ingredient,
            }
        },
        
        submit() {
            const newRecipe = {
                userId: Number(this.$route.params.userId),
                name: this.recipe.name,
                description: this.recipe.description,
                type: this.recipe.type, /* return an array */
                servings: this.recipe.servings,
                duration: this.recipe.duration,
                instructions: this.recipe.instructions,
                ingredients: this.newIngredient,
            };
        
            
                recipeService
                .addRecipe(newRecipe)
                .then(response => {
                    if (response.status === 201) {
                        this.$router.push('myRecipes');
                    }
                })
                .catch(error => {
                    this.handleErrorResponse(error, "adding");
                })
            
        },

        getFormObjects() {
            recipeService
            .getFormProperties().then((response) => {
                this.recipeTypes = response.data.types.type;
                this.units = response.data.units.unitName;
                this.ingredients = response.data.ingredients.ingredient
            })
        },
        filters: {
            extract: function (value, keyToExtract) {
            return value.map(function (item) {
                return item[keyToExtract]
                })
            }
        } 
    }   
}
</script>

<style scoped>

.container {
    display: flex;
    flex-direction: column;
    width: 50vw;
    font-family:"Lora";
}

label {
    font-size: 20px;
}
.form-control {
    display: flex;
    justify-content: flex-start;
    width: 30vw;
    height: 20px;
    font-size: 1rem;
    line-height: 1.5;
    color: #131414;
    border: 1px solid black;
    border-radius: 0.25rem; 
    margin-bottom: 15px;  
}
.type-checkboxes {
    display: flex;
    justify-content: flex-start;
}
.type-options {
    display: flex;
    cursor: pointer;
    position: relative;
    padding-left: 10px;
    margin-bottom: 12px;
    -webkit-user-select: none;
    -moz-user-select: none;
    user-select: none;
}

.type-options label {
    display: flex;
    justify-content: flex-start;
    padding-right: 2px;
    margin-bottom: 0.5rem;
}

.type-options input {
    display: flex;
    vertical-align: -webkit-baseline-middle;
    height: 15px;
    margin-right: 0em;
}

.type-options span {
    vertical-align: -webkit-baseline-middle;
}

textarea.form-control {
    height: 50px;
}


</style>
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
            <!-- <p>Recipe Type: </p>
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
            <select name="basic-dropdown" id="recipe-type" multiple v-model="typeList">
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
            <div class="servings">
                <label id="yield-amount-label" for="yield-amount">Servings: </label>
                <input type="text" id="yield-amount" class="form-control" v-model="yieldAmount" />
                <label id="yield-amount-unit" for="yield-unit">Unit: </label>
                 <select name="basic-dropdown" id="yield-unit" v-model="yieldUnit">
                        <option value='teaspoon'>teaspoon</option>
                        <option value='tablespoon'>tablespoon</option>
                        <option value='ounce'>ounce</option>
                        <option value='cup'>cup</option>
                        <option value='quart'>quart</option>
                        <option value='pound'>pound</option>
                        <option value='gallon'>gallon</option>
                        <option value='gram'>gram</option>
                        <option value='kilogram'>kilogram</option>
                        <option value='milliliter'>milliliter</option>
                        <option value='liter'>liter</option>
                        <option value='pinch'>pinch</option>
                        <option value='each'>each</option>
                        <option value='serving'>serving</option>
                        <option value='piece'>piece</option>
                        <option value='sheet'>sheet</option>
                    </select>
            </div>    
        </div>

        <div class="form-group">
            <label id="time-label" for="duration">Total Time: </label>
            <input type="text" id="duration" class="form-control" v-model="duration" />
        </div>  
        <div class="ingredient-section">  
            <ingredient-form /> 
             

             
             
        </div>    
        <div class="form-group">
            <label id="instructions-label" for="instructions">Instructions:  </label><br>
            <button class="btn" type="button" id="ingredient-btn" v-on:click.prevent="addNewInstructionForm">Add Instruction Step</button>
            <textarea id="instructions" class="form-control" v-model="recipeMethod" />
           
        </div>

        <div class="form-group">
            <label id="public-checkbox" for="public-recipe">Make this recipe public </label>
            <input type="checkbox" id="public-recipe" v-model="isPublic" />
        </div>

        <div class="form-group">
            <label id="favorite-checkbox" for="favorite-recipe">Add to favorites</label>
            <input type="checkbox" id="favorite-recipe" v-model="isFavorite" />
        </div>

        <button class="btn btn-submit" v-on:click="addNewRecipe">Submit</button>
        <!--where should user be directed after hitting submit -->
       
        
    </form>   
</template>


<script>

import recipesService from "../services/RecipeService";
import IngredientForm from "../components/IngredientForm";

export default {
    name: "add-recipe-form",
    components: {
        IngredientForm
    },
    
    data() {
        return {
            username: '',
            ownername: '',
            name: '',
            description: '',
            typeList: [],
            yieldAmount: '',
            yieldUnit: '',
            duration: '',
            recipeMethod: [],
            isPublic: false,
            isFavorite: false,
            ingredientList: []
            
        };
    },
    methods: {
        addNewInstructionForm() {
            this.recipeMethod.push ('')
        },
        
        // addIngredient() {
        //     const newIngredient = {
        //         quantity: this.quantity,
        //         unitOfMeasure: this.unitOfMeasure,
        //         ingredient: this.ingredient,
        //     }
        // },
        
        addNewRecipe() {
            const newRecipe = {
                username: this.$route.user.username,
                ownername: this.$route.user.username,
                name: this.recipe.name,
                description: this.recipe.description,
                typeList: this.recipe.typeList, /* return an array */
                yieldAmount: this.recipe.yieldAmount,
                yieldUnit: this.recipe.yieldUnit,
                duration: this.recipe.duration,
                recipeMethod: this.recipe.recipeMethod,
                isPublic: this.recipe.isPublic,
                isFavorite: this.recipe.isFavorite,
                ingredientList: this.recipe.ingredientList
      
            };

            const currentUser = {
                username: this.$route.user.username,
            }
        
            
            recipesService
            .addRecipe(currentUser, newRecipe).then(response => {
                this.$store.commit("SET_RECIPE", response.data);
            })
        },    
                
        getFormObjects() {
            recipesService
            .getFormProperties().then((response) => {
                this.recipeTypes = response.data.types.type;
                this.units = response.data.units.unitName;
                this.ingredients = response.data.ingredients.ingredient
            })
        },
        
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

.servings {
    display: flex;
    flex-direction: row;
    width: 5vw;
}

#favorite-recipe, #public-recipe {
    width: 10vw;
    height: 3vh;
}


</style>
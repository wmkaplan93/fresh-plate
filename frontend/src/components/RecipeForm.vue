<template>
    <div class="app">
        <link href='https://fonts.googleapis.com/css?family=Lora' rel='stylesheet'>
        <form class="container">
            <h2>New Recipe</h2>
            <div class="form-group">
                <label for="recipe-name">Recipe Name: </label>
                <input type="text" id="recipe-name" class="form-control" v-model="recipe.name" />
            </div>
            <div class="form-group">
                <label for="recipe-description">Recipe Description: </label>
                <input type="text" id="recipe-description" class="form-control" v-model="recipe.description" />
            </div>
            <div class="form-group">
                <label>Recipe Type: </label>
                <div class="recipe-type" v-for="typename in formProperties.types" v-bind:key="typename.typeId">
                <label id="type-label"> {{typename.type}} </label> 
                <input type="checkbox" id="type-checkbox" v-model="recipe.typeList" :value="typename" /> 
                </div>
            </div>  
            <div class="form-group">
                <label for="yield-amount">Servings: </label>
                <input type="text" id="yield-amount" class="form-control" v-model="recipe.yieldAmount" />

                <div class="serving-unit">
                <label for="yield-unit">Unit: </label>    
                <select name="basic-dropdown" id="yield-unit" class="form-control" v-model="recipe.yieldUnit">
                    <option v-for="unit in formProperties.units" v-bind:key="unit.unitId" :value="unit.unitName"> 
                        {{unit.unitName}}
                    </option>
                </select>   
                </div> 
            </div>
            <div class="form-group">
                <label id="time-label" for="duration">Total Time: </label>
                <input type="text" id="duration" class="form-control" v-model="recipe.duration" />
            </div>  
            <div class="ingredient-form">
                    <button class="btn" type="button" id="ingredient-btn" v-on:click.prevent="addNewIngredientForm">
                    Add Ingredient to Recipe</button>
                    <div class="ingredient-card" v-for="(ingredient, index) in ingredientList" v-bind:key="ingredient.ingredientId">
                        <div class="card-body">
                        <span class="delete-box" v-on:click="deleteIngredientForm(index)">X</span>
                
                            <h3 class="card-title">Ingredient Details (Ingredient: {{ index }}) </h3>
                            <div class="recipe-ingredient-form">
                                <label for="ingredient-quantity">Quantity: </label>
                                <input type="text" id="ingredient-quantity" class="form-control" v-model="ingredient.quantity" />
                                <label for="ingredient-unit">Unit: </label>
                                <select name="basic-dropdown" id="ingredient-unit" class="form-control" v-model="ingredient.unitName">
                                    <option v-for="unit in formProperties.units" v-bind:key="unit.unitId" :value="unit.unitName"> 
                                        {{unit.unitName}}
                                    </option>
                                </select>   
                                <label for="database-ingredients">Ingredient: </label>
                                <select name="basic-dropdown" id="database-ingredients" class="form-control" v-model="ingredient.ingredientName">
                                    <option v-for="ingredient in formProperties.ingredients" v-bind:key="ingredient.ingredientId" :value="ingredient.ingredientName">
                                        {{ingredient.ingredientName}}
                                    </option>
                                </select>  
                                <label for="new-ingredient">Add new ingredient: </label>
                                <input type="text" id="new-ingredient" class="form-control" v-model="ingredient.ingredientName">       
                            </div>   
                        </div>
                    </div>                 
            </div>      

            <div class="form-group">
                <label for="instructions">Instructions: </label>
                <textarea id="instructions" class="form-control" v-model="recipe.recipeMethod" />
            </div>
             
            <div class="form-group">
                <label id="public-checkbox" for="public-recipe">Make this recipe public </label>
                <input type="checkbox" id="public-recipe" v-model="recipe.public" />
            </div>

            <div class="form-group">
                <label id="favorite-checkbox" for="favorite-recipe">Add to favorites</label>
                <input type="checkbox" id="favorite-recipe" v-model="recipe.favorite" />
            </div>


            <button class="btn btn-submit" v-on:click="addNewRecipe">Submit</button>
        </form>        
    </div>        
</template>

<script>
import recipeService from "../services/RecipeService"

export default {
    name: "recipe-form",
    data() {
        return {
            formProperties: {
                ingredients: [
                    {
                    ingredientId: 0,
                    ingredientName: ''
                    }
                ],
                types: [
                   {
                    typeId: 0,
                    type: ''
                   } 
                ],
                units: [
                    {
                    unitId: 0,
                    unitName: ''
                    }
                ]
            },
            recipe: {
                name: '',
                description: '',
                typeList: [],
                yieldAmount: '',
                yieldUnit: '',
                duration: '',
                recipeMethod: [],
                public: false,
                favorite: false,
            },
            ingredientList: [
                {
                quantity: '',
                ingredientName: '',
                unitName: ''
                }
            ],   
        }   
    },
    
    created() {
        this.retrieveFormProperties();
    },

    methods: {
        retrieveFormProperties() {
            recipeService.getFormProperties(this.$store.state.user.username).then(response => {
                this.$store.commit("GET_FORM_PROPERTIES", response.data);
                this.formProperties = response.data;    
            })
        },

        addNewRecipe() {
            const newRecipe = {
                username: this.$store.state.user.username,
                ownername: this.$store.state.user.username,
                name: this.recipe.name,
                description: this.recipe.description,
                typeList: this.recipe.typeList, 
                yieldAmount: this.recipe.yieldAmount,
                yieldUnit: this.recipe.yieldUnit,
                duration: this.recipe.duration,
                recipeMethod: this.recipe.recipeMethod,
                public: this.recipe.isPublic,
                favorite: this.recipe.isFavorite,
                ingredientList: this.recipe.ingredientList
      
            };

            const currentUser = {
                username: this.$store.state.user.username,
            }
            
            
            recipeService.addRecipe(currentUser, newRecipe).then(response => {
                if (response.status === 201) {
                    this.$router.push('/users/${username}/myRecipes');
                }
            })
        },

        addNewIngredientForm() {
            this.ingredientList.push ({
                quantity: '',
                ingredientName: '',
                unitName: ''
            })
        },

        deleteIngredientForm(index) {
            this.ingredientList.splice(index, 1)

        }
    
       
    }
}
</script>

<style scoped>

.app {
    display: flex;
    flex-direction: column;
    font-family: "Lora", Arial, Helvetica, sans-serif;
}

.container {
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
    width: 60vw;
    background-color:rgb(255, 255, 255, 0.2);
}

label {
    font-size: 2vw;
}

.form-group {
    padding-bottom: 10px;
}
input, input #ingredient-quantity {
    margin-right: 15px;
    background-color: rgb(255, 255, 255, 0.2);
    height: 5vh;
    width: 20vw;
    font-size: 3vh;
    align-items: center;
}

textarea {
    border-radius: 0px;
    background-color: rgb(255, 255, 255, 0.2);
    height: 10vh;
    width: 40vw;
}
#ingredient-btn {
    color: rgb(211, 208, 208);
    background-color: green;
    width: max-content;
    font-size: 1.5vw;
    padding-left: 5px;
    padding-right: 5px;
}

.btn btn-submit {
    color: rgb(211, 208, 208);
    background-color: rgb(1, 24, 1);
    width: max-content;
}

.card-body {
    margin-top: 15px;
    margin-bottom: 15px;
    border: 2px solid black;
    width: 55vw;
}

.recipe-type input {
    height: 3vh;
    width: 2vw;
    margin-left: 8px;
}

#favorite-recipe, #public-recipe {
    height: 3.5vh;
    width: 2.5vw;
    margin-left: 10px;
}

.delete-box {
    float: right;
    cursor: pointer;
    font-weight: 800;
}

select {
    border: 1px solid black;
}

</style>
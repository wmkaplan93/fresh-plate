<template>
    <div class="app">
        <form class="container">
            <div class="form-group">
                <label for="recipe-name">Recipe Name: </label>
                <input type="text" id="recipe-name" class="form-control" v-model="recipe.name" />
            </div>
            <div class="form-group">
                <label for="recipe-description">Recipe Description: </label>
                <input type="text" id="recipe-description" class="form-control" v-model="recipe.description" />
            </div>
            <div class="form-group">
                <h3>Recipe Type: </h3>
                <div class="recipe-type" v-for="typename in formProperties.types" v-bind:key="typename.typeId">
                <label id="type-label"> {{typename.type}} </label> 
                <input type="checkbox" v-model="recipe.typeList" :value="typename.type" /> 
                </div>
            </div>  
            <div class="form-group">
                <label for="yield-amount">Servings: </label>
                <input type="text" id="yield-amount" class="form-control" v-model="recipe.yieldAmount" />

                <div class="serving-unit">
                <label for="yield-unit">Unit: </label>    
                <select name="basic-dropdown" id="yield-unit" v-model="recipe.yieldUnit">
                    <option v-for="unit in formProperties.units" v-bind:key="unit.unitId" :value="unit.unitName"> 
                        {{unit.unitName}}
                    </option>
                </select>   
                </div> 
            </div>
            <div class="ingredient-form">
                    <button class="btn" type="button" id="ingredient-btn" v-on:click.prevent="addNewIngredientForm">
                    Add Ingredient to Recipe</button>
                    <div class="ingredient-card" v-for="ingredient in ingredientList" v-bind:key="ingredient.ingredientName">
                        <div class="card-body">
                        <span class="float-right" style="cursor:pointer" v-on:click="deleteRecipeIngredientForm(index)">X</span>
                
                            <h3 class="card-title">Add Ingredient Details</h3>
                            <div class="recipe-ingredient-form">
                                <label for="ingredient-quantity">Quantity: </label>
                                <input type="text" id="ingredient-quantity" class="form-control" v-model="recipeIngredient.quantity" />
                                <label for="ingredient-unit">Unit: </label>
                                <select name="basic-dropdown" id="ingredient-unit" class="form-control" v-model="recipeIngredient.ingredientUnit">
                                    <option v-for="unit in formProperties.units" v-bind:key="unit.unitId" :value="unit.unitName"> 
                                        {{unit.unitName}}
                                    </option>
                                </select>   
                                <label for="database-ingredients">Ingredient: </label>
                                <select name="basic-dropdown" id="database-ingredients" class="form-control" v-model="recipeIngredient.ingredientName">
                                    <option v-for="ingredient in formProperties.ingredients" v-bind:key="ingredient.ingredientId" :value="ingredient.ingredientName">
                                        {{ingredient.ingredientName}}
                                    </option>
                                </select>        
                            </div>   
                        </div>
                    </div>                 
            </div>      

            <div class="method-form">
                <label for="instructions">Instructions: </label>
                <textarea id="instructions" class="form-control" v-model="recipe.recipeMethod" />
            </div>   
            <button class="btn btn-submit" v-on:click="addNewRecipe">Submit</button>
        </form>        
    </div>        
</template>

<script>
import recipeService from "../services/RecipeService"
import MyRecipesVue from '../views/MyRecipes.vue';

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
            ingredientList: [],

            recipeIngredient: {
                quantity: '',
                ingredientUnit: '',
                recipeIngredientName: ''
            }
           
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
                isPublic: this.recipe.isPublic,
                isFavorite: this.recipe.isFavorite,
                ingredientList: this.recipe.ingredientList
      
            };

            const currentUser = {
                username: this.$store.state.user.username,
            }
            
            
            recipeService.addRecipe(currentUser, newRecipe).then(response => {
                if (response.status === 201) {
                    this.$router.push({name: 'myRecipes', params: {username:currentUser.username}});
                }
            })
        },

        addNewIngredientForm() {
            this.ingredientList.push ({
                quantity: '',
                ingredientUnit: '',
                recipeIngredientName: ''
            })
        }
       
    }
}
</script>
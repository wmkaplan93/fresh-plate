<template>
    <div class="container">
        <button class="btn" type="button" id="ingredient-btn" v-on:click.prevent="addNewIngredientForm">add ingredient to recipe</button>
        <div class="ingredient-card"
            v-for="ingredient in ingredients" v-bind:key="ingredient.ingredientName">
            <div class="card-body">
                
                <h3 class="card-title">new ingredient</h3>   
                <div class="ingredient-form">
                    <label id="quantity-label" for="quantity">quantity: </label>
                    <input type="text" id="quantity" class="form-control" v-model="ingredient.quantity" />

                    <label id="unit-label" for="unit-type">unit of measure: </label>
                    <select name="basic-dropdown" id="unit-type" v-model="ingredient.unit">
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
                    </select><br><br>        

                    <label id="ingredient-label" for="ingredient">ingredient: </label>
                    <input type="text" id="ingredient" class="form-control" v-model="ingredient.ingredientName" />
                </div>
            </div>    
         </div>
                <div class="ingredient-list" v-for="ingredient in this.$store.state.ingredientList"
                        v-bind:key="ingredient.ingredientId">
                        {{ ingredient.ingredientName }}                  
                </div>
    </div>             
</template>




<script>
import RecipeService from '../services/RecipeService';
export default {
    data() {
        return {
            ingredients: [
                {
                    quantity: '',
                    ingredientUnit: '',
                    ingredientName: ''
                },
            ],
            
        }
    },
    created() {
        this.retrieveIngredients();
    },
    
    methods: {
        retrieveIngredients() {
            RecipeService.getIngredients().then(response => {
                this.$store.commit("SET_INGREDIENTS", response.data)
            })
        },
        
        addNewIngredientForm() {
            this.ingredients.push ({
                quantity: '',
                unit: '',
                ingredientName: ''
            })
        },

        cancelForm() {
            
        }
    },  
}
</script>

<style scoped>

#ingredient-btn {
    color: rgb(211, 208, 208);
    background-color: rgb(2, 87, 2);
    width: max-content;
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
    margin-left: 5px;
}

.card-body {
    margin-top: 15px;
    margin-bottom: 15px;
    border: 2px solid black;
    width: 35vw;
}

h3 {
    font-weight: bold;
    font-size: 23px;
}
</style>
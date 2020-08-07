<template>
    <div id="add-recipe-form">
        <button v-on:click="showForm = true">Add Recipe</button>

        
            <p>Recipe Name</p>
            <input type="text" v-model="name" />
            <p>Recipe Description</p>
            <input type="text" v-model="description" /> 
            <label for="basic-dropdown">Recipe Type</label>
            <select name="basic-dropdown" multiple v-model="type">
                <option>breakfast</option>
                <option>lunch</option>
                <option>dinner</option>
                <option>dessert</option>
                <option>appetizer</option>
                <option>entree</option>
                <option>side</option>
            </select>
            <br>
            <p>Servings</p>
            <input type="text" v-model="yield" />
            <p>Quantity</p>
            <input type="text" v-model="quantity" />
            <p>Total Time</p>
            <input type="text" v-model="duration" />
            <label for="component-dropdown">Unit</label>
            <dropdown id="unit-dropdown" :options="unitOfMeasure"></dropdown> 
            <p>Ingredient</p>   
            <p>Instructions</p>
            <input type="text" v-model="method" />
            <input type="submit" value="Submit">
        </form>
    </div>    
</template>


<script>
import UnitsOfMeasureDropdownVue from './UnitsOfMeasureDropdown.vue'
export default {
    name: "add-recipe-form",
    components: {
        'dropdown': UnitsOfMeasureDropdownVue
    },
    
    data() {
        return {
            name: '',
            description: '',
            type: '',
            yield: '',
            quantity: '',
            duration: '',
            unitOfMeasure: {
                'teaspoon': 1,
                'tablespoon': 2,
                'ounce': 3,
                'cup': 4,
                'quart': 5,
                'pound': 6,
                'gallon': 7,
                'gram': 8,
                'kilogram': 9,
                'milliliter': 10,
                'liter': 11,
                'pinch': 12,
                'each': 13,
                'serving': 14,
                'piece': 15,
                'sheet': 16
            },
            method: ''
        }
    } 
}
</script>
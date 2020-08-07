<template>
    <div id="add-recipe-form">
        <p>Recipe Name</p>
        <input type="text" v-model="name" />
        <p>Recipe Description</p>
        <input type="text" v-model="description" /> 
        <label for="basic-dropdown">Recipe Type</label>
        <select name="basic-dropdown" v-model="type">
            <option>breakfast</option>
            <option>lunch</option>
            <option>dinner</option>
            <option>dessert</option>
            <option>appetizer</option>
            <option>entree</option>
            <option>side</option>
        </select>
        <br>
        <p>Quantity</p>
        <input type="text" v-model="quantity" />
        <label for="component-dropdown">Unit</label>
        <dropdown id="unit-dropdown" :options="unitOfMeasure"></dropdown>    
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
            quantity: '',
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

            }
        }
    } 
}
</script>
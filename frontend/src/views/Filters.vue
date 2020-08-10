<template>
    <div class="filter">
        <label for="component-dropdown"></label>
        <dropdown id="component-dropdown" :options="ingredientOptions"></dropdown>
    
      <div class="dropdown">
                       <input type="text" v-model="searchText" />
                       
                   </div>
                   <ul class="suggestion-list">
                       <li v-for="suggestion in suggestions" v-bind:key="suggestion">
                           {{ suggestion[0]}}
                       </li>    
    
    </div>
       

</template>


<script>
import Dropdown from "../components/Dropdown"
import AutocompleteDropdown from "../components/AutocompleteDropdown"

export default {
    components: {
        Dropdown,
        AutocompleteDropdown
    },

    data() {
        return {
            selectedIngredient: '',
            ingredientOptions: {
                'broth': 'broth',
                'butter': 'butter',
                'flour': 'flour',
                'water': 'water',
                'eggs': 'eggs',
                'vanilla': 'vanilla'
            },
            searchText: ''
        }
    },
    computed: {
        matches() {
            return Object.entries(this.options).filter((option) => {
                let optionText = option[0].toUpperCase()
                return optionText.match(this.searchText.toUpperCase())
            })
        }
    }
}
</script>
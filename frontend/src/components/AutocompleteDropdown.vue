<template>
   <div class="filter">
        <label for="autocomplete-dropdown">Ingredient: </label>
        <autocomplete-dropdown id="autocomplete-dropdown" : options="ingredientOptions" 
            v-model="selectedIngredient"></autocomplete-dropdown>  
   </div>         
</template>
 


<template>
    <div class="app">
        <form class="container">
            <div class="form-group">
                <v-input type="type" class="form-control" v-model="name" />
            </div>
            <div class="form-group">
                <label id="type-label" for="recipe-type">Recipe Type: </label>  
            </div>
            <div class="form-group">
                Types: {{ formProperties }}    
            </div>    
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
                ingredients: [],
                types: [],
                units: []
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
       
    }
}
</script>
<template>
    <div id="overview" data-app>
        <h1 class="subheading black--text">{{this.$store.state.recipeDetails.name}}</h1>
        <h2>{{this.$store.state.recipeDetails.description}}</h2>
        <v-divider></v-divider>
        <h3><strong>Yield:</strong>{{this.$store.state.recipeDetails.yieldAmount}} {{this.$store.state.recipeDetails.yieldUnit}}</h3>
        <v-divider></v-divider>
        <h3 v-for="thing in this.$store.state.recipeDetails.typeList" :key="thing.typeId">
            <i>{{thing.type}}</i>
        </h3>
        <v-divider></v-divider>
        <v-container class="recipe-detail-cards">
            <v-flex xs10 sm6 md5 lg4
            v-for="ing in this.$store.state.recipeDetails.ingredientList"
            :key="ing.ingredientName">
            <v-card class="text-xs-center ma-4">
                <v-card-text>
                    <div class="subheading"><strong>{{ ing.ingredientName }}</strong></div>
                    <div class="gray--text">{{ ing.quantity }} {{ing.unitName }}</div>
                </v-card-text>
            </v-card>

            </v-flex>
        </v-container>


    </div>
</template>

<script>
import recipeService from "../services/RecipeService";

export default {
    name: "recipe-details-content",
    created() {
        this.retrieveRecipeDetails();
    },
    methods: {
        retrieveRecipeDetails() {
            recipeService.getRecipeDetails().then(response => {
                this.$store.commit("GET_RECIPE_DETAILS", response.data);
            })
        }
    }

}
</script>
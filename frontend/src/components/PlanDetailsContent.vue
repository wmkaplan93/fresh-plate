<template>
    <div data-app>
        <h1 class="subheading black--text text-center">{{this.$store.state.details.mealPlan.plan_name}}</h1>
        <h3 class="small text-center">{{this.$store.state.details.mealPlan.plan_description}}</h3>
        <v-card id="overview">
        <v-container class="plan-details-recipes">
            <v-btn depressed small router :to="{ name: 'myGroceryList', params: {planId:this.$store.state.details.mealPlan.plan_id} }">
                <v-icon>shopping_cart</v-icon>
                View Grocery List
            </v-btn>
            <v-layout row wrap>
                <v-flex xs12 sm6 md4 lg4
                v-for="recipe in showRecipes" :key="recipe.recipeId">
                <v-card class="text-center ma-5"> 
                    <v-card-text>
                        <div class="subheading"><strong>{{ recipe.name }}</strong></div>
                        <div class="gray--text">{{ recipe.description }}</div>
                    </v-card-text>
                    <v-card-actions class="text-center d-flex align-center">
                        <v-tooltip bottom>
                            <template v-slot:activator="{ on, attrs }">
                                <v-btn 
                                icon 
                                v-on="on" 
                                v-bind="attrs"
                                router :to="{name: 'recipeDetails', params: { recipeId:recipe.recipeId} }"
                                ><v-icon medium center>info_outline</v-icon>
                                </v-btn>
                            </template>
                            <span>Recipe Details</span>
                        </v-tooltip>                        
                        <v-divider></v-divider>
                    </v-card-actions>
                        <!-- <v-expand-transition>
                            <div v-show="recipe.show">
                                <v-divider></v-divider>
                                <v-card-text>
                                    <div>Total Time: {{ recipe.duration }}</div>
                                    <v-divider></v-divider>
                                    <div><strong>Ingredients: </strong></div>
                                    <br>
                                    <div>Ingreedants go hear.</div>
                                    <v-divider></v-divider>
                                    <div><strong>Method: </strong></div>
                                    <br>
                                    <div>{{ recipe.recipeMethod }}</div>
                                    <v-divider></v-divider>
                                    <div>Yield: {{ recipe.yieldAmount }} {{recipe.yieldUnit }}</div>
                                </v-card-text>
                            </div>
                        </v-expand-transition> -->
                    </v-card>
                </v-flex>
            </v-layout>
        </v-container>
        </v-card>
    </div>
</template>

<script>
import recipeService from "../services/RecipeService";

export default {
    name: "plan-details-content",
    data() {
        return {
            showRecipes: []
        }
    },
    created() {
        this.retrievePlanDetails();
    },
    methods: {
        retrievePlanDetails() {
            recipeService.getPlanDetails(this.$route.params.planId).then(response => {
                this.$store.commit("GET_PLAN_DETAILS", response.data);
                this.addShow();
            })
        },
        addShow() {
            this.showRecipes = this.$store.state.details.recipeList.map(recipe => ({
                ...recipe,
                show: false,
            }))
        }
    }

}
</script>
<style scoped>
#overview {
    width: 65vw;
    background-color: rgba(255,255,255,0.25) !important;
    min-height: 71vh !important;
    justify-content: center;
    justify-items: center;
    display: flex;
}
h1, h3{
    background-color: rgba(131, 131, 131, 0.5);
    text-align: center;
}
</style>
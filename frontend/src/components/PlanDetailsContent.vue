<template>
    <div data-app>
        <h1 class="subheading black--text text-center">{{this.$store.state.details.mealPlan.plan_name}}</h1>
        <h3 class="small text-center">{{this.$store.state.details.mealPlan.plan_description}}</h3>
        <v-card id="overview">
        <v-container class="plan-details-recipes">
            <v-btn depressed small router :to="{ name: 'myGroceryList', params: {planId:this.$store.state.details.mealPlan.plan_id} }">
                <v-icon>shopping_cart</v-icon>
                view grocery list
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
                            <span>recipe details</span>
                        </v-tooltip>                        
                        <v-divider></v-divider>
                        <v-tooltip bottom>
                            <template v-slot:activator="{ on, attrs }">
                                <v-btn
                                icon
                                v-on="on"
                                v-bind="attrs"
                                @click="deleteRecipeSetup(recipe)"
                                ><v-icon medium center>delete_outline</v-icon>
                                </v-btn>
                            </template>
                            <span>delete recipe from plan</span>
                        </v-tooltip>
                    </v-card-actions>
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
            showRecipes: [],
            selectedRecipe: {
                recipeId: 0,
                name: '',
                description: '',
                yieldAmount: 0,
                yieldUnit: '',
                duration: '',
                recipeMethod: '',
                ownername: '',
                public: false,
            },
            selectedPlan: {},
            mealPlanDTO: {
                mealPlan: {},
                recipeList: []
            }
        }
    },
    created() {
        this.retrievePlanDetails();
    },
    mounted() {
        this.retrieveUserPlans()
    },

    methods: {
        retrievePlanDetails() {
            recipeService.getPlanDetails(this.$route.params.planId).then(response => {
                this.$store.commit("GET_PLAN_DETAILS", response.data);
                this.addShow();
            })
        },
        retrieveUserPlans() {
            recipeService.getUserPlans(this.$store.state.user.username).then(response => {
                this.$store.commit("GET_USER_PLANS", response.data);
            })
        },
        addShow() {
            this.showRecipes = this.$store.state.details.recipeList.map(recipe => ({
                ...recipe,
                show: false,
            }))
        },
        selectRecipe(recipe) {
            this.mealPlanDTO.recipeList.push(recipe);
        },
        selectPlan() {
            this.mealPlanDTO.mealPlan = this.$store.state.details.mealPlan;
        },
        deleteRecipeSetup(recipe) {
            this.selectPlan();
            this.selectRecipe(recipe);
            this.deleteRecipeFromPlan(this.mealPlanDTO);
        },
        deleteRecipeFromPlan(mealPlanDTO) {
            recipeService.deleteRecipeFromPlan(mealPlanDTO).then(response => {
                if(response.status === 204) {
                    alert("Success!")
                    this.$router.go();
                }
            })
        },
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
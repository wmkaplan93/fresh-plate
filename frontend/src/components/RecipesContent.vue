<template>
    <div class="overview" data-app>
        <h1 class="subheading black--text">My&nbsp;Recipes</h1>
        <v-container class="recipe-cards">
        
            <v-layout row wrap>
                <v-flex xs12 sm6 md5 lg4 
                v-for="recipe in showRecipes" 
                :key="recipe.recipeId">
                <v-card class="text-xs-center ma-3">
                    <v-card-text>
                        <div class="subheading"><strong>{{ recipe.name }}</strong></div>
                        <div class="gray--text">{{ recipe.description }}</div>
                    </v-card-text>
                    <v-card-actions class="text-center d-flex align-center">
                        <v-menu light transition="scale-transition" origin="center center">
                            <template v-slot:activator="{ on: menu, attrs }">
                                <v-tooltip bottom>
                                    <template v-slot:activator="{ on: tooltip }">
                                        <v-btn
                                        icon
                                        v-bind="attrs"
                                        v-on="{ ...tooltip, ...menu }"
                                        ><v-icon medium center>playlist_add</v-icon></v-btn>
                                    </template>

                                    <span>Add to My Plans</span>
                                </v-tooltip>
                            </template>
                            <v-list>
                                <v-list-item v-for="plan in $store.state.userPlans" :key="plan.plan_id" @click="addToList()">
                                    <v-list-item-title>{{ plan.plan_name }}</v-list-item-title>
                                </v-list-item>
                            </v-list>
                        </v-menu>
                        <v-divider></v-divider>
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
                        <v-tooltip bottom>
                            <template v-slot:activator="{ on, attrs }">
                                <v-btn 
                                icon 
                                v-on="on" 
                                v-bind="attrs"
                                @click="recipe.show = !recipe.show"
                                ><v-icon medium center>{{ recipe.show ? 'expand_less' : 'expand_more'}}</v-icon>
                                </v-btn>
                            </template>
                            <span>{{recipe.show ? "Less Information" : "More Information"}}</span>
                        </v-tooltip>
                    </v-card-actions>
                    <v-expand-transition>
                        <div v-show="recipe.show">
                            <v-divider></v-divider>
                            <v-card-text>
                                <div>Total Time: {{ recipe.duration }}</div>
                                <v-divider></v-divider>
                                <div><strong>Method: </strong></div>
                                <br>
                                <div>{{ recipe.recipeMethod }}</div>
                                <v-divider></v-divider>
                                <div>Yield: {{ recipe.yield }}</div>
                            </v-card-text>
                        </div>
                    </v-expand-transition>
                </v-card>
                </v-flex>
            </v-layout>

        </v-container>
    </div>
</template>

<script>
import recipeService from "../services/RecipeService";

export default {
    name: "recipes-content",
    data() {
        return {
            showRecipes: []
        }
    },
    created() {
        this.retrieveUserRecipes();
    },
    // mounted() {
    //     this.addShow();
    // },
    methods: {
        retrieveUserRecipes() {
            recipeService.getUserRecipes(this.$store.state.user.username).then(response => {
                this.$store.commit("GET_USER_RECIPES", response.data);
                this.addShow();
            })
        },
        addShow() {
            this.showRecipes = this.$store.state.userRecipes.map(recipe => ({
                ...recipe,
                show: false,
                username: this.$store.state.user.username
            }))
        },    
        addToList() {
            alert("Success")
            return '';
        },    
    }
}
</script>
<style scoped>
#overview {
    display: flex;
    width: 65vw;
}
</style>
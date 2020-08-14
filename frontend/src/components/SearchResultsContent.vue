<template>
    <div data-app>
        <h1 class="subheading black--text">search results</h1>
        <v-card id="overview">
        <v-container class="recipe-cards">
        
            <v-layout row wrap>
                <v-flex xs12 sm6 md4 lg4 
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

                                    <span>add to my plans</span>
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
                                router :to="{name: 'recipeDetails', params: { recipeId:recipe.recipeId} }">
                                    <v-icon medium center>info_outline</v-icon>
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
                                @click="recipe.show = !recipe.show"
                                ><v-icon medium center>{{ recipe.show ? 'expand_less' : 'expand_more'}}</v-icon>
                                </v-btn>
                            </template>
                            <span>{{recipe.show ? "less Information" : "more Information"}}</span>
                        </v-tooltip>
                    </v-card-actions>
                    <v-expand-transition>
                        <div v-show="recipe.show">
                            <v-divider></v-divider>
                            <v-card-text>
                                <div>total time: {{ recipe.duration }}</div>
                                <v-divider></v-divider>
                                <div>yield: {{ recipe.yieldAmount }} {{recipe.yieldUnit }}</div>
                            </v-card-text>
                        </div>
                    </v-expand-transition>
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
    name: "search-results-content",
    components: {
    },
    data() {
        return {
            filteredRecipes: [],
            showRecipes: []
        }
    },
    updated() {
        this.retrieveRecipes();
    },
    mounted() {
        this.filteredRecipes = [];
        this.showRecipes = [];
    },
    methods: {
        retrieveRecipes() {
            recipeService.searchRecipes(this.$route.params.searchTerm).then(response => {
                this.filteredRecipes = response.data;
                this.addShow();
            })
        },
        addToList() {
            alert("Success")
            return '';
        },
        addShow() {
            this.showRecipes = this.filteredRecipes.map(recipe => ({
                ...recipe,
                show: false,
                username: this.$store.state.user.username
            }))
        },
    }
}
</script>
<style scoped>
#overview {
    width: 65vw;
    background-color: rgba(255,255,255,0.25) !important;
    min-height: 74vh !important;
    justify-content: center;
    justify-items: center;
    display: flex;
}

h1{ 
    text-align: center !important;
}
</style>
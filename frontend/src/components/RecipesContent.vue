<template>
    <div class="overview" data-app>
        <h1 class="subheading black--text">My Recipes</h1>
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
                                <v-list-item v-for="(item, index) in items" :key="index" @click="addToList()">
                                    <v-list-item-title>{{ item.title }}</v-list-item-title>
                                </v-list-item>
                            </v-list>
                        </v-menu>
                        <v-divider></v-divider>
                        <!-- <v-tooltip bottom>
                            <template v-slot:activator="{ on, attrs }">
                                <v-btn 
                                icon 
                                v-on="on" 
                                v-bind="attrs"
                                @click="recipe.isFavorite = !recipe.isFavorite"
                                ><v-icon medium center>{{ recipe.isFavorite ? 'favorite' : 'favorite_border'}}</v-icon>
                                </v-btn>
                            </template>
                            <span>{{recipe.isFavorite ? "Remove from My Recipes" : "Add to My Recipes"}}</span>
                        </v-tooltip>
                        <v-divider></v-divider> -->
                        <v-tooltip bottom>
                            <template v-slot:activator="{ on, attrs }">
                                <v-btn 
                                icon 
                                class="blue--text" 
                                v-on="on" 
                                v-bind="attrs"
                                @click="show = !show"
                                ><v-icon medium center>{{ !show ? 'expand_more' : 'expand_less'}}</v-icon>
                                </v-btn>
                            </template>
                            <span>{{show ? "Less Information" : "More Information"}}</span>
                        </v-tooltip>
                    </v-card-actions>
                    <v-expand-transition>
                        <div v-show="show">
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
            showRecipes: [],
            items: [
                { title: 'Sunday Splurge' },
                { title: 'Rabbit Food' },
                { title: 'Carnivore Crunch' },
                { title: 'Midnight Munchies' },
                { title: 'Everything is Cake' }
            ],
        }
    },
    created() {
        this.retrieveUserRecipes();
    },
    mounted() {
        this.addShow();
    },
    methods: {
        retrieveUserRecipes() {
            recipeService.getUserRecipes().then(response => {
                this.$store.commit("GET_USER_RECIPES", response.data);
            })
        },
        addShow() {
            this.showRecipes = this.$store.state.allRecipes.map(recipe => ({
                ...recipe,
                show: false,
                username: this.$store.state.user.username
            }))
        },    
    }
}
</script>
<style scoped>
#recipes-content {
    display: flex;
    justify-content: center;
    width: 65vw;
    background: rgba(120,36,194,0.1);
}
#recipes-title {
    font-size: 8vh;
    text-decoration: underline overline;
}

#user-recipes {
    display: flex;
    justify-content: center;
    align-self: flex-start;
}
</style>
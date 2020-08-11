<template>
    <div class="overview" data-app>
        <h1 class="subheading black--text">Explore Recipes</h1>
        <v-container class="recipe-cards">
        
            <v-layout row wrap>
                <v-flex xs12 sm6 md4 lg3 
                v-for="recipe in this.$store.state.allRecipes" 
                :key="recipe.recipeId">
                <v-card class="text-xs-center ma-3">
                    <!-- <v-responsive class="pt-4">
                        image goes here
                    </v-responsive> -->
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
                                        color="red"
                                        v-bind="attrs"
                                        v-on="{ ...tooltip, ...menu }"
                                        ><v-icon medium center>playlist_add</v-icon></v-btn>
                                    </template>

                                    <span>Add to My Recipes</span>
                                </v-tooltip>
                            </template>
                            <v-list>
                                <v-list-item v-for="(item, index) in items" :key="index" @click="addToList()">
                                    <v-list-item-title>{{ item.title }}</v-list-item-title>
                                </v-list-item>
                            </v-list>
                        </v-menu>
                        <v-divider></v-divider>
                        <v-tooltip bottom>
                            <template v-slot:activator="{ on, attrs }">
                                <v-btn 
                                icon 
                                color="blue" 
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
import recipesService from "../services/RecipeService";

export default {
    name: "explore-recipes-content",
    data: () => ({
            recipes: [],
            show: false,
            items: [
                { title: 'Sunday Splurge' },
                { title: 'Rabbit Food' },
                { title: 'Carnivore Crunch' },
                { title: 'Midnight Munchies' },
                { title: 'Everything is Cake' }
            ],
    }),
    created() {
        this.retrieveRecipes();
    },
    methods: {
        retrieveRecipes() {
            recipesService.getRecipes().then(response => {
                this.$store.commit("GET_RECIPES", response.data);
            })
        },
        addToList() {
            alert("Success")
            return '';
        }
    }
}
</script>
<style scoped>
#explore-recipes-title {
    font-size: 8vh;
    text-decoration: underline overline;
    display: flex;
    justify-content: center;
    align-self: flex-start;
}

#explore-recipes {
    display: flex;
    justify-content: center;
    width: 65vw;
    background: rgba(170,12,7,0.1);
    align-items: center;
}

.recipe {
    background-color: pink;
    padding: 10px;
    margin: 10px;
    flex: 1;
    text-align: center;
}
/*
.loading {
    flex: 1;
}*/
</style>
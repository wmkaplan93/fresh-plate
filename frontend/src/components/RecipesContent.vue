<template>
    <div class="overview" data-app>
        <h1 class="subheading black--text">My Recipes</h1>
        <v-container class="recipe-cards">
        
            <v-layout row wrap>
                <v-flex xs12 sm6 md5 lg4 
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
                        <v-tooltip bottom>
                            <template v-slot:activator="{ on, attrs }">
                                <v-btn
                                icon
                                color="red"
                                v-bind="attrs"
                                v-on="on"
                                ><v-icon medium center>remove_circle_outline</v-icon></v-btn>
                            </template>

                            <span>Remove from My Recipes</span>
                        </v-tooltip>
                        <v-divider></v-divider>
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
export default {
    name: "recipes-content",
    data() {
        return {
            show: false
        }
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
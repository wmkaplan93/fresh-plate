<template>
    <div id="explore-recipes-content">
        <h1 id="explore-recipes-title">Explore Recipes</h1>
        <div id="explore-recipes">  
            <!-- <div class="loading" v-if="isLoading">
                <img src="../../public/loader-for-dribble.gif">
            </div> -->
            <router-link :to="{ name: 'RecipeDetails', params: { recipeId: recipe.recipeId } }"
                class="recipe"
                v-for="recipe in this.$store.state.allRecipes"
                v-bind:key="recipe.recipeId"
                tag="div"
            >
            {{ recipe.name }} <br>
            {{ recipe.description }}
            </router-link>
        </div>
    </div>
</template>

<script>
import recipesService from "../services/RecipeService";

export default {
    name: "explore-recipes-content",
    data() {
        return {
            recipes: [],
            isLoading: true
        };
    },
    created() {
        this.retrieveRecipes();
    },
    methods: {
        retrieveRecipes() {
            recipesService.getRecipes().then(response => {
                this.$store.commit("GET_RECIPES", response.data);
            })
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
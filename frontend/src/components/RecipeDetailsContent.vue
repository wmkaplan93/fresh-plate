<template>
    <v-card max-width="600" class="mx-auto" id="overview">
        <v-container>
            <v-row dense>
                <v-col cols="12">
                    <v-card>
                        <v-card-title class="headline">
                            {{this.$store.state.recipeDetails.name}}
                        </v-card-title>
                        <v-card-subtitle>
                            {{this.$store.state.recipeDetails.description}}
                        </v-card-subtitle>
                    </v-card>
                </v-col>
                <v-divider></v-divider>
                <v-col cols="12">
                    <v-card>
                        <v-card-title class="headline">types</v-card-title>
                        <v-card-subtitle>| <span v-for="type in $store.state.recipeDetails.typeList" :key="type.typeId">{{type.type}} | </span></v-card-subtitle>
                    </v-card>
                </v-col>
                <v-divider></v-divider>
                <v-col cols="12">
                    <v-card>
                        <v-card-title class="headline">yield</v-card-title>
                        <v-card-subtitle>
                            {{this.$store.state.recipeDetails.yieldAmount}}
                            {{this.$store.state.recipeDetails.yieldUnit}}
                        </v-card-subtitle>
                    </v-card>
                </v-col>
                <v-col cols="12">
                    <v-card>
                        <v-card-title class="headline">total time</v-card-title>
                        <v-card-subtitle>
                            {{this.$store.state.recipeDetails.duration}}
                        </v-card-subtitle>
                    </v-card>
                </v-col>
                <v-col cols="12">
                    <v-card>
                        <v-card-title class="headline">ingredients</v-card-title>
                        <v-card-subtitle v-for="item in $store.state.recipeDetails.ingredientList" :key="item">
                            {{item.quantity}}
                            {{item.unitName}} - 
                            {{item.ingredientName}}
                        </v-card-subtitle>
                    </v-card>
                </v-col>
                <v-col cols="12">
                    <v-card>
                        <v-card-title class="headline">method</v-card-title>
                        <v-card-subtitle>
                            {{this.$store.state.recipeDetails.recipeMethod}}
                        </v-card-subtitle>
                    </v-card>
                </v-col>
            </v-row>
        </v-container>
    </v-card>
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
            recipeService.getRecipeDetails(this.$route.params.recipeId).then(response => {
                this.$store.commit("GET_RECIPE_DETAILS", response.data);
            })
        }
    }

}
</script>
<style scoped>
#overview {
    width: 65vw;
    min-width: 50vw;
    justify-content: center;
    justify-items: center;
    display: flex;
}
</style>
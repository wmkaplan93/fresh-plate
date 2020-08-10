<template>
    <div id="recipe-details-content">
        <div id="recipe-details">
            <h1 id="recipe-details-title"> {{ this.$store.state.recipe.name }}</h1>
        </div>
    </div>
</template>

<script>
export default {
    name: "recipe-details-content",
    computed: {
        recipe() {
            return this.$store.state.allRecipes.find((r) => {
                r.recipeId == this.$route.params.recipeId
            });
        }
    }
}
</script>
<style scoped>
#recipe-details-content {
    display: flex;
    justify-content: center;
    width: 65vw;
    background: rgba(194,168,36,0.1);
}
#recipe-details-title {
    font-size: 8vh;
    text-decoration: underline overline;
}

#recipe-details {
    display: flex;
    justify-content: center;
    align-self: flex-start;
}
</style>
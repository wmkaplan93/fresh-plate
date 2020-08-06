<template>
    <div id="explore-recipes-content">
        <div id="explore-recipes">
            <h1 id="explore-recipes-title">Explore Recipes</h1>
        </div>
    </div>
</template>

<script>
export default {
    name: "explore-recipes-content"
}
</script>
<style scoped>
#explore-recipes-content {
    display: flex;
    justify-content: center;
    width: 65vw;
    background: rgba(170,12,7,0.1);
}
#explore-recipes-title {
    font-size: 8vh;
    text-decoration: underline overline;
}

#explore-recipes {
    display: flex;
    justify-content: center;
    align-self: flex-start;
}
</style>
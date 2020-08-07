<template>
    <div id="recipes-content">
        <div id="user-recipes">
            <h1 id="recipes-title">My Recipes</h1>
        </div>
    </div>
</template>

<script>
export default {
    name: "recipes-content"
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
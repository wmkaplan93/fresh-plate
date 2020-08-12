<template>
    <div id="overview">
        <user-landing-sidebar />  
        <recipes-content />
        <div id="add-recipe-button">
            <router-link v-bind:to="{ name: 'addRecipe', params: {username:username} }">
            <button type="add-recipe">Add New Recipe</button>
            </router-link> 
        </div>  
    </div>
</template>

<script>
import UserLandingSidebar from "../components/LandingSidebar";
import RecipesContent from "../components/RecipesContent";

export default {
    components: {
        UserLandingSidebar,
        RecipesContent
    },
    data() {
        return {
            username: this.$store.state.user.username

        }
    }
}
</script>
<style scoped>
#overview {
    display: flex;
    justify-content: right;
}
#recipes-title {
    font-family: 'Allan';
    font-size: 10vh;
    text-decoration: underline overline;
}

#user-recipes {
    display: flex;
    width: 67%;
    justify-content: center;
    align-self: flex-start;
}

button {
    background: rgba(255, 255, 255, .6);
    margin-left: 10vw;
    border: 2px solid;
}

a {
    color: black;
}
</style>
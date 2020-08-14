<template>
    <div id="user-landing-sidebar">
        <h1 id="user-landing-title">Welcome</h1>
        <h1 id="user-landing-name">{{username}}</h1>
        <div id="button-list">
            <router-link v-bind:to="{ name: 'myPlans', params: {username:username} }">
                <button type="my-plans">my plans</button>
            </router-link>
            <router-link v-bind:to="{ name: 'myRecipes', params: {username:username} }">
                <button type="my-recipes">my recipes</button>
            </router-link>
            <!-- <router-link v-bind:to="{ name: 'myGroceryList', params: {username:username} }">
                <button type="my-grocery-list">My Grocery List</button>
            </router-link> -->
            <router-link v-bind:to="{ name : 'exploreRecipes' }">
                <button type="exploreRecipes">explore recipes</button>
            </router-link>
            <router-link v-bind:to="{ name: 'addRecipe', params: {username:username} }">
                <button type="add-recipe">add new recipe</button>
            </router-link> 
            <router-link v-bind:to="{ name: 'addMealPlan', params: {username:username} }">
                <button type="add-recipe">new meal plan</button>
            </router-link> 
        </div>
    </div>
</template>

<script>
export default {
    name: "user-landing-sidebar",
    data() {
        return {
            username: this.$store.state.user.username,
            // isLoading: true
        }
    }
}

</script>

<style scoped>
#user-landing-sidebar {
    width: 33vw;
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 90vh;
    font-family: 'Lora';
    border-right: 10px solid rgba(74, 74, 74, .5);
    background-color:rgba(255, 255, 255, 0.35);
}

a {
    text-decoration: none;
}

.router-link-active > button {
    background: rgba(0,108,8,0.7);
}

h1 {
    line-height: .1;
    /* width: 90%; */
    justify-self: end;
    display: flex;
    align-self: center;
}

#user-landing-title {
    padding-top: 6vh;
    font-size: 5vh;
    padding-bottom: 6vh;
}

#user-landing-name {
    padding-bottom: 1.5vh;
    line-height: 1;
    font-size: 7.5vh;
    word-wrap: break-word;
}

#button-list {
    border-top: 8px solid rgba(74, 74, 74, .5);
    /* width: 75%;
    align-items: center;
    justify-content: center; */
}

#button-list button{
    margin-top: 20px;
    margin-bottom: 20px;
    flex-direction: column;
    display: flex;
    border-radius: 0px;
    background-color: rgba(255, 255, 255, 0.35);
    width: 17vw;
    height: 4.5vh;
    font-size: 3vh;
    font-family: 'Allan';
    align-items: center;
}

a {
    color: black;
}
</style>
<template>
    <div id="user-landing-sidebar">
        <link href='https://fonts.googleapis.com/css?family=Allan' rel='stylesheet'>
        <h1 id="user-landing-title">Welcome</h1>
        <h1 id="user-landing-name"> {{name}}</h1>
        <div id="button-list">
            <router-link id="my-plans-button" v-bind:to="{ name: 'myPlans' }">
                <button type="my-plans">My Plans</button>
            </router-link>
            <router-link id="my-recipes-button" v-bind:to="{ name: 'myRecipes' }">
                <button type="my-recipes">My Recipes</button>
            </router-link>
            <router-link id="my-grocery-list-button" v-bind:to="{ name: 'myGroceryList' }">
                <button type="my-grocery-list">My Grocery List</button>
            </router-link>
            <router-link id="explore-button" v-bind:to="{ name : 'explore' }">
                <button type="explore">Explore Recipes</button>
            </router-link>
        </div>
    </div>
</template>

<script>
export default {
    name: "user-landing-sidebar",
    data() {
        return {
            name: this.$store.state.user.username

        }
    }
}

</script>

<style scoped>
#user-landing-sidebar {
    width: 33.5%;
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 90vh;
    font-family: 'Allan';
    border-right: 10px solid rgba(74, 74, 74, .5);
    background-color:rgba(255, 255, 255, 0.5)
}

h1 {
    line-height: .1;
}

#user-landing-title {
    padding-top: 10vh;
}
#user-landing-name {
    padding-bottom: 1.5vh;
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
    background-color: rgba(255, 255, 255, 0.2);
    width: 175px;
    height: 30px;
    font-size: 3vh;
}
</style>
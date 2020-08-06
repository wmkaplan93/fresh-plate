<template>
    <div id="overview">
        <user-landing-sidebar />
        <div id="user-recipes">
            <h1 id="recipes-title">My Recipes</h1>
        </div>
    </div>
</template>

<script>
import UserLandingSidebar from "../components/LandingSidebar";

export default {
    components: {
        UserLandingSidebar
    },
    data() {
        return {
            name: this.$store.state.user.username

        }
    }
}
</script>
<style scoped>
#overview {
    display: flex;
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
</style>
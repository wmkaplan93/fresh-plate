<template>
    <div id="overview">
        <user-landing-sidebar />
        <div id="explore-recipes">
            <h1 id="explore-title">Explore Recieps</h1>
        </div>
    </div>
</template>

<script>
import UserLandingSidebar from "../components/LandingSidebar";

export default {
    components: {
        UserLandingSidebar
    }
}
</script>
<style scoped>
#overview {
    display: flex;
}
#explore-title {
    font-family: 'Allan';
    font-size: 10vh;
    text-decoration: underline overline;
}

#explore-recipes {
    display: flex;
    width: 67%;
    justify-content: center;
    align-self: flex-start;
}
</style>
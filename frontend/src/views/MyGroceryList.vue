<template>
    <div id="overview">
        <user-landing-sidebar />
        <div id="user-grocery-list">
            <h1 id="grocery-list-title">My Grocery List</h1>
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
#grocery-list-title {
    font-family: 'Allan';
    font-size: 10vh;
    text-decoration: underline overline;
}

#user-grocery-list {
    display: flex;
    width: 67%;
    justify-content: center;
    align-self: flex-start;
}
</style>
<template>
    <div id="overview">
        <user-landing-sidebar />
        <div id="user-plans">
            <h1 id="plans-title">My Plans</h1>
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
#plans-title {
    font-family: 'Allan';
    font-size: 10vh;
    text-decoration: underline overline;
}

#user-plans {
    display: flex;
    width: 66%;
    justify-content: center;
    align-self: flex-start;
}
</style>
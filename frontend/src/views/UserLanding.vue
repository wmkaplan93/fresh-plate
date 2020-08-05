<template>
    <div id="user-landing">
        <link href='https://fonts.googleapis.com/css?family=Allan' rel='stylesheet'>
        <user-landing-sidebar />

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

<style>
/* #user-landing {
    display: grid;
    grid-template-columns: 30% 70%;
    grid-template-rows: 25% 15% 15% 15% 30%;
}

#user-landing-title {
    font-size: 8vw;
    font-family: "Allan";
} */
</style>
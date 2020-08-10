<template>
    <div id="overview">
        <user-landing-sidebar />
        <recipe-details-content />
    </div>
</template>
<script>
import UserLandingSidebar from "../components/LandingSidebar";
import RecipeDetailsContent from "../components/RecipeDetailsContent";

export default {
    components: {
        UserLandingSidebar,
        RecipeDetailsContent
    }
}
</script>
<style scoped>
#overview {
    display: flex;
}
#recipes-title {
    font-family: 'Lora';
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
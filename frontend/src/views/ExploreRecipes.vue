<template>
    <div id="overview">
        <user-landing-sidebar />
        <explore-recipes-content />
    </div>
</template>

<script>
import UserLandingSidebar from "../components/LandingSidebar";
import ExploreRecipesContent from "../components/ExploreRecipesContent";

export default {
    components: {
        UserLandingSidebar,
        ExploreRecipesContent
    }
}
</script>
<style scoped>
#overview {
    display: flex;
}
</style>
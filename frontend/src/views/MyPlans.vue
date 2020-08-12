<template>
    <div id="overview">
        <user-landing-sidebar />
        <plans-content />
    </div>
</template>

<script>
import UserLandingSidebar from "../components/LandingSidebar";
import PlansContent from "../components/PlansContent";

export default {
    components: {
        UserLandingSidebar,
        PlansContent
    }
}
</script>
<style scoped>
#overview {
    display: flex;
}

</style>
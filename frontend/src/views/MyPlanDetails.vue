<template>
    <div id="overview">
        <user-landing-sidebar />
        <plan-details-content />
    </div>
</template>

<script>
import UserLandingSidebar from "../components/LandingSidebar";
import PlanDetailsContent from "../components/PlanDetailsContent";

export default {
    components: {
        UserLandingSidebar,
        PlanDetailsContent
    }
}
</script>

<style scoped>
#overview {
    display: flex;
}
</style>
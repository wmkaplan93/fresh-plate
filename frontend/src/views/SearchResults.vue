<template>
    <div id="overview">
        <user-landing-sidebar />
        <search-results-content />
    </div>
</template>

<script>
import UserLandingSidebar from "../components/LandingSidebar";
import SearchResultsContent from "../components/SearchResultsContent";

export default {
    components: {
        UserLandingSidebar,
        SearchResultsContent
    }
}
</script>
<style scoped>
#overview {
    display: flex;
}
</style>
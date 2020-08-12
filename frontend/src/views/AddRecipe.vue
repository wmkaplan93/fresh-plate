<template>
    <div id="overview">
        <user-landing-sidebar />
        <add-recipe-form /> 
    </div>
</template>

<script>
import UserLandingSidebar from "../components/LandingSidebar";
import AddRecipeForm from "../components/RecipeForm";


export default {
    components: { 
        AddRecipeForm,
        UserLandingSidebar
    }
}
</script>

<style scoped>
#overview {
    display: flex;
}
</style>


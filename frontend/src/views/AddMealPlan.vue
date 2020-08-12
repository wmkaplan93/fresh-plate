<template>
  <div id="overview">
      <user-landing-sidebar />
      <add-meal-plan-content />
  </div>
</template>

<script>
import UserLandingSidebar from "../components/LandingSidebar";
import AddMealPlanContent from "../components/AddMealPlanContent";

export default {
    components: {
        UserLandingSidebar,
        AddMealPlanContent
    }
}
</script>

<style>
#overview {
    display: flex;
}
</style>
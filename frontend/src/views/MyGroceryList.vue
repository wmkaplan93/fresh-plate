<template>
    <div id="overview">
        <user-landing-sidebar />
        <grocery-list-content />
        <!-- <div id="user-grocery-list">
            <h1 id="grocery-list-title">My Grocery List</h1>
        </div> -->
    </div>
</template>

<script>
import UserLandingSidebar from "../components/LandingSidebar";
import GroceryListContent from "../components/GroceryListContent";

export default {
    components: {
        UserLandingSidebar,
        GroceryListContent
    }
}
</script>

<style scoped>
#overview {
    display: flex;
}


/* #overview {
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
} */
</style>

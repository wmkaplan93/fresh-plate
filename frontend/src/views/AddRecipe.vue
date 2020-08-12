<template>
    <div id="container">
       
        <div id="form">
            <add-recipe-form /> 
        </div>    
    </div>
</template>

<script>

import AddRecipeForm from "../components/RecipeForm";


export default {
    components: { 
        AddRecipeForm 
    },
    data() {
        return {
            name: this.$store.state.user.username
        }
    }
}
</script>

<style scoped>
#overview {
    display: flex;
}

#form {
    display: flex;
    justify-content: flex-end;
    align-self: flex-start;
}

</style>


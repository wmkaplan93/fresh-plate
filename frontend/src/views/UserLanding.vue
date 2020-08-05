<template>
    <div id="user-landing">
        <link href='https://fonts.googleapis.com/css?family=Allan' rel='stylesheet'>
        <h1 id="user-landing-title">Welcome {{name}}</h1>


    </div>
</template>

<script>
export default {
    data() {
        return {
            name: this.$store.state.user.username

        }
    }
    

}

</script>

<style>
#user-landing {
    display: grid;
    grid-template-columns: 30% 70%;
    grid-template-rows: 25% 15% 15% 15% 30%;
}

#user-landing-title {
    font-size: 8vw;
    font-family: "Allan";
}
</style>
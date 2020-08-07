<template>
    <div id="plans-content">
        <div id="user-plans">
            <h1 id="plans-title">My Meal Plans</h1>
        </div>
    </div>
</template>

<script>
export default {
    name: "plans-content"
}
</script>
<style scoped>
#plans-content {
    display: flex;
    justify-content: center;
    width: 65vw;
    background: rgba(194,168,36,0.1);
}
#plans-title {
    font-size: 8vh;
    text-decoration: underline overline;
}

#user-plans {
    display: flex;
    justify-content: center;
    align-self: flex-start;
}
</style>
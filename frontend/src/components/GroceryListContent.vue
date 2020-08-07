<template>
    <div id="grocery-list-content">
        <div id="user-grocery-list">
            <h1 id="grocery-list-title">My Grocery List</h1>
        </div>
    </div>
</template>

<script>
export default {
    name: "grocery-list-content"
}
</script>
<style scoped>
#grocery-list-content {
    display: flex;
    justify-content: center;
    width: 65vw;
    background: rgba(36,87,194,0.1);
}
#grocery-list-title {
    font-size: 8vh;
    text-decoration: underline overline;
}

#user-grocery-list {
    display: flex;
    justify-content: center;
    align-self: flex-start;
}
</style>
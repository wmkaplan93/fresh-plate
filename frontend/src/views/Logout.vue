<template>
  <h1>logout</h1>
</template>

<script>
export default {
  created() {
    this.$store.commit("LOGOUT");
    alert("Are you sure you would like to log out?");
    this.$router.push("/login");
  }
};
</script>

<template>
  <h1>Logout</h1>
</template>

<script>
export default {
  created() {
    this.$store.commit("LOGOUT");
    alert('Are you sure you want to logout?');
    this.$router.push("/login");
  }
};
</script>

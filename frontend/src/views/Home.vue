<template>
  <div class="home">
    <link href='https://fonts.googleapis.com/css?family=Allan' rel='stylesheet'>
    <h1>Meal Planner</h1>
    <router-link id= "login" v-bind:to="{ name: 'login' }">
      <button type="login">Login</button>
    </router-link>
    <router-link id="register" v-bind:to="{ name: 'register' }">
      <button type="register">Register</button>
    </router-link>
  </div>
</template>

<script>

export default {
  name: "home"
};
</script>
<style scoped>
div {
  font-family:"Allan";
  display: grid;
  grid-template-columns: 8% 8% 8% 26% 50%;
  grid-template-rows: 15% 15% 40% 15% 15%;
  height: 90vh;
}

h1 {
  font-size: 15vw;
  grid-column-start: 4;
  grid-column-end: 6;
  grid-row-start: 3;
  grid-row-end: 4;
  align-self: center;
}

#login {
  grid-column-start: 4;
  grid-column-end: 5;
  justify-self: end;
  grid-row-start: 4;
  grid-row-end: 5;
  align-self: center;
}

#register {
  grid-column-start: 5;
  justify-self: start;
  grid-row-start: 4;
  grid-row-end: 5;
  align-self: center;
  margin-left: 10px;
}
#register button {
  border-radius: 8px;
  font-size: 25px;
  font-family: "Allan";
}

#login button {
  border-radius: 8px;
  font-size: 25px;
  font-family: "Allan";
}


</style>

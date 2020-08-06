<template>
  <div class="home">
    <link href='https://fonts.googleapis.com/css?family=Lora' rel='stylesheet'>
    <h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Fresh Plate</h1>
    <router-link id= "login-button" v-bind:to="{ name: 'login' }">
      <button type="login">Login</button>
    </router-link>
    <router-link id="register-button" v-bind:to="{ name: 'register' }">
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
.home {
  font-family:"Lora";
  display: grid;
  grid-template-columns: 8% 8% 8% 26% 50%;
  grid-template-rows: 15% 15% 40% 15% 15%;
  height: 90vh;
}

h1 {
  font-size: 9vw;
  grid-column-start: 4;
  grid-column-end: 6;
  grid-row-start: 2;
  grid-row-end: 4;
  align-self: center;
}

#login-button {
  grid-column-start: 4;
  grid-column-end: 5;
  grid-row-start: 4;
  grid-row-end: 5;
  align-self: center;
  justify-self: end;
}

#register-button {
  grid-column-start: 5;
  grid-row-start: 4;
  grid-row-end: 5;
  align-self: center;
  justify-self: start;
  margin-left: 10px;
}
#register-button button {
  border-radius: 8px;
  font-size: 25px;
  font-family: "Lora";
}

#login-button button {
  border-radius: 8px;
  font-size: 25px;
  font-family: "Lora";
}


</style>

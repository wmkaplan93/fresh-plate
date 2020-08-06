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
  grid-template-columns: 8% 8% 8% 46% 30%;
  grid-template-rows: 15% 15% 30% 25% 15%;
  height: 90vh;
}

h1 {
  font-size: 9vw;
  grid-column-start: 4;
  grid-column-end: 6;
  grid-row-start: 3;
  grid-row-end: 4;
  align-self: center;
  justify-self: center;
}

#login-button {
  grid-column-start: 4;
  grid-column-end: 5;
  grid-row-start: 4;
  grid-row-end: 5;
  align-self: start;
  justify-self: end;
}

#register-button {
  grid-column-start: 5;
  grid-row-start: 4;
  grid-row-end: 5;
  align-self: start;
  justify-self: start;
  margin-left: 10px;
}
#register-button button {
  border-radius: 0px;
  font-size: 25px;
  font-family: "Lora";
}

#login-button button {
  border-radius: 0px;
  font-size: 25px;
  font-family: "Lora";
}


</style>

<template>
  <div class="home">
    <link href='https://fonts.googleapis.com/css?family=Lora' rel='stylesheet'>
    <h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;fresh plate.</h1>
    <router-link id= "login-button" v-bind:to="{ name: 'login' }">
      <button type="login">login</button>
    </router-link>
    <router-link id="register-button" v-bind:to="{ name: 'register' }">
      <button type="register">register</button>
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

a {
  color: black;
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
  background-color: rgba(255, 255, 255, .6);
}

#register-button {
  grid-column-start: 5;
  grid-row-start: 4;
  grid-row-end: 5;
  align-self: start;
  justify-self: start;
  margin-left: 10px;
  background-color: rgba(255, 255, 255, .6);
}

#register-button button,
#login-button button {
  border-radius: 0px;
  font-size: 4vh;
  height: 5vh;
  font-family: "Lora";
}
</style>

<template>
  <div id="app">
    <div id="nav">
      <link href='https://fonts.googleapis.com/css?family=Lora' rel='stylesheet'>
      <router-link v-bind:to="{ name: 'home' }" v-if="$store.state.token == ''">
        <button type="home-button"><v-icon small>home</v-icon> Home</button>
      </router-link>
      <router-link v-bind:to="{ name: 'UserLanding', params: {username: this.$store.state.user.username} }" v-else>
        <button type="my-home-button"><v-icon small>home</v-icon> My Home</button>
      </router-link>
      <router-link v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''">
        <button type="login-button"><v-icon small>login</v-icon> Login</button>
      </router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-else>
        <button type="logout-button"><v-icon small>directions_run</v-icon> Logout</button>
      </router-link>
      <router-link v-bind:to="{ name: 'register' }" v-if="$store.state.token == ''">
        <button type="register-button"><v-icon small>how_to_reg</v-icon> Register</button>
      </router-link>
      <a @click="$router.go(-1)" v-else><button type="back-button"><v-icon small>keyboard_return</v-icon> Return</button></a>
    </div>
    <router-view />
    <footer>
      <a id="attribution" href='https://www.freepik.com/photos/background'>Background by freepik.com</a>
    </footer>
  </div>
</template>
<style scoped>
#nav {
  background: linear-gradient(90deg, rgba(156, 13, 8, 0.7) 0%, rgba(255,255,255,.5) 60%);
  height: 5vh;
  font-size: 22px;
  font-family: 'Lora';
  /*istock web is our pairing font*/
  color: black;
  justify-content: flex-start;
  align-content: flex-start;
  display: flex;
}

a {
  color: black;
}

#app {
  background-image: url("../public/background options/O8RDSC0.jpg");
  background-size: cover;
  min-height: 100vh;
  width: 100%;
  display: flex;
  flex-direction: column;
}

footer {
  display: flex;
  justify-content: flex-end;
  align-content: flex-end;
}

#nav button {
  border-radius: 0px;
  background-color: rgba(255, 255, 255, .2);
  width: 20vw;
  height: 4vh;
  font-size: 3vh;
  font-family: 'Allan';
  margin: .5vh 1vw 0vw 1vw;
}

</style>

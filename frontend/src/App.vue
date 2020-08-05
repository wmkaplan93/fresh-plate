<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }" v-if="$store.state.token == ''">
        <button type="home-button">Home</button>
      </router-link>
      <router-link v-bind:to="{ name: 'UserLanding' }" v-else>
        <button type="my-home-button">My Home</button>
      </router-link>
      <router-link v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''">
        <button type="login-button">Login</button>
      </router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-else>
        <button type="logout-button">Logout</button>
      </router-link>
      <router-link v-bind:to="{ name: 'register' }" v-if="$store.state.token == ''">
        <button type="register-button">Register</button>
      </router-link>
    </div>
    <router-view />
    <footer>
      <a id="attribution" href='https://www.freepik.com/photos/background'>Background by freepik.com</a>
    </footer>
  </div>
</template>
<style scoped>
#nav {
  background: linear-gradient(90deg, rgba(117,12,12,.75) 0%, rgba(255,255,255,.75) 40%);
  height: 5vh;
  font-size: 22px;
  font-family: 'Allan';
  color: black;
  justify-content: flex-start;
  align-content: flex-start;
  display: flex;
}
#app {
  background-image: url("../public/2489615.jpg");
  /* background-image: url("../public/background options/OH3C840.jpg"); */
  /* background-image: url("../public/background options/474550-PG4GM2-121.jpg"); */
  /* background-image: url("../public/background options/474574-PG4GQP-297.jpg"); */
  /* background-image: url("../public/background options/O8RDSC0.jpg"); */
  background-size: cover;
  background-repeat: no-repeat;
  height: 100%;
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

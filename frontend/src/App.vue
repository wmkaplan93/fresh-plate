<template>
  <div id="app">
    <div id="nav">
      &nbsp;&nbsp;<router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'login' }">Login</router-link>&nbsp;&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'register' }">Register</router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
    <footer>
      <a id="attribution" href='https://www.freepik.com/photos/background'>Background photo created by freepik - www.freepik.com</a>
    </footer>
  </div>
</template>
<style scoped>
#nav {
  background: linear-gradient(90deg, rgba(117,12,12,.75) 0%, rgba(255,255,255,.75) 40%);
  height: 5vh;
  font-size: 22px;
  justify-content: flex-start;
  align-content: flex-end;
}
#app {
  background-image: url("../public/2489615.jpg");
  background-size: 108%;
  background-repeat: no-repeat;
  height: 100vh;
  display: flex;
  flex-direction: column;
}

footer {
  display: flex;
  justify-content: flex-end;
  align-content: flex-end;
}
</style>

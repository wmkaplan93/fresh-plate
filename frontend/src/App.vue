<template>
  <div id="app">
    <div id="nav">
      &nbsp;&nbsp;<router-link v-bind:to="{ name: 'home' }" v-if="$store.state.token == ''">Home</router-link>
      <router-link v-bind:to="{ name: 'UserLanding' }" v-else>My Home</router-link>&nbsp;&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'myPlans' }" v-if="$store.state.token != ''">My Plans</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'myRecipes' }" v-if="$store.state.token != ''">My Recipes</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'myGroceryList' }" v-if="$store.state.token != ''">My Grocery List</router-link>   &nbsp;|&nbsp;   
      <router-link v-bind:to="{ name: 'explore' }" v-if="$store.state.token != ''">Explore Recipes</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''">Login</router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-else>Logout</router-link> &nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'register' }" v-if="$store.state.token == ''">Register</router-link>
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
  align-content: flex-start;
}
#app {
  background-image: url("../public/2489615.jpg");
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
</style>

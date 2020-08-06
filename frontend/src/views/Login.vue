<template>
  <div id="login" class="text-center">
    <link href='https://fonts.googleapis.com/css?family=Allan' rel='stylesheet'>
    <form class="form-signin" @submit.prevent="login">
      <h1>Get Yo' Food On!</h1>
      <h2 class="h3 mb-3 font-weight-normal">Please Sign In:</h2>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only">Username </label>
      &nbsp;
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      &nbsp;
      <label for="password" class="sr-only">Password </label>
      &nbsp;
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      &nbsp;
      <button type="submit">Sign in</button>
      <br><br>
      <router-link :to="{ name: 'register' }" id="need-account">Need an account?</router-link>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {
    
  },
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push({ name: 'UserLanding', params: {name: response.data.user.username} });
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>
<style scoped>
#login {
  font-family:"Allan";
  display: grid;
  grid-template-columns: 8% 8% 8% 26% 50%;
  grid-template-rows: 10% 10% 30% 25% 25%;
  height: 90vh;
}
h1 {
  font-size: 9vw;
  grid-column-start: 4;
  grid-column-end: 6;
  grid-row-start: 3;
  grid-row-end: 4;
  line-height: .1;
}

h2 {
  font-size: 4vw;
  grid-column-start: 4;
  grid-column-end: 6;
  grid-row-start: 3;
  grid-row-end: 4;
  line-height: .2;
  margin-top: 3vh;
}

#attribution {
  grid-column-start: 5;
  grid-row-start: 5;
  justify-self: end;
}

.form-signin {
  grid-area: 3 / 4 / 4 / 6;
  justify-self: grid-start;
}
</style>

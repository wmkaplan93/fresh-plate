<template>
  <div id="login" class="text-center">
    <link href='https://fonts.googleapis.com/css?family=Lora' rel='stylesheet'>
    <form class="form-signin" @submit.prevent="login">
      <h1 id="fresh-plate">Fresh&nbsp;Plate</h1>
      <h2 class="h3 mb-3 font-weight-normal">Please&nbsp;Sign&nbsp;In:</h2>
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
      <label id="username-label" for="username" class="sr-only">Username</label>
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
      &nbsp;<br>
      <label id="password-label" for="password" class="sr-only">Password</label>
      &nbsp;
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      &nbsp;<br>
      <button class='btn' type="submit">Sign in</button>
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
            this.$router.push({ name: 'UserLanding', params: {username: response.data.user.username} });
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
.form-signin {
  display: grid;
  grid-template-columns: 15% 30% 10% 14% 2% 29%;
  grid-template-rows: 15% 15% 15% 10% 15% 5% 10% 15%;
  height: 90vh;
  font-family:"Lora";
}

#fresh-plate {
  font-size: 11vh;
  grid-column-start: 3;
  grid-column-end: 7;
  grid-row-start: 2;
  grid-row-end: 3;
  line-height: .1;
  justify-self: center;
  align-self: center;
}

h2 {
  font-size: 4vw;
  grid-column-start: 3;
  grid-column-end: 7;
  grid-row-start: 3;
  grid-row-end: 4;
  line-height: .2;
  margin-top: 3vh;
  align-self: start;
}

#username-label {
  grid-column-start: 4;
  grid-row-start: 4;
  justify-self: end;
}

#password-label {
  grid-column-start: 4;
  grid-row-start: 5;
  justify-self: end;
}

#username {
  grid-column-start: 6;
  grid-row-start: 4;
  justify-self: start;
  width: 12vw;
}

#password {
  grid-column-start: 6;
  grid-row-start: 5;
  justify-self: start;
  width: 12vw;
}

.btn {
  width: 12vw;
  grid-column-start: 6;
  grid-row-start: 5;
  grid-row-end: 8;
  justify-self: start;
  align-self: center;
}

#need-account {
  font-size: 2vh;
  grid-column-start: 6;
  grid-row-start: 7;
  justify-self: start;
  align-self: start;
}

</style>

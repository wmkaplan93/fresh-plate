<template>
  <div id="login" class="text-center">
    <link href='https://fonts.googleapis.com/css?family=Lora' rel='stylesheet'>
    <form class="form-signin" @submit.prevent="login">
      <h1 id="fresh-plate">fresh&nbsp;plate.</h1>
      <h2 class="h3 mb-3 font-weight-normal">please&nbsp;sign&nbsp;in</h2>
      <!-- <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div> -->
      <div
        id="thanks-message"
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label id="username-label" for="username" class="sr-only">username</label>
      &nbsp;
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="username"
        v-model="user.username"
        required
        autofocus
      />
      &nbsp;<br>
      <label id="password-label" for="password" class="sr-only">password</label>
      &nbsp;
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="password"
        v-model="user.password"
        required
      />
      &nbsp;<br>
      <button class='btn' type="submit">sign in</button>
      &nbsp;<br>
      <router-link :to="{ name: 'enterUsername'}" id="forgot-password"><button id="forgot-password-btn">forgot my password</button></router-link>
      <br><br>
      <router-link :to="{ name: 'register' }" id="need-account"><button id="need-account-btn">need an account?</button></router-link>
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
            this.$router.push({ name: 'myPlans', params: {username: response.data.user.username} });
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
            alert('Invalid username and/or password. Please try again, or hit "Forgot My Password"');
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
  grid-row-start: 4;
  justify-self: end;
  align-self: end;
}

#username {
  grid-column-start: 6;
  grid-row-start: 4;
  justify-self: start;
  width: 12vw;
  background-color: rgba(255, 255, 255, .6);
}

#password {
  grid-column-start: 6;
  grid-row-start: 4;
  justify-self: start;
  align-self: end;
  width: 12vw;
  background-color: rgba(255, 255, 255, .6);
}

.btn {
  width: 12vw;
  grid-column-start: 6;
  grid-row-start: 5;
  justify-self: start;
  color: black;
  background-color: rgba(255, 255, 255, .6);
  margin-top: 3vh;
}

#need-account, #need-account button {
  font-size: 2vh;
  grid-column-start: 6;
  grid-row-start: 6;
  grid-row-end: 8;
  justify-self: start;
  align-self: center;
  height: 3vh;
  width: 14vw;
  color: black;
  background-color: rgba(255, 255, 255, .4);
}

#forgot-password-btn {
  font-size: 1.5vh;
  grid-column-start: 6;
  grid-row-start: 6;
  justify-self: start;
  align-self: start;
  height: 2.5vh;
  width: 14vw;
  color: black;
  background-color: rgba(255, 255, 255, .4);
}

#forgot-password {
  padding-top: 1vh;
  grid-column-start: 6;
  grid-row-start: 6;
  justify-self: start;
}

#thanks-message {
  grid-column-start: 4;
  grid-row-start: 1;
  align-self: center;
}

</style>

<template>
  <div id="register" class="text-center">
    <link href='https://fonts.googleapis.com/css?family=Lora' rel='stylesheet'>
    <form class="form-register" @submit.prevent="register">
      <div id="register-form-styling">
        <h1>Fresh&nbsp;Plate</h1>
        <h2 class="h3 mb-3 font-weight-normal">Create&nbsp;Account</h2>
        <div class="alert alert-danger" role="alert" v-if="registrationErrors">
          {{ registrationErrorMsg }}
        </div>
        <label id="username-label" for="username" class="sr-only">Username</label>
        &nbsp;<br>
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
        &nbsp;<br><br>
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
        />
        <br><br>
        <input
          type="password"
          id="confirmPassword"
          class="form-control"
          placeholder="Confirm Password"
          v-model="user.confirmPassword"
          required
        />

        <br><br>
        <label id="sq-label">Security Question</label>
        <div id="sq-div">
          <select
            name="basic-dropdown"
            id="sq"
            class="form-control"
            placeholder="Please Select A Security Question"
            v-model="user.securityQuestionID"
            required>
          <option v-for="question in securityQuestions" v-bind:key="question.securityQuestionID" :value="question.securityQuestionID">{{ question.securityQuestion }}</option>
          </select>
          <v-icon small>unfold_more</v-icon>
        </div>
        <br><br>
        <label id="sqa-label">Answer</label>
        <input
          type="text"
          id="sqa"
          class="form-control"
          placeholder="Answer"
          v-model="user.answer"
          required
        />
        &nbsp;<br>
        <button class="btn btn-lg btn-primary btn-block" type="submit">
          Create Account
        </button>
        <br> <br>
        <router-link id="have-account" :to="{ name: 'login' }"><button id="have-account-btn">Have an account?</button></router-link>
      </div>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
        securityQuestionID: 0,
        answer: '',
      },
      securityQuestions: [
          {
            securityQuestionID: 0,
            securityQuestion: ''
          }
      ],
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  created() {
    authService.allSecurityQuestions().then (response => {
      this.securityQuestions = response.data
    })
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        alert('Password & Confirm Password do not match.');
        this.user.password = '';
        this.user.confirmPassword = '';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style>
option {
  background-color: rgba(255, 255, 255, 0.2);
  }

#register-form-styling {
  font-family:"Lora";
  display: grid;
  grid-template-columns: 44% 19% 2% 35%;
  grid-template-rows: 30% 15% 8% 8% 8% 8% 8% 8%;
  height: 90vh;
}

h1 {
  font-size: 8vw;
  grid-column-start: 2;
  grid-column-end: 5;
  grid-row-start: 1;
  justify-self: center;
  align-self: start;
}

h2 {
  font-size: 5vw;
  grid-column-start: 2;
  grid-column-end: 5;
  grid-row-start: 2;
  align-self: center;
  justify-self: center;
}

#username-label {
  grid-column-start: 2;
  grid-row-start: 3;
  justify-self: end;
}

#password-label {
  grid-column-start: 2;
  grid-row-start: 4;
  justify-self: end;
}

button, input{
  border-radius: 0px;
  background-color: rgba(255, 255, 255, 0.2);
  width: 15vw;
  height: 4.5vh;
  font-size: 3vh;
  font-family: 'Lora';
  align-items: center;
}

#username {
  grid-column-start: 4;
  grid-row-start: 3;
  justify-self: start;
  width: 17vw;
  height: 4vh;
  font-size: 2vh;
  background-color: rgba(255, 255, 255, .6);
}

#password {
  grid-column-start: 4;
  grid-row-start: 4;
  justify-self: start;
  width: 17vw;
  height: 4vh;
  font-size: 2vh;
  background-color: rgba(255, 255, 255, .6);
}

#confirmPassword {
  grid-column-start: 4;
  grid-row-start: 5;
  justify-self: start;
  width: 17vw;
  height: 4vh;
  font-size: 2vh;
  background-color: rgba(255, 255, 255, .6);
}

#sq-label {
  grid-column-start: 2;
  grid-row-start: 6;
  justify-self: end;
}

#sq-div {
  grid-column-start: 4;
  grid-row-start: 6;
  justify-self: self;
  background-color: rgba(255,255,255,.4);
  height: 4vh;
  width: 33vw;
}

#sqa {
  grid-column-start: 4;
  grid-row-start: 7;
  justify-self: start;
  width: 17vw;
  height: 4vh;
  font-size: 2vh;
  background-color: rgba(255, 255, 255, .6);
}

#sqa-label {
  grid-column-start: 2;
  grid-row-start: 7;
  justify-self: end;
}

.btn {
  grid-column-start: 4;
  grid-row-start: 8;
  justify-self: start;
  background-color: rgba(255, 255, 255, .6);
}

#have-account, #have-account button {
  grid-column-start: 4;
  grid-row-start: 9;
  justify-self: start;
  font-size: 2vh;
  height: 3vh;
  width: 12vw;
  background-color: rgba(255, 255, 255, .4);
  color: black;
}
</style>

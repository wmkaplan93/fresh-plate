<template>
  <div id="forgot-password">
    <h1 id="fresh-plate">Fresh&nbsp;Plate</h1>
    <h2 id="new-password">Reset&nbsp;Password</h2>
      <form class="form-forgot-password" @submit.prevent="checkAnswer" v-if= "!showResetForm">
          <br><br>
          <p id="sec-question">{{ securityQuestion.securityQuestion }}</p><br>
            <input type="text" id="answer" placeholder="Answer" v-model="securityAnswer.answer"/>
            <br><br>
            <input class="btn" id="answer-btn" type="submit" value= "Check Answer" />
      </form>

      <form class="set-new-password" @submit.prevent="updatePassword" v-if= "showResetForm">
          &nbsp;<br>
          
          <input
            type="password"
            id="resetPassword"
            class="form-control"
            placeholder="Enter New Password"
            v-model="user.password"
            required
          />
          <br><br>
          <input
            type="password"
            id="confirmResetPassword"
            class="form-control"
            placeholder="Confirm New Password"
            v-model="user.confirmPassword"
            required
          />
          &nbsp;<br><br>
          <button class="btn" type="submit">
            Reset Password
          </button>
          &nbsp;<br>
          <br> <br>
          <router-link id="have-account" :to="{ name: 'login' }"><button id="have-account-btn">Return to Login</button></router-link>
        &nbsp;<br>
      </form>
  </div>
</template>
<script>
import AuthService from '../services/AuthService'

export default {
    name: "forgotPassword",
    data() {
        return {
            securityAnswer: {
                username: '',
                answer: ''
            },
            user: {
                username: '',
                password: '',
                confirmPassword: '',
                role: 'user',
                securityQuestionId: 0,
                answer: ''
            },
            showResetForm: false,
            securityQuestion: {
                securityQuestionId: 0,
                securityQuestion: ''
            },
            resetPasswordError: false,
            resetPasswordErrorMsg: 'There were problems reseting your password. Please try again'
        }
    },
    created() {
        AuthService.userSecurityQuestion(this.$route.params.username).then (response => {
            if (response.data === '' ) {
              alert('Invalid username entered');
              this.$router.go(-1);
            } else {

            this.securityQuestion = response.data;
            this.user.securityQuestionId = response.data.securityQuestionId;
            this.securityAnswer.username = this.$route.params.username;
            }
          });
          
    },
    methods: {
        checkAnswer() {
            
            AuthService.resetPassword(this.securityAnswer).then (response => {
                this.showResetForm = response.data;
                if (!this.showResetForm) {
                  alert('That is not the correct answer. Please try again.');
                  this.securityAnswer.answer = '';
                }
            });
            }
        },
        updatePassword() {
            this.user.answer = this.securityAnswer.answer;
            this.user.username = this.$route.params.username;
            this.user.securityQuestionId = this.securityQuestion.securityQuestionId;
            
            if (this.user.password != this.user.confirmPassword) {
              this.resetPasswordError = true;
              alert('Password & Confirm Password do not match.');
              this.user.password = '';
              this.user.confirmPassword = '';
            } else {

            AuthService.updatePassword(this.user).then ((response) => {
                  if(response.status == 200) {
                      this.$router.push({name: 'login'})
                  } 
                })
                .catch((error) => {
                  const response = error.response;
                  this.resetPasswordError = true;
                  if(response.status === 400) {
                      this.resetPasswordErrorMsg = 'Bad Request: Validation Errors';
                  }
                });
             }
         },
         clearErrors() {
            this.resetPasswordError = false;
            this.resetPasswordErrorMsg = 'There were problems reseting your password. Please try again';
         }
    
}
</script>
<style scoped>
#forgot-password {
  display: grid;
  grid-template-columns: 15% 30% 10% 14% 2% 29%;
  grid-template-rows: 15% 15% 15% 45% 10%;
  height: 90vh;
  font-family:"Lora";
}

#fresh-plate {
  font-size: 11vh;
  grid-column-start: 4;
  grid-column-end: 7;
  grid-row-start: 2;
  grid-row-end: 4;
  justify-self: start;
  align-self: center;
}

#new-password {
  font-size: 6vh;
  grid-column-start: 4;
  grid-column-end: 7;
  grid-row-start: 3;
  align-self: end;
  justify-self: start;
}

.form-forgot-password, .set-new-password {
  grid-column-start: 4;
  grid-column-end: 7;
  grid-row-start: 4; 
}

#sec-question {
  font-size: 3vh;  
}

#resetPassword, #confirmResetPassword {
  width: 15vw;
  height: 3vh;
  background-color: rgba(255,255,255,.6);
}

#answer {
  width: 12vw;
  height: 3vh;
  background-color: rgba(255,255,255,.6);
}

#answer-btn {
  height: 3vh;
  width: 17vw;
}

</style>
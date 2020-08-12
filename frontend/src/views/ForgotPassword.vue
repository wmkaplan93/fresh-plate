/<template>
  <div class="forgot-password">
    <form class="form-forgot-password" @submit.prevent="checkAnswer" v-if= "!showResetForm">
        <h1 id="fresh-plate">Fresh&nbsp;Plate</h1>
        <h2 id="new-password">Reset&nbsp;Password</h2>

        <br><br>
        <h3>{{ securityQuestion.securityQuestion }}</h3>
        <label id="security-question-answer">Answer</label>
        <input type="text" id="answer" v-model="securityAnswer.answer"/>
        <br><br>
        <input class="btn" type="submit" value= "Check Answer" />
        
    </form>

    <form @submit.prevent="updatePassword" v-if= "showResetForm">
        &nbsp;<br>
        <label id="password-label" for="password" class="sr-only">Password</label>
        &nbsp;<br><br>
        <input
          type="password"
          id="resetPassword"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
        />
        <br><br>
        <input
          type="password"
          id="confirmResetPassword"
          class="form-control"
          placeholder="Confirm Password"
          v-model="user.confirmPassword"
          required
        />
        &nbsp;<br>
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
            resetPassworError: false,
            resetPasswordErrorMsg: 'There were problems reseting your password. Please try again'
        }
    },
    created() {
        AuthService.userSecurityQuestion(this.$route.params.username).then (response => {
            this.securityQuestion = response.data;
            this.user.securityQuestionId = response.data.securityQuestionId;
            this.securityAnswer.username = this.$route.params.username;
        }); 
    },
    methods: {
        checkAnswer() {
            AuthService.resetPassword(this.securityAnswer).then (response => {
                this.showResetForm = response.data
            });
        },
        updatePassword() {
            this.user.answer = this.securityAnswer.answer;
            this.user.username = this.$route.params.username;
            this.user.securityQuestionId = this.securityQuestion.securityQuestionId;
            
            AuthService.updatePassword(this.user).then (response => {
                if(response.status === 200) {
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
         },
         clearErrors() {
            this.resetPassworError = false;
            this.resetPasswordErrorMsg = 'There were problems reseting your password. Please try again';
         }
    }
}
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

#need-account, #need-account button {
  font-size: 2vh;
  grid-column-start: 6;
  grid-row-start: 6;
  grid-row-end: 8;
  justify-self: start;
  align-self: center;
  height: 3vh;
  width: 11vw;
}

#thanks-message {
  grid-column-start: 4;
  grid-row-start: 1;
  align-self: center;
}

</style>
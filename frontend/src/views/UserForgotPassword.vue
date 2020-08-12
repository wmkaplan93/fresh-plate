/<template>
    <div id="passwordReset" class="text-center"> 
    <link href='https://fonts.googleapis.com/css?family=Lora' rel='stylesheet'>
    <form class="username" @submit.prevent="setUsername">
        <h1 id="fresh-plate">Fresh&nbsp;Plate</h1>
        <h2 id="new-password" class="h3 mb-3 font-weight-normal">Please&nbsp;Insert&nbsp;Username:</h2>

    &nbsp;<br>
    <label id="username" for="username" class="sr-only">Username</label>
    &nbsp;
    <input
        type="text"
        id="username1"
        class="form-control"
        placeholder="Username"
        v-model="username"
        required
    />
    &nbsp;<br>
    
    <input class="btn" type="submit" value= "Go to reset page" />
    
   
    <br><br>
    </form>
    
    </div>
</template>

<script>
export default {
    name: "enterUsername",
    data() {
        return {
            username: ''
        }
    },
    methods: {
        setUsername() {
            this.$store.commit("SET_USERNAME", this.username)
            this.$router.push({name: 'forgotPassword', params: {username: this.username}})
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
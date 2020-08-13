/<template>
    <div id="password-reset" class="text-center"> 
      <h1 id="fresh-plate">Fresh&nbsp;Plate</h1>
      <h2 id="insert-username" class="h3 mb-3 font-weight-normal">Please&nbsp;Insert&nbsp;Username:</h2>
      <form class="username" @submit.prevent="setUsername">
      &nbsp;<br>
        <label id="username-label" for="username" class="sr-only">Username</label>
        &nbsp;
        <input
            type="text"
            id="username"
            class="form-control"
            placeholder="Username"
            v-model="username"
            required
        />&nbsp;<br>
        <input id="submit-btn" class="btn" type="submit" value= "Go to reset page" />
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
#password-reset {
  display: grid;
  grid-template-columns: 15% 30% 10% 14% 2% 29%;
  grid-template-rows: 15% 15% 15% 10% 15% 5% 10% 15%;
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

#insert-username {
  font-size: 6vh;
  grid-column-start: 4;
  grid-column-end: 7;
  grid-row-start: 3;
  align-self: end;
  justify-self: start;
}
.username {
  display: flex;
  grid-column-start: 5;
  grid-column-end: 7;
  grid-row-start: 4;
  grid-row-end: 8;
  flex-direction: column;
  align-items: flex-start;
}

#username-label {
  grid-column-start: 4;
  grid-column-end: 5;
  grid-row-start: 5;
  grid-row-end: 6;
  justify-self: end;
}

#username {
  grid-column-start: 6;
  grid-row-start: 4;
  justify-self: start;
  width: 12vw;
  height: 3vh;
}
</style>
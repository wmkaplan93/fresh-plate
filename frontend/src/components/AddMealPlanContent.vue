<template>
  <div>
    <v-card max-width="600" class="mx-auto" id="overview">
    <h1 class="subheading black--text">New&nbsp;Plan</h1>
        <form v-on:submit.prevent="addNewPlan">
        <v-row>
            <v-col cols="12">
                <v-card id="input-form">
                    <v-card-title class="mp-name">
                        <label for="plan-name">&nbsp;&nbsp;Plan Name: </label>
                        <input
                        type="text"
                        id="plan-name"
                        class="form-control"
                        v-model="plan.plan_name"
                        placeholder="Enter Meal Plan Name" />
                    </v-card-title>
                    <v-card-subtitle class="mp-desc">
                        <label for="plan-description"><strong>Plan Description: </strong></label>
                        <textarea
                        id="plan-description"
                        class="form-control"
                        v-model="plan.plan_description"
                        placeholder="Enter a description for your new meal plan" />
                    </v-card-subtitle> 
                </v-card>
            </v-col>
        </v-row>
        <input type="submit" id="submit-button" class="btn btn-submit" value="Submit"/>
        </form>
    </v-card>
  </div>
</template>

<script>
import recipeService from "../services/RecipeService"

export default {
    name: "add-meal-plan-content",
    data() {
        return {
            plan: {
            username: '',
            plan_name: '',
            plan_description: ''
            } 
        }
    },
    methods: {
        addNewPlan() {
            const newPlan = {
                plan_id: 0,
                username: this.$store.state.user.username,
                plan_name: this.plan.plan_name,
                plan_description: this.plan.plan_description
            }
            recipeService.addNewPlan(newPlan).then(response => {
                if (response.status === 201) {
                    alert("Success!")
                    this.$router.push({name: 'myPlans', params: {username:this.$store.state.user.username}})
                }
            })
        }
    }
}
</script>

<style scoped>
#overview {
    background-color: rgba(255,255,255,.3) !important;
    display: flex;
    min-width: 60vw;
    flex-direction: column;
}

input {
    border: 1px solid;
    width: 40vw;
    padding-left: .5vw;
    margin-left: 1vw;
}

textarea {
    border: 1px solid;
    width: 40vw;
    padding-left: .5vw;
    margin-left: .8vw;
}

h1 {
    align-self: center !important;
}

#submit-button {
    width: 15vw;
    background-color: white;
    align-self: flex-end;
    margin-right: 3vw;
}

#input-form {
    background-color: rgba(255,255,255,.95);
}
</style>
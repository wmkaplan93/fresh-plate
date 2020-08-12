<template>
    <div id="plans-content" data-app>
        <h1 class="subheading black--text">My&nbsp;Plans</h1>
        <v-container>
            <v-expansion-panels popout row wrap >
                <v-expansion-panel v-for="plan in this.$store.state.userPlans" :key="plan.plan_id">
                    
                    <v-expansion-panel-header>{{ plan.plan_name }}
                        <v-divider></v-divider>
                        <template v-slot:actions>
                            <v-icon>expand</v-icon>
                        </template>
                    </v-expansion-panel-header>
                    <v-expansion-panel-content>
                        {{ plan.plan_description }}<br>
                        <v-divider></v-divider>
                            <v-tooltip bottom>
                            <template v-slot:activator="{ on, attrs }">
                                <v-btn 
                                icon 
                                v-on="on" 
                                v-bind="attrs"
                                router :to="{ name: 'myPlanDetails', params: {planId:plan.plan_id} }"
                                ><v-icon medium center>info</v-icon>
                                </v-btn>
                            </template>
                            <span>More Information</span>
                        </v-tooltip>
                        <span class="caption">More Information </span>
                        <v-divider></v-divider>
                        <v-tooltip bottom>
                            <template v-slot:activator="{ on, attrs }">
                                <v-btn 
                                icon 
                                v-on="on" 
                                v-bind="attrs"
                                router :to="{name: 'myGroceryList', params: { planId:plan.plan_id} }"
                                ><v-icon medium center>shopping_cart</v-icon>
                                </v-btn>
                            </template>
                            <span>View This Grocery List</span>
                        </v-tooltip>
                        <span class="caption">Grocery List</span>
                    </v-expansion-panel-content>
                </v-expansion-panel>
            </v-expansion-panels>
        </v-container>

    </div>
</template>

<script>
import recipeService from "../services/RecipeService";

export default {
    name: "plans-content",
    data() {
        return {
            show: false
        }
        
    },
    created() {
        this.retrieveUserPlans();
    },
    methods: {
        retrieveUserPlans() {
            recipeService.getUserPlans(this.$store.state.user.username).then(response => {
                this.$store.commit("GET_USER_PLANS", response.data);
            })
        }
    }
}
</script>
<style scoped>
#plans-content {
    display: flex;
    justify-content: center;
    width: 65vw;
}
#plans-title {
    font-size: 8vh;
    text-decoration: underline overline;
}

#user-plans {
    display: flex;
    justify-content: center;
    align-self: flex-start;
}
</style>
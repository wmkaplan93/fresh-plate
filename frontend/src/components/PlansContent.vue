<template>
    <div data-app>
         <h1 class="subheading black--text">my&nbsp;plans</h1>
        <v-card class="text-center" id="overview">
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
                            <span>more information</span>
                        </v-tooltip>
                        <span class="caption">more information </span>
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
                            <span>view this grocery list</span>
                        </v-tooltip>
                        <span class="caption">grocery list</span>
                        <v-divider></v-divider>
                        <v-tooltip bottom>
                            <template v-slot:activator="{ on, attrs }">
                                <v-btn
                                icon
                                v-on="on"
                                v-bind="attrs"
                                @click="selectForDelete(plan)"
                                ><v-icon medium center>delete_outline</v-icon>
                                </v-btn>
                            </template>
                            <span>delete this plan</span>
                        </v-tooltip>
                        <span class="caption">delete this plan</span>
                    </v-expansion-panel-content>
                </v-expansion-panel>
            </v-expansion-panels>
        </v-container>
    </v-card>
    </div>
</template>

<script>
import recipeService from "../services/RecipeService";

export default {
    name: "plans-content",
    data() {
        return {
            show: false,
            selectedPlan: {

            }
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
        },

        selectForDelete(plan) {
            this.selectedPlan = plan;
            this.deleteThisPlan(this.selectedPlan)
        },

        deleteThisPlan(plan) {
            recipeService.deleteThisPlan(plan).then(response => {
                if(response.status === 204) {
                    alert("Deleted!")
                    this.$router.go();
                }
            })
        }
    }
}
</script>
<style scoped>

#overview {
    width: 65vw;
    background-color: rgba(255,255,255,0.25) !important;
    min-height: 74vh !important;
    justify-content: center;
    justify-items: center;
    display: flex;
}

h1 {
    text-align: center !important
}
</style>
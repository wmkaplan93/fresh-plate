<template>
    <div id="grocery-list-content">
        <v-card max-width="600" min-width="450">
            <v-list subheader two-line>
                <v-subheader>my grocery list</v-subheader>
                <v-list-item-group v-model="settings" multiple>
                    <v-virtual-scroll :items="showItems" :item-height="50" height="500">
                        <template v-slot="{ item }">
                            <v-list-item :key="item">
                                <v-btn icon @click="item.show = !item.show">
                                    <v-icon>
                                        {{ item.show ? 'radio_button_unchecked' : 'radio_button_checked'}}
                                    </v-icon>
                                </v-btn>
                            <v-list-item-content>
                                <v-list-item-title>{{item.ingredientName}}</v-list-item-title>
                                <v-list-item-subtitle>{{item.quantity}} {{item.unitName}}</v-list-item-subtitle>
                            </v-list-item-content>
                            </v-list-item>
                        </template>
                    </v-virtual-scroll>
                </v-list-item-group>
            </v-list>
        </v-card>
    </div>
</template>

<script>
import recipeService from "../services/RecipeService";

export default {
    name: "grocery-list-content",
    data() {
        return {
            settings: [],
            showItems: []
        }
    },
    created() {
        this.retrieveGroceryList();
        this.retrieveUserPlans();
    },
    methods: {
        retrieveGroceryList() {
            recipeService.getGroceryList(this.$route.params.planId).then(response => {
                this.$store.commit("GET_GROCERY_LIST", response.data);
                this.addShow();
            })
        },
        retrieveUserPlans() {
            recipeService.getUserPlans(this.$store.state.user.username).then(response => {
                this.$store.commit("GET_USER_PLANS", response.data);
            })
        },
        addShow() {
            this.showItems = this.$store.state.groceryList.map(item => ({
                ...item,
                show: true
            }))
        }
    }
}
</script>
<style scoped>
#grocery-list-content {
    display: flex;
    justify-content: center;
    width: 65vw;
    background-color: rgba(255,255,255,.3);
}
#grocery-list-title {
    font-size: 8vh;
    text-decoration: underline overline;
}

#user-grocery-list {
    display: flex;
    justify-content: center;
    align-self: flex-start;
}
</style>
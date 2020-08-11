<template>
    <div id="plans-content" data-app>
        <h1 class="subheading black--text">My&nbsp;Plans</h1>
        <v-container>
            <v-expansion-panels popout row wrap >
                <v-expansion-panel v-for="(plan, index) in plans" :key="index">
                    
                    <v-expansion-panel-header>{{ plan.title }}
                        <v-divider></v-divider>
                        <template v-slot:actions>
                            <v-icon>expand</v-icon>
                        </template>
                    </v-expansion-panel-header>
                    <v-expansion-panel-content>
                        Sample Recipe 1<br>Sample Recipe 2<br>Sample Recipe 3
                    </v-expansion-panel-content>
                </v-expansion-panel>
            </v-expansion-panels>
        </v-container>

    </div>
</template>

<script>
export default {
    name: "plans-content",
    data() {
        return {
            show: false,
            plans: [
                { title: 'Sunday Splurge' },
                { title: 'Rabbit Food' },
                { title: 'Carnivore Crunch' },
                { title: 'Midnight Munchies' },
                { title: 'Everything is Cake' }
            ],
        }
        
    }
}
</script>
<style scoped>
#plans-content {
    display: flex;
    justify-content: center;
    width: 65vw;
    background: rgba(194,168,36,0.1);
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
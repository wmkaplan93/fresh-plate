import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import UserLanding from '../views/UserLanding.vue'
import MyRecipes from '../views/MyRecipes.vue'
import MyPlans from '../views/MyPlans.vue'
import ExploreRecipes from '../views/ExploreRecipes.vue'
import MyGroceryList from '../views/MyGroceryList.vue'
import AddRecipe from '../views/AddRecipe.vue'
import MyPlanDetails from '../views/MyPlanDetails.vue'
import RecipeDetails from '../views/RecipeDetails.vue'
import AddMealPlan from '../views/AddMealPlan.vue'
import ForgotPassword from '../views/ForgotPassword.vue'
import UserForgotPassword from "../views/UserForgotPassword.vue"
import SearchResults from "../views/SearchResults.vue"

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      },
      beforeEnter(to, from, next) {
        if (store.state.token != '') {
          next({ name: 'UserLanding' });
        } else {
          next();
        }
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      },
      beforeEnter(to, from, next) {
        if (store.state.token != '') {
          next({ name: 'UserLanding', params: { username : this.$store.state.user.username } });
        } else {
          next();
        }
      }
    },
    {
      path: "/users/:username",
      name: "UserLanding",
      component: UserLanding,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/users/:username/myRecipes",
      name: "myRecipes",
      component: MyRecipes,
      meta: {
        requiresAuth: true
      }
    },

    {
      path: "/users/:username/addRecipe",
      name: "addRecipe",
      component: AddRecipe,
      meta: {
        requiresAuth: true
      }
    },

    {
      path: "/users/:username/myPlans",
      name: "myPlans",
      component: MyPlans,
      meta: {
          requiresAuth: true
      }
    },
    {
      path: "/exploreRecipes",
      name: "exploreRecipes",
      component: ExploreRecipes,
      meta: {
        requiresAuth: true
      }
    },
    {

      path: '/myGroceryList/:planId',
      name: "myGroceryList",
      component: MyGroceryList,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/myPlans/:planId',
      name: "myPlanDetails",
      component: MyPlanDetails,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/exploreRecipes/:recipeId',
      name: "recipeDetails",
      component: RecipeDetails,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/users/:username/addMealPlan',
      name: "addMealPlan",
      component: AddMealPlan,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/resetPassword/:username",
      name: "forgotPassword",
      component: ForgotPassword,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/resetPassword",
      name: "enterUsername",
      component: UserForgotPassword,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/searchRecipes/filter?name=:searchTerm",
      name: "searchResults",
      component: SearchResults,
      meta: {
        requiresAuth: true
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;


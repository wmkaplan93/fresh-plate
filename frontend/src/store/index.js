import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));


if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
   
    formProperties: {
      ingredientList: [],
      typeList: [],
      unitList: [],
    },

    ingredient: {
      ingredientId: 0,
      ingredientName: ''
    },

    groceryList: [],
    item: {
      quantity: 0,
      ingredientName: '',
      unitName: ''
    },
    
    type: {
      typeId: 0,
      type: ''
    },

    unit: {
      unitId: 0,
      unitName: ''
    },

    userPlans: [],
    plan: {
      plan_id: 0,
      username: '',
      plan_name: '',
      plan_description: ''
    },

    planDetails: {},
    details: {
      mealPlan: {
        plan_id: 0,
        username: '',
        plan_name: '',
        plan_description: '',
      },
      recipeList: [
        {
          recipeId: 0,
          name: '',
          description: '',
          yieldAmount: 0,
          yieldUnit: '',
          duration: '',
          recipeMethod: '',
          ownername: '',
          public: false
        }
      ]
    },


    recipeList: [],
    userRecipes: [],
    allRecipes: [],
    recipe: {
      
      username: '',
      ownername: '',
      name: '',
      description: '',
      typeList: [],
      yieldAmount: '',
      yieldUnit: '',
      duration: '',
      recipeMethod: [],
      isPublic: false,
      isFavorite: false,
      ingredientList: []
    },

    allRecipeDetails: {},
    recipeDetails: {
      recipeId: 0,
      name: '',
      description: '',
      yieldAmount: 0,
      yieldUnit: '',
      duration: '',
      recipeMethod: '',
      typeList: [
        {
          typeId: 0,
          type: ''
        },
      ],
      ingredientList: [
        {
          quantity: 0,
          ingredientName: '',
          unitName: ''
        },
      ],
      username: null,
      ownername: '',
      public: false
    },
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    GET_FORM_PROPERTIES(state, data) {
      state.formProperties = data;
    },

    SET_RECIPE(state, data) {
      state.recipe = data;
    },  
    GET_PUBLIC_RECIPES(state, data) {
      state.allRecipes = data;
    },
    GET_USER_RECIPES(state, data) {
      state.userRecipes = data;
    },
    GET_USER_PLANS(state, data) {
      state.userPlans = data;
    },
    GET_GROCERY_LIST(state, data) {
      state.groceryList = data;
    },
    GET_PLAN_DETAILS(state, data) {
      state.details = data;
    },
    GET_RECIPE_DETAILS(state, data) {
      state.recipeDetails = data;
    }
  }
})

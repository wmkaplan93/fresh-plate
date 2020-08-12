import axios from 'axios';
const http = axios.create({
  baseURL: "http://localhost:8080"
});

export default {

  getFormProperties(username) {
    return http.get(`/users/${username}/addRecipe`)
  },

  addRecipe(username, recipe) {
    return http.post(`/users/${username}/myRecipes`, recipe)
  },

  getRecipes() {
    return http.get('/exploreRecipes');
  },

  addToLibrary(recipe) {
    return http.post(`/exploreRecipes/${recipe.recipeId}`, recipe)
  },

  getUserRecipes(username) {
    return http.get(`/users/${username}/myRecipes`)
  },

  getUserPlans(username) {
    return http.get(`/users/${username}/myPlans`)
  },

  getGroceryList(planId) {
    return http.get(`/myGroceryList/${planId}`)
  },

  getPlanDetails(planId) {
    return http.get(`/myPlans/${planId}`)
  },

  getRecipeDetails(recipeId) {
    return http.get(`/myRecipes/${recipeId}`)
  }
}

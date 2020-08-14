import axios from 'axios';
const http = axios.create({
  baseURL: "http://localhost:8080"
});

export default {

  getFormProperties(username) {
    return http.get(`/users/${username}/addRecipe`)
  },

  addRecipe(newRecipe) {
    return http.post(`/users/${newRecipe.username}/addRecipe`, newRecipe)
  },

  addNewPlan(newPlan) {
    return http.post(`/users/${newPlan.username}/addMealPlan`, newPlan)
  },

  addToLibrary(recipe) {
    return http.post(`/exploreRecipes/${recipe.recipeId}`, recipe)
  },

  addRecipeToPlan(mealPlanDTO) {
    return http.put(`/users/${mealPlanDTO.mealPlan.username}/myPlans`, mealPlanDTO)
  },

  addToMyRecipes(recipe) {
    return http.put('/exploreRecipes', recipe)
  },

  deleteThisPlan(plan) {
    return http.post(`/users/${plan.username}/myPlans`, plan)
  },

  deleteThisRecipe(recipe) {
    return http.post(`/users/${recipe.username}/myRecipes`, recipe)
  },

  deleteRecipeFromPlan(mealPlanDTO) {
    return http.post(`/myPlans/${mealPlanDTO.mealPlan.plan_id}`, mealPlanDTO)
  },

  getRecipes() {
    return http.get('/exploreRecipes');
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
    return http.get(`/exploreRecipes/${recipeId}`)
  },

  searchRecipes(searchTerm) {
    return http.get(`/searchRecipes/filter?name=${searchTerm}`)
  },



  
}

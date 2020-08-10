import axios from 'axios';

export default {

  getFormProperties() {
    return axios.get(`/users/{username}/myRecipes/addRecipe`)
  },

  addRecipe(recipe) {
    return axios.post(`/users/{username}/myRecipes/addRecipe`, recipe)
  },

  explorePublicRecipes() {
      return axios.get('/exploreRecipes')
  }

}

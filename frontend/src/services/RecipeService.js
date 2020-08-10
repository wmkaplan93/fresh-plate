import axios from 'axios';
const http = axios.create({
  baseURL: "http://localhost:8080"
});

export default {

  getFormProperties() {
    return http.get(`/users/{username}/myRecipes/addRecipe`)
  },

  addRecipe(recipe) {
    return http.post(`/users/{username}/myRecipes/addRecipe`, recipe)
  },

  getRecipes() {
    return http.get('/exploreRecipes');
  }
}

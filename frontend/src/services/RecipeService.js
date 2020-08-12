import axios from 'axios';
const http = axios.create({
  baseURL: "http://localhost:8080"
});

export default {

  getFormProperties(username) {
    return http.get(`/users/${username}/addRecipe`)
  },

  addRecipe(username, recipe) {
    return http.post(`/users/${username}/addRecipe`, recipe)
  },

  getRecipes() {
    return http.get('/exploreRecipes');
  }
}

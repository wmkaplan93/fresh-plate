import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
});

export default {

    getRecipes() {
        return http.get('/exploreRecipes');
    }
}
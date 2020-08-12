import axios from 'axios';

export default {

  login(user) {
    return axios.post('/login', user)
  },

  register(user) {
    return axios.post('/register', user)
  },

  userSecurityQuestion(username) {
    return axios.get(`/resetPassword/${username}`)
  },

  allSecurityQuestions(user) {
    return axios.get('/register', user)
  },

  resetPassword(securityAnswer) {
    return axios.post(`/resetPassword/${securityAnswer.username}`, securityAnswer)
  },

  updatePassword(user) {
    return axios.put(`/resetPassword/${user.username}`, user)
  }

}

import Vue from 'vue'
import Vuex from 'vuex'
// import mutations from './mutations'
// import getters from './getters'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    email: null,
    loginSuccess: false
  },
  // mutations,
  // getters
  mutations: {
    loginSuccess (state, email) {
      state.loginSuccess = true
      state.email = email
    },
    loginError (state, email) {
      state.loginError = true
      state.email = email
    },
    logout (state) {
      state.loginSuccess = false
      location.reload()
    }
  },
  actions: {
    async login ({ commit }, { email, password }) {
      try {
        const result = await axios.post('/api/login', {
          email: email,
          password: password
        })
        if (result.status === 200) {
          commit('loginSuccess', {
            email: email
          })
          alert('로그인 성공')
        }
      } catch (error) {
        commit('loginError', {
          email: email
        })
        alert('로그인 실패')
        throw new Error(error)
      }
    },
    logout ({ commit }) {
      commit('logout')
    }
  },
  getters: {
    isLoggedIn: state => state.loginSuccess,
    hasLoginErrored: state => state.loginError,
    getEmail: state => state.email
  },
  modules: {
  }
})

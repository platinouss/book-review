<template>
  <div>
    <h3> 메인페이지 </h3>
    <button v-if="isAuthenticated"  @click="logout()"> 로그아웃 </button>
  </div>
</template>

<script>
import store from '../store'
import axios from 'axios'

export default {
  name: 'MainView',
  computed: {
    isAuthenticated () {
      return store.getters.isLoggedIn
    }
  },
  methods: {
    async logout () {
      try {
        await axios.post('api/logout', '', {
          headers: {
            Authorization: localStorage.getItem('access_token')
          }
        })
        localStorage.removeItem('access_token')
        this.$store.commit('logout')
        alert('로그아웃 되었습니다.')
        this.$router.push('/')
      } catch (error) {
        alert('로그아웃 실패 !')
      }
    }
  }
}
</script>

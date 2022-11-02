<template>
  <div id="app">
    <nav>
      <router-link to="/"> 메인 | </router-link>
      <router-link to="/login" v-if="!isAuthenticated"> 로그인 | </router-link>
      <router-link to="/register" v-if="!isAuthenticated"> 회원가입 | </router-link>
      <router-link to="/mypage" v-if="isAuthenticated"> 마이페이지 | </router-link>
    </nav>
    <router-view/>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>

<script>
import store from './store'

export default {
  computed: {
    isAuthenticated () {
      return store.getters.isLoggedIn
    }
  }
}
</script>

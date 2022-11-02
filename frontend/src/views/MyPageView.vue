<template>
  <div v-if="isAuthenticated" class="app">
    마이페이지
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'MyPageView',
  computed: {
    isAuthenticated () {
      axios
        .get('api/mypage')
      return true
    }
  }
}
</script>

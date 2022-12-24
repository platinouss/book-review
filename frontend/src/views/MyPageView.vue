<template>
  <div class="app">
    마이페이지
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'MyPageView',
  data () {
    return {
      subject: ''
    }
  },
  mounted () {
    this.getMyPage()
  },
  methods: {
    getMyPage () {
      axios.get('/api/mypage',
        {
          headers: {
            Authorization: localStorage.getItem('access_token')
          }
        }
      ).then(response => {
        const newAccessToken = response.headers.authorization
        if (newAccessToken != null) {
          localStorage.setItem('access_token', newAccessToken)
        }
      })
    }
  }

}
</script>

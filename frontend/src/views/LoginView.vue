<template>
  <el-form ref="form" :model="loginForm" label-width="100px">
    <h2> 로그인 </h2>

    <el-form-item label="이메일">
      <el-input v-model="loginForm.email"></el-input>
    </el-form-item>

    <el-form-item label="패스워드" prop="pass">
      <el-input type="password" v-model="loginForm.password" autocomplete="off"></el-input>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="login()"> 로그인 </el-button>
      <el-button>Cancel</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import axios from 'axios'

export default {
  name: 'LoginView',
  data () {
    return {
      loginForm: {
        email: '',
        password: ''
      }
    }
  },
  methods: {
    login () {
      axios
        .post('/api/login', this.loginForm)
        .then((response) => {
          if (response.status === 200) {
            localStorage.setItem('jwt', response.data)
            console.log(response.data)
            alert('로그인 성공')
            this.$router.push('/')
          }
        })
        .catch(function (error) {
          console.log(error)
          alert('로그인 실패')
        })
    }
  }
}
</script>

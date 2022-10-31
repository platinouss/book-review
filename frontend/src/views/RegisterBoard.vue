<template>
  <el-form ref="form" :model="registerForm" label-width="100px">
    <h2> 회원가입 </h2>

    <el-form-item label="이메일">
      <el-input v-model="registerForm.email"></el-input>
    </el-form-item>

    <el-form-item label="패스워드" prop="pass">
      <el-input type="password" v-model="registerForm.password" autocomplete="off"></el-input>
    </el-form-item>

    <el-form-item label="이름">
      <el-input v-model="registerForm.name"></el-input>
    </el-form-item>

    <el-form-item label="성별">
      <el-select v-model="registerForm.gender" placeholder="성별을 선택해주세요.">
        <el-option label="남성" value="MALE"></el-option>
        <el-option label="여성" value="FEMALE"></el-option>
      </el-select>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="addUser()">Create</el-button>
      <el-button>Cancel</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import axios from 'axios'

export default {
  name: 'RegisterBoard',
  data () {
    return {
      registerForm: {
        email: '',
        password: '',
        name: '',
        gender: ''
      }
    }
  },
  methods: {
    addUser () {
      axios
        .post('/api/register', this.registerForm)
        .then((response) => {
          if (response.status === 200) {
            alert('회원가입 완료')
          }
        })
        .catch(function (error) {
          console.log(error)
          alert('회원가입 실패')
        })
    }
  }
}
</script>

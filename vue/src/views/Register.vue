<template>
  <div class="login-container">
    <div class="login-box">
      <div style="font-weight: bold; font-size: 30px; text-align: center; margin-bottom: 30px; color: #0c9c7a">Please
        sign up
      </div>
      <el-form :model="data.form" ref="formRef" :rules="data.rules">
        <el-form-item prop="username">
          <el-input :prefix-icon="User" size="large" v-model="data.form.username" placeholder="Username"/>
        </el-form-item>
        <el-form-item prop="password">
          <el-input :prefix-icon="Lock" size="large" v-model="data.form.password" placeholder="Password"
                    show-password/>
        </el-form-item>
        <el-form-item prop="newPassword">
          <el-input :prefix-icon="Lock" size="large" v-model="data.form.newPassword" placeholder="Confirm Password"
                    show-password/>
        </el-form-item>
        <el-form-item>
          <el-button size="large" style="width: 100%; background-color: #0c9c7a;color: white; border-color: #0c9c7a"
                     @click="register">Sign up
          </el-button>
        </el-form-item>
      </el-form>
      <div style="text-align: right;">
        Already have an account? Please <a href="/login">Sign in</a>
      </div>
    </div>

  </div>
</template>

<script setup>
import {reactive, ref} from "vue";
import {User, Lock} from "@element-plus/icons-vue";
import request from "@/utils/request";
import {ElMessage} from "element-plus";
import router from "@/router";

const data = reactive({
  form: {role: 'NORMALUSER'},
  rules: {
    username: [
      {required: true, message: 'Username', trigger: 'blur'},
    ],
    password: [
      {required: true, message: 'Password', trigger: 'blur'},
    ],
    newPassword: [
      {required: true, message: 'Confirm Password', trigger: 'blur'},
    ]
  }
})

const formRef = ref()


const register = () => {
  formRef.value.validate((valid => {
    if (valid) {
      // Use backend interface
      request.post('/register', data.form).then(res => {
        if (res.code === '200') {
          ElMessage.success("Register successfully")
          router.push('/login')
        } else {
          ElMessage.error(res.msg)
        }
      })
    }
  })).catch(error => {
    console.error(error)
  })
}

</script>

<style scoped>
.login-container {
  height: 100vh;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
  background: #0c9c7a;
  background-size: cover;
}

.login-box {
  width: 350px;
  padding: 50px 30px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(255, 255, 255, 0.3);
  background-color: #fff;
}
</style>
<template>
  <div class="front-container" style="width: 40%">
    <div class="card" style="padding: 20px">
      <div style="font-size: 20px; margin-bottom: 40px; text-align: center">Change password</div>
      <el-form ref="formRef" :model="data.user" :rules="data.rules" label-width="100px" style="padding-right: 30px">
        <el-form-item prop="password" label="Original password">
          <el-input v-model="data.user.password" autocomplete="off" show-password></el-input>
        </el-form-item>
        <el-form-item prop="newPassword" label="New password">
          <el-input v-model="data.user.newPassword" autocomplete="off" show-password></el-input>
        </el-form-item>
        <el-form-item prop="confirmPassword" label="Confirm password">
          <el-input v-model="data.user.confirmPassword" autocomplete="off" show-password></el-input>
        </el-form-item>
        <div style="text-align: center">
          <el-button type="primary" size="large" @click="updatePassword">保 存</el-button>
        </div>
      </el-form>
    </div>
  </div>

</template>

<script setup>
import {reactive, ref} from "vue";
import request from "@/utils/request";
import {ElMessage} from "element-plus";
import router from "@/router";

const formRef = ref()
const data = reactive({
  user: JSON.parse(localStorage.getItem('system-user') || '{}'),
  rules: {
    password: [
      {required: true, message: 'Enter original password', trigger: 'blur'},
    ],
    newPassword: [
      {required: true, message: 'Enter new password', trigger: 'blur'},
    ],
    confirmPassword: [
      {required: true, message: 'Confirm new password', trigger: 'blur'},
    ]
  }
})

const updatePassword = () => {
  if (data.user.newPassword !== data.user.confirmPassword) {
    ElMessage.warning('The two new passwords are different, please confirm!')
    return
  }
  request.put('/updatePassword', data.user).then(res => {
    if (res.code === '200') {
      ElMessage.success('Update successfully')
      logout()
    } else {
      ElMessage.error(res.msg)
    }
  })
}

const logout = () => {
  router.push('/login')
  localStorage.removeItem('system-user')
}
</script>
<template>
  <div class="front-container" style="width: 40%">
    <div class="card" style="padding: 20px">
      <div style="font-size: 20px; margin-bottom: 40px; text-align: center">Personal Informaton</div>

      <el-form ref="formRef" :model="data.user" :rules="data.rules" label-width="80px" style="padding-right: 30px">
        <el-form-item label="Avatar" prop="avatar">
          <el-upload
              class="avatar-uploader"
              :action="baseUrl + '/files/upload'"
              :show-file-list="false"
              :on-success="handleFileUpload"
          >
            <img v-if="data.user.avatar" :src="data.user.avatar" class="avatar"/>
            <el-icon v-else class="avatar-uploader-icon">
              <Plus/>
            </el-icon>
          </el-upload>
        </el-form-item>
        <el-form-item label="Username" prop="username">
          <el-input :disabled="data.user.id !== undefined" v-model="data.user.username"
                    placeholder="Please input username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Name" prop="name">
          <el-input v-model="data.user.name" placeholder="Please input name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="account" label="Balance">
          <div style="color: red; font-weight: bold">$ {{ data.user.account }}</div>
        </el-form-item>
        <div style="text-align: center">
          <el-button type="primary" size="large" @click="update">Save</el-button>
        </div>

      </el-form>
    </div>
  </div>

  <div style="background-color: #f0f2ff">
    <router-view @updateUser="updateUser"></router-view>
  </div>

</template>

<script setup>
import {reactive, ref} from "vue";
import request from "@/utils/request";
import {ElMessage} from "element-plus";

// Event emit
const emit = defineEmits(['updateUser'])

const baseUrl = import.meta.env.VITE_BASE_URL
const formRef = ref()
const data = reactive({
  user: JSON.parse(localStorage.getItem('system-user') || '{}'),
  rules: {
    username: [
      {required: true, message: 'Please input username', trigger: 'blur'},
    ]
  }
})

const loadUser = () => {
  request.get('/user/selectById/' + data.user.id).then(res => {
    data.user = res.data
    // Update local storage to the latest user data
    localStorage.setItem('system-user', JSON.stringify(res.data))
    emit('updateUser')
  })
}

loadUser()

const handleFileUpload = (res) => {
  data.user.avatar = res.data
}

const update = () => {
  request.put('/user/update', data.user).then((res) => {
    if (res.code === '200') {
      ElMessage.success('Update successfully')
      loadUser()
    } else {
      ElMessage.error(res.msg)
    }
  })
}

</script>

<style scoped>
.avatar-uploader .avatar {
  width: 130px;
  height: 130px;
  display: block;
}
</style>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 130px;
  height: 130px;
  text-align: center;
}
</style>
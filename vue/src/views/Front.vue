<template>
  <div>
    <div
        style="height: 60px; background-color: #2e3143; display: flex; align-items: center; border-bottom: 1px solid #ddd">
      <div style="flex: 1">
        <div style="padding-left: 20px; display: flex; align-items: center">
          <img src="@/assets/imgs/logo.png" alt="" style="width: 40px">
          <div style="font-weight: bold; font-size: 24px; margin-left: 5px; color: #fff">Store</div>
        </div>
      </div>
      <div style="width: fit-content; padding-right: 10px;">
        <el-dropdown>
          <div style="display: flex; align-items: center;">
            <img style="width: 40px; height: 40px; border-radius: 50%"
                 :src="data.user.avatar || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'"
                 alt="">
            <span style="color: #fff; margin-left: 5px">{{ data.user.name || 'default user' }}</span>
          </div>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="router.push('/front/person')">Person</el-dropdown-item>
              <el-dropdown-item @click="router.push('/front/password')">Password</el-dropdown-item>
              <el-dropdown-item @click="logout">Logout</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>

    <div style="background-color: #f0f2ff">
      <router-view @updateUser="updateUser"></router-view>
    </div>

    <Footer />

  </div>
</template>
<script setup>
import {reactive} from "vue";
import router from "@/router";
import {ElMessage} from "element-plus";
import Footer from "@/components/Footer.vue";

const data = reactive({
  user: JSON.parse(localStorage.getItem('system-user') || '{}')
})

const logout = () => {
  localStorage.removeItem('system-user')
  router.push('/login')
  ElMessage.success("Logout successfully")
}

const updateUser = () => {
  data.user = JSON.parse(localStorage.getItem('system-user') || '{}')
}

if (!data.user?.id) {
  ElMessage.error('Please login first!')
  router.push('/login')
}

</script>

<style>
.el-tooltip__trigger {
  cursor: pointer;
  outline: none !important;
}
</style>
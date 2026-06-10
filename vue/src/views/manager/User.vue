<template>
  <div>
    <div class="card" style="margin-bottom: 5px">
      <el-input style="width: 300px" v-model="data.name" placeholder="Enter name" :prefix-icon="Search"/>
      <el-button @click="load" type="primary" style="margin-left: 10px">Search</el-button>
      <el-button @click="reset" type="info">Reset</el-button>
    </div>

    <div class="card" style="margin-bottom: 5px">
      <div style="margin-bottom: 10px">
        <el-button @click="handleAdd" type="primary">Add</el-button>
      </div>
      <el-table :data="data.tableData" stripe style="width: 100%">
        <el-table-column prop="username" label="Username"/>
        <el-table-column prop="name" label="Name"/>
        <el-table-column prop="avatar" label="Avatar">
          <template #default="scope">
            <el-image :src="scope.row.avatar" style="width: 40px; height: 40px; display: block; border-radius: 50%"
                      :preview-src-list="[scope.row.avatar]" preview-teleported/>
          </template>
        </el-table-column>
        <el-table-column prop="role" label="Role"/>
        <el-table-column prop="account" label="Balance"/>
        <el-table-column label="Tool" width="180" fixed="right">
          <template #default="scope">
            <el-button type="primary" @click="handleEdit(scope.row)">Edit</el-button>
            <el-button type="danger" @click="deleteUser(scope.row.id)">Delete</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="card">
      <el-pagination v-model:current-page="data.pageNum" v-model:page-size="data.pageSize"
                     @current-change="load" background layout="total, prev, pager, next" :total="data.total"/>
    </div>

    <el-dialog title="User Information" v-model="data.formVisible" width="30%" destroy-on-close>
      <el-form ref="formRef" :model="data.form" :rules="data.rules" label-width="80px"
               style="padding-right: 30px; padding-top: 20px">
        <el-form-item prop="username" label="Username">
          <el-input :disabled="data.form.id !== undefined" v-model="data.form.username" placeholder="Enter username"
                    auto-complete="off"/>
        </el-form-item>
        <el-form-item prop="name" label="Name">
          <el-input v-model="data.form.name" placeholder="Enter name" auto-complete="off"/>
        </el-form-item>
        <el-form-item prop="avatar" label="Avatar">
          <el-upload
              :action="baseUrl + '/files/upload'"
              list-type="picture"
              :on-success="handleFileUpload"
          >
            <el-button size="small" type="primary">Upload</el-button>
          </el-upload>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="data.formVisible = false">Cancel</el-button>
          <el-button type="primary" @click="save">Save</el-button>
        </span>
      </template>
    </el-dialog>

  </div>
</template>

<script setup>
import {reactive, ref} from "vue";
import {Search} from "@element-plus/icons-vue";
import request from "@/utils/request";
import {ElMessage} from "element-plus";

const baseUrl = import.meta.env.VITE_BASE_URL
const formRef = ref()
const data = reactive({
  name: null,
  tableData: [],
  total: 0,
  pageNum: 1,
  pageSize: 5,
  formVisible: false,
  form: {},
  rules: {
    username: [
      {required: true, message: 'Please enter username', trigger: 'blur'}
    ]
  }
})

// function to search
const load = () => {
  request.get('/user/selectPage', {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      name: data.name,
    }
  }).then(res => {
    if (res.code === '200') {
      data.tableData = res.data?.list
      data.total = res.data?.total
    } else {
      ElMessage.error(res.msg)
    }
  })
}

const reset = () => {
  data.name = null
  load()
}

const deleteUser = (id) => {
  ElMessageBox.confirm('You are about to delete this user, are you sure?', 'Confirm', {type: 'warning'}).then(res => {
    request.delete('/user/delete/' + id).then(res => {
      if (res.code === '200') {
        ElMessage.success('Delete successfully')
        load()
      } else {
        ElMessage.error(res.msg)
      }
    })
  }).catch(() => {
    ElMessage.error('Delete failed')
  })
}

const handleAdd = () => {
  data.form = {}
  data.formVisible = true
}

const handleEdit = (row) => {
  data.form = JSON.parse(JSON.stringify(row))
  data.formVisible = true
}

const add = () => {
  request.post('/user/add', data.form).then(res => {
    if (res.code === '200') {
      ElMessage.success('Add successfully')
      data.formVisible = false
      load()
    } else {
      ElMessage.error(res.msg)
    }
  })
}

const update = () => {
  request.put('/user/update', data.form).then(res => {
    if (res.code === '200') {
      ElMessage.success('Edit successfully')
      data.formVisible = false
      load()
    } else {
      ElMessage.error(res.msg)
    }
  })
}

const save = () => {
  formRef.value.validate((valid) => {
    if (valid) {
      data.form.id ? update() : add()
    }
  })
}

// res.data is the url of the uploaded file
const handleFileUpload = (res) => {
  data.form.avatar = res.data
}
</script>
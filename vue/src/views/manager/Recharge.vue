<template>
  <div>

    <div class="card" style="margin-bottom: 5px;">
      <el-input v-model="data.userName" style="width: 300px; margin-right: 10px" placeholder="Please enter user name to search"></el-input>
      <el-date-picker style="width: 300px; margin-right: 10px" v-model="data.time" type="date" placeholder="Please enter date to search" format="YYYY-MM-DD" value-format="YYYY-MM-DD" />
      <el-button type="primary" @click="load">Search</el-button>
      <el-button type="info" style="margin: 0 10px" @click="reset">Reset</el-button>
    </div>

    <div class="card" style="margin-bottom: 5px">
      <el-table :data="data.tableData" stripe>
        <el-table-column prop="money" label="Recharge Amount">
          <template #default="scope">
            <b style="color: red">{{ scope.row.money }} Yuan</b>
          </template>
        </el-table-column>
        <el-table-column prop="userName" label="Recharger"></el-table-column>
        <el-table-column prop="type" label="Payment Method"></el-table-column>
        <el-table-column prop="time" label="Recharge Time"></el-table-column>
        <el-table-column label="Action" align="center" width="100">
          <template #default="scope">
            <el-button type="danger" @click="handleDelete(scope.row.id)">Delete</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="card">
      <el-pagination @current-change="load" background layout="total, prev, pager, next" v-model:page-size="data.pageSize" v-model:current-page="data.pageNum" :total="data.total"/>
    </div>
  </div>
</template>

<script setup>
import request from "@/utils/request";
import {reactive, ref} from "vue";
import {ElMessageBox, ElMessage} from "element-plus";

const formRef = ref()
const data = reactive({
  pageNum: 1,
  pageSize: 10,
  total: 0,
  formVisible: false,
  form: {},
  tableData: [],
  userName: null,
  time: null,
})

// Pagination query
const load = () => {
  request.get('/recharge/selectPage', {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      userName: data.userName,
      time: data.time
    }
  }).then(res => {
    data.tableData = res.data?.list
    data.total = res.data?.total
  })
}
load()

// Add
const handleAdd = () => {
  data.form = {}
  data.formVisible = true
}

// Edit
const handleEdit = (row) => {
  data.form = JSON.parse(JSON.stringify(row))
  data.formVisible = true
}

// Add save
const add = () => {
  request.post('/recharge/add', data.form).then(res => {
    if (res.code === '200') {
      load()
      ElMessage.success('Operation successful')
      data.formVisible = false
    } else {
      ElMessage.error(res.msg)
    }
  })
}

// Edit save
const update = () => {
  request.put('/recharge/update', data.form).then(res => {
    if (res.code === '200') {
      load()
      ElMessage.success('Operation successful')
      data.formVisible = false
    } else {
      ElMessage.error(res.msg)
    }
  })
}

// Dialog save
const save = () => {
  formRef.value.validate(valid => {
    if (valid) {
      // If data.form has id, it's an update; otherwise it's an add
      data.form.id ? update() : add()
    }
  })
}

// Delete
const handleDelete = (id) => {
  ElMessageBox.confirm('Data cannot be recovered after deletion. Are you sure you want to delete?', 'Delete Confirmation', { type: 'warning' }).then(res => {
    request.delete('/recharge/delete/' + id).then(res => {
      if (res.code === '200') {
        load()
        ElMessage.success('Operation successful')
      } else {
        ElMessage.error(res.msg)
      }
    })
  }).catch(err => {})
}

// Reset
const reset = () => {
  data.userName = null
  load()
}
</script>
<template>
  <div class="front-container">
    <div style="font-size: 20px; font-weight: bold; margin-bottom: 20px">My Recharge Records ({{ data.total }})</div>
    <div class="card" style="padding: 20px">
      <div style="margin-bottom: 20px; display: flex; align-items: center">
        <div style="flex: 1">
          <el-date-picker style="width: 300px; margin-right: 10px" v-model="data.time" type="date" placeholder="Please enter date to search" format="YYYY-MM-DD" value-format="YYYY-MM-DD" />
          <el-button type="primary" @click="load">Search</el-button>
          <el-button type="info" style="margin: 0 10px" @click="reset">Reset</el-button>
        </div>

        <b style="margin-left: 20px; color: red">Current Account Balance: {{ data.user.account }} Yuan</b>
        <el-button @click="handleAdd" type="primary" style="margin-left: 20px">Initiate Recharge</el-button>
      </div>
      <div>
        <el-table :data="data.tableData" stripe>
          <el-table-column prop="money" label="Recharge Amount">
            <template #default="scope">
              <b style="color: red">{{ scope.row.money }} Yuan</b>
            </template>
          </el-table-column>
          <el-table-column prop="type" label="Payment Method"></el-table-column>
          <el-table-column prop="time" label="Recharge Time"></el-table-column>
        </el-table>
      </div>

      <div style="margin-top: 20px">
        <el-pagination @current-change="load" layout="total, prev, pager, next" v-model:page-size="data.pageSize" v-model:current-page="data.pageNum" :total="data.total"/>
      </div>
    </div>

    <el-dialog title="User Recharge" width="30%" v-model="data.formVisible" :close-on-click-modal="false" destroy-on-close>
      <el-form ref="formRef" :model="data.form" :rules="data.rules" label-width="80px" style="padding-right: 30px; padding-top: 20px">
        <el-form-item label="Recharge Amount" prop="money">
          <el-input-number style="width: 200px" :min="1" v-model="data.form.money" autocomplete="off" />
        </el-form-item>
        <el-form-item label="Payment Method" prop="type" style="margin-top: 30px">
          <el-radio-group v-model="data.form.type">
            <el-radio value="Visa card"><img style="width: 100px; height: 40px" src="@/assets/imgs/visa-card.png" alt=""></el-radio>
            <el-radio value="Master card"><img style="width: 100px; height: 40px" src="@/assets/imgs/master-card.png" alt=""></el-radio>
          </el-radio-group>
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
import request from "@/utils/request";
import {reactive, ref} from "vue";
import {ElMessageBox, ElMessage} from "element-plus";

const formRef = ref()
const data = reactive({
  user: JSON.parse(localStorage.getItem('system-user') || '{}'),
  pageNum: 1,
  pageSize: 10,
  total: 0,
  formVisible: false,
  form: {},
  tableData: [],
  time: null,
  rules: {
    money: [
      { required: true, message: 'Please enter amount', trigger: 'blur' }
    ],
    type: [
      { required: true, message: 'Please select payment method', trigger: 'change' }
    ]
  }
})

const loadAccount = () => {
  request.get('/user/selectById/' + data.user.id).then(res => {
    data.user.account = res.data.account
  })
}
loadAccount()

// Pagination query
const load = () => {
  request.get('/recharge/selectPage', {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      userId: data.user.id,
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
  data.form = { money: 1, type: 'Visa card' }
  data.formVisible = true
}

// Edit
const handleEdit = (row) => {
  data.form = JSON.parse(JSON.stringify(row))
  data.formVisible = true
}

// Add save
const add = () => {
  data.form.userId = data.user.id
  request.post('/recharge/add', data.form).then(res => {
    if (res.code === '200') {
      load()
      loadAccount()
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
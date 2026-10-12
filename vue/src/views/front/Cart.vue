<template>
  <div class="front-container">
    <div class="card" style="padding: 20px">
      <div>
        <el-table :data="data.tableData" stripe @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" />
          <el-table-column label="Goods Image">
            <template #default="scope">
              <img style="width: 50px; height: 50px; display: block" :src="scope.row.goodsImg" alt="">
            </template>
          </el-table-column>
          <el-table-column label="Goods Name" prop="goodsName"></el-table-column>
          <el-table-column label="Unit Price">
            <template #default="scope">
              <b style="color: red">￥{{ scope.row.goodsPrice }}</b>
            </template>
          </el-table-column>
          <el-table-column label="Quantity">
            <template #default="scope">
              <el-input-number @change="calTotal" v-model="scope.row.num" :min="1" style="width: 150px"></el-input-number>
            </template>
          </el-table-column>
          <el-table-column label="Action" align="center" width="100">
            <template #default="scope">
              <el-button type="danger" @click="handleDelete(scope.row.id)">Delete</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div style="text-align: right; margin-top: 20px; font-size: 20px">Total Price:
        <b style="color: red; display: inline-block; min-width: 60px; text-align: left">{{ data.total }} Yuan</b>
        <div style="margin-top: 10px"><el-button @click="addOrder" type="danger">Place Order Now</el-button></div>
      </div>
    </div>



    <el-dialog title="Order Information" width="30%" v-model="data.formVisible" :close-on-click-modal="false" destroy-on-close>
      <el-form ref="formRef" :model="data.form" :rules="data.rules" label-width="80px" style="padding-right: 30px; padding-top: 20px">
        <el-form-item label="Name" prop="name">
          <el-input v-model="data.form.name" autocomplete="off" />
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
  total: 0,
  formVisible: false,
  form: {},
  tableData: [],
  selectedRows: [],
  rules: {
    name: [
      { required: true, message: 'Please enter name', trigger: 'blur' },
    ]
  }
})

const calTotal = () => {
  data.total = 0
  // rows is the selected rows
  data.selectedRows.forEach(item => {
    data.total += item.goodsPrice * item.num
  })
  if (data.total > 0) {
    data.total = data.total.toFixed(2)
  }
}

const handleSelectionChange = (rows) => {
  data.selectedRows = rows
  calTotal()
}

// Pagination query
const load = () => {
  request.get('/cart/selectAll', {
    params: {
      userId: data.user.id
    }
  }).then(res => {
    data.tableData = res.data
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
  request.post('/cart/add', data.form).then(res => {
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
  request.put('/cart/update', data.form).then(res => {
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
  ElMessageBox.confirm('Are you sure you want to delete?', 'Delete Confirmation', { type: 'warning' }).then(res => {
    request.delete('/cart/delete/' + id).then(res => {
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
  data.name = null
  load()
}
</script>
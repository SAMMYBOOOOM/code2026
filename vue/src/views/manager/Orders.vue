<template>
  <div>
    <div class="card" style="margin-bottom: 5px;">
      <el-input v-model="data.orderNo" style="width: 300px; margin-right: 10px" placeholder="Please enter order number to search"></el-input>
      <el-button type="primary" @click="load">Search</el-button>
      <el-button type="info" style="margin: 0 10px" @click="reset">Reset</el-button>
    </div>

    <div class="card" style="margin-bottom: 5px">
      <el-table :data="data.tableData" stripe :cell-style="{'backgroundColor': '#e8efff'}" default-expand-all>
        <el-table-column type="expand">
          <template #default="props">
            <div style="padding: 10px">
              <el-table :data="props.row.orderDetailList" border>
                <el-table-column label="Goods Image" prop="goodsImg" width="100">
                  <template #default="scope">
                    <img :src="scope.row.goodsImg" alt="" style="width: 50px; height: 50px">
                  </template>
                </el-table-column>
                <el-table-column label="Goods Name" prop="goodsName" show-overflow-tooltip></el-table-column>
                <el-table-column label="Unit Price" prop="goodsPrice" width="100"></el-table-column>
                <el-table-column label="Quantity" prop="num" width="100">
                  <template #default="scope">
                    X {{ scope.row.num }}
                  </template>
                </el-table-column>
                <el-table-column label="Subtotal" width="150">
                  <template #default="scope">
                    <b style="color: red">{{ (scope.row.goodsPrice * scope.row.num).toFixed(2) }} Yuan</b>
                  </template>
                </el-table-column>
              </el-table>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="orderNo" label="Order Number">
          <template #default="scope">
            <b style="color: #333">{{ scope.row.orderNo }}</b>
          </template>
        </el-table-column>
        <el-table-column prop="total" label="Total Price">
          <template #default="scope">
            <b style="color: red">{{ scope.row.total }} Yuan</b>
          </template>
        </el-table-column>
        <el-table-column prop="userName" label="Orderer"></el-table-column>
        <el-table-column prop="deliverType" label="Delivery Type"></el-table-column>
        <el-table-column prop="status" label="Status">
          <template #default="scope">
            <el-tag type="danger" v-if="scope.row.status === 'Cancelled'">Cancelled</el-tag>
            <el-tag type="warning" v-if="scope.row.status === 'Pending'">Pending</el-tag>
            <el-tag type="primary" v-if="scope.row.status === 'Delivered'">Delivered</el-tag>
            <el-tag type="primary" v-if="scope.row.status === 'Shipped'">Shipped</el-tag>
            <el-tag type="success" v-if="scope.row.status === 'Completed'">Completed</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="time" label="Order Time"></el-table-column>
        <el-table-column label="Order Action" align="center" width="100">
          <template #default="scope">
            <el-button type="primary" @click="handleEdit(scope.row.id)">Ship</el-button>
          </template>
        </el-table-column>
        <el-table-column label="Delete" align="center" width="100">
          <template #default="scope">
            <el-button type="danger" @click="handleDelete(scope.row.id)">Delete</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="card">
      <el-pagination @current-change="load" background layout="total, prev, pager, next" v-model:page-size="data.pageSize" v-model:current-page="data.pageNum" :total="data.total"/>
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
  pageNum: 1,
  pageSize: 3,
  total: 0,
  formVisible: false,
  form: {},
  tableData: [],
  name: null,
  rules: {
    name: [
      { required: true, message: 'Please enter name', trigger: 'blur' },
    ]
  }
})

// Pagination query
const load = () => {
  request.get('/orders/selectPage', {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      orderNo: data.orderNo
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
  request.post('/orders/add', data.form).then(res => {
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
  request.put('/orders/update', data.form).then(res => {
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
    request.delete('/orders/delete/' + id).then(res => {
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
  data.orderNo = null
  load()
}
</script>

<style scoped>
.el-tag {
  font-weight: bold;
}
.el-tag--warning {
  color: orange;
  background-color: #fff2de;
}
</style>
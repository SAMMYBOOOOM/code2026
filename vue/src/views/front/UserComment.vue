<template>
  <div class="front-container">

    <div class="card" style="margin-bottom: 5px;">
      <el-input v-model="data.content" style="width: 300px; margin-right: 10px" placeholder="Please enter review content to search"></el-input>
      <el-input v-model="data.goodsName" style="width: 300px; margin-right: 10px" placeholder="Please enter goods name to search"></el-input>
      <el-button type="primary" @click="load">Search</el-button>
      <el-button type="info" style="margin: 0 10px" @click="reset">Reset</el-button>
    </div>

    <div class="card" style="margin-bottom: 5px">
      <el-table :data="data.tableData" stripe>
        <el-table-column prop="goodsImg" label="Goods Image">
          <template #default="scope">
            <el-image style="width: 50px; height: 50px; display: block; border-radius: 5px" :src="scope.row.goodsImg" :preview-src-list="[scope.row.goodsImg]" preview-teleported></el-image>
          </template>
        </el-table-column>
        <el-table-column prop="goodsName" label="Goods Name">
          <template #default="scope">
            <a :href="'/front/goodsDetail?id=' + scope.row.goodsId">{{ scope.row.goodsName }}</a>
          </template>
        </el-table-column>
        <el-table-column prop="orderNo" label="Order Number"></el-table-column>
        <el-table-column prop="score" label="Rating" width="200">
          <template #default="scope">
            <el-rate disabled allow-half show-score v-model="scope.row.score" />
          </template>
        </el-table-column>
        <el-table-column prop="content" label="Review Content" width="200" show-overflow-tooltip></el-table-column>
        <el-table-column prop="time" label="Time"></el-table-column>
        <el-table-column label="Action" align="center" width="160">
          <template #default="scope">
            <el-button type="primary" @click="handleEdit(scope.row)">Edit</el-button>
            <el-button type="danger" @click="handleDelete(scope.row.id)">Delete</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="card">
      <el-pagination @current-change="load" background layout="total, prev, pager, next" v-model:page-size="data.pageSize" v-model:current-page="data.pageNum" :total="data.total"/>
    </div>

    <el-dialog title="Review Information" width="30%" v-model="data.formVisible" :close-on-click-modal="false" destroy-on-close>
      <el-form ref="formRef" :model="data.form" :rules="data.rules" label-width="80px" style="padding-right: 30px; padding-top: 20px">
        <el-form-item label="Rating" prop="score">
          <el-rate show-score allow-half v-model="data.form.score"></el-rate>
        </el-form-item>
        <el-form-item label="Content" prop="content">
          <el-input type="textarea" :rows="3" v-model="data.form.content" autocomplete="off" placeholder="Please enter review content" />
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
  content: null,
  goodsName: null,
  rules: {
    score: [
      { required: true, message: 'Please enter rating', trigger: 'change' },
    ],
    content: [
      { required: true, message: 'Please enter content', trigger: 'blur' },
    ],
  }
})

// Pagination query
const load = () => {
  request.get('/comment/selectPage', {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      content: data.content,
      goodsName: data.goodsName,
      userId: data.user.id,
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
  request.post('/comment/add', data.form).then(res => {
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
  request.put('/comment/update', data.form).then(res => {
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
    request.delete('/comment/delete/' + id).then(res => {
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
  data.content = null
  data.goodsName = null
  load()
}
</script>
<template>
  <div>

    <div class="card" style="margin-bottom: 5px;">
      <el-input v-model="data.goodsName" style="width: 300px; margin-right: 10px"
                placeholder="Please enter name to search"></el-input>
      <el-button type="primary" @click="load">Search</el-button>
      <el-button type="info" style="margin: 0 10px" @click="reset">Reset</el-button>
    </div>

    <div class="card" style="margin-bottom: 5px">
      <div style="margin-bottom: 10px">
        <el-button type="primary" @click="handleAdd">Add</el-button>
      </div>
      <el-table :data="data.tableData" stripe>
        <el-table-column prop="goodsName" label="Product"></el-table-column>
        <el-table-column prop="img" label="Image">
          <template #default="scope">
            <el-image v-if="scope.row.img" :src="scope.row.img" :preview-src-list="[scope.row.img]" preview-teleported
                      style="width: 100px; height: 70px;"></el-image>
          </template>
        </el-table-column>
        <el-table-column label="Actions" align="center" width="160">
          <template #default="scope">
            <el-button type="primary" @click="handleEdit(scope.row)">Edit</el-button>
            <el-button type="danger" @click="handleDelete(scope.row.id)">Delete</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="card">
      <el-pagination @current-change="load" background layout="total, prev, pager, next"
                     v-model:page-size="data.pageSize" v-model:current-page="data.pageNum" :total="data.total"/>
    </div>

    <el-dialog title="Carousel Information" width="30%" v-model="data.formVisible" :close-on-click-modal="false"
               destroy-on-close>
      <el-form ref="formRef" :model="data.form" :rules="data.rules" label-width="80px"
               style="padding-right: 30px; padding-top: 20px">
        <el-form-item label="Product" prop="goodsId">
          <el-select style="width: 100%" v-model="data.form.goodsId">
            <el-option v-for="item in data.goodsList" :key="item.id" :value="item.id" :label="item.name"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Image" prop="img">
          <el-upload
              :action="baseUrl + '/files/upload'"
              :on-success="handleFileUpload"
              list-type="picture"
              :show-file-list="false"
          >
            <img v-if="data.form.img" style="width: 200px; height: 100px; border-radius: 5px; " :src="data.form.img"
                 alt="">
            <div v-else
                 style="width: 200px; height: 150px; border-radius: 5px; border: 1px dashed #ccc; display: flex; align-items: center; justify-content: center">
              Upload Image
            </div>
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
  goodsList: [],
  goodsName: null,
  rules: {
    goodsId: [
      {required: true, message: 'Please select a product', trigger: 'change'},
    ],
    img: [
      {required: true, message: 'Please upload an image', trigger: 'blur'},
    ]
  }
})
const baseUrl = import.meta.env.VITE_BASE_URL
const handleFileUpload = (res) => {
  data.form.img = res.data
}

request.get('/goods/selectAll').then(res => {
  data.goodsList = res.data
})

// Pagination query
const load = () => {
  request.get('/carousel/selectPage', {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      goodsName: data.goodsName
    }
  }).then(res => {
    data.tableData = res.data?.list
    data.total = res.data?.total
  })
}
load()

// Add new
const handleAdd = () => {
  data.form = {}
  data.formVisible = true
}

// Edit
const handleEdit = (row) => {
  data.form = JSON.parse(JSON.stringify(row))
  data.formVisible = true
}

// Save new carousel
const add = () => {
  request.post('/carousel/add', data.form).then(res => {
    if (res.code === '200') {
      load()
      ElMessage.success('Operation successful')
      data.formVisible = false
    } else {
      ElMessage.error(res.msg)
    }
  })
}

// Save edited carousel
const update = () => {
  request.put('/carousel/update', data.form).then(res => {
    if (res.code === '200') {
      load()
      ElMessage.success('Operation successful')
      data.formVisible = false
    } else {
      ElMessage.error(res.msg)
    }
  })
}

// Save from dialog
const save = () => {
  formRef.value.validate(valid => {
    if (valid) {
      // if data.form has an id then update, otherwise add
      data.form.id ? update() : add()
    }
  })
}

// Delete
const handleDelete = (id) => {
  ElMessageBox.confirm('Data cannot be recovered after deletion. Are you sure you want to delete?', 'Delete Confirmation', {type: 'warning'}).then(res => {
    request.delete('/carousel/delete/' + id).then(res => {
      if (res.code === '200') {
        load()
        ElMessage.success('Operation successful')
      } else {
        ElMessage.error(res.msg)
      }
    })
  }).catch(err => {
  })
}

// Reset
const reset = () => {
  data.goodsName = null
  load()
}
</script>
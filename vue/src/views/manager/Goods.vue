<template>
  <div>

    <div class="card" style="margin-bottom: 5px;">
      <el-input v-model="data.name" style="width: 300px; margin-right: 10px" placeholder="Please enter name to search"></el-input>
      <el-select style="width: 300px; margin-right: 10px" v-model="data.categoryId" placeholder="Please select product category">
        <el-option v-for="item in data.categoryList" :key="item.id" :value="item.id" :label="item.name"></el-option>
      </el-select>
      <el-button type="primary" @click="load">Search</el-button>
      <el-button type="info" style="margin: 0 10px" @click="reset">Reset</el-button>
    </div>

    <div class="card" style="margin-bottom: 5px">
      <div style="margin-bottom: 10px">
        <el-button type="primary" @click="handleAdd">Add</el-button>
      </div>
      <el-table tooltip-effect="dark myEff" :data="data.tableData" stripe>
        <el-table-column prop="name" label="Name" show-overflow-tooltip></el-table-column>
        <el-table-column prop="img" label="Image">
          <template #default="scope">
            <el-image v-if="scope.row.img" :src="scope.row.img" :preview-src-list="[scope.row.img]" preview-teleported style="width: 100px; height: 100px;"></el-image>
          </template>
        </el-table-column>
        <el-table-column prop="price" label="Price"></el-table-column>
        <el-table-column prop="description" label="Description" show-overflow-tooltip></el-table-column>
        <el-table-column prop="content" label="Details">
          <template #default="scope">
            <el-button type="primary" @click="view(scope.row.content)">View Details</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="store" label="Stock"></el-table-column>
        <el-table-column prop="categoryName" label="Category Name"></el-table-column>
        <el-table-column prop="status" label="Listing Status"></el-table-column>
        <el-table-column prop="views" label="Views"></el-table-column>
        <el-table-column prop="saleCount" label="Sales"></el-table-column>
        <el-table-column prop="time" label="Creation Time"></el-table-column>

        <el-table-column label="Actions" align="center" width="160">
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

    <el-dialog title="Product Information" width="50%" v-model="data.formVisible" :close-on-click-modal="false" destroy-on-close>
      <el-form ref="formRef" :model="data.form" :rules="data.rules" label-width="80px" style="padding-right: 30px; padding-top: 20px">
        <el-form-item prop="name" label="Name">
          <el-input v-model="data.form.name" placeholder="Please enter name"></el-input>
        </el-form-item>
        <el-form-item prop="img" label="Image">
          <el-upload :action="uploadUrl" list-type="picture" :on-success="handleImgSuccess">
            <el-button type="primary">Upload Image</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item prop="price" label="Price">
          <el-input-number :min="0" v-model="data.form.price" placeholder="Please enter price"></el-input-number>
        </el-form-item>
        <el-form-item prop="description" label="Description">
          <el-input type="textarea" :rows="3" v-model="data.form.description" placeholder="Please enter description"></el-input>
        </el-form-item>

        <el-form-item prop="store" label="Stock">
          <el-input v-model="data.form.store" placeholder="Please enter stock"></el-input>
        </el-form-item>
        <el-form-item prop="categoryId" label="Category">
          <el-select style="width: 100%" v-model="data.form.categoryId">
            <el-option v-for="item in data.categoryList" :key="item.id" :value="item.id" :label="item.name"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="status" label="Listing Status">
          <el-radio-group v-model="data.form.status">
            <el-radio-button label="Listed" value="Listed"></el-radio-button>
            <el-radio-button label="Unlisted" value="Unlisted"></el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item prop="content" label="Details">
          <div style="border: 1px solid #ccc; width: 100%">
            <Toolbar
                style="border-bottom: 1px solid #ccc"
                :editor="editorRef"
                :mode="mode"
            />
            <Editor
                style="height: 500px; overflow-y: hidden;"
                v-model="data.form.content"
                :mode="mode"
                :defaultConfig="editorConfig"
                @onCreated="handleCreated"
            />
          </div>
        </el-form-item>

      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="data.formVisible = false">Cancel</el-button>
        <el-button type="primary" @click="save">Save</el-button>
      </span>
      </template>
    </el-dialog>

    <el-dialog title="Product Details" v-model="data.viewVisible" width="50%" :close-on-click-modal="false" destroy-on-close>
      <div class="editor-content-view" style="padding: 20px" v-html="data.content"></div>
      <template #footer>
    <span class="dialog-footer">
      <el-button @click="data.viewVisible = false">Close</el-button>
    </span>
      </template>
    </el-dialog>

  </div>
</template>

<script setup>
import request from "@/utils/request";
import {reactive, ref} from "vue";
import {ElMessageBox, ElMessage} from "element-plus";
import '@wangeditor/editor/dist/css/style.css' // import css
import {onBeforeUnmount, shallowRef} from "vue";
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'

const uploadUrl = import.meta.env.VITE_BASE_URL + '/files/upload'
const formRef = ref()
const data = reactive({
  pageNum: 1,
  pageSize: 10,
  total: 0,
  formVisible: false,
  form: {},
  tableData: [],
  categoryList: [],
  name: null,
  categoryId: null,
  viewVisible: false,
  content: null,
  rules: {
    name: [
      { required: true, message: 'Please enter name', trigger: 'blur' }
    ],
    img: [
      { required: true, message: 'Please enter image', trigger: 'blur' }
    ],
    price: [
      { required: true, message: 'Please enter price', trigger: 'blur' }
    ],
    description: [
      { required: true, message: 'Please enter description', trigger: 'blur' }
    ],
    content: [
      { required: true, message: 'Please enter details', trigger: 'blur' }
    ],
    store: [
      { required: true, message: 'Please enter stock', trigger: 'blur' }
    ],
    categoryId: [
      { required: true, message: 'Please enter category ID', trigger: 'change' }
    ]
  }
})


/* wangEditor5 initialization start */
const baseUrl = import.meta.env.VITE_BASE_URL
const editorRef = shallowRef()  // Editor instance, must use shallowRef
const mode = 'default'
const editorConfig = { MENU_CONF: {} }
// Image upload configuration
editorConfig.MENU_CONF['uploadImage'] = {
  server: baseUrl + '/files/wang/upload',  // Server image upload endpoint
  fieldName: 'file'  // Server image upload parameter name
}
// Destroy editor before component unmount to prevent memory leaks
onBeforeUnmount(() => {
  const editor = editorRef.value
  if (editor == null) return
  editor.destroy()
})
// Record editor instance, important!
const handleCreated = (editor) => {
  editorRef.value = editor
}
/* wangEditor5 initialization end */

const view = (content) => {
  data.content = content
  data.viewVisible = true
}

// File upload success handler
const handleImgSuccess = (res) => {
  data.form.img = res.data  // res.data is the file path returned after upload, assign it to the form property
}

request.get('/category/selectAll').then(res => {
  data.categoryList = res.data
})


// Pagination query
const load = () => {
  request.get('/goods/selectPage', {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      name: data.name,
      categoryId: data.categoryId
    }
  }).then(res => {
    data.tableData = res.data?.list
    data.total = res.data?.total
  })
}
load()

// Add new
const handleAdd = () => {
  data.form = { status: 'Listed', price: 0 }
  data.formVisible = true
}

// Edit
const handleEdit = (row) => {
  data.form = JSON.parse(JSON.stringify(row))
  data.formVisible = true
}

// Save new product
const add = () => {
  request.post('/goods/add', data.form).then(res => {
    if (res.code === '200') {
      load()
      ElMessage.success('Operation successful')
      data.formVisible = false
    } else {
      ElMessage.error(res.msg)
    }
  })
}

// Save edited product
const update = () => {
  request.put('/goods/update', data.form).then(res => {
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
  ElMessageBox.confirm('Data cannot be recovered after deletion. Are you sure you want to delete?', 'Delete Confirmation', { type: 'warning' }).then(res => {
    request.delete('/goods/delete/' + id).then(res => {
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
  data.categoryId = null
  load()
}
</script>
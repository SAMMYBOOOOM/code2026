<template>
  <div class="front-container" style="width: 50%">
    <div class="card" style="padding: 20px; display: flex; grid-gap: 20px; margin-bottom: 10px">
      <img :src="data.goods.img" alt="" style="width: 300px; height: 300px">
      <div style="flex: 1">
        <div style="display: flex; align-items: flex-start; grid-gap: 20px; margin-bottom: 10px">
          <div style="font-size: 22px; font-weight: bold; line-height: 25px; flex: 1">
            <el-tag style="margin-right: 5px; float: left; background-color: red; color: white" type="danger" v-if="data.goods.recommend === '是'">Recommended</el-tag>
            {{ data.goods.name }}
          </div>
          <div style="width: 60px; cursor: pointer; color: #666" @click="addCollect" v-if="!data.userCollect?.id">
            <el-icon style="position: relative; top: 3px" size="18"><Star /></el-icon>Favorite
          </div>
          <div style="width: 100px; cursor: pointer; color: orange" @click="removeCollect" v-if="data.userCollect?.id">
            <el-icon style="position: relative; top: 3px" size="18"><StarFilled /></el-icon>Unfavorite
          </div>
        </div>
        <div style="margin-bottom: 20px">
          <span style="color: red; font-size: 18px">￥</span><b style="color: red; font-size: 30px">{{ data.goods.price }}</b>
          <span style="color: #666; margin-left: 20px">Total Sales {{ data.goods.saleCount }}</span>
          <span style="color: #666; margin-left: 20px">Stock Remaining {{ data.goods.store }}</span>
        </div>
        <div style="margin-bottom: 20px; padding: 10px; border-radius: 5px; background-color: #e8e4e4; line-height: 25px; text-align: justify">{{ data.goods.description }}</div>
        <div>
          <el-input-number style="width: 150px; height: 40px" :min="1" v-model="data.num"></el-input-number>
          <el-button @click="addCart" style="height: 40px; margin-left: 5px" type="danger">Add to Cart</el-button>
          <el-button @click="handleAddOrder" style="height: 40px; margin-left: 5px" type="danger">Buy Now</el-button>
        </div>
        <div style="margin-top: 10px; color: #666">Products sold and shipped by the Campus Store; invoices and after-sales service are provided by the store. Please buy with confidence!</div>
      </div>
    </div>

    <div class="card" style="padding: 20px; margin-bottom: 50px">
      <div style="font-size: 20px; padding-bottom: 10px; border-bottom: 1px solid #ddd">
        <span @click="changeTab('Product Details')" style="cursor: pointer" :class="{'current-active': data.current === 'Product Details' }">Product Details</span>
        <span @click="changeTab('Product Reviews')" :class="{'current-active': data.current === 'Product Reviews' }" style="cursor: pointer; margin-left: 20px">Product Reviews</span>
      </div>
      <div v-if="data.current === 'Product Details'" style="padding: 10px" v-html="data.goods.content"></div>
      <div v-if="data.current === 'Product Reviews'" style="min-height: 700px">
        <div v-if="data.commentList.length === 0" style="padding: 50px; text-align: center; color: #666">No reviews yet...</div>
        <div v-if="data.commentList.length > 0" style="padding: 20px;">
          <!--          Display comment list-->
          <div v-for="(item, index) in data.commentList" :key="item.id" style="display: flex; grid-gap: 10px; padding: 10px 0;
            border-bottom: 1px solid #ddd" :style="{ 'borderWidth': index === data.commentList.length - 1 ? 0 : '1px' }">
            <img :src="item.userAvatar" alt="" style="width: 50px; height: 50px; border-radius: 50%">
            <div style="flex: 1">
              <div><span>{{ item.userName }}</span>
                <span style="color: #666; font-size: 13px; margin-left: 10px">{{ item.time }}</span>
              </div>
              <div style="margin-bottom: 5px"> <el-rate v-model="item.score" show-score allow-half disabled></el-rate></div>
              <div>{{ item.content }}</div>
            </div>
          </div>
          <div style="margin-top: 20px">
            <el-pagination @current-change="loadComment" layout="total, prev, pager, next" v-model:page-size="data.pageSize" v-model:current-page="data.pageNum" :total="data.total"/>
          </div>
        </div>
      </div>

    </div>

    <el-dialog title="Order Information" width="30%" v-model="data.formVisible" :close-on-click-modal="false" destroy-on-close>
      <el-form ref="formRef" :model="data.form" :rules="data.rules" label-width="100px" style="padding-right: 30px">
        <el-form-item label="Delivery Type" prop="deliverType">
          <el-radio-group v-model="data.form.deliverType">
            <el-radio-button value="Pickup" label="Pickup"></el-radio-button>
            <el-radio-button value="Delivery" label="Delivery"></el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="Delivery Address" prop="address" v-if="data.form.deliverType === 'Delivery'">
          <el-input v-model="data.form.address" type="textarea" :rows="3" placeholder="Please enter the delivery address, including contact person, phone number, and address details"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="data.formVisible = false">Cancel</el-button>
        <el-button type="primary" @click="addOrder">Confirm</el-button>
      </span>
      </template>
    </el-dialog>

  </div>
</template>

<script setup>
import { reactive, ref } from "vue";
import router from "@/router";
import request from "@/utils/request";
import {ElMessage} from "element-plus";

const formRef = ref()
const data = reactive({
  user: JSON.parse(localStorage.getItem('system-user') || '{}'),
  id: router.currentRoute.value.query.id,
  goods: {},
  num: 1,
  current: 'Product Details',
  commentList: [],
  pageNum: 1,
  pageSize: 5,
  total: 0,
  userCollect: {},
  form: {},
  formVisible: false,
  rules: {
    deliverType: [
      { required: true, message: 'Please select delivery type', trigger: 'change' }
    ],
    address: [
      { required: true, message: 'Please enter delivery address', trigger: 'blur' }
    ]
  }
})

const loadComment = () => {
  request.get('/comment/selectPage', {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      goodsId: data.id
    }
  }).then(res => {
    data.commentList = res.data?.list
    data.total = res.data?.total
  })
}
loadComment()

const handleAddOrder = () => {
  data.form = {}
  data.formVisible = true
}

const addOrder = () => {
  formRef.value.validate(valid => {
    if (valid) {
      data.form.userId = data.user.id
      data.form.cartList = [ {goodsId: data.id, num: data.num } ]
      request.post('/orders/add', data.form).then(res => {
        if (res.code === '200') {
          ElMessage.success('Order placed successfully')
          load()
          data.formVisible = false
        } else {
          ElMessage.error(res.msg)
        }
      })
    }
  })
}

const addCart = () => {
  request.post('/cart/add', { goodsId: data.id, num: data.num, userId: data.user.id }).then(res => {
    if (res.code === '200') {
      ElMessage.success('Added to cart successfully')
    } else {
      ElMessage.error(res.msg)
    }
  })
}

// Whether the current product has been favorited by the currently logged-in user
const loadCollect = () => {
  request.get('/collect/selectAll', {
    params:{
      goodsId: data.id,
      userId: data.user.id
    }
  }).then(res => {
    if (res.data?.length > 0) {  // Data found, meaning the user has already favorited it
      data.userCollect = res.data[0]
    } else {
      data.userCollect = {}
    }
  })
}
loadCollect()

// Remove from favorites
const removeCollect = () => {
  request.delete('/collect/delete/' + data.userCollect.id).then(res => {
    if (res.code === '200') {
      ElMessage.success('Operation successful')
      loadCollect()
    } else {
      ElMessage.error(res.msg)
    }
  })
}

const addCollect = () => {
  request.post('/collect/add', { goodsId: data.id, userId: data.user.id }).then(res => {
    if (res.code === '200') {
      ElMessage.success('Operation successful')
      loadCollect()
    } else {
      ElMessage.error(res.msg)
    }
  })
}

const changeTab = (tabName) => {
  data.current = tabName
}

const load = () => {
  request.get('/goods/selectById/' + data.id).then(res => {
    data.goods = res.data
  })
}
load()
</script>

<style>
.current-active {
  color: red;
  border-bottom: 2px solid red;
  padding-bottom: 10px
}
</style>
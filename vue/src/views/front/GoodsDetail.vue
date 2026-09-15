<template>
  <div class="front-container" style="width: 50%">
    <div class="card" style="padding: 20px; display: flex; grid-gap: 20px; margin-bottom: 10px">
      <img :src="data.goods.img" alt="" style="width: 300px; height: 300px">
      <div style="flex: 1">
        <div style="display: flex; align-items: flex-start; grid-gap: 20px; margin-bottom: 10px">
          <div style="font-size: 22px; font-weight: bold; line-height: 25px">
            <el-tag style="margin-right: 5px; float: left; background-color: red; color: white" type="danger" v-if="data.goods.recommend === '是'">Recommended</el-tag>
            {{ data.goods.name }}</div>
          <div style="width: 60px; cursor: pointer; color: #666">
            <el-icon style="position: relative; top: 3px" size="18"><Star /></el-icon>Favorite
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
          <el-button style="height: 40px; margin-left: 5px" type="danger">Add to Cart</el-button>
          <el-button style="height: 40px; margin-left: 5px" type="danger">Buy Now</el-button>
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
        <div v-if="data.commentList.length > 0" style="padding: 20px; text-align: center">
          <!--          Display comment list-->
        </div>
      </div>

    </div>
  </div>
</template>

<script setup>
import { reactive } from "vue";
import router from "@/router";
import request from "@/utils/request";

const data = reactive({
  id: router.currentRoute.value.query.id,
  goods: {},
  num: 1,
  current: 'Product Details',
  commentList: []
})

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
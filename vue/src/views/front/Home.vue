<template>
  <div class="front-container">
    <!--   First row start -->
    <div style="display: flex; grid-gap: 20px; margin-bottom: 10px">
      <div style="flex: 1">
        <el-carousel height="450px">
          <el-carousel-item v-for="item in data.carouselList" :key="item.id">
            <img @click="router.push('/front/goodsDetail?id=' + item.goodsId)" :src="item.img" alt=""
                 style="width: 100%; height: 450px; cursor: pointer">
          </el-carousel-item>
        </el-carousel>
      </div>
      <div class="card" style="width: 260px">
        <div style="display: flex; align-items: center; padding-bottom: 5px; border-bottom: 1px solid red">
          <img src="@/assets/imgs/love.png" alt="" style="width: 25px">
          <div style="color: red; font-weight: bold; font-size: 20px; margin-left: 2px">Recommended for you</div>
        </div>
        <div style="padding: 20px 0">
          <div class="top-item" @click="router.push('/front/goodsDetail?id=' + item.id)"
               style="cursor: pointer; display: flex; grid-gap: 10px; margin-bottom: 20px"
               v-for="item in data.recommendGoods" :key="item.id">
            <img style="width: 65px; height: 65px" :src="item.img" alt="">
            <div>
              <div class="line2" style="margin-bottom: 5px">{{ item.name }}</div>
              <div style="color: red"><span>￥</span><b>{{ item.price }}</b></div>
            </div>

          </div>
        </div>
      </div>
    </div>
    <!--   First row end -->


    <div class="card" style="padding: 20px;">
      <!--   Second row start -->
      <div
          style="display: flex; align-items: flex-end; padding-bottom: 5px; border-bottom: 1px solid red; margin-bottom: 10px">
        <div style="flex: 1; display: flex; align-items: center">
          <img src="@/assets/imgs/hot.png" alt="" style="width: 25px">
          <div style="color: red; font-weight: bold; font-size: 20px; margin-left: 2px">Hot Selling Products</div>
        </div>
        <div @click="router.push('/front/goods')" style="cursor: pointer; font-size: 13px">View More</div>
      </div>
      <div style="margin-bottom: 40px">
        <el-row :gutter="20">
          <el-col :span="6" v-for="item in data.hotGoods" :key="item.id">
            <div @click="router.push('/front/goodsDetail?id=' + item.id)" class="item"
                 style="width: 100%; border-radius: 5px; height: 350px">
              <img :src="item.img" alt="" style="width: 100%; height: 260px; border-radius: 5px 5px 0 0">
              <div style="padding: 5px">
                <div class="line1" style="font-size: 16px;">{{ item.name }}</div>
                <div>
                  <span style="color: red">￥</span><b style="color: red; font-size: 20px">{{ item.price }}</b>
                  <span style="margin-left: 10px; color: #666">Sales: {{ item.saleCount }}</span>
                </div>
              </div>
            </div>
          </el-col>
        </el-row>
      </div>
      <!--   Second row end -->

      <!--   Third row start -->
      <div
          style="display: flex; align-items: flex-end; padding-bottom: 5px; border-bottom: 1px solid red; margin-bottom: 10px">
        <div style="flex: 1; display: flex; align-items: center">
          <img src="@/assets/imgs/new.png" alt="" style="width: 25px">
          <div style="color: red; font-weight: bold; font-size: 20px; margin-left: 2px">New Arrivals</div>
        </div>
        <div @click="router.push('/front/goods')" style="cursor: pointer; font-size: 13px">View More</div>
      </div>
      <div>
        <el-row :gutter="20">
          <el-col :span="6" v-for="item in data.newGoods" :key="item.id">
            <div @click="router.push('/front/goodsDetail?id=' + item.id)" class="item"
                 style="width: 100%; border-radius: 5px; height: 350px">
              <img :src="item.img" alt="" style="width: 100%; height: 260px; border-radius: 5px 5px 0 0">
              <div style="padding: 5px">
                <div class="line1" style="font-size: 16px">{{ item.name }}</div>
                <div>
                  <span style="color: red">￥</span><b style="color: red; font-size: 20px">{{ item.price }}</b>
                  <span style="margin-left: 10px; color: #666">Sales: {{ item.saleCount }}</span>
                </div>
              </div>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
    <!--   Third row end -->


  </div>
</template>

<script setup>
import {reactive} from "vue";
import request from "@/utils/request";
import router from "@/router";

const data = reactive({
  carouselList: [],
  hotGoods: [],
  newGoods: [],
  recommendGoods: [],
})

request.get('/carousel/selectAll').then(res => {
  data.carouselList = res.data
})

// Hot
request.get('/goods/selectAll', {
  params: {
    status: 'Listed'
  }
}).then(res => {
  data.hotGoods = res.data.sort((v1, v2) => v2.saleCount - v1.saleCount).splice(0, 4)
})

// New
request.get('/goods/selectAll', {
  params: {
    status: 'Listed'
  }
}).then(res => {
  data.newGoods = res.data.splice(0, 4)
})

// Recommend
request.get('/goods/selectAll', {
  params: {
    status: 'Listed'
  }
}).then(res => {
  data.recommendGoods = res.data.filter(v => v.recommend === 'Yes').splice(0, 5)
})
</script>

<style>
.item {
  cursor: pointer;
}

.item:hover {
  border: 1px solid red
}

.top-item:hover {
  color: red;
}
</style>
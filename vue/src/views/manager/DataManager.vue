<template>
  <div>
    <div style="display: flex; grid-gap: 10px">
      <div class="card" style="padding: 20px; flex: 1; display: flex">
        <div style="flex: 1; font-size: 20px;">Total Sales</div>
        <div style="flex: 1; font-size: 20px; font-weight: bold; color: red">￥{{ data.count.total }}</div>
      </div>
      <div class="card" style="padding: 20px; flex: 1; display: flex">
        <div style="flex: 1; font-size: 20px;">Today's Sales</div>
        <div style="flex: 1; font-size: 20px; font-weight: bold; color: #ff8200">￥{{ data.count.today }}</div>
      </div>
      <div class="card" style="padding: 20px; flex: 1; display: flex">
        <div style="flex: 1; font-size: 20px;">Total Products</div>
        <div style="flex: 1; font-size: 20px; font-weight: bold; color: #00b0ef">{{ data.count.goods }}</div>
      </div>
      <div class="card" style="padding: 20px; flex: 1; display: flex">
        <div style="flex: 1; font-size: 20px;">Registered Users</div>
        <div style="flex: 1; font-size: 20px; font-weight: bold; color: #9b3cfd">{{ data.count.user }}</div>
      </div>
    </div>

    <div style="margin-top: 10px; display: flex; grid-gap: 10px">
      <div id="line" style="flex: 1; padding: 20px; height: 500px" class="card"></div>
      <div id="pie" style="flex: 1; padding: 20px; height: 500px" class="card"></div>
    </div>

  </div>
</template>

<script setup>
import { reactive, onMounted } from "vue";
import request from "@/utils/request";
import * as echarts from 'echarts'

const data = reactive({
  count: {}
})

const lineOption = {
  title: {
    text: 'Order Sales Trend for the Past Week',
    left: 'center'
  },
  tooltip: {
    trigger: 'axis'
  },
  legend: {
    left: 'left'
  },
  xAxis: {
    name: 'Date',
    type: 'category',
    data: []
  },
  yAxis: {
    name: 'Sales Amount (Yuan)',
    type: 'value'
  },
  grid: {
    top: '20%',
    bottom:'10%'
  },
  series: [
    {
      data: [],
      type: 'line',
      smooth: true,
      areaStyle: {
        opacity: 0.8, // Shadow opacity
        color: 'rgb(185,190,255)' // Shadow color and opacity
      },
      markPoint: {
        data: [
          { type: 'max', name: 'Max' },
          { type: 'min', name: 'Min' }
        ]
      },
      markLine: {
        data: [{ type: 'average', name: 'Avg' }]
      }
    },
  ]
}

const pieOption = {
  title: {
    text: 'Sales Statistics by Product Category',
    subtext: 'Proportion Chart',
    left: 'center'
  },
  tooltip: {
    trigger: 'item',
    formatter: '{a} <br/>{b} : {c} Yuan ({d}%)'
  },
  legend: {
    top: 0,
    orient: 'vertical',
    left: 'left'
  },
  series: [
    {
      name: 'Sales Amount',
      type: 'pie',
      center: ['50%', '60%'],
      radius: '50%',
      data: [],
      label: {
        show: true,
        formatter(param) {
          return param.name + ' (' + param.percent + '%)';
        }
      },
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      }
    }
  ]
}

request.get('/count').then(res => {
  data.count = res.data
})

// Wait for all page elements to load before setting up the ECharts chart
onMounted(() => {
  // Line chart
  let lineDom = document.getElementById('line')
  let lineChart = echarts.init(lineDom)
  // Request data, initialize chart
  request.get('/selectLine').then(res => {
    lineOption.xAxis.data = res.data.date
    lineOption.series[0].data = res.data.count
    lineChart.setOption(lineOption)
  })

  // Pie chart
  let pieDom = document.getElementById('pie')
  let pieChart = echarts.init(pieDom)
  // Request data, initialize chart
  request.get('/selectPie').then(res => {
    pieOption.series[0].data = res.data
    pieChart.setOption(pieOption)
  })
})
</script>
<template>
  <div>

    <div class="card" style="line-height: 30px; margin-bottom: 10px">
      <div style="margin-bottom: 20px; font-size: 24px">Line 1</div>
      <div>Line 2 <a href="back"> 2 </a>2</div>
      <div>Line 3</div>
      <div>Line 4<b> 4</b></div>
    </div>

    <div class="card">
      Line 5<b style="color: red">5</b> 5<b style="color: red">5</b> 5
    </div>

  </div>
</template>

<script setup>
import { reactive } from "vue";
import request from "@/utils/request";

const data = reactive({
  user: JSON.parse(localStorage.getItem('system-user') || '{}'),
})
</script>
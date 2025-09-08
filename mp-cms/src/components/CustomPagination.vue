<template>
    <el-pagination v-model:current-page="currentPage" :page-size="pageSize" layout="prev, pager, next"
        :total="total" style="justify-content: center;"/>
</template>
  
<script lang="ts" setup>

import { ref, watch, inject } from 'vue'


const currentPage = ref(1)

// const pageSize = ref(10)

watch(currentPage, (newVal) => {
    // console.log('当前页码：' + newVal)
    getByPage(newVal)
})

// watch(pageSize, (newVal) => {
//     console.log(newVal)
// })

const total = inject(/* 注入名 */ 'total')
const pageSize = inject(/* 注入名 */ 'pageSize',10)
const getByPage: Function = inject('getByPage', () => { })
// defineProps(['total'])


</script>
  

<!-- 
名称	说明	类型
size-change	page-size 改变时触发	Function
current-change	current-page 改变时触发	Function
prev-click	用户点击上一页按钮改变当前页时触发	Function
next-click	用户点击下一页按钮改变当前页时触发	Function

WARNING
以上事件不推荐使用（但由于兼容的原因仍然支持，在以后的版本中将会被删除）；如果要监听 current-page 和 page-size 的改变，使用 v-model 双向绑定是个更好的选择。
-->


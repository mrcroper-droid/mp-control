<script setup lang="ts">
import { useRouter } from 'vue-router';
const router = useRouter()

</script>
<template>
  <el-result title="404" sub-title="对不起，您查找的页面不存在，请检查路径再试或点击返回按钮">
    <template #icon>
      <el-empty description="页面不存在" />
    </template>
    <template #extra>
      <el-button type="primary" @click="router.back()">返回</el-button>
    </template>
  </el-result>
</template>
<template>
    <el-dialog v-model="dialogFormVisible" :title="modalTitle" destroy-on-close>
        <slot name="form"></slot>
        <slot name="upload"></slot>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取消</el-button>
                <el-button type="primary" @click="confirm" :loading="loading">
                    确认
                </el-button>
            </span>
        </template>
    </el-dialog>
</template>
  
<script lang="ts" setup>
import { inject } from 'vue'

const dialogFormVisible = inject('modalVisible')
const modalTitle = inject('modalTitle')
const confirm = inject('confirm')
const loading = inject('confirmLoading')


</script>
<style scoped></style>
  
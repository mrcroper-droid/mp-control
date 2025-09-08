<template>
    <el-form :inline="true" :model="searchForm">
        <el-form-item v-for='item,index in searchForm.items' :label="item.label" :key="item.key" :prop="'items.' + index + '.value'">
            <el-select v-model="item.value" :placeholder="item.placeholder">
                <el-option label="Zone one" value="shanghai" />
                <el-option label="Zone two" value="beijing" />
            </el-select>
        </el-form-item>
        <slot name="otherItem"></slot>

        <el-form-item>
            <el-button @click="onSubmit">查找</el-button>
        </el-form-item>
    </el-form>
</template>
  
<script lang="ts" setup>

import { inject, reactive } from 'vue'

const searchForm: any = inject('searchForm')


const onSubmit = inject('submit', () => { })

</script>
  
<style></style>
  
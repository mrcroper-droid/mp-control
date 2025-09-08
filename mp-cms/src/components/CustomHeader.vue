<template>
    <el-page-header :icon="null">
        <template #content>
            <div>
                <span><small>当前位置： {{ pageTitle }} </small></span>

            </div>
        </template>
        <template #extra>
            <div class="flex items-center">
                <slot name="hbts">
                    <el-button type="primary" @click="add" :loading='addLoading'>新增</el-button>
                </slot>
                    
            </div>
        </template>
    </el-page-header>
</template>

<script setup lang="ts">
import { inject } from 'vue';
const add = inject('add')
const pageTitle = inject('pageTitle')

const addLoading = inject('addLoading', false)

</script>
  
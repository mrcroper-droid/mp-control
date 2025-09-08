<template>
    <custom-header>
        <template #hbts>
            <slot name="htbs"></slot>
        </template>
    </custom-header>
    <el-divider />
    <!-- <CustomSearchForm></CustomSearchForm> -->

    <el-table v-show='!tableShow' :data="tableData" style="width: 100%" v-loading="loading" :border="true"
        :cell-style="{ textAlign: 'center' }" :header-cell-style="{ 'text-align': 'center' }">
        <slot name="expand"></slot>

        <el-table-column v-for='column in tableColumn' :key="column.prop" :prop='column.prop' :label="column.label"
            :formatter="column.formatter" />

        <el-table-column label="操作">
            <template #default="scope">
                <el-button v-for="opera in operaButton" :key="opera.name" :size="opera.size || defaultsize"
                    :type="opera.type" @click="opera.func(scope)">
                    {{ opera.name }}</el-button>
                <!-- <el-button size="small" type="danger">Delete</el-button> -->
            </template>
        </el-table-column>
    </el-table>
    <el-divider v-show='!tableShow' />
    <custom-pagination v-show='!tableShow'></custom-pagination>
    <slot name="modal"></slot>
</template>
  
<script lang="ts" setup>

import { inject, ref, watch } from 'vue'
import CustomPagination from './CustomPagination.vue';
// import CustomSearchForm from './CustomSearchForm.vue';
// import CustomModal from './CustomModal.vue';
// import UserForm from './form/UserForm.vue'
import CustomHeader from './CustomHeader.vue';


// columnData中元素的自定义类型类型
interface TableColumn {
    prop: string;
    label: string;
    formatter?: Function,
}

interface operaButton {
    size?: string;
    type?: string;
    name: string;
    func: Function;
}



const defaultsize = 'small'
const tableShow = inject('tableShow', false)

defineProps({
    tableData: Array,
    tableColumn: Array<TableColumn>,// 添加类型定义
    operaButton: Array<operaButton>,
    loading: Boolean,

})

// defineEmits(['delete'])

</script>

<style>
.el-page-header__back,
.el-divider--vertical {
    display: none;
}
</style>
  
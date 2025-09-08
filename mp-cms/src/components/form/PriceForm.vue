<template>
    <el-form :model="form.row" :rules="rules" ref="ruleFormRef">
        <el-row :gutter="16">
            <el-col :span="12">
                <el-form-item label="重量" prop="weight">
                    <el-input v-model="form.row.weight" placeholder="请输入重量"></el-input>
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="价格" prop="price">
                    <el-input v-model="form.row.price" placeholder="请输入价格"></el-input>
                </el-form-item>
            </el-col>
        </el-row>

    </el-form>
</template>
  
<script lang="ts" setup>

import { inject, ref, reactive } from 'vue'
const rules = reactive({
    weight: [{ required: true, message: "请输入重量！" }],
    price: [{ required: true, message: "请输入价格！" }],
})


const form: any = inject('rowData')

const ruleFormRef = ref()

defineExpose({ ruleFormRef, form })
// console.log(form.row)

</script>
  
<style></style>
  
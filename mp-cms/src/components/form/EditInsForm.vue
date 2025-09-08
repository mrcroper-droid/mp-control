<template>
    <el-form :model="form.row" :rules="rules" ref="ruleFormRef">
        <el-row :gutter="16">
            <el-col :span="24">
                <el-form-item label="机构名字" prop="deptName">
                    <el-input v-model="form.row.deptName" placeholder="请输入机构名字"></el-input>
                </el-form-item>
            </el-col>
            <el-col :span="24">
                <el-form-item label="机构管理员" prop="username">
                    <el-select v-model="form.row.username" multiple placeholder="请选择机构管理员">
                        <el-option v-for="item in options.opts" :key="item.userId" :label="item.name + '(' + item.username + ')'"
                            :value="item.userId" />
                    </el-select>
                </el-form-item>
            </el-col>
        </el-row>
        <!-- <el-row :gutter="16">
            <el-col :span="12">
                <el-form-item label="等级" prop="level">
                    <el-input type="number" v-model="form.row.level" placeholder="请输入等级"></el-input>
                </el-form-item>
            </el-col>
        </el-row> -->

    </el-form>
</template>
  
<script lang="ts" setup>

import { inject, ref, reactive } from 'vue'
const rules = reactive({
    deptName: [{ required: true, message: "请输入机构名字！" }],
    username: [{ required: true, message: "请输入机构管理员！" }],
})

const options: any = inject('options')

// console.log(options)

const form: any = inject('rowData', reactive({
    row: {
        deptName: "",
        username: "",
    }
}))

const ruleFormRef = ref()

defineExpose({ ruleFormRef, form })
// console.log(form.row)

</script>
  
<style></style>
  
<template>
    <el-form :model="form.row" ref="ruleFormRef">
        <el-row :gutter="16"  v-if="userRole==1">
            <el-col :span="24">
                <el-form-item label="机构名字" prop="deptName">
                    <el-input v-model="form.row.deptName" placeholder="请输入机构名字"></el-input>
                </el-form-item>
            </el-col>
            <el-col :span="24">
                <el-form-item label="机构管理员" prop="username">
                    <el-input v-model="form.row.username" placeholder="请输入机构管理员"></el-input>
                </el-form-item>
            </el-col>
        </el-row>

        <el-row :gutter="16" v-if="userRole==2||userRole==3">
            <el-col :span="24">
                <el-form-item label="子账号" prop="username">
                    <el-input v-model="form.row.username" placeholder="请输入子账号"></el-input>
                </el-form-item>
            </el-col>
            <el-col :span="24">
                <el-form-item label="名字" prop="name">
                    <el-input v-model="form.row.name" placeholder="请输入名字"></el-input>
                </el-form-item>
            </el-col>
        </el-row>
    </el-form>
</template>
  
<script lang="ts" setup>

import { useStore } from 'vuex'
import { inject, ref, reactive } from 'vue'
// const rules = reactive({
//     deptName: [{ required: true, message: "请输入机构名字！" }],
//     username: [{ required: true, message: "请输入机构管理员！" }],
// })

const store: any = useStore()
const userRole = store.state.user.authority

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
  
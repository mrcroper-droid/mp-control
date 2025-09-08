<template>
    <el-form :model="formValue.form" :rules="rules" ref="ruleFormRef">

        <el-form-item label="手机" prop="username">
            <el-input v-model="formValue.form.username" placeholder="请输入手机号" minlength="11" maxlength="11"
                show-word-limit></el-input>
        </el-form-item>

        <el-form-item label="昵称" prop="name">
            <el-input v-model="formValue.form.name" placeholder="请输入昵称" maxlength="5" show-word-limit></el-input>
        </el-form-item>




        <el-row :gutter="16">
            <el-col :span="12">
                <el-form-item label="密码" prop="password">
                    <el-input v-model="formValue.form.password" placeholder="请输入密码" show-password></el-input>
                </el-form-item>
            </el-col>
            <el-col :span="12">
                <el-form-item label="确认密码" prop="password2">
                    <el-input v-model="formValue.form.password2" placeholder="请输入密码" show-password></el-input>
                </el-form-item>
            </el-col>
        </el-row>
        <el-form-item>
            <el-button type="primary" @click="submit" :loading="rloading">注册</el-button>
        </el-form-item>

        <el-text size="large" type="danger"><strong>注册时请输入正确的手机号，避免联系不到，影响打款进度！！！</strong></el-text>
        <p></p>
    </el-form>
</template>
  
<script lang="ts" setup>

defineProps({
    submit: Function,
    rloading: Boolean
})


import { ref, reactive } from 'vue'
const ruleFormRef = ref()

const rules = reactive({
    name: [{ required: true, message: "请输入昵称！" }, { max: 5, message: "昵称最长5位！" }],
    username: [{ required: true, message: "请输入手机号！" }, { min: 11, max: 11, message: "手机号应为11位！" }],
    password: [{ required: true, message: "请输入密码！" }, { min: 4, max: 8, message: "密码最短4位，最长8位！" }],
    password2: [{ required: true, message: "请输入确认密码！" }, { min: 4, max: 8, message: "密码最短4位，最长8位！" }],
})


const formValue = reactive({
    form: {
        name: "",
        username: "",
        password: '',
        password2: '',
    }
})



defineExpose({ ruleFormRef, formValue })
// console.log(form.row)

</script>
  
<style></style>
  
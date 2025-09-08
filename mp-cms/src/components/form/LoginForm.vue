<template>
    <el-form :model="formValue.form" :rules="rules" ref="ruleFormRef">

        <el-form-item label="手机" prop="username">
            <el-input v-model="formValue.form.username" placeholder="请输入手机号" minlength="11" maxlength="11"
                show-word-limit></el-input>
        </el-form-item>

        <el-form-item label="密码" prop="password">
            <el-input v-model="formValue.form.password" placeholder="请输入密码" show-password></el-input>
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="submit" :loading="lloading">登录</el-button>
        </el-form-item>
    </el-form>
</template>
  
<script lang="ts" setup>

defineProps({
    submit: Function,
    lloading: Boolean
})


import { ref, reactive } from 'vue'
const ruleFormRef = ref()

const rules = reactive({
    username: [{ required: true, message: "请输入手机号！" }, { min: 11, max: 11, message: "手机号应为11位！" }],
    password: [{ required: true, message: "请输入密码！" },],
})


const formValue = reactive({
    form: {
        username: "",
        password: '',
    }
})



defineExpose({ ruleFormRef, formValue })
// console.log(form.row)

</script>
  
<style></style>
  
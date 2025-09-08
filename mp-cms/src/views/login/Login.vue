<script setup lang="ts">
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
import APIS from '@/axios/api';
import URLS from '@/axios/url'
import openMessage from '@/utils/message'


const router = useRouter();
const store = useStore();
// do not use same name with ref
const form = reactive({
    username: '',
    password: '',

})

const rules = reactive({
    username: [{ required: true, message: "请输入账号！" }],
    password: [{ required: true, message: "请输入密码！" }],
})

const ruleFormRef = ref()

const loading = ref(false)

const onSubmit = async (ruleFormRef: any) => {
    if (!ruleFormRef) return
    await ruleFormRef.validate((valid: any, fields: any) => {
        if (valid) {
            loading.value = true
            APIS.post(URLS.LOGIN, form).then(res => {
                loading.value = false
                store.commit('LOGINSUCCESS', {
                    username: form.username,
                    token: res.obj.password,
                    authority: res.obj.authorities.find((x: any) => x.authority).authority,
                })
                // console.log(store.state.user.username)
                router.push('/app/index');
            }).catch(err => {
                loading.value = false;
                openMessage(err.msg)
            })

        } else {
            console.log('error submit!', fields)
        }
    })
}
const register = async (ruleFormRef: any) => {
    if (!ruleFormRef) return
    await ruleFormRef.validate((valid: any, fields: any) => {
        if (valid) {
            loading.value = true
            APIS.post(URLS.REGISTER, form).then(res => {
                loading.value = false
                onSubmit(ruleFormRef)
            }).catch(err => {
                loading.value = false;
                openMessage(err.msg)
            })

        } else {
            console.log('error submit!', fields)
        }
    })
}
</script>
<template>
    <div class="login">
        <el-card class="login-card">
            <template #header>
                <span>微信公众号菜单配置 </span>
                <span>登录</span>
            </template>
            <el-form :model="form" :rules=rules status-icon ref="ruleFormRef">
                <el-form-item label="账号" prop="username">
                    <el-input v-model="form.username" placeholder="请输入账号" />
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="form.password" placeholder="请输入密码" show-password type="password" />
                </el-form-item>

                <el-form-item>
                 <!--<el-button :loading="loading" @click="register(ruleFormRef)">注册</el-button>-->
                 <el-button type="primary" :loading="loading" @click="onSubmit(ruleFormRef)">登录</el-button>
                </el-form-item>
            </el-form>
        </el-card>
        <div style="text-align: center;">
            <span>
                备案号：
                <a href="https://beian.miit.gov.cn" target="_blank" rel="nofollow">
                    滇ICP备2023000829号-1
                </a>
                ，made with  ♥昆明微程软体。
            </span>
        </div>
    </div>


</template>

<style>
.el-form-item__content {
    justify-content: center;
}

.login {
    height: 100%;
    width: 100%;
    display: flex;
    flex-direction: column;
}



.login-card {
    width: 400px;
    margin: auto;
}
</style>
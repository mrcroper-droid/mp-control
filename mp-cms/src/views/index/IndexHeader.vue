<template>
    <el-menu class="el-menu-demo" mode="horizontal" :ellipsis="false" @select="handleSelect">
        <el-menu-item index="0">
            <el-avatar src="https://www.roper.com.cn/u=1811605156,643112314&fm=253&gp=0.jpg"></el-avatar> <span>居居任务网</span>
            <!-- <el-tag>beta</el-tag> -->
        </el-menu-item>
        <!-- <el-menu-item index="3"> -->
        <div style="margin: auto;">您好，欢迎来到居居任务网！请先阅读<a href="https://www.roper.com.cn/tutorial/taskDetail.html"
                target="_blank">内容审核说明</a>（点击绿色链接直达说明页～）</div>
        <!-- </el-menu-item> -->
        <div class="flex-grow" />
        <el-menu-item index="1">
            <span>主题</span>
            <CustomColorPickerVue></CustomColorPickerVue>
        </el-menu-item>
        <el-sub-menu index="2" v-if="username">
            <template #title><el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png">
                </el-avatar> {{ name }}</template>
            <el-menu-item index="2-1"><el-icon>
                    <Edit />
                </el-icon>个人信息</el-menu-item>
            <el-menu-item index="logout"><el-icon>
                    <Setting />
                </el-icon>退出登录</el-menu-item>
            <!-- <el-sub-menu index="2-4">
                <template #title>item four</template>
                <el-menu-item index="2-4-1">item one</el-menu-item>
                <el-menu-item index="2-4-2">item two</el-menu-item>
                <el-menu-item index="2-4-3">item three</el-menu-item>
            </el-sub-menu> -->
        </el-sub-menu>
        <el-menu-item index="login" v-else>
            <template #title> <el-avatar>登录</el-avatar> 登录 </template>
        </el-menu-item>
    </el-menu>
</template>
<script setup lang="ts">
import CustomColorPickerVue from '@/components/CustomColorPicker.vue';
import { useStore } from 'vuex'
const store = useStore()
defineProps({
    username: String,
    name: String
})

const emit = defineEmits<{
    logout: [],
    color: [color: string],
    login: []
}>()
// const props = defineProps<{
//     username: String
// }>()

// const activeIndex = ref('1')
const handleSelect = (key: string, keyPath: string[]) => {
    // console.log(key, keyPath)
    if (key === 'logout') {
        emit('logout')
    } else if (key === 'login') {
        emit('login')
    }
}

</script>

<style></style>
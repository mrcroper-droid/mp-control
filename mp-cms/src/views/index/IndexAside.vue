<template>
    <div class="userInfo">
        <div v-if="store.state.user.username">
            <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" size="large"></el-avatar>
            <p>{{ store.state.user.name }}</p>
        </div>

        <div v-else>
            <el-avatar size="large">登录</el-avatar>
            <p>
                <el-button type="primary" @click="emit('login')">登录</el-button>
            </p>
        </div>
        <!-- <el-button type="primary" text>实名认证</el-button> -->
    </div>
    <el-divider style="margin: 5px;" />
    <el-menu :ellipsis="false" router :default-active="router.currentRoute.value.fullPath">
        <el-menu-item index="/app/index">
            <!-- <el-icon>
                <PieChart />
            </el-icon> -->
            <span>首页</span></el-menu-item>
        <el-menu-item index="/app/my">
            <span>我的任务</span>
        </el-menu-item>
    </el-menu>
</template>
<script setup lang="ts">
import { useStore } from 'vuex';
import router from '@/router';
defineProps({
    username: String,
})

const emit = defineEmits<{
    logout: [],
    login: [],
}>()

const store = useStore()



</script>

<style>
.userInfo {
    width: 200px;
    padding: 20px;
    text-align: center;
}
</style>
<template>
    <el-color-picker v-model="color" :show-alpha="true" :predefine="predefineColors"
        @change="changeColor" size="small" />
</template>
  
<script lang="ts" setup>
import { ref } from 'vue'
import setTheme from '@/utils/setTheme';

import { useStore } from 'vuex'
const store = useStore()


const color = ref(store.state.setting.themeColor)
const predefineColors = ref([
    '#409eff',
    '#ff4500',
    '#ff8c00',
    '#ffd700',
    // '#90ee90',
    '#00ced1',
    '#1e90ff',
    '#c71585',

    // 'rgba(255, 69, 0, 0.68)',
    // 'rgb(255, 120, 0)',
    // 'hsv(51, 100, 98)',
    // 'hsva(120, 40, 94, 0.5)',
    // 'hsl(181, 100%, 37%)',
    // 'hsla(209, 100%, 56%, 0.73)',
    // '#c7158577',
])

setTheme(store.state.setting.themeColor)

const changeColor = () => {

    store.commit('CHANGECOLOR', { themeColor: color.value ? color.value : '#409eff' })
    setTheme(color.value)
}

let button = document.getElementsByName('input')
console.log(button)

// const change = () => {
//     console.log(color.value)
//     // document.documentElement.style.setProperty('--el-color-primary', color.value)
// }


</script>
<style>
.el-icon.el-color-picker__icon.is-icon-arrow-down,
.el-icon.el-color-picker__empty.is-icon-close,
.el-button.el-button--small.is-text.el-color-dropdown__link-btn
{
    display: none;
}

</style>
  
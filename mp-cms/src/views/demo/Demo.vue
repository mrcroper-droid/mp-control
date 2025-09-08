<template>
    <div class="common-layout">
        <CustomUploadBase64 title="2" :handle-change="handleChange" :handle-remove="handleRemove"></CustomUploadBase64>
    </div>

</template>

<script lang="ts" setup>
import { ref, reactive } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router';
import APIS from '@/axios/api';
import URLS from '@/axios/url';
import openMessage from '@/utils/message';
import utils from '@/utils/utils'
import type { UploadFile, UploadFiles, UploadProps, UploadInstance, UploadRawFile } from 'element-plus'
import { genFileId } from 'element-plus'
import type { appMenu, appForm } from '@/utils/interface'
import CustomUploadBase64 from '@/components/CustomUploadBase64.vue';



const handleChange = (uploadFile: UploadFile, uploadFiles: UploadFiles) => {

    // utils.imageToBase64(uploadFile.raw).then((res: any) => {
    //     console.log(res.slice(res.indexOf('base64,') + 7))
    // })

    // console.log(uploadFile)
}

const handleRemove = (uploadFile: UploadFile, uploadFiles: UploadFiles) => {
    // console.log(uploadFile)
}

</script>

<style>
.form {
    padding: 20px;
    margin: auto;
}
</style>
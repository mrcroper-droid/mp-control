<script setup lang="ts">
import { ref,useAttrs } from 'vue'
import type { UploadFile, UploadFiles, UploadProps, UploadInstance, UploadRawFile } from 'element-plus'
import { genFileId } from 'element-plus'

const attrs = useAttrs()


const upload = ref<UploadInstance>()

const handleExceed: UploadProps['onExceed'] = (files) => {
  upload.value!.clearFiles()
  const file = files[0] as UploadRawFile
  file.uid = genFileId()
  upload.value!.handleStart(file)
}


defineProps({
  handleChange: Function,
  handleRemove: Function,
  title: String
})

// console.log(attrs)


</script>
<template>
  <el-upload v-bind="attrs" ref="upload" :on-exceed="handleExceed" :on-change="handleChange" accept="image/*" :on-remove="handleRemove"
    :auto-upload="false" :limit="1">
    <el-button type="primary">点击上传{{ title }}图片</el-button>
  </el-upload>
</template>
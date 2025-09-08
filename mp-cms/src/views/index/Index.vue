<template>
    <div class="common-layout">
        <div style="float: right;" @click="logout">{{ store.state.user.username }} 退出登录</div>

        <el-form label-width="auto" label-position="top" style="max-width: 800px" :model="form" :rules=rules status-icon
            ref="ruleFormRef" class="form">
            <div style="margin: 10px 0;">
                <a @click="visible = true">点击查看第一步操作</a>
            </div>

            <div style="margin: 10px 0;">
                <a target="_blank" href="https://jingyan.baidu.com/article/f3e34a12aadac0b4eb65358a.html">点击查看第二步操作</a>
            </div>


            <el-form-item v-show="selectShow" label="已配置的公众号" prop="">
                <el-select clearable v-model="value" placeholder="请选择已配置的公众号" size="large" style="width: 100%"
                    @change="selectAppId">
                    <el-option v-for="item in options" :key="item.appId" :label="item.name + '(' + item.appId + ')'"
                        :value="item.appId" />
                </el-select>
            </el-form-item>




            <el-form-item label="公众号名字" prop="name">
                <el-input v-model="form.name" placeholder="请输入公众号名字" />
            </el-form-item>
            <el-form-item label="appId" prop="appId">
                <el-input v-model="form.appId" placeholder="请输入appid" />
            </el-form-item>
            <el-form-item label="app原始id" prop="originAppId">
                <el-input v-model="form.originAppId" placeholder="请输入app原始id" />
            </el-form-item>
            <el-form-item label="appSecret" prop="appSecret">
                <el-input v-model="form.appSecret" placeholder="请输入appsecret" />
            </el-form-item>
            <el-form-item label="菜单配置">
                <el-button type="primary" plain @click='addTextMenu'>新增文字菜单</el-button>
                <el-button type="" plain @click='addWebMenu'>新增网页菜单</el-button>
                <el-button type="info" plain @click='addWxMenu'>新增小程序菜单</el-button>

            </el-form-item>
            <div v-for="(menu, index) in form.appMenu" :key="menu.id">

                <el-form-item :label="utils.replyTypeToText(menu.menuType) + '菜单名字' + index"
                    :prop="'appMenu.' + index + '.menuName'" :rules="{
                        required: true,
                        message: '请输入菜单名字!',
                        trigger: 'blur',
                    }">
                    <el-input placeholder="请输入菜单名字" v-model="menu.menuName" />

                </el-form-item>
                <div v-if="menu.menuType == 'click'" style="margin-bottom: 18px;">
                    <el-form-item :label="'回复内容' + index" :prop="'appMenu.' + index + '.content'" :rules="{
                        required: true,
                        message: '请输入回复内容!',
                        trigger: 'blur',
                    }">
                        <el-input :id='"content" + index' v-model="menu.content" type="textarea"
                            :autosize='{ minRows: 2 }' placeholder="请输入回复内容" />
                    </el-form-item>

                    <el-button @click="insertTem(menu, index, 1)">插入模版1</el-button>
                    <el-button @click="insertTem(menu, index, 2)">插入模版2</el-button>
                </div>


                <el-form-item label='url' :prop="'appMenu.' + index + '.url'" v-if="menu.menuType == 'view'" :rules="{
                    required: true,
                    message: '请输入url!',
                    trigger: 'blur',
                }">
                    <el-input v-model="menu.url" type="textarea" autosize placeholder="请输入url" />
                </el-form-item>

                <div v-if="menu.menuType == 'miniprogram'">
                    <el-form-item label='小程序路径' :prop="'appMenu.' + index + '.pagePath'" :rules="{
                        required: true,
                        message: '请输入小程序路径!',
                        trigger: 'blur',
                    }">
                        <el-input v-model="menu.pagePath" type="textarea" autosize placeholder="请输入小程序路径" />
                    </el-form-item>
                    <el-form-item label='小程序appId' :prop="'appMenu.' + index + '.appIdDes'" :rules="{
                        required: true,
                        message: '请输入小程序appId!',
                        trigger: 'blur',
                    }">
                        <el-input v-model="menu.appIdDes" type="textarea" autosize placeholder="请输入小程序appId" />
                    </el-form-item>
                </div>
                <el-button type="danger" @click.prevent="deleteMenu(menu)">
                    {{ '删除菜单' + index }}</el-button>
                <hr style="margin: 20px 0;" />


            </div>


            <el-form-item label="关注回复" prop="followReply">

                <el-input id="gz" v-model="form.followReply" type="textarea" :autosize="{ minRows: 4 }"
                    placeholder="请输入关注回复"></el-input>
            </el-form-item>

            <el-button @click="insertTem('', 'gz', 1)">插入模版1</el-button>
            <el-button @click="insertTem('', 'gz', 2)">插入模版2</el-button>
            <!-- <el-button type="primary" plain @click='gzReplyType = "text"'>配置文字回复</el-button>
                <el-button type="" plain @click='gzReplyType = "image"'>配置图片回复</el-button>
                <el-button type="info" plain @click='gzReplyType = "miniprogrampage"'>配置小程序回复</el-button> -->



            <!-- <div v-if="gzReplyType == 'text'">
                <el-form-item>
                    <el-input id="gz" v-model="form.followReply" type="textarea" :autosize="{ minRows: 4 }"
                        placeholder="请输入关注回复"></el-input>

                </el-form-item>
                <el-button @click="insertTem('', 'gz', 1)">插入模版1</el-button>
                <el-button @click="insertTem('', 'gz', 2)">插入模版2</el-button>
            </div>

            <div v-if="gzReplyType == 'image'">
                <el-form-item>
                    <el-upload>555</el-upload>

                </el-form-item>
     
            </div>

            <div v-if="gzReplyType == 'miniprogrampage'">
                <el-form-item>
                    <el-input id="gz" v-model="form.followReply" type="textarea" :autosize="{ minRows: 4 }"
                        placeholder="请输入关注回复"></el-input>

                </el-form-item>
                <el-form-item>
                    <el-input id="gz" v-model="form.followReply" type="textarea" :autosize="{ minRows: 4 }"
                        placeholder="请输入关注回复"></el-input>

                </el-form-item>
                <el-form-item>
                    <el-input id="gz" v-model="form.followReply" type="textarea" :autosize="{ minRows: 4 }"
                        placeholder="请输入关注回复"></el-input>

                </el-form-item>
                <el-form-item>
                    <el-upload>555</el-upload>

                </el-form-item>
     
            </div> -->



            <div style="margin-top: 10px;">

                <div style="display: flex;">
                    <el-switch v-model="value1" active-text="开启二次回复" inactive-text="关闭二次回复" />
                    <el-form-item v-if="value1" prop="secondReplyTime" :rules="{
                        required: true,
                        message: '请输入二次回复时间!',
                        trigger: 'blur',
                    }">

                        <el-input-number style="margin-left: 10px;width: 100%;" v-model="form.secondReplyTime" :min="1"
                            :max="60">
                            <template #suffix>
                                <span>秒钟</span>
                            </template>
                        </el-input-number>
                    </el-form-item>


                </div>

                <div v-if="value1">
                    <el-form-item label="二次回复" required>
                        <el-button type="primary" plain @click='form.appReply.msgType = "text"'>配置文字回复</el-button>
                        <el-button type="" plain @click='form.appReply.msgType = "image"'>配置图片回复</el-button>
                        <el-button type="info" plain
                            @click='form.appReply.msgType = "miniprogrampage"'>配置小程序回复</el-button>

                    </el-form-item>

                    <div v-if="form.appReply && form.appReply.msgType == 'text'">
                        <el-form-item :prop="'appReply.content'" :rules="{
                            required: true,
                            message: '请输入二次回复内容!',
                            trigger: 'blur',
                        }">
                            <el-input id="two" v-model="form.appReply.content" type="textarea"
                                :autosize="{ minRows: 4 }" placeholder="请输入二次回复内容"></el-input>

                        </el-form-item>
                        <el-button @click="insertTem('', 'two', 1)">插入模版1</el-button>
                        <el-button @click="insertTem('', 'two', 2)">插入模版2</el-button>
                    </div>

                    <div v-if="form.appReply && form.appReply.msgType == 'image'">
                        <el-upload ref="upload" :on-exceed="handleExceed" :on-change="handleChange" accept="image/*"
                            :on-remove="() => form.appReply.media = ''" :auto-upload="false" :limit="1">
                            <el-button type="primary">点击上传media图片</el-button>
                        </el-upload>
                        <div v-if="form.appReply.mediaId">
                            <el-form-item :prop="'appReply.mediaId'" label="已上传图片mediaId" :rules="{
                                required: true,
                                message: '请上传图片!',
                                trigger: 'blur',
                            }">

                                <el-input v-model="form.appReply.mediaId" type="textarea" disabled></el-input>
                            </el-form-item>
                        </div>
                        <div v-else>


                            <el-form-item :prop="'appReply.media'" label="media图片" :rules="{
                                required: true,
                                message: '请上传图片!',
                                trigger: 'blur',
                            }">

                                <el-input v-model="form.appReply.media" type="textarea" disabled></el-input>
                            </el-form-item>
                        </div>



                    </div>

                    <div v-if="form.appReply && form.appReply.msgType == 'miniprogrampage'">
                        <el-form-item :prop="'appReply.title'" label="卡片名字" :rules="{
                            required: true,
                            message: '请输入卡片名字!',
                            trigger: 'blur',
                        }">

                            <el-input v-model="form.appReply.title" placeholder="请输入卡片名字"></el-input>

                        </el-form-item>
                        <el-form-item :prop="'appReply.pagePath'" label="小程序路径" :rules="{
                            required: true,
                            message: '请输入小程序路径!',
                            trigger: 'blur',
                        }">
                            <el-input v-model="form.appReply.pagePath" placeholder="请输入小程序路径"></el-input>


                        </el-form-item>
                        <el-form-item :prop="'appReply.appIdDes'" label="小程序appId" :rules="{
                            required: true,
                            message: '请输入小程序appId!',
                            trigger: 'blur',
                        }">
                            <el-input v-model="form.appReply.appIdDes" placeholder="请输入小程序appId"></el-input>


                        </el-form-item>

                        <el-upload ref="upload1" :on-exceed="handleExceed1"
                            :on-change="handleChange1" accept="image/*" :on-remove="() => form.appReply.thumbMedia = ''"
                            :auto-upload="false" :limit="1">
                            <el-button type="primary">点击上传thumbMedia图片</el-button>
                        </el-upload>

                        <div v-if="form.appReply.thumbMediaId">
                            <el-form-item :prop="'appReply.thumbMediaId'" label="已上传图片thumbMediaId" :rules="{
                                required: true,
                                message: '请上传图片!',
                                trigger: 'blur',
                            }">

                                <el-input v-model="form.appReply.thumbMediaId" type="textarea" disabled></el-input>
                            </el-form-item>
                        </div>
                        <div v-else>
                            <el-form-item :prop="'appReply.thumbMedia'" label="thumbMedia图片" :rules="{
                            required: true,
                            message: '请上传thumbMedia图片!',
                            trigger: 'blur',
                        }">

                            <el-input v-model="form.appReply.thumbMedia" type="textarea" disabled></el-input>
                        </el-form-item>
                        </div>

                       


                    </div>

                    <!-- <el-button @click="insertTem('', 'gz', 1)">插入模版1</el-button>
<el-button @click="insertTem('', 'gz', 2)">插入模版2</el-button> -->

                </div>

            </div>



            <!-- <el-form-item label="二次回复" prop="secondReplyTime">
                <el-input id="gz" v-model="form.followReply" type="textarea" :autosize="{ minRows: 6 }"
                    placeholder="请输入关注回复"></el-input>


            </el-form-item> -->

            <div style="margin: 20px 0;display: flex;">
                <div style="margin: auto;">
                    <el-button :loading="loading" type="success" @click="onSubmit(ruleFormRef)">确认配置</el-button>

                    <el-button :loading="delLoading" type="danger" plain @click="delALL">删除appID</el-button>
                </div>

            </div>



        </el-form>
    </div>
    <el-dialog v-model="dialogVisible1" title="模版一">
        <div style="word-break: break-all;">
            &lta data-miniprogram-appid=\"<el-text type="primary">wxde8ac0a21135c07d</el-text>\"
            data-miniprogram-path=\"<el-text
                type="success">/index/pages/h5/h5?weburl=https%3A%2F%2Fclick.meituan.com%2Ft%3Ft%3D1%26c%3D2%26p%3D-uYnQr9z6Kqr</el-text>\"
            href= "http://www.baidu.com\" data-miniprogram-type=\"text\"&gt<el-text
                type="warning">【必领】美团新客大额紅包</el-text>&lt/a&gt
        </div>

        <div>
            <el-text type="primary">区域1</el-text>
            <el-input v-model="tem11"></el-input>
        </div>

        <div>
            <el-text type="success">区域2</el-text>
            <el-input v-model="tem12"></el-input>
        </div>

        <div>
            <el-text type="warning">区域3</el-text>
            <el-input v-model="tem13"></el-input>
        </div>

        <div style="margin-top: 20px 0;">
            <el-text>预览</el-text>
            <el-input type="textarea" v-model="resultTem1" disabled autosize></el-input>
        </div>

        <el-button type="info" plain @click="previewTem(1)">预览</el-button>
        <el-button type="danger" plain @click="saveTem(1)">插入</el-button>

    </el-dialog>
    <el-dialog v-model="dialogVisible2" title="模版二">
        <div style="word-break: break-all;">
            &lta href=\"<el-text type="primary">http://www.baidu.com</el-text>\"&gt<el-text
                type="success">【必领】美团拼手气紅包</el-text>&lt/a&gt
        </div>

        <div>
            <el-text type="primary">区域1</el-text>
            <el-input v-model="tem21"></el-input>
        </div>

        <div>
            <el-text type="success">区域2</el-text>
            <el-input v-model="tem22"></el-input>
        </div>


        <div style="margin: 20px 0;">
            <el-text>预览</el-text>
            <el-input type="textarea" v-model="resultTem2" disabled autosize></el-input>
        </div>

        <el-button type="info" plain @click="previewTem(2)">预览</el-button>
        <el-button type="danger" plain @click="saveTem(2)">插入</el-button>
    </el-dialog>

    <el-dialog v-model="visible" title="第一步">
        <img src="../../assets/tip1.jpg" style="width: 100%;" />
        <!-- <el-image src="../../assets/tip1.jpg"></el-image> -->
    </el-dialog>
</template>

<script lang="ts" setup>
import { ref, reactive, Text } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router';
import APIS from '@/axios/api';
import URLS from '@/axios/url';
import openMessage from '@/utils/message';
import utils from '@/utils/utils'
import type { UploadFile, UploadFiles, UploadProps, UploadInstance, UploadRawFile } from 'element-plus'
import { genFileId } from 'element-plus'
import type { appMenu, appForm } from '@/utils/interface'



const store = useStore()
const router = useRouter();
const dialogVisible1 = ref(false)
const dialogVisible2 = ref(false)
const visible = ref(false)

const selectShow = ref(false)

const loading = ref(false)
const delLoading = ref(false)

const value = ref('')
const value1 = ref(false)

const options = ref()

const clickNum = ref(0)

const form = reactive<appForm>({
    name: '',
    appId: '',
    originAppId: "",
    appSecret: '',
    appMenu: [
        {
            id: 0,
            pId: '',
            menuName: '',
            content: '',
            menuType: 'click',
            url: '',
            pagePath: '',
            appIdDes: ''
        },
    ],
    followReply: '',
    secondReplyTime: 30,
    appReply: {
        id: 0,
        appId: '',
        msgType: 'text',
        content: '',
        title: '',
        appIdDes: '',
        pagePath: '',
        thumbMedia: '',
        media: '',
        thumbMediaId: '',
        mediaId: '',
    }

})

const currentIndex = ref()
// const currentTextArea = ref()
const currentMenuId = ref()
const currentStart = ref()
const currentEnd = ref()

const currentAppId = ref()


const tem11 = ref("wxde8ac0a21135c07d")
const tem12 = ref("/index/pages/h5/h5?weburl=https%3A%2F%2Fclick.meituan.com%2Ft%3Ft%3D1%26c%3D2%26p%3D-uYnQr9z6Kqr")
const tem13 = ref("【必领】美团新客大额紅包")

const tem21 = ref("http://www.baidu.com")
const tem22 = ref("【必领】美团拼手气紅包")

const resultTem1 = ref("")
const resultTem2 = ref("")

const rules = reactive({
    name: [{ required: true, message: "请输入公众号名字!" }],
    appId: [{ required: true, message: "请输入appId!" }],
    originAppId: [{ required: true, message: "请输入app原始Id!" }],
    appSecret: [{ required: true, message: "请输入appSecret!" }],
})

const ruleFormRef = ref()

const twoReplyImageList = reactive([])

const upload = ref<UploadInstance>()
const upload1 = ref<UploadInstance>()

const addTextMenu = () => {
    clickNum.value++
    form.appMenu.push({
        id: clickNum.value,
        pId: '',
        menuName: '',
        content: '',
        menuType: 'click',
        url: '',
        pagePath: '',
        appIdDes: ''
    })
}

const addWebMenu = () => {
    clickNum.value++
    form.appMenu.push({
        id: clickNum.value,
        pId: '',
        menuName: '',
        content: '',
        menuType: 'view',
        url: '',
        appIdDes: '',
        pagePath: ''
    })
}
const addWxMenu = () => {
    clickNum.value++
    form.appMenu.push({
        id: clickNum.value,
        pId: '',
        menuName: '',
        content: '',
        menuType: 'miniprogram',
        url: '',
        pagePath: '',
        appIdDes: ''
    })
}

const deleteMenu = (menu: any) => {
    if (form.appMenu.length <= 1) {
        openMessage("必须保留一个菜单")
        return
    }
    form.appMenu = form.appMenu.filter(x => x.id !== menu.id)

}

const insertTem = (menu: any, index: any, id: any) => {
    console.log(index)
    currentIndex.value = index
    if (index == 'gz') {
        let textarea = document.getElementById('gz')
        currentStart.value = (textarea as any)!.selectionStart
        currentEnd.value = (textarea as any)!.selectionEnd
    } else if (index == 'two') {
        let textarea = document.getElementById('two')
        currentStart.value = (textarea as any)!.selectionStart
        currentEnd.value = (textarea as any)!.selectionEnd
    }
    else {
        currentMenuId.value = menu.id
        let textarea = document.getElementById('content' + index)
        currentStart.value = (textarea as any)!.selectionStart
        currentEnd.value = (textarea as any)!.selectionEnd
    }

    if (id == 1) {
        dialogVisible1.value = true
    }
    else if (id == 2) {
        dialogVisible2.value = true
    }


}

const previewTem = (id: number) => {
    if (id == 1) {
        resultTem1.value = '<a data-miniprogram-appid=\"' + tem11.value + '\" data-miniprogram-path=\"' + tem12.value + '\" href= "http://www.baidu.com\" data-miniprogram-type=\"text\">' + tem13.value + '</a>'
    }
    else if (id == 2) {
        resultTem2.value = '<a href=\"' + tem21.value + '\">' + tem22.value + '</a>'
    }

}

const saveTem = (id: number) => {
    previewTem(id)
    let result = ''
    if (id == 1) {
        result = resultTem1.value
    } else if (id == 2) {
        result = resultTem2.value
    }
    if (currentIndex.value == 'gz') {
        form.followReply = utils.insertAtPosition(form.followReply, result, currentStart.value)

    } else {
        let oldval = form.appMenu.find(x => x.id == currentMenuId.value)?.content
        let idx = form.appMenu.findIndex(x => x.id == currentMenuId.value)
        let newval = utils.insertAtPosition(oldval, result, currentStart.value)
        form.appMenu[idx].content = newval
    }
    dialogVisible1.value = false
    dialogVisible2.value = false
}

const onSubmit = async (formEl: any) => {
    if (!formEl) return
    await formEl.validate((valid: any, fields: any) => {
        if (valid) {
            if (!value1.value) {
                form.secondReplyTime = null
                form.appReply  = {}
            }
            form.appMenu = form.appMenu.map((x: any) => {
                // delete x.id
                return { appId: form.appId, ...x }
            })
            if (form.appReply.msgType == 'text') {
                form.appReply.media = ""
                form.appReply.title = ""
                form.appReply.appIdDes = ""
                form.appReply.pagePath = ""
                form.appReply.thumbMedia = ""
                form.appReply.mediaId = ""
                form.appReply.thumbMediaId = ""
            } else if (form.appReply.msgType == 'image') {
                form.appReply.content = ""
                form.appReply.title = ""
                form.appReply.appIdDes = ""
                form.appReply.pagePath = ""
                form.appReply.thumbMedia = ""
                form.appReply.thumbMediaId = ""
            } else if (form.appReply.msgType == 'miniprogrampage') {
                form.appReply.content = ""
                form.appReply.media = ""
                form.appReply.mediaId = ""
            }
            console.log('submit!', form)
            loading.value = true
            addAll(form)

        } else {
            openMessage("请先完善信息")
            console.log('error submit!', fields)
        }
    })
}

const logout = () => {
    store.commit('LOGOUT')
    router.push('/app/login')

}

const handleExceed: UploadProps['onExceed'] = (files) => {
    upload.value!.clearFiles()
    const file = files[0] as UploadRawFile
    file.uid = genFileId()
    upload.value!.handleStart(file)
}

const handleChange = (uploadFile: UploadFile, uploadFiles: UploadFiles) => {
    form.appReply.mediaId = ''
    utils.imageToBase64(uploadFile.raw).then((res: any) => {
        form.appReply.media = res.slice(res.indexOf('base64,') + 7)
    })

    // console.log(uploadFile)
}

const handleExceed1: UploadProps['onExceed'] = (files) => {
    upload1.value!.clearFiles()
    const file = files[0] as UploadRawFile
    file.uid = genFileId()
    upload1.value!.handleStart(file)
}

const handleChange1 = (uploadFile: UploadFile, uploadFiles: UploadFiles) => {
    form.appReply.thumbMediaId = ''
    utils.imageToBase64(uploadFile.raw).then((res: any) => {
        form.appReply.thumbMedia = res.slice(res.indexOf('base64,') + 7)
    })

    // console.log(uploadFile)
}




const selectAppId = (value: any) => {
    console.log(value, options.value)

    if (!value) {
        ruleFormRef.value.resetFields()
        form.appMenu = [{
            id: 0,
            pId: '',
            menuName: '',
            content: '',
            menuType: 'click',
            url: '',
            pagePath: '',
            appIdDes: ''
        }]
        value1.value = false
        return
    }
    currentAppId.value = value
    let val = options.value.find((x: any) => x.appId === value)
    form.name = val.name
    form.appId = val.appId
    form.originAppId = val.originAppId
    form.appSecret = val.appSecret
    form.followReply = val.followReply
    form.secondReplyTime = val.secondReplyTime
    form.appMenu = val.appMenu

    if (form.secondReplyTime) {
        value1.value = true
        form.secondReplyTime = val.secondReplyTime
        form.appReply = val.appReply
    }

    // queryAppMenu(value)
}

// const queryApp = () => {
//     APIS.post(URLS.QUERYAPP, {}).then(res => {
//         console.log(res.obj)
//         options.value = res.obj
//         if (res.obj.length > 0) {
//             selectShow.value = true
//         }


//     }).catch(err => openMessage(err.msg))
// }
// const addApp = (params: any) => {
//     return new Promise((reslove, reject) => {
//         APIS.post(URLS.ADDAPP, params).then(res => {
//             reslove(res)
//         }).catch(err => reject(err.msg))
//     })
// }



// const queryAppMenu = (value: any) => {
//     APIS.post(URLS.QUERYAPPMENU, { appId: value }).then(res => {
//         form.appMenu = res.obj

//     }).catch(err => openMessage(err.msg))
// }

// const addAppMenu = (params: any) => {
//     APIS.post(URLS.ADDAPPMENU, params).then(res => {
//         openMessage("菜单创建成功")
//         loading.value = false
//         // console.log(res)

//     }).catch(err => {
//         loading.value = false
//         openMessage(err.msg)
//     })
// }

// const delAppMenu = (params: any) => {
//     APIS.post(URLS.DELAPPMENU, params).then(res => {
//         console.log(res)
//         // form.appMenu = res.obj

//     }).catch(err => openMessage(err.msg))
// }

const queryAll = () => {
    APIS.post(URLS.QUERYALL, {}).then(res => {
        console.log(res.obj)
        options.value = res.obj
        if (res.obj.length > 0) {
            selectShow.value = true
        }
    })
}

const addAll = (params: any) => {
    APIS.post(URLS.ADDALL, params).then(res => {
        loading.value = false
        openMessage(res.msg, 'success')
        // console.log(res)
        window.location.reload()
        // queryAll()

    }).catch(err => {
        loading.value = false
        openMessage(err.msg)
    })
}

const delALL = () => {

    if (!currentAppId.value) {
        openMessage("未选择已配置的公众号")
        return
    }
    delLoading.value = true
    APIS.post(URLS.DELALL, { id: currentAppId.value }).then(res => {
        delLoading.value = false
        openMessage(res.msg)
        window.location.reload()
        // console.log(res)

    }).catch(err => {
        delLoading.value = false
        openMessage(err.msg)
    })
}
queryAll()
</script>

<style>
.form {
    padding: 20px;
    margin: auto;
}
</style>
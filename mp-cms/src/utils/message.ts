import { ElMessage } from "element-plus";

const openMessage = (msg: string, type?: any,) => {
    ElMessage({
        message: msg,
        type: type ? type : "error",
    })
}

export default openMessage
import type { appMenu, appForm } from '@/utils/interface'

const maxId = (array: Array<appMenu>) => {
    return Math.max(...array.map((x: any) => x.id))
}

const insertAtPosition = (str: string | null | undefined, insertStr: string, position: number) => {
    if (!str) {
        str = ''
    }
    return str.slice(0, position) + insertStr + str.slice(position);
}

const replyTypeToText = (type: string) => {
    let res
    switch (type) {
        case 'click': res = '文字'; break;
        case 'view': res = '网页'; break;
        case 'miniprogram': res = '小程序'; break;
        default: break;
    }
    return res
}

const imageToBase64 = (image: any) => {
    return new Promise((reslove, reject) => {
        const reader = new FileReader();
        reader.onload = function (e: any) {
            const base64String = e.target.result;
            reslove(base64String)
            // return base64String; // 输出Base64编码的字符串
        };
        reader.readAsDataURL(image);
    })

}


export default {
    maxId: maxId,
    insertAtPosition: insertAtPosition,
    replyTypeToText: replyTypeToText,
    imageToBase64: imageToBase64
}
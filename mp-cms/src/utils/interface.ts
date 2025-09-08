export interface appMenu {
    id: number | string,
    pId: string
    menuName: string,
    content: string,
    menuType: string,
    url: string,
    pagePath: string,
    appIdDes: string
}

export interface appReply {
    id?: number | string,
    appId?: string,
    msgType?: string,
    content?: string,
    title?: string,
    appIdDes?: string,
    pagePath?: string,
    thumbMedia?: string,
    media?: string,
    thumbMediaId?: string,
    mediaId?: string,
}

export interface appForm {
    name: string,
    appId: string,
    originAppId: string,
    appSecret: string,
    appMenu: Array<appMenu>,
    followReply: string,
    secondReplyTime: number | null | undefined,
    appReply: appReply
}

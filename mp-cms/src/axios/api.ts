import request from './request'

export default {

    post: function (url: string, data?: Object,) {
        return request({
            url: url,
            method: 'post',
            data
        })
    },
    get: function (url: string) {
        return request({
            url: url,
            method: 'get'
        })
    },
}
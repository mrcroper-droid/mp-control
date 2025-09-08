import axios from 'axios'
import { BASE_URL } from './url'
import router from '@/router'
import { store } from '@/store';

// 处理  类型“AxiosResponse<any, any>”上不存在属性“obj”。ts(2339)
declare module "axios" {
  interface AxiosResponse<T = any> {
    errorinfo: null;
    obj: any;
    msg: any;
    statusCode: any;
    // 这里追加你的参数
  }
  export function create(config?: AxiosRequestConfig): AxiosInstance;
}

const request = axios.create({
  baseURL: BASE_URL,
  timeout: 300000
})

// 请求拦截器
request.interceptors.request.use(
  config => {
    // 添加请求头等前置处理
    if (localStorage.getItem('cms')) {
      let token = JSON.parse(localStorage.getItem('cms') as any).user.token;
      if (token) {
        config.headers['Authorization'] = token
      }
    }
    return config

  },
  error => {
    // 请求错误处理
    console.log('Request Error:', error)
    return Promise.reject(error)
  }
)

// 响应拦截器
request.interceptors.response.use(
  response => {
    // 响应后处理
    // console.log(response)
    if (response.status === 200 && response.data.statusCode === '00000') {
      return Promise.resolve(response.data)
    } else {
      return Promise.reject(response.data)
    }
  },
  error => {
    // console.log('Response Error:', error)
    if (error.response && error.response.status === 403) {
      // console.log(store)
      store.commit('LOGOUT')
      return Promise.reject("登录过期")

    } else {
      return Promise.reject(error)
    }

  }
)

export default request

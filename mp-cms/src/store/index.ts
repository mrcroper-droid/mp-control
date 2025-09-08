import { createStore } from 'vuex'
import { user } from './modules/user'
import { setting } from './modules/setting'
import createPersistedstate from 'vuex-persistedstate'

export const store = createStore({
  modules: {
    user: user,
    setting: setting,
  },
  plugins: [createPersistedstate({
    key: 'cms',
    paths: ['user', 'setting']


  },)]
})

// createPersistedstate({
//   key: 'rabbitstore-client',
//   paths: ['user', 'cart']
// })

// ===> 默认是存储在localStorage中

// ===> key是存储数据的键名

// ===> paths是存储state中的那些数据，如果是模块下具体的数据需要加上模块名称，如user.token

// ===> 修改state后触发才可以看到本地存储数据的的变化。
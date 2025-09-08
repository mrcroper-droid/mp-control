import { createRouter, createWebHistory ,createWebHashHistory} from 'vue-router'
import Index from '@/views/index/Index.vue'
import NotFound from "../components/NotFound.vue"
import { store } from '../store/index'
import openMessage from '@/utils/message'
import LoginVue from '@/views/login/Login.vue'
import DemoVue from '@/views/demo/Demo.vue'

const router = createRouter({
  history: createWebHashHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/app/login',
      name: 'login',
      alias: '/',
      component: LoginVue
    },
    
    {
      path: '/app/index',
      name: 'index',
      component: Index,
      // component: () => import('../views/app/Index.vue'),
      meta: {  // 加一个自定义obj
        requireAuth: true   // 参数 true 代表需要登陆才能进入 A
      },
    },

    {
      path: '/app/demo',
      name: 'demo',
      component: DemoVue
    },

    { path: '/:pathMatch(.*)*', name: 'not-found', component: NotFound },

  ]
})

router.beforeEach((to, from, next) => {
  if (to.name === 'index' && to.meta.requireAuth && !(store as any).state.user.username) {
    openMessage("请先登录")
    next({ name: 'login' })
  } else {
    next()
  }
})

// // // GOOD
// router.beforeEach((to, from, next) => {
//   if (to.name !== 'login' && !isAuthenticated) next({ name: 'ogin' })
//   else next()
// })


export default router

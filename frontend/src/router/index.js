import store from '@/store'
import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '../views/MainView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: Main
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/LoginView.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('../views/RegisterBoard.vue')
  },
  {
    path: '/mypage',
    name: 'Mypage',
    component: () => import('../views/MyPageView.vue'),
    meta: {
      requireAuth: true
    }
  },
  {
    path: '*',
    redirect: '/'
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  if (to.matched.some(uri => uri.meta.requireAuth)) {
    if (!store.getters.isLoggedIn) {
      alert('로그인이 필요합니다.')
      next({
        name: 'Login'
      })
    } else {
      next()
    }
  } else {
    next()
  }
})

export default router

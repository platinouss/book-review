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
    path: '/register',
    name: 'register',
    component: () => import('../views/RegisterBoard.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Main from '../components/Main.vue'
import User from '../components/User.vue'
import welcome from '../components/welcome.vue'
import Menu from '../components/Menu.vue'
import auth from '../components/auth.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/Login'
  },
  {
    path: '/Login',
    component: Login,
    name: 'Login',
    //component: () => import('../views/About.vue')
  },
  {
    path: '/Main',
    component: Main,
    redirect: '/welcome',
    children: [
      { path:'/welcome', component: welcome},
      { path:'/user', component: User},
      { path:'/menu', component: Menu},
      { path:'/auth', component: auth},
    ]
  },
]

const router = new VueRouter({
  routes
})

router.beforeEach((to,from,next)=>{
  if (to.path == '/Login') return next();// 访问路径为登录
  // 获取flag
  const flagStr = window.sessionStorage.getItem("flag");// session取值
  if (!flagStr) return next('/Login');// 没登录去登录
  next();
})

export default router

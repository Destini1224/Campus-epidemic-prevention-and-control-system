import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '@/views/Login.vue'
import Index from '@/views/Index/Index.vue'
import Articles from '@/views/SystemManage/Articles.vue'
import ArticlesManage from '@/views/SystemManage/ArticlesManage.vue'
import Student from '@/views/UserManage/Student.vue'
import Teacher from '@/views/UserManage/Teacher.vue'
import Admin from '@/views/UserManage/Admin.vue'
import HealthStu from '@/views/HealthManage/HealthStu.vue'
import HealthTea from '@/views/HealthManage/HealthTea.vue'
import Confirmed from '@/views/HealthManage/Confirmed.vue'
import Departure from '@/views/LeaveManage/Departure.vue'
import Back from '@/views/LeaveManage/Back.vue'
import Leave from '@/views/LeaveManage/Leave.vue'
import Materials from '@/views/MaterialsManage/Materials.vue'
import Application from '@/views/MaterialsManage/Applicaiton.vue'
import UserInfo from '@/views/UserInfo/UserInfo.vue'
import request from '@/utils/request'
import { Message } from 'element-ui'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: Login,
    redirect: '/login'
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/home',
    component: Home,
    redirect: '/index',
    children: [
      {
        path: '/index',
        component: Index
      },
      {
        path: '/articles',
        component: Articles
      },
      {
        path: '/articlesManage',
        component: ArticlesManage
      },
      {
        path: '/student',
        component: Student
      },
      {
        path: '/teacher',
        component: Teacher
      },
      {
        path: '/admin',
        component: Admin
      },
      {
        path: '/healthStu',
        component: HealthStu
      },
      {
        path: '/healthTea',
        component: HealthTea
      },
      {
        path: '/confirmed',
        component: Confirmed
      },
      {
        path: '/departure',
        component: Departure
      },
      {
        path: '/back',
        component: Back
      },
      {
        path: '/leave',
        component: Leave
      },
      {
        path: '/materials',
        component: Materials
      },
      {
        path: '/application',
        component: Application
      },
      {
        path: '/userInfo',
        component: UserInfo
      }
    ]
  }
]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  if (to.path.startsWith('/login')) {
    // 取出token信息
    window.localStorage.removeItem('username')
    window.localStorage.removeItem('token')
    next()
  } else {
    // 获取token的信息。
    let token = JSON.parse(window.localStorage.getItem('token'))
    if (!token) {
      next({ path: '/login' })
    } else {
      // 校验token合法性
      request({
        url: '/admin/checkToken',
        method: 'get',
        headers: {
          token: token
        }
      }).then((response) => {
        if (!response) {
          Message.error('登录信息出现问题，请重新登录')
          next({ path: '/login' })
        }
      })
      next()
    }
  }
})

export default router

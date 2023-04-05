import Vue from 'vue'
import VueRouter from 'vue-router'
import request from '@/utils/request'
import Home from '../views/Home.vue'
import Login from '@/views/Login.vue'
import Index from '@/views/Index/Index.vue'
import News from '@/views/News/News.vue'
import Notices from '@/views/Notices/Notices.vue'
import Campus from '@/views/Campus/Campus.vue'
import Media from '@/views/Media/Media.vue'
import StudentInfo from '@/views/StuInfo/StudentInfo.vue'
import StudentHealth from '@/views/StuInfo/StudentHealth.vue'
import StudentLeave from '@/views/StuInfo/StudentLeave.vue'
import StudentBack from '@/views/StuInfo/StudentBack.vue'
import StudentDeparture from '@/views/StuInfo/StudentDeparture.vue'
import StudentApplicaiton from '@/views/StuInfo/StudentApplication.vue'
import TeacherInfo from '@/views/TeaInfo/TeacherInfo.vue'
import TeacherHealth from '@/views/TeaInfo/TeacherHealth.vue'
import HealthStu from '@/views/TeaInfo/HealthStu.vue'
import Confirmed from '@/views/TeaInfo/Confirmed.vue'
import TeacherLeave from '@/views/TeaInfo/TeacherLeave.vue'
import TeacherBack from '@/views/TeaInfo/TeacherBack.vue'
import TeacherDeparture from '@/views/TeaInfo/TeacherDeparture.vue'
import Materials from '@/views/TeaInfo/Materials.vue'
import TeacherApplicaiton from '@/views/TeaInfo/TeacherApplication.vue'
import { Message } from 'element-ui'
// import store from '../store/index'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/login',
    component: Login
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
      { path: '/index', component: Index },
      { path: '/news', component: News },
      { path: '/notices', component: Notices },
      { path: '/campus', component: Campus },
      { path: '/media', component: Media },
      { path: '/studentInfo', component: StudentInfo },
      { path: '/studentHealth', component: StudentHealth },
      { path: '/studentLeave', component: StudentLeave },
      { path: '/studentBack', component: StudentBack },
      { path: '/studentDeparture', component: StudentDeparture },
      { path: '/studentApplicaiton', component: StudentApplicaiton },
      { path: '/teacherInfo', component: TeacherInfo },
      { path: '/teacherHealth', component: TeacherHealth },
      { path: '/healthStu', component: HealthStu },
      { path: '/confirmed', component: Confirmed },
      { path: '/teacherLeave', component: TeacherLeave },
      { path: '/teacherBack', component: TeacherBack },
      { path: '/teacherDeparture', component: TeacherDeparture },
      { path: '/materials', component: Materials },
      { path: '/teacherApplicaiton', component: TeacherApplicaiton }
    ]
  }
]

const router = new VueRouter({
  routes
})

// 设置路由守卫，在进页面之前，判断有token，才进入页面，否则返回登录页面
router.beforeEach((to, from, next) => {
  if (to.path.startsWith('/login')) {
    // 取出token信息
    window.localStorage.removeItem('username')
    window.localStorage.removeItem('token')
    window.localStorage.removeItem('type')
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

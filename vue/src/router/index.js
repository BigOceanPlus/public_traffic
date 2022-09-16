import {createRouter, createWebHistory} from "vue-router";
import {ElNotification} from "element-plus";

const routes = [
    {
        path: '/',
        name: 'login',
        component: () => import('../views/login.vue'),
    },
    {
        path: '/home',
        name: 'home',
        component: () => import('../views/home.vue'),
    },
    {
        path: '/test',
        name: 'test',
        component: () => import('../views/test.vue'),
    },
    {
        path: '/test1',
        name: 'test1',
        component: () => import('../views/test1.vue'),
    },
]

const router = createRouter({
    history: createWebHistory('/'),
    routes: routes,
})

const timeline = 1000 * 3600 // 1h
const whileList = ["/","/test"]
router.beforeEach((to, from, next) => {
    let token = localStorage.getItem("token")
    let startTime = Number(localStorage.getItem("startTime"))
    let endTime = new Date().getTime()
    if(endTime - startTime > timeline) token = null

    if(token){
        if(to.path == "/") next("/home")
        else next()
    }
    else{
        if(whileList.includes(to.path)) next()
        else{
            ElNotification({
                type: 'error',
                message: '登录验证失败,请重新登录'
            })
            next("/")
        }
    }
})

export default router;
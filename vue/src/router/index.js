import {createRouter, createWebHistory} from "vue-router";
import {ElNotification} from "element-plus";

const routes = [

    {
        path: '/',
        name: 'login',
        component: () => import('../views/login.vue'),
        meta:{
            title: "登录界面"
        }
    },
    {
        path: '/home',
        name: 'home',
        component: () => import('../views/home.vue'),
        redirect: '/home/selector',
        meta: {
            title: "售票系统"
        },
        children: [
            {
                path: '/home/sale',
                name: 'sale',
                component: () => import('../views/sale.vue'),
            },
            {
                path: '/home/selector',
                name: 'selector',
                component: () => import('../views/selector.vue'),
            }
        ]
    },
    {
        path: '/manager',
        name: 'manager',
        component: () => import('../views/manager.vue'),
        meta:{
            title: "用户管理系统"
        }
    },
    {
        path: '/analysis',
        name: 'analysis',
        component: () => import('../views/analysis.vue'),
        meta:{
            title: "数据分析系统"
        }
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
    {
        path: '/test2',
        name: 'test2',
        component: () => import('../views/test2.vue'),
        children: [
            {
                path: '/test2/test3',
                name: 'test3',
                component: () => import('../views/test3.vue'),
            },
            {
                path: '/test2/test4',
                name: 'test4',
                component: () => import('../views/test4.vue'),
            },
        ]
    },
]

const router = createRouter({
    history: createWebHistory('/'),
    routes: routes,
})

const timeline = 1000 * 3600 // 1h
const whileList = ["/","/test","/test2","/test2/test3","/test2/test4","/sale","/selector"]
const homeList = ["/home","/home/sale","/home/selector"]
router.beforeEach((to, from, next) => {
    let token = localStorage.getItem("token")
    let startTime = Number(localStorage.getItem("startTime"))
    let endTime = new Date().getTime()
    if(endTime - startTime > timeline) token = null

    if(token && localStorage.getItem("flag") != null){
        if(to.path === "/"){
            if(localStorage.getItem("flag") === "1") next("/home")
            else if(localStorage.getItem("flag") === "2") next("/analysis")
            else if(localStorage.getItem("flag") === "3") next("/manager")
            else next("/")
        }
        else if((localStorage.getItem("flag") === "1" && homeList.includes(to.path) ||
        (localStorage.getItem("flag") === "2" && to.path === "/analysis") ||
        (localStorage.getItem("flag") === "3" && to.path === "/manager"))) next()
        else next("/")
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
    next()
})

export default router;
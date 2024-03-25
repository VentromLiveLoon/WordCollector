import { createWebHistory,createRouter } from "vue-router";


const routes = [
    {
        path:"/",
        redirect:"/login",
    },
    {   
        path:'/home',
        component:()=>import ('../views/AWord.vue')
    },
    {
        path:'/login',
        component:() => import ('../views/Login.vue')
    },
    {
        path:'/register',
        component:() => import('../views/Register.vue')
    },
    {
        path:'/word/add',
        component:() => import('../views/AddWord.vue')
    },
    {
        path:'/word/list',
        component:()=>import('../views/ListWord.vue')
    }
]

const router = createRouter({
    history:createWebHistory(import.meta.env.BASE_URL),
    routes
})

export default router
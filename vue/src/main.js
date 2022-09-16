import { createApp } from 'vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
//import './style.css'
import App from './App.vue'
import zhCn from 'element-plus/es/locale/lang/zh-cn'

const app = createApp(App);
app.use(router).use(ElementPlus,{locale: zhCn}).mount('#app')
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
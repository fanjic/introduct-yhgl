import Vue from 'vue'
import App from './App.vue'
import router from './router'
import  ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import './assets/css/global.css'
import './assets/iconfont/iconfont.css'
import axios from 'axios'

import echarts from 'echarts'
Vue.prototype.$echarts = echarts 


Vue.use(ElementUI);
Vue.prototype.$http=axios
Vue.prototype.$echarts = echarts 
axios.defaults.baseURL="http://localhost:8090"

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')

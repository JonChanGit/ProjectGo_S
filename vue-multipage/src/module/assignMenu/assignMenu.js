// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Tool from '../../components/Tool';
import Vue from 'vue';
import App from './App';
import ElementUI from 'element-ui';
import store from './storage';
import 'element-ui/lib/theme-chalk/index.css';

Vue.config.productionTip = false;
Vue.use(ElementUI);

window.roleId = Tool.getQueryString('rolerId');
console.log('roleId is :');
console.log(window.roleId);

/* eslint-disable no-new */
let my_vue = new Vue({
  el: '#app',
  store,
  template: '<App/>',
  components: { App },
  method:{
    getData() {

    }
  }
});

window.my_vue = my_vue;

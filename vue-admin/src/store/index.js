import Vue from 'vue';
import Vuex from 'vuex';

import app from './modules/app';
import user from './modules/user';

Vue.use(Vuex);

const store = new Vuex.Store({
    state: {
        roleList:[],// 角色列表
    },
    mutations: {
       setRoleList(state, roleList){
		   state.roleList = roleList;
        },
    },
	getters: {
		getRoleListView: state => {
			let list =[];
			state.roleList.forEach((item,index) => {
			    let {available,id,name} = item;
			    let obj = {'disabled':(available==1)?false:true,'key':id,'label':name};
			    if(id == 1){
					obj.disabled = true;
                }
				list[index] = obj;
            });
			return list;
		}
	},
    actions: {

    },
    modules: {
        app,
        user
    }
});

export default store;

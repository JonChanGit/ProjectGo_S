import Cookies from 'js-cookie';

/**
 * 授权资源相关
 * @type {{state: {}, mutations: {}}}
 */
const access = {
    state: {
		roleList:[],// 角色列表
		groupList:[],// 组列表
    },
    mutations: {
		setRoleList(state, roleList){
			state.roleList = roleList;
		},
		setGroupList(state, groupList){
		    console.log('setGroupList');
		    console.log(groupList);
			state.groupList = groupList;
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
		},
		getGroupListView: state => {
			let list =[];
			state.groupList.forEach((item,index) => {
				let {available,id,name} = item;
				let sp = '';
				for (let i = 0;i < item.leve-1;i++){
					sp += " >";
                }
				let obj = {'disabled':(available==1)?false:true,'key':id,'label':sp+name};
				list[index] = obj;
			});
			return list;
		},
	},
};

export default access;

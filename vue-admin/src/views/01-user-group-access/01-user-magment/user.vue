<template>
	<div>
		<Card >
			<p slot="title">
				用户管理
			</p>
			<div >
				<Button type="primary" @click="showNewUserModal">新增</Button>
				<br/>
				<br/>
				<can-edit-table refs="table2" v-model="editInlineData" :columns-list="editInlineColumns" @on-change="submitChange"></can-edit-table>
			</div>

			<br/>
			<br/>
			<Page :total="pageInfo.totalSize" :current="pageInfo.currPage" :page-size="pageInfo.pageSize" show-sizer></Page>

		</Card>


		<Modal
				v-model="showModal"
				title="新增用户"
				@on-ok="ok"
				@on-cancel="cancel">
			<Form :model="newUser" :label-width="80">
				<FormItem label="用户名">
					<Input v-model="newUser.username" placeholder=""></Input>
				</FormItem>
				<FormItem label="邮箱">
					<Input v-model="newUser.email" placeholder=""></Input>
				</FormItem>
				<FormItem label="可用状态">
					<Select v-model="newUser.locked">
						<Option value=0>可用</Option>
						<Option value=1>锁定</Option>
					</Select>
				</FormItem>
			</Form>
		</Modal>

	</div>
</template>

<script>
	import canEditTable from '../../tables/components/canEditTable.vue';
	import Tool from '@/libs/Tool';

	export default {
		data() {
			return {
				editInlineData:[],
				editInlineColumns:[
					{
						title: '序号',
						type: 'index',
						width: 80,
						align: 'center'
					},
					{
						title: '姓名',
						align: 'center',
						key: 'username',
						editable: true
					},
					{
						title: '邮箱',
						align: 'center',
						key: 'email',
						editable: true
					},
					{
						title: '状态',
						align: 'center',
						key: 'locked',
						width: 120,
						editable: true,
						inputType: 'select',
						selectItems:[
							{
								value:0,
								label:'正常',
							},
							{
								value:1,
								label:'锁定',
							},
						]
					},
					{
						title: '操作',
						align: 'center',
						width: 190,
						key: 'handle',
						handle: ['edit']
					}
				],
				showModal:false,
				newUser:{},
				pageInfo:{},
			};
		},
		components: {
			canEditTable
		},
		methods:{
			submitChange(val, index){
				console.log('val is : ');
				console.log(val);
				Tool.put({
					iView:this,
					data:{
						locked:val[index].locked,
						username:val[index].username,
						email:val[index].email,
						id:val[index].id,
					},
					url:'/api/access/user_and_permission/user.do',
					successCallback:(data)=>{
						this.$Message.success(data.message);
					}
				});
			},
			ok () {
				Tool.post({
					iView:this,
					data:this.newUser,
					url:'/api/access/user_and_permission/user.do',
					successCallback:(data)=>{
						this.$Message.success(data.message);
						this.getData();
					}
				});
			},
			cancel () {
				this.$Message.info('Clicked cancel');
			},
			showNewUserModal:function () {
				this.showModal = true;
			},
			getData(){
				Tool.get({
					iView:this,
					url:'/api/access/user_and_permission/userList.do',
					successCallback:(data)=>{
						this.editInlineData = data.list;
					}
				});
			}
		},
		beforeCreate:function () {
			this.getPageInfo = Tool.getPageInfo();
		},
		mounted:function () {
			this.getData();
		},
	};
</script>

<style>

</style>

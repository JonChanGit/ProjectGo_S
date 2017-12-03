<template>
	<div>
		<Card shadow>
			<p slot="title">
				角色定义
			</p>
			<div >
				<Button type="primary" @click="showNewRoleModal">添加</Button>
				<br/>
				<br/>
				<can-edit-table refs="table2" v-model="editInlineData" :columns-list="editInlineColumns" @on-change="submitChange" @on-delete="handleDel"></can-edit-table>
			</div>
		</Card>


		<Modal
				v-model="showModal"
				title="添加角色"
				@on-ok="ok"
				@on-cancel="cancel">
			<Form :model="newRole" :label-width="80">
				<FormItem label="角色名">
					<Input v-model="newRole.name" placeholder=""></Input>
				</FormItem>
				<FormItem label="可用状态">
					<Select v-model="newRole.available">
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
				pageInfo:{},
				editInlineData:[],
				editInlineColumns:[
					{
						title: '序号',
						type: 'index',
						width: 80,
						align: 'center'
					},
					{
						title: '角色名',
						align: 'center',
						key: 'name',
						editable: true
					},
					{
						title: '状态',
						align: 'center',
						key: 'available',
						width: 120,
						editable: true,
						inputType: 'select',
						selectItems:[
							{
								value:0,
								label:'锁定',
							},
							{
								value:1,
								label:'正常',
							},
						]
					},
					{
						title: '操作',
						align: 'center',
						width: 190,
						key: 'handle',
						handle: ['edit','delete']
					}
				],
				showModal: false,
				newRole:{},
			};
		},
		components: {
			canEditTable
		},
		methods:{
			getData(){
				Tool.get({
					iView:this,
					data:{
					},
					url:'/api/access/user_and_permission/roleList.do',
					successCallback:(data)=>{
						this.editInlineData = data.list;
						this.pageInfo.totalSize = data.totalSize;
					}
				});
			},
			changePage(val){
			},
			changePageSize(val){
			},
			showNewRoleModal(){
				this.showModal = true;
			},
			submitChange(val, index){
				console.log('val is : ');
				console.log(val);
				Tool.put({
					iView:this,
					data:{
						available:val[index].available,
						name:val[index].name,
						id:val[index].id,
					},
					url:'/api/access/user_and_permission/role.do',
					successCallback:(data)=>{
						this.$Message.success(data.message);
					}
				});
			},
			handleDel (val, index) {
				console.log('val is : ');
				console.log(val);
				console.log(index);
				Tool.delete({
					iView:this,
					data:{
						id:val[index].id,
					},
					url:'/api/access/user_and_permission/role.do',
					successCallback:(data)=>{
						this.$Message.success(data.message);
						this.$Message.success('删除了第' + (index + 1) + '行数据');
						this.getData();
					}
				});

			},
			ok () {
				Tool.post({
					iView:this,
					data:this.newRole,
					url:'/api/access/user_and_permission/role.do',
					successCallback:(data)=>{
						this.$Message.success(data.message);
						this.getData();
					}
				});
			},
			cancel () {
			},
		},
		beforeCreate:function () {
		},
		mounted:function () {
			this.pageInfo = Tool.getPageInfo();
			this.getData();
		},
	};
</script>

<style>

</style>

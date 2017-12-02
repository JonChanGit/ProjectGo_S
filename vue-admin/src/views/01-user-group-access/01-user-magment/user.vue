<template>
	<div>
		<Card shadow>
			<Card>
				<p slot="title">
					<Icon type="android-remove"></Icon>
					可编辑单元行
				</p>
				<div class="edittable-table-height-con">
					<can-edit-table refs="table2" v-model="editInlineData" :columns-list="editInlineColumns"></can-edit-table>
				</div>
			</Card>
		</Card>
	</div>
</template>

<script>
	import canEditTable from '../../tables/components/canEditTable.vue';
	import Tool from '@/libs/Tool';

	export default {
		data() {
			return {
				editInlineData:[
					{
						name: 'Aresn',
						sex: '男',
						work: '前端开发'
					},
					{
						name: 'Lison',
						sex: '男',
						work: '前端开发'
					},
					{
						name: 'lisa',
						sex: '女',
						work: '程序员鼓励师'
					}
				],
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
						width: 90,
						editable: true
					},
					{
						title: '状态',
						align: 'center',
						key: 'locked',
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
						handle: ['edit', 'delete']
					}
				]
			};
		},
		components: {
			canEditTable
		},
		mounted:function () {
			Tool.get({
				iView:this,
				url:'/api/access/user_and_permission/userList.do',
				successCallback:(data)=>{
					this.editInlineData = data.list;
				}
			});
		}
	};
</script>

<style>

</style>

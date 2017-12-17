<template>
	<div>
		<Card shadow>
			<p slot="title">
				资源管理
			</p>
			<div >
				<tree></tree>
			</div>
		</Card>
	</div>
</template>

<script>
	import Tool from '@/libs/Tool';
	import Tree from './resources-tree/tree/tree.vue';

	export default {
		data() {
			return {
				pageInfo: {},
			};
		},
		components: {
			Tree
		},
		methods: {
			getData() {
			},
			changePage(val) {
			},
			changePageSize(val) {
			},
		},
		beforeCreate: function () {
		},
		mounted: function () {
			//获取角色列表
			Tool.get({
				iView: this,
				data: {},
				url: '/api/access/user_and_permission/roleList.do',
				successCallback: (data) => {
					if (data.flag == true) {
						let list = data.list;
						this.$store.commit('setRoleList',list);
					}
				}
			});
			Tool.get({
				iView: this,
				data: {},
				url: '/api/access/user_and_permission/groupList.do',
				successCallback: (data) => {
					if (data.flag == true) {
						let list = data.list;
						this.$store.commit('setGroupList',list);
					}
				}
			});
		},
		activated:function () {
		}
	};
</script>

<style>

</style>

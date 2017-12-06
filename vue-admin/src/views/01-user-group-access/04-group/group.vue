<template>
	<div>
		<Card shadow>
			<p slot="title">
				组管理
			</p>
			<div >
				<tree></tree>
			</div>
		</Card>
	</div>
</template>

<script>
	import Tool from '@/libs/Tool';
	import Tree from './group-tree/tree/tree.vue';

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
		},
	};
</script>

<style>

</style>

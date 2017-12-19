<template>
	<div>
		<div style="border-bottom: 1px solid #e9e9e9;padding-bottom:6px;margin-bottom:6px;">
			<Checkbox >全选</Checkbox>
		</div>
		<CheckboxGroup  v-model="checked">
			<Checkbox v-for="item in list" :label="item.id" >{{item.groupName+' + '+item.roleName}}</Checkbox>
		</CheckboxGroup>
		{{checked}}
		<br/>
		<hr/>
		<br/>
		<Button type="error" long @click="deleteHld">删除选中组合</Button>
		<br/>
		<br/>
		<hr/>
		<br/>
	</div>
</template>

<script>
	import Tool from '@/libs/Tool';

	export default {
		data() {
			return {
				pageInfo: {},
				// 被选中的数据
				checked:[],
			};
		},
		components: {},
		props:['list'],
		methods: {
			deleteHld() {
				if(this.checked.length < 1){
					this.$Message.info('没有选中数据');
				}else{
					this.$emit('on-delete',this.checked);
				}
			},
		},
		watch:{
			list:function (newVal,oldVal){
				if(newVal != oldVal){
					this.checked = [];
				}else{

				}
			}
		},
		beforeCreate: function () {
		},
		mounted: function () {
		},
	};
</script>

<style>

</style>

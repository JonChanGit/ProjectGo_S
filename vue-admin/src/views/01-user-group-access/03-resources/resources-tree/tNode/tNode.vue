<template>
	<div>
		<li class="list-group-item li-title" :class="{active:isOpen}" @click="clickMenu()">
    <span class="badge ">
      <Icon type="arrow-down-b" v-show="isOpen"></Icon>
      <Icon type="arrow-right-b" v-show="!isOpen"></Icon>
    </span>
			{{pnode.name}}&nbsp;
		</li>
		<li class="list-group-item tool-bar" :class="{'hidden':glyphiconDown}">

			<div class="btn-group">
				<Button type="primary" @click="openModel('new')">
					<Icon type="plus-circled"></Icon>
				</Button>
				<Button type="info" @click="openModel()">
					<Icon type="edit"></Icon>
				</Button>
				<Button type="error" @click="deleteNode()">
					<Icon type="trash-a"></Icon>
				</Button>
				<Button type="success" @click="openAccessNode()">
					<Icon type="ios-gear"></Icon>
				</Button>
			</div>

			&nbsp;
		</li>

		<ul>
			<div v-for="item in children" v-if="hasChild" class="children-box">
				<treeNode v-bind:isActive='item.active' :pnode="item"></treeNode>
			</div>
		</ul>

		<Spin size="large" fix v-if="spinShow">
			<Icon type="load-c" size=18  class="demo-spin-icon-load"></Icon>
			<div>Loading</div>
		</Spin>

		<Modal v-model="showModel"
			   :title="modelTitle"
			   @on-ok="ok"
			   @on-cancel="cancel">
			<Form :model="resourcesData" :label-width="80">
				<FormItem label="菜单标题">
					<Input v-model="resourcesData.name" placeholder=""></Input>
				</FormItem>
				<FormItem label="排序">
					<Input v-model="resourcesData.sortstring" placeholder=""></Input>
				</FormItem>
				<FormItem label="URL">
					<Input v-model="resourcesData.url" placeholder=""></Input>
				</FormItem>
				<FormItem label="标识">
					<Input v-model="resourcesData.percode" placeholder=""></Input>
				</FormItem>
			</Form>
		</Modal>

		<Modal v-model="showDeleteModel" width="360">
			<p slot="header" style="color:#f60;text-align:center">
				<Icon type="information-circled"></Icon>
				<span>确定删除{{pnode.name}}?</span>
			</p>
			<div style="text-align:center">
				<p>删除后无法恢复，请注意！</p>
				<p>请注意你的操作?</p>
			</div>
			<div slot="footer">
				<Button type="error" size="large" long :loading="delete_modal_loading" @click="del">Delete</Button>
			</div>
		</Modal>
		<Modal v-model="showAccessModel"  width="900"
			   title="权限配置"
			   @on-visible-change="accessModelVChange">
			<Tabs value="name1">
				<TabPane label="角色" name="name1">
					<Transfer
							:data="transferRoleData"
							:target-keys="transferRoleTargetKeys"
							:list-style="unTransferDimensionListStyle"
							:render-format="transferRoleRender"
							:titles="transferRoleTitles"
							@on-change="transferRoleOnChange"></Transfer>
					<br/>
					<Button type="success" long @click="submitRole()">提交</Button>
				</TabPane>
				<TabPane label="组" name="name2">
					<Transfer
							:data="transferGroupData"
							:target-keys="transferGroupTargetKeys"
							:list-style="unTransferDimensionListStyle"
							:render-format="transferGroupRender"
							:titles="transferGroupTitles"
							@on-change="transferGroupOnChange"></Transfer>
					<br/>
					<Button type="success" long @click="submitGroup()">提交</Button>
				</TabPane>
				<TabPane label="多维度" name="name3">
					<Row>
						<Col span="24">
							<div style="border-bottom: 1px solid #e9e9e9;padding-bottom:6px;margin-bottom:6px;">
								<Checkbox >全选</Checkbox>
							</div>
							<CheckboxGroup  >
								<Checkbox label="香蕉"></Checkbox>
								<Checkbox label="苹果"></Checkbox>
								<Checkbox label="西瓜"></Checkbox>
							</CheckboxGroup>
							<br/>
							<hr/>
							<br/>
							<Button type="error" long>删除选中组合</Button>
							<br/>
							<br/>
							<hr/>
							<br/>
						</Col>
					</Row>
					<Row>
						<Col span="12">
							<Transfer
								:data="transferDimensionDataRole"
								:target-keys="transferDimensionRoleTargetKeys"
								:list-style="transferDimensionListStyle"
								:render-format="transferDimensionRender"
								:titles="transferRoleTitles"
								@on-change="transferDimensionRoleOnChange"></Transfer>
						</Col>
						<Col span="12">
							<Transfer
								:data="transferDimensionDataGroup"
								:target-keys="transferDimensionGroupTargetKeys"
								:list-style="transferDimensionListStyle"
								:render-format="transferDimensionRender"
								:titles="transferGroupTitles"
								@on-change="transferDimensionGroupOnChange"></Transfer>
						</Col>
					</Row>
					<br/>
					<Button type="success" long @click="submitRG()">提交选定组合</Button>
				</TabPane>
			</Tabs>
			<div slot="footer">
			</div>
		</Modal>

	</div>
</template>

<script>
	import Axios from 'axios';
	import Qs from 'qs';
	import Tool from '@/libs/Tool.js';
	import C from '@/libs/GlobalConstant.js';
	import treeNode from './tNode.vue';

	export default {
		name: 'treeNode',
		data() {
			return {
				'children': {},
				isOpen: false,
				'hasChild': false,
				'parentids': [],
				'spinShow': false,//显示加载提示
				'showModel':false,//显示模态框
				'modelTitle':'添加菜单',//显示模态框
				'showDeleteModel':false,//显示删除模态框
				'showAccessModel':false,//显示权限配置模态框
				'delete_modal_loading': false,
				'resourcesData':{},//编辑数据
				'modelOkFunction':{},//模态框动态绑定OK方法
				//穿梭框-角色标题
				'transferRoleTitles':['可选择角色','已选择角色'],
				//穿梭框-组标题
				'transferGroupTitles':['可选择组','已选择组'],
				//穿梭框-角色数据
				'transferRoleData':[],
				//穿梭框-角色被选择数据
				'transferRoleTargetKeys': [C.ROLE_Administrator],
				//穿梭框-组数据
				'transferGroupData':[],
				//穿梭框-组被选择数据
				'transferGroupTargetKeys': [],
				//穿梭框-维度数据-角色
				'transferDimensionDataRole':[],
				//穿梭框-维度数据-组
				'transferDimensionDataGroup':[],
				//穿梭框-维度被选择数据-角色
				'transferDimensionRoleTargetKeys': [],
				//穿梭框-维度被选择数据-组
				'transferDimensionGroupTargetKeys': [],
				//非穿梭框样式
				'unTransferDimensionListStyle': {
					width: '403px',
					height: '300px'
				},
				//穿梭框样式
				'transferDimensionListStyle': {
					height: '300px'
				}
			};
		},
		components: {
			'treeNode': treeNode
		},
		props: {
			'isActive': Boolean, // 是否激活
			'pnode': {}, // 当前节点对象
		},
		computed: {
			glyphiconDown: function () {
				return this.isOpen === true ? false : true;
			},
			glyphiconUp: function () {
				return this.isOpen === true ? true : false;
			}
		},
		created: function () {
			this.transferRoleData = this.$store.getters.getRoleListView;
			this.transferDimensionDataRole = this.$store.getters.getRoleListView;
			this.transferGroupData = this.$store.getters.getGroupListView;
			this.transferDimensionDataGroup = this.$store.getters.getGroupListView;
			console.log('this.transferRoleData');
			console.log(this.transferRoleData);
			this.isOpen = this.isActive;
			try {
				this.parentids = this.pnode.parentids.split(',');
			} catch (e) {
				console.log(this.parentids);
			}

		},
		methods: {
			/**
			 * 展开权限配置模态框时
			 * */
			accessModelVChange(v){
				if(v){
					Tool.get({
						iView: this,
						data: {
							pid: this.pnode.id,
						},
						url: '/api/access/user_and_permission/access.do',
						successCallback: (data) => {
							//使用对象拓展运算符，拆开数据，然后在方法中使用Rest运算符合并成新数组
							let lst = Tool.restMergeArray(C.ROLE_Administrator,...data.entity.roleList);
							this.transferRoleTargetKeys = lst;
							this.transferGroupTargetKeys = data.entity.groupList;
						}
					});
				}
			},
			//穿梭框-角色自定义输出
			transferRoleRender (item) {
				return item.label;
			},
			//穿梭框-角色数据变更
			transferRoleOnChange (newTargetKeys) {
				this.transferRoleTargetKeys = newTargetKeys;
			},
			submitRole(){
				Tool.post({
					iView: this,
					data: {
						pid: this.pnode.id,
						'roles':this.transferRoleTargetKeys.join(","),
					},
					url: '/api/access/user_and_permission/rolePermission.do',
					successCallback: (data) => {
						obj.iView.$Message.success(data.message);
					}
				});
			},
			submitGroup(){
				Tool.post({
					iView: this,
					data: {
						pid: this.pnode.id,
						'roles':this.transferGroupTargetKeys.join(","),
					},
					url: '/api/access/user_and_permission/groupPermission.do',
					successCallback: (data) => {
						obj.iView.$Message.success(data.message);
					}
				});
			},
			submitRG(){
				console.log(this.transferDimensionGroupTargetKeys.length);
				if(this.transferDimensionRoleTargetKeys.length != 1 || this.transferDimensionGroupTargetKeys.length != 1){
					this.$Message.error('请选择组合');
					return;
				}
				Tool.post({
					iView: this,
					data: {
						pid: this.pnode.id,
						'groupId':this.transferDimensionRoleTargetKeys[0],
						'roleId':this.transferDimensionGroupTargetKeys[0],
					},
					url: '/api/access/user_and_permission/rgPermission.do',
					successCallback: (data) => {
						obj.iView.$Message.success(data.message);
					}
				});
			},
			//穿梭框-组自定义输出
			transferGroupRender (item) {
				return item.key + ':' + item.label;
			},
			//穿梭框-组数据变更
			transferGroupOnChange (newTargetKeys) {
				this.transferGroupTargetKeys = newTargetKeys;
			},
			//穿梭框-维度自定义输出
			transferDimensionRender (item) {
				return item.key + ':' + item.label;
			},
			//穿梭框-维度（角色）数据变更
			transferDimensionRoleOnChange (newTargetKeys) {
				if(newTargetKeys.length > 1){
					this.$Message.info('一次只能选择一条数据');
				}else{
					this.transferDimensionRoleTargetKeys = newTargetKeys;
				}
			},
			//穿梭框-维度（组）数据变更
			transferDimensionGroupOnChange (newTargetKeys) {
				if(newTargetKeys.length > 1){
					this.$Message.info('一次只能选择一条数据');
				}else{
					this.transferDimensionGroupTargetKeys = newTargetKeys;
				}
			},
			clickMenu: function () {
				if (!this.isOpen) {
					this.open();
				} else {
					this.close();
				}
			},
			open: function () {
				this.spinShow = true;
				Tool.get({
					iView: this,
					data: {
						pid: this.pnode.id,
					},
					url: '/api/access/user_and_permission/resourcesChildrenNode.do',
					successCallback: (data) => {
						if (data.flag == true) {
							this.children = data.entity.children;

							this.hasChild = true;
						}
						this.active = true;
						this.isOpen = true;
						this.spinShow = false;
					}
				});
			},
			close: function () {
				this.children = new Object;
				this.isOpen = false;
			},
			openModel:function (type) {
				if(type == 'new'){
					this.modelTitle = "添加菜单";
					this.resourcesData.id = 0;
					this.resourcesData.parentid = this.pnode.id;
					this.modelOkFunction = this.newDialog;
				}else {
					this.modelTitle = "编辑菜单";
					this.resourcesData = this.pnode;
					//this.resourcesData.parentid = 0;
					this.modelOkFunction = this.editDialog;
				}
				this.showModel = true;
			},
			openAccessNode:function () {
				this.showAccessModel= true;
			},
			/**
			 * 添加数据
			 */
			newDialog: function () {
				console.log(this.resourcesData);
				Tool.post({
					iView:this,
					data:this.resourcesData,
					url:'/api/access/user_and_permission/resourcesNode.do',
					successCallback:(data)=>{
						this.$Message.success(data.message);
						this.open();
					}
				});
			},
			/**
			 * 编辑数据
			 */
			editDialog: function () {
				Tool.put({
					iView:this,
					data:this.resourcesData,
					url:'/api/access/user_and_permission/resourcesNode.do',
					successCallback:(data)=>{
						this.$Message.success(data.message);
					}
				});
			},
			deleteNode: function () {
				this.showDeleteModel = true;
			},
			del:function () {
				Tool.delete({
					iView:this,
					data:{
						permissionId:this.pnode.id,
					},
					url:'/api/access/user_and_permission/resourcesNode.do',
					successCallback:(data)=>{
						this.showDeleteModel = false;
						this.$Message.success(data.message);
						this.$parent.open();
					}
				});
			},
			ok:function () {
				this.modelOkFunction();
			},
			cancel:function () {

			}
			
			
		}
	};
</script>

<style scoped>
	.li-title{
		cursor: pointer;
	}
	.list-group-item {
		position: relative;
		display: block;
		padding: 10px 15px;
		margin-bottom: -1px;
		background-color: #fff;
		border: 1px solid #ddd;
	}

	.list-group-item:first-child {
		border-top-left-radius: 4px;
		border-top-right-radius: 4px;
	}

	.list-group-item.active, .list-group-item.active:focus, .list-group-item.active:hover {
		z-index: 2;
		color: #fff;
		background-color: #337ab7;
		border-color: #337ab7;
	}

	.tool-bar {
		border-bottom: 0px;
		border-left: 0px;
	}

	.children-box {
		background-color: #fff;
		border: 1px solid #ddd;
		border-radius: 4px;
		margin-left: 3%;
	}

	.hidden {
		display: none;
	}
</style>

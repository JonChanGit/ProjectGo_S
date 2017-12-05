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
			   title="Common Modal dialog box title"
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


	</div>
</template>

<script>
	import Axios from 'axios';
	import Qs from 'qs';
	import Tool from '@/libs/Tool.js';
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
				'showDeleteModel':false,//显示删除模态框
				'delete_modal_loading': false,
				'resourcesData':{},//编辑数据
				'modelOkFunction':{},//模态框动态绑定OK方法
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
			this.isOpen = this.isActive;
			try {
				this.parentids = this.pnode.parentids.split(',');
			} catch (e) {
				console.log(this.parentids);
			}

		},
		methods: {
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
					this.resourcesData.id = 0;
					this.resourcesData.parentid = this.pnode.id;
					this.modelOkFunction = this.newDialog;
				}else {
					this.resourcesData = this.pnode;
					//this.resourcesData.parentid = 0;
					this.modelOkFunction = this.editDialog;
				}
				this.showModel = true;
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
				/* layer.confirm('确定删除?', {icon: 3, title:'警告'}, (index)=>{
				   console.log(this.pnode.id);
				   Axios.post(Tools.uslPre+'/access/user_and_permission/permissionDelete.do',
					 Qs.stringify({permissionId: this.pnode.id,})
				   ).then((response) => {
					 Tools.requestFeedback({response,successCallback:()=>{
					   layer.msg('操作成功');
					   this.$parent.open();
					   },
					 });
				   }).catch((error) => {
					 console.log(error);
					 layer.closeAll();
				   });
				   layer.close(index);
				 });*/

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

<template>
  <div>
    <li class="list-group-item" :class="{active:isOpen}" @click="clickMenu()">
    <span class="badge ">
      <Icon type="arrow-down-b" v-show="isOpen"></Icon>
      <Icon type="arrow-right-b" v-show="!isOpen"></Icon>
    </span>
      {{pnode.name}}&nbsp;
    </li>
    <li class="list-group-item tool-bar" :class="{'hidden':glyphiconDown}">

      <div class="btn-group">
        <Button type="primary" @click="newDialog()"><Icon type="plus-circled"></Icon></Button>
        <Button type="info"  @click="editDialog()"><Icon type="edit"></Icon></Button>
        <Button type="error" @click="deleteNode()"><Icon type="trash-a"></Icon></Button>
      </div>

      &nbsp;
    </li>

    <ul>
      <div v-for="item in children" v-if="hasChild" class="children-box">
        <treeNode v-bind:isActive='item.active' :pnode="item"></treeNode>
      </div>
    </ul>
  </div>
</template>

<script>
  import Axios from 'axios';
  import Qs from 'qs';
  import Tools from '@/libs/Tool.js';
  import treeNode from './tNode.vue';

  const html_template = `
            <input type="hidden"  id="p_Id" value="{{id}}">
            <div class="input-group">
              <span class="input-group-addon">名称</span>
              <input type="text" class="form-control" id="p_Name" placeholder="菜单名称" aria-describedby="" value="{{name}}">
            </div>
            <div class="input-group">
              <span class="input-group-addon">排序</span>
              <input type="text" class="form-control" id="p_c_sortstring" placeholder="c_sortstring" aria-describedby="" value="{{sortstring}}">
            </div>
            <div class="input-group">
              <span class="input-group-addon">URL</span>
              <input type="text" class="form-control" id="p_Url" placeholder="访问路径" aria-describedby="" value="{{url}}">
            </div>
            <div class="input-group">
              <span class="input-group-addon" id="">标识</span>
              <input type="text" class="form-control" id="p_percode" placeholder="权限标识字符串" aria-describedby="" value="{{percode}}">
            </div>
            `;


  export default {
    name: 'treeNode',
    data () {
      return {
        'children': {},
        isOpen: false,
        'hasChild': false,
        'parentids': [],
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
        console.log('open');
        Axios.get(Tools.uslPre+'/access/user_and_permission/resourcesChildrenNode.do',
          Qs.stringify({pid: this.pnode.id,})
        ).then((response) => {
          let data = response.data;
          if (data.flag == true) {
            this.children = data.entity.children;

            this.hasChild = true;
          }
          this.active = true;
        }).catch((error) => {
          console.log(error);
        });
        this.isOpen = true;
        /* let list = [{'available':0,'id':2,'name':'1','parentid':1,'parentids':'[1]','percode':'1','rootPparentid':1,'sortstring':'1','url':'1'},{'available':0,'id':3,'name':'2','parentid':1,'parentids':'0,1','percode':'2','rootPparentid':1,'sortstring':'2','url':'2'},{'available':0,'id':4,'name':'3','parentid':1,'parentids':'0,1','percode':'3','rootPparentid':1,'sortstring':'3','type':'menu','url':'3'}];

         this.children = list;*/
      },
      close: function () {
        console.log('close');
        this.children = new Object;
        this.isOpen = false;
      },
      /**
       * 添加数据
       */
      newDialog: function () {
        /*layer.open({
          title: '新增功能',
          content: Tools.render(html_template,{}),
          'yes': function (index, layero) {
            Axios.post(Tools.uslPre+'/access/user_and_permission/permissionAdd.do',
              Qs.stringify({
                'name': $('#p_Name').val(),
                'percode': $('#p_percode').val(),
                'sortstring': $('#p_c_sortstring').val(),
                'url': $('#p_Url').val(),
                'parentid': this.thisVue.pnode.id,
              })
            ).then((response) => {
              let data = response.data;
              Tools.msg(data);
              this.thisVue.open();
            }).catch((error) => {
              console.log(error);
            });
            layer.close(index);
          },
          thisVue: this
        });*/
      },
      /**
       * 编辑数据
       */
      editDialog: function () {
        let _html = Tools.render(html_template,this.pnode);
        /*layer.open({
          title: '新增功能',
          content: _html,
          'yes': (index, layero)=>{
            Axios.post(Tools.uslPre+'/access/user_and_permission/permissionChangeData.do',
              Qs.stringify({
                'name': $('#p_Name').val(),
                'percode': $('#p_percode').val(),
                'sortstring': $('#p_c_sortstring').val(),
                'url': $('#p_Url').val(),
                'permissionId': this.pnode.id,
              })
            ).then((response) => {
              let data = response.data;
              Tools.msg(data);
              this.$parent.open();
            }).catch((error) => {
              console.log(error);
            });
            layer.close(index);
          },
          thisVue: this
        });*/
      },
      deleteNode:function () {

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

      }
    }
  };
</script>

<style scoped>
  .list-group-item {
    cursor: pointer;
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
  .tool-bar{
    border-bottom: 0px;
    border-left: 0px;
  }
  .children-box{
    background-color: #fff;
    border: 1px solid #ddd;
    border-radius: 4px;
  }
  .hidden{
    display: none;
  }
</style>

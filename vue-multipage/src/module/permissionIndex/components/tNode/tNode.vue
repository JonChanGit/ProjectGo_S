<template>
  <div>
    <li class="list-group-item" :class="{active:isOpen}" @click="clickMenu()">
    <span class="badge ">
      <span class="glyphicon"
            :class="{'glyphicon-chevron-down':glyphiconDown,'glyphicon-chevron-up':glyphiconUp}"></span>
    </span>
      <span class="glyphicon glyphicon-chevron-right" v-for="item in parentids" v-if="false"></span>
      {{pnode.name}}&nbsp;
    </li>
    <li class="list-group-item tool-bar" :class="{'hidden':glyphiconDown}">

      <div class="btn-group">
        <button type="button" class="btn btn-primary" @click="newDialog()"><span class="glyphicon glyphicon-plus"></span></button>
        <button type="button" class="btn btn-primary"><i class="fa fa-edit"></i></button>
        <button type="button" class="btn btn-danger" @click="deleteNode()"><span class="glyphicon glyphicon-trash"></span></button>
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
  /* global layer ,$*/
  import Axios from 'axios';
  import Qs from 'qs';
  import Tools from '@/components/Tool.js';
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
        layer.load(2, {time: 10*1000});
        Axios.post('/access/user_and_permission/permissionGetChildrenNode.do',
          Qs.stringify({pid: this.pnode.id,})
        ).then((response) => {
          let data = response.data;
          if (data.flag == true) {
            this.children = data.entity.children;

            this.hasChild = true;
          }
          this.active = true;
          layer.closeAll();
        }).catch((error) => {
          console.log(error);
          layer.closeAll();
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
        // let index =
        layer.open({
          title: '新增功能',
          content: html_template,
          'yes': function (index, layero) {
            Axios.post('/access/user_and_permission/permissionAdd.do',
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
        });
      },
      deleteNode:function () {

        layer.confirm('确定删除?', {icon: 3, title:'警告'}, (index)=>{
          console.log(this.pnode.id);
          Axios.post('/access/user_and_permission/permissionDelete.do',
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
        });

      }
    }
  };
</script>

<style scoped>
  .list-group-item {
    cursor: pointer;
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

</style>

<template>
  <div>
    <li class="list-group-item" :class="{active:isOpen}" @click="clickMenu()">
    <span class="badge ">
      <span class="glyphicon"
            :class="{'glyphicon-chevron-down':glyphiconDown,'glyphicon-chevron-up':glyphiconUp}"></span>
    </span>
      {{pnode.name}}&nbsp;
    </li>
    <li class="list-group-item" :class="{'hidden':glyphiconDown}">
      <div class="btn-group btn-group-sm" role="group" aria-label="...">
        <button class="btn btn-app" @click="newDialog()">
          <span class="glyphicon glyphicon-plus"></span>新增
        </button>
      </div>
      &nbsp;
    </li>


    <div v-for="item in children" v-if="hasChild" >
      <treeNode v-bind:isActive='item.active' :pnode="item"></treeNode>
    </div>

  </div>
</template>

<script>
  /* global layer ,$*/
  import Axios from 'axios';
  import Qs from 'qs';
  import Tools from '@/components/Tool.js';
  import treeNode from './tNode.vue';


  export default {
    name: 'treeNode',
    data () {
      return {
        'children': {},
        isOpen: false,
        'hasChild':false
      };
    },
    components: {
      'treeNode': treeNode
    },
    props: {
      'isActive': Boolean,
      'pnode': {}
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
      console.log(this.pnode);
      this.isOpen = this.isActive;
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
        Axios.post('/access/user_and_permission/permissionGetChildrenNode.do',
          Qs.stringify({pid: this.pnode.id,})
        ).then((response) => {
          let data = response.data;
          if (data.flag == true) {
            this.children = data.entity.children;

            // this.child = root;
          }
          this.active = true;
        }).catch((error) => {
          console.log(error);
        });
        this.isOpen = true;

        let list = [{'available':0,'id':2,'name':'1','parentid':1,'parentids':'0,1','percode':'1','rootPparentid':1,'sortstring':'1','url':'1'},{'available':0,'id':3,'name':'2','parentid':1,'parentids':'0,1','percode':'2','rootPparentid':1,'sortstring':'2','url':'2'},{'available':0,'id':4,'name':'3','parentid':1,'parentids':'0,1','percode':'3','rootPparentid':1,'sortstring':'3','type':'menu','url':'3'}];

        this.children = list;
        this.hasChild = true;

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
          content: `
            <div class="input-group">
              <span class="input-group-addon">名称</span>
              <input type="text" class="form-control" id="p_Name" placeholder="菜单名称" aria-describedby="">
            </div>
            <div class="input-group">
              <span class="input-group-addon">排序</span>
              <input type="text" class="form-control" id="p_c_sortstring" placeholder="c_sortstring" aria-describedby="">
            </div>
            <div class="input-group">
              <span class="input-group-addon">URL</span>
              <input type="text" class="form-control" id="p_Url" placeholder="访问路径" aria-describedby="">
            </div>
            <div class="input-group">
              <span class="input-group-addon" id="">标识</span>
              <input type="text" class="form-control" id="p_percode" placeholder="权限标识字符串" aria-describedby="">
            </div>
            `,
          'yes': function(index, layero){
            Axios.post('/access/user_and_permission/permissionAdd.do',
              Qs.stringify({
                'name': $('#p_Name').val(),
                'percode':$('#p_percode').val(),
                'sortstring':$('#p_c_sortstring').val(),
                'url':$('#p_Url').val(),
                'parentid':this.thisVue.pnode.id,
              })
            ).then((response) => {
              let data = response.data;
              Tools.msg(data);

            }).catch((error) => {
              console.log(error);
            });
            layer.close(index);
          },
          thisVue:this
        });
      }
    }
  };
</script>

<style scoped>
  .list-group-item {
    cursor: pointer;
  }

</style>

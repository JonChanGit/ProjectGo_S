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
  </div>
</template>

<script>
  /* global layer ,$*/
  import Axios from 'axios';
  import Qs from 'qs';
  import Tools from '@/components/Tool.js';


  export default {
    name: 'app',
    data () {
      return {
        'Children': {},
        isOpen: false,
      };
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
        if (this.isOpen) {
          this.open();
        } else {
          this.close();
        }
        this.isOpen = !this.isOpen;
      },
      open: function () {
        console.log('open');

      },
      close: function () {
        console.log('close');
      },
      newDialog: function () {
        let index = layer.open({
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

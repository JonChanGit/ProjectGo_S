<template>
  <ul class="list-group">
    <t-node v-bind:isActive='active' :pnode="child"></t-node>
  </ul>
</template>

<script>
  import Axios from 'axios';
  import Qs from 'qs';
  import tNode from '../tNode/tNode.vue';
  export default {
    name: 'app',
    components: {
      tNode: tNode
    },
    data () {
      return {
        treeMap: {},
        active: false,
        child: {}
      };
    },
    // props: ['active'],
    computed: {},
    created: function () {
      console.log('创建完成');
      Axios.post('/access/user_and_permission/permissionGetNode.do',
        Qs.stringify({pid: 0,})
      ).then((response) => {
        let data = response.data;
        if (data.flag == true) {
          let root = data.entity;
          this.child = root;

        }
        this.active = true;
      }).catch((error) => {
        console.log(error);
      });

      this.child = {'available':1,'id':1,'name':'Root','parentid':0,'parentids':'0','percode':'','rootPparentid':0,'sortstring':'0','type':'root','url':'#'};
    },
  };
</script>

<style scoped>

</style>

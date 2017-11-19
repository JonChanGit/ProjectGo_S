<template>
 <div>
   <el-tree :load="loadNode" show-checkbox lazy node-key="id" :default-expand-all="canChecked" :show-checkbox="canChecked" :props="defaultProps" ref="tree" @node-click="handleNodeClick"></el-tree>
   <el-button @click="text">test</el-button>
 </div>
</template>

<script>

  import Axios from 'axios';
  import Qs from 'qs';
  import Tool from '../../components/Tool';

  export default {
    name: 'tmp',
    components: {},
    data() {
      return {
        defaultProps: {
          label: 'name',
          children: 'zones',
          isLeaf: 'leaf',
        },
        value1: [1, 2],
        loading: true,
        canChecked:true,
      };
    },

    methods: {
      text() {
        let nodes = this.$refs.tree.getCheckedNodes(false);
        for(let item of nodes){
          let ids = item.parentids;
          let arr = ids.split(',');
          console.log(arr);
          console.log(item.id);
        }
      },
      handleNodeClick(data) {
      },
      loadNode: function (node, resolve) {
        let [queryObj,url] = [{},''];

        if (node.level === 0) {
          queryObj = {pid: 0,};
          url = Tool.uslPre + '/access/user_and_permission/permissionGetNode.do';
        } else {
          queryObj = {pid: node.data.id,};
          url = Tool.uslPre + '/access/user_and_permission/permissionGetChildrenNode.do';
        }

        Axios.post(url,
          Qs.stringify(queryObj)
        ).then((response) => {
          Tool.requestFeedback({
            response,
            successCallback: (data) => {
              if(node.level === 0){
                return resolve([data.entity]);
              }else{
                return resolve(data.entity.children);
              }
            }
          });
        }).catch((error) => {
          console.error(error);
        });

      }
    }
  };
</script>

<style scoped>
  .transfer-footer {
    margin: 0;
    padding: 0;
  }
</style>

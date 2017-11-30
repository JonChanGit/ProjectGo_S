<template>
  <div>
    <el-tree :load="loadNode" show-checkbox lazy
             node-key="id"
             :default-expand-all=true
             :show-checkbox="canChecked"
             :props="defaultProps"
             ref="tree"
             @check-change="getSelectIds"></el-tree>
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
        canChecked: false,
      };
    },

    methods: {
      getSelectIds() {
        let nodes = this.$refs.tree.getCheckedNodes(false);
        let resultArr = [];
        let map = Tool.map;
        console.log(map);
        map.clear();// 清空原来的Map
        for (let item of nodes) {
          let ids = item.parentids;
          let arr = ids.split(',');
          for (let ix of arr) {
            if (Number.isFinite(ix)) {
              map.put(ix, ix);
            } else {
              map.put(Number.parseInt(ix), Number.parseInt(ix));
            }
          }
          map.put(item.id, item.id);
        }
        let mapArr = map.getArr();
        for (let item of mapArr) {
          resultArr[resultArr.length] = item.key;
        }
        console.log(resultArr);
        this.$store.commit('setData',resultArr);
        return resultArr;
      },
      loadNode: function (node, resolve) {
        let [queryObj, url] = [{}, ''];

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
              if (node.level === 0) {
                return resolve([data.entity]);
              } else {
                return resolve(data.entity.children);
              }
            }
          });
        }).catch((error) => {
          console.error(error);
        });

      }
    },
    mounted(){
      if(window.roleId != null){
        Axios.post(Tool.uslPre+'/access/user_and_permission/assignGetRolePermissionInfo.do',
          Qs.stringify({roleId:window.roleId})
        ).then((response) => {
          Tool.requestFeedback({
            response,
            successCallback: (data) => {
             console.log(data);
             let keys = [];
             for(let item of data.entity){
               if(item.leaf){
                 keys[keys.length] = item.permissionId;
               }
             }
             this.$refs.tree.setCheckedKeys(keys,false);
            }
          });
        }).catch((error) => {
          console.error(error);
        });
        this.canChecked = true;
      }
    },
  };
</script>

<style scoped>
  .transfer-footer {
    margin: 0;
    padding: 0;
  }
</style>

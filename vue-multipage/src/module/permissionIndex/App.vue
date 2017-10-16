<template>
  <div id="app">
    <tree></tree>
  </div>
</template>

<script>
import tree from './components/tree/tree.vue';
import '@/assets/css/npmStyle.css';
// import Qs from 'qs';

export default {
  name: 'permissionIndex',
  components: {
    tree:tree
  },
  methods: {

  }
};
</script>

<style scoped>

</style>

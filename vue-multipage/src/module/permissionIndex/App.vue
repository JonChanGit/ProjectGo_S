<template>
  <div id="app">
    <tree></tree>
    <hr/>
    <div class="list-group">
      <a href="#" class="list-group-item active">
        Root
      </a>
      <a href="#" class="list-group-item">&nbsp;&nbsp;Level1-1</a>
      <a href="#" class="list-group-item">&nbsp;&nbsp;&nbsp;&nbsp;Level2</a>
      <a href="#" class="list-group-item">&nbsp;&nbsp;&nbsp;&nbsp;Level2</a>
      <a href="#" class="list-group-item">&nbsp;&nbsp;Level1-2</a>
      <a href="#" class="list-group-item">&nbsp;&nbsp;Level1-3</a>
      <a href="#" class="list-group-item">&nbsp;&nbsp;Level1-4</a>
    </div>
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

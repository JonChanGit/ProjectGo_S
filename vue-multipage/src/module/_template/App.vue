<template>
  <div id="app">
    <img src="../../assets/logo.png">

    <button class="btn btn-block btn-success" @click="openMessageBox()">Success</button>

    <hello></hello>
  </div>
</template>

<script>

import Hello from '@/components/HelloWorld.vue';

export default {
  name: 'tmp',
  components: {
    Hello
  },
  methods: {
    openMessageBox: function () {
      /* global layer */
      layer.msg('openMessageBox', {icon: 6});
    }
  }
};
</script>

<style scoped>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
</style>

<template>
  <div id="app">
    <img src="../../assets/logo.png">

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
      // sss
      console.log('openMessageBox!');
    }
  }
};
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
</style>

<template>
  <div id="app">
    <img src="./images/logo.png">
    <b-container class="bv-example-row">
      <b-row>
        <b-col>1 of 3</b-col>
        <b-col>2 of 3</b-col>
        <b-col>3 of 3</b-col>
      </b-row>
    </b-container>
    <div class="row">
      <template v-for="variant in ['primary','secondary','success','outline-success','warning','danger','link']">
        <div class="col-md-4 pb-2" v-for="size in ['sm','','lg']" :key="`{variant}_${size}`">
          <b-button :size="size" :variant="variant">
            {{variant}} {{size}}
          </b-button>
        </div>
      </template>
    </div>


    <b-button  variant="success" @click="openMessageBox()">
      lay
    </b-button>
    <br/>
    <br/>

    <div>
      <b-btn v-b-modal.modal1>Launch demo modal</b-btn>

      <!-- Modal Component -->
      <b-modal id="modal1" title="Bootstrap-Vue">
        <p clas="my-4">Hello from modal!</p>
      </b-modal>
    </div>

    <b-list-group>
      <b-list-group-item active>
        Awesome list
      </b-list-group-item>
      <b-list-group-item href="#">
        Action links are easy
      </b-list-group-item>
      <b-list-group-item>
        This is a text only item
      </b-list-group-item>
    </b-list-group>


    <br/>
    <br/>
    <hello></hello>
  </div>
</template>

<script>
import Hello from '@/components/HelloWorld.vue'

export default {
  name: 'app',
  components: {
    Hello
  },
  methods: {
    openMessageBox: function () {
      console.log('openMessageBox!')
    }
  }
}
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

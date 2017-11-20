import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

const state = {
  data :[],
};

const mutations={
  setData(state,data){
    state.data = data;
  },
};

const getters = {
  getData: state =>{
    return state.data;
  }
};

export default new Vuex.Store({
  state,mutations,getters
});

/**
 * Created by jon75 on 2017/10/15.
 */
/* global layer ,$, template*/
export default{
  uslPre: '/api',
  // uslPre:'',
  msg: function (responseData) {
    console.log(responseData);
    layer.msg(responseData.message);
  },
  /**
   * @param(Object) response Axios请求返回对象
   * @param(Function) successCallback 成功返回
   * @param(Object) option 回调参数
   */
  requestFeedback: function (obj) {
    console.log('requestFeedback : ');
    console.log(obj);
    let data = obj.response.data;
    if (data.flag === true) {
      if (obj.option == null) {
        obj.successCallback(data, obj.option);
      } else {
        obj.successCallback(obj.option);
      }
    } else {
      layer.msg(data.message);
    }
  },
  /**
   * 使用artTemplate渲染模板
   * https://aui.github.io/art-template/zh-cn/docs/api.html#template-filename-content
   * @param htmlTemplate 模板字符串
   * @param data 需要渲染的数据
   */
  render: function (htmlTemplate, data) {
    let render = template.compile(htmlTemplate);
    return render(data);// 渲染模板
  },
  /**
   * var map = new Map();
   map.put("re","redhacker");
   map.put("do","douguoqiang");
   map.put("gq","dougq");
   alert("map的大小为：" + map.size())
   alert("key为re的map中存储的对象为：" + map.get("re"));
   map.remove("re");
   alert("移除key为re的对象后，获取key为re的map中存储的对象为：" + map.get("re"));
   alert("map移除一个元素后的大小为：" + map.size());
   alert("map是否是一个空map:" + map.isEmpty());
   */
  map: {
    struct:function (key, value) {
      this.key = key;
      this.value = value;
    },
    put:function (key, value) {
      for (var i = 0; i < this.arr.length; i++) {
        if (this.arr[i].key === key) {
          this.arr[i].value = value;
          return;
        }
      }
      this.arr[this.arr.length] = new this.struct(key, value);
    },
    get :function (key) {
      for (var i = 0; i < this.arr.length; i++) {
        if (this.arr[i].key === key) {
          return this.arr[i].value;
        }
      }
      return null;
    },
    remove :function (key) {
      var v;
      for (var i = 0; i < this.arr.length; i++) {
        v = this.arr.pop();
        if (v.key === key) {
          continue;
        }
        this.arr.unshift(v);
      }
    },
    size : function () {
      return this.arr.length;
    },
    isEmpty : function () {
      return this.arr.length <= 0;
    },
    getArr: function(){
      return Array.from(this.arr);
    },
    arr: new Array(),
  }
};


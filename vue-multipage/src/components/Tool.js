/**
 * Created by jon75 on 2017/10/15.
 */
/* global layer ,$*/
export default{
  msg: function (responseData) {
    console.log(responseData);
    layer.msg(responseData.message);
  },
  /**
   * @param(Object) response Axios请求返回对象
   * @param(Function) successCallback 成功返回
   * @param(Object) option 回调参数
   */
  requestFeedback:function (obj) {
    console.log('requestFeedback : ');
    console.log(obj);
    let data = obj.response.data;
    if(data.flag === true){
      obj.successCallback(obj.option);
    }else{
      layer.msg(data.message);
    }
  }
};


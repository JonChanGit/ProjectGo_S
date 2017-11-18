/**
 * Created by jon75 on 2017/10/15.
 */
/* global layer ,$, template*/
export default{
  //uslPre:'/api',
  uslPre:'',
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
  },
  /**
   * 使用artTemplate渲染模板
   * https://aui.github.io/art-template/zh-cn/docs/api.html#template-filename-content
   * @param htmlTemplate 模板字符串
   * @param data 需要渲染的数据
   */
  render:function (htmlTemplate,data) {
    let render = template.compile(htmlTemplate);
    return render(data);//渲染模板
  }
};


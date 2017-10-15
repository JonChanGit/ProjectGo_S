/**
 * Created by jon75 on 2017/10/15.
 */
/* global layer ,$*/
export default{
  msg: function (responseData) {
    console.log(responseData);
    layer.msg(responseData.message);
  }
};


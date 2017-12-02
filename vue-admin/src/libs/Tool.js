/**
 * Created by jon75 on 2017/10/15.
 */
/* global layer ,$, template*/

import Axios from 'axios';
import Qs from 'qs';

/**
 * 分页基本信息
 * @type {{pageSize: number, currPage: number, totalPage: number, totalSize: number}}
 */
const pageInfo = {
	pageSize: 10,
	currPage: 1,
	totalPage: 1,
	totalSize: 0,
};

export default {
	uslPre: '/api',
	// uslPre:'',
	/**
	 * @param {Object} obj.response Axios请求返回对象
	 * @param {Function} obj.successCallback 成功返回
	 * @param {Object} obj.option 回调参数
	 * @param {Object} obj.iView iView实例 必须
	 */
	requestFeedback: function (obj) {
		let data = obj.response.data;
		if (data.flag === true) {
			if (obj.option == null) {
				obj.successCallback(data, obj.option);
			} else {
				obj.successCallback(obj.option);
			}
		} else {
			console.log(this);
			obj.iView.$Message.error(data.msg);
		}
	},

	/**
	 * 获取分页基本设置
	 */
	getPageInfo() {
		return JSON.parse(JSON.stringify(pageInfo));
	},

	/**
	 * post 请求(create)
	 * @param {String} obj.url 请求地址
	 * @param {Object} obj.data 请求参数
	 * @param {Function} obj.successCallback 成功返回
	 * @param {Object} obj.option 回调参数
	 * @param {Object} obj.iView iView实例 必须
	 */
	post: function (obj) {
		Axios.post(obj.url,
			Qs.stringify(obj.data == null ? {} : obj.data)
		).then((response) => {
			console.log(response);
			let data = response.data;
			if (data.flag === true) {
				if (obj.option == null) {
					obj.successCallback(data, obj.option);
				} else {
					obj.successCallback(data);
				}
			} else {
				console.log(this);
				obj.iView.$Message.error(data.message);
			}
		}).catch(function (error) {
			obj.iView.$Message.error(error);
		});

	},
	/**
	 * get 请求
	 * @param {String} obj.url 请求地址
	 * @param {Object} obj.data 请求参数
	 * @param {Function} obj.successCallback 成功返回
	 * @param {Object} obj.option 回调参数
	 * @param {Object} obj.iView iView实例 必须
	 */
	get: function (obj) {
		Axios.get(obj.url,
			Qs.stringify(obj.data == null ? {} : obj.data)
		).then((response) => {
			let data = response.data;
			if (data.flag === true) {
				if (obj.option == null) {
					obj.successCallback(data, obj.option);
				} else {
					obj.successCallback(data);
				}
			} else {
				console.log(this);
				obj.iView.$Message.error(data.message);
			}
		}).catch(function (error) {
			obj.iView.$Message.error(error);
		});

	},
	/**
	 * delete 请求
	 * @param {String} obj.url 请求地址
	 * @param {Object} obj.data 请求参数
	 * @param {Function} obj.successCallback 成功返回
	 * @param {Object} obj.option 回调参数
	 * @param {Object} obj.iView iView实例 必须
	 */
	delete: function (obj) {
		Axios.delete(obj.url,
			Qs.stringify(obj.data == null ? {} : obj.data)
		).then((response) => {
			let data = response.data;
			if (data.flag === true) {
				if (obj.option == null) {
					obj.successCallback(data, obj.option);
				} else {
					obj.successCallback(data);
				}
			} else {
				console.log(this);
				obj.iView.$Message.error(data.message);
			}
		}).catch(function (error) {
			obj.iView.$Message.error(error);
		});

	},
	/**
	 * put 请求(update)
	 * @param {String} obj.url 请求地址
	 * @param {Object} obj.data 请求参数
	 * @param {Function} obj.successCallback 成功返回
	 * @param {Object} obj.option 回调参数
	 * @param {Object} obj.iView iView实例 必须
	 */
	put: function (obj) {
		Axios.put(obj.url,
			Qs.stringify(obj.data == null ? {} : obj.data)
		).then((response) => {
			let data = response.data;
			if (data.flag === true) {
				if (obj.option == null) {
					obj.successCallback(data, obj.option);
				} else {
					obj.successCallback(data);
				}
			} else {
				console.log(this);
				obj.iView.$Message.error(data.message);
			}
		}).catch(function (error) {
			obj.iView.$Message.error(error);
		});

	},


	/**
	 * 使用artTemplate渲染模板
	 * https://aui.github.io/art-template/zh-cn/docs/api.html#template-filename-content
	 * @param {String} htmlTemplate 模板字符串
	 * @param {Object} data 需要渲染的数据
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
		struct: function (key, value) {
			this.key = key;
			this.value = value;
		},
		put: function (key, value) {
			for (var i = 0; i < this.arr.length; i++) {
				if (this.arr[i].key === key) {
					this.arr[i].value = value;
					return;
				}
			}
			this.arr[this.arr.length] = new this.struct(key, value);
		},
		get: function (key) {
			for (var i = 0; i < this.arr.length; i++) {
				if (this.arr[i].key === key) {
					return this.arr[i].value;
				}
			}
			return null;
		},
		remove: function (key) {
			var v;
			for (var i = 0; i < this.arr.length; i++) {
				v = this.arr.pop();
				if (v.key === key) {
					continue;
				}
				this.arr.unshift(v);
			}
		},
		size: function () {
			return this.arr.length;
		},
		isEmpty: function () {
			return this.arr.length <= 0;
		},
		getArr: function () {
			return Array.from(this.arr);
		},
		clear: function () {
			this.arr = new Array();
		},
		arr: new Array(),
	},
	getQueryString(name) {
		var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
		var r = window.location.search.substr(1).match(reg);
		if (r != null) return decodeURIComponent(r[2]);
		return null;
	}
};


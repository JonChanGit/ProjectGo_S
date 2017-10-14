# vue-cli多页面应用
> A Vue.js project

### 文章地址：
http://www.cnblogs.com/fengyuqing/p/vue_cli_webpack.html

### 代码地址：
Github：https://github.com/breezefeng/vue-cli-multipage

## Build Setup

``` bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build
```
#### 针对在webstorm里面不能hot reload的问题大家可以按如下步骤设置一下就行：
> Please try turning 'Safe write' option ( Settings | Appearance & Behavior | System Settings | Use "safe write" (save changes to temporary file first)) off

For detailed explanation on how things work, checkout the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).

### 目录结构

```
vue-cli-multipage
  |---build
  |---config
  |---src
    |---assets
      |---img 图片文件
      |---css 样式文件
      |---font 字体文件      
    |---components  组件
      |---Button.vue 按钮组件
      |---Hello.vue
    |---module
      |---index  首页模块
        |---index.html
        |---index.js
        |---App.vue
      |---detail  详情页模块
        |---detail.html
        |---detail.js
        |---App.vue
```

从目录结构上，各种组件、页面模块、资源等都按类新建了文件夹，方便我们储存文件。

其实我们所有的文件，最主要都是放在module文件夹里，以文件夹名为html的名称。

例如：
```
|---index  首页模块
  |---index.html
  |---index.js
  |---App.vue
```

此时我们访问的链接是：
[http://localhost:8080/module/index.html](http://localhost:8080/module/index.html)

这里一定要注意，在module里下级文件夹里需要将html，js，vue template 都统一放在当前文件夹里，当然你也可以继续放其他的资源，例如css、图片、组件等，webpack会打包到当前页面里。

如果项目不需要这个页面了，可以把这个文件夹直接删除掉，干净利落，干活也开心。

像以前传统的开发项目，所有的图片都习惯放在images里，当项目有改动时，有些图片等资源用不上了，但还占着坑位，导致项目越来越大，虽然现在的硬件容量大到惊人，但我们应该还是要养到一个良好的习惯。

### 组件的使用

组件（Component）是 vue.js 最强大的功能之一，当你发现使用组件可以减少造轮子里，你会深深的爱上它。

我们的组件都是放在components目录下的，调用组件的方法也很简单。

`import Hello from 'components/Hello'`

然后记得在*.vue注册导入的组件，要不然会无法使用。

```
import Hello from 'components/Hello'

export default {
  name: 'app',
  components: {
    //在这里注册组件，不然无法使用
    Hello
  }
}
```

### 构建代码说明

那我们使用的是vue-cli的手脚架，用过vue-cli的同学都知道构建代码是放在根目录build下，vue多页面主要修改了这三个JS文件：webpack.base.conf.js、webpack.dev.conf.js、webpack.prod.conf.js。

```
/**
**    [webpack.base.conf.js]这里主要列出相关代码的修改点，具体代码请看build/webpack.base.conf.js
*/
var entries = getEntry('./src/module/**/*.js'); // 获得入口js文件

module.exports = {
  entry: entries,
  ....
}

function getEntry(globPath) {
  var entries = {},
    basename, tmp, pathname;

  glob.sync(globPath).forEach(function (entry) {
    basename = path.basename(entry, path.extname(entry));
    tmp = entry.split('/').splice(-3);
    pathname = tmp.splice(0, 1) + '/' + basename; // 正确输出js和html的路径
    entries[pathname] = entry;
  });

  return entries;
}
```
```
/**
**    [webpack.prod.conf.js]这里主要列出相关代码的修改点，具体代码请看build/webpack.base.prod.js
*/
function getEntry(globPath) {
  var entries = {},
    basename, tmp, pathname;

  glob.sync(globPath).forEach(function (entry) {
    basename = path.basename(entry, path.extname(entry));
    tmp = entry.split('/').splice(-3);
    pathname = tmp.splice(0, 1) + '/' + basename; // 正确输出js和html的路径
    entries[pathname] = entry;
  });
  return entries;
}

var pages = getEntry('./src/module/**/*.html');

for (var pathname in pages) {
  // 配置生成的html文件，定义路径等
  var conf = {
    filename: pathname + '.html',
    template: pages[pathname],   // 模板路径
    inject: true,              // js插入位置
    minify: {
      //removeComments: true,
      //collapseWhitespace: true,
      //removeAttributeQuotes: true
    },
    // necessary to consistently work with multiple chunks via CommonsChunkPlugin
    chunksSortMode: 'dependency'
  };

  if (pathname in module.exports.entry) {
    conf.chunks = ['manifest', 'vendor', pathname];
    conf.hash = true;
  }

  module.exports.plugins.push(new HtmlWebpackPlugin(conf));
}
```

修改的代码不是很多，但是却为多页应用提供了强大的构建支持。欢迎大家使用，也希望大家多多交流。

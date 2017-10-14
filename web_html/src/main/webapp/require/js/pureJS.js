
/**
 * 纯JS文件，无需依赖JQuery插件
 */

/**
 * 动态加载JS与CSS文件 
 * @param filename 文件路径
 * @param filetype 文件类型
 * @returns
 */
function load_js_css_file(filename,filetype){

    if(filetype == "js"){
        var fileref = document.createElement('script');
        fileref.setAttribute("type","text/javascript");
        fileref.setAttribute("src",filename);
    }else if(filetype == "css"){
    
        var fileref = document.createElement('link');
        fileref.setAttribute("rel","stylesheet");
        fileref.setAttribute("type","text/css");
        fileref.setAttribute("href",filename);
    }
   if(typeof fileref != "undefined"){
        document.getElementsByTagName("head")[0].appendChild(fileref);
    }
    
}

/**
 * 判断是否是数字
 */
function IsNum(s){
    //S = s.trim();
     if(s!=null){
         var r,re;
         re = /\d*/i; //\d表示数字,*表示匹配多个数字
         r = s.match(re);
         return (r==s)?true:false;
     }
     return false;
 }



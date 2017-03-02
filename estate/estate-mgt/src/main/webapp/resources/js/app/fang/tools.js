/**
 * Created by robin on 17/3/2.
 */
define(contextPath+'/js/app/fang/tools.js',[],function(){
   var Tools ={};
   Tools.layoutFormat = function(options){
       return (options.sCounts ? options.sCounts + '室 ' : '') +
           (options.tCounts ? options.tCounts + '厅 ' : '') +
           (options.wCounts ? options.wCounts + '卫 ' : '') +
           (options.cCounts ? options.cCounts + '厨 ' : '') +
           (options.ytCounts ? options.ytCounts + '阳台 ' : '');
   };
    return Tools;
});
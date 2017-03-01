/**
 * Created by robin on 17/3/1.
 */
define(contextPath+'/js/service/xiaoqu-service.js',
    ['main-app', contextPath + '/js/service/request-service.js'], function(mainApp, RequestService) {

    var XiaoquService = {};
    XiaoquService.getXiaoquInfo = function(param, header){
      return RequestService.get('/api/xiao-qu/'+param.id);
    };

    return XiaoquService;
});

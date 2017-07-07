/**
 * Created by robin on 17/3/1.
 */
define(contextPath+'/js/service/xiaoqu-service.js',
    ['main-app', contextPath + '/js/service/request-service.js'], function(mainApp, RequestService) {
        var XiaoquService = {};
        XiaoquService.getXiaoquInfo = function (params, header) {
            return RequestService.get('/api/xiao-qu/' + params.id);
        };
        XiaoquService.getXiaoquDetail = function (params, header) {
            return RequestService.get('/api/xiao-qu/detail/' + params.id);
        };
        XiaoquService.list = function (params, header) {
            return RequestService.get('/api/xiao-qu/list', params, header);
        };
        XiaoquService.image = function (params, header) {
            return RequestService.get('/api/xiao-qu/image', params, header);
        };
        XiaoquService.imageUpload = function (params, header) {
            return RequestService.postMultipart('/api/xiao-qu/image', params, header);
        };
        XiaoquService.imageDelete = function (params, header) {
            return RequestService.post('/api/xiao-qu/delete-image', params, header);
        };
        XiaoquService.setFirstImage = function (params, header) {
            return RequestService.post('/api/xiao-qu/set-first-image', params, header);
        };
        XiaoquService.updateCommunity = function(params, header){
            return RequestService.put('/api/xiao-qu', params, header);
        };
        XiaoquService.createXiaoqu = function(params, header){
            return RequestService.post('/api/xiao-qu', params, header);
        };
    return XiaoquService;
});

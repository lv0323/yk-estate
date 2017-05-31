/**
 * Created by robin on 17/5/5.
 */
define(contextPath+'/js/service/fang-collect-service.js',
    ['main-app',contextPath + '/js/service/request-service.js'],
    function (mainApp,RequestService) {
        var FangCollectService = {};

        FangCollectService.list = function (params, header) {
            return RequestService.get('/api/fangCollect/list', params, header);
        };

        FangCollectService.detail = function (params, header) {
            return RequestService.get('/api/fangCollect/detail', params, header);
        };

        return FangCollectService;

    });
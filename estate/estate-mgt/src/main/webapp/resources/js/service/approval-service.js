/**
 * Created by robin on 17/6/5.
 */
define(contextPath+'/js/service/approval-service.js',
    ['main-app',contextPath + '/js/service/request-service.js'],
    function (mainApp,RequestService) {

        var ApprovalService = {};

        ApprovalService.create = function(params, header){
            return RequestService.post('/api/approval/create', params, header);
        };

        ApprovalService.approve = function(params, header){
            return RequestService.post('/api/approval/approve', params, header);
        };

        ApprovalService.list = function(params, header){
            return RequestService.get('/api/approval/list', params, header);
        };
        ApprovalService.myList = function(params, header){
            return RequestService.get('/api/approval/my', params, header);
        };

        return ApprovalService;
    });
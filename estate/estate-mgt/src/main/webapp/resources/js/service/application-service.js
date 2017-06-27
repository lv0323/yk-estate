
define(contextPath+'/js/service/application-service.js',
    ['main-app',contextPath + '/js/service/request-service.js'],
    function (mainApp,RequestService) {

        var ApplicationManagementService = {};

        ApplicationManagementService.findApplications = function (params, header) {
            return RequestService.get('/api/application/all', params, header);
        };

        ApplicationManagementService.approve = function (params) {
            return RequestService.put('/api/application/approve', params);
        };

        ApplicationManagementService.reject = function (params) {
            return RequestService.put('/api/application/reject', params);
        };

        ApplicationManagementService.close = function (params) {
            return RequestService.put('/api/application/close', params);
        };

        return ApplicationManagementService;
    });
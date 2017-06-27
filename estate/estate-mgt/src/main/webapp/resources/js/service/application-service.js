
define(contextPath+'/js/service/application-service.js',
    ['main-app',contextPath + '/js/service/request-service.js'],
    function (mainApp,RequestService) {

        var ApplicationManagementService = {};

        ApplicationManagementService.findApplications = function (params, header) {
            return RequestService.get('/api/application/all', params, header);
        };

        ApplicationManagementService.approve = function (params, header) {
            return RequestService.get('/api/application/approve', params, header);
        };

        ApplicationManagementService.reject = function (params, header) {
            return RequestService.get('/api/application/reject', params, header);
        };

        ApplicationManagementService.close = function (params, header) {
            return RequestService.get('/api/application/close', params, header);
        };

        return ApplicationManagementService;
    });
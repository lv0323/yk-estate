/**
 * Created by yanghong on 5/22/17.
 */
define(contextPath+'/js/service/createFranchisee-service.js',
    ['main-app',contextPath + '/js/service/request-service.js'],
    function (mainApp,RequestService) {

        var CreateService = {};

        CreateService.getParentCompany = function (param, header) {
            return RequestService.get('/api/company/parents', param, header);
        };

        CreateService.getParentCompanyDep = function (param, header) {
            return RequestService.get('/api/company/departments', param, header);
        };

        CreateService.getParentCompanyDepEmp = function (param, header) {
            return RequestService.get('/api/company/partAIds', param, header);
        };

        CreateService.createCompany = function (param, header) {
            return RequestService.post('/api/company/create', param, header);
        };

        return CreateService;

    });
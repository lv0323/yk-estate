/**
 * Created by yanghong on 5/22/17.
 */
define(contextPath+'/js/service/franchisee-service.js',
    ['main-app',contextPath + '/js/service/request-service.js'],
    function (mainApp,RequestService) {

        var FranchiseeService = {};

        FranchiseeService.getParentCompany = function (param, header) {
            return RequestService.get('/api/company/parents', param, header);
        };

        FranchiseeService.getParentCompanyDep = function (param, header) {
            return RequestService.get('/api/company/departments', param, header);
        };

        FranchiseeService.getParentCompanyDepEmp = function (param, header) {
            return RequestService.get('/api/company/partAIds', param, header);
        };

        FranchiseeService.createCompany = function (param, header) {
            return RequestService.post('/api/company/create', param, header);
        };

        FranchiseeService.listCompany = function (params,header) {
            return RequestService.get('/api/company/list',params,header);
        };

        return FranchiseeService;

    });
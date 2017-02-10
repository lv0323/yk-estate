/**
 * Created by yanghong on 1/23/17.
 */
define(contextPath+'/js/service/company-service.js',
    ['main-app',contextPath + '/js/service/request-service.js'],
    function (mainApp,RequestService) {

        var CompanyService = {};

        CompanyService.getCompany = function (header) {
            return RequestService.get('/api/company/query', null, header);
        };

        CompanyService.getSpecifiedCompany = function (companyId,header) {
            return RequestService.get('/api/company/'+companyId, header);
        };

        CompanyService.addCompany = function (params,header) {
            return RequestService.post('/api/company/create',params.data,header);
        };

        CompanyService.editCompany = function (params,header) {
            return RequestService.post('/api/company/edit',params.data,header);
        };

        CompanyService.toggleLockCompany = function (params,header) {
            return RequestService.get('/api/company/lock',params.data,header);
        };

        CompanyService.renewCompany = function (params,header) {
            return RequestService.get('/api/company/renew',params.data,header);
        };

        return CompanyService;
    });
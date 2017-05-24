/**
 * Created by yanghong on 1/23/17.
 */
define(contextPath+'/js/service/company-service.js',
    ['main-app',contextPath + '/js/service/request-service.js'],
    function (mainApp,RequestService) {

        var CompanyService = {};

        CompanyService.listCompany = function (params,header) {
            return RequestService.get('/api/company/list',params,header);
        };

        return CompanyService;
    });
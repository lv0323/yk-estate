/**
 * Created by yanghong on 1/23/17.
 */
define(contextPath+'/js/service/company-service.js', ['main-app',contextPath + '/js/service/request-service.js','datatables','datatablesBootstrap'],
    function (mainApp,RequestService) {

        var CompanyService = {};

        CompanyService.queryCompany = function (params) {
            return RequestService.get(params.url, null, params.header);
        };

        CompanyService.updatePostCompany = function (params) {
            return RequestService.post(params.url,params.data,params.header);
        };

        CompanyService.updateGetCompany = function (params) {
            return RequestService.get(params.url,params.data,params.header);
        }

        return CompanyService;
    });
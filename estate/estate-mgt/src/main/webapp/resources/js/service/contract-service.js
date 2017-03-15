/**
 * Created by yanghong on 3/13/17.
 */
define(contextPath + '/js/service/contract-service.js',
    ['main-app', contextPath + '/js/service/request-service.js'],
    function (mainApp, RequestService) {
        var ContractService = {};

        ContractService.getDealList = function (params, header) {
            return RequestService.get('/api/contract/list', params, header||{});
        };
        ContractService.closeDeal = function (params, header) {
            return RequestService.post('/api/contract/close', params, header||{});
        };
        ContractService.addDeal = function (params, header) {
            return RequestService.post('/api/contract/create', params, header||{});
        };
        return ContractService;
    });

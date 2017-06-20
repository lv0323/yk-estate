/**
 * Created by yanghong on 6/19/17.
 */
define(contextPath + '/js/service/credit-service.js',
    ['main-app', contextPath + '/js/service/request-service.js'],
    function (mainApp, RequestService) {

        var CreditService = {};

        CreditService.getAgentList = function (params, header) {
            return RequestService.get('/api/op/dianping/corps', params, header||{});
        };
        CreditService.getSpecifiedAgent = function (dealId, header) {
            return RequestService.get('/api/'+dealId, header);
        };
        CreditService.addAgent = function (params, header) {
            return RequestService.post('/api/op/dianping/corps', params, header||{});
        };

        return CreditService;

});
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
        CreditService.addAgent = function (params, header) {
            return RequestService.post('/api/op/dianping/corps', params, header||{});
        };
        CreditService.activateAgent = function (corpId, data, header) {
            return RequestService.post('/api/op/dianping/'+corpId+'/status/active', data||{}, header||{});
        };
        CreditService.suspendAgent = function (corpId, data, header) {
            return RequestService.post('/api/op/dianping/'+corpId+'/status/suspend', data||{}, header||{});
        };
        CreditService.rejectAgent = function (corpId, data, header) {
            return RequestService.post('/api/op/dianping/'+corpId+'/status/rejected', data||{}, header||{});
        };
        CreditService.getSpecifiedAgentBasicInfo = function (params, header) {
            return RequestService.get('/api/op/dianping/corp', params, header||{});
        };
        CreditService.updateSpecifiedAgentBasicInfo = function (corpId, params, header) {
            return RequestService.post('/api/op/dianping/'+corpId, params, header||{});
        };
        CreditService.getSpecifiedAgentComment = function (corpId, header) {
            return RequestService.get('/api/op/dianping/'+corpId+'/comments', {}, header);
        };
        CreditService.deleteSpecifiedAgentComment = function (corpId, commentId, header) {
            return RequestService.get('/api/op/dianping/'+corpId+'/comments/'+commentId, {}, header||{});
        };
        return CreditService;

});
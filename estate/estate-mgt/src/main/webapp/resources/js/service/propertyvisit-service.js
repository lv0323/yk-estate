/**
 * Created by yanghong on 3/6/17.
 */
define(contextPath + '/js/service/propertyvisit-service.js',
    ['main-app', contextPath + '/js/service/request-service.js'],
    function (mainApp, RequestService) {
        var PropertyVisitService = {};

        PropertyVisitService.getPropertyInfo = function (params, header) {
            return RequestService.get('/api/fang/tiny', params, header||{});
        };

        PropertyVisitService.getPropertyVisitList = function (params, header) {
            return RequestService.get('/api/showing/list', params, header||{});
        };

        PropertyVisitService.getPropertyVisitOperation = function (params, header) {
            return RequestService.get('/api/showing/close', params, header||{});
        };

        PropertyVisitService.addPropertyVisit = function (params, header) {
            return RequestService.post('/api/showing/create', params, header||{});
        };

        return PropertyVisitService;
    });
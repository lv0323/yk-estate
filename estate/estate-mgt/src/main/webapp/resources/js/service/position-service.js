/**
 * Created by yanghong on 2/7/17.
 */
define(contextPath + '/js/service/position-service.js',
    ['main-app', contextPath + '/js/service/request-service.js'],
    function (mainApp, RequestService) {

        var PositionService = {};

        PositionService.getPosition = function (header) {
            return RequestService.get('/api/position/query', null, header);
        };

        PositionService.addPosition = function (params, header) {
            return RequestService.post('/api/position/add', params.data, header);
        };

        PositionService.editPosition = function (params, header) {
            return RequestService.post('/api/position/edit', params.data, header);
        };

        PositionService.deletePosition = function (params, header) {
            return RequestService.get('/api/position/delete', params.data, header);
        };



        return PositionService;
    });
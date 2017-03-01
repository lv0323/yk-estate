/**
 * Created by robin on 17/2/24.
 */
define(contextPath+'/js/service/fang-service.js',
    ['main-app',contextPath + '/js/service/request-service.js'],
    function (mainApp,RequestService) {

        var FangService = {};

        FangService.xiaoquOption = function (params, header) {
            return RequestService.get('/xiao-qu-option', params, header);
        };
        FangService.buildings = function (params, header) {
            return RequestService.get('/buildings', params, header);
        };
        FangService.buildingInfo = function (params, header) {
            return RequestService.get('/buildings/'+ params.id, params, header);
        };
        FangService.buildingPost = function (params, header) {
            return RequestService.post('/building', params, header);
        };
        FangService.buildingUnit = function (params, header) {
            return RequestService.get('/building-unit', params, header);
        };
        FangService.buildingUnitPost = function (params, header) {
            return RequestService.post('/building-unit', params, header);
        };
        FangService.subType = function (params, header) {
            return RequestService.get('/fang/sub-types', params, header);
        };
        FangService.create = function (params, header) {
            return RequestService.post('/fang/create', params, header);
        };
        FangService.checkLicence = function (params, header) {
            return RequestService.post('/fang/pre-check-licence', params, header);
        };
        FangService.list = function (params, header) {
            return RequestService.get('/fang/list', params, header);
        };

        return FangService;
    });
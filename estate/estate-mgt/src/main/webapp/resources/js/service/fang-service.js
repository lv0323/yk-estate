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
        FangService.buildingUnit = function (params, header) {
            return RequestService.get('/building-unit', params, header);
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

        return FangService;
    });
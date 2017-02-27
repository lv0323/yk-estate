/**
 * Created by robin on 17/2/24.
 */
define(contextPath+'/js/service/house-service.js',
    ['main-app',contextPath + '/js/service/request-service.js'],
    function (mainApp,RequestService) {

        var HouseService = {};

        HouseService.xiaoquOption = function (params, header) {
            return RequestService.get('/xiao-qu-option', params, header);
        };
        HouseService.buildings = function (params, header) {
            return RequestService.get('/buildings', params, header);
        };
        HouseService.buildingUnit = function (params, header) {
            return RequestService.get('/building-unit', params, header);
        };
        HouseService.subType = function (params, header) {
            return RequestService.get('/subType', params, header);
        };

        return HouseService;
    });
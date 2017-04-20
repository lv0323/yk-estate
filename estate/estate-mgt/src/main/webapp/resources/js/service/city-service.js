/**
 * Created by yanghong on 1/23/17.
 */
define(contextPath+'/js/service/city-service.js',
    ['main-app',contextPath + '/js/service/request-service.js'],
    function (mainApp,RequestService) {

        var CityService = {};
        CityService.getCity = function (param, header) {
            return RequestService.get('/api/cities/', param, header);
        };
        CityService.getDistrict = function (params, header) {
            return RequestService.get('/api/cities/districts', params, header);
        };

        CityService.getSubDistrict = function (params, header) {
            return RequestService.get('/api/cities/sub-districts', params, header);
        };
        CityService.districtsWithSubs = function (params, header) {
            return RequestService.get('/api/cities/districts-with-subs', params, header);
        };
        
        return CityService;
    });
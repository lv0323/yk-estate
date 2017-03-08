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
        FangService.base = function (params, header) {
            return RequestService.get('/fang/base', params, header);
        };
        FangService.baseChange = function (params, header) {
            return RequestService.post('/fang/change-base', params, header);
        };
        FangService.summary = function (params, header) {
            return RequestService.get('/fang/summary', params, header);
        };
        FangService.ext = function (params, header) {
            return RequestService.get('/fang/ext', params, header);
        };
        FangService.extChange = function (params, header) {
            return RequestService.post('/fang/change-ext', params, header);
        };
        FangService.contact = function (params, header) {
            return RequestService.get('/fang/contact', params, header);
        };
        FangService.descr = function (params, header) {
            return RequestService.get('/fang/descr', params, header);
        };
        FangService.updateDescr = function (params, header) {
            return RequestService.post('/fang/descr', params, header);
        };
        FangService.infoOwner = function (params, header) {
            return RequestService.get('/fang/info-owner', params, header);
        };
        FangService.listFollow = function (params, header) {
            return RequestService.get('/fang/list-follow', params, header);
        };
        FangService.createFollow = function (params, header) {
            return RequestService.post('/fang/follow', params, header);
        };
        FangService.checkList = function (params, header) {
            return RequestService.get('/fang/list-check', params, header);
        };
        FangService.checkCreate = function (params, header) {
            return RequestService.post('/fang/check', params, header);
        };
        FangService.image = function (params, header) {
            return RequestService.get('/fang/image', params, header);
        };
        FangService.imageUpload = function (params, header) {
            return RequestService.postMultipart('/fang/image', params, header);
        };
        FangService.setFirstImage = function (params, header) {
            return RequestService.post('/fang/set-first-image', params, header);
        };
        FangService.deleteImage = function (params, header) {
            return RequestService.post('/fang/delete-image', params, header);
        };
        return FangService;
    });
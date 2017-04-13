/**
 * Created by robin on 17/2/24.
 */
define(contextPath+'/js/service/fang-service.js',
    ['main-app',contextPath + '/js/service/request-service.js'],
    function (mainApp,RequestService) {

        var FangService = {};

        FangService.xiaoquOption = function (params, header) {
            return RequestService.get('/api/house-dict/xiao-qu-option', params, header);
        };
        FangService.buildings = function (params, header) {
            return RequestService.get('/api/house-dict/buildings', params, header);
        };
        FangService.buildingInfo = function (params, header) {
            return RequestService.get('/api/house-dict/buildings/'+ params.id, params, header);
        };
        FangService.buildingPost = function (params, header) {
            return RequestService.post('/api/house-dict/building', params, header);
        };
        FangService.buildingUnit = function (params, header) {
            return RequestService.get('/api/house-dict/building-unit', params, header);
        };
        FangService.buildingUnitPost = function (params, header) {
            return RequestService.post('/api/house-dict/building-unit', params, header);
        };
        FangService.subType = function (params, header) {
            return RequestService.get('/api/fang/sub-types', params, header);
        };
        FangService.create = function (params, header) {
            return RequestService.post('/api/fang/create', params, header);
        };
        FangService.checkLicence = function (params, header) {
            return RequestService.post('/api/fang/pre-check-licence', params, header);
        };
        FangService.list = function (params, header) {
            return RequestService.get('/api/fang/list', params, header);
        };
        FangService.base = function (params, header) {
            return RequestService.get('/api/fang/base', params, header);
        };
        FangService.baseChange = function (params, header) {
            return RequestService.post('/api/fang/change-base', params, header);
        };
        FangService.summary = function (params, header) {
            return RequestService.get('/api/fang/summary', params, header);
        };
        FangService.ext = function (params, header) {
            return RequestService.get('/api/fang/ext', params, header);
        };
        FangService.extChange = function (params, header) {
            return RequestService.post('/api/fang/change-ext', params, header);
        };
        FangService.contact = function (params, header) {
            return RequestService.get('/api/fang/contact', params, header);
        };
        FangService.descr = function (params, header) {
            return RequestService.get('/api/fang/descr', params, header);
        };
        FangService.updateDescr = function (params, header) {
            return RequestService.post('/api/fang/descr', params, header);
        };
        FangService.successiveInfoOwner = function (params, header) {
            return RequestService.get('/api/fang/successive-info-owner', params, header);
        };
        FangService.listFollow = function (params, header) {
            return RequestService.get('/api/fang/list-follow', params, header);
        };
        FangService.createFollow = function (params, header) {
            return RequestService.post('/api/fang/follow', params, header);
        };
        FangService.checkList = function (params, header) {
            return RequestService.get('/api/fang/list-check', params, header);
        };
        FangService.checkCreate = function (params, header) {
            return RequestService.post('/api/fang/check', params, header);
        };
        FangService.image = function (params, header) {
            return RequestService.get('/api/fang/image', params, header);
        };
        FangService.imageUpload = function (params, header) {
            return RequestService.postMultipart('/api/fang/image', params, header);
        };
        FangService.setFirstImage = function (params, header) {
            return RequestService.post('/api/fang/set-first-image', params, header);
        };
        FangService.deleteImage = function (params, header) {
            return RequestService.post('/api/fang/delete-image', params, header);
        };
        FangService.contact = function (params, header) {
            return RequestService.get('/api/fang/contact', params, header);
        };
        FangService.contactChange = function (params, header) {
            return RequestService.post('/api/fang/change-contact', params, header);
        };
        FangService.publish = function (params, header) {
            return RequestService.post('/api/fang-process/publish', params, header);
        };
        FangService.unPublish = function (params, header) {
            return RequestService.post('/api/fang-process/un-publish', params, header);
        };
        FangService.rejectPublic = function (params, header) {
            return RequestService.post('/api/fang-process/reject-public', params, header);
        };
        FangService.applyPublic = function (params, header) {
            return RequestService.post('/api/fang-process/apply-public', params, header);
        };
        FangService.confirmPublic = function (params, header) {
            return RequestService.post('/api/fang-process/confirm-public', params, header);
        };
        FangService.undoPublic = function (params, header) {
            return RequestService.post('/api/fang-process/undo-public', params, header);
        };
        FangService.pause = function (params, header) {
            return RequestService.post('/api/fang-process/pause', params, header);
        };
        FangService.summaryByLicenceId = function (params, header) {
            return RequestService.get('/api/fang/summary-by-licence-id', params, header);
        };
        return FangService;
    });
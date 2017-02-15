/**
 * Created by yanghong on 2/7/17.
 */
define(contextPath + '/js/service/department-service.js',
    ['main-app', contextPath + '/js/service/request-service.js'],
    function (mainApp, RequestService) {

        var DepartmentService = {};
        
        DepartmentService.getSpecifiedDepartment = function (departId,header) {
            return RequestService.get('/api/department/'+departId, header);
        };

        DepartmentService.getDepartment = function (header) {
            return RequestService.get('/api/department/query', null, header);
        };

        DepartmentService.getAllDepartment = function (header) {
            return RequestService.get('/api/department/query-all', null, header);
        };

        DepartmentService.getCity = function (header) {
            return RequestService.get('/api/cities/', null, header);
        };

        DepartmentService.getDistrict = function (params, header) {
            return RequestService.get('/api/cities/districts', params.data, header);
        };

        DepartmentService.getSubDistrict = function (params, header) {
            return RequestService.get('/api/cities/sub-districts', params.data, header);
        };

        DepartmentService.addDepartment = function (params, header) {
            return RequestService.post('/api/department/add', params.data, header);
        };

        DepartmentService.editDepartment = function (params, header) {
            return RequestService.post('/api/department/edit', params.data, header);
        };

        DepartmentService.deleteDepartment = function (params, header) {
            return RequestService.get('/api/department/delete', params.data, header);
        };



        return DepartmentService;
    });
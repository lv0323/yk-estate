/**
 * Created by yanghong on 2/7/17.
 */
define(contextPath + '/js/service/department-service.js',
    ['main-app', contextPath + '/js/service/request-service.js'],
    function (mainApp, RequestService) {

        var DepartmentService = {};

        DepartmentService.iniDropListBySupId = function(url,superior_id,header,jqueryElementOuter,jqueryElementInner){
            RequestService.get(url,{id:superior_id},header)
                .done(function (data) {
                    var appendOption = '';
                    $.each(data, function (index, element) {
                        appendOption += '<option id="' + element.id + '">' + element.name + '</option>';

                    });
                    $('#'+jqueryElementOuter+' #'+jqueryElementInner).html(appendOption);
                });
        };

        DepartmentService.getDepartment = function (header) {
            return RequestService.get('/api/department/query-sorted', null, header);
        };

        DepartmentService.getCity = function (header) {
            return RequestService.get('/api/department/district/cites', null, header);
        };

        DepartmentService.getDistrict = function (params, header) {
            return RequestService.get('/api/department/district/districts', params.data, header);
        };

        DepartmentService.getSubDistrict = function (params, header) {
            return RequestService.get('/api/department/district/sub-districts', params.data, header);
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
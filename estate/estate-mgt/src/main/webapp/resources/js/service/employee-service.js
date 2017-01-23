define(contextPath + '/js/service/employee-service.js',
    ['main-app', contextPath + '/js/service/util-service.js', contextPath + '/js/service/request-service.js'],
    function (mainApp, utilService, requestService) {

        var EmployeeService = {};
        EmployeeService.getAvatar = function (params, header) {
            var data = {id: params.id};
            return requestService.get('/api/employee/avatar', data, header);
        };

        return EmployeeService;
    });

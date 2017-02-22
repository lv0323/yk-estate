define(contextPath + '/js/service/employee-service.js',
    ['main-app', contextPath + '/js/service/util-service.js',
        contextPath + '/js/service/department-service.js',
        contextPath + '/js/service/position-service.js',
        contextPath + '/js/service/request-service.js'],
    function (mainApp, utilService, DepartmentService, PositionService, requestService) {

        var EmployeeService = {
            quitEmployee:[],
            nonQuitEmployee:[],
            allEmployee:[]
        };

        EmployeeService.getAvatar = function (params, header) {
            var data = {id: params.id};
            return requestService.get('/api/employee/avatar', data, header);
        };

        EmployeeService.changePassword = function (params, header) {
            return requestService.post('/api/employee/change-password', params, header);
        };

        EmployeeService.getSaltSugar = function (header) {
            return requestService.get('/api/employee/change-password-salt-sugar', null, header);
        };

        EmployeeService.getEmployee = function (params, header) {
            return requestService.get('/api/employee/query', params, header);
        };

        EmployeeService.addEmployee = function (params, header) {
            return requestService.post('/api/employee/create', params.data, header);
        };

        EmployeeService.quitEmployeeAction = function (params, header) {
            return requestService.get('/api/employee/quit', params.data, header);
        };

        EmployeeService.editEmployee = function (params, header) {
            return requestService.post('/api/employee/edit', params.data, header);
        };

        return EmployeeService;
    });

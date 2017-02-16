define(contextPath + '/js/service/employee-service.js',
    ['main-app', contextPath + '/js/service/util-service.js',
        contextPath + '/js/service/department-service.js',
        contextPath + '/js/service/position-service.js',
        contextPath + '/js/service/request-service.js'],
    function (mainApp, utilService, DepartmentService, PositionService, requestService) {

        var EmployeeService = {
            quitEmployee:[],
            nonQuitEmployee:[]
        };

        EmployeeService.getAvatar = function (params, header) {
            var data = {id: params.id};
            return requestService.get('/api/employee/avatar', data, header);
        };

        EmployeeService.getEmployee = function (header) {
            return requestService.get('/api/employee/query', null, header);
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

        /*EmployeeService.getDepartment = function (header) {
            return DepartmentService.getDepartment(header);
        };

        EmployeeService.getPosition = function (header) {
            return PositionService.getPosition(header);
        };*/


        return EmployeeService;
    });

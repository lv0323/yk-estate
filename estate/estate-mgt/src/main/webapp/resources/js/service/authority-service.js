define(contextPath+'/js/service/authority-service.js',
    ['main-app',contextPath + '/js/service/request-service.js'],
    function (mainApp,RequestService) {

        var AuthorityService = {};

        AuthorityService.getGrants = function (params, header) {

            return RequestService.get('/api/grants', params, header);
        };

        AuthorityService.reGrant = function (params, header) {
            return RequestService.post('/api/grants/regrant', params, header);
        };

        AuthorityService.reGrantByPosition = function (params, header) {
            return RequestService.post('/api/grants/regrant-by-position', params, header);
        };

        AuthorityService.reGrantEmployeeByPosition = function (params, header) {
            return RequestService.post('/api/grants/regrant-employee-by-position', params, header);
        };

        return AuthorityService;
    });
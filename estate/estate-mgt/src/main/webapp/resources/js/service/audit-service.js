/**
 * Created by yanghong on 2/24/17.
 */
define(contextPath+'/js/service/audit-service.js',
    ['main-app',contextPath + '/js/service/request-service.js'],
    function (mainApp,RequestService) {

    var AuditService = {};
    
    AuditService.getAudit = function (params, header) {
        var data = {
            subject: params.subject,
            startTime: params.startTime,
            endTime: params.endTime
        };

        return RequestService.get('/', data, header);
    };

    return AuditService;
});
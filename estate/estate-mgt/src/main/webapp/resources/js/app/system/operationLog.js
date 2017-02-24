/**
 * Created by yanghong on 2/24/17.
 */
require(['main-app',
        contextPath + '/js/service/audit-service.js'],
    function (mainApp, AuditService) {

        AuditService.getAudit()

});
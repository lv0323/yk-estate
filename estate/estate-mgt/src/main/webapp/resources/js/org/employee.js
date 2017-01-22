/**
 * Created by yanghong on 1/17/17.
 */
require(['main-app',contextPath + '/js/service/request-service.js', 'submenu'],
    function (mainApp,RequestService) {
        $('[data-submenu]').dropdown();
        $('[data-submenu]').submenupicker();
});
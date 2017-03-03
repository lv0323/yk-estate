/**
 * Created by yanghong on 3/2/17.
 */
require(['main-app',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        'datatables', 'datatablesBootstrap'],
    function (mainApp, pagingPlugin) {

        var pageConfig = {
            limit: 8,
            init: false
        };

        var pagination = function(dataTotal) {
            if(pageConfig.init){
                return;
            }
            pageConfig.init = true;
            var config = {
                pagingId:'#departList_paging',
                totalCounts:dataTotal,
                pageSize: pageConfig.limit,
                onChange: function (num, type) {
                    getPropertyVisit((num-1)*pageConfig.limit, pageConfig.limit);
                }
            };
            pagingPlugin.init(config);
        };

        function getPropertyVisit(offset, limit) {

        }

    });
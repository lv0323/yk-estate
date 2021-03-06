/**
 * Created by yanghong on 2/14/17.
 */
define(contextPath+'/js/plugins/pagination/pagingPlugin.js',
    ['main-app','jqPaginator'],
    function (mainApp) {

        var pagingPlugin = {};

        pagingPlugin.init = function (config) {
            $(config.pagingId).jqPaginator({
                totalCounts: config.totalCounts||1,
                pageSize: config.pageSize || 10,
                visiblePages: config.visiblePages || 5,
                currentPage: config.currentPage || 1,
                onPageChange: config.onChange
            });

        };
        pagingPlugin.update = function(id, option){
            option.totalCounts = option.totalCounts || 1;
            $(id).jqPaginator('option', option);
        };
        return pagingPlugin;
});

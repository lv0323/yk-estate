/**
 * Created by yanghong on 2/24/17.
 */
require(['main-app',
        contextPath + '/js/service/audit-service.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        contextPath+'/js/service/util-service.js',
        'datatables', 'datatablesBootstrap', 'datepicker.zh-cn'],
    function (mainApp, AuditService, pagingPlugin, UtilService ) {

        var pageConfig = {
            limit: 8,
            init: false
        };

        var tableConfig ={
            init: false,
            target:null
        };

        var jqPaginatorInstance = false;

        $.fn.datepicker.defaults.language = "zh-CN";
        $('#logStartDate').datepicker({
            todayHighlight:true,
            autoclose: true
        });
        $('#logEndDate').datepicker({
            todayHighlight:true,
            autoclose: true
        });

        function displayOperationLog(data) {
            var dataSet = data.map(function (item, index) {
                return {
                    createTime: UtilService.timeStamp2Date(item.createTime),
                    departmentName: item.departmentName,
                    operatorName: item.operatorName,
                    content: item.content
                }
            });

            if (!tableConfig.target) {
                tableConfig.target = $('#operationLogList').DataTable({
                    data: dataSet,
                    paging: false,
                    searching: false,
                    info: false,
                    ordering: false,
                    autoWidth: false,
                    columns: [
                        {title: "操作时间", data: 'createTime'},
                        {title: "部门", data: 'departmentName', defaultContent: ""},
                        {title: "操作者", data: 'operatorName', defaultContent: ""},
                        {title: "操作内容", data: 'content', defaultContent: ""}
                    ]
                });
            } else {
                tableConfig.target.clear();
                tableConfig.target.rows.add(dataSet).draw();
            }
        }

        var pagination = function( params, dataTotal) {
            if(pageConfig.init){
                return;
            }
            jqPaginatorInstance = true;
            pageConfig.init = true;
            var config = {
                pagingId:'#operationLogList_paging',
                totalCounts:dataTotal,
                pageSize: pageConfig.limit,
                onChange: function (num, type) {
                    getAndDisplayOperationLog(params, (num-1)*pageConfig.limit, pageConfig.limit);
                }
            };
            pagingPlugin.init(config);

        };

        var getAndDisplayOperationLog = function (params, offset, limit) {
            AuditService.getAudit({subject:params.subject, startDate: params.startDate, endDate: params.endDate},{'x-paging': 'total=true&offset='+offset+'&limit=' + limit})
                .done(function (data) {
                    displayOperationLog(data.items);
                    if(data.total !== 0){
                        pagination(params, data.total);
                    }
                })
                .fail(function(){
                $('#operationLogList>tbody').append('<tr><td colspan="4">无法获取数据</td></tr>');
            });
        };

        function initTabTable(params) {
            pageConfig.init = false;
            if(jqPaginatorInstance){
                $('#operationLogList_paging').jqPaginator('destroy');
                jqPaginatorInstance = false;
            }
            getAndDisplayOperationLog(params, 0, pageConfig.limit);
        }

        function initPage() {
            $('.nav-stacked li:first').addClass('active');
            var subject = $('.nav-pills').find('li.active a').data('name');
            initTabTable({subject:subject});
        }

        initPage();

        $('#filterOperationLogBtn').on('click', function () {
            if($('#box-filter').css('display')=="none"){
                $('#box-filter').show();
            }else {
                $('#box-filter').hide();
            }
        });

        $('.nav-stacked').on('click', 'li', function () {
            $('.nav-stacked li').removeClass('active');
            $(this).addClass('active');
        });

        $('.nav-stacked').on('click', 'a', function (e) {
            var subject = $(e.target).data('name');
            $('#logStartDate').val("");
            $('#logEndDate').val("");
            initTabTable({subject:subject});
        });

        $('#confirmFilterOperationLogBtn').on('click',function () {
            var startDate = $('#logStartDate').val();
            var endDate = $('#logEndDate').val();
            var subject = $('.nav-pills').find('li.active a').data('name');
            initTabTable({subject:subject, startDate:startDate, endDate: endDate});
        });


});
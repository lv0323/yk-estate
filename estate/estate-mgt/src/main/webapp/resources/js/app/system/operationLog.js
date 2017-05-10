/**
 * Created by yanghong on 2/24/17.
 */
require(['main-app',
        contextPath + '/js/service/audit-service.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        contextPath+'/js/service/util-service.js',
        contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js',
        'datatables', 'datatablesBootstrap', 'datetimepicker.zh-cn'],
    function (mainApp, AuditService, pagingPlugin, UtilService, SweetAlertHelp ) {

        var pageConfig = {
            limit: 10,
            init: false
        };

        var logParams = {
            subject: null,
            startDate: null,
            endDate: null
        };

        var tableConfig ={
            init: false,
            target:null
        };

        $.fn.datetimepicker.defaults.language = "zh-CN";
        $('#logStartDate').datetimepicker({
            todayHighlight:true,
            format: 'yyyy-mm-dd',
            startView: 2,
            minView: 2,
            autoclose: true
        });
        $('#logEndDate').datetimepicker({
            todayHighlight:true,
            format: 'yyyy-mm-dd',
            startView: 2,
            minView: 2,
            autoclose: true
        });

        function displayOperationLog(data) {
            var dataSet = data.map(function (item, index) {
                return {
                    createTime: UtilService.timeStamp2Datetime(item.createTime),
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

        var pagination = function( dataTotal) {
            var id = '#operationLogList_paging';
            if(pageConfig.init){
                pagingPlugin.update(id, {
                    totalCounts:dataTotal,
                    currentPage:pageConfig.currentPage
                });
                return;
            }
            pageConfig.init = true;
            var config = {
                pagingId:'#operationLogList_paging',
                totalCounts:dataTotal,
                pageSize: pageConfig.limit,
                onChange: function (num, type) {
                    if(type === 'init'){
                        return;
                    }
                    pageConfig.currentPage = num;
                    getAndDisplayOperationLog(logParams, (num-1)*pageConfig.limit, pageConfig.limit, num);
                }
            };
            pagingPlugin.init(config);

        };

        var getAndDisplayOperationLog = function (logParams, offset, limit, currentpage) {
            if(!currentpage){
                pageConfig.currentPage = 1;
            }
            AuditService.getAudit({subject:logParams.subject, startDate: logParams.startDate, endDate: logParams.endDate},{'x-paging': 'total=true&offset='+offset+'&limit=' + limit})
                .done(function (data) {
                    displayOperationLog(data.items);
                    pagination(data.total);
                })
                .fail(function(res){
                $('#operationLogList>tbody').html('<tr><td colspan="4">无法获取数据</td></tr>');
                SweetAlertHelp.fail(res);
            });
        };

        function initPage() {
            $('.nav-stacked li:first').addClass('active');
            var subject = $('.nav-pills').find('li.active a').data('name');
            logParams = {
                subject:subject,
                startDate: null,
                endDate: null
            };
            getAndDisplayOperationLog(logParams, 0, pageConfig.limit);
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
            logParams = {
                subject:subject,
                startDate: null,
                endDate: null
            };
            getAndDisplayOperationLog(logParams, 0, pageConfig.limit);
        });

        $('#confirmFilterOperationLogBtn').on('click',function () {
            var startDate = $('#logStartDate').val();
            var endDate = $('#logEndDate').val();
            var subject = $('.nav-pills').find('li.active a').data('name');
            logParams = {
                subject:subject,
                startDate: startDate,
                endDate: endDate
            };
            getAndDisplayOperationLog(logParams, 0, pageConfig.limit);
            return false;
        });


});
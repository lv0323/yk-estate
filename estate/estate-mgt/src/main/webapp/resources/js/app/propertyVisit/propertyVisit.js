/**
 * Created by yanghong on 3/2/17.
 */
require(['main-app',
        contextPath + '/js/service/propertyvisit-service.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        'datepicker.zh-cn', 'sweetalert', 'datatables', 'datatablesBootstrap'],
    function (mainApp, PropertyVisitService, pagingPlugin) {

        var pageConfig = {
            limit: 8,
            init: false
        };

        var tableConfig ={
            init: false,
            target:null
        };

        $.fn.datepicker.defaults.language = "zh-CN";
        $('#minCreateDate').datepicker({
            todayHighlight:true,
            autoclose: true
        });
        $('#maxCreateDate').datepicker({
            todayHighlight:true,
            autoclose: true
        });

        var displayTable = function (data) {
            var dataSet = data.items.map(function (item, index) {
                return {
                   /* employeeName: item.,
                    customerName: item.,
                    propertyId: item.,
                    visitStartDate: item.,
                    visitEndDate: item.,
                    status: item.*/
                }
            });

            if (!tableConfig.target) {
                tableConfig.target = $('#propertyVisitList').DataTable({
                    data: dataSet,
                    paging: false,
                    searching: false,
                    info: false,
                    ordering: false,
                    autoWidth: false,
                    columnDefs: [
                        {className: "text-right", "targets": [5]} /*添加class*/
                    ],
                    columns: [
                        {title: "带看员工", data: 'employeeName'},
                        {title: "带看客户", data: 'customerName', defaultContent: ""},
                        {title: "带看房源编号", data: 'propertyId', defaultContent: ""},
                        {title: "带看生成时间", data: 'visitStartDate', defaultContent: ""},
                        {title: "带看结束时间", data: 'visitEndDate', defaultContent: ""},
                        {title: "状态", data: 'status', defaultContent: ""}
                    ]
                });
            } else {
                tableConfig.target.clear();
                tableConfig.target.rows.add(dataSet).draw();
            }
        };

        var pagination = function(dataTotal) {
            if(pageConfig.init){
                return;
            }
            pageConfig.init = true;
            var config = {
                pagingId:'#propertyVisitList_paging',
                totalCounts:dataTotal,
                pageSize: pageConfig.limit,
                onChange: function (num, type) {
                    if(type === 'init'){
                        return;
                    }
                    getPropertyVisit((num-1)*pageConfig.limit, pageConfig.limit);
                }
            };
            pagingPlugin.init(config);
        };

        function getPropertyVisit(offset, limit) {
            PropertyVisitService.getPropertyVisitList({'x-paging': 'total=true&offset='+offset+'&limit=' + limit})
                .done(function (data) {
                    displayTable(data);
                    pagination(data.total);
                })
                .fail(function(){
                    $('#propertyVisitList>tbody').append('<tr><td colspan="6">无法获取数据</td></tr>');
                });
        }

        getPropertyVisit(0, pageConfig.limit);

        //toggle filter for Employee display
        $('#filterPropertyVisitBtn').on('click',function () {
            if($('#box-filter').css('display')=="none"){
                $('#box-filter').show();
            }else {
                $('#box-filter').hide();
            }
        });


    });
/**
 * Created by yanghong on 3/2/17.
 */
require(['main-app',
        contextPath + '/js/service/propertyvisit-service.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        contextPath+'/js/service/util-service.js',
        contextPath + '/js/utils/dataTableHelp.js',
        contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js',
        'datepicker.zh-cn', 'datatables', 'datatablesBootstrap'],
    function (mainApp, PropertyVisitService, pagingPlugin, UtilService, dataTableHelp, SweetAlertHelp) {

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
                    employee: "<img class='img-circle' style='width:42px; height:42px; display: inline-block;' src='"+item.avatarURI+"'/>"+"&nbsp;"+item.departmentName+"-"+item.employeeName,
                    customerName: item.customerTiny.name,
                    propertyAddress: item.fangTiny.houseLicence.location,
                    visitStartDate: UtilService.timeStamp2Datetime(item.createTime),
                    visitUpdDate: UtilService.timeStamp2Datetime(item.updateTime),
                    status: (item.process.name==="CREATED")?"<label class='badge badge-info'>"+item.process.label+"</label>":(item.process.name==="SUCCESS")?"<label class='badge badge-success'>"+item.process.label+"</label>":"<label class='badge badge-warning'>"+item.process.label+"</label>",
                    operation:(item.process.name==="CREATED")?
                        [{attr: {class: 'btn completeVisitBtn'}, data: {index: index, id: item.id, toggle: 'modal', target:'#completeVisitDialog'}, text: '完成'},
                            {attr: {class: 'btn cancelVisitBtn'}, data: {index: index, id: item.id, toggle: 'modal', target:'#cancelVisitDialog'}, text: '取消'}
                        ]:""
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
                        {className: "text-right", "targets": [6]} /*添加class*/
                    ],
                    columns: [
                        {title: "带看员工", data: 'employee'},
                        {title: "带看客户", data: 'customerName', defaultContent: ""},
                        {title: "带看房源地址", data: 'propertyAddress', defaultContent: ""},
                        {title: "带看生成时间", data: 'visitStartDate', defaultContent: ""},
                        {title: "带看更新时间", data: 'visitUpdDate', defaultContent: ""},
                        {title: "状态", data: 'status', defaultContent: ""},
                        {title: "操作", data:'operation', "render": dataTableHelp.operationFormat()}
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

        function getPropertyVisit(params, offset, limit) {
            PropertyVisitService.getPropertyVisitList(params, {'x-paging': 'total=true&offset='+offset+'&limit=' + limit})
                .done(function (data) {
                    displayTable(data);
                    pagination(data.total);
                })
                .fail(function(){
                    $('#propertyVisitList>tbody').append('<tr><td colspan="6">无法获取数据</td></tr>');
                });
        }

        getPropertyVisit(null, 0, pageConfig.limit);

        //toggle filter for Employee display
        $('#filterPropertyVisitBtn').on('click',function () {
            if($('#box-filter').css('display')=="none"){
                $('#box-filter').show();
            }else {
                $('#box-filter').hide();
            }
        });

        //initialize complete visit dialog
        $('.fadeInRight').on('click','.completeVisitBtn',function(e){
            var visitId = $(e.target).data('id');
            $('#completeVisitId').val(visitId);
        });

        //action for complete visit
        $('#confirmCompleteVisitBtn').on('click', function(){
            var visitId = $('#completeVisitId').val();
            var toUpdateData = {
                showingId: visitId,
                process: 'SUCCESS'
            };

            PropertyVisitService.closePropertyVisit(toUpdateData)
                .done(function () {
                    SweetAlertHelp.success({}, function () {
                        location.reload(true);
                    });
                })
                .fail(function (res) {
                    SweetAlertHelp.fail(res);
                });
        });

        //initialize cancel visit dialog
        $('.fadeInRight').on('click','.cancelVisitBtn',function(e){
            var visitId = $(e.target).data('id');
            $('#cancelVisitId').val(visitId);
        });

        //action for cancel visit
        $('#confirmCancelVisitBtn').on('click', function(){
            var visitId = $('#cancelVisitId').val();
            var toUpdateData = {
                showingId: visitId,
                process: 'CANCEL'
            };

            PropertyVisitService.closePropertyVisit(toUpdateData)
                .done(function () {
                    SweetAlertHelp.success({}, function () {
                        location.reload(true);
                    });

                })
                .fail(function (res) {
                    SweetAlertHelp.fail(res);
                });
        });


    });
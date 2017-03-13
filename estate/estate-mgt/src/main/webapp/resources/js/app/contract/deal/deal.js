/**
 * Created by yanghong on 3/13/17.
 */
require(['main-app',
        contextPath + '/js/service/contract-service.js',
        contextPath + '/js/service/department-service.js',
        contextPath + '/js/service/employee-service.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        contextPath+'/js/service/util-service.js',
        contextPath + '/js/utils/dataTableHelp.js',
        contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js',
        'datetimepicker.zh-cn', 'chosen', 'datatables', 'datatablesBootstrap'],
    function (mainApp, ContractService, DepartmentService, EmployeeService, pagingPlugin, UtilService, dataTableHelp, SweetAlertHelp) {

        var pageConfig = {
            limit: 8,
            currentPage: 1,
            init: false
        };

        var tableConfig = {
            init: false,
            target: null
        };

        var filterConfig = {
            departmentId: {
                init: false
            },
            employeeId: {
                init: false
            }
        };

        var filter = {
            process: '',
            departmentId: '',
            children: false,
            employeeId: '',
            minCreateDate: '',
            maxCreateDate: ''
        };

        $.fn.datetimepicker.defaults.language = "zh-CN";
        $('#minCreateDate').datetimepicker({
            todayHighlight: true,
            format: 'yyyy-mm-dd',
            startView: 2,
            minView: 2,
            autoclose: true
        }).on("change", function (e) {
            filter.minCreateDate = e.target.value;
            getDeal(filter, 0, pageConfig.limit);

        });
        $('#maxCreateDate').datetimepicker({
            todayHighlight: true,
            format: 'yyyy-mm-dd',
            startView: 2,
            minView: 2,
            autoclose: true
        }).on("change", function (e) {
            filter.maxCreateDate = e.target.value;
            getDeal(filter, 0, pageConfig.limit);
        });

        var displayTable = function (data) {
            var dataSet = data.items.map(function (item, index) {
                return {}
            });

            if (!tableConfig.target) {
                tableConfig.target = $('#DealList').DataTable({
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
                        {title: "成交员工", data: 'employee'},
                        {title: "成交客户", data: 'customerName', defaultContent: ""},
                        {title: "成交房源地址", data: 'propertyAddress', defaultContent: ""},
                        {title: "成交创建时间", data: 'visitStartDate', defaultContent: ""},
                        {title: "成交结束时间", data: 'visitCloseDate', defaultContent: ""},
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
            var id = "#propertyVisitList_paging";
            if(pageConfig.init){
                pagingPlugin.update(id, {
                    totalCounts:dataTotal,
                    currentPage:pageConfig.currentPage
                });
                return;
            }
            pageConfig.init = true;
            var config = {
                pagingId:'#DealList_paging',
                totalCounts:dataTotal,
                pageSize: pageConfig.limit,
                onChange: function (num, type) {
                    if(type === 'init'){
                        return;
                    }
                    pageConfig.currentPage = num;
                    getDeal(filter, (num-1)*pageConfig.limit, pageConfig.limit);
                }
            };
            pagingPlugin.init(config);
        };

        function getDeal(filter, offset, limit) {
            var params = {};
            for (var key in filter){
                if(!!filter[key]){
                    params[key] = filter[key];
                }
            }
            ContractService.getDealList(params, {'x-paging': 'total=true&offset='+offset+'&limit=' + limit})
                .done(function (data) {
                    displayTable(data);
                    pagination(data.total);
                })
                .fail(function(){
                    $('#DealList>tbody').html('<tr><td colspan="7">无法获取数据</td></tr>');
                });
        }

        getDeal(null, 0, pageConfig.limit);




        function iniEmployeeDropDown(value) {
            var empListOption = "";
            var defaultOption = [];
            defaultOption.push("<option value=''>全部员工</option>");
            if(value.length>0){
                EmployeeService.getAllEmployee({departmentId: value}).done(function (response) {
                    if(response && response && response.length>0){
                        empListOption = response.map(function(item){
                            return "<option value='"+item.id+"'>"+item.name+"</option>";
                        });
                    }
                    var fullEmpListOption = defaultOption.concat(empListOption);
                    $('#employeeList').html(fullEmpListOption);
                    initChosen("#employeeList", 'employeeId');

                }).fail(function(){
                    $('#employeeList').html(defaultOption);
                    initChosen("#employeeList", 'employeeId');
                });
            }else {
                $('#employeeList').html(defaultOption);
                initChosen("#employeeList", 'employeeId');
            }

        }

        function chosenChange(key, value){
            if(key === 'departmentId'){
                iniEmployeeDropDown(value);
            }
        }

        function initChosen(id, key){
            $(id).chosen("destroy");
            if(!filterConfig[key].init){
                filterConfig[key].init = !filterConfig[key].init;
                $(id).chosen().change(function(e, result){
                    filter[key] = result.selected;
                    chosenChange(key, result.selected);
                    if(key === 'departmentId'){
                        filter.employeeId = "";
                    }
                    getDeal(filter, 0, pageConfig.limit);

                });
                return;
            }
            $(id).chosen();
            $(id).trigger('chosen:updated');
        }

        /*init depListOption in filter*/
        function initDepartDropDown() {
            var depListOption = "";
            DepartmentService.getAllDepartment().done(function(data){
                depListOption =data.map(function(item, index){
                    var indent = "";
                    for(var i = 0;i<item.level;i++){
                        indent += "&nbsp;";
                    }
                    return "<option value='"+item.id+"'>"+indent+ item.name+"</option>";
                });
                $('#departmentList').append(depListOption);
                initChosen("#departmentList", 'departmentId');

            });
        }

        initChosen("#employeeList", 'employeeId');
        initDepartDropDown();

        //toggle filter for Employee display
        $('#filterDealBtn').on('click',function () {
            if($('#box-filter').css('display')=="none"){
                $('#box-filter').show();
            }else {
                $('#box-filter').hide();
            }
        });

        $('a[name="DealStatus"], a[name="houseType"], a[name="businessType"]').on('click', function () {
            $(this).siblings().removeClass("actived");
            $(this).addClass("actived");
            filter.process = $(this).attr("title");
            getDeal(filter, 0, pageConfig.limit);
        });

        $('#inferiorIncLabel').on('click', function () {
            filter.children=!($('#inferiorInc').is(':checked'));
            getDeal(filter, 0, pageConfig.limit);
        });

        //initialize complete visit dialog
        $('.fadeInRight').on('click','.completeDealBtn',function(e){
            var contractId = $(e.target).data('id');
            $('#completeDealId').val(contractId);
        });

        //action for complete visit
        $('#confirmCompleteDealBtn').on('click', function(){
            var contractId = $('#completeDealId').val();
            var toUpdateData = {
                contractId: contractId,
                process: 'SUCCESS'
            };

            ContractService.closeDeal(toUpdateData)
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
        $('.fadeInRight').on('click','.cancelDealBtn',function(e){
            var contractId = $(e.target).data('id');
            $('#cancelDealId').val(contractId);
        });

        //action for cancel visit
        $('#confirmCancelDealBtn').on('click', function(){
            var contractId = $('#cancelDealId').val();
            var toUpdateData = {
                contractId: contractId,
                process: 'CANCEL'
            };

            ContractService.closeDeal(toUpdateData)
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
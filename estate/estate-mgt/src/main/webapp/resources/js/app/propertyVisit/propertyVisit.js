/**
 * Created by yanghong on 3/2/17.
 */
require(['main-app',
        contextPath + '/js/service/propertyvisit-service.js',
        contextPath + '/js/service/department-service.js',
        contextPath + '/js/service/employee-service.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        contextPath+'/js/service/util-service.js',
        contextPath + '/js/utils/dataTableHelp.js',
        contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js',
        'datetimepicker.zh-cn', 'chosen', 'datatables', 'datatablesBootstrap'],
    function (mainApp, PropertyVisitService, DepartmentService, EmployeeService, pagingPlugin, UtilService, dataTableHelp, SweetAlertHelp) {

        var pageConfig = {
            limit: 8,
            currentPage:1,
            init: false
        };

        var tableConfig ={
            init: false,
            target:null
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
            process:'',
            departmentId: '',
            children: false,
            employeeId:'',
            minCreateDate:'',
            maxCreateDate:''
        };

        $.fn.datetimepicker.defaults.language = "zh-CN";
        $('#minCreateDate').datetimepicker({
            todayHighlight:true,
            format: 'yyyy-mm-dd',
            startView: 2,
            minView: 2,
            autoclose: true
        }).on("change", function(e) {
            filter.minCreateDate = e.target.value;
            getPropertyVisit(filter, 0, pageConfig.limit);

        });
        $('#maxCreateDate').datetimepicker({
            todayHighlight:true,
            format: 'yyyy-mm-dd',
            startView: 2,
            minView: 2,
            autoclose: true
        }).on("change", function(e) {
            filter.maxCreateDate = e.target.value;
            getPropertyVisit(filter, 0, pageConfig.limit);
        });

        var displayTable = function (data) {
            var dataSet = data.items.map(function (item, index) {
                return {
                    employee: "<div class='img-circle-container-sm'><img style='width:42px; display: inline-block;' src='" + item.avatarURI + "'/></div>" + "&nbsp;" + item.departmentName + "-" + item.employeeName,
                    customerName: item.customerTiny.name,
                    propertyAddress: "<span>"+item.fangTiny.houseLicence.location+"</span><br><span>授权编号:"+item.fangTiny.houseLicence.id+"</span>",
                    visitStartDate: UtilService.timeStamp2DatetimeMin(item.createTime),
                    visitCloseDate: (item.closeTime) ? UtilService.timeStamp2DatetimeMin(item.closeTime) : "",
                    status: (item.process.name === "CREATED") ? "<label class='badge badge-info'>" + item.process.label + "</label>" : (item.process.name === "SUCCESS") ? "<label class='badge badge-success'>" + item.process.label + "</label>" : "<label class='badge badge-warning'>" + item.process.label + "</label>",
                    operation: (item.process.name === "CREATED") ?
                        [{
                            attr: {class: 'btn completeVisitBtn'},
                            data: {index: index, id: item.id, toggle: 'modal', target: '#completeVisitDialog'},
                            text: '完成'
                        },
                            {
                                attr: {class: 'btn cancelVisitBtn'},
                                data: {index: index, id: item.id, toggle: 'modal', target: '#cancelVisitDialog'},
                                text: '取消'
                            }
                        ] : ""
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
                        {title: "员工", data: 'employee'},
                        {title: "客户", data: 'customerName', defaultContent: ""},
                        {title: "房源地址", data: 'propertyAddress', defaultContent: ""},
                        {title: "生成时间", data: 'visitStartDate', defaultContent: ""},
                        {title: "结束时间", data: 'visitCloseDate', defaultContent: ""},
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
                pagingId:'#propertyVisitList_paging',
                totalCounts:dataTotal,
                pageSize: pageConfig.limit,
                onChange: function (num, type) {
                    if(type === 'init'){
                        return;
                    }
                    pageConfig.currentPage = num;
                    getPropertyVisit(filter, (num-1)*pageConfig.limit, pageConfig.limit, num);
                }
            };
            pagingPlugin.init(config);
        };

        function getPropertyVisit(filter, offset, limit, currentpage) {
            var params = {};
            for (var key in filter){
                if(!!filter[key]){
                    params[key] = filter[key];
                }
            }
            if(!currentpage){
                pageConfig.currentPage = 1;
            }
            PropertyVisitService.getPropertyVisitList(params, {'x-paging': 'total=true&offset='+offset+'&limit=' + limit})
                .done(function (data) {
                    displayTable(data);
                    pagination(data.total);
                })
                .fail(function(){
                    $('#propertyVisitList>tbody').html('<tr><td colspan="7">无法获取数据</td></tr>');
                });
        }

        getPropertyVisit(null, 0, pageConfig.limit);




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
                    getPropertyVisit(filter, 0, pageConfig.limit);

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
        $('#filterPropertyVisitBtn').on('click',function () {
            if($('#box-filter').css('display')=="none"){
                $('#box-filter').show();
            }else {
                $('#box-filter').hide();
            }
        });

        $('a[name="visitStatus"]').on('click', function () {
            $('a[name="visitStatus"]').removeClass("actived");
            $(this).addClass("actived");
            filter.process = $(this).attr("title");
            getPropertyVisit(filter, 0, pageConfig.limit);
        });

        $('#inferiorIncLabel').on('click', function () {
            filter.children=!($('#inferiorInc').is(':checked'));
            getPropertyVisit(filter, 0, pageConfig.limit);
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
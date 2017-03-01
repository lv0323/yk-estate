/**
 * Created by yanghong on 1/17/17.
 */
require(['main-app',contextPath + '/js/service/employee-service.js',
        contextPath + '/js/service/department-service.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        contextPath+'/js/utils/dataTableHelp.js',
        contextPath+'/js/app/org/department/departCommon.js',
        contextPath+'/js/app/org/position/positionCommon.js',
        contextPath+'/js/service/util-service.js',
    'datatables', 'zTree','datatablesBootstrap', 'datepicker.zh-cn','sweetalert'],
    function (mainApp, EmployeeService, DepartmentService, pagingPlugin, dataTableHelp, DepartCommon, PositionCommon, UtilService) {
        var header = {};

        var employeeAllDataRaw = {};

        var pageConfig = {
            limit: 8,
            init: false
        };

        var tableConfig ={
            init: false,
            target:null
        };

        var quitPosition = 'false'; //init nonQuit Employee by default

        $.fn.datepicker.defaults.language = "zh-CN";
        $('#addEmployeeEntryDate').datepicker({
            todayHighlight:true,
            autoclose: true
        });
        $('#editEmployeeEntryDate').datepicker({
            todayHighlight:true,
            autoclose: true
        });

       /* 部门树*/
        function departmentTree(data){
            function beforeClick(treeId, treeNode, clickFlag) {}
            function onClick(event, treeId, treeNode, clickFlag) {
                EmployeeService.allEmployee = [];
                $('#quitPosition').find('option[value="-1"]').attr('selected','selected');
                $('#quitPosition').val("-1");
                EmployeeService.getEmployee({departmentId:treeNode.id},{'x-paging':'total=true&offset=0&limit=10'})
                    .done(function (data) {
                        $.each(data.items,function (index, employee) {
                            EmployeeService.allEmployee.push(employee);
                        });
                        displayFilteredEmployee(EmployeeService.allEmployee);
                        pagination(data.total);
                    });
                //console.log('点击了:'+ treeNode.id + treeNode.name);
            }
            var zTreeSetting = {
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback: {
                    beforeClick: beforeClick,
                    onClick: onClick
                }
            };
            var departments = data.map(function(item){
                return {
                    name:item.name,
                    id:item.id,
                    pId:item.parentId};
            })
            $.fn.zTree.init($("#departmentTree"), zTreeSetting, departments);
        }

       /* end部门树*/

        var displayFilteredEmployee = function (data) {
            employeeAllDataRaw = data;
            var dataSet = data.map(function (item, index) {
                if(!item.quit){
                    return {
                        employeeName: item.name,
                        departmentName: item.departmentName,
                        positionName: item.positionName,
                        mobile: item.mobile,
                        operation: [
                            {
                                attr: {class: 'btn editEmployeeBtn'},
                                data: {index: index, id: item.id, toggle: 'modal', target: '#editEmployeeDialog'},
                                text: '编辑'
                            },
                            {
                                attr: {class: 'btn quitEmployeeBtn'},
                                data: {index: index, id: item.id, toggle: 'modal', target: '#quitEmployeeDialog'},
                                text: '离职'
                            }]
                    }
                }else {
                    return {
                        employeeName: item.name,
                        departmentName: item.departmentName,
                        positionName: item.positionName,
                        mobile: item.mobile,
                        operation: '已离职'
                    }
                }
            });

            if (!tableConfig.target) {
                tableConfig.target = $('#employeeList').DataTable({
                    data: dataSet,
                    paging: false,
                    searching: false,
                    info: false,
                    ordering: false,
                    autoWidth: false,
                    columnDefs: [
                        {className: "text-right", "targets": [3]} /*添加class*/
                    ],
                    columns: [
                        {
                            title: "姓名",
                            data: 'employeeName'
                        },
                        {title: "所属部门", data: 'departmentName', defaultContent: ""},
                        {title: "岗位名称", data: 'positionName', defaultContent: ""},
                        {title: "电话", data: 'mobile', defaultContent: ""},
                        {title: "操作", data: 'operation', "render": dataTableHelp.operationFormat()}
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
                pagingId:'#employeeList_paging',
                totalCounts:dataTotal,
                pageSize: pageConfig.limit,
                onChange: function (num, type) {
                    filterEmployee(quitPosition, (num-1)*pageConfig.limit, pageConfig.limit);
                }
            };
            pagingPlugin.init(config);

        };

        function filterEmployee(quitPosition, offset, limit) {
            EmployeeService.quitEmployee = [];
            EmployeeService.nonQuitEmployee = [];
            EmployeeService.allEmployee = [];
            //quitPosition = (quitPosition == 'true')?positionStatus["true"]:positionStatus["false"];
            EmployeeService.getEmployee(null, {'x-paging': 'total=true&offset='+offset+'&limit=' + limit}).done(function (data) {
                $.each(data.items,function (index, employee) {
                    if(employee["quit"]){
                        EmployeeService.quitEmployee.push(employee);
                    }else {
                        EmployeeService.nonQuitEmployee.push(employee);
                    }
                    EmployeeService.allEmployee.push(employee);
                });
                // employeeAllDataRaw = EmployeeService.nonQuitEmployee;
                if(quitPosition == 'false'){
                    displayFilteredEmployee(EmployeeService.nonQuitEmployee);
                }else if(quitPosition == 'true'){
                    displayFilteredEmployee(EmployeeService.quitEmployee);
                }else if(quitPosition == '-1'){
                    displayFilteredEmployee(EmployeeService.allEmployee);
                }
                pagination(data.total);
                DepartmentService.getAllDepartment().done(function(data){
                    departmentTree(data);
                });
            });
        }

        filterEmployee(quitPosition, 0, pageConfig.limit);

        //toggle filter for Employee display
        $('.fadeInRight').on('click','#filterEmployeeBtn',function(){
            if($('#box-filter').css('display')=="none"){
                $('#box-filter').show();
            }else {
                $('#box-filter').hide();
            }
        });

        //get quitPosition
        $('#quitPosition').on('change',function () {
            var quitPosition = $('#quitPosition option:selected').val();
            filterEmployee(quitPosition, 0, pageConfig.limit);
        });

        //initialize title in add Employee dialog
        $('#addEmployeeBtn').on('click', function(){
            DepartCommon.initDepartSelector();
            PositionCommon.initPositionSelector();
        });

        //get checked gender
        $('#addEmployeeGender,#editEmployeeGender').on('click','input',function(){
            $(this).prop('checked',true);
            $(this).siblings().prop('checked',false);
        });

        //action for added Employee
        $('#confirmAddEmployeeBtn').on('click', function(){
            var toAddData = {
                'departmentId': $('#addEmployeeDepart').attr('selectedvalue'),
                'positionId': $('#addEmployeePosition option:selected').attr("id"),
                'isAgent': $('#addEmployeeIsAgent').is(':checked'),
                'mobile': $('#addEmployeeMobile').val(),
                'name': $('#addEmployeeName').val(),
                'gender': $('#addEmployeeGender input:checked').val(),
                'idcardNumber': $('#addEmployeeID').val(),
                'wechat': $('#addEmployeeWechat').val(),
                'status':$('#addEmployeeStatus option:selected').val(),
                'entryDate':$('#addEmployeeEntryDate').val()
            };

            EmployeeService.addEmployee({data:toAddData},header)
                .done(function(){
                    // location.reload(true);
                    swal({
                            title: "操作成功!",
                            type: "success",
                            confirmButtonText: "确定",
                            confirmButtonColor: "#3c8dbc"
                        },
                        function(){
                            location.reload(true);
                        });
                })
                .fail(function (res) {
                    // alert(res["message"]);
                    swal({
                        title: "错误!",
                        text: res["message"],
                        type: "error",
                        confirmButtonText: "确定",
                        confirmButtonColor: "#3c8dbc"
                    });
                });
        });

        //initialize title and default value in edit Company dialog
        $('#employeeList').on('click','.editEmployeeBtn',function(e) {
            var index = $(e.target).data('index');
            var employee = employeeAllDataRaw[index];
            DepartCommon.initDepartSelector(employee["departmentId"]);
            PositionCommon.initPositionSelector(employee["positionId"]);
            var time = UtilService.timeStamp2Date(employee["entryDate"]);
            $('#editEmployeeName').val(employee["name"]);
            $('#editEmployeeId').val(employee["id"]);
            $('#editEmployeeGender').find('input[value='+employee.gender["name"]+']').prop('checked',true);
            $('#editEmployeeGender').find('input[value!='+employee.gender["name"]+']').prop('checked',false);
            $('#editEmployeeMobile').val(employee["mobile"]);
            $('#editEmployeeID').val(employee["idcardNumber"]);
            $('#editEmployeeWechat').val(employee["wechat"]);
            $('#editEmployeeIsAgent').prop('checked',employee["agent"]);
            $('#editEmployeeStatus').val(employee.status["name"]);
            $('#editEmployeeEntryDate').val(time);

        });

        //action for edit Company dialog
        $('#confirmEditEmployeeBtn').on('click', function() {
            // var entryDate = new Date($('#editEmployeeEntryDate').val());

            var toEditData = {
                id: $('#editEmployeeId').val(),
                departmentId: $('#editEmployeeDepart').attr("selectedvalue"),
                positionId: $('#editEmployeePosition option:selected').attr("id"),
                mobile: $('#editEmployeeMobile').val(),
                name: $('#editEmployeeName').val(),
                gender: $('#editEmployeeGender input:checked').val(),
                idcardNumber: $('#editEmployeeID').val(),
                wechat: $('#editEmployeeWechat').val(),
                isAgent: $('#editEmployeeIsAgent').is(':checked'),
                status: $('#editEmployeeStatus option:selected').val(),
                entryDate: $('#editEmployeeEntryDate').val()
            };

            EmployeeService.editEmployee({data:toEditData},header)
                .done(function(){
                    // location.reload(true);
                    swal({
                            title: "操作成功!",
                            type: "success",
                            confirmButtonText: "确定",
                            confirmButtonColor: "#3c8dbc"
                        },
                        function(){
                            location.reload(true);
                        });
                })
                .fail(function (res) {
                    // alert(res["message"]);
                    swal({
                        title: "错误!",
                        text: res["message"],
                        type: "error",
                        confirmButtonText: "确定",
                        confirmButtonColor: "#3c8dbc"
                    });
                });
        });

        //set employeeId in quit dialog
        $('#employeeList').on('click','.quitEmployeeBtn',function(e){
            var employeeId = parseInt($(e.target).data('id'),10);
            $('#quitEmployeeId').val(employeeId);
        });

        //action for quiting employee
        $('#confirmQuitEmployeeBtn').on('click', function(){
            var employeeId = $('#quitEmployeeId').val();
            EmployeeService.quitEmployeeAction({data:{id:employeeId}}, header)
                .done(function () {
                    // location.reload(true);
                    swal({
                            title: "操作成功!",
                            type: "success",
                            confirmButtonText: "确定",
                            confirmButtonColor: "#3c8dbc"
                        },
                        function(){
                            location.reload(true);
                        });
                })
                .fail(function (res) {
                    // alert(res["message"]);
                    swal({
                        title: "错误!",
                        text: res["message"],
                        type: "error",
                        confirmButtonText: "确定",
                        confirmButtonColor: "#3c8dbc"
                    });
                });
        });

    });
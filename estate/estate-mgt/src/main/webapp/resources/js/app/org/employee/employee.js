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
        contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js',
    'datatables', 'zTree','datatablesBootstrap', 'datetimepicker.zh-cn'],
    function (mainApp, EmployeeService, DepartmentService, pagingPlugin, dataTableHelp, DepartCommon, PositionCommon, UtilService, SweetAlertHelp) {
        var header = {};

        var employeeAllDataRaw = {};

        var jqPaginatorInstance = false;

        var pageConfig = {
            limit: 8,
            init: false
        };

        var tableConfig ={
            init: false,
            target:null
        };

        var quitPosition = 'false'; //init nonQuit Employee by default

        $.fn.datetimepicker.defaults.language = "zh-CN";
        $('#addEmployeeEntryDate').datetimepicker({
            todayHighlight:true,
            format: 'yyyy-mm-dd',
            startView: 2,
            minView: 2,
            autoclose: true
        });
        $('#editEmployeeEntryDate').datetimepicker({
            todayHighlight:true,
            format: 'yyyy-mm-dd',
            startView: 2,
            minView: 2,
            autoclose: true
        });

       /* 部门树*/
        function departmentTree(data){
            function beforeClick(treeId, treeNode, clickFlag) {}
            function onClick(event, treeId, treeNode, clickFlag) {
                EmployeeService.allEmployee = [];
                $('#quitPosition').find('option[value="-1"]').attr('selected','selected');
                $('#quitPosition').val("-1");
                pageConfig.init = false;
                if(jqPaginatorInstance){
                    $('#employeeList_paging').jqPaginator('destroy');
                    jqPaginatorInstance = false;
                }
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
            });
            var zTreeObj = $.fn.zTree.init($("#departmentTree"), zTreeSetting, departments);
            zTreeObj.expandAll(true);
        }

       /* end部门树*/

        var displayFilteredEmployee = function (data) {
            employeeAllDataRaw = data;
            var dataSet = data.map(function (item, index) {
                return {
                    employeeName: item.name,
                    departmentName: item.departmentName,
                    positionName: item.positionName,
                    mobile: item.mobile,
                    openContact: (item.openContact=="" || item.openContact==null)?(""):(typeof(item.openContact.split(',')[1])=='undefined')?(item.openContact):(item.openContact.split(',')[0]+'转'+item.openContact.split(',')[1]),
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
                        {className: "text-right", "targets": [5]} /*添加class*/
                    ],
                    columns: [
                        {
                            title: "姓名",
                            data: 'employeeName'
                        },
                        {title: "所属部门", data: 'departmentName', defaultContent: ""},
                        {title: "岗位名称", data: 'positionName', defaultContent: ""},
                        {title: "手机", data: 'mobile', defaultContent: ""},
                        {title: "外网电话", data: 'openContact', defaultContent: ""},
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
            jqPaginatorInstance = true;
            pageConfig.init = true;
            var config = {
                pagingId:'#employeeList_paging',
                totalCounts:dataTotal,
                pageSize: pageConfig.limit,
                onChange: function (num, type) {
                    if(type === 'init'){
                        return;
                    }
                    filterEmployee(quitPosition, (num-1)*pageConfig.limit, pageConfig.limit);
                }
            };
            pagingPlugin.init(config);

        };

        function filterEmployee(quitPosition, offset, limit) {
            EmployeeService.getEmployee(null, {'x-paging': 'total=true&offset='+offset+'&limit=' + limit})
                .done(function (data) {
                    displayFilteredEmployee(data.items);
                    pagination(data.total);
                }).fail(function(){
                    $('#employeeList>tbody').append('<tr><td colspan="6">无法获取数据</td></tr>');
                });
        }

        filterEmployee(quitPosition, 0, pageConfig.limit);

        DepartmentService.getAllDepartment().done(function(data){
            departmentTree(data);
        });

        function verifyEmployeeInput(actionType, toSubmitEmployee) {
            var flag = true;
            $('.form-group').find('.invalid-input').removeClass('invalid-input');
            if (toSubmitEmployee.departmentId==="" || typeof(toSubmitEmployee.departmentId)==='undefined') {
                flag = false;
                $('#'+actionType+'EmployeeDepart').addClass('invalid-input');
            }
            if (toSubmitEmployee.name===""|| typeof(toSubmitEmployee.name)==='undefined') {
                flag = false;
                $('#'+actionType+'EmployeeName').addClass('invalid-input');
            }
            if (toSubmitEmployee.gender===""|| typeof(toSubmitEmployee.gender)==='undefined') {
                flag = false;
                $('#'+actionType+'EmployeeGender').addClass('invalid-input');
            }
            if (toSubmitEmployee.idcardNumber===""|| typeof(toSubmitEmployee.idcardNumber)==='undefined') {
                flag = false;
                $('#'+actionType+'EmployeeID').addClass('invalid-input');
            }
            if (toSubmitEmployee.entryDate===""|| typeof(toSubmitEmployee.entryDate)==='undefined') {
                flag = false;
                $('#'+actionType+'EmployeeEntryDate').addClass('invalid-input');
            }
            return flag;
        }

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
            var hostNumber = $('#addEmployeeOpenContactHN').val();
            var extensionNumber = $('#addEmployeeOpenContactEN').val();
            var openContact = [hostNumber,extensionNumber];
            var toAddData = {
                'departmentId': $('#addEmployeeDepart').attr('selectedvalue'),
                'positionId': $('#addEmployeePosition option:selected').attr("id"),
                'mobile': $('#addEmployeeMobile').val(),
                'openContact': (hostNumber==="")?"":(extensionNumber==="")?hostNumber:openContact.join(','),
                'name': $('#addEmployeeName').val(),
                'gender': $('#addEmployeeGender input:checked').val(),
                'idcardNumber': $('#addEmployeeID').val(),
                'wechat': $('#addEmployeeWechat').val(),
                'status':$('#addEmployeeStatus option:selected').val(),
                'entryDate':$('#addEmployeeEntryDate').val()
            };

            if(verifyEmployeeInput('add',toAddData)){
                EmployeeService.addEmployee({data:toAddData},header)
                    .done(function(){
                        SweetAlertHelp.success({}, function () {
                            location.reload(true);
                        });
                    })
                    .fail(function (res) {
                        SweetAlertHelp.fail(res);
                    });
            }else {
                SweetAlertHelp.fail({message:"请填写所有必填字段"});
            }

        });

        //initialize title and default value in edit Company dialog
        $('#employeeList').on('click','.editEmployeeBtn',function(e) {
            var index = $(e.target).data('index');
            var employee = employeeAllDataRaw[index];
            var hostNumber = "";
            var extensionNumber = "";
            if(employee["openContact"]==null){
            } else if(typeof(employee["openContact"].split(',')[1])=='undefined'){
                hostNumber = employee["openContact"];
            }else {
                hostNumber = employee["openContact"].split(',')[0];
                extensionNumber = employee["openContact"].split(',')[1];
            }
            DepartCommon.initDepartSelector(employee["departmentId"]);
            PositionCommon.initPositionSelector(employee["positionId"]);
            var time = UtilService.timeStamp2Date(employee["entryDate"]);
            $('#editEmployeeName').val(employee["name"]);
            $('#editEmployeeId').val(employee["id"]);
            $('#editEmployeeGender').find('input[value='+employee.gender["name"]+']').prop('checked',true);
            $('#editEmployeeGender').find('input[value!='+employee.gender["name"]+']').prop('checked',false);
            $('#editEmployeeMobile').text(employee["mobile"]);
            $('#editEmployeeOpenContactHN').val(hostNumber);
            $('#editEmployeeOpenContactEN').val(extensionNumber);
            $('#editEmployeeID').val(employee["idcardNumber"]);
            $('#editEmployeeWechat').val(employee["wechat"]);
            $('#editEmployeeIsAgent').prop('checked',employee["agent"]);
            $('#editEmployeeStatus').val(employee.status["name"]);
            $('#editEmployeeEntryDate').val(time);

        });

        //action for edit Company dialog
        $('#confirmEditEmployeeBtn').on('click', function() {
            var hostNumber = $('#editEmployeeOpenContactHN').val();
            var extensionNumber = $('#editEmployeeOpenContactEN').val();
            var openContact = [hostNumber,extensionNumber];
            var toEditData = {
                id: $('#editEmployeeId').val(),
                departmentId: $('#editEmployeeDepart').attr("selectedvalue"),
                positionId: $('#editEmployeePosition option:selected').attr("id"),
                openContact: (hostNumber==="")?"":(extensionNumber==="")?hostNumber:openContact.join(','),
                name: $('#editEmployeeName').val(),
                gender: $('#editEmployeeGender input:checked').val(),
                idcardNumber: $('#editEmployeeID').val(),
                wechat: $('#editEmployeeWechat').val(),
                isAgent: $('#editEmployeeIsAgent').is(':checked'),
                status: $('#editEmployeeStatus option:selected').val(),
                entryDate: $('#editEmployeeEntryDate').val()
            };

            if(verifyEmployeeInput('edit',toEditData)){
                EmployeeService.editEmployee({data:toEditData},header)
                    .done(function(){
                        SweetAlertHelp.success({}, function () {
                            location.reload(true);
                        });
                    })
                    .fail(function (res) {
                        SweetAlertHelp.fail(res);
                    });
            }else {
                SweetAlertHelp.fail({message:"请填写所有必填字段"});
            }

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
                    SweetAlertHelp.success({}, function () {
                        location.reload(true);
                    });
                })
                .fail(function (res) {
                    SweetAlertHelp.fail(res);
                });
        });

    });
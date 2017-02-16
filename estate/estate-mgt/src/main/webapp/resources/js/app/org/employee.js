/**
 * Created by yanghong on 1/17/17.
 */
require(['main-app',contextPath + '/js/service/employee-service.js',
        contextPath + '/js/service/department-service.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        contextPath+'/js/utils/dataTableHelp.js',
        contextPath+'/js/app/org/department/departCommon.js',
    'datatables', 'zTree','datatablesBootstrap'],
    function (mainApp, EmployeeService, DepartmentService, pagingPlugin, dataTableHelp, DepartCommon) {
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

        var positionStatus = {
            on: 'nonQuit',
            off: 'quit'
        };
        var quitPosition = 'nonQuit'; //init nonQuit Employee by default

       /* 部门树*/
        function departmentTree(data){
            function beforeClick(treeId, treeNode, clickFlag) {}
            function onClick(event, treeId, treeNode, clickFlag) {
                console.log('点击了:'+ treeNode.id + treeNode.name);
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
        DepartmentService.getAllDepartment().done(function(data){
           departmentTree(data);
        });
       /* end部门树*/

        /*function iniDepartDropList(header) {
            var appendOption = '';
            DepartmentService.getAllDepartment(header).done(function (data) {
                $.each(data, function (index, element) {
                    appendOption += '<option id="' + element.department["id"] + '">' + element.department["name"] + '</option>';
                });
                $('#addEmployeeDepart').html(appendOption);
            });
        }*/


        /*function iniPositionDropList(header) {
            var appendOption = '';
            EmployeeService.getPosition(header).done(function (data) {
                $.each(data, function (index, element) {
                    appendOption += '<option id="' + element["id"] + '">' + element["name"] + '</option>';
                });
                $('#addEmployeePosition').html(appendOption);
            });
        }*/

        /*function iniSelectedDepartDropList(header,departmentId) {
            var appendOption = '';
            EmployeeService.getDepartment(header).done(function (data) {
                $.each(data, function (index, element) {
                    appendOption += '<option id="' + element.department["id"] + '">' + element.department["name"] + '</option>';
                });
                $('#editEmployeeDepart').html(appendOption);
                $('#editEmployeeDepart').find('option[id='+departmentId+']').attr('selected','selected');
            });
        }*/

        /*function iniSelectedPositionDropList(header,positionId) {
            var appendOption = '';
            EmployeeService.getPosition(header).done(function (data) {
                $.each(data, function (index, element) {
                    appendOption += '<option id="' + element["id"] + '">' + element["name"] + '</option>';
                });
                $('#editEmployeePosition').html(appendOption);
                $('#editEmployeePosition').find('option[id='+positionId+']').attr('selected','selected');
            });
        }*/

        function displayFilteredEmployee(quitPosition, employee) {
            var appendHtml = '';
            if(quitPosition == 'nonQuit'){
                $.each(employee,function (index, employeeRaw) {
                    appendHtml += '<tr>' +
                        '<td>'+employeeRaw["name"]+'</td>' +
                        '<td>'+employeeRaw.department["name"]+'</td>' +
                        '<td>'+employeeRaw.position["name"]+'</td>' +
                        '<td>'+employeeRaw["mobile"]+'</td>' +
                        '<td class="text-right">' +
                        '<a class="btn" id="editEmployeeBtn" data-index="'+index+'" data-id="'+employeeRaw["id"]+'" data-toggle="modal" data-target="#editEmployeeDialog">编辑</a>' +
                        '<span class="opt-gap"></span>' +
                        '<a class="btn" id="quitEmployeeBtn" data-index="'+index+'" data-id="'+employeeRaw["id"]+'" data-toggle="modal" data-target="#quitEmployeeDialog">离职</a>' +
                        '</td>' +
                        '</tr>';
                });
                $('#employeeList>tbody').html(appendHtml);
            }else if(quitPosition == 'quit'){
                $.each(employee,function (index, employeeRaw) {
                    appendHtml += '<tr>' +
                        '<td>'+employeeRaw["name"]+'</td>' +
                        '<td>'+employeeRaw.department["name"]+'</td>' +
                        '<td>'+employeeRaw.position["name"]+'</td>' +
                        '<td>'+employeeRaw["mobile"]+'</td>' +
                        '</tr>';
                });
                $('#employeeList>tbody').html(appendHtml);
            }

        }

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
                    filterEmployee((num-1)*pageConfig.limit, pageConfig.limit);
                }
            };
            pagingPlugin.init(config);

        };

        function filterEmployee(quitPosition, offset, limit) {
            EmployeeService.quitEmployee = [];
            EmployeeService.nonQuitEmployee = [];
            EmployeeService.getEmployee({'x-paging': 'total=true&offset='+offset+'&limit=' + limit}).done(function (data) {
                $.each(data,function (index, employeeRaw) {
                    if(employeeRaw["quit"]){
                        EmployeeService.quitEmployee.push(employeeRaw);
                    }else {
                        EmployeeService.nonQuitEmployee.push(employeeRaw);
                    }
                });
                employeeAllDataRaw = EmployeeService.nonQuitEmployee;
                if(quitPosition == positionStatus["on"]){
                    displayFilteredEmployee(quitPosition,EmployeeService.nonQuitEmployee);
                }else if(quitPosition == positionStatus["off"]){
                    displayFilteredEmployee(quitPosition,EmployeeService.quitEmployee);
                }
                pagination(data.total);
            });
        }

        filterEmployee(quitPosition, 0, pageConfig.limit);

        //get quitPosition
        $('#quitPosition').on('change',function () {
            var quitPosition = $('#quitPosition option:selected').val();
            filterEmployee(quitPosition, 0, pageConfig.limit);

        });

        /*$('#employeeList').DataTable({
            "paging": true,
            "lengthChange": false,
            "searching": false,
            "ordering": false,
            "info": false,
            "autoWidth": false
        });*/
        //get data from server and display data
        /*EmployeeService.getEmployee(header)
            .done(function(data){
                employeeAllDataRaw = data;
                var appendHtml = '';
                $.each(data,function (index, employeeRaw) {
                    appendHtml = '<tr>' +
                        '<td>'+employeeRaw["name"]+'</td>' +
                        '<td>'+employeeRaw.department["name"]+'</td>' +
                        '<td>'+employeeRaw.position["name"]+'</td>' +
                        '<td>'+employeeRaw["mobile"]+'</td>' +
                        '<td class="text-right">' +
                        '<a class="btn" id="editEmployeeBtn" data-index="'+index+'" data-id="'+employeeRaw["id"]+'" data-toggle="modal" data-target="#editEmployeeDialog">编辑</a>' +
                        '<span class="opt-gap"></span>' +
                        '<a class="btn" id="deleteEmployeeBtn" data-index="'+index+'" data-id="'+employeeRaw["id"]+'" data-toggle="modal" data-target="#deleteEmployeeDialog">删除</a>' +
                        '</td>' +
                        '</tr>';
                    $('#employeeList>tbody').append(appendHtml);
                });

                $('#employeeList').DataTable({
                    "paging": true,
                    "lengthChange": false,
                    "searching": false,
                    "ordering": false,
                    "info": false,
                    "autoWidth": false
                });
            })
            .fail(function () {
                $('#employeeList>tbody').append('<tr><td colspan="4">无法获取数据</td></tr>');
            });*/

        //initialize title in add Employee dialog
        $('.fadeInRight').on('click','#addEmployeeBtn',function(){
            DepartCommon.initDepartSelector();
        });

        //toggle filter for Employee display
        $('.fadeInRight').on('click','#filterEmployeeBtn',function(){
            if($('#box-filter').css('display')=="none"){
                $('#box-filter').show();
            }else {
                $('#box-filter').hide();
            }
        });

        //get checked gender
        $('#addEmployeeGender,#editEmployeeGender').on('click','input',function(){
            $(this).prop('checked',true);
            $(this).siblings().prop('checked',false);
        });

        //action for added Employee
        $('#addEmployeeDialog').on('click','#confirmAddEmployeeBtn',function(){
            var toAddData = {
                'departmentId': $('#addEmployeeDepart option:selected').attr("id"),
                'positionId': $('#addEmployeePosition option:selected').attr("id"),
                'isAgent': $('#addEmployeeIsAgent').is(':checked'),
                'mobile': $('#addEmployeeMobile').val(),
                'name': $('#addEmployeeName').val(),
                'gender': $('#addEmployeeGender input:checked').val(),
                'idcardNumber': $('#addEmployeeID').val(),
                'wechat': $('#addEmployeeWechat').val(),
                'status':$('#addEmployeeStatus option:selected').val()
            };

            EmployeeService.addEmployee({data:toAddData},header)
                .done(function(){
                    location.reload(true);
                })
                .fail(function () {
                    alert("请填写所有必填字段");
                });
        });

        //initialize title and default value in edit Company dialog
        $('#employeeList').on('click','#editEmployeeBtn',function(e) {
            var index = $(e.target).data('index');
            var employee = employeeAllDataRaw[index];
            // DepartCommon.initDepartSelector(departPid);
            //$('#editEmployeePosition').find('option[id='+positionId+']').attr('selected','selected');
            $('#editEmployeeName').val(employee["name"]);
            $('#editEmployeeId').val(employee["id"]);
            $('#editEmployeeGender').find('input[value='+employee["gender"]+']').prop('checked',true);
            $('#editEmployeeGender').find('input[value!='+employee["gender"]+']').prop('checked',false);
            $('#editEmployeeMobile').val(employee["mobile"]);
            $('#editEmployeeID').val(employee["idcardNumber"]);
            $('#editEmployeeWechat').val(employee["wechat"]);
            // $('#editEmployeeIsAgent').prop('checked',employee["is_agent"]);
            $('#editEmployeeStatus').val(employee["status"]);

        });

        //action for edit Company dialog
        $('#editEmployeeDialog').on('click','#confirmEditEmployeeBtn',function() {
            var toEditData = {
                id: $('#editEmployeeId').val(),
                departmentId: $('#editEmployeeDepart option:selected').attr("id"),
                positionId: $('#editEmployeePosition option:selected').attr("id"),
                mobile: $('#editEmployeeMobile').val(),
                name: $('#editEmployeeName').val(),
                gender: $('#editEmployeeGender input:checked').val(),
                idcardNumber: $('#editEmployeeID').val(),
                wechat: $('#editEmployeeWechat').val(),
                // isAgent: $('#editEmployeeIsAgent').is(':checked')
                status: $('#editEmployeeStatus option:selected').val()
            };

            EmployeeService.editEmployee({data:toEditData},header)
                .done(function(){
                    location.reload(true);
                })
                .fail(function () {
                    alert("请填写所有必填字段");
                });
        });

        //set employeeId in quit dialog
        $('#employeeList').on('click','#quitEmployeeBtn',function(e){
            var employeeId = parseInt($(e.target).data('id'),10);
            $('#quitEmployeeId').val(employeeId);
        });

        //action for quiting employee
        $('#quitEmployeeDialog').on('click','#confirmQuitEmployeeBtn',function(){
            var employeeId = $('#quitEmployeeId').val();
            EmployeeService.quitEmployeeAction({data:{id:employeeId}}, header)
                .done(function () {
                    location.reload(true);
                })
                .fail(function (res) {
                    // var res = JSON.parse(data.responseText);
                });
        });

    });
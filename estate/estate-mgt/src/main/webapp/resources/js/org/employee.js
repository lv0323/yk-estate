/**
 * Created by yanghong on 1/17/17.
 */
require(['main-app',contextPath + '/js/service/employee-service.js','datatables','datatablesBootstrap'],
    function (mainApp,EmployeeService) {
        var header = {};

        var employeeAllDataRaw = {};

        function iniDepartDropList(header) {
            var appendOption = '';
            EmployeeService.getDepartment(header).done(function (data) {
                $.each(data, function (index, element) {
                    appendOption += '<option id="' + element.department["id"] + '">' + element.department["name"] + '</option>';
                });
                $('#addEmployeeDepart').html(appendOption);
            });
        }

        function iniPositionDropList(header) {
            var appendOption = '';
            EmployeeService.getPosition(header).done(function (data) {
                $.each(data, function (index, element) {
                    appendOption += '<option id="' + element["id"] + '">' + element["name"] + '</option>';
                });
                $('#addEmployeePosition').html(appendOption);
            });
        }

        function iniSelectedDepartDropList(header,departmentId) {
            var appendOption = '';
            EmployeeService.getDepartment(header).done(function (data) {
                $.each(data, function (index, element) {
                    appendOption += '<option id="' + element.department["id"] + '">' + element.department["name"] + '</option>';
                });
                $('#editEmployeeDepart').html(appendOption);
                $('#editEmployeeDepart').find('option[id='+departmentId+']').attr('selected','selected');
            });
        }

        function iniSelectedPositionDropList(header,positionId) {
            var appendOption = '';
            EmployeeService.getPosition(header).done(function (data) {
                $.each(data, function (index, element) {
                    appendOption += '<option id="' + element["id"] + '">' + element["name"] + '</option>';
                });
                $('#editEmployeePosition').html(appendOption);
                $('#editEmployeePosition').find('option[id='+positionId+']').attr('selected','selected');
            });
        }

        //get data from server and display data
        EmployeeService.getEmployee(header)
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
            });

        //initialize title in add Employee dialog
        $('.fadeInRight').on('click','#addEmployeeBtn',function(){
            $('#addEmployeeLabel').text('增加员工');
            iniDepartDropList(header);
            iniPositionDropList(header);
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
            $('#editEmployeeLabel').text('编辑员工');
            $('#editEmployeeName').val(employee["name"]);
            $('#editEmployeeId').val(employee["id"]);
            $('#editEmployeeGender').find('input[value='+employee["gender"]+']').prop('checked',true);
            $('#editEmployeeGender').find('input[value!='+employee["gender"]+']').prop('checked',false);
            $('#editEmployeeMobile').val(employee["mobile"]);
            $('#editEmployeeID').val(employee["idcard_number"]);
            $('#editEmployeeWechat').val(employee["wechat"]);
            // $('#editEmployeeIsAgent').prop('checked',employee["is_agent"]);
            $('#editEmployeeStatus').val(employee["status"]);
            iniSelectedDepartDropList(header,employee.department["id"]);
            iniSelectedPositionDropList(header,employee.position["id"]);
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

    });
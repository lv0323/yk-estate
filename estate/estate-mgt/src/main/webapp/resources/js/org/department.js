/**
 * Created by yanghong on 1/17/17.
 */
require(['main-app',contextPath + '/js/service/request-service.js'],
    function (mainApp,RequestService) {

        var BaseUrl = "/api/department/";
        var header = {'x-auth-token':'c1caabf1-4c77-445d-9e2d-38e5b7fa0895'};
        var departDataRaw = {};

        //get data from server and display data
        RequestService.get(BaseUrl+"query-sorted",null,header)
            .done(function(data){
                if(data === null){
                    $('#departList>tbody').append('<tr><td colspan="4">没有数据</td></tr>');
                }else {
                    departDataRaw = data;
                    $.each(data,function(index,departRaw){
                        var appendHtml = '<tr>' +
                            '<td><a class="btn" id="lookDepartBtn" data-id="'+departRaw.department["id"]+'">'+departRaw.department["name"]+'</a></td>' +
                            '<td>'+departRaw.department["telephone"]+'</td>' +
                            '<td>'+departRaw.department["address"]+'</td>' +
                            '<td class="text-right"><a class="btn" id="editDepartBtn" data-index="'+index+'" data-id="'+departRaw.department["id"]+'" data-toggle="modal" data-target="#editDepartDialog">编辑</a>';

                        //if parent_id is null, disable delete button
                        if(departRaw.deletable){
                            appendHtml+='<span class="opt-gap"></span>'+
                                '<a class="btn" id="delDepartBtn" data-pid="'+departRaw.department["parent_id"]+'" data-id="'+departRaw.department["id"]+'" data-toggle="modal" data-target="#deleteDepartDialog">删除</a></td>'+
                                '</tr>';
                        }else{
                            appendHtml+='</tr>';
                        }
                        $('#departList>tbody').append(appendHtml);

                        //initialize departments selection in add/edit dialog
                        $('#addDepartDialog #departPid').append('<option value="'+departRaw.department["id"]+'">'+departRaw.department["name"]+'</option>');
                        $('#editDepartDialog #departPid').append('<option value="'+departRaw.department["id"]+'">'+departRaw.department["name"]+'</option>');

                    });

                    $('#departList').DataTable({
                        "paging": true,
                        "lengthChange": false,
                        "searching": false,
                        "ordering": false,
                        "info": false,
                        "autoWidth": false
                    });

                }
            })
            .fail(function(){
                $('#departList>tbody').append('<tr><td colspan="4">无法获取数据</td></tr>');
            });

        //initialize title in add department dialog
        $('.fadeInRight').on('click','#addDepartBtn',function(){
            $('#addDepartDialog #addDepartLabel').text('增加部门');
            var parent_id = $('#addDepartDialog #departPid :selected').val();
            console.log("parent_id in dialog"+parent_id);
        });

        //action for added department
        $('#addDepartDialog').on('click','#confirmAddDepartBtn',function(){
            var parent_id = $('#addDepartDialog #departPid :selected').val();
            console.log("parent_id in dialog after selected"+parent_id);
            var toAddDepart = {
                address: $('#addDepartDialog #departAddress').val(),
                name: $('#addDepartDialog #departName').val(),
                parent_id: parseInt(parent_id,10),
                short_name: $('#addDepartDialog #departSpell').val(),
                telephone: $('#addDepartDialog #departTel').val()
            };
            RequestService.post(BaseUrl+"add",toAddDepart,header)
                .done(function(){
                    location.reload(true);
                });
        });


        //initialize index value stored in deleteDepartDialog
        $('#departList').on('click','#delDepartBtn',function(e){
            var departId = $(e.target).data('id');
            console.log("departId in row: "+departId);
            $('#deleteDepartDialog #departId').val(departId);
        });

        //delete data according to index value where specifies id
        $('#deleteDepartDialog').on('click','#confirmDelDepartBtn',function(){
            var departId_request = $('#deleteDepartDialog #departId').val();
            console.log("departId_request in Dialog to be delivered to request: "+departId_request);
            RequestService.get(BaseUrl+"delete",{id:departId_request},header)
                .done(function(){
                    location.reload(true);
                });
        });

        //initialize title and default value in edit department dialog
        $('#departList').on('click','#editDepartBtn',function(e){
            var index = $(e.target).data('index');
            var depart = departDataRaw[index].department;
            var pId  = depart["parent_id"];
            $('#editDepartDialog #editDepartLabel').text('编辑部门');
            $('#editDepartDialog #departPid').find('option[value='+pId+']').attr('selected','selected');
            $('#editDepartDialog #departId').val(depart["id"]);
            $('#editDepartDialog #departName').val(depart["name"]);
            $('#editDepartDialog #departSpell').val(depart["short_name"]);
            $('#editDepartDialog #departTel').val(depart["telephone"]);
            $('#editDepartDialog #departAddress').val(depart["address"]);

            var id = $('#editDepartDialog #departId').val();
            console.log("departId_request in Dialog to be delivered to request: "+id);
        });

        //action for updated department
        $('#editDepartDialog').on('click','#confirmEditDepartBtn',function(){
            var id = $('#editDepartDialog #departId').val();
            console.log("departId_request in Dialog to be delivered to request: "+id);
            var parent_id = $('#editDepartDialog #departPid :selected').val();
            var toAddDepart = {
                id: parseInt(id,10),
                address: $('#editDepartDialog #departAddress').val(),
                name: $('#editDepartDialog #departName').val(),
                parent_id: parseInt(parent_id,10),
                short_name: $('#editDepartDialog #departSpell').val(),
                telephone: $('#editDepartDialog #departTel').val()
            };
            RequestService.post(BaseUrl+"edit",toAddDepart,header)
                .done(function(){
                    location.reload(true);
                });
        });


});
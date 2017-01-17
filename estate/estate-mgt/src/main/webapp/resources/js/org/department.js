/**
 * Created by yanghong on 1/17/17.
 */
require(['main-app',contextPath + '/js/service/request-service.js'],
    function (mainApp,RequestService) {

        var BaseUrl = "/api/department/";
        var header = {'x-auth-token':'c4f9e1c0-3a93-45f5-b4e4-147ca31875a5'};
        var allData = {};

        //get data from server and display data
        RequestService.get(BaseUrl+"query-sorted",null,header)
            .done(function(data){
                if(data === null){
                    $('#departList>tbody').append('<tr><td colspan="4">没有数据</td></tr>');
                }else {
                    allData.departData = data;
                    $.each(data,function(index,depart){
                        var appendHtml = '<tr>' +
                            '<td><a class="btn" id="lookDepartBtn" data-id="'+depart["id"]+'">'+depart["name"]+'</a></td>' +
                            '<td>'+depart["telephone"]+'</td>' +
                            '<td>'+depart["address"]+'</td>' +
                            '<td class="text-right"><a class="btn" id="editDepartBtn" data-index="'+index+'" data-id="'+depart["id"]+'" data-toggle="modal" data-target="#editDepartDialog">编辑</a>';

                        //if parent_id is null, disable delete button
                        if(depart["parent_id"]){
                            appendHtml+='<span class="opt-gap"></span>'+
                                '<a class="btn" id="delDepartBtn" data-pid="'+depart["parent_id"]+'" data-id="'+depart["id"]+'" data-toggle="modal" data-target="#deleteDepartDialog">删除</a></td>'+
                                '</tr>';
                        }else{
                            appendHtml+='</tr>';
                        }
                        $('#departList>tbody').append(appendHtml);

                        //initialize departments selection in add/edit dialog
                        $('#addDepartDialog #departPid').append('<option value="'+depart["id"]+'">'+depart["name"]+'</option>');
                        $('#editDepartDialog #departPid').append('<option value="'+depart["id"]+'">'+depart["name"]+'</option>');

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
        });

        //action for added department
        var toAddDepart = {
            address: $('#addDepartDialog #departAddress').val(),
            name: $('#addDepartDialog #departName').val(),
            parent_id: $('#addDepartDialog #departPid :selected').val(),
            short_name: $('#addDepartDialog #departSpell').val(),
            telephone: $('#addDepartDialog #departTel').val()
        };
        $('#addDepartDialog').on('click','#confirmAddDepartBtn',RequestService.post(BaseUrl+"add",JSON.stringify(toAddDepart),header));

});
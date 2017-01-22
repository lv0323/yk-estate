/**
 * Created by yanghong on 1/17/17.
 */
require(['main-app',contextPath + '/js/service/request-service.js','datatables','datatablesBootstrap'],
    function (mainApp,RequestService) {

        var BaseUrl = "/api/department/";
<<<
        var header = {};
===
        var departAllDataRaw = {};

        //get city from server
        RequestService.get(BaseUrl+"district/cites",null,header)
            .done(function (data) {
                departAllDataRaw.city = data;
                $.each(data,function (index, city) {
                    $('#addDepartDialog #departCid').append('<option id="'+city.id+'">'+city.name+'</option>');
                    $('#editDepartDialog #departCid').append('<option id="'+city.id+'">'+city.name+'</option>');
                });
            });



        //get subDistrict from server
        RequestService.get(BaseUrl+"district/sub-districts",null,header)
            .done(function (data) {
                departAllDataRaw.subDistrict = data;
                $.each(data,function (index, subDistrict) {
                    $('#addDepartDialog #departSDid').append('<option id="'+subDistrict.id+'">'+subDistrict.name+'</option>');
                    $('#editDepartDialog #departSDid').append('<option id="'+subDistrict.id+'">'+subDistrict.name+'</option>');
                });
            });

        //get data from server and display data
        RequestService.get(BaseUrl+"query-sorted",null,header)
            .done(function(data){
                if(data === null){
                    $('#departList>tbody').append('<tr><td colspan="4">没有数据</td></tr>');
                }else {
                    departAllDataRaw.departmentRaw = data;
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
                        $('#addDepartDialog #departPid').append('<option value="'+departRaw.department["id"]+'">'+'&nbsp;'.repeat(departRaw.level*4)+departRaw.department["name"]+'</option>');
                        $('#editDepartDialog #departPid').append('<option value="'+departRaw.department["id"]+'">'+'&nbsp;'.repeat(departRaw.level*4)+departRaw.department["name"]+'</option>');
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

        //initialize title in add department dialog
        $('.addDepartDialog').on('change','#departCid',function(){
            var city_id = $('#addDepartDialog #departCid option:selected').val();
            //get district from server
            RequestService.get(BaseUrl+"district/districts",city_id,header)
                .done(function (data) {
                    departAllDataRaw.district = data;
                    $.each(data,function (index, district) {
                        $('#addDepartDialog #departDid').append('<option id="'+district.id+'">'+district.name+'</option>');
                        $('#editDepartDialog #departDid').append('<option id="'+district.id+'">'+district.name+'</option>');
                    });
                });
        });

        //action for added department
        $('#addDepartDialog').on('click','#confirmAddDepartBtn',function(){
            var toAddDepart = {
                    address: $('#addDepartDialog #departAddress').val(),
                    name: $('#addDepartDialog #departName').val(),
                    parentId: $('#addDepartDialog #departPid option:selected').val(),
                    shortName: $('#addDepartDialog #departSpell').val(),
                    telephone: $('#addDepartDialog #departTel').val()
                };


            RequestService.post(BaseUrl+"add",toAddDepart,header)
                .done(function(){
                    location.reload(true);
                })
                .fail(function (data) {
                    /*var res = JSON.parse(data.responseText);
                    if(res["ex_code"] === "NULL_PARENT"){
                        alert(res["message"]);
                    }*/
                });
        });


        //initialize index value stored in deleteDepartDialog
        $('#departList').on('click','#delDepartBtn',function(e){
            var departId = parseInt($(e.target).data('id'),10);
            $('#deleteDepartDialog #departId').val(departId);
        });

        //delete data according to index value where specifies id
        $('#deleteDepartDialog').on('click','#confirmDelDepartBtn',function(){
            var departId_request = parseInt($('#deleteDepartDialog #departId').val(),10);
            RequestService.get(BaseUrl+"delete",{id:departId_request},header)
                .done(function(){
                    location.reload(true);
                });
        });

        //initialize title and default value in edit department dialog
        $('#departList').on('click','#editDepartBtn',function(e){
            var index = $(e.target).data('index');
            var depart = departAllDataRaw.departmentRaw[index].department;
            var pId  = depart["parent_id"];
            $('#editDepartDialog #editDepartLabel').text('编辑部门');
            if(pId){
                // $('#editDepartDialog #departPid').prop('disabled',false);
                $('#editDepartDialog #departPid').css('display','inline');
                $('#editDepartDialog #superiorDepartLabel').css('display','inline');
                $('#editDepartDialog #departPid').find('option[value='+pId+']').attr('selected','selected');
                $('#editDepartDialog #departPid').find('option[value!='+pId+']').removeAttr('selected');
            }else{
                // $('#editDepartDialog #departPid').prop('disabled','disabled');
                $('#editDepartDialog #departPid').css('display','none');
                $('#editDepartDialog #superiorDepartLabel').css('display','none');
                $('#editDepartDialog #departPid').find('option[value=""]').attr('selected','selected');
                $('#editDepartDialog #departPid').find('option[value!='+pId+']').removeAttr('selected');
            }
            $('#editDepartDialog #departId').val(depart["id"]);
            $('#editDepartDialog #departName').val(depart["name"]);
            $('#editDepartDialog #departSpell').val(depart["short_name"]);
            $('#editDepartDialog #departTel').val(depart["telephone"]);
            $('#editDepartDialog #departAddress').val(depart["address"]);
        });

        //action for updated department
        $('#editDepartDialog').on('click','#confirmEditDepartBtn',function(){
            var id = $('#editDepartDialog #departId').val();
            var parent_id = $('#editDepartDialog #departPid option:selected').val();
            var toEditDepart = {};
            if(parent_id){
                toEditDepart = {
                    id: parseInt(id,10),
                    address: $('#editDepartDialog #departAddress').val(),
                    name: $('#editDepartDialog #departName').val(),
                    parentId: parseInt(parent_id,10),
                    shortName: $('#editDepartDialog #departSpell').val(),
                    telephone: $('#editDepartDialog #departTel').val()
                };
            }else{
                toEditDepart = {
                    id: parseInt(id,10),
                    address: $('#editDepartDialog #departAddress').val(),
                    name: $('#editDepartDialog #departName').val(),
                    parentId: parent_id,
                    shortName: $('#editDepartDialog #departSpell').val(),
                    telephone: $('#editDepartDialog #departTel').val()
                };
            }
            if(parent_id === id){
                alert("父部门不能为其本身"); //forbid depart to be arranged under itself
            }else {
                RequestService.post(BaseUrl+"edit",toEditDepart,header)
                    .done(function(){
                        location.reload(true);
                    })
                    .fail(function (data) {
                        var res = JSON.parse(data.responseText);
                        if(res["ex_code"] === "INVALID_PARENT"){
                            alert(res["message"]); //in case parent depart to be arranged under its child
                        }
                    });
            }

        });


});
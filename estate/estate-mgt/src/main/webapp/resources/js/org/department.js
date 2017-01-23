/**
 * Created by yanghong on 1/17/17.
 */
require(['main-app',contextPath + '/js/service/request-service.js','datatables','datatablesBootstrap'],
    function (mainApp,RequestService) {

        var BaseUrl = "/api/department/";

        var header = {};

        var departAllDataRaw = {};

        function iniDropList(url,superior_id,header,jqueryElementOuter,jqueryElementInner){
            RequestService.get(url,{id:superior_id},header)
                .done(function (data) {
                    var appendOption = '';
                    $.each(data, function (index, element) {
                        appendOption += '<option id="' + element.id + '">' + element.name + '</option>';

                    });
                    $('#'+jqueryElementOuter+' #'+jqueryElementInner).html(appendOption);
                })
        }

        //get city from server
        RequestService.get(BaseUrl+"district/cites",null,header)
            .done(function (data) {
                var appendOption = "";
                $.each(data,function (index, city) {
                    appendOption += '<option id="'+city.id+'">'+city.name+'</option>';
                });
                $('#addDepartDialog #departCid').append(appendOption);
                $('#editDepartDialog #departCid').append(appendOption);
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

        //get district and subDistrict from server in add department dialog
        $('#addDepartDialog').on('change','#departCid',function(){
            var city_id = $('#addDepartDialog #departCid option:selected').attr("id");
            //get district from server
            RequestService.get(BaseUrl+"district/districts",{id:city_id},header)
                .done(function (data) {
                    var appendOption = "";
                    $.each(data,function (index, district) {
                        appendOption += '<option id="'+district.id+'">'+district.name+'</option>';
                    });
                    $('#addDepartDialog #departDid').html(appendOption);

                    var district_id = $('#addDepartDialog #departDid option:selected').attr("id");
                    //get subDistrict from server
                    iniDropList(BaseUrl+"district/sub-districts",district_id,header,'addDepartDialog','departSDid');
                });
        });

        //get subDistrict from server in add department dialog
        $('#addDepartDialog').on('change','#departDid',function(){
            var district_id = $('#addDepartDialog #departDid option:selected').attr("id");
            iniDropList(BaseUrl+"district/sub-districts",district_id,header,'addDepartDialog','departSDid');
        });



        //action for added department
        $('#addDepartDialog').on('click','#confirmAddDepartBtn',function(){
            var toAddDepart = {
                    address: $('#addDepartDialog #departAddress').val(),
                    name: $('#addDepartDialog #departName').val(),
                    parentId: $('#addDepartDialog #departPid option:selected').val(),
                    shortName: $('#addDepartDialog #departSpell').val(),
                    telephone: $('#addDepartDialog #departTel').val(),
                    cityId:$('#addDepartDialog #departCid option:selected').attr("id"),
                    districtId:$('#addDepartDialog #departDid option:selected').attr("id"),
                    subDistrictId:$('#addDepartDialog #departSDid option:selected').attr("id")
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
            $('#editDepartDialog #departCid').find('option[id='+depart["city_id"]+']').attr('selected','selected');
            var city_id = $('#editDepartDialog #departCid option:selected').attr("id");
            if(city_id){
                RequestService.get(BaseUrl+"district/districts",{id:city_id},header)
                    .done(function (data) {
                        var appendOption = "";
                        $.each(data,function (index, district) {
                            appendOption += '<option id="'+district.id+'">'+district.name+'</option>';
                        });
                        $('#editDepartDialog #departDid').html(appendOption);
                        $('#editDepartDialog #departDid').find('option[id='+depart["district_id"]+']').attr('selected','selected');
                        var district_id = $('#editDepartDialog #departDid option:selected').attr("id");
                        //get subDistrict from server
                        RequestService.get(BaseUrl+"district/sub-districts",{id:district_id},header)
                            .done(function (data) {
                                var appendOption = '';
                                $.each(data, function (index, subDistrict) {
                                    appendOption += '<option id="' + subDistrict.id + '">' + subDistrict.name + '</option>';

                                });
                                $('#editDepartDialog #departSDid').html(appendOption);
                                $('#editDepartDialog #departSDid').find('option[id='+depart["sub_district_id"]+']').attr('selected','selected');
                            });

                    });
            }

        });

        //get district and subDistrict from server in edit department dialog
        $('#editDepartDialog').on('change','#departCid',function(){
            var city_id = $('#editDepartDialog #departCid option:selected').attr("id");
            //get district from server
            RequestService.get(BaseUrl+"district/districts",{id:city_id},header)
                .done(function (data) {
                    var appendOption = "";
                    $.each(data,function (index, district) {
                        appendOption += '<option id="'+district.id+'">'+district.name+'</option>';
                    });
                    $('#editDepartDialog #departDid').html(appendOption);

                    var district_id = $('#editDepartDialog #departDid option:selected').attr("id");
                    //get subDistrict from server
                    iniDropList(BaseUrl+"district/sub-districts",district_id,header,'editDepartDialog','departSDid');
                });
        });

        //get subDistrict from server in edit department dialog
        $('#editDepartDialog').on('change','#departDid',function(){
            var district_id = $('#editDepartDialog #departDid option:selected').attr("id");
            iniDropList(BaseUrl+"district/sub-districts",district_id,header,'editDepartDialog','departSDid');
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
                    telephone: $('#editDepartDialog #departTel').val(),
                    cityId:$('#editDepartDialog #departCid option:selected').attr("id"),
                    districtId:$('#editDepartDialog #departDid option:selected').attr("id"),
                    subDistrictId:$('#editDepartDialog #departSDid option:selected').attr("id")
                };
            }else{
                toEditDepart = {
                    id: parseInt(id,10),
                    address: $('#editDepartDialog #departAddress').val(),
                    name: $('#editDepartDialog #departName').val(),
                    parentId: parent_id,
                    shortName: $('#editDepartDialog #departSpell').val(),
                    telephone: $('#editDepartDialog #departTel').val(),
                    cityId:$('#editDepartDialog #departCid option:selected').attr("id"),
                    districtId:$('#editDepartDialog #departDid option:selected').attr("id"),
                    subDistrictId:$('#editDepartDialog #departSDid option:selected').attr("id")
                };
            }
            if(parent_id === id){
                alert("父部门不能为其本身"); //forbid depart to be arranged under itself
            }else {
                RequestService.post(BaseUrl+"edit",toEditDepart,header)
                    .done(function(){
                        location.reload(true);
                    })
                    .fail(function (res) {
                        // var res = JSON.parse(data.responseText);
                        if(res["ex_code"] === "INVALID_PARENT"){
                            alert(res["message"]); //in case parent depart to be arranged under its child
                        }
                    });
            }

        });


});
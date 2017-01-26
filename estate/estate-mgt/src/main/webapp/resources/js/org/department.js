/**
 * Created by yanghong on 1/17/17.
 */
require(['main-app',contextPath + '/js/service/organization-service.js','datatables','datatablesBootstrap'],
    function (mainApp,OrganizationService) {

        var BaseUrl = "/api/department/";

        var header = {};

        var departAllDataRaw = {};


        //get city from server
        OrganizationService.queryElement({url:BaseUrl+"district/cites",header:header})
            .done(function (data) {
                var appendOption = "";
                $.each(data,function (index, city) {
                    appendOption += '<option id="'+city.id+'">'+city.name+'</option>';
                });
                $('#addDepartDialog #departCid').append(appendOption);
                $('#editDepartDialog #departCid').append(appendOption);
            });

        //get data from server and display data
        OrganizationService.queryElement({url:BaseUrl+"query-sorted",header:header})
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
                        if(departRaw.level === 0){
                            $('#addDepartDialog .listUl').append('<li class="department department-'+ index +'" index="'+departRaw.department.id+'"><span class="department-name" data-index="'+ departRaw.department.id +'">'+ departRaw.department.name +'</span><dl class="department-dl"></dl>')
                            $('#editDepartDialog .listUl').append('<li class="department department-'+ index +'" index="'+departRaw.department.id+'"><span class="department-name" data-index="'+ departRaw.department.id +'">'+ departRaw.department.name +'</span><dl class="department-dl"></dl>')
                        }else{
                            $('#addDepartDialog').find('.department[index='+departRaw.department.parent_id+']').find('>.department-dl').append('<li class="department" index="'+departRaw.department.id+'"><span class="department-name" data-index="'+ departRaw.department.id +'">'+ departRaw.department.name +'</span><dl class="department-dl"></dl>')
                            $('#editDepartDialog').find('.department[index='+departRaw.department.parent_id+']').find('>.department-dl').append('<li class="department" index="'+departRaw.department.id+'"><span class="department-name" data-index="'+ departRaw.department.id +'">'+ departRaw.department.name +'</span><dl class="department-dl"></dl>')
                        }

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
            var toAddCity = {
                id:city_id
            };
            //get district from server
            OrganizationService.getElement({url:BaseUrl+"district/districts",data:toAddCity,header:header})
                .done(function (data) {
                    var appendOption = "";
                    $.each(data,function (index, district) {
                        appendOption += '<option id="'+district.id+'">'+district.name+'</option>';
                    });
                    $('#addDepartDialog #departDid').html(appendOption);

                    var district_id = $('#addDepartDialog #departDid option:selected').attr("id");
                    //get subDistrict from server
                    OrganizationService.iniDropList(BaseUrl+"district/sub-districts",district_id,header,'addDepartDialog','departSDid');
                });
        });

        //get subDistrict from server in add department dialog
        $('#addDepartDialog').on('change','#departDid',function(){
            var district_id = $('#addDepartDialog #departDid option:selected').attr("id");
            OrganizationService.iniDropList(BaseUrl+"district/sub-districts",district_id,header,'addDepartDialog','departSDid');
        });



        //action for added department
        $('#addDepartDialog').on('click','#confirmAddDepartBtn',function(){
            var toAddDepart = {
                    address: $('#addDepartDialog #departAddress').val(),
                    name: $('#addDepartDialog #departName').val(),
                    parentId: $('#addDepartDialog .parent').attr('department'),
                    shortName: $('#addDepartDialog #departSpell').val(),
                    telephone: $('#addDepartDialog #departTel').val(),
                    cityId:$('#addDepartDialog #departCid option:selected').attr("id"),
                    districtId:$('#addDepartDialog #departDid option:selected').attr("id"),
                    subDistrictId:$('#addDepartDialog #departSDid option:selected').attr("id")
                };
            OrganizationService.updatePostDepartment({url:BaseUrl+"add",data:toAddDepart,header:header})
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
            OrganizationService.getElement({url:BaseUrl+"delete",data:{id:departId_request},header:header})
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
                $('#editDepartDialog .parent-department-form-group').show(0);
                $("#editDepartDialog .dropdown-yk .parent").text($('#editDepartDialog').find('.department[index='+pId+'] >.department-name').text());
                $("#editDepartDialog .dropdown-yk .parent").attr('department',pId);
            }else{
                // $('#editDepartDialog #departPid').prop('disabled','disabled');
                $("#editDepartDialog .dropdown-yk .parent").text('请选择');
                $("#editDepartDialog .dropdown-yk .parent").removeAttr('department');
                $('#editDepartDialog .parent-department-form-group').hide(0);
            }
            $('#editDepartDialog #departId').val(depart["id"]);
            $('#editDepartDialog #departName').val(depart["name"]);
            $('#editDepartDialog #departSpell').val(depart["short_name"]);
            $('#editDepartDialog #departTel').val(depart["telephone"]);
            $('#editDepartDialog #departAddress').val(depart["address"]);
            $('#editDepartDialog #departCid').find('option[id='+depart.city["id"]+']').attr('selected','selected');
            var city_id = $('#editDepartDialog #departCid option:selected').attr("id");
            if(city_id){
                OrganizationService.getElement({url:BaseUrl+"district/districts",data:{id:city_id},header:header})
                    .done(function (data) {
                        var appendOption = "";
                        $.each(data,function (index, district) {
                            appendOption += '<option id="'+district.id+'">'+district.name+'</option>';
                        });
                        $('#editDepartDialog #departDid').html(appendOption);
                        $('#editDepartDialog #departDid').find('option[id='+depart.district["id"]+']').attr('selected','selected');
                        var district_id = $('#editDepartDialog #departDid option:selected').attr("id");
                        //get subDistrict from server
                        OrganizationService.getElement({url:BaseUrl+"district/sub-districts",data:{id:district_id},header:header})
                            .done(function (data) {
                                var appendOption = '';
                                $.each(data, function (index, subDistrict) {
                                    appendOption += '<option id="' + subDistrict.id + '">' + subDistrict.name + '</option>';

                                });
                                $('#editDepartDialog #departSDid').html(appendOption);
                                $('#editDepartDialog #departSDid').find('option[id='+depart.sub_district["id"]+']').attr('selected','selected');
                            });

                    });
            }

        });

        //get district and subDistrict from server in edit department dialog
        $('#editDepartDialog').on('change','#departCid',function(){
            var city_id = $('#editDepartDialog #departCid option:selected').attr("id");
            //get district from server
            OrganizationService.getElement({url:BaseUrl+"district/districts",data:{id:city_id},header:header})
                .done(function (data) {
                    var appendOption = "";
                    $.each(data,function (index, district) {
                        appendOption += '<option id="'+district.id+'">'+district.name+'</option>';
                    });
                    $('#editDepartDialog #departDid').html(appendOption);

                    var district_id = $('#editDepartDialog #departDid option:selected').attr("id");
                    //get subDistrict from server
                    OrganizationService.iniDropList(BaseUrl+"district/sub-districts",district_id,header,'editDepartDialog','departSDid');
                });
        });

        //get subDistrict from server in edit department dialog
        $('#editDepartDialog').on('change','#departDid',function(){
            var district_id = $('#editDepartDialog #departDid option:selected').attr("id");
            OrganizationService.iniDropList(BaseUrl+"district/sub-districts",district_id,header,'editDepartDialog','departSDid');
        });


        //action for updated department
        $('#editDepartDialog').on('click','#confirmEditDepartBtn',function(){
            var id = $('#editDepartDialog #departId').val();
            var parent_id = $('#editDepartDialog .parent').attr('department');
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
                OrganizationService.updatePostDepartment({url:BaseUrl+"edit",data:toEditDepart,header:header})
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

        /*下拉框*/
        //点击后判断ul是否隐藏
        $(".dropdown-yk .parent").click(function(){
            var ul = $(".listUl");
            if(ul.css("display")=="none"){
                ul.slideDown(200);
            }else{
                ul.slideUp(200);
            }
            return false;
        });
        //鼠标悬停显示二级导航栏目
       /* $('.dropdown-yk li').hover(function(){
            $('dl',this).show(0);
        },function(){
            $('dl',this).hide(0);
        }).each(function(){
            var second = $(this).find('.second');
            if(second.length == '1'){
                $(this).addClass('dot');
            }
        });*/

        $('.dropdown-yk').on('mouseover mouseout','.department',function(e){
            if(event.type == "mouseover"){
                if($('>dl',this).find('.department').length>0){
                    $('>dl',this).show(0);
                }
            }else if(event.type == "mouseout"){
                $('dl',this).hide(0);
            }
        });

        /*$('.dropdown-yk dl').each(function(){
            $('dd:last',this).css('border-bottom','0');
        });*/
        $('.dropdown-yk').on('click','.department',function () {
            $(".dropdown-yk .parent").text($(this).find('>.department-name').text());
            $(".dropdown-yk .parent").attr('department',$(this).find('>.department-name').data('index'));
            $(".listUl").hide();
            return false;
        });

        //选中某个内容后赋值给p标签，并隐藏ul列表
        /*$(".dropdown-yk .add").click(function(){
            var txt = $(this).text();
            $(".dropdown-yk .parent").html(txt);
            $(".listUl").hide();
            return false;
        });*/

        $('.modal-content').on('click', function(){
            $(".listUl").hide();
        });
});
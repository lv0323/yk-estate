/**
 * Created by yanghong on 2/9/17.
 */
require(['main-app',contextPath + '/js/service/department-service.js'],
    function (mainApp, DepartmentService) {

        var header = {};
        var BaseUrl = "/api/department/";
        var departId = window.location.href.split('?')[1];

        //initialize departments in parent department selector
        DepartmentService.getDepartment(header)
            .done(function(data){
                //get city from server in add/edit dialog
                DepartmentService.getCity(header)
                    .done(function (data) {
                        var appendOption = "";
                        $.each(data,function (index, city) {
                            appendOption += '<option id="'+city.id+'">'+city.name+'</option>';
                        });
                        $('#editDepartDialog #departCid').append(appendOption);
                    });

                $.each(data,function(index,departRaw){
                    //initialize departments selection in add/edit dialog
                    if(departRaw.level === 0){
                        $('#editDepartDialog .listUl').append('<li class="department department-'+ index +'" index="'+departRaw.department.id+'"><span class="department-name" data-index="'+ departRaw.department.id +'">'+ departRaw.department.name +'</span><dl class="department-dl"></dl>')
                    }else{
                        $('#editDepartDialog').find('.department[index='+departRaw.department.parentId+']').find('>.department-dl').append('<li class="department" index="'+departRaw.department.id+'"><span class="department-name" data-index="'+ departRaw.department.id +'">'+ departRaw.department.name +'</span><dl class="department-dl"></dl>')
                    }

                    DepartmentService.getSpecifiedDepartment(departId,header)
                        .done(function (depart) {
                            var pId  = depart["parentId"];
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
                            $('#editDepartDialog #departSpell').val(depart["shortName"]);
                            $('#editDepartDialog #departTel').val(depart["telephone"]);
                            $('#editDepartDialog #departAddress').val(depart["address"]);
                            $('#editDepartDialog #departCid').find('option[id='+depart.city["id"]+']').attr('selected','selected');
                            var city_id = $('#editDepartDialog #departCid option:selected').attr("id");
                            if(city_id){
                                DepartmentService.getDistrict({data:{id:city_id}},header)
                                    .done(function (data) {
                                        var appendOption = "";
                                        $.each(data,function (index, district) {
                                            appendOption += '<option id="'+district.id+'">'+district.name+'</option>';
                                        });
                                        $('#editDepartDialog #departDid').html(appendOption);
                                        $('#editDepartDialog #departDid').find('option[id='+depart.district["id"]+']').attr('selected','selected');
                                        var district_id = $('#editDepartDialog #departDid option:selected').attr("id");
                                        //get subDistrict from server
                                        DepartmentService.getSubDistrict({data:{id:district_id}},header)
                                            .done(function (data) {
                                                var appendOption = '';
                                                $.each(data, function (index, subDistrict) {
                                                    appendOption += '<option id="' + subDistrict.id + '">' + subDistrict.name + '</option>';

                                                });
                                                $('#editDepartDialog #departSDid').html(appendOption);
                                                $('#editDepartDialog #departSDid').find('option[id='+depart.subDistrict["id"]+']').attr('selected','selected');
                                            });

                                    });
                            }
                        });

                });
            });


        //get district and subDistrict from server in edit department dialog
        $('#editDepartDialog').on('change','#departCid',function(){
            var city_id = $('#editDepartDialog #departCid option:selected').attr("id");
            //get district from server
            DepartmentService.getDistrict({data:{id:city_id}},header)
                .done(function (data) {
                    var appendOption = "";
                    $.each(data,function (index, district) {
                        appendOption += '<option id="'+district.id+'">'+district.name+'</option>';
                    });
                    $('#editDepartDialog #departDid').html(appendOption);

                    var district_id = $('#editDepartDialog #departDid option:selected').attr("id");
                    //get subDistrict from server
                    DepartmentService.iniDropListBySupId(BaseUrl+"district/sub-districts",district_id,header,'editDepartDialog','departSDid');
                });
        });

        //get subDistrict from server in edit department dialog
        $('#editDepartDialog').on('change','#departDid',function(){
            var district_id = $('#editDepartDialog #departDid option:selected').attr("id");
            DepartmentService.iniDropListBySupId(BaseUrl+"district/sub-districts",district_id,header,'editDepartDialog','departSDid');
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
                DepartmentService.editDepartment({data:toEditDepart},header)
                    .done(function(){
                        // location.reload(true);
                        window.location.href="/mgt/org/department.ftl";
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
            if($('h3.box-title').text()==='编辑部门'){
                var ul = $(".listUl");
                if(ul.css("display")=="none"){
                    ul.slideDown(200);
                }else{
                    ul.slideUp(200);
                }
                return false;
            }

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
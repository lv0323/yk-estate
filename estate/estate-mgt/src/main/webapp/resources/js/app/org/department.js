/**
 * Created by yanghong on 1/17/17.
 */
require(['main-app',contextPath + '/js/service/department-service.js',contextPath + '/js/plugins/pagination/pagingPlugin.js','dropdown'],
    function (mainApp,DepartmentService,pagingPlugin) {
        var BaseUrl = "/api/department/";
        var header = {};
        var departAllDataRaw = {};

        var pageConfig = {
            limit: 8,
            init: false
        };

        //get city from server in add/edit dialog
        var iniCityDropdown = function (data) {
            var appendOption = "";
            $.each(data,function (index, city) {
                appendOption += '<option id="'+city.id+'">'+city.name+'</option>';
            });
            $('#addDepartDialog #departCid').append(appendOption);
            $('#editDepartDialog #departCid').append(appendOption);
        };
        DepartmentService.getCity(header)
            .done(iniCityDropdown);

        //get data from server and display data
        var displayTable = function (data) {
            departAllDataRaw.departmentRaw = data.items;
            /*得到下拉框所需要的数据*/
            departAllDataRaw.dropdownData = data.items.map(function(item, index){
                return {level:item.level,name:item.name,id:item.id,parent_id:item.parentId};
            });
            /*为add窗口中下拉框添加所需要的数据*/
            $("#addDepartForm .dropdown-yk").duojiDropdown({
                data:departAllDataRaw.dropdownData
            });

            $('#departList>tbody').html(data.items.map(function (depart, index) {
                return '<tr>' +
                    '<td><a class="btn" id="lookDepartBtn" data-id="'+depart["id"]+'">'+depart["name"]+'</a></td>' +
                    '<td>'+depart["telephone"]+'</td>' +
                    '<td>'+depart["address"]+'</td>' +
                    '<td class="text-right"><a class="btn" id="editDepartBtn" data-index="'+index+'" data-id="'+depart["id"]+'" data-toggle="modal" data-target="#editDepartDialog">编辑</a>'+
                    '<span class="opt-gap"></span>'+
                    '<a class="btn" id="delDepartBtn" data-pid="'+depart["parentId"]+'" data-id="'+depart["id"]+'" data-toggle="modal" data-target="#deleteDepartDialog">删除</a></td>'+
                    '</tr>';
            }));
        };

        var pagination = function(dataTotal) {
            if(pageConfig.init){
                return;
            }
            pageConfig.init = true;
            var config = {
                pagingId:'#departList_paging',
                totalCounts:dataTotal,
                pageSize: pageConfig.limit,
                onChange: function (num, type) {
                    getDepartment((num-1)*pageConfig.limit, pageConfig.limit);
                }
            };
            pagingPlugin.init(config);

        };

        function getDepartment(offset, limit) {
            DepartmentService.getDepartment({'x-paging': 'total=true&offset='+offset+'&limit=' + limit})
                .done(function(data){
                    displayTable(data);
                    pagination(data.total);
                })
                .fail(function(){
                    $('#departList>tbody').append('<tr><td colspan="4">无法获取数据</td></tr>');
                });
        }

        getDepartment(0, pageConfig.limit);

        //open new window for department details
        $('#departList').on('click','#lookDepartBtn',function(e){
            var departId = $(e.target).data('id');
            window.location.href="/mgt/org/departmentDetail.ftl?"+departId;
        });

        //open new window for department details
        $('#departList').on('click','#editDepartBtn',function(e){
            var departId = $(e.target).data('id');
            window.location.href="/mgt/org/editDepartmentDetail.ftl?"+departId;
        });

        //get district and subDistrict from server in add department dialog
        $('#addDepartDialog').on('change','#departCid',function(){
            var city_id = $('#addDepartDialog #departCid option:selected').attr("id");

            //get district from server
            DepartmentService.getDistrict({data:{id:city_id}},header)
                .done(function (data) {
                    var appendOption = "";
                    $.each(data,function (index, district) {
                        appendOption += '<option id="'+district.id+'">'+district.name+'</option>';
                    });
                    $('#addDepartDialog #departDid').html(appendOption);

                    var district_id = $('#addDepartDialog #departDid option:selected').attr("id");
                    //get subDistrict from server
                    DepartmentService.iniDropListBySupId(BaseUrl+"district/sub-districts",district_id,header,'addDepartDialog','departSDid');
                });
        });

        //get subDistrict from server in add department dialog
        $('#addDepartDialog').on('change','#departDid',function(){
            var district_id = $('#addDepartDialog #departDid option:selected').attr("id");
            DepartmentService.iniDropListBySupId(BaseUrl+"district/sub-districts",district_id,header,'addDepartDialog','departSDid');
        });



        //action for added department
        $('#addDepartDialog').on('click','#confirmAddDepartBtn',function(){
            var toAddDepart = {
                    address: $('#addDepartDialog #departAddress').val(),
                    name: $('#addDepartDialog #departName').val(),
                    parentId: $('#addDepartDialog .dropdown-yk').attr('selectedValue'),
                    // shortName: $('#addDepartDialog #departSpell').val(),
                    telephone: $('#addDepartDialog #departTel').val(),
                    cityId:$('#addDepartDialog #departCid option:selected').attr("id"),
                    districtId:$('#addDepartDialog #departDid option:selected').attr("id"),
                    subDistrictId:$('#addDepartDialog #departSDid option:selected').attr("id")
                };
            DepartmentService.addDepartment({data:toAddDepart},header)
                .done(function(){
                    // location.reload(true);
                    window.location.href="/mgt/org/department.ftl";
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
            DepartmentService.deleteDepartment({data:{id:departId_request}},header)
                .done(function(){
                    location.reload(true);
                });
        });

        //initialize title and default value in edit department dialog
        /*$('#departList').on('click','#editDepartBtn',function(e){
            var index = $(e.target).data('index');
            var depart = departAllDataRaw.departmentRaw[index].department;
            var pId  = depart["parent_id"];
            var dropdownData = departAllDataRaw.departmentRaw.map(function(item, index){
                return {level:item.level,name:item.department.name,id:item.department.id,parent_id:item.department.parent_id};
            });
            $('#editDepartDialog #editDepartLabel').text('编辑部门');
            if(pId){
                // $('#editDepartDialog #departPid').prop('disabled',false);
                $('#editDepartDialog .parent-department-form-group').show(0);
                $("#editDepartDialog .dropdown-yk").duojiDropdown({
                    data:departAllDataRaw.dropdownData,
                    currentValue:pId,
                });
            }else{
                // $('#editDepartDialog #departPid').prop('disabled','disabled');
                $("#editDepartDialog .dropdown-yk").duojiDropdown({
                    data:departAllDataRaw.dropdownData,
                });
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
                                $('#editDepartDialog #departSDid').find('option[id='+depart.sub_district["id"]+']').attr('selected','selected');
                            });

                    });
            }

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
            var parent_id = $('#editDepartDialog .dropdown-yk').attr('selectedValue');
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
                        location.reload(true);
                    })
                    .fail(function (res) {
                        // var res = JSON.parse(data.responseText);
                        if(res["ex_code"] === "INVALID_PARENT"){
                            alert(res["message"]); //in case parent depart to be arranged under its child
                        }
                    });
            }
        });*/
});


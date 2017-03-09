/**
 * Created by yanghong on 2/9/17.
 */
require(['main-app',
        contextPath + '/js/app/org/department/departCommon.js',
        contextPath + '/js/service/department-service.js',
        contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js',
        'select'],
    function (mainApp, DepartCommon, DepartmentService, SweetAlertHelp) {

        var header = {};
        var departId = window.location.href.split('?')[1];

        DepartCommon.registerOnChangeForLocationSelector();

        function loadDepart(depart, city_id, district_id) {
            DepartCommon.reloadDistrict(city_id).done(function () {
                $('#departDid').find('option[id='+depart["districtId"]+']').attr('selected','selected');
                DepartCommon.reloadSubDistrict(district_id).done(function () {
                    $('#departSDid').find('option[id='+depart["subDistrictId"]+']').attr('selected','selected');
                    $('.selectpicker').selectpicker({
                        style: 'btn-default',
                        dropupAuto:false,
                        size: 8
                    });
                });
            });


            $('#departId').val(depart["id"]);
            $('#departName').val(depart["name"]);
            //$('#departSpell').val(depart["shortName"]);
            $('#departTel').val(depart["telephone"]);
            $('#departAddress').val(depart["address"]);
        }


        DepartmentService.getSpecifiedDepartment(departId,header)
            .done(function (depart) {
                var current_depart_pid = depart["parentId"];
                DepartCommon.initDepartSelector(current_depart_pid);
                DepartCommon.reloadCity().done(function () {
                    $('#departCid').find('option[id='+depart["cityId"]+']').attr('selected','selected');
                    var city_id = depart["cityId"];
                    var district_id = depart["districtId"];
                    loadDepart(depart, city_id, district_id);
                });

            });


        //action for updated department
        $('#confirmEditDepartBtn').on('click', function(){
            var id = $('#editDepartDialog #departId').val();
            // var parent_id = $('#editDepartDialog .parent').attr('department');
            var parent_id = $('#editDepartDialog .dropdown-yk').attr('selectedvalue');
            var toEditDepart = {};
            if(parent_id){
                toEditDepart = {
                    id: parseInt(id,10),
                    address: $('#editDepartDialog #departAddress').val(),
                    name: $('#editDepartDialog #departName').val(),
                    parentId: parseInt(parent_id,10),
                    //shortName: $('#editDepartDialog #departSpell').val(),
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
                    parentId: 0,
                    //shortName: $('#editDepartDialog #departSpell').val(),
                    telephone: $('#editDepartDialog #departTel').val(),
                    cityId:$('#editDepartDialog #departCid option:selected').attr("id"),
                    districtId:$('#editDepartDialog #departDid option:selected').attr("id"),
                    subDistrictId:$('#editDepartDialog #departSDid option:selected').attr("id")
                };
            }

            if(DepartCommon.verifyDepartmentInput(toEditDepart)){
                DepartmentService.editDepartment({data:toEditDepart},header)
                    .done(function(){
                        SweetAlertHelp.success({}, function () {
                            window.location.href="/mgt/org/department";
                        });
                    })
                    .fail(function (res) {
                        SweetAlertHelp.fail(res);

                    });
            }else {
                SweetAlertHelp.fail({message:"请填写所有必填字段"});
            }


        });

        /*下拉框*/
        //点击后判断ul是否隐藏 if($('h3.box-title').text()==='编辑部门'){
});
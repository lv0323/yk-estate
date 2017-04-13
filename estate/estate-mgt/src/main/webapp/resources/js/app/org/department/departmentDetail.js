/**
 * Created by yanghong on 2/9/17.
 */
require(['main-app',
        contextPath + '/js/app/org/department/departCommon.js',
        contextPath + '/js/service/department-service.js',
        contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js',
        'select', 'chosen'],
    function (mainApp, DepartCommon, DepartmentService, SweetAlertHelp) {

        var header = {};
        var departId = window.location.href.split('?')[1];

        //DepartCommon.registerOnChangeForLocationSelector();

        function loadDepart(depart) {
            $('#departCid option[value="'+depart["cityId"]+'"]').prop('selected', true).attr('selected','selected');
            $("#departCid").trigger("chosen:updated");
            DepartCommon.chosenChange('departCid', depart["cityId"]).done(function () {
                $('#departDid option[value="'+depart["districtId"]+'"]').prop('selected', true).attr('selected','selected');
                $("#departDid").trigger("chosen:updated");
                DepartCommon.chosenChange('departDid', depart["districtId"]).done(function () {
                    $('#departSDid option[value="'+depart["subDistrictId"]+'"]').prop('selected', true).attr('selected','selected');
                    $("#departSDid").trigger("chosen:updated");
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
                DepartCommon.initDepartSelector(current_depart_pid).done(function () {
                    $('.superiorDepart .dropdown-yk').off('click');
                    $('.duoji-dropdown-input').off('click');
                });

                DepartCommon.initAndRegisterChangeEvent().done(function () {
                    loadDepart(depart);
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
                    cityId:$('#editDepartDialog #departCid option:selected').val(),
                    districtId:$('#editDepartDialog #departDid option:selected').val(),
                    subDistrictId:$('#editDepartDialog #departSDid option:selected').val()
                };
            }else{
                toEditDepart = {
                    id: parseInt(id,10),
                    address: $('#editDepartDialog #departAddress').val(),
                    name: $('#editDepartDialog #departName').val(),
                    parentId: 0,
                    //shortName: $('#editDepartDialog #departSpell').val(),
                    telephone: $('#editDepartDialog #departTel').val(),
                    cityId:$('#editDepartDialog #departCid option:selected').val(),
                    districtId:$('#editDepartDialog #departDid option:selected').val(),
                    subDistrictId:$('#editDepartDialog #departSDid option:selected').val()
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
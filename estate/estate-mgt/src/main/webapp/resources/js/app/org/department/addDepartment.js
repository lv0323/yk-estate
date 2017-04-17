/**
 * Created by yanghong on 2/15/17.
 */
require(['main-app',
        contextPath + '/js/app/org/department/departCommon.js',
        contextPath + '/js/service/department-service.js',
        contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js'],

    function (mainApp,DepartCommon, DepartmentService, SweetAlertHelp){

        var header = {};

        DepartCommon.initDepartSelector();
        DepartCommon.initAndRegisterChangeEvent();

        //action for added department
        $('#confirmAddDepartBtn').on('click', function(){
            var toAddDepart = {
                address: $('#departAddress').val(),
                name: $('#departName').val(),
                parentId: $('.dropdown-yk').attr('selectedValue'),
                // shortName: $('#addDepartDialog #departSpell').val(),
                telephone: $('#departTel').val(),
                cityId:$('#departCid option:selected').val(),
                districtId:$('#departDid option:selected').val(),
                subDistrictId:$('#departSDid option:selected').val()
            };

            if(DepartCommon.verifyDepartmentInput(toAddDepart)){
                DepartmentService.addDepartment({data:toAddDepart},header)
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


    });
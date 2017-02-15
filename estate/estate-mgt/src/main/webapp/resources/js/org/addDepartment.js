/**
 * Created by yanghong on 2/15/17.
 */
require(['main-app',contextPath + '/js/utils/departCommon.js', contextPath + '/js/service/department-service.js'],

    function (mainApp,DepartCommon, DepartmentService){

        var header = {};

        DepartCommon.initDepartSelector();
        DepartCommon.initLocationSelector();
        DepartCommon.registerOnChangeForLocationSelector();

        //action for added department
        $('#confirmAddDepartBtn').on('click', function(){
            var toAddDepart = {
                address: $('#departAddress').val(),
                name: $('#departName').val(),
                parentId: $('.dropdown-yk').attr('selectedValue'),
                // shortName: $('#addDepartDialog #departSpell').val(),
                telephone: $('#departTel').val(),
                cityId:$('#departCid option:selected').attr("id"),
                districtId:$('#departDid option:selected').attr("id"),
                subDistrictId:$('#departSDid option:selected').attr("id")
            };
            DepartmentService.addDepartment({data:toAddDepart},header)
                .done(function(){
                    // location.reload(true);
                    window.location.href="/mgt/org/department.ftl";
                })
                .fail(function (res) {
                    alert(res["message"]);
                });
        });


    });
/**
 * Created by yanghong on 2/16/17.
 */
define(contextPath+'/js/app/org/department/departCommon.js',
    ['main-app', contextPath + '/js/service/department-service.js', 'locationUtil', 'dropdown'],
    function (mainApp,DepartmentService) {

        var DepartCommon = {};

        var header = {};
        var dropdownData = {};

        DepartCommon.initDepartSelector = function (currentDepartPId) {
            DepartmentService.getAllDepartment(header).done(function (data) {
                dropdownData = data.map(function (item, index) {
                    return {level: item.level, name: item.name, id: item.id, parent_id: item.parentId};
                });

                //showing empty input for adding Depart
                if(typeof(currentDepartPId) == 'undefined'){
                    $(".dropdown-yk").duojiDropdown({
                        data: dropdownData
                    });
                }else if (currentDepartPId != 0){
                    $(".dropdown-yk").duojiDropdown({
                        data: dropdownData,
                        currentId:currentDepartPId
                    });
                }else {
                    $('.form-group').has(".dropdown-yk").css('display','none');
                }
            });
        };

        var initDistrict = function (city_id) {
            //then fill district selector
            DepartmentService.getDistrict({data:{id:city_id}},header)
                .done(function (data) {
                    $('#departDid').LocationDropdown(data);
                    var district_id = $('#departDid option:selected').attr("id");

                    //then fill subDistrict selector
                    DepartmentService.getSubDistrict({data:{id:district_id}},header)
                        .done(function (data) {
                            $('#departSDid').LocationDropdown(data);
                        })
                });
        };

        DepartCommon.reloadCity = function () {
            var defer =$.Deferred();
            DepartmentService.getCity(header)
                .done(function (data) {
                $('#departCid').LocationDropdown(data);
                    defer.resolve(data);
            });
            return defer.promise();
        };

        DepartCommon.reloadDistrict = function (city_id) {
            var defer =$.Deferred();
            DepartmentService.getDistrict({data:{id:city_id}},header)
                .done(function (data) {
                    $('#departDid').LocationDropdown(data);
                    defer.resolve(data);
                });
            return defer.promise();
        };

        DepartCommon.reloadSubDistrict = function (district_id) {
            var defer =$.Deferred();
            DepartmentService.getSubDistrict({data:{id:district_id}},header)
                .done(function (data) {
                    $('#departSDid').LocationDropdown(data);
                    defer.resolve(data);
                });
            return defer.promise();
        };

        //fill city/district/subDistrict selector
        DepartCommon.initLocationSelector = function () {
            DepartmentService.getCity(header).done(function (data) {
                $('#departCid').LocationDropdown(data);
                var city_id = $('#departCid option:selected').attr("id");
                initDistrict(city_id);
            });
        };

        //register onChangeEvents for location selector
        DepartCommon.registerOnChangeForLocationSelector = function () {
            //register onChange event for city selector
            $('#departCid').on('change', function() {
                var city_id = $('#departCid option:selected').attr("id");
                initDistrict(city_id);
            });

            //register onChange event for depart selector
            $('#departDid').on('change', function(){
                var district_id = $('#departDid option:selected').attr("id");
                //then fill subDistrict selector
                DepartmentService.getSubDistrict({data:{id:district_id}},header)
                    .done(function (data) {
                        $('#departSDid').LocationDropdown(data);
                    });
            });
        };



        return DepartCommon;
    });
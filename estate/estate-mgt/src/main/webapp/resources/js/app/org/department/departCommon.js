/**
 * Created by yanghong on 2/16/17.
 */
define(contextPath+'/js/app/org/department/departCommon.js',
    ['main-app', contextPath + '/js/service/department-service.js', 'locationUtil', 'dropdown','chosen'],
    function (mainApp,DepartmentService) {

        var DepartCommon = {};

        var header = {};
        var dropdownData = {};

        var chosenConfig = {
            departCid: {
                init: false
            },
            departDid: {
                init: false
            },
            departSDid: {
                init: false
            }
        };

        DepartCommon.chosenChange = function(key, value){
            var defer = $.Deferred();
            if(key === 'departCid'){
                DepartCommon.reloadDistrict(value).done(function () {
                    initChosen('#departDid', 'departDid');
                    defer.resolve(value);
                });
            }
            if(key === 'departDid'){
                DepartCommon.reloadSubDistrict(value).done(function () {
                    initChosen('#departSDid', 'departSDid');
                    defer.resolve(value);
                })
            }
            return defer;
        };

        function initChosen(id, key){
            $(id).chosen("destroy");
            if(!chosenConfig[key].init){
                chosenConfig[key].init = !chosenConfig[key].init;
                $(id).chosen({disable_search_threshold: 10}).change(function(e, result){
                    DepartCommon.chosenChange(key, result.selected);
                });
            }
            $(id).chosen({disable_search_threshold: 10});
            $(id).trigger('chosen:updated');
        }

        DepartCommon.initAndRegisterChangeEvent = function () {
            var defer = $.Deferred();
            DepartCommon.reloadCity().done(function () {
                initChosen('#departCid', 'departCid');

                var city_id = $('#departCid option:selected').val();
                DepartCommon.reloadDistrict(city_id).done(function () {
                    initChosen('#departDid', 'departDid');

                    var district_id = $('#departDid option:selected').val();
                    DepartCommon.reloadSubDistrict(district_id).done(function () {
                        initChosen('#departSDid', 'departSDid');
                        defer.resolve(district_id);
                    });
                });
            });
            return defer;
        };

        DepartCommon.initDepartSelector = function (currentDepartPId) {
            var defer = $.Deferred();
            DepartmentService.getAllDepartment(header).done(function (data) {
                dropdownData = data.map(function (item, index) {
                    return {level: item.level, name: item.name, id: item.id, parent_id: item.parentId};
                });

                //showing empty input for adding Depart
                if(typeof(currentDepartPId) == 'undefined'){
                    $(".superiorDepart .dropdown-yk").duojiDropdown({
                        data: dropdownData
                    });
                }else if (currentDepartPId != 0){
                    $(".superiorDepart .dropdown-yk").duojiDropdown({
                        data: dropdownData,
                        currentId:currentDepartPId
                    });
                }else {
                    $(".superiorDepart .dropdown-yk").duojiDropdown({
                        data: dropdownData,
                        currentId:currentDepartPId
                    });
                    $('#editDepartDialog .form-group').has('.superiorDepart .dropdown-yk').css('display','none');
                }
                defer.resolve(dropdownData);
            });
            return defer;
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

        DepartCommon.verifyDepartmentInput = function (toSubmitDepart) {
            var flag = true;
            $('.form-group').find('.invalid-input').removeClass('invalid-input');
            if (toSubmitDepart.address==="" || typeof(toSubmitDepart.address)==='undefined') {
                flag = false;
                $('#departAddress').addClass('invalid-input');
            }
            if (toSubmitDepart.name==="" || typeof(toSubmitDepart.name)==='undefined') {
                flag = false;
                $('#departName').addClass('invalid-input');
            }
            if (toSubmitDepart.parentId===""|| typeof(toSubmitDepart.parentId)==='undefined') {
                flag = false;
                $('.dropdown-yk').addClass('invalid-input');
            }
            if (toSubmitDepart.telephone===""|| typeof(toSubmitDepart.telephone)==='undefined') {
                flag = false;
                $('#departTel').addClass('invalid-input');
            }
            if (toSubmitDepart.cityId===""|| typeof(toSubmitDepart.cityId)==='undefined') {
                flag = false;
                $('#departCid').addClass('invalid-input');
            }
            if (toSubmitDepart.districtId===""|| typeof(toSubmitDepart.districtId)==='undefined') {
                flag = false;
                $('#departDid').addClass('invalid-input');
            }
            if (toSubmitDepart.subDistrictId===""|| typeof(toSubmitDepart.subDistrictId)==='undefined') {
                flag = false;
                $('#departSDid').addClass('invalid-input');
            }
            return flag;
        };



        return DepartCommon;
    });
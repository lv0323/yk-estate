/**
 * Created by yanghong on 3/15/17.
 */
/**
 * Created by yanghong on 3/14/17.
 */
require(['main-app',
        contextPath + '/js/service/contract-service.js',
        contextPath + '/js/service/propertyvisit-service.js',
        contextPath + '/js/service/fang-service.js',
        contextPath + '/js/service/department-service.js',
        contextPath + '/js/service/employee-service.js',
        contextPath + '/js/service/util-service.js',
        contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js', 'chosen'],
    function (mainApp, ContractService, PropertyVisitService, FangService, DepartmentService, EmployeeService, UtilService, SweetAlertHelp) {

        var chosenConfig = {
            departmentId: {
                init: false
            },
            employeeId: {
                init: false
            }
        };

        function verifyAddDeal(toSubmitDeal) {
            var flag = true;
            $('.form-group').find('.invalid-input').removeClass('invalid-input');
            if (toSubmitDeal.fangId === "" || typeof(toSubmitDeal.fangId) === 'undefined') {
                flag = false;
                $('#houseLicenceID').addClass('invalid-input');
                $('#areaSize').addClass('invalid-input');
            }
            if (toSubmitDeal.price === "" || typeof(toSubmitDeal.price) === 'undefined') {
                flag = false;
                $('#dealPrice').addClass('invalid-input');
            }
            if (toSubmitDeal.assignorName === "" || typeof(toSubmitDeal.assignorName) === 'undefined') {
                flag = false;
                $('#OwnerName').addClass('invalid-input');
            }
            if (toSubmitDeal.assignorIdNo === "" || typeof(toSubmitDeal.assignorIdNo) === 'undefined') {
                flag = false;
                $('#OwnerIdNo').addClass('invalid-input');
            }
            if (toSubmitDeal.assignorMobile === "" || typeof(toSubmitDeal.assignorMobile) === 'undefined') {
                flag = false;
                $('#OwnerContact').addClass('invalid-input');
            }
            if (toSubmitDeal.assigneeName === "" || typeof(toSubmitDeal.assigneeName) === 'undefined') {
                flag = false;
                $('#customerName').addClass('invalid-input');
            }
            if (toSubmitDeal.assigneeIdNo === "" || typeof(toSubmitDeal.assigneeIdNo) === 'undefined') {
                flag = false;
                $('#customerIdNo').addClass('invalid-input');
            }
            if (toSubmitDeal.assigneeMobile === "" || typeof(toSubmitDeal.assigneeMobile) === 'undefined') {
                flag = false;
                $('#customerContact').addClass('invalid-input');
            }
            if (toSubmitDeal.employeeId === "" || typeof(toSubmitDeal.employeeId) === 'undefined') {
                flag = false;
                $('#employeePosition').addClass('invalid-input');
                $('#employeeMobile').addClass('invalid-input');
                $('#employeeIdNo').addClass('invalid-input');

            }
            return flag;
        }
        
        function getAgentInfo(employeeId) {
            EmployeeService.getSpecifiedEmployee(employeeId).done(function (data) {
                    $('#employeePosition').text(data.positionName);
                    $('#employeeMobile').text(data.mobile);
                    $('#employeeIdNo').text(data.idcardNumber);
                });
        }

        function iniEmployeeDropDown(value) {
            var empListOption = "";
            var defaultOption = [];
            defaultOption.push("<option value=''>选择员工</option>");
            if(value.length>0){
                EmployeeService.getAllEmployee({departmentId: value}).done(function (response) {
                    if(response && response && response.length>0){
                        empListOption = response.map(function(item){
                            return "<option value='"+item.id+"'>"+item.name+"</option>";
                        });
                    }
                    var fullEmpListOption = defaultOption.concat(empListOption);
                    $('#employeeList').html(fullEmpListOption);
                    initChosen("#employeeList", 'employeeId');

                }).fail(function(){
                    $('#employeeList').html(defaultOption);
                    initChosen("#employeeList", 'employeeId');
                });
            }else {
                $('#employeeList').html(defaultOption);
                initChosen("#employeeList", 'employeeId');
            }

        }

        function chosenChange(key, value){
            if(key === 'departmentId'){
                iniEmployeeDropDown(value);
            }
        }

        function initChosen(id, key){
            $(id).chosen("destroy");
            if(!chosenConfig[key].init){
                chosenConfig[key].init = !chosenConfig[key].init;
                $(id).chosen().change(function(e, result){
                    chosenChange(key, result.selected);
                    if(key === 'employeeId'){
                        getAgentInfo(result.selected);
                    }
                    if(key === 'departmentId'){
                        $('#employeePosition').text("");
                        $('#employeeMobile').text("");
                        $('#employeeIdNo').text("");
                    }
                });
            }
            $(id).chosen();
            $(id).trigger('chosen:updated');
        }

        /*init depListOption in filter*/
        function initDepartDropDown() {
            var depListOption = "";
            DepartmentService.getAllDepartment().done(function(data){
                depListOption =data.map(function(item, index){
                    var indent = "";
                    for(var i = 0;i<item.level;i++){
                        indent += "   ";
                    }
                    return "<option value='"+item.id+"'>"+indent+ item.name+"</option>";
                });
                $('#departmentList').append(depListOption);
                initChosen("#departmentList", 'departmentId');

            });
        }

        initChosen("#employeeList", 'employeeId');
        initDepartDropDown();

        $('#houseLicenceID').val(UtilService.getUrlParam('licenceId'));

        $('#businessType').on('change', function () {
            var bizType = $('#businessType option:selected').val();
            $('#priceUnit'+bizType).show();
            $('#priceUnit'+bizType).siblings().hide();
        });

        $('#getHouseInfoBtn').on('click',function () {
            $('.form-group').find('.invalid-input').removeClass('invalid-input');
            var houseLicenceID = $('#houseLicenceID').val();
            PropertyVisitService.getPropertyInfo({licenceId:houseLicenceID})
                .done(function (data) {
                    $('#fangID').val(data.id);
                    $('#areaSize').val(data.estateArea);
                    $('#houseType').val(data.houseType.name);
                    $('#businessType').val(data.bizType.name);
                    $('#priceUnit'+data.bizType.name).show();
                    $('#priceUnit'+data.bizType.name).siblings().hide();
                    var fangID = $('#fangID').val();
                    FangService.ext({fangId:fangID})
                        .done(function (data) {
                            $('#certifAddress').val(data.certifAddress);
                            $('#certifNo').val(data.certifNo);
                        })
                        .fail(function (res) {
                            SweetAlertHelp.fail(res);
                        });
                    FangService.contact({fangId:fangID})
                        .done(function (data) {
                            $('#OwnerName').val(data.name);
                            $('#OwnerContact').val(data.mobile);
                        })
                        .fail(function (res) {
                            SweetAlertHelp.fail(res);
                        });
                })
                .fail(function (res) {
                    SweetAlertHelp.fail(res);
                    $('input').val("");
                });
        });
        $('#confirmAddDealBtn').on('click',function () {
            var toAddDeal = {
                fangId: $('#fangID').val(),
                contractType: 'DEAL',
                houseType: $('#houseType option:selected').val(),
                certifAddress: $('#certifAddress').val(),
                certifNo: $('#certifNo').val(),
                estateArea: $('#areaSize').val(),
                price: $('#dealPrice').val(),
                priceUnit: $('select[name="priceUnit"]:visible').val(),
                bizType: $('#businessType option:selected').val(),
                assignorName: $('#OwnerName').val(),
                assignorMobile: $('#OwnerContact').val(),
                assignorIdSource: $('#OwnerIdSource option:selected').val(),
                assignorIdNo: $('#OwnerIdNo').val(),
                assigneeName: $('#customerName').val(),
                assigneeMobile: $('#customerContact').val(),
                assigneeIdSource: $('#customerIdSource option:selected').val(),
                assigneeIdNo: $('#customerIdNo').val(),
                employeeId: $('#employeeList option:selected').val(),
                note: $('#note').val()
            };
            if(verifyAddDeal(toAddDeal)){
                ContractService.addDeal(toAddDeal)
                    .done(function () {
                        SweetAlertHelp.success({}, function () {
                            window.location.href="/mgt/contract/deal";
                        });
                    }).fail(function (res) {
                    SweetAlertHelp.fail(res);
                });
            }else {
                SweetAlertHelp.fail({message:"请填写所有必填字段"});
            }
        });
        /*从url中获取*/

    });

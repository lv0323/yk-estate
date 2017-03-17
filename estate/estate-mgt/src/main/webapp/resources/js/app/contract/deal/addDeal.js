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
        contextPath + '/js/service/util-service.js',
        contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js'],
    function (mainApp, ContractService, PropertyVisitService, FangService, UtilService, SweetAlertHelp) {
        function verifyAddDeal(toSubmitDeal) {
            var flag = true;
            $('.form-group').find('.invalid-input').removeClass('invalid-input');
            if (toSubmitDeal.fangId === "" || typeof(toSubmitDeal.fangId) === 'undefined') {
                flag = false;
                $('#houseLicenceID').addClass('invalid-input');
                /*$('#certifAddress').addClass('invalid-input');
                 $('#certifNo').addClass('invalid-input');*/
                $('#areaSize').addClass('invalid-input');
                $('#totalPrice').addClass('invalid-input');
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
            return flag;
        }
        $('#houseLicenceID').val(UtilService.getUrlParam('licenceId'));
        $('#getHouseInfoBtn').on('click',function () {
            $('.form-group').find('.invalid-input').removeClass('invalid-input');
            var houseLicenceID = $('#houseLicenceID').val();
            PropertyVisitService.getPropertyInfo({licenceId:houseLicenceID})
                .done(function (data) {
                    $('#fangID').val(data.id);
                    $('#areaSize').val(data.estateArea);
                    $('#totalPrice').val(data.publishPrice);
                    $('#priceUnit').val(data.priceUnit.name);
                    $('#houseType').val(data.houseType.name);
                    $('#businessType').val(data.bizType.name);
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
                price: $('#totalPrice').val(),
                priceUnit: $('#priceUnit option:selected').val(),
                bizType: $('#businessType option:selected').val(),
                assignorName: $('#OwnerName').val(),
                assignorMobile: $('#OwnerContact').val(),
                assignorIdSource: $('#OwnerIdSource option:selected').val(),
                assignorIdNo: $('#OwnerIdNo').val(),
                assigneeName: $('#customerName').val(),
                assigneeMobile: $('#customerContact').val(),
                assigneeIdSource: $('#customerIdSource option:selected').val(),
                assigneeIdNo: $('#customerIdNo').val(),
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

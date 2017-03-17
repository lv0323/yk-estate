/**
 * Created by yanghong on 3/16/17.
 */
require(['main-app',
        contextPath + '/js/service/contract-service.js',
        contextPath+'/js/service/util-service.js'],
    function (mainApp, ContractService, UtilService) {
        var dealId = UtilService.getUrlParam('dealId');
        ContractService.getSpecifiedDeal(dealId).done(function (data) {
            $('#fangID').val(data.fangId);
            $('#houseLicenceID').val(data.fangTiny.houseLicence.id);
            $('#areaSize').val(data.estateArea);
            $('#totalPrice').val(data.price);
            $('#priceUnit').html(data.priceUnit.label);
            $('#houseType').html(data.houseType.label);
            $('#businessType').html(data.bizType.label);
            $('#certifAddress').val(data.certifAddress);
            $('#certifNo').val(data.certifNo);
            $('#OwnerName').val(data.assignorName);
            $('#OwnerIdSource').html(data.assignorIdSource.label);
            $('#OwnerIdNo').val(data.assignorIdNo);
            $('#OwnerContact').val(data.assignorMobile);
            $('#customerName').val(data.assigneeName);
            $('#customerIdSource').html(data.assigneeIdSource.label);
            $('#customerIdNo').val(data.assigneeIdNo);
            $('#customerContact').val(data.assigneeMobile);
            $('#note').val(data.note);
            $('input,textarea').prop('disabled',true);
        });
    });
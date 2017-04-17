/**
 * Created by yanghong on 3/2/17.
 */
require(['main-app',
        contextPath + '/js/service/propertyvisit-service.js',
        contextPath + '/js/service/util-service.js',
        contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js', 'chosen'],
    function (mainApp, PropertyVisitService,UtilService, SweetAlertHelp) {

        var chosenConfig = {
            customerSource: {
                init: false
            }
        };

        $('#houseLicenceID').val(UtilService.getUrlParam('licenceId'));

        function initChosen(id, key){
            $(id).chosen("destroy");
            if(!chosenConfig[key].init){
                chosenConfig[key].init = !chosenConfig[key].init;
            }
            $(id).chosen({disable_search_threshold: 10});
            $(id).trigger('chosen:updated');
        }
        initChosen('#customerSource', 'customerSource');

        function verifyAddPropertyVisitInput(toSubmitPropertyVisit) {
            var flag = true;
            $('.form-group').find('.invalid-input').removeClass('invalid-input');
            if (toSubmitPropertyVisit.fangId === "" || typeof(toSubmitPropertyVisit.fangId) === 'undefined') {
                flag = false;
                $('#houseLicenceID').addClass('invalid-input');
                $('#houseDict').addClass('invalid-input');
                $('#building').addClass('invalid-input');
                $('#unit').addClass('invalid-input');
                $('#roomNo').addClass('invalid-input');
                $('#areaSize').addClass('invalid-input');
                $('#totalPrice').addClass('invalid-input');
                $('#unitPrice').addClass('invalid-input');
                $('#bedRoom').addClass('invalid-input');
                $('#livingRoom').addClass('invalid-input');
                $('#kitchen').addClass('invalid-input');
                $('#bathRoom').addClass('invalid-input');
                $('#balcony').addClass('invalid-input');
            }
            if (toSubmitPropertyVisit.customerName === "" || typeof(toSubmitPropertyVisit.customerName) === 'undefined') {
                flag = false;
                $('#customerName').addClass('invalid-input');
            }
            if (toSubmitPropertyVisit.customerSource === "" || typeof(toSubmitPropertyVisit.customerSource) === 'undefined') {
                flag = false;
                $('#customerSource').addClass('invalid-input');
            }
            if (toSubmitPropertyVisit.customerMobile === "" || typeof(toSubmitPropertyVisit.customerMobile) === 'undefined') {
                flag = false;
                $('#customerContact').addClass('invalid-input');
            }
            return flag;
        }

        $('#getHouseInfoBtn').on('click',function () {
            $('.form-group').find('.invalid-input').removeClass('invalid-input');
            var houseLicenceID = $('#houseLicenceID').val();
            PropertyVisitService.getPropertyInfo({licenceId:houseLicenceID})
                .done(function (data) {
                    $('#fangID').val(data.id);
                    $('#areaSize').val(data.estateArea);
                    $('#totalPrice').val(data.publishPrice);
                    $('#totalPriceUnit').html(data.priceUnit.label);
                    $('#unitPrice').val(data.unitPrice);
                    $('#bedRoom').val(data.sCounts);
                    $('#livingRoom').val(data.tCounts);
                    $('#kitchen').val(data.cCounts);
                    $('#bathRoom').val(data.wCounts);
                    $('#balcony').val(data.ytCounts);
                })
                .fail(function (res) {
                    SweetAlertHelp.fail(res);
                });
        });

        $('#confirmAddPropertyVisitBtn').on('click',function () {
            var toAddPropertyVisit = {
                fangId: $('#fangID').val(),
                customerName: $('#customerName').val(),
                customerSource: $('#customerSource option:selected').val(),
                customerMobile: $('#customerContact').val()
            };

            if(verifyAddPropertyVisitInput(toAddPropertyVisit)){
                PropertyVisitService.addPropertyVisit(toAddPropertyVisit)
                    .done(function () {
                        SweetAlertHelp.success({}, function () {
                            window.location.href="/mgt/propertyVisit/propertyVisit";
                        });
                    }).fail(function (res) {
                        SweetAlertHelp.fail(res);
                    });

            }else {
                SweetAlertHelp.fail({message:"请填写所有必填字段"});
            }

        });

    });
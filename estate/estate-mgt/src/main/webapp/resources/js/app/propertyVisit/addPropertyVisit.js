/**
 * Created by yanghong on 3/2/17.
 */
require(['main-app',
        contextPath + '/js/service/propertyvisit-service.js', 'sweetalert'],
    function (mainApp, PropertyVisitService) {

        // var houseLicenceID = window.location.href.split('?')[1];

        function verifyAddPropertyVisitInput(toSubmitPropertyVisit) {
            var flag = true;
            $('.form-group').find('.invalid-input').removeClass('invalid-input');
            /*if (toSubmitPropertyVisit.==="" || typeof(toSubmitPropertyVisit.)==='undefined') {
                flag = false;
                $('#houseDict').addClass('invalid-input');
            }
            if (toSubmitPropertyVisit.==="" || typeof(toSubmitPropertyVisit.)==='undefined') {
                flag = false;
                $('#building').addClass('invalid-input');
            }
            if (toSubmitPropertyVisit.===""|| typeof(toSubmitPropertyVisit.)==='undefined') {
                flag = false;
                $('#unit').addClass('invalid-input');
            }
            if (toSubmitPropertyVisit.===""|| typeof(toSubmitPropertyVisit.)==='undefined') {
                flag = false;
                $('#roomNo').addClass('invalid-input');
            }
            if (toSubmitPropertyVisit.===""|| typeof(toSubmitPropertyVisit.)==='undefined') {
                flag = false;
                $('#areaSize').addClass('invalid-input');
            }
            if (toSubmitPropertyVisit.===""|| typeof(toSubmitPropertyVisit.)==='undefined') {
                flag = false;
                $('#totalPrice').addClass('invalid-input');
            }
            if (toSubmitPropertyVisit.===""|| typeof(toSubmitPropertyVisit.)==='undefined') {
                flag = false;
                $('#unitPrice').addClass('invalid-input');
            }
            if (toSubmitPropertyVisit.===""|| typeof(toSubmitPropertyVisit.)==='undefined') {
                flag = false;
                $('#bedRoom').addClass('invalid-input');
            }
            if (toSubmitPropertyVisit.===""|| typeof(toSubmitPropertyVisit.)==='undefined') {
                flag = false;
                $('#livingRoom').addClass('invalid-input');
            }
            if (toSubmitPropertyVisit.===""|| typeof(toSubmitPropertyVisit.)==='undefined') {
                flag = false;
                $('#kitchen').addClass('invalid-input');
            }
            if (toSubmitPropertyVisit.===""|| typeof(toSubmitPropertyVisit.)==='undefined') {
                flag = false;
                $('#bathRoom').addClass('invalid-input');
            }
            if (toSubmitPropertyVisit.===""|| typeof(toSubmitPropertyVisit.)==='undefined') {
                flag = false;
                $('#balcony').addClass('invalid-input');
            }
            if (toSubmitPropertyVisit.===""|| typeof(toSubmitPropertyVisit.)==='undefined') {
                flag = false;
                $('#customerName').addClass('invalid-input');
            }
            if (toSubmitPropertyVisit.===""|| typeof(toSubmitPropertyVisit.)==='undefined') {
                flag = false;
                $('#customerSource').addClass('invalid-input');
            }
            if (toSubmitPropertyVisit.===""|| typeof(toSubmitPropertyVisit.)==='undefined') {
                flag = false;
                $('#customerContact').addClass('invalid-input');
            }*/
            return flag;
        }

        $('#getHouseInfoBtn').on('click',function () {
            PropertyVisitService.getPropertyInfo()
                .done(function () {
                    $('#houseDict').val();
                    $('#building').val();
                    $('#unit').val();
                    $('#roomNo').val();
                    $('#areaSize').val();
                    $('#totalPrice').val();
                    $('#unitPrice').val();
                    $('#bedRoom').val();
                    $('#livingRoom').val();
                    $('#kitchen').val();
                    $('#bathRoom').val();
                    $('#balcony').val();
                });
        });

        $('#confirmAddPropertyVisitBtn').on('click',function () {
            var toAddPropertyVisit = {

            };

            if(verifyAddPropertyVisitInput){
                PropertyVisitService.addPropertyVisit()
                    .done(function () {
                        swal({
                                title: "操作成功!",
                                type: "success",
                                confirmButtonText: "确定",
                                confirmButtonColor: "#3c8dbc"
                            },
                            function(){
                                window.location.href="/mgt/org/department.ftl";
                            });
                    }).fail(function (res) {
                        swal({
                            title: "错误!",
                            text: res["message"],
                            type: "error",
                            confirmButtonText: "确定",
                            confirmButtonColor: "#3c8dbc"
                        });
                    });

            }else {
                swal({
                    title: "错误!",
                    text: "请填写所有必填字段",
                    type: "error",
                    confirmButtonText: "确定",
                    confirmButtonColor: "#3c8dbc"
                });
            }

        });

    });
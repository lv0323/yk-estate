/**
 * Created by yanghong on 2/21/17.
 */
require(['main-app',
        contextPath + '/js/service/profile-service.js',
        contextPath + '/js/service/employee-service.js',
        contextPath + '/js/service/util-service.js', 'Croppie','sweetalert'],
    function (mainApp, ProfileService, EmployeeService, UtilService) {

        var header = {};
        var fileData = new FormData();
        var fileName = "";

        /*load page begin*/
        var loadPage = function (data, header) {
            $('#username').text(data.name);
            $('#myProfileUsername').text(data.name);
            $('#mobile').text(data.mobile);
            var entryDate = UtilService.timeStamp2Date(data.entryDate);
            $('#entryDate').text(entryDate);
            $('#wechat').text(data.wechat);
        };
        EmployeeService.getSelf(header).done(function (data) {
            loadPage(data, header);
        }).fail(function (res) {
            // alert(res["message"]);
            swal({
                title: "错误!",
                text: res["message"],
                type: "error",
                confirmButtonText: "确定",
                confirmButtonColor: "#3c8dbc"
            });
        });
        /*load page end*/

        var $uploadCrop = $('#previewImg').croppie({
            viewport: {
                width: 120,
                height: 160
            },
            enableOrientation:true,
            enableExif: true
        });

        function readFile(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    $('.previewImg').addClass('ready');
                    $uploadCrop.croppie('bind', {
                        url: e.target.result
                    }).then(function(){
                        //console.log('jQuery bind complete');
                    });
                };
                reader.readAsDataURL(input.files[0]);
            }
        }

        $('#toUplImgFileInput').on('change',function () {
            readFile(this);
        });

        $('#confirmChangeAvatarBtn').on('click',function () {
            fileName = $('#toUplImgFileInput').val().split('\\').pop();
            $uploadCrop.croppie('result', {
                type: 'blob',
                size: 'viewport',
                format: 'jpeg'
            }).then(function (resp) {
                fileData.append("avatar",resp,fileName);
                EmployeeService.uploadAvatar(fileData, header)
                    .done(function () {
                        // alert("头像上传成功");
                        // location.reload(true);
                        swal({
                                title: "头像上传成功!",
                                type: "success",
                                confirmButtonText: "确定",
                                confirmButtonColor: "#3c8dbc"
                            },
                            function(){
                                location.reload(true);
                            });
                    }).fail(function (res) {
                    // alert(res["message"]);
                    swal({
                        title: "错误!",
                        text: res["message"],
                        type: "error",
                        confirmButtonText: "确定",
                        confirmButtonColor: "#3c8dbc"
                    });
                });
            });
        });

        $('#confirmChangeMyPasswordBtn').on('click', function () {
            var oldPassword = $('#myOldPassword').val();
            var newPassword = $('#myNewPassword').val();

            ProfileService.changePassword({oldPassword:oldPassword, newPassword:newPassword}, header)
                .done(function () {
                    // alert("密码修改成功");
                    // location.reload(true);
                    swal({
                            title: "密码修改成功!",
                            type: "success",
                            confirmButtonText: "确定",
                            confirmButtonColor: "#3c8dbc"
                        },
                        function(){
                            location.reload(true);
                        });
                })
                .fail(function (res) {
                    swal({
                        title: "错误!",
                        text: res["message"],
                        type: "error",
                        confirmButtonText: "确定",
                        confirmButtonColor: "#3c8dbc"
                    });
                });
        })

    });
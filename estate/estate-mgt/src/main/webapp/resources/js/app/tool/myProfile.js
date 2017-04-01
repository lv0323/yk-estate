/**
 * Created by yanghong on 2/21/17.
 */
require(['main-app',
        contextPath + '/js/service/profile-service.js',
        contextPath + '/js/service/employee-service.js',
        contextPath + '/js/service/util-service.js',
        contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js', 'Croppie'],
    function (mainApp, ProfileService, EmployeeService, UtilService, SweetAlertHelp) {

        var header = {};
        var fileData = new FormData();
        var fileName = "";
        var openContactHN = "";
        var openContactEN = "";

        /*load page begin*/
        var loadPage = function (data, header) {
            $('#username').text(data.name);
            $('#myProfileUsername').text(data.name);
            $('#mobile').text(data.mobile);
            var entryDate = UtilService.timeStamp2Date(data.entryDate);
            $('#entryDate').text(entryDate);
            $('#wechat').text(data.wechat);
            var contactNoText = (data.openContact=="" || data.openContact==null)?(""):(typeof(data.openContact.split(',')[1])=='undefined')?(data.openContact):(data.openContact.split(',')[0]+'转'+data.openContact.split(',')[1]);
            $('#openContact').text(contactNoText);
            var contactNo = (data.openContact=="" || data.openContact==null)?(""):data.openContact;
            openContactHN = contactNo.split(',')[0];
            openContactEN = (typeof (contactNo.split(',')[1])=='undefined')?(""):(contactNo.split(',')[1]);
        };
        EmployeeService.getSelf(header).done(function (data) {
            loadPage(data, header);
        }).fail(function (res) {
            SweetAlertHelp.fail(res);
        });
        /*load page end*/

        var $uploadCrop = $('#previewImg').croppie({
            viewport: {
                width: 150, //120
                height: 200 //160
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
                        SweetAlertHelp.success({title:"头像上传成功!"}, function () {
                            location.reload(true);
                        });
                    }).fail(function (res) {
                        SweetAlertHelp.fail(res);
                });
            });
        });

        $('#confirmChangeMyPasswordBtn').on('click', function () {
            var oldPassword = $('#myOldPassword').val();
            var newPassword = $('#myNewPassword').val();

            ProfileService.changePassword({oldPassword:oldPassword, newPassword:newPassword}, header)
                .done(function () {
                    SweetAlertHelp.success({title:"密码修改成功!"}, function () {
                        location.reload(true);
                    });
                })
                .fail(function (res) {
                    SweetAlertHelp.fail(res);
                });
        });

        $('#editPersonalInfoBtn').on('click', function () {

            $('#editWechat').val($('#wechat').text());
            $('#editOpenContactHN').val(openContactHN);
            $('#editOpenContactEN').val(openContactEN);

        });

        $('#confirmEditPersonalInfoBtn').on('click', function () {
            var editWechat = $('#editWechat').val();
            var hostNumber = $('#editOpenContactHN').val();
            var extensionNumber = $('#editOpenContactEN').val();
            var openContact = [hostNumber,extensionNumber];
            var contactNo = (hostNumber==="")?"":(extensionNumber==="")?hostNumber:openContact.join(',');

            EmployeeService.editSelf({openContact: contactNo, weChat: editWechat}, header)
                .done(function () {
                    SweetAlertHelp.success({title:"个人信息修改成功!"}, function () {
                        location.reload(true);
                    });
                }).fail(function (res) {
                    SweetAlertHelp.fail(res);
                });
        });

    });
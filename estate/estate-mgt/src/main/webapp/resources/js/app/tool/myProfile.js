/**
 * Created by yanghong on 2/21/17.
 */
require(['main-app',
        contextPath + '/js/service/profile-service.js',
        contextPath + '/js/service/employee-service.js'],
    function (mainApp, ProfileService, EmployeeService) {

        var header = {};

        $('#username').val();
        $('#mobile').val();
        $('#entryDate').val();
        $('#wechat').val();

        $('#confirmChangeMyPasswordBtn').on('click', function () {
            var oldPassword = $('#myOldPassword').val();
            var newPassword = $('#myNewPassword').val();

            ProfileService.changePassword({oldPassword:oldPassword, newPassword:newPassword}, header)
                .done(function () {
                    alert("密码修改成功");
                    location.reload(true);
                });
        })

    });
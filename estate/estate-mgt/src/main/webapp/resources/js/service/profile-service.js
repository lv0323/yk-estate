/**
 * Created by yanghong on 2/21/17.
 */
define(contextPath + '/js/service/profile-service.js',
    ['main-app',
        contextPath + '/js/service/employee-service.js',
        contextPath + '/js/service/util-service.js'],
    function (mainApp, EmployeeService, utilService) {

        var ProfileService= {};

        var encryptionParams = {};

        function getEncryptionParams(header) {
            var defer =$.Deferred();
            EmployeeService.getSaltSugar(header).done(function (response) {
                if(response.salt && response.sugar) {
                    encryptionParams = {
                        sugar: response.sugar,
                        salt: response.salt
                    };
                    defer.resolve(response);
                }else {
                    defer.reject(response);
                }
            }).fail(function (response) {
                defer.reject(response);
            });
            return defer.promise();
        }

        function generateSaltedPassword(password) {
            return utilService.generateSignature(password, encryptionParams.salt);
        }

        function generateSugaredPassword(password) {
            return utilService.generateSignature(utilService.generateSignature(password, encryptionParams.salt), encryptionParams.sugar);
        }

        ProfileService.changePassword = function (params, header) {
            var defer =$.Deferred();
            getEncryptionParams(header).done(function () {
                var opts = {};
                var sugaredOldPassword = generateSugaredPassword(params.oldPassword);
                var saltedNewPassword = generateSaltedPassword(params.newPassword);
                opts.sugaredPassword = sugaredOldPassword;
                opts.saltedNewPassword = saltedNewPassword;
                EmployeeService.changePassword(opts, header)
                    .done(function (response) {
                        defer.resolve(response);
                    }).fail(function (res) {
                        defer.reject(res);
                        alert(res["message"]);
                    });
            }).fail(function (res) {
                defer.reject(res);
                alert(res["message"]); //加密参数生成失败
            });

            return defer.promise();
        };

        return ProfileService;
    });
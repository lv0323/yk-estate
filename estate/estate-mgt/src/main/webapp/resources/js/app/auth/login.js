require(['main-app', 
    contextPath + '/js/service/identity-service.js', 
    contextPath + '/js/service/validation-service.js',
    contextPath + '/js/service/util-service.js',
        contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js',
        contextPath + '/js/service/employee-service.js','sweetalert'],
    function (mainApp, IdentityService, ValidationService, UtilService, SweetAlertHelp, EmployeeService) {

        var LoginModule=angular.module('LoginModule',[]);
        LoginModule.controller("LoginCtrl", ['$scope','$timeout', '$interval','$window','$location', function($scope, $timeout, $interval, $window, $location) {
            /*初始化和重置数据*/
            function resetData(){
                $scope.state={
                    page:'login'
                };
                $scope.loginData = {};
                $scope.activeData={
                    sendSMS :true,
                    smsText:'点击获取'
                };
            }
            /*初始状态-现实登录页面*/
            resetData();
            $scope.loadCaptcha =function() {
                $scope.captchaId = UtilService.generateSalt(8);
                localStorage.setItem("captchaId", $scope.captchaId);
                $scope.captcha = contextPath + '/captcha?clientId='+clientId+'&id='+$scope.captchaId+'&width=120&height=30';
                $scope.loginData.captcha = '';
            };
            $scope.loadCaptcha();
            /*页面切换*/
            $scope.changePage= function(page){
                $scope.state.page = page;
            };
            /*登录*/
            $scope.submit = function(){
                if (!$scope.loginData.mobile||!$scope.loginData.password||!$scope.loginData.captcha) {
                    SweetAlertHelp.fail({message:'请输入完整信息'});
                    return;
                }
                $scope.loginData.mobile =$scope.loginData.mobile.trim();
                $scope.loginData.password =$scope.loginData.password.trim();
                $scope.loginData.captcha =$scope.loginData.captcha.trim();
                if(!ValidationService.isValidPhoneNumber($scope.loginData.mobile)){
                    SweetAlertHelp.fail({message:'手机号格式不正确'});
                    return;
                }
                IdentityService.loginAction({mobile:$scope.loginData.mobile,password:$scope.loginData.password}, {clientId:clientId, code:$scope.loginData.captcha,id:$scope.captchaId}).done(function(response){
                    var userInfo = JSON.parse(localStorage.getItem('userInfo'));
                    $window.location=contextPath+'/fangManage/list?target=.fang';
                }).fail(function(response){
                    SweetAlertHelp.fail({message:response&&response.message});
                });
            };
            /*激活图形验证码*/
            $scope.loadActiveCaptcha =function() {
                $scope.activeCaptchaId = UtilService.generateSalt(8);
                localStorage.setItem("activeCaptchaId", $scope.activeCaptchaId);
                $scope.activeCaptcha=contextPath + '/captcha?clientId='+clientId+'&id='+$scope.activeCaptchaId+'&width=120&height=30';
                $scope.activeData.captcha = '';
            };
            $scope.loadActiveCaptcha();
            /*激活短信验证码*/
            $scope.getActiveSMS = function(){
                if(!$scope.activeData.mobile||!$scope.activeData.captcha){
                    return;
                }
                $scope.activeData.mobile =$scope.activeData.mobile.trim();
                $scope.activeData.captcha =$scope.activeData.captcha.trim();

                if(!ValidationService.isValidPhoneNumber($scope.activeData.mobile)){
                    SweetAlertHelp.fail({message:'手机号格式不正确'});
                    return;
                }
                $scope.activeData.sendSMS = false;
                IdentityService.sendSMS({clientId:clientId, code:$scope.activeData.captcha,id:$scope.activeCaptchaId, mobile:$scope.activeData.mobile, type: "REGISTER"}).done(function(response){
                    $scope.smsData=response;
                    var second = 60;
                    $scope.activeData.newSmsText = second + 's';
                    $interval(function () {
                        if(second > 1){
                            second--;
                            $scope.activeData.smsText = second + 's';
                        }else{
                            $scope.activeData.sendSMS = true;
                            $scope.activeData.smsText = '点击获取';
                        }
                    }, 1000, 60);
                }).fail(function(){
                    $scope.activeData.sendSMS = true;
                });
            };
            /*激活*/
            $scope.activate = function(){
                if(!$scope.activeData.secretKey || !$scope.activeData.password||!$scope.activeData.passwordAgain||!$scope.activeData.sms){
                    SweetAlertHelp.fail({message:'请输入完整信息!'});
                    return;
                }
                if($scope.activeData.password.trim().length<8){
                    SweetAlertHelp.fail({message:'密码位数不可小于8位!'});
                    return;
                }
                if($scope.activeData.password.trim() !== $scope.activeData.passwordAgain.trim()){
                    SweetAlertHelp.fail({message:'输入的密码不一致!'});
                    return;
                }
                IdentityService.activate({password:$scope.activeData.password.trim(), secretKey:$scope.activeData.secretKey},
                    {"X-SMS-CODE":'serial='+$scope.smsData.serial
                    +'&id='+ $scope.smsData.sms_id
                    +'&mobile='+$scope.smsData.mobile
                    +'&code=' + $scope.activeData.sms
                    +'&type=REGISTER&clientId='+clientId}).done(function(){
                    SweetAlertHelp.success({message:'激活成功!'});
                    resetData();
                }).fail(function(response){
                    SweetAlertHelp.fail({message:response&&response.message});
                    $scope.loadActiveCaptcha();
                });
            }
        }]);

        angular.element(document).ready(function() {
            angular.bootstrap(document.getElementById("loginBox"),["LoginModule"])
        });
    });
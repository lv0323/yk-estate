/**
 * Created by robin on 17/3/20.
 */
require(['main-app',
        contextPath + '/js/service/identity-service.js',
        contextPath + '/js/service/validation-service.js',
        contextPath + '/js/service/util-service.js',
        contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js',
        contextPath + '/js/service/employee-service.js','sweetalert'],
    function (mainApp, IdentityService, ValidationService, UtilService, SweetAlertHelp, EmployeeService) {

        var module=angular.module('ActivationModule',[]);
        module.controller("ActivationCtrl", ['$scope','$timeout', '$interval','$window','$location', function($scope, $timeout, $interval, $window, $location ) {
            /*初始化和重置数据*/
            var smsType ='EMPLOYEE_ACTIVE';
            function resetData(){
                $scope.state={
                    step:'step1',
                    disableNext:false
                };
                $scope.activeData={
                    sendSMS :true,
                    smsText:'点击获取'
                };
            }
            /*初始状态-现实登录页面*/
            resetData();
            /*页面切换*/
            $scope.nextStep= function(){
                $scope.state.disableNext = true;
                $scope.unActivated();
                return false;
            };
            /*激活图形验证码*/
            $scope.loadActiveCaptcha =function() {
                $scope.state.step = 'step1';
                $scope.activeCaptchaId = UtilService.generateSalt(8);
                localStorage.setItem("activeCaptchaId", $scope.activeCaptchaId);
                $scope.activeCaptcha=contextPath + '/captcha?clientId='+clientId+'&id='+$scope.activeCaptchaId+'&width=120&height=30';
                $scope.activeData.captcha = '';
            };
            $scope.loadActiveCaptcha();
            $scope.mobileChange= function(){
                $scope.smsTimer && $interval.clear($scope.smsTimer)
            };
            $scope.unActivated = function(){
                if(!$scope.activeData.secretKey || !$scope.activeData.mobile||!$scope.activeData.captcha){
                    SweetAlertHelp.fail({message:'请输入完整信息!'});
                    return;
                }
                $scope.activeData.mobile =$scope.activeData.mobile.trim();
                $scope.activeData.secretKey =$scope.activeData.secretKey.trim();
                $scope.activeData.captcha =$scope.activeData.captcha.trim();

                if(!ValidationService.isValidPhoneNumber($scope.activeData.mobile)){
                    SweetAlertHelp.fail({message:'手机号格式不正确'});
                    return;
                }
                IdentityService.unActivated({mobile:$scope.activeData.mobile, secretKey:$scope.activeData.secretKey},
                    {'X-CAPTCHA': "id=" + $scope.activeCaptchaId +"&clientId="+ clientId +"&code="+ $scope.activeData.captcha,
                    'Content-Type':'application/json;charset=utf-8'
                }).then(function(){
                    $scope.getActiveSMS();
                }).fail(function(res){
                    $scope.$apply(function(){
                        $scope.state.disableNext = false;
                    });
                    SweetAlertHelp.fail({message: res && res.message});
                });
            };
            /*激活短信验证码*/
            $scope.getActiveSMS = function(){
                if($scope.smsTimer){
                    $scope.state.step = 'step2';
                    return;
                }
                $scope.activeData.sendSMS = false;
                IdentityService.sendSMS({clientId:clientId, code:$scope.activeData.captcha,id:$scope.activeCaptchaId, mobile:$scope.activeData.mobile, type: smsType}).done(function(response){
                    $scope.smsData=response;
                    $scope.state.step = 'step2';
                    var second = 60;
                    $scope.activeData.newSmsText = second + 's';
                    $scope.smsTimer = $interval(function () {
                        if(second > 1){
                            second--;
                            $scope.activeData.smsText = second + 's';
                        }else{
                            $scope.activeData.sendSMS = true;
                            $scope.activeData.smsText = '点击获取';
                        }
                    }, 1000, 60);

                }).fail(function(response){
                    $scope.$apply(function(){
                        $scope.activeData.sendSMS = true;
                        $scope.state.disableNext = false;
                        if(response.message =='图片验证码不正确'){
                            $scope.loadActiveCaptcha();
                        }
                    });
                    SweetAlertHelp.fail({message:response.message});
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
                    +'&id='+ $scope.smsData.smsId
                    +'&mobile='+$scope.smsData.mobile
                    +'&code=' + $scope.activeData.sms
                    +'&type='+ smsType
                    +'&clientId='+clientId}).done(function(){
                    SweetAlertHelp.success({message:'激活成功!'});
                    resetData();
                }).fail(function(response){
                    SweetAlertHelp.fail({message:response&&response.message});
                    //$scope.loadActiveCaptcha();
                });
            }
        }]);

        angular.element(document).ready(function() {
            angular.bootstrap(document.getElementById("activationBox"),["ActivationModule"])
        });
    });
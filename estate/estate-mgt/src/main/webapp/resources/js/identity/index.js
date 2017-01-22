require(['main-app', 
    contextPath + '/js/service/identity-service.js', 
    contextPath + '/js/service/util-service.js'],
    function (mainApp, IdentityService, UtilService) {

        var LoginModule=angular.module('LoginModule',[]);
        LoginModule.controller("LoginCtrl", ['$scope','$timeout', '$window','$location', function($scope, $timeout, $window, $location) {

            $scope.loadCaptcha =function() {
                $scope.captchaId = UtilService.generateSalt(8);
                localStorage.setItem("captchaId", $scope.captchaId);
                $scope.captcha = contextPath + '/captcha?clientId='+clientId+'&id='+$scope.captchaId+'&width=120&height=30';
            };
            $scope.loadCaptcha();
            /*初始状态-现实登录页面*/
            $scope.state={
              page:'login'
            };
            $scope.loginData={};
            $scope.activeData={};
            $scope.warn={
                title:'提示',
                content:'请输入完整信息',
            };
            /*页面切换*/
            $scope.changePage= function(page){
                $scope.state.page = page;
            };
            /*登录*/
            $scope.submit = function(){
                if (!$scope.loginData.mobile||!$scope.loginData.password||!$scope.loginData.captcha) {
                    show({title:'提示',content:'请输入完整信息'})
                    return;
                }
                IdentityService.loginAction({mobile:$scope.loginData.mobile,password:$scope.loginData.password}, {clientId:clientId, code:$scope.loginData.captcha,id:$scope.captchaId}).done(function(response){
                    $window.location=contextPath+'/org/department.ftl';
                }).fail(function(response){
                    show({title:'提示',content:response&&response.message});
                });
            };
            /*激活*/
            $scope.loadActiveCaptcha =function() {
                $scope.activeCaptchaId = UtilService.generateSalt(8);
                localStorage.setItem("activeCaptchaId", $scope.activeCaptchaId);
                $scope.activeCaptcha=contextPath + '/captcha?clientId='+clientId+'&id='+$scope.activeCaptchaId+'&width=120&height=30';
            };
            $scope.loadActiveCaptcha();
            /*激活短信验证码*/
            $scope.getActiveSMS = function(){
                if(!$scope.activeData.captcha){
                    return;
                }
                IdentityService.sendSMS({clientId:clientId, code:$scope.activeData.captcha,id:$scope.activeCaptchaId, mobile:$scope.activeData.mobile, type: "REGISTER"}).done(function(response){
                    $scope.smsData=response;
                    console.log($scope.smsData);
                });
            };
            $scope.activate = function(){
                if(!$scope.activeData.secretKey || !$scope.activeData.password||!$scope.activeData.passwordAgain||!$scope.activeData.sms){
                    show({title:'提示',content:'请输入完整信息!'});
                    return;
                }
                if($scope.activeData.password.trim().length<8){
                    show({title:'提示',content:'密码位数不可小于8位'});
                    return;
                }
                if($scope.activeData.password.trim() !== $scope.activeData.passwordAgain.trim()){
                    show({title:'提示',content:'输入的密码不一致'});
                    return;
                }
                IdentityService.activate({password:$scope.activeData.password.trim(), secretKey:$scope.activeData.secretKey},
                    {"X-SMS-CODE":'serial='+$scope.smsData.serial
                    +'&id='+ $scope.smsData.sms_id
                    +'&mobile='+$scope.smsData.mobile
                    +'&code=' + $scope.activeData.sms
                    +'&type=REGISTER&clientId='+clientId}).done(function(){
                    show({title:'提示',content:'激活成功!'});
                    $scope.activeData = {};
                    $scope.state={
                        page:'login'
                    };
                }).fail(function(response){
                    show({title:'提示',content:response&&response.message});
                });
            }
            /*弹出框*/
            function show(param){
                $('#myModal').modal('show');
                $timeout(function(){
                    $scope.warn.title= param.title;
                    $scope.warn.content= param.content;
                },30)
            }
        }]);

        angular.element(document).ready(function() {
            angular.bootstrap(document.getElementById("loginBox"),["LoginModule"])
        });
    });
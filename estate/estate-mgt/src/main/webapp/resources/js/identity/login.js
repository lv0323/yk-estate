require(['main-app', 
    contextPath + '/js/service/identity-service.js', 
    contextPath + '/js/service/util-service.js'],
    function (mainApp, IdentityService, UtilService) {
        function loadCaptcha() {
            var imageId = UtilService.generateSalt(8);
            localStorage.setItem("imageId", imageId);
            $('#captcha-image').attr('src', contextPath + '/captcha?clientId=1000&id=100&width=120&height=30&iid=' + imageId)
        }
        $('#captcha-image').on('click', loadCaptcha);
        loadCaptcha();
        var LoginModule=angular.module('LoginModule',[]);
        LoginModule.controller("LoginCtrl", ['$scope','$timeout', '$window','$location', function($scope, $timeout, $window, $location) {
            $scope.loginData={};
            $scope.warn={
                title:'提示',
                content:'请输入用户名和密码',
            };
            $('#myModal').on('show.bs.modal', function (e) {
                $scope.theWarn = $scope.warn;
            });
            $scope.submit = function(){
                if (!$scope.loginData.mobile||!$scope.loginData.password) {
                    show({title:'提示',content:'请输入用户名和密码'})
                    return;
                }
                IdentityService.loginAction({mobile:$scope.loginData.mobile,password:$scope.loginData.password}).done(function(response){
                    $window.location=contextPath+'/org/department.ftl';
                }).fail(function(response){
                    show({title:'提示',content:response&&response.message});
                });
            };
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
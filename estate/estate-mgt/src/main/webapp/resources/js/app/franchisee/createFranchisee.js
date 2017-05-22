/**
 * Created by yanghong on 5/22/17.
 */
require(['main-app',
        contextPath + '/js/service/createFranchisee-service.js',
        contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js',
        contextPath + '/js/directive/index.js',
        'select', 'chosen', 'datetimepicker.zh-cn'],
    function (mainApp, CreateService, SweetAlertHelp) {

        var CreateFranchiseeModule=angular.module('CreateFranchiseeModule',['directiveYk']);
        CreateFranchiseeModule.controller("CreateFranchiseeCtrl", ['$scope','$timeout', '$q', '$interval', '$window', '$location', function ($scope, $timeout, $q, $interval, $window) {

            var config = {
                cityId: {
                    init: false
                },
                companyId: {
                    init: false
                },
                signatureDepId: {
                    init: false
                },
                signatureRepId: {
                    init: false
                }
            };

            $scope.cityList = [{id: 1, name: '北京'}];
            $scope.companyList = [{id: 1, name: '北京代理'}];
            $scope.signatureDepList = [{id:1, name: '盈科总部'}];
            $scope.signatureRepList = [{id:1, name: '李欣儿'}];

            $scope.contractStartDate = '';
            $scope.contractEndDate = '';

            /*下拉框*/
            $scope.initChosen = function(id, key){
                $(id).chosen("destroy");
                if(!config[key].init){
                    config[key].init = !config[key].init;
                    $(id).chosen().change(function(e, result){
                        $scope.$apply(function(){
                            $scope.filter[key] = result.selected;
                            $scope.list();
                        });
                    });
                    return;
                }
                $(id).chosen();
                $(id).trigger('chosen:updated');
            };

        }]);

        angular.element(document).ready(function() {
            angular.bootstrap(document.getElementById("createFranchiseeWrapper"),["CreateFranchiseeModule"])
        });

    });
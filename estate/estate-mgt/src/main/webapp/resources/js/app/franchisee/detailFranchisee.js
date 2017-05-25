/**
 * Created by yanghong on 5/22/17.
 */
require(['main-app',
        contextPath + '/js/service/franchisee-service.js',
        contextPath+'/js/service/city-service.js',
        contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js',
        contextPath + '/js/directive/index.js',
        'select', 'chosen', 'datetimepicker.zh-cn'],
    function (mainApp, FranchiseeService, CityService, SweetAlertHelp) {

        var DetailFranchiseeModule=angular.module('DetailFranchiseeModule',['directiveYk']);
        DetailFranchiseeModule.controller("DetailFranchiseeCtrl", ['$scope','$timeout', '$q', '$interval', '$window', '$location', function ($scope, $timeout, $q, $interval, $window) {

            $scope.detail ={name:'河北天水地产经纪有限公司', city:'北京'}

        }]);

        angular.element(document).ready(function() {
            angular.bootstrap(document.getElementById("detailFranchiseeWrapper"),["DetailFranchiseeModule"])
        });

    });
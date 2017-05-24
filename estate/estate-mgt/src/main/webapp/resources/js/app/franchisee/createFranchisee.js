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

        var CreateFranchiseeModule=angular.module('CreateFranchiseeModule',['directiveYk']);
        CreateFranchiseeModule.controller("CreateFranchiseeCtrl", ['$scope','$timeout', '$q', '$interval', '$window', '$location', function ($scope, $timeout, $q, $interval, $window) {

            var config = {
                cityId: {
                    init: false
                },
                parentId: {
                    init: false
                },
                signatureDepId: {
                    init: false
                },
                partAId: {
                    init: false
                }
            };

            $scope.cityList = [{name:'',id:''}];
            $scope.companyList = [{name:'',id:''}];
            $scope.signatureDepList = [{name:'',id:''}];
            $scope.signatureRepList = [{name:'',id:''}];

            $scope.create = {
                cityId: '',
                parentId: '',
                signatureDepId: '',
                partAId: '',
                type: '',
                name: '',
                abbr: '',
                address: '',
                introduction:'',
                startDate: '',
                endDate: '',
                years:'',
                storeCount: '',
                price :''
            };

            /*根据url设置type选项默认值*/
            var url = window.location.href.split('?')[0];
            var checkPage = function(url) {
                var regs = [new RegExp('ChannelPartner$'), new RegExp('StorePartner$'), new RegExp('RegionAgent$')];
                var contains = ['CHANNEL', 'SINGLE_STORE', 'REGIONAL_AGENT'];
                for(var i = 0; i < regs.length; i++){
                    if(regs[i].test(url)){
                        this.create.type =  contains[i];
                    }
                }
            };
            checkPage.call($scope, url);

            /*区域城市列表初始化*/
            CityService.getCity().then(function(response){
                $scope.cityList = response.map(function(item){
                    return {
                        name: item.name,
                        id: item.id
                    }
                });
            });

            /*签约父公司列表初始化*/
            FranchiseeService.getParentCompany().then(function (response) {
                $scope.companyList = response.map(function (item) {
                   return {
                       name: item.abbr || item.name,
                       id: item.id
                   }
                });
            });

            $scope.loadParentCompanyDep = function (companyId) {
                if(companyId === ''){
                    return;
                }
                FranchiseeService.getParentCompanyDep({companyId: companyId}).then(function (response) {
                    $scope.$apply(function(){
                        $scope.signatureDepList = response.map(function (item) {
                            return {
                                name: item.name,
                                id: item.id
                            }
                        });
                    });
                });

            };

            $scope.loadParentCompanyDepEmp = function (companyId, depId) {
                if(companyId === ''){
                    return;
                }
                FranchiseeService.getParentCompanyDepEmp({companyId: companyId, departmentId: depId}).then(function (response) {
                    $scope.$apply(function(){
                        $scope.signatureRepList = response.map(function (item) {
                            return {
                                name: item.name,
                                id: item.id
                            }
                        });
                    });
                });
            };


            $scope.chosenChange = function(id, key, value){
                if(key === 'parentId'){
                    $scope.loadParentCompanyDep(value);
                    $scope.loadParentCompanyDepEmp(value);
                    $scope.create.signatureDepId = '';
                    $scope.create.partAId = '';
                }
                if(key === 'signatureDepId'){
                    $scope.loadParentCompanyDepEmp($scope.create.parentId, $scope.create.signatureDepId);
                }
            };

            /*下拉框*/
            $scope.initChosen = function(id, key){
                $(id).chosen("destroy");
                if(!config[key].init){
                    config[key].init = !config[key].init;
                    $(id).chosen().change(function(e, result){
                        $scope.$apply(function(){
                            $scope.create[key] = result.selected;
                            $scope.chosenChange(id, key, result.selected);
                        });
                    });
                    return;
                }
                $(id).chosen();
                $(id).trigger('chosen:updated');
            };

            $scope.setDate = function(key, value){
                $scope.create[key] = value;
            };

            $scope.confirmAddDealBtn = function () {
                // console.log($scope.create)
                var flag = true;
                $('.form-group').find('.invalid-input').removeClass('invalid-input');
                if (!$scope.create.cityId) {
                    $('#cityListDrop').addClass('invalid-input');
                    flag = false;
                }
                if (!$scope.create.parentId) {
                    $('#companyListDrop').addClass('invalid-input');
                    flag = false;
                }
                if (!$scope.create.partAId) {
                    $('#signatureRepListDrop').addClass('invalid-input');
                    flag = false;
                }
                if (!$scope.create.type) {
                    $('#franchiseeType').addClass('invalid-input');
                    flag = false;
                }
                if (!$scope.create.name) {
                    $('#franchiseeName').addClass('invalid-input');
                    flag = false;
                }
                if (!$scope.create.abbr) {
                    $('#franchiseeAbbr').addClass('invalid-input');
                    flag = false;
                }
                if (!$scope.create.address) {
                    $('#franchiseeAddr').addClass('invalid-input');
                    flag = false;
                }
                if (!$scope.create.startDate) {
                    $('#contractSDate').addClass('invalid-input');
                    flag = false;
                }
                if (!$scope.create.endDate) {
                    $('#contractEDate').addClass('invalid-input');
                    flag = false;
                }
                if (!$scope.create.years) {
                    $('#contractYears').addClass('invalid-input');
                    flag = false;
                }
                if (!$scope.create.storeCount) {
                    $('#franchiseeStores').addClass('invalid-input');
                    flag = false;
                }
                if (!$scope.create.price) {
                    $('#franchiseeFee').addClass('invalid-input');
                    flag = false;
                }
                if (!$scope.create.bossName) {
                    $('#franchiseeBoss').addClass('invalid-input');
                    flag = false;
                }
                if (!$scope.create.mobile) {
                    $('#franchiseeBossMobile').addClass('invalid-input');
                    flag = false;
                }
                if(flag){
                    FranchiseeService.createCompany($scope.create)
                        .done(function () {
                            SweetAlertHelp.success({}, function () {
                                window.history.back();
                            });
                        }).fail(function (res) {
                            SweetAlertHelp.fail(res);
                        });
                }else {
                    SweetAlertHelp.fail({message:"请填写所有必填字段"});
                }
            }

        }]);

        angular.element(document).ready(function() {
            angular.bootstrap(document.getElementById("createFranchiseeWrapper"),["CreateFranchiseeModule"])
        });

    });
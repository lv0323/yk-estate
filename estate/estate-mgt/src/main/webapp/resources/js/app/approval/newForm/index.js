/**
 * Created by robin on 17/6/5.
 */
require(['main-app',
    contextPath + '/js/service/validation-service.js',
    contextPath + '/js/service/util-service.js',
    contextPath+'/js/service/city-service.js',
    contextPath+'/js/service/approval-service.js',
    contextPath+'/js/service/franchisee-service.js',
    contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js',
    contextPath + '/js/directive/index.js',
    'angular-route',
    'chosen', 'select', 'datepicker.zh-cn', 'datetimepicker.zh-cn'],
    function (mainApp, ValidationService, UtilService, CityService, ApprovalService, FranchiseeService, SweetAlertHelp) {
        var module = angular.module('NewFormModule',['ngRoute','directiveYk']).config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider){
            $routeProvider
                .when('/',{templateUrl:contextPath+'/approval/home'})
                .when('/leaving',{templateUrl:contextPath+'/approval/leaving'})
                .when('/bizTrip',{templateUrl:contextPath+'/approval/bizTrip'})
                .when('/coldVisit',{templateUrl:contextPath+'/approval/coldVisit'})
                .when('/signing',{templateUrl:contextPath+'/approval/signing'})
                .otherwise({redirectTo:'/'});
        }]).run(['$rootScope',function ($rootScope) {
            $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
                var type;
                switch(current.loadedTemplateUrl){
                    case '/mgt/approval/leaving':
                        type ='LEAVING';
                        break;
                    case '/mgt/approval/bizTrip':
                        type ='BIZ_TRIP';
                        break;
                    case '/mgt/approval/coldVisit':
                        type ='COLD_VISIT';
                        break;
                    case '/mgt/approval/signing':
                        type ='SIGNING';
                        break;
                    default:
                        type ='';
                        break;

                }
                $rootScope.$broadcast('routeChangeSuccess',type)
            })
        }]);

        function FormCtrl($scope, $location,$timeout) {
            var _this = this;
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
                partAInChargeId: {
                    init: false
                }
            };
            $scope.$on('routeChangeSuccess',function(e, m){
                _this.chooseForm(m);
            });
            _this.formType ={
                  "LEAVING":"LEAVING",
                  "BIZ_TRIP":"BIZ_TRIP",
                  "COLD_VISIT":"COLD_VISIT",
                  "SIGNING":"SIGNING"
            };
            _this.formName ={
                "LEAVING":"外出登记表",
                "BIZ_TRIP":"出差信息表",
                "COLD_VISIT":"陌生客户拜访表",
                "SIGNING":"签约客户表"
            };
            _this.config={
                header: '',
                form:'',
                type:{
                    'leaving': _this.formType.LEAVING,
                    'bizTrip': _this.formType.BIZ_TRIP,
                    'coldVisit': _this.formType.COLD_VISIT,
                    'signing': _this.formType.SIGNING
                },
                bossType:[
                    { value:'DEPT_MANAGER', label:'店长'},
                    { value:'MANAGER', label:'总经理'},
                    { value:'AGENT', label:'经纪人'},
                    { value:'BOSS', label:'老板'}
                ],
                dateTimeReg: /^[1-9]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\s+(20|21|22|23|[0-1]\d):[0-5]\d$/,
                dateReg: /^[1-9]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/,
                cityList: [{name:'',id:''}],
                companyList: [{name:'',id:''}],
                signatureDepList: [{name:'',id:''}],
                signatureRepList: [{name:'',id:''}]
            };
            _this.data = {
                leaving: {
                    startTime: '',
                    endTime: '',
                    location: '',
                    reason: '',
                    noClockReason: ''
                },
                bizTrip: {
                    startTime: '',
                    endTime: '',
                    days: '',
                    reason: '',
                    outcome: '',
                    problem: '',
                    resource: '',
                    costs: ''
                },
                coldVisit: {
                    companyName: '',
                    bossName: '',
                    bossType: 'DEPT_MANAGER',
                    contactInfo: '',
                    address: '',
                    visitTime1: '',
                    report1: '',
                    visitTime2: '',
                    report2: '',
                    visitTime3: '',
                    report3: '',
                    followers: ''
                },
                signing: {
                    companyType: '',
                    companyName: '',
                    companyAbbr: '',
                    cityId: '',
                    cityName: '',
                    address: '',
                    bossName: '',
                    bossMobile: '',
                    partAInChargeId: '',
                    partAInChargeName: '',
                    note: '',
                    years: '',
                    storeCount: '',
                    startDate: '',
                    endDate: '',
                    price: ''
                }
            };

            function findCityNameById(id){
                if(!id){
                    return '';
                }
                return _this.config.cityList.filter(function(item){
                    return item.id == id;
                })[0].name;
            }
            function findPartANameById(id) {
                if(!id){
                    return '';
                }
                return _this.config.signatureRepList.filter(function(item){
                    return item.id == id;
                })[0].name;
            }
            /*下拉框*/
            _this.initChosen = function(id, key){
                $(id).chosen("destroy");
                if(!config.cityId.init){
                    config.cityId.init = !config.cityId.init;
                    $(id).chosen().change(function(e, result){
                        $scope.$apply(function(){
                            _this.data.signing.cityId = result.selected;
                            _this.data.signing.cityName = findCityNameById(result.selected)
                        });
                    });
                    return;
                }
                $(id).chosen();
                $(id).trigger('chosen:updated');
            };
            /*筛选栏城市列表初始化*/
            CityService.getCity().then(function(response){
                _this.config.cityList = response.map(function(item){
                    return {
                        name: item.name,
                        id: item.id
                    }
                });
            }).fail(function(res){
                SweetAlertHelp.fail({message: res && res.message})
            });
            _this.datePickChange = function(key, value, info){
                $scope.$apply(function(){
                    if(info){
                        _this.data[info][key] = value;
                    }else{
                        _this.baseInfo[key] = value;
                    }
                });
            };

            _this.chooseForm = function(type){
                _this.config.header = _this.formName[type];
                _this.config.form = _this.formType[type];
            };
            _this.reset = function(type){
                $scope.$apply(function() {
                    for (var key in _this.data[type]) {
                        _this.data[type][key] = '';
                    }
                    if(type =='coldVisit'){
                        _this.data[type].bossType='DEPT_MANAGER';
                    }
                    _this.config.uploading = false;
                });
            };
            _this.submit = function(type){

                var params = {},
                    data = _this.data[type],
                    backup = angular.copy(data);
                if(backup.startTime && backup.startTime > backup.endTime){
                    SweetAlertHelp.fail({message: '外出时间不能大于返回时间!'});
                    return;
                }
                if(backup.startDate && backup.startDate > backup.endDate){
                    SweetAlertHelp.fail({message: '开始时间不能大于结束时间!'});
                    return;
                }
                if(backup.partAInChargeId === ''){
                    SweetAlertHelp.fail({message: '请选择本公司负责人!'});
                    return;
                }
                _this.config.uploading = true;
                params.type = _this.config.type[type];
                delete backup.signatureDepId;
                delete backup.parentId;
                params.data = JSON.stringify(backup);
                ApprovalService.create(params).then(function(){
                   $location.path('/');
                    _this.reset(type);
                    SweetAlertHelp.success();
                }).fail(function(res){
                    SweetAlertHelp.fail({message: res && res.message})
                })
            };

            /*签约父公司列表初始化*/
            FranchiseeService.getParentCompany().then(function (response) {
                $scope.$apply(function(){
                    _this.config.companyList = response.map(function (item) {
                        return {
                            name: item.abbr || item.name,
                            id: item.id
                        }
                    });
                });
            });

            _this.loadParentCompanyDep = function (companyId) {
                if(companyId === ''){
                    return;
                }
                FranchiseeService.getParentCompanyDep({companyId: companyId}).then(function (response) {
                    $scope.$apply(function(){
                        _this.config.signatureDepList = response.map(function (item) {
                            return {
                                name: item.name,
                                id: item.id
                            }
                        });
                    });
                });

            };

            _this.loadParentCompanyDepEmp = function (companyId, depId) {
                if(companyId === ''){
                    return;
                }
                FranchiseeService.getParentCompanyDepEmp({companyId: companyId, departmentId: depId}).then(function (response) {
                    $scope.$apply(function(){
                        _this.config.signatureRepList = response.map(function (item) {
                            return {
                                name: item.name,
                                id: item.id
                            }
                        });
                    });
                });
            };
            _this.chosenChange = function(id, key, value){
                if(key === 'parentId'){
                    _this.loadParentCompanyDep(value);
                    _this.loadParentCompanyDepEmp(value);
                    _this.data.signing.signatureDepId = '';
                    _this.data.signing.partAInChargeId = '';
                }else if(key === 'signatureDepId'){
                    _this.loadParentCompanyDepEmp( _this.data.signing.parentId,  _this.data.signing.signatureDepId);
                }else if(key === 'partAInChargeId'){
                    _this.data.signing.partAInChargeName = findPartANameById(value);
                }
            };
            /*下拉框*/
            _this.initPartAChosen = function(id, key){
                $(id).chosen("destroy");
                if(!config[key].init){
                    config[key].init = !config[key].init;
                    $(id).chosen().change(function(e, result){
                        $scope.$apply(function(){
                            _this.data.signing[key] = result.selected;
                            _this.chosenChange(id, key, result.selected);
                           // $scope.signingForm.$dirty = true;
                        });
                    });
                    return;
                }
                $(id).chosen();
                $(id).trigger('chosen:updated');
            };

        }
        FormCtrl.$inject = ['$scope', '$location', '$timeout', '$interval', '$window'];
        module.controller("FormCtrl", FormCtrl);

        angular.element(document).ready(function() {
            angular.bootstrap(document.getElementById("newFrom"),["NewFormModule"])
        });
    }
);
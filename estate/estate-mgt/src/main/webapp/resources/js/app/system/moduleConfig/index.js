require(['main-app', contextPath + '/js/service/department-service.js',
        contextPath + '/js/service/employee-service.js',
        contextPath + '/js/service/position-service.js',
        contextPath + '/js/service/authority-service.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        contextPath+'/js/utils/dataTableHelp.js',
        contextPath+'/js/app/org/department/departCommon.js',
        contextPath+'/js/app/org/position/positionCommon.js',
        contextPath+'/js/service/util-service.js',
        contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js',
        contextPath + '/js/directive/index.js',
    'datatables', 'select', 'zTree','datatablesBootstrap', 'datetimepicker.zh-cn', 'chosen'],
    function (mainApp, DepartmentService, EmployeeService, PositionService, AuthorityService, pagingPlugin, dataTableHelp, DepartCommon, PositionCommon, UtilService, SweetAlertHelp) {
        var module = angular.module('Module', ['directiveYk']);
        function ModuleCtrl($scope, $timeout){
            var _this = this;
            _this.baseData = {
                position: [],
            };
            _this.moduleConfig = {
                targetId: '',
                targetType: 'POSITION',
                category:'PAGE'
            };
            var initData ={
                P_FANG: false,
                P_FANG_LIST: false,
                P_FANG_NEW: false,
                P_FANG_FOLLOW: false,
                P_FANG_CHECK: false,
                P_SHOWING: false,
                P_SHOWING_LIST: false,
                P_CONTRACT: false,
                P_CONTRACT_LIST: false,
                P_ORG: false,
                P_ORG_DEPT: false,
                P_ORG_POSITION: false,
                P_ORG_EMPLOYEE: false,
                P_CONFIG: false,
                P_CONFIG_AUDIT: false,
                P_CONFIG_HOUSE_DICT: false,
                P_CONFIG_PAGE: false,
                P_CONFIG_PERMISSION: false
            };
            _this.moduleAuthority = {};
            _this.moduleAuthority =angular.copy(initData);

            /*岗位*/
            PositionService.getAllPosition().done(function(data){
                $scope.$apply(function(){
                    _this.baseData.position = data;
                });

            });
            /*end 岗位*/
            _this.getModuleConfig = function(position){
                if(position &&position.id){
                    _this.moduleConfig.targetId = position.id;
                }
                _this.moduleAuthority = angular.copy(initData);
                AuthorityService.getGrants(_this.moduleConfig).then(function(data){
                    angular.forEach(data, function(item){
                        $scope.$apply(function(){
                            _this.moduleAuthority[item.permission && item.permission.name] = true;
                        });
                    });
                }).fail(function(response){
                    SweetAlertHelp.fail({message: response && response.message});
                });
            };
            _this.updateConfig = function(){
                if(!_this.moduleConfig.targetId){
                    SweetAlertHelp.fail({message:'请选择岗位'});
                    return;
                }
                var params = {
                    targetId: _this.moduleConfig.targetId,
                    targetType: _this.moduleConfig.targetType,
                    category: _this.moduleConfig.category
                };
                params.grants = [];
                angular.forEach(_this.moduleAuthority, function(value,key){
                    if(value!=''){
                        params.grants.push(key+'.'+value)
                    }
                });
                AuthorityService.reGrant(params).then(function(){
                    SweetAlertHelp.success();
                    _this.getModuleConfig();
                }).fail(function(response){
                    SweetAlertHelp.fail({message: response && response.message});
                });
            }
        }
        ModuleCtrl.$inject =['$scope', '$timeout'];
        module.controller("ModuleCtrl", ModuleCtrl);

        angular.element(document).ready(function(){
           angular.bootstrap(document.getElementById("moduleConfig"), ["Module"])
        });

    });
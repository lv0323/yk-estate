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
        var module = angular.module('AuthorityModule', ['directiveYk']);
        function AuthorityCtrl($scope, $timeout){
            var _this = this;
            _this.config ={
                cityList: [],
                currentCity:null,
                category:{
                    FANG: 'FANG',
                    XIAOQU: 'XIAO_QU',
                    ORGANIZATION: 'ORGANIZATION',
                    COMPANY: 'COMPANY',
                    FRANCHISEE: 'FRANCHISEE',
                    OPERATION: 'OPERATION',
                    APPROVAL:'APPROVAL'
                }
            };
            _this.baseData = {
                department: [],
                position: [],
                currentPosition: null
            };
            _this.authorityConfig = {
                targetId: '',
                targetType: '',
                category: _this.config.category.FANG
            };
            var initData ={
                fang:{
                    CREATE_FANG :false,
                    LIST_FANG_SELL:false,
                    LIST_FANG_RENT:false,
                    VIEW_SELL_CONTACT_LIMIT:'',
                    VIEW_RENT_CONTACT_LIMIT:'',
                    NOT_FOLLOW_SELL:false,
                    NOT_FOLLOW_RENT:false,
                    DEL_FANG_IMG_SHI_JING:'NONE',
                    DEL_FANG_IMG_HU_XING:'NONE',
                    DEL_FANG_IMG_CERTIF:'NONE',
                    DEL_FANG_IMG_ATTORNEY:'NONE',
                    DEL_FANG_IMG_ID_CARD:'NONE',
                    DEL_FANG_CONTACT:'NONE',
                    FANG_PUBLISH:'NONE',
                    FANG_PAUSE:'NONE',
                    FANG_UN_PUBLISH:'NONE',
                    FANG_RE_PUBLISH:'NONE',
                    FANG_APPLY_PUBLIC:'NONE',
                    FANG_CONFIRM_PUBLIC:false,
                    FANG_REJECT_PUBLIC:false,
                    FANG_UNDO_PUBLIC:false,
                    MODIFY_FANG_INFO:'NONE',
                    UPDATE_FANG_BASE:'NONE',
                    UPDATE_FANG_EXT:'NONE',
                    VIEW_FANG_CONTACT:'NONE',
                    MODIFY_FANG_CONTACT :'NONE'
                },
                xiaoqu: {
                    CREATE_XIAO_QU: false,
                    MODIFY_XIAO_QU: false,
                    DEL_XIAO_QU: false,
                    CREATE_BUILDING: false,
                    MODIFY_BUILDING: false,
                    DEL_BUILDING: false
                },
                organization: {
                    ORG_MANAGEMENT: false,
                    UNBIND_DEVICE: false
                },
                company:{
                    PERMISSION_MANAGEMENT:false,
                    VIEW_AUDIT_LOG:false
                },
                franchisee:{
                    CREATE_FRANCHISEE: false,
                    LIST_FRANCHISEE: false,
                    MODIFY_FRANCHISEE:false
                },
                operation:{
                    OP_MANAGE_XY: false,
                },
                approval:{
                    APPROVAL_CREATE: false,
                    APPROVAL_APPROVE: false,
                    APPROVAL_LIST_EXPORT: false,
                }
            };
            _this.authorityFang = {};
            _this.authorityXiaoqu = {};
            _this.authorityOrganization = {};
            _this.authorityCompany = {};
            _this.authorityFranchisee = {};
            _this.authorityOperatioon = {};
            _this.authorityApproval = {};
            angular.copy(initData.fang, _this.authorityFang);
            angular.copy(initData.xiaoqu, _this.authorityXiaoqu);
            angular.copy(initData.organization, _this.authorityOrganization);
            angular.copy(initData.company, _this.authorityCompany);
            angular.copy(initData.franchisee, _this.authorityFranchisee);
            angular.copy(initData.operation, _this.authorityOperatioon);
            angular.copy(initData.approval, _this.authorityApproval);

            /* 部门员工树*/
            DepartmentService.getAllDepartment().done(function(data){
                var departmentData = data.map(function(item){
                    return {
                        name:item.name,
                        id:item.id,
                        treeId: item.id,
                        type: 'department',
                        parentId:item.parentId};
                });
                    EmployeeService.getAllEmployee().done(function(data){

                        var employeeData =data.map(function (item, index) {
                           return{
                               name: item.name,
                               id:item.id,
                               treeId : 'e'+item.id,
                               parentId: item.departmentId,
                               type: 'employee'
                           }
                        });
                        //Array.prototype.push.apply(departmentData, employeeData);
                        Array.prototype.push.apply(departmentData, employeeData);
                        _this.baseData.department = departmentData;
                        departmentTree(departmentData, 'treeId', 'parentId');
                    });

            });
            function departmentTree(data, idKey, pIdKey){
                function beforeClick(treeId, treeNode, clickFlag) {}
                function onClick(event, treeId, treeNode, clickFlag) {
                    if(treeNode.type !="employee"){
                        return;
                    }
                    _this.authorityConfig.targetId = treeNode.id;
                    _this.authorityConfig.targetType = 'EMPLOYEE';
                    _this.getAllAuthority(true);
                    $('#first-nav-tab').click();
                }
                function zTreeOnNodeCreated(event, treeId, treeNode){
                    if(treeNode.type =='employee'){
                        $('#'+treeNode.tId+'_ico').addClass('employee_ico')
                    }
                }
                var zTreeSetting = {
                    data: {
                        simpleData: {
                            enable: true
                        }
                    },
                    callback: {
                        beforeClick: beforeClick,
                        onClick: onClick,
                        onNodeCreated: zTreeOnNodeCreated
                    }
                };
                idKey && (zTreeSetting.data.simpleData.idKey = idKey);
                pIdKey && (zTreeSetting.data.simpleData.pIdKey = pIdKey);
                var zTreeObj = $.fn.zTree.init($("#departmentTree"), zTreeSetting, data);
                zTreeObj.expandAll(true);
            }
            /* end树*/
            /*岗位*/
            PositionService.getAllPosition().done(function(data){
                $scope.$apply(function(){
                    _this.baseData.position = data;
                });

            });
            /*end 岗位*/

            function authorityCommonDeal(data, authority){
                angular.forEach(data, function(item){
                    $scope.$apply(function() {
                        if (item.limits != null) {
                            authority[item.permission && item.permission.name] = item.limits;
                        } else if (item.scope != null) {
                            authority[item.permission && item.permission.name] = item.scope.name;
                        } else {
                            authority[item.permission && item.permission.name] = true;
                        }
                    });
                });
                $timeout(function(){
                    $('select').selectpicker("refresh");
                });
            };
            /*获取权限的通用方法*/
            function getAuthority(config, callback) {
                AuthorityService.getGrants(config).then(function(data){
                    callback && callback(data)
                }).fail(function(response){
                    SweetAlertHelp.fail({message: response && response.message});
                });
            }

            _this.getFangAuthority = function(){
                var config = angular.copy(_this.authorityConfig);
                config.category = _this.config.category.FANG;
                getAuthority(config, function(data){
                    angular.copy(initData.fang, _this.authorityFang);
                    authorityCommonDeal(data, _this.authorityFang);
                });
            };
            _this.getXiaoquAuthority = function(){
                var config = angular.copy(_this.authorityConfig);
                config.category = _this.config.category.XIAOQU;
                getAuthority(config, function(data){
                    angular.copy(initData.xiaoqu, _this.authorityXiaoqu);
                    authorityCommonDeal(data, _this.authorityXiaoqu);
                });
            };
            _this.getOrganizationAuthority = function(){
                var config = angular.copy(_this.authorityConfig);
                config.category = _this.config.category.ORGANIZATION;
                getAuthority(config, function(data){
                    angular.copy(initData.organization, _this.authorityOrganization);
                    authorityCommonDeal(data, _this.authorityOrganization);
                });
            };
            _this.getCompanyAuthority = function(){
                var config = angular.copy(_this.authorityConfig);
                config.category = _this.config.category.COMPANY;
                getAuthority(config, function(data){
                    angular.copy(initData.company, _this.authorityCompany);
                    authorityCommonDeal(data, _this.authorityCompany);
                });
            };
            _this.getFranchiseeAuthority = function(){
                var config = angular.copy(_this.authorityConfig);
                config.category = _this.config.category.FRANCHISEE;
                getAuthority(config, function(data){
                    angular.copy(initData.franchisee, _this.authorityFranchisee);
                    authorityCommonDeal(data, _this.authorityFranchisee);
                });
            };
            _this.getOperationAuthority = function(){
                var config = angular.copy(_this.authorityConfig);
                config.category = _this.config.category.OPERATION;
                getAuthority(config, function(data){
                    angular.copy(initData.operation, _this.authorityOperatioon);
                    authorityCommonDeal(data, _this.authorityOperatioon);
                });
            };
            _this.getApprovalAuthority = function(){
                var config = angular.copy(_this.authorityConfig);
                config.category = _this.config.category.APPROVAL;
                getAuthority(config, function(data){
                    angular.copy(initData.operation, _this.authorityOperatioon);
                    authorityCommonDeal(data, _this.authorityApproval);
                });
            };
            _this.getAllAuthority = function(reset){
                if(reset){
                    $('#first-nav-tab').click()
                }
                _this.getFangAuthority();
                _this.getXiaoquAuthority();
                _this.getOrganizationAuthority();
                _this.getCompanyAuthority();
                _this.getFranchiseeAuthority();
                _this.getOperationAuthority();
                _this.getApprovalAuthority();
            };

            _this.getPositionAllAuthority = function(position, reset){
                _this.baseData.currentPosition = position;
                $('a.position-list-nav[id='+position.id+']').addClass('curSelectedNode');
                $('a.position-list-nav[id!='+position.id+']').removeClass('curSelectedNode');
                _this.authorityConfig.targetId = position.id;
                _this.authorityConfig.targetType = 'POSITION';
                _this.getAllAuthority(reset)
            };

            _this.updateAuthority = function(type){
                if(!_this.authorityConfig.targetId){
                    SweetAlertHelp.fail({message:'请选择职员或者岗位'});
                    return;
                }
                var params = {};
                params.targetId = _this.authorityConfig.targetId;
                params.targetType = _this.authorityConfig.targetType;
                params.category = type;
                params.grants = [];
                var grantsData;
                switch(params.category){
                    case _this.config.category.FANG:
                        grantsData = angular.copy(_this.authorityFang);
                        break;
                    case _this.config.category.XIAOQU:
                        grantsData = angular.copy(_this.authorityXiaoqu);
                        break;
                    case _this.config.category.ORGANIZATION:
                        grantsData = angular.copy(_this.authorityOrganization);
                        break;
                    case _this.config.category.COMPANY:
                        grantsData = angular.copy(_this.authorityCompany);
                        break;
                    case _this.config.category.FRANCHISEE:
                        grantsData = angular.copy(_this.authorityFranchisee);
                        break;
                    case _this.config.category.OPERATION:
                        grantsData = angular.copy(_this.authorityOperatioon);
                        break;
                    case _this.config.category.APPROVAL:
                        grantsData = angular.copy(_this.authorityApproval);
                        break;
                }
                angular.forEach(grantsData, function(value,key){
                    if(value!=''){
                        params.grants.push(key+'.'+value)
                    }
                });
                AuthorityService.reGrant(params).then(function(){
                    if(_this.authorityConfig.targetType === 'POSITION'){
                        SweetAlertHelp.success({
                            message:"是否将权限更新至该岗位所有员工?",
                            showCancelButton :true,
                            cancelButtonText : "取消",
                            confirmButtonColor : "#5cb85c",
                            confirmButtonText : "更新",
                        },function(){
                            _this.reGrantByPosition();
                        });
                        _this.getPositionAllAuthority(_this.baseData.currentPosition);
                    }else{
                        SweetAlertHelp.success();
                        _this.getAllAuthority();
                    }

                }).fail(function(response){
                    SweetAlertHelp.fail({message: response && response.message});
                });
            }
            _this.reGrantByPosition = function(){
                AuthorityService.reGrantByPosition({positionId:_this.authorityConfig.targetId}).then(function(){
                    SweetAlertHelp.success();
                }).fail(function(response){
                    SweetAlertHelp.fail({message: response && response.message});
                });
            }
        }
        AuthorityCtrl.$inject =['$scope', '$timeout'];
        module.controller("AuthorityCtrl", AuthorityCtrl);

        angular.element(document).ready(function(){
           angular.bootstrap(document.getElementById("authorityConfig"), ["AuthorityModule"])
        });

    });
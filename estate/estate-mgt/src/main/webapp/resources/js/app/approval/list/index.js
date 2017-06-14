/**
 * Created by robin on 17/6/5.
 */
require(['main-app',
    contextPath + '/js/service/validation-service.js',
    contextPath + '/js/service/util-service.js',
    contextPath+'/js/service/city-service.js',
    contextPath+'/js/service/approval-service.js',
    contextPath+'/js/service/franchisee-service.js',
    contextPath + '/js/plugins/pagination/pagingPlugin.js',
    contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js',
    contextPath + '/js/directive/index.js',
    'chosen', 'select', 'datepicker.zh-cn', 'datetimepicker.zh-cn'],
    function(mainApp, ValidationService, UtilService, CityService, ApprovalService, FranchiseeService, pagingPlugin, SweetAlertHelp){
        var module = angular.module('ListModule',['directiveYk']);
        var config = {
            applyCompanyId: {
                init: false
            },
            applyDeptId: {
                init: false
            },
            applyId: {
                init: false
            }
        };
        function ListCtrl($scope, $location, $timeout){
            var _this = this;
            var pageConfig = {
                limit: 10,
                offset: 0,
                dataTotal:0,
                currentPage:1,
                init: false,
            };
            _this.filter ={
                type:'LEAVING',
                status:''
            };

            _this.config = {
                companyList: [{name:'',id:''}],
                signatureDepList: [{name:'',id:''}],
                signatureRepList: [{name:'',id:''}],
                status:{
                    CREATED:'CREATED',
                    APPROVED:'APPROVED',
                    REJECTED:'REJECTED'
                },
                type:{
                    LEAVING:'LEAVING',
                    BIZ_TRIP:'BIZ_TRIP',
                    COLD_VISIT:'COLD_VISIT',
                    SIGNING:'SIGNING',
                },
                companyType:{
                    YK:"盈科",
                    CHANNEL:"渠道",
                    SINGLE_STORE:"单店",
                    REGIONAL_AGENT:"区域代理"
                },

                exportLink:{
                    baseUrl: contextPath +'/api/approval/report-export?',
                    queryString:'type=LEAVING'
                },
            };
            _this.approvalList =[];
            _this.listFunc = /\/myList/.test($location.absUrl())?ApprovalService.myList:ApprovalService.list;

            _this.list = function(offset, currentPage){
                var param ={};
                for (var key in _this.filter){
                    if(!!_this.filter[key]){
                        param[key] = _this.filter[key];
                    }
                }
                if(!currentPage){
                    pageConfig.currentPage = 1;
                }
                _this.listFunc(param,
                    {'X-PAGING':'total=true&offset='+(offset||pageConfig.offset)+'&limit='+ pageConfig.limit}).then(function(response){
                    pagination(response.total);
                    _this.approvalList = response.items.map(function(item){
                        return item;
                    });
                    $scope.$apply();
                });
            };
            _this.list();

            _this.setFilterType = function(key, value){
                _this.filter[key]=value;
                _this.list();
                _this.exportLink();
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
                if(key === 'applyCompanyId'){
                    _this.loadParentCompanyDep(value);
                    _this.loadParentCompanyDepEmp(value);
                    _this.filter.applyDeptId = '';
                    _this.filter.applyId = '';
                }else if(key === 'applyDeptId'){
                    _this.loadParentCompanyDepEmp( _this.filter.applyCompanyId,  _this.filter.applyDeptId);
                }
                _this.list();
            };
            /*下拉框*/
            _this.initApplyChosen = function(id, key){
                $(id).chosen("destroy");
                if(!config[key].init){
                    config[key].init = !config[key].init;
                    $(id).chosen().change(function(e, result){
                        $scope.$apply(function(){
                            _this.filter[key] = result.selected;
                            _this.chosenChange(id, key, result.selected);
                            // $scope.signingForm.$dirty = true;
                        });
                    });
                    return;
                }
                $(id).chosen();
                $(id).trigger('chosen:updated');
            };
            
            /*日期框*/
            _this.datePickChange = function(key, value){
                $scope.$apply(function(){
                    _this.filter[key] = value;
                });
                _this.list();
                _this.exportLink();
            };

            _this.approved = function(id){
                SweetAlertHelp.confirm({text:'确认通过吗?'}, function (confirm) {
                    if(!confirm){
                        return false;
                    }
                    ApprovalService.approve({id :id, status:_this.config.status.APPROVED}).then(function(){
                        SweetAlertHelp.success();
                        $('#approvalDetail').modal('hide');
                        _this.list();
                    }).fail(function(res){
                        SweetAlertHelp.fail({message: res && res.message});
                    });
                })
            };

            _this.rejected = function(id) {
                SweetAlertHelp.input({title: '请输入审批意见', text: ' ', inputPlaceholder:'请输入意见'}, function (inputValue) {
                    if (inputValue === false) {
                        return false;
                    }
                    if (inputValue === "") {
                        swal.showInputError("请输入审批意见！");
                        return false
                    }
                    ApprovalService.approve({id :id, status:_this.config.status.REJECTED ,comment:inputValue}).then(function(){
                       SweetAlertHelp.success();
                        $('#approvalDetail').modal('hide');
                        _this.list();

                    }).fail(function(res){
                        SweetAlertHelp.fail({message: res && res.message});
                    });
                });
            };

            _this.detail = function(approval){
                $('#approvalDetail').modal('show');
                _this.currentApproval = approval;

            };
            _this.reportExport = function(){
                ApprovalService.reportExport(_this.filter).then(function(response){
                    console.log(response);
                })
            };
            _this.exportLink = function(){
                var param =[];
                for (var key in _this.filter){
                    if(!!_this.filter[key]){
                        param.push(key + '=' +_this.filter[key]);
                    }
                }
                _this.config.exportLink.queryString = param.join('&');
            };
            /*分页*/
            var pagination = function(dataTotal) {
                var id = '#list_paging';
                if(pageConfig.init){
                    pagingPlugin.update(id, {
                        totalCounts:dataTotal,
                        currentPage:pageConfig.currentPage
                    });
                    return;
                };
                pageConfig.init = true;
                var config = {
                    pagingId: id,
                    totalCounts:dataTotal,
                    pageSize: pageConfig.limit,
                    onChange: function (num, type) {
                        if(type === 'init'){
                            return;
                        }
                        pageConfig.currentPage = num;
                        _this.list((num-1)*pageConfig.limit, num);
                    }
                };
                pagingPlugin.init(config);

            };
        }
        ListCtrl.$inject=['$scope', '$location', '$timeout'];
        module.controller("ListCtrl", ListCtrl);
        
        angular.element(document).ready(function(){
            angular.bootstrap(document.getElementById("approvalList"),["ListModule"])
        })
    });
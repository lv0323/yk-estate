
require(['main-app',
        contextPath + '/js/service/application-service.js',
        contextPath + '/js/service/employee-service.js',
        contextPath + '/js/service/fang-service.js',
        contextPath + '/js/service/util-service.js',
        contextPath + '/js/app/fang/tools.js',
        contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        contextPath + '/js/directive/index.js',
        'jqPaginator', 'select', 'chosen', 'datetimepicker.zh-cn', 'sweetalert'],
    function (mainApp, ApplicationManagementService, EmployeeService, FangService, UtilService, Tools, SweetAlertHelp, pagingPlugin) {
        var pageConfig = {
            limit: 10,
            offset: 0,
            dataTotal:0,
            currentPage:1,
            init: false,
        };

        var ApplicationManagementModule=angular.module('ApplicationManagementModule',['directiveYk']);
        ApplicationManagementModule.controller("ApplicationsController", ['$scope','$timeout', '$q', '$interval', '$window', '$location', function($scope, $timeout, $q, $interval, $window) {

            $scope.approveApplication = function(applicationId){
                SweetAlertHelp.input({title: '请输入审批意见', text: ' ', inputPlaceholder:'请输入意见'}, function (inputValue) {
                    if (inputValue === false) {
                        return false;
                    }
                    var params ={};
                    params['applicationId'] = applicationId;
                    params['reviewerComments'] = 'inputValue';
                    ApplicationManagementService.approve(params).then(function(response){
                        SweetAlertHelp.success();
                        $scope.loadApplications();
                    }).fail(function(response){
                        SweetAlertHelp.fail({message:response&&response.message});
                    });fangCollect
                });
            };

            $scope.rejectApplication = function(applicationId){
                SweetAlertHelp.input({title: '请输入审批意见', text: ' ', inputPlaceholder:'请输入意见'}, function (inputValue) {
                    if (inputValue === false) {
                        return false;
                    }
                    if (inputValue === "") {
                        swal.showInputError("请输入审批意见！");
                        return false
                    }
                    var params = {};
                    params['applicationId'] = applicationId;
                    params['reviewerComments'] = inputValue;
                    ApplicationManagementService.reject(params).then(function (response) {
                        SweetAlertHelp.success();
                        $scope.loadApplications();
                    }).fail(function (response) {
                        SweetAlertHelp.fail({message: response && response.message});
                    });
                });
            };

            $scope.closeApplication = function(applicationId){
                // todo
                var params ={};
                params['applicationId'] = applicationId;
                params['reviewerComments'] = '审核人员关闭';
                ApplicationManagementService.close(params).then(function(response){
                    SweetAlertHelp.success();
                    $scope.loadApplications();
                }).fail(function(response){
                    SweetAlertHelp.fail({message:response&&response.message});
                });
            };

            /*页面相关内容*/
            $scope.page = {
                name: "房源列表",
                warn: {
                    title:'提示',
                    content:''
                },
                now:new Date().getTime(),
                userInfo : JSON.parse(localStorage.getItem('userInfo')),
            };

            var allTypes = (function() {
                return ['PUBLISH_HOUSE', 'UN_PUBLISH_HOUSE', 'UN_PUBLIC_HOUSE', 'PUBLIC_HOUSE'];
            })();

            var allStatus = (function() {
                return ['NEW', 'APPROVED', 'REJECTED', 'CLOSED'];
            })();


            $scope.filter ={
                'types':allTypes,
                'id':'',
                'applicantId':'',
                'status':['NEW'],
                'startTime': '',
                'endTime': ''
            };

            $scope.pageStatus = {
                'type':'ALL',
                'status':'NEW'
            };

            $scope.filterType = function(type) {
                if (type === 'ALL') {
                    $scope.filter.types = allTypes;
                } else {
                    $scope.filter.types = [type];
                }

                $scope.pageStatus.type = type;

                $scope.loadApplications();
            };

            $scope.filterStatus = function(status) {
                if (status === 'ALL') {
                    $scope.filter.status = allStatus;
                } else {
                    $scope.filter.status = [status];
                }

                $scope.pageStatus.status = status;

                $scope.loadApplications();
            };

            $scope.applicationList = [];


            /*分页*/
            var pagination = function(dataTotal) {
                var id = '#pagination';
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
                    totalCounts: dataTotal,
                    visiblePages: 10,
                    pageSize: pageConfig.limit,
                    onChange: function (num, type) {
                        if(type === 'init'){
                            return;
                        }
                        pageConfig.currentPage = num;
                        $scope.list((num-1)*pageConfig.limit, num);
                    }
                };
                pagingPlugin.init(config);

            };

            /*通过编号查找*/
            $scope.searchById = function(){
                $scope.loadApplications();
            };

            /*分页*/
            var pagination = function(dataTotal) {
                var id = '#pagination';
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
                    totalCounts: dataTotal,
                    visiblePages: 10,
                    pageSize: pageConfig.limit,
                    onChange: function (num, type) {
                        if(type === 'init'){
                            return;
                        }
                        pageConfig.currentPage = num;
                        $scope.loadApplications((num-1)*pageConfig.limit, num);
                    }
                };
                pagingPlugin.init(config);

            };

            // application list
            $scope.loadApplications = function(offset, currentpage){
                var param = {};

                for (var key in $scope.filter){
                    if(!!$scope.filter[key]){
                        param[key] = $scope.filter[key];
                    }
                }

                if(!currentpage){
                    pageConfig.currentPage = 1;
                }
                ApplicationManagementService.findApplications(param,{'X-PAGING':'total=true&offset='+(offset||pageConfig.offset)+'&limit='+ pageConfig.limit}).then(function(response){
                    $scope.$apply(function(){
                        pagination(response.total);
                        $scope.applicationList = response.items;
                    });
                }).fail(function(response){
                    SweetAlertHelp.fail({message:response&&response.message});
                });
            };

            $scope.loadApplications();


        }]);

        angular.element(document).ready(function() {
            angular.bootstrap(document.getElementById("ApplicationsWrapper"),["ApplicationManagementModule"])
        });
    });
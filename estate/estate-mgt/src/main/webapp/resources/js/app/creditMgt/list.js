/**
 * Created by yanghong on 6/19/17.
 */
require(['main-app',
        contextPath + '/js/service/credit-service.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js',
        contextPath + '/js/directive/index.js'],
    function (mainApp, CreditService, pagingPlugin, SweetAlertHelp) {

        var pageConfig = {
            limit: 8,
            offset: 0,
            dataTotal:0,
            currentPage:1,
            init: false
        };

        var CreditMgtListModule=angular.module('CreditMgtListModule',['directiveYk']);
        CreditMgtListModule.controller("CreditMgtListCtrl", ['$scope','$timeout', '$q', '$interval', '$window', '$location', function ($scope, $timeout, $q, $interval, $window) {

            $scope.agentList = [];

            /*批量新增中介*/
            $scope.toAddAgentList = {
                firstNewAgent:'',
                newAgentList: []
            };

            $scope.agentAdd = function () {
                $scope.toAddAgentList.newAgentList.unshift('');
            };

            $scope.agentRemove = function (item, index) {
                $scope.toAddAgentList.newAgentList.splice(index, 1);
            };

            $scope.confirmAddAgent = function () {
                var newAgents = angular.copy($scope.toAddAgentList.newAgentList);
                if(!$scope.toAddAgentList.firstNewAgent){
                    SweetAlertHelp.fail({message: '公司名称不能为空'});
                }else{
                    newAgents.unshift($scope.toAddAgentList.firstNewAgent);
                    //submit add
                    CreditService.addAgent({corps:newAgents, status: 'NEW'})
                        .done(function () {
                            SweetAlertHelp.success();
                            $scope.list();
                            $('#addAgentDialog').modal('hide');
                        }).fail(function (res) {
                            SweetAlertHelp.fail({message: res && res.message});
                        });
                }

            };

            /*筛选栏隐藏／显示*/
            $scope.page = {
                collapse: true
            };

            $scope.triggerCollapse = function () {
                $scope.page.collapse = !$scope.page.collapse;
            };

            /*设置筛选条件*/
            $scope.filter = {
                status: '',
                corpName: ''
            };

            $scope.setFilterType = function (key, value) {
                $scope.filter[key]=value;
                $scope.list();
            };

            /*激活中介*/
            $scope.activateCorp = function (agentId) {
                CreditService.activateAgent(agentId)
                    .done(function () {
                        SweetAlertHelp.success();
                        $scope.list();
                    })
                    .fail(function (res) {
                        SweetAlertHelp.fail({message: res && res.message});
                    });
            };

            /*拒绝中介*/
            $scope.rejectCorp = function (agentId) {
                CreditService.rejectAgent(agentId)
                    .done(function () {
                        SweetAlertHelp.success();
                        $scope.list();
                    })
                    .fail(function (res) {
                        SweetAlertHelp.fail({message: res && res.message});
                    });
            };

            /*冻结中介*/
            $scope.suspendCorp = function (agentId) {
                CreditService.suspendAgent(agentId)
                    .done(function () {
                        SweetAlertHelp.success();
                        $scope.list();
                    })
                    .fail(function (res) {
                        SweetAlertHelp.fail({message: res && res.message});
                    });
            };

            /*分页*/
            var pagination = function(dataTotal) {
                var id = '#agentList_paging';
                if(pageConfig.init){
                    pagingPlugin.update(id, {
                        totalCounts:dataTotal,
                        currentPage:pageConfig.currentPage
                    });
                    return;
                }
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

            /*列表展示*/
            $scope.list = function (offset, currentPage) {
                if(!currentPage){
                    pageConfig.currentPage = 1;
                }
                CreditService.getAgentList($scope.filter, {'X-PAGING':'total=true&offset='+(offset||pageConfig.offset)+'&limit='+ pageConfig.limit})
                    .done(function (res) {
                        $scope.$apply(function () {
                            $scope.agentList = res.items.map(function (item) {
                                return {
                                    id: item.id,
                                    name: item.name,
                                    status: item.status,
                                    positiveCount: item.positiveCount,
                                    negativeCount: item.negativeCount,
                                    visitCount: item.visitCount,
                                    commentCount: item.commentCount
                                }
                            });
                            pagination(res.total);
                        });
                    })
                    .fail(function (res) {
                        SweetAlertHelp.fail({message: res && res.message});
                    });
            };

            $scope.list();



        }]);

        angular.element(document).ready(function() {
            angular.bootstrap(document.getElementById("creditMgtListWrapper"),["CreditMgtListModule"])
        });

        });
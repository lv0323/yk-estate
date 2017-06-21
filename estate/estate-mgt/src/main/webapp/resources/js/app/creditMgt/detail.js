/**
 * Created by yanghong on 6/19/17.
 */
require(['main-app',
        contextPath + '/js/service/credit-service.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js',
        contextPath + '/js/service/util-service.js',
        contextPath + '/js/directive/index.js'],
        function (mainApp, CreditService, pagingPlugin, SweetAlertHelp, UtilService) {
            var pageConfig = {
                limit: 8,
                offset: 0,
                dataTotal:0,
                currentPage:1,
                init: false
            };

            var detailAgentModule = angular.module('detailAgentModule', ['directiveYk']);
            detailAgentModule.controller( "DetailAgentCtrl", ['$scope','$timeout', '$q', '$interval', '$window', '$location', function ($scope, $timeout, $q, $interval, $window) {

                $scope.agent = {
                    detail: {},
                    commentList: []
                };

                /*获得中介公司基本信息*/
                var agentId =UtilService.getUrlParam('agentId');
                $scope.displayBasicInfo = function () {
                    CreditService.getSpecifiedAgentBasicInfo({corpId: agentId})
                        .done(function (res) {
                            $scope.$apply(function () {
                                $scope.agent.detail = {
                                    id: res.id,
                                    name: res.name,
                                    status: res.status,
                                    commentCount: res.commentCount,
                                    positiveCount: res.positiveCount,
                                    negativeCount: res.negativeCount,
                                    visitCount: res.visitCount
                                };
                                $scope.toEditAgent = {
                                    positiveCount:$scope.agent.detail.positiveCount,
                                    negativeCount:$scope.agent.detail.negativeCount,
                                    visitCount:$scope.agent.detail.visitCount
                                };
                            });

                            $scope.displayComments();
                        })
                        .fail(function (res) {
                            SweetAlertHelp.fail({message: res && res.message});
                        });
                };
                $scope.displayBasicInfo();

                /*编辑中介公司基本信息*/
                $scope.confirmEditAgent = function () {
                    CreditService.updateSpecifiedAgentBasicInfo($scope.agent.detail.id, $scope.toEditAgent)
                        .done(function () {
                            SweetAlertHelp.success();
                            $scope.displayBasicInfo();
                            $('#editAgentDialog').modal('hide');
                        })
                        .fail(function (res) {
                            SweetAlertHelp.fail({message: res && res.message});
                        });
                };

                /*分页*/
                var pagination = function(dataTotal) {
                    var id = '#comment_paging';
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
                            $scope.displayComments((num-1)*pageConfig.limit, num);
                        }
                    };
                    pagingPlugin.init(config);

                };

                /*展示中介公司评论*/
                $scope.displayComments = function (offset, currentPage) {
                    if(!currentPage){
                        pageConfig.currentPage = 1;
                    }
                    CreditService.getSpecifiedAgentComment($scope.agent.detail.id, {'X-PAGING':'total=true&offset='+(offset||pageConfig.offset)+'&limit='+ pageConfig.limit})
                        .done(function (res) {
                            $scope.$apply(function () {
                                $scope.agent.commentList = res.items.map(function (item) {
                                    return {
                                        id: item.id,
                                        avatar: item.avatar.match('^(http)') ? item.avatar : '',
                                        nicky: item.nicky,
                                        createTime: item.createTime.split(' ')[0],
                                        content: item.content,
                                        positiveCount: item.positiveCount,
                                        tags: item.tags?item.tags.split('_'):[]
                                    }
                                });
                                pagination(res.total);
                            });
                        })
                        .fail(function (res) {
                            SweetAlertHelp.fail({message: res && res.message});
                        });
                };


                /*编辑中介公司评论*/
                $scope.deleteCmt = function (cmtId) {
                    SweetAlertHelp.confirm({text:'该操作不可恢复'}, function () {
                        CreditService.deleteSpecifiedAgentComment($scope.agent.detail.id, cmtId)
                            .done(function () {
                                SweetAlertHelp.success();
                                $scope.displayBasicInfo();
                            })
                            .fail(function (res) {
                                SweetAlertHelp.fail({message: res && res.message});
                            });
                    })

                };

            }]);

            angular.element(document).ready(function() {
                angular.bootstrap(document.getElementById("detailAgentWrapper"),["detailAgentModule"])
            });
    });
/**
 * Created by yanghong on 5/18/17.
 */
/**
 * Created by yanghong on 5/18/17.
 */
require(['main-app',
        contextPath + '/js/service/storePartner-service.js',
        contextPath+'/js/service/franchisee-service.js',
        contextPath + '/js/service/util-service.js',
        contextPath+'/js/service/city-service.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js',
        contextPath + '/js/directive/index.js',
        'chosen'],
    function (mainApp, StoreService, FranchiseeService, UtilService, CityService, pagingPlugin, SweetAlertHelp) {

        var pageConfig = {
            limit: 8,
            offset: 0,
            dataTotal:0,
            currentPage:1,
            init: false
        };

        var StorePartnerListModule=angular.module('StorePartnerListModule',['directiveYk']);
        StorePartnerListModule.controller("StorePartnerListCtrl", ['$scope','$timeout', '$q', '$interval', '$window', '$location', function ($scope, $timeout, $q, $interval, $window) {

            var config = {
                cityId: {
                    init: false
                },
                parentId: {
                    init: false
                }
            };

            $scope.filter ={
                cityId:'',
                companyType: 'SINGLE_STORE',
                parentId: ''
            };

            $scope.page = {
                collapse:true
            };

            $scope.cityList = [{name:'',id:''}];
            $scope.superiorDepList = [{name:'',id:''}];

            $scope.storePartnerList = [
                {id: '', name:'', deptsCount: '', employeeCount: '', cityName: '', boss: {name: '', mobile:''}, partA: {name: '', companyAbbr: '', mobile: ''}, startDate: '', endDate: ''}
            ];

            /*筛选栏隐藏／显示*/
            $scope.triggerCollapse = function () {
                $scope.page.collapse = !$scope.page.collapse;
            };

            /*筛选栏城市列表初始化*/
            CityService.getCity().then(function(response){
                $scope.cityList = response.map(function(item){
                    return {
                        name: item.name,
                        id: item.id
                    }
                });
            });

            /*可能的签约公司列表初始化*/
            FranchiseeService.getParentCompany().then(function (response) {
                $scope.superiorDepList = response.map(function (item) {
                    return {
                        name: item.abbr || item.name,
                        id: item.id
                    }
                });
            });

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

            /*分页*/
            var pagination = function(dataTotal) {
                var id = '#PartnerList_paging';
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

            $scope.list = function (offset, currentPage) {
                if(!currentPage){
                    pageConfig.currentPage = 1;
                }

                FranchiseeService.listCompany($scope.filter, {'X-PAGING':'total=true&offset='+(offset||pageConfig.offset)+'&limit='+ pageConfig.limit}).then(function (res) {
                    $scope.$apply(function() {
                        $scope.storePartnerList = res.items.map(function (item) {
                            return {
                                id: item.id,
                                name: item.name,
                                singleStoreCount: item.singleStoreCount || '未知',
                                employeeCount: item.employeeCount || '未知',
                                cityName: item.cityName,
                                boss: {name: item.boss.name, mobile: item.boss.mobile},
                                partA: {name: item.partA && item.partA.name, companyAbbr: item.partA && item.partA.companyAbbr, mobile: item.partA && item.partA.mobile},
                                startDate: UtilService.timeStamp2Date(item.startDate),
                                endDate: UtilService.timeStamp2Date(item.endDate)
                            }
                        });
                        pagination(res.total);
                    });
                });
                // pagination(1)
            };

            $scope.list()

        }]);

        angular.element(document).ready(function() {
            angular.bootstrap(document.getElementById("storePartnerListWrapper"),["StorePartnerListModule"])
        });


    });
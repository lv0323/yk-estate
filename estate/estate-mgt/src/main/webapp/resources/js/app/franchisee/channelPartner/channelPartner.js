/**
 * Created by yanghong on 5/18/17.
 */
require(['main-app',
        contextPath + '/js/service/channelPartner-service.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js',
        contextPath + '/js/directive/index.js',
        'chosen'],
    function (mainApp, ChannelService, pagingPlugin, SweetAlertHelp) {

        var pageConfig = {
            limit: 8,
            offset: 0,
            dataTotal:0,
            currentPage:1,
            init: false
        };

        var ChannelPartnerListModule=angular.module('ChannelPartnerListModule',['directiveYk']);
        ChannelPartnerListModule.controller("ChannelPartnerListCtrl", ['$scope','$timeout', '$q', '$interval', '$window', '$location', function ($scope, $timeout, $q, $interval, $window) {

            var config = {
                cityId: {
                    init: false
                },
                signatureRepId: {
                    init: false
                }
            };

            $scope.filter ={
                cityId:'',
                signatureRepId:''
            };
            
            $scope.page = {
                collapse:true
            };

            $scope.cityList = [{id: 1, name: '北京'}];
            $scope.signatureRepList = [{id:1, name: '盈科总部'}];
            $scope.channelPartnerList = [
                {id: 0, name:'北京盈科渠道商', totalStores: 134, totalAgent: 546, cityName: '北京', representative: '李欣儿', representativeMobile:'18688889999', signatureRep:'范哲思', signatureRepCompany:'盈科地产', signatureRepMobile:'13566667777', signatureLowerYear: '2014-5-8', signatureUpperYear: '2018-7-9'},
                {id: 1, name:'天津盈科渠道商', totalStores: 104, totalAgent: 753, cityName: '天津', representative: '王欣儿', representativeMobile:'18677779999', signatureRep:'肖哲思', signatureRepCompany:'肖式地产', signatureRepMobile:'13577778888', signatureLowerYear: '2014-5-8', signatureUpperYear: '2018-7-9'}
            ];

            /*筛选栏隐藏／显示*/
            $scope.triggerCollapse = function () {
                $scope.page.collapse = !$scope.page.collapse;
            };

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
            
            $scope.list = function () {
                pagination(1)
            }

            $scope.list()

        }]);

        angular.element(document).ready(function() {
            angular.bootstrap(document.getElementById("channelPartnerListWrapper"),["ChannelPartnerListModule"])
        });


    });
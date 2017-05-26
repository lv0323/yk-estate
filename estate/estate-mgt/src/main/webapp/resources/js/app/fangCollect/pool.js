require(['main-app',
        contextPath + '/js/service/identity-service.js',
        contextPath + '/js/service/validation-service.js',
        contextPath + '/js/service/city-service.js',
        contextPath + '/js/service/department-service.js',
        contextPath + '/js/service/employee-service.js',
        contextPath + '/js/service/fang-collect-service.js',
        contextPath + '/js/service/util-service.js',
        contextPath + '/js/app/fang/tools.js',
        contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        contextPath + '/js/directive/index.js',
        'jqPaginator', 'select', 'chosen', 'datetimepicker.zh-cn', 'sweetalert'],
    function (mainApp, IdentityService, ValidationService, CityService, DepartmentService, EmployeeService, FangCollectService, UtilService, Tools, SweetAlertHelp, pagingPlugin) {
        var pageConfig = {
            limit: 12,
            offset: 0,
            dataTotal:0,
            currentPage:1,
            init: false,
        };

        var HouseListModule=angular.module('HousePoolModule',['directiveYk']);
        HouseListModule.controller("HousePoolCtrl", ['$scope','$timeout', '$q', '$interval', '$window', '$location', function($scope, $timeout, $q, $interval, $window) {
            /*页面相关内容*/
            $scope.page ={
                name: "房源列表",
                layoutString:'',
                collapse:false,
                now:new Date().getTime(),
                userInfo : JSON.parse(localStorage.getItem('userInfo'))
            };
            $scope.houseList = [];
            $scope.search ={
                id:''
            };
            $scope.filter ={
                cityId:'',
                bizType:'',
                districtId: '',
                subDistrictId:'',
                houseType:'',
                sCounts:'',
                timeType: '',
                resident: '',
                decorate: '',
                order:'DEFAULT',
                orderType:'',
            };
            $scope.districtList =[];
            $scope.subDistrictList =[];
            $scope.estateList = [];

            /*区域*/
            CityService.getDistrict().then(function(response){
                $scope.filter.cityId = response[0].cityId;
                $scope.list();
                $scope.districtList = response.map(function(item){
                    return {
                        name: item.name,
                        id: item.id
                    }
                });
            });
            $scope.getSubDistrict= function(id) {
                if (id == "") {
                    $scope.filter.subDistrictId = '';
                    $scope.subDistrictList = [];
                    return;
                }
                CityService.getSubDistrict({id: id}).then(function (response) {
                    $scope.$apply(function(){
                        $scope.subDistrictList = response.map(function(item){
                            return {
                                name: item.name,
                                id: item.id
                            }
                        });
                    });

                })
            };
            $scope.setDistrict = function(id){
                $scope.setFilterType('districtId', id);
                //$scope.getSubDistrict(id);
            };

            $scope.setDistract = function(value){
                $scope.filter.distractId = value;
            };
            $scope.setFilterType = function(key,value){
                if(key === 'process'){
                    $scope.filter['subProcess'] = '';
                }
                $scope.filter[key]=value;
                $scope.list();
            };
            /*户型*/
            $scope.sCountsList =[
                {name: '不限', value: ''},
                {name: '1室', value: '1'},
                {name: '2室', value: '2'},
                {name: '3室', value: '3'},
                {name: '4室', value: '4'},
                {name: '5室', value: '5'},
                {name: '6室', value: '6'}
            ];

            $scope.timeCheck = function(e){
                if(!$scope.filter.timeType){

                }
                $scope.list();
            };
            $scope.setDate = function(key, value){
                $scope.filter[key] = value;
                $scope.timeCheck()
            };
            $scope.setXuanxiang = function(){
                console.log($scope.filter.xuanxiang);
            };
            $scope.fySortlist = [
                {name: '系统默认', value: 'DEFAULT'},
                {name: '房源楼层', value: 'FLOOR'},
                {name: '房源总价', value: 'PUBLISH_PRICE'},
                {name: '房源单价', value: 'UNIT_PRICE'},
                {name: '建筑面积', value: 'AREA'}
            ];
            $scope.setSort = function(item){
                if(item == 'DEFAULT'){
                    if(item == $scope.filter.order){
                        return;
                    }
                    $scope.filter.order = item;
                    $scope.filter.orderType = '';

                } else if(item == $scope.filter.order){
                    $scope.filter.orderType = $scope.filter.orderType=='UP'?'DOWN':'UP'
                }else{
                    $scope.filter.order = item;
                    $scope.filter.orderType = 'DOWN'
                }
                $scope.list();
            };

            /*分页*/
            var pagination = function(dataTotal) {
                var id = '#houseList_paging';
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
            /*筛选*/
            $scope.triggerCollapse = function(){
                $scope.page.collapse = !$scope.page.collapse;
            };
            /*房源列表*/
            $scope.list = function(offset, currentpage){
                var param ={};
                for (var key in $scope.filter){
                    if(!!$scope.filter[key]){
                        param[key] = $scope.filter[key];
                    }
                }
                if(param.order && param.orderType){
                    /*跟进日期排序特殊处理*/
                    if(param.order === 'FOLLOW_TIME'){
                        param.order = param.order + '_' + ( param.orderType == 'UP'?'CLOSER' : 'FARTHER');
                    }else{
                        param.order = param.order + '_' + param.orderType;
                    }
                }
                if(!currentpage){
                    pageConfig.currentPage = 1;
                }
                FangCollectService.list(param,{'X-PAGING':'total=true&offset='+(offset||pageConfig.offset)+'&limit='+ pageConfig.limit}).then(function(response){
                    $scope.houseList =[];
                    $scope.$apply(function(){
                        pagination(response.total);
                        $scope.houseList = response.items.map(function(item){
                            item.layoutFormat = Tools.layoutFormat({
                                sCounts:item.sCounts,
                                tCounts:item.tCounts,
                                cCounts:item.cCounts,
                                wCounts:item.wCounts,
                                ytCounts:item.ytCounts}) ;
                            return item;
                        });

                    });
                }).fail(function(response){
                    SweetAlertHelp.fail({message:response&&response.message});
                });
            };
        }]);

        angular.element(document).ready(function() {
            angular.bootstrap(document.getElementById("housePoolWrapper"),["HousePoolModule"])
        });
    });
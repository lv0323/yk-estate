/**
 * Created by robin on 17/2/17.
 */
require(['main-app',
        contextPath + '/js/service/identity-service.js',
        contextPath + '/js/service/validation-service.js',
        contextPath + '/js/service/city-service.js',
        contextPath + '/js/service/department-service.js',
        contextPath + '/js/service/employee-service.js',
        contextPath + '/js/service/fang-service.js',
        contextPath + '/js/service/util-service.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        'jqPaginator', 'select', 'chosen', 'datetimepicker.zh-cn'],
    function (mainApp, IdentityService, ValidationService, CityService, DepartmentService, EmployeeService, FangService, UtilService, pagingPlugin) {
        var pageConfig = {
            limit: 8,
            init: false,
        };
        var HouseListModule=angular.module('HouseListModule',[]);
        HouseListModule.directive('datetimepicker', function () {
            return {
                restrict: 'A',
                link : function (scope, element, attrs) {
                    $(element).datetimepicker({
                        format: 'yyyy-mm-dd',
                        weekStart: 1,
                        autoclose: true,
                        startView: 2,
                        minView: 2,
                        pickerPosition:'bottom-left',
                        forceParse: false,
                        language: 'zh-CN'
                    });
                    $(element).find('input').change(function(){
                        scope[attrs.change](attrs.key,$(this).val());
                    });
                }
            }
        });
        HouseListModule.directive('selectPicker', ['$timeout', function ($timeout) {
            return {
                restrict: 'A',
                /*controller: AddHouseCtrl,
                 controllerAs: 'ctrl',*/
                link: function (scope, element, attrs) {
                    $timeout(function () {
                        $(element).selectpicker({
                            style: 'btn-default',
                            dropupAuto: false,
                            size: 8,
                        });
                    }, 0);
                }
            }
        }]);
        HouseListModule.directive('repeatDone',['$timeout', function($timeout){
            return {
                link: function(scope,element,attrs){
                    if (scope.$last) {
                        $timeout(function() {
                            scope.$eval(attrs['repeatDone']);   // 执行绑定的表达式
                        });
                    }
                }
            }
        }]);
        HouseListModule.controller("HouseListCtrl", ['$scope','$timeout', '$interval','$window','$location', function($scope, $timeout, $interval, $window) {
            var config = {
                departmentId: {
                    init: false
                },
                employeeId: {
                    init: false
                },
            };
            /*页面相关内容*/
            $scope.page ={
                name: "房源列表",
                warn: {
                    title:'提示',
                    content:''
                },
                layoutString:''
            };
            $scope.filter ={
                cityId:1,
                bizType:'',
                districtId: '',
                subDistrictId:'',
                houseType:'',
                process:'',
                minArea:'',
                maxArea:'',
                areaType:'',
                sCounts:'',
                hts:[],
                htsObj:{},
                departmentId:'',
                employeeId:'',
                includeChildren: false,
                timeType: '',
                startDate: '',
                endDate: '',
                resident: '',
                decorate: '',
                propertyType: '',
                certifType: '',
                delegateType: '',
                xuanxiang: '',
                order:'DEFAULT',
                orderType:''
            };
            $scope.districtList =[];
            $scope.subDistrictList =[];
            $scope.employeeList =[{name:'',id:''}];
            /*弹出框*/
            $scope.showWarn = function(param){
                $('#warnModel').modal('show');
                $timeout(function(){
                    $scope.page.warn.title= param.title;
                    $scope.page.warn.content= param.content;
                    $scope.page.warn.closeF= param.closeF;
                },30)
            };
            /*区域*/
            CityService.getDistrict().then(function(response){
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
                $scope.getSubDistrict(id);
            };
            $scope.getEmployee = function(key){
                $scope.employeeList =[{name:'',id:''}];
                $scope.filter.employeeId = '';
                if(key === ''){
                    return;
                }
                EmployeeService.getAllEmployee({departmentId: key}).done(function (response) {
                    $scope.$apply(function(){
                        if(response && response.items && response.items.length>0){
                            $scope.employeeList = response.items.map(function(item){
                                return {
                                    name: item.name,
                                    id: item.id
                                }
                            });
                        }else{
                            $scope.employeeList =[{name:'',id:''}];
                        }
                    });
                }).fail(function(){
                    $scope.employeeList =[{name:'',id:''}];
                });
            };
            $scope.includeChildrenCheck = function(){
                if($scope.filter.departmentId){
                    $scope.list();
                };
            };
            $scope.chosenChange = function(id, key, value){
                if(key === 'departmentId'){
                    $scope.getEmployee(value);
                }
            };
            $scope.initChosen = function(id, key){
                $(id).chosen("destroy");
                if(!config[key].init){
                    config[key].init = !config[key].init;
                    $(id).chosen().change(function(e, result){
                        $scope.$apply(function(){
                            $scope.filter[key] = result.selected;
                            $scope.chosenChange(id, key, result.selected);
                            $scope.list();
                        });
                    });
                    return;
                }
                $(id).chosen();
                $(id).trigger('chosen:updated');
            };
            $scope.setDistract = function(value){
                $scope.filter.distractId = value;
            };
            $scope.setFilterType = function(key,value){
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
            $scope.setQuality = function(value){
                console.log($scope.filter.quality);
            };
            $scope.characterList =[
                {name :'唯一住房', value:'10140', key:'unique'},
                {name :'满五年', value:'10141', key:'fiveYears'},
                {name :'满两年', value: '10143', key:'towYears'}
            ];
            $scope.setCharacter = function(value){
                console.log($scope.filter.character);
            };
            $scope.setTradeType = function(e) {
                console.log($scope.filter.tradeType);
            };
            $scope.setDepId = function(e) {
                console.log($scope.filter.depId);
            };
            $scope.depExpList=[
                {name :'含下级', value:'true', key:'includeChildren'},
            ];
            $scope.timeCheck = function(e){
                if(!$scope.filter.timeType){
                    return;
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
              /*  {name: '楼盘名称', value: '1'},
                {name: '栋座名称', value: '2'},*/
                {name: '房源楼层', value: 'FLOOR'},
                {name: '房源总价', value: 'PUBLISH_PRICE'},
                {name: '房源单价', value: 'UNIT_PRICE'},
                {name: '建筑面积', value: 'AREA'},
                {name: '最后跟进日期', value: 'FOLLOW_TIME'}
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
            $scope.ddCount = function(){
                console.log('count');
            };
            /*部门*/
            DepartmentService.getAllDepartment().done(function(data){
                $scope.depList =data.map(function(item, index){
                    var indent = "";
                    for(var i = 0;i<item.level;i++){
                        indent += "   ";
                    }
                   return {
                       id: item.id,
                       name: indent+ item.name,
                   }
                });
            });

            $scope.checkArea = function(key){
                if($scope.filter.minArea && $scope.filter.maxArea && $scope.filter.minArea > $scope.filter.maxArea ){
                    $scope.showWarn({title:'提示',content:'最小面积不能大于最大面积'});
                    $scope.filter[key] = '';
                    return;
                }
                $scope.filter.areaType = 'custom'
                $scope.list();
            };

            /*分页*/
            var pagination = function(dataTotal) {
                $scope.state ={
                    collapse:false
                };
                if(pageConfig.init){
                    return;
                };
                pageConfig.init = true;
                var config = {
                    pagingId:'#houseList_paging',
                    totalCounts:dataTotal,
                    pageSize: pageConfig.limit,
                    onChange: function (num, type) {
                        if(type === 'init'){
                            return;
                        }
                        getCompany((num-1)*pageConfig.limit, pageConfig.limit);
                    }
                };
                pagingPlugin.init(config);

            };
            pagination(100);

            //筛选
            $scope.triggerCollapse = function(){
                $scope.state.collapse = !$scope.state.collapse;
            };
            $scope.list = function(offset){
                var param ={};
                for (var key in $scope.filter){
                    if(!!$scope.filter[key]){
                        param[key] = $scope.filter[key];
                    }
                }
                param.hts = [];
                for(var key in param.htsObj){
                    if(!!param.htsObj[key]){
                        param.hts.push(key);
                    }
                }
                delete param.htsObj;
                if(!param.areaType){
                    delete param.minArea;
                    delete param.maxArea;
                }
                if(param.order &&param.orderType){
                    param.order = param.order + '_' + param.orderType;
                }
                FangService.list(param,{'X-PAGING':'total=true&offset='+(offset||0)+'&limit=8'}).then(function(response){
                    console.log(response);
                });
            };
            $scope.houseList = [
                {
                    bizType:'sale',
                    houseImg:'http://img.12157.top/upload/uploadfile/pic/web/14816115366066107.png',
                },{
                    bizType:'rent',
                    houseImg:'http://img.12157.top/upload/uploadfile/pic/web/14816115366066107.png',
                }
            ];

        }]);

        angular.element(document).ready(function() {
            angular.bootstrap(document.getElementById("houseListWrapper"),["HouseListModule"])
        });
    });
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
        contextPath + '/js/directive/index.js',
        'jqPaginator', 'select', 'chosen', 'datetimepicker.zh-cn'],
    function (mainApp, IdentityService, ValidationService, CityService, DepartmentService, EmployeeService, FangService, UtilService, pagingPlugin) {
        var pageConfig = {
            limit: 8,
            offset: 0,
            dataTotal:0,
            currentPage:1,
            init: false,
        };
        var module=angular.module('followModule',['directiveYk']);
        module.controller("FollowCtrl", ['$scope','$timeout', '$interval','$window','$location', function($scope, $timeout, $interval, $window) {
            var config = {
                departmentId: {
                    init: false
                },
                employeeId: {
                    init: false
                },
                ioDepartmentId: {
                    init: false
                },
                ioEmployeeId: {
                    init: false
                },
            };
            /*页面相关内容*/
            $scope.page ={
                name: "房源跟进",
                warn: {
                    title:'提示',
                    content:''
                },
                layoutString:'',
                collapse:false,
                now:new Date().getTime()
            };
            $scope.followList = [];

            $scope.houseList = [];
            $scope.filter ={};
            $scope.depExpList=[
                {name :'含下级', value:'true', key:'includeChildren'},
            ];
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

            $scope.setFilterType = function(key,value){
                $scope.filter[key]=value;
                $scope.list();
            };
            $scope.setDepId = function(e) {
                console.log($scope.filter.depId);
            };
            $scope.setDate = function(key, value){
                $scope.filter[key] = value;
                $scope.timeCheck()
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

            /*分页*/
            var pagination = function(dataTotal) {
                var id = '#follow_paging';
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
                        $scope.list((num-1)*pageConfig.limit, num);
                    }
                };
                pagingPlugin.init(config);

            };
            //筛选
            $scope.triggerCollapse = function(){
                $scope.page.collapse = !$scope.page.collapse;
            };
            $scope.list = function(offset){
                var now = new Date().getTime() ;
                FangService.listFollow({},{'X-PAGING':'total=true&offset='+(offset||pageConfig.offset)+'&limit='+ pageConfig.limit}).then(function(response){
                    pagination(response.total);
                    $scope.followList = response.items.map(function(item){
                        if(item.publishTime){
                            item.publishedDay = (now - item.publishTime)/(24 * 3600 * 1000)
                        }
                        return item
                    });
                    $scope.$apply();
                });
            };
            $scope.list();
        }]);

        angular.element(document).ready(function() {
            angular.bootstrap(document.getElementById("houseFollowWrapper"),["followModule"])
        });
    });
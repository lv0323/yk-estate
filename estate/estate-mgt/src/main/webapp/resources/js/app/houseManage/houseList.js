/**
 * Created by robin on 17/2/17.
 */
require(['main-app',
        contextPath + '/js/service/identity-service.js',
        contextPath + '/js/service/validation-service.js',
        contextPath + '/js/service/department-service.js',
        contextPath + '/js/service/util-service.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        'jqPaginator', 'select', 'chosen', 'datetimepicker.zh-cn'],
    function (mainApp, IdentityService, ValidationService, DepartmentService, UtilService, pagingPlugin) {
        var pageConfig = {
            limit: 8,
            init: false
        };
        $('.selectpicker').selectpicker({
            style: 'btn-default',
            dropupAuto:false,
            size: 8,
        });
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
            $scope.filter ={
                area:{
                    type:'',
                    ceil:'',
                    floor:''
                },
                countf: '',
                character:{
                    unique:false,
                    fiveYears: false,
                    towYears: false
                },
                date:{
                    dateType: '',
                    startDate: '',
                    endDate: ''

                },
                depExp:{
                    lowerLevel: false
                },
                depId:'',
                distract: '',
                quality:'',
                sort:{
                    item:'default',
                    type:''

                },
                tradeStatus: '',
                tradeType:'',
                usage: '',
                xuanxiang: '',
                xuanxiangExp:{
                    entrustWay: '',
                    price: '',
                    grade: '',
                    decorate: '',
                    imageBool: '',
                    pType : '',
                    proveType: '',
                    settle: ''
                }
            };
            /*面积*/
            $scope.setAreaType = function(type){
                $scope.filter.area.type = type;
            }
            /*区域*/
            $scope.distractList =[
                {name: '不限', value: ''},
                {name: '浦东', value: '4440'},
                {name: '宝山', value: '4441'},
                {name: '普陀', value: '4442'},
                {name: '静安', value: '4443'},
                {name: '杨浦', value: '4444'},
                {name: '奉贤', value: '4445'},
                {name: '青浦', value: '4446'},
                {name: '闵行', value: '4447'},
                {name: '长宁', value: '4448'}
            ];
            $scope.setDistract = function(value){
                $scope.filter.distract = value;
            };
            /*用途*/
            $scope.usageList =[
                {name: '不限', value: ''},
                {name: '住宅', value: '9880'},
                {name: '商铺', value: '9881'},
                {name: '写字楼', value: '9882'},
                {name: '车位', value: '9883'},
                {name: '公寓', value: '9884'},
                {name: '其他', value: '9885'}
            ];
            $scope.setUsage = function(value){
                $scope.filter.usage = value;
                console.log(value);
            };
            /*户型*/
            $scope.countfList =[
                {name: '不限', value: ''},
                {name: '1室', value: '1'},
                {name: '2室', value: '2'},
                {name: '3室', value: '3'},
                {name: '4室', value: '4'},
                {name: '5室', value: '5'}
            ];
            $scope.setCountf = function(value){
                $scope.filter.countf = value;
                console.log(value);
            };
            /*状态*/
            $scope.tradeStatusList =[
                {name: '不限', value: ''},
                {name: '有效', value: '10223'},
                {name: '已售', value: '10224'},
                {name: '我租', value: '10225'},
                {name: '我售', value: '10226'},
                {name: '未知', value: '10227'}
            ];
            $scope.setTradeStatus = function(value){
                $scope.filter.tradeStatus = value;
                console.log(value);
            };

            $scope.qualityList =[
                {name :'不限', value:''},
                {name :'优质房', value:'10131'},
                {name :'普通房', value:'10132'},
                {name :'聚焦房', value: '10133'}
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
                {name :'含下级', value:'10142', key:'lowerLevel'},
            ];
            $scope.setDepExp = function(e){
                console.log($scope.filter.depExp);
            };
            $scope.dateTypeList =[
                {name :'--请选择--', value:''},
                {name :'录入日期', value:'1'},
                {name :'最后跟进日', value:'2'},
                {name :'勘察日期', value: '4'}
            ];

            $scope.setDateType = function(e){
                console.log($scope.filter.date);
            };
            $scope.setDate = function(key, value){
                $scope.filter.date[key] = value;
                console.log($scope.filter.date);
            };
            $scope.setXuanxiang = function(){
                console.log($scope.filter.xuanxiang);
            };
            $scope.fySortlist = [
                {name: '系统默认', value: 'default'},
                {name: '楼盘名称', value: '1'},
                {name: '栋座名称', value: '2'},
                {name: '房源楼层', value: '3'},
                {name: '房源总价', value: '4'},
                {name: '房源单价', value: '5'},
                {name: '建筑面积', value: '6'},
                {name: '最后跟进日期', value: '7'}
            ];
            $scope.setSort = function(item){
                if(item == 'default'){
                    if(item == $scope.filter.sort.item){
                        return;
                    }
                    $scope.filter.sort.item = item;
                    $scope.filter.sort.type = '';

                } else if(item == $scope.filter.sort.item){
                    $scope.filter.sort.type = $scope.filter.sort.type=='ASC'?'DESC':'ASC'
                }else{
                    $scope.filter.sort.item = item;
                    $scope.filter.sort.type = 'DESC'
                }
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
                console.log($scope.depList);
            });
            $scope.initDepList = function(){
                $('.chosen-select-dep').chosen();
            };
            /*$('.chosen-select-dep').chosen();*/
            /*员工*/
            $('.chosen-select-emp').chosen();
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
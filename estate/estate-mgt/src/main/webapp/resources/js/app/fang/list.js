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
        contextPath + '/js/app/fang/tools.js',
        contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        contextPath + '/js/directive/index.js',
        'jqPaginator', 'select', 'chosen', 'datetimepicker.zh-cn', 'sweetalert'],
    function (mainApp, IdentityService, ValidationService, CityService, DepartmentService, EmployeeService, FangService, UtilService, Tools, SweetAlertHelp, pagingPlugin) {
        var pageConfig = {
            limit: 8,
            offset: 0,
            dataTotal:0,
            currentPage:1,
            init: false,
        };

        var HouseListModule=angular.module('HouseListModule',['directiveYk']);
        HouseListModule.controller("HouseListCtrl", ['$scope','$timeout', '$q', '$interval', '$window', '$location', function($scope, $timeout, $q, $interval, $window) {
            var config = {
                departmentId: {
                    init: false
                },
                employeeId: {
                    init: false
                },
                searchById:false,
                xiaoQuId: {
                    init: false,
                    value: '',
                    timer: null
                },
                confirmText: {
                    'PUBLISH': '确认上架?',
                    'UN_PUBLISH': '确认下架?',
                    'PAUSE': '确认暂缓?',
                    'APPLY_PUBLISH': '申请发布?',
                    'REJECT_PUBLISH': '拒绝发布?',
                    'CONFIRM_PUBLISH': '确认发布?',
                    'UNDO_PUBLISH': '撤销发布?'
                }
            };
            /*页面相关内容*/
            $scope.page ={
                name: "房源列表",
                warn: {
                    title:'提示',
                    content:''
                },
                layoutString:'',
                collapse:false,
                now:new Date().getTime(),
                status: {
                    'DELEGATE': 'DELEGATE',
                    'PUBLISH': 'PUBLISH',
                    'UN_PUBLISH': 'UN_PUBLISH',
                    'PAUSE': 'PAUSE',
                    'APPLY_PUBLISH': 'APPLY_PUBLISH',
                    'REJECT_PUBLISH': 'REJECT_PUBLISH',
                    'CONFIRM_PUBLISH': 'CONFIRM_PUBLISH',
                    'UNDO_PUBLISH': 'UNDO_PUBLISH',
                    'SUCCESS': 'SUCCESS'
                },
                userInfo : JSON.parse(localStorage.getItem('userInfo')),
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
                subProcess: '',
                endDate: '',
                resident: '',
                decorate: '',
                propertyType: '',
                certifType: '',
                delegateType: '',
                xuanxiang: '',
                order:'DEFAULT',
                orderType:'',
                xiaoQuId:''
            };
            $scope.districtList =[];
            $scope.subDistrictList =[];
            $scope.employeeList =[{name:'',id:''}];
            $scope.estateList = [];
            function ceilCheck(ceilValue, value) {
                if (ceilValue && value && parseFloat(ceilValue) < parseFloat(value)) {
                    return false;
                }
                return true;
            }
            $scope.selectPickerChange = function(id, key, info) {
                if(info){
                    if(!$scope[info][key]){
                        $(id).siblings('.btn-default').addClass('invalid-input');
                        return false;
                    }else{
                        $(id).siblings('.btn-default').removeClass('invalid-input');
                        return true;
                    }
                }else{
                    if(!$scope.baseInfo[key]){
                        $(id).siblings('.btn-default').addClass('invalid-input');
                        return false;
                    }else{
                        $(id).siblings('.btn-default').removeClass('invalid-input');
                        return true;
                    }
                }

            };
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
            /*房源跟进*/
            $scope.newFollowInit = function(fangId){
                $scope.newFollow = {fangId:fangId};
                $('#followModel').modal({'show':true,backdrop:'static'});
                setTimeout(function(){
                    $('#houseNewFollow').selectpicker('refresh');
                },200)
            };
            $scope.followCreate = function(){
                var info ={};
                angular.copy($scope.newFollow, info);
                FangService.createFollow(info).then(function(response){
                    $('#followModel').modal('hide');
                    SweetAlertHelp.success();
                }).fail(SweetAlertHelp.fail);
            };
            /*end 房源跟进*/
            /*获得小区信息*/
            $scope.estateGet = function(key){
                if(!key){
                    key = 'a';
                }
                FangService.xiaoquOption({keyword: key}).done(function (response) {
                    $scope.$apply(function(){
                        $scope.estateList = response.map(function(item){
                            return {
                                name: item.xiaoQuName,
                                value: item.xiaoQuId
                            }
                        });
                    });
                }).fail(function () {
                    $scope.$apply(function() {
                        $scope.estateList = [{name:'',id:''}];
                    });
                });
            };
            $scope.estateGet();

            /*小区楼盘*/
            $scope.stateInputChange = function(e){
                $timeout.cancel(config.xiaoQuId.timer);
                config.xiaoQuId.timer = $timeout(function(){
                    config.xiaoQuId.value = $(e.target).val().trim();
                    if(config.xiaoQuId.value === ""){
                        return;
                    }
                    $scope.estateGet(config.xiaoQuId.value);
                },800);
            };

            $scope.chosenEstate = function(id,key){

                if(!config.xiaoQuId.init){
                    $scope.initChosen(id, key);
                }else{
                    $(id).trigger('chosen:updated');
                    if( $('#houseEstate_chosen .search-field-input').val().trim() == ''){
                        $('#houseEstate_chosen .search-field-input').val(config.xiaoQuId.value);
                    }
                }
                $('#estateContainer').off('input','.search-field-input', $scope.stateInputChange).on('input','.search-field-input', $scope.stateInputChange);
            };

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
                        if(response && response.length>0){
                            $scope.employeeList = response.map(function(item){
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
                if(key === 'process'){
                    $scope.filter['subProcess'] = '';
                }
                $scope.filter[key]=value;
                $scope.list();
            };
            $scope.setSubProcess = function(value){
                $scope.filter['process'] ='PUBLISH';
                $scope.setFilterType('subProcess' ,value);
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
                if(!ceilCheck($scope.filter.maxArea, $scope.filter.minArea)){
                    SweetAlertHelp.fail({message:'最小面积不能大于最大面积'});
                    $scope.filter[key] = '';
                    return;
                }
                $scope.filter.areaType = 'custom'
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

            /*通过授权编号查找*/
            $scope.searchById = function(){
                config.searchById = true;
                if(!$scope.search.id){
                    $scope.list();
                    return;
                }
                FangService.summaryByLicenceId({'licenceId':$scope.search.id}).then(function(response){
                    $scope.houseList =[];
                    $scope.$apply(function() {
                        $scope.houseList.push(response);
                        pagination(1);
                    });
                }).fail(function(response){
                    SweetAlertHelp.fail({message:response.message});
                })
            };
            /*筛选*/
            $scope.triggerCollapse = function(){
                $scope.page.collapse = !$scope.page.collapse;
            };
            /*房源列表*/
            $scope.list = function(offset, currentpage){
                config.searchById = false;
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
                FangService.list(param,{'X-PAGING':'total=true&offset='+(offset||pageConfig.offset)+'&limit='+ pageConfig.limit}).then(function(response){
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
            $scope.confirmChangeStatus = function(status, id){
                var deferred = $q.defer();
                var operation = null;
                var params = {};
                switch(status) {
                    case $scope.page.status.PUBLISH:
                        operation = FangService.publish;
                        params = {'fangId':id, 'applyReason':'todo add reason here'};
                        break;
                    case $scope.page.status.UN_PUBLISH:
                        operation = FangService.unPublish;
                        params = {'fangId':id, 'applyReason':'todo add reason here'};
                        break;
                    case $scope.page.status.PAUSE:
                        operation =  FangService.pause;
                        params = {'fangId':id, 'applyReason':'todo add reason here'};
                        break;
                    case $scope.page.status.APPLY_PUBLISH:
                        operation =  FangService.applyPublic;
                        params = {'fangId':id, 'applyReason':'todo add reason here'};
                        break;
                    case $scope.page.status.UNDO_PUBLISH:
                        operation =  FangService.undoPublic;
                        params = {'fangId':id, 'applyReason':'todo add reason here'};
                        break;
                    case $scope.page.status.REJECT_PUBLISH:
                        operation =  FangService.rejectPublic;
                        params = {'fangId':id, 'comments':'todo add comments here'};
                        break;
                    case $scope.page.status.CONFIRM_PUBLISH:
                        operation =  FangService.confirmPublic;
                        params = {'fangId':id, 'comments':'todo add comments here'};
                        break;
                }
                if(operation){
                    operation(params).then(function(response){
                        deferred.resolve(response);
                    }).fail(function(response){
                        deferred.reject(response);
                    });
                }else{
                    deferred.reject({message:'未知操作'});
                }

                return deferred.promise;
            };
            $scope.changeStatus = function(status, id){
                var option = {};
                option.title = config.confirmText[status];
                SweetAlertHelp.confirm(option, function () {
                    $scope.confirmChangeStatus(status, id).then(function(){
                        SweetAlertHelp.success();
                        if(config.searchById){
                            $scope.searchById();
                        }else{
                            $scope.list((pageConfig.currentPage - 1) * pageConfig.limit, pageConfig.currentPage);
                        }
                    }).catch(function(response){
                        SweetAlertHelp.fail({message:response&&response.message});
                    })
                });
            }
        }]);

        angular.element(document).ready(function() {
            angular.bootstrap(document.getElementById("houseListWrapper"),["HouseListModule"])
        });
    });
require(['main-app',contextPath + '/js/service/fang-service.js',
        contextPath + '/js/service/xiaoqu-service.js',
        contextPath + '/js/service/city-service.js',
        contextPath + '/js/service/department-service.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        contextPath+'/js/utils/dataTableHelp.js',
        contextPath+'/js/app/org/department/departCommon.js',
        contextPath+'/js/app/org/position/positionCommon.js',
        contextPath+'/js/service/util-service.js',
        contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js',
        contextPath + '/js/directive/index.js',
    'datatables', 'select', 'zTree','datatablesBootstrap', 'datetimepicker.zh-cn', 'chosen'],
    function (mainApp, FangService, XiaoquService, CityService, DepartmentService, pagingPlugin, dataTableHelp, DepartCommon, PositionCommon, UtilService, SweetAlertHelp) {
        var pageConfig = {
            limit: 8,
            offset: 0,
            dataTotal:0,
            currentPage:1,
            init: false,
        };
        var domainTypeConfig ={
            'CITY': 'CITY',
            'DISTRICT': "DISTRICT",
            'SUB_DISTRICT': "SUB_DISTRICT"
        };
        var searchConfig = {
            xiaoQuId: {
                init: false,
                value: '',
                timer: null
            }
        };
        /*小区列表*/
        var module = angular.module('HouseDictModule', ['directiveYk']);
        function DictCtrl($scope, $timeout){
            var _this = this;
            _this.config ={
                cityList: [],
                currentCity:null,
            };
            _this.filterData ={
                districtId: null,
                subDistrictId: null,
                xiaoQuId :null
            };
            _this.estateList = [];

            /*chosen*/
            _this.estateGet = function(key){
                if(!key){
                    key = 'a';
                }
                FangService.xiaoquOption({keyword: key}).done(function (response) {
                    $scope.$apply(function(){
                        _this.estateList = response.map(function(item){
                            return {
                                name: item.xiaoQuName,
                                value: item.xiaoQuId
                            }
                        });
                    });
                }).fail(function () {
                    $scope.$apply(function() {
                        _this.estateList = [{name:'',id:''}];
                    });
                });
            };
            _this.estateGet();
            _this.initChosen = function(id, key){
                $(id).chosen("destroy");
                if(!searchConfig[key].init){
                    searchConfig[key].init = !searchConfig[key].init;
                    $(id).chosen().change(function(e, result){
                        $scope.$apply(function(){
                            _this.filterData = {xiaoQuId: result.selected}
                            _this.list();
                        });
                    });
                    return;
                }
                $(id).chosen();
                $(id).trigger('chosen:updated');
            };
            _this.chosenEstate = function(id,key){

                if(!searchConfig.xiaoQuId.init){
                    _this.initChosen(id, key);
                }else{
                    $(id).trigger('chosen:updated');
                    if( $('#houseEstate_chosen .search-field-input').val().trim() == ''){
                        $('#houseEstate_chosen .search-field-input').val(searchConfig.xiaoQuId.value);
                    }
                }
                $('#searchList').off('input','.search-field-input', _this.stateInputChange).on('input','.search-field-input', _this.stateInputChange);
            };
            _this.stateInputChange = function(e){
                $timeout.cancel(searchConfig.xiaoQuId.timer);
                searchConfig.xiaoQuId.timer = $timeout(function(){
                    searchConfig.xiaoQuId.value = $(e.target).val().trim();
                    if(searchConfig.xiaoQuId.value === ""){
                        return;
                    }
                    _this.estateGet(searchConfig.xiaoQuId.value);
                },800);
            };
            /*end chosen*/

            _this.list = function(offset, currentPage) {
                var params = _this.filterData;
                params.cityId = _this.config.cityList[_this.config.currentCity].id;
                if(!currentPage){
                    pageConfig.currentPage = 1;
                }
                XiaoquService.list(params,{'X-PAGING':'total=true&offset='+(offset||pageConfig.offset)+'&limit='+ pageConfig.limit}).then(function(response){
                    _this.xiaoquList =[];
                    $scope.$apply(function(){
                        pagination(response.total);
                        _this.xiaoquList = response.items;
                    });
                });
            };
            /* 树*/
            function dictionaryTree(data, idKey, pIdKey){
                function beforeClick(treeId, treeNode, clickFlag) {
                    if(treeNode.domainType === domainTypeConfig.CITY){
                        return false
                    }
                }
                function onClick(event, treeId, treeNode, clickFlag) {
                    if(treeNode.domainType === domainTypeConfig.DISTRICT){
                        _this.filterData.districtId = treeNode.id;
                        _this.filterData.subDistrictId = null;
                        _this.filterData.xiaoQuId = null;
                        _this.list();
                    }else if(treeNode.domainType === domainTypeConfig.SUB_DISTRICT){
                        _this.filterData.districtId = treeNode.districtId;
                        _this.filterData.subDistrictId = treeNode.id;
                        _this.filterData.xiaoQuId = null;
                        _this.list();
                    }
                };
                var zTreeSetting = {
                    view: {
                        showIcon: false
                    },
                    data: {
                        simpleData: {
                            enable: true
                        }
                    },
                    callback: {
                        beforeClick: beforeClick,
                        onClick: onClick
                    }
                };
                idKey && (zTreeSetting.data.simpleData.idKey = idKey);
                pIdKey && (zTreeSetting.data.simpleData.pIdKey = pIdKey);
                var zTreeObj = $.fn.zTree.init($("#dictionaryTree"), zTreeSetting, data);
                /*zTreeObj.expandAll(true);*/
            }
            _this.getDistrictsWithSubs = function(){
                CityService.districtsWithSubs({cityId: _this.config.cityList[_this.config.currentCity].id}).then(function(response){
                var districtList = []
                response.map(function(item, index, array){
                    var district = {
                        id : item.id,
                        domainType: item.domainType,
                        name: item.name,
                        treeId : item.domainType + item.id,
                        parentId : domainTypeConfig.CITY + item.cityId
                    };
                    districtList.push(district);
                    item.subs && item.subs.map(function(subItem, index, array){
                        var subDistrict ={
                            id : subItem.id,
                            domainType: subItem.domainType,
                            districtId : district.id,
                            treeId : subItem.domainType + subItem.id,
                            name: subItem.name,
                            parentId : district.treeId
                        };
                        districtList.push(subDistrict);
                    })
                });
                districtList.unshift({
                    id : 1,
                    treeId : domainTypeConfig.CITY + 1,
                    name: '北京',
                    parentId : null,
                    domainType: domainTypeConfig.CITY
                });
                dictionaryTree(districtList, 'treeId', 'parentId');
            });
            };
            /* end树*/
            /*分页*/
            var pagination = function(dataTotal) {
                var id = '#xiaoquList_paging';
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
                        _this.list((num-1)*pageConfig.limit, num);
                    }
                };
                pagingPlugin.init(config);

            };
            /*end 分页*/

            /*获得cityID*/
            (function(){
                CityService.getCity().then(function(response){
                    $scope.$apply(function(){
                        _this.config.cityList = response;
                        _this.config.currentCity = 0;
                    });
                    _this.list();
                    _this.getDistrictsWithSubs();
                });
            })();
            /*end 获得cityID*/
        }
        DictCtrl.$inject =['$scope', '$timeout'];
        module.controller("DictCtrl", DictCtrl);

        angular.element(document).ready(function(){
           angular.bootstrap(document.getElementById("houseDictionary"), ["HouseDictModule"])
        });

    });
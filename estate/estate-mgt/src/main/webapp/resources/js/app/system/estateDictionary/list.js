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
        function DictCtrl($scope, $timeout, $window){
            var _this = this;
            _this.config ={
                cityList: [],
                currentCity:null,
                inum:null,
            };
            _this.filterData ={
                districtId: null,
                subDistrictId: null,
                xiaoQuId :null,
                treeId:null
            };
            _this.data = {};
            _this.data.create = {
                communityName:'',
                communityAlias:'',
                communityCity:'1',
                communitySubDistrict:'',
                communityDistrict:''
            };
            _this.showCreateNewXiaoquDialog = function(){
                $scope.createXiaoquForm.$setPristine();

                $('#createXiaoqu').modal({'show':true,backdrop:'static'});
            };

            _this.createXiaoqu = function() {
                if($scope.createXiaoquForm.$invalid||!$scope.createXiaoquForm.$dirty){
                    if (!_this.data.addNewXiaoquForm.communityName) {
                        _this.commonInputWarn('communityName','createXiaoquForm');
                    }
                    return;
                }
                var params = angular.copy(_this.data.create);
                ms.name = _this.data.create.communityName;para
                params.alias = _this.data.create.communityAlias;
                params.cityId = _this.data.create.communityCity;
                params.subDistrictId = _this.data.create.communitySubDistrict;

                XiaoquService.createXiaoqu(params).then(function(response){
                    $('#createXiaoqu').modal('hide');

                    SweetAlertHelp.success({}, function() {
                        $window.location.href='/mgt/system/estateDictionary/detail?id='+ response.id;
                    });

                }).fail(function(response){
                    SweetAlertHelp.fail({message:response&&response.message});
                });
            };

            _this.estateList = [];

            /*chosen*/
            _this.estateGet = function(key){
                if(!key){
                    key = 'a';
                }
                FangService.xiaoquOption({keyword: key}).done(function (response) {
                    //console.log(response)
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
                    console.log(searchConfig.xiaoQuId.value);
                },800);
            };
            /*end chosen*/

            _this.list = function(offset,currentPage) {
                var params = _this.filterData;
                params.cityId=_this.config.currentCity;
                if(!currentPage){
                    pageConfig.currentPage = 1;
                }
                XiaoquService.list(params,{'X-PAGING':'total=true&offset='+(offset||pageConfig.offset)+'&limit='+ pageConfig.limit}).then(function(response){
                    _this.xiaoquList =[];
                    $scope.$apply(function(){
                        pagination(response.total);
                        _this.xiaoquList = response.items;
                        //console.log(_this.xiaoquList)
                    });
                }).fail(function(res){
                    SweetAlertHelp.fail({message: res && res.message});
                });
            };
            /* 树*/
            function dictionaryTree(data, idKey, pIdKey){
                function beforeClick(treeId, treeNode, clickFlag) {
                    if(treeNode.domainType === domainTypeConfig.CITY){
                        // return false
                        _this.config.currentCity=treeNode.id;
                        console.log(_this.config.currentCity)
                        _this.filterData.treeId=treeNode.id
                        _this.list()
                    }
                }
                function onClick(event, treeId, treeNode, clickFlag) {
                    console.log(treeId,treeNode)
                    if(treeNode.domainType === domainTypeConfig.CITY){
                        // return false
                        _this.config.currentCity=treeNode.id;
                        console.log(_this.config.currentCity)
                        _this.filterData.treeId=treeNode.id
                        _this.list()
                    }
                    else if(treeNode.domainType === domainTypeConfig.DISTRICT){
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
                var zTreeObj = $.fn.zTree.init($('#dictionaryTree1'), zTreeSetting,data);
                //data是指 地区 区域 板块  的信息
                // zTreeObj.expandAll(true)进行一个伸展;
            }

            _this.districtWithSubs = {};
            _this.districtToSubsMap = {};
            _this.subDistricts = {};

            _this.districtChanged = function() {
                _this.subDistricts = _this.districtToSubsMap[_this.data.create.communityDistrict];
            };

            $scope.$watch('districtSelect', function(newVal) {
                _this.subDistricts = _this.districtToSubsMap[_this.data.create.communityDistrict];
            });

            var districtList = [];
            _this.getDistrictsWithSubs = function(i){
                CityService.districtsWithSubs({cityId:i}).then(function(response){
                    _this.districtWithSubs = response;
                    response.map(function(item, index, array){
                        var district = {
                            id : item.id,
                            domainType: item.domainType,
                            name: item.name,
                            treeId : item.domainType + item.id,
                            parentId : domainTypeConfig.CITY + item.cityId
                        };
                        districtList.push(district);
                        _this.districtToSubsMap[district.id] = item.subs;

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

                    districtList.push({
                        id:_this.config.currentCity,
                        treeId : domainTypeConfig.CITY + _this.config.currentCity,
                        name: "楼盘库",
                        parentId : null,
                        domainType: domainTypeConfig.CITY
                    });
                    dictionaryTree(districtList, 'treeId', 'parentId')
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
            CityService.getDistrict().then(function(response){
                //console.log(response)
                $scope.$apply(function(){
                    _this.config.currentCity=response[0].cityId;
                    //console.log(_this.config.currentCity)
                });
                _this.list();
                _this.getDistrictsWithSubs(_this.config.currentCity);
            });

            /*end 获得cityID*/
        }
        DictCtrl.$inject =['$scope', '$timeout','$window'];
        module.controller("DictCtrl", DictCtrl);

        angular.element(document).ready(function(){
            angular.bootstrap(document.getElementById("houseDictionary"), ["HouseDictModule"])
        });

    })
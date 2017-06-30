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
    'datatables', 'select', 'zTree','datatablesBootstrap', 'fancyboxThumbs','datetimepicker.zh-cn', 'chosen', 'pagescroller', 'jssorSlider', 'angularMap'],
    function (mainApp, FangService, XiaoquService, CityService, DepartmentService, pagingPlugin, dataTableHelp, DepartCommon, PositionCommon, UtilService, SweetAlertHelp) {
        var pageConfig = {
            limit: 8,
            offset: 0,
            dataTotal:0,
            currentPage:1,
            init: false,
        };
        var xiaoquId = UtilService.getUrlParam('id');
        /*小区列表*/
        var module = angular.module('DictDetailModule', ['directiveYk', 'angular-baidu-map']);
        function DetailCtrl($scope, $timeout){
            var _this = this;
            _this.data ={
                baseInfo: {},
                detail:{},
                buildings :[],
                showBuilding:{},
                modifyBuilding:{},
                modifyCommunityPart_1:{},
                modifyCommunityPart_2:{},
                config:{
                    showImage: false
                }
            };
            _this.virtualMap ={
                list:[],
                count:0,
            };
            _this.page = {};
            _this.showMap = {};
            _this.baiduMap = {};
            _this.mapConfig = {
                baiduMap : null,
                mapPoint : null,
                mapSearchStr: ''
            };
            _this.commonInputWarn = function(name, formName){
                $scope[formName||'buildingForm'][name].$setDirty();
                $scope[formName||'buildingForm'][name].$setValidity('required', false);
            };
            /*获取图片*/
            _this.imageGet = function(type){
                return XiaoquService.image({
                    fangId:xiaoquId,
                    customType:type
                });
            };
            function uploadMap(type){
                var formData = new FormData();
                for(var i =0; i< $("#file_upload")[0].files.length; i++){
                    formData.append("images", $("#file_upload")[0].files[i]);
                }
                formData.append("xiaoQuId", xiaoquId);
                formData.append("customType", type);
                $scope.$apply(function(){
                    _this.page.uploading = true;
                });
                XiaoquService.imageUpload(formData).then(function(response){
                    if(response.result === 'FAILED'){
                        SweetAlertHelp.fail(response.ext);
                        return;
                    }
                    SweetAlertHelp.success(response.ext);
                }).fail(function (response) {
                    SweetAlertHelp.fail(response.responseJSON)
                }).always(function(){
                    $scope.$apply(function() {
                        _this.page.uploading = false;
                    });
                    _this.imageGet(_this.showMap.para).then(function(response) {
                        $scope.$apply(function () {
                            _this[_this.showMap.key].list = response;
                            _this[_this.showMap.key].count = response.length;
                            _this.showMap.list = response;
                        });
                    });
                });
            }
            _this.addMap = function(type, title, key){
                $('#mapModel').modal({'show':true,backdrop:'static'});
                $('#dictionaryDetail').off('change','#file_upload');
                var $el = $('#file_upload');
                $el.wrap('<form>').closest('form').get(0).reset();
                $el.unwrap();
                $('#dictionaryDetail').on('change','#file_upload',function(){
                    uploadMap(type);
                });
                _this.page.uploadTitle = title;
                _this.showMap.list= _this[key].list;
                _this.showMap.key =key;
                _this.showMap.para =type;
            };
            _this.showAllMap = function(type, title, key){
                var list = _this[key].list.map(function(item){
                    return{
                        href:item.fileURI,
                        title:''
                    }
                });
                $.fancybox.open(list, {
                    helpers : {
                        thumbs : {
                            width: 75,
                            height: 50
                        }
                    }
                });
            };
            _this.deleteImage = function(fileId){
                XiaoquService.imageDelete({fileId:fileId}).then(function(){
                    SweetAlertHelp.success();
                    _this.imageGet(_this.showMap.para).then(function(response){
                        $scope.$apply(function(){
                            _this[_this.showMap.key].list = response;
                            _this[_this.showMap.key].count = response.length;
                            _this.showMap.list = response;
                        });
                    }).fail(function(res){
                        SweetAlertHelp.fail({message: res && res.message});
                    });
                });
            };
            _this.setFirstImage = function(fileId){
                XiaoquService.setFirstImage({fileId:fileId}).then(function(){
                    SweetAlertHelp.success();
                    _this.imageGet(_this.showMap.para).then(function(response){
                        $scope.$apply(function(){
                            _this[_this.showMap.key].list = response;
                            _this[_this.showMap.key].count = response.length;
                            _this.showMap.list = response;
                        });

                    });
                });
            };
            /*end 图片*/
            _this.getBaseInfo = function(){
                XiaoquService.getXiaoquInfo({id :xiaoquId}).then(function(response){
                    $scope.$apply(function(){
                        _this.data.baseInfo = response;
                    });
                });
            };
            /*获取详细信息*/
            var loadDetail = function getDetail(){
                XiaoquService.getXiaoquDetail({id :xiaoquId}).then(function(response){
                    $scope.$apply(function(){
                        _this.data.detail = response;
                    });
                    _this.imageGet('SHI_JING').then(function(response){
                        $scope.$apply(function(){
                            _this.virtualMap.list = response;
                            _this.virtualMap.count = response.length;
                            _this.data.config.showImage = true;
                        });
                    });
                    /*如果地图指令先执行*/
                    if(_this.mapConfig.baiduMap){
                        _this.mapConfig.mapPoint = new BMap.Point(_this.data.detail.longitude, _this.data.detail.latitude);
                        _this.mapConfig.baiduMap.centerAndZoom(_this.mapConfig.mapPoint, 15);
                        var marker = new BMap.Marker(_this.mapConfig.mapPoint);
                        _this.mapConfig.baiduMap.addOverlay(marker);
                    }
                });
            };

            loadDetail();

            _this.getBuildings = function(){
                FangService.buildings({xiaoQuId:xiaoquId}).then(function(response){
                    $scope.$apply(function(){
                        _this.data.buildings = response;
                    });
                });
            };
            _this.getBuildings();
            /*百度地图*/
            _this.mapReady = function(map){
                _this.mapConfig.baiduMap = map;
                _this.mapConfig.baiduMap.enableScrollWheelZoom();
                _this.mapConfig.baiduMap.addControl(new BMap.NavigationControl());
                _this.mapConfig.baiduMap.addControl(new BMap.ScaleControl());
                _this.mapConfig.baiduMap.addControl(new BMap.OverviewMapControl());
                _this.mapConfig.baiduMap.addControl(new BMap.MapTypeControl());
                if(_this.data.detail && _this.data.detail.latitude){
                     _this.mapConfig.mapPoint = new BMap.Point(_this.data.detail.longitude, _this.data.detail.latitude);
                    _this.mapConfig.baiduMap.centerAndZoom(_this.mapConfig.mapPoint, 15);
                    var marker = new BMap.Marker(_this.mapConfig.mapPoint);
                    _this.mapConfig.baiduMap.addOverlay(marker);

                }
                _this.searchMap('公交');
            };
            _this.searchMap =function(str){
                if(_this.mapConfig.mapSearchStr === str){
                    return;
                }
                _this.mapConfig.mapSearchStr = str;
                var local = new BMap.LocalSearch(_this.mapConfig.baiduMap, {
                    renderOptions: {map: _this.mapConfig.baiduMap, panel: "r-result"}
                });
                _this.mapConfig.baiduMap.clearOverlays();
                var circle = new BMap.Circle( _this.mapConfig.mapPoint, 1000, {fillColor:"blue", strokeWeight: 1 ,fillOpacity: 0.3, strokeOpacity: 0.3});
                _this.mapConfig.baiduMap.addOverlay(circle);
                local.searchNearby(str,  _this.mapConfig.mapPoint, 1000);
            };
            /*end 百度地图*/
            /*查看栋座信息*/
            _this.buildingItem = function(item){
                _this.data.showBuilding = item;
                if(item.units){
                    _this.data.showBuilding.unitsName = item.units.map(function(item){
                        return item.unitName
                    }).join('、');
                }
                $('#showBuilding').modal({'show':true,backdrop:'static'});
            };
            /*end 查看栋座信息*/

            /*编辑小区信息*/
            _this.showCommunityModifyPart_1 = function(){
                $scope.communityModifyFormPart_1.$setPristine();

                _this.data.modifyCommunityPart_1 = {
                    name: _this.data.detail.name,
                    alias: _this.data.detail.alias,
                    structureStr: _this.data.detail.structureStr,
                    propertyFee: _this.data.detail.propertyFee,
                    buildedYear : _this.data.detail.buildedYear,
                    address : _this.data.detail.address,
                    buildings : _this.data.detail.buildings,
                    houses:_this.data.detail.houses
                }

                $('#communityModifyPart_1').modal({'show':true,backdrop:'static'});
            };

            _this.communityUpdatePart_1 = function(){
                if($scope.communityModifyFormPart_1.$invalid||!$scope.communityModifyFormPart_1.$dirty){
                    if (!_this.data.modifyCommunityPart_1.name) {
                        _this.commonInputWarn('communityName','communityModifyFormPart_1');
                    }
                    return;
                }
                var params = angular.copy(_this.data.modifyCommunityPart_1);
                params.id = xiaoquId;
                delete params.address;

                XiaoquService.updateCommunity(params).then(function(response){
                    loadDetail();
                    SweetAlertHelp.success();
                    $('#communityModifyPart_1').modal('hide');
                }).fail(function(response){
                    SweetAlertHelp.fail({message:response&&response.message});
                });
            };

            _this.showCommunityModifyPart_2 = function(){
                $scope.communityModifyFormPart_2.$setPristine();

                _this.data.modifyCommunityPart_2 = {
                    developers: _this.data.detail.developers,
                    developYear: _this.data.detail.developYear,
                    parkingSpace: _this.data.detail.parkingSpace,
                    parkingRate: _this.data.detail.parkingRate,
                    greenRate : _this.data.detail.greenRate,
                    propertyCompany : _this.data.detail.propertyCompany,
                    propertyCompanyPhone : _this.data.detail.propertyCompanyPhone,
                    parkingFee:_this.data.detail.parkingFee,
                    containerRate:_this.data.detail.containerRate
                }

                $('#communityModifyPart_2').modal({'show':true,backdrop:'static'});
            };

            _this.communityUpdatePart_2 = function(){
                if($scope.communityModifyFormPart_2.$invalid||!$scope.communityModifyFormPart_2.$dirty){
                    return;
                }
                var params = angular.copy(_this.data.modifyCommunityPart_2);
                params.id = xiaoquId;

                XiaoquService.updateCommunity(params).then(function(response){
                    loadDetail();
                    SweetAlertHelp.success();
                    $('#communityModifyPart_2').modal('hide');
                }).fail(function(response){
                    SweetAlertHelp.fail({message:response&&response.message});
                });
            };


            /*编辑栋座信息*/
            _this.buildingModify = function(item){
                $scope.buildingForm.$setPristine();
                if(!item){
                    _this.data.modifyBuilding = {
                        name: '',
                        floors: '',
                        firstUnit: '',
                        unitList: [],
                        houses : '',
                        stairs : '',
                        description : ''
                    }
                }else{
                    _this.data.modifyBuilding = angular.copy(item);
                    delete _this.data.modifyBuilding.communityId;
                    delete _this.data.modifyBuilding.companyId;
                    delete _this.data.modifyBuilding.createById;
                    delete _this.data.modifyBuilding.createTime;
                    delete _this.data.modifyBuilding.deleted;
                    delete _this.data.modifyBuilding.updateById;
                    delete _this.data.modifyBuilding.updateTime;
                    delete _this.data.modifyBuilding.version;
                    _this.data.modifyBuilding.unitList = _this.data.modifyBuilding.units.map(function(item){
                        return item.unitName
                    });
                    _this.data.modifyBuilding.firstUnit = _this.data.modifyBuilding.unitList.shift();
                }
                $('#buildingModify').modal({'show':true,backdrop:'static'});
            };
            /* 添加单元*/
            _this.unitAdd = function(){
                _this.data.modifyBuilding.unitList.unshift('');
            };
            /*删除单元*/
            _this.unitRemove = function(item, index){
                $scope.buildingForm.$setDirty();
                _this.data.modifyBuilding.unitList.splice(index, 1);
            };
            /* 新增和修改building*/
            _this.buildingUpdate = function(){
                if($scope.buildingForm.$invalid||!$scope.buildingForm.$dirty){
                    if (!_this.data.modifyBuilding.name) {
                        _this.commonInputWarn('buildingName','buildingForm');
                    }
                    if (!_this.data.modifyBuilding.floors) {
                        _this.commonInputWarn('buildingFloors','buildingForm');
                    }
                    if (!_this.data.modifyBuilding.stairs) {
                        _this.commonInputWarn('buildingStairs','buildingForm');
                    }
                    if (!_this.data.modifyBuilding.houses) {
                        _this.commonInputWarn('buildingHouses','buildingForm');
                    }
                    if (!_this.data.modifyBuilding.firstUnit) {
                        _this.commonInputWarn('firstUnit','buildingForm');
                    }
                    return;
                }
                _this.data.modifyBuilding.unitNames = [];
                _this.data.modifyBuilding.unitNames.push(_this.data.modifyBuilding.firstUnit);
                Array.prototype.push.apply(_this.data.modifyBuilding.unitNames, _this.data.modifyBuilding.unitList)
                var params = angular.copy(_this.data.modifyBuilding);
                delete params.unitList;
                if(_this.data.modifyBuilding.id){
                    /*update*/
                    params.buildingId = params.id;
                    delete params.id;
                    delete params.units;
                    delete params.firstUnit;
                    FangService.buildingUpdate(params).then(function(response){
                        SweetAlertHelp.success();
                        $('#buildingModify').modal('hide');
                        _this.getBuildings();
                    }).fail(function(response){
                        SweetAlertHelp.fail({message:response&&response.message});
                    });
                }else{
                    /*create*/
                    params.xiaoQuId = xiaoquId;
                    FangService.buildingPost(params).then(function(response){
                        SweetAlertHelp.success();
                        $('#buildingModify').modal('hide');
                        _this.getBuildings();
                    }).fail(function(response){
                        SweetAlertHelp.fail({message:response&&response.message});
                    })
                }
            };
            /*end编辑栋座信息*/

            /*删除栋座*/
            _this.buildingDelete = function(){
                if(!_this.data.modifyBuilding.id){
                    return;
                }
                SweetAlertHelp.confirm({title:'确认删除栋座?'},function(){
                    $('#buildingModify').modal('hide');
                    FangService.buildingDelete({buildingId:_this.data.modifyBuilding.id}).then(function(response) {
                        SweetAlertHelp.success();
                        _this.getBuildings();
                    }).fail(function(response){
                        SweetAlertHelp.fail({message:response&&response.message})
                    });
                });

            };
            /*end删除栋座*/
        };
        DetailCtrl.$inject =['$scope', '$timeout'];
        module.controller("DetailCtrl", DetailCtrl);
        var navLabel = new Array('基本信息', '栋座字典', "周边配套", '开发商/物业');

        $('#main-container').pageScroller({ navigation: navLabel });
        angular.element(document).ready(function(){
           angular.bootstrap(document.getElementById("dictionaryDetail"), ["DictDetailModule"])
        });

    });
/**
 * Created by robin on 17/2/17.
 */
require(['main-app',
        contextPath + '/js/service/identity-service.js',
        contextPath + '/js/service/fang-service.js',
        contextPath + '/js/service/validation-service.js',
        contextPath + '/js/service/xiaoqu-service.js',
        contextPath + '/js/service/util-service.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        contextPath + '/js/directive/index.js',
        'jqPaginator', 'select', 'chosen', 'datepicker.zh-cn', 'datetimepicker.zh-cn'],
    function (mainApp,  IdentityService, FangService, ValidationService, XiaoquService, UtilService, pagingPlugin) {

        var AddHouseModule = angular.module('AddHouseModule', ['directiveYk']);

        function AddHouseCtrl($scope, $timeout, $interval, $window) {
            var _this = this;
            var config = {
                xiaoQuId: {
                    init: false,
                    value: '',
                    timer: null
                },
                buildingId: {
                    init: false
                },
                buildingUnitId: {
                    init: false
                },
            };

            function ceilCheck(ceilValue, value) {
                if (ceilValue && value && parseFloat(ceilValue) < parseFloat(value)) {
                    return false;
                }
                return true;
            }
            _this.selectPickerChange = function(id, key) {
                if(!_this.data[key]){
                    $(id).siblings('.btn-default').addClass('invalid-input');
                    return false;
                }
                $(id).siblings('.btn-default').removeClass('invalid-input');
                return true;
            };
            _this.commonInputWarn = function(name, formName){
                $scope[formName||'houseForm'][name].$setDirty();
                $scope[formName||'houseForm'][name].$setValidity('required', false);
            };
            _this.stepConfig = {
                step1: 1,
                step2: 2
            };
            _this.bizTypeConfig = {
                rent: 'RENT',
                sell: 'SELL'
            };
            /*页面相关内容*/
            _this.page ={
                name: "新增房源",
                currentStep: _this.stepConfig.step1,
                warn: {
                    title:'提示',
                    content:'请输入完整信息'
                },
                layoutString:''
            };
            /*户型选择*/
            _this.layoutList={
                shi:[{value:1,name:'1'}, {value:2,name:'2'}, {value:3,name:'3'}, {value:4,name:'4'}, {value:5,name:'5'}, {value:6,name:'6'},{value:7,name:'7'},{value:0,name:'无'}],
                ting:[{value:1,name:'1'}, {value:2,name:'2'}, {value:3,name:'3'}, {value:4,name:'4'}, {value:5,name:'5'}, {value:6,name:'6'},{value:7,name:'7'},{value:0,name:'无'}],
                chu:[{value:1,name:'1'}, {value:2,name:'2'}, {value:3,name:'3'}, {value:4,name:'4'}, {value:5,name:'5'}, {value:6,name:'6'},{value:7,name:'7'},{value:0,name:'无'}],
                wei:[{value:1,name:'1'}, {value:2,name:'2'}, {value:3,name:'3'}, {value:4,name:'4'}, {value:5,name:'5'}, {value:6,name:'6'},{value:7,name:'7'},{value:0,name:'无'}],
                yangtai:[{value:1,name:'1'}, {value:2,name:'2'}, {value:3,name:'3'}, {value:4,name:'4'}, {value:5,name:'5'}, {value:6,name:'6'},{value:7,name:'7'},{value:0,name:'无'}]
            };
            /*新建栋座*/
            _this.newBuilding = {
                xiaoQuId:'',
                name:'',
                floors:'',
                stairs:'',
                houses:'',
                description:'',
                xiaoqu: {
                    id: '',
                    name: '',
                    address: ''
                }
            };
            /*新建单元*/
            _this.newUnit = {
                buildingId:'',
                buildingName:'',
                unitNames: []
            };
            /*户型*/
            _this.currentLayout ={
                sCounts: '',
                tCounts: '',
                cCounts: '',
                wCounts: '',
                ytCounts: ''
            };
            _this.data ={
                bizType: 'RENT',
                bottomPrice:'',
                buildingId: '',
                buildingUnitId: '',
                buildDate: '',
                buildYear: '',
                commissionWilling: '',
                decorate: '',
                delegateEnd:'',
                delegateStart:'',
                delegateType: '',
                estateArea: '',
                floor: '',
                floorCounts: '',
                hasElevator:'',
                heatingType: '',
                houseNo: '',
                isOnly:'',
                sCounts: 0,
                tCounts: 0,
                cCounts: 0,
                wCounts: 0,
                ytCounts: 0,
                level: 'PU',
                showing: '',
                mobiles:['','',''],
                note: "",
                orientation: '',
                ownerName: '',
                overYears: '',
                permitAddress: '',
                certifNo:'',
                certifType: '',
                propertyType: '',
                publishPrice: '',
                purchasePrice: '',
                purchaseDate: '',
                realArea: '',
                rentPriceUnit: 'YUAN',
                sellPriceUnit: 'YUAN',
                resident: '',
                status: '',
                source: '',
                structureType: '',
                houseSubType: '',
                taxesWilling: '',
                houseType: '',
                unitPrice: '',
                xiaoQuId: ''
            };
            /*住宅类型*/
            _this.subTypeList = [];
            /*地址*/
            _this.estateList = [];
            _this.buildList = [{name:'',value:''}];
            _this.unitList = [{name:'',value:''}];
            /*获得小区信息*/
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
            /*获得栋座信息*/
            _this.buildingsGet = function(key){
                _this.buildList =[{name:'',value:''}];
                _this.data.buildingId = '';
                _this.data.buildingUnitId = '';
                if(key === ''){
                    _this.buildList =[{name:'',id:''}];
                    _this.unitList =[{name:'',value:''}];
                    return;
                }
                FangService.buildings({xiaoQuId: key}).done(function (response) {
                    $scope.$apply(function(){
                        if(response && response.length>0){
                            _this.buildList = response;
                        }else{
                            _this.buildList =[{name:'',id:''}];
                        }
                    });
                }).fail(function(){
                    _this.buildList =[{name:'',id:''}];
                });

            };
            /*获得单元信息*/
            _this.unitsGet = function(key){
                _this.unitList =[{name:'',value:''}];
                _this.data.buildingUnitId = '';
                if(key === ''){
                    _this.unitList =[{name:'',value:''}];
                    return;
                }
                FangService.buildingUnit({buildingId: key}).done(function (response) {
                    $scope.$apply(function(){
                        if(response && response.length>0){
                            _this.unitList = response.map(function(item){
                                return {
                                    name: item.unitName,
                                    value: item.id
                                }
                            });
                        }else{
                            _this.unitList =[{name:'',value:''}];
                        }
                    });
                }).fail(function(){
                    _this.unitList =[{name:'',value:''}];
                });
            };
            /*设置总楼层数*/
            _this.floorCountsSet = function(value){
                var building =_this.buildList.filter(function(item){
                    return item.id == value
                });
                _this.data.floorCounts = building[0] && building[0].floors;
            };

            /*检查Licence*/
            _this.checkLicence = function(){
                if(_this.data.xiaoQuId && _this.data.buildingId && _this.data.buildingUnitId && _this.data.houseNo && _this.data.bizType){
                    var data = {
                        xiaoQuId: _this.data.xiaoQuId,
                        bizType: _this.data.bizType,
                        buildingId: _this.data.buildingId,
                        buildingUnitId: _this.data.buildingUnitId,
                        houseNo: _this.data.houseNo
                    };
                    FangService.checkLicence(data).then(function(response){
                        if(response.result != 'SUCCEED'){
                            _this.showWarn({title:'提示',content:'已存在该房间记录'});
                        }
                    });
                }
            };
            _this.chosenChange = function(id, key, value,target){
                if(key === 'xiaoQuId'){
                    _this.buildingsGet(value);
                    $('#houseEstate_chosen>.chosen-single').removeClass('invalid-input');
                }else if(key === 'buildingId'){
                    _this.floorCountsSet(value);
                    _this.unitsGet(value);
                }
                if(value === ""){
                    return;
                }
                _this.checkLicence();
            };
             _this.stateInputChange = function(e){
                $timeout.cancel(config.xiaoQuId.timer);
                config.xiaoQuId.timer = $timeout(function(){
                    config.xiaoQuId.value = $(e.target).val().trim();
                    if(config.xiaoQuId.value === ""){
                        return;
                    }
                    _this.estateGet(config.xiaoQuId.value);
                },800);
            };
            /*弹出框*/
            _this.showWarn = function(param){
                $('#warnModel').modal('show');
                $timeout(function(){
                    _this.page.warn.title= param.title;
                    _this.page.warn.content= param.content;
                    _this.page.warn.closeF= param.closeF;
                },30)
            };
            _this.addBuildInit = function(){
                XiaoquService.getXiaoquInfo({id: _this.data.xiaoQuId}).then(function(response){
                    if(!response){
                        return;
                    }
                    $('#addBuildModel').modal('show');
                    _this.newBuilding.xiaoQuId = _this.data.xiaoQuId;
                    $timeout(function(){
                        _this.newBuilding.xiaoqu={
                            id:_this.data.xiaoQuId,
                            name: response.name,
                            address : response.address
                        };
                    },30);
                });
            };
            _this.addBuildConfirm = function(){
                if($scope.newBuildForm.$invalid||!$scope.newBuildForm.$dirty){
                    if (!_this.newBuilding.name) {
                        _this.commonInputWarn('buildingName','newBuildForm');
                    }
                    if (!_this.newBuilding.floors) {
                        _this.commonInputWarn('buildingFloors','newBuildForm');
                    }
                    if (!_this.newBuilding.stairs) {
                        _this.commonInputWarn('buildingStairs','newBuildForm');
                    }
                    if (!_this.newBuilding.houses) {
                        _this.commonInputWarn('buildingHouses','newBuildForm');
                    }
                    return;
                }
                FangService.buildingPost(_this.newBuilding).then(function(){
                    $('#addBuildModel').modal('hide');
                    _this.buildingsGet(_this.newBuilding.xiaoQuId);
                });
            };
            _this.addUnitInit = function(){
                FangService.buildingInfo({id: _this.data.buildingId}).then(function(response){
                    if(!response){
                        return;
                    }
                    $('#addUnitModel').modal('show');
                    _this.newUnit.buildingId = _this.data.buildingId;
                    $timeout(function(){
                        _this.newUnit.buildingName= response.name;
                    },30);
                });
            };
            _this.addUnitConfirm = function () {
                    if (!_this.newUnit.unitNames[0]&&!_this.newUnit.unitNames[1]&&!_this.newUnit.unitNames[2]&&!_this.newUnit.unitNames[3]&&!_this.newUnit.unitNames[4]) {
                        _this.commonInputWarn('buildingUnits0','addUnitForm');
                        return;
                    }
                FangService.buildingUnitPost(_this.newUnit).then(function(){
                    $('#addUnitModel').modal('hide');
                    _this.unitsGet(_this.data.buildingId);
                });
            };
            /*楼层检查*/
            _this.floorCountCheck = function(){
                if(!ceilCheck(_this.data.floorCounts, _this.data.floor)){
                    _this.showWarn({title:'提示',content:'楼层数不能大于总层数'});
                    return false;
                }
                return true;
            };
            //检查面积
            _this.checkArea = function(){
                if(!ceilCheck(_this.data.estateArea, _this.data.realArea)){
                    _this.showWarn({title:'提示',content:'套内面积不能大于建筑面积'});
                    return false;
                }
                return true;
            };
            /*总价均价变化、总价底价检查*/
            _this.estateAreaCheck = function(){
                _this.unitPriceSet('estateArea');
                _this.checkArea();
            };
            _this.publishPriceSet = function(){
                _this.unitPriceSet('publishPrice');
                _this.bottomPriceCheck();
            };
            _this.bottomPriceCheck = function () {
                if(!ceilCheck(_this.data.publishPrice, _this.data.bottomPrice)){
                    _this.showWarn({title: '提示', content: '底价不能大于总价'});
                    return false
                }
                return true;
            };
            _this.unitPriceSet = function(origin){
                if(!_this.data.estateArea||(!_this.data.publishPrice && !_this.data.unitPrice)){
                    return;
                }
                if(origin === 'estateArea'||origin === 'publishPrice'){
                    _this.data.unitPrice = (_this.data.publishPrice / _this.data.estateArea).toFixed(2);
                }else{
                    _this.data.publishPrice = _this.data.unitPrice * _this.data.estateArea;
                }
            };
            _this.chosenEstate = function(id,key){

                if(!config.xiaoQuId.init){
                    _this.initChosen(id, key);
                }else{
                    $(id).trigger('chosen:updated');
                    if( $('#houseEstate_chosen .search-field-input').val().trim() == ''){
                        $('#houseEstate_chosen .search-field-input').val(config.xiaoQuId.value);
                    }
                }
                $('#estateContainer').off('input','.search-field-input', _this.stateInputChange).on('input','.search-field-input', _this.stateInputChange);
            };
            _this.initSelectPicker = function(id){
                $(id).selectpicker({
                    style: 'btn-default',
                    dropupAuto:false,
                    size: 8
                });
            };
            _this.initChosen = function(id, key){
                $(id).chosen("destroy");
                if(!config[key].init){
                    config[key].init = !config[key].init;
                    $(id).chosen().change(function(e, result){
                        $scope.$apply(function(){
                            _this.data[key] = result.selected;
                            _this.chosenChange(id, key, result.selected);
                        });
                    });
                    return;
                }
                $(id).chosen();
                $(id).trigger('chosen:updated');
            };
            _this.layoutDialogShow = function(){
                _this.currentLayout = {
                    sCounts: _this.data.sCounts,
                    tCounts: _this.data.tCounts,
                    cCounts: _this.data.cCounts,
                    wCounts: _this.data.wCounts,
                    ytCounts: _this.data.ytCounts
                };
            };
            _this.layoutConfirm = function(){
                _this.data.sCounts = _this.currentLayout.sCounts;
                _this.data.tCounts = _this.currentLayout.tCounts;
                _this.data.cCounts = _this.currentLayout.cCounts;
                _this.data.wCounts = _this.currentLayout.wCounts;
                _this.data.ytCounts = _this.currentLayout.ytCounts;
                _this.page.layoutString = (_this.data.sCounts || _this.data.tCounts || _this.data.cCounts || _this.data.wCounts || _this.data.ytCounts) ?
                (_this.data.sCounts ? _this.data.sCounts + '室' : '') +
                (_this.data.tCounts ? _this.data.tCounts + '厅' : '') +
                (_this.data.cCounts ? _this.data.cCounts + '厨' : '') +
                (_this.data.wCounts ? _this.data.wCounts + '卫' : '') +
                (_this.data.ytCounts ? _this.data.ytCounts + '阳台' : '') : '';
            };
            _this.log = function(){
                console.log( _this.data);
            };
            _this.datePickApply = function(){
                $scope.$apply();
            };
            _this.nextStep = function () {
                var flag = true;
                if (!_this.data.xiaoQuId) {
                    flag = false;
                    $('#houseEstate_chosen>.chosen-single').addClass('invalid-input');
                }
                if (!_this.data.buildingId) {
                    flag = false;
                    $('#houseBuild_chosen>.chosen-single').addClass('invalid-input');
                }
                if (!_this.data.buildingUnitId) {
                    flag = false;
                    $('#houseUnit_chosen>.chosen-single').addClass('invalid-input');
                }
                if (!_this.data.houseNo) {
                    flag = false;
                    _this.commonInputWarn('houseNo');
                }
                if (!_this.data.floor) {
                    flag = false;
                    _this.commonInputWarn('floor');
                }
                if (!_this.data.floorCounts) {
                    flag = false;
                    _this.commonInputWarn('floorCounts');
                }
                if (!_this.data.address) {
                    flag = false;
                    _this.commonInputWarn('address');
                }
                if (!_this.data.publishPrice) {
                    flag = false;
                    _this.commonInputWarn('publishPrice');
                }
                if (!_this.data.estateArea) {
                    flag = false;
                    _this.commonInputWarn('estateArea');
                }
                if (!_this.data.houseType) {
                    flag = false;
                    $('#houseType').siblings('.btn-default').addClass('invalid-input');
                }
                if (!_this.data.houseSubType) {
                    flag = false;
                    $('#houseSubType').siblings('.btn-default').addClass('invalid-input');
                }
                if (!_this.data.structureType) {
                    flag = false;
                    $('#houseStructureType').siblings('.btn-default').addClass('invalid-input');
                }
                if (!_this.data.orientation) {
                    flag = false;
                    $('#houseOrientation').siblings('.btn-default').addClass('invalid-input');
                }
                if (!_this.data.ownerName) {
                    flag = false;
                    _this.commonInputWarn('houseOwname');
                }
                if (!_this.data.mobiles[0]) {
                    flag = false;
                    _this.commonInputWarn('houseOwphone1');
                }
                if (!_this.data.buildYear) {
                    flag = false;
                    _this.commonInputWarn('buildYear');
                }
                if(!flag){
                    return;
                }
                if (!_this.checkArea() || !_this.floorCountCheck() || !_this.bottomPriceCheck()) {
                    return;
                }
                _this.page.currentStep = _this.stepConfig.step2;
            };
            _this.prevStep = function () {
                _this.page.currentStep = _this.stepConfig.step1;
            };
            _this.subTypeRefresh = function(id){
                $(id).selectpicker('refresh')
            };
            _this.setBuildDate = function(){
                _this.log();
            };
            _this.datePickChange = function(key, value){
                $scope.$apply(function(){
                    _this.data[key] = value;
                });
            };
            _this.setLayout = function(type, value){
                _this.currentLayout[type] = value;
            };
            _this.submit = function(){
                var flag = true;
                /*if($scope.houseForm.$invalid||!$scope.houseForm.$dirty) {*/
                if (!_this.data.delegateType) {
                    $('#houseDelegateType').siblings('.btn-default').addClass('invalid-input');
                    flag = false;
                }
                if (!_this.data.delegateStart) {
                    _this.commonInputWarn('delegateStart');
                    flag = false;
                }
                if(!_this.data.showing){
                    $('#houseShowing').siblings('.btn-default').addClass('invalid-input');
                    flag = false;
                }
                if(!_this.data.status){
                    $('#houseStatus').siblings('.btn-default').addClass('invalid-input');
                    flag = false;
                }
                if(!_this.data.source){
                    $('#houseSource').siblings('.btn-default').addClass('invalid-input');
                    flag = false;
                }
                if(!_this.data.decorate){
                    $('#houseDecorate').siblings('.btn-default').addClass('invalid-input');
                    flag = false;
                }
                if(!_this.data.heatingType){
                    $('#houseHeatingType').siblings('.btn-default').addClass('invalid-input');
                    flag = false;
                }
                if(!_this.data.hasElevator){
                    $('#houseHasElevator').siblings('.btn-default').addClass('invalid-input');
                    flag = false;
                }
                if(!_this.data.taxesWilling){
                    $('#houseTaxesWilling').siblings('.btn-default').addClass('invalid-input');
                    flag = false;
                }
                /*}*/
                if(!flag){
                    return;
                }
                _this.data.priceUnit = _this.data.houseType ===_this.bizTypeConfig.rent? _this.data.rentPriceUnit: _this.data.sellPriceUnit;
                _this.data.isOnly = _this.data.isOnly ?'Y':'N';
                _this.data.resident = _this.data.resident ?'Y':'N';
                FangService.create(_this.data).then(function(){
                    _this.showWarn({title:'提示',content:'新增房源完成',closeF:_this.goList});
                });

            };
            _this.goList = function(){
                $window.location ='/mgt/fangManage/list.ftl?target=.fang';
            };
            _this.houseTypeChange = function(){
                _this.data.houseSubType = '';
                if(!_this.selectPickerChange('#houseType', 'houseType')){
                    _this.subTypeList =[{name:'',value:''}];
                    return;
                }
                FangService.subType({houseType:_this.data.houseType}).then(function(response){
                    $scope.$apply(function(){
                        _this.subTypeList = response.map(function(item){
                            return{
                                name: item.label,
                                value: item.name
                            }
                        })
                    });
                }).fail(function(){
                    $scope.$apply(function() {
                        _this.subTypeList =[{name:'',value:''}];
                    });
                });
            };
        }
        AddHouseCtrl.$inject = ['$scope', '$timeout', '$interval', '$window'];
        AddHouseModule.controller("AddHouseCtrl", AddHouseCtrl);

        angular.element(document).ready(function() {
            angular.bootstrap(document.getElementById("addHouse"),["AddHouseModule"])
        });
    });
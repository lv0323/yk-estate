/**
 * Created by robin on 17/2/17.
 */
require(['main-app',
        contextPath + '/js/service/identity-service.js',
        contextPath+'/js/service/fang-service.js',
        contextPath + '/js/service/validation-service.js',
        contextPath + '/js/service/department-service.js',
        contextPath + '/js/service/util-service.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        'jqPaginator', 'select', 'chosen', 'datetimepicker.zh-cn'],
    function (mainApp, IdentityService, FangService, ValidationService, DepartmentService, UtilService, pagingPlugin) {

        var AddHouseModule=angular.module('AddHouseModule',[]);
        AddHouseModule.directive('datetimepicker', function () {
            return {
                restrict: 'A',
                /*controller: AddHouseCtrl,
                controllerAs: 'ctrl',*/
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
                        scope.ctrl[attrs.change](attrs.key,$(this).val());
                    });
                }
            }
        });
        AddHouseModule.directive('selectPicker', ['$timeout',function ($timeout) {
            return {
                restrict: 'A',
                /*controller: AddHouseCtrl,
                controllerAs: 'ctrl',*/
                link : function (scope, element, attrs) {
                    $timeout(function(){
                        $(element).selectpicker({
                            style: 'btn-default',
                            dropupAuto:false,
                            size: 8,
                        });
                    },0);
                }
            }
        }]);
        AddHouseModule.directive('chosenSelect', ['$timeout',function ($timeout) {
            return {
                restrict: 'A',
                /*controller: AddHouseCtrl,
                controllerAs: 'ctrl',*/
                link : function (scope, element, attrs) {
                    $timeout(function(){
                        $(element).chosen("destroy");
                        $(element).chosen().change(function(e, result){
                            scope.$apply(function(){
                                scope.ctrl.data[attrs.chosenSelect] = result.selected;
                                scope.ctrl.log();
                            });
                        });
                    },0);
                }
            }
        }]);
        AddHouseModule.directive('repeatDone',['$timeout', function($timeout){
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
        AddHouseModule.directive('stMobilePhone', function() {
            return {
                restrict: 'A',
                require: 'ngModel',
                link: function($scope, elem, attrs, ctrl) {
                    ctrl.$parsers.unshift(function(value) {
                        if (!value || ValidationService.isValidPhoneNumber(value)) {
                            ctrl.$setValidity('mobilePhone', true);
                        } else {
                            ctrl.$setValidity('mobilePhone', false);
                        }
                        return value;
                    });
                }
            };
        });
        AddHouseModule.directive('stNumber', function() {
            return {
                restrict: 'A',
                require: 'ngModel',
                link: function($scope, elem, attrs, ctrl) {
                    ctrl.$parsers.unshift(function(value) {
                        if (!value || isValidMoneyAmount(value)) {
                            ctrl.$setValidity('moneyAmount', true);
                        } else {
                            ctrl.$setValidity('moneyAmount', false);
                        }
                        return value;
                    });
                }
            };
        });


        function AddHouseCtrl($scope, $timeout, $interval, $window) {
            var _this = this;
            var config = {
                xiaoquId:{
                    init: false,
                    value:'',
                    timer:null
                },
                buildingId:{
                    init:false
                },
                buildingUnitId:{
                    init:false
                },
            };
            _this.stepConfig = {
                step1: 1,
                step2: 2
            };
            _this.bizTypeConfig = {
                rent: 'RENT',
                sell: 'SELL'
            }
            /*页面相关内容*/
            _this.page ={
                name: "新增住宅",
                currentStep: _this.stepConfig.step1,
                warn: {
                    title:'提示',
                    content:'请输入完整信息',
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
                id:'',
                floorAll:'',
                countT:'',
                countH:'',
                remark:'',
                xiaoqu: {
                    id: '',
                    name: '',
                    address: '',
                }
            };
            /*新建单元*/
            _this.newUnit = {
                build: {
                    id: '',
                    name: ''
                },
                unit: []
            };
            /*户型*/
            _this.currentLayout ={
                sCounts: '',
                tCounts: '',
                cCounts: '',
                wCounts: '',
                ytCounts: ''
            };
            /*房屋信息*/
            _this.data ={
                bizType: 'SELL',
                buildingId: '',
                buildingUnitId: '',
                buildDate: '',
                character:{
                    isOnly:false,
                    fiveYears:false,
                    twoYears:false
                },
                commission: '',
                construct: '',
                decorate: '',
                downPayPer: '',
                entrustWay: '',
                estateArea: '',
                floor: '',
                floorCounts: '',
                heating: '',
                houseNo: '',
                sCounts: 0,
                tCounts: 0,
                cCounts: 0,
                wCounts: 0,
                ytCounts: 0,
                level: '',
                lift:'',
                look: '',
                minPrice: '',
                orientation: '',
                owName: '',
                owPhone1: '',
                owPhone2: '',
                owPhone3: '',
                permitAddress: '',
                permitNumber: '',
                presentSituation: '',
                proveDate: '',
                proveType: '',
                pType: '',
                publishPrice: '',
                purchase: '',
                realArea: '',
                remark: '',
                rentPriceUnit: 'YUAN',
                sellPriceUnit: 'YUAN',
                settle: '',
                source: '',
                startDate: '',
                subType: '',
                endDate: '',
                tax: '',
                type: '',
                unitPrice: '',
                xiaoquId: ''
            };
            /*住宅类型*/
            _this.subTypeList = [];
            /*地址*/
            _this.estateList = [];
            _this.buildList = [{name:'',value:''}];
            _this.unitList = [{name:'',value:''}];
            /*获得小区信息*/
            function estateGet(key){
                FangService.xiaoquOption({keyword: key}).done(function (response) {
                    $scope.$apply(function(){
                        _this.estateList = response.map(function(item, index){
                            return {
                                name: item.xiaoQuName,
                                value: item.xiaoQuId
                            }
                        });
                    });
                });
            }
            estateGet('a');
            /*获得栋座信息*/
            function buildingsGet(key){
                FangService.buildings({xiaoQuId: key}).done(function (response) {
                    $scope.$apply(function(){
                        if(response && response.length>0){
                            _this.buildList = response.map(function(item, index){
                                return {
                                    name: item.name,
                                    value: item.id
                                }
                            });
                        }else{
                            _this.buildList =[{name:'',value:''}];
                        }
                    });
                });
            }
            /*获得单元信息*/
            function unitsGet(key){
                FangService.buildingUnit({buildingId: key}).done(function (response) {
                    $scope.$apply(function(){
                        if(response && response.length>0){
                            _this.unitList = response.map(function(item, index){
                                return {
                                    name: item.unitName,
                                    value: item.id
                                }
                            });
                        }else{
                            _this.unitList =[{name:'',value:''}];
                        }
                    });
                });
            }
            function chosenChange(id, key, value){
                if(value === ""){
                    return;
                }
                console.log(key);
                if(key === 'xiaoquId'){
                    buildingsGet(value);
                }else if(key === 'buildingId'){
                    unitsGet(value)
                }
            }
            function stateInputChange(e){
                $timeout.cancel(config.estate.timer)
                config.estate.timer = $timeout(function(){
                    config.estate.value = $(e.target).val().trim();
                    if(config.estate.value === ""){
                        return;
                    }
                    estateGet(config.estate.value);
                },800);
            }
            /*弹出框*/
            function showWarn(param){
                $('#warnModel').modal('show');
                $timeout(function(){
                    _this.page.warn.title= param.title;
                    _this.page.warn.content= param.content;
                },30)
            }
            /*获取子类型*/
            _this.addBuildInit = function(){
                console.log('init Build');
            };
            _this.addBuildConfirm = function(){
                console.log('add Build');
            };
            _this.addUnitInit = function(){
                console.log('init Unit');
            };
            _this.addUnitConfirm = function () {

            };
            //检查面积
            _this.checkArea = function(){
                if(_this.data.realArea && this.data.estateArea){
                    if(_this.data.realArea > this.data.estateArea){
                        showWarn({title:'提示',content:'套内面积不能大于建筑面积'});
                    }
                }

            };
            _this.chosenEstate = function(id,key){

                if(!config.xiaoquId.init){
                    _this.initChosen(id, key);
                }else{
                    $(id).trigger('chosen:updated');
                    if( $('#houseEstate_chosen .search-field-input').val().trim() == ''){
                        $('#houseEstate_chosen .search-field-input').val(config.estate.value);
                    }
                }
                $('#estateContainer').off('input','.search-field-input', stateInputChange);
                $('#estateContainer').on('input','.search-field-input', stateInputChange);
            };
            _this.initSelectPicker = function(id){
                $(id).selectpicker({
                    style: 'btn-default',
                    dropupAuto:false,
                    size: 8,
                });
            };
            _this.initChosen = function(id, key){
                $(id).chosen("destroy");
                if(!config[key].init){
                    config[key].init = !config[key].init;
                    $(id).chosen().change(function(e, result){
                        $scope.$apply(function(){
                            _this.data[key] = result.selected;
                            chosenChange(id, key, result.selected)
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
                _this.log();
            }
            _this.log = function(){
                console.log( _this.data);
            };
            _this.nextStep = function () {
                if($scope.houseForm.$invalid||!$scope.houseForm.$dirty){
                    if(!_this.data.xiaoquId){
                        $('#houseEstate_chosen>.chosen-single').addClass('invalid-input');
                    }
                    if(!_this.data.buildingId){
                        $('#houseBuild_chosen>.chosen-single').addClass('invalid-input');
                    }
                    if(!_this.data.buildingUnitId){
                        $('#houseUnit_chosen>.chosen-single').addClass('invalid-input');
                    }
                    if(!_this.data.houseNo){
                        $scope.houseForm.houseNo.$setDirty();
                        $scope.houseForm.houseNo.$setValidity('required', false);;
                    }
                    if(!_this.data.floor){
                        $scope.houseForm.floor.$setDirty();
                        $scope.houseForm.floor.$setValidity('required', false);;
                    }
                    if(!_this.data.floorCounts){
                        $scope.houseForm.floorCounts.$setDirty();
                        $scope.houseForm.floorCounts.$setValidity('required', false);;
                    }
                    if(!_this.data.publishPrice){
                        $scope.houseForm.publishPrice.$setDirty();
                        $scope.houseForm.publishPrice.$setValidity('required', false);;
                    }
                    if(!_this.data.estateArea){
                        $scope.houseForm.estateArea.$setDirty();
                        $scope.houseForm.estateArea.$setValidity('required', false);;
                    }
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
            _this.setDate = function(key, value){
                _this.data[key] = value;
                _this.log();
            };
            _this.setLayout = function(type, value){
                _this.currentLayout[type] = value;
            };
            _this.submit = function(){
                FangService.create({id:[2,3,4]}).then(function(response){

                }).fail(function(){

                });

            };
            _this.typeChange = function(){
                if(!_this.data.type){
                    return;
                }
                FangService.subType({houseType:_this.data.type}).then(function(response){
                    $scope.$apply(function(){
                        _this.subTypeList = response.map(function(item){
                            return{
                                name: item.label,
                                value: item.name
                            }
                        })
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
/**
 * Created by robin on 17/2/17.
 */
require(['main-app',
        contextPath + '/js/service/identity-service.js',
        contextPath+'/js/service/house-service.js',
        contextPath + '/js/service/validation-service.js',
        contextPath + '/js/service/department-service.js',
        contextPath + '/js/service/util-service.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        'jqPaginator', 'select', 'chosen', 'datetimepicker.zh-cn'],
    function (mainApp, IdentityService, HouseService, ValidationService, DepartmentService, UtilService, pagingPlugin) {
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

        function AddHouseCtrl($scope, $timeout, $interval, $window) {
            var _this = this;
            var config = {
                estate:{
                    init: false,
                    value:'',
                    timer:null
                },
                build:{
                    init:false
                },
                unit:{
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
                currentStep: _this.stepConfig.step1
            };
            /*户型选择*/
            _this.layoutList={
                shi:[{value:1,name:'1'}, {value:2,name:'2'}, {value:3,name:'3'}, {value:4,name:'4'}, {value:5,name:'5'}, {value:6,name:'6'},{value:7,name:'7'},{value:'',name:'无'}],
                ting:[{value:1,name:'1'}, {value:2,name:'2'}, {value:3,name:'3'}, {value:4,name:'4'}, {value:5,name:'5'}, {value:6,name:'6'},{value:7,name:'7'},{value:'',name:'无'}],
                chu:[{value:1,name:'1'}, {value:2,name:'2'}, {value:3,name:'3'}, {value:4,name:'4'}, {value:5,name:'5'}, {value:6,name:'6'},{value:7,name:'7'},{value:'',name:'无'}],
                wei:[{value:1,name:'1'}, {value:2,name:'2'}, {value:3,name:'3'}, {value:4,name:'4'}, {value:5,name:'5'}, {value:6,name:'6'},{value:7,name:'7'},{value:'',name:'无'}],
                yangtai:[{value:1,name:'1'}, {value:2,name:'2'}, {value:3,name:'3'}, {value:4,name:'4'}, {value:5,name:'5'}, {value:6,name:'6'},{value:7,name:'7'},{value:'',name:'无'}]
            };
            /*新建栋座*/
            _this.newBuilding = {
                id:'',
                floorAll:'',
                countT:'',
                countH:'',
                remark:'',
                estate: {
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
                shi:'',
                ting:'',
                chu:'',
                wei:'',
                yangtai:''
            };
            /*房屋信息*/
            _this.data ={
                allSqm: '',
                bizType: 'SELL',
                build: '',
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
                estate: '',
                floor: '',
                floorAll: '',
                heating: '',
                layout:{
                    shi:'',
                    ting:'',
                    chu:'',
                    wei:'',
                    yangtai:''
                },
                level: '',
                lift:'',
                look: '',
                minPrice: '',
                orientation: '',
                owName: '',
                owPhone1: '',
                owPhone2: '',
                owPhone3: '',
                partSqm: '',
                permitAddress: '',
                permitNumber: '',
                presentSituation: '',
                price: '',
                proveDate: '',
                proveType: '',
                pType: '',
                purchase: '',
                remark: '',
                rentPriceUnit: 'YUAN',
                room: '',
                sellPriceUnit: 'YUAN',
                settle: '',
                source: '',
                startDate: '',
                subType: '',
                endDate: '',
                tax: '',
                type: '',
                unit: '',
                unitPrice: ''
            };
            /*住宅类型*/
            _this.subTypeList = [];
            /*地址*/
            _this.estateList = [];
            _this.buildList = [{name:'',value:''}];
            _this.unitList = [{name:'',value:''}];
            _this.houseConstructList = [
                {name:'塔房',value :'1'},
                {name:'板房',value :'2'},
                {name:'塔板结合',value :'3'},
                {name:'其他',value :'4'},


            ];
            _this.orientationList =[
                {name:'东', value:'9887'},
                {name:'南', value:'9888'},
                {name:'西', value:'9889'},
                {name:'北', value:'9880'},
                {name:'南北', value:'9881'},
                {name:'东西', value:'9882'},
                {name:'东南', value:'9883'},
                {name:'西南', value:'9884'},
                {name:'东北', value:'9885'},
                {name:'西北', value:'9886'},

            ];
            _this.gradeList =[
                {name:'A级',value:'9772'},
                {name:'B级',value:'9873'},
                {name:'C级',value:'9874'},
            ];
            /*
            * <option value="9876" info="ty4">独家</option>
             <option value="9877" info="ty5">签约</option>
             <option value="9878" info="ty6">未签</option>
             <option value="9967" info="ty77">限时</option>
             <option value="9969" info="ty78">托管</option>
            * */
            _this.entrustWayList = [
                {name:'独家', value: '9876'},
                {name:'签约', value: '9877'},
                {name:'未签', value: '9878'},
                {name:'限时', value: '9879'},
                {name:'托管', value: '9870'}
            ];
            /*获得小区信息*/
            function estateGet(key){
                HouseService.xiaoquOption({keyword: key}).done(function (response) {
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
                HouseService.buildings({xiaoQuId: key}).done(function (response) {
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
                HouseService.buildingUnit({buildingId: key}).done(function (response) {
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
            /*获取子类型*/
            _this.typeChange = function(){
                _this.subTypeList = [
                    {name:'多层',value :'1'},
                    {name:'高层',value :'2'},
                    {name:'小高层',value :'3'},
                    {name:'多层复式',value :'4'},
                    {name:'高层复式',value :'5'},
                    {name:'多层跃式',value :'6'},
                    {name:'高层跃式',value :'7'},
                    {name:'裙楼',value :'8'},
                    {name:'四合院',value :'9'}
                ];
            };
            _this.subTypeRefresh = function(id){
                $(id).selectpicker('refresh')
            }
            _this.addBuildInit = function(){
                console.log('init Build');
            };
            _this.addBUnitInit = function(){
                console.log('init Unit');
            };
            _this.addBuildConfirm = function(){
                console.log('add Build');
            };
            _this.addUnitConfirm = function () {

            };
            function chosenChange(id, key, value){
                if(value === ""){
                    return;
                }
                console.log(key);
                if(key === 'estate'){
                    buildingsGet(value);
                }else if(key === 'build'){
                    unitsGet(value)
                }
            }
            _this.nextStep = function () {
            //检查数据的完整性和正确性
                _this.page.currentStep = _this.stepConfig.step2;
            };
            _this.prevStep = function () {
            //检查数据的完整性和正确性
                _this.page.currentStep = _this.stepConfig.step1;
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
                /*$timeout(function(){
                    console.log('chosen:updated');
                    $('#houseEstate_chosen input').val('多');
                    $(id).trigger('chosen:updated');

                },3000)*/

            };
            var stateInputChange = function(e){
                $timeout.cancel(config.estate.timer)
                config.estate.timer = $timeout(function(){
                    config.estate.value = $(e.target).val().trim();
                    if(config.estate.value === ""){
                        return;
                    }
                    estateGet(config.estate.value);
                },800);
            }
            _this.chosenEstate = function(id,key){

                if(!config.estate.init){
                    _this.initChosen(id, key);
                    config.estate.init = true;
                }else{
                    $(id).trigger('chosen:updated');
                    if( $('#houseEstate_chosen .search-field-input').val().trim() == ''){
                        $('#houseEstate_chosen .search-field-input').val(config.estate.value);
                    }
                }
                $('#estateContainer').off('input','.search-field-input', stateInputChange);
                $('#estateContainer').on('input','.search-field-input', stateInputChange);
            };
            /*_this.initChosen('#houseEstate', 'estate');*/
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
            _this.layoutDialogShow = function(){
                angular.copy(_this.data.layout, _this.currentLayout);
            };
            _this.layoutConfirm = function(){
                angular.copy(_this.currentLayout, _this.data.layout);
                _this.log();
            }
            _this.log = function(){
                console.log( _this.data);
            };
            /*_this.initSelectPicker('#rentPriceUnit');
            _this.initSelectPicker('#sellPriceUnit');
            _this.initSelectPicker('#housetailFirper');
            _this.initSelectPicker('#houseLook');
            _this.initSelectPicker('#presentSituation');
            _this.initSelectPicker('#houseSource');
            _this.initSelectPicker('#houseProveType');
            _this.initSelectPicker('.init-select-picker');*/


        }
        AddHouseCtrl.$inject = ['$scope', '$timeout', '$interval', '$window'];
        AddHouseModule.controller("AddHouseCtrl", AddHouseCtrl);

        angular.element(document).ready(function() {
            angular.bootstrap(document.getElementById("addHouse"),["AddHouseModule"])
        });
    });
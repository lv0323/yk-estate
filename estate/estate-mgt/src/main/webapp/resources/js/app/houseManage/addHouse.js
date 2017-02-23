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

        /*$('#rentPriceUnit').selectpicker({
            style: 'btn-default',
            dropupAuto:false,
            size: 8,
        });*/
        /*$('.chosen-select').chosen();*/
        var AddHouseModule=angular.module('AddHouseModule',[]);
        AddHouseModule.directive('datetimepicker', function () {
            return {
                restrict: 'A',
                controller: AddHouseCtrl,
                controllerAs: 'ctrl',
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
                controller: AddHouseCtrl,
                controllerAs: 'ctrl',
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
                controller: AddHouseCtrl,
                controllerAs: 'ctrl',
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
            _this.desc ={
                pageName:"新增住宅"
            };
            _this.stepConfig ={
                step1: 1,
                step2: 2
            };
            _this.currentStep = _this.stepConfig.step1,
            _this.data ={
                allSqm: '',
                bizType: 'sell',
                build: '',
                buildDate: '',
                character:{
                    unique:false,
                    fiveYears:false,
                    twoYears:false
                },
                commission: '',
                construct: '',
                decorate: '',
                direction: '',
                downPayPer: '',
                entrustWay: '',
                estate: '',
                floor: '',
                floorAll: '',
                heating: '',
                lift:'',
                look: '',
                minPrice: '',
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
                rentPriceUnit: '9929',
                room: '',
                sellPriceUnit: '9935',
                settle: '0',
                source: '',
                startDate: '',
                endDate: '',
                tax: '',
                type: '',
                unit: '',
                unitPrice: ''
            };
            _this.typeList = [
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
            _this.estateList = [
                {name:'多层',value :1},
                {name:'高层',value :2},
                {name:'小高层',value :3},
                {name:'多层复式',value :4},
                {name:'高层复式',value :5},
                {name:'多层跃式',value :6},
                {name:'高层跃式',value :7},
                {name:'裙楼',value :8},
                {name:'四合院',value :9}
            ];
            _this.houseBuildList = [
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
            _this.houseConstructList = [
                {name:'塔房',value :'1'},
                {name:'板房',value :'2'},
                {name:'塔板结合',value :'3'},
                {name:'其他',value :'4'},


            ];
            _this.houseUnitList = [
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
            _this.directionList =[
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
            _this.nextStep = function () {
            //检查数据的完整性和正确性
                _this.currentStep = _this.stepConfig.step2;
            };
            _this.prevStep = function () {
            //检查数据的完整性和正确性
                _this.currentStep = _this.stepConfig.step1;
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
                $(id).chosen().change(function(e, result){
                    $scope.$apply(function(){
                        _this.data[key] = result.selected;
                    });
                    _this.log();
                });
            };
            _this.setBuildDate = function(){
                console.log(_this.data);
            }
            _this.setDate = function(key, value){
                _this.data[key] = value;
                console.log( _this.data);
            };
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
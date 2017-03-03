/**
 * Created by robin on 17/2/17.
 */
require(['main-app',
        contextPath + '/js/service/identity-service.js',
        contextPath + '/js/service/fang-service.js',
        contextPath + '/js/service/validation-service.js',
        contextPath + '/js/service/xiaoqu-service.js',
        contextPath + '/js/service/util-service.js',
        contextPath + '/js/app/fang/tools.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        contextPath + '/js/directive/index.js',
        'jqPaginator', 'select', 'chosen', 'datepicker.zh-cn', 'datetimepicker.zh-cn', 'sweetalert'],
    function (mainApp,  IdentityService, FangService, ValidationService, XiaoquService, UtilService, Tools) {
        function ceilCheck(ceilValue, value) {
            if (ceilValue && value && parseFloat(ceilValue) < parseFloat(value)) {
                return false;
            }
            return true;

        }
        var module = angular.module('InfoModule', ['directiveYk']);

        function InfoCtrl($scope, $timeout, $interval, $window) {
            var fangId = UtilService.getUrlParam('id');
            var _this = this;
            /*页面相关内容*/
            _this.page ={
                name: "房源详情",
                warn: {
                    title:'提示',
                    content:'请输入完整信息'
                },
                layoutString:''
            };
            _this.summary = {};
            _this.baseInfo = {};
            _this.ext = {};
            _this.description = {};
            _this.descrUpdateInfo = {};
            _this.subTypeList = [];
            /*户型选择*/
            _this.layoutList={
                shi:[{value:1,name:'1'}, {value:2,name:'2'}, {value:3,name:'3'}, {value:4,name:'4'}, {value:5,name:'5'}, {value:6,name:'6'},{value:7,name:'7'},{value:0,name:'无'}],
                ting:[{value:1,name:'1'}, {value:2,name:'2'}, {value:3,name:'3'}, {value:4,name:'4'}, {value:5,name:'5'}, {value:6,name:'6'},{value:7,name:'7'},{value:0,name:'无'}],
                chu:[{value:1,name:'1'}, {value:2,name:'2'}, {value:3,name:'3'}, {value:4,name:'4'}, {value:5,name:'5'}, {value:6,name:'6'},{value:7,name:'7'},{value:0,name:'无'}],
                wei:[{value:1,name:'1'}, {value:2,name:'2'}, {value:3,name:'3'}, {value:4,name:'4'}, {value:5,name:'5'}, {value:6,name:'6'},{value:7,name:'7'},{value:0,name:'无'}],
                yangtai:[{value:1,name:'1'}, {value:2,name:'2'}, {value:3,name:'3'}, {value:4,name:'4'}, {value:5,name:'5'}, {value:6,name:'6'},{value:7,name:'7'},{value:0,name:'无'}]
            };
            _this.setLayout = function(type, value){
                _this.currentLayout[type] = value;
            };
            _this.setInfoOwner = function(response){

            };
            _this.selectPickerChange = function(id, key) {
                if(!_this.baseInfo[key]){
                    $(id).siblings('.btn-default').addClass('invalid-input');
                    return false;
                }
                $(id).siblings('.btn-default').removeClass('invalid-input');
                return true;
            };
            /*基本信息*/
            _this.getSummary = function(){
                FangService.summary({fangId:fangId}).then(function(response){
                    _this.summary = response;
                    _this.summary.layoutString = Tools.layoutFormat({
                        sCounts:_this.summary.sCounts,
                        tCounts:_this.summary.tCounts,
                        cCounts:_this.summary.cCounts,
                        wCounts:_this.summary.wCounts,
                        ytCounts:_this.summary.ytCounts,});
                    $scope.$apply();
                });
            };
            _this.getSummary();
            /*归属*/
            _this.getInfoOwner = function(){
                FangService.infoOwner({fangId:fangId}).then(function(response){
                    _this.setInfoOwner(response);
                });
            };
            _this.getInfoOwner();
            _this.getExt = function(){
                FangService.ext({fangId:fangId}).then(function(response){
                    $scope.$apply(function(){
                        _this.ext= response;
                    });
                });
            };
            _this.getExt();
            /*外部描述*/
            _this.descrGet = function(){
                FangService.descr({fangId:fangId}).then(function(response){
                    $scope.$apply(function(){
                        _this.description = response;
                    });
                })
            };
            _this.descrGet();
            _this.descrUpdateInit = function(){
                angular.copy(_this.description, _this.descrUpdateInfo);
                $('#descrModel').modal('show');

            };
            _this.layoutDialogShow = function(){
                _this.currentLayout = {
                    sCounts: _this.baseInfo.sCounts,
                    tCounts: _this.baseInfo.tCounts,
                    cCounts: _this.baseInfo.cCounts,
                    wCounts: _this.baseInfo.wCounts,
                    ytCounts: _this.baseInfo.ytCounts
                };
            };
            _this.layoutConfirm = function(){
                _this.baseInfo.sCounts = _this.currentLayout.sCounts;
                _this.baseInfo.tCounts = _this.currentLayout.tCounts;
                _this.baseInfo.cCounts = _this.currentLayout.cCounts;
                _this.baseInfo.wCounts = _this.currentLayout.wCounts;
                _this.baseInfo.ytCounts = _this.currentLayout.ytCounts;
                _this.baseInfo.layoutString =  Tools.layoutFormat({sCounts: _this.baseInfo.sCounts, tCounts: _this.baseInfo.tCounts, cCounts: _this.baseInfo.cCounts, wCounts: _this.baseInfo.wCounts, ytCounts: _this.baseInfo.ytCounts});
            };
            _this.baseInfoInit = function(){
              FangService.base({fangId:fangId}).then(function(response){
                  $('#baseModel').modal({'show':true,backdrop:'static'});
                  var info = response;
                  info.bizType = response.bizType.name;
                  info.houseSubType = response.houseSubType.name;
                  info.decorate = response.decorate.name;
                  info.orientation = response.orientation.name;
                  info.priceUnit = response.priceUnit.name;
                  info.structureType = response.structureType.name;
                  info.heatingType = response.heatingType.name;
                  info.layoutString = Tools.layoutFormat({sCounts: info.sCounts, tCounts: info.tCounts, cCounts: info.cCounts, wCounts: info.wCounts, ytCounts: info.ytCounts});
                  $scope.$apply(function(){
                      _this.baseInfo = info;
                  });
                  $('#houseSubType').selectpicker('refresh');
                  $('#houseOrientation').selectpicker('refresh');
                  $('#houseDecorate').selectpicker('refresh');
                  $('#houseStructureType').selectpicker('refresh');
                  $('#priceUnit').selectpicker('refresh');
                  $('#houseHasElevator').selectpicker('refresh');
                  $('#houseHeatingType').selectpicker('refresh');
              });
            };
            _this.success = function(){
                swal({
                    title: "操作成功!",
                    type: "success",
                    confirmButtonText: "确定",
                    confirmButtonColor: "#3c8dbc"
                });
            }
            _this.descrUpdate = function(){
                FangService.updateDescr(_this.descrUpdateInfo).then(function(response){
                    $('#descrModel').modal('hide');
                    _this.descrGet();
                    _this.success();
                }).fail(function(res){
                    swal({
                        title: "错误!",
                        text: res["message"],
                        type: "error",
                        confirmButtonText: "确定",
                        confirmButtonColor: "#3c8dbc"
                    });
                });
            };
            _this.subTypeRefresh = function(id){
                $(id).selectpicker('refresh')
            };
            _this.datePickChange = function(key, value){
                $scope.$apply(function(){
                    _this.baseInfo[key] = value;
                });
            };
            _this.baseInfoUpdate = function(){
                var info = _this.baseInfo;
                info.fangId = info.id;
                delete info.id;
                delete info.process;
                delete info.floorType;
                delete info.layoutString;
                delete info.houseType;
                FangService.baseChange(info).then(function(response){
                    $('#baseModel').modal('hide');
                    _this.success();
                });
            };
        }
        InfoCtrl.$inject = ['$scope', '$timeout', '$interval', '$window'];
        module.controller("InfoCtrl", InfoCtrl);

        angular.element(document).ready(function() {
            angular.bootstrap(document.getElementById("detailInfo"),["InfoModule"])
        });
    });
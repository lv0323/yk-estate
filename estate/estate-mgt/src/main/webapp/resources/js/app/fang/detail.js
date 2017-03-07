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
    function (mainApp,  IdentityService, FangService, ValidationService, XiaoquService, UtilService, Tools, pagingPlugin) {
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
            var pageConfig ={
                fellowPage: {
                    limit: 8,
                    offset: 0,
                    dataTotal: 0,
                    currentPage: 1,
                    init: false,
                    id:'#fellow_paging',
                    change:'follow'
                },
                surveyPage: {
                    limit: 8,
                    offset: 0,
                    dataTotal: 0,
                    currentPage: 1,
                    init: false,
                    id:'#survey_paging',
                    change:'survey'
                }
            };
            /*页面相关内容*/
            _this.page ={
                name: "房源详情",
                warn: {
                    title:'提示',
                    content:'请输入完整信息'
                },
                layoutString:'',
                now : new Date().getTime()
            };

            _this.summary = {};
            _this.baseInfo = {};
            _this.ext = {};
            _this.updateExt = {};
            _this.description = {};
            _this.descrUpdateInfo = {};
            _this.subTypeList = [];
            _this.followList =[];
            _this.surveyList =[];
            _this.newFollow = {};
            _this.newSurvey= {};
            /*户型选择*/
            _this.layoutList={
                shi:[{value:1,name:'1'}, {value:2,name:'2'}, {value:3,name:'3'}, {value:4,name:'4'}, {value:5,name:'5'}, {value:6,name:'6'},{value:7,name:'7'},{value:0,name:'无'}],
                ting:[{value:1,name:'1'}, {value:2,name:'2'}, {value:3,name:'3'}, {value:4,name:'4'}, {value:5,name:'5'}, {value:6,name:'6'},{value:7,name:'7'},{value:0,name:'无'}],
                chu:[{value:1,name:'1'}, {value:2,name:'2'}, {value:3,name:'3'}, {value:4,name:'4'}, {value:5,name:'5'}, {value:6,name:'6'},{value:7,name:'7'},{value:0,name:'无'}],
                wei:[{value:1,name:'1'}, {value:2,name:'2'}, {value:3,name:'3'}, {value:4,name:'4'}, {value:5,name:'5'}, {value:6,name:'6'},{value:7,name:'7'},{value:0,name:'无'}],
                yangtai:[{value:1,name:'1'}, {value:2,name:'2'}, {value:3,name:'3'}, {value:4,name:'4'}, {value:5,name:'5'}, {value:6,name:'6'},{value:7,name:'7'},{value:0,name:'无'}]
            };
            _this.success = function(){
                swal({
                    title: "操作成功!",
                    type: "success",
                    confirmButtonText: "确定",
                    confirmButtonColor: "#3c8dbc"
                });
            }
            _this.fail = function(res) {
                swal({
                    title: "错误!",
                    text: res["message"],
                    type: "error",
                    confirmButtonText: "确定",
                    confirmButtonColor: "#3c8dbc"
                });
            };
            _this.setLayout = function(type, value){
                _this.currentLayout[type] = value;
            };
            _this.setInfoOwner = function(response){

            };
            _this.selectPickerChange = function(id, key, info) {
                if(info){
                    if(!_this[info][key]){
                        $(id).siblings('.btn-default').addClass('invalid-input');
                        return false;
                    }else{
                        $(id).siblings('.btn-default').removeClass('invalid-input');
                        return true;
                    }
                }else{
                    if(!_this.baseInfo[key]){
                        $(id).siblings('.btn-default').addClass('invalid-input');
                        return false;
                    }else{
                        $(id).siblings('.btn-default').removeClass('invalid-input');
                        return true;
                    }
                }

            };
            _this.datePickChange = function(key, value, info){
                $scope.$apply(function(){
                    if(info){
                        _this[info][key] = value;
                    }else{
                        _this.baseInfo[key] = value;
                    }

                });
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
                        _this.ext.purchaseDate = UtilService.timeStamp2Date(_this.ext.purchaseDate);
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
            _this.descrUpdate = function(){
                FangService.updateDescr(_this.descrUpdateInfo).then(function(response){
                    $('#descrModel').modal('hide');
                    _this.descrGet();
                    _this.success();
                }).fail(_this.fail);
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
            _this.subTypeRefresh = function(id){
                $(id).selectpicker('refresh')
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
            _this.extInfoInit = function(){
                $('#extModel').modal({'show':true,backdrop:'static'});
                $timeout(function(){
                    var info ={};
                    angular.copy(_this.ext, info);
                    info.level = _this.ext.level.name;
                    info.certifType = _this.ext.certifType.name;
                    info.commissionWilling = _this.ext.commissionWilling.name;
                    info.delegateType = _this.ext.delegateType.name;
                    info.propertyType = _this.ext.propertyType.name;
                    info.showing = _this.ext.showing.name;
                    info.source = _this.ext.source.name;
                    info.status = _this.ext.status.name;
                    info.address = _this.ext.certifAddress;
                    info.delegateStart = UtilService.timeStamp2Date(_this.ext.delegateStart);
                    info.delegateEnd = UtilService.timeStamp2Date(_this.ext.delegateEnd);
                    info.taxesWilling = _this.ext.taxesWilling.name;
                    info.isOnly = _this.ext.isOnly === 'Y';
                    $scope.$apply(function(){
                        _this.updateExt = info;
                    });
                    $('#houseCertifType').selectpicker('refresh');
                    $('#houseDelegateType').selectpicker('refresh');
                    $('#housePropertyType').selectpicker('refresh');
                    $('#houseCommissionWilling').selectpicker('refresh');
                    $('#houseTaxesWilling').selectpicker('refresh');
                    $('#houseShowing').selectpicker('refresh');
                    $('#houseStatus').selectpicker('refresh');
                    $('#houseSource').selectpicker('refresh');
                },30);
                _this.extInfoUpdate = function(){
                    var info = _this.updateExt;
                    info.isOnly = _this.updateExt.isOnly ? 'Y' : 'N';
                    FangService.extChange(info).then(function(response){
                        $('#extModel').modal('hide');
                        _this.getExt();
                        _this.success();
                    });
                };
            };
            /*业主信息*/
            _this.ownerInfoInit = function(){
                _this.updateOwner = {};
                $('#ownerModel').modal({'show':true,backdrop:'static'});
            }
            _this.ownerInfoUpdate = function(){
                //$('#ownerModel').modal('hide');
            };
            /*房源跟进*/
            _this.follow = function(offset){
                FangService.listFollow({fangId:fangId},{'X-PAGING':'total=true&offset='+(offset||pageConfig.fellowPage.offset)+'&limit='+ pageConfig.fellowPage.limit}).then(function(response){
                    $scope.$apply(function(){
                        pagination(response.total, 'fellowPage');
                        _this.followList= response.items.map(function(item){
                            if(item.fangTiny && item.fangTiny.publishTime){
                                item.publishedDay = Math.floor((_this.page.now - item.fangTiny.publishTime)/(24 * 3600 * 1000));
                            }
                            return item
                        });
                    })
                });
            }
            _this.follow();
            _this.newFollowInit = function(){
                _this.newFollow = {};
                $('#followModel').modal({'show':true,backdrop:'static'});
            };
            _this.followCreate = function(){
                var info ={};
                angular.copy(_this.newFollow, info);
                info.fangId = fangId;
                FangService.createFollow(info).then(function(response){
                    $('#followModel').modal('hide');
                    _this.follow();
                    _this.success();
                }).fail(_this.fail);
            };
            /*end 房源跟进*/

            /*勘察*/
            _this.survey = function(offset){
                FangService.checkList({fangId:fangId},{'X-PAGING':'total=true&offset='+(offset||pageConfig.surveyPage.offset)+'&limit='+ pageConfig.surveyPage.limit}).then(function(response){
                    $scope.$apply(function(){
                        pagination(response.total, 'surveyPage');
                        _this.surveyList= response.items.map(function(item){
                            if(item.fangTiny && item.fangTiny.publishTime){
                                item.publishedDay = Math.floor((_this.page.now - item.fangTiny.publishTime)/(24 * 3600 * 1000));
                            }
                            return item
                        });
                    })
                });
            };
            _this.survey();
            _this.newSurveyInit = function(){
                _this.newSurvey= {};
                $('#surveyModel').modal({'show':true,backdrop:'static'});
            };
            _this.surveyCreate = function(){
                if(!_this.newSurvey.advantage){
                    return;
                }
                var info ={};
                angular.copy(_this.newSurvey, info);
                info.fangId = fangId;
                FangService.checkCreate(info).then(function(response){
                    $('#surveyModel').modal('hide');
                    _this.survey();
                    _this.success();
                }).fail(_this.fail);
            }
            /*分页*/
            var pagination = function(dataTotal, pageName) {
                var page = pageConfig[pageName];
                if(page.init){
                    pagingPlugin.update(page.id, {
                        totalCounts:dataTotal,
                        currentPage:page.currentPage
                    });
                    return;
                };
                pageConfig[pageName].init = true;
                var config = {
                    pagingId: page.id,
                    totalCounts:dataTotal,
                    pageSize: page.limit,
                    onChange: function (num, type) {
                        if(type === 'init'){
                            return;
                        }
                        pageConfig[pageName].currentPage = num;
                        _this[page.change]((num-1)*pageConfig[pageName].limit, num);
                    }
                };
                pagingPlugin.init(config);

            };
        }
        InfoCtrl.$inject = ['$scope', '$timeout', '$interval', '$window'];
        module.controller("InfoCtrl", InfoCtrl);

        angular.element(document).ready(function() {
            angular.bootstrap(document.getElementById("detailInfo"),["InfoModule"])
        });
    });
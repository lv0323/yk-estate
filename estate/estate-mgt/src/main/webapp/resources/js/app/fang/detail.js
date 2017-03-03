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
            _this.ext = {};
            _this.description = {};
            _this.descrUpdateInfo = {};
            _this.setInfoOwner = function(response){

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
            }
            _this.descrUpdate = function(){
                FangService.updateDescr(_this.descrUpdateInfo).then(function(response){
                    $('#descrModel').modal('hide');
                    _this.descrGet();
                    swal({
                            title: "操作成功!",
                            type: "success",
                            confirmButtonText: "确定",
                            confirmButtonColor: "#3c8dbc"
                        });
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
        }
        InfoCtrl.$inject = ['$scope', '$timeout', '$interval', '$window'];
        module.controller("InfoCtrl", InfoCtrl);

        angular.element(document).ready(function() {
            angular.bootstrap(document.getElementById("detailInfo"),["InfoModule"])
        });
    });
/**
 * Created by robin on 17/2/17.
 */
require(['main-app',
        contextPath + '/js/service/identity-service.js',
        contextPath + '/js/service/fang-collect-service.js',
        contextPath + '/js/service/validation-service.js',
        contextPath + '/js/service/xiaoqu-service.js',
        contextPath + '/js/service/util-service.js',
        contextPath + '/js/app/fang/tools.js',
        contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        contextPath + '/js/directive/index.js',
        'jqPaginator', 'select', 'chosen', 'fancyboxThumbs', 'fileupload','datepicker.zh-cn', 'datetimepicker.zh-cn'],
    function (mainApp,  IdentityService, FangCollectService, ValidationService, XiaoquService, UtilService, Tools, SweetAlertHelp, pagingPlugin) {
        function ceilCheck(ceilValue, value) {
            if (ceilValue && value && parseFloat(ceilValue) < parseFloat(value)) {
                return false;
            }
            return true;

        }
        var module = angular.module('InfoModule', ['directiveYk']);

        function InfoCtrl($scope, $timeout, $interval, $window) {
            var fangPoolId = UtilService.getUrlParam('id');
            var _this = this;
            var pageConfig ={
                followPage: {
                    limit: 8,
                    offset: 0,
                    dataTotal: 0,
                    currentPage: 1,
                    init: false,
                    id:'#follow_paging',
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
            $scope.$watch("followFangTag", function(){
                if($scope.followFangTag){
                    _this.contactInfoInit();
                }
            });
            /*页面相关内容*/
            _this.page ={
                name: "房源采集",
                warn: {
                    title:'提示',
                    content:'请输入完整信息'
                },
                layoutString:'',
                now : new Date().getTime(),
                uploadTitle:'',
                uploading: false,
                userInfo : JSON.parse(localStorage.getItem('userInfo')),
                licenseId:''
            };
            _this.summary = {};
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
            _this.getDetail = function(){
                FangCollectService.detail({fangPoolId:fangPoolId}).then(function(response){
                    $scope.$apply(function(){
                        _this.summary = response;
                        _this.summary.layoutString = Tools.layoutFormat({
                            sCounts:response.sCounts,
                            tCounts:response.tCounts,
                            cCounts:response.cCounts,
                            wCounts:response.wCounts,
                            ytCounts:response.ytCounts}) ;
                        if( _this.summary.imagePath){
                            _this.imageList =  JSON.parse(_this.summary.imagePath)
                        }
                        if( _this.summary.extInfo){
                            _this.extInfo =  JSON.parse(_this.summary.extInfo);
                        }
                    });

                });
            };
            _this.getDetail();

        }
        InfoCtrl.$inject = ['$scope', '$timeout', '$interval', '$window'];
        module.controller("InfoCtrl", InfoCtrl);

        angular.element(document).ready(function() {
            angular.bootstrap(document.getElementById("detailInfo"),["InfoModule"])
        });
    });
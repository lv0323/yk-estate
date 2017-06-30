require(['main-app',
    contextPath + '/js/service/xiaoqu-service.js',
    contextPath+'/js/service/department-service.js',
    contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js',
    contextPath + '/js/directive/index.js','angularMap',
    'select','chosen'], function (mainApp, XiaoquService, DepartmentService, SweetAlertHelp) {

    var config = {
        departCid: {
            init: false
        },
        departDid: {
            init: false
        },
        departSDid: {
            init: false
        }
    };

    var DictAddModule = angular.module('DictAddModule', ['directiveYk', 'angular-baidu-map']);
    DictAddModule.controller('DictAddCtrl',  ['$scope','$timeout', '$q', '$interval', '$window', '$location', function ($scope, $timeout, $q, $interval, $window){
        $scope.data = {
            name:'',
            cityId: null,
            districtId:null,
            subDistrictId: null,
            latitude:null,
            longitude:null,
            address:''
        };

        $scope.location = {
            cityList:[{name:'',id:''}],
            districtList:[{name:'',id:''}],
            subDistrictList:[{name:'',id:''}]
        };

        $scope.mapConfig = {
            baiduMap : null,
            defaultAddr: ''
        };

        $scope.getCity = function () {
            DepartmentService.getCity({}).done(function (data) {
                $scope.$apply(function() {
                    $scope.location.cityList = data.map(function (item) {
                        return {
                            id: item.id,
                            name: item.name
                        }
                    });
                });
            }).fail(function () {
                $scope.location.cityList =[{name:'',id:''}];
            });

        };
        $scope.getCity();

        $scope.getDistrict = function (cityId) {
            DepartmentService.getDistrict({data:{id:cityId}},{}).done(function (data) {
                $scope.$apply(function() {
                    $scope.location.districtList = data.map(function (item) {
                        return {
                            id: item.id,
                            name: item.name
                        }
                    });
                });
            }).fail(function () {
                $scope.location.districtList =[{name:'',id:''}];
            });
        };

        $scope.getSubDistrict = function (districtId) {
            DepartmentService.getSubDistrict({data:{id:districtId}},{}).done(function (data) {
                $scope.$apply(function() {
                    $scope.location.subDistrictList = data.map(function (item) {
                        return {
                            id: item.id,
                            name: item.name
                        }
                    });
                });
            }).fail(function () {
                $scope.location.subDistrictList =[{name:'',id:''}];
            });
        };

        $scope.chosenChange  = function (id, key, value) {
            if(key === 'departCid'){
                $scope.getDistrict(value);
                $scope.mapConfig.defaultAddr = $('#departCid_chosen .chosen-single span').text();
            }else if(key === 'departDid'){
                $scope.getSubDistrict(value);
                $scope.mapConfig.defaultAddr = $('#departCid_chosen .chosen-single span').text() + $('#departDid_chosen .chosen-single span').text();
            }else if(key === 'departSDid'){
                $scope.mapConfig.defaultAddr = $('#departCid_chosen .chosen-single span').text() + $('#departDid_chosen .chosen-single span').text() + $('#departSDid_chosen .chosen-single span').text();
            }
        };

        $scope.initChosen = function (id, key) {
            $(id).chosen("destroy");
            if(!config[key].init){
                config[key].init = !config[key].init;
                $(id).chosen().change(function(e, result){
                    $scope.$apply(function(){
                        $scope.chosenChange(id, key, result.selected);
                    });
                });
                return;
            }
            $(id).chosen();
            $(id).trigger('chosen:updated');
        };



        //initial map when baiduMap firstly loaded
        $scope.mapReady = function (map) {
            $scope.mapConfig.baiduMap = map;
            var address = ($scope.mapConfig.defaultAddr!=='')?$scope.mapConfig.defaultAddr:'北京市';
            $scope.getCoordinates(address).done(function (point) {
                if(point){
                    $scope.mapConfig.baiduMap.enableScrollWheelZoom();
                    $scope.mapConfig.baiduMap.addControl(new BMap.NavigationControl());
                    $scope.mapConfig.baiduMap.addControl(new BMap.ScaleControl());
                    $scope.mapConfig.baiduMap.addControl(new BMap.OverviewMapControl());
                    $scope.mapConfig.baiduMap.addControl(new BMap.MapTypeControl());
                    $scope.mapConfig.baiduMap.centerAndZoom(point, 12);
                }
            }).fail(function () {
                SweetAlertHelp.fail({message:"获取城市失败"});
            });
        };

        //trigger when location chosen changes
        $scope.recenterMap = function (address) {
            if($scope.mapConfig.baiduMap === null){
                return;
            }
            $scope.getCoordinates(address).done(function (point) {
                if(point){
                    $scope.mapConfig.baiduMap.centerAndZoom(point, 12);
                }else{
                    SweetAlertHelp.fail({message:"获取城市失败"});
                }
            });
        };

        var watch = $scope.$watch('mapConfig.defaultAddr',function(newValue,oldValue, $scope){
            $scope.recenterMap(newValue);
        });

        $scope.getCoordinates = function (address) {
            var defer = $.Deferred();
            var myGeo = new BMap.Geocoder();
            myGeo.getPoint(address,function (point) {
                defer.resolve(point);
            },$scope.mapConfig.defaultAddr);

            return defer.promise();
        };

        //get and deliver geographic point
        $scope.checkAddress = function (address) {
            var defer = $.Deferred();
            $scope.mapConfig.baiduMap.clearOverlays();
            if(address) {
                $('#communityAddress').removeClass('invalid-input');
                $scope.getCoordinates(address).done(function (point) {
                    if (point) {
                        $scope.mapConfig.baiduMap.centerAndZoom(point, 15);
                        $scope.mapConfig.baiduMap.addOverlay(new BMap.Marker(point));
                        defer.resolve(point);
                    }else {
                        $('#communityAddress').addClass('invalid-input');
                        SweetAlertHelp.fail({message: "该地址没有解析到结果, 请重新输入"});
                    }
                });
            }else {
                $('#communityAddress').addClass('invalid-input');
                SweetAlertHelp.fail({message:"详细地址不能为空"});
            }
            return defer.promise();
        };

        function verifyAddCommunityInput(data) {
            var flag = true;
            $('.form-group').find('.invalid-input').removeClass('invalid-input');
            if (data.name === "" || typeof(data.name) === 'undefined') {
                flag = false;
                $('#communityName').addClass('invalid-input');
            }
            if (data.cityId === "" || typeof(data.cityId) === 'undefined') {
                flag = false;
                $('#departCid_chosen').addClass('invalid-input');
            }
            if (data.districtId === "" || typeof(data.districtId) === 'undefined') {
                flag = false;
                $('#departDid_chosen').addClass('invalid-input');
            }
            if (data.subDistrictId === "" || typeof(data.subDistrictId) === 'undefined') {
                flag = false;
                $('#departSDid_chosen').addClass('invalid-input');
            }
            if (data.address === "" || typeof(data.address) === 'undefined') {
                flag = false;
                $('#communityAddress').addClass('invalid-input');
            }
            return flag;
        }

        $scope.confirmAddDepart = function(){
            $scope.data.cityId = $('#departCid option:selected').val();
            $scope.data.districtId = $('#departDid option:selected').val();
            $scope.data.subDistrictId = $('#departSDid option:selected').val();

            if(verifyAddCommunityInput($scope.data)){
                $scope.checkAddress($scope.data.address).done(function (point) {
                    $scope.data.latitude = point.lat;
                    $scope.data.longitude = point.lng;

                    XiaoquService.createXiaoqu($scope.data).then(function(response){

                        SweetAlertHelp.success({}, function() {
                            $window.location.href='/mgt/system/estateDictionary/detail?id='+ response.id;
                        });

                    }).fail(function(response){
                        SweetAlertHelp.fail({message:response&&response.message});
                    });

                });

            }else{
                SweetAlertHelp.fail({message:"请填写所有必填字段"});
            }


        };



    }]);

    angular.element(document).ready(function() {
        angular.bootstrap(document.getElementById("dictAddCtrlWrapper"),["DictAddModule"])
    });


});
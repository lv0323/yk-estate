/**
 * Created by robin on 17/3/2.
 */
(function (angular, factory) {
    if (typeof define === 'function' && define.amd) {
        define(contextPath+'/js/directive/index.js', [], function () {
            return factory(angular);
        });
    } else {
        return factory(angular);
    }
}(angular, function (angular) {
    function isValidPhoneNumber(value) {
        var reg = /^(13|15|17|18|14)\d{9}(~)?$/;
        return (reg.test(value));
    }
    var module = angular.module('directiveYk',[]);

    module.directive('datetimepicker', function () {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                if(attrs.format==='yyyy'){
                    $(element).datetimepicker({
                        format: 'yyyy',
                        autoclose: true,
                        startView: 4,
                        minView: 4,
                        pickerPosition: 'bottom-left',
                        forceParse: false,
                        language: 'zh-CN'
                    });
                } else{
                    $(element).datetimepicker({
                        format: 'yyyy-mm-dd',
                        weekStart: 1,
                        autoclose: true,
                        startView: 2,
                        minView: 2,
                        pickerPosition: 'bottom-left',
                        forceParse: false,
                        language: 'zh-CN'
                    });
                }
                $(element).find('input').change(function () {
                    if(scope.ctrl){
                        scope.ctrl[attrs.change](attrs.key, $(this).val(), attrs.owner);
                    }else{
                        scope[attrs.change](attrs.key, $(this).val(), attrs.owner);
                    }

                });
            }
        }
    });
    module.directive('selectPicker', ['$timeout', function ($timeout) {
        return {
            restrict: 'A',
            /*controller: AddHouseCtrl,
             controllerAs: 'ctrl',*/
            link: function (scope, element, attrs) {
                $timeout(function () {
                    $(element).selectpicker({
                        style: 'btn-default',
                        dropupAuto: false,
                        size: 8,
                    });
                }, 0);
            }
        }
    }]);
    module.directive('repeatDone',['$timeout', function($timeout){
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
    module.directive('chosenSelect', ['$timeout', function ($timeout) {
        return {
            restrict: 'A',
            /*controller: AddHouseCtrl,
             controllerAs: 'ctrl',*/
            link: function (scope, element, attrs) {
                $timeout(function () {
                    $(element).chosen("destroy");
                    $(element).chosen().change(function (e, result) {
                        scope.$apply(function () {
                            scope.ctrl.data[attrs.chosenSelect] = result.selected;
                            scope.ctrl.log();
                        });
                    });
                }, 0);
            }
        }
    }]);
    module.directive('stMobilePhone', function () {
        return {
            restrict: 'A',
            require: 'ngModel',
            link: function ($scope, elem, attrs, ctrl) {
                ctrl.$parsers.unshift(function (value) {
                    if (!value || isValidPhoneNumber(value)) {
                        ctrl.$setValidity('mobilePhone', true);
                    } else {
                        ctrl.$setValidity('mobilePhone', false);
                    }
                    return value;
                });
            }
        };
    });
    module.directive('stNumber', function () {
        return {
            restrict: 'A',
            require: 'ngModel',
            link: function ($scope, elem, attrs, ctrl) {
                ctrl.$parsers.unshift(function (value) {
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
}));

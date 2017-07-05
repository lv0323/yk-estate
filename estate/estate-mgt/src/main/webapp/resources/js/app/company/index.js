/**
 * Created by robin on 17/7/5.
 */
require(['main-app', contextPath+'/js/service/company-service.js'],
    function (mainApp, CompanyService) {

        var CompanyModule = angular.module('CompanyModule',[]);
        function CompanyInfoCtrl($scope){
            _this = this;
            _this.company = {};
            CompanyService.self().then(function(res){
                $scope.$apply(function(){
                    _this.company = res;
                });
            }).fail(function(){

            });
            
        }
        CompanyInfoCtrl.$inject = ['$scope'];
        CompanyModule.controller('CompanyInfoCtrl', CompanyInfoCtrl);

        angular.element(document).ready(function(){
            angular.bootstrap(document.getElementById('companyInfo'), ['CompanyModule']);
        })
    });
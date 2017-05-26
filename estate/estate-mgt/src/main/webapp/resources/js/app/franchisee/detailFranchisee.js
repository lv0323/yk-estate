/**
 * Created by yanghong on 5/22/17.
 */
require(['main-app',
        contextPath + '/js/service/franchisee-service.js',
        contextPath+'/js/service/city-service.js',
        contextPath + '/js/service/department-service.js',
        contextPath + '/js/service/employee-service.js',
        contextPath+'/js/service/util-service.js',
        contextPath + '/js/utils/dataTableHelp.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js',
        contextPath + '/js/directive/index.js',
        'select', 'chosen', 'datetimepicker.zh-cn'],
    function (mainApp, FranchiseeService, CityService, DepartmentService, EmployeeService, UtilService, dataTableHelp, pagingPlugin, SweetAlertHelp) {

        var DetailFranchiseeModule=angular.module('DetailFranchiseeModule',['directiveYk']);
        DetailFranchiseeModule.controller("DetailFranchiseeCtrl", ['$scope','$timeout', '$q', '$interval', '$window', '$location', function ($scope, $timeout, $q, $interval, $window) {

            var tableConfigDep ={
                init: false,
                target:null
            };

            var tableConfigEmp ={
                init: false,
                target:null
            };

            var tableConfigCtr ={
                init: false,
                target:null
            };

            var pageConfigDep = {
                limit: 8,
                init: false
            };

            var pageConfigEmp = {
                limit: 8,
                init: false
            };

            var pageConfigCtr = {
                limit: 8,
                init: false
            };


            $scope.detail ={name:'河北天水地产经纪有限公司', city:'北京'};
            var partnerId = UtilService.getUrlParam('partnerId');

            /*load Department table*/
            var displayTableDep = function (data) {
                var dataSet = data.items.map(function (item, index) {
                    if(item.primary){
                        return {
                            departName: item.name,
                            level: item.level,
                            telephone: item.telephone,
                            address: item.address
                        }
                    }else{
                        return {
                            departName: item.name,
                            level: item.level,
                            telephone: item.telephone,
                            address: item.address
                        }
                    }

                });

                if (!tableConfigDep.target) {
                    tableConfigDep.target = $('#departList').DataTable({
                        data: dataSet,
                        paging: false,
                        searching: false,
                        info: false,
                        ordering: false,
                        autoWidth: false,
                        columns: [
                            {title: "部门名称", data: 'departName', defaultContent: ""},
                            {title: "部门级别", data: 'level', defaultContent: ""},
                            {title: "部门电话", data: 'telephone', defaultContent: ""},
                            {title: "部门地址", data: 'address', defaultContent: ""}
                        ]
                    });
                } else {
                    tableConfigDep.target.clear();
                    tableConfigDep.target.rows.add(dataSet).draw();
                }
            };

            var paginationDep = function(dataTotal) {
                if(pageConfigDep.init){
                    return;
                }
                pageConfigDep.init = true;
                var config = {
                    pagingId:'#departList_paging',
                    totalCounts:dataTotal,
                    pageSize: pageConfigDep.limit,
                    onChange: function (num, type) {
                        if(type === 'init'){
                            return;
                        }
                        getDepartment((num-1)*pageConfigDep.limit, pageConfigDep.limit);
                    }
                };
                pagingPlugin.init(config);

            };

            function getDepartment(offset, limit) {
                DepartmentService.getDepartment({companyId:partnerId}, {'x-paging': 'total=true&offset='+offset+'&limit=' + limit})
                    .done(function(data){
                        displayTableDep(data);
                        paginationDep(data.total);
                    })
                    .fail(function(){
                        $('#departList>tbody').append('<tr><td colspan="4">无法获取数据</td></tr>');
                    });
            }

            getDepartment(0, pageConfigDep.limit);
            /*end load Department table*/


            /*load employee table*/
            var displayFilteredEmployee = function (data) {
                var dataSet = data.map(function (item, index) {
                    return {
                        employeeName: item.name,
                        departmentName: item.departmentName,
                        positionName: item.positionName,
                        mobile: item.mobile,
                        openContact: (item.openContact=="" || item.openContact==null)?(""):(typeof(item.openContact.split(',')[1])=='undefined')?(item.openContact):(item.openContact.split(',')[0]+'转'+item.openContact.split(',')[1])
                    }
                });

                if (!tableConfigEmp.target) {
                    tableConfigEmp.target = $('#employeeList').DataTable({
                        data: dataSet,
                        paging: false,
                        searching: false,
                        info: false,
                        ordering: false,
                        autoWidth: false,
                        columns: [
                            {title: "姓名", data: 'employeeName'},
                            {title: "所属部门", data: 'departmentName', defaultContent: ""},
                            {title: "岗位名称", data: 'positionName', defaultContent: ""},
                            {title: "手机", data: 'mobile', defaultContent: ""},
                            {title: "外网电话", data: 'openContact', defaultContent: ""}
                        ]
                    });
                } else {
                    tableConfigEmp.target.clear();
                    tableConfigEmp.target.rows.add(dataSet).draw();
                }
            };

            var paginationEmp = function(dataTotal) {
                var id = "#employeeList_paging";
                if(pageConfigEmp.init){
                    pagingPlugin.update(id, {
                        totalCounts:dataTotal,
                        currentPage:pageConfigEmp.currentPage
                    });
                    return;
                }

                pageConfigEmp.init = true;
                var config = {
                    pagingId:'#employeeList_paging',
                    totalCounts:dataTotal,
                    pageSize: pageConfigEmp.limit,
                    onChange: function (num, type) {
                        if(type === 'init'){
                            return;
                        }
                        pageConfigEmp.currentPage = num;
                        filterEmployee((num-1)*pageConfigEmp.limit, pageConfigEmp.limit, num);
                    }
                };
                pagingPlugin.init(config);

            };

            function filterEmployee(offset, limit, currentPage) {
                if(!currentPage){
                    pageConfigEmp.currentPage = 1;
                }

                EmployeeService.getEmployee({companyId:partnerId}, {'x-paging': 'total=true&offset='+offset+'&limit=' + limit})
                    .done(function (data) {
                        displayFilteredEmployee(data.items);
                        paginationEmp(data.total);
                    })
                    .fail(function(){
                        $('#employeeList>tbody').html('<tr><td colspan="5">无法获取数据</td></tr>');
                    });


            }

            filterEmployee(0, pageConfigEmp.limit);
            /*end load employee table*/

            /*load contract table*/
            /*end load contract table*/


        }]);

        angular.element(document).ready(function() {
            angular.bootstrap(document.getElementById("detailFranchiseeWrapper"),["DetailFranchiseeModule"])
        });

    });
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

            var config = {
                companyId: {
                    init: false
                },
                parentId: {
                    init: false
                },
                signatureDepId: {
                    init: false
                },
                partAId: {
                    init: false
                }
            };

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
            var pageConfigSign={
                currentPage:1,
                limit: 8,
                init: false
            };
            var pageConfigChannel={
                currentPage:1,
                limit: 8,
                init: false
            };
            var pageConfigSingle={
                currentPage:1,
                limit: 8,
                init: false
            };
            var pageConfigList={
                currentPage:1,
                limit: 8,
                init: false
            };

            /*签约操作*/
            $scope.companyList = [{name:'',id:''}];
            $scope.signatureDepList = [{name:'',id:''}];
            $scope.signatureRepList = [{name:'',id:''}];
            $scope.newSign = {};

            var partnerId = UtilService.getUrlParam('partnerId');

            function getCompany(){
                FranchiseeService.getCompany({id:partnerId}).then(function(response){
                    $scope.$apply(function(){
                        $scope.detail = response;
                    });

                });
            }
            getCompany();

            $scope.editDetail = function(){
                $scope.modifyDetail = {};
                angular.copy($scope.detail, $scope.modifyDetail);
                $scope.modifyDetail.startDate =  UtilService.timeStamp2Date($scope.modifyDetail.startDate)
                $scope.modifyDetail.endDate =  UtilService.timeStamp2Date($scope.modifyDetail.endDate)
                $('#editBasicModel').modal({'show':true,backdrop:'static'});
            };
            $scope.detailDatePickChange = function(key, value){
                $scope.$apply(function(){
                    $scope.modifyDetail[key] = value;
                    $scope.detailInfo.$dirty = true;
                });
            };
            $scope.updateDetail = function(){
                if(!$scope.detailInfo.$dirty || $scope.detailInfo.$invalid){
                    return;
                }
                var info ={
                    companyId :partnerId,
                    name:$scope.modifyDetail.name,
                    abbr:$scope.modifyDetail.abbr,
                    address:$scope.modifyDetail.address,
                    introduction:$scope.modifyDetail.introduction,
                    startDate:$scope.modifyDetail.startDate,
                    endDate:$scope.modifyDetail.endDate
                };
                FranchiseeService.updateInfo(info).then(function(){
                    SweetAlertHelp.success();
                    getCompany();
                    $('#editBasicModel').modal('hide');
                }).fail(function(res){
                    SweetAlertHelp.fail({message: res && res.message});
                })

            };
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

            function displaySigning(items){
                $scope.$apply(function(){
                    $scope.signList = items;
                })
            }
            function paginationSigning(dataTotal){
                /*分页*/
                    var id = '#sign_paging';
                    if(pageConfigSign.init){
                        pagingPlugin.update(id, {
                            totalCounts:dataTotal,
                            currentPage:pageConfigSign.currentPage
                        });
                        return;
                    };
                pageConfigSign.init = true;
                    var config = {
                        pagingId: id,
                        totalCounts: dataTotal,
                        visiblePages: 10,
                        pageSize: pageConfigSign.limit,
                        onChange: function (num, type) {
                            if(type === 'init'){
                                return;
                            }
                            pageConfigSign.currentPage = num;
                            $scope.getSignList((num-1)*pageConfigSign.limit, num);
                        }
                    };
                    pagingPlugin.init(config);
            }
            $scope.getSignList = function(offset, currentPage) {
                if(!currentPage){
                    pageConfigSign.currentPage = 1;
                }

                FranchiseeService.listSigning({companyId:partnerId}, {'x-paging': 'total=true&offset='+offset+'&limit=' + pageConfigSign.limit})
                    .done(function (data) {
                        displaySigning(data.items);
                        paginationSigning(data.total);
                    })
                    .fail(function(){
                    });
            };
            $scope.getSignList(0, pageConfigSign.limit);

            function paginationChannel(dataTotal){
                /*分页*/
                var id = '#channel_paging';
                if(pageConfigChannel.init){
                    pagingPlugin.update(id, {
                        totalCounts:dataTotal,
                        currentPage:pageConfigChannel.currentPage
                    });
                    return;
                };
                pageConfigChannel.init = true;
                var config = {
                    pagingId: id,
                    totalCounts: dataTotal,
                    visiblePages: 10,
                    pageSize: pageConfigChannel.limit,
                    onChange: function (num, type) {
                        if(type === 'init'){
                            return;
                        }
                        pageConfigChannel.currentPage = num;
                        $scope.listChannel((num-1)*pageConfigChannel.limit, num);
                    }
                };
                pagingPlugin.init(config);
            }
            $scope.listChannel =function(offset, currentPage){
                if(!currentPage){
                    pageConfigChannel.currentPage = 1;
                }
                FranchiseeService.listCompany({parentId:partnerId,companyType:'CHANNEL'},{'x-paging': 'total=true&offset='+offset+'&limit=' + pageConfigChannel.limit}).then(function(response){
                    $scope.$apply(function(){
                        $scope.channelList = response.items;
                        paginationChannel(response.total)
                    });
                }).fail(function(res){

                })
            };
            $scope.listChannel(0, 1);

            function paginationSingle(dataTotal){
                /*分页*/
                var id = '#single_paging';
                if(pageConfigSingle.init){
                    pagingPlugin.update(id, {
                        totalCounts:dataTotal,
                        currentPage:pageConfigSingle.currentPage
                    });
                    return;
                };
                pageConfigSingle.init = true;
                var config = {
                    pagingId: id,
                    totalCounts: dataTotal,
                    visiblePages: 10,
                    pageSize: pageConfigSingle.limit,
                    onChange: function (num, type) {
                        if(type === 'init'){
                            return;
                        }
                        pageConfigSingle.currentPage = num;
                        $scope.listSingle((num-1)*pageConfigSingle.limit, num);
                    }
                };
                pagingPlugin.init(config);
            }
            $scope.listSingle =function(offset, currentPage){
                if(!currentPage){
                    pageConfigSingle.currentPage = 1;
                }
                FranchiseeService.listCompany({parentId:partnerId,companyType:'SINGLE_STORE'},{'x-paging': 'total=true&offset='+offset+'&limit=' + pageConfigSingle.limit}).then(function(response){
                    $scope.$apply(function(){
                        $scope.singleList = response.items;
                        paginationSingle(response.total)
                    });
                }).fail(function(res){

                })
            };
            $scope.listSingle(0, pageConfigList.limit);

            
            $scope.createSigning = function(){
                $scope.newSign = {
                     companyId:partnerId,
                     companyName: $scope.detail.name,
                     parentId:'',
                     partAId:'',
                     years:'',
                     storeCount:'',
                     startDate:'',
                     endDate:'',
                     price:''
                 };
                $('#renewSigning').modal({'show':true,backdrop:'static'});
            };

            $scope.renewSigningDatePickChange = function(key, value){
                $scope.$apply(function(){
                    $scope.newSign[key] = value;
                    $scope.newSignForm.$dirty = true;
                });
            };

            /*签约父公司列表初始化*/
            FranchiseeService.getParentCompany().then(function (response) {
                $scope.$apply(function(){
                    $scope.companyList = response.map(function (item) {
                        return {
                            name: item.abbr || item.name,
                            id: item.id
                        }
                    });
                });
            });

            $scope.loadParentCompanyDep = function (companyId) {
                if(companyId === ''){
                    return;
                }
                FranchiseeService.getParentCompanyDep({companyId: companyId}).then(function (response) {
                    $scope.$apply(function(){
                        $scope.signatureDepList = response.map(function (item) {
                            return {
                                name: item.name,
                                id: item.id
                            }
                        });
                    });
                });

            };

            $scope.loadParentCompanyDepEmp = function (companyId, depId) {
                if(companyId === ''){
                    return;
                }
                FranchiseeService.getParentCompanyDepEmp({companyId: companyId, departmentId: depId}).then(function (response) {
                    $scope.$apply(function(){
                        $scope.signatureRepList = response.map(function (item) {
                            return {
                                name: item.name,
                                id: item.id
                            }
                        });
                    });
                });
            };


            $scope.chosenChange = function(id, key, value){
                if(key === 'parentId'){
                    $scope.loadParentCompanyDep(value);
                    $scope.loadParentCompanyDepEmp(value);
                    $scope.newSign.signatureDepId = '';
                    $scope.newSign.partAId = '';
                }
                if(key === 'signatureDepId'){
                    $scope.loadParentCompanyDepEmp($scope.newSign.parentId, $scope.newSign.signatureDepId);
                }
            };

            /*下拉框*/
            $scope.initChosen = function(id, key){
                $(id).chosen("destroy");
                if(!config[key].init){
                    config[key].init = !config[key].init;
                    $(id).chosen().change(function(e, result){
                        $scope.$apply(function(){
                            $scope.newSign[key] = result.selected;
                            $scope.chosenChange(id, key, result.selected);
                            $scope.newSignForm.$dirty = true;
                        });
                    });
                    return;
                }
                $(id).chosen();
                $(id).trigger('chosen:updated');
            };

            $scope.addNewSign = function(){
                var deferred = null
                if($scope.newSignForm.$invalid || !$scope.newSignForm.$dirty){
                    return;
                }
                if($scope.newSign.id){
                    $scope.newSign.signingId = $scope.newSign.id;
                    deferred = FranchiseeService.updateSigning($scope.newSign);
                }else{
                    deferred =FranchiseeService.renewSigning($scope.newSign);
                }
                deferred.then(function(response){
                    $('#renewSigning').modal('hide');
                    SweetAlertHelp.success();
                    $scope.getSignList(0, pageConfigSign.limit);
                }).fail(function(res){
                    SweetAlertHelp.fail({message:res&&res.message})
                });

            };

            /*删除 签约列表*/
            $scope.deleteSign = function(id){
                FranchiseeService.deleteSigning({signingId:id}).then(function(){
                    SweetAlertHelp.success();
                    $scope.getSignList(0, pageConfigSign.limit);
                }).fail(function(res){
                    SweetAlertHelp.fail({message:res && res.message})
                })
            };
            /*更新 签约列表*/
            $scope.updateSign=function(sign) {
                angular.copy(sign, $scope.newSign);
                $scope.newSign.companyName=$scope.detail.name;
                $scope.newSign.hideCompanyListDrop = true;
                delete $scope.newSign.partA;
                delete $scope.newSign.partA;
                $scope.newSign.startDate = UtilService.timeStamp2Date($scope.newSign.startDate)
                $scope.newSign.endDate = UtilService.timeStamp2Date($scope.newSign.endDate)
                $('#renewSigning').modal({'show':true,backdrop:'static'});
            };
        }]);

        angular.element(document).ready(function() {
            angular.bootstrap(document.getElementById("detailFranchiseeWrapper"),["DetailFranchiseeModule"])
        });

    });
require(['main-app', contextPath + '/js/service/department-service.js',
        contextPath + '/js/service/employee-service.js',
        contextPath + '/js/service/position-service.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        contextPath+'/js/utils/dataTableHelp.js',
        contextPath+'/js/app/org/department/departCommon.js',
        contextPath+'/js/app/org/position/positionCommon.js',
        contextPath+'/js/service/util-service.js',
        contextPath + '/js/plugins/SweetAlert/SweetAlertHelp.js',
        contextPath + '/js/directive/index.js',
    'datatables', 'select', 'zTree','datatablesBootstrap', 'datetimepicker.zh-cn', 'chosen'],
    function (mainApp, DepartmentService, EmployeeService, PositionService, pagingPlugin, dataTableHelp, DepartCommon, PositionCommon, UtilService, SweetAlertHelp) {
        var pageConfig = {
            limit: 8,
            offset: 0,
            dataTotal:0,
            currentPage:1,
            init: false,
        };
        var domainTypeConfig ={
            'CITY': 'CITY',
            'DISTRICT': "DISTRICT",
            'SUB_DISTRICT': "SUB_DISTRICT"
        };
        var searchConfig = {
            xiaoQuId: {
                init: false,
                value: '',
                timer: null
            }
        };
        /*小区列表*/
        var module = angular.module('AuthorityModule', ['directiveYk']);
        function AuthorityCtrl($scope, $timeout){
            var _this = this;
            _this.config ={
                cityList: [],
                currentCity:null,
            };
            _this.baseData = {
                department: [],
                position: []
            };

            /*chosen*/
            _this.estateGet = function(key){
                if(!key){
                    key = 'a';
                }
            };
            _this.estateGet();
            _this.initChosen = function(id, key){
                $(id).chosen("destroy");
                if(!searchConfig[key].init){
                    searchConfig[key].init = !searchConfig[key].init;
                    $(id).chosen().change(function(e, result){
                        $scope.$apply(function(){
                            _this.filterData = {xiaoQuId: result.selected}
                            _this.list();
                        });
                    });
                    return;
                }
                $(id).chosen();
                $(id).trigger('chosen:updated');
            };
            _this.chosenEstate = function(id,key){

                if(!searchConfig.xiaoQuId.init){
                    _this.initChosen(id, key);
                }else{
                    $(id).trigger('chosen:updated');
                    if( $('#houseEstate_chosen .search-field-input').val().trim() == ''){
                        $('#houseEstate_chosen .search-field-input').val(searchConfig.xiaoQuId.value);
                    }
                }
                $('#searchList').off('input','.search-field-input', _this.stateInputChange).on('input','.search-field-input', _this.stateInputChange);
            };
            _this.stateInputChange = function(e){
                $timeout.cancel(searchConfig.xiaoQuId.timer);
                searchConfig.xiaoQuId.timer = $timeout(function(){
                    searchConfig.xiaoQuId.value = $(e.target).val().trim();
                    if(searchConfig.xiaoQuId.value === ""){
                        return;
                    }
                    _this.estateGet(searchConfig.xiaoQuId.value);
                },800);
            };
            /*end chosen*/

            _this.list = function(offset, currentPage) {
                var params = _this.filterData;
                params.cityId = _this.config.cityList[_this.config.currentCity].id;
                if(!currentPage){
                    pageConfig.currentPage = 1;
                }
                XiaoquService.list(params,{'X-PAGING':'total=true&offset='+(offset||pageConfig.offset)+'&limit='+ pageConfig.limit}).then(function(response){
                    _this.xiaoquList =[];
                    $scope.$apply(function(){
                        pagination(response.total);
                        _this.xiaoquList = response.items;
                    });
                });
            };
            /* 部门员工树*/
            DepartmentService.getAllDepartment().done(function(data){
                var departmentData = data.map(function(item){
                    return {
                        name:item.name,
                        id:item.id,
                        treeId: item.id,
                        type: 'department',
                        parentId:item.parentId};
                });
                    EmployeeService.getAllEmployee().done(function(data){

                        var employeeData =data.map(function (item, index) {
                           return{
                               name: item.name,
                               id:item.id,
                               parentId: item.departmentId,
                               type: 'employee'
                           }
                        });
                        //Array.prototype.push.apply(departmentData, employeeData);
                        Array.prototype.push.apply(departmentData, employeeData);
                        _this.baseData.department = departmentData;
                        departmentTree(departmentData, 'treeId', 'parentId');
                    });

            });
            function departmentTree(data, idKey, pIdKey){
                function beforeClick(treeId, treeNode, clickFlag) {}
                function onClick(event, treeId, treeNode, clickFlag) {
                    byDepart = {
                        status:true,
                        departmentId: treeNode.id
                    };
                    filterEmployee(byDepart, 0, pageConfig.limit);
                    //console.log('点击了:'+ treeNode.id + treeNode.name);
                }
                function zTreeOnNodeCreated(event, treeId, treeNode){
                    if(treeNode.type =='employee'){
                        $('#'+treeNode.tId+'_ico').addClass('employee_ico')
                    }
                }
                var zTreeSetting = {
                    data: {
                        simpleData: {
                            enable: true
                        }
                    },
                    callback: {
                        beforeClick: beforeClick,
                        onClick: onClick,
                        onNodeCreated: zTreeOnNodeCreated
                    }
                };
                idKey && (zTreeSetting.data.simpleData.idKey = idKey);
                pIdKey && (zTreeSetting.data.simpleData.pIdKey = pIdKey);
                var zTreeObj = $.fn.zTree.init($("#departmentTree"), zTreeSetting, data);
                zTreeObj.expandAll(true);
            }
            /* end树*/
            /*岗位*/
            PositionService.getAllPosition().done(function(data){
                $scope.$apply(function(){
                    _this.baseData.position = data;
                });

            });
            /*end 岗位*/
            _this.get
        }
        AuthorityCtrl.$inject =['$scope', '$timeout'];
        module.controller("AuthorityCtrl", AuthorityCtrl);

        angular.element(document).ready(function(){
           angular.bootstrap(document.getElementById("authorityConfig"), ["AuthorityModule"])
        });

    });
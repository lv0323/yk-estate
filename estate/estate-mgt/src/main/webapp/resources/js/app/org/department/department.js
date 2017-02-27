/**
 * Created by yanghong on 1/17/17.
 */
require(['main-app',contextPath + '/js/service/department-service.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        contextPath + '/js/utils/dataTableHelp.js',
        contextPath+'/js/app/org/department/departCommon.js'],
    function (mainApp, DepartmentService, pagingPlugin, dataTableHelp, DepartCommon) {
        var header = {};

        var tableConfig ={
            init: false,
            target:null
        };

        var pageConfig = {
            limit: 8,
            init: false
        };

        var displayTable = function (data) {
            //departAllDataRaw = data.items;
            var dataSet = data.items.map(function (item, index) {
                if(item.primary){
                    return {
                        departName: {attr: {class: 'lookDepartBtn btn'}, data: {id: item.id}, text: item.name},
                        telephone: item.telephone,
                        address: item.address,
                        operation: [
                            {
                                attr: {class: 'btn editDepartBtn'},
                                data: {index: index, id: item.id},
                                text: '编辑'
                            },
                            {
                                attr: {class: 'btn reDeployDepartBtn'},
                                data: {index: index, id: item.id, pid: item.parentId, toggle: 'modal', target: '#reDeployDepartDialog'},
                                text: '调动'
                            }]
                    }
                }else{
                    return {
                        departName: {attr: {class: 'lookDepartBtn btn'}, data: {id: item.id}, text: item.name},
                        telephone: item.telephone,
                        address: item.address,
                        operation: [
                            {
                                attr: {class: 'btn editDepartBtn'},
                                data: {index: index, id: item.id},
                                text: '编辑'
                            },
                            {
                                attr: {class: 'btn reDeployDepartBtn'},
                                data: {index: index, id: item.id, pid: item.parentId, toggle: 'modal', target: '#reDeployDepartDialog'},
                                text: '调动'
                            },
                            {
                                attr: {class: 'btn delDepartBtn'},
                                data: {index: index, id: item.id, toggle: 'modal', target: '#deleteDepartDialog'},
                                text: '删除'
                            }]
                    }
                }

            });

            if (!tableConfig.target) {
                tableConfig.target = $('#departList').DataTable({
                    data: dataSet,
                    paging: false,
                    searching: false,
                    info: false,
                    ordering: false,
                    autoWidth: false,
                    columnDefs: [
                        {className: "text-right", "targets": [3]} /*添加class*/
                    ],
                    columns: [
                        {
                            title: "部门名称",
                            data: 'departName',
                            defaultContent: "",
                            "render": dataTableHelp.operationFormat()
                        },
                        {title: "部门电话", data: 'telephone', defaultContent: ""},
                        {title: "部门地址", data: 'address', defaultContent: ""},
                        {title: "操作", data: 'operation', "render": dataTableHelp.operationFormat()}
                    ]
                });
            } else {
                tableConfig.target.clear();
                tableConfig.target.rows.add(dataSet).draw();
            }
        };

        var pagination = function(dataTotal) {
            if(pageConfig.init){
                return;
            }
            pageConfig.init = true;
            var config = {
                pagingId:'#departList_paging',
                totalCounts:dataTotal,
                pageSize: pageConfig.limit,
                onChange: function (num, type) {
                    getDepartment((num-1)*pageConfig.limit, pageConfig.limit);
                }
            };
            pagingPlugin.init(config);

        };

        function getDepartment(offset, limit) {
            DepartmentService.getDepartment({'x-paging': 'total=true&offset='+offset+'&limit=' + limit})
                .done(function(data){
                    displayTable(data);
                    pagination(data.total);
                })
                .fail(function(){
                    $('#departList>tbody').append('<tr><td colspan="4">无法获取数据</td></tr>');
                });
        }

        getDepartment(0, pageConfig.limit);

        //open new window for department details
        $('#departList').on('click','.lookDepartBtn',function(e){
            var departId = $(e.target).data('id');
            window.location.href="/mgt/org/departmentDetail.ftl?"+departId;
        });

        //open new window for department details
        $('#departList').on('click','.editDepartBtn',function(e){
            var departId = $(e.target).data('id');
            window.location.href="/mgt/org/editDepartmentDetail.ftl?"+departId;
        });

        //initialize index value stored in deleteDepartDialog
        $('#departList').on('click','.delDepartBtn',function(e){
            var departId = parseInt($(e.target).data('id'),10);
            $('#delDepartId').val(departId);
        });

        //delete data according to index value where specifies id
        $('#confirmDelDepartBtn').on('click', function(){
            var departId_request = parseInt($('#delDepartId').val(),10);
            DepartmentService.deleteDepartment({data:{id:departId_request}},header)
                .done(function(){
                    location.reload(true);
                }).fail(function (res) {
                    alert(res["message"]);
            });
        });

        //initialize index value stored in reDeployDepartDialog
        $('#departList').on('click', '.reDeployDepartBtn', function(e){
            var departId = parseInt($(e.target).data('id'),10);
            var departPId = parseInt($(e.target).data('pid'),10);
            $('#reDeployDepartId').val(departId);
            DepartCommon.initDepartSelector(departPId);
        });

        //reDeploy according to index value where specifies id
        $('#confirmReDeployDepartBtn').on('click', function(){
            var departId = parseInt($('#reDeployDepartId').val(),10);
            var departPId = parseInt($('.duoji-dropdown').attr('selectedvalue'),10);
            DepartmentService.changeParent({data:{departmentId:departId,parentId: departPId}},header)
                .done(function(){
                    location.reload(true);
                })
                .fail(function (res) {
                    alert(res["message"]);
                });
        });


});


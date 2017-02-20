/**
 * Created by yanghong on 1/19/17.
 */
require(['main-app',
        contextPath + '/js/service/position-service.js',
        contextPath + '/js/plugins/pagination/pagingPlugin.js',
        contextPath + '/js/utils/dataTableHelp.js'],
    function (mainApp,PositionService,pagingPlugin, dataTableHelp) {

        var header = {};
        var positionAllDataRaw = {};

        var pageConfig = {
            limit: 8,
            init: false
        };

        var tableConfig ={
            init: false,
            target:null
        };

        var displayTable = function (data) {
            positionAllDataRaw = data.items;
            var dataSet = data.items.map(function (item, index) {
                return {
                    positionName: item.name,
                    type: item.type.label,
                    note: item.note,
                    operation: [
                        {
                            attr: {class: 'btn editPositionBtn'},
                            data: {index: index, id: item.id, toggle: 'modal', target: '#editPositionDialog'},
                            text: '编辑'
                        },
                        {
                            attr: {class: 'btn delPositionBtn'},
                            data: {index: index, id: item.id, toggle: 'modal', target: '#deletePositionDialog'},
                            text: '删除'
                        }]
                }
            });

            if (!tableConfig.target) {
                tableConfig.target = $('#positionList').DataTable({
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
                            title: "岗位名称",
                            data: 'positionName'
                        },
                        {title: "岗位性质", data: 'type', defaultContent: ""},
                        {title: "说明", data: 'note', defaultContent: ""},
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
                pagingId:'#positionList_paging',
                totalCounts:dataTotal,
                pageSize: pageConfig.limit,
                onChange: function (num, type) {
                    getPosition((num-1)*pageConfig.limit, pageConfig.limit);
                }
            };
            pagingPlugin.init(config);

        };


        function getPosition(offset, limit) {
            PositionService.getPosition({'x-paging': 'total=true&offset='+offset+'&limit=' + limit})
                .done(function(data){
                    displayTable(data);
                    pagination(data.total);
                })
                .fail(function(){
                    $('#positionList>tbody').append('<tr><td colspan="4">无法获取数据</td></tr>');
                });
        }

        getPosition(0, pageConfig.limit);

        //action for added Position
        $('#confirmAddPositionBtn').on('click', function(){
            var toAddPosition = {
                name: $('#addPositionName').val(),
                type: $('#addPositionType option:selected').val(),
                note: $('#addPositionNote').val()
            };
            PositionService.addPosition({data:toAddPosition},header)
                .done(function(){
                    location.reload(true);
                })
                .fail(function (res) {
                    alert(res["message"]);
                });
        });


        //initialize index value stored in deletePositionDialog
        $('#positionList').on('click','.delPositionBtn',function(e){
            var positionId = parseInt($(e.target).data('id'),10);
            $('#delPositionId').val(positionId);
        });

        //delete data according to index value where specifies id
        $('#confirmDelPositionBtn').on('click', function(){
            var positionId_request = parseInt($('#delPositionId').val(),10);
            PositionService.deletePosition({data:{id:positionId_request}},header)
                .done(function(){
                    location.reload(true);
                })
                .fail(function (res) {
                    alert(res["message"]);
                });
        });


        //initialize title and default value in edit Position dialog
        $('#positionList').on('click','.editPositionBtn',function(e){
            var index = $(e.target).data('index');
            var position = positionAllDataRaw[index];
            $('#editPositionId').val(position["id"]);
            $('#editPositionName').val(position["name"]);
            $('#editPositionType').val(position.type["name"]);
            $('#editPositionNote').val(position["note"]);
        });

        //action for updated Position
        $('#confirmEditPositionBtn').on('click', function(){
            var id = $('#editPositionId').val();
            var toEditPosition = {
                    id: parseInt(id,10),
                    name: $('#editPositionName').val(),
                    type: $('#editPositionType option:selected').val(),
                    note: $('#editPositionNote').val()
                };

            PositionService.editPosition({data:toEditPosition},header)
                .done(function(){
                    location.reload(true);
                })
                .fail(function (res) {
                    alert(res["message"]);
                });
        });
});
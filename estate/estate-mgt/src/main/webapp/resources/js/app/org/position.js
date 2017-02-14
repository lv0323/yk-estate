/**
 * Created by yanghong on 1/19/17.
 */
require(['main-app',contextPath + '/js/service/position-service.js',contextPath + '/js/plugins/pagination/pagingPlugin.js'],
    function (mainApp,PositionService,pagingPlugin) {

        var header = {};
        var positionAllDataRaw = {};

        var pageConfig = {
            limit: 8,
            init: false
        };

        var displayTable = function (data) {
            positionAllDataRaw = data.items;
            $('#positionList>tbody').html(data.items.map(function (positionRaw,index) {
                return '<tr>' +
                    '<td>'+positionRaw["name"]+'</td>' +
                    '<td>'+positionRaw["note"]+'</td>' +
                    '<td class="text-right"><a class="btn" id="editPositionBtn" data-index="'+index+'" data-id="'+positionRaw["id"]+'" data-toggle="modal" data-target="#editPositionDialog">编辑</a>'+
                    '<span class="opt-gap"></span>'+
                    '<a class="btn" id="delPositionBtn" data-id="'+positionRaw["id"]+'" data-toggle="modal" data-target="#deletePositionDialog">删除</a></td>'+
                    '</tr>';
            }));
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

        /*$('#positionList').DataTable({
            "paging": true,
            "lengthChange": false,
            "searching": false,
            "ordering": false,
            "info": false,
            "autoWidth": false
        });*/

        /*PositionService.getPosition(header)
            .done(function(data){
                if(data === null){
                    $('#positionList>tbody').append('<tr><td colspan="3">没有数据</td></tr>');
                }else {
                    positionAllDataRaw = data;
                    $.each(data,function (index,positionRaw) {
                        var appendHtml = '<tr>' +
                            '<td>'+positionRaw["name"]+'</td>' +
                            '<td>'+positionRaw["note"]+'</td>' +
                            '<td class="text-right"><a class="btn" id="editPositionBtn" data-index="'+index+'" data-id="'+positionRaw["id"]+'" data-toggle="modal" data-target="#editPositionDialog">编辑</a>';

                        appendHtml+='<span class="opt-gap"></span>'+
                            '<a class="btn" id="delPositionBtn" data-id="'+positionRaw["id"]+'" data-toggle="modal" data-target="#deletePositionDialog">删除</a></td>'+
                            '</tr>';

                        $('#positionList>tbody').append(appendHtml);
                    });


                    $('#positionList').DataTable({
                        "paging": true,
                        "lengthChange": false,
                        "searching": false,
                        "ordering": false,
                        "info": false,
                        "autoWidth": false
                    });
                }
            })
            .fail(function(){
                $('#positionList>tbody').append('<tr><td colspan="4">无法获取数据</td></tr>');
            });
*/

        //initialize title in add Position dialog
        $('.fadeInRight').on('click','#addPositionBtn',function(){
            $('#addPositionDialog #addPositionLabel').text('增加岗位');
        });

        //action for added Position
        $('#addPositionDialog').on('click','#confirmAddPositionBtn',function(){
            var toAddPosition = {
                name: $('#addPositionDialog #positionName').val(),
                note: $('#addPositionDialog #positionNote').val()
            };
            PositionService.addPosition({data:toAddPosition},header)
                .done(function(){
                    location.reload(true);
                });
        });


        //initialize index value stored in deletePositionDialog
        $('#positionList').on('click','#delPositionBtn',function(e){
            var positionId = parseInt($(e.target).data('id'),10);
            $('#deletePositionDialog #positionId').val(positionId);
        });

        //delete data according to index value where specifies id
        $('#deletePositionDialog').on('click','#confirmDelPositionBtn',function(){
            var positionId_request = parseInt($('#deletePositionDialog #positionId').val(),10);
            PositionService.deletePosition({data:{id:positionId_request}},header)
                .done(function(){
                    location.reload(true);
                })
                .fail(function (res) {
                    // var res = JSON.parse(data.responseText);
                    if(res["exCode"] === "HAS_EMPLOYEE"){
                        alert(res["message"]);
                    }
                });
        });


        //initialize title and default value in edit Position dialog
        $('#positionList').on('click','#editPositionBtn',function(e){
            var index = $(e.target).data('index');
            var position = positionAllDataRaw[index];
            $('#editPositionDialog #editPositionLabel').text('编辑岗位');
            $('#editPositionDialog #positionId').val(position["id"]);
            $('#editPositionDialog #positionName').val(position["name"]);
            $('#editPositionDialog #positionNote').val(position["note"]);
        });

        //action for updated Position
        $('#editPositionDialog').on('click','#confirmEditPositionBtn',function(){
            var id = $('#editPositionDialog #positionId').val();
            var toEditPosition = {
                    id: parseInt(id,10),
                    name: $('#editPositionDialog #positionName').val(),
                    note: $('#editPositionDialog #positionNote').val()
                };

            PositionService.editPosition({data:toEditPosition},header)
                .done(function(){
                    location.reload(true);
                });
        });
});
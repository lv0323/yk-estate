/**
 * Created by yanghong on 1/19/17.
 */
require(['main-app',contextPath + '/js/service/request-service.js','datatables','datatablesBootstrap'],
    function (mainApp,RequestService) {

        var BaseUrl = "/api/position/";
        var header = {'x-auth-token': 'd75b1d56-e19a-4e03-bff0-7c7c54dfc093'};
        var positionAllDataRaw = {};

        RequestService.get(BaseUrl+"query",null,header)
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

                        //if position has binded employee, disable delete button
                        // if(positionRaw.deletable){
                            appendHtml+='<span class="opt-gap"></span>'+
                                '<a class="btn" id="delPositionBtn" data-id="'+positionRaw["id"]+'" data-toggle="modal" data-target="#deletePositionDialog">删除</a></td>'+
                                '</tr>';
                        // }else{
                        //     appendHtml+='</tr>';
                        // }
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
            RequestService.post(BaseUrl+"add",toAddPosition,header)
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
            RequestService.get(BaseUrl+"delete",{id:positionId_request},header)
                .done(function(data){
                    location.reload(true);
                    console.log(data);
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
            var toAddPosition = {
                    id: parseInt(id,10),
                    name: $('#editPositionDialog #positionName').val(),
                    note: $('#editPositionDialog #positionNote').val()
                };

            RequestService.post(BaseUrl+"edit",toAddPosition,header)
                .done(function(){
                    location.reload(true);
                });
        });
});
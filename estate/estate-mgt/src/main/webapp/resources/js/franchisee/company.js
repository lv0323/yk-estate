/**
 * Created by yanghong on 1/23/17.
 */
require(['main-app',contextPath + '/js/service/request-service.js','datatables','datatablesBootstrap'],
    function (mainApp,RequestService) {
        var BaseUrl = "/api/company/";
        var header = {};

        //get data from server and display data
        RequestService.get(BaseUrl+"query",null,header)
            .done(function(data){
                if(data === null){
                    $('#companyList>tbody').append('<tr><td colspan="4">没有数据</td></tr>');
                }else {
                    var appendHtml = '';
                    $.each(data,function (index, company) {
                        appendHtml += '<tr>' +
                            '<td><a class="btn" id="lookCompanyBtn" data-id="'+company["id"]+'">'+company["name"]+'</a></td>' +
                            '<td>'+departRaw.department["telephone"]+'</td>' +
                            '<td>'+departRaw.department["address"]+'</td>' +
                            '<td class="text-right"><a class="btn" id="editDepartBtn" data-index="'+index+'" data-id="'+departRaw.department["id"]+'" data-toggle="modal" data-target="#editDepartDialog">编辑</a>';


                    });
                }
            })
            .fail(function(){
                $('#companyList>tbody').append('<tr><td colspan="4">无法获取数据</td></tr>');
            })
    });
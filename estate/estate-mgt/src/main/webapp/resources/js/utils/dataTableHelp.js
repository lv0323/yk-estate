/**
 * Created by robin on 17/2/15.
 */
'use strict';
/*
 var tableConfig ={
     init: false,
     target:null
 };

 var dataSet =data.items.map(function(item,index){
     return {
        companyName: {attr: {class: 'lookCompanyBtn btn'}, data: {id: item.id}, text: item.name},
        secretKey: item.secretKey,
        startDate: item.startDate,
        endDate: item.endDate,
        operation: [
         {attr: {class: 'btn', id: 'hehe'}, data: {id: item.id}, text: '编辑'},
         {attr: {class: 'btn'}, data: {id: item.id}, text: '续约'},
         {attr: {class: 'btn'}, data: {id: item.id}, text: '冻结'}]
     }
 });

 if(!tableConfig.target){
     tableConfig.target = $('#companyList2').DataTable( {
         data: dataSet,
         paging: false,
         searching: false,
         info: false,
         ordering: false,
         autoWidth: false,
         columnDefs: [{ className: "text-right", "targets": [ 4 ] }
    ],
     columns: [
         { title: "公司名称",data:'companyName',defaultContent: "", "render": dataTableHelp.operationFormat()},
         { title: "公司授权号" ,data:'secretKey', defaultContent: ""},
         { title: "加盟有效起始日期" ,data:'startDate', defaultContent: ""},
         { title: "加盟有效截止日期" ,data:'endDate', defaultContent: ""},
         { title: "操作", data:'operation', "render": dataTableHelp.operationFormat()}
     ]});
 }else{
     tableConfig.target.clear();
     tableConfig.target.rows.add(dataSet).draw();
 }
 */

define(contextPath+'/js/utils/dataTableHelp.js', ['main-app', 'datatablesBootstrap'],
    function (mainApp) {
        var dataTableHelp = {};
        dataTableHelp.init = function(opts){
            var defaults = {
                paging: false,
                searching: false,
                info: false,
                ordering: false,
                autoWidth: false,
            };
            var options = $.extend({}, defaults, opts);
            options.columns && options.columns.map(function(item){
                if(!item.defaultContent){
                    item.defaultContent = '';
                }
            });
            return $(options.target).DataTable(options);
        }
        dataTableHelp.operationFormat = function(){
            return function (data, type, full, meta) {
                if (!data) {
                    return '';
                }
                var content;
                var attrStr = "";
                var dataStr = "";
                if(Array.isArray(data)){
                    content = data.map(function (item, index) {
                        attrStr = "";
                        dataStr = "";
                        for (var attr in item.attr) {
                            attrStr += attr + '="' + item.attr[attr] + '" ';
                        }
                        for (var dataStrK in item.data) {
                            dataStr += 'data-' + dataStrK + '="' + item.data[dataStrK] + '" ';
                        }
                        return '<a ' + attrStr + dataStr + ' >' + item.text + '</a>';

                    });
                    return content.join('<span class="opt-gap"></span>');
                }else if(typeof data === 'object'){
                    attrStr = "";
                    dataStr = "";
                    for (var attr in data.attr) {
                        attrStr += attr + '="' + data.attr[attr] + '" ';
                    }
                    for (var dataStrK in data.data) {
                        dataStr += 'data-' + dataStrK + '="' + data.data[dataStrK] + '" ';
                    }
                    return '<a ' + attrStr + dataStr + ' >' + data.text + '</a>';
                }else{
                    return data + '';
                }

            }
        }

        return dataTableHelp;
    });
/**
 * Created by robin on 17/2/15.
 */
'use strict';

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
                        if(typeof (item.attr) == 'undefined'){
                            return item;
                        }
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
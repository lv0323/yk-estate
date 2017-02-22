/**
 * Created by robin on 17/2/10.
 */
/**
 *多级下拉框
 * param
 * opts.data:{
 *              id
 *              name
 *              parent_id
 *              level
 *          }
 */
require(['jquery'], function( $ ) {
    $.fn.extend({
        "duojiDropdown": function (opts) {
            var _this = this;
            /*默认初始化数据*/
            var defaults = {
                input: 'duoji-dropdown-input',
                listUl: 'duoji-dropdown-listUl',
                selectItem: 'duoji-dropdown-select-item',
                selectItemName: 'duoji-dropdown-item-name',
                dlClass: 'duoji-dropdown-dl',
                selectedKey:'selectedValue',
                topLevel : 1,
                currentId: null
            };

            var options = $.extend({}, defaults, opts);
            options.selectItemClass = '.' + options.selectItem;
            options.inputClass = '.' + options.input;
            options.listUlClass = '.' + options.listUl;
            $(_this).html('');
            $(_this).addClass('duoji-dropdown');
            $(_this).append('<div class="'+options.input+'">请选择</div> <ul class="'+options.listUl+'"> </ul>');
            /*多级下拉框*/
            $.each(options.data, function (index, item){
                if (item.level === options.topLevel) {
                    $(_this).find(options.listUlClass).append('<li class="' + options.selectItem + ' ' + options.selectItem + '-' + index + '" index="' + item.id + '"><span class="' + options.selectItemName + '" data-index="' + item.id + '">' + item.name + '</span><dl class="' + options.dlClass + '"></dl>')
                }
            });
            $.each(options.data, function (index, item) {
                if(options.currentId && item.id === options.currentId){
                    $(_this).find(options.inputClass).text(item.name);
                    $(_this).attr(options.selectedKey, item.id);
                }
                $(_this).find('.' + options.selectItem + '[index=' + item.parent_id + ']').find('>.' + options.dlClass).append('<li class="' + options.selectItem + ' ' + options.selectItem + '-' + index + '" index="' + item.id + '"><span class="' + options.selectItemName + '" data-index="' + item.id + '">' + item.name + '</span><dl class="' + options.dlClass + '"></dl>')
            });

            //点击后判断ul是否隐藏
            $(this).find(options.inputClass).click(function () {
                var ul = $(options.listUlClass);
                if (ul.css("display") == "none") {
                    ul.slideDown(200);
                } else {
                    ul.slideUp(200);
                }
                return false;
            });
            $(this).on('mouseover mouseout', options.selectItemClass, function (e) {
                if (event.type == "mouseover") {
                    if ($('>dl', this).find(options.selectItemClass).length > 0) {
                        $('>dl', this).show(0);
                    }
                } else if (event.type == "mouseout") {
                    $('dl', this).hide(0);
                }
            });
            $(this).on('click', options.selectItemClass, function () {
                var text = $(this).children('.' + options.selectItemName).text();
                var index = $(this).children('.' + options.selectItemName).data('index');
                $(_this).find(options.inputClass).text(text);
                $(_this).attr(options.selectedKey, index);
                $(options.listUlClass).hide();
                options.onSelect && options.onSelect();
                return false;
            });
            $('body').on('click', function () {
                $(options.listUlClass).hide();
            });
        }
    });
});
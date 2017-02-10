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
                selectedKey:'selectedValue'
            };

            var options = $.extend({}, defaults, opts);
            options.selectItemClass = '.' + options.selectItem;
            options.inputClass = '.' + options.input;
            options.ulClass = '.' + options.listUl;
            $(_this).html('');
            $(_this).addClass('duoji-dropdown');
            $(_this).append('<div class="'+options.input+'">请选择</div> <ul class="'+options.listUl+'"> </ul>');
            /*多级下拉框*/
            $.each(options.data, function (index, item) {
                if (item.level === 0) {
                    $(_this).find(options.ulClass).append('<li class="' + options.selectItem + ' ' + options.selectItem + '-' + index + '" index="' + item.id + '"><span class="' + options.selectItemName + '" data-index="' + item.id + '">' + item.name + '</span><dl class="' + options.dlClass + '"></dl>')
                } else {
                    $(_this).find('.' + options.selectItem + '[index=' + item.parent_id + ']').find('>.' + options.dlClass).append('<li class="' + options.selectItem + ' ' + options.selectItem + '-' + index + '" index="' + item.id + '"><span class="' + options.selectItemName + '" data-index="' + item.id + '">' + item.name + '</span><dl class="' + options.dlClass + '"></dl>')
                }
            });
            if(options.currentValue){
                $(_this).find(options.inputClass).text($(_this).find(options.selectItemClass+'[index='+options.currentValue+'] >.' + options.selectItemName).text());
                $(_this).find(options.inputClass).attr(options.selectedKey,options.currentValue);
                $(_this).attr(options.selectedKey,options.currentValue);
            }else{
                $(_this).find(options.inputClass).text('请选择');
                $(_this).find(options.inputClass).removeAttr(options.selectedKey);
                $(_this).removeAttr(options.selectedKey);
            }
            //点击后判断ul是否隐藏
            $(this).find(options.inputClass).click(function () {
                var ul = $(options.ulClass);
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
                $(_this).find(options.inputClass).text($(this).children('.' + options.selectItemName).text());
                $(_this).attr(options.selectedKey, $(this).children('.' + options.selectItemName).data('index'));
                $(options.ulClass).hide();
                return false;
            });
            $('body').on('click', function () {
                $(options.ulClass).hide();
            });
        }
    });
});
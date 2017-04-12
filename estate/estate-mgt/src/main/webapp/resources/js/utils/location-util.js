/**
 * Created by yanghong on 2/15/17.
 */
(/** @lends <global> */function( window, document, undefined ) {


    (function ($) {
        $.fn.extend({
            "LocationDropdown":function (data) {

                var _this = this;

                var appendOption = '';
                
                $.each(data,function (index, city) {
                    appendOption += '<option id="'+city.id+'">'+city.name+'</option>';
                });

                $(_this).html(appendOption);

            }
        });
    }(jQuery));

}(window, document));

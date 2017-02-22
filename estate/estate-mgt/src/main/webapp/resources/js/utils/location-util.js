/**
 * Created by yanghong on 2/15/17.
 */

/*

require(['jquery'], function( $ ) {

        $.fn.extend({
            "CityDropdown":function () {

                var _this = this;

                var header = {};
                var appendOption = "hihi";

                /!*DepartmentService.getCity(header)
                    .done(function (data) {
                        $.each(data,function (index, city) {
                            appendOption += '<option id="'+city.id+'">'+city.name+'</option>';
                        });
                    });*!/


                $(_this).html(appendOption);

            }
        });
 return {name:'cityUtil'}
});*/
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

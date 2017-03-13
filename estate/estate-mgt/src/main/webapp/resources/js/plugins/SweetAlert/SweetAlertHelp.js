/**
 * Created by robin on 17/3/7.
 */
define(contextPath+'/js/plugins/SweetAlert/SweetAlertHelp.js',
    ['main-app','sweetalert'],
    function (mainApp) {

        var SweetAlertHelp = {};


        SweetAlertHelp.success = function(res, callback){
            var widthDiffs = window.innerWidth - $("body").eq(0).prop('clientWidth');
            swal({
                title: (res && res.title) || "操作成功!",
                type: "success",
                text: (res && res["message"]) || '',
                confirmButtonText: (res && res.confirmButtonText) || "确定",
                confirmButtonColor: (res && res.confirmButtonColor) ||  "#3c8dbc"
            },function(){
                $("body").css({"padding-right":''});
                callback && callback()
            });
            var scrollBarWidth = widthDiffs - (window.innerWidth - $("body").eq(0).prop('clientWidth'));
            if (scrollBarWidth > 0) {
                $("body").css({"padding-right":scrollBarWidth+'px'});
            }
        }
        SweetAlertHelp.fail = function(res, callback) {
            var widthDiffs = window.innerWidth - $("body").eq(0).prop('clientWidth');
            swal({
                title: (res && res.title)|| "错误!",
                text: (res && res["message"]) || '',
                type: "error",
                confirmButtonText: res.confirmButtonText || "确定",
                confirmButtonColor: res.confirmButtonColor || "#3c8dbc"
            },function(){
                $("body").css({"padding-right":''});
                callback && callback()
            });
            var scrollBarWidth = widthDiffs - (window.innerWidth - $("body").eq(0).prop('clientWidth'));
            if (scrollBarWidth > 0) {
                $("body").css({"padding-right":scrollBarWidth+'px'});
            }
        };

        return SweetAlertHelp;
    });
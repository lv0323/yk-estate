/**
 * Created by robin on 17/3/7.
 */
define(contextPath+'/js/plugins/SweetAlert/SweetAlertHelp.js',
    ['main-app','sweetalert'],
    function (mainApp) {

        var SweetAlertHelp = {};

        SweetAlertHelp.sweetAlert = function(option, callback){
            var widthDiffs = window.innerWidth - $("body").eq(0).prop('clientWidth');
            var setting = $.extend({},  {
                title: "操作成功!",
                type: "success",
                text: '',
                confirmButtonText: "确定",
                confirmButtonColor: "#3c8dbc"
            }, option);
            swal(setting,function(){
                $("body").css({"padding-right":''});
                callback && callback()
            });
            var scrollBarWidth = widthDiffs - (window.innerWidth - $("body").eq(0).prop('clientWidth'));
            if (scrollBarWidth > 0) {
                $("body").css({"padding-right":scrollBarWidth+'px'});
            }
        };

        SweetAlertHelp.success = function(option, callback){
            option = option||{};
            option.text = option.message;
            option.type = "success";
            option.title = "操作成功!";
            SweetAlertHelp.sweetAlert(option, callback);
        };
        SweetAlertHelp.fail = function(option, callback) {
            option = option||{};
            option.text = option.message;
            option.type = "error";
            option.title = "错误!";
            SweetAlertHelp.sweetAlert(option, callback);
        };

        SweetAlertHelp.confirm = function (option, callback) {
            option = option||{};
            option.title = option.title || "确认该操作？";
            option.text = "";
            option.type = "warning";
            option.showCancelButton = true;
            option.cancelButtonText = "取消";
            option.confirmButtonColor = "#DD6B55";
            option.confirmButtonText = "确认";
            option.closeOnConfirm =  false;
            SweetAlertHelp.sweetAlert(option, callback);
        };
        SweetAlertHelp.close = function(){
            swal.close();
        };
        return SweetAlertHelp;
    });
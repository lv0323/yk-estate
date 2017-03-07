/**
 * Created by robin on 17/3/7.
 */
define(contextPath+'/js/plugins/SweetAlert/SweetAlertHelp.js',
    ['main-app','sweetalert'],
    function (mainApp) {

        var SweetAlertHelp = {};


        SweetAlertHelp.success = function(res, callback){
            swal({
                title: res.title || "操作成功!",
                type: "success",
                text: res["message"] || '',
                confirmButtonText: res.confirmButtonText || "确定",
                confirmButtonColor: res.confirmButtonColor ||  "#3c8dbc"
            },callback);
        }
        SweetAlertHelp.fail = function(res, callback) {
            swal({
                title: res.title || "错误!",
                text: res["message"] || '',
                type: "error",
                confirmButtonText: res.confirmButtonText || "确定",
                confirmButtonColor: res.confirmButtonColor || "#3c8dbc"
            },callback);
        };

        return SweetAlertHelp;
    });
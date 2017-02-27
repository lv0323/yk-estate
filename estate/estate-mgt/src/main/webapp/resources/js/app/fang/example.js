/**
 * Created by robin on 17/2/17.
 */
//获取服务器时间(跟进时间获取)
jQuery.ajax({
    url:RZ_URL + RZ_SERVERTIME,
    data:{},
    dataType:"text",
    async:true,
    type:"post",
    success:function(data){
        $('.form_followdatetime').datetimepicker({
            format: 'yyyy-mm-dd hh:ii',
            startDate:data,
            weekStart: 1,
            todayBtn:  1,
            autoclose: true,
            todayHighlight: 1,
            startView: 2,
            minView: 0,
            pickerPosition:'bottom-left',
            forceParse: false,
            language: 'zh-CN'
        });
    }
});

//日期插件
//选择年月日的       startView: 2,   minView: 2, format: 'yyyymmdd  hh:ii',
$('.form_datetime').datetimepicker({
    format: 'yyyy-mm-dd hh:ii',
    weekStart: 1,
    todayBtn:  1,
    autoclose: true,
    todayHighlight: 1,
    startView: 2,
    minView: 0,
    pickerPosition:'bottom-left',
    forceParse: false,
    language: 'zh-CN'
});
//选择年月日的       startView: 2,   minView: 2, format: 'yyyymmdd',
$('.form_date').datetimepicker({
    format: 'yyyy-mm-dd',
    weekStart: 1,
    autoclose: true,
    startView: 2,
    minView: 2,
    pickerPosition:'bottom-left',
    forceParse: false,
    language: 'zh-CN'
});
//选择年月的    startView: 3,   minView: 3, format: 'yyyymm',
$('.form_month').datetimepicker({
    format: 'yyyy-mm',
    weekStart: 1,
    autoclose: true,
    startView: 3,
    minView: 3,
    pickerPosition:'bottom-left',
    forceParse: false,
    language: 'zh-CN'
});
//选择年的     startView: 4,   minView: 4, format: 'yyyy',
$('.form_year').datetimepicker({
    format: 'yyyy',
    weekStart: 1,
    autoclose: true,
    startView: 4,
    minView: 4,
    pickerPosition:'bottom-left',
    forceParse: false,
    language: 'zh-CN'
});
//客源列表条件筛选
function selectionArea(obj){
    $(obj).addClass("actived").siblings().removeClass("actived");
    $(obj).parent().siblings(".distract-box").show();
}
function selection(obj){
    $(obj).addClass("actived").siblings().removeClass("actived");
}
//鼠标滑到列表时列表显示隐藏按钮
function showAddbtn(obj){
    $(obj).find(".btn-add").slideDown(300);
}
function hideAddbtn(obj){
    $(obj).find(".btn-add").slideUp(300);
}
//查看客源统计、查看房源统计
function Counter(obj) {
    $(obj).siblings(".countInfo").toggle();
}
//显示隐藏点评文本框
function comment(obj) {
    $(obj).parent().siblings(".comment").slideToggle();
}
function comment1(obj) {
    $(obj).parent().parent().siblings().find(".comment").slideToggle();
}
//查询条件显示隐藏
//$('.btn-collapse').click(function() {
//	if ($("#searchList").hasClass("hide")) {
//		alert();
//		$(this).html("<i class='iconfont'>&#xe65e;</i> 更多筛选");
//		$('#searchList').removeClass("hide");
//	} else {
//		alert(1);
//		$(this).html("<i class='iconfont'>&#xe649;</i> 精简筛选");
//		$('#searchList').addClass("hide");
//	}
//});

//seachToget
function collapseOpen(){
    if($(".btn-collapse").hasClass("icon-up")){
        $(".btn-collapse").addClass("icon-down").removeClass("icon-up");
        $(".btn-collapse").html("<i class='iconfont'>&#xe65e;</i> 更多筛选");
        $(".collapse-box").hide();
    }else{
        $(".btn-collapse").addClass("icon-up").removeClass("icon-down");
        $(".btn-collapse").html("<i class='iconfont'>&#xe649;</i> 精简筛选");
        $(".collapse-box").show();
    }
}
//popover-show
function popverShow(obj){
    $(obj).next(".popover").slideToggle("slow");
    $(obj).parent().parent().siblings().find(".popover").slideUp("fast");
    $(obj).next(".popover").css({
        "top":"30px",
    });
    $(".popover.bottom > .arrow").css({
        "left":"50px",
    });
}
function popverShow1(obj){
    $(obj).next(".popover").slideToggle("slow");
    $(obj).next(".popover").css({
        "top":"30px",
    });
    $(".popover.bottom > .arrow").css({
        "left":"240px",
    });
}
//title 浮动 ，切换选中
$(function(){
    $(window).scroll(function(){
        var scrolls = $(this).scrollTop();
        if(scrolls>414)
        {
            $(".contractlist .fix_title").css("position","relative");
            $(".contractlist .fix_title").css("top",scrolls-468);

        }else{
            $(".contractlist .fix_title").css("position","static");
        }
    });
});

$(function(){
    $(".contractlist > .table > tbody > tr ").click(function(){
        $(this).parent().addClass("actived").siblings().removeClass("actived");
    });
});
$(function() {
    $("[data-toggle='tooltip']").tooltip();
});
//精简显示带看记录列表
function showAll(obj) {
    $(obj).parent().siblings().find('ul.list-group li:gt(0)').slideDown(200);
    $(obj).hide().siblings().show();
}

function hideAll(obj) {
    $(obj).parent().siblings().find('ul.list-group li:gt(0)').slideUp(200);
    $(obj).hide().siblings().show();
}
//批量处理浮动定位
(function($){
    $.fn.capacityFixed = function(options) {
        var opts = $.extend({},$.fn.capacityFixed.deflunt,options);
        var FixedFun = function(element) {
            var top = opts.top;
            element.css({
                "top":top
            });
            var topnum=$(window).height()-$(".operate").height()-20;
            element.css({
                position: "fixed",
                top: topnum
            });
            $(window).scroll(function() {
                var scrolls = $(this).scrollTop();
                var scr_bottom = $("html").height() - $(window).height() - $(this).scrollTop();
                /*
                 if (scrolls > top) {

                 if (window.XMLHttpRequest) {

                 element.css({
                 position: "fixed",
                 top: topnum
                 });
                 } else {
                 element.css({
                 top: scrolls
                 });
                 }
                 }else {
                 element.css({
                 position: "static",
                 top: top
                 });
                 }*/
                var h=$(".markup").height();
                if(scr_bottom > h )
                {
                    element.css({
                        position: "fixed",
                        top: topnum
                    });
                }
                else {
                    element.css({
                        position: "static",
                        top: top
                    });
                }
            });
            element.find(".close-ico").click(function(event){
                element.remove();
                event.preventDefault();
            })
        };
        return $(this).each(function() {
            FixedFun($(this));
        });
    };


    $.fn.capacityFixed.deflunt={
        right : 0,//相对于页面宽度的右边定位
        top:0
    };
})(jQuery);

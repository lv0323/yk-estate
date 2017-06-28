requirejs.config({
    baseUrl: contextPath+'/js',
    paths: {
        'jquery': 'libs/jquery-2.1.4.min',
        'bootstrap': 'libs/bootstrap.min',
        'angular': 'libs/angular.min',
        'angular-route': 'libs/angular-route.min',
        'cryptojs.core': 'libs/cryptojs/core-min',
        'cryptojs.hmac': 'libs/cryptojs/hmac',
        'cryptojs.md5': 'libs/cryptojs/md5',
        'parsley': 'libs/parsley.min',
        'zTree': 'plugins/zTree/jquery.ztree.core.min',
        'datepicker': 'plugins/datepicker/bootstrap-datepicker',
        'datepicker.zh-cn': 'plugins/datepicker/locales/bootstrap-datepicker.zh-CN',
        'datetimepicker': 'plugins/datepicker/bootstrap-datetimepicker.min',
        'datetimepicker.zh-cn': 'plugins/datepicker/locales/bootstrap-datetimepicker.zh-CN',
        'datatables': 'plugins/datatables/jquery.dataTables.min',
        'datatablesBootstrap': 'plugins/datatables/dataTables.bootstrap.min',
        'jquery-ui/ui/widget': 'plugins/fileupload/jquery.ui.widget',
        'fancybox': 'plugins/fancybox/jquery.fancybox',
        'fancyboxThumbs': 'plugins/fancybox/jquery.fancybox-thumbs',
        'fileupload': 'plugins/fileupload/jquery.fileupload',
        'chosen': 'plugins/chosen/chosen.jquery',
        'select': 'plugins/select/bootstrap-select',
        'submenu': 'plugins/bootstrap-submenu',
        'dropdown': 'plugins/dropdown/index',
        'jqPaginator': 'plugins/pagination/jqPaginator',
        'locationUtil': 'utils/location-util',
        'adminLTE': 'app/AdminLTE/index',
        'adminLTEdemo': 'app/AdminLTE/demo',
        'Croppie': 'plugins/croppie/croppie.min',
        'sweetalert':'plugins/SweetAlert/sweetalert.min',
        'jssorSlider':'plugins/slider/jssor.slider-23.1.1.mini',
        'angularMap':'plugins/baiduMap/angular-baidu-map.min',
        'pagescroller':'plugins/pagescroller/pagescroller.min'
    },
    shim: {
        'bootstrap': ['jquery'],
        'angular': {
            deps: ['jquery'],

            exports: 'angular'
        },
        'adminLTE': ['jquery', 'bootstrap'],
        'adminLTEdemo':['jquery', 'bootstrap'],
        'cryptojs.hmac': ['cryptojs.core'],
        'cryptojs.md5':['cryptojs.core'],
        'parsley': ['jquery'],
        'fancybox':{
            deps: ['jquery'],
            exports: '$.fn.fancybox'
        },
        'fancyboxThumbs':{
            deps: ['jquery','fancybox']
        },
        'zTree':['jquery'],
        'datepicker': {
            deps: ['jquery', 'bootstrap'],
            exports: '$.fn.datepicker'
        },
        'jquery-ui/ui/widget':{
            deps: ['jquery']
        },
        'datepicker.zh-cn': ['jquery', 'datepicker'],
        'datetimepicker': { deps: ['jquery', 'bootstrap'],
            exports: '$.fn.datetimepicker'
        },
        'datetimepicker.zh-cn': ['jquery', 'datetimepicker'],
        'datatables':['jquery', 'bootstrap'],
        'datatablesBootstrap':{
            deps: ['jquery', 'bootstrap', 'datatables'],
            exports: 'datatablesBootstrap'
        },
        'chosen':['jquery'],
        'select':{
            deps: ['jquery', 'bootstrap'],
            export: 'select'
        },
        'submenu':['jquery', 'bootstrap'],
        'jqPaginator':['jquery'],
        'locationUtil':{
            deps: ['jquery'],
            exports: 'locationUtil'
        },
        'Croppie':{
            deps: ['jquery'],
            exports: 'Croppie'
        },
        'sweetalert':{
            deps: ['jquery'],
            exports: 'sweetalert'
        },
        'jssorSlider':{
            deps: ['jquery'],
            exports: 'jssorSlider'
        },
        'angularMap':{
            deps:[],
            exports :'angularMap'
        },
        'pagescroller':{
            deps: ['jquery'],
            exports: 'pagescroller'
        },
        'angular-route':{
            deps: ['angular'],
            exports: 'angular-route'
        }
    },
    waitSeconds: 0,
    urlArgs: "vn="+vn
});

define('main-app', ['jquery', 'bootstrap', 'adminLTE','adminLTEdemo'], function(){});

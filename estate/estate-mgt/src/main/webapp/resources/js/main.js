requirejs.config({
    paths: {
        jquery: contextPath+'/js/libs/jquery-2.2.0.min',
        bootstrap: contextPath+'/js/libs/bootstrap.min',
        'cryptojs.core': contextPath+'/js/libs/core-min',
        'cryptojs.md5': contextPath+'/js/libs/md5',
        'cryptojs.hmac': contextPath+'/js/libs/hmac',
        'parsley': contextPath+'/js/libs/parsley.min',
        'datepicker': contextPath+'/js/plugins/datepicker/bootstrap-datepicker',
        'datepicker.zh-cn': contextPath+'/js/plugins/datepicker/locales/bootstrap-datepicker.zh-CN',
        'datetimepicker': contextPath+'/js/libs/bootstrap-datetimepicker.min',
        'datetimepicker.zh-cn': contextPath+'/js/libs/bootstrap-datetimepicker.zh-CN',
        'datatables': contextPath+'/js/plugins/datatables/jquery.dataTables.min',
        'datatablesBootstrap': contextPath+'/js/plugins/datatables/dataTables.bootstrap.min',
        'submenu': contextPath+'/js/plugins/bootstrap-submenu',
        'dropdown': contextPath+'/js/plugins/dropdown/index',
        'jqPaginator': contextPath+'/js/plugins/pagination/jqPaginator'
    },
    shim: {
        'bootstrap': ['jquery'],
        'cryptojs.md5': {
            deps: ['cryptojs.core','cryptojs.hmac'],
            exports: 'CryptoJS'
        },
        'parsley': ['jquery'],
        'datepicker': {
            deps: ['jquery', 'bootstrap'],
            exports: '$.fn.datepicker'
        },
        'datepicker.zh-cn': ['jquery', 'datepicker'],
        'datetimepicker.zh-cn': ['jquery', 'datetimepicker'],
        'datetimepicker': ['jquery', 'bootstrap'],
        'datatables':['jquery', 'bootstrap'],
        'datatablesBootstrap':{
            deps: ['jquery', 'bootstrap', 'datatables'],
            exports: 'datatablesBootstrap'
        },
        'submenu':['jquery', 'bootstrap'],
        'jqPaginator':['jquery']
    },
    waitSeconds: 0,
    urlArgs: "vn="+vn
});

define('main-app', ['jquery', 'bootstrap'], function(){});

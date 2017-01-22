requirejs.config({
    paths: {
        jquery: contextPath+'/js/libs/jquery-2.2.0.min',
        bootstrap: contextPath+'/js/libs/bootstrap.min',
        'cryptojs.core': contextPath+'/js/libs/core-min',
        'cryptojs.md5': contextPath+'/js/libs/md5',
        'cryptojs.hmac': contextPath+'/js/libs/hmac',
        'parsley': contextPath+'/js/libs/parsley.min',
        'datepicker': contextPath+'/js/libs/bootstrap-datepicker.min',
        'datepicker.zh-cn': contextPath+'/js/libs/bootstrap-datepicker.zh-CN',
        'datetimepicker': contextPath+'/js/libs/bootstrap-datetimepicker.min',
        'datetimepicker.zh-cn': contextPath+'/js/libs/bootstrap-datetimepicker.zh-CN',
        'datatables': contextPath+'/js/plugins/datatables/jquery.dataTables.min',
        'datatablesBootstrap': contextPath+'/js/plugins/datatables/dataTables.bootstrap.min',
        'submenu': contextPath+'/js/plugins/bootstrap-submenu'
    },
    shim: {
        'bootstrap': ['jquery'],
        'cryptojs.md5': {
            deps: ['cryptojs.core','cryptojs.hmac'],
            exports: 'CryptoJS'
        },
        'parsley': ['jquery'],
        'datepicker.zh-cn': ['jquery', 'datepicker'],
        'datepicker': ['jquery', 'bootstrap'],
        'datetimepicker.zh-cn': ['jquery', 'datetimepicker'],
        'datetimepicker': ['jquery', 'bootstrap'],
        'datatables':['jquery', 'bootstrap'],
        'datatablesBootstrap':{
            deps: ['jquery', 'bootstrap', 'datatables'],
            exports: 'datatablesBootstrap'
        },
        'submenu':['jquery', 'bootstrap']
    },
    waitSeconds: 0,
    urlArgs: "vn="+vn
});

define('main-app', ['jquery', 'bootstrap'], function(){});

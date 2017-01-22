requirejs.config({
    paths: {
        jquery: contextPath+'/js/libs/jquery-2.2.0.min',
        bootstrap: contextPath+'/js/libs/bootstrap.min',
        'cryptojs.core': contextPath+'/js/libs/core-min',
        'cryptojs.sha256': contextPath+'/js/libs/sha256-min',
        'parsley': contextPath+'/js/libs/parsley.min',
        'datepicker': contextPath+'/js/libs/bootstrap-datepicker.min',
        'datepicker.zh-cn': contextPath+'/js/libs/bootstrap-datepicker.zh-CN',
        'datetimepicker': contextPath+'/js/libs/bootstrap-datetimepicker.min',
        'datetimepicker.zh-cn': contextPath+'/js/libs/bootstrap-datetimepicker.zh-CN',
        'datatables': contextPath+'/js/plugins/datatables/jquery.dataTables.min',
        'datatablesBootstrap': contextPath+'/js/plugins/datatables/dataTables.bootstrap.min'
    },
    shim: {
        'bootstrap': ['jquery'],
        'cryptojs.sha256': {
            deps: ['cryptojs.core'],
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
        }
    },
    waitSeconds: 0,
    urlArgs: "vn="+vn
});

define('main-app', ['jquery', 'bootstrap'], function(){});

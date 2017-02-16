requirejs.config({
    baseUrl: contextPath+'/js',
    paths: {
        'jquery': 'libs/jquery-2.2.0.min',
        'bootstrap': 'libs/bootstrap.min',
        'cryptojs.core': 'libs/cryptojs/core-min',
        'cryptojs.hmac': 'libs/cryptojs/hmac',
        'cryptojs.md5': 'libs/cryptojs/md5',
        'parsley': 'libs/parsley.min',
        'zTree': 'plugins/zTree/jquery.ztree.core.min',
        'datepicker': 'plugins/datepicker/bootstrap-datepicker',
        'datepicker.zh-cn': 'plugins/datepicker/locales/bootstrap-datepicker.zh-CN',
        'datetimepicker': 'libs/bootstrap-datetimepicker.min',
        'datetimepicker.zh-cn': 'libs/bootstrap-datetimepicker.zh-CN',
        'datatables': 'plugins/datatables/jquery.dataTables.min',
        'datatablesBootstrap': 'plugins/datatables/dataTables.bootstrap.min',
        'submenu': 'plugins/bootstrap-submenu',
        'dropdown': 'plugins/dropdown/index',
        'jqPaginator': 'plugins/pagination/jqPaginator'
    },
    shim: {
        'bootstrap': ['jquery'],
        'cryptojs.hmac': ['cryptojs.core'],
        'cryptojs.md5':['cryptojs.core'],
        'parsley': ['jquery'],
        'zTree':['jquery'],
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

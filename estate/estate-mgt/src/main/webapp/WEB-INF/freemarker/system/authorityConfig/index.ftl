<#include "/common/header.ftl" />
<link rel="stylesheet" href="${contextPath}/css/plugins/zTree/iconSkin.css">
<link rel="stylesheet" href="${contextPath}/css/app/system/authorityConfig/index.css">
<#include "/common/sidebar.ftl" />
<div id="authorityConfig" class="content-wrapper" ng-controller="AuthorityCtrl as ctrl">
    <section class="content-header">
        <ol class="breadcrumb">
            <li>
                <a ng-href="${contextPath}/system/estateDictionary?target=.sys">
                    <i class="fa fa-home fa-lg" aria-hidden="true"></i>
                    系统设置
                </a>
            </li>
            <li class="active">
                权限管理
            </li>
        </ol>
    </section>
    <!--Main content -->
    <section class="content">
        <!-- Small boxes (Stat box) -->
        <!-- Main row -->
        <div class="row animated fadeInRight">
            <div class="col-lg-3 col-md-3 col-sm-4">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs nav-justified">
                        <li class=""><a href="#tab-user" data-toggle="tab" aria-expanded="true"><i class="icon iconfont f18"></i> 组织机构</a></li>
                        <li class="active"><a href="#tab-post" data-toggle="tab" aria-expanded="false"><i class="icon iconfont f18"></i> 岗位权限</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane animated fadeInDown" id="tab-user">
                            <div class="zTreeDemoBackground left">
                                <ul id="departmentTree" class="ztree"></ul>
                            </div>
                        </div>
                        <div class="tab-pane animated fadeInDown active" id="tab-post">
                            <div class="zTreeDemoBackground left">
                                <input id="comnoPost" value="BSnlhD" type="hidden">
                                <ul id="positionList" class="ztree">
                                    <li ng-repeat="position in ctrl.baseData.position" ng-click="">
                                        <a id="treeDemo_1_a">
                                            <span class="button ico_docu position_ico"></span>
                                            <span class="treeDemo_1_span" ng-bind="position.name"></span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!--tab-content end-->
                </div>
                <!--nav-tabs-custom end-->
            </div>
            <div class="col-lg-9 col-md-9 col-sm-8" id="content"><div class="nav-tabs-custom no-shadow m-t-10">
                <!-- Tabs within a box -->
                <ul class="nav nav-tabs ui-sortable-handle">
                    <li class="pull-right"><a class="setting text-blue" onclick="depAuthority()"><i class="iconfont"></i>部门权限</a></li>
                    <li class="active"><a class="house" href="#house" data-toggle="tab" aria-expanded="true">房源</a></li>
                    <li class=""><a class="client" href="#client" data-toggle="tab" aria-expanded="false">客源</a></li>
                    <li class=""><a class="agreement" href="#agreement" data-toggle="tab" aria-expanded="false">合同</a></li>
                    <!--<li><a class="sale" href="#sale" data-toggle="tab">销售</a></li>
                    <li><a class="utiles" href="#utiles" data-toggle="tab">实用工具</a></li>
                    <li><a class="business" href="#business" data-toggle="tab">业务动态</a></li>
                    <li><a class="system" href="#system" data-toggle="tab">系统设置</a></li>-->
                    <li class=""><a class="clock" href="#clock" data-toggle="tab" aria-expanded="false">考勤</a></li>
                </ul>
                <div class="tab-content">
                    <!-- Morris chart - Sales -->
                    <div class="tab-pane animated fadeInDown active" id="house">
                        <form action="#" method="post" class="form-horizontal" id="fyForm">
                            <input name="uid" value="75" class="uid" id="fyUid" type="hidden">
                            <input name="type" value="fy" type="hidden">
                            <!-- BUG #9127 岗位id：注意不能和员工uid同时有值 -->
                            <input name="ptmid" value="" id="fyPtmid" type="hidden">
                            <!-- 用于区分是岗位模块查询权限还是员工查询权限操作 -->
                            <input name="operator" value="" id="fyOperator" type="hidden">
                            <div id="timeline-grid" class="gridbox gridalicious">
                                <div class="row">
                                    <div class="col-lg-6 col-md-6 col-sm-12">
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>新增权限</h4>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">新增房源：</label>
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="100101" class="" name="map['100101']" value="1" checked="" type="checkbox">
                                                        <label for="100101">新增</label>
                                                    </div>
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="100107" class="" name="map['100107']" value="1" type="checkbox">
                                                        <label for="100107">申请楼盘</label>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">新增归属人：</label>
                                                    <div class="ml80">
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100102" class="" name="map['100102']" value="1" checked="" type="checkbox">
                                                            <label for="100102">钥匙</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100103" class="" name="map['100103']" value="1" checked="" type="checkbox">
                                                            <label for="100103">独家</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100104" class="" name="map['100104']" value="1" checked="" type="checkbox">
                                                            <label for="100104">维护</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100105" class="" name="map['100105']" value="1" checked="" type="checkbox">
                                                            <label for="100105">勘察</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100106" class="" name="map['100106']" value="1" checked="" type="checkbox">
                                                            <label for="100106">其它</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>查看权限</h4>
                                                <div class="form-inline">
                                                    <label class="control-label">交易：</label>
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="100301" class="" name="map['100301']" value="1" checked="" type="checkbox">
                                                        <label for="100301">出租</label>
                                                    </div>
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="100302" class="" name="map['100302']" value="1" checked="" type="checkbox">
                                                        <label for="100302">出售</label>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">用途：</label>
                                                    <div class="ml80">
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100303" class="" name="map['100303']" value="1" checked="" type="checkbox">
                                                            <label for="100303">住宅</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100304" class="" name="map['100304']" value="1" checked="" type="checkbox">
                                                            <label for="100304">商铺</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100305" class="" name="map['100305']" value="1" checked="" type="checkbox">
                                                            <label for="100305">写字楼</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100306" class="" name="map['100306']" value="1" checked="" type="checkbox">
                                                            <label for="100306">别墅</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100307" class="" name="map['100307']" value="1" checked="" type="checkbox">
                                                            <label for="100307">公寓</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100308" class="" name="map['100308']" value="1" checked="" type="checkbox">
                                                            <label for="100308">车位</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100309" class="" name="map['100309']" value="1" checked="" type="checkbox">
                                                            <label for="100309">其他</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">公盘：</label>
                                                    <div class="col-sm-3">
                                                        <select id="100310" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['100310']">
                                                            <option value="1">无</option>
                                                            <option value="2">本人</option>
                                                            <option value="3">本部</option>
                                                            <option value="4" selected="">跨部</option>
                                                        </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="100310" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                    </div>
                                                    <div class="clearfix">
                                                        <label class="pull-left control-label">栋座/单元：</label>
                                                        <div class="col-sm-3">
                                                            <select id="100311" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['100311']">
                                                                <option value="1">无</option>
                                                                <option value="2">本人</option>
                                                                <option value="3">本部</option>
                                                                <option value="4" selected="">跨部</option>
                                                            </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="100311" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                        </div>
                                                        <label class="pull-left control-label m-t-sm">楼层/房号：</label>
                                                        <div class="col-sm-3 m-t-sm">
                                                            <select id="100312" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['100312']">
                                                                <option value="1">无</option>
                                                                <option value="2">本人</option>
                                                                <option value="3">本部</option>
                                                                <option value="4" selected="">跨部</option>
                                                            </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="100312" title="跨部" aria-expanded="false"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open" style="max-height: 325.4px; overflow: hidden; min-height: 98px;"><ul class="dropdown-menu inner" role="menu" style="max-height: 175.4px; overflow-y: auto; min-height: 98px;"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                        </div>
                                                    </div>
                                                    <div class="clearfix m-t-sm">
                                                        <label class="control-label">看业主/底价：</label>
                                                        <div class="col-sm-3">
                                                            <select id="100313" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['100313']">
                                                                <option value="1">无</option>
                                                                <option value="2">本人</option>
                                                                <option value="3">本部</option>
                                                                <option value="4" selected="">跨部</option>
                                                            </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="100313" title="跨部" aria-expanded="false"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open" style="max-height: 978.4px; overflow: hidden; min-height: 98px;"><ul class="dropdown-menu inner" role="menu" style="max-height: 828.4px; overflow-y: auto; min-height: 98px;"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">私盘：</label>
                                                    <div class="col-sm-3">
                                                        <select id="100314" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['100314']">
                                                            <option value="1">无</option>
                                                            <option value="2">本人</option>
                                                            <option value="3">本部</option>
                                                            <option value="4" selected="">跨部</option>
                                                        </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="100314" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                    </div>
                                                    <div class="clearfix">
                                                        <label class="pull-left control-label">栋座/单元：</label>
                                                        <div class="col-sm-3">
                                                            <select id="100315" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['100315']">
                                                                <option value="1">无</option>
                                                                <option value="2">本人</option>
                                                                <option value="3">本部</option>
                                                                <option value="4" selected="">跨部</option>
                                                            </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="100315" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                        </div>
                                                        <label class="pull-left control-label m-t-sm">楼层/房号：</label>
                                                        <div class="col-sm-3 m-t-sm">
                                                            <select id="100316" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['100316']">
                                                                <option value="1">无</option>
                                                                <option value="2">本人</option>
                                                                <option value="3">本部</option>
                                                                <option value="4" selected="">跨部</option>
                                                            </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="100316" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                        </div>
                                                    </div>
                                                    <div class="clearfix m-t-sm">
                                                        <label class="control-label">看业主/底价：</label>
                                                        <div class="col-sm-3">
                                                            <select id="100317" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['100317']">
                                                                <option value="1">无</option>
                                                                <option value="2">本人</option>
                                                                <option value="3">本部</option>
                                                                <option value="4" selected="">跨部</option>
                                                            </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="100317" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">特盘：</label>
                                                    <div class="col-sm-3">
                                                        <select id="100318" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['100318']">
                                                            <option value="1">无</option>
                                                            <option value="2">本人</option>
                                                            <option value="3">本部</option>
                                                            <option value="4" selected="">跨部</option>
                                                        </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="100318" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                    </div>
                                                    <div class="clearfix  m-t-sm">
                                                        <label class="pull-left control-label">栋座/单元：</label>
                                                        <div class="col-sm-3">
                                                            <select id="100319" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['100319']">
                                                                <option value="1">无</option>
                                                                <option value="2">本人</option>
                                                                <option value="3">本部</option>
                                                                <option value="4" selected="">跨部</option>
                                                            </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="100319" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                        </div>
                                                        <label class="pull-left control-label">楼层/房号：</label>
                                                        <div class="col-sm-3">
                                                            <select id="100320" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['100320']">
                                                                <option value="1">无</option>
                                                                <option value="2">本人</option>
                                                                <option value="3">本部</option>
                                                                <option value="4" selected="">跨部</option>
                                                            </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="100320" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                        </div>
                                                    </div>
                                                    <div class="clearfix m-t-sm">
                                                        <label class="control-label">看业主/底价：</label>
                                                        <div class="col-sm-3">
                                                            <select id="100321" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['100321']">
                                                                <option value="1">无</option>
                                                                <option value="2">本人</option>
                                                                <option value="3">本部</option>
                                                                <option value="4" selected="">跨部</option>
                                                            </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="100321" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">封盘：</label>
                                                    <div class="col-sm-3">
                                                        <select id="100322" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['100322']">
                                                            <option value="1">无</option>
                                                            <option value="2">本人</option>
                                                            <option value="3">本部</option>
                                                            <option value="4" selected="">跨部</option>
                                                        </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="100322" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                    </div>
                                                    <div class="clearfix">
                                                        <label class="pull-left control-label">栋座/单元：</label>
                                                        <div class="col-sm-3">
                                                            <select id="100323" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['100323']">
                                                                <option value="1">无</option>
                                                                <option value="2">本人</option>
                                                                <option value="3">本部</option>
                                                                <option value="4" selected="">跨部</option>
                                                            </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="100323" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                        </div>
                                                        <label class="pull-left control-label  m-t-sm">楼层/房号：</label>
                                                        <div class="col-sm-3 m-t-sm">
                                                            <select id="100324" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['100324']">
                                                                <option value="1">无</option>
                                                                <option value="2">本人</option>
                                                                <option value="3">本部</option>
                                                                <option value="4" selected="">跨部</option>
                                                            </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="100324" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                        </div>
                                                    </div>
                                                    <div class="clearfix m-t-sm">
                                                        <label class="control-label">看业主/底价：</label>
                                                        <div class="col-sm-3">
                                                            <select id="100325" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['100325']">
                                                                <option value="1">无</option>
                                                                <option value="2">本人</option>
                                                                <option value="3">本部</option>
                                                                <option value="4" selected="">跨部</option>
                                                            </select><div class="btn-group bootstrap-select show-menu-arrow form-control dropup"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="100325" title="跨部" aria-expanded="false"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open" style="max-height: 985.4px; overflow: hidden; min-height: 98px;"><ul class="dropdown-menu inner" role="menu" style="max-height: 835.4px; overflow-y: auto; min-height: 98px;"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">查看次数：</label>
                                                    <div class="ml80 f13">
                                                        <div>出租房源每天看业主 <input style="width:30px; text-align:center" name="map['100326']" value="200" type="text"> 次</div>
                                                        <div class="m-t-sm">出售房源每天看业主 <input style="width:30px; text-align:center" name="map['100327']" value="200" type="text"> 次</div>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="100328" class="" name="map['100328']" value="1" type="checkbox">
                                                        <label for="100328">出租看业主不必写跟进 </label>
                                                    </div>
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="100329" class="" name="map['100329']" value="1" type="checkbox">
                                                        <label for="100329">出售看业主不必写跟进 </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>删除权限</h4>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">删除归属：</label>
                                                    <div class="ml80">
                                                        <!--
                                                        <div class="checkbox checkbox-nice">
                                                            <input type="checkbox" id="100402" class="" name="map['100402']" value="1" />
                                                            <label for="100402">归属</label>
                                                        </div>
                                                        -->
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100404" class="" name="map['100404']" value="1" checked="" type="checkbox">
                                                            <label for="100404">钥匙</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100406" class="" name="map['100406']" value="1" checked="" type="checkbox">
                                                            <label for="100406">独家</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100405" class="" name="map['100405']" value="1" checked="" type="checkbox">
                                                            <label for="100405">维护</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100403" class="" name="map['100403']" value="1" checked="" type="checkbox">
                                                            <label for="100403">勘察</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100407" class="" name="map['100407']" value="1" checked="" type="checkbox">
                                                            <label for="100407">其它</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">删除房源：</label>
                                                    <div class="ml80 clearfix">
                                                        <div class="col-sm-3">
                                                            <select id="100408" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['100408']">
                                                                <option value="1">无</option>
                                                                <option value="2">本人</option>
                                                                <option value="3">本部</option>
                                                                <option value="4" selected="">跨部</option>
                                                            </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="100408" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-inline ">
                                                    <label class="pull-left control-label">删除图片：</label>
                                                    <div class="ml80">
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100409" class="" name="map['100409']" value="1" checked="" type="checkbox">
                                                            <label for="100409">户型图</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100410" class="" name="map['100410']" value="1" checked="" type="checkbox">
                                                            <label for="100410">勘察图</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100411" class="" name="map['100411']" value="1" checked="" type="checkbox">
                                                            <label for="100411">证件图</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>看房方式</h4>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">看房方式：</label>
                                                    <div class="ml80">
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100506" class="" name="map['100506']" value="1" checked="" type="checkbox">
                                                            <label for="100506">预约</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100507" class="" name="map['100507']" value="1" checked="" type="checkbox">
                                                            <label for="100507">有钥</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100505" class="" name="map['100505']" value="1" checked="" type="checkbox">
                                                            <label for="100505">借钥</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100504" class="" name="map['100504']" value="1" checked="" type="checkbox">
                                                            <label for="100504">直接</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">钥匙：</label>
                                                    <div class="ml80">
                                                        <div class="checkbox checkbox-nice">
                                                            <input type="checkbox" id="100503" class="" name="map['100503']" value="1" />
                                                            <label for="100503">新增钥匙</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input type="checkbox" id="100501" class="" name="map['100501']" value="1" />
                                                            <label for="100501">申请增加钥匙</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                -->
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-12">
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>修改权限（部分修改）</h4>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">全部修改：</label>
                                                    <div class="ml80 clearfix">
                                                        <div class="col-sm-3">
                                                            <select id="100234" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['100234']" onchange="updateHousePartSelect()">
                                                                <option value="1">无</option>
                                                                <option value="2">本人</option>
                                                                <option value="3">本部</option>
                                                                <option value="4" selected="">跨部</option>
                                                            </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="100234" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">部分修改：</label>
                                                    <div class="ml80 clearfix">
                                                        <div class="col-sm-3">
                                                            <select id="100235" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['100235']">
                                                                <option value="1" selected="selected">无</option>



                                                            </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="100235" title="无"><span class="filter-option pull-left">无</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">基本信息：</label>
                                                    <div class="ml80">
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100201" class="" name="map['100201']" value="1" checked="" type="checkbox">
                                                            <label for="100201">现状</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100202" class="" name="map['100202']" value="1" checked="" type="checkbox">
                                                            <label for="100202">家具</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100203" class="" name="map['100203']" value="1" checked="" type="checkbox">
                                                            <label for="100203">家电</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100204" class="" name="map['100204']" value="1" checked="" type="checkbox">
                                                            <label for="100204">配套</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100205" class="" name="map['100205']" value="1" checked="" type="checkbox">
                                                            <label for="100205">备注</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100236" class="" name="map['100236']" value="1" checked="" type="checkbox">
                                                            <label for="100236">价格</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">委托方式：</label>
                                                    <div class="ml80">
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100207" class="" name="map['100207']" value="1" checked="" type="checkbox">
                                                            <label for="100207">未签</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100208" class="" name="map['100208']" value="1" checked="" type="checkbox">
                                                            <label for="100208">签约</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100209" class="" name="map['100209']" value="1" checked="" type="checkbox">
                                                            <label for="100209">独家</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100210" class="" name="map['100210']" value="1" checked="" type="checkbox">
                                                            <label for="100210">限时</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100212" class="" name="map['100212']" value="1" checked="" type="checkbox">
                                                            <label for="100212">托管</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">状态：</label>
                                                    <div class="ml80">
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100213" class="" name="map['100213']" value="1" checked="" type="checkbox">
                                                            <label for="100213">有效转其他</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100214" class="" name="map['100214']" value="1" checked="" type="checkbox">
                                                            <label for="100214">有效转成交</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100215" class="" name="map['100215']" value="1" checked="" type="checkbox">
                                                            <label for="100215">成交转有效</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100216" class="" name="map['100216']" value="1" checked="" type="checkbox">
                                                            <label for="100216">有效转预定</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100217" class="" name="map['100217']" value="1" checked="" type="checkbox">
                                                            <label for="100217">预定转有效</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100218" class="" name="map['100218']" value="1" checked="" type="checkbox">
                                                            <label for="100218">其他转有效</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">房源归属：</label>
                                                    <div class="ml80">
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100220" class="" name="map['100220']" value="1" checked="" type="checkbox">
                                                            <label for="100220">钥匙</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100221" class="" name="map['100221']" value="1" checked="" type="checkbox">
                                                            <label for="100221">独家</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100223" class="" name="map['100223']" value="1" checked="" type="checkbox">
                                                            <label for="100223">维护</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100224" class="" name="map['100224']" value="1" checked="" type="checkbox">
                                                            <label for="100224">勘察</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100225" class="" name="map['100225']" value="1" checked="" type="checkbox">
                                                            <label for="100225">其他</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">业主信息：</label>
                                                    <div class="ml80">
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100226" class="" name="map['100226']" value="1" checked="" type="checkbox">
                                                            <label for="100226">新增联系人</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100227" class="" name="map['100227']" value="1" checked="" type="checkbox">
                                                            <label for="100227">编辑联系人</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100228" class="" name="map['100228']" value="1" checked="" type="checkbox">
                                                            <label for="100228">删除联系人</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">物业地址：</label>
                                                    <div class="ml80">
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100229" class="" name="map['100229']" value="1" checked="" type="checkbox">
                                                            <label for="100229">房源地址</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100233" class="" name="map['100233']" value="1" checked="" type="checkbox">
                                                            <label for="100233">证件地址</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>跟进权限</h4>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">查看：</label>
                                                    <div class="ml80">
                                                        <div class="col-sm-3">
                                                            <select id="100603" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['100603']">
                                                                <option value="1">无</option>
                                                                <option value="2">本人</option>
                                                                <option value="3">本部</option>
                                                                <option value="4" selected="">跨部</option>
                                                            </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="100603" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100601" class="" name="map['100601']" value="1" checked="" type="checkbox">
                                                            <label for="100601">新增</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100602" class="" name="map['100602']" value="1" checked="" type="checkbox">
                                                            <label for="100602">删除</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100607" class="" name="map['100607']" value="1" checked="" type="checkbox">
                                                            <label for="100607">修改本人当天跟进</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">跟进点评：</label>
                                                    <div class="ml80">
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100604" class="" name="map['100604']" value="1" checked="" type="checkbox">
                                                            <label for="100604">点评</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100605" class="" name="map['100605']" value="1" checked="" type="checkbox">
                                                            <label for="100605">查看</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100606" class="" name="map['100606']" value="1" checked="" type="checkbox">
                                                            <label for="100606">删除</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>房源勘察</h4>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">查看：</label>
                                                    <div class="ml80">
                                                        <div class="col-sm-3">
                                                            <select id="100703" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['100703']">
                                                                <option value="1" selected="">无</option>
                                                                <option value="2">本人</option>
                                                                <option value="3">本部</option>
                                                                <option value="4">跨部</option>
                                                            </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="100703" title="无"><span class="filter-option pull-left">无</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100701" class="" name="map['100701']" value="1" checked="" type="checkbox">
                                                            <label for="100701">新增勘察</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100702" class="" name="map['100702']" value="1" checked="" type="checkbox">
                                                            <label for="100702">上传图片</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">证件图：</label>
                                                    <div class="ml80">
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100704" class="" name="map['100704']" value="1" checked="" type="checkbox">
                                                            <label for="100704">新增证件</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100705" class="" name="map['100705']" value="1" checked="" type="checkbox">
                                                            <label for="100705">查看证件</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>转盘/转归属</h4>
                                                <div class="form-inline clearfix">
                                                    <label class="pull-left control-label">转盘：</label>
                                                    <div class="ml80">
                                                        <div class="col-sm-3">
                                                            <select id="100801" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['100801']">
                                                                <option value="1">无</option>
                                                                <option value="2">本人</option>
                                                                <option value="3">本部</option>
                                                                <option value="4" selected="">跨部</option>
                                                            </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="100801" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                        </div>
                                                    </div>
                                                    <label class="pull-left control-label">转盘查看：</label>
                                                    <div class="ml80">
                                                        <div class="col-sm-3">
                                                            <select id="100802" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['100802']">
                                                                <option value="1">无</option>
                                                                <option value="2">本人</option>
                                                                <option value="3">本部</option>
                                                                <option value="4" selected="">跨部</option>
                                                            </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="100802" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-inline clearfix">
                                                    <label class="pull-left control-label">转归属：</label>
                                                    <div class="ml80">
                                                        <div class="col-sm-3">
                                                            <select id="100803" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['100803']">
                                                                <option value="1" selected="">无</option>
                                                                <option value="2">本人</option>
                                                                <option value="3">本部</option>
                                                                <option value="4">跨部</option>
                                                            </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="100803" title="无"><span class="filter-option pull-left">无</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>自动转房源</h4>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">自动转房源：</label>
                                                    <div class="ml80">
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100901" class="" name="map['100901']" value="1" checked="" type="checkbox">
                                                            <label for="100901">新增</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100902" class="" name="map['100902']" value="1" checked="" type="checkbox">
                                                            <label for="100902">修改</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100903" class="" name="map['100903']" value="1" checked="" type="checkbox">
                                                            <label for="100903">删除</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100904" class="" name="map['100904']" value="1" checked="" type="checkbox">
                                                            <label for="100904">执行</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100905" class="" name="map['100905']" value="1" checked="" type="checkbox">
                                                            <label for="100905">启动/停止</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="clearfix text-center">
                                <button type="button" class="btn btn-default"><i class="iconfont"></i> 取消</button>
                                <button type="button" class="btn btn-primary" onclick="saveFy();"><i class="iconfont"></i> 保存</button>
                            </div>
                        </form>
                        </div>
                    <div class="tab-pane animated fadeInDown" id="client">
                        <form action="#" method="post" class="form-horizontal" id="kyForm">
                            <input name="uid" value="75" class="uid" id="kyUid" type="hidden">
                            <input name="type" value="ky" type="hidden">
                            <!-- BUG #9127 岗位id：注意不能和员工uid同时有值 -->
                            <input name="ptmid" value="" id="kyPtmid" type="hidden">
                            <!-- 用于区分是岗位模块查询权限还是员工查询权限操作 -->
                            <input name="operator" value="" id="kyOperator" type="hidden">
                            <div id="timeline-grid2" class="gridbox gridalicious">
                                <div class="row">
                                    <div class="col-lg-6 col-md-6 col-sm-12">
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>新增权限</h4>
                                                <div class="form-inline">
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="200101" class="" name="map['200101']" value="1" checked="" type="checkbox">
                                                        <label for="200101">新增客源</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>查看权限</h4>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">公客查看：</label>
                                                    <div class="col-sm-3">
                                                        <select id="200201" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['200201']">
                                                            <option value="1">无</option>
                                                            <option value="2">本人</option>
                                                            <option value="3">本部</option>
                                                            <option value="4" selected="">跨部</option>
                                                        </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="200201" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                    </div>
                                                    <label class="pull-left control-label text-right">私客查看：</label>
                                                    <div class="col-sm-3">
                                                        <select id="200202" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['200202']">
                                                            <option value="1">无</option>
                                                            <option value="2">本人</option>
                                                            <option value="3">本部</option>
                                                            <option value="4" selected="">跨部</option>
                                                        </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="200202" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">交易：</label>
                                                    <div class="ml80">
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200203" class="" name="map['200203']" value="1" checked="" type="checkbox">
                                                            <label for="200203">求租</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200204" class="" name="map['200204']" value="1" checked="" type="checkbox">
                                                            <label for="200204">求购</label>
                                                        </div>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">用途：</label>
                                                    <div class="ml80">
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200205" class="" name="map['200205']" value="1" checked="" type="checkbox">
                                                            <label for="200205">住宅</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200206" class="" name="map['200206']" value="1" checked="" type="checkbox">
                                                            <label for="200206">商铺</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200207" class="" name="map['200207']" value="1" checked="" type="checkbox">
                                                            <label for="200207">写字楼</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200208" class="" name="map['200208']" value="1" checked="" type="checkbox">
                                                            <label for="200208">别墅</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200209" class="" name="map['200209']" value="1" checked="" type="checkbox">
                                                            <label for="200209">公寓</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200210" class="" name="map['200210']" value="1" checked="" type="checkbox">
                                                            <label for="200210">车位</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200211" class="" name="map['200211']" value="1" checked="" type="checkbox">
                                                            <label for="200211">其他</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">查看次数：</label>
                                                    <div class="ml80 f13">
                                                        <div>求租客源可查看次数 <input style="width:30px; text-align:center" name="map['200212']" value="200" type="text"> 次</div>
                                                        <div class="m-t-sm">求购客源可查看次数 <input style="width:30px; text-align:center" name="map['200213']" value="200" type="text"> 次</div>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">看客户不写跟进：</label>
                                                    <div class="col-sm-3">
                                                        <select id="200214" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['200214']">
                                                            <option value="1">无</option>
                                                            <option value="2">本人</option>
                                                            <option value="3">本部</option>
                                                            <option value="4">跨部</option>
                                                        </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="200214" title="无"><span class="filter-option pull-left">无</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>删除权限</h4>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">删除客源：</label>
                                                    <div class="col-sm-3">
                                                        <select id="200401" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['200401']">
                                                            <option value="1">无</option>
                                                            <option value="2">本人</option>
                                                            <option value="3">本部</option>
                                                            <option value="4" selected="">跨部</option>
                                                        </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="200401" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                    </div>
                                                    <label class="pull-left control-label text-right">删除归属：</label>
                                                    <div class="col-sm-3">
                                                        <select id="200402" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['200402']">
                                                            <option value="1">无</option>
                                                            <option value="2">本人</option>
                                                            <option value="3">本部</option>
                                                            <option value="4" selected="">跨部</option>
                                                        </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="200402" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>(探索)跟进权限</h4>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">查看：</label>
                                                    <div class="ml80">
                                                        <div class="col-sm-4">
                                                            <select id="200501" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['200501']">
                                                                <option value="1">无</option>
                                                                <option value="2">本人</option>
                                                                <option value="3">本部</option>
                                                                <option value="4" selected="">跨部</option>
                                                            </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="200501" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200502" class="" name="map['200502']" value="1" checked="" type="checkbox">
                                                            <label for="200502">新增</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200503" class="" name="map['200503']" value="1" checked="" type="checkbox">
                                                            <label for="200503">删除</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200504" class="" name="map['200504']" value="1" checked="" type="checkbox">
                                                            <label for="200504">修改本人跟进</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">跟进点评：</label>
                                                    <div class="ml80">
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200505" class="" name="map['200505']" value="1" checked="" type="checkbox">
                                                            <label for="200505">点评</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200506" class="" name="map['200506']" value="1" checked="" type="checkbox">
                                                            <label for="200506">查看</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200507" class="" name="map['200507']" value="1" checked="" type="checkbox">
                                                            <label for="200507">删除</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-12">
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>修改权限</h4>
                                                <div class="form-inline">
                                                    <label class="control-label">全部修改：</label>
                                                    <div class="col-sm-4">
                                                        <select id="200301" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['200301']" onchange="updatePartSelect()">
                                                            <option value="1">无</option>
                                                            <option value="2">本人</option>
                                                            <option value="3">本部</option>
                                                            <option value="4" selected="">跨部</option>
                                                        </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="200301" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="control-label">部分修改：</label>
                                                    <div class="col-sm-4">
                                                        <select id="200320" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['200320']">
                                                            <option value="1" selected="selected">无</option>



                                                        </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="200320" title="无"><span class="filter-option pull-left">无</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">基本信息：</label>
                                                    <div class="ml80">
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200302" class="" name="map['200302']" value="1" checked="" type="checkbox">
                                                            <label for="200302">姓名电话</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">房产需求：</label>
                                                    <div class="ml80">
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200303" class="" name="map['200303']" value="1" checked="" type="checkbox">
                                                            <label for="200303">交易</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200304" class="" name="map['200304']" value="1" checked="" type="checkbox">
                                                            <label for="200304">用途</label>
                                                        </div>

                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200306" class="" name="map['200306']" value="1" checked="" type="checkbox">
                                                            <label for="200306">委托方式</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200307" class="" name="map['200307']" value="1" checked="" type="checkbox">
                                                            <label for="200307">价格</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200308" class="" name="map['200308']" value="1" checked="" type="checkbox">
                                                            <label for="200308">面积</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200309" class="" name="map['200309']" value="1" checked="" type="checkbox">
                                                            <label for="200309">楼层</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200310" class="" name="map['200310']" value="1" checked="" type="checkbox">
                                                            <label for="200310">户型</label>
                                                        </div>
                                                        <!-- BUG #9379 位置暂时不确定，先注释
                                                        <div class="checkbox checkbox-nice">
                                                            <input type="checkbox" id="200311" class="" name="map['200311']" value="1" />
                                                            <label for="200311">位置</label>
                                                        </div>
                                                        -->
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200312" class="" name="map['200312']" value="1" checked="" type="checkbox">
                                                            <label for="200312">备注</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">分类：</label>
                                                    <div class="ml80">
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200313" class="" name="map['200313']" value="1" checked="" type="checkbox">
                                                            <label for="200313">等级</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200314" class="" name="map['200314']" value="1" checked="" type="checkbox">
                                                            <label for="200314">优质客</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200315" class="" name="map['200315']" value="1" checked="" type="checkbox">
                                                            <label for="200315">公私</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">状态：</label>
                                                    <div class="ml80">
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200316" class="" name="map['200316']" value="1" checked="" type="checkbox">
                                                            <label for="200316">有效转其他</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200317" class="" name="map['200317']" value="1" checked="" type="checkbox">
                                                            <label for="200317">有效转成交</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200318" class="" name="map['200318']" value="1" checked="" type="checkbox">
                                                            <label for="200318">成交转有效</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200319" class="" name="map['200319']" value="1" checked="" type="checkbox">
                                                            <label for="200319">其他转有效</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200321" class="" name="map['200321']" value="1" type="checkbox">
                                                            <label for="200321">其他转成交</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200322" class="" name="map['200322']" value="1" type="checkbox">
                                                            <label for="200322">成交转其他</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>转客/转归属权限</h4>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">转客：</label>
                                                    <div class="col-sm-3">
                                                        <select id="200601" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['200601']">
                                                            <option value="1">无</option>
                                                            <option value="2">本人</option>
                                                            <option value="3">本部</option>
                                                            <option value="4" selected="">跨部</option>
                                                        </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="200601" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                    </div>
                                                    <label class="pull-left control-label text-right">转客查看：</label>
                                                    <div class="col-sm-3">
                                                        <select id="200602" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['200602']">
                                                            <option value="1">无</option>
                                                            <option value="2">本人</option>
                                                            <option value="3">本部</option>
                                                            <option value="4" selected="">跨部</option>
                                                        </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="200602" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">转归属：</label>
                                                    <div class="col-sm-3">
                                                        <select id="200603" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['200603']">
                                                            <option value="1">无</option>
                                                            <option value="2">本人</option>
                                                            <option value="3">本部</option>
                                                            <option value="4">跨部</option>
                                                        </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="200603" title="无"><span class="filter-option pull-left">无</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>自动转客权限</h4>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">操作：</label>
                                                    <div class="ml80">
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200701" class="" name="map['200701']" value="1" checked="" type="checkbox">
                                                            <label for="200701">新增</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200702" class="" name="map['200702']" value="1" checked="" type="checkbox">
                                                            <label for="200702">修改</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200703" class="" name="map['200703']" value="1" checked="" type="checkbox">
                                                            <label for="200703">删除</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="200704" class="" name="map['200704']" value="1" checked="" type="checkbox">
                                                            <label for="200704">执行</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="clearfix text-center">
                                <button type="button" class="btn btn-default"><i class="iconfont"></i> 取消</button>
                                <button type="button" class="btn btn-primary" onclick="saveKy();"><i class="iconfont"></i> 保存</button>
                            </div>
                        </form>    </div>
                    <div class="tab-pane animated fadeInDown" id="agreement">
                        <form action="#" method="post" class="form-horizontal" id="htForm">
                            <input name="uid" value="75" class="uid" id="htUid" type="hidden">
                            <input name="type" value="ht" type="hidden">
                            <!-- BUG #9127 岗位id：注意不能和员工uid同时有值 -->
                            <input name="ptmid" value="" id="htPtmid" type="hidden">
                            <!-- 用于区分是岗位模块查询权限还是员工查询权限操作 -->
                            <input name="operator" value="" id="htOperator" type="hidden">
                            <div id="timeline-grid3" class="gridbox gridalicious">
                                <div class="row">
                                    <div class="col-lg-6 col-md-6 col-sm-12">
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>新增权限</h4>
                                                <div class="form-inline">
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="300101" class="" name="map['300101']" value="1" checked="" type="checkbox">
                                                        <label for="300101">新增合同</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>查看权限</h4>
                                                <div class="form-inline">
                                                    <label class="control-label pull-left">查看：</label>
                                                    <div class="col-sm-4">
                                                        <select id="300201" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['300201']">
                                                            <option value="1">无</option>
                                                            <option value="2">本人</option>
                                                            <option value="3">本部</option>
                                                            <option value="4" selected="">跨部</option>
                                                        </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="300201" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="control-label pull-left">分类：</label>
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="300202" class="" name="map['300202']" value="1" checked="" type="checkbox">
                                                        <label for="300202">买卖</label>
                                                    </div>
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="300203" class="" name="map['300203']" value="1" checked="" type="checkbox">
                                                        <label for="300203">租赁</label>
                                                    </div>
                                                    <!-- BUG #9681 先删除金融和托管，此处权限也暂时注释！
                                                    <div class="checkbox checkbox-nice">
                                                        <input type="checkbox" id="300204" class="" name="map['300204']" value="1"  checked />
                                                        <label for="300204">托管</label>
                                                    </div>
                                                    <div class="checkbox checkbox-nice">
                                                        <input type="checkbox" id="300205" class="" name="map['300205']" value="1"  checked />
                                                        <label for="300205">金融</label>
                                                    </div>
                                                    -->
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">用途：</label>
                                                    <div class="ml80">
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="300206" class="" name="map['300206']" value="1" checked="" type="checkbox">
                                                            <label for="300206">住宅</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="300207" class="" name="map['300207']" value="1" checked="" type="checkbox">
                                                            <label for="300207">商铺</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="300208" class="" name="map['300208']" value="1" checked="" type="checkbox">
                                                            <label for="300208">写字楼</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="300209" class="" name="map['300209']" value="1" checked="" type="checkbox">
                                                            <label for="300209">别墅</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="300210" class="" name="map['300210']" value="1" checked="" type="checkbox">
                                                            <label for="300210">公寓</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="300211" class="" name="map['300211']" value="1" checked="" type="checkbox">
                                                            <label for="300211">车位</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="300212" class="" name="map['300212']" value="1" checked="" type="checkbox">
                                                            <label for="300212">其他</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">时间：</label>
                                                    <div class="col-sm-4">
                                                        <select id="300213" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['300213']">
                                                            <option value="1">最近30天</option>
                                                            <option value="2">最近60天</option>
                                                            <option value="3" selected="">最近90天</option>
                                                            <option value="4">不限</option>
                                                        </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="300213" title="最近90天"><span class="filter-option pull-left">最近90天</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">最近30天</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">最近60天</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">最近90天</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">不限</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">业绩：</label>
                                                    <div class="col-sm-4">
                                                        <select id="300214" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['300214']">
                                                            <option value="1" selected="">本人</option>
                                                            <option value="2">本部</option>
                                                            <option value="3">全部</option>
                                                        </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="300214" title="本人"><span class="filter-option pull-left">本人</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">全部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>收付权限</h4>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">收付查看：</label>
                                                    <div class="col-sm-4">
                                                        <select id="300401" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['300401']">
                                                            <option value="1">无</option>
                                                            <option value="2">本人</option>
                                                            <option value="3">本部</option>
                                                            <option value="4" selected="">跨部</option>
                                                        </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="300401" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="form-inline">
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="300402" class="" name="map['300402']" value="1" checked="" type="checkbox">
                                                        <label for="300402">新增应收应付</label>
                                                    </div>
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="300403" class="" name="map['300403']" value="1" checked="" type="checkbox">
                                                        <label for="300403">编辑应收应付</label>
                                                    </div>
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="300404" class="" name="map['300404']" value="1" checked="" type="checkbox">
                                                        <label for="300404">删除应收应付</label>
                                                    </div>
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="300405" class="" name="map['300405']" value="1" checked="" type="checkbox">
                                                        <label for="300405">确认应收应付</label>
                                                    </div>
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="300406" class="" name="map['300406']" value="1" checked="" type="checkbox">
                                                        <label for="300406">新增实收实付</label>
                                                    </div>
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="300407" class="" name="map['300407']" value="1" checked="" type="checkbox">
                                                        <label for="300407">编辑实收实付</label>
                                                    </div>
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="300408" class="" name="map['300408']" value="1" checked="" type="checkbox">
                                                        <label for="300408">删除实收实付</label>
                                                    </div>
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="300409" class="" name="map['300409']" value="1" checked="" type="checkbox">
                                                        <label for="300409">确认实收实付</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-12">
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>分成</h4>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">基本信息：</label>
                                                    <div class="ml80">
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="300301" class="" name="map['300301']" value="1" checked="" type="checkbox">
                                                            <label for="300301">新增</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="300302" class="" name="map['300302']" value="1" checked="" type="checkbox">
                                                            <label for="300302">编辑</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="300303" class="" name="map['300303']" value="1" checked="" type="checkbox">
                                                            <label for="300303">删除</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>附件</h4>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">权限范围：</label>
                                                    <div class="col-sm-4">
                                                        <select id="300501" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['300501']">
                                                            <option value="1">无</option>
                                                            <option value="2">本人</option>
                                                            <option value="3">本部</option>
                                                            <option value="4" selected="">跨部</option>
                                                        </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="300501" title="跨部"><span class="filter-option pull-left">跨部</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">跨部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="form-inline">
                                                    <div class="ml80">
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="300502" class="" name="map['300502']" value="1" checked="" type="checkbox">
                                                            <label for="300502">查看</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="300503" class="" name="map['300503']" value="1" checked="" type="checkbox">
                                                            <label for="300503">新增</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="300504" class="" name="map['300504']" value="1" checked="" type="checkbox">
                                                            <label for="300504">删除</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>合同编辑</h4>
                                                <div class="form-inline">
                                                    <div class="ml80">
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="300601" class="" name="map['300601']" value="1" checked="" type="checkbox">
                                                            <label for="300601">确认合同</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="300602" class="" name="map['300602']" value="1" checked="" type="checkbox">
                                                            <label for="300602">反确认合同</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>跟进记录</h4>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">跟进记录：</label>
                                                    <div class="ml80">
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="300701" class="" name="map['300701']" value="1" checked="" type="checkbox">
                                                            <label for="300701">新增跟进</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="300702" class="" name="map['300702']" value="1" checked="" type="checkbox">
                                                            <label for="300702">跟进查看</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="300703" class="" name="map['300703']" value="1" checked="" type="checkbox">
                                                            <label for="300703">跟进删除</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="clearfix text-center">
                                <button type="button" class="btn btn-default"><i class="iconfont"></i>取消</button>
                                <button type="button" class="btn btn-primary" onclick="saveHt();"><i class="iconfont"></i>保存</button>
                            </div>
                        </form>    </div>
                    <div class="tab-pane animated fadeInDown" id="sale">

                    </div>
                    <div class="tab-pane animated fadeInDown" id="utiles">

                    </div>
                    <div class="tab-pane animated fadeInDown" id="business">

                    </div>
                    <div class="tab-pane animated fadeInDown" id="system">

                    </div>
                    <div class="tab-pane animated fadeInDown" id="clock">

                        <form action="#" method="post" class="form-horizontal" id="kqForm">
                            <input name="uid" value="75" class="uid" id="kqUid" type="hidden">
                            <input name="type" value="kq" type="hidden">
                            <!-- BUG #9127 岗位id：注意不能和员工uid同时有值 -->
                            <input name="ptmid" value="" id="kqPtmid" type="hidden">
                            <!-- 用于区分是岗位模块查询权限还是员工查询权限操作 -->
                            <input name="operator" value="" id="kqOperator" type="hidden">
                            <div id="timeline-grid2" class="gridbox gridalicious">
                                <div class="row">
                                    <div class="col-lg-6 col-md-6 col-sm-12">
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>班次设置权限</h4>
                                                <div class="form-inline">
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="500101" class="" name="map['500101']" value="1" title="1111" type="checkbox">
                                                        <label for="500101">新增班次</label>
                                                    </div>
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="500102" class="" name="map['500102']" value="1" type="checkbox">
                                                        <label for="500102">编辑班次</label>
                                                    </div>
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="500103" class="" name="map['500103']" value="1" type="checkbox">
                                                        <label for="500103">删除班次</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>其他权限</h4>
                                                <div class="form-inline">
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="500301" class="" name="map['500301']" value="1" type="checkbox">
                                                        <label for="500301">类型维护</label>
                                                    </div>
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="500302" class="" name="map['500302']" value="1" type="checkbox">
                                                        <label for="500302">考勤统计</label>
                                                    </div>
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="500303" class="" name="map['500303']" value="1" type="checkbox">
                                                        <label for="500303">申诉/异常考勤处理</label>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">考勤记录：</label>
                                                    <div class="col-sm-3">
                                                        <select id="500304" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="map['500304']">
                                                            <option value="1">无</option>
                                                            <option value="2">本人</option>
                                                            <option value="3">本部</option>
                                                            <option value="4">全部</option>
                                                        </select><div class="btn-group bootstrap-select show-menu-arrow form-control"><button type="button" class="btn dropdown-toggle btn-default" data-toggle="dropdown" data-id="500304" title="无"><span class="filter-option pull-left">无</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner" role="menu"><li data-original-index="0" class="selected"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">无</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="1"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本人</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="2"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">本部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li><li data-original-index="3"><a tabindex="0" class="" style="" data-tokens="null"><span class="text">全部</span><span class="glyphicon glyphicon-ok check-mark"></span></a></li></ul></div></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-12">
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>班次安排权限</h4>
                                                <div class="form-inline">
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="500201" class="" name="map['500201']" value="1" type="checkbox">
                                                        <label for="500201">新增班组</label>
                                                    </div>
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="500202" class="" name="map['500202']" value="1" type="checkbox">
                                                        <label for="500202">编辑班组</label>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="500203" class="" name="map['500203']" value="1" type="checkbox">
                                                        <label for="500203">删除班组</label>
                                                    </div>
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="500204" class="" name="map['500204']" value="1" type="checkbox">
                                                        <label for="500204">班次调整</label>
                                                    </div>
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="500205" class="" name="map['500205']" value="1" type="checkbox">
                                                        <label for="500205">班次导出</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="clearfix text-center">
                                <button type="button" class="btn btn-default"><i class="iconfont"></i> 取消</button>
                                <button type="button" class="btn btn-primary" onclick="saveKq();"><i class="iconfont"></i> 保存</button>
                            </div>
                        </form>    </div>
                </div>
            </div></div>
            <!--col-sm-9 end-->
            <div class="clearfix"></div>
        </div>
        <!-- /.row (main row) -->
    </section>
</div>
<#include "/common/footer.ftl" />
<script src="${contextPath!}/js/app/system/authorityConfig/index.js"></script>


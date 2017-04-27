<#include "/common/header.ftl" />
<link rel="stylesheet" href="${contextPath}/css/plugins/zTree/iconSkin.css">
<link rel="stylesheet" href="${contextPath}/css/app/system/moduleConfig/index.css">
<#include "/common/sidebar.ftl" />
<div id="moduleConfig" class="content-wrapper" ng-controller="ModuleCtrl as ctrl">
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
    <section class="content">
        <div class="row animated fadeInRight">
            <!-- 树 -->
            <div class="col-lg-3 col-md-3 col-sm-4">
                <div class="box box-solid">
                    <div class="box-header with-border">
                        <h3 class="box-title">所有岗位</h3>
                    </div>
                    <div class="tab-content zTreeDemoBackground">
                        <div class=" animated fadeInDown ">
                            <ul id="positionList" class="ztree">
                                <li ng-repeat="position in ctrl.baseData.position"
                                    ng-click="ctrl.getModuleConfig(position)">
                                    <a href="javascript:;" class="position-list-nav">
                                        <span class="button ico_docu position_ico"></span>
                                        <span class="treeDemo_1_span" ng-bind="position.name"></span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end 树 -->
            <div class="col-lg-9 col-md-9 col-sm-9">
                <form class="form-horizontal" id="searchForm" method="post">
                    <div id="timeline-grid" class="gridalicious">
                        <div style="clear: both; height: 0px; width: 0px; display: block;" id="clearplgSO"></div>
                        <div class="galcolumn" id="item0xhA0N">
                            <div class="item" style="margin-bottom: 10px; opacity: 1;">
                                <div class="box box-solid backf5 " style="margin-bottom:0">
                                    <header class="box-header with-border">
                                        <div class="checkbox checkbox-nice">
                                            <input id="P_FANG" ng-model="ctrl.moduleAuthority.P_FANG" type="checkbox">
                                            <label for="P_FANG"><h3 class="box-title">房源管理</h3></label>
                                        </label>
                                        </div>
                                    </header>
                                    <div class="box-body">
                                        <div class="form-group form-inline">
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input ng-model="ctrl.moduleAuthority.P_FANG_LIST" id="P_FANG_LIST" type="checkbox">
                                                    <label for="P_FANG_LIST">房源列表</label>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input ng-model="ctrl.moduleAuthority.P_FANG_NEW" id="P_FANG_NEW" type="checkbox">
                                                    <label for="P_FANG_NEW">新增房源</label>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input ng-model="ctrl.moduleAuthority.P_FANG_FOLLOW" id="P_FANG_FOLLOW" type="checkbox">
                                                    <label for="P_FANG_FOLLOW">房源跟进</label>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input ng-model="ctrl.moduleAuthority.P_FANG_CHECK" id="P_FANG_CHECK" type="checkbox">
                                                    <label for="P_FANG_CHECK">房源勘查</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="item" style="margin-bottom: 10px; opacity: 1;">
                                <div class="box box-solid backf5 " style="margin-bottom:0">
                                    <header class="box-header with-border">
                                        <div class="checkbox checkbox-nice">
                                            <input id="P_SHOWING" ng-model="ctrl.moduleAuthority.P_SHOWING" type="checkbox">
                                            <label for="P_SHOWING"><h3 class="box-title">带看管理</h3></label>
                                        </div>
                                    </header>
                                    <div class="box-body">
                                        <div class="form-group form-inline">
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input id="P_SHOWING_LIST" ng-model="ctrl.moduleAuthority.P_SHOWING_LIST" type="checkbox">
                                                    <label for="P_SHOWING_LIST">房源带看</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="item" style="margin-bottom: 10px; opacity: 1;">
                                <div class="box box-solid backf5 " style="margin-bottom:0">
                                    <header class="box-header with-border">
                                        <div class="checkbox checkbox-nice">
                                            <input id="P_CONTRACT" ng-model="ctrl.moduleAuthority.P_CONTRACT" type="checkbox">
                                            <label for="P_CONTRACT"><h3 class="box-title">合同管理</h3></label>
                                        </div>
                                    </header>
                                    <div class="box-body">
                                        <div class="form-group form-inline">
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input id="P_CONTRACT_LIST" ng-model="ctrl.moduleAuthority.P_CONTRACT_LIST" type="checkbox">
                                                    <label for="P_CONTRACT_LIST">成交管理</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="galcolumn">
                            <div class="item" style="margin-bottom: 10px; opacity: 1;">
                                <div class="box box-solid backf5 " style="margin-bottom:0">
                                    <header class="box-header with-border">
                                        <div class="checkbox checkbox-nice">
                                            <input id="P_ORG" ng-model="ctrl.moduleAuthority.P_ORG" type="checkbox">
                                            <label for="P_ORG"><h3 class="box-title">组织架构</h3></label>
                                        </div>
                                    </header>
                                    <div class="box-body">
                                        <div class="form-group form-inline">
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input id="P_ORG_DEPT" ng-model="ctrl.moduleAuthority.P_ORG_DEPT" type="checkbox">
                                                    <label for="P_ORG_DEPT">部门管理</label>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input id="P_ORG_POSITION" ng-model="ctrl.moduleAuthority.P_ORG_POSITION" type="checkbox">
                                                    <label for="P_ORG_POSITION">岗位管理</label>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input id="P_ORG_EMPLOYEE" ng-model="ctrl.moduleAuthority.P_ORG_EMPLOYEE" type="checkbox">
                                                    <label for="P_ORG_EMPLOYEE">员工管理</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="item" style="margin-bottom: 10px; opacity: 1;">
                                <div class="box box-solid backf5 " style="margin-bottom:0">
                                    <header class="box-header with-border">
                                        <div class="checkbox checkbox-nice">
                                            <input id="P_CONFIG" ng-model="ctrl.moduleAuthority.P_CONFIG" type="checkbox">
                                            <label for="P_CONFIG"><h3 class="box-title">系统设置</h3></label>
                                        </div>
                                    </header>
                                    <div class="box-body">
                                        <div class="form-group form-inline">
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input id="P_CONFIG_AUDIT" ng-model="ctrl.moduleAuthority.P_CONFIG_AUDIT" type="checkbox">
                                                    <label for="P_CONFIG_AUDIT">操作日志</label>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input id="P_CONFIG_HOUSE_DICT" ng-model="ctrl.moduleAuthority.P_CONFIG_HOUSE_DICT" type="checkbox">
                                                    <label for="P_CONFIG_HOUSE_DICT">楼盘字典</label>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input id="P_CONFIG_PAGE" ng-model="ctrl.moduleAuthority.P_CONFIG_PAGE" type="checkbox">
                                                    <label for="P_CONFIG_PAGE">岗位模块</label>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input id="P_CONFIG_PERMISSION" ng-model="ctrl.moduleAuthority.P_CONFIG_PERMISSION" type="checkbox">
                                                    <label for="P_CONFIG_PERMISSION">权限设置</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div style="clear: both; height: 0px; width: 0px; display: block;" id="clearxhA0N"></div>
                    </div>
                    <div class="clearfix text-center">
                        <a class="btn btn-primary" href="javascript:void(0)" ng-click="ctrl.updateConfig()">
                            <i class="fa fa-save"></i> 保存
                        </a>
                    </div>
                </form>
            </div>
            <div class="clearfix"></div>
        </div>
    </section>
</div>
<#include "/common/footer.ftl" />
<script src="${contextPath!}/js/app/system/moduleConfig/index.js"></script>


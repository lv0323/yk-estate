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
                岗位模块
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
                                    <a href="javascript:;" class="position-list-nav" ng-attr-id="{{position.id}}">
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
                        <@checkPermission value='CT_YK|CT_RA'>
                            <div class="item" style="margin-bottom: 10px; opacity: 1;">
                                <div class="box box-solid backf5 " style="margin-bottom:0">
                                    <header class="box-header with-border">
                                        <div class="checkbox checkbox-nice">
                                            <input id="P_FRANCHISEE" ng-model="ctrl.moduleAuthority.P_FRANCHISEE" type="checkbox" ng-change="ctrl.allPickChange('P_FRANCHISEE')">
                                            <label for="P_FRANCHISEE"><h3 class="box-title">加盟商管理</h3></label>
                                        </div>
                                    </header>
                                    <div class="box-body">
                                        <div class="form-group form-inline">
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input id="P_FRANCHISEE_C" ng-model="ctrl.moduleAuthority.P_FRANCHISEE_C" type="checkbox" ng-change="ctrl.dataChange('P_FRANCHISEE', ctrl.moduleAuthority.P_FRANCHISEE_C)">
                                                    <label for="P_FRANCHISEE_C">渠道加盟</label>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input id="P_FRANCHISEE_SS" ng-model="ctrl.moduleAuthority.P_FRANCHISEE_SS" type="checkbox" ng-change="ctrl.dataChange('P_FRANCHISEE', ctrl.moduleAuthority.P_FRANCHISEE_SS)">
                                                    <label for="P_FRANCHISEE_SS">单店加盟</label>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input id="P_FRANCHISEE_RA" ng-model="ctrl.moduleAuthority.P_FRANCHISEE_RA" type="checkbox" ng-change="ctrl.dataChange('P_FRANCHISEE', ctrl.moduleAuthority.P_FRANCHISEE_RA)">
                                                    <label for="P_FRANCHISEE_RA">区域代理加盟</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </@>
                            <div class="item" style="margin-bottom: 10px; opacity: 1;">
                                <div class="box box-solid backf5 " style="margin-bottom:0">
                                    <header class="box-header with-border">
                                        <div class="checkbox checkbox-nice">
                                            <input id="P_FANG" ng-model="ctrl.moduleAuthority.P_FANG" type="checkbox" ng-change="ctrl.allPickChange('P_FANG')">
                                            <label for="P_FANG"><h3 class="box-title">房源管理</h3></label>
                                        </label>
                                        </div>
                                    </header>
                                    <div class="box-body">
                                        <div class="form-group form-inline">
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input ng-model="ctrl.moduleAuthority.P_FANG_LIST" id="P_FANG_LIST" type="checkbox" ng-change="ctrl.dataChange('P_FANG', ctrl.moduleAuthority.P_FANG_LIST)">
                                                    <label for="P_FANG_LIST">房源列表</label>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input ng-model="ctrl.moduleAuthority.P_FANG_NEW" id="P_FANG_NEW" type="checkbox" ng-change="ctrl.dataChange('P_FANG', ctrl.moduleAuthority.P_FANG_NEW)">
                                                    <label for="P_FANG_NEW">新增房源</label>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input ng-model="ctrl.moduleAuthority.P_FANG_FOLLOW" id="P_FANG_FOLLOW" type="checkbox" ng-change="ctrl.dataChange('P_FANG', ctrl.moduleAuthority.P_FANG_FOLLOW)">
                                                    <label for="P_FANG_FOLLOW">房源跟进</label>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input ng-model="ctrl.moduleAuthority.P_FANG_CHECK" id="P_FANG_CHECK" type="checkbox" ng-change="ctrl.dataChange('P_FANG', ctrl.moduleAuthority.P_FANG_CHECK)">
                                                    <label for="P_FANG_CHECK">房源勘查</label>
                                                </div>
                                            </div>
                                            <@checkPermission value='CT_YK'>
                                                <div class="col-xs-6">
                                                    <div class="checkbox checkbox-nice">
                                                        <input ng-model="ctrl.moduleAuthority.P_FANG_APPLICATION_PAGE" id="P_FANG_APPLICATION_PAGE" type="checkbox" ng-change="ctrl.dataChange('P_FANG', ctrl.moduleAuthority.P_FANG_APPLICATION_PAGE)">
                                                        <label for="P_FANG_APPLICATION_PAGE">房源状态更改审批</label>
                                                    </div>
                                                </div>
                                            </@>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        <@checkPermission value='CT_YK'>
                            <div class="item" style="margin-bottom: 10px; opacity: 1;">
                                <div class="box box-solid backf5 " style="margin-bottom:0">
                                    <header class="box-header with-border">
                                        <div class="checkbox checkbox-nice">
                                            <input id="P_FANG_COLLECTION" ng-model="ctrl.moduleAuthority.P_FANG_COLLECTION" type="checkbox" ng-change="ctrl.allPickChange('P_FANG_COLLECTION')">
                                            <label for="P_FANG_COLLECTION"><h3 class="box-title">房源采集</h3></label>
                                        </label>
                                        </div>
                                    </header>
                                    <div class="box-body">
                                        <div class="form-group form-inline">
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input ng-model="ctrl.moduleAuthority.P_FANG_COLLECTION_POOL" id="P_FANG_COLLECTION_POOL" type="checkbox" ng-change="ctrl.dataChange('P_FANG_COLLECTION', ctrl.moduleAuthority.P_FANG_COLLECTION_POOL)">
                                                    <label for="P_FANG_COLLECTION_POOL">房源池</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </@>
                            <div class="item" style="margin-bottom: 10px; opacity: 1;">
                                <div class="box box-solid backf5 " style="margin-bottom:0">
                                    <header class="box-header with-border">
                                        <div class="checkbox checkbox-nice">
                                            <input id="P_SHOWING" ng-model="ctrl.moduleAuthority.P_SHOWING" type="checkbox" ng-change="ctrl.allPickChange('P_SHOWING')">
                                            <label for="P_SHOWING"><h3 class="box-title">带看管理</h3></label>
                                        </div>
                                    </header>
                                    <div class="box-body">
                                        <div class="form-group form-inline">
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input id="P_SHOWING_LIST" ng-model="ctrl.moduleAuthority.P_SHOWING_LIST" type="checkbox" ng-change="ctrl.dataChange('P_SHOWING', ctrl.moduleAuthority.P_SHOWING_LIST)">
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
                                            <input id="P_CONTRACT" ng-model="ctrl.moduleAuthority.P_CONTRACT" type="checkbox" ng-change="ctrl.allPickChange('P_CONTRACT')">
                                            <label for="P_CONTRACT"><h3 class="box-title">合同管理</h3></label>
                                        </div>
                                    </header>
                                    <div class="box-body">
                                        <div class="form-group form-inline">
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input id="P_CONTRACT_LIST" ng-model="ctrl.moduleAuthority.P_CONTRACT_LIST" type="checkbox" ng-change="ctrl.dataChange('P_CONTRACT', ctrl.moduleAuthority.P_CONTRACT_LIST)">
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
                                            <input id="P_ORG" ng-model="ctrl.moduleAuthority.P_ORG" type="checkbox" ng-change="ctrl.allPickChange('P_ORG')">
                                            <label for="P_ORG"><h3 class="box-title">组织架构</h3></label>
                                        </div>
                                    </header>
                                    <div class="box-body">
                                        <div class="form-group form-inline">
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input id="P_ORG_DEPT" ng-model="ctrl.moduleAuthority.P_ORG_DEPT" type="checkbox" ng-change="ctrl.dataChange('P_ORG', ctrl.moduleAuthority.P_ORG_DEPT)">
                                                    <label for="P_ORG_DEPT">部门管理</label>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input id="P_ORG_POSITION" ng-model="ctrl.moduleAuthority.P_ORG_POSITION" type="checkbox" ng-change="ctrl.dataChange('P_ORG', ctrl.moduleAuthority.P_ORG_POSITION)">
                                                    <label for="P_ORG_POSITION">岗位管理</label>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input id="P_ORG_EMPLOYEE" ng-model="ctrl.moduleAuthority.P_ORG_EMPLOYEE" type="checkbox" ng-change="ctrl.dataChange('P_ORG', ctrl.moduleAuthority.P_ORG_EMPLOYEE)">
                                                    <label for="P_ORG_EMPLOYEE">员工管理</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <@checkPermission value='CT_YK'>
                            <div class="item" style="margin-bottom: 10px; opacity: 1;">
                                <div class="box box-solid backf5 " style="margin-bottom:0">
                                    <header class="box-header with-border">
                                        <div class="checkbox checkbox-nice">
                                            <input id="P_OPERATION" ng-model="ctrl.moduleAuthority.P_OPERATION" type="checkbox" ng-change="ctrl.allPickChange('P_OPERATION')">
                                            <label for="P_OPERATION"><h3 class="box-title">运营管理</h3></label>
                                        </div>
                                    </header>
                                    <div class="box-body">
                                        <div class="form-group form-inline">
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input id="P_OPERATION_XY" ng-model="ctrl.moduleAuthority.P_OPERATION_XY" type="checkbox" ng-change="ctrl.dataChange('P_OPERATION', ctrl.moduleAuthority.P_OPERATION_XY)">
                                                    <label for="P_OPERATION_XY">信誉平台</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </@>
                            <@checkPermission value='CT_YK'>
                            <div class="item" style="margin-bottom: 10px; opacity: 1;">
                                <div class="box box-solid backf5 " style="margin-bottom:0">
                                    <header class="box-header with-border">
                                        <div class="checkbox checkbox-nice">
                                            <input id="P_APPROVAL" ng-model="ctrl.moduleAuthority.P_APPROVAL" type="checkbox" ng-change="ctrl.allPickChange('P_APPROVAL')">
                                            <label for="P_APPROVAL"><h3 class="box-title">表单管理</h3></label>
                                        </div>
                                    </header>
                                    <div class="box-body">
                                        <div class="form-group form-inline">
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input id="P_APPROVAL_1" ng-model="ctrl.moduleAuthority.P_APPROVAL_1" type="checkbox" ng-change="ctrl.dataChange('P_APPROVAL', ctrl.moduleAuthority.P_APPROVAL_1)">
                                                    <label for="P_APPROVAL_1">新建表单</label>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input id="P_APPROVAL_2" ng-model="ctrl.moduleAuthority.P_APPROVAL_2" type="checkbox" ng-change="ctrl.dataChange('P_APPROVAL', ctrl.moduleAuthority.P_APPROVAL_2)">
                                                    <label for="P_APPROVAL_2">表单列表</label>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input id="P_APPROVAL_3" ng-model="ctrl.moduleAuthority.P_APPROVAL_3" type="checkbox" ng-change="ctrl.dataChange('P_APPROVAL', ctrl.moduleAuthority.P_APPROVAL_3)">
                                                    <label for="P_APPROVAL_3">我的表单</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </@>
                            <div class="item" style="margin-bottom: 10px; opacity: 1;">
                                <div class="box box-solid backf5 " style="margin-bottom:0">
                                    <header class="box-header with-border">
                                        <div class="checkbox checkbox-nice">
                                            <input id="P_CONFIG" ng-model="ctrl.moduleAuthority.P_CONFIG" type="checkbox" ng-change="ctrl.allPickChange('P_CONFIG')">
                                            <label for="P_CONFIG"><h3 class="box-title">系统设置</h3></label>
                                        </div>
                                    </header>
                                    <div class="box-body">
                                        <div class="form-group form-inline">
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input id="P_CONFIG_AUDIT" ng-model="ctrl.moduleAuthority.P_CONFIG_AUDIT" type="checkbox" ng-change="ctrl.dataChange('P_CONFIG', ctrl.moduleAuthority.P_CONFIG_AUDIT)">
                                                    <label for="P_CONFIG_AUDIT">操作日志</label>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input id="P_CONFIG_HOUSE_DICT" ng-model="ctrl.moduleAuthority.P_CONFIG_HOUSE_DICT" type="checkbox" ng-change="ctrl.dataChange('P_CONFIG', ctrl.moduleAuthority.P_CONFIG_HOUSE_DICT)">
                                                    <label for="P_CONFIG_HOUSE_DICT">楼盘字典</label>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input id="P_CONFIG_PAGE" ng-model="ctrl.moduleAuthority.P_CONFIG_PAGE" type="checkbox" ng-change="ctrl.dataChange('P_CONFIG', ctrl.moduleAuthority.P_CONFIG_PAGE)">
                                                    <label for="P_CONFIG_PAGE">岗位模块</label>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="checkbox checkbox-nice">
                                                    <input id="P_CONFIG_PERMISSION" ng-model="ctrl.moduleAuthority.P_CONFIG_PERMISSION" type="checkbox" ng-change="ctrl.dataChange('P_CONFIG', ctrl.moduleAuthority.P_CONFIG_PERMISSION)">
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


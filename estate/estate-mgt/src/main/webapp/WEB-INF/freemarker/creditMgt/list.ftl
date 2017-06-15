<link href="${contextPath}/css/app/fang/index.css" rel="stylesheet">

<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper" id="agentListWrapper" ng-controller="agentListCtrl">
    <section class="content-header">
        <ol class="breadcrumb">
            <li>
                <a href="#">
                    <i class="fa fa-home fa-lg" aria-hidden="true"></i>
                    信誉平台管理
                </a>
            </li>
            <li class="active">
                审核管理
            </li>
        </ol>
    </section>

    <section class="content">
        <div class="animated fadeInRight">
            <div class="row">
                <div class="col-lg-12">
                    <section class="connectedSortable">
                        <div class="box box-solid">
                            <div class="box-header">
                                <h3 class="box-title">中介列表</h3>
                                <div class="box-tools">
                                    <a class="btn" id="addPartnerBtn" ng-href="">
                                        <i class="fa fa-plus" aria-hidden="true"></i>
                                        新增中介
                                    </a>
                                    <span class="opt-gap"></span>
                                    <a class="btn-collapse icon-down btn" ng-click="triggerCollapse()">
                                        <strong><i class="fa" ng-class="{true:'fa-chevron-up',false:'fa-chevron-down'}[page.collapse]" aria-hidden="true"></i>筛选</strong>
                                    </a>
                                </div>
                            </div>

                            <div class="box-body clearfix no-padding default-height">
                                <form id=formList" class="form-inline">
                                    <div id="searchList" ng-cloak class="clearfix" ng-show="page.collapse">
                                        <div class="collapse-box">
                                            <div class="form-group sortlist">
                                                <label class="control-label">筛选</label>
                                                <div class="col-lg-2 col-md-2 col-sm-3">
                                                    <div class="form-group sortlist">
                                                        <label class="control-label">审核状态</label>
                                                        <div id="verification_status" class="tj">
                                                            <a ng-href="javascript::" ng-class="{'actived': '' == filter.houseType}" ng-click="setFilterType('houseTypes' ,'')">不限</a>
                                                        <#--<#list corpStatusList?if_exists as cs>-->
                                                            <#--<a ng-href="javascript::" ng-class="{'actived': '${cs.name()}' == filter.houseType}" ng-click="setFilterType('houseType' ,'${cs.name()}')">-->
                                                            <#--${cs.getLabel()}</a>-->
                                                        <#--</#list>-->
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="PartnerList" class="table-responsive clearfix" ng-cloak>
                                        <div class="media clearfix" ng-repeat="partner in channelPartnerList">
                                            <div class="media-body">
                                                <div class="col-lg-8 col-md-8 col-sm-8" style="padding-right: 0">
                                                    <div class="clearfix">
                                                        <h5 class="clearfix media-heading pull-left" >
                                                            <a ng-href="{{'/mgt/franchisee/channelPartner/detailChannelPartner?partnerId='+partner.id}}" target="_blank" class="f18" ng-bind="partner.name"></a>
                                                        </h5>
                                                    </div>
                                                    <div class="clearfix m-t-10 text-muted">
                                                        <span class="tip tip-success">盈科签约人</span>
                                                        <span class="m-l-5 text-muted"> {{partner.partA.name}}-{{partner.partA.companyAbbr}}-{{partner.partA.mobile}}</span>
                                                    </div>
                                                </div>

                                                <div class="col-lg-4 col-md-4 col-sm-4 text-left">
                                                    <p><strong class="text-muted">{{partner.cityName}}</strong></p>
                                                    <div><span class="tip tip-danger">负责人</span><span class="m-l-5 text-muted">{{partner.boss.name}}-{{partner.boss.mobile}}</span></div>
                                                    <div><span class="tip tip-danger">签约日期</span><span class="m-l-5 text-muted">{{partner.startDate}}~{{partner.endDate}}</span></div>
                                                </div>

                                                <div class="clearfix col-lg-12 col-md-12 col-sm-12">
                                                    <div class="pull-left btn-add">
                                                        <span class="badge badge-info m-r-5">店</span><span class="text-muted m-r-20">{{partner.deptCount}}</span>
                                                        <span class="badge badge-warning m-r-5">员工</span><span class="text-muted m-r-20"> {{partner.employeeCount}}</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="pagination-container">
                                        <ul id="PartnerList_paging" class="pagination"></ul>
                                    </div>
                                </form>
                            </div>

                        </div>
                    </section>
                </div>
            </div>
        </div>
    </section>

</div>
<!-- /.content-wrapper -->

<#include "/common/footer.ftl" />
<script src="${contextPath!}/js/app/franchisee/channelPartner/channelPartner.js"></script>
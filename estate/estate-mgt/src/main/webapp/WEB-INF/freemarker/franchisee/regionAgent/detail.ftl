<link href="${contextPath}/css/app/fang/index.css" rel="stylesheet">

<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper" id="detailFranchiseeWrapper" ng-controller="DetailFranchiseeCtrl">
    <section class="content-header">
        <ol class="breadcrumb">
            <li>
                <a href="#">
                    <i class="fa fa-home fa-lg" aria-hidden="true"></i>
                    加盟商管理
                </a>
            </li>
            <li>
                区域代理管理
            </li>
            <li class="active">
                区域代理详情
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
                                <h3 class="box-title">区域代理详情</h3>
                            </div>
                        <#--<div class="row">-->
                        <#--<div class="col-lg-6 col-md-6" >-->
                            <div class="box-body">
                                <div class="box-header">
                                    <h4 class="text-blue">基本信息</h4>
                                    <div class="box-tools">
                                        <a class="btn" ng-click="editDetail()">
                                            <i class="fa fa-pencil"></i>编辑
                                        </a>
                                    </div>
                                </div>
                                <div class="page">
                                    <table class="table">
                                        <tbody>
                                        <tr>
                                            <td class="text-muted">名称</td>
                                            <td ng-bind="detail.name" class="text-left"></td>
                                            <td class="text-muted">简称</td>
                                            <td ng-bind="detail.abbr" class="text-left"></td>
                                        </tr>
                                        <tr>
                                            <td class="text-muted">地址</td>
                                            <td ng-bind="detail.address" class="text-left"></td>
                                            <td class="text-muted">签约日期</td>
                                            <td class="text-left"><span ng-bind="detail.startDate|date:'yyyy-MM-dd'"></span>~<span ng-bind="detail.endDate|date:'yyyy-MM-dd'"></span></td>
                                        </tr>
                                        <tr>
                                            <td class="text-muted">简介</td>
                                            <td ng-bind="detail.introduction" class="text-left"  colspan=3></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                    <form class="form-horizontal">
                                        <hr>
                                        <h4 class="text-blue">部门列表</h4>
                                        <div class="form-group clearfix">
                                            <table id="departList" class="list table table-bordered table-hover">
                                                <thead><tr>
                                                    <th><span>部门名称</span></th>
                                                    <th><span>部门级别</span></th>
                                                    <th><span>部门电话</span></th>
                                                    <th><span>部门地址</span></th>
                                                </tr></thead>
                                                <tbody></tbody>
                                            </table>
                                            <div class="pagination-container">
                                                <ul id="departList_paging" class="pagination"></ul>
                                            </div>
                                        </div>
                                        <hr>
                                        <h4 class="text-blue">员工列表</h4>
                                        <div class="form-group clearfix">
                                            <table id="employeeList" class="list table table-bordered table-hover">
                                                <thead><tr>
                                                    <th><span>姓名</span></th>
                                                    <th><span>所属部门</span></th>
                                                    <th><span>岗位名称</span></th>
                                                    <th><span>手机</span></th>
                                                    <th><span>外网电话</span></th>
                                                </tr></thead>
                                                <tbody></tbody>
                                            </table>
                                            <div class="pagination-container">
                                                <ul id="employeeList_paging" class="pagination"></ul>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        <#--</div>-->
                            <div class="box-body">
                                <div class="box-header">
                                    <h4 class="text-blue">签约列表</h4>
                                    <div class="box-tools">
                                        <a class="btn"  ng-click="createSigning()">
                                            <i class="fa fa-plus" aria-hidden="true"></i>新增
                                        </a>
                                    </div>
                                </div>
                                <div class="page">
                                    <form class="form-horizontal">
                                        <div class="form-group clearfix">
                                            <table id="contractList" class="list table table-bordered table-hover">
                                                <thead><tr>
                                                    <th><span>签约时间</span></th>
                                                    <th><span>签约公司</span></th>
                                                    <th><span>签约人</span></th>
                                                    <th><span>签约年限</span></th>
                                                    <th><span>签约店数</span></th>
                                                    <th><span>签约价格</span></th>
                                                    <th class="text-right"><span>操作</span></th>
                                                </tr></thead>
                                                <tbody>
                                                <tr ng-repeat="sign in signList">
                                                    <td><span ng-bind="sign.startDate|date:'yyyy-MM-dd'"></span>~<span ng-bind="sign.endDate|date:'yyyy-MM-dd'"></span></td>
                                                    <td ng-bind="sign.partA&&sign.partA.companyAbbr"></td>
                                                    <td ng-bind="sign.partA&&sign.partA.name"></td>
                                                    <td ng-bind="sign.years"></td>
                                                    <td ng-bind="sign.storeCount"></td>
                                                    <td ng-bind="sign.price"></td>
                                                    <td class="text-right"><a class="btn" ng-click="updateSign(sign)">更新</a>&nbsp;<a class="btn" ng-click="deleteSign(sign.id)">删除</a></td>
                                                </tr>
                                                </tbody>
                                            </table>
                                            <div class="pagination-container">
                                                <ul id="sign_paging" class="pagination"></ul>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        <#--<div class="col-lg-6 col-md-6" >-->
                            <div class="box-body">
                                <div class="box-header">
                                    <h4 class="text-blue">渠道商</h4>
                                    <#--<div class="box-tools">
                                        <a class="btn" ng-href="javascript:;" data-toggle="modal" data-target="#editContractModel">
                                            <i class="fa fa-plus" aria-hidden="true"></i>新增
                                        </a>
                                    </div>-->
                                </div>
                                <div class="page">
                                    <div id="channelList" class="table-responsive clearfix" ng-cloak>
                                        <div class="media clearfix" ng-repeat="partner in channelList">
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
                                                    <div><span class="tip tip-danger">签约日期</span><span class="m-l-5 text-muted">{{partner.startDate|date:'yyyy-MM-dd'}}~{{partner.endDate|date:'yyyy-MM-dd'}}</span></div>
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
                                        <ul id="channel_paging" class="pagination"></ul>
                                    </div>
                                </div>
                            </div>
                            <div class="box-body">
                                <div class="box-header">
                                    <h4 class="text-blue">单店加盟</h4>
                                    <#--<div class="box-tools">
                                        <a class="btn" ng-href="javascript:;" data-toggle="modal" data-target="#editContractModel">
                                            <i class="fa fa-plus" aria-hidden="true"></i>新增
                                        </a>
                                    </div>-->
                                </div>
                                <div class="page">
                                    <div id="singleList" class="table-responsive clearfix" ng-cloak>
                                        <div class="media clearfix" ng-repeat="partner in singleList">
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
                                                    <div><span class="tip tip-danger">签约日期</span><span class="m-l-5 text-muted">{{partner.startDate|date:'yyyy-MM-dd'}}~{{partner.endDate|date:'yyyy-MM-dd'}}</span></div>
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
                                        <ul id="single_paging" class="pagination"></ul>
                                    </div>
                                </div>
                            </div>
                        <#--</div>-->
                        <#--</div>-->
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </section>

<#include "/franchisee/modal.ftl" />
</div>
<!-- /.content-wrapper -->

<#include "/common/footer.ftl" />
<script src="${contextPath!}/js/app/franchisee/detailFranchisee.js"></script>
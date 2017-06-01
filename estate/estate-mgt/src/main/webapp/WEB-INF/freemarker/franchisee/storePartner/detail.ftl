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
                单店加盟管理
            </li>
            <li class="active">
                单店加盟详情
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
                                <h3 class="box-title">单店加盟详情</h3>
                            </div>
                        <#--<div class="row">-->
                        <#--<div class="col-lg-6 col-md-6" >-->
                            <div class="box-body">
                                <div class="box-header">
                                    <h4 class="text-blue">基本信息</h4>
                                    <div class="box-tools">
                                        <a class="btn" data-toggle="modal" ng-click="editDetail()">
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
                        <#--<div class="col-lg-6 col-md-6" >-->
                            <div class="box-body">
                                <div class="box-header">
                                    <h4 class="text-blue">签约列表</h4>
                                    <div class="box-tools">
                                        <a class="btn" ng-click="createSigning('add')">
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
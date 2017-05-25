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
                渠道商管理
            </li>
            <li class="active">
                渠道商详情
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
                                <h3 class="box-title">渠道商详情</h3>
                                <div class="box-tools">
                                    <a class="btn btn-white" ng-href="javascript:;" ng-click="editPartner(partner.id)">
                                        <i class="fa fa-pencil"></i>编辑
                                    </a>
                                </div>
                            </div>
                            <div class="box-body">
                                <div class="page">
                                    <form class="form-horizontal">
                                        <div class="form-group clearfix">
                                            <label class="pull-left control-label">名称</label>
                                            <div class="col-lg-3 col-md-3 col-sm-3">
                                                <input id="franchiseeName" ng-model="detail.name" type="text" class="form-control">
                                                <span ng-bind="detail.name"></span>
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="pull-left control-label">区域</label>
                                            <div class="col-lg-3 col-md-3 col-sm-3">
                                                <input id="franchiseeCity" ng-model="detail.city" type="text" class="form-control">
                                                <span ng-bind="detail.city"></span>
                                            </div>
                                        </div>
                                        <hr>
                                        <h4 class="text-blue">部门列表</h4>
                                        <div class="form-group clearfix">

                                        </div>
                                        <hr>
                                        <h4 class="text-blue">员工列表</h4>
                                        <div class="form-group clearfix">

                                        </div>
                                        <hr>
                                        <h4 class="text-blue">签约列表</h4>
                                        <div class="form-group clearfix">

                                        </div>
                                        <#--<div class="form-group center">-->
                                            <#--<div class="col-sm-12">-->
                                                <#--<button type="button" class="btn btn-primary" ng-click="confirmAddDealBtn()">保存</button>-->
                                            <#--</div>-->
                                        <#--</div>-->
                                    </form>
                                </div>
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
<script src="${contextPath!}/js/app/franchisee/detailFranchisee.js"></script>
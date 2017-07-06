<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />

<div id="companyInfo" class="content-wrapper" ng-controller="CompanyInfoCtrl as ctrl" style="min-height: 748px;">
    <section class="content-header">
        <ol class="breadcrumb">
            <li>
                <a href="#">
                    <i class="fa fa-home fa-lg" aria-hidden="true"></i>
                    公司信息
                </a>
            </li>
        </ol>
    </section>
    <section class="content">
        <div class="animated fadeInRight">
            <div class="row">
                <div class="col-lg-12">
                    <section class="connectedSortable">
                        <div class="box box-solid">
                            <header class="box-header with-border">
                                <h3 class="box-title pull-left">公司信息</h3>
                            </header>
                            <div class="box-body no-padding text-center">
                                <div class="col-md-12 col-md-12 col-sm-12" style="line-height: 2.8;">
                                    <div class="row">
                                        <div class="col-md-2 col-sm-4 text-muted">公司名称:</div>
                                        <div class="col-md-4 col-sm-8 text-left" ng-bind="ctrl.company.name"></div>
                                        <div class="col-md-2 col-sm-4 text-muted">公司简称:</div>
                                        <div class="col-md-4 col-sm-8 text-left" ng-bind="ctrl.company.abbr"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-2 col-sm-4 text-muted">所在地址:</div>
                                        <div class="col-md-4 col-sm-8 text-left" ng-bind="ctrl.company.address"></div>
                                        <div class="col-md-2 col-sm-4 text-muted">授权码:</div>
                                        <div class="col-md-4 col-sm-8 text-left" ng-bind="ctrl.company.secretKey"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-2 col-sm-4 text-muted">部门数量:</div>
                                        <div class="col-md-4 col-sm-8 text-left" ng-bind="ctrl.company.deptCount"></div>
                                        <div class="col-md-2 col-sm-4 text-muted">员工人数:</div>
                                        <div class="col-md-4 col-sm-8 text-left" ng-bind="ctrl.company.employeeCount"></div>
                                    </div>
                                    <div class="row" ng-if="ctrl.company.type && ctrl.company.type.name !== 'YK'"  ng-cloak>
                                        <div class="col-md-2 col-sm-4 text-muted">签约日期:</div>
                                        <div class="col-md-4 col-sm-8 text-left">
                                            <span ng-bind="ctrl.company.startDate|date:'yyyy-MM-dd'"></span>~<span ng-bind="ctrl.company.endDate|date:'yyyy-MM-dd'"></span>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-2 col-sm-4 text-muted">公司简介:</div>
                                        <div class="col-md-10 col-sm-8 text-left" ng-bind="ctrl.company.introduction"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </section>

</div>
<#include "/common/footer.ftl" />
<script src="${contextPath!}/js/app/company/index.js"></script>
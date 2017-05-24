<link href="${contextPath}/css/app/fang/index.css" rel="stylesheet">

<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper" id="createFranchiseeWrapper" ng-controller="CreateFranchiseeCtrl">
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
                创建区域代理
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
                                <h3 class="box-title">创建区域代理</h3>
                            </div>

                            <div class="box-body">
                                <div class="page">
                                    <form class="form-horizontal">
                                        <div class="form-group clearfix">
                                            <label class="pull-left control-label">区域</label>
                                            <div class="col-lg-3 col-md-3 col-sm-3">
                                                <select id="cityListDrop" ng-model="create.cityId" class="chosen-select-dep">
                                                    <option value="">选择城市</option>
                                                    <option ng-repeat="city in cityList" ng-value="city.id" repeat-done="initChosen('#cityListDrop', 'cityId')">{{city.name}}</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="pull-left control-label">签约信息</label>
                                            <div class="col-lg-3 col-md-3 col-sm-3">
                                                <select id="companyListDrop">
                                                    <option value="">所属公司</option>
                                                    <option ng-repeat="company in companyList" ng-value="company.id" repeat-done="initChosen('#companyListDrop', 'parentId')">{{company.name}}</option>
                                                </select>
                                            </div>
                                            <div class="col-lg-3 col-md-3 col-sm-3">
                                                <select id="signatureDepListDrop">
                                                    <option value="">上级部门</option>
                                                    <option ng-repeat="signatureDep in signatureDepList" ng-value="signatureDep.id" repeat-done="initChosen('#signatureDepListDrop', 'signatureDepId')">{{signatureDep.name}}</option>
                                                </select>
                                            </div>
                                            <div class="col-lg-3 col-md-3 col-sm-3">
                                                <select id="signatureRepListDrop">
                                                    <option value="">部门员工</option>
                                                    <option ng-repeat="signatureRep in signatureRepList" ng-value="signatureRep.id" repeat-done="initChosen('#signatureRepListDrop', 'partAId')">{{signatureRep.name}}</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="pull-left control-label">加盟类型</label>
                                            <div class="col-lg-3 col-md-3 col-sm-3">
                                                <select id="franchiseeType" ng-model="create.type" class="selected_1 show-menu-arrow form-control" select-picker>
                                                    <option value="">选择加盟类型</option>
                                                <#list franchiseeType?if_exists as type>
                                                    <#if type == 'YK'>
                                                        <option value="${type.name()}" style="display: none;">${type.getLabel()}</option>
                                                    <#else>
                                                        <option value="${type.name()}" >${type.getLabel()}</option>
                                                    </#if>
                                                </#list>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="pull-left control-label">加盟商信息</label>
                                            <div class="col-lg-3 col-md-3 col-sm-3">
                                                <input id="franchiseeName" ng-model="create.name" type="text" class="form-control" placeholder="加盟商名称">
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-2">
                                                <input id="franchiseeAbbr" ng-model="create.abbr" type="text" class="form-control" placeholder="简称" />
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="pull-left control-label">加盟商地址</label>
                                            <div class="col-sm-7">
                                                <input id="franchiseeAddr" ng-model="create.address" type="text" class="form-control" placeholder="加盟商地址" />
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="pull-left control-label">简介</label>
                                            <div class="col-sm-7">
                                                <textarea ng-model="create.introduction" class="form-control noresize" placeholder="填写介绍" cols="20" rows="4"></textarea>
                                            </div>
                                        </div>
                                        <hr>
                                        <h4 class="text-blue">加盟详情</h4>
                                        <div class="form-group clearfix">
                                            <label class="pull-left control-label">签约有效期</label>
                                            <div class="col-lg-3 col-md-3 col-sm-3">
                                                <div class="input-group date form_date" datetimepicker key="startDate" change="setDate">
                                                    <input id="contractSDate" class="form-control" size="16" placeholder="开始日期" type="text" ng-model="create.startDate">
                                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                                </div>
                                            </div>
                                            <div class="col-lg-3 col-md-3 col-sm-3">
                                                <div class="input-group date form_date" datetimepicker key="endDate" change="setDate">
                                                    <input id="contractEDate" class="form-control" size="16" placeholder="结束日期" type="text" ng-model="create.endDate">
                                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                                </div>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-2">
                                                <div class="input-group">
                                                    <input id="contractYears" type="text" class="form-control" placeholder="年限" ng-model="create.years">
                                                    <label class="input-group-addon">年</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="pull-left control-label">门店总数</label>
                                            <div class="col-lg-3 col-md-3 col-sm-3">
                                                <input id="franchiseeStores" type="text" class="form-control" placeholder="门店总数" ng-model="create.storeCount">
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="pull-left control-label">加盟费</label>
                                            <div class="col-lg-3 col-md-3 col-sm-3">
                                                <div class="input-group">
                                                    <input id="franchiseeFee" type="text" class="form-control" placeholder="加盟费" ng-model="create.price" >
                                                    <label class="input-group-addon">元</label>
                                                </div>
                                            </div>
                                        </div>
                                        <hr>
                                        <h4 class="text-blue">客户信息</h4>
                                        <div class="form-group clearfix">
                                            <label class="pull-left control-label">负责人姓名</label>
                                            <div class="col-sm-3">
                                                <input id="franchiseeBoss" type="text" class="form-control" placeholder="负责人姓名" ng-model="create.bossName">
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="pull-left control-label">负责人手机</label>
                                            <div class="col-sm-3">
                                                <input id="franchiseeBossMobile" type="text" class="form-control" placeholder="负责人手机" ng-model="create.mobile">
                                            </div>
                                        </div>
                                        <hr>


                                        <div class="form-group center">
                                            <div class="col-sm-12">
                                                <button type="button" class="btn btn-primary" ng-click="confirmAddDealBtn()">保存</button>
                                            </div>
                                        </div>
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
<script src="${contextPath!}/js/app/franchisee/createFranchisee.js"></script>
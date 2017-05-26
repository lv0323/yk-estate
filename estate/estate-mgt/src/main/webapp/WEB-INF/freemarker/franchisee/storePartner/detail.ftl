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
                                        <a class="btn" ng-href="javascript:;" data-toggle="modal" data-target="#editBasicModel">
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
                                        </tr>
                                        <tr>
                                            <td class="text-muted">区域</td>
                                            <td ng-bind="detail.city" class="text-left"></td>
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
                                        <a class="btn" ng-href="javascript:;" data-toggle="modal" data-target="#editContractModel">
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
                                                <tbody></tbody>
                                            </table>
                                            <div class="pagination-container">
                                                <ul id="contractList_paging" class="pagination"></ul>
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

    <div class="modal fade" id="editBasicModel" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">编辑基本信息</h4>

                </div>
                <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group clearfix">
                            <label class="pull-left control-label">名称</label>
                            <div class="col-lg-5 col-md-5 col-sm-5">
                                <input id="franchiseeName" ng-model="detail.name" type="text" class="form-control">
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="pull-left control-label">区域</label>
                            <div class="col-lg-5 col-md-5 col-sm-5">
                                <input id="franchiseeCity" ng-model="detail.city" type="text" class="form-control">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" ng-click="editBasic()">确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="editContractModel" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">编辑签约信息</h4>

                </div>
                <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group clearfix">

                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" ng-click="editContract()">确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>

</div>
<!-- /.content-wrapper -->

<#include "/common/footer.ftl" />
<script src="${contextPath!}/js/app/franchisee/detailFranchisee.js"></script>
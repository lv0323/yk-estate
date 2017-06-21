<link href="${contextPath}/css/app/fang/index.css" rel="stylesheet">

<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper" id="detailAgentWrapper" ng-controller="DetailAgentCtrl">
    <section class="content-header">
        <ol class="breadcrumb">
            <li>
                <a href="#">
                    <i class="fa fa-home fa-lg" aria-hidden="true"></i>
                    信誉平台管理
                </a>
            </li>
            <li>
                审核管理
            </li>
            <li class="active">
                中介详情
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
                                <h3 class="box-title">中介详情</h3>
                            </div>
                            <div class="box-body">
                                <div class="box-header">
                                    <h4 class="text-blue">基本信息</h4>
                                    <div class="box-tools">
                                        <a class="btn" data-toggle="modal" data-target="#editAgentDialog">
                                            <i class="fa fa-pencil"></i>编辑
                                        </a>
                                    </div>
                                </div>
                                <div class="page">
                                    <table class="table">
                                        <tbody>
                                        <tr>
                                            <td class="text-muted">名称</td>
                                            <td ng-bind="agent.detail && agent.detail.name" class="text-left"></td>
                                            <td class="text-muted">状态</td>
                                            <td ng-bind="agent.detail.status && agent.detail.status.label" class="text-left"></td>
                                        </tr>
                                        <tr>
                                            <td class="text-muted">好评数</td>
                                            <td ng-bind="agent.detail && agent.detail.positiveCount" class="text-left"></td>
                                            <td class="text-muted">差评数</td>
                                            <td ng-bind="agent.detail && agent.detail.negativeCount" class="text-left"></td>
                                        </tr>
                                        <tr>
                                            <td class="text-muted">浏览数</td>
                                            <td ng-bind="agent.detail && agent.detail.visitCount" class="text-left"></td>
                                            <td class="text-muted">评论数</td>
                                            <td ng-bind="agent.detail && agent.detail.commentCount" class="text-left"></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="box-body">
                                <div class="box-header">
                                    <h4 class="text-blue">评论列表</h4>
                                </div>
                                <div class="page">
                                    <form class="form-horizontal">
                                        <div class="form-group clearfix">
                                            <table class="list table table-bordered table-hover">
                                                <thead><tr>
                                                    <th><span>评论人</span></th>
                                                    <th><span>评论时间</span></th>
                                                    <th><span>评论内容</span></th>
                                                    <th><span>好评数</span></th>
                                                    <th><span>标签</span></th>
                                                    <th class="text-right"><span>操作</span></th>
                                                </tr></thead>
                                                <tbody>
                                                    <tr ng-repeat="comment in agent.commentList">
                                                        <td>
                                                            <div class="img-circle-container">
                                                                <img ng-src="{{comment.avatar}}" style="width: 45px;" class="img-circle">
                                                            </div>
                                                            {{comment.nicky}}</td>
                                                        </td>
                                                        <td ng-bind="comment.createTime"></td>
                                                        <td ng-bind="comment.content"></td>
                                                        <td ng-bind="comment.positiveCount"></td>
                                                        <td>
                                                            <span class="badge badge-info" style="margin-left: 5px;" ng-repeat="tag in comment.tags">
                                                                {{tag}}
                                                            </span>
                                                        </td>
                                                        <td class="text-right"><a class="btn" ng-click="deleteCmt(comment.id)">删除</a></td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <div class="pagination-container">
                                                <ul id="comment_paging" class="pagination"></ul>
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
    <div class="modal fade" id="editAgentDialog" tabindex="-1" role="dialog" aria-labelledby="editAgentLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="close">
                        <i class="fa fa-times" aria-hidden="true"></i>
                    </button>
                    <h4 class="modal-title" id="editAgentLabel">编辑中介</h4>
                </div>
                <div class="modal-body">
                    <form id="editAgentForm" class="form-horizontal">
                        <div class="form-group clearfix">
                            <label class="pull-left control-label">好评数</label>
                            <div class="col-lg-5 col-md-5 col-sm-5">
                                <input ng-model="toEditAgent.positiveCount" type="text" class="form-control" required>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="pull-left control-label">差评数</label>
                            <div class="col-lg-5 col-md-5 col-sm-5">
                                <input ng-model="toEditAgent.negativeCount" type="text" class="form-control" required>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="pull-left control-label">浏览数</label>
                            <div class="col-lg-5 col-md-5 col-sm-5">
                                <input ng-model="toEditAgent.visitCount" type="text" class="form-control" required>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" ng-click="confirmEditAgent()">确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- /.content-wrapper -->

<#include "/common/footer.ftl" />
<script src="${contextPath!}/js/app/creditMgt/detail.js"></script>
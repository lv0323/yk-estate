<link href="${contextPath}/css/app/fang/index.css" rel="stylesheet">

<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper" id="creditMgtListWrapper" ng-controller="CreditMgtListCtrl">
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
                                    <a class="btn" id="addAgentBtn" data-toggle="modal" data-target="#addAgentDialog">
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
                                <form class="form-inline">
                                    <div id="searchList" ng-cloak class="clearfix">
                                        <div class="col-lg-4 col-md-4 col-sm-4">
                                            <div class="input-group" style="width:100%;">
                                                <input placeholder="通过公司名查询" class="form-control" ng-model="toSearchAgentName" type="text">
                                                <span class="input-group-btn">
                                                <button type="button" class="btn btn-primary btn-sm" ng-click="setFilterType('corpName' ,toSearchAgentName)"><i class="fa fa-search"></i>查询</button>
                                            </span>
                                            </div>
                                        </div>
                                        <div class="clearfix"></div>
                                        <div class="collapse-box" ng-show="page.collapse">
                                            <div class="form-group sortlist">
                                                <div class="form-group sortlist">
                                                    <label class="control-label">审核状态</label>
                                                    <div id="corp_status" class="tj">
                                                        <a href="" ng-class="{'actived': '' == filter.status}" ng-click="setFilterType('status' ,'')">不限</a>
                                                    <#list corpStatusList?if_exists as cs>
                                                        <a href="" ng-class="{'actived': '${cs.name()}' == filter.status}" ng-click="setFilterType('status' ,'${cs.name()}')">
                                                        ${cs.getLabel()}</a>
                                                    </#list>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="table-responsive clearfix" ng-cloak>
                                            <div class="box-body">
                                                <table id="agentList" class="list table table-bordered table-hover">
                                                    <thead>
                                                    <tr>
                                                        <th>公司名称</th>
                                                        <th>状态</th>
                                                        <th>好评数</th>
                                                        <th>差评数</th>
                                                        <th>浏览数</th>
                                                        <th>评论数</th>
                                                        <th>操作</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr  ng-repeat="agent in agentList">
                                                        <td><a ng-href="{{'/mgt/creditMgt/detail?target=.credit&agentId='+agent.id}}" target="_blank">{{agent.name}}</a></td>
                                                        <td><label class="tip" ng-class="{'tip-success': agent.status && agent.status.name =='ACTIVE',
                                                            'tip-warning': agent.status && agent.status.name =='NEW',
                                                            'tip-danger': agent.status && agent.status.name =='SUSPEND'}">{{agent.status && agent.status.label}}</label></td>
                                                        <td><label class="badge badge-success">{{agent.positiveCount}}</label></td>
                                                        <td><label class="badge badge-danger">{{agent.negativeCount}}</label></td>
                                                        <td><label class="badge badge-info">{{agent.visitCount}}</label></td>
                                                        <td><label class="badge badge-warning">{{agent.commentCount}}</label></td>
                                                        <td class="text-right" ng-show="agent.status && agent.status.name == 'NEW' ">
                                                            <a class="btn" ng-click="activateCorp(agent.id)">通过</a>
                                                            <span class="opt-gap"></span>
                                                            <a class="btn" ng-click="rejectCorp(agent.id)">拒绝</a>
                                                        </td>
                                                        <td class="text-right" ng-show="agent.status && agent.status.name == 'ACTIVE' ">
                                                            <a class="btn" ng-click="suspendCorp(agent.id)">冻结</a>
                                                        </td>
                                                        <td class="text-right" ng-show="agent.status && agent.status.name == 'SUSPEND' ">
                                                            <a class="btn" ng-click="activateCorp(agent.id)">激活</a>
                                                        </td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                    </div>
                                    <div class="pagination-container">
                                        <ul id="agentList_paging" class="pagination"></ul>
                                    </div>
                                </form>
                            </div>

                        </div>
                    </section>
                </div>
            </div>
        </div>
    </section>
    <!-- Modal addAgentDialog -->
    <div class="modal fade" id="addAgentDialog" tabindex="-1" role="dialog" aria-labelledby="addAgentLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="close">
                        <i class="fa fa-times" aria-hidden="true"></i>
                    </button>
                    <h4 class="modal-title" id="addAgentLabel">新增中介</h4>
                </div>
                <div class="modal-body">
                    <form id="addAgentForm" class="form-horizontal">
                        <div class="form-group">
                            <label class="control-label">中介名称</label>
                            <div class="col-lg-5 col-md-5 col-sm-5 clearfix">
                                <div class="clearfix">
                                    <div class="col-lg-10 col-md-10 col-sm-10" style="padding:0">
                                        <input type="text" class="form-control" required ng-model="toAddAgentList.firstNewAgent"/>
                                    </div>
                                    <div class="col-lg-2 col-md-2 col-sm-2 clearfix m-t-7">
                                        <a href="#">
                                            <i class="fa fa-plus-circle" aria-hidden="true" ng-click="agentAdd()"></i>
                                        </a>
                                    </div>
                                </div>
                                <div ng-repeat="agent in toAddAgentList.newAgentList track by $index" ng-class="{'m-t-7':true }" class="clearfix">
                                    <div class="col-lg-10 col-md-10 col-sm-10" style="padding:0">
                                        <input type="text" class="form-control" ng-model="toAddAgentList.newAgentList[$index]"/>
                                    </div>
                                    <div class="col-lg-2 col-md-2 col-sm-2 m-t-7">
                                        <a href="#">
                                            <i class="fa fa-minus-circle" aria-hidden="true" ng-click="agentRemove(item, $index)"></i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" ng-click="confirmAddAgent()">确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- /.content-wrapper -->

<#include "/common/footer.ftl" />
<script src="${contextPath!}/js/app/creditMgt/list.js"></script>
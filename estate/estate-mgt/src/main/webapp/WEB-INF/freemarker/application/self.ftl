<link href="${contextPath}/css/app/fang/index.css" rel="stylesheet">
<link href="${contextPath}/css/app/fang/applications.css" rel="stylesheet">
<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />

<div class="content-wrapper" id="MyApplicationsWrapper" ng-controller="MyApplicationsController">
    <section class="content-header">
        <ol class="breadcrumb">
            <li>
                <a href="#">
                    <i class="fa fa-home fa-lg" aria-hidden="true"></i>
                    房源管理
                </a>
            </li>
            <li class="active">
                我的房源申请列表
            </li>
        </ol>
    </section>
    <!--Main content -->
    <section class="content">
        <div class="animated fadeInRight">
            <div class="row">
                <div class="col-lg-12">
                    <div class="box box-solid form-inline" >
                        <div class="box-body clearfix no-padding default-height">
                            <form id="formlist" class="form-inline">
                                <div id="searchList" ng-cloak class="clearfix">
                                    <div class="col-lg-4 col-md-4 col-sm-4" id="searchById">
                                        <div class="input-group" style="width:100%;">
                                            <input placeholder="通过申请编号查询" class="form-control" ng-model="filter.id" type="text">
                                            <span class="input-group-btn">
                                                <button type="button" class="btn btn-primary btn-sm" ng-click="searchById()"><i class="fa fa-search"></i>查询</button>
                                            </span>
                                        </div>
                                    </div>
                                    <div class="clearfix"></div>
                                    <div class="form-group sortlist">
                                        <label class="control-label">申请类型</label>
                                        <div id="allTypes" class="tj">
                                            <a href="" ng-class="{'actived': 'ALL' == pageStatus.type}" ng-click="filterType('ALL')">全部</a>
                                        <#list types?if_exists as type>
                                            <a href="" ng-class="{'actived': '${type.name()}' == pageStatus.type}" ng-click="filterType('${type.name()}')">
                                            ${type.getLabel()}
                                            </a>
                                        </#list>
                                        </div>
                                    </div>
                                    <div class="form-group sortlist">
                                        <label class="control-label">申请状态</label>
                                        <div id="allStatus" class="tj">
                                            <a href="" ng-class="{'actived': 'ALL' == pageStatus.status}" ng-click="filterStatus('ALL')">全部</a>
                                        <#list statusList?if_exists as status>
                                            <a href="" ng-class="{'actived': '${status.name()}' == pageStatus.status}" ng-click="filterStatus('${status.name()}')">
                                            ${status.getLabel()}
                                            </a>
                                        </#list>
                                        </div>
                                    </div>
                                </div>
                                <div id="applicationListPage" class="table-responsive clearfix" ng-cloak>
                                    <div class="media clearfix house-item" ng-repeat="applicationDTO in applicationList">
                                        <div class="media-body">
                                            <div class="col-lg-12 col-md-12 col-sm-12 app-item">
                                                <div class="clearfix">
                                                    <h5 class="media-heading pull-left text-ellipsis" style="width:300px;">
                                                        <a ng-href="{{'/mgt/fangManage/detail?id='+applicationDTO.domain.id}}" target="_blank" class="text-muted" ng-bind="applicationDTO.domain.head"></a>
                                                    </h5>
                                                    <label class="badge pull-left m-l-20" ng-class="{'badge-success':applicationDTO.domain.process.name == 'SUCCESS',
                                                           'badge-info':applicationDTO.domain.process.name == 'PUBLISH',
                                                           'badge-warning':applicationDTO.domain.process.name == 'UN_PUBLISH',
                                                           'badge-danger':applicationDTO.domain.process.name == 'DELEGATE'}">{{applicationDTO.domain.process.label}}</label>
                                                    <label ng-if="applicationDTO.domain.subProcess" class="badge pull-left m-l-5 badge-warning" >{{applicationDTO.domain.subProcess.label}}</label>
                                                    <span class="text-muted m-l-10">
                                                        {{applicationDTO.domain.infoOwner.departmentName}} ~ {{applicationDTO.domain.infoOwner.employeeName}}
                                                    </span>
                                                </div>
                                                <div class="clearfix text-muted">
                                                    <span>授权编号:{{applicationDTO.domain.licenceId}}</span>
                                                    <span class="m-l-10">楼层:{{applicationDTO.domain.floor}}/{{applicationDTO.domain.floorCounts}}F</span>
                                                    <span class="m-l-10">朝向:{{applicationDTO.domain.orientation.label}}</span>
                                                    <span class="m-l-10">装修:{{applicationDTO.domain.decorate.label}}</span>
                                                    <span class="m-l-10">创建时间:{{applicationDTO.domain.createTime|date:'yyyy-MM-dd'}}</span>
                                                </div>
                                                <div class="clearfix m-t-10 text-muted f16">
                                                    <span>申请编号: <span class="text-blue">{{applicationDTO.application.id}}</span></span>
                                                    <span class="badge badge-primary m-l-10" ng-bind="'申请'+ applicationDTO.application.type.label"></span>
                                                    <span class="m-l-10">申请人:<span class="text-blue">{{applicationDTO.applicant.departmentName}}</span>~<span class="text-blue">{{applicationDTO.applicant.name}}</span></span>
                                                    <span class="m-l-10">申请原因: <span class="text-blue">{{applicationDTO.application.applyReason}}</span></span>
                                                    <span class="m-l-10" ng-show="applicationDTO.application.status.name != 'NEW'">审批人: <span ng-class="{'text-success':applicationDTO.application.status.name=='APPROVED','text-danger':applicationDTO.application.status.name=='REJECTED'||applicationDTO.application.status.name=='CLOSED'}">{{applicationDTO.reviewer.departmentName}}~{{applicationDTO.reviewer.name}}</span></span>
                                                    <span class="m-l-10" ng-show="applicationDTO.application.status.name != 'NEW'">审批意见: <span ng-class="{'text-success':applicationDTO.application.status.name=='APPROVED','text-danger':applicationDTO.application.status.name=='REJECTED'||applicationDTO.application.status.name=='CLOSED'}">{{applicationDTO.application.reviewerComments}}</span>
                                                </div>
                                            </div>
                                            <div class="app-operation">
                                                <div class="text-right">
                                                    <label class="badge margin-r-5" ng-class="{'badge-primary':applicationDTO.application.status.name=='NEW',
                                                                                                'badge-success':applicationDTO.application.status.name=='APPROVED',
                                                                                                'badge-danger':applicationDTO.application.status.name=='REJECTED'}">{{applicationDTO.application.status.label}}</label>
                                                    <span class="text-blue">{{applicationDTO.application.createTime|date:'yyyy-MM-dd HH:mm'}}</span>
                                                </div>
                                                <div class="text-right m-t-15">
                                                    <a href="" class="btn btn-default app-btn" ng-show="applicationDTO.application.status.name == 'NEW'" ng-click="closeApplication(applicationDTO.application.id)">关闭申请</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="pagination-container">
                                        <ul id="pagination" class="pagination"></ul>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
<!-- /.content-wrapper -->

<#include "/common/footer.ftl" />
<script src="${contextPath!}/js/app/application/self.js?vn=${bts!}"></script>
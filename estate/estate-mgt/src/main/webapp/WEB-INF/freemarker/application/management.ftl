<link href="${contextPath}/css/app/fang/index.css" rel="stylesheet">

<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />

<!-- -->
<div class="content-wrapper" id="ApplicationsWrapper" ng-controller="ApplicationsController">
    <section class="content-header">
        <ol class="breadcrumb">
            <li>
                <a href="#">
                    <i class="fa fa-home fa-lg" aria-hidden="true"></i>
                    房源管理
                </a>
            </li>
            <li class="active">
                房源审批列表
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
                                            <input placeholder="通过授权编号查询" class="form-control" ng-model="search.id" type="text">
                                            <span class="input-group-btn">
                                                <button type="button" class="btn btn-primary btn-sm" ng-click="searchById()"><i class="fa fa-search"></i>查询</button>
                                            </span>
                                        </div>
                                    </div>
                                    <div class="clearfix"></div>
                                    <div class="form-group sortlist">
                                        <label class="control-label">类型</label>
                                        <div id="allTypes" class="tj">
                                            <a ng-href="javascript::" ng-class="{'actived': 'ALL' == pageStatus.type}" ng-click="filterType('ALL')">全部</a>
                                        <#list types?if_exists as type>
                                            <a ng-href="javascript::" ng-class="{'actived': '${type.name()}' == pageStatus.status}" ng-click="filterType('${type.name()}')">
                                            ${type.getLabel()}
                                            </a>
                                        </#list>
                                        </div>
                                    </div>
                                    <div class="form-group sortlist">
                                        <label class="control-label">状态</label>
                                        <div id="allStatus" class="tj">
                                            <a ng-href="javascript::" ng-class="{'actived': 'ALL' == pageStatus.status}" ng-click="filterStatus('ALL')">全部</a>
                                        <#list statusList?if_exists as status>
                                            <a ng-href="javascript::" ng-class="{'actived': '${status.name()}' == pageStatus.status}" ng-click="filterStatus('${status.name()}')">
                                            ${status.getLabel()}
                                            </a>
                                        </#list>
                                        </div>
                                    </div>
                                </div>
                                <div id="applicationListPage" class="table-responsive clearfix" ng-cloak>
                                    <div class="media clearfix house-item" ng-repeat="applicationDTO in applicationList">
                                        <img class="flagImg" ng-src="{{applicationDTO.domain.bizType.name ==='RENT' ? '/mgt/img/house/rent.png':'/mgt/img/house/sale.png'}}" height="55" width="55">
                                        <a class="pull-left" href="javascript:void(0)">
                                            <img class="media-object" ng-src="{{applicationDTO.domain.imageURI}}" height="75px" width="100px">
                                        </a>
                                        <div class="media-body">
                                            <div class="col-lg-8 col-md-8 col-sm-9" style="padding-right: 0">
                                                <div class="clearfix">
                                                    <h5 class="media-heading pull-left text-ellipsis" style="width:300px;">
                                                        <a ng-href="{{'/mgt/fangManage/detail?id='+applicationDTO.domain.id}}" target="_blank" class="text-muted" ng-bind="applicationDTO.domain.head"></a>
                                                    </h5>
                                                    <label class="badge pull-left m-l-20" ng-class="{'badge-success':applicationDTO.domain.process.name == 'SUCCESS',
                                                           'badge-info':applicationDTO.domain.process.name == 'PUBLISH',
                                                           'badge-warning':applicationDTO.domain.process.name == 'UN_PUBLISH',
                                                           'badge-danger':applicationDTO.domain.process.name == 'DELEGATE'}">{{applicationDTO.domain.process.label}}</label>
                                                    <label ng-if="applicationDTO.domain.subProcess" class="badge pull-left m-l-20 badge-primary" style="margin-left: 8px">{{applicationDTO.domain.subProcess.label}}</label>
                                                    <i class="fa fa-circle  m-l-20" style="font-size:16px;" ng-class="{true:'text-rent', false:'text-sell'}[applicationDTO.domain.bizType.name == 'RENT']"></i>
                                                    <span ng-class="{true:'text-rent', false:'text-sell'}[applicationDTO.domain.bizType.name == 'RENT']" ng-show="applicationDTO.domain.publishTime">[{{(page.now - applicationDTO.domain.publishTime)/(24*6060*1000)|number:0}}]</span>
                                                    <span class="text-muted">
                                                        {{applicationDTO.domain.infoOwner.departmentName}} ~ {{applicationDTO.domain.infoOwner.employeeName}}
                                                    </span>
                                                </div>
                                                <div class="clearfix m-t-10 text-muted">
                                                    <span>申请类别:{{applicationDTO.application.type.label}}</span>
                                                    <span>状态:{{applicationDTO.application.status.label}}</span>
                                                    <span class="m-l-10">
                                                        id: {{applicationDTO.application.id}}
                                                    </span>
                                                </div>
                                                <div class="clearfix m-t-10 text-muted">
                                                    <span>授权编号:{{applicationDTO.domain.licenceId}}</span>
                                                    <span class="m-l-10">
                                                        {{applicationDTO.domain.layoutFormat}}
                                                    </span>
                                                    <span class="m-l-10">{{applicationDTO.domain.floor}}/{{applicationDTO.domain.floorCounts}}F</span>
                                                    <span class="m-l-10">{{applicationDTO.domain.orientation.label}}</span>
                                                    <span class="m-l-10">{{applicationDTO.domain.decorate.label}}</span>
                                                    <span class="m-l-10">{{applicationDTO.domain.createTime|date:'yyyy-MM-dd'}}</span>
                                                </div>
                                            </div>
                                            <div class="col-lg-2 col-md-2 hidden-sm">
                                                <p><strong class="f18">{{applicationDTO.domain.estateArea}}</strong>m<sup>2</sup></p>
                                                <span class="text-muted">{{applicationDTO.domain.realArea}}m<sup>2</sup></span>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3 text-right">
                                                <p><strong class="text-danger f18">{{applicationDTO.domain.publishPrice}}</strong>{{applicationDTO.domain.priceUnit.label}}</p>
                                                <p>{{applicationDTO.domain.unitPrice}}<span ng-if="applicationDTO.domain.priceUnit.name === 'WAN'">元</span><span ng-if="applicationDTO.domain.priceUnit.name !== 'WAN'">{{applicationDTO.domain.priceUnit.label}}</span>/m<sup>2</sup></p>
                                            </div>
                                            <div class="clearfix col-lg-12 col-md-12 col-sm-12">
                                                <div class="pull-left">
                                                    <span class="tip tip-success tag" ng-repeat="tag in applicationDTO.domain.tags" ng-bind="tag.label"></span>
                                                </div>
                                                <div class="pull-left btn-add m-l-20">
                                                    <span class="add-info-operation m-r-20">
                                                        <a ng-href="javascript:;" ng-class="{'actived': applicationDTO.application.status == 'NEW'}" ng-click="approveApplication(applicationDTO.application.id)">
                                                            <i class="fa fa-pencil"></i>通过申请
                                                        </a>
                                                    </span>
                                                </div>
                                                <div class="pull-left btn-add m-l-20">
                                                    <span class="add-info-operation m-r-20">
                                                        <a ng-href="javascript:;" ng-class="{'actived': applicationDTO.application.status == 'NEW'}" ng-click="rejectApplication(applicationDTO.application.id)">
                                                            <i class="fa fa-pencil"></i>拒绝申请
                                                        </a>
                                                    </span>
                                                </div>
                                                <div class="pull-left btn-add m-l-20">
                                                    <span class="add-info-operation m-r-20">
                                                        <a ng-href="javascript:;" ng-class="{'actived': applicationDTO.application.status == 'NEW'}" ng-click="closeApplication(applicationDTO.application.id)">
                                                            <i class="fa fa-pencil"></i>关闭申请
                                                        </a>
                                                    </span>
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
<script src="${contextPath!}/js/app/application/management.js?vn=${bts!}"></script>
<link href="${contextPath}/css/app/fang/index.css" rel="stylesheet">

<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />

<!-- -->
<div class="content-wrapper" id="houseFollowWrapper" ng-controller="FollowCtrl">
    <section class="content-header">
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-home fa-lg" aria-hidden="true"></i>房源管理</a></li>
            <li class="active">房源跟进</li>
        </ol>
    </section>
    <!--Main content -->
    <section class="content">
        <div class="animated fadeInRight">
            <div class="row">
                <div class="col-lg-12">
                    <div class="box box-solid form-inline" >
                        <div class="box-header with-border">
                            <h3 class="box-title pull-left">房源跟进</h3>
                            <div class="box-tools">
                                <a class="btn-collapse icon-down btn" ng-click="triggerCollapse()">
                                    <strong><i class="fa" ng-class="{true:'fa-chevron-up',false:'fa-chevron-down'}[page.collapse]" aria-hidden="true"></i>更多筛选</strong>
                                </a>
                            </div>
                        </div>
                        <div class="box-body clearfix no-padding">
                            <form id="formlist" class="form-inline">
                                <div id="searchList" ng-cloak ng-show="page.collapse">
                                    <div class="collapse-box">
                                        <div class="form-group sortlist">
                                            <label class="control-label" style="vertical-align:top;padding-top:5px;">跟进方式</label>
                                            <div class="tj" id="distract">
                                                <a ng-href="javascript:" ng-class="{'actived': '' == filter.followType}" ng-click="setFilterType('followType', '')">不限</a>
                                                <#list followType?if_exists as type>
                                                    <a ng-href="javascript:" ng-class="{'actived': '${type.name()}' == filter.followType}" ng-click="setFilterType('followType', '${type.name()}')">
                                                        ${type.getLabel()}
                                                    </a>
                                                </#list>
                                            </div>
                                        </div>
                                        <div class="form-group sortlist">
                                            <label class="control-label">跟进日期</label>
                                            <div class="col-lg-3 col-md-3 col-sm-3">
                                                <div class="input-group date form_date" datetimepicker key="minFollowDate" change="setDate">
                                                    <input class="form-control" size="16" placeholder="开始日期" type="text" ng-model="filter.startDate">
                                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                                </div>
                                            </div>
                                            <div class="col-lg-3 col-md-3 col-sm-3">
                                                <div class="input-group date form_date" datetimepicker key="maxFollowDate" change="setDate">
                                                    <input class="form-control" size="16" placeholder="结束日期" type="text" ng-model="filter.endDate">
                                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group sortlist">
                                            <label class="control-label">部门员工</label>
                                            <div class="col-lg-2 col-md-2 col-sm-3">
                                                <select id="departmentId" class="chosen-select-dep">
                                                    <option value="">选择部门</option>
                                                    <option ng-repeat="dep in depList" ng-value="dep.id" repeat-done="initChosen('#departmentId', 'departmentId')">{{dep.name}}</option>
                                                    </select>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3 m-t-7">
                                                <div class="checkbox checkbox-nice" ng-repeat="depExp in depExpList">
                                                    <input name="depExp" id="depExp{{depExp.value}}" ng-model="filter[depExp.key]"  ng-click="setDepExp()" type="checkbox" ng-change="includeChildrenCheck('departmentId')">
                                                    <label for="depExp{{depExp.value}}">{{depExp.name}}</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3">
                                                <select id="employeeId" class="chosen-select-emp">
                                                    <option value="">全部员工</option>
                                                    <option ng-repeat="employee in employeeList" ng-value="employee.id" repeat-done="initChosen('#employeeId', 'employeeId')">{{employee.name}}</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group sortlist">
                                            <label class="control-label">房源归属</label>
                                            <div class="col-lg-2 col-md-2 col-sm-3">
                                                <select id="ioDepartmentId" class="chosen-select-dep">
                                                    <option value="">选择部门</option>
                                                    <option ng-repeat="dep in depList" ng-value="dep.id" repeat-done="initChosen('#ioDepartmentId', 'ioDepartmentId')">{{dep.name}}</option>
                                                    </select>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3 m-t-7">
                                                <div class="checkbox checkbox-nice" ng-repeat="depExp in ioDepExpList">
                                                    <input name="ioDepExp" id="ioDepExp{{depExp.value}}" ng-model="filter[depExp.key]"  ng-click="setDepExp()" type="checkbox" ng-change="includeChildrenCheck('ioDepartmentId')">
                                                    <label for="ioDepExp{{depExp.value}}">{{depExp.name}}</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3">
                                                <select id="ioEmployeeId" class="chosen-select-emp">
                                                    <option value="">全部员工</option>
                                                    <option ng-repeat="employee in ioEmployeeList" ng-value="employee.id" repeat-done="initChosen('#ioEmployeeId', 'ioEmployeeId')">{{employee.name}}</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="getHouseCharPage" class="table-responsive clearfix" ng-cloak>
                                    <div class="table-responsive contractlist">
                                        <table class="table">
                                            <thead>
                                            <tr>
                                                <th>跟进人</th>
                                                <th>跟进方式</th>
                                                <th>跟进日期</th>
                                                <th>交易类型</th>
                                                <th>物业地址</th>
                                                <th>房源状态</th>
                                                <th>发布日期</th>
                                                <th>发布天数</th>
                                            <#--<th class="text-right">操作</th>-->
                                            </tr>
                                            </thead>
                                            <tbody ng-repeat="follow in followList">
                                            <tr>
                                                <td>
                                                    <div class="img-circle-container">
                                                        <img ng-src="{{follow.avatarURI}}" class="img-circle">
                                                    </div>
                                                    {{follow.departmentName}}-{{follow.employeeName}}</td>
                                                <td><label class="badge badge-success">{{follow.followType.label}}</label></td>
                                                <td>{{follow.createTime|date:'yyyy-MM-dd'}}</td>
                                                <td><label class="badge badge-danger">{{follow.fangTiny.bizType.label}}</label></td>
                                                <td><a ng-href="{{'/mgt/fangManage/detail?id='+follow.fangId}}" target="_blank">{{follow.fangTiny.houseLicence &&follow.fangTiny.houseLicence.location}}</a></td>
                                                <td><label class="badge badge-danger">{{follow.fangTiny.process.label}}</label></td>
                                                <td>{{follow.fangTiny.publishTime|date:'yyyy-MM-dd'}}</td>
                                                <td>{{follow.publishedDay}}</td>
                                            <#--<td class="text-right">
                                                <a href="javascript:void(0);" ng-click="ctrl.deleteFollow(follow.id)">删除</a>
                                            </td>-->
                                            </tr>
                                            <tr><td colspan="7">跟进内容:{{follow.content}}</td></tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="pagination-container">
                                        <ul id="follow_paging" class="pagination"></ul>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <div class="modal fade" id="warnModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                    </button>
                    <h4 class="modal-title" id="myModalLabel" ng-bind="page.warn.title">
                    </h4>
                </div>
                <div class="modal-body" ng-bind="page.warn.content">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" ng-click="page.warn.closeF()" data-dismiss="modal">确定
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- /.content-wrapper -->

<#include "/common/footer.ftl" />
<script src="${contextPath!}/js/app/fang/follow.js?vn=${bts!}"></script>
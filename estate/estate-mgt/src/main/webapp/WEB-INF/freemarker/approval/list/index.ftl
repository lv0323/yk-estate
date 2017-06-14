<#include "/common/header.ftl" />
<link rel="stylesheet" href="${contextPath}/css/app/approval/list/index.css">
<#include "/common/sidebar.ftl" />
<div id="approvalList" class="content-wrapper" ng-controller="ListCtrl as ctrl">
    <section class="content-header">
        <ol class="breadcrumb">
            <li><a><i class="fa fa-home fa-lg" aria-hidden="true"></i>表单管理</a></li>
            <li><a href="#/">表单列表</a></li>
        </ol>
    </section>
    <section class="content">
        <div class="row animated fadeInRight">
            <div class="col-lg-12 clearfix" id="content">
                <div class="box box-solid">
                    <div class="box-header">
                        <h3 class="box-title">表单列表</h3>
                    </div>
                    <div class="box-body clearfix no-padding default-height">
                        <div id="box-filter">
                            <div class="collapse-box">
                                <div class="form-group sortlist">
                                    <label class="control-label" style="vertical-align:top;padding-top:5px;">类型</label>
                                    <div class="tj">
                                    <#list ApprovalType?if_exists as type>
                                        <a ng-href="javascript:" ng-class="{'actived': '${type.name()}' == ctrl.filter.type}" ng-click="ctrl.setFilterType('type', '${type.name()}')">
                                        ${type.getLabel()}
                                        </a>
                                    </#list>
                                    </div>
                                </div>
                                <div class="form-group sortlist" ng-if="ctrl.filter.type === 'SIGNING' ">
                                    <label class="control-label" style="vertical-align:top;padding-top:5px;">状态</label>
                                    <div class="tj">
                                        <a ng-href="javascript:" ng-class="{'actived': '' == ctrl.filter.status}" ng-click="ctrl.setFilterType('status', '')">不限</a>
                                    <#list ApprovalStatus?if_exists as type>
                                        <a ng-href="javascript:" ng-class="{'actived': '${type.name()}' == ctrl.filter.status}" ng-click="ctrl.setFilterType('status', '${type.name()}')">
                                        ${type.getLabel()}
                                        </a>
                                    </#list>
                                    </div>
                                </div>
                                <div class="form-group sortlist">
                                    <label class="control-label">日期</label>
                                    <div class="col-lg-3 col-md-3 col-sm-3">
                                        <div class="input-group date form_date" datetimepicker key="startTime" change="datePickChange">
                                            <input class="form-control" size="16" placeholder="开始日期" type="text" ng-model="ctrl.filter.startTime">
                                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                        </div>
                                    </div>
                                    <div class="col-lg-3 col-md-3 col-sm-3">
                                        <div class="input-group date form_date" datetimepicker key="endTime" change="datePickChange">
                                            <input class="form-control" size="16" placeholder="结束日期" type="text" ng-model="ctrl.filter.endTime">
                                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group sortlist clearfix">
                                    <label class="control-label">员工</label>
                                        <div class="col-lg-3 col-md-3 col-sm-3 part-a-select">
                                            <select id="companyListDrop">
                                                <option value="">签约公司</option>
                                                <option ng-repeat="company in ctrl.config.companyList" ng-value="company.id" repeat-done="ctrl.initApplyChosen('#companyListDrop', 'applyCompanyId')">{{company.name}}</option>
                                            </select>
                                        </div>
                                        <div class="col-lg-3 col-md-3 col-sm-3 part-a-select">
                                            <select id="signatureDepListDrop">
                                                <option value="">公司部门</option>
                                                <option ng-repeat="signatureDep in ctrl.config.signatureDepList" ng-value="signatureDep.id" repeat-done="ctrl.initApplyChosen('#signatureDepListDrop', 'applyDeptId')">{{signatureDep.name}}</option>
                                            </select>
                                        </div>
                                        <div class="col-lg-3 col-md-3 col-sm-3 part-a-select">
                                            <select id="signatureRepListDrop">
                                                <option value="">部门员工</option>
                                                <option ng-repeat="signatureRep in ctrl.config.signatureRepList" ng-value="signatureRep.id" repeat-done="ctrl.initApplyChosen('#signatureRepListDrop', 'applyId')">{{signatureRep.name}}</option>
                                            </select>
                                        </div>
                                </div>
                            </div>
                        </div>
                        <div class="clearfix">
                            <a class="btn-link pull-right m-r-15 export-link m-t-10" target="_blank" ng-href="{{ctrl.config.exportLink.baseUrl + ctrl.config.exportLink.queryString}}">导出报表</a>
                        </div>
                        <div class="table-responsive contractlist">
                            <table class="list table table-bordered table-hover dataTable no-footer" ng-cloak>
                                <thead>
                                <tr>
                                    <th>提交人</th>
                                    <th ng-if-start="ctrl.filter.type === ctrl.config.type.LEAVING">外出时间</th>
                                    <th>返回时间</th>
                                    <th>外出地点</th>
                                    <th>外出事由</th>
                                    <th ng-if-end="ctrl.filter.type === ctrl.config.type.LEAVING">未打卡事由</th>

                                    <th ng-if-start="ctrl.filter.type === ctrl.config.type.BIZ_TRIP">出差时间</th>
                                    <th>返回时间</th>
                                    <th>出差天数</th>
                                    <th>出差事由</th>
                                    <th>出差成果</th>
                                    <th>待解决问题</th>
                                    <th>对接资源信息</th>
                                    <th ng-if-end="ctrl.filter.type === ctrl.config.type.BIZ_TRIP">出差费用</th>

                                    <th ng-if-start="ctrl.filter.type === ctrl.config.type.COLD_VISIT">客户公司名称</th>
                                    <th>对方负责人姓名</th>
                                    <th>负责人身份</th>
                                    <th>联系方式</th>
                                    <th>门店地址</th>
                                    <th>首次拜访时间</th>
                                    <th>首次拜访情况说明</th>
                                    <th>二次拜访时间</th>
                                    <th>二次拜访情况说明</th>
                                    <th>三次拜访时间</th>
                                    <th>三次拜访情况说明</th>
                                    <th ng-if-end="ctrl.filter.type === ctrl.config.type.COLD_VISIT">去访人员姓名</th>

                                    <th>提交时间</th>

                                    <th ng-if-start="ctrl.filter.type === ctrl.config.type.SIGNING">公司名称</th>
                                    <th>对方负责人姓名</th>
                                    <th>签约类型</th>
                                    <th>签约年限</th>
                                    <th>门店数</th>
                                    <th>签约起始日期</th>
                                    <th ng-if-end="ctrl.filter.type === ctrl.config.type.SIGNING">签约金额</th>



                                    <th ng-if="ctrl.filter.type === 'SIGNING'">状态</th>
                                </tr>
                                </thead>
                                <tbody ng-cloak>
                                <tr ng-repeat="approval in ctrl.approvalList">
                                    <td>
                                        <span ng-if="ctrl.filter.type !== ctrl.config.type.SIGNING">
                                            {{approval.applyCompanyShortName}}-{{approval.applyName}}
                                        </span>
                                        <a href="" ng-click="ctrl.detail(approval)" ng-if="ctrl.filter.type === ctrl.config.type.SIGNING">{{approval.applyCompanyShortName}}-{{approval.applyName}}</a>
                                    </td>
                                    <td ng-if-start="ctrl.filter.type === ctrl.config.type.LEAVING">{{approval.leaving.startTime}}</td>
                                    <td>{{approval.leaving.endTime}}</td>
                                    <td>{{approval.leaving.location}}</td>
                                    <td>{{approval.leaving.reason}}</td>
                                    <td ng-if-end="ctrl.filter.type === ctrl.config.type.LEAVING">{{approval.leaving.noClockReason}}</td>


                                    <td ng-if-start="ctrl.filter.type === ctrl.config.type.BIZ_TRIP">{{approval.bizTrip.startTime}}</td>
                                    <td>{{approval.bizTrip.endTime}}</td>
                                    <td>{{approval.bizTrip.days}}</td>
                                    <td>{{approval.bizTrip.reason}}</td>
                                    <td>{{approval.bizTrip.outcome}}</td>
                                    <td>{{approval.bizTrip.problem}}</td>
                                    <td>{{approval.bizTrip.resource}}</td>
                                    <th ng-if-end="ctrl.filter.type === ctrl.config.type.BIZ_TRIP">{{approval.bizTrip.costs}}</th>

                                    <td ng-if-start="ctrl.filter.type === ctrl.config.type.COLD_VISIT">{{approval.coldVisit.companyName}}</td>
                                    <td>{{approval.coldVisit.bossName}}</td>
                                    <td>{{approval.coldVisit.bossType&&approval.coldVisit.bossType.label}}</td>
                                    <td>{{approval.coldVisit.contactInfo}}</td>
                                    <td>{{approval.coldVisit.address}}</td>
                                    <td>{{approval.coldVisit.visitTime1}}</td>
                                    <td>{{approval.coldVisit.report1}}</td>
                                    <td>{{approval.coldVisit.visitTime2}}</td>
                                    <td>{{approval.coldVisit.report2}}</td>
                                    <td>{{approval.coldVisit.visitTime3}}</td>
                                    <td>{{approval.coldVisit.report3}}</td>
                                    <th ng-if-end="ctrl.filter.type === ctrl.config.type.COLD_VISIT">{{approval.coldVisit.followers}}</th>

                                    <td ng-bind="approval.createTime|date:'yyyy-MM-dd HH:mm'"></td>

                                    <td ng-if-start="ctrl.filter.type === ctrl.config.type.SIGNING">{{approval.signing.companyName}}</td>
                                    <td>{{approval.signing.bossName}}</td>
                                    <td>{{ctrl.config.companyType[approval.signing.companyType]}}</td>
                                    <td>{{approval.signing.years}}</td>
                                    <td>{{approval.signing.storeCount}}</td>
                                    <td>{{approval.signing.startDate}}</td>
                                    <td ng-if-end="ctrl.filter.type === ctrl.config.type.SIGNING">{{approval.signing.price}}</td>

                                    <td ng-if="ctrl.filter.type === 'SIGNING'" >
                                        <label ng-if="approval.status.name != 'CREATED'" class="badge" ng-class="{'badge-success':approval.status.name == 'APPROVED',
                                                           'badge-danger':approval.status.name == 'REJECTED'}" ng-bind="approval.status.label"></label>
                                        <div ng-if="approval.status.name == 'CREATED' && ctrl.filter.type === 'SIGNING'">
                                            <a href="" class="text-blue" style= "margin-left: 16px;" ng-click="ctrl.detail(approval)">审核</a>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="pagination-container">
                            <ul id="list_paging" class="pagination"></ul>
                        </div>
                    </div>
            </div>
        </div>
    </section>
    <div class="modal fade layout-dialog" id="approvalDetail" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">签约详情</h4>
                </div>
                <div class="modal-body">
                    <div class="table-responsive contractlist">
                        <table class="table table-bordered table-hover dataTable no-footer" ng-cloak>
                            <tbody>
                            <tr>
                                <td>申请人:</td>
                                <td>{{ctrl.currentApproval.applyName}}</td>
                                <td>申请时间:</td>
                                <td>{{ctrl.currentApproval.createTime|date:'yyyy-MM-dd HH:mm'}}</td>
                            </tr>
                            <tr>
                                <td>城市:</td>
                                <td>{{ctrl.currentApproval.signing.cityName}}</td>
                                <td>签约类型:</td>
                                <td>{{ctrl.config.companyType[ctrl.currentApproval.signing.companyType]}}</td>
                            </tr>
                            <tr>
                                <td>公司名称:</td>
                                <td>{{ctrl.currentApproval.signing.companyName}}</td>
                                <td>公司简称:</td>
                                <td>{{ctrl.currentApproval.signing.companyAbbr}}</td>
                            </tr>
                            <tr>
                                <td>详细地址:</td>
                                <td colspan="3">{{ctrl.currentApproval.signing.address}}</td>
                            </tr>
                            <tr>
                                <td>对方负责人姓名:</td>
                                <td>{{ctrl.currentApproval.signing.bossName}}</td>
                                <td>联系方式:</td>
                                <td>{{ctrl.currentApproval.signing.bossMobile}}</td>
                            </tr>
                            <tr>
                                <td>本公司负责人员姓名:</td>
                                <td>{{ctrl.currentApproval.signing.partAInChargeName}}</td>
                                <td>备注:</td>
                                <td>{{ctrl.currentApproval.signing.note}}</td>
                            </tr>
                            <tr>
                                <td>签约年限:</td>
                                <td>{{ctrl.currentApproval.signing.years}}</td>
                                <td>门店数:</td>
                                <td>{{ctrl.currentApproval.signing.storeCount}}</td>
                            </tr>
                            <tr>
                                <td>签约起始日期:</td>
                                <td>{{ctrl.currentApproval.signing.startDate}}</td>
                                <td>签约结束日期:</td>
                                <td>{{ctrl.currentApproval.signing.endDate}}</td>
                            </tr>
                            <tr>
                                <td>签约金额:</td>
                                <td>{{ctrl.currentApproval.signing.price}}</td>
                                <td></td>
                                <td></td>
                            </tr>

                            <tr ng-class="{true:'approval-data',false:'rejected-data'}[ctrl.currentApproval.status.name === 'APPROVED']" ng-if-start="ctrl.currentApproval.status.name !== 'CREATED'">
                                <td>审批人:</td>
                                <td>{{ctrl.currentApproval.approverName}}</td>
                                <td>审批时间</td>
                                <td>{{ctrl.currentApproval.approvalTime|date:'yyyy-MM-dd HH:mm'}}</td>
                            </tr>
                            <tr ng-class="{true:'approval-data',false:'rejected-data'}[ctrl.currentApproval.status.name === 'APPROVED']"  ng-if-end="ctrl.currentApproval.status.name !== 'CREATED'">
                                <td>审批结果:</td>
                                <td>{{ctrl.currentApproval.status.label}}</td>
                                <td></td>
                                <td></td>
                            </tr><tr ng-class="{true:'approval-data',false:'rejected-data'}[ctrl.currentApproval.status.name === 'APPROVED']" ng-if-end="ctrl.currentApproval.status.name !== 'CREATED'">
                                <td>审批意见</td>
                                <td colspan="3">{{ctrl.currentApproval.comment}}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="modal-footer">
                    <button ng-if="ctrl.currentApproval.status.name !== 'CREATED'" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button ng-if-start="ctrl.currentApproval.status.name === 'CREATED'" type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#layoutModel" ng-click="ctrl.approved(ctrl.currentApproval.id)">通过</button>
                    <button ng-if-end="ctrl.currentApproval.status.name === 'CREATED'" type="button" class="btn btn-danger" data-toggle="modal" data-target="#layoutModel" ng-click="ctrl.rejected(ctrl.currentApproval.id)">拒绝</button>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "/common/footer.ftl"/>
<script src="${contextPath!}/js/app/approval/list/index.js"></script>

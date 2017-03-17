<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <section class="content-header">
        <ol class="breadcrumb">
            <li>
                <a href="#">
                    <i class="fa fa-home fa-lg" aria-hidden="true"></i>
                    系统设置
                </a>
            </li>
            <li class="active">
                操作日志
            </li>
        </ol>
    </section>

    <section class="content">
        <div class="animated fadeInRight">
            <div class="row">
                <div class="col-lg-12">
                    <section class="connectedSortable">
                        <div class="col-lg-2 col-md-3 col-sm-3">
                            <div class="box box-solid">
                                <div class="box-body no-padding">
                                    <ul class="nav nav-pills nav-stacked">
                                    <#list AuditSubjectList?if_exists as asl>
                                        <li>
                                            <a data-name="${asl.name()}">${asl.getLabel()}</a>
                                        </li>
                                    </#list>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-10 col-md-9 col-sm-9">
                            <div class="box box-solid">
                                <div class="box-header">
                                    <h3 class="box-title">操作日志</h3>
                                    <div class="box-tools">
                                        <a class="btn" id="filterOperationLogBtn">
                                            <i class="fa fa-filter" aria-hidden="true"></i>
                                            筛选
                                        </a>
                                    </div>
                                </div>

                                <div class="box-body">
                                    <form class="form-inline">
                                        <div id="box-filter" style="display:none;">
                                            <div class="form-group sortlist form-inline">
                                                <label class="control-label">筛选日期</label>
                                                <div class="col-lg-4 col-md-4 col-sm-4">
                                                    <div class="input-group date">
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                        </div>
                                                        <input type="text" class="form-control pull-right" id="logStartDate" placeholder="开始日期">
                                                    </div>
                                                </div>
                                                <label class="pull-left" style="margin-top: 5px;">~</label>
                                                <div class="col-lg-4 col-md-4 col-sm-4">
                                                    <div class="input-group date">
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                        </div>
                                                        <input type="text" class="form-control pull-right" id="logEndDate" placeholder="截止日期">
                                                    </div>
                                                </div>
                                                <button id="confirmFilterOperationLogBtn" class="btn btn-primary">查询</button>
                                            </div>
                                        </div>
                                    </form>

                                    <table id="operationLogList" class="list table table-bordered table-hover">
                                        <col width="20%" />
                                        <col width="10%" />
                                        <col width="12%" />
                                        <col width="58%" />
                                        <thead><tr>
                                            <th><span>操作时间</span></th>
                                            <th><span>部门</span></th>
                                            <th><span>操作者</span></th>
                                            <th><span>操作内容</span></th>
                                        </tr></thead>
                                        <tbody></tbody>
                                    </table>
                                    <div class="pagination-container">
                                        <ul id="operationLogList_paging" class="pagination"></ul>
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
<script src="${contextPath!}/js/app/system/operationLog.js"></script>
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
                                <header class="box-header">
                                    <h3 class="box-title">操作日志</h3>
                                    <div class="box-tools">
                                        <a class="btn" id="filterOperationLogBtn">
                                            <i class="fa fa-filter" aria-hidden="true"></i>
                                            筛选
                                        </a>
                                    </div>
                                </header>

                                <div class="box-body form-inline form-horizontal">
                                    <div id="box-filter" class="form-horizontal" style="display:none;">
                                        <div class="form-group ">
                                            <label class="col-lg-2 col-md-2 col-sm-2 control-label">在职｜离职： </label>
                                            <div class="col-lg-2 col-md-2 col-sm-2">
                                                <select id="quitPosition" class="form-control btn-group dropup">
                                                    <option value="false">在职</option>
                                                    <option value="true">离职</option>
                                                    <option value="-1">全部</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <table class="list table table-bordered table-hover">
                                        <thead><tr>
                                            <th><span>操作时间</span></th>
                                            <th><span>部门</span></th>
                                            <th><span>员工</span></th>
                                            <th><span>操作内容</span></th>
                                            <th class="text-right"><span>操作</span></th>
                                        </tr></thead>
                                        <tbody></tbody>
                                    </table>
                                    <ul id="operationLogList_paging" class="pagination"></ul>
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
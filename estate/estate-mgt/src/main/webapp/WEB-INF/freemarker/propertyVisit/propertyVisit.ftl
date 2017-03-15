<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />

<!-- Modal completeVisitDialog -->
<div class="modal fade" id="completeVisitDialog" tabindex="-1" role="dialog" aria-labelledby="completeVisitLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <i class="fa fa-times" aria-hidden="true"></i>
                </button>
                <h4 class="modal-title" id="completeVisitLabel">完成带看</h4>
            </div>
            <input id="completeVisitId" class="hidden">
            <div class="modal-body">
                确定执行完成带看操作吗？
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="confirmCompleteVisitBtn">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal cancelVisitDialog -->
<div class="modal fade" id="cancelVisitDialog" tabindex="-1" role="dialog" aria-labelledby="cancelVisitLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <i class="fa fa-times" aria-hidden="true"></i>
                </button>
                <h4 class="modal-title" id="cancelVisitLabel">取消带看</h4>
            </div>
            <input id="cancelVisitId" class="hidden">
            <div class="modal-body">
                确定执行取消带看操作吗？
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="confirmCancelVisitBtn">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <section class="content-header">
        <ol class="breadcrumb">
            <li>
                <a href="#">
                    <i class="fa fa-home fa-lg" aria-hidden="true"></i>
                    房源带看
                </a>
            </li>
            <li class="active">
                带看列表
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
                                <h3 class="box-title">带看列表</h3>
                                <div class="box-tools">
                                    <a class="btn" id="addVisitBtn" href="/mgt/propertyVisit/addPropertyVisit?target=.prtyVisit">
                                        <i class="fa fa-plus" aria-hidden="true"></i>
                                        新增带看
                                    </a>
                                    <span class="opt-gap"></span>
                                    <a class="btn" id="filterPropertyVisitBtn">
                                        <i class="fa fa-filter" aria-hidden="true"></i>
                                        筛选
                                    </a>
                                </div>
                            </div>
                            <div class="box-body">
                                <form class="form-inline">
                                    <div id="box-filter"  style="display: none;">
                                        <div class="form-group sortlist">
                                            <label class="control-label">状态</label>
                                            <div class="tj">
                                                <a name="visitStatus" class="actived" title="">不限</a>
                                            <#list showingOperation?if_exists as status>
                                                <a name="visitStatus" title="${status.name()}">${status.getLabel()}</a>
                                            </#list>
                                            </div>
                                        </div>
                                        <div class="form-group sortlist">
                                            <label class="control-label">部门员工</label>
                                            <div class="col-lg-2 col-md-2 col-sm-3">
                                                <select id="departmentList" class="chosen-select-dep">
                                                    <option value="">选择部门</option>
                                                </select>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3 m-t-7">
                                                <div class="checkbox checkbox-nice">
                                                    <input id="inferiorInc" type="checkbox">
                                                    <label id="inferiorIncLabel" for="inferiorInc">含下级</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3">
                                                <select id="employeeList" class="chosen-select-emp">
                                                    <option value="">全部员工</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group sortlist form-inline">
                                            <label class="control-label">带看时间</label>
                                            <div class="col-lg-2 col-md-3 col-sm-3">
                                                <div class="input-group date">
                                                    <div class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </div>
                                                    <input type="text" class="form-control pull-right" id="minCreateDate" placeholder="带看起始日期">
                                                </div>
                                            </div>
                                            <div class=" col-lg-2 col-md-3 col-sm-3">
                                                <div class="input-group date">
                                                    <div class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </div>
                                                    <input type="text" class="form-control pull-right" id="maxCreateDate" placeholder="带看截止日期">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>

                                <!-- table -->
                                <table id="propertyVisitList" class="list table table-bordered table-hover">
                                    <thead><tr>
                                        <th><span>带看员工</span></th>
                                        <th><span>带看客户</span></th>
                                        <th><span>带看房源地址</span></th>
                                        <th><span>带看生成时间</span></th>
                                        <th><span>带看结束时间</span></th>
                                        <th><span>状态</span></th>
                                        <th><span>操作</span></th>
                                    </tr></thead>
                                    <tbody></tbody>
                                </table>
                                <div class="pagination-container">
                                    <ul id="propertyVisitList_paging" class="pagination"></ul>
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
<script src="${contextPath!}/js/app/propertyVisit/propertyVisit.js"></script>
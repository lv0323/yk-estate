<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />

<!-- Modal completeDealDialog -->
<div class="modal fade" id="completeDealDialog" tabindex="-1" role="dialog" aria-labelledby="completeDealLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <i class="fa fa-times" aria-hidden="true"></i>
                </button>
                <h4 class="modal-title" id="completeDealLabel">完成成交</h4>
            </div>
            <input id="completeDealId" class="hidden">
            <div class="modal-body">
                确定执行完成成交操作吗？
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="confirmCompleteDealBtn">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal cancelDealDialog -->
<div class="modal fade" id="cancelDealDialog" tabindex="-1" role="dialog" aria-labelledby="cancelDealLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <i class="fa fa-times" aria-hidden="true"></i>
                </button>
                <h4 class="modal-title" id="cancelDealLabel">取消成交</h4>
            </div>
            <input id="cancelDealId" class="hidden">
            <div class="modal-body">
                确定执行取消成交操作吗？
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="confirmCancelDealBtn">确定</button>
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
                    合同管理
                </a>
            </li>
            <li class="active">
                成交管理
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
                                <h3 class="box-title">成交列表</h3>
                                <div class="box-tools">
                                    <a class="btn" id="addDealBtn" href="/mgt/contract/addDeal?target=.contract">
                                        <i class="fa fa-plus" aria-hidden="true"></i>
                                        新增成交
                                    </a>
                                    <span class="opt-gap"></span>
                                    <a class="btn" id="filterDealBtn">
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
                                                <a name="DealStatus" class="actived" title="">不限</a>
                                            <#list contractOperation?if_exists as status>
                                                <a name="DealStatus" title="${status.name()}">${status.getLabel()}</a>
                                            </#list>
                                            </div>
                                        </div>
                                        <div class="form-group sortlist">
                                            <label class="control-label">房屋用途</label>
                                            <div class="tj">
                                                <a name="houseType" class="actived" title="">不限</a>
                                            <#list houseType?if_exists as type>
                                                <a name="houseType" title="${type.name()}">${type.getLabel()}</a>
                                            </#list>
                                            </div>
                                        </div>
                                        <div class="form-group sortlist">
                                            <label class="control-label">成交类型</label>
                                            <div class="tj">
                                                <a name="businessType" class="actived" title="">不限</a>
                                            <#list bizType?if_exists as biz>
                                                <a name="businessType" title="${biz.name()}">${biz.getLabel()}</a>
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
                                            <label class="control-label">成交时间</label>
                                            <div class="col-lg-3 col-md-3 col-sm-3">
                                                <div class="input-group date">
                                                    <div class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </div>
                                                    <input type="text" class="form-control pull-right" id="minCreateDate" placeholder="创建日期">
                                                </div>
                                            </div>
                                            <div class=" col-lg-3 col-md-3 col-sm-3">
                                                <div class="input-group date">
                                                    <div class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </div>
                                                    <input type="text" class="form-control pull-right" id="maxCreateDate" placeholder="截止日期">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <div id="DealList"></div>
                                <div class="pagination-container">
                                    <ul id="DealList_paging" class="pagination"></ul>
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
<script src="${contextPath!}/js/app/contract/deal/deal.js"></script>
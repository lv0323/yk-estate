<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />

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
                                    <div id="box-filter"  style="display: block;">
                                        <div class="form-group sortlist">
                                            <label class="control-label">状态</label>
                                            <div class="tj">
                                            <#list showingOperation?if_exists as status>
                                                <a name="visitStatus" id="${status.name()}">${status.getLabel()}</a>
                                            </#list>
                                            </div>
                                        </div>
                                        <div class="form-group sortlist">
                                            <label class="control-label">组织信息</label>
                                            <div class="tj">

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
                                        <div class="form-group sortlist center">
                                            <div class="col-sm-12">
                                                <button type="button" class="btn btn-primary" id="confirmFilterPropertyVisitBtn">筛选</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>

                                <!-- table -->
                                <table id="propertyVisitList" class="list table table-bordered table-hover">
                                    <thead><tr>
                                        <th><span>带看员工</span></th>
                                        <th><span>带看客户</span></th>
                                        <th><span>带看房源编号</span></th>
                                        <th><span>带看生成时间</span></th>
                                        <th><span>带看结束时间</span></th>
                                        <th><span>状态</span></th>
                                    </tr></thead>
                                    <tbody></tbody>
                                </table>
                                <ul id="propertyVisitList_paging" class="pagination"></ul>
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
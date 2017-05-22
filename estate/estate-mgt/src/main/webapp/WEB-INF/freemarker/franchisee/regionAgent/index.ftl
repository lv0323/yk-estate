<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <section class="content-header">
        <ol class="breadcrumb">
            <li>
                <a href="#">
                    <i class="fa fa-home fa-lg" aria-hidden="true"></i>
                    加盟商管理
                </a>
            </li>
            <li class="active">
                区域代理管理
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
                                <h3 class="box-title">区域代理列表</h3>
                                <div class="box-tools">
                                    <a class="btn" id="addPartnerBtn" ng-href="/mgt/franchisee/regionAgent/createRegionAgent?target=.franchisee">
                                        <i class="fa fa-plus" aria-hidden="true"></i>
                                        新增区域代理
                                    </a>
                                    <span class="opt-gap"></span>
                                    <a class="btn" id="filterPartnerBtn">
                                        <i class="fa fa-filter" aria-hidden="true"></i>
                                        筛选
                                    </a>
                                </div>
                            </div>

                            <div class="box-body">
                                <form class="form-inline">
                                    <div id="box-filter"  style="display: block;">
                                        <div class="form-group sortlist">
                                            <label class="control-label">城市</label>
                                            <div class="tj">
                                                <a name="city" class="actived" title="">不限</a>

                                            </div>
                                        </div>
                                    </div>
                                </form>

                                <div id="PartnerList"></div>
                                <div class="pagination-container">
                                    <ul id="PartnerList_paging" class="pagination"></ul>
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
<script src="${contextPath!}/js/app/franchisee/regionAgent/regionAgent.js"></script>
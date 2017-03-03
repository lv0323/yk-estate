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
                                    <a class="btn" id="addVisitBtn" href="/mgt/propertyVisit/addPropertyVisit.ftl">
                                        <i class="fa fa-plus" aria-hidden="true"></i>
                                        新增带看
                                    </a>
                                </div>
                            </div>
                            <div class="box-body">
                                <!-- table -->
                                <table id="propertyVisitList" class="list table table-bordered table-hover">
                                    <thead><tr>
                                        <th><span>带看员工</span></th>
                                        <th><span>带看客户</span></th>
                                        <th><span>带看房源</span></th>
                                        <th class="text-right"><span>操作</span></th>
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
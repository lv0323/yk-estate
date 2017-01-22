<!-- DataTables -->
<#--<link rel="stylesheet" href="${contextPath}/js/plugins/datatables/dataTables.bootstrap.css">-->
<link href="${contextPath}/css/org/orgnization.css" rel="stylesheet">
<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />

<div class="content-wrapper">
    <section class="content-header">
        <ol class="breadcrumb">
            <li>
                <a href="#">
                    <i class="fa fa-home fa-lg" aria-hidden="true"></i>
                    组织机构
                </a>
            </li>
            <li class="active">
                员工管理
            </li>
        </ol>
    </section>

    <!--Main content -->
    <section class="content">
        <div class="animated fadeInRight">
            <div class="row">
                <div class="col-lg-12">
                    <section class="connectedSortable">
                        <!-- left side -->
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <div class="box box-solid">
                                <div class="box-header with-border">
                                    <h3 class="box-title">所有部门</h3>
                                </div>
                                <div class="">

                                </div>
                            </div>
                        </div>
                        <!-- right side -->
                        <div class="col-lg-9 col-md-9 col-sm-9">
                            <div class="box box-solid">
                                <div class="box-header">
                                    <h3 class="box-title">员工管理</h3>
                                    <div class="box-tools">
                                        <a class="btn" id="addEmployeeBtn" data-toggle="modal" data-target="#addEmployeeDialog">
                                            <i class="fa fa-plus" aria-hidden="true"></i>
                                            新增员工
                                        </a>
                                    </div>
                                </div>
                                <div class="box-body">
                                    <!-- table -->
                                    <table id="employeeList" class="list table table-bordered table-hover">
                                        <thead><tr>
                                            <th><span>编号</span></th>
                                            <th><span>姓名</span></th>
                                            <th><span>状态</span></th>
                                            <th><span>电话</span></th>
                                            <th><span>岗位</span></th>
                                            <th class="text-right"><span>操作</span></th>
                                        </tr></thead>
                                        <tbody></tbody>
                                    </table>
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


<script>
    $(function () {


    });
</script>

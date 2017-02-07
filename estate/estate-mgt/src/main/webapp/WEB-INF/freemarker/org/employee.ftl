<!-- DataTables -->
<#--<link rel="stylesheet" href="${contextPath}/js/plugins/datatables/dataTables.bootstrap.css">-->
<link href="${contextPath}/css/org/orgnization.css" rel="stylesheet">
<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />

<!-- Modal addEmployeeDialog -->
<div class="modal fade" id="addEmployeeDialog" tabindex="-1" role="dialog" aria-labelledby="addEmployeeLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <i class="fa fa-times" aria-hidden="true"></i>
                </button>
                <h4 class="modal-title" id="addEmployeeLabel"></h4>
            </div>
            <div class="modal-body">
                <form id="addEmployeeForm" class="form-horizontal" method="post">
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">真实姓名<span class="required-field">*</span></label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <input type="text" id="addEmployeeName" class="form-control" placeholder="真实姓名">
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <div id="addEmployeeGender">
                                <input type="radio" id="M" value="M" checked="checked">男
                                <input type="radio" id="F" value="F">女
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">手机号<span class="required-field">*</span></label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <input type="text" id="addEmployeeMobile" class="form-control" placeholder="手机号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">身份证号<span class="required-field">*</span></label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <input type="text" id="addEmployeeID" class="form-control" placeholder="身份证号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">微信</label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <input type="text" id="addEmployeeWechat" class="form-control" placeholder="微信">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">部门<span class="required-field">*</span></label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <input type="text" id="addEmployeeDepart" class="form-control" placeholder="部门">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">岗位<span class="required-field">*</span></label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <input type="text" id="addEmployeePosition" class="form-control" placeholder="岗位">
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <input type="checkbox" id="isAgent">是经纪人
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">岗位状态<span class="required-field">*</span></label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <div id="addEmployeeStatus">
                                <select id="workingStatus" class="form-control btn-group dropup">
                                    <option value="WORKING">在职</option>
                                    <option value="INTERN">实习</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="confirmAddEmployeeBtn">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

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

<script src="${contextPath!}/js/org/employee.js"></script>


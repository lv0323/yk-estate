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
                <h4 class="modal-title" id="addEmployeeLabel">增加员工</h4>
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
                            <#list gender?if_exists as gd>
                                <input type="radio" value="${gd.name()}">${gd.getGender()}
                            </#list>
                               <#-- <input type="radio" id="M" value="M" checked="checked">男
                                <input type="radio" id="F" value="F">女-->
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
                            <div id="addEmployeeDepart" class="dropdown-yk">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">岗位<span class="required-field">*</span></label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <select type="text" id="addEmployeePosition" class="form-control btn-group dropup">
                                <#list positionTypeList?if_exists as pt>
                                    <option value="${pt.name()}">${pt.getLabel()}</option>
                                </#list>
                            </select>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <input type="checkbox" id="addEmployeeIsAgent">是经纪人
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">岗位状态<span class="required-field">*</span></label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <select id="addEmployeeStatus" class="form-control btn-group dropup">
                                <#list workingStatusList?if_exists as ws>
                                    <option value="${ws.name()}">${ws.getStatus()}</option>
                                </#list>
                            </select>
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


<!-- Modal editEmployeeDialog -->
<div class="modal fade" id="editEmployeeDialog" tabindex="-1" role="dialog" aria-labelledby="editEmployeeLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <i class="fa fa-times" aria-hidden="true"></i>
                </button>
                <h4 class="modal-title" id="editEmployeeLabel">编辑员工</h4>
            </div>
            <div class="modal-body">
                <form id="editEmployeeForm" class="form-horizontal" method="post">
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">真实姓名<span class="required-field">*</span></label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <input type="text" id="editEmployeeName" class="form-control" placeholder="真实姓名">
                            <input id="editEmployeeId" class="hidden">
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <div id="editEmployeeGender">
                                <#--<input type="radio" value="M" checked="checked">男
                                <input type="radio" value="F">女-->
                                <#list gender?if_exists as gd>
                                    <input type="radio" value="${gd.name()}">${gd.getGender()}
                                </#list>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">手机号<span class="required-field">*</span></label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <input type="text" id="editEmployeeMobile" class="form-control" placeholder="手机号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">身份证号<span class="required-field">*</span></label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <input type="text" id="editEmployeeID" class="form-control" placeholder="身份证号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">微信</label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <input type="text" id="editEmployeeWechat" class="form-control" placeholder="微信">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">部门<span class="required-field">*</span></label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <div id="editEmployeeDepart" class="dropdown-yk">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">岗位<span class="required-field">*</span></label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <select type="text" id="editEmployeePosition" class="form-control btn-group dropup">
                            <#list positionTypeList?if_exists as pt>
                                <option value="${pt.name()}">${pt.getLabel()}</option>
                            </#list>
                            </select>
                        </div>
                        <div class="hidden col-lg-3 col-md-3 col-sm-3">
                            <input type="checkbox" id="editEmployeeIsAgent">是经纪人
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">岗位状态<span class="required-field">*</span></label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <select id="editEmployeeStatus" class="form-control btn-group dropup">
                            <#list workingStatusList?if_exists as ws>
                                <option value="${ws.name()}">${ws.getStatus()}</option>
                            </#list>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="confirmEditEmployeeBtn">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>


<!-- Modal quitEmployeeDialog -->
<div class="modal fade bs-example-modal-sm" id="quitEmployeeDialog" tabindex="-1" role="dialog" aria-labelledby="quitEmployeeLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <i class="fa fa-times" aria-hidden="true"></i>
                </button>
                <h4 class="modal-title" id="quitEmployeeLabel">员工离职</h4>
            </div>
            <div class="modal-body">
                该操作不可恢复，确认执行离职操作?
                <input id="quitEmployeeId" class="hidden">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="confirmQuitEmployeeBtn">确定</button>
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
                                <div class="zTreeDemoBackground left">
                                    <ul id="departmentTree" class="ztree"></ul>
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
                                        <span class="opt-gap"></span>
                                        <a class="btn" id="filterEmployeeBtn">
                                            <i class="fa fa-filter" aria-hidden="true"></i>
                                            筛选
                                        </a>
                                    </div>
                                </div>
                                <div class="box-body">
                                    <div id="box-filter" class="form-horizontal" style="display:none;">
                                        <div class="form-group ">
                                            <label class="col-lg-3 col-md-3 col-sm-3 control-label">在职｜离职： </label>
                                            <div class="col-lg-3 col-md-3 col-sm-3">
                                                <select id="quitPosition" class="form-control btn-group dropup">
                                                    <option value="false">在职</option>
                                                    <option value="true">离职</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- table -->
                                    <table id="employeeList" class="list table table-bordered table-hover">
                                        <thead><tr>
                                            <th><span>姓名</span></th>
                                            <th><span>岗位名称</span></th>
                                            <th><span>电话</span></th>
                                            <th class="text-right"><span>操作</span></th>
                                        </tr></thead>
                                        <tbody></tbody>
                                    </table>
                                    <ul id="employeeList_paging" class="pagination"></ul>
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

<script src="${contextPath!}/js/app/org/employee.js"></script>


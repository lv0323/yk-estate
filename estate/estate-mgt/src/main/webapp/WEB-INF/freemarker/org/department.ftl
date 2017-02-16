<link href="${contextPath}/css/org/orgnization.css" rel="stylesheet">

<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />

<!-- Modal addDepartDialog -->
<#--<div class="modal fade" id="addDepartDialog" tabindex="-1" role="dialog" aria-labelledby="addDepartLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <i class="fa fa-times" aria-hidden="true"></i>
                </button>
                <h4 class="modal-title" id="addDepartLabel"></h4>
            </div>
            <div class="modal-body">
                <form id="addDepartForm" class="form-horizontal" method="post">
                    <div class="form-group">
                        <label class="col-lg-2 col-md-2 col-sm-2 control-label">上级部门</label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <div class="dropdown-yk">
                                <div class="parent">请选择</div>
                                <ul class="listUl">
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 col-md-2 col-sm-2 control-label">部门名称</label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <input type="text" id="departName" class="form-control" reg="^[\S]{1,8}$" placeholder="名称限8个字符">
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <input type="text" id="departSpell" class="form-control hidden" placeholder="拼音简码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 col-md-2 col-sm-2 control-label">部门电话</label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <input type="text" id="departTel" class="form-control" reg="^1\d{10}$|^\d{3}-\d{8}$|^\d{4}-\d{7}$|^0\d{11}$|^\d{4}-\d{8}$|^$" placeholder="部门电话">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 col-md-2 col-sm-2 control-label">部门地址</label>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <select id="departCid" class="form-control btn-group dropup">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <select id="departDid" class="form-control btn-group dropup">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <select id="departSDid" class="form-control btn-group dropup">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <input type="text" id="departAddress" class="form-control" placeholder="请输入详细地址">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="confirmAddDepartBtn">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>-->

<!-- Modal editDepartDialog -->
<#--<div class="modal fade" id="editDepartDialog" tabindex="-1" role="dialog" aria-labelledby="editDepartLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <i class="fa fa-times" aria-hidden="true"></i>
                </button>
                <h4 class="modal-title" id="editDepartLabel"></h4>
            </div>
            <div class="modal-body">
                <form id="editDepartForm" class="form-horizontal" method="post">
                    <div class="form-group parent-department-form-group">
                        <label id="superiorDepartLabel" class="col-lg-2 col-md-2 col-sm-2 control-label">上级部门</label>
                        <div class="col-lg-3 col-md-4 col-sm-4 ">
                            <div class="dropdown-yk">
                            </div>
                            <input id="departId" class="hidden">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 col-md-2 col-sm-2 control-label">部门名称</label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <input type="text" id="departName" class="form-control" reg="^[\S]{1,8}$" placeholder="名称限8个字符">
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <input type="text" id="departSpell" class="form-control hidden" placeholder="拼音简码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 col-md-2 col-sm-2 control-label">部门电话</label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <input type="text" id="departTel" class="form-control" reg="^1\d{10}$|^\d{3}-\d{8}$|^\d{4}-\d{7}$|^0\d{11}$|^\d{4}-\d{8}$|^$" placeholder="部门电话">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 col-md-2 col-sm-2 control-label">部门地址</label>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <select id="departCid" class="form-control btn-group dropup">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <select id="departDid" class="form-control btn-group dropup">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <select id="departSDid" class="form-control btn-group dropup">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <input type="text" id="departAddress" class="form-control" placeholder="请输入详细地址">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="confirmEditDepartBtn">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>-->


<!-- Modal deleteDepartDialog -->
<div class="modal fade bs-example-modal-sm" id="deleteDepartDialog" tabindex="-1" role="dialog" aria-labelledby="deleteDepartLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <i class="fa fa-times" aria-hidden="true"></i>
                </button>
                <h4 class="modal-title" id="deleteDepartLabel">删除部门</h4>
            </div>
            <div class="modal-body">
                该操作不可恢复，确认删除?
                <input id="departId" class="hidden">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="confirmDelDepartBtn">确定</button>
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
                组织机构
                </a>
            </li>
            <li class="active">
                部门管理
            </li>
        </ol>
    </section>

    <!--Main content -->
    <section class="content">
        <div class="animated fadeInRight">
            <div class="row">
                <div class="col-lg-12">
                    <section class="connectedSortable">
                        <div class="box box-solid">
                            <div class="box-header">
                                <h3 class="box-title">部门列表</h3>
                                <div class="box-tools">
                                    <#--<a class="btn" id="addDepartBtn" data-toggle="modal" data-target="#addDepartDialog">-->
                                     <a class="btn" id="addDepartBtn" href="/mgt/org/addDepartment.ftl">
                                        <i class="fa fa-plus" aria-hidden="true"></i>
                                        新增部门
                                    </a>
                                </div>
                            </div>
                            <div class="box-body">
                                <!-- table -->
                                <table id="departList" class="list table table-bordered table-hover">
                                    <thead><tr>
                                        <th><span>部门名称</span></th>
                                        <th><span>部门电话</span></th>
                                        <th><span>部门地址</span></th>
                                        <th class="text-right"><span>操作</span></th>
                                    </tr></thead>
                                    <tbody></tbody>
                                </table>
                                <ul id="departList_paging" class="pagination"></ul>
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
<script src="${contextPath!}/js/app/org/department.js"></script>
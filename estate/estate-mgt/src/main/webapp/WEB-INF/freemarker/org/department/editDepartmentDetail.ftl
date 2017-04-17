<link href="${contextPath}/css/app/org/orgnization.css" rel="stylesheet">

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
            <li>
                <a href="#">
                    部门管理
                </a>
            </li>
            <li class="active">
                编辑部门
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
                                <h3 class="box-title">编辑部门</h3>
                            </div>
                            <div class="box-body modal-content" id="editDepartDialog">
                                <form id="editDepartForm" class="form-horizontal" method="post">
                                    <div class="form-group parent-department-form-group">
                                        <label id="superiorDepartLabel" class="control-label">上级部门</label>
                                        <div class="superiorDepart col-lg-3 col-md-4 col-sm-4">
                                            <div class="dropdown-yk" style="background-color: #eeeeee;">
                                                <div class="parent">请选择</div>
                                                <ul class="listUl">
                                                </ul>
                                            </div>
                                            <input id="departId" class="hidden">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">部门名称</label>
                                        <div class="col-lg-3 col-md-4 col-sm-4">
                                            <input type="text" id="departName" class="form-control" reg="^[\S]{1,8}$" placeholder="名称限8个字符">
                                        </div>
                                        <div class="col-lg-3 col-md-3 col-sm-3">
                                            <input type="text" id="departSpell" class="form-control hidden" placeholder="拼音简码">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">部门电话</label>
                                        <div class="col-lg-3 col-md-4 col-sm-4">
                                            <input type="text" id="departTel" class="form-control" reg="^1\d{10}$|^\d{3}-\d{8}$|^\d{4}-\d{7}$|^0\d{11}$|^\d{4}-\d{8}$|^$" placeholder="部门电话">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">部门地址</label>
                                        <div class="col-lg-2 col-md-2 col-sm-2">
                                            <select id="departCid" class="form-control"> <#--selectpicker show-menu-arrow bs-select-hidden-->
                                                <option value="">请选择</option>
                                            </select>
                                        </div>
                                        <div class="col-lg-2 col-md-2 col-sm-2">
                                            <select id="departDid" class="form-control"> <#--form-control btn-group dropup-->
                                                <option value="">请选择</option>
                                            </select>
                                        </div>
                                        <div class="col-lg-2 col-md-2 col-sm-2">
                                            <select id="departSDid" class="form-control">
                                                <option value="">请选择</option>
                                            </select>
                                        </div>
                                       <#-- <div class="col-lg-5 col-md-5 col-sm-5">
                                            <input type="text" id="departAddress" class="form-control" placeholder="请输入详细地址">
                                        </div>-->
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label" style="visibility: hidden"></label>
                                        <div class="col-lg-6 col-md-6 col-sm-6">
                                            <textarea id="departAddress" class="form-control noresize" rows="2" placeholder="请输入详细地址"></textarea>
                                        </div>
                                    </div>
                                </form>
                                <div class="modal-footer center">
                                    <button type="button" class="btn btn-primary" id="confirmEditDepartBtn">保存</button>
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
<script src="${contextPath!}/js/app/org/department/departmentDetail.js"></script>


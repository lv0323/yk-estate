<!-- DataTables -->
<#--<link rel="stylesheet" href="${contextPath}/js/plugins/datatables/dataTables.bootstrap.css">-->
<link href="${contextPath}/css/org/orgnization.css" rel="stylesheet">
<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />

<!-- Modal addCompanyDialog -->
<#--<div class="modal fade" id="addCompanyDialog" tabindex="-1" role="dialog" aria-labelledby="addCompanyLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <i class="fa fa-times" aria-hidden="true"></i>
                </button>
                <h4 class="modal-title" id="addCompanyLabel"></h4>
            </div>
            <div class="modal-body">
                <form id="addCompanyForm" class="form-horizontal" method="post">
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">公司名称<span class="required-field">*</span></label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <input type="text" id="addCompanyName" class="form-control" placeholder="公司名称" required>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <input type="text" id="addCompanySpell" class="form-control" placeholder="拼音简码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">公司许可证<span class="required-field">*</span></label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <input type="text" id="addCompanyLicense" class="form-control" placeholder="公司许可证">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">公司地址<span class="required-field">*</span></label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <input type="text" id="addCompanyAddress" class="form-control" placeholder="公司地址">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">公司说明</label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <input type="text" id="addCompanyNote" class="form-control" placeholder="公司说明">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">加盟有效时期<span class="required-field">*</span></label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input type="text" class="form-control pull-right" id="addCompanyStartDate">
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input type="text" class="form-control pull-right" id="addCompanyEndDate">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">法人姓名<span class="required-field">*</span></label>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <input type="text" id="companyRepName" class="form-control" placeholder="法人姓名">
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <div id="companyRepGender">
                                <input type="radio" id="M" value="M" checked="checked">男
                                <input type="radio" id="F" value="F">女
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">法人电话<span class="required-field">*</span></label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <input type="text" id="companyRepMobile" class="form-control" placeholder="法人电话">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">法人身份证<span class="required-field">*</span></label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <input type="text" id="companyRepID" class="form-control" placeholder="法人身份证">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">法人微信</label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <input type="text" id="companyRepWechat" class="form-control" placeholder="法人微信">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="confirmAddCompanyBtn">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>-->

<!-- Modal editCompanyDialog -->
<#--<div class="modal fade" id="editCompanyDialog" tabindex="-1" role="dialog" aria-labelledby="editCompanyLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <i class="fa fa-times" aria-hidden="true"></i>
                </button>
                <h4 class="modal-title" id="editCompanyLabel"></h4>
            </div>
            <div class="modal-body">
                <form id="editCompanyForm" class="form-horizontal" method="post">
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">公司名称<span class="required-field">*</span></label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <input type="text" id="editCompanyName" class="form-control" placeholder="公司名称">
                            <input type="text" id="editCompanyId" class="hidden">
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <input type="text" id="editCompanySpell" class="form-control" placeholder="拼音简码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">公司许可证<span class="required-field">*</span></label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <input type="text" id="editCompanyLicense" class="form-control" placeholder="公司许可证">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">公司地址<span class="required-field">*</span></label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <input type="text" id="editCompanyAddress" class="form-control" placeholder="公司地址">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">公司说明</label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <input type="text" id="editCompanyNote" class="form-control" placeholder="公司说明">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="confirmEditCompanyBtn">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>-->


<!-- Modal renewCompanyDialog -->
<div class="modal fade" id="renewCompanyDialog" tabindex="-1" role="dialog" aria-labelledby="renewCompanyLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <i class="fa fa-times" aria-hidden="true"></i>
                </button>
                <h4 class="modal-title" id="renewCompanyLabel">公司续约</h4>
            </div>
            <div class="modal-body">
                <input id="renewCompanyId" class="hidden">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">加盟截止时期延至</label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input type="text" class="form-control pull-right" data-provide="datepicker" id="companyEndDateRenew">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="confirmRenewCompanyBtn">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>


<!-- Modal toggleLockCompanyDialog -->
<div class="modal fade" id="toggleLockCompanyDialog" tabindex="-1" role="dialog" aria-labelledby="toggleLockCompanyLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <i class="fa fa-times" aria-hidden="true"></i>
                </button>
                <h4 class="modal-title" id="toggleLockCompanyLabel">公司解冻｜冻结</h4>
            </div>
            <input id="toggleLockCompanyId" class="hidden">
            <input id="toggleLockCompanyLocked" class="hidden">
            <div class="modal-body">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="confirmToggleLockCompanyBtn">确定</button>
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
                    加盟商管理
                </a>
            </li>
            <li class="active">
                公司管理
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
                                <h3 class="box-title">公司列表</h3>
                                <div class="box-tools">
                                    <#--<a class="btn" id="addCompanyBtn" data-toggle="modal" data-target="#addCompanyDialog">-->
                                    <a class="btn" id="addCompanyBtn" href="/mgt/franchisee/addCompany.ftl">
                                        <i class="fa fa-plus" aria-hidden="true"></i>
                                        创建公司
                                    </a>
                                </div>
                            </div>
                            <div class="box-body">
                                <!-- table -->
                                <table id="companyList" class="list table table-bordered table-hover">
                                    <thead><tr>
                                        <th><span>公司名称</span></th>
                                        <th><span>公司授权号</span></th>
                                        <th><span>加盟有效起始日期</span></th>
                                        <th><span>加盟有效截止日期</span></th>
                                        <th class="text-right"><span>操作</span></th>
                                    </tr></thead>
                                    <tbody></tbody>
                                </table>
                                <ul id="companyList_paging" class="pagination"></ul>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </section>
</div>


<#include "/common/footer.ftl" />
<script src="${contextPath!}/js/app/franchisee/company.js"></script>
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
                    加盟商管理
                </a>
            </li>
            <li>
                <a href="#">
                    <i class="fa fa-home fa-lg" aria-hidden="true"></i>
                公司管理
                </a>
            </li>
            <li class="active">
                公司详情
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
                                <h3 class="box-title">公司详情</h3>
                            </div>
                            <div class="box-body">
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
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </section>
</div>


<#include "/common/footer.ftl" />
<!-- InputMask -->
<#--<script src="js/plugins/input-mask/jquery.inputmask.js"></script>-->
<#--<script src="js/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>-->
<#--<script src="js/plugins/input-mask/jquery.inputmask.extensions.js"></script>-->
<!-- bootstrap datepicker -->
<#--<script src="js/plugins/datepicker/bootstrap-datepicker.js"></script>-->
<script src="${contextPath!}/js/franchisee/company.js"></script>
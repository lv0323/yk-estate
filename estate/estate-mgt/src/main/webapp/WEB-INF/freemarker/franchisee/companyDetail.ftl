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
                            <div class="box-body" id="editCompanyDialog">
                                <form id="editCompanyForm" class="form-horizontal" method="post">
                                    <div class="form-group">
                                        <label class="col-lg-2 col-md-2 col-sm-3 control-label">公司名称<span class="required-field">*</span></label>
                                        <div class="col-lg-3 col-md-4 col-sm-4">
                                            <input type="text" id="editCompanyName" class="form-control" placeholder="公司名称" required>
                                            <input id="editCompanyId" class="hidden">
                                        </div>
                                        <div class="col-lg-3 col-md-3 col-sm-3">
                                            <input type="text" id="editCompanySpell" class="form-control" placeholder="拼音简码">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 col-md-2 col-sm-3 control-label">公司许可证<span class="required-field">*</span></label>
                                        <div class="col-lg-3 col-md-4 col-sm-4">
                                            <input type="text" id="editCompanyLicense" class="form-control" placeholder="公司许可证">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 col-md-2 col-sm-3 control-label">公司地址<span class="required-field">*</span></label>
                                        <div class="col-lg-3 col-md-4 col-sm-4">
                                            <input type="text" id="editCompanyAddress" class="form-control" placeholder="公司地址">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 col-md-2 col-sm-3 control-label">公司说明</label>
                                        <div class="col-lg-3 col-md-4 col-sm-4">
                                            <input type="text" id="editCompanyNote" class="form-control" placeholder="公司说明">
                                        </div>
                                        <div class="col-lg-2 col-md-2 col-sm-2">
                                            <div type="text" id="editCompanyLocked" class="control-label"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 col-md-2 col-sm-3 control-label">加盟有效时期<span class="required-field">*</span></label>
                                        <div class="col-lg-3 col-md-4 col-sm-4">
                                            <div class="input-group date">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-calendar"></i>
                                                </div>
                                                <input type="text" class="form-control pull-right" id="editCompanyStartDate">
                                            </div>
                                        </div>
                                        <div class="col-lg-3 col-md-4 col-sm-4">
                                            <div class="input-group date">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-calendar"></i>
                                                </div>
                                                <input type="text" class="form-control pull-right" id="editCompanyEndDate">
                                            </div>
                                        </div>
                                    </div>
                                <#--<div class="form-group">
                                    <label class="col-lg-2 col-md-2 col-sm-3 control-label">法人姓名<span class="required-field">*</span></label>
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
                                    <label class="col-lg-2 col-md-2 col-sm-3 control-label">法人电话<span class="required-field">*</span></label>
                                    <div class="col-lg-3 col-md-4 col-sm-4">
                                        <input type="text" id="companyRepMobile" class="form-control" placeholder="法人电话">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 col-md-2 col-sm-3 control-label">法人身份证<span class="required-field">*</span></label>
                                    <div class="col-lg-3 col-md-4 col-sm-4">
                                        <input type="text" id="companyRepID" class="form-control" placeholder="法人身份证">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 col-md-2 col-sm-3 control-label">法人微信</label>
                                    <div class="col-lg-3 col-md-4 col-sm-4">
                                        <input type="text" id="companyRepWechat" class="form-control" placeholder="法人微信">
                                    </div>
                                </div>-->
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
<script src="${contextPath!}/js/franchisee/companyDetail.js"></script>
<script>
    $(function () {
        $('#editCompanyDialog input').prop("disabled", true);
        $('#editCompanyDialog select').prop("disabled", true);
    });

</script>
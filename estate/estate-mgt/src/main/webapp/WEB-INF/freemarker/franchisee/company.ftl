<!-- DataTables -->
<#--<link rel="stylesheet" href="${contextPath}/js/plugins/datatables/dataTables.bootstrap.css">-->
<link href="${contextPath}/css/org/orgnization.css" rel="stylesheet">
<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />

<!-- Modal addCompanyDialog -->
<div class="modal fade" id="addCompanyDialog" tabindex="-1" role="dialog" aria-labelledby="addCompanyLabel">
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
                        <label class="col-lg-2 col-md-3 col-sm-3 control-label">公司名称</label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <input type="text" id="companyName" class="form-control" placeholder="公司名称">
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <input type="text" id="companySpell" class="form-control" placeholder="拼音简码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 col-md-3 col-sm-3 control-label">公司许可证</label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <input type="text" id="companyLicense" class="form-control" placeholder="公司许可证">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 col-md-3 col-sm-3 control-label">公司地址</label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <input type="text" id="companyAddress" class="form-control" placeholder="公司地址">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 col-md-3 col-sm-3 control-label">公司说明</label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <input type="text" id="companyNote" class="form-control" placeholder="公司说明">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 col-md-3 col-sm-3 control-label">加盟有效时期</label>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input type="text" class="form-control pull-right" id="companyStartDate">
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input type="text" class="form-control pull-right" id="companyEndDate">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 col-md-3 col-sm-3 control-label">法人姓名</label>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <input type="text" id="companyRepName" class="form-control" placeholder="法人姓名">
                        </div>
                        <label class="col-lg-2 col-md-2 col-sm-2 control-label">性别</label>
                        <div class="col-lg-3 col-md-3 col-sm-3">

                            <input type="radio" id="companyRepGender" value="M">男
                            <input type="radio" id="companyRepGender" value="F">女

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 col-md-3 col-sm-3 control-label">法人电话</label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <input type="text" id="companyRepMobile" class="form-control" placeholder="法人电话">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 col-md-3 col-sm-3 control-label">法人身份证</label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <input type="text" id="companyRepID" class="form-control" placeholder="法人身份证">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 col-md-3 col-sm-3 control-label">法人微信</label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <input type="text" id="companyWechat" class="form-control" placeholder="法人微信">
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
</div>

<!-- Modal editPositionDialog -->
<div class="modal fade" id="editPositionDialog" tabindex="-1" role="dialog" aria-labelledby="editPositionLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <i class="fa fa-times" aria-hidden="true"></i>
                </button>
                <h4 class="modal-title" id="editPositionLabel"></h4>
            </div>
            <div class="modal-body">
                <form id="editPositionForm" class="form-horizontal" method="post">
                    <div class="form-group">
                        <label class="col-lg-2 col-md-2 col-sm-2 control-label">岗位名称</label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <input type="text" id="positionName" class="form-control" placeholder="岗位名称">
                            <input id="positionId" class="hidden">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 col-md-2 col-sm-2 control-label">岗位说明</label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <input type="text" id="positionNote" class="form-control" placeholder="岗位说明">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="confirmEditPositionBtn">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>


<!-- Modal deletePositionDialog -->
<div class="modal fade bs-example-modal-sm" id="deletePositionDialog" tabindex="-1" role="dialog" aria-labelledby="deleteDepartLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <i class="fa fa-times" aria-hidden="true"></i>
                </button>
                <h4 class="modal-title" id="deletePositionLabel">删除岗位</h4>
            </div>
            <div class="modal-body">
                该操作不可恢复，确认删除?
                <input id="positionId" class="hidden">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="confirmDelPositionBtn">确定</button>
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
                                <h3 class="box-title">公司管理</h3>
                                <div class="box-tools">
                                    <a class="btn" id="addCompanyBtn" data-toggle="modal" data-target="#addCompanyDialog">
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
                                        <th><span>公司状态</span></th>
                                        <th><span>公司地址</span></th>
                                        <th class="text-right"><span>操作</span></th>
                                    </tr></thead>
                                    <tbody></tbody>
                                </table>
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
<script>
    $(function () {
      /*  $('#companyStartDate').datepicker({
            autoclose: true
        });
        $('#companyEndDate').datepicker({
            autoclose: true
        });*/
    });
</script>
<!-- DataTables -->
<#--<link rel="stylesheet" href="${contextPath}/js/plugins/datatables/dataTables.bootstrap.css">-->
<link href="${contextPath}/css/app/org/orgnization.css" rel="stylesheet">
<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />


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
                        <label class="control-label">加盟截止至</label>
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
                                   <#-- <a class="btn" id="addCompanyBtn" href="/mgt/franchisee/addCompany">
                                        <i class="fa fa-plus" aria-hidden="true"></i>
                                        创建公司
                                    </a>-->
                                </div>
                            </div>
                            <div class="box-body">
                                <!-- table -->
                                <table id="companyList" class="list table table-bordered table-hover">
                                </table>
                                <div class="pagination-container">
                                    <ul id="companyList_paging" class="pagination"></ul>
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
<script src="${contextPath!}/js/app/franchisee/company.js"></script>
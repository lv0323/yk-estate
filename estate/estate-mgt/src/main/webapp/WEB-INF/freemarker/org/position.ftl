<!-- DataTables -->
<#--<link rel="stylesheet" href="${contextPath}/js/plugins/datatables/dataTables.bootstrap.css">-->
<link href="${contextPath}/css/org/orgnization.css" rel="stylesheet">
<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />

<!-- Modal addPositionDialog -->
<div class="modal fade" id="addPositionDialog" tabindex="-1" role="dialog" aria-labelledby="addPositionLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <i class="fa fa-times" aria-hidden="true"></i>
                </button>
                <h4 class="modal-title" id="addPositionLabel"></h4>
            </div>
            <div class="modal-body">
                <form id="addPositionForm" class="form-horizontal" method="post">
                    <div class="form-group">
                        <label class="col-lg-2 col-md-2 col-sm-2 control-label">岗位名称</label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <input type="text" id="positionName" class="form-control" placeholder="岗位名称">
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
                <button type="button" class="btn btn-primary" id="confirmAddPositionBtn">保存</button>
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
<div class="modal fade bs-example-modal-sm" id="deletePositionDialog" tabindex="-1" role="dialog" aria-labelledby="deletePositionLabel">
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
                    组织机构
                </a>
            </li>
            <li class="active">
                岗位管理
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
                                <h3 class="box-title">岗位管理</h3>
                                <div class="box-tools">
                                    <a class="btn" id="addPositionBtn" data-toggle="modal" data-target="#addPositionDialog">
                                        <i class="fa fa-plus" aria-hidden="true"></i>
                                        新增岗位
                                    </a>
                                </div>
                            </div>
                            <div class="box-body">
                                <!-- table -->
                                <table id="positionList" class="list table table-bordered table-hover">
                                    <thead><tr>
                                        <th><span>岗位名称</span></th>
                                        <th><span>说明</span></th>
                                        <th class="text-right"><span>操作</span></th>
                                    </tr></thead>
                                    <tbody></tbody>
                                </table>
                                <ul id="positionList_paging" class="pagination"></ul>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </section>
</div>


<#include "/common/footer.ftl" />
<script src="${contextPath!}/js/app/org/position.js"></script>
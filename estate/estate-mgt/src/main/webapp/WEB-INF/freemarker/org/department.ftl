<link href="${contextPath}/css/org/department.css" rel="stylesheet">

<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />

<!-- Modal addDepartDialog -->
<div class="modal fade" id="addDepartDialog" tabindex="-1" role="dialog" aria-labelledby="addDepartLabel">
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
                            <select id="departPid" class="form-control btn-group dropdown" onchange="">
                                <option value="">--请选择--</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 col-md-2 col-sm-2 control-label">部门名称</label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <input type="text" id="departName" class="form-control" reg="^[\S]{1,8}$" placeholder="名称限8个字符">
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <input type="text" id="departSpell" class="form-control" placeholder="拼音简码">
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
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <input id="departDid" class="form-control btn-group dropup">

                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <input id="departSDid" class="form-control btn-group dropup">
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
</div>

<!-- Modal editDepartDialog -->
<div class="modal fade" id="editDepartDialog" tabindex="-1" role="dialog" aria-labelledby="editDepartLabel">
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
                    <div class="form-group">
                        <label class="col-lg-2 col-md-2 col-sm-2 control-label">上级部门</label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <select id="departPid" class="form-control btn-group dropdown" onchange=""></select>
                            <input id="departId" class="hidden">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 col-md-2 col-sm-2 control-label">部门名称</label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <input type="text" id="departName" class="form-control" reg="^[\S]{1,8}$" placeholder="名称限8个字符">
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <input type="text" id="departSpell" class="form-control" placeholder="拼音简码">
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
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <input id="departDid" class="form-control btn-group dropup">

                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <input id="departSDid" class="form-control btn-group dropup">
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
</div>


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
                部门列表
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
                                    <a class="btn" id="addDepartBtn" data-toggle="modal" data-target="#addDepartDialog">
                                        <i class="fa fa-plus" aria-hidden="true"></i>
                                        新增部门
                                    </a>
                                </div>
                            </div>
                            <div class="box-body">
                                <!-- table -->
                                <table id="departList" class="table table-bordered table-hover">
                                    <thead><tr>
                                        <th><span>部门名称</span></th>
                                        <th><span>部门电话</span></th>
                                        <th><span>部门地址</span></th>
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
<!-- /.content-wrapper -->

<#include "/common/footer.ftl" />
<script src="${contextPath!}/js/org/department.js"></script>
<script type="text/javascript">

    $(function(){

        /*
        var allData = {};
        var url = contextPath+ "/api/department/";

        $.getJSON(url+"query",{companyId:company_id})
                .done(function(data){
                    if(data === null){
                        $('#departList>tbody').append('<tr><td colspan="4">没有数据</td></tr>');
                    }else {
                        allData.departData = data;
                        $.each(data,function(index,depart){
                            var appendHtml = '<tr>' +
                                    '<td><a class="btn" id="lookDepartBtn" data-id="'+depart["id"]+'">'+depart["name"]+'</a></td>' +
                                    '<td>'+depart["telephone"]+'</td>' +
                                    '<td>'+depart["address"]+'</td>' +
                                    '<td class="text-right"><a class="btn" id="editDepartBtn" data-index="'+index+'" data-id="'+depart["id"]+'" data-toggle="modal" data-target="#editDepartDialog">编辑</a>';

                            //if parent_id is null, disable delete button
                            if(depart["parent_id"]){
                                appendHtml+='<span class="opt-gap"></span>'+
                                        '<a class="btn" id="delDepartBtn" data-pid="'+depart["parent_id"]+'" data-id="'+depart["id"]+'" data-toggle="modal" data-target="#deleteDepartDialog">删除</a></td>'+
                                        '</tr>';
                            }else{
                                appendHtml+='</tr>';
                            }
                            $('#departList>tbody').append(appendHtml);

                            //initialize departments selection in add/edit dialog
                            $('#addDepartDialog #departPid').append('<option value="'+depart["id"]+'">'+depart["name"]+'</option>');
                            $('#editDepartDialog #departPid').append('<option value="'+depart["id"]+'">'+depart["name"]+'</option>');

                        });

                        $('#departList').DataTable({
                            "paging": true,
                            "lengthChange": false,
                            "searching": false,
                            "ordering": false,
                            "info": false,
                            "autoWidth": false
                        });
                    }
                })
                .fail(function(){
                    $('#departList>tbody').append('<tr><td colspan="4">无法获取数据</td></tr>');
                });
*/


        //action for added department
        /*$('#addDepartDialog').on('click','#confirmAddDepartBtn',function(){
            var toAddDepart = {
                address:$('#addDepartDialog #departAddress').val(),
                name:$('#addDepartDialog #departName').val(),
                parent_id:$('#addDepartDialog #departPid :selected').val(),
                short_name:$('#addDepartDialog #departSpell').val(),
                telephone:$('#addDepartDialog #departTel').val()
            };
            $.ajax({
                url: url+"add",
                type: 'POST',
                data:toAddDepart,
                headers:{'x-auth-token':'0d3a7d8d-9274-48fa-8e09-1d26a9a56c9c'},
                success: function(){
                    console.log("success");
                    location.reload(true);
                },
                error:function(res){
                    console.log("error"+res);
                }
            });

            /*
            var address = $('#addDepartDialog #departAddress').val();
            var name = $('#addDepartDialog #departName').val();
            var parent_id = $('#addDepartDialog #departPid :selected').val();
            var short_name = $('#addDepartDialog #departSpell').val();
            var telephone = $('#addDepartDialog #departTel').val();

            $.ajax({
                url: url+"add",
                type: 'POST',
                contentType: 'application/x-www-form-urlencoded',
                dataType: "json",
                data:"{'address':'" + address + "', 'company_id':'" + company_id + "','name':'"+name+"','parent_id':'"+parent_id+"','short_name':'"+short_name+"','telephone':'"+telephone+"'}",
                success: function(){
                    console.log("success");
                    location.reload(true);
                },
                error:function(res){
                    console.log("error"+res);
                }
            });
        });*/

        //initialize title and default value in edit department dialog
        /*$('#departList').on('click','#editDepartBtn',function(e){
            var index = $(e.target).data('index');
            var depart = allData.departData[index];
            var pId  = depart["parent_id"];
            $('#editDepartDialog #editDepartLabel').text('编辑部门');
            $('#editDepartDialog #departPid').find('option[value='+pId+']').attr('selected','selected');
            $('#editDepartDialog #departName').val(depart["name"]);
            $('#editDepartDialog #departSpell').val(depart["short_name"]);
            $('#editDepartDialog #departTel').val(depart["telephone"]);
            $('#editDepartDialog #departAddress').val(depart["address"]);
        });*/

        //action for updated department
        /*$('#editDepartDialog').on('click','#confirmEditDepartBtn',function(){
            var id = $(e.target).data('id');
            $.ajax({
                url: url+"add",
                type: 'GET',
                data:{id:index},
                success: function(){
                    console.log("success");
                    location.reload(true);
                },
                error:function(res){
                    console.log("error"+res);
                }
            });
        });*/


        //initialize index value stored in deleteDepartDialog
        /*$('#departList').on('click','#delDepartBtn',function(e){
            var departId = $(e.target).data('id');
            $('#deleteDepartDialog #departId').val(departId);
        });*/

        //delete data according to index value where specifies id
        /*$('#deleteDepartDialog').on('click','#confirmDelDepartBtn',function(){
            var departId = $('#deleteDepartDialog #departId').val();
            $.ajax({
                url: url+"delete",
                type: 'GET',
                data:{id:departId},
                success: function(){
                    location.reload(true);
                },
                error:function(res){
                    console.log("error"+res);
                }
            });
        });*/

    });


</script>

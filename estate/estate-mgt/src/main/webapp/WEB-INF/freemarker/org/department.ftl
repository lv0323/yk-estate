<link href="${contextPath}/css/department/department.css" rel="stylesheet">

<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />
<!-- Modal saveDepart -->
<div class="modal fade" id="saveDepart" tabindex="-1" role="dialog" aria-labelledby="saveDepartLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <i class="fa fa-times" aria-hidden="true"></i>
                </button>
                <h4 class="modal-title" id="saveDepartLabel"></h4>
            </div>
            <div class="modal-body">
                <form id="saveDepartForm" class="form-horizontal" method="post">
                    <div class="form-group">
                        <label class="col-lg-2 col-md-2 col-sm-2 control-label">上级部门</label>
                        <div class="col-lg-3 col-md-4 col-sm-4">
                            <select id="departPid" class="form-control btn-group dropdown" onchange=""></select>
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
                            <select id="departDid" class="form-control btn-group dropup" onchange=""></select>

                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <select id="departSDid" class="form-control btn-group dropup" onchange=""></select>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <input type="text" id="departAddress" class="form-control" placeholder="请输入详细地址">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="saveDepart()">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>


<!-- Modal deleteDepart -->
<div class="modal fade" id="deleteDepart" tabindex="-1" role="dialog" aria-labelledby="deleteDepartLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <i class="fa fa-times" aria-hidden="true"></i>
                </button>
                <h4 class="modal-title" id="deleteDepartLabel">删除部门</h4>
            </div>
            <div class="modal-body">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确定</button>
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
                                    <a class="btn" onclick="addDepart()" data-toggle="modal" data-target="#saveDepart">
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
<script type="text/javascript">
    /*$.ajax({
        type: "get",
        url: "192.168.0.143:8083/api/department/query",
        data: {companyId:0},
        success: function(){
            console.log("fetched data successfully")
        },
        error: function(){

        }
    });*/

    $(function(){

        $.getJSON("http://192.168.0.143:9999/api/department/query",{companyId:0})
                .done(function(data){
                    if(data === null){
                        $('#departList>tbody').append('<tr><td colspan="4">没有数据</td></tr>');
                    }else {
                        $.each(data,function(index,depart){
//                            var depart = {
//                                parent_id:departData["parent_id"],
//                                id: departData["id"],
//                                name:departData["name"],
//                                short_name:departData["short_name"],
//                                telephone:departData["telephone"],
//                                address:departData["address"],
//                                company_id:departData["company_id"]
//                            };

                            $('#departList>tbody').append('<tr id="'+depart["id"]+'">' +
                                    '<td><a href="javascript:void(0)" class="btn" onclick="lookDepart('+depart["id"]+')">'+depart["name"]+'</a></td>' +
                                    '<td>'+depart["telephone"]+'</td>' +
                                    '<td>'+depart["address"]+'</td>' +
                                    '<td class="text-right"><a class="btn" onclick="editDepart('+depart["id"]+')" data-toggle="modal" data-target="#saveDepart">编辑</a>'+
                                    '<span class="opt-gap"></span>'+
                                    '<a href="javascript:void(0)" class="btn" onclick="delDepart('+depart["id"]+')" data-toggle="modal" data-target="#deleteDepart">删除</a></td>'+
                                    '</tr>');
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
    });

    addDepart = function(){
        $('#saveDepart #saveDepartLabel').text('增加部门');
    };

    lookDepart = function(id){
        alert("look depart"+id);
    };

    editDepart = function(depart){
        $('#saveDepart #saveDepartLabel').text('编辑部门');

        /*$('#'+id).children('td[class!="text-right"]').each(function(){
            alert($(this).text());
        });*/
        alert(depart);
        $('#departName').val(depart["name"]);
        $('#departTel').val(depart["telephone"]);
        $('#departAddress').val(depart["address"]);

    };

    delDepart = function(id){

    };

    saveDepart = function(){

    };

</script>

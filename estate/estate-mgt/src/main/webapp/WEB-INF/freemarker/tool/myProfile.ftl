<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />

<!-- Modal changeMyPasswordDialog -->
<div class="modal fade" id="changeMyPasswordDialog" tabindex="-1" role="dialog" aria-labelledby="changeMyPasswordLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <i class="fa fa-times" aria-hidden="true"></i>
                </button>
                <h4 class="modal-title" id="changeMyPasswordLabel">修改密码</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">旧密码：</label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <input type="password" id="myOldPassword" class="form-control pull-right" placeholder="请输入旧密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-sm-3 control-label">新密码：</label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <input type="password" id="myNewPassword" class="form-control pull-right" placeholder="请输入新密码">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="confirmChangeMyPasswordBtn">确定</button>
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
                    实用工具
                </a>
            </li>
            <li class="active">
                个人中心
            </li>
        </ol>
    </section>

    <section class="content">
        <div class="animated fadeInRight">
            <div class="row">
                <div class="col-lg-12">
                    <section class="connectedSortable">
                        <div class="box box-solid">
                            <header class="box-header">
                                <h3 class="box-title pull-left">个人资料</h3>
                            </header>
                            <div class="box-body no-padding text-center">
                                <div class="col-md-3 col-sm-3">
                                    <div class="clearfix">
                                        <a class="btn pull-right">
                                            <i class="fa fa-pencil" aria-hidden="true"></i>
                                            修改头像
                                        </a>
                                    </div>
                                    <div style="max-width:300px; margin:0 auto;">
                                        <i class="fa fa-user-o fa-5x" style="margin-top:30px;" aria-hidden="true"></i>
                                        <img style="width:150px; height:150px; border:2px solid #d2d6de; display: none;">
                                        <h5 class="clearfix" id="myProfileUsername"></h5>
                                    </div>
                                </div>
                                <div class="col-sm-offset-1 col-md-8 col-sm-8">
                                    <div class="profile-content">
                                        <div class="clearfix">
                                            <h5 class="pull-left">关于我</h5>
                                            <div class="table-responsive">
                                                <table class="table">
                                                    <tbody>
                                                        <tr>
                                                            <td class="text-right text-muted">用户名</td>
                                                            <td id="username" class="text-left"></td>
                                                            <td class="text-right text-muted">密码</td>
                                                            <td class="text-left">
                                                                <a id="changePswBtn" class="btn" data-toggle="modal" data-target="#changeMyPasswordDialog">
                                                                    <i class="fa fa-pencil" aria-hidden="true"></i>修改密码
                                                                </a>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="text-right text-muted">手机号</td>
                                                            <td id="mobile" class="text-left"></td>
                                                            <td class="text-right text-muted">入职日期</td>
                                                            <td id="entryDate" class="text-left"></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="text-right text-muted">微信</td>
                                                            <td id="wechat" class="text-left"></td>

                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
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
<script src="${contextPath!}/js/app/tool/myProfile.js"></script>
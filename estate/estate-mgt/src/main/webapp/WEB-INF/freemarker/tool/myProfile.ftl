<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />

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
                                                            <td class="text-left">张三</td>
                                                            <td class="text-right text-muted">密码</td>
                                                            <td class="text-left">
                                                                <input style="border:0;outline:0;background:#FFF;" type="password">
                                                                <a id="changePswBtn" class="btn">
                                                                    <i class="fa fa-pencil" aria-hidden="true"></i>修改密码
                                                                </a>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="text-right text-muted">手机号</td>
                                                            <td class="text-left">13064759545</td>
                                                            <td class="text-right text-muted">入职日期</td>
                                                            <td class="text-left">2016-12-13</td>
                                                        </tr>
                                                        <tr>
                                                            <td class="text-right text-muted">微信</td>
                                                            <td class="text-left"></td>

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
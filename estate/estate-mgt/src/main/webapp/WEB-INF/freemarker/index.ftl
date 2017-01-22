<#include "/common/header.ftl" />
<link href="${contextPath}/css/app/identity/login.css?vn=${bts!}" rel="stylesheet">
<div class="login-box"  id="loginBox" data-angular>
    <div class="login-logo">
        盈科地产后台管理系统
    </div>
    <!-- /.login-logo -->
<div ng-controller="LoginCtrl">
    <form id="loginForm" ng-submit="submit()">
        <div class="login-box-body">
            <div class="form-group has-feedback">
                <input type="text" class="form-control" ng-model="loginData.mobile" placeholder="请输入用户名">
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" ng-model="loginData.password" placeholder="请输入密码">
            </div>
            <div class="form-group has-feedback captcha-container">
                <input type="text" class="form-control" ng-model="loginData.captcha" placeholder="请输入验证码">
                    <img id="captcha-image" alt="验证码" title="验证码" src="">
            </div>
        </div>
        <input type="submit" class="btn btn-block login-btn" value="登录"></input>
    </form>
    <div class="modal fade bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                    </button>
                    <h4 class="modal-title" id="myModalLabel" ng-bind="warn.title">

                    </h4>
                </div>
                <div class="modal-body" ng-bind="warn.content">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">确定
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
    <!-- /.login-box-body -->
</div>
<#include "/common/footer.ftl" />
<script src="${contextPath!}/js/identity/login.js?vn=${bts!}"></script>
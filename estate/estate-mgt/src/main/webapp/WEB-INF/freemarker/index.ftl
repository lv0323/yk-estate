<#include "/common/header.ftl" />
<link href="${contextPath}/css/app/identity/index.css?vn=${bts!}" rel="stylesheet">
<div class="login-box"  id="loginBox" data-angular>
    <div class="login-logo">
        盈科地产后台管理系统
    </div>
    <!-- /.login-logo -->
<div ng-controller="LoginCtrl" ng-cloak>
    <div ng-if="state.page=='login'">
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
                    <img id="captcha-image" alt="验证码" title="验证码" ng-src="{{captcha}}" ng-click="loadCaptcha()">
            </div>
        </div>
        <input type="submit" class="btn btn-block login-btn" value="登录"></input>
    </form>
        <div class="bottom-content"><a class="to-active"  ng-click="changePage('active')">去激活</a></div>
    </div>
    <div ng-if="state.page=='active'">
    <form id="loginForm" ng-submit="activate()">
        <div class="login-box-body">
            <div class="form-group has-feedback">
                <input type="text" class="form-control" ng-model="activeData.secretKey" placeholder="请输入授权码">
            </div>
            <div class="form-group has-feedback">
                <input type="text" class="form-control" ng-model="activeData.mobile" placeholder="请输入用户名">
            </div>
            <div class="form-group has-feedback captcha-container">
                <input type="text" class="form-control" ng-model="activeData.captcha" placeholder="请输入验证码">
                <img id="captcha-image" alt="验证码" title="验证码" ng-click="loadActiveCaptcha()" ng-src="{{activeCaptcha}}">
            </div>
            <div class="form-group has-feedback sms-container">
                <input type="te xt" class="form-control" ng-model="activeData.sms" placeholder="请输入手机验证码">
                <a class="btn btn-primary get-sms" ng-click="getActiveSMS()" ng-disabled="!activeData.captcha||!activeData.captcha">验证码</a>
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" ng-model="activeData.password" placeholder="请输入密码">
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" ng-model="activeData.passwordAgain" placeholder="请再次输入密码">
            </div>
        </div>
        <input type="submit" class="btn btn-block login-btn" value="激活"></input>
        <div class="bottom-content"><a class="to-active" ng-click="changePage('login')">去登录</a></div>
    </form>
    </div>
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
<script src="${contextPath!}/js/identity/index.js?vn=${bts!}"></script>
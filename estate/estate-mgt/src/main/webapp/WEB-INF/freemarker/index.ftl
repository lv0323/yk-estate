<#include "/common/header.ftl" />
<style>
    .main-footer{
        width: 100%;
        background: transparent;
        border:0;
        margin:0 auto;
        text-align: center;
        position: fixed;
        bottom: 0;
        color: #fff;

    }
</style>
<link href="${contextPath}/css/app/identity/index.css?vn=${bts!}" rel="stylesheet">
<div class="login-box"  id="loginBox" data-angular>
    <div class="login-logo">
        盈家后台管理系统
    </div>
    <!-- /.login-logo -->
<div ng-controller="LoginCtrl" ng-cloak>
    <div ng-if="state.page=='login'">
    <form id="loginForm" ng-submit="submit()">
        <div class="login-box-body">
            <div class="form-group has-feedback">
                <input type="text" class="form-control" ng-model="loginData.mobile" placeholder="请输入手机号">
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
    <form id="activeForm" ng-submit="activate()">
        <div class="identity-box">
            <div class="form-group has-feedback" style="border-bottom: 1px solld #333;">
                <input type="text" class="form-control" ng-model="activeData.secretKey" placeholder="请输入授权码">
            </div>
        </div>
        <div class="identity-box">
            <div class="form-group has-feedback">
                <input type="text" class="form-control" ng-model="activeData.mobile" placeholder="请输入手机号">
            </div>
            <div class="form-group has-feedback captcha-container">
                <input type="text" class="form-control" ng-model="activeData.captcha" placeholder="请输入验证码">
                <img id="captcha-image" alt="验证码" title="验证码" ng-click="loadActiveCaptcha()" ng-src="{{activeCaptcha}}">
            </div>
            <div class="form-group has-feedback sms-container">
                <input type="te xt" class="form-control" ng-model="activeData.sms" placeholder="请输入短信验证码">
                <a class="btn btn-primary get-sms" ng-click="getActiveSMS()" ng-disabled="!activeData.mobile||!activeData.captcha||!activeData.sendSMS">{{activeData.smsText}}</a>
            </div>
            </div>
            <div class="identity-box">
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
<input hidden="hidden" id="contextPath" value="${contextPath!}"/>
<input hidden="hidden" id="clientId" value="${clientId!}"/>
<input hidden="hidden" id="vn" value="${bts!}"/>
<footer class="main-footer">
    Copyright &copy; 2014-2016 <a href="#">Yingke Estate</a>.All rights
    reserved.
</footer>
</div>
<script type="text/javascript">
    var contextPath = document.getElementById('contextPath').value;
    var clientId = document.getElementById('clientId').value;
    var vn = document.getElementById('vn').value;
</script>
<script src="${contextPath}/js/libs/require.min.js?vn=${bts!}"></script>
<script src="${contextPath!}/js/require-config.js?vn=${bts!}"></script>
<script src="${contextPath}/js/app/identity/userInfo.js"></script>
<script src="${contextPath!}/js/app/identity/index.js?vn=${bts!}"></script>
</body>
</html>
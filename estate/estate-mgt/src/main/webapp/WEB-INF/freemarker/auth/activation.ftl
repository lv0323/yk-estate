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
<link href="${contextPath}/css/app/auth/index.css?vn=${bts!}" rel="stylesheet">
<div class="activation-box"  id="activationBox" data-angular>
    <div class="activation-logo">
        盈家后台管理系统--员工激活
    </div>
    <!-- /.login-logo -->
<div ng-controller="ActivationCtrl" ng-cloak>
    <form id="activeForm" ng-submit="activate()">
        <div ng-show=" state.step === 'step1'">
            <div class="form-group has-feedback">
                <input type="text" class="form-control" ng-model="activeData.secretKey" placeholder="请输入授权码">
            </div>
            <div class="form-group has-feedback">
                <input type="text" class="form-control" ng-model="activeData.mobile" ng-pattern="/1\d{10}/" required placeholder="请输入手机号" ng-change="mobileChange()">
            </div>
                <div class="form-group has-feedback captcha-container">
                    <input type="text" class="form-control" ng-model="activeData.captcha" placeholder="请输入验证码">
                    <img id="captcha-image" alt="验证码" title="验证码" ng-click="loadActiveCaptcha()" ng-src="{{activeCaptcha}}">
                </div>
            <button  type="button" class="btn btn-block login-btn" ng-click="nextStep()" ng-disabled="state.disableNext">下一步</button>
        </div>
        <div ng-show=" state.step === 'step2'">
            <div class="form-group has-feedback sms-container">
                <input type="text" class="form-control" ng-model="activeData.sms" placeholder="请输入短信验证码">
                <button class="btn btn-primary get-sms" ng-click="getActiveSMS()" ng-disabled="!activeData.mobile||!activeData.captcha||!activeData.sendSMS">{{activeData.smsText}}</button>
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" ng-model="activeData.password" placeholder="请输入密码">
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" ng-model="activeData.passwordAgain" placeholder="请确认密码">
            </div>
            <input type="submit" class="btn btn-block login-btn" value="激活"></input>
        </div>
        <div class="bottom-content">
            <a ng-clock ng-if="state.step == 'step2'" href="" ng-click="state.step = 'step1';state.disableNext=false">上一步</a>
            <a class="to-active" ng-href="{{'/mgt/index'}}">去登录</a>
        </div>
    </form>
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
<script src="${contextPath!}/js/app/auth/activation.js?vn=${bts!}"></script>
<script src="${contextPath!}/js/cnzz/index.js?vn=${bts!}"></script>
</body>
</html>
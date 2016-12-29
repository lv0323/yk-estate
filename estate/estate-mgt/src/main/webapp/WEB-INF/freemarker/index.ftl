<#include "/common/header.ftl" />
<link href="${contextPath}/css/app/identity/login.css?vn=${bts!}" rel="stylesheet">
<div class="login-box">
    <div class="login-logo">
        盈科地产后台管理系统
    </div>
    <!-- /.login-logo -->

    <form id="loginForm">
        <div class="login-box-body">
            <div class="form-group has-feedback">
                <input type="text" class="form-control" placeholder="请输入用户名">
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" placeholder="请输入密码">
            </div>
            <div class="form-group has-feedback captcha-container">
                <input type="text" class="form-control" placeholder="请输入验证码">
                    <img id="captcha-image" alt="验证码" title="验证码" src="">
            </div>
        </div>
        <input type="submit" class="btn btn-block login-btn" value="登录"></input>
    </form>

    <!-- /.login-box-body -->
</div>
<#include "/common/footer.ftl" />
<script src="${contextPath!}/js/identity/login.js?vn=${bts!}"></script>
<#include "/common/header.ftl" />
<link rel="stylesheet" href="${contextPath}/css/app/approval/newForm/index.css">
<#include "/common/sidebar.ftl" />
<div id="newFrom" class="content-wrapper" ng-controller="FormCtrl as ctrl">
    <section class="content-header">
        <ol class="breadcrumb">
            <li>
                <a>
                    <i class="fa fa-home fa-lg" aria-hidden="true"></i>
                    表单审批
                </a>
            </li>
            <li ng-class="{'active':ctrl.config.header}" ng-click="ctrl.backToHome()"><a href="#/">新建表单</a></li>
            <li ng-show="ctrl.config.header">
                <i ng-bind="ctrl.config.header"></i>
            </li>
        </ol>
    </section>
    <section class="content">
        <div class="row animated fadeInRight">
            <div class="col-lg-12 clearfix" id="content">
                <div ng-view></div>
            </div>
        </div>
    </section>
    <div class="upload-loading" ng-cloak ng-show="ctrl.config.uploading">
        <div class="loader-inner pacman">
            <div></div>
            <div></div>
            <div></div>
            <div></div>
            <div></div>
        </div>
    </div>
</div>
<#include "/common/footer.ftl" />
<script src="${contextPath!}/js/app/approval/newForm/index.js"></script>


<link href="${contextPath}/css/app/fang/index.css" rel="stylesheet">
<link href="${contextPath}/css/app/fang/detail.css" rel="stylesheet">

<#include "/common/header.ftl" />

<!-- -->
<div id="detailInfo" ng-controller="InfoCtrl as ctrl">
    <div class="inquiryDetail">
        <div class="fixed">
            <ol class="breadcrumb col-lg-offset-1 col-md-offset-1 col-sm-offset-1 col-lg-10 col-md-10 col-sm-10" style="padding-top:0;padding-bottom:0;margin-bottom:0;">
                <li><i class="fa fa-home fa-lg"></i>&nbsp;<span ng-bind="ctrl.page.name"></span></li>
                <li class="pull-right" style="margin-right:5px;"><a href="javascript:window.opener=null;window.close();">关闭</a></li>
            </ol>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-offset-1 col-lg-10 col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10">
            <section class="content" style="padding-top:0;">
                <div class="row animated fadeInRight">
                    <div class="col-lg-12">
                        <div class=" m-t-10 box box-solid form-inline">
                            <div class="box-body">
                                <div class="media-item">
                                    <div class="media media-hover clearfix no-border no-padding">
                                        <img class="flagtip" ng-src="{{ctrl.summary.bizType.name ==='RENT' ? '../img/house/rent.png':'../img/house/sale.png'}}" height="55" width="55">
                                        <a class="pull-left" href="#">
                                            <img class="media-object housePhoto" ng-src="{{ctrl.summary.imageURI}}" height="75px" width="100px">
                                        </a>
                                        <div class="media-body">
                                            <div class="col-lg-12">
                                                <div class="clearfix">
                                                    <h6 class="media-heading pull-left" style="font-size:14px;">
                                                        <a id="address" ng-bind="ctrl.summary.head"></a>
                                                    </h6>
                                                    <label class="badge badge-success m-l-15" ng-bind="ctrl.summary.process.label"></label>
                                                    <span ng-bind="ctrl.summary.publishTime|date:'yyyy-MM-dd'"></span>
                                                    <span class="pull-right" style="min-width:90px">编号:<span ng-bind="ctrl.summary.licenceId"></span></span>
                                                </div>
                                                <div class="clearfix text-muted m-t-10">
                                                    <span><span id="floor" ng-bind="ctrl.summary.floor"> </span>/<span ng-bind="ctrl.summary.floorCounts"></span>F</span>
                                                    <span class="m-l-10"></span>
                                                    <span class="m-l-10" ng-bind="ctrl.summary.layoutString"></span>
                                                </div>
                                                <div class="clearfix m-t-10 text-muted">
        										<span>
                                                    <strong ng-bind="ctrl.summary.estateArea"></strong>m<sup>2</sup>&nbsp;<strong>0.00</strong>m<sup>2</sup>
                                                    (<strong class="text-danger"><span ng-bind="ctrl.summary.publishPrice"></strong><span ng-bind="ctrl.summary.priceUnit.label"></span>&nbsp;
                                                    <strong class="text-warning" ng-bind="ctrl.summary.unitPrice"></strong>元/m<sup>2</sup>)
                                                </span>
                                                    <a class="edit-info pull-right" onclick=""><i class="fa fa-pencil"></i>修改</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row animated fadeInRight">
                    <div class="col-lg-12">
                        <div class="nav-tabs-custom">
                            <!-- Tabs within a box -->
                            <ul class="nav nav-tabs" id="yeqian">
                                <li class="active"><a href="#basic-info" data-toggle="tab">基本信息</a></li>
                                <li><a href="#contact-info" data-toggle="tab" onclick="getEmhouseListByHouseid();">归属信息</a></li>
                                <li><a href="#follow-info" data-toggle="tab" onclick="getHouseFollowPage();">跟进记录</a></li>
                                <li><a href="#survey-info" data-toggle="tab" onclick="getHousesurveyListByHouseId();">勘察记录</a></li>
                                <li><a href="#survey-info" data-toggle="tab" onclick="getHousesurveyListByHouseId();">外网描述</a></li>
                            </ul>
                            <div class="tab-content">
                                <!-- Morris chart - Sales -->
                                <div class="active tab-pane" id="basic-info">
                                    <div class="row">
                                        <div class="col-lg-3 col-md-3 col-sm-12">
                                            <div class="panel">
                                                <div class="panel-heading">业主信息</div>
                                                <div class="panel-body form-inline">
                                                    <div class="m-b-15">
                                                        <span class="pull-left">业主:</span>
                                                        <div class="m-t-10 m-l-40 p-t-3">***</div>
                                                    </div>
                                                    <div class="m-b-15">
                                                        <span class="pull-left">手机:</span>
                                                        <div class="m-t-10 m-l-40 p-t-3">******</div>
                                                    </div>
                                                    <a href="javascript:void(0)" class="btn btn-white" style="display:block;" onclick="checkViewcount()">查看业主</a>
                                                </div>
                                            </div>
                                            <div class="panel">
                                                <div class="panel-heading">
                                                    归属(第一归属)
                                                </div>
                                                <div class="panel-body form-inline">
                                                    <ul class="media-list">
                                                        <li class="media media-hover">
                                                            <a class="pull-left" href="#">
                                                                <!-- BUG #10650 房源详情页显示第一归属人头像过大，设定宽度高度 -->
                                                                <img src="http://img.12157.top/upload/uploadfile/pic/web/14877338294652679.jpg " class="img-circle" height="45px" width="45px">
                                                            </a>
                                                            <div class="media-body">
                                                                <h6 class="media-heading" ng-bind="ctrl.summary.infoOwner.employeeName">归属人</h6>
                                                                <p class="text-muted" ng-bind="ctrl.summary.infoOwner.departmentName">部门</p>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                    <!--
                                                    <div id="showMore">
                                                        <a href="javascript:void(0);" class="btn btn-white" style="display:block;">显示全部</a>
                                                    </div>
                                                    -->
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-9 col-md-9 col-sm-12" style="padding:0;">
                                            <div class="table-responsive">
                                                <table class="table table-bordered">
                                                    <tbody>
                                                    <tr>
                                                        <th colspan="9">基本信息</th>
                                                        <th class="text-right"><a onclick="updateHousetail();"><i class="fa fa-pencil"></i>修改</a></th>
                                                    </tr>
                                                    <tr>
                                                        <td class="text-muted text-center" style="width:60px;">特性</td>
                                                        <td colspan="9">
                                                            <label class="label label-success m-r-5" ng-bind="ctrl.ext.level.label"></label>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="text-muted text-center" style="width:80px;">现状</td>
                                                        <td colspan="5" ng-bind="ctrl.summary.process.label"></td>
                                                        <td class="text-muted text-center" style="width:80px;">来源</td>
                                                        <td colspan="5" ng-bind="ctrl.ext.source.label"></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="text-muted text-center">委托</td>
                                                        <td ng-bind="ctrl.ext.delegateType.label"></td>
                                                        <td class="text-muted text-center">委托日期</td>
                                                        <td ng-bind="ctrl.ext.delegateStart|date:'yyyy-MM-dd'"></td>
                                                        <td class="text-muted text-center">到期日期</td>
                                                        <td ng-bind="ctrl.ext.delegateEnd|date:'yyyy-MM-dd'"></td>
                                                        <td class="text-muted text-center">委托编号</td>
                                                        <td colspan="5">1000</td>
                                                    </tr>
                                                    <tr>
                                                        <td class="text-muted text-center">看房</td>
                                                        <td colspan="10" ng-bind="ctrl.ext.showing.label"></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="text-muted text-center">证件地址</td>
                                                        <td colspan="5" ng-bind="ctrl.ext.certifAddress"></td>
                                                        <td class="text-muted text-center">证件编码</td>
                                                        <td colspan="5" ng-bind="ctrl.ext.certifNo"></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="text-muted text-center">证件</td>
                                                        <td ng-bind="ctrl.ext.certifType.label"></td>
                                                        <td class="text-muted text-center">出证日期</td>
                                                        <td></td>
                                                        <td class="text-muted text-center">产权类型</td>
                                                        <td colspan="5" ng-bind="ctrl.ext.propertyType.label"></td>
                                                    </tr>
                                                    <!--<tr>
                                                        <td class="text-muted text-center">装修</td>
                                                        <td colspan="9"></td>
                                                    </tr>-->
                                                    <tr>
                                                        <td class="text-muted text-center">税款</td>
                                                        <td ng-bind="ctrl.ext.taxesWilling.label"></td>
                                                        <td class="text-muted text-center">付佣</td>
                                                        <td ng-bind="ctrl.ext.commissionWilling.label"></td>
                                                        <td class="text-muted text-center">原购价</td>
                                                        <td colspan="5" ng-bind="ctrl.ext.purchasePrice">
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="text-muted text-center">备注</td>
                                                        <td colspan="9"></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="tab-pane" id="contact-info">
                                    <div class="box box-solid no-shadow">
                                        <div class="box-header with-border">
                                            <h3 class="box-title pull-left">归属人</h3>
                                            <div class="box-tools">
                                                <a class="btn addAscription pull-right" onclick="saveOrUpdateEmhouse('add','','');"><i class="fa fa-plus" aria-hidden="true"></i>新增归属</a>
                                            </div>
                                        </div>
                                        <div class="box-body" id="emhouseList">
                                        </div>
                                    </div>
                                </div>
                                <div class="tab-pane" id="follow-info">
                                    <div class="box-tools" style="position:absolute;top:7px;right:10px">
                                        <a class="btn addAscription pull-right" onclick="saveHouseFollow(2982)" href="javascript:void(0)"><i class="fa fa-plus" aria-hidden="true"></i>新增跟进</a>
                                    </div>
                                    <form id="houseFollowParamId" action="/housemanage/housefollow!getHouseFollowListByHouseid.do">
                                        <input id="housefollowMethod" name="housefollowVO.fyHouseId" value="2982" type="hidden">
                                        <!-- 详情页访问跟进记录标记 -->
                                        <input id="" name="operationType" value="houseDetail" type="hidden">
                                        <div id="showHouseFollowPageDiv">
                                        </div>
                                    </form>
                                </div>
                                <div class="tab-pane" id="survey-info">
                                    <div id="housesurveyList2"></div>
                                </div>
                            </div>
                        </div>
                        <div class="text-center">
                            录入时间：<span ng-bind="ctrl.summary.createTime|date:'yyyy-MM-dd hh:mm:ss'"></span>
                        </div>
                    </div>
                </div>
                <!-- /.row (main row) -->
            </section>
        </div>
    </div>
    <div class="modal fade" id="warnModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                    </button>
                    <h4 class="modal-title" id="myModalLabel" ng-bind="page.warn.title">
                    </h4>
                </div>
                <div class="modal-body" ng-bind="page.warn.content">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" ng-click="page.warn.closeF()" data-dismiss="modal">确定</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- /.content-wrapper -->

<#include "/common/footer.ftl" />
<script src="${contextPath!}/js/app/fang/detail.js?vn=${bts!}"></script>
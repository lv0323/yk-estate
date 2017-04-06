<link href="${contextPath}/css/app/fang/index.css" rel="stylesheet">
<link href="${contextPath}/css/app/fang/detail.css" rel="stylesheet">

<#include "/common/header.ftl" />

<!-- -->
<div id="detailInfo" ng-controller="InfoCtrl as ctrl">
    <div class="inquiryDetail">
        <div class="fixed">
            <ol class="breadcrumb col-lg-offset-1 col-md-offset-1 col-sm-offset-1 col-lg-10 col-md-10 col-sm-10">
                <li><i class="fa fa-home fa-lg"></i>&nbsp;<span ng-bind="ctrl.page.name"></span></li>
                <li class="pull-right" style="margin-right:5px;"><a href="javascript:window.opener=null;window.close();">关闭</a></li>
            </ol>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-offset-1 col-lg-10 col-md-offset-1 col-md-10 col-sm-offset-1 col-sm-10">
            <section class="content">
                <div class="row animated fadeInRight">
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
                                                <span class="pull-right" style="min-width:90px">授权编号:<span ng-bind="ctrl.summary.licenceId"></span></span>
                                            </div>
                                            <div class="clearfix text-muted m-t-10">
                                                <span><span id="floor" ng-bind="ctrl.summary.floor"> </span>/<span ng-bind="ctrl.summary.floorCounts"></span>F</span>
                                                <span class="m-l-10"></span>
                                                <span class="m-l-10" ng-bind="ctrl.summary.layoutString"></span>
                                            </div>
                                            <div class="clearfix m-t-10 text-muted">
                                            <span>
                                                <strong ng-bind="ctrl.summary.estateArea"></strong>m<sup>2</sup>&nbsp;<strong ng-bind="ctrl.summary.realArea"></strong>m<sup>2</sup>
                                                (<strong class="text-danger"><span ng-bind="ctrl.summary.publishPrice"></strong><span ng-bind="ctrl.summary.priceUnit.label"></span>&nbsp;
                                                <strong class="text-warning" ng-bind="ctrl.summary.unitPrice"></strong><span ng-bind="ctrl.summary.bizType.name==='RENT'?ctrl.summary.priceUnit.label:'元'"></span>/m<sup>2</sup></span>
                                                )
                                            </span>
                                                <a class="edit-info pull-right" href="#" ng-click="ctrl.baseInfoInit()"><i class="fa fa-pencil"></i>修改</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="animated fadeInRight">
                    <div class="nav-tabs-custom">
                        <!-- Tabs within a box -->
                        <ul class="nav nav-tabs" id="yeqian">
                            <li class="active"><a href="#basic-info" data-toggle="tab">基本信息</a></li>
                            <li><a href="#follow-info" data-toggle="tab">跟进记录</a></li>
                            <li><a href="#survey-info" data-toggle="tab">勘察记录</a></li>
                            <li><a href="#image-info" data-toggle="tab">房源图片</a></li>
                            <li><a href="#descr-info" data-toggle="tab">外网描述</a></li>
                        </ul>
                        <div class="tab-content">
                            <!-- Morris chart - Sales -->
                            <div class="active tab-pane" id="basic-info">
                                <div class="row">
                                    <div class="col-lg-3 col-md-3 col-sm-12" style="padding-left: 0">
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
                                                <a href="javascript:void(0)" class="btn btn-white" style="display:block;" ng-click="ctrl.contactInfoInit()">查看业主</a>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">归属</div>
                                            <div class="panel-body form-inline">
                                                <div class="m-b-15">
                                                    <span class="pull-left">归属人:</span>
                                                    <div class="m-t-10 m-l-40" ng-bind="ctrl.summary.infoOwner.employeeName"></div>
                                                </div>
                                                <div class="m-b-15">
                                                    <span class="pull-left">部门:</span>
                                                    <div class="m-t-10 m-l-40" ng-bind="ctrl.summary.infoOwner.departmentName"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-9 col-md-9 col-sm-12" style="padding:0;">
                                        <div class="table-responsive">
                                            <table class="table table-bordered">
                                                <tbody>
                                                <tr>
                                                    <th colspan="9">基本信息</th>
                                                    <th class="text-right"><a ng-click="ctrl.extInfoInit()" ng-href="javascript:;"><i class="fa fa-pencil"></i>修改</a></th>
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
                                                    <td ng-bind="ctrl.ext.purchaseDate"></td>
                                                    <td class="text-muted text-center">产权类型</td>
                                                    <td colspan="5" ng-bind="ctrl.ext.propertyType.label"></td>
                                                </tr>
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
                                                    <td colspan="9" ng-bind="ctrl.ext.note"></td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="follow-info">
                                <div class="box-tools" style="position:absolute;top:7px;right:10px">
                                    <a class="btn addAscription pull-right" ng-click="ctrl.newFollowInit()" href="javascript:void(0)"><i class="fa fa-plus" aria-hidden="true"></i>新增跟进</a>
                                </div>
                                <div class="table-responsive contractlist">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th>跟进人</th>
                                            <th>跟进方式</th>
                                            <th>跟进日期</th>
                                            <th>交易类型</th>
                                            <th>房源状态</th>
                                            <th>发布日期</th>
                                            <th>发布天数</th>
                                            <#--<th class="text-right">操作</th>-->
                                        </tr>
                                        </thead>
                                        <tbody ng-repeat="follow in ctrl.followList">
                                        <tr>
                                            <td>
                                                <div class="img-circle-container">
                                                    <img ng-src="{{follow.avatarURI}}" class="img-circle">
                                                </div>
                                                {{follow.departmentName}}-{{follow.employeeName}}
                                            </td>
                                            <td><label class="badge badge-success">{{follow.followType.label}}</label></td>
                                            <td>{{follow.createTime|date:'yyyy-MM-dd'}}</td>
                                            <td><label class="badge badge-danger">{{follow.fangTiny.bizType.label}}</label></td>
                                            <td><label class="badge badge-danger">{{follow.fangTiny.process.label}}</label></td>
                                            <td>{{follow.fangTiny && follow.fangTiny.publishTime|date:'yyyy-MM-dd'}}</td>
                                            <td>{{follow.publishedDay}}</td>
                                            <#--<td class="text-right">
                                                <a href="javascript:void(0);" ng-click="ctrl.deleteFollow(follow.id)">删除</a>
                                            </td>-->
                                        </tr>
                                        <tr><td colspan="7">跟进内容:{{follow.content}}</td></tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="pagination-container">
                                    <ul id="follow_paging" class="pagination"></ul>
                                </div>
                            </div>
                            <div class="tab-pane" id="survey-info">
                                    <div class="box-tools" style="position:absolute;top:7px;right:10px">
                                        <a class="btn addAscription pull-right" ng-click="ctrl.newSurveyInit()" href="javascript:void(0)"><i class="fa fa-plus" aria-hidden="true"></i>新增勘察</a>
                                    </div>
                                    <div class="table-responsive contractlist" id="houseSurveyList"><div class="table-responsive contractlist">
                                        <table class="table">
                                            <thead>
                                            <tr>
                                                <th>勘察人</th>
                                                <th>勘察时间</th>
                                                <th>交易类型</th>
                                                <th>房源状态</th>
                                                <th>发布日期</th>
                                                <th>发布天数</th>
                                            </tr>
                                            </thead>
                                            <tbody ng-repeat="survey in ctrl.surveyList">
                                            <tr>
                                                <td>
                                                    <div class="img-circle-container">
                                                        <img ng-src="{{survey.avatarURI}}" class="img-circle">
                                                    </div>
                                                    {{survey.departmentName}} - {{survey.employeeName}}</td>
                                                <td>{{survey.createTime|date:'yyyy-MM-dd'}}</td>
                                                <td><label class="badge badge-danger">{{survey.fangTiny.bizType.label}}</label></td>
                                                <td><label class="badge badge-danger">{{survey.fangTiny.process.label}}</label></td>
                                                <td>{{survey.fangTiny && survey.fangTiny.publishTime|date:'yyyy-MM-dd'}}</td>
                                                <td>{{survey.publishedDay}}</td>
                                            </tr>
                                            <tr>
                                                <td colspan="3"><span class="text-muted">优势：</span>{{survey.advantage}}</td>
                                                <td colspan="4"><span class="text-muted">劣势：</span>{{survey.disAdvantage}}</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class="pagination-container">
                                    <ul id="survey_paging" class="pagination"></ul>
                                </div>
                            </div>
                            <div class="tab-pane" id="image-info">
                                <div id="housesurveyList2">
                                    <div class="box box-solid no-shadow">
                                        <div class="box-header" style="border-bottom: 1px solid #eee;">
                                            <h3 class="pull-left box-title">
                                                房源照片
                                            </h3>
                                        </div>
                                        <div class="box-body surveyImg">
                                            <div class="row">
                                                <div class="col-lg-3 col-md-3 col-sm-6" ng-repeat="type in ctrl.imageTypeList">
                                                    <div class="thumbnail">
                                                       <img ng-src="{{ctrl[type.key].count > 0? ctrl[type.key].list[0].fileURI : type.defaultMap}}"/>
                                                        <div class="caption clearfix">
                                                            <a class="btn btn-warning btn-xs pull-left" ng-click="ctrl.addMap(type.para, type.addText, type.key);">{{type.addText}}</a>
                                                            <a class="btn btn-success btn-xs pull-left m-l-30" ng-click="ctrl.showAllMap(type.para, type.addText, type.key);">查看全部</a>
                                                            <span class="pull-right">{{ctrl[type.key].count}}张</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="descr-info">
                                <div class="row">
                                    <div class="col-lg-12 col-md-12 col-sm-12" style="padding:0;">
                                        <div class="table-responsive">
                                            <table class="table table-bordered">
                                                <tbody>
                                                <tr>
                                                    <th colspan="9">基本信息</th>
                                                    <th width="10%" class="text-right"><a ng-href="#" ng-click="ctrl.descrUpdateInit()"><i class="fa fa-pencil" ng-click="ctrl.initUpdateDEscr()"></i>修改</a></th>
                                                </tr>
                                                <tr>
                                                    <td width="10%" class="text-muted text-center">房屋标题</td>
                                                    <td colspan="9" ng-bind="ctrl.description.title"></td>
                                                </tr>
                                                <tr>
                                                    <td width="10%" class="text-muted text-center">核心卖点</td>
                                                    <td colspan="9" ng-bind="ctrl.description.core"></td>
                                                </tr>
                                                <tr>
                                                    <td class="text-muted text-center">户型介绍</td>
                                                    <td colspan="9" ng-bind="ctrl.description.huXing"></td>
                                                </tr>
                                                <tr>
                                                    <td class="text-muted text-center">装修描述</td>
                                                    <td colspan="9" ng-bind="ctrl.description.zhuangXiu"></td>
                                                </tr>
                                                <tr>
                                                    <td class="text-muted text-center">权属抵押</td>
                                                    <td colspan="9" ng-bind="ctrl.description.quanShu"></td>
                                                </tr>
                                                <tr>
                                                    <td class="text-muted text-center">交通出行</td>
                                                    <td colspan="9" ng-bind="ctrl.description.jiaoTong"></td>
                                                </tr>
                                                <tr>
                                                    <td class="text-muted text-center">小区介绍</td>
                                                    <td colspan="9" ng-bind="ctrl.description.xiaoQu"></td>
                                                </tr>
                                                <tr>
                                                    <td class="text-muted text-center">投资分析</td>
                                                    <td colspan="9" ng-bind="ctrl.description.touZi"></td>
                                                </tr><tr>
                                                    <td class="text-muted text-center">周边配套</td>
                                                    <td colspan="9" ng-bind="ctrl.description.peiTao"></td>
                                                </tr>
                                                <tr>
                                                    <td class="text-muted text-center">学区信息</td>
                                                    <td colspan="9" ng-bind="ctrl.description.xueQu"></td>
                                                </tr>
                                                <tr>
                                                    <td class="text-muted text-center">税费解析</td>
                                                    <td colspan="9" ng-bind="ctrl.description.shuiFei"></td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="text-center">
                        录入时间：<span ng-bind="ctrl.summary.createTime|date:'yyyy-MM-dd hh:mm:ss'"></span>
                    </div>
                </div>
            </section>
        </div>
    </div>
    <div class="modal fade layout-dialog" id="layoutModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">户型</h4>
                </div>
                <div class="modal-body">
                    <div class="tip-box form-horizontal">
                        <div class="clearfix">
                            <label class="pull-left control-label">室</label>
                            <div class="pull-left m-t-7">
                                <span ng-repeat="shi in ctrl.layoutList.shi" class="tip tip-info" value="{{shi.value}}"
                                      ng-class="{'active':shi.value == ctrl.currentLayout.sCounts}" ng-click="ctrl.setLayout('sCounts', shi.value)">{{shi.value?shi.name+'室':shi.name}}</span>
                            </div>
                        </div>
                        <div class="clearfix">
                            <label class="pull-left control-label">厅</label>
                            <div class="pull-left m-t-7">
                                <span ng-repeat="ting in ctrl.layoutList.ting" class="tip tip-info" value="{{ting.value}}"
                                      ng-class="{'active':ting.value == ctrl.currentLayout.tCounts}" ng-click="ctrl.setLayout('tCounts', ting.value)">{{ting.value? ting.name+'厅' :ting.name}}</span>
                            </div>
                        </div>
                        <div class="clearfix">
                            <label class="pull-left control-label">厨</label>
                            <div class="pull-left m-t-7">
                                <span ng-repeat="chu in ctrl.layoutList.chu" class="tip tip-info" value="{{chu.value}}"
                                      ng-class="{'active':chu.value == ctrl.currentLayout.cCounts}" ng-click="ctrl.setLayout('cCounts', chu.value)">{{chu.value?chu.name+'厨':chu.name}}</span>
                            </div>
                        </div>
                        <div class="clearfix">
                            <label class="pull-left control-label">卫</label>
                            <div class="pull-left m-t-7">
                                <span ng-repeat="wei in ctrl.layoutList.wei" class="tip tip-info" value="{{wei.value}}"
                                      ng-class="{'active':wei.value == ctrl.currentLayout.wCounts}" ng-click="ctrl.setLayout('wCounts', wei.value)">{{wei.value ? wei.name+'卫':wei.name}}</span>
                            </div>
                        </div>
                        <div class="clearfix">
                            <label class="pull-left control-label">阳台</label>
                            <div class="pull-left m-t-7">
                                <span ng-repeat="yangtai in ctrl.layoutList.yangtai" class="tip tip-info" value="{{yangtai.value}}"
                                      ng-class="{'active':yangtai.value == ctrl.currentLayout.ytCounts}" ng-click="ctrl.setLayout('ytCounts', yangtai.value)">{{yangtai.value? yangtai.name+'阳台':yangtai.name}}</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#layoutModel" ng-click="ctrl.layoutConfirm()">确认</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
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
    <div class="modal fade" id="descrModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                    </button>
                    <h4 class="modal-title" id="myModalLabel">外网描述</h4>
                </div>
                <div class="modal-body">
                    <form class="form-inline layui-layer-wrap">
                        <div class="form-group clearfix">
                            <label class="control-label col-xs-2">房屋标题</label>
                            <div class="col-xs-10">
                                <textarea name="" rows="2" cols="" class="form-control" placeholder="房屋标题" ng-model="ctrl.descrUpdateInfo.title"></textarea>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-xs-2">核心卖点</label>
                            <div class="col-xs-10">
                                <textarea name="" rows="2" cols="" class="form-control" placeholder="核心卖点" ng-model="ctrl.descrUpdateInfo.core"></textarea>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-xs-2">户型介绍</label>
                            <div class="col-xs-10">
                                <textarea name="" rows="2" cols="" class="form-control" placeholder="户型介绍" ng-model="ctrl.descrUpdateInfo.huXing"></textarea>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-xs-2">装修描述</label>
                            <div class="col-xs-10">
                                <textarea name="" rows="2" cols="" class="form-control" placeholder="装修描述" ng-model="ctrl.descrUpdateInfo.zhuangXiu"></textarea>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-xs-2">权属抵押</label>
                            <div class="col-xs-10">
                                <textarea name="" rows="2" cols="" class="form-control" placeholder="权属抵押" ng-model="ctrl.descrUpdateInfo.quanShu"></textarea>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-xs-2">交通出行</label>
                            <div class="col-xs-10">
                                <textarea name="" rows="2" cols="" class="form-control" placeholder="交通出行" ng-model="ctrl.descrUpdateInfo.jiaoTong"></textarea>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-xs-2">小区介绍</label>
                            <div class="col-xs-10">
                                <textarea name="" rows="2" cols="" class="form-control" placeholder="小区介绍" ng-model="ctrl.descrUpdateInfo.xiaoQu"></textarea>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-xs-2">投资分析</label>
                            <div class="col-xs-10">
                                <textarea name="" rows="2" cols="" class="form-control" placeholder="投资分析" ng-model="ctrl.descrUpdateInfo.touZi"></textarea>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-xs-2">周边配套</label>
                            <div class="col-xs-10">
                                <textarea name="" rows="2" cols="" class="form-control" placeholder="周边配套" ng-model="ctrl.descrUpdateInfo.peiTao"></textarea>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-xs-2">学区信息</label>
                            <div class="col-xs-10">
                                <textarea name="" rows="2" cols="" class="form-control" placeholder="学区信息" ng-model="ctrl.descrUpdateInfo.xueQu"></textarea>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-xs-2">税费解析</label>
                            <div class="col-xs-10">
                                <textarea name="" rows="2" cols="" class="form-control" placeholder="税费解析" ng-model="ctrl.descrUpdateInfo.shuiFei"></textarea>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" ng-click="ctrl.descrUpdate()">确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="baseModel" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel">基本信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group clearfix">
                        <label class="control-label">用途</label>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <input type="text" class="form-control" name="houseType" ng-model="ctrl.baseInfo.houseType.label" disabled/>
                        </div>
                        <input type="hidden" value="9880"/>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <select id="houseSubType" select-picker class="selectpicker show-menu-arrow form-control sel-news" ng-model="ctrl.baseInfo.houseSubType">
                            <#list houseSubTypes ? if_exists as type>
                                <option value="${type.name()}"<#if (type.name() = houseSubType)> selected="selected"</#if>>${type.getLabel()}</option>
                            </#list>
                            </select>
                        </div>
                        <div class="col-lg-4 col-md-4  col-sm-4 text-right">
                            <label class="mt7">授权编号：{{ctrl.baseInfo.licenceId}}</label>
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="control-label">类型</label>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <select id="houseStructureType" select-picker class="selectpicker show-menu-arrow form-control bs-select-hidden sel-news" name="houseStructureType"
                                    ng-model="ctrl.baseInfo.structureType" ng-change="ctrl.selectPickerChange('#houseStructureType', 'structureType')">
                                <option value="">结构类型</option>
                            <#list structureType?if_exists as type>
                                <option value="${type.name()}">${type.getLabel()}</option>
                            </#list>
                            </select>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <div class="input-group date form_date" format="yyyy" datetimepicker key="buildYear" change="datePickChange">
                                <input name="buildYear" class="form-control" size="16" placeholder="建造年代" type="text" ng-model="ctrl.baseInfo.buildYear" required ng-pattern="/\d{4}$/">
                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="control-label">房型</label>
                        <div class="col-lg-4 col-md-4 col-sm-4 m-t-7">
                            <p>
                                <span class="stwy" id="countf_chosen"></span>
                                <input id="countf" type="hidden" name="houseVO.fyHouseCountf"   reg="^\S+$"  class="fyHouseCountf" />
                                <input type="hidden" name="houseVO.fyHouseCountt" class="fyHousetailCountt"/>
                                <input type="hidden" name="houseVO.fyHouseCountw" class="fyHousetailCountw"/>
                                <input type="hidden" name="houseVO.fyHouseCounty" class="fyHousetailCounty"/>
                                <a class="houseHold" data-toggle="modal" data-target="#layoutModel" href="#"  class="m-l-30" ng-click="ctrl.layoutDialogShow()">
                                    <i class="fa fa-th-list"></i>
                                    <span ng-bind="ctrl.baseInfo.layoutString||'选择户型'"></span>
                                </a>
                            </p>
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="control-label">朝向</label>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <select id="houseOrientation" select-picker class="selectpicker show-menu-arrow form-control" name="orientation" ng-model="ctrl.baseInfo.orientation" ng-change="ctrl.selectPickerChange('#houseOrientation', 'orientation')">
                            <#list orientation?if_exists as ortation>
                                <option value="${ortation.name()}">${ortation.getLabel()}</option>
                            </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="control-label">电梯</label>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <select select-picker class="selectpicker show-menu-arrow form-control" name="houseLift" id="houseHasElevator"
                                    ng-model="ctrl.baseInfo.hasElevator" ng-change="ctrl.selectPickerChange('#houseHasElevator', 'hasElevator')">
                                <option value="Y">有</option>
                                <option value="N">无</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="control-label">供暖类型</label>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <select select-picker class="selectpicker show-menu-arrow form-control" name="houseHeating" id="houseHeatingType"
                                    ng-model="ctrl.baseInfo.heatingType" ng-change="ctrl.selectPickerChange('#houseHeatingType', 'heatingType')">
                            <#list heatingType ?if_exists as type>
                                <option value="${type.name()}">${type.getLabel()}</option>
                            </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="control-label">装修</label>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <select select-picker class="selectpicker show-menu-arrow form-control" name="houseDecorate" id="houseDecorate" ng-model="ctrl.baseInfo.decorate" ng-change="ctrl.selectPickerChange('#houseDecorate', 'decorate')">
                            <#list decorate ?if_exists as type>
                                <option value="${type.name()}">${type.getLabel()}</option>
                            </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="control-label">面积</label>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <input type="text" name="estateArea" class="form-control" required placeholder="建筑面积" ng-model="ctrl.baseInfo.estateArea" ng-pattern="/^\d{1,5}([.]\d{1,2})*$/" ng-blur="ctrl.estateAreaCheck()"/>
                        </div>
                        <div class="pull-left m-t-7 p-l-0">
                            ㎡
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <input type="text" name="housePartsqm" class="form-control" placeholder="套内面积" ng-model="ctrl.baseInfo.realArea" ng-pattern="/^\d{1,5}([.]\d{1,2})*$/" ng-blur="ctrl.checkArea()"/>
                        </div>
                        <div class="pull-left m-t-7 p-l-0">
                            ㎡
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="control-label">价格</label>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <input type="text" name="publishPrice" required class="form-control" placeholder="总价" strName="总价" ng-model="ctrl.baseInfo.publishPrice" ng-blur="ctrl.publishPriceSet('publishPrice')" ng-pattern="/^(?!0+(?:\.0+)?$)(?:[1-9]\d{0,8}|0)(?:\.\d{1,2})?$/"/>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2" class="pull-left" id="rent" style="display:block">

                            <select select-picker class="selectpicker show-menu-arrow form-control sel-news" name="priceUnit" id="priceUnit" ng-model="ctrl.baseInfo.priceUnit">
                            <#list priceUnit ?if_exists as unit>
                                <option value="${unit.name()}">${unit.getLabel()}</option>
                            </#list>
                            </select>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <input type="text" name="housePriceunit" strName="单价" class="form-control" ng-model="ctrl.baseInfo.unitPrice" placeholder="单价" ng-blur="ctrl.unitPriceSet('unitPrice')" ng-pattern="/^(?!0+(?:\.0+)?$)(?:[1-9]\d{0,8}|0)(?:\.\d{1,2})?$/"/>
                        </div>
                        <div class="pull-left m-t-7 p-l-0" id="danwei">
                            <#list priceUnit ?if_exists as unit>
                                <span ng-show="ctrl.baseInfo.priceUnit === '${unit.name()}'">${unit.getLabel()}</span>
                            </#list>/㎡
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="control-label">底价</label>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <input type="text" name="housePriceMin" ng-model="ctrl.baseInfo.bottomPrice" class="form-control" placeholder="底价" strName="底价" ng-blur="ctrl.bottomPriceCheck('publishPrice')" ng-pattern="/^(?!0+(?:\.0+)?$)(?:[1-9]\d{0,8}|0)(?:\.\d{1,2})?$/"/>
                        </div>
                        <div class="pull-left m-t-7 p-l-0" id="danwei">
                        <#list priceUnit ?if_exists as unit>
                            <span ng-show="ctrl.baseInfo.priceUnit === '${unit.name()}'">${unit.getLabel()}</span>
                        </#list>
                        </div>
                    </div>
                    <div class="form-group clearfix" ng-show="ctrl.summary.bizType.name ==='SELL'">
                        <label class="control-label">落户</label>
                        <div class="col-lg-8 col-md-8 col-sm-8 form-inline m-t-7">
                            <div class="radio radio-nice" >
                                <input type="radio" name="houseSettle" id="settleYes" ng-value="'Y'" ng-model="ctrl.baseInfo.resident"/>
                                <label for="settleYes">是</label>
                            </div>
                            <div class="radio radio-nice" >
                                <input type="radio" name="houseSettle" id="settleNo" ng-value="'N'" ng-model="ctrl.baseInfo.resident"/>
                                <label for="settleNo">否</label>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" ng-click="ctrl.baseInfoUpdate()">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
    <div class="modal fade" id="extModel" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">基本信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" name="extDialogForm">
                    <div class="form-group clearfix">
                        <label class="control-label">特性</label>
                        <div class="pull-left" style="margin-top:8px">
                        <#list houseLevel ?if_exists as level>
                            <div class="radio radio-inline" style="padding-top:0px">
                                <input value="${level.name()}" id="level${level.name()}" ng-model="ctrl.updateExt.level"
                                       type="radio">
                                <label for="level${level.name()}">${level.getLabel()}</label>
                            </div>
                        </#list>
                        </div>
                        <span class="opt-gap pull-left" style="margin-top:10px;"></span>
                        <div class="pull-left" style="margin-top:8px">
                            <div class="radio radio-inline" style="padding-top:0px">
                                <input type="radio" id="character10141" ng-value="5"
                                       ng-model="ctrl.updateExt.overYears"/>
                                <label for="character10141">满五年</label>
                            </div>
                            <div class="radio radio-inline" style="padding-top:0px">
                                <input type="radio" id="character10142" ng-value="2"
                                       ng-model="ctrl.updateExt.overYears"/>
                                <label for="character10142">满两年</label>
                            </div>
                            <div class="radio radio-inline" style="padding-top:0px">
                                <input type="radio" id="character10143" value="" ng-model="ctrl.updateExt.overYears"/>
                                <label for="character10143">不满两年</label>
                            </div>
                        </div>
                        <span class="opt-gap pull-left" style="margin-top:10px;"></span>
                        <div class="pull-left" id="tx3">
                            <div class="checkbox checkbox-nice checkbox-inline">
                                <input type="checkbox" name="houseCharacter" id="character10140"
                                       ng-model="ctrl.updateExt.isOnly"/>
                                <label for="character10140">唯一住房</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="control-label">证件</label>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <select select-picker class="selectpicker show-menu-arrow form-control" name="houseCertifType" id="houseCertifType"
                                    ng-model="ctrl.updateExt.certifType" ng-change="ctrl.selectPickerChange('#houseCertifType', 'certifType', 'updateExt')">
                            <#list certifType ?if_exists as type>
                                <option value="${type.name()}">${type.getLabel()}</option>
                            </#list>
                            </select>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <div class="input-group date form_date" datetimepicker key="purchaseDate" change="datePickChange" owner="updateExt">
                                <input class="form-control " size="16" type="text" id="houseProveDate" ng-model="ctrl.updateExt.purchaseDate" placeholder="出证日期" name="houseProveDate"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <select select-picker class="selectpicker show-menu-arrow form-control" name="housePType" id="housePropertyType"
                                    ng-model="ctrl.updateExt.propertyType" ng-change="ctrl.selectPickerChange('#housePropertyType', 'propertyType', 'updateExt')">
                            <#list propertyType ?if_exists as type>
                                <option value="${type.name()}">${type.getLabel()}</option>
                            </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="control-label">证件地址</label>
                        <div class="col-lg-4 col-md-4 col-sm-4">
                            <input type="text" name="address" name="address" class="form-control" required placeholder="房产证地址" ng-model="ctrl.updateExt.address"/>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <input name="permitNumber" class="form-control" placeholder="证件编号" type="text" ng-model="ctrl.updateExt.certifNo">
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="control-label">委托</label>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <select select-picker class="selectpicker show-menu-arrow form-control"  reg="^\S+$"  name="delegateType" id="houseDelegateType"
                                    ng-model="ctrl.updateExt.delegateType"  ng-change="ctrl.selectPickerChange('#houseDelegateType', 'delegateType', 'updateExt')">
                                <option value="">--请选择--</option>
                            <#list delegateType ?if_exists as type>
                                <option value="${type.name()}">${type.getLabel()}</option>
                            </#list>
                            </select>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <div class="input-group date form_date" datetimepicker key="delegateStart" change="datePickChange" owner="updateExt">
                                <input class="form-control bgWhite" ng-model="ctrl.updateExt.delegateStart" required ng-pattern="/\d{4}-\d{2}-\d{2}$/" type="text" placeholder="开始日期" name="delegateStart"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <div class="input-group date form_date" datetimepicker key="delegateEnd" change="datePickChange" owner="updateExt">
                                <input class="form-control bgWhite" size="16" type="text" placeholder="到期日期" name="delegateEnd" ng-model="ctrl.updateExt.delegateEnd" ng-change="ctrl.setBuildDate()" id="delegateEnd"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="control-label">税款</label>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <select select-picker class="selectpicker show-menu-arrow form-control " name="houseTaxesWilling" id="houseTaxesWilling"
                                    ng-model="ctrl.updateExt.taxesWilling" ng-change="ctrl.selectPickerChange('#houseTaxesWilling', 'taxesWilling', 'updateExt')">
                                <option value="">--请选择--</option>
                            <#list taxesWilling ?if_exists as type>
                                <option value="${type.name()}">${type.getLabel()}</option>
                            </#list>
                            </select>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <select select-picker class="selectpicker show-menu-arrow form-control minusHeight" name="houseCommission" id="houseCommissionWilling"
                                    ng-model="ctrl.updateExt.commissionWilling" ng-change="ctrl.selectPickerChange('#houseCommissionWilling', 'commissionWilling', 'updateExt')">
                                <option value="">付佣</option>
                            <#list commissionWilling ?if_exists as type>
                                <option value="${type.name()}">${type.getLabel()}</option>
                            </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="control-label">原购价</label>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <input type="text" name="housePurchase" class="form-control" placeholder="原购价" ng-model="ctrl.updateExt.purchasePrice"/>
                        </div>
                        <div class="pull-left m-t-7 p-l-0" id="yuangou">
                            元
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="control-label">看房</label>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <select select-picker class="selectpicker show-menu-arrow form-control" name="houseShowing" id="houseShowing"
                                    ng-model="ctrl.updateExt.showing" ng-change="ctrl.selectPickerChange('#houseShowing', 'showing', 'updateExt')">
                                <option value="">--请选择--</option>
                            <#list showing ?if_exists as type>
                                <option value="${type.name()}">${type.getLabel()}</option>
                            </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="control-label">现状</label>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <select select-picker class="selectpicker show-menu-arrow form-control"  name="houseStatus" id="houseStatus"
                                    ng-model="ctrl.updateExt.status"  ng-change="ctrl.selectPickerChange('#houseStatus', 'status', 'updateExt')">
                                <option value="">--请选择--</option>
                            <#list houseStatus ?if_exists as status>
                                <option value="${status.name()}">${status.getLabel()}</option>
                            </#list>
                            </select>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-2">
                            <select select-picker class="selectpicker show-menu-arrow form-control"  name="houseSource" id="houseSource"
                                    ng-model="ctrl.updateExt.source" ng-change="ctrl.selectPickerChange('#houseSource', 'source', 'updateExt')">
                                <option value="">来源</option>
                            <#list houseSource ?if_exists as source>
                                <option value="${source.name()}">${source.getLabel()}</option>
                            </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="control-label">备注</label>
                        <div class="col-lg-9 col-md-9 col-sm-9">
                            <textarea name="note" cols="30" rows="3" class="form-control" ng-model="ctrl.updateExt.note"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" ng-click="ctrl.extInfoUpdate()">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
    <div class="modal fade" id="contactModel" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">业主信息</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" name="contactDialogForm">
                        <div class="form-group clearfix">
                            <label class="control-label">业主</label>
                            <div class="col-lg-3 col-md-3 col-sm-3">
                                <input type="text" name="houseOwname" reg="^\S+$" class="form-control"
                                       placeholder="业主姓名" required ng-model="ctrl.updateContact.name"/>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label">手机1</label>
                            <div class="col-lg-5 col-md-5 col-sm-5">
                                <input type="text" name="houseOwphone1" required st-mobile-phone class="form-control"
                                       placeholder="业主手机1" ng-model="ctrl.updateContact.mobile"/>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label">手机2</label>
                            <div class="col-lg-5 col-md-5 col-sm-5">
                                <input type="text" name="houseOwphone2" st-mobile-phone class="form-control"
                                       placeholder="业主手机2" ng-model="ctrl.updateContact.aMobile"/>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label">手机3</label>
                            <div class="col-lg-5 col-md-5 col-sm-5">
                                <input type="text" name="houseOwphone3" st-mobile-phone class="form-control"
                                       placeholder="业主手机3" ng-model="ctrl.updateContact.bMobile"/>
                        </div>
                    </form>
                </div>
            </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" ng-click="ctrl.ownerInfoUpdate()">确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
    <!-- 跟进-->
    <div class="modal fade" id="followModel" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">新增跟进</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" name="ownerDialogForm">
                    <div class="form-group clearfix">
                        <label class="control-label">跟进方式</label>
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <select select-picker class="selectpicker show-menu-arrow form-control " name="houseNewFollow" id="houseNewFollow"
                                    ng-model="ctrl.newFollow.followType" ng-change="ctrl.selectPickerChange('#houseNewFollow', 'followType', 'newFollow')">
                                <option value="">--请选择--</option>
                            <#list followType ?if_exists as type>
                                <option value="${type.name()}">${type.getLabel()}</option>
                            </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="control-label">跟进内容</label>
                        <div class="col-lg-9 col-md-9 col-sm-9">
                            <textarea name="note" cols="30" rows="3" class="form-control" ng-model="ctrl.newFollow.content"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" ng-click="ctrl.followCreate()">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
    <!--勘察 -->
    <div class="modal fade" id="surveyModel" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">新增勘察</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" name="ownerDialogForm">
                    <div class="form-group clearfix">
                        <label class="control-label">优势</label>
                        <div class="col-lg-9 col-md-9 col-sm-9">
                            <textarea name="note" cols="30" rows="3" class="form-control" ng-model="ctrl.newSurvey.advantage"></textarea>
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="control-label">劣势</label>
                        <div class="col-lg-9 col-md-9 col-sm-9">
                            <textarea name="note" cols="30" rows="3" class="form-control" ng-model="ctrl.newSurvey.disAdvantage"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" ng-click="ctrl.surveyCreate()">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
    <!-- 图片 -->
    <div class="modal fade" id="mapModel" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" ng-bind="ctrl.page.uploadTitle"></h4>
            </div>
            <div class="modal-body upload-modal-body">
                <span class="btn btn-primary fileinput-button">
                    <i class="glyphicon glyphicon-plus"></i>
                    <span>上传图片</span>
                    <input id="file_upload" type="file" name="files[]" multiple="" accept="image/jpg, image/jpeg, image/x-png">
                </span>
                <div id="uploadedList">
                    <ul class="photoList m-t-10">
                    <input value="2" id="attachmentsize" type="hidden">
                    <input id="housePhotoUrl" type="hidden">
                    <li ng-repeat="img in ctrl.showMap.list">
                        <img ng-src="{{img.fileURI}}" height="100%" width="100%">
                        <div class="btn-box">
                            <a class="pull-left" title="删除" ng-click="ctrl.deleteImage(img.id)"><i class="fa fa-trash" ng-click=""></i></a>
                            <a class="pull-right" title="设为封面图" ng-click="ctrl.setFirstImage(img.id)"><i class="fa fa-file-photo-o"></i></a>
                        </div>
                    </li>
                </ul>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
            </div>
            <div class="upload-loading" ng-show="ctrl.page.uploading">
                <div class="loader-inner pacman">
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
<!-- /.content-wrapper -->

<#include "/common/footer.ftl" />
<script src="${contextPath!}/js/app/fang/detail.js?vn=${bts!}"></script>
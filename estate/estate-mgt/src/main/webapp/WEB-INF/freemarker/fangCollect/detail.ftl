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
                                    <div class="media-body">
                                        <div class="col-lg-12">
                                            <div class="clearfix">
                                                <h6 class="media-heading pull-left" style="font-size:14px;">
                                                    <a id="address" ng-bind="ctrl.summary.header" ng-href="{{ctrl.summary.url}}" target="_blank"></a>
                                                </h6>
                                                <label class="badge m-l-10" ng-class="{'badge-info':ctrl.summary.bizType.name == 'SELL','badge-warning':ctrl.summary.bizType.name == 'RENT'}" ng-bind="ctrl.summary.bizType.label"></label>
                                                <a ng-href="{{ctrl.summary.url}}" target="_blank" class="badge badge-success m-l-15" ng-bind="ctrl.summary.fangOrigin.label"></a>
                                                <span class="pull-right" style="min-width:90px">房源编号:<span ng-bind="ctrl.summary.id"></span></span>
                                            </div>
                                            <div class="clearfix text-muted m-t-10">
                                                <span ng-show="ctrl.summary.floorCounts"><span id="floor" ng-bind="ctrl.summary.floor||'未知'"> </span>/<span ng-bind="ctrl.summary.floorCounts"></span>F</span>
                                                <span class="m-l-10" ng-bind="ctrl.summary.orientation.label"></span>
                                                <span class="m-l-10" ng-bind="ctrl.summary.layoutString"></span>
                                            </div>
                                            <div class="clearfix m-t-10 text-muted">
                                            <span>
                                                <strong ng-bind="ctrl.summary.estateArea"></strong>m<sup>2</sup>&nbsp;<span ng-show="ctrl.summary.realArea"><strong ng-bind="ctrl.summary.realArea"></strong>m<sup>2</sup></span>
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
                        <div class="tab-content">
                            <div class="tab-pane active" id="descr-info">
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
                                                    <td width="10%" class="text-muted text-center">房屋地址</td>
                                                    <td colspan="9" ng-bind="ctrl.summary.address"></td>
                                                </tr>
                                                <tr>
                                                    <td width="10%" class="text-muted text-center">小区名称</td>
                                                    <td colspan="9" ng-bind="ctrl.summary.xiaoQuName"></td>
                                                </tr>
                                                <tr>
                                                    <td class="text-muted text-center">房屋类型</td>
                                                    <td colspan="9"><span ng-bind="ctrl.summary.houseType.label"></span>-<span ng-bind="ctrl.summary.houseSubType.label"></span></td>
                                                </tr>
                                                <tr>
                                                    <td class="text-muted text-center">装修描述</td>
                                                    <td colspan="9" ng-bind="ctrl.summary.decorate"></td>
                                                </tr>
                                                <tr>
                                                    <td class="text-muted text-center">是否电梯</td>
                                                    <td colspan="9" ng-bind="ctrl.summary.hasElevator.label"></td>
                                                </tr>
                                                <tr>
                                                    <td class="text-muted text-center">可否落户</td>
                                                    <td colspan="9" ng-bind="ctrl.summary.resident.label"></td>
                                                </tr>
                                                <tr>
                                                    <td class="text-muted text-center">业主信息</td>
                                                    <td colspan="9"><span ng-bind="ctrl.summary.contactName||'姓名:'"></span>-<span ng-bind="ctrl.summary.contactMobile"></span></td>
                                                </tr>
                                                <tr>
                                                    <td class="text-muted text-center">业主描述</td>
                                                    <td colspan="9" ng-bind="ctrl.summary.description"></td>
                                                </tr>
                                                <tr>
                                                    <td class="text-muted text-center">扩展信息</td>
                                                    <td colspan="9">
                                                        <div ng-repeat="(key, value) in ctrl.extInfo">
                                                            {{value}}
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="text-muted text-center">图片信息</td>
                                                    <td colspan="9">
                                                        <div class="text-center" ng-repeat="imagePath in ctrl.imageList">
                                                            <img ng-src="{{imagePath}}">
                                                        </div>
                                                    </td>
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
<script src="${contextPath!}/js/app/fangCollect/detail.js?vn=${bts!}"></script>
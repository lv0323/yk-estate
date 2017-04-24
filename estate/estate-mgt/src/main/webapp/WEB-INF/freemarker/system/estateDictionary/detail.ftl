<#include "/common/header.ftl" />
<link rel="stylesheet" href="${contextPath}/css/app/system/estateDictionary/detail.css">
<#include "/common/sidebar.ftl" />
<div id="dictionaryDetail" class="content-wrapper" ng-controller="DetailCtrl as ctrl">
    <section class="content-header">
        <ol class="breadcrumb">
            <li>
                <a ng-href="${contextPath}/system/estateDictionary?target=.sys">
                    <i class="fa fa-home fa-lg" aria-hidden="true"></i>
                    楼盘字典
                </a>
            </li>
            <li class="active">
                楼盘详情
            </li>
        </ol>
    </section>
    <!--Main content -->
    <section class="content">
        <!-- Small boxes (Stat box) -->
        <!-- Main row -->
        <div class="row" id="main-container">
            <div class="col-lg-10 col-lg-offset-2 col-md-10 col-md-offset-2 col-sm-12">
                <div id="main" style="position: relative;">
                    <div class="section section_1" style="position: relative; margin: 0px;">
                        <div class="box box-solid">
                            <div class="box-header" style="border-bottom: 1px solid #ddd;">
                                <h3 class="box-title">基本信息</h3>
                                <#--<div class="box-tools">-->
                                    <#--<a id="uppic" class="btn btn-white"><i class="fa fa-upload"></i> 上传图片</a>-->
                                <#--</div>-->
                            </div>
                            <div class="box-body">
                                <div class="row">
                                    <div class="col-lg-5 col-sm-12 p-l-0">
                                        <div class="thumbnail" ng-cloak ng-show="ctrl.data.config.showImage" id="xiaoquImage">
                                            <img ng-src="{{ctrl.virtualMap.list.length > 0 ? ctrl.virtualMap.list[0].fileURI : ctrl.data.detail.imageURI}}"/>
                                            <div class="caption clearfix">
                                                <a class="btn btn-warning btn-xs pull-left" ng-click="ctrl.addMap('SHI_JING','新增实景图', 'virtualMap');">新增实景图</a>
                                                <a class="btn btn-success btn-xs pull-left m-l-30" ng-click="ctrl.showAllMap('SHI_JING','新增实景图', 'virtualMap');">查看全部</a>
                                                <span class="pull-right">{{ctrl.virtualMap.count}}张</span>
                                            </div>
                                        </div>
                                        <#--<div class="jssorSlideWrapper" id="xiaoquSlider">-->
                                            <#--<div data-u="slides" class="jssorSlideContainer">-->
                                                <#--<div>-->
                                                    <#--<img data-u="image" src="../../img/house/fcz.png" />-->
                                                    <#--<img data-u="thumb" src="../../img/house/fcz.png" />-->
                                                <#--</div>-->
                                                <#--<div>-->
                                                    <#--<img data-u="image" src="../../img/house/rent.png" />-->
                                                    <#--<img data-u="thumb" src="../../img/house/rent.png" />-->
                                                <#--</div>-->
                                            <#--</div>-->
                                            <#--<div data-u="thumbnavigator" class="thumbnavigator" data-autocenter="1">-->
                                                <#--<div  class="thumbnavigator-content"></div>-->
                                                <#--<div data-u="slides" style="cursor: default;">-->
                                                    <#--<div data-u="prototype" class="p">-->
                                                        <#--<div class="w">-->
                                                            <#--<div data-u="thumbnailtemplate" class="t"></div>-->
                                                        <#--</div>-->
                                                        <#--<div class="c"></div>-->
                                                    <#--</div>-->
                                                <#--</div>-->
                                            <#--</div>-->
                                            <#--<span data-u="arrowleft" class="jssora02l" data-autocenter="2"></span>-->
                                            <#--<span data-u="arrowright" class="jssora02r"  data-autocenter="2"></span>-->
                                        <#--</div>-->

                                    </div>
                                    <div class="col-lg-6 col-sm-12 p-l-0" id="xiaoquInfoList">
                                        <dl class="dl-horizontal">
                                            <dt class="direct-chat-timestamp">楼盘名称：</dt>
                                            <dd ng-bind="ctrl.data.detail.name"></dd>
                                        </dl>
                                        <dl class="dl-horizontal">
                                            <dt class="direct-chat-timestamp">楼盘别名：</dt>
                                            <dd ng-bind="ctrl.data.detail.alias"</dd>
                                        </dl>
                                        <dl class="dl-horizontal">
                                            <dt class="direct-chat-timestamp">建筑结构：</dt>
                                            <dd ng-bind="ctrl.data.detail.structureStr"</dd>
                                        </dl>
                                        <dl class="dl-horizontal">
                                            <dt class="direct-chat-timestamp">物业费：</dt>
                                            <dd ng-bind="ctrl.data.detail.propertyFee"</dd>
                                        </dl>
                                        <dl class="dl-horizontal">
                                            <dt class="direct-chat-timestamp">竣工年份：</dt>
                                            <dd ng-bind="ctrl.data.detail.buildedYear"</dd>
                                        </dl>
                                        <dl class="dl-horizontal">
                                            <dt class="direct-chat-timestamp">楼盘地址：</dt>
                                            <dd ng-bind="ctrl.data.detail.address"></dd>
                                        </dl>
                                        <dl class="dl-horizontal">
                                            <dt class="direct-chat-timestamp">栋数：</dt>
                                            <dd ng-bind="ctrl.data.detail.buildings"></dd>
                                        </dl>
                                        <dl class="dl-horizontal">
                                            <dt class="direct-chat-timestamp">户数：</dt>
                                            <dd ng-bind="ctrl.data.detail.houses"></dd>
                                        </dl>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="section section_3" style="position: relative; margin: 0px;">
                        <div class="box box-solid">
                            <div class="box-header" style="border-bottom: 1px solid #ddd;">
                                <h3 class="box-title pull-left">栋座字典</h3>
                                <div class="box-tools">
                                    <a href="javascript:void(0);" class="pull-right btn btn-white" ng-click="ctrl.buildingModify()"><i class="fa fa-plus"></i>新增栋座</a>
                                </div>
                            </div>
                            <div class="box-body">
                                <div class="table-responsive">
                                    <table class="table table-striped table-hover">
                                        <thead>
                                        <tr>
                                            <th><span>栋座</span></th>
                                            <th><span>总层</span></th>
                                            <th><span>单元数</span></th>
                                            <th><span>梯/户</span></th>
                                            <th><span>操作</span></th>
                                        </tr>
                                        </thead>
                                        <tbody ng-cloak>
                                        <tr ng-repeat="building in ctrl.data.buildings">
                                            <td ng-bind="building.name"></td>
                                            <td ng-bind="building.floors"></td>
                                            <td ng-bind="(building.units && building.units.length || 0)"></td>
                                            <td><span ng-bind="building.stairs && building.stairs+'梯'"></span><span ng-bind="building.houses && building.houses + '户'"></span></td>
                                            <td>
                                                <a href="javascript:void(0);" ng-click="ctrl.buildingItem(building)">详情</a>
                                                <a href="javascript:void(0);" ng-click="ctrl.buildingModify(building)">编辑</a>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="section section_5" style="position: relative; margin: 0px;">
                        <div class="box box-solid">
                            <div class="box-header" style="border-bottom: 1px solid #ddd;">
                                <h3 class="box-title">周边配套</h3>
                            </div>
                            <div class="box-body">
                                <div class="mapps">
                                    <a class="btn btn-white btn-sm" ng-class="{'btn-primary' :ctrl.mapConfig.mapSearchStr === '公交车'}" ng-click="ctrl.searchMap('公交车')"><i class="fa fa-bus"></i>公交</a>
                                    <a class="btn btn-white btn-sm" ng-class="{'btn-primary' :ctrl.mapConfig.mapSearchStr === '地铁站'}" ng-click="ctrl.searchMap('地铁站')"><i class="fa fa-subway"></i>地铁</a>
                                    <a class="btn btn-white btn-sm" ng-class="{'btn-primary' :ctrl.mapConfig.mapSearchStr === '学校'}" ng-click="ctrl.searchMap('学校')"><i class="fa fa-graduation-cap"></i>教育</a>
                                    <a class="btn btn-white btn-sm" ng-class="{'btn-primary' :ctrl.mapConfig.mapSearchStr === '医院'}" ng-click="ctrl.searchMap('医院')"><i class="fa fa-heartbeat"></i>医院</a>
                                    <a class="btn btn-white btn-sm" ng-class="{'btn-primary' :ctrl.mapConfig.mapSearchStr === '银行'}" ng-click="ctrl.searchMap('银行')"><i class="fa fa-bank"></i>银行</a>
                                    <a class="btn btn-white btn-sm" ng-class="{'btn-primary' :ctrl.mapConfig.mapSearchStr === '商城'}" ng-click="ctrl.searchMap('商城')"><i class="fa fa-shopping-cart"></i>购物</a>
                                    <a class="btn btn-white btn-sm" ng-class="{'btn-primary' :ctrl.mapConfig.mapSearchStr === '美食'}" ng-click="ctrl.searchMap('美食')"><i class="fa fa-coffee"></i>餐饮</a>
                                </div>
                                <div class="m-t-15">
                                    <div class="col-lg-3 col-sm-12" id="salesstaffMapPage">
                                        <div style="overflow-x: hidden; overflow-y: auto; height: 688px;">
                                            <div class="adr_info clearfix">
                                                <div id="r-result" class="pull-left" style="width:100%;"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="l-map" class="col-lg-9 col-sm-12"  baidu-map="${mapKey!}" map-ready="ctrl.mapReady(map)" style="height: 688px">

                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="section section_7" style="position: relative; margin: 0px;">
                        <div class="box box-solid">
                            <div class="box-header" style="border-bottom: 1px solid #ddd;">
                                <div class="box-title">开发商/物业</div>
                                <div class="box-tools">
                                </div>
                            </div>
                            <div class="box-body">
                                <div class="col-sm-6">
                                    <dl class="dl-horizontal">
                                        <dt>开发商：</dt>
                                        <dd ng-bind="ctrl.data.detail.developers"></dd>
                                    </dl>
                                    <dl class="dl-horizontal">
                                        <dt>开发年代：</dt>
                                        <dd ng-bind="ctrl.data.detail.developYear"></dd>
                                    </dl>
                                    <dl class="dl-horizontal">
                                        <dt>车位数量：</dt>
                                        <dd ng-bind="ctrl.data.detail.parkingSpace"></dd>
                                    </dl>
                                    <dl class="dl-horizontal">
                                        <dt>车位使用率：</dt>
                                        <dd ng-bind="ctrl.data.detail.parkingRate"></dd>
                                    </dl>
                                    <dl class="dl-horizontal">
                                        <dt>绿化率：</dt>
                                        <dd ng-bind="ctrl.data.detail.greenRate"></dd>
                                    </dl>
                                </div>
                                <!---col-sm-6 end-->
                                <div class="col-sm-6">
                                    <dl class="dl-horizontal">
                                        <dt>物业公司：</dt>
                                        <dd ng-bind="ctrl.data.detail.propertyCompany"></dd>
                                    </dl>
                                    <dl class="dl-horizontal">
                                        <dt>物业公司电话：</dt>
                                        <dd ng-bind="ctrl.data.detail.propertyCompanyPhone"></dd>
                                    </dl>
                                    <dl class="dl-horizontal">
                                        <dt>车位租金：</dt>
                                        <dd ng-bind="ctrl.data.detail.parkingFee"></dd>
                                    </dl>
                                    <dl class="dl-horizontal">
                                        <dt>容积率：</dt>
                                        <dd ng-bind="ctrl.data.detail.containerRate"></dd>
                                    </dl>
                                </div>
                                <!---col-sm-6 end-->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.row (main row) -->
    </section>
    <!-- 栋座详情-->
    <div class="modal fade" id="showBuilding" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">栋座详情</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group clearfix">
                        <label class="control-label col-xs-3">楼盘字典:</label>
                        <div class="col-lg-9 col-md-9 col-sm-9" ng-bind="ctrl.data.detail.name"></div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="control-label col-xs-3">楼盘地址:</label>
                        <div class="col-lg-9 col-md-9 col-sm-9" ng-bind="ctrl.data.detail.address"></div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="control-label col-xs-3">栋座名称:</label>
                        <div class="col-lg-9 col-md-9 col-sm-9" ng-bind="ctrl.data.showBuilding.name"></div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="control-label col-xs-3">栋座楼层:</label>
                        <div class="col-lg-9 col-md-9 col-sm-9" ng-bind="ctrl.data.showBuilding.floors"></div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="control-label col-xs-3">单元名称:</label>
                        <div class="col-lg-9 col-md-9 col-sm-9" ng-bind="ctrl.data.showBuilding.unitsName"></div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="control-label col-xs-3">梯/户数:</label>
                        <div class="col-lg-9 col-md-9 col-sm-9">
                            <span ng-bind="ctrl.data.showBuilding.stairs && ctrl.data.showBuilding.stairs+'梯'"></span><span ng-bind="ctrl.data.showBuilding.houses && ctrl.data.showBuilding.houses + '户'"></span>
                        </div>
                    </div>
                    <div class="form-group clearfix">
                        <label class="control-label col-xs-3">描述说明:</label>
                        <div class="col-lg-9 col-md-9 col-sm-9" ng-bind="ctrl.data.showBuilding.description"></div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
                </div>
            </div>
        </div>
    </div>
    <!-- 编辑 -->
    <div class="modal fade" id="buildingModify" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">栋座详情</h4>
                </div>
                <div class="modal-body">
                    <form name="buildingForm">
                        <div class="form-group clearfix">
                            <label class="control-label col-xs-3">楼盘字典:</label>
                            <div class="col-lg-9 col-md-9 col-sm-9" ng-bind="ctrl.data.detail.name" required></div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-xs-3">楼盘地址:</label>
                            <div class="col-lg-9 col-md-9 col-sm-9" ng-bind="ctrl.data.detail.address" required></div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-xs-3">栋座名称:</label>
                            <div class="col-lg-5 col-md-5 col-sm-5">
                                <input type="text" name="buildingName" reg="^\S+$" class="form-control"
                                       required ng-model="ctrl.data.modifyBuilding.name" required/>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-xs-3">栋座楼层:</label>
                            <div class="col-lg-5 col-md-5 col-sm-5">
                                    <input type="text" name="buildingFloors" reg="^\S+$" class="form-control"
                                       required ng-model="ctrl.data.modifyBuilding.floors" required/>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-xs-3">单元名称:</label>
                            <div class="col-lg-5 col-md-5 col-sm-5 clearfix">
                                <div class="clearfix">
                                    <div class="col-lg-10 col-md-10 col-sm-10" style="padding:0">
                                        <input type="text" name="firstUnit" reg="^\S+$" class="form-control"
                                               required ng-model="ctrl.data.modifyBuilding.firstUnit"/>
                                    </div>
                                    <div class="col-lg-2 col-md-2 col-sm-2 clearfix m-t-7">
                                        <a href="#">
                                            <i class="fa fa-plus-circle" aria-hidden="true" ng-click="ctrl.unitAdd()"></i>
                                        </a>
                                    </div>
                                </div>
                                <div ng-repeat="unit in ctrl.data.modifyBuilding.unitList track by $index" ng-class="{'m-t-7':true }" class="clearfix">
                                    <div class="col-lg-10 col-md-10 col-sm-10" style="padding:0">
                                        <input type="text" reg="^\S+$" class="form-control" ng-model="ctrl.data.modifyBuilding.unitList[$index]"/>
                                    </div>
                                    <div class="col-lg-2 col-md-2 col-sm-2 m-t-7">
                                        <a href="#">
                                            <i class="fa fa-minus-circle" aria-hidden="true" ng-click="ctrl.unitRemove(item, $index)"></i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-xs-3">梯/户数:</label>
                            <div class="col-lg-3 col-md-3 col-sm-3">
                                <input type="text" name="buildingStairs" reg="^\S+$" class="form-control"
                                       required ng-model="ctrl.data.modifyBuilding.stairs" required/>
                            </div>
                            <div class="col-lg-3 col-md-3 col-sm-3">
                                <input type="text" name="buildingHouses" reg="^\S+$" class="form-control"
                                       required ng-model="ctrl.data.modifyBuilding.houses" required/>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-xs-3">描述说明:</label>
                            <div class="col-lg-9 col-md-9 col-sm-9">
                                <textarea name="buildingDescription" cols="30" rows="3" name="buildingDescription" class="form-control" ng-model="ctrl.data.modifyBuilding.description" required></textarea>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" ng-show="ctrl.data.modifyBuilding.id" ng-click="ctrl.buildingDelete()" style="margin-right: 30px">删除</button>
                    <button type="button" class="btn btn-primary" ng-click="ctrl.buildingUpdate()">确定</button>
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
<#include "/common/footer.ftl" />
<script src="${contextPath!}/js/app/system/estateDictionary/detail.js"></script>


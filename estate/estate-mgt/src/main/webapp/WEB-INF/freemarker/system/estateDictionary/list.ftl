<#include "/common/header.ftl" />
<link rel="stylesheet" href="${contextPath}/css/app/system/estateDictionary/list.css">
<#include "/common/sidebar.ftl" />
<div id="houseDictionary" class="content-wrapper" ng-controller="DictCtrl as ctrl">
    <section class="content-header">
        <ol class="breadcrumb">
            <li>
                <a href="#">
                    <i class="fa fa-home fa-lg" aria-hidden="true"></i>
                    系统设置
                </a>
            </li>
            <li class="active">
                楼盘字典
            </li>
        </ol>
    </section>
    <!--Main content -->
    <section class="content">
        <div class="animated fadeInRight">
            <div class="row">
                <div class="col-lg-12">
                    <section class="connectedSortable">
                        <!-- left side -->
                        <div class="col-lg-3 col-md-3 col-sm-3">
                            <div class="box box-solid">
                                <div class="box-header with-border">
                                    <h3 class="box-title">所有部门</h3>
                                </div>
                                <div class="zTreeDemoBackground left">
                                    <ul id="dictionaryTree" class="ztree"></ul>
                                </div>
                            </div>
                        </div>
                        <!-- right side -->
                        <div class="col-lg-9 col-md-9 col-sm-12">
                            <div class="box box-solid form-inline" id="searchResult">
                                <div class="box-header with-border">
                                    <h3 class="box-title">楼盘字典</h3>
                                    <!-- 客户可以自己新增楼盘字典，增加入口 -->
                                    <div class="box-tools">
                                        <#--<a href="javascript:void(0);" class="pull-right btn btn-white" ng-click="ctrl.showCreateNewXiaoquDialog()"><i class="fa fa-plus"></i>新增楼盘</a>-->
                                            <a href="javascript:void(0);" class="pull-right btn btn-white" ng-href="/mgt/system/estateDictionary/add.ftl?target=.sys"><i class="fa fa-plus"></i>新增楼盘</a>
                                    </div>
                                </div>
                                <div class="box-body" ng-cloak>
                                    <div id="searchList">
                                        <div class="form-group clearfix">
                                            <#--<div class="col-lg-3 col-md-3 col-sm-3">
                                                <select id="houseType" select-picker class="selectpicker show-menu-arrow form-control sel-news"  ng-model="ctrl.data.houseType" ng-change="ctrl.houseTypeChange(e)">
                                                    <option value="">房源类型</option>
                                                <#list houseTypes?if_exists as type>
                                                    <option value="${type.name()}">${type.getLabel()}</option>
                                                </#list>
                                                </select>
                                            </div>-->
                                            <div class="col-lg-5 col-md-5 col-sm-5" style="padding-left: 0">
                                                <select id="houseEstate" data-placeholder="楼盘字典" class="chosen-select" name="houseEstate">
                                                    <option value="">楼盘字典</option>
                                                    <option ng-repeat="estate in ctrl.estateList" repeat-done="ctrl.chosenEstate('#houseEstate', 'xiaoQuId')" ng-value="estate.value">{{estate.name}}</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="estatedictionaryPage">
                                        <div class="media clearfix" ng-repeat="xiaoqu in ctrl.xiaoquList">
                                            <a class="pull-left" href="#" style="padding-right: 20px">
                                                <div class="media-object bg-icon xiaoqu-img-container">
                                                    <img ng-src="{{xiaoqu.imageURI}}" class="xiaoqu-img"/>
                                                </div>
                                            </a>
                                            <div class="media-body">
                                                <div class="m-t-7 clearfix">
                                                    <h4 class="media-heading pull-left">
                                                        <a class="text-muted" ng-bind="xiaoqu.name" ng-href="{{'/mgt/system/estateDictionary/detail?id='+ xiaoqu.id}}"></a>
                                                    </h4>
                                                </div>
                                                <div class="m-t-7 clearfix">
                                                    <span ng-bind="xiaoqu.district"></span>-<span ng-bind="xiaoqu.subDistrict"></span>&nbsp;&nbsp;<span ng-bind="xiaoqu.address"></span>
                                                </div>
                                                <div class="m-t-7 clearfix">建筑年代:<span ng-bind="xiaoqu.buildedYear"><span></div>
                                            </div>
                                        </div>
                                        <div class="pagination-container">
                                            <ul id="xiaoquList_paging" class="pagination"></ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </section>

    <!-- 新增小区 -->
    <div class="modal fade" id="createXiaoqu" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <form name="createXiaoquForm">
                        <div class="form-group clearfix">
                            <label class="control-label col-xs-3">楼盘名字:</label>
                            <div class="col-lg-5 col-md-5 col-sm-5">
                                <input type="text" name="communityName" reg="^\S+$" class="form-control"
                                       required ng-model="ctrl.data.create.communityName" required/>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-xs-3">楼盘别名:</label>
                            <div class="col-lg-5 col-md-5 col-sm-5">
                                <input type="text" name="communityAlias" reg="^\S+$" class="form-control"
                                       ng-model="ctrl.data.create.communityAlias"/>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-xs-3">城市:</label>
                            <div class="col-lg-5 col-md-5 col-sm-5">
                                <input type="text" name="communityAlias" reg="^\S+$" class="form-control"
                                       value="北京" disabled required/>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-xs-3">区域:</label>
                            <div class="col-lg-5 col-md-5 col-sm-5">
                                <select id='districtSelect' ng-model="ctrl.data.create.communityDistrict" ng-change="ctrl.districtChanged()">
                                    <option ng-repeat="district in ctrl.districtWithSubs"  ng-value="district.id">{{district.name}}</option>
                                </select>

                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label col-xs-3">子区域:</label>
                            <div class="col-lg-5 col-md-5 col-sm-5">
                                <select id='subDistrictSelect' ng-model="ctrl.data.create.communitySubDistrict">
                                    <option ng-repeat="subDistrict in ctrl.subDistricts" ng-value="subDistrict.id">{{subDistrict.name}}</option>
                                </select>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" ng-click="ctrl.createXiaoqu()">确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>

</div>
<#include "/common/footer.ftl" />
<script src="${contextPath!}/js/app/system/estateDictionary/list.js"></script>


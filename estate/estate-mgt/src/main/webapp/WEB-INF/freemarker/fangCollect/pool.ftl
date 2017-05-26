<link href="${contextPath}/css/app/fang/index.css" rel="stylesheet">

<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />
<style>
    .media{
        padding: 10px 10px 5px;
    }
    .media p{
        margin-bottom: 0;
    }
</style>
<!-- -->
<div class="content-wrapper" id="housePoolWrapper" ng-controller="HousePoolCtrl">
    <section class="content-header">
        <ol class="breadcrumb">
            <li>
                <a href="#">
                    <i class="fa fa-home fa-lg" aria-hidden="true"></i>
                    房源采集
                </a>
            </li>
            <li class="active">
                房源池
            </li>
        </ol>
    </section>
    <!--Main content -->
    <section class="content">
        <div class="animated fadeInRight">
            <div class="row">
                <div class="col-lg-12">
                    <div class="box box-solid form-inline" >
                        <div class="box-header with-border">
                            <h3 class="box-title pull-left">采集列表</h3>
                            <div class="box-tools">
                                <a class="btn-collapse icon-down btn" ng-click="triggerCollapse()">
                                    <strong><i class="fa" ng-class="{true:'fa-chevron-up',false:'fa-chevron-down'}[page.collapse]" aria-hidden="true"></i>更多筛选</strong>
                                </a>
                            </div>
                        </div>
                        <div class="box-body clearfix no-padding default-height">
                            <form id="formlist" class="form-inline">
                                <div id="searchList" ng-cloak class="clearfix" style="padding: 0;">
                                    <div class="clearfix"></div>
                                    <div class="collapse-box" ng-show="page.collapse">
                                        <div class="form-group sortlist">
                                            <label class="control-label" style="vertical-align:top;padding-top:5px;">区域</label>
                                            <div class="tj" id="distract">
                                                <a ng-href="javascript:" ng-class="{'actived': '' == filter.districtId}" ng-click="setDistrict('')">不限</a>
                                                <a ng-href="javascript:" ng-repeat="district in districtList" ng-class="{'actived': district.id == filter.districtId}" ng-click="setDistrict(district.id)">
                                                    {{district.name}}
                                                </a>
                                            </div>
                                        </div>
                                        <#--<div class="form-group sortlist" ng-show="'' != filter.districtId">
                                            <label class="control-label" style="vertical-align:top;padding-top:5px;">子区域</label>
                                            <div class="tj" id="distract">
                                                <a ng-href="javascript:" ng-class="{'actived': '' == filter.subDistrictId}" ng-click="setFilterType('subDistrictId', '')">不限</a>
                                                <a ng-href="javascript:" ng-repeat="subDistrict in subDistrictList" ng-class="{'actived': subDistrict.id == filter.subDistrictId}" ng-click="setFilterType('subDistrictId', subDistrict.id)">
                                                    {{subDistrict.name}}
                                                </a>
                                            </div>
                                        </div>-->
                                        <div class="form-group sortlist">
                                            <label class="control-label">用途</label>
                                            <div id="usage" class="tj">
                                                <a ng-href="javascript::" ng-class="{'actived': '' == filter.houseType}" ng-click="setFilterType('houseType' ,'')">不限</a>
                                            <#list houseTypes?if_exists as type>
                                                <a ng-href="javascript::" ng-class="{'actived': '${type.name()}' == filter.houseType}" ng-click="setFilterType('houseType' ,'${type.name()}')">
                                            ${type.getLabel()}</a>
                                            </#list>
                                            </div>
                                        </div>
                                        <div class="form-group sortlist">
                                            <label class="control-label">户型</label>
                                            <div id="sCounts" class="tj">
                                                <a ng-href="javascript::" ng-repeat="sCounts in sCountsList" value="{{sCounts.value}}" ng-class="{'actived': sCounts.value == filter.sCounts}" ng-click="setFilterType('sCounts' ,sCounts.value)">
                                                    {{sCounts.name}}
                                                </a>
                                            </div>
                                        </div>
                                        <div class="form-group sortlist" style="border-bottom: 1px dashed #ccc;">
                                            <label class="control-label">成交类型</label>
                                            <div class="tj">
                                                <a ng-href="javascript::" ng-class="{'actived': '' == filter.bizType}" ng-click="setFilterType('bizType' ,'')">不限</a>
                                            <#list bizType?if_exists as biz>
                                                <a ng-href="javascript::" ng-class="{'actived': '${biz.name()}' == filter.bizType}" ng-click="setFilterType('bizType' ,'${biz.name()}')">
                                                ${biz.getLabel()}</a>
                                            </#list>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group sortlist">
                                        <label class="control-label">排序</label>
                                        <div class="tj" id="fySortlist">
                                            <a ng-repeat="sort in fySortlist" ng-href="javascript:;" ng-click="setSort(sort.value)" ng-class="{'actived': sort.value == filter.order}">
                                                {{sort.name}}
                                                <i ng-if="sort.value == filter.order" class="fa" ng-class="{'fa-arrow-down':filter.orderType == 'DOWN','fa-arrow-up':filter.orderType == 'UP' }"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div id="getHouseCharPage" class="table-responsive clearfix" ng-cloak>
                                    <div class="media clearfix house-item" ng-repeat="house in houseList">
                                        <div class="media-body">
                                            <div class="col-lg-6 col-md-6 col-sm-6" style="padding-right: 0">
                                                <div class="clearfix">
                                                    <h5 class="media-heading pull-left text-ellipsis">
                                                        <a ng-href="{{'/mgt/fangCollection/detail?id='+house.id}}" target="_blank" class="text-muted" ng-bind="house.header"></a>
                                                    </h5>
                                                    <label class="badge m-l-10"
                                                           ng-class="{'badge-info':house.bizType.name == 'SELL','badge-warning':house.bizType.name == 'RENT'}" ng-bind="house.bizType.label"></label>
                                                    <a target="_blank" ng-href="{{house.url}}" class="badge m-l-10 badge-success">{{house.fangOrigin.label}}</a>
                                                </div>
                                                <div class="clearfix m-t-7 text-muted">
                                                    <span>{{house.layoutFormat}}</span>
                                                    <span class="m-l-10">{{house.floor}}/{{house.floorCounts}}F</span>
                                                    <span class="m-l-10">{{house.orientation.label}}</span>
                                                    <span class="m-l-10">{{house.decorate}}</span>
                                                    <span class="m-l-10">{{house.updateTime|date:'yyyy-MM-dd'}}</span>
                                                   <#-- <span class="m-l-10">{{house.address}}</span>-->
                                                </div>
                                            </div>
                                            <div class="col-lg-3 col-md-3 col-sm-3">
                                                <p><strong class="f18">{{house.estateArea}}</strong>m<sup>2</sup><span class="text-muted" ng-if="house.realArea">({{house.realArea}}m<sup>2</sup>)</span></p>
                                                <p><strong class="text-danger f18">{{house.publishPrice}}</strong>{{house.priceUnit.label}}
                                                    <span ng-if="house.unitPrice">-{{house.unitPrice}}<span ng-if="house.priceUnit.name !== 'WAN'">{{house.priceUnit.label}}</span>/m<sup>2</sup></span>

                                                </p>
                                            </div>
                                            <div class="col-lg-3 col-md-3 col-sm-3 text-right">
                                                <p><strong class="text-danger f18" ng-if="house.contactName">{{house.contactName}}</strong>{{house.contactName?'':'姓名:'}}</p>
                                                <p class="m-t-7">{{house.contactMobile}}</p>
                                            </div>
                                            <div class="clearfix col-lg-12 col-md-12 col-sm-12">
                                                <div class="pull-left">
                                                    <span class="tip tip-success tag" ng-repeat="tag in house.tags" ng-bind="tag.label"></span>
                                                </div>
                                                <#--<div class="pull-left btn-add m-l-20">
                                                    <span class="add-info-operation m-r-20">
                                                        <a ng-href="javascript:;" >
                                                            <i class="fa fa-pencil"></i>新增跟进
                                                        </a>
                                                    </span>
                                                    <span class="m-r-20">
                                                        <a class="m-l-10" ng-href="javascript:;" ng-show="house.process.name == page.status.DELEGATE|| house.process.name == page.status.UN_PUBLISH || house.process.name == page.status.PAUSE"
                                                           ng-click="changeStatus(page.status.PUBLISH, house.id)">
                                                            <i class="fa fa-pencil"></i>上架
                                                        </a>
                                                    </span>
                                                    <span ng-show="house.process.name == page.status.PUBLISH" class="sub-process-opration">
                                                        <a class="m-l-10" ng-href="javascript:;" ng-show="house.subProcess == null" ng-click="changeStatus(page.status.APPLY_PUBLISH, house.id)">
                                                            <i class="fa fa-pencil"></i>申请发布
                                                        </a>
                                                    </span>
                                                </div>-->
                                            </div>
                                        </div>
                                    </div>
                                    <div class="pagination-container">
                                        <ul id="houseList_paging" class="pagination"></ul>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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
                                <label class="control-label">姓名</label>
                                <div class="col-lg-3 col-md-3 col-sm-3">
                                    <input type="text" name="houseOwphone2"  class="form-control" ng-model="page.userInfo.name" readonly="readonly"/>
                                </div>
                            </div>
                            <div class="form-group clearfix">
                                <label class="control-label">跟进方式</label>
                                <div class="col-lg-3 col-md-3 col-sm-3">
                                    <select select-picker class="selectpicker show-menu-arrow form-control " name="houseNewFollow" id="houseNewFollow"
                                            ng-model="newFollow.followType" ng-change="selectPickerChange('#houseNewFollow', 'followType', 'newFollow')">
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
                                    <textarea name="note" cols="30" rows="3" class="form-control" ng-model="newFollow.content"></textarea>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" ng-click="followCreate()">确定</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
<!-- /.content-wrapper -->

<#include "/common/footer.ftl" />
<script src="${contextPath!}/js/app/fangCollect/pool.js?vn=${bts!}"></script>
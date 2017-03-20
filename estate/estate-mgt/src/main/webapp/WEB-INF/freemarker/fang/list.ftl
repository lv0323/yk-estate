<link href="${contextPath}/css/app/fang/index.css" rel="stylesheet">

<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />

<!-- -->
<div class="content-wrapper" id="houseListWrapper" ng-controller="HouseListCtrl">
    <section class="content-header">
        <ol class="breadcrumb">
            <li>
                <a href="#">
                    <i class="fa fa-home fa-lg" aria-hidden="true"></i>
                    房源管理
                </a>
            </li>
            <li class="active">
                房源列表
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
                            <h3 class="box-title pull-left">房源列表</h3>
                            <div class="box-tools">
                                <a class="btn" ng-href="/mgt/fangManage/create?target=.fang"><i class="fa fa-plus" aria-hidden="true"></i>新增房源</a>
                                <a class="btn-collapse icon-down btn" ng-click="triggerCollapse()">
                                    <strong><i class="fa" ng-class="{true:'fa-chevron-up',false:'fa-chevron-down'}[page.collapse]" aria-hidden="true"></i>更多筛选</strong>
                                </a>
                            </div>
                        </div>
                        <div class="box-body clearfix no-padding default-height">
                            <form id="formlist" class="form-inline">
                                <div id="searchList" ng-cloak class="clearfix">
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
                                        <div class="form-group sortlist" ng-show="'' != filter.districtId">
                                            <label class="control-label" style="vertical-align:top;padding-top:5px;">子区域</label>
                                            <div class="tj" id="distract">
                                                <a ng-href="javascript:" ng-class="{'actived': '' == filter.subDistrictId}" ng-click="setFilterType('subDistrictId', '')">不限</a>
                                                <a ng-href="javascript:" ng-repeat="subDistrict in subDistrictList" ng-class="{'actived': subDistrict.id == filter.subDistrictId}" ng-click="setFilterType('subDistrictId', subDistrict.id)">
                                                    {{subDistrict.name}}
                                                </a>
                                            </div>
                                        </div>
                                        <div class="form-group sortlist">
                                            <label class="control-label">用途</label>
                                            <div id="usage" class="tj">
                                                <a ng-href="javascript::" ng-class="{'actived': '' == filter.houseType}" ng-click="setFilterType('houseTypes' ,'')">不限</a>
                                            <#list houseTypes?if_exists as type>
                                                <a ng-href="javascript::" ng-class="{'actived': '${type.name()}' == filter.houseType}" ng-click="setFilterType('houseType' ,'${type.name()}')">
                                            ${type.getLabel()}</a>
                                            </#list>
                                            </div>
                                        </div>
                                        <div class="form-group sortlist">
                                            <label class="control-label">面积</label>
                                            <div id="squareMetre" class="tj">
                                                <a ng-href="javascript:void(0)" ng-class="{'actived':filter.areaType == ''}" ng-click="setFilterType('areaType', '')">不限</a>
                                                <a ng-href="javascript:void(0)" ng-class="{'actived':filter.areaType == 'custom'}" ng-click="setFilterType('areaType', 'custom')">自定义</a>
                                                <input class="form-control" id="minSqure" placeholder="最小面积" name="minSqure" ng-model="filter.minArea" ng-blur="checkArea('minArea')" style="width:100px;">~
                                                <input class="form-control" id="maxSqure" placeholder="最大面积" name="maxSqure" ng-model="filter.maxArea" ng-blur="checkArea('maxArea')" style="width:100px;">
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
                                        <div class="form-group sortlist">
                                            <label class="control-label">状态</label>
                                            <div id="houseTradeStatus" class="tj">
                                                <a ng-href="javascript::" ng-class="{'actived': '' == filter.process}" ng-click="setFilterType('process' ,'')">默认</a>
                                            <#list houseProcess?if_exists as process>
                                                <a ng-href="javascript::" ng-class="{'actived': '${process.name()}' == filter.process}" ng-click="setFilterType('process' ,'${process.name()}')">
                                                ${process.getLabel()}</a>
                                            </#list>

                                            </div>
                                        </div>
                                        <div class="form-group sortlist">
                                            <label class="control-label">特性</label>
                                            <div id="fyHouseCharacter" class="tj">
                                                <#list houseTag?if_exists as tag>
                                                    <div class="checkbox checkbox-nice">
                                                        <input name="houseCharacter${tag.name()}" id="character${tag.name()}" ng-model="filter.htsObj.${tag.name()}" type="checkbox" ng-change="list()">
                                                        <label for="character${tag.name()}">${tag.getLabel()}</label>
                                                    </div>
                                                </#list>
                                            </div>
                                        </div>
                                        <div class="form-group sortlist">
                                            <label class="control-label">筛选</label>
                                            <div class="col-lg-2 col-md-2 col-sm-3">
                                                <select name="houseTratype" id="fouseTratype" select-picker class="selectpicker show-menu-arrow form-control" ng-model="filter.bizType" ng-change="list()">
                                                    <option value="">--请选择--</option>
                                                    <option value="RENT">出租</option>
                                                    <option value="SELL">出售</option>
                                                </select>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3">
                                                <select id="douseDepid" class="chosen-select-dep">
                                                    <option value="">选择部门</option>
                                                    <option ng-repeat="dep in depList" ng-value="dep.id" repeat-done="initChosen('#douseDepid', 'departmentId')">{{dep.name}}</option>
                                                    </select>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-2 m-t-7">
                                                <div class="checkbox checkbox-nice" ng-repeat="depExp in depExpList">
                                                    <input name="depExp" id="depExp{{depExp.value}}" ng-model="filter[depExp.key]"  ng-click="setDepExp()" type="checkbox" ng-change="includeChildrenCheck()">
                                                    <label for="depExp{{depExp.value}}">{{depExp.name}}</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3">
                                                <select id="employeeId" class="chosen-select-emp">
                                                    <option value="">全部员工</option>
                                                    <option ng-repeat="employee in employeeList" ng-value="employee.id" repeat-done="initChosen('#employeeId', 'employeeId')">{{employee.name}}</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group sortlist form-inline">
                                            <label class="control-label"></label>
                                            <div class="col-lg-2 col-md-2 col-sm-3">
                                                <select select-picker class="select_1 selectpicker show-menu-arrow form-control" id="timeType" name="timeType" ng-model="filter.timeType" ng-change="timeCheck()">
                                                    <option value="">--请选择--</option>
                                                <#list timeType?if_exists as type>
                                                    <option value="${type.name()}">${type.getLabel()}</option>
                                                </#list>
                                                </select>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3">
                                                <div class="input-group date form_date" datetimepicker key="startDate" change="setDate">
                                                    <input class="form-control" size="16" placeholder="开始日期" type="text" ng-model="filter.startDate">
                                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                                </div>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3">
                                                <div class="input-group date form_date" datetimepicker key="endDate" change="setDate">
                                                    <input class="form-control" size="16" placeholder="结束时间" type="text" ng-model="filter.endDate">
                                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                                </div>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3">
                                                <select class="xuanxiang select_1 selectpicker show-menu-arrow form-control" select-picker ng-model='filter.xuanxiang' ng-change="setXuanxiang()" style="padding:2px 0px;width:60px;">
                                                    <option value="">--请选择--</option>
                                                    <option value="delegateType">委托</option>
                                                    <option value="decorate">装修</option>
                                                    <option value="propertyType">产权</option>
                                                    <option value="proveType">证件</option>
                                                    <option value="resident">落户</option>
                                                </select>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3 sm-mt10" ng-if="filter.xuanxiang === 'delegateType'">
                                                <select class="selected_1 selectpicker show-menu-arrow form-control" select-picker id="houseEntrustway" name="houseEntrustway" ng-model="filter.delegateType" ng-change="list()">
                                                    <option value="">--请选择--</option>
                                                <#list delegateType?if_exists as type>
                                                    <option value="${type.name()}">${type.getLabel()}</option>
                                                </#list>
                                                </select>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3 sm-mt10" ng-show="filter.xuanxiang === 'decorate'">
                                                <select class="selected_1 selectpicker show-menu-arrow form-control" select-picker style="padding:2px 0px;width:60px;" id="houseDecorate" name="houseDecorate" ng-model="filter.decorate" ng-change="list()">
                                                    <option value="">--请选择--</option>
                                                <#list decorate?if_exists as type>
                                                    <option value="${type.name()}">${type.getLabel()}</option>
                                                </#list>
                                                </select>
                                            </div>

                                            <div class="col-lg-2 col-md-2 col-sm-3 sm-mt10" ng-show="filter.xuanxiang === 'propertyType'">
                                                <select class="selected_1 selectpicker show-menu-arrow form-control" select-picker id="housePtype" style="padding:2px 0px;width:60px;" name="housePtype" ng-model="filter.propertyType" ng-change="list()">
                                                    <option value="">--请选择--</option>
                                                <#list propertyType?if_exists as type>
                                                    <option value="${type.name()}">${type.getLabel()}</option>
                                                </#list>

                                                </select>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3 sm-mt10" ng-show="filter.xuanxiang === 'proveType'">
                                                <select class="selected_1 selectpicker show-menu-arrow form-control" select-picker style="padding:2px 0px;width:60px;"  name="houseProvetype" ng-model="filter.certifType"  ng-change="list()">
                                                    <option value="">--请选择--</option>
                                                <#list certifType?if_exists as type>
                                                    <option value="${type.name()}">${type.getLabel()}</option>
                                                </#list>
                                                </select>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3 sm-mt10" ng-show="filter.xuanxiang === 'resident'">
                                                <select class="selected_1 selectpicker show-menu-arrow form-control" select-picker style="padding:2px 0px;width:60px;" name="houseSettle" ng-model="filter.resident"  ng-change="list()">
                                                    <option value="">--请选择--</option>
                                                    <option value="Y">是</option>
                                                    <option value="N">否</option>
                                                </select>
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
                                        <img class="flagImg" ng-src="{{house.bizType.name ==='RENT' ? '../img/house/rent.png':'../img/house/sale.png'}}" height="55" width="55">
                                        <a class="pull-left" href="javascript:void(0)">
                                            <img class="media-object" ng-src="{{house.imageURI}}" height="75px" width="100px">
                                        </a>
                                        <div class="media-body">
                                            <div class="col-lg-8 col-md-8 col-sm-9">
                                                <div class="clearfix">
                                                    <h5 class="media-heading pull-left text-ellipsis" style="width:300px;">
                                                        <a ng-href="{{'/mgt/fangManage/detail?id='+house.id}}" target="_blank" class="text-muted" ng-bind="house.head"></a>
                                                    </h5>
                                                    <label class="badge pull-left m-l-20" ng-class="{'badge-success':house.process.name == 'SUCCESS',
                                                           'badge-info':house.process.name == 'PUBLISH',
                                                           'badge-warning':house.process.name == 'UN_PUBLISH',
                                                           'badge-danger':house.process.name == 'DELEGATE'}">{{house.process.label}}</label>
                                                    <i class="fa fa-circle  m-l-20" style="font-size:16px;" ng-class="{true:'text-rent', false:'text-sell'}[house.bizType.name == 'RENT']"></i>
                                                    <span ng-class="{true:'text-rent', false:'text-sell'}[house.bizType.name == 'RENT']" ng-show="house.publishTime">[{{(page.now - house.publishTime)/(24*6060*1000)|number:0}}]</span>
                                                    <span class="text-muted">
                                                        {{house.infoOwner.departmentName}} ~ {{house.infoOwner.employeeName}}
                                                    </span>
                                                </div>
                                                <div class="clearfix m-t-10 text-muted">
                                                    <span>授权编号:{{house.licenceId}}</span>
                                                    <span class="m-l-10">
                                                        {{house.layoutFormat}}
                                                    </span>
                                                    <span class="m-l-10">{{house.floor}}/{{house.floorCounts}}F</span>
                                                    <span class="m-l-10">{{house.orientation.label}}</span>
                                                    <span class="m-l-10">{{house.decorate.label}}</span>
                                                    <span class="m-l-10">{{house.createTime|date:'yyyy-MM-dd'}}</span>
                                                </div>
                                                <div class="clearfix m-t-10">
                                                    <div class="pull-left">
                                                        <span class="tip tip-success tag" ng-repeat="tag in house.tags" ng-bind="tag.label"></span>
                                                    </div>
                                                    <div class="btn-add  pull-left m-l-30">
                                                        <a ng-href="{{'/mgt/fangManage/detail?id='+house.id}}" target="_blank"><i class="fa fa-pencil"></i>查看详情</a>
                                                        <a class="m-l-20" ng-href="javascript:;" ng-show="house.process.name == page.status.DELEGATE|| house.process.name == page.status.UN_PUBLISH"
                                                           ng-click="changeStatus(page.status.PUBLISH, house.id)">
                                                            <i class="fa fa-pencil"></i>上架
                                                        </a>
                                                        <a class="m-l-20" ng-href="javascript:;"
                                                           ng-click="changeStatus(page.status.UN_PUBLISH, house.id)"  ng-show="house.process.name == page.status.DELEGATE|| house.process.name == page.status.PUBLISH">
                                                            <i class="fa fa-pencil"></i>下架
                                                        </a>
                                                        <a class="m-l-20" ng-href="{{'/mgt/propertyVisit/addPropertyVisit?target=.prtyVisit&licenceId='+house.licenceId}}"
                                                           ng-show="house.process.name == page.status.DELEGATE || house.process.name == page.status.PUBLISH">
                                                            <i class="fa fa-pencil"></i>新增带看
                                                        </a>
                                                        <a class="m-l-20" ng-href="{{'/mgt/contract/addDeal?target=.contract&licenceId='+house.licenceId}}"
                                                           ng-show="house.process.name == page.status.DELEGATE || house.process.name == page.status.PUBLISH">
                                                            <i class="fa fa-pencil"></i>成交
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-2 col-md-2 hidden-sm">
                                                <p><strong class="f18">{{house.estateArea}}</strong>m<sup>2</sup></p>
                                                <span class="text-muted">{{house.realArea}}m<sup>2</sup></span>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3 text-right">
                                                <p><strong class="text-danger f18">{{house.publishPrice}}</strong>{{house.priceUnit.label}}</p>
                                                <p>{{house.unitPrice}}<span ng-if="house.priceUnit.name === 'WAN'">元</span><span ng-if="house.priceUnit.name !== 'WAN'">{{house.priceUnit.label}}</span>/m<sup>2</sup></p>
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
    </section>
</div>
<!-- /.content-wrapper -->

<#include "/common/footer.ftl" />
<script src="${contextPath!}/js/app/fang/list.js?vn=${bts!}"></script>
<link href="${contextPath}/css/app/fang/index.css" rel="stylesheet">
<link href="${contextPath}/css/app/fang/addHouse.css" rel="stylesheet">
<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />
<div class="content-wrapper" id="addHouse" ng-controller="AddHouseCtrl as ctrl">
        <section class="content-header">
            <ol class="breadcrumb">
                <li><i class="fa fa-home fa-lg"></i><a href="#">房源管理</a></li>
                <li class="active"><span ng-bind="ctrl.page.name"></span></li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <!-- Small boxes (Stat box) -->
            <!-- Main row -->
            <div class="row animated fadeInRight">
                <div class="col-lg-12">
                    <div class="box box-solid" ng-cloak>
                        <div class="box-header with-border">
                            <h3 class="box-title">新增住宅</h3>
                        </div>

                        <form id="createForm" class="form-horizontal" name="houseForm">
                            <div class="box-body">
                                <div id="wizard">
                                    <ul id="status">
                                        <li ng-class="{active:ctrl.page.currentStep === ctrl.stepConfig.step1}"><strong>1.</strong>基本信息<span class="chevron"></span></li>
                                        <li ng-class="{active:ctrl.page.currentStep === ctrl.stepConfig.step2}"><strong>2.</strong>配套信息<span class="chevron"></span></li>
                                    </ul>
                                    <div class="items" ng-class="{'step2': ctrl.page.currentStep === ctrl.stepConfig.step2}">
                                        <div class="page" id="page1">
                                            <div class="form-group clearfix">
                                                <label class="control-label">用途</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select id="houseType" select-picker class="selectpicker show-menu-arrow form-control sel-news"  ng-model="ctrl.data.houseType" ng-change="ctrl.houseTypeChange(e)">
                                                        <option value="">房源类型</option>
                                                    <#list houseTypes?if_exists as type>
                                                        <option value="${type.name()}">${type.getLabel()}</option>
                                                    </#list>
                                                    </select>
                                                </div>
                                                <input type="hidden" value="9880"/>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select id="houseSubType" select-picker class="selectpicker show-menu-arrow form-control sel-news" ng-model="ctrl.data.houseSubType" ng-change="ctrl.selectPickerChange('#houseSubType', 'houseSubType')">
                                                        <option value="">子类型</option>
                                                        <option ng-repeat="type in ctrl.subTypeList" ng-value="type.value" repeat-done="ctrl.subTypeRefresh('#houseSubType')">{{type.name}}</option>
                                                    </select>
                                                </div>
                                                <div class="col-lg-4 col-md-4  col-sm-4 text-right">
                                                    <label class="mt7">编号：自动生成</label>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">交易</label>
                                                <div class="col-lg-9 col-md-9 col-sm-9 form-inline m-t-7" id="jiaoyi">
                                                    <div class="radio radio-nice">
                                                        <input type="radio" name="houseTratype" ng-value="'RENT'" ng-model="ctrl.data.bizType" ng-change="ctrl.checkLicence()" id="fy75" />
                                                        <label for="fy75">出租</label>
                                                    </div>
                                                    <div class="radio radio-nice">
                                                        <input type="radio" name="houseTratype" ng-value="'SELL'" ng-model="ctrl.data.bizType" ng-change="ctrl.checkLicence()" id="fy76"/>
                                                        <label for="fy76">出售</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">物业地址</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2" id="estateContainer">
                                                    <select id="houseEstate" data-placeholder="楼盘字典" class="chosen-select" name="houseEstate">
                                                        <option value="">楼盘字典</option>
                                                        <option ng-repeat="estate in ctrl.estateList" repeat-done="ctrl.chosenEstate('#houseEstate', 'xiaoQuId')" ng-value="estate.value">{{estate.name}}</option>
                                                    </select>
                                                </div>
                                                <div class="col-lg-3 col-md-3 col-sm-3 no-padding">
                                                    <div class="col-xs-6">
                                                        <select name="houseBuild" id="houseBuild" class="chosen-select" reg="^\S+$">
                                                            <option value="" header="">栋座</option>
                                                            <option ng-repeat="build in ctrl.buildList" repeat-done="ctrl.initChosen('#houseBuild', 'buildingId')" ng-value="build.id">{{build.name}}</option>
                                                        </select>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <select name="houseUnit" id="houseUnit" class="chosen-select" reg="^\S+$">
                                                            <option value="" header="">单元</option>
                                                            <option ng-repeat="unit in ctrl.unitList" repeat-done="ctrl.initChosen('#houseUnit', 'buildingUnitId')" ng-value="unit.value">{{unit.name}}</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-lg-1 col-md-1 col-sm-1">
                                                    <input name="houseNo" placeholder="房号" type="text" class="addfy_input bitian form-control" ng-model="ctrl.data.houseNo" ng-blur="ctrl.checkLicence()" reg="^\S+$" required/>
                                                </div>
                                                <div class="col-lg-1 col-md-1 col-sm-1">
                                                    <input type="text" name="floor"  reg="^\S+$"  class="form-control" placeholder="楼层" ng-model="ctrl.data.floor" required ng-blur="ctrl.floorCountCheck()"/>
                                                </div>
                                                <div class="col-lg-1 col-md-1 col-sm-1">
                                                    <input type="text" name="floorCounts" reg="^\S+$" class="form-control" placeholder="总层" ng-model="ctrl.data.floorCounts" required ng-blur="ctrl.floorCountCheck()"/>
                                                </div>
                                                <div class="pull-left m-l-5" ng-show="ctrl.data.xiaoQuId&&!ctrl.data.buildingId" ng-click="ctrl.addBuildInit()">
                                                    <button type="button" class="btn btn-primary" id="changes">申请添加栋座</button>
                                                </div>
                                                <div class="pull-left m-l-5" ng-show="ctrl.data.xiaoQuId&&ctrl.data.buildingId" ng-click="ctrl.addUnitInit()">
                                                    <button type="button" class="btn btn-primary" id="changes">申请添加单元</button>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">证件地址</label>
                                                <div class="col-lg-4 col-md-4 col-sm-4">
                                                    <input type="text" name="address" name="address" class="form-control" required placeholder="房产证地址" ng-model="ctrl.data.address"/>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <input name="permitNumber" class="form-control" placeholder="证件编号" type="text" ng-model="ctrl.data.certifNo">
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">类型</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select id="houseStructureType" select-picker class="selectpicker show-menu-arrow form-control bs-select-hidden sel-news" name="houseStructureType"
                                                            ng-model="ctrl.data.structureType" ng-change="ctrl.selectPickerChange('#houseStructureType', 'structureType')">
                                                        <option value="">结构类型</option>
                                                    <#list structureType?if_exists as type>
                                                        <option value="${type.name()}">${type.getLabel()}</option>
                                                    </#list>
                                                    </select>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <div class="input-group date form_date" format="yyyy" datetimepicker key="buildYear" change="datePickChange">
                                                        <input name="buildYear" class="form-control" size="16" placeholder="建造日期" type="text" ng-model="ctrl.data.buildYear" required ng-pattern="/\d{4}$/">
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
                                                            <span ng-bind="ctrl.page.layoutString||'选择户型'"></span>
                                                        </a>
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">朝向</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select id="houseOrientation" select-picker class="selectpicker show-menu-arrow form-control" name="orientation" ng-model="ctrl.data.orientation" ng-change="ctrl.selectPickerChange('#houseOrientation', 'orientation')">
                                                        <option value="">--请选择--</option>
                                                    <#list orientation?if_exists as ortation>
                                                        <option value="${ortation.name()}">${ortation.getLabel()}</option>
                                                    </#list>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">面积</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <input type="text" name="estateArea" class="form-control" required placeholder="建筑面积" ng-model="ctrl.data.estateArea" ng-pattern="/^\d{1,5}([.]\d{1,2})*$/" ng-blur="ctrl.estateAreaCheck()"/>
                                                </div>
                                                <div class="pull-left m-t-7 p-l-0">
                                                    ㎡
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <input type="text" name="housePartsqm" class="form-control" placeholder="套内面积" ng-model="ctrl.data.realArea" ng-pattern="/^\d{1,5}([.]\d{1,2})*$/" ng-blur="ctrl.checkArea()"/>
                                                </div>
                                                <div class="pull-left m-t-7 p-l-0">
                                                    ㎡
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">价格</label>
                                                <div class="col-lg-1 col-md-2 col-sm-2">
                                                    <input type="text" name="publishPrice" required class="form-control" placeholder="总价" strName="总价" ng-model="ctrl.data.publishPrice" ng-blur="ctrl.publishPriceSet('publishPrice')" ng-pattern="/^(?!0+(?:\.0+)?$)(?:[1-9]\d{0,8}|0)(?:\.\d{1,2})?$/"/>
                                                </div>
                                                <div class="col-lg-1 col-md-2 col-sm-2" class="pull-left" id="rent" style="display:block" ng-show="ctrl.data.bizType === ctrl.bizTypeConfig.rent">
                                                    <select select-picker class="selectpicker show-menu-arrow form-control sel-news" name="rentPriceUnit" id="rentPriceUnit" ng-model="ctrl.data.rentPriceUnit">
                                                    <#list rentPriceUnit ?if_exists as unit>
                                                        <option value="${unit.name()}">${unit.getLabel()}</option>
                                                    </#list>
                                                    </select>
                                                </div>
                                                <div class="col-lg-1 col-md-2 col-sm-2" id="sale" ng-show="ctrl.data.bizType === ctrl.bizTypeConfig.sell">
                                                    <select select-picker class="selectpicker show-menu-arrow form-control sel-news" id="sellPriceUnit" ng-model="ctrl.data.sellPriceUnit">
                                                    <#list sellPriceUnit ?if_exists as unit>
                                                        <option value="${unit.name()}">${unit.getLabel()}</option>
                                                    </#list>
                                                    </select>
                                                </div>
                                                <div class="col-lg-1 col-md-2 col-sm-2">
                                                    <input type="text" name="housePriceunit" strName="单价" class="form-control" ng-model="ctrl.data.unitPrice" placeholder="单价" ng-blur="ctrl.unitPriceSet('unitPrice')" ng-pattern="/^(?!0+(?:\.0+)?$)(?:[1-9]\d{0,8}|0)(?:\.\d{1,2})?$/"/>
                                                </div>
                                                <div class="pull-left m-t-7 p-l-0" id="danwei">
                                                <#list rentPriceUnit ?if_exists as unit>
                                                    <span ng-show="ctrl.data.bizType === ctrl.bizTypeConfig.rent && ctrl.data.rentPriceUnit === '${unit.name()}'">${unit.getLabel()}</span>
                                                </#list>
                                                <#list sellPriceUnit ?if_exists as unit>
                                                    <span ng-show="ctrl.data.bizType === ctrl.bizTypeConfig.sell && ctrl.data.sellPriceUnit === '${unit.name()}'">${unit.getLabel()}</span>
                                                </#list>/㎡
                                                </div>
                                                <div class="col-lg-1 col-md-2 col-sm-2">
                                                    <input type="text" name="housePriceMin" ng-model="ctrl.data.bottomPrice" class="form-control" placeholder="底价" strName="底价" ng-blur="ctrl.bottomPriceCheck('publishPrice')" ng-pattern="/^(?!0+(?:\.0+)?$)(?:[1-9]\d{0,8}|0)(?:\.\d{1,2})?$/"/>
                                                </div>
                                                <div class="pull-left m-t-7 p-l-0" id="danwei">
                                                <#list rentPriceUnit ?if_exists as unit>
                                                    <span ng-show="ctrl.data.bizType === ctrl.bizTypeConfig.rent && ctrl.data.rentPriceUnit === '${unit.name()}'">${unit.getLabel()}</span>
                                                </#list>
                                                <#list sellPriceUnit ?if_exists as unit>
                                                    <span ng-show="ctrl.data.bizType === ctrl.bizTypeConfig.sell && ctrl.data.sellPriceUnit === '${unit.name()}'">${unit.getLabel()}</span>
                                                </#list>/㎡
                                                </div>
                                            </div>
                                            <div class="form-group clearfix" id="fyHouseSettle">
                                                <label class="control-label">落户</label>
                                                <div class="col-lg-8 col-md-8 col-sm-8 form-inline m-t-7">
                                                    <div class="radio radio-nice" >
                                                        <input type="radio" name="houseSettle" id="settleYes" ng-value="'Y'" ng-model="ctrl.data.resident"/>
                                                        <label for="settleYes">是</label>
                                                    </div>
                                                    <div class="radio radio-nice" >
                                                        <input type="radio" name="houseSettle" id="settleNo" ng-value="'N'" ng-model="ctrl.data.resident"/>
                                                        <label for="settleNo">否</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">业主</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <input type="text" name="houseOwname" reg="^\S+$" class="form-control" placeholder="业主姓名" required ng-model="ctrl.data.ownerName"/>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">手机</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <input type="text" name="houseOwphone1" required st-mobile-phone class="form-control" placeholder="业主手机1" ng-model="ctrl.data.mobiles[0]"/>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <input type="text" name="houseOwphone2" st-mobile-phone class="form-control" placeholder="业主手机2" ng-model="ctrl.data.mobiles[1]"/>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <input type="text" name="houseOwphone3" st-mobile-phone class="form-control" placeholder="业主手机3" ng-model="ctrl.data.mobiles[2]"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-lg-12 col-md-12 col-sm-12">
                                                    <div class="btn_nav  pull-right">
                                                        <button type="button" class="next btn btn-primary" ng-click="ctrl.nextStep()">下一步<i class="fa fa-chevron-right"></i></button>
                                                        <button type="button" class="btn btn-default" style="border-color:#aaa"><i class="fa fa-times"></i>取消</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="page">
                                            <div class="form-group clearfix">
                                                <label class="control-label">特性</label>
                                                <div class="pull-left" style="margin-top:8px">
                                                <#list houseLevel ?if_exists as level>
                                                    <div class="radio radio-inline" style="padding-top:0px">
                                                        <input value="${level.name()}" id="level${level.name()}" ng-model="ctrl.data.level" type="radio">
                                                        <label for="level${level.name()}">${level.getLabel()}</label>
                                                    </div>
                                                </#list>
                                                </div>
                                                <span class="opt-gap pull-left" style="margin-top:10px;"></span>
                                                <div class="pull-left" style="margin-top:8px">
                                                    <div class="radio radio-inline" style="padding-top:0px">
                                                        <input type="radio" id="character10141" ng-value="5" ng-model="ctrl.data.overYears" ng-change="ctrl.log()"/>
                                                        <label for="character10141">满五年</label>
                                                    </div>
                                                    <div class="radio radio-inline" style="padding-top:0px">
                                                        <input type="radio" id="character10142" ng-value="2" ng-model="ctrl.data.overYears" ng-change="ctrl.log()"/>
                                                        <label for="character10142">满两年</label>
                                                    </div>
                                                    <div class="radio radio-inline" style="padding-top:0px">
                                                        <input type="radio" id="character10143" value="" ng-model="ctrl.data.overYears" ng-change="ctrl.log()"/>
                                                        <label for="character10143">不满两年</label>
                                                    </div>
                                                </div>
                                                <span class="opt-gap pull-left" style="margin-top:10px;"></span>
                                                <div class="pull-left id="tx3">
                                                    <div class="checkbox checkbox-nice checkbox-inline">
                                                        <input type="checkbox" name="houseCharacter" id="character10140" ng-model="ctrl.data.isOnly" ng-change="ctrl.log()"/>
                                                        <label for="character10140">唯一住房</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">委托</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select select-picker class="selectpicker show-menu-arrow form-control"  reg="^\S+$"  name="delegateType" id="houseDelegateType"
                                                            ng-model="ctrl.data.delegateType"  ng-change="ctrl.selectPickerChange('#houseDelegateType', 'delegateType')">
                                                        <option value="">--请选择--</option>
                                                    <#list delegateType ?if_exists as type>
                                                        <option value="${type.name()}">${type.getLabel()}</option>
                                                    </#list>
                                                    </select>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <div class="input-group date form_date" datetimepicker key="delegateStart" change="datePickChange">
                                                        <input class="form-control bgWhite" ng-model="ctrl.data.delegateStart" required ng-pattern="/\d{4}-\d{2}-\d{2}$/" type="text" placeholder="开始日期" name="delegateStart"/>
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                    </div>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <div class="input-group date form_date" datetimepicker key="delegateEnd" change="datePickChange">
                                                        <input class="form-control bgWhite" size="16" type="text" placeholder="到期日期" name="delegateEnd" ng-model="ctrl.data.delegateEnd" ng-change="ctrl.setBuildDate()" id="delegateEnd" readonly/>
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">看房</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select select-picker class="selectpicker show-menu-arrow form-control" name="houseShowing" id="houseShowing"
                                                            ng-model="ctrl.data.showing" ng-change="ctrl.selectPickerChange('#houseShowing', 'showing')">
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
                                                            ng-model="ctrl.data.status"  ng-change="ctrl.selectPickerChange('#houseStatus', 'status')">
                                                        <option value="">--请选择--</option>
                                                    <#list houseStatus ?if_exists as status>
                                                        <option value="${status.name()}">${status.getLabel()}</option>
                                                    </#list>
                                                    </select>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select select-picker class="selectpicker show-menu-arrow form-control"  name="houseSource" id="houseSource"
                                                            ng-model="ctrl.data.source" ng-change="ctrl.selectPickerChange('#houseSource', 'source')">
                                                        <option value="">来源</option>
                                                    <#list houseSource ?if_exists as source>
                                                        <option value="${source.name()}">${source.getLabel()}</option>
                                                    </#list>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">证件</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select select-picker class="selectpicker show-menu-arrow form-control" name="houseProveType" id="houseCertifType"
                                                            ng-model="ctrl.data.certifType" ng-change="ctrl.selectPickerChange('#houseCertifType', 'certifType')">
                                                        <option value="">--请选择--</option>
                                                    <#list certifType ?if_exists as type>
                                                        <option value="${type.name()}">${type.getLabel()}</option>
                                                    </#list>
                                                    </select>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <div class="input-group date form_date" datetimepicker key="purchaseDate" change="datePickChange">
                                                        <input class="form-control " size="16" type="text" id="houseProveDate" ng-model="ctrl.data.purchaseDate" placeholder="出证日期" name="houseProveDate"/>
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                    </div>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select select-picker class="selectpicker show-menu-arrow form-control" name="housePType" id="housePropertyType"
                                                            ng-model="ctrl.data.propertyType" ng-change="ctrl.selectPickerChange('#housePropertyType', 'propertyType')">
                                                        <option value="">产权类型</option>
                                                    <#list propertyType ?if_exists as type>
                                                        <option value="${type.name()}">${type.getLabel()}</option>
                                                    </#list>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">装修</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select select-picker class="selectpicker show-menu-arrow form-control" name="houseDecorate" id="houseDecorate"
                                                            ng-model="ctrl.data.decorate" ng-change="ctrl.selectPickerChange('#houseDecorate', 'decorate')">
                                                        <option value="">--请选择--</option>
                                                        <#list decorate ?if_exists as type>
                                                            <option value="${type.name()}">${type.getLabel()}</option>
                                                        </#list>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">供暖类型</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select select-picker class="selectpicker show-menu-arrow form-control" name="houseHeating" id="houseHeatingType"
                                                            ng-model="ctrl.data.heatingType" ng-change="ctrl.selectPickerChange('#houseHeatingType', 'heatingType')">
                                                        <option value="">--请选择--</option>
                                                        <#list heatingType ?if_exists as type>
                                                            <option value="${type.name()}">${type.getLabel()}</option>
                                                        </#list>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">电梯</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select select-picker class="selectpicker show-menu-arrow form-control" name="houseLift" id="houseHasElevator"
                                                            ng-model="ctrl.data.hasElevator" ng-change="ctrl.selectPickerChange('#houseHasElevator', 'hasElevator')">
                                                        <option value="">--请选择--</option>
                                                        <option value="Y">有</option>
                                                        <option value="N">无</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">税款</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select select-picker class="selectpicker show-menu-arrow form-control " name="houseTaxesWilling" id="houseTaxesWilling"
                                                            ng-model="ctrl.data.taxesWilling" ng-change="ctrl.selectPickerChange('#houseTaxesWilling', 'taxesWilling')">
                                                        <option value="">--请选择--</option>
                                                        <#list taxesWilling ?if_exists as type>
                                                            <option value="${type.name()}">${type.getLabel()}</option>
                                                        </#list>
                                                    </select>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select select-picker class="selectpicker show-menu-arrow form-control minusHeight" name="houseCommission" id="houseCommissionWilling"
                                                            ng-model="ctrl.data.commissionWilling" ng-change="ctrl.selectPickerChange('#houseCommissionWilling', 'commissionWilling')">
                                                        <option value="">付佣</option>
                                                    <#list commissionWilling ?if_exists as type>
                                                        <option value="${type.name()}">${type.getLabel()}</option>
                                                    </#list>
                                                    </select>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <input type="text" name="housePurchase" class="form-control" placeholder="原购价" ng-model="ctrl.data.purchasePrice"/>
                                                </div>
                                                <div class="pull-left m-t-7 p-l-0" id="yuangou">
                                                    元
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">备注</label>
                                                <div class="col-lg-9 col-md-9 col-sm-9">
                                                    <textarea name="note" cols="30" rows="3" class="form-control" ng-model="ctrl.data.note"></textarea>
                                                </div>
                                            </div>
                                            <div class="form-group border0">
                                                <div class="col-lg-12 col-md-12 col-sm-12">
                                                    <div class="btn_nav  pull-right">
                                                        <button type="button" class="prev btn btn-primary" ng-click="ctrl.prevStep()"><i class="fa fa-chevron-left"></i>上一步</button>
                                                        <button type="button" class="next btn btn-primary" ng-click="ctrl.submit()"><i class="fa fa-save"></i>保存</button>
                                                        <button type="button" class="btn btn-default" style="border-color: #aaa"><i class="fa fa-times"></i>取消</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- /.row (main row) -->
        </section>
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
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#layoutModel" ng-click="ctrl.layoutConfirm()">确认</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="addBuildModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">添加楼栋</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" name="newBuildForm">
                        <input id="glBuildingId" name="glBuildingVO.glBuildingId" value="" type="hidden">
                        <input id="glEstateId" name="glBuildingVO.glEstateId" value="85718" type="hidden">
                        <input id="glBuildingVerify" name="glBuildingVO.glBuildingVerify" value="true" type="hidden">
                        <div class="form-group clearfix">
                            <label class="control-label">楼盘名称</label>
                            <div class="col-xs-8 mt7" ng-bind="ctrl.newBuilding.xiaoqu.name"></div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label">楼盘地址</label>
                            <div class="col-xs-8 mt7" ng-bind="ctrl.newBuilding.xiaoqu.address"></div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label">栋座名称</label>
                            <div class="col-xs-9">
                                <input id="glBuildingName" name="buildingName" required reg="^\S+$" class="form-control" placeholder="栋座名称" type="text" ng-model="ctrl.newBuilding.name">
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label">栋座楼层</label>
                            <div class="col-xs-3">
                                <input id="glBuildingFloorall" name="buildingFloors" required reg="^\S+$" class="form-control" placeholder="总层" type="text" ng-model="ctrl.newBuilding.floors">
                            </div>
                        </div>

                        <div class="form-group clearfix">
                            <label class="control-label">梯/户数</label>
                            <div class="col-xs-3">
                                <input id="buildingCounth" required name="buildingStairs" class="form-control" placeholder="梯数" type="text" ng-model="ctrl.newBuilding.stairs">
                            </div>
                            <div class="col-xs-3">
                                <input id="buildingCounth" required name="buildingHouses" class="form-control" placeholder="户数" type="text" ng-model="ctrl.newBuilding.houses">
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label">描述说明</label>
                            <div class="col-xs-9">
                                <textarea id="glBuildingRemark" name="buildingRemark" cols="30" rows="5" class="form-control" placeholder="备注" ng-model="ctrl.newBuilding.description"></textarea>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" ng-click="ctrl.addBuildConfirm()">确认</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="addUnitModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">添加单元</h4>
                </div>
                <div class="modal-body">
                    <form id="form" class="form-horizontal" name="addUnitForm">
                        <input id="glBuildingId" name="glBuildingVO.glBuildingId" value="588309" type="hidden">
                        <div class="form-group clearfix">
                            <label class="control-label">栋座名称</label>
                            <div class="col-xs-8 mt7" ng-bind="ctrl.newUnit.buildingName"></div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label">已有单元</label>
                            <div class="col-xs-8 mt7">
                                <div class="col-xs-6" ng-repeat=" item in ctrl.unitList">{{item.name}}</div>
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label">单元名称</label>
                            <div class="col-xs-3">
                                <input name="buildingUnits0" class="form-control" ng-model="ctrl.newUnit.unitNames[0]" placeholder="单元名称" type="text">
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label">单元名称</label>
                            <div class="col-xs-3">
                                <input name="buildingUnits1" class="form-control" ng-model="ctrl.newUnit.unitNames[1]" placeholder="单元名称" type="text">
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label">单元名称</label>
                            <div class="col-xs-3">
                                <input name="buildingUnits2" class="form-control" ng-model="ctrl.newUnit.unitNames[2]" placeholder="单元名称" type="text">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" data-toggle="modal" ng-click="ctrl.addUnitConfirm()">确认</button>
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
                    <h4 class="modal-title" id="myModalLabel" ng-bind="ctrl.page.warn.title">

                    </h4>
                </div>
                <div class="modal-body" ng-bind="ctrl.page.warn.content">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" ng-click="ctrl.page.warn.closeF()" data-dismiss="modal">确定
                    </button>
                </div>
            </div>
        </div>
    </div>
    </div>
<!-- /.content-wrapper -->

<#include "/common/footer.ftl" />
<script src="${contextPath!}/js/app/fang/create.js?vn=${bts!}"></script>

<link href="${contextPath}/css/app/houseManage/index.css" rel="stylesheet">
<link href="${contextPath}/css/app/houseManage/addHouse.css" rel="stylesheet">
<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />
<div class="content-wrapper" id="addHouse" ng-controller="AddHouseCtrl as ctrl">
        <style type="text/css">
            .tip.active{
                background-color:#169bd5;
                color:#fff;
            }
        </style>
        <section class="content-header">
            <ol class="breadcrumb">
                <li><i class="fa fa-home fa-lg"></i><a href="#">房源管理</a></li>
                <li class="active"><span ng-bind="ctrl.desc.pageName"></span></li>
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

                        <form id="addForm" class="form-horizontal">
                            <input type="hidden" id="repeatflag"/>
                            <input type="hidden" id="fyHouseId" name="houseVO.fyHouseId"/>
                            <input type="hidden" id="fyHouseCityid" value="73"/>
                            <input type="hidden" id="fyHouseComno" value="BSnlhD"/>
                            <input type="hidden" id="fyHouseEstname" name="houseVO.fyHouseEstname"/>
                            <input type="hidden" id="fyHouseDsid" name="houseVO.fyHouseDsid"/>
                            <input type="hidden" id="fyHousePicid" name="houseVO.fyHousePicid"/>
                            <input type="hidden" id="fyHouseBuildname" name="houseVO.fyHouseBuildname"/>
                            <input type="hidden" id="fyHouseUnitname" name="houseVO.fyHouseUnitname"/>
                            <input type="hidden"  name="houseVO.fyHousekeyId" value=""/>
                            <input type="hidden" id="fyHousekeyDeptid" name="houseVO.fyHousekeyDeptid" value=""/>
                            <input type="hidden" id="fyHousekeyEmpid" name="houseVO.fyHousekeyEmpid" value=""/>
                            <input type="hidden" id="fyHousekeyHavetime" name="houseVO.fyHousekeyHavetime" value=""/>
                            <input type="hidden" id="fyHousekeyKeyno" name="houseVO.fyHousekeyKeyno" value=""/>
                            <input type="hidden" id="fyHousekeyRemark" name="houseVO.fyHousekeyRemark" value=""/>
                            <input type="hidden" id="fyHouseOwphone" value="false"/>
                            <div class="box-body">
                                <div id="wizard">
                                    <ul id="status">
                                        <li ng-class="{active:ctrl.currentStep === ctrl.stepConfig.step1}"><strong>1.</strong>基本信息<span class="chevron"></span></li>
                                        <li ng-class="{active:ctrl.currentStep === ctrl.stepConfig.step2}"><strong>2.</strong>配套信息<span class="chevron"></span></li>
                                    </ul>
                                    <div class="items" ng-class="{'step2': ctrl.currentStep === ctrl.stepConfig.step2}">
                                        <div class="page" id="page1">
                                            <div class="form-group clearfix">
                                                <label class="control-label">用途</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <label  class="mt7">住宅</label>
                                                </div>
                                                <input type="hidden" value="9880"/>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select id="houseType" select-picker class="selectpicker show-menu-arrow form-control sel-news" ng-model="ctrl.data.type" ng-change="ctrl.log()">
                                                        <option value="">住宅类型</option>
                                                        <option ng-repeat="type in ctrl.typeList" ng-value="type.value">{{type.name}}</option>
                                                    </select>
                                                </div>
                                                <div class="col-lg-4 col-md-4  col-sm-4 text-right">
                                                    <label class="mt7">编号：自动生成</label>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix" id="estateContainer">
                                                <label class="control-label">物业地址</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select id="houseEstate" data-placeholder="楼盘字典" class="chosen-select"
                                                            name="houseEstate" reg="^\S+$">
                                                        <option ng-value="">楼盘字典</option>
                                                    <#--<#list xiaoQuOptions?if_exists as xq>
                                                        <option type="radio" value="${xq.getXiaoQuId()}">${xq.getXiaoQuName()}</option>
                                                    </#list>-->
                                                   <option ng-repeat="estate in ctrl.estateList" repeat-done="ctrl.chosenInitEstate('#houseEstate', 'estate')" ng-value="estate.value">{{estate.name}}</option>
                                                    </select>
                                                </div>
                                                <div class="col-lg-3 col-md-3 col-sm-3 no-padding">
                                                    <div class="col-xs-6">
                                                        <select name="fyHouseBuildid" id="houseBuild" class="chosen-select" reg="^\S+$">
                                                            <option value="" header="">栋座</option>
                                                            <option ng-repeat="build in ctrl.houseBuildList" repeat-done="ctrl.initChosen('#houseBuild', 'build')" ng-value="build.value">{{build.name}}</option>
                                                        </select>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <select name="fyHouseUnitid" id="houseUnit" class="chosen-select" reg="^\S+$">
                                                            <option value="" header="">单元</option>
                                                            <option ng-repeat="unit in ctrl.houseUnitList" repeat-done="ctrl.initChosen('#houseUnit', 'unit')" ng-value="unit.value">{{unit.name}}</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-lg-1 col-md-1 col-sm-1">
                                                    <input name="houseRoom" placeholder="房号" type="text" class="addfy_input bitian form-control" ng-model="ctrl.data.room" reg="^\S+$"/>
                                                </div>
                                                <div class="col-lg-1 col-md-1 col-sm-1">
                                                    <input type="text" name="houseFloor"  reg="^\S+$"  class="form-control" placeholder="楼层" ng-model="ctrl.data.floor"/>
                                                </div>
                                                <div class="col-lg-1 col-md-1 col-sm-1">
                                                    <input type="text" name="houseFloorAll" reg="^\S+$" class="form-control" placeholder="总层" ng-model="ctrl.data.floorAll"/>
                                                </div>
                                                <div class="pull-left m-l-5" ng-show="ctrl.data.estate&&!ctrl.data.build" data-toggle="modal" data-target="#addBuildModel" ng-click="ctrl.addBuildInit()">
                                                    <button type="button" class="btn btn-primary" id="changes">申请添加栋座</button>
                                                </div>
                                                <div class="pull-left m-l-5" ng-show="ctrl.data.estate&&ctrl.data.build" data-toggle="modal" data-target="#addUnitModel" ng-click="ctrl.addUnitInit()">
                                                    <button type="button" class="btn btn-primary" id="changes">申请添加单元</button>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">证件地址</label>
                                                <div class="col-lg-4 col-md-4 col-sm-4">
                                                    <input type="text" name="permitAddress" class="form-control" placeholder="房产证地址" ng-model="ctrl.data.permitAddress"/>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <input name="permitNumber" class="form-control" placeholder="证件编号" type="text" ng-model="ctrl.data.permitNumber">
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">类型</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select id="houseConstruct" select-picker class="selectpicker show-menu-arrow form-control bs-select-hidden sel-news" name="fyHouseConstruct" ng-model="ctrl.data.construct">
                                                        <option value="">结构类型</option>
                                                        <option ng-repeat="construct in ctrl.houseConstructList" ng-value="construct.value">{{construct.name}}</option>
                                                    </select>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <div class="input-group date form_date" datetimepicker key="buildDate" change="setDate">
                                                        <input class="form-control" size="16" placeholder="建造日期" type="text" ng-model="ctrl.data.startDate" ng-change="ctrl.setBuildDate()" >
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
                                                            <span ng-bind="(ctrl.data.layout.shi || ctrl.data.layout.ting || ctrl.data.layout.chu || ctrl.data.layout.wei || ctrl.data.layout.yangtai) ?
                                                            (ctrl.data.layout.shi? ctrl.data.layout.shi+'室':'') +
                                                            (ctrl.data.layout.ting ? ctrl.data.layout.ting+'厅':'') +
                                                            (ctrl.data.layout.chu?ctrl.data.layout.chu+'厨':'') +
                                                            (ctrl.data.layout.wei?ctrl.data.layout.wei+'卫':'') +
                                                            (ctrl.data.layout.yangtai?ctrl.data.layout.yangtai+'阳台':'') :'选择户型'"></span>
                                                        </a>
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">朝向</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select id="houseDirection" select-picker class="selectpicker show-menu-arrow form-control" name="houseDirection" ng-model="ctrl.data.direction" ng-change="ctrl.log()">
                                                        <option value="">--请选择--</option>
                                                        <option ng-repeat="direction in ctrl.directionList" ng-value="direction.value">{{direction.name}}</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">面积</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <input type="text" name="houseAllsqm"  reg="^\d{1,4}([.]\d{1,2})*$" class="form-control" placeholder="建筑面积" ng-model="ctrl.data.allSqm"/>
                                                </div>
                                                <div class="pull-left m-t-7 p-l-0">
                                                    ㎡
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <!-- BUG #9501 面积必填项增加套内面积必填 -->
                                                    <input type="text" name="houseVO.fyHousePartsqm" class="form-control" placeholder="套内面积" id="fyHousePartsqm" onblur="checkArea();" ng-model="ctrl.data.partSqm"/>
                                                </div>
                                                <div class="pull-left m-t-7 p-l-0">
                                                    ㎡
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">交易</label>
                                                <div class="col-lg-9 col-md-9 col-sm-9 form-inline m-t-7" id="jiaoyi">
                                                    <div class="radio radio-nice">
                                                        <input type="radio" name="houseVO.fyHouseTratype" ng-value="'rent'" ng-model="ctrl.data.bizType" id="fy75" />
                                                        <label for="fy75">出租</label>
                                                    </div>
                                                    <div class="radio radio-nice">
                                                        <input type="radio" name="houseVO.fyHouseTratype" ng-value="'sell'" ng-model="ctrl.data.bizType" id="fy76"/>
                                                        <label for="fy76">出售</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">价格</label>
                                                <div class="col-lg-1 col-md-2 col-sm-2">
                                                    <input type="text" name="housePrice" reg="^(?!0+(?:\.0+)?$)(?:[1-9]\d{0,8}|0)(?:\.\d{1,2})?$" class="form-control" placeholder="总价" strName="总价" ng-model="ctrl.data.price"/>
                                                </div>
                                                <div class="col-lg-1 col-md-2 col-sm-2" class="pull-left" id="rent" style="display:block" ng-show="ctrl.data.bizType === 'rent'">
                                                    <select select-picker class="selectpicker show-menu-arrow form-control sel-news" name="rentPriceUnit" id="rentPriceUnit" ng-model="ctrl.data.rentPriceUnit">
                                                        <option value="9929" info="ty51">元</option>
                                                        <option value="9930" info="ty52">元/天</option>
                                                        <option value="9931" info="ty53">元/月</option>
                                                        <option value="9932" info="ty54">元/季</option>
                                                        <option value="9933" info="ty55">元/年</option>
                                                    </select>
                                                </div>
                                                <div class="col-lg-1 col-md-2 col-sm-2" id="sale" ng-show="ctrl.data.bizType === 'sell'">
                                                    <select select-picker class="selectpicker show-menu-arrow form-control sel-news" id="sellPriceUnit" ng-model="ctrl.data.sellPriceUnit">
                                                        <option value="9935" info="ty56">万元</option>
                                                        <option value="9936" info="ty57">元</option>
                                                    </select>
                                                </div>
                                                <div class="col-lg-1 col-md-2 col-sm-2">
                                                    <input type="text" name="housePriceunit" strName="单价" class="form-control" ng-model="ctrl.data.unitPrice" placeholder="单价" reg="^0\.\d{1,2}$|^[1-9]\d{0,8}\.\d{1,2}$|^[1-9]\d{0,8}$|^0$|^$"/>
                                                </div>
                                                <div class="pull-left m-t-7 p-l-0" id="danwei">

                                                </div>
                                                <div class="col-lg-1 col-md-2 col-sm-2">
                                                    <input type="text" name="housePriceMin" ng-model="ctrl.data.minPrice" class="form-control" placeholder="底价" strName="底价" reg="^0\.\d{1,2}$|^[1-9]\d{0,8}\.\d{1,2}$|^[1-9]\d{0,8}$|^0$|^$" />
                                                </div>
                                                <div class="pull-left m-t-7 p-l-0">
                                                    待开发
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2" ng-show="ctrl.data.bizType === 'sell'">
                                                    <select select-picker class="selectpicker show-menu-arrow form-control sel-news" name="housetailFirper" id="housetailFirper" ng-model="ctrl.data.downPayPer">
                                                        <option value="">首付比例</option>
                                                        <option value="10128">10%</option>
                                                        <option value="10129">20%</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix" id="fyHouseSettle">
                                                <label class="control-label">落户</label>
                                                <div class="col-lg-8 col-md-8 col-sm-8 form-inline m-t-7">
                                                    <div class="radio radio-nice" >
                                                        <input type="radio" name="houseSettle" id="settleYes" ng-value="1" ng-model="ctrl.data.settle"/>
                                                        <label for="settleYes">是</label>
                                                    </div>
                                                    <div class="radio radio-nice" >
                                                        <input type="radio" name="houseSettle" id="settleNo" ng-value="0" ng-model="ctrl.data.settle"/>
                                                        <label for="settleNo">否</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">业主</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <input type="text" name="houseOwname" reg="^\S+$" class="form-control" placeholder="业主姓名" ng-model="ctrl.data.owName"/>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">手机</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <input type="text" name="houseOwphone" class="form-control" placeholder="业主手机1" ng-model="ctrl.data.owPhone1" reg="^1\d{10}$|^\d{3}-\d{8}$|^\d{4}-\d{7}$|^0\d{11}$|^\d{4}-\d{8}$"/>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <input type="text" name="houseOwphone" class="form-control" placeholder="业主手机2" ng-model="ctrl.data.owPhone2" reg="^1\d{10}$|^\d{3}-\d{8}$|^\d{4}-\d{7}$|^0\d{11}$|^\d{4}-\d{8}$|^$"/>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <input type="text" name="houseOwphone" class="form-control" placeholder="业主手机3" ng-model="ctrl.data.owPhone3" reg="^1\d{10}$|^\d{3}-\d{8}$|^\d{4}-\d{7}$|^0\d{11}$|^\d{4}-\d{8}$|^$"/>
                                                </div>
                                            </div>
                                            <div class="form-group border0">
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
                                                <div class="pull-left col-lg-5 col-md-5 col-sm-5" id="tx3">
                                                    <div class="checkbox checkbox-nice checkbox-inline">
                                                        <input type="checkbox" name="houseCharacter" id="character10140" ng-model="ctrl.data.character.unique" ng-change="ctrl.log()"/>
                                                        <label for="character10140">唯一住房</label>
                                                    </div>
                                                    <div class="checkbox checkbox-nice checkbox-inline">
                                                        <input type="checkbox" name="houseCharacter" id="character10141" ng-model="ctrl.data.character.fiveYears" ng-change="ctrl.log()"/>
                                                        <label for="character10141">满五年</label>
                                                    </div>
                                                    <div class="checkbox checkbox-nice checkbox-inline">
                                                        <input type="checkbox" name="houseCharacter" id="character10142" ng-model="ctrl.data.character.twoYears" ng-change="ctrl.log()"/>
                                                        <label for="character10142">满两年</label>
                                                    </div>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select select-picker class="selectpicker show-menu-arrow form-control"  name="houseGrade" id="houseGrade">
                                                        <option value="">等级</option>
                                                        <option ng-repeat="grade in ctrl.gradeList" ng-value="grade.value">{{grade.name}}</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">委托</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select select-picker class="selectpicker show-menu-arrow form-control"  reg="^\S+$"  name="houseEntrustWay" id="houseEntrustWay" ng-model="ctrl.data.entrustWay"  ng-change="ctrl.log()">
                                                        <option value="">--请选择--</option>
                                                        <option ng-repeat="entrustWay in ctrl.entrustWayList" ng-value="entrustWay.value">{{entrustWay.name}}</option>
                                                    </select>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <div class="input-group date form_date" datetimepicker key="startDate" change="setDate">
                                                        <input class="form-control bgWhite" ng-model="ctrl.data.startDate" ng-change="ctrl.setBuildDate()" size="16" reg="^\d{4}-\d{2}-\d{2}$" type="text" placeholder="开始日期" name="houseStarttrudate" readonly/>
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                    </div>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <div class="input-group date form_date" datetimepicker key="endDate" change="setDate">
                                                        <input class="form-control bgWhite" size="16" type="text" placeholder="到期日期" name="houseEndtrudate" ng-model="ctrl.data.endDate" ng-change="ctrl.setBuildDate()"/>
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">看房</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select select-picker class="selectpicker show-menu-arrow form-control"  id="houseLook" ng-model="ctrl.data.look" name="houseLook" ng-change="ctrl.log()">
                                                        <option value="">--请选择--</option>
                                                        <option value="10184">预约</option>
                                                        <option value="10185">有钥</option>
                                                        <option value="10186">借钥</option>
                                                        <option value="10187">直接</option>
                                                    </select>
                                                </div>
                                                <#--<div class="col-lg-2 col-md-2 col-sm-2">
                                                    <!-- BUG #9269 新增钥匙号后，显示钥匙号，增加span标签 &ndash;&gt;
                                                    <span id="showHouseKey"></span>
                                                    <a onclick="addHousekey();" style="display:none;" id="yybh">钥匙编号</a>
                                                </div>-->
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">现状</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select select-picker class="selectpicker show-menu-arrow form-control"  name="presentSituation" id="presentSituation" ng-model="ctrl.data.presentSituation"  ng-change="ctrl.log()">
                                                        <option value="">--请选择--</option>
                                                        <option ng-value="10239">出售(空房)</option>
                                                        <option ng-value="10240">出售(业主住)</option>
                                                        <option ng-value="10241">出售(租客住)</option>
                                                        <option ng-value="10242">出售(空房)</option>
                                                        <option ng-value="10243">出租(租客住)</option>
                                                        <option ng-value="10244">自住</option>
                                                        <option ng-value="10245">全新</option>
                                                        <option ng-value="10246">未知</option>
                                                    </select>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select select-picker class="selectpicker show-menu-arrow form-control"  name="houseSource" id="houseSource" ng-model="ctrl.data.source" ng-change="ctrl.log()">
                                                        <option value="">来源</option>
                                                        <option value="10249">来电</option>
                                                        <option value="10250">来访</option>
                                                        <option value="10251">58同城</option>
                                                        <option value="10252">网络</option>
                                                        <option value="10253">搜房网</option>
                                                        <option value="10254">中介</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">证件</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select select-picker class="selectpicker show-menu-arrow form-control" name="houseProveType" id="houseProveType" ng-model="ctrl.data.proveType">
                                                        <option value="">--请选择--</option>
                                                        <option value="10163">房产证</option>
                                                        <option value="10164">购房合同</option>
                                                        <option value="10165">购房发票</option>
                                                        <option value="10166">抵押合同</option>
                                                        <option value="10167">认购书</option>
                                                        <option value="10168">预售合同</option>
                                                        <option value="10169">回迁协议</option>
                                                        <option value="10170">收件收据</option>
                                                        <option value="10171">未出证</option>
                                                    </select>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <div class="input-group date form_date" datetimepicker key="proveDate" change="setDate">
                                                        <input class="form-control " size="16" type="text" id="houseProveDate" ng-model="ctrl.data.proveDate" placeholder="出证日期" name="houseProveDate"/>
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                    </div>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select select-picker class="selectpicker show-menu-arrow form-control" name="housePType" id="housePType" ng-model="ctrl.data.pType" ng-change="ctrl.log()">
                                                        <option value="">产权类型</option>
                                                        <option ng-value="10189">经济适用房</option>
                                                        <option ng-value="10190">房改房</option>
                                                        <option ng-value="10191">商品房</option>
                                                        <option ng-value="10192">集体房</option>
                                                        <option ng-value="10193">限价房</option>
                                                        <option ng-value="10194">军产房</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">装修</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select select-picker class="selectpicker show-menu-arrow form-control" name="houseDecorate" id="houseDecorate" ng-model="ctrl.data.decorate" ng-change="ctrl.log()">
                                                        <option value="">--请选择--</option>
                                                        <option value="9898">毛坯</option>
                                                        <option value="9899">简装</option>
                                                        <option value="9900">精装</option>
                                                        <option value="9901">中装</option>
                                                        <option value="9902">豪装</option>
                                                        <option value="9903">清水</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">供暖类型</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select select-picker class="selectpicker show-menu-arrow form-control" name="houseHeating"  ng-model="ctrl.data.heating" ng-change="ctrl.log()">
                                                        <option value="">--请选择--</option>
                                                        <option value="9898">地暖</option>
                                                        <option value="9899">集中供暖</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">电梯</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select select-picker class="selectpicker show-menu-arrow form-control" name="houseLift"  ng-model="ctrl.data.lift" ng-change="ctrl.log()">
                                                        <option value="">--请选择--</option>
                                                        <option value="9898">有</option>
                                                        <option value="9899">无</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">税款</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select select-picker class="selectpicker show-menu-arrow form-control " name="houseTax" ng-model="ctrl.data.tax" ng-change="ctrl.log()">
                                                        <option value="">--请选择--</option>
                                                        <option value="10215">全包</option>
                                                        <option value="10216">包税费</option>
                                                        <option value="10217">不包</option>
                                                    </select>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select select-picker class="selectpicker show-menu-arrow form-control minusHeight" name="houseCommission" ng-model="ctrl.data.commission" ng-change="ctrl.log()">
                                                        <option value="">付佣</option>
                                                        <option value="10173">商议</option>
                                                        <option value="10174">全佣</option>
                                                        <option value="10175">九折佣</option>
                                                        <option value="10176">八折佣</option>
                                                        <option value="10177">七折佣</option>
                                                        <option value="10178">六折佣</option>
                                                        <option value="10179">五折佣</option>
                                                        <option value="10180">四折佣</option>
                                                        <option value="10181">三折佣</option>
                                                        <option value="10182">不给佣</option>
                                                    </select>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <input type="text" name="housePurchase" class="form-control" placeholder="原购价" ng-model="ctrl.data.purchase" ng-change="ctrl.log()"/>
                                                </div>
                                                <div class="pull-left m-t-7 p-l-0" id="yuangou">
                                                    元
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">备注</label>
                                                <div class="col-lg-9 col-md-9 col-sm-9">
                                                    <textarea name="houseRemark" cols="30" rows="3" class="form-control" ng-model="ctrl.data.remark" ng-change="ctrl.log()"></textarea>
                                                </div>
                                            </div>
                                            <div class="form-group border0">
                                                <div class="col-lg-12 col-md-12 col-sm-12">
                                                    <div class="btn_nav  pull-right">
                                                        <button type="button" class="prev btn btn-primary" ng-click="ctrl.prevStep()"><i class="fa fa-chevron-left"></i>上一步</button>
                                                        <button type="button" class="next btn btn-primary"><i class="fa fa-save"></i>保存</button>
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
                                      ng-class="{'active':shi.value == ctrl.currentLayout.shi}" ng-click="ctrl.setLayout('shi', shi.value)">{{shi.value?shi.name+'室':shi.name}}</span>
                            </div>
                        </div>
                        <div class="clearfix">
                            <label class="pull-left control-label">厅</label>
                            <div class="pull-left m-t-7">
                                <span ng-repeat="ting in ctrl.layoutList.ting" class="tip tip-info" value="{{ting.value}}"
                                      ng-class="{'active':ting.value == ctrl.currentLayout.ting}" ng-click="ctrl.setLayout('ting', ting.value)">{{ting.value? ting.name+'厅' :ting.name}}</span>
                            </div>
                        </div>
                        <div class="clearfix">
                            <label class="pull-left control-label">厨</label>
                            <div class="pull-left m-t-7">
                                <span ng-repeat="chu in ctrl.layoutList.chu" class="tip tip-info" value="{{chu.value}}"
                                      ng-class="{'active':chu.value == ctrl.currentLayout.chu}" ng-click="ctrl.setLayout('chu', chu.value)">{{chu.value?chu.name+'厨':chu.name}}</span>
                            </div>
                        </div>
                        <div class="clearfix">
                            <label class="pull-left control-label">卫</label>
                            <div class="pull-left m-t-7">
                                <span ng-repeat="wei in ctrl.layoutList.wei" class="tip tip-info" value="{{wei.value}}"
                                      ng-class="{'active':wei.value == ctrl.currentLayout.wei}" ng-click="ctrl.setLayout('wei', wei.value)">{{wei.value ? wei.name+'卫':wei.name}}</span>
                            </div>
                        </div>
                        <div class="clearfix">
                            <label class="pull-left control-label">阳台</label>
                            <div class="pull-left m-t-7">
                                <span ng-repeat="yangtai in ctrl.layoutList.yangtai" class="tip tip-info" value="{{yangtai.value}}"
                                      ng-class="{'active':yangtai.value == ctrl.currentLayout.yangtai}" ng-click="ctrl.setLayout('yangtai', yangtai.value)">{{yangtai.value? yangtai.name+'阳台':yangtai.name}}</span>
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
                    <h4 class="modal-title">户型</h4>
                </div>
                <div class="modal-body">
                    <form id="form" class="form-horizontal">
                        <input id="glBuildingId" name="glBuildingVO.glBuildingId" value="" type="hidden">
                        <input id="glEstateId" name="glBuildingVO.glEstateId" value="85718" type="hidden">
                        <input id="glBuildingVerify" name="glBuildingVO.glBuildingVerify" value="true" type="hidden">
                        <div class="form-group clearfix">
                            <label class="control-label">楼盘名称</label>
                            <div class="col-xs-8 mt7">艾东小区</div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label">楼盘地址</label>
                            <div class="col-xs-8 mt7"> 杨高南路2451弄</div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label">栋座名称</label>
                            <div class="col-xs-9">
                                <input id="glBuildingName" name="glBuildingVO.glBuildingName" reg="^\S+$" class="form-control" placeholder="栋座名称" type="text">
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label">栋座楼层</label>
                            <div class="col-xs-3">
                                <input id="glBuildingFloorall" name="glBuildingVO.glBuildingFloorall" reg="^\S+$" class="form-control" placeholder="总层" type="text">
                            </div>
                        </div>

                        <div class="form-group clearfix">
                            <label class="control-label">梯/户数</label>
                            <div class="col-xs-3">
                                <input id="glBuildingCountt" name="glBuildingVO.glBuildingCountt" class="form-control" placeholder="梯数" type="text">
                            </div>
                            <div class="col-xs-3">
                                <input id="glBuildingCounth" name="glBuildingVO.glBuildingCounth" class="form-control" placeholder="户数" type="text">
                            </div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label">描述说明</label>
                            <div class="col-xs-9">
                                <textarea id="glBuildingRemark" name="glBuildingVO.glBuildingRemark" cols="30" rows="5" class="form-control" placeholder="备注"></textarea>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addBuildModel" ng-click="ctrl.addBuildConfirm()">确认</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="addUnitModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">户型</h4>
                </div>
                <div class="modal-body">
                    <form id="form" class="form-horizontal">
                        <input id="glBuildingId" name="glBuildingVO.glBuildingId" value="588309" type="hidden">
                        <div class="form-group clearfix">
                            <label class="control-label">栋座名称</label>
                            <div class="col-xs-8 mt7">2号楼</div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label">单元名称</label>
                            <div class="col-xs-3">
                                <input name="glBuildingVO.buildingUnits" class="form-control" reg="^\S+$" placeholder="单元名称" is_tip_null="yes" type="text">
                            </div>
                            <div class="col-xs-1 mt7"><a class="text-red"><i class="fa fa-plus-circle"></i></a></div>
                        </div>
                        <div class="form-group clearfix">
                            <label class="control-label">单元名称</label>
                            <div class="col-xs-3">
                                <input name="glBuildingVO.buildingUnits" class="form-control" reg="^\S+$" placeholder="单元名称" is_tip_null="yes" type="text">
                            </div>
                            <div class="col-xs-1 mt7"><a class="text-red"><i class="fa fa-minus-circle"></i></a></div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addUnitModel" ng-click="ctrl.addUnitConfirm()">确认</button>
                </div>
            </div>
        </div>
    </div>
    </div>
<!-- /.content-wrapper -->

<#include "/common/footer.ftl" />
<script src="${contextPath!}/js/app/houseManage/addHouse.js?vn=${bts!}"></script>

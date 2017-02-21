<link href="${contextPath}/css/app/houseManage/index.css" rel="stylesheet">
<link href="${contextPath}/css/app/houseManage/addHouse.css" rel="stylesheet">
<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />
<div class="content-wrapper" id="addHouse" ng-controller="AddHouseCtrl as ctrl" ng-cloak>
        <style type="text/css">
            .tip.active{
                background-color:#169bd5;
                color:#fff;
            }
        </style>
        <section class="content-header">
            <ol class="breadcrumb">
                <li><i class="fa fa-home fa-lg"></i><a href="#">房源管理</a></li>
                <li class="active"><span>{{ctrl.desc.pageName}}</span></li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <!-- Small boxes (Stat box) -->
            <!-- Main row -->
            <div class="row animated fadeInRight">
                <div class="col-lg-12">
                    <div class="box box-solid">
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
                                        <!--<li><strong>3.</strong>勘察信息<span class="chevron"></span></li>-->
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
                                                    <select id="houseType" name="houseType" class="selectpicker show-menu-arrow form-control sel-news" ng-model="ctrl.data.type" ng-change="ctrl.log()">
                                                        <option value="">住宅类型</option>
                                                        <option ng-repeat="type in ctrl.typeList" repeat-done="ctrl.initSelectPicker('#houseType')" ng-value="type.value">{{type.name}}</option>
                                                    </select>
                                                </div>
                                                <div class="col-lg-4 col-md-4  col-sm-4 text-right">
                                                    <label class="mt7">编号：自动生成</label>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">物业地址</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select id="houseEstate" name="houseEstate" class="chosen-select" reg="^\S+$" ng-model="ctrl.data.estate" ng-change="ctrl.log()">
                                                        <option value="" >楼盘字典</option>
                                                    <#--<#list xiaoQuOptions?if_exists as xq>
                                                        <option type="radio" value="${xq.getXiaoQuId()}">${xq.getXiaoQuName()}</option>
                                                    </#list>-->
                                                     <option ng-repeat="estate in ctrl.houseEstateList" repeat-done="ctrl.initChosen('#houseEstate', 'estate')" ng-value="estate.value">{{estate.name}}</option>
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
                                                <div class="pull-left m-l-5">
                                                    <button type="button" class="btn btn-primary" id="changes" onclick="applyAddEstate();">申请添加楼盘</button>
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
                                                    <select id="houseConstruct" class="selectpicker show-menu-arrow form-control bs-select-hidden sel-news" name="fyHouseConstruct" ng-model="ctrl.data.construct">
                                                        <option value="">结构类型</option>
                                                        <option ng-repeat="construct in ctrl.houseConstructList" repeat-done="ctrl.initSelectPicker('#houseConstruct')" ng-value="construct.value">{{construct.name}}</option>
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
                                                        <a class="houseHold" href="#"  class="m-l-30"><i class="fa fa-th-list"></i>选择户型</a>
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">朝向</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select id="houseDirection" class="selectpicker show-menu-arrow form-control bs-select-hidden" name="fyHouseDirection" ng-model="ctrl.data.direction" ng-change="ctrl.log()">
                                                        <option value="">--请选择--</option>
                                                        <option ng-repeat="direction in ctrl.directionList" repeat-done="ctrl.initSelectPicker('#houseDirection')" ng-value="direction.value">{{direction.name}}</option>
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
                                                    <select class="selectpicker show-menu-arrow form-control sel-news" name="rentPriceUnit" id="rentPriceUnit" ng-model="ctrl.data.rentPriceUnit">
                                                        <option value="9929" info="ty51">元</option>
                                                        <option value="9930" info="ty52">元/天</option>
                                                        <option value="9931" info="ty53">元/月</option>
                                                        <option value="9932" info="ty54">元/季</option>
                                                        <option value="9933" info="ty55">元/年</option>
                                                    </select>
                                                </div>
                                                <div class="col-lg-1 col-md-2 col-sm-2" id="sale" ng-show="ctrl.data.bizType === 'sell'">
                                                    <select class="selectpicker show-menu-arrow form-control sel-news" id="sellPriceUnit" ng-model="ctrl.data.sellPriceUnit">
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
                                                    <select class="selectpicker show-menu-arrow form-control sel-news" name="housetailFirper" id="housetailFirper" ng-model="ctrl.data.downPayPer">
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
                                                        <input type="radio" name="houseSettle" ng-value="1" ng-model="ctrl.data.settle"/>
                                                        <label for="yes">是</label>
                                                    </div>
                                                    <div class="radio radio-nice" >
                                                        <input type="radio" name="houseSettle" ng-value="0" ng-model="ctrl.data.settle"/>
                                                        <label for="no">否</label>
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
                                                        <input type="checkbox" name="houseVO.fyHouseCharacter" value="10140" iteminfo="fy19"/>
                                                        <label for="10140">唯一住房</label>
                                                    </div>
                                                    <div class="checkbox checkbox-nice checkbox-inline">
                                                        <input type="checkbox" name="houseVO.fyHouseCharacter" value="10141" iteminfo="fy20"/>
                                                        <label for="10141">满五年</label>
                                                    </div>
                                                    <div class="checkbox checkbox-nice checkbox-inline">
                                                        <input type="checkbox" name="houseVO.fyHouseCharacter" value="10142" iteminfo="fy21"/>
                                                        <label for="10142">满两年</label>
                                                    </div>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select class="selectpicker show-menu-arrow form-control"  name="houseGrade" id="houseGrade">
                                                        <option value="">等级</option>
                                                        <option ng-repeat="grade in ctrl.gradeList" ng-value="grade.value" repeat-done="ctrl.initSelectPicker('#houseGrade')">{{grade.name}}</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">委托</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select class="selectpicker show-menu-arrow form-control"  reg="^\S+$"  name="houseEntrustWay" id="houseEntrustWay">
                                                        <option value="">--请选择--</option>
                                                        <option ng-repeat="entrustWay in ctrl.entrustWayList" ng-value="entrustWay.value" repeat-done="ctrl.initSelectPicker('#houseEntrustWay')">{{entrustWay.name}}</option>
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
                                                    <select class="selectpicker show-menu-arrow form-control bs-select-hidden" id="fyHouseLook" onchange="selectkey(this,'fy57')" name="houseVO.fyHouseLook" >
                                                        <option value="">--请选择--</option>
                                                        <option value="10184" iteminfo = "fy56">预约</option>
                                                        <option value="10185" iteminfo = "fy57">有钥</option>
                                                        <option value="10186" iteminfo = "fy58">借钥</option>
                                                        <option value="10187" iteminfo = "fy59">直接</option>
                                                    </select>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <!-- BUG #9269 新增钥匙号后，显示钥匙号，增加span标签 -->
                                                    <span id="showHouseKey"></span>
                                                    <a onclick="addHousekey();" style="display:none;" id="yybh">钥匙编号</a>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">现状</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select class="selectpicker show-menu-arrow form-control bs-select-hidden"  name="houseVO.fyHousePresentsituation" id="fyHousePresentsituation">
                                                        <option value="">--请选择--</option>
                                                        <option value="10239">出售(空房)</option>
                                                        <option value="10240">出售(业主住)</option>
                                                        <option value="10241">出售(租客住)</option>
                                                        <option value="10242">出售(空房)</option>
                                                        <option value="10243">出租(租客住)</option>
                                                        <option value="10244">自住</option>
                                                        <option value="10245">全新</option>
                                                        <option value="10246">未知</option>
                                                    </select>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select class="selectpicker show-menu-arrow form-control bs-select-hidden"  name="houseVO.fyHouseSource" id="fyHouseSource">
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
                                                    <select class="selectpicker show-menu-arrow form-control bs-select-hidden" name="houseVO.fyHouseProvetype" id="fyHouseProvetype">
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
                                                    <select class="selectpicker show-menu-arrow form-control bs-select-hidden"  name="houseVO.fyHousePtype" id="fyHousePtype">
                                                        <option value="">产权类型</option>
                                                        <option value="10189">经济适用房</option>
                                                        <option value="10190">房改房</option>
                                                        <option value="10191">商品房</option>
                                                        <option value="10192">集体房</option>
                                                        <option value="10193">限价房</option>
                                                        <option value="10194">军产房</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">装修</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select class="selectpicker show-menu-arrow form-control bs-select-hidden"  name="houseVO.fyHouseDecorate" id="fyHouseDecorate">
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
                                                <label class="control-label">税款</label>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select class="selectpicker show-menu-arrow form-control bs-select-hidden" name="houseVO.fyHouseTax" id="fyHouseTax">
                                                        <option value="">--请选择--</option>
                                                        <option value="10215">全包</option>
                                                        <option value="10216">包税费</option>
                                                        <option value="10217">不包</option>
                                                    </select>
                                                </div>
                                                <div class="col-lg-2 col-md-2 col-sm-2">
                                                    <select class="selectpicker show-menu-arrow form-control bs-select-hidden minusHeight" name="housetailVO.fyHousetailPaycommission" id="fyHousetailPaycommission">
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
                                                    <input type="text" name="housetailVO.fyHousetailPurchase" id="fyHousetailPurchase" class="form-control" placeholder="原购价" />
                                                </div>
                                                <div class="pull-left m-t-7 p-l-0" id="yuangou">
                                                    元
                                                </div>
                                            </div>
                                            <div class="form-group clearfix">
                                                <label class="control-label">备注</label>
                                                <div class="col-lg-9 col-md-9 col-sm-9">
                                                    <textarea name="housetailVO.fyHousetailRemark" id="fyHousetailRemark" cols="30" rows="3" class="form-control"></textarea>
                                                </div>
                                            </div>
                                            <div class="form-group border0">
                                                <div class="col-lg-12 col-md-12 col-sm-12">
                                                    <div class="btn_nav  pull-right">
                                                        <button type="button" class="prev btn btn-primary" ng-click="ctrl.prevStep()"><i class="fa fa-chevron-left"></i>上一步</button>
                                                        <button type="button" class="next btn btn-primary" onclick="savehouse()"><i class="fa fa-save"></i>保存</button>
                                                        <button type="button" class="btn btn-default" onclick="quxiao();"><i class="fa fa-times" style="border-color: #aaa"></i>取消</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!--<div class="page">
                                                <div class="form-group clearfix">
                                                    <label class="col-lg-2 control-label">配套</label>
                                                    <div class="col-lg-8 col-md-8">
                                                        <input type="text" id="fyHousetailMatch" name="housetailVO.fyHousetailMatch" class="form-control" placeholder="地下室、小房、、、" />
                                                    </div>
                                                </div>
                                                <div class="form-group clearfix">
                                                    <label class="col-lg-2 control-label">家具</label>
                                                    <div class="col-lg-8 col-md-8">
                                                        <input type="text" name="housetailVO.fyHousetailFurnit" class="form-control" placeholder="双人床1张、单人床2张、橱柜、餐桌、电脑桌、、、" />
                                                    </div>
                                                </div>
                                                <div class="form-group clearfix">
                                                    <label class="col-lg-2 control-label">家电</label>
                                                    <div class="col-lg-8 col-md-8">
                                                        <input type="text" name="housetailVO.fyHousetailElectri" class="form-control" placeholder="电视、冰箱、空调、微波炉、、、" />
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <h5>选择户型图</h5>
                                                    <div class="row">
                                                        <div class="col-lg-2 col-sm-6 col-md-3">
                                                            <div class="thumbnail">
                                                                <label class="control-label pull-right text-right" style="margin-bottom:5px">
                                                                    <input type="checkbox" name="r3" class="flat-red" checked/> 选择
                                                                </label>
                                                                <img src="../images/house.jpg">
                                                                <div class="caption clearfix">
                                                                    <div class="clearfix m-b-15">
                                                                        <span class="pull-left">2室2厅1卫2阳台</span>
                                                                        <span class="pull-right">128平米</span>
                                                                    </div>
                                                                    <label class="label label-success">住宅</label>
                                                                    <label class="label label-info">南北通透</label>
                                                                    <label class="label label-danger">车位</label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-2 col-sm-6 col-md-3">
                                                            <div class="thumbnail">
                                                                <label class="control-label pull-right text-right" style="margin-bottom:5px">
                                                                    <input type="checkbox" name="r3" class="flat-red" checked/> 选择
                                                                </label>
                                                                <img src="../images/house.jpg">
                                                                <div class="caption clearfix">
                                                                    <div class="clearfix m-b-15">
                                                                        <span class="pull-left">2室2厅1卫2阳台</span>
                                                                        <span class="pull-right">128平米</span>
                                                                    </div>
                                                                    <label class="label label-success">住宅</label>
                                                                    <label class="label label-info">南北通透</label>
                                                                    <label class="label label-danger">车位</label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-2 col-sm-6 col-md-3">
                                                            <div class="thumbnail">
                                                                <label class="control-label pull-right text-right" style="margin-bottom:5px">
                                                                    <input type="checkbox" name="r3" class="flat-red" checked/> 选择
                                                                </label>
                                                                <img src="../images/house.jpg">
                                                                <div class="caption clearfix">
                                                                    <div class="clearfix m-b-15">
                                                                        <span class="pull-left">2室2厅1卫2阳台</span>
                                                                        <span class="pull-right">128平米</span>
                                                                    </div>
                                                                    <label class="label label-success">住宅</label>
                                                                    <label class="label label-info">南北通透</label>
                                                                    <label class="label label-danger">车位</label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-2 col-sm-6 col-md-3">
                                                            <div class="thumbnail">
                                                                <label class="control-label pull-right text-right" style="margin-bottom:5px">
                                                                    <input type="checkbox" name="r3" class="flat-red" checked/> 选择
                                                                </label>
                                                                <img src="../images/house.jpg">
                                                                <div class="caption clearfix">
                                                                    <div class="clearfix m-b-15">
                                                                        <span class="pull-left">2室2厅1卫2阳台</span>
                                                                        <span class="pull-right">128平米</span>
                                                                    </div>
                                                                    <label class="label label-success">住宅</label>
                                                                    <label class="label label-info">南北通透</label>
                                                                    <label class="label label-danger">车位</label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-2 col-sm-6 col-md-3">
                                                            <div class="thumbnail">
                                                                <label class="control-label pull-right text-right" style="margin-bottom:5px">
                                                                    <input type="checkbox" name="r3" class="flat-red" checked/> 选择
                                                                </label>
                                                                <img src="../images/house.jpg">
                                                                <div class="caption clearfix">
                                                                    <div class="clearfix m-b-15">
                                                                        <span class="pull-left">2室2厅1卫2阳台</span>
                                                                        <span class="pull-right">128平米</span>
                                                                    </div>
                                                                    <label class="label label-success">住宅</label>
                                                                    <label class="label label-info">南北通透</label>
                                                                    <label class="label label-danger">车位</label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                </div>
                                            </div>
                                            <div class="form-group border0">
                                                <div class="col-lg-12">
                                                    <div class="btn_nav  pull-right">
                                                        <button type="button" class="prev btn btn-primary"><i class="iconfont">&#xe643;</i>上一步</button>
                                                        <button type="button" class="next btn btn-primary" onclick="savehouse()"><i class="icon iconfont">&#xe644;</i>保存</button>
                                                        <button type="button" class="btn btn-default"><i class="iconfont">&#xe641;</i>取消</button>
                                                    </div>
                                                </div>
                                            </div>
                                                        </div>-->
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- /.row (main row) -->
        </section>
        <!-- ./wrapper -->
        <div id="modal" class="tip-box form-horizontal" style="display:none;">
            <div class="clearfix">
                <label class="pull-left control-label">室</label>
                <div class="pull-left m-t-7">
                    <span class="tip tip-info fyHouseCountf" value="1">1室</span>
                    <span class="tip tip-info fyHouseCountf" value="2">2室</span>
                    <span class="tip tip-info fyHouseCountf" value="3">3室</span>
                    <span class="tip tip-info fyHouseCountf" value="4">4室</span>
                    <span class="tip tip-info fyHouseCountf" value="5">5室</span>
                    <span class="tip tip-info fyHouseCountf" value="6">6室</span>
                    <span class="tip tip-info fyHouseCountf" value="7">7室</span>
                </div>
            </div>
            <div class="clearfix">
                <label class="pull-left control-label">厅</label>
                <div class="pull-left m-t-7">
                    <span class="tip tip-info fyHousetailCountt" value="1">1厅</span>
                    <span class="tip tip-info fyHousetailCountt" value="2">2厅</span>
                    <span class="tip tip-info fyHousetailCountt" value="3">3厅</span>
                    <span class="tip tip-info fyHousetailCountt" value="4">4厅</span>
                    <span class="tip tip-info fyHousetailCountt" value="5">5厅</span>
                    <span class="tip tip-info fyHousetailCountt" value="6">6厅</span>
                    <span class="tip tip-info fyHousetailCountt" value="7">7厅</span>
                </div>
            </div>
            <div class="clearfix">
                <label class="pull-left control-label">卫</label>
                <div class="pull-left m-t-7">
                    <span class="tip tip-info fyHousetailCountw" value="1">1卫</span>
                    <span class="tip tip-info fyHousetailCountw" value="2">2卫</span>
                    <span class="tip tip-info fyHousetailCountw" value="3">3卫</span>
                    <span class="tip tip-info fyHousetailCountw" value="4">4卫</span>
                    <span class="tip tip-info fyHousetailCountw" value="5">5卫</span>
                    <span class="tip tip-info fyHousetailCountw" value="6">6卫</span>
                    <span class="tip tip-info fyHousetailCountw" value="7">7卫</span>
                </div>
            </div>
            <div class="clearfix">
                <label class="pull-left control-label">阳台</label>
                <div class="pull-left m-t-7">
                    <span class="tip tip-info fyHousetailCounty" value="1">1阳台</span>
                    <span class="tip tip-info fyHousetailCounty" value="2">2阳台</span>
                    <span class="tip tip-info fyHousetailCounty" value="3">3阳台</span>
                    <span class="tip tip-info fyHousetailCounty" value="4">4阳台</span>
                    <span class="tip tip-info fyHousetailCounty" value="5">5阳台</span>
                    <span class="tip tip-info fyHousetailCounty" value="6">6阳台</span>
                    <span class="tip tip-info fyHousetailCounty" value="7">7阳台</span>
                </div>
            </div>
        </div>
       <#-- <script>
            $(function() {
                $(".tip-box span").click(function(){
                    $(this).addClass("active").siblings().removeClass("active");
                });
                layer.ready(function() {
                    $(document).on('click', '.houseHold', function() {
                        var obj=$(this);
                        $(".tip-box span").removeClass("active");
                        layer.open({
                            type: 1,
                            title: '户型',
                            area: ['500px', '300px'],
                            content: $("#modal"),
                            btn: ['确定', '取消'],
                            closeBtn: 1,
                            shadeClose: true,
                            yes: function(index, layero){
                                obj.parent().find(".stwy").text($(".tip-box span.active").text());
                                obj.parent().find(".fyHouseCountf").val($(".tip-box span.active.fyHouseCountf").attr("value"));
                                obj.parent().find(".fyHousetailCountt").val($(".tip-box span.active.fyHousetailCountt").attr("value"));
                                obj.parent().find(".fyHousetailCountw").val($(".tip-box span.active.fyHousetailCountw").attr("value"));
                                obj.parent().find(".fyHousetailCounty").val($(".tip-box span.active.fyHousetailCounty").attr("value"));
                                obj.parent().find(".fyHousetailCharacter").val($(".tip-box span.active.fyHousetailCharacter").attr("value"));
                                layer.close(index);
                            }
                        });
                    });
                });
            });
        </script>-->
    </div>
<!-- /.content-wrapper -->

<#include "/common/footer.ftl" />
<script src="${contextPath!}/js/app/houseManage/addHouse.js?vn=${bts!}"></script>

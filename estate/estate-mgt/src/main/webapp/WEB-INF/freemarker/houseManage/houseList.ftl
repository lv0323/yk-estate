<link href="${contextPath}/css/app/houseManage/index.css" rel="stylesheet">

<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />

<!-- -->
<div class="content-wrapper" id="houseListWrapper" ng-controller="HouseListCtrl" ng-cloak>
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
                    <div class="box box-solid form-inline">
                        <div class="box-header with-border">
                            <h3 class="box-title pull-left">房源列表</h3>
                            <div class="box-tools">
                               <#-- <a class="btn"><i class="fa fa-plus" aria-hidden="true"></i>新增住宅</a>
                                <a class="btn"><i class="fa fa-plus" aria-hidden="true"></i>新增公寓</a>
                                <a class="btn"><i class="fa fa-plus" aria-hidden="true"></i>新增商铺</a>
                                <a class="btn"><i class="fa fa-plus" aria-hidden="true"></i>新增别墅</a>
                                <a class="btn"><i class="fa fa-plus" aria-hidden="true"></i>新增写字楼</a>
                                <a class="btn"><i class="fa fa-plus" aria-hidden="true"></i>新增车位</a>-->
                                <a class="btn" onclick="addHouse()"><i class="fa fa-plus" aria-hidden="true"></i>新增房源</a>
                                <a class="btn-collapse icon-down btn" ng-click="triggerCollapse()">
                                    <strong><i class="fa" ng-class="{true:'fa-chevron-up',false:'fa-chevron-down'}[state.collapse]" aria-hidden="true"></i>更多筛选</strong>
                                </a>
                            </div>
                        </div>
                        <div class="box-body clearfix no-padding">
                            <form id="formlist" class="form-inline">
                                <input id="fyHouseCityid" value="73" type="hidden">
                                <input id="fyHouseDsid" name="houseVO.fyHouseDsid" type="hidden">
                                <input id="fyHousePicid" name="houseVO.fyHousePicid" type="hidden">
                                <input id="fyHouseUse" name="houseVO.fyHouseUse" type="hidden">
                                <input id="fyHouseType" name="houseVO.fyHouseType" type="hidden">
                                <input id="fyHouseStatus" name="houseVO.fyHouseStatus" value="10222" type="hidden">
                                <input id="orderid" value="0" name="houseVO.orderid" type="hidden">
                                <input id="ordermark" name="houseVO.ordermark" type="hidden">
                                <div id="searchList" class="clearfix">
                                    <div class="col-lg-4 col-md-4 col-sm-4">
                                        <div class="input-group" style="width:100%;">
                                            <input placeholder="房源地址、业主姓名、业主电话、房源编号..." class="form-control" name="houseVO.fyHouseEstname" type="text">
    								<span class="input-group-btn">
    			                        <button type="button" class="btn btn-primary btn-sm"><i class="fa fa-search"></i>查询</button>
    			                    </span>
                                        </div>
                                    </div>
                                    <div class="col-lg-2 col-md-2 col-sm-2" style="position:realtive;">
                                        <a class="btn text-green" ng-click="getHouseCount = !getHouseCount" ng-init="getHouseCount=false">
                                            <i class="fa fa-bar-chart"></i>房源统计{{count}}
                                        </a>
                                        <div class="popover fade bottom in" ng-show="getHouseCount" style="min-width:120px;">
                                            <div class="arrow"></div>
                                            <div class="popover-content no-padding" id="getHouseCount">
                                                <table class="table table-striped">
                                                    <tbody>
                                                    <tr>
                                                        <td id="zongji"><span>房源总计：<strong></strong>条</span></td>
                                                    </tr>
                                                    <tr>
                                                        <td id="chushou"><span>出售总计：<strong class="text-danger"></strong>条</span></td>
                                                    </tr>
                                                    <tr>
                                                        <td id="chuzu"><span>出租总计：<strong class="text-success"></strong>条</span></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="pull-right" style="position:relative;" ng-if="false">
                                        <a class="btn" onclick="popverShow1(this);favoritesPage();"><i class="fa fa-star-o" aria-hidden="true"></i>我的收藏</a>
                                        <div class="popover fade bottom in" style="right:0px;">
                                            <div class="arrow"></div>
                                            <div class="popover-content no-padding" style="max-height: 450px; overflow: auto;">
                                                <div id="favoritesPage"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="clearfix"></div>
                                    <div class="collapse-box" ng-show="state.collapse">
                                        <div class="form-group sortlist">
                                            <label class="control-label" style="vertical-align:top;padding-top:5px;">区域</label>
                                            <div class="tj" id="distract">
                                                <a ng-href="javascript::" ng-repeat="distract in distractList" value="{{distract.value}}" ng-class="{'actived': distract.value == filter.distract}" ng-click="setDistract(distract.value )">
                                                    {{distract.name}}
                                                </a>
                                            </div>
                                            <div class="tj distract-box" id="picearea">
                                            </div>
                                        </div>
                                        <div class="form-group sortlist">
                                            <label class="control-label">用途</label>
                                            <div id="usage" class="tj">
                                                <a ng-href="javascript::" ng-repeat="usage in usageList" value="{{usage.value}}" ng-class="{'actived': usage.value == filter.usage}" ng-click="setUsage(usage.value)">
                                                    {{usage.name}}
                                                </a>
                                            </div>
                                            <div id="usageType" class="tj distract-box">
                                            </div>
                                        </div>
                                        <div class="form-group sortlist">
                                            <label class="control-label">面积</label>
                                            <div id="squareMetre" class="tj">
                                                <a ng-href="javascript:void(0)" ng-class="{'actived':filter.area.type == ''}" ng-click="setAreaType('')">不限</a>
                                                <a ng-href="javascript:void(0)" ng-class="{'actived':filter.area.type == 'custom'}" ng-click="setAreaType('custom')">自定义</a>
                                                <input class="form-control" id="minSqure" value="" name="houseVO.minSqure" style="width:100px;">~ <input class="form-control" id="maxSqure" value="" name="houseVO.maxSqure" style="width:100px;">
                                            </div>
                                        </div>
                                        <div class="form-group sortlist">
                                            <label class="control-label">户型</label>
                                            <div id="countf" class="tj">
                                                <a ng-href="javascript::" ng-repeat="countf in countfList" value="{{countf.value}}" ng-class="{'actived': countf.value == filter.countf}" ng-click="setCountf(countf.value)">
                                                    {{countf.name}}
                                                </a>
                                                <#--<input id="fyHouseCountfmin" class="form-control" style="width:100px;" name="houseVO.fyHouseCountfmin" value="">
                                                ~
                                                <input id="fyHouseCountfmax" class="form-control" style="width:100px;" name="houseVO.fyHouseCountfmax" value="">-->
                                            </div>
                                        </div>
                                        <div class="form-group sortlist">
                                            <label class="control-label">状态</label>
                                            <div id="houseTradeStatus" class="tj">
                                                <a ng-href="javascript::" ng-repeat="tradeStatus in tradeStatusList" value="{{tradeStatus.value}}" ng-class="{'actived': tradeStatus.value == filter.tradeStatus}" ng-click="setTradeStatus(tradeStatus.value)">
                                                    {{tradeStatus.name}}
                                                </a>
                                            </div>
                                        </div>
                                        <div class="form-group sortlist">
                                            <label class="control-label">特性</label>
                                            <div class="pull-left">
                                                <div class="radio radio-inline" style="padding-top:0px">
                                                    <span ng-repeat="quality in qualityList">
                                                        <input name="houseVO.fyHouseQuali" ng-value="quality.value" id="qualityItem{{$index}}" ng-model="filter.quality" ng-click="setQuality(quality.value)" ng-checked="quality.value == filter.quality" type="radio">
                                                        <label for="qualityItem{{$index}}"> {{quality.name}}</label>
                                                    </span>
                                                </div>
                                            </div>
                                            <span class="opt-gap pull-left" style="margin-top:3px;"></span>
                                            <div id="fyHouseCharacter" class="tj">
                                                <div class="checkbox checkbox-nice" ng-repeat="character in characterList">
                                                    <input name="houseVO.fyHouseCharacter" id="character{{character.value}}" ng-model="filter.character[character.key]" ng-value="{{character.value}}" ng-click="setCharacter();" type="checkbox">
                                                    <label for="character{{character.value}}">{{character.name}}</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group sortlist">
                                            <label class="control-label">筛选</label>
                                            <div class="col-lg-2 col-md-2 col-sm-3">
                                                <select name="houseVO.fyHouseTratype" id="fyHouseTratype" class="selectpicker show-menu-arrow form-control bs-select-hidden" ng-model="filter.tradeType" ng-change="setTradeType(e);">
                                                    <option value="">--请选择--</option>
                                                    <option value="10206">出租</option>
                                                    <option value="10207">出售</option>
                                                </select>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3">
                                                <select id="fyHouseDepid" class="chosen-select-dep" data-placeholder="选择部门"  ng-model="filter.DepId" ng-change="setDepId(e);">
                                                    <option ng-repeat="dep in depList" value="dep.value" repeat-done="initDepList()">{{dep.name}}</option>
                                                    <#--<option value="" header="QBBM">全部部门</option>
                                                    <option value="126" header="RDSFC">瑞迪斯房产</option>
                                                    <option value="157" header="CW">　财务</option>
                                                    <option value="128" header="TZYYB">　　唐镇营业部</option>
                                                    <option value="127" header="CSYYB">　　　川沙营业部</option>
                                                    <option value="182" header="CS2">　　　　川沙2</option>--></select>
                                                </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3">
                                                <select name="houseVO.fyHouseEmpid" id="fyHouseEmpid" class="chosen-select-emp">
                                                    <option value="" header="QBYG">全部员工</option>
                                                </select>
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-sm-3 m-t-7">
                                                <div class="checkbox checkbox-nice" ng-repeat="depExp in depExpList">
                                                    <input name="depExp" id="depExp{{depExp.value}}" ng-model="filter.depExp[depExp.key]" ng-value="{{depExp.value}}" ng-click="setDepExp()" type="checkbox">
                                                    <label for="depExp{{depExp.value}}">{{depExp.name}}</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group sortlist form-inline">
                                            <label class="control-label"></label>
                                            <div class="col-lg-2 col-md-2 col-sm-3">
                                                <select class="select_1 selectpicker show-menu-arrow form-control bs-select-hidden" id="dateType" name="houseVO.dateType" ng-model="filter.date.dateType" ng-change="setDateType(e)">
                                                    <option value="">--请选择--</option>
                                                    <option value="1">委托日期 </option>
                                                    <option value="2">录入日期 </option>
                                                    <option value="3">最后跟进日 </option>
                                                    <option value="4">勘察日期 </option>
                                                </select>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3">
                                                <div class="input-group date form_date" datetimepicker key="startDate" change="setDate">
                                                    <input class="form-control" size="16" placeholder="开始日期" type="text" ng-model="filter.date.startDate" ng-change="setDateType()" >
                                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                                </div>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3">
                                                <div class="input-group date form_date" datetimepicker key="endDate" change="setDate">
                                                    <input class="form-control" size="16" placeholder="结束时间" type="text" ng-model="filter.date.endDate" ng-change="setDateType()">
                                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                                </div>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3">
                                                <select class="xuanxiang select_1 selectpicker show-menu-arrow form-control bs-select-hidden" ng-model='filter.xuanxiang' ng-change="setXuanxiang()" name="houseVO.otherType" style="padding:2px 0px;width:60px;">
                                                    <option value="">--请选择--</option>
                                                    <option value="entrustWay">委托</option>
                                                    <!--<option value="price">价格</option>-->
                                                    <option value="grade">等级</option>
                                                    <option value="decorate">装修</option>
                                                    <!--<option value="imageBool">照片</option>-->
                                                    <option value="pType">产权</option>
                                                    <option value="proveType">证件</option>
                                                    <option value="settle">落户</option>
                                                </select>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3 sm-mt10" ng-if="filter.xuanxiang === 'entrustWay'">
                                                <select class="selected_1 selectpicker show-menu-arrow form-control bs-select-hidden fyHouseEntrustway" id="fyHouseEntrustway" name="houseVO.fyHouseEntrustway" ng-model="filter.xuanxiangExp.entrustWay">
                                                    <option value="">--请选择--</option>
                                                    <option value="9876">独家</option>
                                                    <option value="9877">签约</option>
                                                    <option value="9878">未签</option>
                                                    <option value="9967">限时</option>
                                                    <option value="9969">托管</option>
                                                </select>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3 sm-mt10" ng-show="filter.xuanxiang === 'price'">
                                                <select class="selected_1 selectpicker show-menu-arrow form-control bs-select-hidden fyHousePrice" style="padding:2px 0px;width:60px;" name="houseVO.fyHousePrice" ng-model="filter.xuanxiangExp.price">
                                                    <option value="">--请选择--</option>
                                                    <option value="1">涨价 </option>
                                                    <option value="2">降价 </option>
                                                </select>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3 sm-mt10" ng-show="filter.xuanxiang === 'grade'">
                                                <select class="selected_1 selectpicker show-menu-arrow form-control bs-select-hidden fyHouseGrade" id="fyHouseGrade" style="padding:2px 0px;width:60px;" name="houseVO.fyHouseGrade" ng-model="filter.xuanxiangExp.grade">
                                                    <option value="">--请选择--</option>
                                                    <option value="9872">A级</option>
                                                    <option value="9873">B级</option>
                                                    <option value="9874">C级</option>
                                                </select>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3 sm-mt10" ng-show="filter.xuanxiang === 'decorate'">
                                                <select class="selected_1 selectpicker show-menu-arrow form-control bs-select-hidden fyHouseDecorate" style="padding:2px 0px;width:60px;" id="fyHouseDecorate" name="houseVO.fyHouseDecorate" ng-model="filter.xuanxiangExp.decorate">
                                                    <option value="">--请选择--</option>
                                                    <option value="9898">毛坯</option>
                                                    <option value="9899">简装</option>
                                                    <option value="9900">精装</option>
                                                    <option value="9901">中装</option>
                                                    <option value="9902">豪装</option>
                                                    <option value="9903">清水</option>
                                                </select>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3 sm-mt10" ng-show="filter.xuanxiang === 'imageBool'">
                                                <select class="selected_1 selectpicker show-menu-arrow form-control bs-select-hidden imageBool" style="padding:2px 0px;width:60px;" name="houseVO.imageBool" ng-model="filter.xuanxiangExp.imageBool">
                                                    <option value="">--请选择--</option>
                                                    <option value="1">有 </option>
                                                    <option value="2">无 </option>
                                                </select>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3 sm-mt10" ng-show="filter.xuanxiang === 'pType'">
                                                <select class="selected_1 selectpicker show-menu-arrow form-control bs-select-hidden fyHousePtype" id="fyHousePtype" style="padding:2px 0px;width:60px;" name="houseVO.fyHousePtype" ng-model="filter.xuanxiangExp.pType">
                                                    <option value="">--请选择--</option>
                                                    <option value="10189">经济适用房</option>
                                                    <option value="10190">房改房</option>
                                                    <option value="10191">商品房</option>
                                                    <option value="10192">集体房</option>
                                                    <option value="10193">限价房</option>
                                                    <option value="10194">军产房</option>
                                                </select>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3 sm-mt10" ng-show="filter.xuanxiang === 'proveType'">
                                                <select class="selected_1 selectpicker show-menu-arrow form-control bs-select-hidden fyHouseProvetype" style="padding:2px 0px;width:60px;" id="fyHouseProvetype" name="houseVO.fyHouseProvetype" ng-model="filter.xuanxiangExp.proveType">
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
                                            <div class="col-lg-2 col-md-2 col-sm-3 sm-mt10" ng-show="filter.xuanxiang === 'settle'">
                                                <select class="selected_1 selectpicker show-menu-arrow form-control bs-select-hidden fyHouseSettle" style="padding:2px 0px;width:60px;" name="houseVO.fyHouseSettle" ng-model="filter.xuanxiangExp.settle">
                                                    <option value="">--请选择--</option>
                                                    <option value="1">是</option>
                                                    <option value="0">否</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group sortlist">
                                        <label class="control-label">排序</label>
                                        <div class="tj" id="fySortlist">
                                            <a ng-repeat="sort in fySortlist" ng-href="javascript:;" ng-click="setSort(sort.value)" ng-class="{'actived': sort.value == filter.sort.item}">
                                                {{sort.name}}
                                                <i ng-if="sort.value == filter.sort.item" class="fa" ng-class="{'fa-arrow-down':filter.sort.type == 'DESC','fa-arrow-up':filter.sort.type == 'ASC' }"></i></a>
                                        </div>
                                    </div>
                                </div>
                                <div id="getHouseCharPage" class="table-responsive clearfix">
                                    <!-- 重复提交标识 -->
                                    <input id="repeatflag" value="false" type="hidden">
                                    <input value="http://img.12157.top/upload/" id="uploadfileServerURL" type="hidden">
                                    <div class="media clearfix house-item" ng-repeat="house in houseList">
                                        <img class="flagImg" ng-src="{{house.bizType ==='rent' ? '../img/house/rent.png':'../img/house/sale.png'}}" height="55" width="55">
                                        <a class="pull-left" href="javascript:void(0)" onclick="houseItem('1639')">
                                            <img class="media-object" ng-src="{{house.houseImg}}" height="75px" width="100px">
                                        </a>
                                        <div class="media-body">
                                            <div class="col-lg-8 col-md-8 col-sm-9">
                                                <div class="clearfix">
                                                    <h5 class="media-heading pull-left text-ellipsis" style="width:320px;">
                                                        <a href="javascript:void(0)" onclick="houseItem('1639')" class="text-muted">
                                                            <!--BUG #11195 房源列表问题 -->
                                                            浦东 川沙 上浦小区 1 1 201
                                                        </a>
                                                    </h5>
                                                    <label class="badge badge-success pull-left m-l-20">有效</label>
                                                    <i class="fa fa-circle text-danger m-l-20" style="font-size:16px;"></i>
                                                    [<span class="text-danger">53</span>]<span class="text-muted">2016-12-26 <span class="opt-gap"></span>川沙2 ~ 张三</span>
                                                </div>
                                                <div class="clearfix m-t-10 text-muted">
                                                    <span>RDSFC000001</span>
                                                    <span class="m-l-10">3室4厅2卫2阳台</span>
                                                    <span class="m-l-10">2/6F</span>
                                                    <span class="m-l-10">南</span>
                                                    <span class="m-l-10">简装</span>
                                                    <span class="m-l-10">2016-12-12</span>
                                                </div>
                                                <div class="clearfix m-t-10">
                                                    <div class="pull-left">
                                                        <span class="tip tip-success no-margin">公盘</span>
                                                        <span class="tip tip-danger no-margin">普通房</span>
                                                        <span class="tip tip-warning no-margin">有钥</span>
                                                        <span class="tip tip-default no-margin">不包</span>
                                                    </div>
                                                    <div class="btn-add  pull-left m-l-30">
                                                        <a onclick="saveHouseFollow(1639)" href="javascript:void(0)"><i class="fa fa-pencil"></i>新增跟进</a>
                                                        <a class="m-l-20" onclick="checkAlike(1639);" href="javascript:void(0)"><i class="fa fa-pencil"></i>新增勘察</a>
                                                        <a class="m-l-20" onclick="checkApplyReceive('1639','','','')" href="javascript:void(0)"><i class="fa fa-pencil"></i>申请转盘</a>
                                                        <a class="m-l-20" onclick="checkDelHouse('1639')" href="javascript:void(0)"><i class="fa fa-pencil"></i>删除</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-2 col-md-2 hidden-sm">
                                                <strong class="f18">74.00</strong>m<sup>2</sup><br><br>
                                                <span class="text-muted">0.00m<sup>2</sup></span>
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-3 text-right">
                                                <p><strong class="text-danger f18">199.00</strong>万元</p>
                                                <p>26891.89元/m<sup>2</sup></p>
                                                <a href="javascript:void(0)" id="favorites" style="color:#777;" onclick="favoritesLookOwner('1639','')" class="text-muted mr10 collection ">
                                                    <i class="fa fa-star-o" aria-hidden="true"></i>收藏
                                                </a>
                                                <div class="checkbox checkbox-nice">
                                                    <input id="house_1639" onclick="setHouseSelected(1639);" type="checkbox">
                                                    <label for="house_1639" style="padding-right:0px">选择</label>
                                                </div>
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
<script src="${contextPath!}/js/app/houseManage/houseList.js?vn=${bts!}"></script>
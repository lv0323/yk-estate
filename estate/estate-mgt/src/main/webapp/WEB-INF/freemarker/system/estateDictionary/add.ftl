<link href="${contextPath}/css/app/org/orgnization.css" rel="stylesheet">

<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />

<div class="content-wrapper" id="dictAddCtrlWrapper" ng-controller="DictAddCtrl">
    <section class="content-header">
        <ol class="breadcrumb">
            <li>
                <a href="#">
                    <i class="fa fa-home fa-lg" aria-hidden="true"></i>
                    系统设置
                </a>
            </li>
            <li>
                <a href="#">
                    楼盘字典
                </a>
            </li>
            <li class="active">
                创建楼盘
            </li>
        </ol>
    </section>

    <!--Main content -->
    <section class="content">
        <div class="animated fadeInRight">
            <div class="row">
                <div class="col-lg-12">
                    <section class="connectedSortable">
                        <div class="box box-solid">
                            <div class="box-header">
                                <h3 class="box-title">创建楼盘</h3>
                            </div>
                            <div class="box-body modal-content">
                                <form id="addDepartForm" class="form-horizontal" method="post">
                                    <div class="form-group clearfix">
                                        <label class="control-label">楼盘名字</label>
                                        <div class="col-lg-6 col-md-6 col-sm-6">
                                            <input type="text" id="communityName" reg="^\S+$" class="form-control"
                                                   required ng-model="data.name" required/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">楼盘地址</label>
                                        <div class="col-lg-2 col-md-2 col-sm-2">
                                            <select id="departCid" class="form-control"> <#--selectpicker show-menu-arrow bs-select-hidden-->
                                                <option value="">请选择</option>
                                                <option ng-value="city.id" ng-repeat="city in location.cityList" repeat-done="initChosen('#departCid', 'departCid')">{{city.name}}</option>
                                            </select>
                                        </div>
                                        <div class="col-lg-2 col-md-2 col-sm-2">
                                            <select id="departDid" class="form-control"> <#--form-control btn-group dropup-->
                                                <option value="">请选择</option>
                                                <option ng-value="district.id" ng-repeat="district in location.districtList" repeat-done="initChosen('#departDid', 'departDid')">{{district.name}}</option>
                                            </select>
                                        </div>
                                        <div class="col-lg-2 col-md-2 col-sm-2">
                                            <select id="departSDid" class="form-control">
                                                <option value="">请选择</option>
                                                <option ng-value="subDistrict.id" ng-repeat="subDistrict in location.subDistrictList" repeat-done="initChosen('#departSDid', 'departSDid')">{{subDistrict.name}}</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label" style="visibility: hidden"></label>
                                        <div class="col-lg-6 col-md-6 col-sm-6">
                                            <div class="input-group" style="margin-top: 10px;">
                                                <input id="communityAddress" ng-model="data.address" class="form-control" type="text" placeholder="请输入详细地址">
                                                <span class="input-group-btn">
                                                    <button type="button" class="btn btn-primary btn-sm" ng-click="checkAddress(data.address)"><i class="fa fa-search"></i>查询</button>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div id="l-map" class="col-lg-7 col-md-7 col-sm-7"  baidu-map="${mapKey!}" map-ready="mapReady(map)" style="margin-top: 10px;height: 400px">

                                        </div>
                                    </div>
                                </form>
                                <div class="modal-footer center">
                                    <div class="col-lg-7 col-md-7 col-sm-7">
                                        <button type="button" class="btn btn-primary" ng-click="confirmAddDepart()">保存</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </section>
</div>



<#include "/common/footer.ftl" />
<script src="${contextPath!}/js/app/system/estateDictionary/add.js"></script>


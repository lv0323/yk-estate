<link href="${contextPath}/css/app/propertyVisit/propertyVisit.css" rel="stylesheet">
<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <section class="content-header">
        <ol class="breadcrumb">
            <li>
                <a href="#">
                    <i class="fa fa-home fa-lg" aria-hidden="true"></i>
                    合同管理
                </a>
            </li>
            <li>
                <a href="#">
                    成交管理
                </a>
            </li>
            <li class="active">
                新增成交
            </li>
        </ol>
    </section>
    <section class="content">
        <div class="animated fadeInRight">
            <div class="row">
                <div class="col-lg-12">
                    <section class="connectedSortable">
                        <div class="box box-solid">
                            <div class="box-header">
                                <h3 class="box-title">新增成交</h3>
                            </div>
                            <div class="box-body">
                                <div class="page">
                                    <form class="form-horizontal">
                                        <div class="form-group clearfix">
                                            <label class="pull-left control-label">授权编号</label>
                                            <div class="col-sm-5">
                                                <div class="input-group">
                                                    <input id="houseLicenceID" type="text" class="form-control" placeholder="成交房源授权编号">
                                                    <input id="fangID" type="text" class="hidden">
                                                    <span class="input-group-btn">
                                                        <a id="getHouseInfoBtn" class="btn btn-primary">
                                                            <i class="fa fa-home fa-lg" aria-hidden="true"></i>获取房源信息
                                                        </a>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="pull-left control-label">证件地址</label>
                                            <div class="col-sm-6">
                                                <input id="certifAddress" class="form-control" placeholder="房产证地址" type="text">
                                            </div>
                                            <div class="col-sm-2">
                                                <input id="certifNo" class="form-control" placeholder="证件编号" type="text">
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="pull-left control-label">物业信息</label>
                                            <div class="col-sm-2">
                                                <div class="input-group">
                                                    <input id="areaSize" type="text" class="form-control" placeholder="建筑面积">
                                                    <div class="input-group-addon">㎡</div>
                                                </div>
                                            </div>
                                            <div class="col-lg-3 col-md-4 col-sm-4">
                                                <div class="input-group">
                                                    <div class="input-group-addon">总价</div>
                                                    <input id="totalPrice" type="text" name="publishPrice" required class="form-control" placeholder="总价" reg="/^(?!0+(?:\.0+)?$)(?:[1-9]\d{0,8}|0)(?:\.\d{1,2})?$/"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <select id="priceUnit" class="btn-group dropup form-control">
                                                <#list priceUnit?if_exists as unit>
                                                    <option value="${unit.name()}">${unit.getLabel()}</option>
                                                </#list>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="pull-left control-label">房屋用途</label>
                                            <div class="col-sm-3">
                                                <select id="houseType" class="btn-group dropup form-control">
                                                <#list houseType?if_exists as type>
                                                    <option value="${type.name()}">${type.getLabel()}</option>
                                                </#list>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="pull-left control-label">成交类型</label>
                                            <div class="col-sm-3">
                                                <select id="businessType" class="btn-group dropup form-control">
                                                <#list bizType?if_exists as biz>
                                                    <option value="${biz.name()}">${biz.getLabel()}</option>
                                                </#list>
                                                </select>
                                            </div>
                                        </div>
                                        <hr>
                                        <h4 class="text-blue">业主信息</h4>
                                        <div class="form-group clearfix">
                                            <label class="pull-left control-label">业主姓名</label>
                                            <div class="col-sm-3">
                                                <input id="OwnerName" type="text" class="form-control" placeholder="业主姓名">
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="pull-left control-label">证件信息</label>
                                            <div class="col-sm-3">
                                                <select id="OwnerIdSource" class="btn-group dropup form-control">
                                                <#list identitySource ?if_exists as source>
                                                    <option value="${source.name()}">${source.getLabel()}</option>
                                                </#list>
                                                </select>
                                            </div>
                                            <div class="col-sm-4">
                                                <input id="OwnerIdNo" type="text" class="form-control" placeholder="证件号码">
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="pull-left control-label">手机</label>
                                            <div class="col-sm-3">
                                                <input id="OwnerContact" type="text" class="form-control" placeholder="手机">
                                            </div>
                                        </div>
                                        <hr>
                                        <h4 class="text-blue">客户信息</h4>
                                        <div class="form-group clearfix">
                                            <label class="pull-left control-label">客户姓名</label>
                                            <div class="col-sm-3">
                                                <input id="customerName" type="text" class="form-control" placeholder="客户姓名">
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="pull-left control-label">证件信息</label>
                                            <div class="col-sm-3">
                                                <select id="customerIdSource" class="btn-group dropup form-control">
                                                <#list identitySource ?if_exists as source>
                                                    <option value="${source.name()}">${source.getLabel()}</option>
                                                </#list>
                                                </select>
                                            </div>
                                            <div class="col-sm-4">
                                                <input id="customerIdNo" type="text" class="form-control" placeholder="证件号码">
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="pull-left control-label">手机</label>
                                            <div class="col-sm-3">
                                                <input id="customerContact" type="text" class="form-control" placeholder="手机">
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="form-group clearfix">
                                            <label class="pull-left control-label">备注</label>
                                            <div class="col-sm-10">
                                                <textarea id="note" class="form-control noresize" placeholder="填写备注" cols="20" rows="4"></textarea>
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="form-group center">
                                            <div class="col-sm-12">
                                                <button type="button" class="btn btn-primary" id="confirmAddDealBtn">保存</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </section>
</div>
<!-- /.content-wrapper -->
<#include "/common/footer.ftl" />
<script src="${contextPath!}/js/app/contract/deal/addDeal.js"></script>

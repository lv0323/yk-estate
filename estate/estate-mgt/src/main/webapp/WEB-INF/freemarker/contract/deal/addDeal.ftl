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
                                                    <input id="houseLicenceID" type="text" class="form-control" placeholder="带看房源授权编号">
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
                                            <label class="pull-left control-label">物业地址</label>
                                            <div class="col-lg-3 col-md-3 col-sm-3">
                                                <input id="houseDict" type="text" class="form-control" placeholder="楼盘字典">
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-2">
                                                <input id="building" type="text" class="form-control" placeholder="栋座">
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-2">
                                                <input id="unit" type="text" class="form-control" placeholder="单元">
                                            </div>
                                            <div class="col-lg-2 col-md-2 col-sm-2">
                                                <div class="input-group">
                                                    <input id="roomNo" type="text" class="form-control" placeholder="房号">
                                                    <div class="input-group-addon">号房</div>
                                                </div>
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
                                                    <div id="totalPriceUnit" class="input-group-addon">元</div>
                                                </div>
                                            </div>
                                            <div class="col-lg-3 col-md-4 col-sm-4">
                                                <div class="input-group">
                                                    <div class="input-group-addon">单价</div>
                                                    <input id="unitPrice" type="text" class="form-control" placeholder="单价" reg="/^(?!0+(?:\.0+)?$)(?:[1-9]\d{0,8}|0)(?:\.\d{1,2})?$/"/>
                                                    <div class="input-group-addon">元/㎡</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="pull-left control-label">户型</label>
                                            <div class="col-sm-2">
                                                <div class="input-group">
                                                    <input id="bedRoom" type="text" class="form-control" placeholder="室">
                                                    <div class="input-group-addon">室</div>
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="input-group">
                                                    <input id="livingRoom" type="text" class="form-control" placeholder="厅">
                                                    <div class="input-group-addon">厅</div>
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="input-group">
                                                    <input id="kitchen" type="text" class="form-control" placeholder="厨">
                                                    <div class="input-group-addon">厨</div>
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="input-group">
                                                    <input id="bathRoom" type="text" class="form-control" placeholder="卫">
                                                    <div class="input-group-addon">卫</div>
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="input-group">
                                                    <input id="balcony" type="text" class="form-control" placeholder="阳台">
                                                    <div class="input-group-addon">阳台</div>
                                                </div>
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
                                            <label class="pull-left control-label">客源来源</label>
                                            <div class="col-sm-3">
                                                <select id="customerSource" class="btn-group dropup form-control">
                                                <#list customerSource ?if_exists as source>
                                                    <option value="${source.name()}">${source.getLabel()}</option>
                                                </#list>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <label class="pull-left control-label">联系电话</label>
                                            <div class="col-sm-3">
                                                <input id="customerContact" type="text" class="form-control" placeholder="联系电话">
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="form-group center">
                                            <div class="col-sm-12">
                                                <button type="button" class="btn btn-primary" id="confirmAddPropertyVisitBtn">保存</button>
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
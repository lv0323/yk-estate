<link href="${contextPath}/css/app/fang/index.css" rel="stylesheet">

<#include "/common/header.ftl" />
<#include "/common/sidebar.ftl" />

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper" id="regionAgentListWrapper" ng-controller="RegionAgentListCtrl">
    <section class="content-header">
        <ol class="breadcrumb">
            <li>
                <a href="#">
                    <i class="fa fa-home fa-lg" aria-hidden="true"></i>
                    加盟商管理
                </a>
            </li>
            <li class="active">
                区域代理管理
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
                                <h3 class="box-title">区域代理列表</h3>
                                <div class="box-tools">
                                    <a class="btn" id="addPartnerBtn" ng-href="/mgt/franchisee/regionAgent/createRegionAgent?target=.franchisee">
                                        <i class="fa fa-plus" aria-hidden="true"></i>
                                        新增区域代理
                                    </a>
                                    <span class="opt-gap"></span>
                                    <a class="btn-collapse icon-down btn" ng-click="triggerCollapse()">
                                        <strong><i class="fa" ng-class="{true:'fa-chevron-up',false:'fa-chevron-down'}[page.collapse]" aria-hidden="true"></i>筛选</strong>
                                    </a>
                                </div>
                            </div>

                            <div class="box-body">
                                <form class="form-inline">
                                    <div id="searchList" ng-cloak class="clearfix" ng-show="page.collapse">
                                        <div class="collapse-box">
                                            <div class="form-group sortlist">
                                                <label class="control-label">筛选</label>
                                                <div class="col-lg-2 col-md-2 col-sm-3">
                                                    <select id="cityListDrop" class="chosen-select-dep">
                                                        <option value="">选择城市</option>
                                                        <option ng-repeat="city in cityList" ng-value="city.id" repeat-done="initChosen('#cityListDrop', 'cityId')">{{city.name}}</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div id="PartnerList" class="table-responsive clearfix" ng-cloak>
                                        <div class="media clearfix" ng-repeat="partner in regionAgentList">
                                            <div class="media-body">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="pagination-container">
                                        <ul id="PartnerList_paging" class="pagination"></ul>
                                    </div>
                                </form>
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
<script src="${contextPath!}/js/app/franchisee/regionAgent/regionAgent.js"></script>
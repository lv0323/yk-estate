<#include "/common/header.ftl" />
<link rel="stylesheet" href="${contextPath}/css/plugins/zTree/iconSkin.css">
<link rel="stylesheet" href="${contextPath}/css/app/system/authorityConfig/index.css">
<#include "/common/sidebar.ftl" />
<div id="authorityConfig" class="content-wrapper" ng-controller="AuthorityCtrl as ctrl">
    <section class="content-header">
        <ol class="breadcrumb">
            <li>
                <a ng-href="${contextPath}/system/estateDictionary?target=.sys">
                    <i class="fa fa-home fa-lg" aria-hidden="true"></i>
                    系统设置
                </a>
            </li>
            <li class="active">
                权限管理
            </li>
        </ol>
    </section>
    <section class="content">
        <div class="row animated fadeInRight">
            <!-- 树 -->
            <div class="col-lg-3 col-md-3 col-sm-4">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs nav-justified">
                        <li class="active"><a href="#tab-user" data-toggle="tab" aria-expanded="true"><i class="fa fa-sitemap"></i> 组织机构</a></li>
                        <li class=""><a href="#tab-post" data-toggle="tab" aria-expanded="false"><i class="fa fa-user-circle"></i> 岗位权限</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane animated fadeInDown active" id="tab-user">
                            <div class="zTreeDemoBackground left">
                                <ul id="departmentTree" class="ztree"></ul>
                            </div>
                        </div>
                        <div class="tab-pane animated fadeInDown " id="tab-post">
                            <div class="zTreeDemoBackground left">
                                <input id="comnoPost" value="BSnlhD" type="hidden">
                                <ul id="positionList" class="ztree">
                                    <li ng-repeat="position in ctrl.baseData.position" ng-click="ctrl.getPositionAllAuthority(position, true)">
                                        <a href="javascript:;" class="position-list-nav">
                                            <span class="button ico_docu position_ico"></span>
                                            <span class="treeDemo_1_span" ng-bind="position.name"></span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end 树 -->
            <div class="col-lg-9 col-md-9 col-sm-8" id="content"><div class="nav-tabs-custom no-shadow m-t-10">
                <ul class="nav nav-tabs ui-sortable-handle">
                    <li class="active">
                        <a class="house" href="#house" data-toggle="tab" aria-expanded="true" id="first-nav-tab">房源</a>
                    </li>
                    <li>
                        <a class="xiaoqu" href="#xiaoqu" data-toggle="tab" aria-expanded="false">小区</a>
                    </li>
                    <li ng-class="{'active' : ctrl.config.category.ORGANIZATION==ctrl.authorityConfig.category}">
                        <a class="organization" href="#organization" data-toggle="tab" aria-expanded="false">组织架构</a>
                    </li>
                    <li ng-class="{'active' : ctrl.config.category.COMPANY==ctrl.authorityConfig.category}">
                        <a class="company" href="#company" data-toggle="tab" aria-expanded="false">公司</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane animated fadeInDown" ng-class="{active : ctrl.config.category.FANG==ctrl.authorityConfig.category}" id="house">
                        <form action="#" method="post" class="form-horizontal" id="fyForm">
                            <div class="gridbox gridalicious authority-content">
                                <div class="row">
                                    <div class="col-lg-6 col-md-6 col-sm-12">
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>新增权限</h4>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">新增房源：</label>
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="100101" ng-model="ctrl.authorityFang.CREATE_FANG" type="checkbox">
                                                        <label for="100101">新增</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>查看权限</h4>
                                                <div class="form-inline">
                                                    <label class="control-label">交易：</label>
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="100302" ng-model="ctrl.authorityFang.LIST_FANG_SELL" type="checkbox">
                                                        <label for="100302">出售</label>
                                                    </div>
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="100301" ng-model="ctrl.authorityFang.LIST_FANG_RENT" type="checkbox">
                                                        <label for="100301">出租</label>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">查看次数：</label>
                                                    <div class="ml90 f13">
                                                        <div>出售房源每天看业主 <input style="width:40px; text-align:center" ng-model="ctrl.authorityFang.VIEW_SELL_CONTACT_LIMIT" type="text"> 次</div>
                                                        <div class="m-t-sm">出租房源每天看业主 <input style="width:40px; text-align:center" ng-model="ctrl.authorityFang.VIEW_RENT_CONTACT_LIMIT" type="text"> 次</div>

                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <label class="pull-left control-label">查看业主信息：</label>
                                                    <div class="clearfix">
                                                        <div class="col-sm-4">
                                                            <select select-picker class="selectpicker show-menu-arrow form-control"
                                                                    ng-model="ctrl.authorityFang.VIEW_FANG_CONTACT">
                                                            <#list authorityScope ?if_exists as type>
                                                                <option value="${type.name()}">${type.getLabel()}</option>
                                                            </#list>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="100329" ng-model="ctrl.authorityFang.NOT_FOLLOW_SELL" type="checkbox">
                                                        <label for="100329">出售看业主不必写跟进 </label>
                                                    </div>
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="100328" ng-model="ctrl.authorityFang.NOT_FOLLOW_RENT" type="checkbox">
                                                        <label for="100328">出租看业主不必写跟进 </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>删除权限</h4>
                                                <div class="form-inline ">
                                                    <div class="clearfix">
                                                        <label class="pull-left control-label">删除实景图：</label>
                                                        <div class="col-sm-3">
                                                            <select select-picker class="selectpicker show-menu-arrow form-control"
                                                                    ng-model="ctrl.authorityFang.DEL_FANG_IMG_SHI_JING">
                                                            <#list authorityScope ?if_exists as type>
                                                                <option value="${type.name()}">${type.getLabel()}</option>
                                                            </#list>
                                                            </select>
                                                        </div>
                                                        <label class="pull-left control-label">删除户型图：</label>
                                                        <div class="col-sm-3">
                                                            <select select-picker class="selectpicker show-menu-arrow form-control"
                                                                    ng-model="ctrl.authorityFang.DEL_FANG_IMG_HU_XING">
                                                            <#list authorityScope ?if_exists as type>
                                                                <option value="${type.name()}">${type.getLabel()}</option>
                                                            </#list>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="clearfix m-t-7">
                                                        <label class="pull-left control-label">删除房产证：</label>
                                                        <div class="col-sm-3">
                                                            <select select-picker class="selectpicker show-menu-arrow form-control"
                                                                    ng-model="ctrl.authorityFang.DEL_FANG_IMG_CERTIF">
                                                            <#list authorityScope ?if_exists as type>
                                                                <option value="${type.name()}">${type.getLabel()}</option>
                                                            </#list>
                                                            </select>
                                                        </div>
                                                        <label class="pull-left control-label">删除委托书：</label>
                                                        <div class="col-sm-3">
                                                            <select select-picker class="selectpicker show-menu-arrow form-control"
                                                                    ng-model="ctrl.authorityFang.DEL_FANG_IMG_ATTORNEY">
                                                            <#list authorityScope ?if_exists as type>
                                                                <option value="${type.name()}">${type.getLabel()}</option>
                                                            </#list>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="clearfix m-t-7">
                                                        <label class="pull-left control-label">删除业主身份证：</label>
                                                        <div class="col-sm-3">
                                                            <select select-picker class="selectpicker show-menu-arrow form-control"
                                                                    ng-model="ctrl.authorityFang.DEL_FANG_IMG_ID_CARD">
                                                            <#list authorityScope ?if_exists as type>
                                                                <option value="${type.name()}">${type.getLabel()}</option>
                                                            </#list>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <#--<div class="form-inline ">-->
                                                    <#--<label class="pull-left control-label">删除图片：</label>-->
                                                    <#--<div class="ml90">-->
                                                        <#--<div class="checkbox checkbox-nice">-->
                                                            <#--<input value="1" checked="" type="checkbox">-->
                                                            <#--<label for="100410">实景图</label>-->
                                                        <#--</div>-->
                                                        <#--<div class="checkbox checkbox-nice">-->
                                                            <#--<input value="1" checked="" type="checkbox">-->
                                                            <#--<label for="100409">户型图</label>-->
                                                        <#--</div>-->
                                                        <#--<div class="checkbox checkbox-nice">-->
                                                            <#--<input value="1" checked="" type="checkbox">-->
                                                            <#--<label for="100411">房产证</label>-->
                                                        <#--</div>-->
                                                        <#--<div class="checkbox checkbox-nice">-->
                                                            <#--<input value="1" checked="" type="checkbox">-->
                                                            <#--<label for="100411">委托书</label>-->
                                                        <#--</div>-->
                                                        <#--<div class="checkbox checkbox-nice">-->
                                                            <#--<input value="1" checked="" type="checkbox">-->
                                                            <#--<label for="100411">业主身份证</label>-->
                                                        <#--</div>-->
                                                    <#--</div>-->
                                                <#--</div>-->
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-12">
                                        <div class="item" id="modify-authority">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>修改权限</h4>
                                                <#--<div class="form-inline">
                                                    <label class="pull-left control-label">状态：</label>
                                                    <div class="ml90">
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100213" value="1" checked="" type="checkbox">
                                                            <label for="100213">上架房源</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100214" value="1" checked="" type="checkbox">
                                                            <label for="100214">暂缓房源</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100215"  value="1" checked="" type="checkbox">
                                                            <label for="100215">下架房源</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100216"  value="1" checked="" type="checkbox">
                                                            <label for="100216">上架已下架的房源</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100217"  value="1" checked="" type="checkbox">
                                                            <label for="100217">申请发布外网</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100218"  value="1" checked="" type="checkbox">
                                                            <label for="100218">确认发布外网</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100219"  value="1" checked="" type="checkbox">
                                                            <label for="100219">拒绝发布外网</label>
                                                        </div>
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100220"  value="1" checked="" type="checkbox">
                                                            <label for="100220">撤销发布外网</label>
                                                        </div>
                                                    </div>
                                                </div>-->
                                                <div class="form-inline">
                                                    <div class="clearfix">
                                                        <label class="pull-left control-label">上架房源：</label>
                                                        <div class="col-sm-4">
                                                            <select select-picker class="selectpicker show-menu-arrow form-control"
                                                                    ng-model="ctrl.authorityFang.FANG_UN_PUBLISH">
                                                            <#list authorityScope ?if_exists as type>
                                                                <option value="${type.name()}">${type.getLabel()}</option>
                                                            </#list>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="clearfix m-t-7">
                                                        <label class="pull-left control-label">暂缓房源：</label>
                                                        <div class="col-sm-4">
                                                            <select select-picker class="selectpicker show-menu-arrow form-control"
                                                                    ng-model="ctrl.authorityFang.FANG_PAUSE">
                                                            <#list authorityScope ?if_exists as type>
                                                                <option value="${type.name()}">${type.getLabel()}</option>
                                                            </#list>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="clearfix m-t-7">
                                                        <label class="pull-left control-label">下架房源：</label>
                                                        <div class="col-sm-4">
                                                            <select select-picker class="selectpicker show-menu-arrow form-control"
                                                                    ng-model="ctrl.authorityFang.FANG_UN_PUBLISH">
                                                            <#list authorityScope ?if_exists as type>
                                                                <option value="${type.name()}">${type.getLabel()}</option>
                                                            </#list>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="clearfix m-t-7">
                                                        <label class="pull-left control-label">上架已下架的房源：</label>
                                                        <div class="col-sm-4">
                                                            <select select-picker class="selectpicker show-menu-arrow form-control"
                                                                    ng-model="ctrl.authorityFang.FANG_RE_PUBLISH">
                                                            <#list authorityScope ?if_exists as type>
                                                                <option value="${type.name()}">${type.getLabel()}</option>
                                                            </#list>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="clearfix m-t-7">
                                                        <label class="pull-left control-label">申请发布外网：</label>
                                                        <div class="col-sm-4">
                                                            <select select-picker class="selectpicker show-menu-arrow form-control"
                                                                    ng-model="ctrl.authorityFang.FANG_APPLY_PUBLIC">
                                                            <#list authorityScope ?if_exists as type>
                                                                <option value="${type.name()}">${type.getLabel()}</option>
                                                            </#list>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="clearfix m-t-7">
                                                        <label class="pull-left control-label">确认发布外网：</label>
                                                        <div class="col-sm-4">
                                                            <select select-picker class="selectpicker show-menu-arrow form-control"
                                                                    ng-model="ctrl.authorityFang.FANG_CONFIRM_PUBLIC">
                                                            <#list authorityScope ?if_exists as type>
                                                                <option value="${type.name()}">${type.getLabel()}</option>
                                                            </#list>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="clearfix m-t-7">
                                                        <label class="pull-left control-label">拒绝发布外网：</label>
                                                        <div class="col-sm-4">
                                                            <select select-picker class="selectpicker show-menu-arrow form-control"
                                                                    ng-model="ctrl.authorityFang.FANG_REJECT_PUBLIC">
                                                            <#list authorityScope ?if_exists as type>
                                                                <option value="${type.name()}">${type.getLabel()}</option>
                                                            </#list>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="clearfix m-t-7">
                                                        <label class="pull-left control-label">撤销发布外网：</label>
                                                        <div class="col-sm-4">
                                                            <select select-picker class="selectpicker show-menu-arrow form-control"
                                                                    ng-model="ctrl.authorityFang.FANG_UNDO_PUBLIC">
                                                            <#list authorityScope ?if_exists as type>
                                                                <option value="${type.name()}">${type.getLabel()}</option>
                                                            </#list>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <#--<div class="form-inline">
                                                    <label class="pull-left control-label">业主信息：</label>
                                                    <div class="ml90">
                                                        <div class="checkbox checkbox-nice">
                                                            <input id="100226" class="" name="map['100226']" value="1" checked="" type="checkbox">
                                                            <label for="100226">修改业主信息</label>
                                                        </div>
                                                    </div>
                                                </div>-->
                                                <div class="form-inline">
                                                    <div class="clearfix">
                                                        <label class="pull-left control-label">修改业主信息：</label>
                                                        <div class="col-sm-4">
                                                            <select select-picker class="selectpicker show-menu-arrow form-control"
                                                                    ng-model="ctrl.authorityFang.UPDATE_FANG_BASE">
                                                            <#list authorityScope ?if_exists as type>
                                                                <option value="${type.name()}">${type.getLabel()}</option>
                                                            </#list>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="clearfix m-t-7">
                                                        <label class="pull-left control-label">修改基本信息：</label>
                                                        <div class="col-sm-4">
                                                            <select select-picker class="selectpicker show-menu-arrow form-control"
                                                                    ng-model="ctrl.authorityFang.UPDATE_FANG_EXT">
                                                            <#list authorityScope ?if_exists as type>
                                                                <option value="${type.name()}">${type.getLabel()}</option>
                                                            </#list>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="clearfix m-t-7">
                                                        <label class="pull-left control-label">修改配套信息：</label>
                                                        <div class="col-sm-4">
                                                            <select select-picker class="selectpicker show-menu-arrow form-control"
                                                                    ng-model="ctrl.authorityFang.MODIFY_FANG_CONTACT">
                                                            <#list authorityScope ?if_exists as type>
                                                                <option value="${type.name()}">${type.getLabel()}</option>
                                                            </#list>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="clearfix text-center">
                                <button type="button" class="btn btn-primary" ng-click="ctrl.updateAuthority('FANG')"><i class="fa fa-save"></i> 保存</button>
                            </div>
                        </form>
                        </div>
                    <div class="tab-pane animated fadeInDown" ng-class="{active : ctrl.config.category.XIAOQU==ctrl.authorityConfig.category}" id="xiaoqu">
                        <form action="#" method="post" class="form-horizontal" id="xiaoquForm">
                            <div class="gridbox gridalicious authority-content">
                                <div class="row">
                                    <div class="col-lg-6 col-md-6 col-sm-12">
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>新增权限</h4>
                                                <div class="form-inline">
                                                    <div class="clearfix">
                                                        <label class="pull-left control-label">新增小区：</label>
                                                        <div class="col-sm-4">
                                                            <select select-picker class="selectpicker show-menu-arrow form-control" name="houseCertifType" id="houseCertifType"
                                                                    ng-model="ctrl.authorityXiaoqu.CREATE_XIAO_QU">
                                                            <#list authorityScope ?if_exists as type>
                                                                <option value="${type.name()}">${type.getLabel()}</option>
                                                            </#list>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <div class="clearfix">
                                                        <label class="pull-left control-label">新增栋座/单元：</label>
                                                        <div class="col-sm-4">
                                                            <select select-picker class="selectpicker show-menu-arrow form-control" name="houseCertifType" id="houseCertifType"
                                                                    ng-model="ctrl.authorityXiaoqu.CREATE_BUILDING">
                                                            <#list authorityScope ?if_exists as type>
                                                                <option value="${type.name()}">${type.getLabel()}</option>
                                                            </#list>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>删除权限</h4>
                                                <div class="form-inline">
                                                    <div class="clearfix">
                                                        <label class="pull-left control-label">删除小区：</label>
                                                        <div class="col-sm-4">
                                                            <select select-picker class="selectpicker show-menu-arrow form-control" name="houseCertifType" id="houseCertifType"
                                                                    ng-model="ctrl.authorityXiaoqu.DEL_XIAO_QU">
                                                            <#list authorityScope ?if_exists as type>
                                                                <option value="${type.name()}">${type.getLabel()}</option>
                                                            </#list>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <div class="clearfix">
                                                        <label class="pull-left control-label">删除栋座/单元：</label>
                                                        <div class="col-sm-4">
                                                            <select select-picker class="selectpicker show-menu-arrow form-control" name="houseCertifType" id="houseCertifType"
                                                                    ng-model="ctrl.authorityXiaoqu.DEL_BUILDING">
                                                            <#list authorityScope ?if_exists as type>
                                                                <option value="${type.name()}">${type.getLabel()}</option>
                                                            </#list>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-12">
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>修改权限</h4>
                                                <div class="form-inline">
                                                    <div class="clearfix">
                                                        <label class="pull-left control-label">修改小区：</label>
                                                        <div class="col-sm-4">
                                                            <select select-picker class="selectpicker show-menu-arrow form-control" name="houseCertifType" id="houseCertifType"
                                                                    ng-model="ctrl.authorityXiaoqu.MODIFY_XIAO_QU">
                                                            <#list authorityScope ?if_exists as type>
                                                                <option value="${type.name()}">${type.getLabel()}</option>
                                                            </#list>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <div class="clearfix">
                                                        <label class="pull-left control-label">修改栋座/单元：</label>
                                                        <div class="col-sm-4">
                                                            <select select-picker class="selectpicker show-menu-arrow form-control" name="houseCertifType" id="houseCertifType"
                                                                    ng-model="ctrl.authorityXiaoqu.MODIFY_BUILDING">
                                                            <#list authorityScope ?if_exists as type>
                                                                <option value="${type.name()}">${type.getLabel()}</option>
                                                            </#list>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="clearfix text-center">
                                <button type="button" class="btn btn-primary" ng-click="ctrl.updateAuthority('XIAO_QU')"><i class="fa fa-save"></i> 保存</button>
                            </div>
                        </form>
                    </div>
                    <div class="tab-pane animated fadeInDown" ng-class="{active : ctrl.config.category.ORGANIZATION==ctrl.authorityConfig.category}" id="organization">
                        <form action="#" method="post" class="form-horizontal" id="htForm">
                            <div class="gridbox gridalicious authority-content">
                                <div class="row">
                                    <div class="col-lg-6 col-md-6 col-sm-12">
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>管理</h4>
                                                <div class="form-inline">
                                                    <div class="clearfix">
                                                        <label class="pull-left control-label">组织架构管理</label>
                                                        <div class="col-sm-4">
                                                            <select select-picker class="selectpicker show-menu-arrow form-control"
                                                                    ng-model="ctrl.authorityOrganization.ORG_MANAGEMENT">
                                                            <#list authorityScope ?if_exists as type>
                                                                <option value="${type.name()}">${type.getLabel()}</option>
                                                            </#list>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <div class="clearfix">
                                                        <label class="pull-left control-label">解绑设备</label>
                                                        <div class="col-sm-4">
                                                            <select select-picker class="selectpicker show-menu-arrow form-control"
                                                                    ng-model="ctrl.authorityOrganization.UNBIND_DEVICE">
                                                            <#list authorityScope ?if_exists as type>
                                                                <option value="${type.name()}">${type.getLabel()}</option>
                                                            </#list>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-12">
                                    </div>
                                </div>
                            </div>
                            <div class="clearfix text-center">
                                <button type="button" class="btn btn-primary" ng-click="ctrl.updateAuthority('ORGANIZATION')"><i class="fa fa-save"></i> 保存</button>
                            </div>
                        </form>    </div>
                    <div class="tab-pane animated fadeInDown" ng-class="{active : ctrl.config.category.COMPANY==ctrl.authorityConfig.category}" id="company">
                        <form action="#" method="post" class="form-horizontal" id="kqForm">
                            <div class="gridbox gridalicious authority-content">
                                <div class="row">
                                    <div class="col-lg-6 col-md-6 col-sm-12">
                                        <div class="item">
                                            <div class="tweet-wrapper clearfix">
                                                <h4>管理</h4>
                                                <div class="form-inline">
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="pane4_1"  type="checkbox" ng-model="ctrl.authorityCompany.PERMISSION_MANAGEMENT">
                                                        <label for="pane4_1">模块与权限设置</label>
                                                    </div>
                                                </div>
                                                <div class="form-inline">
                                                    <div class="checkbox checkbox-nice">
                                                        <input id="pane4_2"  type="checkbox" ng-model="ctrl.authorityCompany.VIEW_AUDIT_LOG">
                                                        <label for="pane4_2">查看业务日志</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-12"></div>
                                </div>
                            </div>
                            <div class="clearfix text-center">
                                <button type="button" class="btn btn-primary" ng-click="ctrl.updateAuthority('COMPANY')"><i class="fa fa-save"></i> 保存</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div></div>
            <div class="clearfix"></div>
        </div>
    </section>
</div>
<#include "/common/footer.ftl" />
<script src="${contextPath!}/js/app/system/authorityConfig/index.js"></script>


<header class="main-header">
    <!-- Logo -->
    <a href="#" class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>Y</b>K</span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><b>盈科&nbsp;</b>地产</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </a>
        <!-- Navbar Right Menu -->
        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <!-- User Account Menu -->
                <li class="dropdown user user-menu">
                    <!-- Menu Toggle Button -->
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <#--<img src="dist/img/user2-160x160.jpg" class="user-image" alt="User Image">-->
                        <span class="hidden-xs username">${username}<i class="m-l-10 fa fa-gears"></i></span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- The user image in the menu -->
                        <li class="user-header">
                        <#--<img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">-->

                            <p>
                                <span class="username"></span>
                                <small>盈家</small>
                            </p>
                        </li>
                        <!-- Menu Body -->
                    <#--<li class="user-body">
                        <div class="row">
                            <div class="col-xs-4 text-center">
                                <a href="#">Followers</a>
                            </div>
                            <div class="col-xs-4 text-center">
                                <a href="#">Sales</a>
                            </div>
                            <div class="col-xs-4 text-center">
                                <a href="#">Friends</a>
                            </div>
                        </div>
                        <!-- /.row &ndash;&gt;
                    </li>-->
                        <!-- Menu Footer-->
                        <li class="user-footer">
                        <#--<div class="pull-left">
                            <a href="#" class="btn btn-default btn-flat">Profile</a>
                        </div>-->
                            <div class="pull-right">
                                <a href="#" class="btn btn-default btn-flat btn-logout">退出</a>
                            </div>
                        </li>
                    </ul>
                </li>
                <!-- Control Sidebar Toggle Button -->
            <#--<li>
                <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
            </li>-->
            </ul>
        </div>
    </nav>
</header>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel (optional) -->
        <div class="user-panel">
            <div class="pull-left image">
                <div class="user-panelImg">
                <#if avatarUrl??>
                    <img src="${avatarUrl}" style="width:42px; display: inline-block;">
                <#else>
                    <img src="${contextPath!}/img/common/avatar-default.png"
                         style="width:42px; height:42px;display: inline-block;">
                </#if>
                </div>
            </div>
            <div class="pull-left info">
                <p class="username">${username}</p>
                <a href="#"><i class="fa fa-circle text-success"></i>在线</a>
            </div>
        </div>

        <!-- Sidebar Menu -->
        <ul class="sidebar-menu">
            <li class="header">主导航</li>
            <!-- Optionally, you can add icons to the links -->
            <@checkPermission value='CT_YK|CT_RA'>
            <@checkPermission value='P_FRANCHISEE'>
            <li class="treeview franchisee">
                <a href="#"><i class="fa fa-user-plus" aria-hidden="true"></i> <span>加盟商管理</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <@checkPermission value='P_FRANCHISEE_C'>
                        <li><a href="/mgt/franchisee/channelPartner?target=.franchisee"><i class="fa fa-circle"></i>渠道商管理</a></li>
                    </@>
                    <@checkPermission value='P_FRANCHISEE_SS'>
                        <li><a href="/mgt/franchisee/storePartner?target=.franchisee"><i class="fa fa-circle"></i>单店加盟管理</a></li>
                    </@>
                    <@checkPermission value='CT_YK'>
                    <@checkPermission value='P_FRANCHISEE_RA'>
                        <li><a href="/mgt/franchisee/regionAgent?target=.franchisee"><i class="fa fa-circle"></i>区域代理管理</a></li>
                    </@>
                    </@>
                </ul>
            </li>
            </@>
            </@>
        <@checkPermission value='P_FANG'>
            <li class="treeview fang">
                <a href="#"><i class="fa fa-home" aria-hidden="true"></i> <span>房源管理</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <@checkPermission value='P_FANG_LIST'>
                        <li><a href="/mgt/fangManage/list?target=.fang"><i class="fa fa-circle"></i>房源列表</a></li>
                    </@>
                    <@checkPermission value='P_FANG_NEW'>
                        <li><a href="/mgt/fangManage/create?target=.fang"><i class="fa fa-circle"></i>新增房源</a></li>
                    </@>
                    <@checkPermission value='P_FANG_FOLLOW'>
                        <li><a href="/mgt/fangManage/follow?target=.fang"><i class="fa fa-circle"></i>房源跟进</a></li>
                    </@>
                    <@checkPermission value='P_FANG_CHECK'>
                        <li><a href="/mgt/fangManage/survey?target=.fang"><i class="fa fa-circle"></i>房源勘察</a></li>
                    </@>
                    <@checkPermission value='P_FANG_LIST'>
                        <li><a href="/mgt/application/fang/management"><i class="fa fa-circle"></i>房源审批列表</a></li>
                    </@>
                    <@checkPermission value='P_FANG_LIST'>
                        <li><a href="/mgt/application/fang/management/self"><i class="fa fa-circle"></i>我的房源申请列表</a></li>
                    </@>
                </ul>
            </li>
        </@>
        <@checkPermission value='P_SHOWING'>
            <li class="treeview prtyVisit">
                <a href="#"><i class="fa fa-key" aria-hidden="true"></i> <span>房源带看</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <@checkPermission value='P_SHOWING_LIST'>
                        <li><a href="/mgt/propertyVisit/propertyVisit?target=.prtyVisit">
                            <i class="fa fa-circle"></i>带看管理</a></li>
                    </@>
                </ul>
            </li>
        </@>

        <@checkPermission value='CT_YK'>
        <@checkPermission value='P_FANG_COLLECTION'>
            <li class="treeview fangCollection">
                <a href="#"><i class="fa fa-pie-chart" aria-hidden="true"></i> <span>房源采集</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                <@checkPermission value='P_FANG_COLLECTION_POOL'>
                    <li><a href="/mgt/fangCollection/pool?target=.fangCollection"><i class="fa fa-circle"></i>房源池</a></li>
                </@>
                </ul>
            </li>
        </@>
        </@>

        <@checkPermission value='CT_YK'>
        <@checkPermission value='P_APPROVAL'>
            <li class="treeview approval">
                <a href="#"><i class="fa fa-table" aria-hidden="true"></i> <span>表单管理</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                <@checkPermission value='P_APPROVAL_1'>
                    <li><a href="/mgt/approval/newForm?target=.approval"><i class="fa fa-circle"></i>新建表单</a></li>
                </@>
                <@checkPermission value='P_APPROVAL_2'>
                    <li><a href="/mgt/approval/list?target=.approval"><i class="fa fa-circle"></i>表单列表</a></li>
                </@>
                <@checkPermission value='P_APPROVAL_3'>
                    <li><a href="/mgt/approval/myList?target=.approval"><i class="fa fa-circle"></i>我的表单</a></li>
                </@>
                </ul>
            </li>
        </@>
        </@>
        <@checkPermission value='P_CONTRACT'>
            <li class="treeview contract">
                <a href="#"><i class="fa fa-file-text-o" aria-hidden="true"></i> <span>合同管理</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <@checkPermission value='P_CONTRACT_LIST'>
                        <li><a href="/mgt/contract/deal?target=.contract"><i class="fa fa-circle"></i>成交管理</a></li>
                    </@>
                </ul>
            </li>
        </@>

        <@checkPermission value='P_OPERATION'>
            <li class="treeview credit">
                <a href="#"><i class="fa fa-handshake-o" aria-hidden="true"></i> <span>运营管理</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <@checkPermission value='P_OPERATION_XY'>
                        <li><a href="/mgt/creditMgt/list?target=.credit"><i class="fa fa-circle"></i>信誉平台</a></li>
                    </@>
                </ul>
            </li>
        </@>

            <li class="treeview tool">
                <a href="#"><i class="fa fa-wrench" aria-hidden="true"></i> <span>实用工具</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/mgt/tool/myProfile?target=.tool"><i class="fa fa-circle"></i>个人中心</a></li>
                </ul>
            </li>
        <@checkPermission value='P_ORG'>
            <li class="treeview org">
                <a href="#"><i class="fa fa-sitemap" aria-hidden="true"></i> <span>组织机构</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <@checkPermission value='P_ORG_DEPT'>
                        <li><a href="/mgt/org/department?target=.org"><i class="fa fa-circle"></i>部门管理</a></li>
                    </@>
                    <@checkPermission value='P_ORG_EMPLOYEE'>
                        <li><a href="/mgt/org/employee?target=.org"><i class="fa fa-circle"></i>员工管理</a></li>
                    </@>
                    <@checkPermission value='P_ORG_POSITION'>
                        <li><a href="/mgt/org/position?target=.org"><i class="fa fa-circle"></i>岗位管理</a></li>
                    </@>
                </ul>
            </li>
        </@>


        <@checkPermission value='P_CONFIG'>
            <li class="treeview sys">
                <a href="#"><i class="fa fa-cog" aria-hidden="true"></i> <span>系统设置</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <@checkPermission value='P_CONFIG_AUDIT'>
                        <li><a href="/mgt/system/operationLog?target=.sys"><i class="fa fa-circle"></i>操作日志</a></li>
                    </@>
                    <@checkPermission value='P_CONFIG_HOUSE_DICT'>
                        <li><a href="/mgt/system/estateDictionary?target=.sys"><i class="fa fa-circle"></i>楼盘字典</a></li>
                    </@>
                    <@checkPermission value='P_CONFIG_PERMISSION'>
                        <li><a href="/mgt/system/authorityConfig?target=.sys"><i class="fa fa-circle"></i>权限管理</a></li>
                    </@>
                    <@checkPermission value='P_CONFIG_PAGE'>
                        <li><a href="/mgt/system/moduleConfig?target=.sys"><i class="fa fa-circle"></i>岗位模块</a></li>
                    </@>
                </ul>
            </li>
        </@>

        </ul>
        <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
</aside>


<header class="main-header">
    <!-- Logo -->
    <a href="#" class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>Y</b>K</span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><b>YK&nbsp;</b>Admin</span>
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
                        <img src="dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                        <span class="hidden-xs username"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- The user image in the menu -->
                        <li class="user-header">
                            <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

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
                <li>
                    <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
                </li>
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
                    <i class="fa fa-user-circle fa-3x" aria-hidden="true"></i>
                </div>
            </div>
            <div class="pull-left info">
                <p>User Name</p>
                <a href="#"><i class="fa fa-circle text-success"></i>在线</a>
            </div>
        </div>

        <!-- Sidebar Menu -->
        <ul class="sidebar-menu">
            <li class="header">主导航</li>
            <!-- Optionally, you can add icons to the links -->
            <li><a href="#"><i class="fa fa-link"></i> <span>Link</span></a></li>
            <li><a href="#"><i class="fa fa-link"></i> <span>Another Link</span></a></li>
            <li class="treeview active">
                <a href="#"><i class="fa fa-link"></i> <span>组织机构</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/mgt/org/department.ftl">部门列表</a></li>
                    <li><a href="/mgt/org/employee.ftl">员工管理</a></li>
                    <li><a href="/mgt/org/position.ftl">岗位管理</a></li>
                </ul>
            </li>
        </ul>
        <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
</aside>


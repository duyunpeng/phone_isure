<aside class="sidebar-menu fixed">
    <div class="sidebar-inner scrollable-sidebar">
        <div class="main-menu">
        [@shiro.hasRole name="admin"]
            <ul class="accordion" id="sidebar">
                <li class="menu-header">
                    Main Menu
                </li>
                <li class="bg-palette1 active">
                    <a href="/">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-home fa-lg"></i></span>
                            <span class="text m-left-sm">首页</span>
                        </span>
                    </a>
                </li>
                <li class="openable bg-palette2">
                    <a href="#">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-users fa-lg"></i></span>
                            <span class="text m-left-sm">用户</span>
                            <span class="submenu-icon"></span></span>
                        <span class="menu-content-hover block">Menu</span>
                    </a>
                    <ul class="submenu">
                        <li>
                            <a href="[@spring.url '/merchant/agency_pagination'/]">
                                <span class="submenu-label">代理管理</span>
                            </a>
                        </li>
                        [#--<li>--]
                            [#--<a href="[@spring.url '/merchant/wait_agency_pagination'/]">--]
                                [#--<span class="submenu-label">待审核代理管理</span>--]
                            [#--</a>--]
                        [#--</li>--]
                        <li>
                            <a href="[@spring.url '/merchant/merchant_pagination'/]">
                                <span class="submenu-label">商户管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/merchant/merchant_wait_pagination'/]">
                                <span class="submenu-label">待审核商户管理</span>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="openable bg-palette2">
                    <a href="#">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-users fa-lg"></i></span>
                            <span class="text m-left-sm">保单</span>
                            <span class="submenu-icon"></span></span>
                        <span class="menu-content-hover block">Menu</span>
                    </a>
                    <ul class="submenu">
                        <li>
                            <a href="[@spring.url '/policy/pagination'/]">
                                <span class="submenu-label">保单管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/policy/charge_back_pagination'/]">
                                <span class="submenu-label">保单管理(待处理退单)</span>
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/policy/claim_for_compensation_pagination'/]">
                                <span class="submenu-label">保单管理(待处理理赔)</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="openable bg-palette2">
                    <a href="#">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-users fa-lg"></i></span>
                            <span class="text m-left-sm">资金明细</span>
                            <span class="submenu-icon"></span></span>
                        <span class="menu-content-hover block">Menu</span>
                    </a>
                    <ul class="submenu">
                        <li>
                            <a href="[@spring.url '/money_detailed/pagination'/]">
                                <span class="submenu-label">资金明细</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="openable bg-palette4">
                    <a href="#">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-list fa-lg"></i></span>
                            <span class="text m-left-sm">系统设置</span>
                            <span class="submenu-icon"></span></span>
                        <span class="menu-content-hover block">Menu</span>
                    </a>
                    <ul class="submenu">
                        <li>
                            <a href="[@spring.url '/area/pagination'/]">
                                <span class="submenu-label">区域管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/app_key/pagination'/]">
                                <span class="submenu-label">AppKey管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/permission/pagination'/]">
                                <span class="submenu-label">权限管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/role/pagination'/]">
                                <span class="submenu-label">角色管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/account/pagination'/]">
                                <span class="submenu-label">账号管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/recharge/pagination'/]">
                                <span class="submenu-label">充值管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/id_type/pagination'/]">
                                <span class="submenu-label">证件类型</span>
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/phone_brand/pagination'/]">
                                <span class="submenu-label">手机品牌管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/policy_fee /pagination'/]">
                                <span class="submenu-label">手机型号管理</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="bg-palette1">
                    <a href="[@spring.url '/logout'/]">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa ion-log-out fa-lg"></i></span>
                            <span class="text m-left-sm">退出</span>
                        </span>
                    </a>
                </li>
            </ul>
        [/@shiro.hasRole]
        [@shiro.hasRole name="agent"]
            <ul class="accordion" id="sidebar">
                <li class="menu-header">
                    Main Menu
                </li>
                <li class="bg-palette1 active">
                    <a href="/">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-home fa-lg"></i></span>
                            <span class="text m-left-sm">首页</span>
                        </span>
                    </a>
                </li>
                <li class="openable bg-palette2">
                    <a href="#">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-users fa-lg"></i></span>
                            <span class="text m-left-sm">保单</span>
                            <span class="submenu-icon"></span></span>
                        <span class="menu-content-hover block">Menu</span>
                    </a>
                    <ul class="submenu">
                        <li>
                            <a href="[@spring.url '/policy/agent_pagination'/]">
                                <span class="submenu-label">保单管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/policy/charge_back_pagination'/]">
                                <span class="submenu-label">保单管理(待处理退单)</span>
                            </a>
                        </li>
                    </ul>
                </li>
                [#--<li class="openable bg-palette2">--]
                    [#--<a href="#">--]
                        [#--<span class="menu-content block">--]
                            [#--<span class="menu-icon"><i class="block fa fa-users fa-lg"></i></span>--]
                            [#--<span class="text m-left-sm">商户</span>--]
                            [#--<span class="submenu-icon"></span></span>--]
                        [#--<span class="menu-content-hover block">Menu</span>--]
                    [#--</a>--]
                    [#--<ul class="submenu">--]
                        [#--<li>--]
                            [#--<a href="[@spring.url '/merchant/merchant_agent_pagination'/]">--]
                                [#--<span class="submenu-label">商户管理</span>--]
                            [#--</a>--]
                        [#--</li>--]
                    [#--</ul>--]
                [#--</li>--]
                [#--<li class="openable bg-palette2">--]
                    [#--<a href="#">--]
                        [#--<span class="menu-content block">--]
                            [#--<span class="menu-icon"><i class="block fa fa-users fa-lg"></i></span>--]
                            [#--<span class="text m-left-sm">代理</span>--]
                            [#--<span class="submenu-icon"></span></span>--]
                        [#--<span class="menu-content-hover block">Menu</span>--]
                    [#--</a>--]
                    [#--<ul class="submenu">--]
                        [#--<li>--]
                            [#--<a href="[@spring.url '/merchant/agency_pagination'/]">--]
                                [#--<span class="submenu-label">代理管理</span>--]
                            [#--</a>--]
                        [#--</li>--]
                    [#--</ul>--]
                [#--</li>--]

                <li class="openable bg-palette2">
                    <a href="#">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-users fa-lg"></i></span>
                            <span class="text m-left-sm">用户</span>
                            <span class="submenu-icon"></span></span>
                        <span class="menu-content-hover block">Menu</span>
                    </a>
                    <ul class="submenu">
                        <li>
                            <a href="[@spring.url '/merchant/agency_pagination'/]">
                                <span class="submenu-label">代理管理</span>
                            </a>
                        </li>
                        [#--<li>--]
                            [#--<a href="[@spring.url '/merchant/wait_agency_pagination'/]">--]
                                [#--<span class="submenu-label">待审核代理管理</span>--]
                            [#--</a>--]
                        [#--</li>--]
                        <li>
                            <a href="[@spring.url '/merchant/merchant_pagination'/]">
                                <span class="submenu-label">商户管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/merchant/merchant_wait_pagination'/]">
                                <span class="submenu-label">待审核商户管理</span>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="openable bg-palette2">
                    <a href="#">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-users fa-lg"></i></span>
                            <span class="text m-left-sm">资金明细</span>
                            <span class="submenu-icon"></span></span>
                        <span class="menu-content-hover block">Menu</span>
                    </a>
                    <ul class="submenu">
                        <li>
                            <a href="[@spring.url '/money_detailed/pagination'/]">
                                <span class="submenu-label">资金明细</span>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="bg-palette1">
                    <a href="[@spring.url '/logout'/]">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa ion-log-out fa-lg"></i></span>
                            <span class="text m-left-sm">退出</span>
                        </span>
                    </a>
                </li>
            </ul>
        [/@shiro.hasRole]
        [@shiro.hasRole name="picc"]
            <ul class="accordion" id="sidebar">
                <li class="menu-header">
                    Main Menu
                </li>
                <li class="bg-palette1 active">
                    <a href="/">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-home fa-lg"></i></span>
                            <span class="text m-left-sm">首页</span>
                        </span>
                    </a>
                </li>
                <li class="openable bg-palette2">
                    <a href="#">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-users fa-lg"></i></span>
                            <span class="text m-left-sm">保单</span>
                            <span class="submenu-icon"></span></span>
                        <span class="menu-content-hover block">Menu</span>
                    </a>
                    <ul class="submenu">
                        <li>
                            <a href="[@spring.url '/policy/external_pagination'/]">
                                <span class="submenu-label">保单查看</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="bg-palette1">
                    <a href="[@spring.url '/logout'/]">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa ion-log-out fa-lg"></i></span>
                            <span class="text m-left-sm">退出</span>
                        </span>
                    </a>
                </li>
            </ul>
        [/@shiro.hasRole]
        [@shiro.hasRole name="auth"]
            <ul class="accordion" id="sidebar">
                <li class="menu-header">
                    Main Menu
                </li>
                <li class="bg-palette1 active">
                    <a href="/">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-home fa-lg"></i></span>
                            <span class="text m-left-sm">首页</span>
                        </span>
                    </a>
                </li>
                <li class="openable bg-palette2">
                    <a href="#">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-users fa-lg"></i></span>
                            <span class="text m-left-sm">用户</span>
                            <span class="submenu-icon"></span></span>
                        <span class="menu-content-hover block">Menu</span>
                    </a>
                    <ul class="submenu">
                        <li>
                            <a href="[@spring.url '/merchant/agency_pagination'/]">
                                <span class="submenu-label">代理管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/merchant/wait_agency_pagination'/]">
                                <span class="submenu-label">待审核代理管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/merchant/merchant_pagination'/]">
                                <span class="submenu-label">商户管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/merchant/merchant_wait_pagination'/]">
                                <span class="submenu-label">待审核商户管理</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="bg-palette1">
                    <a href="[@spring.url '/account/pagination'/]">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-user fa-lg"></i></span>
                            <span class="submenu-label">账号管理</span>
                        </span>
                    </a>
                </li>
                <li class="bg-palette1">
                    <a href="[@spring.url '/logout'/]">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa ion-log-out fa-lg"></i></span>
                            <span class="text m-left-sm">退出</span>
                        </span>
                    </a>
                </li>
            </ul>
        [/@shiro.hasRole]
        </div>
    </div><!-- sidebar-inner -->
</aside>
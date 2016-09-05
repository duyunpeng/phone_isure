[@override name="title"]待审核用户管理[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a href="/">首页</a></li>
    <li>待审核用户管理</li>
</ul>
[/@override]

[@override name="headerText"]
待审核用户 管理
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="smart-widget widget-dark-blue">
    <div class="smart-widget-header">
        <span class="smart-widget-option">
            [#--<span class="refresh-icon-animated" style="display: none;"><i--]
            [#--class="fa fa-circle-o-notch fa-spin"></i></span>--]
                [#--<a href="#" class="widget-toggle-hidden-option"><i class="fa fa-cog"></i></a>--]
            [#--<a href="#" class="widget-collapse-option" data-toggle="collapse"><i class="fa fa-chevron-up"></i></a>--]
            [#--<a href="#" class="widget-refresh-option"><i class="fa fa-refresh"></i></a>--]
            [#--<a href="#" class="widget-remove-option"><i class="fa fa-times"></i></a>--]
        </span>
    </div>
    <div class="smart-widget-inner">
        <div class="smart-widget-hidden-section" style="display: none;">
            <ul class="widget-color-list clearfix">
                <li style="background-color:#20232b;" data-color="widget-dark"></li>
                <li style="background-color:#4c5f70;" data-color="widget-dark-blue"></li>
                <li style="background-color:#23b7e5;" data-color="widget-blue"></li>
                <li style="background-color:#2baab1;" data-color="widget-green"></li>
                <li style="background-color:#edbc6c;" data-color="widget-yellow"></li>
                <li style="background-color:#fbc852;" data-color="widget-orange"></li>
                <li style="background-color:#e36159;" data-color="widget-red"></li>
                <li style="background-color:#7266ba;" data-color="widget-purple"></li>
                <li style="background-color:#f5f5f5;" data-color="widget-light-grey"></li>
                <li style="background-color:#fff;" data-color="reset"></li>
            </ul>
        </div>
        <div class="smart-widget-body no-padding">
            <div class="padding-md">
                <section class="overflow-auto nice-scrollbar">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>账号名</th>
                            <th>创建时间</th>
                            <th>最后登录IP</th>
                            <th>最后登录时间</th>
                            <th>角色</th>
                            <th>AppKey</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            [#if pagination.data??]
                                [#list pagination.data as merchant ]
                                <tr>
                                    <td>${merchant.userName!}</td>
                                    <td>[@mc.dateShow merchant.createDate/]</td>
                                    <td>${merchant.lastLoginIP!}</td>
                                    <td>[@mc.dateShow merchant.lastLoginDate/]</td>
                                    <td>${merchant.roles[0].name!}</td>
                                    <td>${merchant.appKey.name!}</td>
                                    <td>${(merchant.status.getName())!}</td>
                                    <td>
                                        <a href="[@spring.url '/merchant/info/${merchant.id!}'/]"
                                           data-toggle="tooltip" data-placement="top" title="点击查看详情">
                                            <span class="label label-info">查看</span>
                                        </a>
                                        [#if merchant.status == "DISABLE"]
                                            <a href="[@spring.url '/merchant/auth?id=${merchant.id!}&version=${merchant.version!}'/]"
                                               data-toggle="tooltip" data-placement="top" title="点击通过认证">
                                                <span class="label label-danger">通过认证</span>
                                            </a>
                                        [/#if]
                                    </td>
                                </tr>
                                [/#list]
                            [/#if]
                        </tbody>
                    </table>
                </section>
            </div>
            <div class="bg-grey">
                [#if pagination!]
            [@mc.showPagination '/merchant/auth_pagination' /]
        [/#if]
            </div>
        </div>

    </div>
</div>
    [#include 'shared/confirmation.ftl'/]
[/@override]

[@override name="bottomResources"]
    [@super /]
[/@override]
[@extends name="/decorator.ftl"/]
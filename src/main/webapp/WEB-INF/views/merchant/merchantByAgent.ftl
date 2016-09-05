[@override name="title"]商户管理[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a href="/">首页</a></li>
    <li>商户管理</li>
</ul>
[/@override]

[@override name="headerText"]
商户 管理
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="smart-widget widget-dark-blue">
    <div class="smart-widget-inner">
        <div class="smart-widget-header">
        <span class="smart-widget-option">
            [#--<span class="refresh-icon-animated" style="display: none;"><i--]
            [#--class="fa fa-circle-o-notch fa-spin"></i></span>--]
                <a href="#" class="widget-toggle-hidden-option"><i class="fa fa-cog"></i></a>
            <a href="#" class="widget-collapse-option" data-toggle="collapse"><i class="fa fa-chevron-up"></i></a>
            [#--<a href="#" class="widget-refresh-option"><i class="fa fa-refresh"></i></a>--]
            <a href="#" class="widget-remove-option"><i class="fa fa-times"></i></a>
        </span>
            <form class="form-inline no-margin" role="form">
                <div class="form-group">
                    <label for="merchantName" class="control-label">商户名称</label>
                    <input type="text" class="form-control" id="merchantName" name="merchantName" value="${command.merchantName!}"
                           placeholder="商户名称"/>
                </div>
                <div class="form-group">
                    <label for="userName" class="control-label">用户名</label>
                    <input type="text" class="form-control" id="userName" name="userName" value="${command.userName!}"
                           placeholder="用户名"/>
                </div>
                <div class="form-group">
                    <label for="status" class="control-label">状态</label>
                    <select name="status" id class="form-control">
                        [#assign status = (command.status!)?default("") /]
                        <option value="ALL" [@mc.selected status "ALL" /]>全部</option>
                        <option value="ENABLE" [@mc.selected status "ENABLE" /]>启用</option>
                        <option value="DISABLE" [@mc.selected status "DISABLE" /]>禁用</option>
                    </select>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-md btn-success">查询</button>
                </div>
            </form>
        </div>
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
                            <th>商户名称</th>
                            <th>用户名</th>
                            <th>商户联系人</th>
                            <th>商户联系人电话</th>
                            <th>地区</th>
                            <th>余额</th>
                            <th>认证状态</th>
                            <th>认证失败原因</th>
                            <th>用户类型</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            [#if pagination.data??]
                                [#list pagination.data as merchant ]
                                <tr>
                                    <td>${merchant.merchantName!}</td>
                                    <td>${merchant.userName!}</td>
                                    <td>${merchant.contacts!}</td>
                                    <td>${merchant.contactsPhone!}</td>
                                    <td>${merchant.area.name!}</td>
                                    <td>${merchant.money!}</td>
                                    <td>${(merchant.authStatus.getName())!}</td>
                                    <td>${merchant.authFailureExplain!}</td>
                                    <td>${(merchant.userType.getName())!}</td>
                                    <td>
                                        <a href="[@spring.url '/merchant/info_agent_merchant/${merchant.id!}'/]"
                                           data-toggle="tooltip" data-placement="top" title="点击查看详情">
                                            <span class="label label-info">查看</span>
                                        </a>
                                    </td>
                                </tr>
                                [/#list]
                            [/#if]
                        </tbody>
                    </table>
                </section>
            </div>
        </div>
        <div class="bg-grey">
            [#if pagination!]
            [@mc.showPagination '/merchant/merchant_agent_pagination?merchantName=${command.merchantName!}&userName=${command.userName!}&status=${command.status!}' /]
        [/#if]
        </div>

    </div>
</div>
    [#include 'shared/confirmation.ftl'/]
[/@override]

[@override name="bottomResources"]
    [@super /]
<script src="[@spring.url '/resources/js/ajax.js'/]"></script>
<script type="text/javascript">
    $("#appKey").selectAjaxData({url: "/app_key/all_list"});
</script>
[/@override]
[@extends name="/decorator.ftl"/]
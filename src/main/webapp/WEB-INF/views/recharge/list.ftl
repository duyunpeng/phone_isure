[@override name="title"]充值管理[/@override]
[@override name="topResources"]
    [@super /]
<link href="[@spring.url '/resources/js/datetimepicker/jquery.datetimepicker.css' /]" rel="stylesheet" type="text/css"/>
[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a href="/">首页</a></li>
    <li>充值管理</li>
</ul>
[/@override]

[@override name="headerText"]
充值 管理
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="smart-widget widget-dark-blue">
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
                <label>开始<input type="text" class="form-control" value="${command.startDealTime!}" id="startDealTime"
                                name="startDealTime"/></label>
                到
                <label>结束<input type="text" class="form-control" value="${command.endDealTime!}" id="endDealTime"
                                name="endDealTime"/></label>
                <label for="merchant" class="control-label">充值商户</label>
                <input type="text" class="form-control" id="merchant" name="merchant" value="${command.merchant!}"
                       placeholder="充值商户"/>
            </div>

            <div class="form-group">
                <label for="isPay" class="control-label">状态</label>
                <select name="isPay" id class="form-control">
                    [#assign isPay = (command.isPay!)?default("") /]
                    <option value="ALL" [@mc.selected isPay "ALL" /]>全部</option>
                    <option value="YES" [@mc.selected isPay "YES" /]>是</option>
                    <option value="NO" [@mc.selected isPay "NO" /]>否</option>
                </select>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-md btn-success">查询</button>
            </div>
        </form>
        <div>
            <h2>总金额:${count!}</h2>
        </div>
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
                            <th>充值商户</th>
                            <th>上级代理</th>
                            <th>充值金额</th>
                            <th>支付时间</th>
                            <th>支付方式</th>
                            <th>支付号</th>
                            <th>是否支付</th>
                        </tr>
                        </thead>
                        <tbody>
                            [#if pagination.data??]
                                [#list pagination.data as recharge ]
                                <tr>
                                    <td>${recharge.merchant.userName!}--${recharge.merchant.merchantName!}</td>
                                    <td>${recharge.merchant.agent.userName!}--${recharge.merchant.agent.merchantName!}</td>
                                    <td>${recharge.rechargeMoney!}</td>
                                    <td>${recharge.payDate!}</td>
                                    <td>${recharge.payType.getName()!}</td>
                                    <td>${recharge.payNo!}</td>
                                    <td>${recharge.isPay.getName()!}</td>
                                </tr>
                                [/#list]
                            [/#if]
                        </tbody>
                    </table>
                </section>
            </div>
            <div class="bg-grey">
                [#if pagination!]
            [@mc.showPagination '/recharge/pagination?startDealTime=${command.startDealTime!}&endDealTime=${command.endDealTime}&merchant=${command.merchant!}&isPay=${command.isPay}' /]
        [/#if]
            </div>
        </div>

    </div>
</div>
    [#include 'shared/confirmation.ftl'/]
[/@override]

[@override name="bottomResources"]
    [@super /]
<script src="[@spring.url '/resources/js/datetimepicker/jquery.datetimepicker.full.js'/]"></script>
<script type="text/javascript">


    $.datetimepicker.setLocale('en');
    $('#startDealTime').datetimepicker({
        dayOfWeekStart: 1,
        lang: 'en',
    });
    $('#endDealTime').datetimepicker({
        dayOfWeekStart: 1,
        lang: 'en',
    });
</script>
[/@override]
[@extends name="/decorator.ftl"/]
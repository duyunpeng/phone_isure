[@override name="title"]保单管理[/@override]
[@override name="topResources"]
    [@super /]
<link href="[@spring.url '/resources/js/colorbox/css/colorbox.css/' /]" rel="stylesheet" type="text/css"/>
<link href="[@spring.url '/resources/js/datetimepicker/jquery.datetimepicker.css' /]" rel="stylesheet" type="text/css"/>
[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a href="/">首页</a></li>
    <li>保单管理</li>
</ul>
[/@override]

[@override name="headerText"]
保单 管理
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="row text-center paddingTB-xs">
    <div class="col-lg-4">
        <h3>保单数量</h3>
        <span class="badge badge-info font-18 policy_all">7</span>
    </div>
    <div class="col-lg-4">
        <h3>退单数量</h3>
        <span class="badge badge-danger font-18 policy_back">7</span>
    </div>
    <div class="col-lg-4">
        <h3>理赔数量</h3>
        <span class="badge badge-success font-18 policy_claim">7</span>
    </div>
</div>
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
        <div class="row margin-sm">
            <form class="form-inline no-margin" role="form">
                <div class="form-group">
                    <label for="merchant" class="control-label">商户</label>
                    <input type="text" class="form-control" id="merchant" name="merchant" value="${command.merchant!}"
                           placeholder="商户"/>
                </div>
                <div class="form-group">
                    <label for="agentUserName" class="control-label">代理</label>
                    <input type="text" class="form-control" id="agentUserName" name="agentUserName"
                           value="${command.agentUserName!}"
                           placeholder="代理"/>
                </div>
                <div class="form-group">
                    <label for="policyNo" class="control-label">保单号</label>
                    <input type="text" class="form-control" id="policyNo" name="policyNo" value="${command.policyNo!}"
                           placeholder="保单号"/>
                </div>
                <div class="form-group">
                    <label for="insuredName" class="control-label">参保人姓名</label>
                    <input type="text" class="form-control" id="insuredName" name="insuredName"
                           value="${command.insuredName!}"
                           placeholder="参保人姓名"/>
                </div>
                <div class="form-group">
                    <label for="insuredName" class="control-label">手机串号IMEI</label>
                    <input type="text" class="form-control" id="IMEI" name="IMEI"
                           value="${command.IMEI!}"
                           placeholder="手机串号IMEI"/>
                </div>


                <div class="form-group">
                    <label for="starTime" class="control-label">下单时间</label>
                    <input type="text" class="form-control" value="${command.startTime!}" id="starTime" name="startTime"
                           placeholder="开始时间"/>
                    到
                    <input type="text" class="form-control" value="${command.endTime!}" id="endTime" name="endTime"
                           placeholder="结束时间"/>
                </div>

                <div class="form-group">
                    <label for="policyStatus" class="control-label">状态</label>
                    <select name="policyStatus" id class="form-control">
                        [#assign policyStatus = (command.policyStatus!)?default("") /]
                        <option value="ALL" [@mc.selected policyStatus "ALL" /]>全部</option>
                        <option value="NORMAL" [@mc.selected policyStatus "NORMAL" /]>正常</option>
                        <option value="BACK" [@mc.selected policyStatus "BACK" /]>退单中</option>
                        <option value="SUCCESS_CLAIM" [@mc.selected policyStatus "SUCCESS_CLAIM" /]>理賠完成</option>
                        <option value="NEED_CLAIM" [@mc.selected policyStatus "NEED_CLAIM" /]>需要理賠</option>
                        <option value="SUCCESS_BACK" [@mc.selected policyStatus "SUCCESS_BACK" /]>已退单</option>
                    </select>
                </div>


                <div class="form-group">
                    <button type="submit" class="btn btn-md btn-success">查询</button>
                </div>
            </form>
        </div>
        <hr/>
        <div class="row margin-sm">
            <form class="form-inline no-margin" action="/policy/export_excel" role="form">
                <div class="form-group">
                    <label for="insuredName" class="control-label">下单时间</label>
                    <input type="text" class="form-control" id="beginDate" name="beginDate"
                           placeholder="开始时间"/>
                    到
                    <input type="text" class="form-control" id="endDate" name="endDate"
                           placeholder="结束时间"/>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-md btn-success">导出Excel表格</button>
                </div>
            </form>
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
                            <th>保单号</th>
                            <th>代理商户</th>
                            <th>上级代理</th>
                            <th>手机型号</th>
                            <th>手机串号IMEI</th>
                            <th>参保人姓名</th>
                            <th>参保人手机号码</th>
                            <th>参保手机新机图片</th>
                            <th>参保手机理赔之后照片</th>
                            <th>状态</th>
                            <th>操作${Session["agent"]!}</th>
                        </tr>
                        </thead>
                        <tbody>
                            [#if pagination.data??]
                                [#list pagination.data as policy ]
                                <tr>
                                    <td>${policy.policyNo!}</td>
                                    <td>${policy.merchant.userName!}--${policy.merchant.merchantName!}</td>
                                    <td>${policy.merchant.agent.userName!}--${policy.merchant.agent.merchantName!}</td>
                                    <td>${policy.phoneModel!}</td>
                                    <td>${policy.IMEI!}</td>
                                    <td>${policy.insuredName!}</td>
                                    <td>${policy.insuredPhone!}</td>
                                    <td class="colorbox-td">
                                        <div class="colorbox">
                                            [#list policy.insuredBeginPicture as qualification]
                                                <div class="img-view">
                                                    [#if qualification_index == 0]
                                                        <span class="label label-success"
                                                              data-img="${qualification.picPath}">查看</span>
                                                    [#else]
                                                        <span class="label label-success hidden"
                                                              data-img="${qualification.picPath}">查看</span>
                                                    [/#if]
                                                </div>
                                            [/#list]
                                        </div>
                                    </td>
                                    <td class="colorbox-td">
                                        <div class="colorbox">
                                            [#list policy.insuredAfterPicture as qualification]
                                                <div class="img-view">
                                                    [#if qualification_index == 0]
                                                        <span class="label label-success"
                                                              data-img="${qualification.picPath}">查看</span>
                                                    [#else]
                                                        <span class="label label-success hidden"
                                                              data-img="${qualification.picPath}">查看</span>
                                                    [/#if]
                                                </div>
                                            [/#list]
                                        </div>
                                    </td>
                                    <td>${(policy.policyStatus.getName())!}</td>
                                    <td>
                                        <a href="[@spring.url '/policy/info/${policy.id!}'/]"
                                           data-toggle="tooltip" data-placement="top" title="点击查看详情">
                                            <span class="label label-info">详情</span>
                                        </a>
                                        [#if policy.policyStatus == "BACK"]
                                            <a href="[@spring.url '/policy/update_policyStatus?id=${policy.id!}&version=${policy.version!}&policyStatus=NEED_CLAIM'/]"
                                               data-toggle="tooltip" data-placement="top" title="点击处理退单">
                                                <span class="label label-primary">处理退单</span>
                                            </a>
                                        [/#if]
                                        [#if  Session["agent"] == "true"]
                                            [#if policy.policyStatus != "SUCCESS_CLAIM" && policy.policyStatus != "SUCCESS_BACK" && policy.policyStatus != "NEED_CLAIM"]
                                                <a href="[@spring.url '/policy/handle_policy/${policy.id!}'/]"
                                                   data-toggle="tooltip" data-placement="top" title="点击理赔"
                                                <span class="label label-purple">理赔</span>
                                                </a>
                                            [/#if]
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
             [@mc.showPagination '/policy/agent_pagination?merchant=${command.merchant!}&policyNo=${command.policyNo}&insuredName=${command.insuredName!}&IMEI=${command.IMEI!}&beginTime=${command.beginTime!}&endTime=${command.endTime!}&policyStatus=${command.policyStatus!}&agentUserName=${command.agentUserName!}' /]
        [/#if]
            </div>
        </div>
    </div>
</div>

[#--文件上传进度--]
<div class="file_upload_load"></div>
    [#include 'shared/confirmation.ftl'/]
[/@override]

[@override name="bottomResources"]
    [@super /]
<script src="[@spring.url '/resources/js/upload/webuploader.js'/]"></script>
<script src="[@spring.url '/resources/js/colorbox/jquery.colorbox-min.js' /]"></script>
<script src="[@spring.url '/resources/js/layer/layer.js'/]"></script>
<script src="[@spring.url '/resources/js/datetimepicker/jquery.datetimepicker.full.js'/]"></script>
<script type="text/javascript">

    $(".identity-but").on("click", function () {
        var _src = $(this).attr("data");
        var _that = $("#Pic");
        $(_that).find(".identity-pic").attr("src", _src)
        _that.modal();
    })
    $(".change-policyStatus").on("click", function () {
        var _that = $("#policy-status");
        var _id = $(this).attr("data-id");
        var _version = $(this).attr("data-version");
        var _policyNo = $(this).attr("data-no");
        _that.find("input[name='id']").val(_id);
        _that.find("input[name='version']").val(_version);
        _that.find("input[name='policyNo']").val(_policyNo);
        _that.modal();
    })

    var colorBox = $('.colorbox');
    $.each(colorBox, function (a, b) {
        var colorList = $(b).children(".img-view");
        $(colorList).colorbox({
            photo: true,
            opacity: .9,
            scalePhotos: true,
            scrolling: false,
            width: '480px',
            height: '500px',
            transition: 'elastic',
            rel: 'group' + a,
            href: function () {
                var url = $(this).children().data('img');
                return url;
            }
        });
    });

    $.ajax({
        url: "/policy/count",
        type: "post",
        success: function (data) {
            var data;
            if (typeof data == "object") {
                data = data
            } else {
                data = JSON.parse(data)
            }
            console.log(data);
            $(".policy_all").text(data["all"]);
            $(".policy_back").text(data["back"]);
            $(".policy_claim").text(data["claim"]);
        }
    })

    $.datetimepicker.setLocale('en');
    $('#starTime').datetimepicker({
        dayOfWeekStart: 1,
        lang: 'en',
    });
    $('#endTime').datetimepicker({
        dayOfWeekStart: 1,
        lang: 'en',
    });
    $('#beginDate').datetimepicker({
        dayOfWeekStart: 1,
        lang: 'en',
    });
    $('#endDate').datetimepicker({
        dayOfWeekStart: 1,
        lang: 'en',
    });

</script>
[/@override]
[@extends name="/decorator.ftl"/]

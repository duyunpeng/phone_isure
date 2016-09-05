[@override name="title"]保单管理 - 保单查看[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a href="/">首页</a></li>
    <li><a href="${url}">保单管理</a></li>
    <li>保单查看</li>
</ul>
[/@override]

[@override name="headerText"]
保单 查看
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="row">
    <div class="col-lg-12">
        <ul class="contract-show">
            <li>
                <span class="col-md-3">保单号</span>
                <div class="col-md-8 contract-box">${policy.policyNo!}</div>
            </li>
            <li>
                <span class="col-md-3">代理商户</span>
                <div class="col-md-8 contract-box">${policy.merchant.merchantName!}</div>
            </li>
            <li>
                <span class="col-md-3">手机型号</span>
                <div class="col-md-8 contract-box">${policy.phoneModel!}</div>
            </li>
            <li>
                <span class="col-md-3">手机串号</span>
                <div class="col-md-8 contract-box">${policy.IMEI!}</div>
            </li>
            <li>
                <span class="col-md-3">保单费用</span>
                <div class="col-md-8 contract-box">${policy.policyFee!}</div>
            </li>
            <li>
                <span class="col-md-3">保单金额</span>
                <div class="col-md-8 contract-box">${policy.policyMoney!}</div>
            </li>
            <li>
                <span class="col-md-3">参保人姓名</span>
                <div class="col-md-8 contract-box">${policy.insuredName!}</div>
            </li>
            <li>
                <span class="col-md-3">参保人手机号码</span>
                <div class="col-md-8 contract-box">${policy.insuredPhone!}</div>
            </li>
            <li>
                <span class="col-md-3">参保手机新机图片</span>
                <div class="col-md-8 contract-box">
                    [#if policy.insuredBeginPicture??]
                        [#list policy.insuredBeginPicture as pic]
                            <img src="${pic.picPath!}"/>
                        [/#list]
                    [/#if]
                </div>
            </li>
            <li>
                <span class="col-md-3">参保手机理赔之后照片</span>
                <div class="col-md-8 contract-box">
                    [#if policy.insuredAfterPicture??]
                        [#list policy.insuredAfterPicture as pic]
                            <img src="${pic.picPath!}"/>
                        [/#list]
                    [/#if]
                </div>
            </li>
            <li>
                <span class="col-md-3">证件类型</span>
                <div class="col-md-8 contract-box">${policy.idType.name!}</div>
            </li>
            <li>
                <span class="col-md-3">证件号码</span>
                <div class="col-md-8 contract-box">${policy.idNumber!}</div>
            </li>
            <li>
                <span class="col-md-3">参保时间开始</span>
                <div class="col-md-8 contract-box">${policy.insuredBeginDate!}</div>
            </li>
            <li>
                <span class="col-md-3">参保时间结束</span>
                <div class="col-md-8 contract-box">${policy.insuredEndDate!}</div>
            </li>
            <li>
                <span class="col-md-3">状态</span>
                <div class="col-md-8 contract-box">${policy.policyStatus.getName()!}</div>
            </li>
            <div>
                <div class="col-sm-offset-6 col-sm-12">
                    <a href="[@spring.url url /]" class="btn btn-default">返回列表</a>
                </div>
            </div>
        </ul>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]
<script type="text/javascript">
    var data_list = $(".data-list");
    data_list.slimScroll({
        height: '600px'
    });
</script>
[/@override]
[@extends name="/decorator.ftl"/]
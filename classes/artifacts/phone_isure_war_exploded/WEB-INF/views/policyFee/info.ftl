[@override name="title"]手机型号管理 - 手机型号查看[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a href="/">首页</a></li>
    <li><a href="/policy_fee/pagination">手机型号</a></li>
    <li>手机型号查看</li>
</ul>
[/@override]

[@override name="headerText"]
手机型号 查看
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="row">
    <div class="col-lg-12">
        <ul class="contract-show">
            <li>
                <span class="col-md-3">手机品牌</span>
                <div class="col-md-8 contract-box">${policyFee.phoneBrand.name!}</div>
            </li>
            <li>
                <span class="col-md-3">手机型号</span>
                <div class="col-md-8 contract-box">${policyFee.phoneModel!}</div>
            </li>
            <li>
                <span class="col-md-3">保单费用</span>
                <div class="col-md-8 contract-box">${policyFee.policyFee!}</div>
            </li>
            <li>
                <span class="col-md-3">排序</span>
                <div class="col-md-8 contract-box">${policyFee.sort!}</div>
            </li>
            <li>
                <span class="col-md-3">状态</span>
                <div class="col-md-8 contract-box">${(policyFee.status.getName())!}</div>
            </li>
            <div>
                <div class="col-sm-offset-6 col-sm-12">
                    <a href="[@spring.url '/policy_fee/create' /]" class="btn btn-success">再创建一个</a>
                    <a href="[@spring.url '/policy_fee/pagination' /]" class="btn btn-default">返回列表</a>
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
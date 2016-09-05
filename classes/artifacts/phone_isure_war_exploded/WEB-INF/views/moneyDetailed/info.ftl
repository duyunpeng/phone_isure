[@override name="title"]资金明细管理 - 资金明细查看[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a href="/">首页</a></li>
    <li><a href="/money_detailed/pagination">资金明细管理</a></li>
    <li>资金明细查看</li>
</ul>
[/@override]

[@override name="headerText"]
资金明细 查看
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="row">
    <div class="col-lg-12">
        <ul class="contract-show">
            <li>
                <span class="col-md-3">商户</span>
                <div class="col-md-8 contract-box">${moneyDetailed.merchant!}</div>
            </li>
            <li>
                <span class="col-md-3">流向类型</span>
                <div class="col-md-8 contract-box">${moneyDetailed.flowType!}</div>
            </li>
            <li>
                <span class="col-md-3">金额</span>
                <div class="col-md-8 contract-box">${moneyDetailed.money!}</div>
            </li>
            <li>
                <span class="col-md-3">说明</span>
                <div class="col-md-8 contract-box">${moneyDetailed.explain!}</div>
            </li>
            <div>
                <div class="col-sm-offset-6 col-sm-12">
                    <a href="[@spring.url '/money_detailed/pagination' /]" class="btn btn-default">返回列表</a>
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
[@override name="title"]充值管理 - 充值查看[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a href="/">首页</a></li>
    <li><a href="/area/pagination">充值管理</a></li>
    <li>充值查看</li>
</ul>
[/@override]

[@override name="headerText"]
充值 查看
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="row">
    <div class="col-lg-12">
        <ul class="contract-show">
            <li>
                <span class="col-md-3">充值商户</span>
                <div class="col-md-8 contract-box">${area.name!}</div>
            </li>
            <li>
                <span class="col-md-3">充值金额</span>
                <div class="col-md-8 contract-box">${area.shortName!}</div>
            </li>
            <li>
                <span class="col-md-3">支付时间</span>
                <div class="col-md-8 contract-box">${area.parent.name!}</div>
            </li>
            <li>
                <span class="col-md-3">支付方式</span>
                <div class="col-md-8 contract-box">${area.sort!}</div>
            </li>
            <li>
                <span class="col-md-3">支付号</span>
                <div class="col-md-8 contract-box">[@mc.dateShow area.createDate /]</div>
            </li>
            <li>
                <span class="col-md-3">是否支付</span>
                <div class="col-md-8 contract-box">${area.longitude!}</div>
            </li>
            <div>
                <div class="col-sm-offset-6 col-sm-12">
                    <a href="[@spring.url '/area/create' /]" class="btn btn-success">再创建一个</a>
                    <a href="[@spring.url '/area/pagination' /]" class="btn btn-default">返回列表</a>
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
[@override name="title"]手机品牌管理 - 手机品牌查看[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a href="/">首页</a></li>
    <li><a href="/phone_brand/pagination">手机品牌</a></li>
    <li>手机品牌查看</li>
</ul>
[/@override]

[@override name="headerText"]
手机品牌 查看
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="row">
    <div class="col-lg-12">
        <ul class="contract-show">
            <li>
                <span class="col-md-3">品牌名称</span>
                <div class="col-md-8 contract-box">${phoneBrand.name!}</div>
            </li>
            <li>
                <span class="col-md-3">排序</span>
                <div class="col-md-8 contract-box">${phoneBrand.sort!}</div>
            </li>
            <li>
                <span class="col-md-3">状态</span>
                <div class="col-md-8 contract-box">${(phoneBrand.status.getName())!}</div>
            </li>
            <div>
                <div class="col-sm-offset-6 col-sm-12">
                    <a href="[@spring.url '/phone_brand/create' /]" class="btn btn-success">再创建一个</a>
                    <a href="[@spring.url '/phone_brand/pagination' /]" class="btn btn-default">返回列表</a>
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
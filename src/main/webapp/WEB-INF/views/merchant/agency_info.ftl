[@override name="title"]代理管理 - 代理查看[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a href="/">首页</a></li>
    <li><a href="/merchant/pagination">代理管理</a></li>
    <li>代理查看</li>
</ul>
[/@override]

[@override name="headerText"]
代理 查看
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="row">
    <div class="col-lg-12">
        <ul class="contract-show">
            <li>
                <span class="col-md-3">代理名称</span>
                <div class="col-md-8 contract-box">${merchant.merchantName!}</div>
            </li>
            <li>
                <span class="col-md-3">代理联系人</span>
                <div class="col-md-8 contract-box">${merchant.contacts!}</div>
            </li>
            <li>
                <span class="col-md-3">代理联系人电话</span>
                <div class="col-md-8 contract-box">${merchant.contactsPhone!}</div>
            </li>
            <li>
                <span class="col-md-3">地区</span>
                <div class="col-md-8 contract-box">${merchant.area.name!}</div>
            </li>
            <li>
                <span class="col-md-3">详细地区</span>
                <div class="col-md-8 contract-box">${merchant.detailedArea!}</div>
            </li>
            <li>
                <span class="col-md-3">备注</span>
                <div class="col-md-8 contract-box">${merchant.remarks!}</div>
            </li>
            <li>
                <span class="col-md-3">余额</span>
                <div class="col-md-8 contract-box">${merchant.money!}</div>
            </li>
            <li>
                <span class="col-md-3">认证状态</span>
                <div class="col-md-8 contract-box">${(merchant.authStatus.getName()!)}</div>
            </li>
            <li>
                <span class="col-md-3">认证失败原因</span>
                <div class="col-md-8 contract-box">${merchant.authFailureExplain!}</div>
            </li>
            <li>
                <span class="col-md-3">用户类型</span>
                <div class="col-md-8 contract-box">${merchant.userType.getName()!}</div>
            </li>
            <li>
                <span class="col-md-3">代理</span>
                <div class="col-md-8 contract-box">${merchant.agent.userName!}</div>
            </li>

            <li>
                <span class="col-md-3">用户名</span>
                <div class="col-md-8 contract-box">${merchant.userName!}</div>
            </li>
            <li>
                <span class="col-md-3">最后登录ip</span>
                <div class="col-md-8 contract-box">${merchant.lastLoginIP!}</div>
            </li>
            <li>
                <span class="col-md-3">最后登录时间</span>
                <div class="col-md-8 contract-box">[@mc.dateShow merchant.lastLoginDate/]</div>
            </li>
            <li>
                <span class="col-md-3">最后登录平台</span>
                <div class="col-md-8 contract-box">${merchant.lastLoginPlatform!}</div>
            </li>
            <li>
                <span class="col-md-3">用户角色</span>
                <div class="col-md-8 contract-box">${merchant.roles[0].name!}</div>
            </li>
            <li>
                <span class="col-md-3">邮箱</span>
                <div class="col-md-8 contract-box">${merchant.email!}</div>
            </li>
            <li>
                <span class="col-md-3">应用标识</span>
                <div class="col-md-8 contract-box">${merchant.appKey.description!}</div>
            </li>
            <li>
                <span class="col-md-3">状态</span>
                <div class="col-md-8 contract-box">${merchant.status.getName()!}</div>
            </li>
            <li>
                <span class="col-md-3">头像</span>
                <div class="col-md-8 contract-box"><img src="${merchant.headPic.picPath!}"></div>
            </li>

            <div>
                <div class="col-sm-offset-6 col-sm-12">
                    <a href="[@spring.url '/merchant/agency_pagination' /]" class="btn btn-default">返回列表</a>
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
[@override name="title"]代理管理 - 下级代理创建[/@override]
[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a href="/">首页</a></li>
    <li><a href="/merchant/agency_pagination">代理管理</a></li>
    <li>下级代理创建</li>
</ul>
[/@override]

[@override name="headerText"]
下级代理 创建
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="row">
    <div class="col-lg-8">
        <form class="form-horizontal" action="/merchant/create_agent" method="post" data-parsley-validate>

            [@spring.bind "command.userName"/]
            <div class="form-group">
                <label for="userName" class="col-md-3 control-label">账号名*</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" id="userName" name="userName"
                           value="${command.userName!}" placeholder="输入用户名"
                           data-parsley-required="true" data-parsley-required-messages="用户名不能为空"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "userName" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.password"/]
            <div class="form-group">
                <label for="password" class="col-md-3 control-label">密码*</label>
                <div class="col-md-9">
                    <input type="password" class="form-control" id="password" name="password"
                           value="${command.password!}" placeholder="输入密码"
                           data-parsley-required="true" data-parsley-required-messages="密码不能为空"
                           data-parsley-minlength="6" data-parsley-trigger="change"/>
                    [@spring.showErrors "password" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.confirmPassword"/]
            <div class="form-group">
                <label for="confirmPassword" class="col-md-3 control-label">确认密码*</label>
                <div class="col-md-9">
                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword"
                           value="${command.confirmPassword!}" placeholder="输入确认密码"
                           data-parsley-required="true" data-parsley-required-messages="确认密码不能为空"
                           data-parsley-equalto="#password" data-parsley-equalto-messages="两次密码输入不一致"
                           data-parsley-minlength="6" data-parsley-trigger="change"/>
                    [@spring.showErrors "confirmPassword" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.merchantName"/]
            <div class="form-group">
                <label for="merchantName" class="col-md-3 control-label">代理名称*</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" id="merchantName" name="merchantName"
                           value="${command.merchantName!}" placeholder="输入代理名称"
                           data-parsley-required="true" data-parsley-required-messages="代理名称不能为空"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "merchantName" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.contacts"/]
            <div class="form-group">
                <label for="contacts" class="col-md-3 control-label">代理联系人*</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" id="contacts" name="contacts"
                           value="${command.contacts!}" placeholder="输入代理联系人"
                           data-parsley-required="true" data-parsley-required-messages="代理联系人不能为空"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "contacts" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.contactsPhone"/]
            <div class="form-group">
                <label for="contactsPhone" class="col-md-3 control-label">代理联系人*</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" id="contactsPhone" name="contactsPhone"
                           value="${command.contactsPhone!}" placeholder="输入代理联系人电话"
                           data-parsley-required="true" data-parsley-required-messages="代理联系人不能为空电话"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "contactsPhone" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.area"/]
            <div class="form-group">
                <label for="parent" class="col-md-3 control-label">区域</label>
                <div class="col-md-9 area-cascade">
                    <div class="col-lg-4 no-padding">
                        <select class="form-control">

                        </select>
                    </div>
                </div>
            </div>

            [@spring.bind "command.detailedArea"/]
            <div class="form-group">
                <label for="detailedArea" class="col-md-3 control-label">详细地址*</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" id="detailedArea" name="detailedArea"
                           value="${command.detailedArea!}" placeholder="输入详细地址"
                           data-parsley-required="true" data-parsley-required-messages="详细地址不能为空电话"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "detailedArea" "parsley-required"/]
                </div>
            </div>

            <div class="text-center m-top-md">
                <button type="reset" class="btn btn-default">重置</button>
                <button type="submit" class="btn btn-success">创建</button>
            </div>
        </form>
    </div>
    <div class="col-lg-3">
        <ul class="blog-sidebar-list font-18">创建注意事项
            <li>*位必填项</li>
        </ul>
    </div>
</div>

[/@override]

[@override name="bottomResources"]
    [@super /]
<script src="[@spring.url '/resources/js/area.js'/]"></script>
<script type="text/javascript">
    $(".area-cascade").areaCascade("area");
</script>
[/@override]
[@extends name="/decorator.ftl"/]
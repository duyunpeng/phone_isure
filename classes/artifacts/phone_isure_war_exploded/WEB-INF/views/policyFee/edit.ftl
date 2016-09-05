[@override name="title"]手机型号管理 - 手机型号修改[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a href="/">首页</a></li>
    <li><a href="/policy_fee/pagination">手机型号</a></li>
    <li>手机型号修改</li>
</ul>
[/@override]

[@override name="headerText"]
手机型号 修改
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="row">
    <div class="col-lg-8">
        <form class="form-horizontal" action="/policy_fee/edit" method="post" data-parsley-validate>

            <input type="hidden" name="id" value="${policyFee.id!command.id}" />
            <input type="hidden" name="version" value="${policyFee.version!command.version}" />

            [@spring.bind "command.phoneBrand"/]
            <div class="form-group">
                <label for="name" class="col-md-3 control-label">手机品牌*</label>
                <div class="col-md-9">
                    <select class="form-control" name="phoneBrand" id="phoneBrand" data="${policyFee.phoneBrand.id}"
                            data-parsley-required="true" data-parsley-required-messages="请选择品牌"
                            data-parsley-trigger="change">
                    </select>
                    [@spring.showErrors "phoneBrand" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.phoneModel"/]
            <div class="form-group">
                <label for="shortName" class="col-md-3 control-label">手机型号*</label>
                <div class="col-md-9">
                    <input class="form-control" id="phoneModel" name="phoneModel"
                           value="${policyFee.phoneModel!command.phoneModel}" placeholder="输入手机型号"
                           data-parsley-required="true" data-parsley-required-messages="手机型号不能为空"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "phoneModel" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.policyFee"/]
            <div class="form-group">
                <label for="longitude" class="col-md-3 control-label">保单费用*</label>
                <div class="col-md-9">
                    <input class="form-control" id="policyFee" name="policyFee"
                           value="${policyFee.policyFee!command.policyFee}" placeholder="输入保单费用"
                           data-parsley-required="true" data-parsley-required-messages="保单费用不能为空"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "policyFee" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.policyMoney"/]
            <div class="form-group">
                <label for="policyMoney" class="col-md-3 control-label">保单金额*</label>
                <div class="col-md-9">
                    <input class="form-control" id="policyMoney" name="policyMoney"
                           value="${policyFee.policyMoney!command.policyMoney}" placeholder="输入保单金额"
                           data-parsley-required="true" data-parsley-required-messages="保单金额不能为空"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "policyMoney" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.sort"/]
            <div class="form-group">
                <label for="sort" class="col-md-3 control-label">排序值*</label>
                <div class="col-md-9">
                    <input class="form-control" id="sort" name="sort"
                           value="${policyFee.sort!command.sort}" placeholder="输入排序值"
                           data-parsley-required="true" data-parsley-required-messages="排序值不能为空"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "sort" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.status"/]
            <div class="form-group">
                <label for="status" class="col-md-3 control-label">状态*</label>
                <div class="col-md-9">
                    <select class="form-control" name="status" id="status"
                            data-parsley-required="true" data-parsley-required-messages="请选择状态"
                            data-parsley-trigger="change">
                        [#assign status = (policyFee.status!command.status)?default("") /]
                        <option value="">请选择</option>
                        <option value="ENABLE" [@mc.selected status "ENABLE"/]>启用</option>
                        <option value="DISABLE" [@mc.selected status "DISABLE"/]>禁用</option>
                    </select>
                    [@spring.showErrors "status" "parsley-required"/]
                </div>
            </div>

            <div class="text-center m-top-md">
                <button type="reset" class="btn btn-default">重置</button>
                <button type="submit" class="btn btn-success">修改</button>
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
<script src="[@spring.url '/resources/js/ajax.js'/]"></script>
<script>
    $("#phoneBrand").selectAjaxData({url: "/phone_brand/list"});
</script>
[/@override]
[@extends name="/decorator.ftl"/]
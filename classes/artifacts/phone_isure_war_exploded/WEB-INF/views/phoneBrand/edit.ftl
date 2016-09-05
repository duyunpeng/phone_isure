[@override name="title"]手机品牌管理 - 手机品牌修改[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a href="/">首页</a></li>
    <li><a href="/phone_brand/pagination">手机品牌管理</a></li>
    <li>手机品牌修改</li>
</ul>
[/@override]

[@override name="headerText"]
手机品牌 修改
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="row">
    <div class="col-lg-8">
        <form class="form-horizontal" action="/phone_brand/edit" method="post" data-parsley-validate>

            <input type="hidden" name="id" value="${phoneBrand.id!command.id}" />
            <input type="hidden" name="version" value="${phoneBrand.version!command.version}" />

            [@spring.bind "command.name"/]
            <div class="form-group">
                <label for="name" class="col-md-3 control-label">名称*</label>
                <div class="col-md-9">
                    <input class="form-control" id="name" name="name"
                           value="${phoneBrand.name!command.name}" placeholder="输入品牌名称"
                           data-parsley-required="true" data-parsley-required-messages="区域名称不能为空"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "name" "parsley-required"/]
                </div>
            </div>


            [@spring.bind "command.sort"/]
            <div class="form-group">
                <label for="sort" class="col-md-3 control-label">品牌排序值*</label>
                <div class="col-md-9">
                    <input class="form-control" id="sort" name="sort"
                           value="${phoneBrand.sort!command.sort}" placeholder="输入品牌排序值"
                           data-parsley-required="true" data-parsley-required-messages="品牌排序值不能为空"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "sort" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.status"/]
            <div class="form-group">
                <label for="status" class="col-md-3 control-label">状态*</label>
                <div class="col-md-9">
                    <select class="form-control" name="status" id="status"
                            data-parsley-required="true" data-parsley-required-messages="请选择品牌状态"
                            data-parsley-trigger="change">
                        [#assign status = (phoneBrand.status!command.status)?default("") /]
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
<script src="[@spring.url '/resources/js/area.js'/]"></script>
<script>
    $(".area-cascade").areaCascade("parent");
</script>
[/@override]
[@extends name="/decorator.ftl"/]
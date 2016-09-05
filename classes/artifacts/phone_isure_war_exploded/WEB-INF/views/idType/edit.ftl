[@override name="title"]证件类型管理 - 证件类型修改[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a href="/">首页</a></li>
    <li><a href="/id_type/pagination">证件类型管理</a></li>
    <li>证件类型修改</li>
</ul>
[/@override]

[@override name="headerText"]
证件类型 修改
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="row">
    <div class="col-lg-8">
        <form class="form-horizontal" action="/id_type/edit" method="post" data-parsley-validate>
            <input type="hidden" name="id" value="${idType.id!command.id}"/>
            <input type="hidden" name="version" value="${idType.version!command.version}" />
            <input type="hidden" name="status" value="${idType.status!command.status}" />
            [@spring.bind "command.name"/]
            <div class="form-group">
                <label for="name" class="col-md-3 control-label">证件名称*</label>
                <div class="col-md-9">
                    <input class="form-control" id="name" name="name"
                           value="${idType.name!command.name}" placeholder="输入证件名称"
                           data-parsley-required="true" data-parsley-required-messages="证件名称不能为空"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "name" "parsley-required"/]
                </div>
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
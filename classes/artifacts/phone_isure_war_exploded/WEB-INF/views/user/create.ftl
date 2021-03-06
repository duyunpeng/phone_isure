[@override name="title"]用户管理 - 用户创建[/@override]
[@override name="topResources"]
    [@super /]
[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a href="/">首页</a></li>
    <li><a href="/user/pagination">用户管理</a></li>
    <li>用户创建</li>
</ul>
[/@override]

[@override name="headerText"]
用户 创建
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="row">
    <div class="col-lg-8">
        <form class="form-horizontal" action="/user/create" method="post" data-parsley-validate>

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

            [@spring.bind "command.roles"/]
            <div class="form-group">
                <label for="description" class="col-md-3 control-label">角色*</label>
                <div class="col-md-9">
                    <div class="col-md-10 div-input role-data"></div>
                    <input type="hidden" name="roles" id="role"/>
                    <button type="button" class="btn btn-primary col-md-2 role-modal-search-modal">点击选择角色</button>
                    [@spring.showErrors "roles" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.appKey"/]
            <div class="form-group">
                <label for="appKey" class="col-md-3 control-label">AppKey</label>
                <div class="col-md-9">
                    <select class="form-control" id="appKey" name="appKey" data="${command.appKey!}"
                            data-parsley-required="true" data-parsley-required-messages="请选择AppKey"
                            data-parsley-trigger="change">

                    </select>
                    [@spring.showErrors "appKey" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.status"/]
            <div class="form-group">
                <label for="status" class="col-md-3 control-label">用户状态*</label>
                <div class="col-md-9">
                    <select class="form-control" name="status" id="status"
                            data-parsley-required="true" data-parsley-required-messages="请选择角色状态"
                            data-parsley-trigger="change">
                        [#assign status = (command.status!)?default("") /]
                        <option value="">请选择</option>
                        <option value="ENABLE" [@mc.selected status "ENABLE"/]>启用</option>
                        <option value="DISABLE" [@mc.selected status "DISABLE"/]>禁用</option>
                    </select>
                    [@spring.showErrors "status" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.email"/]
            <div class="form-group">
                <label for="email" class="col-md-3 control-label">邮箱</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" id="email" name="email" value="${command.email!}"
                           placeholder="输入邮箱" data-parsley-email/>
                    [@spring.showErrors "email" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.name"/]
            <div class="form-group">
                <label for="name" class="col-md-3 control-label">昵称</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" id="name" name="name" value="${command.name!}"
                           placeholder="输入昵称" data-parsley-email/>
                    [@spring.showErrors "name" "parsley-required"/]
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

<!-- 选择角色弹窗 -->
<div class="modal fade" id="role-modalSearch">
    <div class="modal-content">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true"
                                                                               class="red">&times;</span><span
                        class="sr-only">Close</span></button>
                <p class="modal-title">角色列表--勾选添加到已选角色列表
                    <small class="text-muted"></small>
                </p>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-9 input-list">
                        <!-- 查询表单 -->
                        <form class="form-inline margin-md" role="form" action="[@spring.url '/permission/list' /]">
                            <div class="form-group">
                                <label class="control-label col-md-5" for="permissionName">角色名称</label>
                                <div class="col-md-7">
                                    <input type="text" class="form-control" id="roleName" name="roleName" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-6" for="status">角色状态</label>
                                <div class="col-md-6">
                                    <select name="status" id="status" class="form-control">
                                        <option value="ALL">全部</option>
                                        <option value="ENABLE">启用</option>
                                        <option value="DISABLE">禁用</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-5" for="appKey-modal">AppKey</label>
                                <div class="col-md-7">
                                    <select class="form-control" id="appKey-modal" name="appKey">

                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="button" class="btn btn-md btn-success">查询</button>
                            </div>
                        </form>
                        <!-- table数据 -->
                        <table class="table table-bordered table-sortable table-hover">
                            <thead>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                        <!-- 分页信息 -->
                        <div class="row">
                            <div class="col-sm-4 text-center">
                                <small class="inline table-options paging-info">
                                </small>
                            </div>
                            <div class="col-sm-4 text-right sm-center">
                                <ul class="pagination pagination-sm no-margin pagination-custom no-m-left">
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="tile-header">
                            <h3><strong>已选</strong>列表</h3>
                        </div>
                        <div class="tile-body selector-box modal-search-selector">
                            <button class="btn margin-top-15 btn-success role-modal-search-hide-modal">确定</button>
                            <button class="btn margin-top-15 btn-danger role-selector-remove-all">删除全部</button>
                            <div class="role-selector-box-data"></div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]
<script src="[@spring.url '/resources/js/ajax.js'/]"></script>
<script src="[@spring.url '/resources/js/modal-search-yjh.js'/]"></script>
<script src="[@spring.url '/resources/js/area.js'/]"></script>
<script type="text/javascript">
    //加载appKey数据
    $("#appKey").selectAjaxData({url: "/app_key/all_list"});
    $("#appKey-modal").selectAjaxData({url: "/app_key/all_list"});

    $(".area-cascade").areaCascade("area");

    var _roleName = $(".role-data");
    var _roleData = $("#role");
    var _oldRoleIds = [];
    _oldRoleIds.push(_roleData.val());
    var _roleModal = new ModalSearch({
        url: "/role/list",
        pageSize: 6,
        isSingle: true,
        id: "role-modalSearch",
        openModalClass: ".role-modal-search-modal",
        hideModalClass: ".role-modal-search-hide-modal",
        removeAllClass: ".role-selector-remove-all",
        selectorBoxClass: ".role-selector-box-data",
        headers: ['角色名称', '角色描述', 'AppKey'],
        rowDataName: ["name", "description", "appKey.name"],
        selectorDateName: ["name"],
        oldDataIds: _oldRoleIds,
        hideModalHandler: function (jsonDataArr) {
            _roleName.text("");
            _roleData.val("");
            $.each(jsonDataArr, function (a, b) {
                _roleName.text(b.name + "-------" + b.description);
                _roleData.val(b.id);
            })
        }
    });
</script>
[/@override]
[@extends name="/decorator.ftl"/]
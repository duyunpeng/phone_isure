[@override name="title"]保单管理 - 保单退单[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a href="/">首页</a></li>
    <li><a href="${url}">保单管理</a></li>
    <li>保单退单处理</li>
</ul>
[/@override]

[@override name="headerText"]
保单退单处理
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="row">
    <div class="col-lg-8">
        <form class="form-horizontal" action="/policy/handle_policy" method="post" data-parsley-validate>

            <input type="hidden" name="id" value="${policy.id!command.id}"/>
            <input type="hidden" name="version" value="${policy.version!command.version}"/>

            <div class="form-group">
                <label for="name" class="col-md-3 control-label">保单号*</label>
                <div class="col-md-9">
                    <input class="form-control" id="policyNo" name="policyNo"
                           value="${policy.policyNo!command.policyNo}" disabled/>
                </div>
            </div>

            <div class="form-group">
                <label for="name" class="col-md-3 control-label">代理商户*</label>
                <div class="col-md-9">
                    <input class="form-control" id="merchant" name="merchant"
                           value="${policy.merchant.userName!command.merchant}" disabled/>
                </div>
            </div>

            <div class="form-group">
                <label for="name" class="col-md-3 control-label">手机型号*</label>
                <div class="col-md-9">
                    <input class="form-control" id="phoneModel" name="phoneModel" value="${policy.phoneModel!}"
                           disabled/>
                </div>
            </div>

            <div class="form-group">
                <label for="name" class="col-md-3 control-label">保单费用*</label>
                <div class="col-md-9">
                    <input class="form-control" id="policyFee" name="policyFee"
                           value="${policy.policyFee!command.policyFee}" disabled/>
                </div>
            </div>

            <div class="form-group">
                <label for="name" class="col-md-3 control-label">参保人姓名*</label>
                <div class="col-md-9">
                    <input class="form-control" id="insuredName" name="insuredName"
                           value="${policy.insuredName!command.insuredName}" disabled/>
                </div>
            </div>

            <div class="form-group">
                <label for="name" class="col-md-3 control-label">参保人手机号码*</label>
                <div class="col-md-9">
                    <input class="form-control" id="insuredPhone" name="insuredPhone"
                           value="${policy.insuredPhone!command.insuredPhone}" disabled/>
                </div>
            </div>

            <div class="form-group">
                <label for="name" class="col-md-3 control-label">参保图片*</label>
                <div class="col-md-9">
                    <ul>
                        [#list policy.insuredBeginPicture as price]
                            <li>
                                <img src="${price.mediumPicPath!}">
                                <input type="hidden" value="${price.mediumPicPath!}" name="insuredBeginPicture"/>
                            </li>
                        [/#list]
                    </ul>
                </div>
            </div>

            <div class="form-group">
                <label for="name" class="col-md-3 control-label">证件类型*</label>
                <div class="col-md-9">
                    <input class="form-control" id="idType" name="idType"
                           value="${policy.idType.getName()!command.idType}" disabled/>
                </div>
            </div>

            <div class="form-group">
                <label for="name" class="col-md-3 control-label">证件号码*</label>
                <div class="col-md-9">
                    <input class="form-control" id="idNumber" name="idNumber"
                           value="${policy.idNumber!command.idNumber}" disabled/>
                </div>
            </div>

            <div class="form-group">
                <label for="name" class="col-md-3 control-label">参保开始时间*</label>
                <div class="col-md-9">
                    <input class="form-control" id="insuredBeginDate" name="insuredBeginDate"
                           value="[@mc.dateShow policy.insuredBeginDate!command.insuredBeginDate/]" disabled/>
                </div>
            </div>

            <div class="form-group">
                <label for="name" class="col-md-3 control-label">状态*</label>
                <div class="col-md-9">
                    <input class="form-control" id="policyStatus" name="policyStatus"
                           value="${policy.policyStatus.getName()!command.policyStatus}" disabled/>
                </div>
            </div>

            <div class="form-group">
                <label for="agent" class="col-md-3 control-label">上传理赔凭证</label>
                <div class="col-md-9">
                    <button type="button" class="btn btn-sm btn-primary img-upload" >点击添加图片</button>
                    <ul class="img-box">
                    </ul>
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
<script src="[@spring.url '/resources/js/upload/webuploader.js'/]"></script>
<script src="[@spring.url '/resources/js/layer/layer.js'/]"></script>
<script type="text/javascript">
    // 初始化Web Uploader
    uploader = WebUploader.create({
        // 自动上传。
        auto: true,
        swf:  '/resources/js/upload/Uploader.swf',
        // 文件接收服务端。
        server: '/upload/img_upload',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '.img-upload',
        // 只允许选择文件，可选。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }
    });
    // 当有文件添加进来的时候
    uploader.on('fileQueued', function (file) {
        uploader.makeThumb(file, function (error, src) {
            if (error) {
                return;
            }
//                alert(src);
        });
    });
    uploader.on('uploadProgress', function (file, percentage) {
        $('html').addClass('.file_upload_mask');
        $('.file_upload_load').show();
    });

    uploader.on('uploadSuccess', function (file, result) {
        $('html').removeClass('.file_upload_mask');
        $('.file_upload_load').hide();
        layer.msg("上传成功！", {icon: 1});
        var url = result.files[0].url;
        var $ul = $(".img-box");
        $ul.append('<li><img src=' + url + '/><input type="hidden" value=' + url + ' name="insuredAfterPicture"/><div><a href="#" class="btn-sm btn-danger del-img">删除</a></div></li>');
    });

    uploader.on('uploadError', function (handler) {
        $('html').removeClass('.file_upload_mask');
        $('.file_upload_load').hide();
        layer.msg("上传失败！");
    });

    $(".img-box").on("click", "a", function () {
        $(this).parent().parent().remove();
    })
</script>
[/@override]
[@extends name="/decorator.ftl"/]
/**
 * 设置Summernote
 */
function initSummernoteOfAddPage() {
    $('.summernote').summernote({
        lang: 'zh-CN',
        dialogsInBody: true,
        callbacks: {
            onChange: function (contents, $edittable) {
                $("input[name='" + this.id + "']").val(contents);
            },
            onImageUpload: function (files) {
                var obj = this;
                var data = new FormData();
                data.append("file", files[0]);
                $.ajax({
                    type: "post",
                    url: ctx + "common/upload",
                    data: data,
                    cache: false,
                    contentType: false,
                    processData: false,
                    dataType: 'json',
                    success: function (result) {
                        if (result.code == web_status.SUCCESS) {
                            $('#' + obj.id).summernote('insertImage', result.url);
                        } else {
                            $.modal.alertError(result.msg);
                        }
                    },
                    error: function (error) {
                        $.modal.alertWarning("图片上传失败。");
                    }
                });
            }
        }

    });
}

function initSummernoteOfEditPage() {
    $('.summernote').each(function (i) {
        $('#' + this.id).summernote({
            lang: 'zh-CN',
            dialogsInBody: true,
            callbacks: {
                onChange: function (contents, $edittable) {
                    $("input[name='" + this.id + "']").val(contents);
                },
                onImageUpload: function (files) {
                    var obj = this;
                    var data = new FormData();
                    data.append("file", files[0]);
                    $.ajax({
                        type: "post",
                        url: ctx + "common/upload",
                        data: data,
                        cache: false,
                        contentType: false,
                        processData: false,
                        dataType: 'json',
                        success: function (result) {
                            if (result.code == web_status.SUCCESS) {
                                $('#' + obj.id).summernote('insertImage', result.url);
                            } else {
                                $.modal.alertError(result.msg);
                            }
                        },
                        error: function (error) {
                            $.modal.alertWarning("图片上传失败。");
                        }
                    });
                }
            }
        });
        var content = $("input[name='" + this.id + "']").val();
        $('#' + this.id).summernote('code', content);
    })
}

function initFileInput() {
    // 多图上传
    $("#multipleFile").fileinput({
        uploadUrl: ctx + 'common/uploads',
        uploadAsync: false,
        showUpload: false,
    })
}
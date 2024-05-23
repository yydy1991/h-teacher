(function ($) {
    $.extend({
        barracuda: {
            document: {
                // 将要上传的文件
                filesToUpload: [],
                initFileDiv: function (fileDivId) {
                    let html = '<div class="fileinput fileinput-new input-group" data-provides="fileinput">\n' +
                        '                        <div onclick="$(\'#uploadDocumentInput\').click()" class="form-control" data-trigger="fileinput">\n' +
                        '                            <i class="glyphicon glyphicon-file fileinput-exists"></i>\n' +
                        '                            <span class="fileinput-filename"></span>\n' +
                        '                        </div>\n' +
                        '                        <span class="input-group-addon btn btn-white btn-file">\n' +
                        '                            <span class="fileinput-new">选择文件</span>\n' +
                        '                            <input id="uploadDocumentInput" type="file" multiple>\n' +
                        '                        </span>\n' +
                        '                    </div>\n' +
                        '                    <div class="files-name-div">\n' +
                        '                    </div>'
                    $("#" + fileDivId).html(html);

                    $('#uploadDocumentInput').on('change', function (e) {
                        // 处理自己的业务
                        let files = $("#uploadDocumentInput")[0].files;
                        if (files.length > 0) {
                            $.barracuda.document.filesToUpload.push(...files);
                            $.barracuda.document.showFilesName();
                        }
                    });
                },
                showFilesName: function () {
                    let filesNameDiv = $(".files-name-div");
                    filesNameDiv.html('');
                    for (let i = 0; i < $.barracuda.document.filesToUpload.length; i++) {
                        let e = $.barracuda.document.filesToUpload[i];
                        filesNameDiv.append('<div class="file-name">' + e.name + '<a onclick="$.barracuda.document.deleteThisFile(' + i + ')" class="fa fa-times delete-file"></a></div>');
                    }
                },
                deleteThisFile: function (index) {
                    $.barracuda.document.filesToUpload[index] = null;
                    $.barracuda.document.filesToUpload = $.barracuda.document.filesToUpload.filter(e => {
                        return e != null
                    });
                    $.barracuda.document.showFilesName();
                }
            }
        }
    });
})(window.jQuery);
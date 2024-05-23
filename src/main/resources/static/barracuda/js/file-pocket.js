// 文件袋 用于拖拽上传文件
let FilePocketJs = function ($) {
    function uploadFile(file) {
        let resultDocument = undefined;
        let data = new FormData();
        data.append("files", file);
        $.ajax({
            async: false,
            type: "post",
            url: ctx + "document/document/upload",
            data: data,
            cache: false,
            contentType: false,
            processData: false,
            dataType: 'json',
            success: function (result) {
                if (result.code === web_status.SUCCESS) {
                    let resultData = result.data;
                    if (resultData.length > 0) {
                        resultDocument = resultData[0];
                    }
                } else {
                    $.modal.alertError(result.msg);
                }
            },
            error: function () {
                $.modal.alertWarning("文件上传失败。");
            }
        });
        return resultDocument;
    }

    function setUploadedDocumentHtml(uploadedDocument) {
        let documentId = uploadedDocument.id;
        let documentName = uploadedDocument.documentName;
        let html = '<div data-id="' + documentId + '" class="file-name-div">\n' +
            '            <p>' + documentName +
            '                <a onclick="$(this).closest(\'div\').remove()" class="delete-file-btn">' +
            '                   <i class="fa fa-close"></i>' +
            '               </a>\n' +
            '            </p>\n' +
            '        </div>';
        $(".file-display-area").append(html);
    }

    let FilePocket = class {
        containerId;

        constructor(id) {
            this.containerId = id;
            this.initContainerHtml(this.containerId);
        }

        listDocumentIds() {
            let result = [];
            let fileNameDiv = $("#" + this.containerId + " .file-name-div");
            fileNameDiv.each(function () {
                result.push($(this).data("id"));
            })
            return result;
        }

        // 设置文件
        setDocuments(files) {
            for (let file of files) {
                setUploadedDocumentHtml(file);
            }
        }

        initContainerHtml(containerId) {
            let containerHtml = `
                <div class="file-drop-area">
                    <label class="file-pocket-notice">
                    将文件拖放到此处
                    </label>
                </div>
                <div class="file-display-area">
                    
                </div>
                `;

            $("#" + containerId).html(containerHtml);
            $("#" + containerId).addClass("file-pocket-container");


            let container = document.getElementById(containerId);

            // 拖动元素在目标元素上悬停期间
            container.addEventListener('dragover', (e) => {
                // 必须写
                // 阻止默认行为，允许放置
                e.stopPropagation();
                // 阻止事件冒泡
                e.preventDefault();
            });

            container.addEventListener('drop', function (e) {
                // 必须写
                // 阻止默认行为，允许放置
                e.stopPropagation();
                // 阻止事件冒泡
                e.preventDefault();
                // 文件上传处理逻辑 获取拖放的文件列表
                let items = e.dataTransfer.items;
                // 处理文件上传逻辑...
                for (const item of items) {
                    // 提取Entry对象
                    const entry = item.webkitGetAsEntry();
                    if (entry.isFile) {
                        // 处理文件，拿到File文件
                        entry.file((file) => {
                            let uploadedDocument = uploadFile(file);
                            setUploadedDocumentHtml(uploadedDocument);
                        })
                    } else {
                        // 处理文件夹，拿到FileEntry对象
                        const reader = entry.createReader()
                        reader.readEntries((entries) => {
                            reHandleFile(entries);
                        })
                    }
                }

            });

            // 如果文件夹是多级，则递归读取
            const reHandleFile = (entries) => {
                for (const entry of entries) {
                    if (entry.isFile) {
                        // 处理文件，拿到File文件
                        entry.file((file) => {
                            let uploadedDocument = uploadFile(file);
                            setUploadedDocumentHtml(uploadedDocument);
                        })
                    } else {
                        // 处理文件夹，拿到FileEntry对象
                        const reader = entry.createReader()
                        reader.readEntries((entries) => {
                            // 递归
                            reHandleFile(entries)
                        })
                    }
                }
            }
        }
    }


    let createFilePocket = function (filePocketId) {
        return new FilePocket(filePocketId)
    }


    return {
        createFilePocket
    }
}(jQuery);
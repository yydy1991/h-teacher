package com.barracuda.barracudateacher.tool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    private FileUtil() {
    }

    public static List<File> listFilesRecursively(File dir) {
        List<File> result = new ArrayList<>();
        // 检查给定路径是否是一个目录
        if (dir.isDirectory()) {
            // 获取目录中的文件和子目录
            File[] files = dir.listFiles();
            // 如果目录不为空
            if (files != null) {
                // 遍历文件和子目录
                for (File file : files) {
                    // 如果是文件，打印文件名
                    if (file.isFile()) {
                        result.add(file);
                    }
                    // 如果是目录，递归调用
                    else if (file.isDirectory()) {
                        listFilesRecursively(file);
                    }
                }
            }
        } else {
            result.add(dir);
        }
        return result;
    }
}

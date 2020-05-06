package com.leo.security;

import java.io.File;
import java.io.IOException;
import java.security.AccessControlException;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 *
 */
public class FileUtil {

    private final static String FOLDER_PATH = "/Users/qiaojian/Downloads/";

    public static void makeFile(String fileName) {
        try {
            File fs = new File(FOLDER_PATH + fileName);
            fs.createNewFile();
        } catch (AccessControlException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void doPrivilegedAction(final String fileName) {
        // 用特权访问方式创建文件
        AccessController.doPrivileged( new PrivilegedAction<String> () {
            @Override
            public String run() {
                makeFile(fileName);
                return null;
            }
        });
    }

}

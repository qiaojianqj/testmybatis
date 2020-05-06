package com.leo.security;

import java.io.File;
import java.io.IOException;
import java.security.AccessControlException;

/**
 *
 */
public class PrivilegeTest {
    public static void main(String[] args) {
        System.out.println("***************************************");
        System.out.println("I will show AccessControl functionality...");

        System.out.println("Preparation step : turn on system permission check...");
        // 打开系统安全权限检查开关，添加vm options: -Djava.security.manager
        // 使用特定policy，添加vm options: -Djava.security.policy=privilegetest.policy
        System.out.println();

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Create a new file named privilege1.txt via privileged action ...");
        FileUtil.doPrivilegedAction("privilege1.txt");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();

        System.out.println("/////////////////////////////////////////");
        System.out.println("Create a new file named privilege2.txt via File ...");
        try {
            File fs = new File (
                    "/Users/qiaojian/Downloads/privilege2.txt");
            fs.createNewFile();
        } catch (IOException | AccessControlException e) {
            e.printStackTrace();
        }
        System.out.println("/////////////////////////////////////////");
        System.out.println();

        System.out.println("-----------------------------------------");
        System.out.println("create a new file named privilege3.txt via FileUtil ...");
        FileUtil.makeFile("privilege3.txt");
        System.out.println("-----------------------------------------");
        System.out.println();

        System.out.println("***************************************");

        // s1 = new String(), 因此 s1 引用指向一块新的堆内存 存放"StringTest"
        //String s1 = new StringBuilder().append("String").append("Test").toString();
        String s1 = new String("StringTest");
        //s1.intern() 触发先到字符串常量池去找 "StringTest" ，没找到
        //s1.intern() 然后会将堆中的"StringTest"引用存到字符串常量池，并返回其引用
        //因此，此时s1.intern()返回的引用和s1的引用是一样的
        System.out.println(s1.intern() == s1);

        //同理，
        // s2 = new String(), 因此s2引用指向一块新的堆内存，存放"java"
        // s2.intern() 触发先到字符串常量池去找 "java"，由于java字符串是保留字符串，常量池已有，
        // 因此，此时s2.intern()返回的引用是常量池已有的java字符串引用 和 s2的引用不一样
        //String s2 = new StringBuilder().append("ja").append("va").toString();
        String s2 = new String("java");
        System.out.println(s2.intern() == s2);

        String s3 = "java";
        System.out.println(s3.intern() == s3);

    }


}

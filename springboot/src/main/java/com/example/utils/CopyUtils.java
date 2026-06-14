package com.example.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;

public class CopyUtils {
    private static String packagePath = System.getProperty("user.dir") + "/springboot/src/main/java/com/example";
    private static String resourcesPath = System.getProperty("user.dir") + "/springboot/src/main/resources";
    private static String vuePath = System.getProperty("user.dir") + "/vue/src/views/manager";

    private static String controllerSuffix = "Controller.java";
    private static String serviceSuffix = "Service.java";
    private static String mapperSuffix = "Mapper.java";
    private static String mapperXmlSuffix = "Mapper.xml";
    private static String vueSuffix = ".vue";

    // Small utility for copying code
    public static void main(String[] args) {
        // Prefix of the source file to copy
        String sourceName = "Category";
        // Prefix of the target file after copying
        String targetName = "Goods";

        // Copy the full set: Controller, Service, Mapper, Mapper.xml
        copyMapperXml(sourceName, targetName);
        copyMapper(sourceName, targetName);
        copyService(sourceName, targetName);
        copyController(sourceName, targetName);

        // Copy Vue file
        copyVue(sourceName, targetName);
    }

    // Copy Mapper.xml
    public static void copyMapperXml(String sourceName, String targetName) {
        // Path prefix for mapper XML files
        String mapperXmlPathPrefix = resourcesPath + "/mapper/";
        // Read file content
        String content = FileUtil.readUtf8String(mapperXmlPathPrefix + sourceName + mapperXmlSuffix);
        // Replace file content
        String result1 = StrUtil.replace(content, sourceName, targetName); // Replace uppercase entity name
        String result = StrUtil.replace(result1, StrUtil.lowerFirst(sourceName), StrUtil.lowerFirst(targetName)); // Replace lowercase entity object name

        // Create target Mapper.xml
        File targetFile = FileUtil.touch(mapperXmlPathPrefix + targetName + mapperXmlSuffix);
        FileUtil.writeUtf8String(result, targetFile);
        System.out.println(targetName + mapperXmlSuffix + " copied successfully!");
    }


    // Copy Mapper interface
    public static void copyMapper(String sourceName, String targetName) {
        // Path prefix for mapper interfaces
        String mapperPathPrefix = packagePath + "/mapper/";
        // Read file content
        String content = FileUtil.readUtf8String(mapperPathPrefix + sourceName + mapperSuffix);
        // Replace file content
        String result1 = StrUtil.replace(content, sourceName, targetName); // Replace uppercase entity name
        String result = StrUtil.replace(result1, StrUtil.lowerFirst(sourceName), StrUtil.lowerFirst(targetName)); // Replace lowercase entity object name

        // Create target Mapper interface
        File targetFile = FileUtil.touch(mapperPathPrefix + targetName + mapperSuffix);
        FileUtil.writeUtf8String(result, targetFile);
        System.out.println(targetName + mapperSuffix + " copied successfully!");
    }

    // Copy Service
    public static void copyService(String sourceName, String targetName) {
        // Path prefix for services
        String servicePathPrefix = packagePath + "/service/";
        // Read file content
        String content = FileUtil.readUtf8String(servicePathPrefix + sourceName + serviceSuffix);
        // Replace file content
        String result1 = StrUtil.replace(content, sourceName, targetName); // Replace uppercase entity name
        String result = StrUtil.replace(result1, StrUtil.lowerFirst(sourceName), StrUtil.lowerFirst(targetName)); // Replace lowercase entity object name

        // Create target Service
        File targetFile = FileUtil.touch(servicePathPrefix + targetName + serviceSuffix);
        FileUtil.writeUtf8String(result, targetFile);
        System.out.println(targetName + serviceSuffix + " copied successfully!");
    }

    // Copy Controller
    public static void copyController(String sourceName, String targetName) {
        // Path prefix for controllers
        String controllerPathPrefix = packagePath + "/controller/";
        // Read file content
        String content = FileUtil.readUtf8String(controllerPathPrefix + sourceName + controllerSuffix);
        // Replace file content
        String result1 = StrUtil.replace(content, sourceName, targetName); // Replace uppercase entity name
        String result = StrUtil.replace(result1, StrUtil.lowerFirst(sourceName), StrUtil.lowerFirst(targetName)); // Replace lowercase entity object name

        // Create target Controller
        File targetFile = FileUtil.touch(controllerPathPrefix + targetName + controllerSuffix);
        FileUtil.writeUtf8String(result, targetFile);
        System.out.println(targetName + controllerSuffix + " copied successfully!");
    }

    // Copy Vue file
    public static void copyVue(String sourceName, String targetName) {
        // Read file content
        String content = FileUtil.readUtf8String(vuePath + "/" + sourceName + vueSuffix);
        // Replace file content
        String result = StrUtil.replace(content, "/" + StrUtil.lowerFirst(sourceName),  "/" + StrUtil.lowerFirst(targetName)); // Replace lowercase backend API path

        // Create target Vue file
        File targetFile = FileUtil.touch(vuePath + "/" + targetName + vueSuffix);
        FileUtil.writeUtf8String(result, targetFile);
        System.out.println(targetName + vueSuffix + " copied successfully!");
    }

}
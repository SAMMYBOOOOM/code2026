package com.example.controller;

import cn.hutool.core.io.FileUtil;
import com.example.common.Result;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Files controller
 */
@RestController
@RequestMapping("/files")
public class FileController {

    // Show file local storage path
    private static final String filePath = System.getProperty("user.dir") + "/files/";

    @Value("${fileBaseUrl}")
    private String fileBaseUrl;

    @Value("${server.port}")
    private String port;

    /**
     * upload
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) {
        // Define file's distinctive name
        String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
        // Patch the complete file path
        String realFilePath = filePath + fileName;
        try {
            if (!FileUtil.isDirectory(filePath)) {
                FileUtil.mkdir(filePath);
            }
            FileUtil.writeBytes(file.getBytes(), realFilePath);
        } catch (IOException e) {
            System.out.println("File upload error");
        }

        // return file download url
        String url = fileBaseUrl + ":" + port + "/files/download/" + fileName;
        return Result.success(url);
    }

    /**
     * download
     */
    @GetMapping("/download/{fileName}")
    public void download(@PathVariable String fileName, HttpServletResponse response) {
        // Set download file http response header
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        // Patch the complete file path
        String realFilePath = filePath + fileName;
        try {
            // Through file path get file bytes
            byte[] bytes = FileUtil.readBytes(realFilePath);
            ServletOutputStream os = response.getOutputStream();
            // Write file bytes to file stream
            os.write(bytes);
            os.flush();
            os.close();
        } catch (IOException e) {
            System.out.println("File download error");
        }
    }

}

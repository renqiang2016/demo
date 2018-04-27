package com.example.ren.controller.fastdfs;

import com.example.ren.utils.FastdfsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * fastdfs接口
 *
 * @author qiang.ren
 * @date 2018/4/26
 */
@RestController
@RequestMapping("/file")
public class FastdfsController {

    @Autowired
    private FastdfsClient fastdfsClient;

    // 上传文件
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(MultipartFile file) throws Exception {
        String fileUrl= fastdfsClient.uploadFile(file);
        return fileUrl;
    }

    // 上传图片并获得缩略图
    @RequestMapping(value = "/upload/small", method = RequestMethod.POST)
    public String uploadAndCrtThumbImage(MultipartFile file) throws Exception {
        String fileUrl= fastdfsClient.uploadImageAndCrtThumbImage(file);
        return fileUrl;
    }
}

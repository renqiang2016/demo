package com.example.ren.utils;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * fastdfs 客户端工具类
 * Created by qiang.ren on 2018/4/25.
 */
@Component
public class FastdfsClient {

    private final FastFileStorageClient storageClient;

    private static final String URL_HEADER = "http://192.168.71.161:88/";

    @Autowired
    public FastdfsClient(FastFileStorageClient storageClient) {
        this.storageClient = storageClient;
    }

    /**
     * 上传文件
     * @param file 文件
     * @return
     */
    public String uploadFile(MultipartFile file){
        String fileType = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();
        StorePath path = null;
        try {
            path = storageClient.uploadFile(file.getInputStream(), file.getSize(), fileType, null);
        }catch (IOException e){
            e.printStackTrace();
        }
        if(path != null) {
            return URL_HEADER + path.getFullPath();
        }else {
            return null;
        }
    }

    /**
     * 将一段字符串生成一个文件上传
     * @param content 文件内容
     * @param fileExtension 后缀(如.txt)
     * @return
     */
    public String uploadString(String content, String fileExtension) {
        byte[] buff = content.getBytes(Charset.forName("UTF-8"));
        ByteArrayInputStream stream = new ByteArrayInputStream(buff);
        StorePath storePath = storageClient.uploadFile(stream, buff.length, fileExtension,null);
        return URL_HEADER + storePath.getFullPath();
    }

    /**
     * 删除文件
     * @param fullPath 全名
     */
    public void deleteFile(String fullPath){
        storageClient.deleteFile(fullPath);
    }

    /**
     * 传图片并同时生成一个缩略图
     * "JPG", "JPEG", "PNG", "GIF", "BMP", "WBMP"
     * @param file 文件对象
     * @return 文件访问地址
     * @throws IOException
     */
    public String uploadImageAndCrtThumbImage(MultipartFile file) throws IOException{
        StorePath storePath = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(),file.getSize(),
                FilenameUtils.getExtension(file.getOriginalFilename()),null);
        return URL_HEADER + storePath.getFullPath();
    }
}

package com.example.ren.fastdfs;

import com.example.ren.utils.FastdfsClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Fastdfs测试
 * Created by qiang.ren on 2018/4/25.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FastdfsTests {

    private static Logger logger = LoggerFactory.getLogger(FastdfsTests.class);

    @Autowired
    private FastdfsClient fastdfsClient;

    @Test
    public void deleteTest(){
        String fullPath = "http://192.168.71.161:88/group1/M00/00/00/wKhHnlrih6yAMl7UAADg9OSIpuo967.png";
        fastdfsClient.deleteFile(fullPath);
    }

    @Test
    public void uploadStringTest(){
        String content = "hello world! 你好。";
        String extension = "txt";
        String result = fastdfsClient.uploadString(content, extension);
        logger.info("result = {}"+result);
    }
}

package com.example.ren.redis;

import com.example.ren.dao.redis.UserRedis;
import com.example.ren.dao.mysql.UserRepository;
import com.example.ren.model.mysql.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;

/**
 * redis测试类
 * Created by qiang.ren on 2018/4/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {

    private static Logger logger = LoggerFactory.getLogger(RedisTests.class);

    @Autowired
    UserRedis userRedis;

    @Autowired
    UserRepository userRepository;

    @Before
    public void setup(){
        /*Optional<User> optional = userRepository.findById(1L);
        userRedis.delete("user");
        userRedis.add("user", 10L, optional.get());*/
    }

    @Test
    public void get(){
        User user;
        Optional<User> optional = userRepository.findByName("任强");
        if (null == userRedis.get("user")){
            if (optional.isPresent()){
                user = optional.get();
                Assert.assertNotNull(user);
                logger.info("======user====== name:{}, deparment:{}, role:{}",
                        user.getName(), user.getDepartment().getName(), user.getRoles().get(0).getName());
                userRedis.add("user", 10L, user);
            }else {
                logger.info("数据库中没有这个人");
            }
        }else {
            user = userRedis.get("user");
            logger.info("======user====== name:{}, deparment:{}, role:{}",
                    user.getName(), user.getDepartment().getName(), user.getRoles().get(0).getName());
        }
    }
}

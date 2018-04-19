package com.example.ren.mongo;

import com.example.ren.dao.mongo.MongoUserRepository;
import com.example.ren.model.mongo.MongoUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * mongo测试
 * Created by qiang.ren on 2018/4/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTests {

    private static Logger logger = LoggerFactory.getLogger(MongoTests.class);

    @Autowired
    private MongoUserRepository mongoUserRepository;

    @Before
    public void setup(){
        Set<String> roles = new HashSet<>();
        roles.add("管理员");
        MongoUser mongoUser = new MongoUser("1", "Rabbit", "123456", "任强",
                "qiang.ren@phicomm.com", new Date(), roles);
        mongoUserRepository.deleteAll();
        mongoUserRepository.save(mongoUser);
    }

    @Test
    public void findAll(){
        List<MongoUser> mongoUsers = mongoUserRepository.findAll();
        Assert.notNull(mongoUsers);
        for(MongoUser user : mongoUsers){
            logger.info("===user=== userid:{}, username:{}, pass:{}, registrationDate:{}",
                    user.getUserId(), user.getUserName(), user.getPassword(), user.getRegistrationDate());
        }
    }
}

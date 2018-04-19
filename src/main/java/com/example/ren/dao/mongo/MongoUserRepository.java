package com.example.ren.dao.mongo;

import com.example.ren.model.mongo.MongoUser;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * MongoUser与mongo相关操作
 * Created by qiang.ren on 2018/4/16.
 */
public interface MongoUserRepository extends MongoRepository<MongoUser, String>{
    MongoUser findByUserName(String userName);
}
